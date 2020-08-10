package com.ruoyi.assetspackage.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.domain.TLcImportFlow;
import com.ruoyi.assetspackage.domain.TempCurAssetsPackage;
import com.ruoyi.assetspackage.domain.luckElephant.*;
import com.ruoyi.assetspackage.domain.score.TLcScore;
import com.ruoyi.assetspackage.enums.ImportTypeEnum;
import com.ruoyi.assetspackage.mapper.LuckElephantRemoteMapper;
import com.ruoyi.assetspackage.service.ILuckElephantRemoteService;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.assetspackage.service.ITLcImportFlowService;
import com.ruoyi.assetspackage.service.ITLcScoreService;
import com.ruoyi.framework.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guozeqi
 * @create 2020-08-06
 */
@Slf4j
@Service
public class LuckElephantRemoteServiceImpl implements ILuckElephantRemoteService {

    @Autowired
    private LuckElephantRemoteMapper luckElephantRemoteMapper;

    @Autowired
    private ITLcImportFlowService tlcImportFlowService;

    @Autowired
    private ITLcScoreService tlcScoreService;

    @Autowired
    private IOrgPackageService orgPackageService;


    @Override
    @Transactional
    public LuckElephantAddAssetResponse batchAddAssets(LuckElephantAddAssetRequest param,String curDate) throws Exception {
        //必输项校验
        LuckElephantAddAssetResponse response = this.checkAddData(param,curDate);
        if(response != null){
            return response;
        }
        String batchNo = param.getBatchNo();
        String orgId = param.getOrgId();
        String orgName = param.getOrgName();
        //参数构建
        List<TempCurAssetsPackage> tempCurAssetList = buildAddAssetParam(param);
        //插入临时表
        this.luckElephantRemoteMapper.batchAddTemp(tempCurAssetList);
        List<String> caseNoList = this.luckElephantRemoteMapper.selectUpdateList(batchNo);//查询已经存在的在催案件
        if(caseNoList.size() > 0){
            response = new LuckElephantAddAssetResponse();
            response.setRetTime(curDate);
            response.setRetCode(LuckElephantCodeEnum.error.getCode());
            response.setRetMsg("案件已经存在："+JSON.toJSONString(caseNoList));
            return response;
        }else{
            //导入总金额
            BigDecimal totalMoney = new BigDecimal(0.00);
            // 新增流水集合，如果案件是修改的话，不修改批次号
            List<TempCurAssetsPackage> tLcImportFlowList = new ArrayList<>(tempCurAssetList.size());
            //参数计算、赋值
            for (TempCurAssetsPackage tempCurAssetsPackage : tempCurAssetList) {
                totalMoney = totalMoney.add(tempCurAssetsPackage.getRmbYe() != null ? new BigDecimal(tempCurAssetsPackage.getRmbYe()) : new BigDecimal(0.00));
                tLcImportFlowList.add(tempCurAssetsPackage);
            }
            //插入主表
            this.luckElephantRemoteMapper.batchAddAssets(tempCurAssetList);
            //插入流水表
            if (tLcImportFlowList != null && tLcImportFlowList.size() > 0) {
                TLcImportFlow tLcImportFlow = new TLcImportFlow();
                tLcImportFlow.setImportBatchNo(batchNo)
                        .setImportType(ImportTypeEnum.ASSET_TEMPLETE.getCode())
                        .setOrgId(orgId)
                        .setOrgName(orgName)
                        .setTotalNum(tLcImportFlowList.size())
                        .setTotalMoney(totalMoney)
                        .setCreateBy("0000");
                this.tlcImportFlowService.insertTLcImportFlow(tLcImportFlow);
            }
            OrgPackage orgPackage = orgPackageService.selectOrgPackageByOrgId(orgId);
            if("1".equals(orgPackage.getIsAutoScore())){
                //插入催收评分表
                List<TLcScore> tLcScores = tlcScoreService.buildParam2(tempCurAssetList);
                tlcScoreService.batchInsert(tLcScores);
            }
        }

        response = new LuckElephantAddAssetResponse();
        response.setRetTime(curDate);
        response.setRetCode(LuckElephantCodeEnum.success.getCode());
        response.setRetMsg("成功");
        return response;
    }


