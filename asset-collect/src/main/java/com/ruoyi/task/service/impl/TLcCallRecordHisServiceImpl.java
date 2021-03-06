package com.ruoyi.task.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.assetspackage.domain.RemoteConfigure;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.assetspackage.util.DownLoadFromHttpsUtil;
import com.ruoyi.caseConfig.service.ITLcAllocatCaseConfigService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.enums.ContactRelaEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.util.zipUtil;
import com.ruoyi.radioQc.service.ITLcSendRadioQcRecordService;
import com.ruoyi.robot.domain.CallContent;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.task.domain.*;
import com.ruoyi.task.mapper.TLcCallRecordHisMapper;
import com.ruoyi.task.mapper.TLcCallRecordMapper;
import com.ruoyi.task.service.ITLcCallRecordHisService;
import com.ruoyi.task.service.ITLcCallRecordService;
import com.ruoyi.task.service.ITLcTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 通话结果记录Service业务层处理
 *
 * @author gaohg
 * @date 2021-3-10
 */
@Slf4j
@Service
public class TLcCallRecordHisServiceImpl implements ITLcCallRecordHisService {

    private static final String AUTO_SEND_QUALITY_CHECK = "auto_send_quality_check";

    @Autowired
    private TLcCallRecordHisMapper tLcCallRecordHisMapper;
    @Autowired
    private ITLcSendRadioQcRecordService sendRadioQcRecordService;
    @Autowired
    private ITLcTaskService tLcTaskService;
    @Autowired
    private IOrgPackageService orgPackageService;
    @Autowired
    private RemoteConfigure remoteConfigure;
    @Autowired
    private ITLcAllocatCaseConfigService caseConfigService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDictDataService sysDictDataService;
    @Value("${ruoyi.tempDir}")
    private String tempDir;

    /**
     * 查询通话结果记录列表
     *
     * @param tLcCallRecordHis 通话结果记录
     * @return 通话结果记录
     */
    @Override
    public List<TLcCallRecordHis> selectTLcCallRecordHisList(TLcCallRecordHis tLcCallRecordHis) {
        if (tLcCallRecordHis.getEndCreateTime() != null) {
            tLcCallRecordHis.setEndCreateTime(DateUtils.getEndOfDay(tLcCallRecordHis.getEndCreateTime()));
        }
        if (tLcCallRecordHis.getEndCallStartTime() != null) {
            tLcCallRecordHis.setEndCallStartTime(DateUtils.getEndOfDay(tLcCallRecordHis.getEndCallStartTime()));
        }
        if (tLcCallRecordHis.getStartCallLen() != null) {
            tLcCallRecordHis.setStartCallLen(tLcCallRecordHis.getStartCallLen() * 1000);
        }
        if (tLcCallRecordHis.getEndCallLen() != null) {
            tLcCallRecordHis.setEndCallLen(tLcCallRecordHis.getEndCallLen() * 1000);
        }
        String caseNo = tLcCallRecordHis.getCaseNo();
        if(StringUtils.isNotBlank(caseNo)){
            tLcCallRecordHis.setCaseNoList(Arrays.asList(caseNo.split(",")));
        }
        return tLcCallRecordHisMapper.selectTLcCallRecordList(tLcCallRecordHis);
    }


