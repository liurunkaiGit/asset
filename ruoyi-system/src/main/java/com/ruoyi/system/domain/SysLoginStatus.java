package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 登录状态对象 sys_login_status
 * 
 * @author guozeqi
 * @date 2020-08-06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SysLoginStatus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id;
    private String orgId;

    @Excel(name = "序号")
    private Integer sque;

    /** 统计日期 */
    @Excel(name = "统计日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date censusDate;

    /** 登录名称 */
    @Excel(name = "登录名称")
    private String loginName;

    /** 用户名称 */
    @Excel(name = "员工姓名")
    private String userName;

    /** 委托方名称 */
    @Excel(name = "归属部门")
    private String orgName;

    /** 首次登录时间 */
    @Excel(name = "首次登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 最后访问时间 */
    @Excel(name = "最后访问时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 登录次数 */
    @Excel(name = "登录次数")
    private Integer loginNum;

    /** 在线时长 */
    @Excel(name = "在线时长")
    private String onlineLen;

    /** 当前状态(0未登录,1已登录) */
    @Excel(name = "当前状态", readConverterExp = "0=未登录,1=已登录")
    private String status;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Date startCensusDate;
    private Date endCensusDate;

}