    private List<TempCurAssetsPackage> buildAddAssetParam(LuckElephantAddAssetRequest param){
        //参数获取
        String batchNo = param.getBatchNo();
        String orgId = param.getOrgId();
        String orgName = param.getOrgName();
        List<LuckElephantAddAssetRequest.AddAssetEntity> facts = param.getFacts();
        //参数转换
        List<TempCurAssetsPackage> desList = new ArrayList<>(facts.size());
        facts.stream().forEach(data ->{
            TempCurAssetsPackage tempAsset = new TempCurAssetsPackage();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            tempAsset.setId(uuid);
            tempAsset.setImportBatchNo(batchNo);
            tempAsset.setOrgId(orgId);
            tempAsset.setOrg(orgName);
            tempAsset.setCreateBy("0000");
            tempAsset.setOrgCasno(data.getOrgCasno());
            tempAsset.setCurName(data.getCurName());
            tempAsset.setCurSex(data.getSex());
            tempAsset.setWorkName(data.getWorkName());
            tempAsset.setEmail(data.getEmail());
            tempAsset.setEducation(data.getEducation());
            tempAsset.setCustomerHomeAddress(data.getFamilyAddr());
            tempAsset.setRegistAddress(data.getHouseAddr());
            tempAsset.setCertificateNo(data.getCardCode());
            tempAsset.setWorkAddress(data.getWorkAddr());
            tempAsset.setMarriage(data.getMarriage());
            tempAsset.setWorkDept(data.getCurType());
            tempAsset.setBillAddress(data.getCity());
            tempAsset.setJkrq(data.getLoanTime());
            tempAsset.setFirstYqjcDate(data.getYhkDate());
            tempAsset.setRmbYhbjzje(data.getRmbYhbjzje());
            tempAsset.setRmbQkzje(data.getRmbQkzje());
            tempAsset.setRmbYe(data.getWaYe());//委案金额
            tempAsset.setWaYe(data.getWaYe());//结案应还金额
            tempAsset.setOverdueDays(data.getOverdueDays());
            tempAsset.setTransfertype(data.getTransfertype());
            tempAsset.setBorrowNo(data.getBorrowNo());
            tempAsset.setBorrowBlank(data.getBorrowBlank());
            tempAsset.setBorrowEd(data.getLoanAmount());
            tempAsset.setFz(data.getStagesNum());
            tempAsset.setYkqs(data.getPayStages());
            tempAsset.setMqhkje(data.getRmbZdyhje());
            tempAsset.setRmbYhlizje(data.getRmbYhlizje());
            tempAsset.setRmbYhfyzje(data.getRmbYhfyzje());
            tempAsset.setRmbYhfxzje(data.getRmbYhfxzje());
            tempAsset.setZnj(data.getZnj());
            tempAsset.setLjyhje(data.getLjyhje());
            tempAsset.setRcr(data.getRcr());
            tempAsset.setZhychkr(data.getLastRepayDate());
            tempAsset.setCustomerMobile(data.getCustomerMobile());
            tempAsset.setWorkTel(data.getWorkTel());
            tempAsset.setFirstLiaisonName(data.getFirstLiaisonName());
            tempAsset.setFirstLiaisonRelation(data.getFirstLiaisonRelation());
            tempAsset.setFirstLiaisonMobile(data.getFirstLiaisonMobile());
            tempAsset.setSecondLiaisonName(data.getSecondLiaisonName());
            tempAsset.setSecondLiaisonRelation(data.getSecondLiaisonRelation());
            tempAsset.setSecondLiaisonMobile(data.getSecondLiaisonMobile());
            tempAsset.setThreeLiaisonName(data.getThreeLiaisonName());
            tempAsset.setThreeLiaisonRelation(data.getThreeLiaisonRelation());
            tempAsset.setThreeLiaisonMobile(data.getThreeLiaisonMobile());
            //新增字段
            tempAsset.setCurNo(data.getCurNo());
            tempAsset.setPayStatus(data.getPayStatus());
            tempAsset.setLastLoanDate(data.getLastLoanDate());
            tempAsset.setLastRepayAmount(data.getLastRepayAmount());
            //todo 计算 退案日=入催日+委案周期
            String dealDays = data.getDealDays();//委案周期
            String tar = calcuDate(data.getRcr(), Integer.valueOf(dealDays));
            tempAsset.setTar(tar);
            desList.add(tempAsset);
        });

        return desList;
    }


