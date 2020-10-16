package com.ruoyi.assetspackage.service.impl;

import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.domain.score.TLcScore;
import com.ruoyi.assetspackage.mapper.AssetsImportFromXYMapper;
import com.ruoyi.assetspackage.mapper.CurAssetsPackageMapper;
import com.ruoyi.assetspackage.service.*;
import com.ruoyi.assetspackage.util.DataImportUtil;
import com.ruoyi.assetspackage.util.ParseExcelUtil;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.domain.CloseCase;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * 客户资产Service业务层处理
 *
 * @author guozeqi
 * @date 2020-07-13
 */
@Slf4j
@Service
public class AssetsImportFromXYServiceImpl extends BaseController implements IAssetsImportFromXYService {

    @Autowired
    private CurAssetsPackageMapper curAssetsPackageMapper;
    @Autowired
    RemoteConfigure remoteConfigure;
    @Autowired
    private IOrgPackageService orgPackageService;
    @Autowired
    private ITemplatesPackageService templatesPackageService;
    @Autowired
    private ITemplateRelationPackageService templateRelationPackageService;
    @Autowired
    private ITLcScoreService tlcScoreService;
    @Autowired
    private AssetsImportFromXYMapper assetsImportFromXYMapper;
    @Autowired
    private CurAssetsRepaymentPackageServiceImpl curAssetsRepaymentPackageServiceImpl;



