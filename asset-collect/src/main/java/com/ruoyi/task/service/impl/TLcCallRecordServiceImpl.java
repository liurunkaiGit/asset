package com.ruoyi.task.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.domain.RemoteConfigure;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.assetspackage.util.DownLoadFromHttpsUtil;
import com.ruoyi.caseConfig.domain.TLcAllocatCaseConfig;
import com.ruoyi.caseConfig.service.ITLcAllocatCaseConfigService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.enums.ContactRelaEnum;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.util.zipUtil;
import com.ruoyi.radioQc.service.ITLcSendRadioQcRecordService;
import com.ruoyi.robot.domain.CallContent;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.task.domain.*;
import com.ruoyi.task.mapper.TLcCallRecordMapper;
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
 * @author liurunkai
 * @date 2019-12-31
 */
@Slf4j
@Service
public class TLcCallRecordServiceImpl implements ITLcCallRecordService {

    private static final String AUTO_SEND_QUALITY_CHECK = "auto_send_quality_check";

    @Autowired
    private TLcCallRecordMapper tLcCallRecordMapper;
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
     * 查询通话结果记录
     *
     * @param id 通话结果记录ID
     * @return 通话结果记录
     */
    @Override
    public TLcCallRecord selectTLcCallRecordById(Long id) {
        return tLcCallRecordMapper.selectTLcCallRecordById(id);
    }

    /**
     * 查询通话结果记录列表
     *
     * @param tLcCallRecord 通话结果记录
     * @return 通话结果记录
     */
    @Override
    public List<TLcCallRecord> selectTLcCallRecordList(TLcCallRecord tLcCallRecord) {
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
        return tLcCallRecordMapper.selectTLcCallRecordList(tLcCallRecord);
    }

    @Override
    public List<TLcCallRecord> selectTLcCallRecordXYList(TLcCallRecord tLcCallRecord) {
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
        return tLcCallRecordMapper.selectTLcCallRecordXYList(tLcCallRecord);
    }

    @Override
    public List<TLcCallRecordForXY> selectTLcCallRecordListForXY(TLcCallRecord tLcCallRecord) {
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
        List<TLcCallRecord> list = tLcCallRecordMapper.selectTLcCallRecordList(tLcCallRecord);
        List<TLcCallRecordForXY> xyList = this.ConvertXYList(list);
        return xyList;
    }


    @Override
    public List<Map<String,Object>> selectTLcCallRecordListForXY2(TLcCallRecord tLcCallRecord) {
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


        int pageSize = 10000;//每页的数据条数
        int pageCount = 0;//总页数
        int count = tLcCallRecordMapper.selectTLcCallRecordCount(tLcCallRecord);
        pageCount = count/pageSize + 1;

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i = 0 ; i < pageCount; i ++){
            tLcCallRecord.setPNum(pageSize*i);
            tLcCallRecord.setPSize(pageSize);
            List<TLcCallRecord> recordList = tLcCallRecordMapper.selectTLcCallRecordXYList(tLcCallRecord);
            List<TLcCallRecordForXY> xyList = this.ConvertXYList(recordList);
            List<Map<String, Object>> datas = this.ConvertXYList2(xyList);
            list.addAll(datas);
            log.error("*******兴业催记导出生成"+(i+1)+"万条*********");
        }