    private LuckElephantAddAssetResponse checkAddData(LuckElephantAddAssetRequest param,String curDate){
        LuckElephantAddAssetResponse response = null;
        //参数获取
        String batchNo = param.getBatchNo();//批次号
        String orgId = param.getOrgId();//机构编号
        String orgName = param.getOrgName();//机构名称
        String sysCode = param.getSysCode();//数据来源
        String frontTransTime = param.getFrontTransTime();//交易时间
        response = checkNull(batchNo, curDate, "批次号","add") != null ? (LuckElephantAddAssetResponse)checkNull(batchNo, curDate, "批次号","add") : null;
        response = checkNull(orgId, curDate, "机构编号","add") != null ? (LuckElephantAddAssetResponse)checkNull(orgId, curDate, "机构编号","add") : null;
        response = checkNull(orgName, curDate, "机构名称","add") != null ? (LuckElephantAddAssetResponse)checkNull(orgName, curDate, "机构名称","add") : null;
        response = checkNull(sysCode, curDate, "数据来源","add") != null ? (LuckElephantAddAssetResponse)checkNull(sysCode, curDate, "数据来源","add") : null;
        response = checkNull(frontTransTime, curDate, "交易时间","add") != null ? (LuckElephantAddAssetResponse)checkNull(frontTransTime, curDate, "交易时间","add") : null;
        if(response!=null){
            return response;
        }
        //数据校验
        List<LuckElephantAddAssetRequest.AddAssetEntity> facts = param.getFacts();
        if(facts.size() > 0){
            for (LuckElephantAddAssetRequest.AddAssetEntity data : facts) {
                String orgCasno = data.getOrgCasno();//机构案件号
                String curName = data.getCurName();//客户姓名
                String sex = data.getSex();//性别
                String workName = data.getWorkName();//单位名称
                String education = data.getEducation();//教育水平
                String curNo = data.getCurNo();//用户编号
                String cardCode = data.getCardCode();//证件号码
                String workAddr = data.getWorkAddr();//单位地址
                String marriage = data.getMarriage();//婚姻状况
                String dealDays = data.getDealDays();//委案周期
                String loanTime = data.getLoanTime();//借款时间
                String payStatus = data.getPayStatus();//还款状态
                String yhkDate = data.getYhkDate();//最近应还日
                String lastLoanDate = data.getLastLoanDate();//贷款到期日
                String rmbYhbjzje = data.getRmbYhbjzje();//本期应还本金
                String rmbQkzje = data.getRmbQkzje();//贷款余额
                String waYe = data.getWaYe();//逾期待还金额
                String overdueDays = data.getOverdueDays();//逾期天数
                String transfertype = data.getTransfertype();//逾期阶段
                String borrowNo = data.getBorrowNo();//还款卡账号
                String lastRepayAmount = data.getLastRepayAmount();//最近还款金额
                String borrowBlank = data.getBorrowBlank();//还款卡银行
                String loanAmount = data.getLoanAmount();//借款金额
                String stagesNum = data.getStagesNum();//借款期次
                String payStages = data.getPayStages();//已还清期数
                String rmbYhfxzje = data.getRmbYhfxzje();//逾期利息
                String znj = data.getZnj();//担保违约金
                String ljyhje = data.getLjyhje();//总已还金额
                String rcr = data.getRcr();//入催日期
                String lastRepayDate = data.getLastRepayDate();//最近还款时间
                String customerMobile = data.getCustomerMobile();//本人手机号
                //非空校验
                response = checkNull(orgCasno, curDate, "机构案件号","add") != null ? (LuckElephantAddAssetResponse)checkNull(orgCasno, curDate, "机构案件号","add") : null;
                response = checkNull(curName, curDate, "客户姓名","add") != null ? (LuckElephantAddAssetResponse)checkNull(curName, curDate, "客户姓名","add") : null;
                response = checkNull(sex, curDate, "性别","add") != null ? (LuckElephantAddAssetResponse)checkNull(sex, curDate, "性别","add") : null;
                response = checkNull(workName, curDate, "单位名称","add") != null ? (LuckElephantAddAssetResponse)checkNull(workName, curDate, "单位名称","add") : null;
                response = checkNull(education, curDate, "教育水平","add") != null ? (LuckElephantAddAssetResponse)checkNull(education, curDate, "教育水平","add") : null;
                response = checkNull(curNo, curDate, "用户编号","add") != null ? (LuckElephantAddAssetResponse)checkNull(curNo, curDate, "用户编号","add") : null;
                response = checkNull(cardCode, curDate, "证件号码","add") != null ? (LuckElephantAddAssetResponse)checkNull(cardCode, curDate, "证件号码","add") : null;
                response = checkNull(workAddr, curDate, "单位地址","add") != null ? (LuckElephantAddAssetResponse)checkNull(workAddr, curDate, "单位地址","add") : null;
                response = checkNull(marriage, curDate, "婚姻状况","add") != null ? (LuckElephantAddAssetResponse)checkNull(marriage, curDate, "婚姻状况","add") : null;
                response = checkNull(dealDays, curDate, "委案周期","add") != null ? (LuckElephantAddAssetResponse)checkNull(dealDays, curDate, "委案周期","add") : null;
                response = checkNull(loanTime, curDate, "借款时间","add") != null ? (LuckElephantAddAssetResponse)checkNull(loanTime, curDate, "借款时间","add") : null;
                response = checkNull(payStatus, curDate, "还款状态","add") != null ? (LuckElephantAddAssetResponse)checkNull(payStatus, curDate, "还款状态","add") : null;
                response = checkNull(yhkDate, curDate, "最近应还日","add") != null ? (LuckElephantAddAssetResponse)checkNull(yhkDate, curDate, "最近应还日","add") : null;
                response = checkNull(lastLoanDate, curDate, "贷款到期日","add") != null ? (LuckElephantAddAssetResponse)checkNull(lastLoanDate, curDate, "贷款到期日","add") : null;
                response = checkNull(rmbYhbjzje, curDate, "本期应还本金","add") != null ? (LuckElephantAddAssetResponse)checkNull(rmbYhbjzje, curDate, "本期应还本金","add") : null;
                response = checkNull(rmbQkzje, curDate, "贷款余额","add") != null ? (LuckElephantAddAssetResponse)checkNull(rmbQkzje, curDate, "贷款余额","add") : null;
                response = checkNull(waYe, curDate, "逾期待还金额","add") != null ? (LuckElephantAddAssetResponse)checkNull(waYe, curDate, "逾期待还金额","add") : null;
                response = checkNull(overdueDays, curDate, "逾期天数","add") != null ? (LuckElephantAddAssetResponse)checkNull(overdueDays, curDate, "逾期天数","add") : null;
                response = checkNull(transfertype, curDate, "逾期阶段","add") != null ? (LuckElephantAddAssetResponse)checkNull(transfertype, curDate, "逾期阶段","add") : null;
                response = checkNull(borrowNo, curDate, "还款卡账号","add") != null ? (LuckElephantAddAssetResponse)checkNull(borrowNo, curDate, "还款卡账号","add") : null;
                response = checkNull(lastRepayAmount, curDate, "最近还款金额","add") != null ? (LuckElephantAddAssetResponse)checkNull(lastRepayAmount, curDate, "最近还款金额","add") : null;
                response = checkNull(borrowBlank, curDate, "还款卡银行","add") != null ? (LuckElephantAddAssetResponse)checkNull(borrowBlank, curDate, "还款卡银行","add") : null;
                response = checkNull(loanAmount, curDate, "借款金额","add") != null ? (LuckElephantAddAssetResponse)checkNull(loanAmount, curDate, "借款金额","add") : null;
                response = checkNull(stagesNum, curDate, "借款期次","add") != null ? (LuckElephantAddAssetResponse)checkNull(stagesNum, curDate, "借款期次","add") : null;
                response = checkNull(payStages, curDate, "已还清期数","add") != null ? (LuckElephantAddAssetResponse)checkNull(payStages, curDate, "已还清期数","add") : null;
                response = checkNull(rmbYhfxzje, curDate, "逾期利息","add") != null ? (LuckElephantAddAssetResponse)checkNull(rmbYhfxzje, curDate, "逾期利息","add") : null;
                response = checkNull(znj, curDate, "担保违约金","add") != null ? (LuckElephantAddAssetResponse)checkNull(znj, curDate, "担保违约金","add") : null;
                response = checkNull(ljyhje, curDate, "总已还金额","add") != null ? (LuckElephantAddAssetResponse)checkNull(ljyhje, curDate, "总已还金额","add") : null;
                response = checkNull(rcr, curDate, "入催日期","add") != null ? (LuckElephantAddAssetResponse)checkNull(rcr, curDate, "入催日期","add") : null;
                response = checkNull(lastRepayDate, curDate, "最近还款时间","add") != null ? (LuckElephantAddAssetResponse)checkNull(lastRepayDate, curDate, "最近还款时间","add") : null;
                response = checkNull(customerMobile, curDate, "本人手机号","add") != null ? (LuckElephantAddAssetResponse)checkNull(customerMobile, curDate, "本人手机号","add") : null;
                if(response != null){
                    String retMsg = response.getRetMsg();
                    retMsg = retMsg + "案件编号："+orgCasno;
                    response.setRetMsg(retMsg);
                    return response;
                }
            }
        }
        return response;
    }



