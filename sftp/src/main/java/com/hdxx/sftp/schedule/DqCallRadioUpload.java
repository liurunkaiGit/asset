package com.hdxx.sftp.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hdxx.sftp.config.DqPropertiesConfig;
import com.hdxx.sftp.config.RestTemplateUtil;
import com.hdxx.sftp.domain.TLcCallRecord;
import com.hdxx.sftp.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestClientException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 东乔录音上传sftp定时
 * @author: liurunkai
 * @Date: 2020/11/20 14:28
 */
@Slf4j
@Configuration
public class DqCallRadioUpload {

    @Autowired
    private DqPropertiesConfig dqPropertiesConfig;
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Value("${asset.getCallRecordListUrl}")
    private String getCallRecordListUrl;

    @Scheduled(cron = "${dq.uploadRadio.timer}")
    public void dqCallRadioUpload() {
        log.info("东乔录音上传sftp定时任务开始....{}", LocalDateTime.now(ZoneId.systemDefault()));
        // 调用掘金系统接口获取东乔录音地址
        List<TLcCallRecord> callRecordList = getCallRecordList();
        if (callRecordList != null && callRecordList.size() > 0) {
            String callFilePathSuffix = "recordFiles_" + LocalDateTimeUtils.format(LocalDateTime.now(), LocalDateTimeUtils.YYYYMMDDHHMMSS);
            String callFilePath = dqPropertiesConfig.getCallRadioPath() + callFilePathSuffix;
            // 录音下载
            downLoadCallRadio(callRecordList, callFilePath);
            log.info("录音下载成功");
            // 录音打包、压缩文件
            zip(callFilePath);
            log.info("录音打包压缩成功");
            // 上传东乔sftp
            uploadSftp(callFilePath + ".zip", callFilePathSuffix + ".zip");
            log.info("东乔录音上传sftp成功");
        } else {
            log.info("东乔录音为空：{}", LocalDateTime.now(ZoneId.systemDefault()));
        }
    }

    /**
     * 录音上传sftp
     *
     * @param path
     */
    private void uploadSftp(String path, String fileName) {
        try {
            SFTPUtil sftp = new SFTPUtil(dqPropertiesConfig.getUsername(), dqPropertiesConfig.getPassword(), dqPropertiesConfig.getHost(), dqPropertiesConfig.getPort());
            sftp.login();
            File file = new File(path);
            InputStream is = new FileInputStream(file);
            // directory路径必须存在
            sftp.upload(dqPropertiesConfig.getUploadPath(), fileName, is);
            sftp.logout();
        } catch (Exception e) {
            log.error("上传sftp失败，error is：{}", e);
            throw new RuntimeException("上传sftp失败");
        }
    }

    /**
     * 压缩打包
     */
    private void zip(String callFilePath) {
        try {
            ZipUtil.zipFile(new File(callFilePath), callFilePath, "zip");
        } catch (Exception e) {
            log.error("打包失败，error is：{}", e);
            throw new RuntimeException("打包失败");
        }
    }

    /**
     * 录音下载
     *
     * @param callRecordList
     */
    private void downLoadCallRadio(List<TLcCallRecord> callRecordList, String callFilePath) {
        for (TLcCallRecord tmpTLcCallRecord : callRecordList) {
            String[] str = tmpTLcCallRecord.getCallRadioLocation().split("/");
            if (tmpTLcCallRecord.getCallRadioLocation().startsWith("https")) {
                try {
                    log.info("下载https语音文件保存目录为，filePath={}", callFilePath);
                    DownLoadFromHttpsUtil.downloadFileForHttps(tmpTLcCallRecord.getCallRadioLocation(), callFilePath, str[str.length - 1]);
                } catch (Exception e) {
                    log.error("下载https语音文件异常={}", e);
                }
            } else if (tmpTLcCallRecord.getCallRadioLocation().startsWith("http")) {
                try {
                    log.info("下载http语音文件保存目录为，filePath={}", callFilePath);
                    DownLoadFromHttpsUtil.downLoadFromUrlHttp(tmpTLcCallRecord.getCallRadioLocation(), callFilePath, str[str.length - 1]);
                } catch (Exception e) {
                    log.error("下载http语音文件异常={}", e);
                }
            }
        }
    }

    /**
     * 调用掘金系统接口获取东乔录音地址
     *
     * @return
     */
    private List<TLcCallRecord> getCallRecordList() {
        List<TLcCallRecord> callRecordList = new ArrayList<>();
        try {
            TLcCallRecord callRecord = TLcCallRecord.builder().orgId(dqPropertiesConfig.getOrgId())
                    .startCreateTime(LocalDateTimeUtils.getMinDate(LocalDate.now()))
                    .endCreateTime(LocalDateTimeUtils.getMaxDate(LocalDate.now()))
                    .build();
            ResponseEntity<Response> responseEntity = restTemplateUtil.getRestTemplate().postForEntity(getCallRecordListUrl, callRecord, Response.class);
            if (responseEntity.getStatusCodeValue() == HttpStatus.OK.value() && responseEntity.getBody() != null && responseEntity.getBody().getStatus() == HttpStatus.OK.value()) {
                Object result = responseEntity.getBody().getResult();
                String string = JSON.toJSONString(result);
                callRecordList = JSONObject.parseObject(string, new TypeReference<List<TLcCallRecord>>() {
                });
            } else {
                log.error("调用掘金系统获取东乔录音地址接口错误，responseEntity状态码：{}，status：{}", responseEntity.getStatusCodeValue(), responseEntity.getBody().getStatus());
            }
        } catch (RestClientException e) {
            log.error("调用掘金系统获取东乔录音地址接口错误，error is：{}", e);
        }
        return callRecordList;
    }

}
