package com.ruoyi.assetspackage.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author guozeqi
 * @create 2020-01-02
 */
@Data
@Component
@PropertySource("classpath:configure/recordDataHeadMapping.properties")
public class RecordSystemHeadMapping {

    @Value("${org_caseNo}")
    private String org_caseNo;
    @Value("${certificate_no1}")
    private String certificate_no;
    @Value("${phone}")
    private String phone;
    @Value("${relation}")
    private String relation;
    /*@Value("${phone_code}")
    private String phone_code;*/
    @Value("${remark}")
    private String remark;
    @Value("${seat}")
    private String seat;
    @Value("${make_call_time}")
    private String make_call_time;
    @Value("${call_start_time}")
    private String call_start_time;
    @Value("${call_end_time}")
    private String call_end_time;
    @Value("${call_length}")
    private String call_length;
    @Value("${call_record_id}")
    private String call_record_id;
    @Value("${grade}")
    private String grade;
    @Value("${user_name2}")
    private String name;
    @Value("${call_status}")
    private String call_status;



}