    @Override
    @Transactional
    public void handler(HttpServletRequest request, MultipartFile file, String templateId, String orgId, String importBatchNo) throws Exception {
        List<TempCurAssetsPackage> paramList2 = null;
        InputStream stream = null;
        String fileName = "";
        String fileNameFull = "";
        TemplatesPackage templatesPackage = this.templatesPackageService.selectTemplatesPackageById(templateId);
        // 本地资源路径
        String localPath = Global.getProfile();
        // 数据库资源地址
        String uploadDir = localPath + StringUtils.substringAfter(templatesPackage.getUrl(), Constants.RESOURCE_PREFIX);
        File dirPath = new File(uploadDir);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        if (file != null) {
            stream = file.getInputStream();
            fileName = file.getOriginalFilename();
            String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);

            //组装文件路径写入磁盘
            fileNameFull = uploadDir + fileName;
            OutputStream bos = new FileOutputStream(fileNameFull);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.close();
            stream.close();
            ImportDataMapping importDataMapping = SpringUtils.getBean(ImportDataMapping.class);
            importDataMapping = this.voluation(importDataMapping, templateId);
            int headNum = Integer.valueOf(importDataMapping.getHeadRowNum());
            int dataNum = Integer.valueOf(importDataMapping.getDataRowNum());
            List<Map<String, String>> datas = ParseExcelUtil.resolveExcel(fileNameFull, headNum, dataNum);
            datas = DataImportUtil.dataReplace(datas, importDataMapping);
            OrgPackage orgPackage = this.orgPackageService.selectOrgPackageByDeptId(orgId);
            String orgName = orgPackage.getOrgName();
            paramList2 = DataImportUtil.dataConvert(datas, orgId,importBatchNo,orgName);
            //插入临时表
            this.batchAddTemp(paramList2);
            datas = null;
            //插入文件统计表
            long size = file.getSize();
            BigDecimal bigD1 = new BigDecimal(size);
            BigDecimal bigD2 = new BigDecimal(1024L);
            String fileSize = bigD1.divide(bigD2,1,BigDecimal.ROUND_HALF_UP).toString();
            Map<String,Object> fileParam = new HashMap<>();
            fileParam.put("fileName",fileName);
            fileParam.put("fileSize",fileSize);
            fileParam.put("orgId",orgId);
            fileParam.put("importBatchNo",importBatchNo);
            fileParam.put("createBy",ShiroUtils.getLoginName());
            this.assetsImportFromXYMapper.insetFile(fileParam);
        }
    }


    public void batchAddTemp(List<TempCurAssetsPackage> paramList)throws Exception{

        int total = paramList.size();
        int index = 500;
        int pagesize = total/index;
        if(total <=index){
            this.curAssetsPackageMapper.batchAddTemp(paramList);
        }else{
            for(int i=0;i<pagesize;i++){
                List lt = paramList.subList(i*index, (i+1)*index);
                this.curAssetsPackageMapper.batchAddTemp(lt);

            }
            if(total % index != 0){
                List lt = paramList.subList(index * pagesize,total);
                this.curAssetsPackageMapper.batchAddTemp(lt);
            }
        }

    }

    /**
     * 兴业校验与统计
     * @param request
     * @param importBatchNo
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Map<String, String> checkData(HttpServletRequest request, String importBatchNo) throws Exception{
        Map<String, String> map = new HashMap<String, String>();
        List<Map<String, String>> exectionList = null;
        //查询临时表信息
        TempCurAssetsPackage param = new TempCurAssetsPackage();
        param.setImportBatchNo(importBatchNo);
        List<TempCurAssetsPackage> tempCurAssetList = this.curAssetsPackageMapper.selectTempCurAssetsPackageList(param);
        //规则校验
        exectionList = DataImportUtil.checkData2(tempCurAssetList);
        //去除异常数据
        if(exectionList.size() > 0) {
            for (Map<String, String> exceptionidmap : exectionList) {
                String exceptionid = exceptionidmap.get("id");
                //更新状态
                this.curAssetsPackageMapper.updateExceptionStatus(exceptionid);
            }
        }
        //查询需要更新的数据集合
        int updateCount = this.curAssetsPackageMapper.selectUpdateCount(importBatchNo);
        //修改状态为需要更新状态
        if(updateCount>0){
            this.curAssetsPackageMapper.modifyUpdateStatus2(importBatchNo);
        }
        //查询需要新增的数据集合
        int insertCount = this.curAssetsPackageMapper.selectInsertCount(importBatchNo);

        request.getSession().setAttribute("exectionList",exectionList);
        map.put("insertSize",String.valueOf(insertCount));
        map.put("updateSize",String.valueOf(updateCount));
        map.put("exectionSize",String.valueOf(exectionList.size()));

        /** 出催统计开始 */
        String orgId = tempCurAssetList.get(0).getOrgId();
        String orgName= tempCurAssetList.get(0).getOrg();
        Date createTime = tempCurAssetList.get(0).getCreateTime();
        request.getSession().setAttribute("urgeCreateTime",createTime);//临时表的创建时间
        List<CurAssetsPackage> preSettleList = null;
        List<CurAssetsPackage> urgeList = null;
        List<CurAssetsPackage> partRepaymentList = null;
        int urgeNum = 0;
        //查询上一次导入的数据（比较的数据）
        Map<String,Object> paramMap2 = new HashMap<>();
        paramMap2.put("orgId",orgId);
        paramMap2.put("closeCase",1);
        Long Uptotal = this.assetsImportFromXYMapper.selectUpNotCompareTotal(paramMap2);
        if(Uptotal != null && Uptotal > 0){//上一次存在没有比较的数据
            preSettleList = this.assetsImportFromXYMapper.findPreSettleList(orgId,importBatchNo);//回收结案
            urgeList = this.assetsImportFromXYMapper.findUrgeList(orgId,importBatchNo);//出催案件
            partRepaymentList = this.assetsImportFromXYMapper.findPartRepaymentList(orgId,importBatchNo);//部分还款
            urgeNum = urgeList.size() + partRepaymentList.size();
            //插入出催表
            if(urgeList.size()>0){
                this.insertUrgeTemp(urgeList,"1",createTime,importBatchNo);//预测结清
            }
            if(partRepaymentList.size()>0){
                this.insertUrgeTemp(partRepaymentList,"2",createTime,importBatchNo);//部分还款
            }

        }

        request.getSession().setAttribute("preSettleList",preSettleList);//回收结案=需要结案-出催案件
        request.getSession().setAttribute("urgeList",urgeList);//出催案件
        map.put("importBatchNo",importBatchNo);
        map.put("orgId",orgId);
        map.put("orgName",orgName);
        map.put("totalNum",String.valueOf(tempCurAssetList.size()));
        map.put("addNum",String.valueOf(insertCount));
        map.put("modifyNum",String.valueOf(updateCount));
        map.put("urgeNum",String.valueOf(urgeNum));
        map.put("createBy",ShiroUtils.getLoginName());
        return map;
    }


    /**
     * 批量添加资产
     * @param paramList
     * @throws Exception
     */
    @Override
    public void batchAddAssets(List<TempCurAssetsPackage> paramList) throws Exception {
        String orgId = paramList.get(0).getOrgId();
        //把这一次导入的数据插入数据库
        int total = paramList.size();
        int index = 500;
        int pagesize = total/index;
        if(total <=index){
            this.assetsImportFromXYMapper.batchAddAssets(paramList);
        }else{
            for(int i=0;i<pagesize;i++){
                List lt = paramList.subList(i*index, (i+1)*index);
                this.assetsImportFromXYMapper.batchAddAssets(lt);

            }
            if(total % index != 0){
                List lt = paramList.subList(index * pagesize,total);
                this.assetsImportFromXYMapper.batchAddAssets(lt);
            }
        }
        OrgPackage orgPackage = orgPackageService.selectOrgPackageByOrgId(orgId);
        if("1".equals(orgPackage.getIsAutoScore())){
            //插入催收评分表
            List<TLcScore> tLcScores = tlcScoreService.buildParam(paramList);
            tlcScoreService.batchInsert(tLcScores);
        }
    }



    @Override
    public int deleteFlowXyByBatchNo(String importBatchNo){
        return this.assetsImportFromXYMapper.deleteFlowXyByBatchNo(importBatchNo);
    }

   /* @Override
    public void batchUpdateIsCompare(List<CurAssetsPackage> CurAssetsList) {
        this.assetsImportFromXYMapper.batchUpdateIsCompare(CurAssetsList);
    }*/

    @Override
    public List<TLcImportFlowForXy> selectFlowList(TLcImportFlowForXy param) throws Exception {
        if (param.getEndCreateTime() != null) {
            param.setEndCreateTime(DateUtils.getEndOfDay(param.getEndCreateTime()));
        }
        return this.assetsImportFromXYMapper.selectFlowList(param);
    }

    @Override
    public List<Map<String, Object>> selectFileList(String importBatchNo) throws Exception {
        return this.assetsImportFromXYMapper.selectFileList(importBatchNo);
    }

    @Override
    public List<TLcUrge> selectUrgeList(TLcUrge TLcUrge){
        if (TLcUrge.getEndCreateTime() != null) {
            TLcUrge.setEndCreateTime(DateUtils.getEndOfDay(TLcUrge.getEndCreateTime()));
        }
        return this.assetsImportFromXYMapper.selectUrgeList(TLcUrge);
    }


    public ImportDataMapping voluation(ImportDataMapping bean, String templateId) {
        /** 查询匹配关系 */
        //根据模板名称查询模板信息
        TemplatesPackage templateInfo = templatesPackageService.selectTemplatesPackageById(templateId);
        String headRowNum = templateInfo.getHeadRowNum();
        String dataRowNum = templateInfo.getDataRowNum();
        //根据模板id查询模板表头映射关系
        List<TemplateRelationPackage> relations = templateRelationPackageService.selectTemplateRelationPackageListBytemplateId(templateId);

        /** 给bean赋值 */
        bean.setHeadRowNum(headRowNum);//表头行
        bean.setDataRowNum(dataRowNum);//数据行
        for (TemplateRelationPackage relation : relations) {
            String systemTemplateName = relation.getSystemTemplateName();
            String customerTemplateName = relation.getCustomerTemplateName();

            if ("机构案件号".equals(systemTemplateName)) {
                bean.setOrgCasNo(customerTemplateName);
                continue;
            }
            if ("所属机构".equals(systemTemplateName)) {
                bean.setOrg(customerTemplateName);
                continue;
            }
            if ("案件回收时间".equals(systemTemplateName)) {
                bean.setAjhssj(customerTemplateName);
                continue;
            }
            if ("手别".equals(systemTemplateName)) {
                bean.setTransferType(customerTemplateName);
                continue;
            }
            if ("姓名".equals(systemTemplateName)) {
                bean.setCurName(customerTemplateName);
                continue;}
            if ("证件号".equals(systemTemplateName)) {
                bean.setCertificateNo(customerTemplateName);
                continue;}
            if ("性别".equals(systemTemplateName)) {
                bean.setCurSex(customerTemplateName);
                continue;}
            if ("婚姻状况".equals(systemTemplateName)) {
                bean.setMarriage(customerTemplateName);
                continue;}
            if ("教育程度".equals(systemTemplateName)) {
                bean.setEducation(customerTemplateName);
                continue;}
            if ("委案金额".equals(systemTemplateName)) {
                bean.setRmbYe(customerTemplateName);
                continue;}
            if ("结案应还金额".equals(systemTemplateName)) {
                bean.setWaYe(customerTemplateName);
                continue;}
            if ("入催日".equals(systemTemplateName)) {
                bean.setRcr(customerTemplateName);
                continue;}
            if ("账单日".equals(systemTemplateName)) {
                bean.setAccountDate(customerTemplateName);
                continue;}
            if ("逾期天数".equals(systemTemplateName)) {
                bean.setOverdueDays(customerTemplateName);
                continue;}
            if ("逾期起始日".equals(systemTemplateName)) {
                bean.setFirstYqDate(customerTemplateName);
                continue;}
            if ("应还日期".equals(systemTemplateName)) {
                bean.setFirstYqjcDate(customerTemplateName);
                continue;}
            if ("欠款总金额".equals(systemTemplateName)) {
                bean.setRmbQkzje(customerTemplateName);
                continue;}
            if ("最低应还金额".equals(systemTemplateName)) {
                bean.setRmbZdyhje(customerTemplateName);
                continue;}
            if ("应还本金".equals(systemTemplateName)) {
                bean.setRmbYhbjzje(customerTemplateName);
                continue;}
            if ("应还利息".equals(systemTemplateName)) {
                bean.setRmbYhlizje(customerTemplateName);
                continue;}
            if ("应还罚息".equals(systemTemplateName)) {
                bean.setRmbYhfxzje(customerTemplateName);
                continue;}
            if ("应还费用".equals(systemTemplateName)) {
                bean.setRmbYhfyzje(customerTemplateName);
                continue;}
            if ("滞纳金".equals(systemTemplateName)) {
                bean.setZnj(customerTemplateName);
                continue;}
            if ("所属城市".equals(systemTemplateName)) {
                bean.setWwCityName(customerTemplateName);
                continue;}
            if ("所属区域".equals(systemTemplateName)) {
                bean.setAreaCenter(customerTemplateName);
                continue;}
            if ("推荐商户".equals(systemTemplateName)) {
                bean.setTjFirm(customerTemplateName);
                continue;}
            if ("推荐网点".equals(systemTemplateName)) {
                bean.setTjWd(customerTemplateName);
                continue;}
            if ("产品名称".equals(systemTemplateName)) {
                bean.setCpmc(customerTemplateName);
                continue;}
            if ("还款方式".equals(systemTemplateName)) {
                bean.setHkType(customerTemplateName);
                continue;}
            if ("放款金额".equals(systemTemplateName)) {
                bean.setBorrowEd(customerTemplateName);
                continue;}
            if ("分期期数".equals(systemTemplateName)) {
                bean.setFz(customerTemplateName);
                continue;}
            if ("年利率".equals(systemTemplateName)) {
                bean.setYearRates(customerTemplateName);
                continue;}
            if ("日利率".equals(systemTemplateName)) {
                bean.setDayRates(customerTemplateName);
                continue;}
            if ("借款卡号".equals(systemTemplateName)) {
                bean.setBorrowNo(customerTemplateName);
                continue;}
            if ("借款卡银行".equals(systemTemplateName)) {
                bean.setBorrowBlank(customerTemplateName);
                continue;}
            if ("单位名称".equals(systemTemplateName)) {
                bean.setWorkName(customerTemplateName);
                continue;}
            if ("电子邮箱".equals(systemTemplateName)) {
                bean.setEmail(customerTemplateName);
                continue;}
            if ("单位地址".equals(systemTemplateName)) {
                bean.setWorkAddress(customerTemplateName);
                continue;}
            if ("住宅地址".equals(systemTemplateName)) {
                bean.setCustomerHomeAddress(customerTemplateName);
                continue;}
            if ("户籍地址".equals(systemTemplateName)) {
                bean.setRegistAddress(customerTemplateName);
                continue;}
            if ("身份证地址".equals(systemTemplateName)) {
                bean.setCertificateAddress(customerTemplateName);
                continue;}
            if ("账单地址".equals(systemTemplateName)) {
                bean.setBillAddress(customerTemplateName);
                continue;}
            if ("首逾标识".equals(systemTemplateName)) {
                bean.setFirstYqFlag(customerTemplateName);
                continue;}
            if ("最大逾期天数".equals(systemTemplateName)) {
                bean.setMaxYqtsHis(customerTemplateName);
                continue;}
            if ("累计逾期天数".equals(systemTemplateName)) {
                bean.setSumYqtsHis(customerTemplateName);
                continue;}
            if ("逾期次数".equals(systemTemplateName)) {
                bean.setSumYqcsHis(customerTemplateName);
                continue;}
            if ("移动电话".equals(systemTemplateName)) {
                bean.setCustomerMobile(customerTemplateName);
                continue;}
            if ("联系人1姓名".equals(systemTemplateName)) {
                bean.setFirstLiaisonName(customerTemplateName);
                continue;}
            if ("联系人1关系".equals(systemTemplateName)) {
                bean.setFirstLiaisonRelation(customerTemplateName);
                continue;}
            if ("联系人2姓名".equals(systemTemplateName)) {
                bean.setSecondLiaisonName(customerTemplateName);
                continue;}
            if ("联系人2关系".equals(systemTemplateName)) {
                bean.setSecondLiaisonRelation(customerTemplateName);
                continue;}
            if ("联系人3姓名".equals(systemTemplateName)) {
                bean.setThreeLiaisonName(customerTemplateName);
                continue;}
            if ("联系人3关系".equals(systemTemplateName)) {
                bean.setThreeLiaisonRelation(customerTemplateName);
                continue;}
            if ("住宅电话".equals(systemTemplateName)) {
                bean.setCustomerHomeTel(customerTemplateName);
                continue;}
            if ("联系人1电话1".equals(systemTemplateName)) {
                bean.setFirstLiaisonMobile(customerTemplateName);
                continue;}
            if ("联系人1电话2".equals(systemTemplateName)) {
                bean.setFirstLiaisonTel(customerTemplateName);
                continue;}
            if ("联系人2电话1".equals(systemTemplateName)) {
                bean.setSecondLiaisonMobile(customerTemplateName);
                continue;}
            if ("联系人2电话2".equals(systemTemplateName)) {
                bean.setSecondLiaisonTel(customerTemplateName);
                continue;}
            if ("联系人3电话1".equals(systemTemplateName)) {
                bean.setThreeLiaisonMobile(customerTemplateName);
                continue;}
            if ("联系人3电话2".equals(systemTemplateName)) {
                bean.setThreeLiaisonTel(customerTemplateName);
                continue;}
            if ("单位电话".equals(systemTemplateName)) {
                bean.setWorkTel(customerTemplateName);
                continue;}

            if ("账龄".equals(systemTemplateName)) {
                bean.setAccountAge(customerTemplateName);
                continue;}
            if ("留案标签".equals(systemTemplateName)) {
                bean.setLaFlag(customerTemplateName);
                continue;}
            if ("风险标签".equals(systemTemplateName)) {
                bean.setFxFlag(customerTemplateName);
                continue;}
            if ("合同类型".equals(systemTemplateName)) {
                bean.setHtlx(customerTemplateName);
                continue;}
            if ("减免标签".equals(systemTemplateName)) {
                bean.setJmbq(customerTemplateName);
                continue;}
            if ("法催标签".equals(systemTemplateName)) {
                bean.setFcbq(customerTemplateName);
                continue;}
            if ("罚息是否变化".equals(systemTemplateName)) {
                bean.setFxsfbh(customerTemplateName);
                continue;}
            if ("备注".equals(systemTemplateName)) {
                bean.setRemark(customerTemplateName);
                continue;}
            if ("退案日".equals(systemTemplateName)) {
                bean.setTar(customerTemplateName);
                continue;}
            if ("借款日期".equals(systemTemplateName)) {
                bean.setJkrq(customerTemplateName);
                continue;}
            if ("最近一次还款日".equals(systemTemplateName)) {
                bean.setZhychkr(customerTemplateName);
                continue;}
            if ("每期还款金额".equals(systemTemplateName)) {
                bean.setMqhkje(customerTemplateName);
                continue;}
            if ("当期欠款金额".equals(systemTemplateName)) {
                bean.setDqqkje(customerTemplateName);
                continue;}
            if ("累计已还金额".equals(systemTemplateName)) {
                bean.setLjyhje(customerTemplateName);
                continue;}
            if ("首付金额".equals(systemTemplateName)) {
                bean.setSfje(customerTemplateName);
                continue;}
            if ("指定还款账号1".equals(systemTemplateName)) {
                bean.setZdhkzh1(customerTemplateName);
                continue;}
            if ("指定还款账号2".equals(systemTemplateName)) {
                bean.setZdhkzh2(customerTemplateName);
                continue;}
            if ("指定还款账户户名1".equals(systemTemplateName)) {
                bean.setZdhkzhhm1(customerTemplateName);
                continue;}
            if ("指定还款账户户名2".equals(systemTemplateName)) {
                bean.setZdhkzhhm2(customerTemplateName);
                continue;}
            if ("指定还款渠道1".equals(systemTemplateName)) {
                bean.setZdhkqd1(customerTemplateName);
                continue;}
            if ("指定还款渠道2".equals(systemTemplateName)) {
                bean.setZdhkqd2(customerTemplateName);
                continue;}
            if ("考核目标".equals(systemTemplateName)) {
                bean.setKhmb(customerTemplateName);
                continue;}
            if ("商品价格".equals(systemTemplateName)) {
                bean.setSpjg(customerTemplateName);
                continue;}
            if ("贷款类型".equals(systemTemplateName)) {
                bean.setDklx(customerTemplateName);
                continue;}
            if ("借款笔数".equals(systemTemplateName)) {
                bean.setJkbs(customerTemplateName);
                continue;}
            if ("商品信息".equals(systemTemplateName)) {
                bean.setSpxx(customerTemplateName);
                continue;}
            if ("委案次数".equals(systemTemplateName)) {
                bean.setWacs(customerTemplateName);
                continue;}
            if ("已还期数".equals(systemTemplateName)) {
                bean.setYkqs(customerTemplateName);
                continue;}
            if ("工作部门".equals(systemTemplateName)) {
                bean.setWorkDept(customerTemplateName);
                continue;}
            if ("客户电话2".equals(systemTemplateName)) {
                bean.setCustomerMobile2(customerTemplateName);
                continue;}
            if ("客户电话3".equals(systemTemplateName)) {
                bean.setCustomerMobile3(customerTemplateName);
                continue;}
            if ("客户电话4".equals(systemTemplateName)) {
                bean.setCustomerMobile4(customerTemplateName);
                continue;}
            if ("联系人4姓名".equals(systemTemplateName)) {
                bean.setFourthLiaisonName(customerTemplateName);
                continue;}
            if ("联系人4关系".equals(systemTemplateName)) {
                bean.setFourthLiaisonRelation(customerTemplateName);
                continue;}
            if ("联系人4电话".equals(systemTemplateName)) {
                bean.setFourthLiaisonMobile(customerTemplateName);
                continue;}
            if ("联系人5姓名".equals(systemTemplateName)) {
                bean.setFifthLiaisonName(customerTemplateName);
                continue;}
            if ("联系人5关系".equals(systemTemplateName)) {
                bean.setFifthLiaisonRelation(customerTemplateName);
                continue;}
            if ("联系人5电话".equals(systemTemplateName)) {
                bean.setFifthLiaisonMobile(customerTemplateName);
                continue;}
            if ("呆账核销日期".equals(systemTemplateName)) {
                bean.setDzhxrq(customerTemplateName);
                continue;}
            if ("客户号".equals(systemTemplateName)) {
                bean.setCustomerNo(customerTemplateName);
                continue;}
            if ("数据日期".equals(systemTemplateName)) {
                bean.setSjrq(customerTemplateName);
                continue;}
        }
        return bean;
    }


    /**
     * 结案
     * @param paramList
     * @param createTime 临时表的创建时间
     * @throws Exception
     */
    public void updateCloseCase(List<CurAssetsPackage> paramList,Date createTime,String isExitCollect) throws Exception{
        if(paramList != null && paramList.size() >0) {
            List<CloseCase> remoteList = new ArrayList<>();
            for (CurAssetsPackage curAssetsPackage : paramList) {
                curAssetsPackage.setCloseCaseDate(createTime);//临时表的创建时间
                curAssetsPackage.setIsExitCollect(isExitCollect);
                remoteList.add(buildCloseCase(curAssetsPackage));
            }
            int total = paramList.size();
            int index = 500;
            int pagesize = total / index;
            if (total <= index) {
                this.assetsImportFromXYMapper.batchUpdateCloseCase(paramList);
            } else {
                for (int i = 0; i < pagesize; i++) {
                    List lt = paramList.subList(i * index, (i + 1) * index);
                    this.assetsImportFromXYMapper.batchUpdateCloseCase(lt);

                }
                if (total % index != 0) {
                    List lt = paramList.subList(index * pagesize, total);
                    this.assetsImportFromXYMapper.batchUpdateCloseCase(lt);
                }
            }
            //催收模块结案
            curAssetsRepaymentPackageServiceImpl.closeCase2(remoteList);
        }

    }


    /**
     * 插入出催统计临时表
     * @param paramList
     * @param type
     * @param createTime 临时表的创建时间
     * @param importBatchNo 临时表的批次号
     * @throws Exception
     */
    private void insertUrgeTemp(List<CurAssetsPackage> paramList,String type,Date createTime,String importBatchNo) throws Exception{
        List<TLcUrge> TlcUrgeList = new ArrayList<>();
        for (CurAssetsPackage curAsset : paramList) {
            Map<String, Object> owner = this.assetsImportFromXYMapper.findOwner(curAsset);//上一次资产导入的批次号
            Long ownerId = null;
            String ownerName = null;
            String loginName = null;//业务归属人登录名称
            if(owner != null && owner.get("ownerId") != null && owner.get("ownerId") instanceof Long){
                ownerId = (Long)owner.get("ownerId");
            }
            if(owner != null && owner.get("ownerName") != null && owner.get("ownerName") instanceof String){
                ownerName = (String) owner.get("ownerName");
            }
            if(owner != null && owner.get("loginName") != null && owner.get("loginName") instanceof String){
                loginName = (String) owner.get("loginName");
            }
            TLcUrge entity  = new TLcUrge();
            entity.setImportBatchNo(importBatchNo)//临时表的批次号
                    .setOrgId(curAsset.getOrgId())
                    .setOrgName(curAsset.getOrg())
                    .setOrgCasNo(curAsset.getOrgCasno())
                    .setCustomeName(curAsset.getCurName())
                    .setWaje(curAsset.getRmbYe())
                    .setDqyhje(curAsset.getDqyhje())
                    .setOwnerId(ownerId)
                    .setOwnerName(ownerName)
                    .setType(type)
                    .setTransfertype(curAsset.getTransfertype())
                    .setRcr(curAsset.getRcr())
                    .setLoginName(loginName);
            entity.setCreateTime(createTime);//临时表的创建时间
            entity.setCreateBy(ShiroUtils.getLoginName());
            TlcUrgeList.add(entity);
        }
        //插入出催统计表
        int total = TlcUrgeList.size();
        int index = 500;
        int pagesize = total/index;
        if(total <=index){
            this.assetsImportFromXYMapper.batchInsertTlcUrgeTemp(TlcUrgeList);
        }else{
            for(int i=0;i<pagesize;i++){
                List lt = TlcUrgeList.subList(i*index, (i+1)*index);
                this.assetsImportFromXYMapper.batchInsertTlcUrgeTemp(lt);

            }
            if(total % index != 0){
                List lt = TlcUrgeList.subList(index * pagesize,total);
                this.assetsImportFromXYMapper.batchInsertTlcUrgeTemp(lt);
            }
        }



    }




    private CloseCase buildCloseCase(CurAssetsPackage curAssetsPackage) {
        return CloseCase.builder()
                .isExitCollect(curAssetsPackage.getIsExitCollect())
                .caseNo(curAssetsPackage.getOrgCasno())
                .importBatchNo(curAssetsPackage.getImportBatchNo())
                .orgId(curAssetsPackage.getOrgId())
                .isClose(3)
                .dqyhje(curAssetsPackage.getDqyhje())
                .jayhje(curAssetsPackage.getWaYe())
                .build();
    }


   /* *//**
     * @方法描述：获取两个ArrayList的差集
     * @param firstArrayList 第一个ArrayList
     * @param secondArrayList 第二个ArrayList
     * @return resultList 差集ArrayList
     *//*
    public List<CurAssetsPackage> receiveDefectList(List<CurAssetsPackage> firstArrayList, List<CurAssetsPackage> secondArrayList) {
        List<CurAssetsPackage> resultList = new ArrayList<CurAssetsPackage>();
        LinkedList<CurAssetsPackage> result = new LinkedList<CurAssetsPackage>(firstArrayList);// 大集合用linkedlist
        HashSet<CurAssetsPackage> othHash = new HashSet<CurAssetsPackage>(secondArrayList);// 小集合用hashset
        Iterator<CurAssetsPackage> iter = result.iterator();// 采用Iterator迭代器进行数据的操作
        while(iter.hasNext()){
            if(othHash.contains(iter.next())){
                iter.remove();
            }
        }
        resultList = new ArrayList<CurAssetsPackage>(result);
        return resultList;
    }*/



}