    @Override
    public List<TLcCallRecordForXY> selectTLcCallRecordHisListForXY(TLcCallRecordHis tLcCallRecord) {
        if (tLcCallRecord.getEndCreateTime() != null) {
            tLcCallRecord.setEndCreateTime(DateUtils.getEndOfDay(tLcCallRecord.getEndCreateTime()));
        }
        if (tLcCallRecord.getEndCallStartTime() != null) {
            tLcCallRecord.setEndCallStartTime(DateUtils.getEndOfDay(tLcCallRecord.getEndCallStartTime()));
        }
        if (tLcCallRecord.getStartCallLen() != null) {
            tLcCallRecord.setStartCallLen(tLcCallRecord.getStartCallLen() * 1000);
        }
        if (tLcCallRecord.getEndCallLen() != null) {
            tLcCallRecord.setEndCallLen(tLcCallRecord.getEndCallLen() * 1000);
        }
        List<TLcCallRecordHis> list = tLcCallRecordHisMapper.selectTLcCallRecordList(tLcCallRecord);
        List<TLcCallRecordForXY> xyList = this.ConvertXYList(list);
        return xyList;
    }


//    @Override
//    public List<TLcCallRecordHis> selectTLcCallRecordXYList(TLcCallRecordHis tLcCallRecordHis) {
//        if (tLcCallRecordHis.getEndCreateTime() != null) {
//            tLcCallRecordHis.setEndCreateTime(DateUtils.getEndOfDay(tLcCallRecordHis.getEndCreateTime()));
//        }
//        if (tLcCallRecordHis.getEndCallStartTime() != null) {
//            tLcCallRecordHis.setEndCallStartTime(DateUtils.getEndOfDay(tLcCallRecordHis.getEndCallStartTime()));
//        }
//        if (tLcCallRecordHis.getStartCallLen() != null) {
//            tLcCallRecordHis.setStartCallLen(tLcCallRecordHis.getStartCallLen() * 1000);
//        }
//        if (tLcCallRecordHis.getEndCallLen() != null) {
//            tLcCallRecordHis.setEndCallLen(tLcCallRecordHis.getEndCallLen() * 1000);
//        }
//        String caseNo = tLcCallRecordHis.getCaseNo();
//        if(StringUtils.isNotBlank(caseNo)){
//            tLcCallRecordHis.setCaseNoList(Arrays.asList(caseNo.split(",")));
//        }
//        return tLcCallRecordHisMapper.selectTLcCallRecordXYList(tLcCallRecordHis);
//    }

//    @Override
//    public List<TLcCallRecordForXY> selectTLcCallRecordListForXY(TLcCallRecordHis tLcCallRecordHis) {
//        if (tLcCallRecordHis.getEndCreateTime() != null) {
//            tLcCallRecordHis.setEndCreateTime(DateUtils.getEndOfDay(tLcCallRecordHis.getEndCreateTime()));
//        }
//        if (tLcCallRecordHis.getEndCallStartTime() != null) {
//            tLcCallRecordHis.setEndCallStartTime(DateUtils.getEndOfDay(tLcCallRecordHis.getEndCallStartTime()));
//        }
//        if (tLcCallRecordHis.getStartCallLen() != null) {
//            tLcCallRecordHis.setStartCallLen(tLcCallRecordHis.getStartCallLen() * 1000);
//        }
//        if (tLcCallRecordHis.getEndCallLen() != null) {
//            tLcCallRecordHis.setEndCallLen(tLcCallRecordHis.getEndCallLen() * 1000);
//        }
//        List<TLcCallRecordHis> list = tLcCallRecordHisMapper.selectTLcCallRecordList(tLcCallRecordHis);
//        List<TLcCallRecordForXY> xyList = this.ConvertXYList(list);
//        return xyList;
//    }


