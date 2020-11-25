package com.hdxx.sftp.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hdxx.sftp.config.DqPropertiesConfig;
import com.hdxx.sftp.config.RestTemplateUtil;
import com.hdxx.sftp.domain.TLcCallRecord;
import com.hdxx.sftp.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestClientException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    public void dqCallRadioUpload() throws Exception {
        log.info("东乔录音上传sftp定时任务开始....{}", LocalDateTime.now(ZoneId.systemDefault()));
        // 调用掘金系统接口获取东乔录音地址
        List<TLcCallRecord> callRecordList = getCallRecordList();
        if (callRecordList != null && callRecordList.size() > 0) {
            String callFilePathSuffix = "recordFiles_" + LocalDateTimeUtils.format(LocalDateTime.now(), LocalDateTimeUtils.YYYYMMDDHHMMSS);
            String callFilePath = dqPropertiesConfig.getCallRadioPath() + callFilePathSuffix;
            // 录音下载
            downLoadCallRadio(callRecordList, callFilePath);
            log.info("录音下载并生成清单成功");
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
    private void downLoadCallRadio(List<TLcCallRecord> callRecordList, String callFilePath) throws Exception {
        String fileName = LocalDate.now() + "录音清单.xlsx"; //创建名称
        String rpathfinal = callFilePath + "/" + fileName;//路径
        SXSSFWorkbook wb = new SXSSFWorkbook(1024); // 这里1024是在内存中的数量，如果大于此数量时，会写到硬盘，以避免在内存导致内存溢出
        Sheet sh = wb.createSheet();
        // 生成第一行表头
        Row row = sh.createRow(0);
        row.createCell(0).setCellValue("机构案件号");
        row.createCell(1).setCellValue("客户姓名");
        row.createCell(2).setCellValue("拨打时间");
        row.createCell(3).setCellValue("电话号码");
        row.createCell(4).setCellValue("录音名称");
        for (int i = 0; i < callRecordList.size(); i++) {
            TLcCallRecord tmpTLcCallRecord = callRecordList.get(i);
            String phone = tmpTLcCallRecord.getPhone();
            if (StringUtils.isNotBlank(phone)) {
                phone = phone.replaceAll("\\*","_");
            }
            String suffix = tmpTLcCallRecord.getCallRadioLocation().substring(tmpTLcCallRecord.getCallRadioLocation().lastIndexOf(".") + 1);
            String name = DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS, tmpTLcCallRecord.getCreateTime()) + "_" + phone + "_" + tmpTLcCallRecord.getCaseNo() + "." + suffix;
            if (tmpTLcCallRecord.getCallRadioLocation().startsWith("https")) {
                try {
                    log.info("下载https语音文件保存目录为，filePath={}", callFilePath);
                    DownLoadFromHttpsUtil.downloadFileForHttps(tmpTLcCallRecord.getCallRadioLocation(), callFilePath, name);
                } catch (Exception e) {
                    log.error("下载https语音文件异常={}", e);
                }
            } else if (tmpTLcCallRecord.getCallRadioLocation().startsWith("http")) {
                try {
                    log.info("下载http语音文件保存目录为，filePath={}", callFilePath);
                    DownLoadFromHttpsUtil.downLoadFromUrlHttp(tmpTLcCallRecord.getCallRadioLocation(), callFilePath, name);
                } catch (Exception e) {
                    log.error("下载http语音文件异常={}", e);
                }
            }
            // 生成excel文件内容
            Row row1 = sh.createRow(i + 1);
            row1.createCell(0).setCellValue(tmpTLcCallRecord.getCaseNo());
            row1.createCell(1).setCellValue(tmpTLcCallRecord.getCustomName());
            row1.createCell(2).setCellValue(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, tmpTLcCallRecord.getCreateTime()));
            row1.createCell(3).setCellValue(phone);
            row1.createCell(4).setCellValue(name);
        }
        FileOutputStream output = new FileOutputStream(rpathfinal);
        wb.write(output);
        output.close();
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

    public static void main(String[] args) {
        String s = "a**n";
        s = s.replaceAll("\\*","_");
        System.out.println(s);
    }

}
