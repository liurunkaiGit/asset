package com.ruoyi.shareproject.projectinformation.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 【项目信息管理】对象 t_lp_project_information
 *
 * @author gaohg
 * @date 2020-10-14
 */
@Data
public class TLpProjectInformation extends BaseEntity {
    private static final long serialVersionUID = 15515L;

    /** 主键 */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "项目名称")
    private String names;

    /** 委托方code */
    private String orgCode;
    /** 委托方名字 */
    @Excel(name = "关联委托方")
    private String orgName;
    /** 委托方 服务的机构id */
    private Long deptId;
    /** 委托方 服务的机构名字*/
    private String deptName;
    /** 归属中心 id*/
    private Long belongingCenterId;
    /** 归属中心 */
    @Excel(name = "归属中心")
    private String belongingCenterName;

    /** 项目经理 */
    @Excel(name = "项目经理")
    private String projectManager;

    /** 项目主管 */
    @Excel(name = "项目主管")
    private String projectDirector;

    /** 坐席数量 */
    @Excel(name = "坐席数量")
    private Integer seatsNumber;

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
    private Integer casesNumber;

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
    /** 账龄 */
    private String accountAge;
}
