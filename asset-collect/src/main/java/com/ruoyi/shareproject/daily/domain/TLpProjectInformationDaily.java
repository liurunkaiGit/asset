package com.ruoyi.shareproject.daily.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 【项目日报-项目信息】对象 t_lp_project_information_daily
 * 
 * @author gaohg
 * @date 2020-10-26
 */
@Data
public class TLpProjectInformationDaily extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String names;

    /** 机构编码 */
    @Excel(name = "机构编码")
    private String orgCode;

    /** $column.columnComment */
    @Excel(name = "机构编码")
    private String orgName;

    /** 服务部门id */
    @Excel(name = "服务部门id")
    private Long deptId;

    /** 服务部门名称 */
    @Excel(name = "服务部门名称")
    private String deptName;

    /** 项目经理 */
    @Excel(name = "项目经理")
    private String projectManager;

    /** 项目主管 */
    @Excel(name = "项目主管")
    private String projectDirector;

    /** 坐席数量 */
    @Excel(name = "坐席数量")
    private Long seatsNumber;

    /** 甲方名称 */
    @Excel(name = "甲方名称")
    private String partyName;

    /** 甲方类型 */
    @Excel(name = "甲方类型")
    private String partyType;

    /** 开票信息 */
    @Excel(name = "开票信息")
    private String billingInformation;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 产品性质 */
    @Excel(name = "产品性质")
    private String productNature;

    /** 结费方式 */
    @Excel(name = "结费方式")
    private String settlementMethod;

    /** 分案逻辑 */
    @Excel(name = "分案逻辑")
    private String divisionLogic;

    /** 撤案 */
    @Excel(name = "撤案")
    private String withdrawCase;

    /** 费率 */
    @Excel(name = "费率")
    private String rates;

    /** 计费公式 */
    @Excel(name = "计费公式")
    private String billingFormula;

    /** 作业系统 */
    @Excel(name = "作业系统")
    private String operatingSystem;

    /** 话务平台 */
    @Excel(name = "话务平台")
    private String trafficPlatform;

    /** 人均委案量 */
    @Excel(name = "人均委案量")
    private Long casesNumber;

    /** 户均金额 */
    @Excel(name = "户均金额")
    private Double perHousehold;

    /** 项目开始时间 */
    @Excel(name = "项目开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 项目结束时间 */
    @Excel(name = "项目结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** $column.columnComment */
    private String delFlag;

    /** 归属中心id */
    @Excel(name = "归属中心id")
    private Long belongingCenterId;

    /** 归属中心名字 */
    @Excel(name = "归属中心名字")
    private String belongingCenterName;

    /** 账龄 */
    @Excel(name = "账龄")
    private String accountAge;

    private Long dailyId;

    /** $column.columnComment */
    private String dailyName;

}
