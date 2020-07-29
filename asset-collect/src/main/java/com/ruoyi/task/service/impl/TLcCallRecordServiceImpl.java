package com.ruoyi.task.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.domain.RemoteConfigure;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.assetspackage.util.DownLoadFromHttpsUtil;
import com.ruoyi.caseConfig.domain.TLcAllocatCaseConfig;
import com.ruoyi.caseConfig.service.ITLcAllocatCaseConfigService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.util.zipUtil;
import com.ruoyi.radioQc.service.ITLcSendRadioQcRecordService;
import com.ruoyi.robot.domain.CallContent;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcCallRecordForXY;
import com.ruoyi.task.mapper.TLcCallRecordMapper;
import com.ruoyi.task.service.ITLcCallRecordService;
import com.ruoyi.task.service.ITLcTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    private List<TLcCallRecordForXY> ConvertXYList(List<TLcCallRecord> list){
        List<TLcCallRecordForXY> resultList = new ArrayList<>();
        for (TLcCallRecord tLcCallRecord : list) {
            TLcCallRecordForXY xyEntity = new TLcCallRecordForXY();
            xyEntity.setSque(tLcCallRecord.getSque());
            xyEntity.setCaseNo(tLcCallRecord.getCaseNo());
            xyEntity.setYwdetp(tLcCallRecord.getYwdetp());
            xyEntity.setWbjb(tLcCallRecord.getWbjb());
            xyEntity.setCustomName(tLcCallRecord.getCustomName());
            xyEntity.setProductName(tLcCallRecord.getProductName());
            xyEntity.setCsdz(tLcCallRecord.getCsdz());
            xyEntity.setCreateTime(tLcCallRecord.getCreateTime());
            xyEntity.setPhone(tLcCallRecord.getPhone());
            xyEntity.setEnterCollDate(tLcCallRecord.getEnterCollDate());
            xyEntity.setTar(tLcCallRecord.getTar());
            xyEntity.setCallResult(tLcCallRecord.getCallResult());
            //特殊字段处理
            String contactName = tLcCallRecord.getContactName();
            Integer contactRelation = tLcCallRecord.getContactRelation();
            String relateion = this.getRelateion(contactRelation);
            contactName = relateion+"-"+contactName;
            xyEntity.setContactName(contactName);

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
        this.sendRadioQcRecordService.sendRadioToQualityCheck(tLcCallRecord);
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





}
