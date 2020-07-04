package com.ruoyi.assetspackage.domain;

import lombok.Data;
import org.springframework.stereotype.Component;


/**
 * @author guozeqi
 * @create 2019-12-26
 */
@Data
@Component
public class RecordImportDataMapping {
    private String orgCaseNo; //机构案件号
    private String certificateNo;
    private String phone;
    private String relation;
   /* private String phoneCode;*/
    private String remark;
    private String seat;
    private String makeCallTime;
    private String callStartTime;
    private String callEndTime;
    private String callLength;
    private String callRecordId;
    private String grade;
    private String name;
    private String callStatus;
    private String headRowNum;
    private String dataRowNum;
}