    @Override
    public LuckElephantUpdateAssetResponse batchUpdateAssets(LuckElephantUpdateAssetRequest param,String curDate) throws Exception {
        return null;
    }

    @Override
    public LuckElephantRepaymentAssetResponse batchRepaymentAssets(LuckElephantRepaymentAssetRequest param,String curDate) throws Exception {
        return null;
    }




    private Object checkNull(String tarParam,String curDate,String remark,String requestFlag){
        if("add".equals(requestFlag)){
            LuckElephantAddAssetResponse response = null;
            if(tarParam==null || "".equals(tarParam)){
                response = new LuckElephantAddAssetResponse();
                response.setRetTime(curDate);
                response.setRetCode(LuckElephantCodeEnum.error.getCode());
                response.setRetMsg(remark+"为空.");
            }
            return response;
        }
        if("update".equals(requestFlag)){
            LuckElephantUpdateAssetResponse response = null;
            if(tarParam==null || "".equals(tarParam)){
                response = new LuckElephantUpdateAssetResponse();
                response.setRetTime(curDate);
                response.setRetCode(LuckElephantCodeEnum.error.getCode());
                response.setRetMsg(remark+"为空.");
            }
            return response;
        }
        if("repayment".equals(requestFlag)){
            LuckElephantRepaymentAssetResponse response = null;
            if(tarParam==null || "".equals(tarParam)){
                response = new LuckElephantRepaymentAssetResponse();
                response.setRetTime(curDate);
                response.setRetCode(LuckElephantCodeEnum.error.getCode());
                response.setRetMsg(remark+"为空.");
            }
            return response;
        }

        return null;
    }

