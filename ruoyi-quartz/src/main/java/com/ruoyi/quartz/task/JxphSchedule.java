package com.ruoyi.quartz.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ruoyi.common.utils.AesUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RestTemplateUtil;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.task.domain.JxphCallRecord;
import com.ruoyi.task.service.ITLcCallRecordService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/8/10 9:24
 */
@Slf4j
@Component("jxphSchedule")
public class JxphSchedule {

    private static final String TOKEN = "JV7evvhcvxTRqYHa";

    @Autowired
    private ITLcCallRecordService callRecordService;
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private ISysConfigService sysConfigService;

    /**
     * 推送电催记录
     */
    public void sendCallRecord() {
        log.info("开始推送电催记录");
        // 查询当天的电催记录
        Map<String, Object> param = new HashMap<>();
        Date startDate = DateUtils.getStartOfDay(new Date());
        Date endDate = DateUtils.getEndOfDay(new Date());
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        param.put("orgId", 214);
        List<JxphCallRecord> jxphCallRecordList = this.callRecordService.selectJxphCallRecord(param);
        String encrypt = null;
        try {
            encrypt = AesUtils.encrypt(JSON.toJSONString(jxphCallRecordList, SerializerFeature.WriteMapNullValue), TOKEN);
            log.info("加密成功，加密后的字符串为：{}",encrypt);
        } catch (Exception e) {
            log.error("加密异常：{}", e);
            throw new RuntimeException("加密异常");
        }
        String jxphSendCallRecordUrl = this.sysConfigService.selectConfigByKey("jxphSendCallRecordUrl");
        Long timestamp = System.currentTimeMillis();
        JxphCallRecordRequest jxphCallRecordRequest = JxphCallRecordRequest.builder().nonce(RandomStringUtils.randomAlphanumeric(16)).timestamp(timestamp.toString()).msg_encrypt(encrypt).build();
        ResponseEntity<Map> responseEntity = restTemplateUtil.getRestTemplate().postForEntity(jxphSendCallRecordUrl, jxphCallRecordRequest, Map.class);
        if (responseEntity != null && responseEntity.getBody() != null) {
            Map map = responseEntity.getBody();
            if (map != null && HttpStatus.OK.value() == Integer.valueOf(map.get("code").toString())) {
                log.info("调用jxph接口:推送电催记录成功");
            } else {
                log.error("调用接口异常：{}", map.get("message"));
                throw new RuntimeException("调用接口异常");
            }
        } else {
            log.error("调用接口异常：{}", "responseEntity is null or responseEntity.getBody() is null");
            throw new RuntimeException("调用接口异常");
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JxphCallRecordRequest{
        private String nonce;
        private String timestamp;
        private String msg_encrypt;
    }
}