    private List<TLcCallRecordForXY> ConvertXYList(List<TLcCallRecordHis> list){
        List<TLcCallRecordForXY> resultList = new ArrayList<>();
        for (TLcCallRecordHis tLcCallRecord : list) {
            TLcCallRecordForXY xyEntity = new TLcCallRecordForXY();
            xyEntity.setSque(tLcCallRecord.getSque());
            xyEntity.setCaseNo(tLcCallRecord.getCaseNo());
//            xyEntity.setYwdetp(tLcCallRecord.getYwdetp());
            xyEntity.setWbjb(tLcCallRecord.getWbjb());
            xyEntity.setCustomName(tLcCallRecord.getCustomName());
            xyEntity.setProductName(tLcCallRecord.getProductName());
            xyEntity.setCsdz(tLcCallRecord.getCsdz());
            xyEntity.setCreateTime(tLcCallRecord.getCreateTime());
            xyEntity.setPhone(tLcCallRecord.getPhone());
            xyEntity.setEnterCollDate(tLcCallRecord.getEnterCollDate());
            xyEntity.setTar(tLcCallRecord.getTar());
//            xyEntity.setCallResult(tLcCallRecord.getCallResult());
            //特殊字段处理
//            String recommendVendor = tLcCallRecord.getRecommendVendor();//推荐商户
//            if(recommendVendor != null) {
//                xyEntity.setYwdetp(getYwDetp(recommendVendor.trim()));
//            }
//            if (tLcCallRecord.getProductName().equals("小鲨分期（ML）")) {
//                xyEntity.setYwdetp("驻店业务部");
//            }
            xyEntity.setYwdetp(handleYwDept(tLcCallRecord.getProductName()));
            String callRecord = tLcCallRecord.getCallResult();//电话码中文
            if(callRecord != null) {
                xyEntity.setCallResult(getCallResult(callRecord.trim()));
            }

            String contactName = tLcCallRecord.getContactName();
            Integer contactRelation = tLcCallRecord.getContactRelation();
            String relateion="";
            if(contactRelation != null){
                relateion = this.getRelateion(contactRelation);
            }
//            String relateion = this.getRelateion(contactRelation);
            if (tLcCallRecord.getContactRelation() == ContactRelaEnum.SELE.getCode()) {
                xyEntity.setContactName("本人");
            } else {
                if(!"".equals(relateion)){
                    contactName = relateion+"-"+contactName;
                }
                xyEntity.setContactName(contactName);
            }

            String remark = tLcCallRecord.getRemark();
            if(remark != null && !"".equals(remark) && !"[]".equals(remark)){
                xyEntity.setRemarkDetail(remark);
            }else{
                xyEntity.setRemarkDetail(tLcCallRecord.getCallResult());
            }
            resultList.add(xyEntity);
        }
        return resultList;
    }

    private List<Map<String,Object>> ConvertXYList2(List<TLcCallRecordForXY> list){
        List<Map<String,Object>> resultList = new ArrayList<>();
        for (TLcCallRecordForXY xyRecord : list) {
            Map<String,Object> rowMap = new LinkedHashMap<>();
            rowMap.put("序号",xyRecord.getSque() != null ? xyRecord.getSque().toString() : null);
            rowMap.put("贷款合同号",xyRecord.getCaseNo() != null ? xyRecord.getCaseNo() : null);
            rowMap.put("业务部门",xyRecord.getYwdetp() != null ? xyRecord.getYwdetp() : null);
            rowMap.put("外包经办",xyRecord.getWbjb() != null ? xyRecord.getWbjb() : null);
            rowMap.put("客户姓名",xyRecord.getCustomName() != null ? xyRecord.getCustomName() : null);
            rowMap.put("产品名称",xyRecord.getProductName() != null ? xyRecord.getProductName() : null);
            rowMap.put("催收动作",xyRecord.getCsdz() != null ? xyRecord.getCsdz() : null);
            rowMap.put("催收时间",xyRecord.getCreateTime() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,xyRecord.getCreateTime()) : null);
            rowMap.put("催收结果",xyRecord.getCallResult() != null ? xyRecord.getCallResult() : null);
            rowMap.put("联络人",xyRecord.getContactName() != null ? xyRecord.getContactName() : null);
            rowMap.put("联络方式",xyRecord.getPhone() != null ? xyRecord.getPhone() : null);
            rowMap.put("催收详细情况",xyRecord.getRemarkDetail() != null ? xyRecord.getRemarkDetail() : null);
            rowMap.put("委案时间",xyRecord.getEnterCollDate() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,xyRecord.getEnterCollDate()) : null);
            rowMap.put("到期时间",xyRecord.getTar() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,xyRecord.getTar()) : null);
            resultList.add(rowMap);
        }
        return resultList;
    }