    private Object checkJE(String tarParam,String curDate,String requestFlag){
        if("add".equals(requestFlag)){
            LuckElephantAddAssetResponse response = null;
            if(!checkJE2(tarParam)){
                response = new LuckElephantAddAssetResponse();
                response.setRetTime(curDate);
                response.setRetCode(LuckElephantCodeEnum.error.getCode());
                response.setRetMsg(tarParam+"金额格式不正确");
            }
            return response;
        }
        if("update".equals(requestFlag)){
            LuckElephantUpdateAssetResponse response = null;
            if(!checkJE2(tarParam)){
                response = new LuckElephantUpdateAssetResponse();
                response.setRetTime(curDate);
                response.setRetCode(LuckElephantCodeEnum.error.getCode());
                response.setRetMsg(tarParam+"金额格式不正确");
            }
            return response;
        }
        if("repayment".equals(requestFlag)){
            LuckElephantRepaymentAssetResponse response = null;
            if(!checkJE2(tarParam)){
                response = new LuckElephantRepaymentAssetResponse();
                response.setRetTime(curDate);
                response.setRetCode(LuckElephantCodeEnum.error.getCode());
                response.setRetMsg(tarParam+"金额格式不正确");
            }
            return response;
        }

        return null;
    }

    private Object checkRQ(String tarParam,String pattern,String curDate,String requestFlag){
        if("add".equals(requestFlag)){
            LuckElephantAddAssetResponse response = null;
            if(!checkRQ2(tarParam,pattern)){
                response = new LuckElephantAddAssetResponse();
                response.setRetTime(curDate);
                response.setRetCode(LuckElephantCodeEnum.error.getCode());
                response.setRetMsg(tarParam+"日期格式不正确");
            }
            return response;
        }
        if("update".equals(requestFlag)){
            LuckElephantUpdateAssetResponse response = null;
            if(!checkRQ2(tarParam,pattern)){
                response = new LuckElephantUpdateAssetResponse();
                response.setRetTime(curDate);
                response.setRetCode(LuckElephantCodeEnum.error.getCode());
                response.setRetMsg(tarParam+"日期格式不正确");
            }
            return response;
        }
        if("repayment".equals(requestFlag)){
            LuckElephantRepaymentAssetResponse response = null;
            if(!checkRQ2(tarParam,pattern)){
                response = new LuckElephantRepaymentAssetResponse();
                response.setRetTime(curDate);
                response.setRetCode(LuckElephantCodeEnum.error.getCode());
                response.setRetMsg(tarParam+"日期格式不正确");
            }
            return response;
        }

        return null;
    }


    private static boolean checkJE2(String value){
        String regex = "^[+]?(([0-9]\\d*[.]?)|(0.))(\\d{0,2})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean checkRQ2(String str, String formatString) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }



    private String calcuDate(String rcr, int days){
        String result = null;
        SimpleDateFormat sdf = null;
        if(rcr.contains("-")){
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }else{
            sdf = new SimpleDateFormat("yyyy/MM/dd");
        }
        try {
            Date date = sdf.parse(rcr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+days);
            result = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("计算退案日错误{}",e);
        }
        return result;
    }



}

