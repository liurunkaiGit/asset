package com.ruoyi.assetspackage.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Set;

/**
 *
 *
 * @author guozeqi
 * @date 2020-05-11
 */
@Data
public class CollectionRecordImportTemp extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    private String orgCaseNo; //机构案件号
    private String certificateNo;
    private String phone;
    private String relation;
//    private String phoneCode;
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

    private String orgId;
    private String importBatchNo;
    private String startDate;
    private String endDate;
    private String isException;
    private Long createId;



    /**
     * 拥有查看数据权限的部门集合
     */
    private Set<Long> deptIds;

}
