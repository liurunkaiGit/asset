package com.ruoyi.quartz.task;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.service.ITLcCallRecordService;
import com.ruoyi.utils.DownLoadFromHttpsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 定时任务调度--每日语音文件下载s
 * 
 * @author fengzhitao
 */
@Slf4j
@Component("callTask")
public class CallTask
{
    @Autowired
    private ITLcCallRecordService tLcCallRecordService;
    @Autowired
    private ISysConfigService sysConfigService;
    @Value("${isEnableTimer}")
    private Boolean isEnableTimer;

    /**
     * 下载语音通话文件
     * 默认不传开始时间、结束时间
     */
    public void downloadCallFile(){
        this.downloadCallFile(null,null);
    }
    /**
     * 下载语音通话文件
     * @param startDate
     * @param endDate
     */
    public void downloadCallFile(String startDate,String endDate){
        if(!isEnableTimer){
            log.info("下载录音定时任务已经再执行了");
            return;
        }
        if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)){
            // 获取前一天的0点和24点
            SimpleDateFormat dateFmt = new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_MM_SS);
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date dBefore = calendar.getTime();
            startDate = dateFmt.format(dBefore);
            startDate = startDate.substring(0, 10) + " 00:00:00";
            endDate = startDate.substring(0, 10) + " 23:59:59";
        }else if(StringUtils.isEmpty(endDate) && StringUtils.isNotEmpty(startDate)){
            startDate = startDate.substring(0, 10) + " 00:00:00";
            endDate = startDate.substring(0, 10) + " 23:59:59";
        } else if(StringUtils.isEmpty(startDate) && StringUtils.isNotEmpty(endDate)){
            startDate = endDate.substring(0, 10) + " 00:00:00";
            endDate = endDate.substring(0, 10) + " 23:59:59";
        }else{//开始时间和结束时间都不为空
            startDate = startDate.substring(0, 10) + " 00:00:00";
            endDate = endDate.substring(0, 10) + " 23:59:59";
        }
        /*startDate = "2020-06-08 00:00:00";
        String endDate = "2020-06-08 23:59:59";*/

        TLcCallRecord tLcCallRecord = new TLcCallRecord();
        tLcCallRecord.setStartCreateTime(DateUtils.parseDate(startDate));
        tLcCallRecord.setEndCreateTime(DateUtils.parseDate(endDate));

        List<TLcCallRecord> list = tLcCallRecordService.findTLcCallRecordListByDate(tLcCallRecord);
        String callFilePath = sysConfigService.selectConfigByKey("file.callFilePath");
        if(list != null && list.size() > 0){
            for(int i = 0; i < list.size(); i ++){
                TLcCallRecord tmpTLcCallRecord = list.get(i);
                if(StringUtils.isNotEmpty(tmpTLcCallRecord.getCallRadioLocation())){//存在下载地址
                    String[] str = tmpTLcCallRecord.getCallRadioLocation().split("/");
                    String fileName = "";
                    if(str.length > 1){
                        fileName = str[str.length - 1];
                        if(tmpTLcCallRecord.getCallRadioLocation().startsWith("https")){//https类型的URL下载
                            try{
                                String filePath = callFilePath + File.separator + tmpTLcCallRecord.getOrgName() + File.separator + tmpTLcCallRecord.getPlatform() + File.separator + startDate.substring(0, 10);
                                log.info("下载语音文件保存目录为，filePath={}",filePath);
                                DownLoadFromHttpsUtil.downloadFileForHttps(tmpTLcCallRecord.getCallRadioLocation(),filePath,fileName);
                            }catch (Exception e){
                                log.error("下载语音文件异常={}",e);
                            }
                        } else if(tmpTLcCallRecord.getCallRadioLocation().startsWith("http")){//http类型的URL下载
                            try{
                                String filePath = callFilePath + File.separator + tmpTLcCallRecord.getOrgName() + File.separator + tmpTLcCallRecord.getPlatform() + File.separator + startDate.substring(0, 10);
                                log.info("下载语音文件保存目录为，filePath={}",filePath);
                                DownLoadFromHttpsUtil.downLoadFromUrlHttp(tmpTLcCallRecord.getCallRadioLocation(),filePath,fileName);
                            }catch (Exception e){
                                log.error("下载语音文件异常={}",e);
                            }
                        }
                    }else{
                        log.error("下载语音文件URL异常，语音记录ID={},URL={}",tmpTLcCallRecord.getId(),tmpTLcCallRecord.getCallRadioLocation());
                        continue;
                    }
                }
            }
        }
    }
}
