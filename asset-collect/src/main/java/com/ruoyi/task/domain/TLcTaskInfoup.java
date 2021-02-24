package com.ruoyi.task.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigInteger;
import java.util.Date;

/**
 * 信息更新对象 t_lc_task_infoup
 * 
 * @author gaohg
 * @date 2021-02-04
 */
@Data
public class TLcTaskInfoup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主鍵ID */
    @Excel(name = "任务ID")
    private Long id;
    private Long taskId;
    /** 所属机构 */
    @Excel(name = "委托机构")
    private String orgName;
    /** 案件编号 */
    @Excel(name = "结构案件号")
    private String caseNo;
    /** 委案金额 */
    @Excel(name = "委案金额")
    private Double arrearsTotal;


    /** 证件号 */
    @Excel(name = "证件号")
    private String certificateNo;
    @Excel(name = "信息状态")
    private String xinxiStatus;
    /** 信息更新人 */
    @Excel(name = "操作人")
    private String infoupBy;

    /** 信息更新时间 */
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date infoupTime;
    @Excel(name = "信息更新")
    private String content;

    /** 证件类型 */
    private Long certificateType;

    /** 客户编号 */
    private String customCode;

    /** 客户姓名 */
    private String customName;

    /** 逾期天数 */
    private Long overdueDays;

    /** 业务归属人 */
    private String ownerName;

    /** 业务归属机构 */
    private String orgId;



    /** 信息审批人 */
    private String modifyBy;

    /** 信息审批时间 */
    private Date modifyTime;

    /** 信息审核状态 */
    private Integer infoupAproStatus;



    /** 信息更新状态 */
    private Integer infoupStatus;
    private String phone;
    private String certificateAddress;
    private BigInteger [] ids;
    private BigInteger [] taskIds;
    private String importBatchNo;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("caseNo", getCaseNo())
            .append("certificateNo", getCertificateNo())
            .append("certificateType", getCertificateType())
            .append("customCode", getCustomCode())
            .append("customName", getCustomName())
            .append("arrearsTotal", getArrearsTotal())
            .append("overdueDays", getOverdueDays())
            .append("ownerName", getOwnerName())
            .append("orgId", getOrgId())
            .append("orgName", getOrgName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("modifyBy", getModifyBy())
            .append("modifyTime", getModifyTime())
            .append("infoupAproStatus", getInfoupAproStatus())
            .append("infoupBy", getInfoupBy())
            .append("infoupTime", getInfoupTime())
            .append("infoupStatus", getInfoupStatus())
            .toString();
    }
}