//    @Override
//    public List<TLcCallRecordHis> findCallRecordByCertificateNo(String certificateNo) {
//        return this.tLcCallRecordHisMapper.findCallRecordByCertificateNo(certificateNo);
//    }


//    @Override
//    public List<TLcCallRecordHis> findCallRecordByCaseNo(String caseNo) {
//        return this.tLcCallRecordHisMapper.findCallRecordByCaseNo(caseNo);
//    }
//
//    @Override
//    public List<TLcCallRecordHis> findHisDuncaseCallRecordByCaseNo(String caseNo) {
//        return this.tLcCallRecordHisMapper.findHisDuncaseCallRecordByCaseNo(caseNo);
//    }

//    @Override
//    public List<CallContent> viewCallContent(String id) {
//        TLcCallRecord tLcCallRecord = this.tLcCallRecordHisMapper.selectTLcCallRecordById(Long.valueOf(id));
//        List<CallContent> callContentList = JSONObject.parseArray(tLcCallRecord.getRemark(), CallContent.class);
//        return callContentList;
//    }


//    @Override
//    public List<TLcCallRecordHis> findListenCallRecord(TLcCallRecordHis tLcCallRecord) {
//        int startCallLen = tLcCallRecord.getStartCallLen() == null ? 0 : tLcCallRecord.getStartCallLen();
//        int endCallLen = tLcCallRecord.getEndCallLen() == null ? 0 : tLcCallRecord.getEndCallLen();
//        tLcCallRecord.setStartCallLen(startCallLen*1000);
//        tLcCallRecord.setEndCallLen(endCallLen*1000);
//        if (tLcCallRecord.getCallEndTime() != null) {
//            tLcCallRecord.setCallEndTime(DateUtils.getEndOfDay(tLcCallRecord.getCallEndTime()));
//        }
//        List<TLcCallRecordHis> listenCallRecord = this.tLcCallRecordHisMapper.findListenCallRecord(tLcCallRecord);
//        return listenCallRecord;
//    }



//    @Override
//    public List<TLcCallRecordHis> findTLcCallRecordListByDate(TLcCallRecordHis tLcCallRecordHis) {
//        return this.tLcCallRecordHisMapper.findTLcCallRecordListByDate(tLcCallRecordHis);
//    }

//    @Override
//    public List<Map<String, Object>> selectCallRecordByTime(Date createTime, int pageNum, int pageSize) {
//        return tLcCallRecordHisMapper.selectCallRecordByTime(createTime,pageNum,pageSize);
//    }
//
//
//    @Override
//    public List<JxphCallRecord> selectJxphCallRecord(Map<String, Object> param) {
//        return this.tLcCallRecordHisMapper.selectJxphCallRecord(param);
//    }
//
//    @Override
//    public List<TLcCallRecordHis> findZjCallRecordListByDate(TLcCallRecordHis tLcCallRecordHis) {
//        return this.tLcCallRecordHisMapper.findZjCallRecordListByDate(tLcCallRecordHis);
//    }
//
//    @Override
//    public List<TLcCallRecordForJX> selectTLcCallRecordHisListForJX(TLcCallRecordHis tLcCallRecord) {
//        return null;
//    }