        return list;
    }

    private List<TLcCallRecordForXY> ConvertXYList(List<TLcCallRecord> list){
        List<TLcCallRecordForXY> resultList = new ArrayList<>();
        for (TLcCallRecord tLcCallRecord : list) {
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
            String recommendVendor = tLcCallRecord.getRecommendVendor();//推荐商户
            if(recommendVendor != null) {
                xyEntity.setYwdetp(getYwDetp(recommendVendor.trim()));
            }
            if (tLcCallRecord.getProductName().equals("小鲨分期（ML）")) {
                xyEntity.setYwdetp("驻店业务部");
            }

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


    /**
     * 新增通话结果记录
     *
     * @param tLcCallRecord 通话结果记录
     * @return 结果
     */
    @Override
    public void insertTLcCallRecord(TLcCallRecord tLcCallRecord) {
        Long userId = null;
        try {
            userId = ShiroUtils.getSysUser().getUserId();
        } catch (Exception e) {
            userId = -1L;
        }
        tLcCallRecord.setCreateBy(userId == null ? null : userId.toString());
        tLcCallRecord.setMakeCallTime(new Date());
        tLcCallRecordMapper.insertTLcCallRecord(tLcCallRecord);
//        OrgPackage orgPackage = this.orgPackageService.selectOrgPackageByOrgId(tLcCallRecord.getOrgId());
        // 通话录音地址不为空并且通话录音是否推送到质检系统
//        if (!"-1".equals(tLcCallRecord.getCreateBy()) && StringUtils.isNotBlank(tLcCallRecord.getCallRadioLocation()) &&
//                orgPackage != null && orgPackage.getSendRadioQc().equals(IsNoEnum.IS.getCode())) {
//            // 异步将录音文件推送到录音质检系统
//            this.sendRadioQcRecordService.sendRadioToQualityCheck(tLcCallRecord, orgPackage);
//        }
    }

    /**
     * 修改通话结果记录
     *
     * @param tLcCallRecord 通话结果记录
     * @return 结果
     */
    @Override
    public int updateTLcCallRecord(TLcCallRecord tLcCallRecord) {
        // 异步将录音文件推送到录音质检系统
        this.sendRadioQcRecordService.sendRadioToQualityCheck(tLcCallRecord, ShiroUtils.getSysUser().getJobNo(), ShiroUtils.getSysUser().getLoginName());
        return tLcCallRecordMapper.updateTLcCallRecord(tLcCallRecord);
    }

    /**
     * 删除通话结果记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcCallRecordByIds(String ids) {
        return tLcCallRecordMapper.deleteTLcCallRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除通话结果记录信息
     *
     * @param id 通话结果记录ID
     * @return 结果
     */
    @Override
    public int deleteTLcCallRecordById(Long id) {
        return tLcCallRecordMapper.deleteTLcCallRecordById(id);
    }

    @Override
    public List<TLcCallRecord> findCallRecordByCertificateNo(String certificateNo) {
        return this.tLcCallRecordMapper.findCallRecordByCertificateNo(certificateNo);
    }

    @Override
    public void downLoadRadio(HttpServletRequest request, HttpServletResponse response, String id) {
        //查询录音地址
        TLcCallRecord tLcCallRecord = selectTLcCallRecordById(Long.valueOf(id));
        try {
            // 下载地址
//            String urlpath = remoteConfigure.getTelphoneRecordUrl()+tLcCallRecord.getCallRadioLocation();
            String urlpath = tLcCallRecord.getCallRadioLocation();
            // 下载名称
            String[] split = tLcCallRecord.getCallRadioLocation().split("/");
            String fileName = split[split.length-1];
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("multipart/form-data");
//            response.setHeader("Content-Disposition",
//                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, fileName));
//            FileUtils.writeBytes(urlpath, response.getOutputStream());
            if (urlpath.startsWith("https://")) {
                DownLoadFromHttpsUtil.downLoadFromHttps(response,urlpath);
            } else if (urlpath.startsWith("http://")) {
                DownLoadFromHttpsUtil.downLoadFromHttp(response,urlpath);
            }
        } catch (Exception e) {
            log.error("下载录音异常，exception is {}", e);
            throw new RuntimeException("下载录音异常");
        }
    }

    @Override
    public List<TLcCallRecord> findCallRecordByCaseNo(String caseNo) {
        return this.tLcCallRecordMapper.findCallRecordByCaseNo(caseNo);
    }

    @Override
    public List<TLcCallRecord> findHisDuncaseCallRecordByCaseNo(String caseNo) {
        return this.tLcCallRecordMapper.findHisDuncaseCallRecordByCaseNo(caseNo);
    }

    @Override
    public List<CallContent> viewCallContent(String id) {
        TLcCallRecord tLcCallRecord = this.tLcCallRecordMapper.selectTLcCallRecordById(Long.valueOf(id));
        List<CallContent> callContentList = JSONObject.parseArray(tLcCallRecord.getRemark(), CallContent.class);
        return callContentList;
    }

    @Override
    public void downRecord(HttpServletRequest request, HttpServletResponse response, String ids) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String curTime = sdf.format(new Date());
        String tempPath = tempDir;
        String target = tempDir+"/recordFiles_"+curTime;
        String fileName="recordFiles_"+curTime+".zip";
        String[] recordIds = ids.split(",");
        try {
            File file = new File(target);
            if(!file.exists()){
                file.mkdirs();
            }
            //下载文件
            for (String recordId : recordIds) {
                //查询录音地址
                TLcCallRecord tLcCallRecord = tLcCallRecordMapper.selectTLcCallRecordById(Long.valueOf(recordId));
                Date callStartTime = tLcCallRecord.getCallStartTime();
                String phone = tLcCallRecord.getPhone();
                String caseNo = tLcCallRecord.getCaseNo();
                // 下载文件
                String urlpath = tLcCallRecord.getCallRadioLocation();
                String suffix = urlpath.substring(urlpath.lastIndexOf(".") + 1);
                String name = sdf.format(callStartTime)+"_"+phone+"_"+caseNo+"."+suffix;
                DownLoadFromHttpsUtil.downLoadFromHttps2(urlpath, target,name);
            }
            //压缩文件
            String zip = zipUtil.zipFile(file, target,"zip");
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, fileName));
            FileUtils.writeBytes(target+ ".zip", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("下载录音异常，exception is {}", e);
        }finally {
            zipUtil.deleteDir(new File(tempPath));
        }
    }

    @Override
    public List<TLcCallRecord> findListenCallRecord(TLcCallRecord tLcCallRecord) {
        int startCallLen = tLcCallRecord.getStartCallLen() == null ? 0 : tLcCallRecord.getStartCallLen();
        int endCallLen = tLcCallRecord.getEndCallLen() == null ? 0 : tLcCallRecord.getEndCallLen();
        tLcCallRecord.setStartCallLen(startCallLen*1000);
        tLcCallRecord.setEndCallLen(endCallLen*1000);
        if (tLcCallRecord.getCallEndTime() != null) {
            tLcCallRecord.setCallEndTime(DateUtils.getEndOfDay(tLcCallRecord.getCallEndTime()));
        }
        List<TLcCallRecord> listenCallRecord = this.tLcCallRecordMapper.findListenCallRecord(tLcCallRecord);
        /*for (TLcCallRecord tlcCallRecord : listenCallRecord) {
            tlcCallRecord.setLoginName(ShiroUtils.getLoginName());
        }*/
        return listenCallRecord;
    }

    @Override
    public int updateStar(TLcCallRecord tLcCallRecord) {
        int star = tLcCallRecord.getStar();
        star += 1;
        tLcCallRecord.setStar(star);
        return tLcCallRecordMapper.updateStar(tLcCallRecord);
    }

    @Override
    public List<TLcCallRecord> findTLcCallRecordListByDate(TLcCallRecord tLcCallRecord) {
        return this.tLcCallRecordMapper.findTLcCallRecordListByDate(tLcCallRecord);
    }

    @Override
    public List<Map<String, Object>> selectCallRecordByTime(Date createTime, int pageNum, int pageSize) {
        return tLcCallRecordMapper.selectCallRecordByTime(createTime,pageNum,pageSize);
    }

    @Override
    public Integer selectCallRecordCount(Date createTime) {
        return tLcCallRecordMapper.selectCallRecordCount(createTime);
    }

    @Override
    public TLcCallRecord selectTLcHisCallRecordById(Long id) {
        return this.tLcCallRecordMapper.selectTLcHisCallRecordById(id);
    }

    @Override
    public void downLoadHisRadio(HttpServletRequest request, HttpServletResponse response, String id) {
        //查询录音地址
        TLcCallRecord tLcCallRecord = selectTLcHisCallRecordById(Long.valueOf(id));
        try {
            // 下载地址
//            String urlpath = remoteConfigure.getTelphoneRecordUrl()+tLcCallRecord.getCallRadioLocation();
            String urlpath = tLcCallRecord.getCallRadioLocation();
            // 下载名称
            String[] split = tLcCallRecord.getCallRadioLocation().split("/");
            String fileName = split[split.length-1];
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("multipart/form-data");
//            response.setHeader("Content-Disposition",
//                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, fileName));
//            FileUtils.writeBytes(urlpath, response.getOutputStream());
            if (urlpath.startsWith("https://")) {
                DownLoadFromHttpsUtil.downLoadFromHttps(response,urlpath);
            } else if (urlpath.startsWith("http://")) {
                DownLoadFromHttpsUtil.downLoadFromHttp(response,urlpath);
            }
        } catch (Exception e) {
            log.error("下载录音异常，exception is {}", e);
            throw new RuntimeException("下载录音异常");
        }
    }

    @Override
    public List<JxphCallRecord> selectJxphCallRecord(Map<String, Object> param) {
        return this.tLcCallRecordMapper.selectJxphCallRecord(param);
    }

    @Override
    public List<TLcCallRecord> findZjCallRecordListByDate(TLcCallRecord tLcCallRecord) {
        return this.tLcCallRecordMapper.findZjCallRecordListByDate(tLcCallRecord);
    }

    @Override
    public Long selectCountByTimePeriod(Map<String, Object> param) {
        return this.tLcCallRecordMapper.selectCountByTimePeriod(param);
    }

    @Override
    public List<TLcCallRecordForJX> selectTLcCallRecordListForJX(TLcCallRecord tLcCallRecord) {
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
        List<TLcCallRecord> list = tLcCallRecordMapper.selectTLcCallRecordList(tLcCallRecord);
        List<TLcCallRecordForJX> jxList = this.convertJXList(list,tLcCallRecord);
        return jxList;
    }

    @Override
    public List<TLcCallRecordForDQ> selectTLcCallRecordListForDQ(TLcCallRecord tLcCallRecord) {
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
        List<TLcCallRecord> list = tLcCallRecordMapper.selectTLcCallRecordList(tLcCallRecord);
        List<TLcCallRecordForDQ> dqList = this.ConvertDQList(list);
        return dqList;
    }

    @Override
    public List<TLcCallRecord> selectCallRecordListByOrgIdAndTime(TLcCallRecord callRecord) {
        return this.tLcCallRecordMapper.selectCallRecordListByOrgIdAndTime(callRecord);
    }

    @Override
    public void updateTLcCallRecordByUcid(TLcCallRecord tLcCallRecord) {
        this.tLcCallRecordMapper.updateTLcCallRecordByUcid(tLcCallRecord);
    }

    private List<TLcCallRecordForDQ> ConvertDQList(List<TLcCallRecord> list) {
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

    private List<TLcCallRecordForJX> convertJXList(List<TLcCallRecord> list, TLcCallRecord tLcCallRecord) {
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


}
