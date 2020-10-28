package com.ruoyi.shareproject.daily.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 【请填写功能名称】对象 t_lp_attendance_daily
 * 
 * @author gaohg
 * @date 2020-10-26
 */
@Data
public class TLpAttendanceDaily extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 项目表id */
    @Excel(name = "项目表id")
    private Long proId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String proName;

    /** 出勤日期 */
    @Excel(name = "出勤日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date attendanceDate;

    /** 应出勤人数 */
    @Excel(name = "应出勤人数")
    private Integer attendanceRequired;

    /** 实际出勤人数 */
    @Excel(name = "实际出勤人数")
    private Integer actualAttendance;

    /** 申请离职人数 */
    @Excel(name = "申请离职人数")
    private Integer numberOfDepartures;

    /** 待招聘上线人数 */
    @Excel(name = "待招聘上线人数")
    private Integer numberOfRecruiters;

    /** 新人上线人数 */
    @Excel(name = "新人上线人数")
    private Integer newNumberOfRecruiters;

    /** 休假旷工请假 */
    @Excel(name = "休假旷工请假")
    private Integer numberTypes;

    /** 机构id */
    @Excel(name = "机构id")
    private Long orgId;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String orgName;

    /** 日报id */
    @Excel(name = "日报id")
    private Long dailyId;

    /** $column.columnComment */
    @Excel(name = "日报id")
    private String dailyName;
}