//    @Override
//    public List<TLcCallRecordForDQ> selectTLcCallRecordHisListForDQ(TLcCallRecordHis tLcCallRecord) {
//        return null;
//    }


    @Override
    public List<TLcCallRecordForJX> selectTLcCallRecordListForJX(TLcCallRecordHis tLcCallRecordHis) {
        if (tLcCallRecordHis.getEndCreateTime() != null) {
            tLcCallRecordHis.setEndCreateTime(DateUtils.getEndOfDay(tLcCallRecordHis.getEndCreateTime()));
        }
        if (tLcCallRecordHis.getEndCallStartTime() != null) {
            tLcCallRecordHis.setEndCallStartTime(DateUtils.getEndOfDay(tLcCallRecordHis.getEndCallStartTime()));
        }
        if (tLcCallRecordHis.getStartCallLen() != null) {
            tLcCallRecordHis.setStartCallLen(tLcCallRecordHis.getStartCallLen() * 1000);
        }
        if (tLcCallRecordHis.getEndCallLen() != null) {
            tLcCallRecordHis.setEndCallLen(tLcCallRecordHis.getEndCallLen() * 1000);
        }
        List<TLcCallRecordHis> list = tLcCallRecordHisMapper.selectTLcCallRecordList(tLcCallRecordHis);
        List<TLcCallRecordForJX> jxList = this.convertJXList(list,tLcCallRecordHis);
        return jxList;
    }

    @Override
    public List<TLcCallRecordForDQ> selectTLcCallRecordListForDQ(TLcCallRecordHis tLcCallRecordHis) {
        if (tLcCallRecordHis.getEndCreateTime() != null) {
            tLcCallRecordHis.setEndCreateTime(DateUtils.getEndOfDay(tLcCallRecordHis.getEndCreateTime()));
        }
        if (tLcCallRecordHis.getEndCallStartTime() != null) {
            tLcCallRecordHis.setEndCallStartTime(DateUtils.getEndOfDay(tLcCallRecordHis.getEndCallStartTime()));
        }
        if (tLcCallRecordHis.getStartCallLen() != null) {
            tLcCallRecordHis.setStartCallLen(tLcCallRecordHis.getStartCallLen() * 1000);
        }
        if (tLcCallRecordHis.getEndCallLen() != null) {
            tLcCallRecordHis.setEndCallLen(tLcCallRecordHis.getEndCallLen() * 1000);
        }
        List<TLcCallRecordHis> list = tLcCallRecordHisMapper.selectTLcCallRecordList(tLcCallRecordHis);
        List<TLcCallRecordForDQ> dqList = this.ConvertDQList(list);
        return dqList;
    }

