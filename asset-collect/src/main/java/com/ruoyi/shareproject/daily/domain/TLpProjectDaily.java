package com.ruoyi.shareproject.daily.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 【项目日报】对象 t_lp_project_daily
 * 
 * @author gaohg
 * @date 2020-10-26
 */
@Data
public class TLpProjectDaily extends BaseEntity
{
    private static final long serialVersionUID = 1556565656656565L;

    /** $column.columnComment */
    private Long id;

    /** 今日出勤 */
    @Excel(name = "今日出勤")
    private String attendanceToday;

    /** 今日指标 */
    @Excel(name = "今日指标")
    private String indexToday;

    /** 日报时间 */
    @Excel(name = "日报时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dailyTime;
    /** 客户最新通知*/
    @Excel(name = "客户最新通知")
    private String customerUpdate;

    /** 项目其它事项 */
    @Excel(name = "项目其它事项")
    private String otherProject;

    /** $column.columnComment */
    @Excel(name = "项目其它事项")
    private String remarks;

    /** 机构id */
    @Excel(name = "机构id")
    private Long orgId;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String orgName;

    /** 日报名称 */
    @Excel(name = "日报名称")
    private String namesAttendance;

    /** 项目表id */
    @Excel(name = "pro_id")
    private String urlAttendance;

    /** 项目表id */
    @Excel(name = "项目表id")
    private Long proId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String proName;

    private String dailyName;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("attendanceToday", getAttendanceToday())
            .append("indexToday", getIndexToday())
            .append("dailyTime", getDailyTime())
            .append("customerUpdate", getCustomerUpdate())
            .append("otherProject", getOtherProject())
            .append("remarks", getRemarks())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("orgId", getOrgId())
            .append("orgName", getOrgName())
            .append("namesAttendance", getNamesAttendance())
            .append("urlAttendance", getUrlAttendance())
            .append("proId", getProId())
            .append("proName", getProName())
            .toString();
    }
}