//    @Override
//    public List<TLcCallRecordHis> selectCallRecordListByOrgIdAndTime(TLcCallRecordHis tLcCallRecordHis) {
//        return this.tLcCallRecordHisMapper.selectCallRecordListByOrgIdAndTime(tLcCallRecordHis);
//    }

    private List<TLcCallRecordForDQ> ConvertDQList(List<TLcCallRecordHis> list) {
        List<TLcCallRecordForDQ> dqList = list.stream().map(callRecord -> {
            TLcCallRecordForDQ tLcCallRecordForDQ = new TLcCallRecordForDQ();
            String callSign = callRecord.getCallSignEn();
            tLcCallRecordForDQ.setSque(callRecord.getSque())
                    .setCaseNo(callRecord.getCaseNo())
                    .setAreaCenter(callRecord.getAreaCenter())
                    .setCustomName(callRecord.getCustomName())
                    .setTjWd(callRecord.getTjWd())
                    .setContactStatus((callSign.equalsIgnoreCase("PTP") || callSign.equalsIgnoreCase("CYH") || callSign.equalsIgnoreCase("TP") || callSign.equalsIgnoreCase("WCY") || callSign.equalsIgnoreCase("HKKN") || callSign.equalsIgnoreCase("ZG") || callSign.equalsIgnoreCase("R01") || callSign.equalsIgnoreCase("FBRJT") || callSign.equalsIgnoreCase("BPHZG")) ? "可联" : "失联")
                    .setRepayStatus(callSign.equalsIgnoreCase("CYH")?"称已还":null)
                    .setActualRepayAmount(callRecord.getCnje())
                    .setContactRelation(callRecord.getContactRelation())
                    .setAgentName(callRecord.getAgentName())
                    .setCallSign(callRecord.getCallSign())
                    .setCollRecord(callRecord.getRemark())
                    .setRmbYe(callRecord.getArrearsTotal())
                    .setOverdueDays(callRecord.getOverdueDays())
                    .setActionCode(StringUtils.isNoneBlank(callRecord.getActionCode())?callRecord.getActionCode().split("-")[0]:null)
                    .setCreateTime(callRecord.getCreateTime());
            return tLcCallRecordForDQ;
        }).collect(Collectors.toList());
        return dqList;
    }

    private List<TLcCallRecordForJX> convertJXList(List<TLcCallRecordHis> list, TLcCallRecordHis tLcCallRecord) {
        List<TLcCallRecordForJX> jxList = list.stream().map(callRecord -> {
            TLcCallRecordForJX tLcCallRecordForJX = new TLcCallRecordForJX();
            String callSign = callRecord.getCallSignEn();
            tLcCallRecordForJX.setWeekStartDate(tLcCallRecord.getStartCreateTime())
                    .setWeekEndDate(tLcCallRecord.getEndCreateTime())
                    .setCompanyName("huadao")
                    .setCaseNo(callRecord.getCaseNo())
                    .setEnterCollDate(callRecord.getEnterCollDate())
                    .setArrearsTotal(callRecord.getArrearsTotal())
                    .setCloseCaseYhje(new BigDecimal(callRecord.getCloseCaseYhje()))
                    .setOverdueDays(StringUtils.isNotBlank(callRecord.getOverdueDays()) ? Integer.valueOf(callRecord.getOverdueDays()) : null)
                    .setCallTime(callRecord.getCreateTime())
                    .setCollType("电话催收")
                    .setPhone(callRecord.getPhone())
                    .setIsPromisePay((callSign.equalsIgnoreCase("PTP") || callSign.equalsIgnoreCase("CYH")) ? "Y" : "N")
                    .setIsOutColl("N")
                    .setCollResult((callSign.equalsIgnoreCase("PTP") || callSign.equalsIgnoreCase("CYH") || callSign.equalsIgnoreCase("TP") || callSign.equalsIgnoreCase("WCY") || callSign.equalsIgnoreCase("HKKN") || callSign.equalsIgnoreCase("ZG") || callSign.equalsIgnoreCase("R01")) ? "可联" : "失联")
                    .setAgent(callRecord.getAgentName())
                    .setCallCode(callRecord.getCallSign())
                    .setRemark(callRecord.getRemark());
            return tLcCallRecordForJX;
        }).collect(Collectors.toList());
        return jxList;
    }


    private String getRelateion(Integer contactRelation){
        String relateion = "";
        if(contactRelation == 1){
            relateion = "本人";
        }else if(contactRelation == 2){
            relateion = "直系";
        }else if(contactRelation == 3){
            relateion = "亲戚";
        }else if(contactRelation == 4){
            relateion = "朋友";
        }else if(contactRelation == 5){
            relateion = "父母";
        }else if(contactRelation == 6){
            relateion = "配偶";
        }else if(contactRelation == 7){
            relateion = "兄弟";
        }else if(contactRelation == 8){
            relateion = "姐妹";
        }else if(contactRelation == 9){
            relateion = "哥哥";
        }else if(contactRelation == 10){
            relateion = "兄长";
        }else if(contactRelation == 11){
            relateion = "弟弟";
        }else if(contactRelation == 12){
            relateion = "姐姐";
        }else if(contactRelation == 13){
            relateion = "妹妹";
        }else if(contactRelation == 14){
            relateion = "家人";
        }else if(contactRelation == 15){
            relateion = "老公";
        }else if(contactRelation == 16){
            relateion = "老婆";
        }else if(contactRelation == 17){
            relateion = "同事";
        }else if(contactRelation == 18){
            relateion = "公司";
        }else{
            relateion = "其它";
        }
        return relateion;
    }


    private String getYwDetp(String recommendVendor){
        String ywDept = "";
        if(recommendVendor.contains("助学事业部")){
            ywDept = "高校";
        }
        if(recommendVendor.contains("特商事业部")){
            ywDept = "电商";
        }
        if(recommendVendor.contains("网商事业部")){
            ywDept = "网商";
        }
        return ywDept;
    }

    private String getCallResult(String callResult){
        String result = "";
        if("承诺还款".equals(callResult)){
            result = "承诺还款";
        }
        if("称已还".equals(callResult)){
            result = "承诺还款";
        }
        if("谈判沟通".equals(callResult)){
            result = "无承诺还款";
        }
        if("无诚意".equals(callResult)){
            result = "无承诺还款";
        }
        if("还款困难".equals(callResult)){
            result = "无承诺还款";
        }
        if("不在".equals(callResult)){
            result = "无承诺还款";
        }
        if("已接听".equals(callResult)){
            result = "无承诺还款";
        }
        if("不配合转告".equals(callResult)){
            result = "无承诺还款";
        }
        if("非本人接听".equals(callResult)){
            result = "无承诺还款";
        }
        if("转告".equals(callResult)){
            result = "转告还款";
        }
        if("失联".equals(callResult)){
            result = "转告还款";
        }
        if("无人接听".equals(callResult)){
            result = "未接通";
        }
        if("拒接".equals(callResult)){
            result = "未接通";
        }
        if("占线忙音".equals(callResult)){
            result = "未接通";
        }
        if("关机".equals(callResult)){
            result = "未接通";
        }
        if("挂断".equals(callResult)){
            result = "未接通";
        }
        if("空号".equals(callResult)){
            result = "未接通";
        }
        if("无此人".equals(callResult)){
            result = "未接通";
        }
        if("无回应".equals(callResult)){
            result = "未接通";
        }
        if("无法接通".equals(callResult)){
            result = "未接通";
        }
        if("主叫号码不可用".equals(callResult)){
            result = "未接通";
        }
        if("停机".equals(callResult)){
            result = "未接通";
        }
        if("主叫欠费".equals(callResult)){
            result = "未接通";
        }
        if("呼损".equals(callResult)){
            result = "未接通";
        }
        if("黑名单".equals(callResult)){
            result = "未接通";
        }
        if("线路盲区".equals(callResult)){
            result = "未接通";
        }
        return result;
    }

    private String handleYwDept(String productName){
        String businessDept = null;
        if(null != productName) {
            switch (productName) {
                case "51金鲨贷":
                case "51金鲨贷（等额本息）":
                case "电商-百融":
                case "电商-车轮":
                case "电商-滴滴":
                case "电商-返利":
                case "电商-平安":
                case "电商-融之家":
                case "电商-微聚未来":
                case "电商-未来视界":
                case "电商-小鲨快贷":
                case "电商-新浪":
                case "家庭消费贷-线上通用产品":
                case "小鲨分期（浅橙）":
                case "小鲨分期（浅橙） 等额本息":
                case "小鲨分期（小米金融）":
                case "兴家贷（GG）":
                    businessDept = "电商";
                    break;
                case "小鲨分期":
                    businessDept = "网商";
                    break;
                case "电信（橙分期）":
                case "小鲨易贷-平安一账通":
                case "兴业消费金融-小鲨易贷-还呗":
                case "兴业消费金融-小鲨易贷（还呗）":
                case "兴业消费金融-小鲨易贷-京东":
                case "兴业消费金融-小鲨易贷（京东）":
                case "兴业消费金融-小鲨易贷-拉卡拉":
                case "兴业消费金融-小鲨易贷（拉卡拉）":
                case "兴业消费金融-小鲨易贷-美团":
                case "兴业消费金融-小鲨易贷（美团）":
                case "兴业消费金融-小鲨易贷-携程金融":
                case "兴业消费金融-小鲨易贷（携程金融）":
                case "兴业消费金融-小鲨易贷-盈酷":
                case "兴业消费金融-小鲨易贷（盈酷）":
                case "兴业消费金融-小鲨易贷-招联":
                case "兴业消费金融-小鲨易贷（招联）":
                    businessDept = "高校";
                    break;
                case "小鲨分期（ML）":
                    businessDept = "驻店业务部";
                    break;
                default: businessDept = "电商";
            }
        }
        return businessDept;
    }
}
