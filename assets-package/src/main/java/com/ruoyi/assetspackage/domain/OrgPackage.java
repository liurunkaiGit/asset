package com.ruoyi.assetspackage.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.Set;

/**
 * 机构对象 org_package
 *
 * @author guozeqi
 * @date 2019-12-27
 */
@Data
public class OrgPackage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 委托方名称
     */
    @Excel(name = "委托方名称")
    private String orgName;

    /**
     * 委托方编码
     */
    @Excel(name = "委托方编码")
    private String orgCode;

    /**
     * 合作开始时间
     */
    @Excel(name = "合作开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 合作结束时间
     */
    @Excel(name = "合作结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 机构状态
     */
    @Excel(name = "机构状态")
    private String orgStatus;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /**
     * 修改人
     */
    @Excel(name = "修改人")
    private String updateBy;

    /**
     * 修改时间
     */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    private String StartDate2;

    private String endDate2;

    private Long deptId;
    /**
     * 服务部门
     */
    @Excel(name = "服务部门")
    private String deptName;

    private String projectName;

    /**
     * 是否是用机器人1：是2否
     */
    @Excel(name = "是否是用机器人1：是2否")
    private Integer sendRobot;

    /**
     * 是否自动开启机器人 任务1：是2否
     */
    @Excel(name = "是否自动开启机器人 任务1：是2否")
    private Integer autoStartRobotTask;

    /**
     * 是否推送到语音质检1：是2否
     */
    @Excel(name = "是否推送到语音质检1：是2否")
    private Integer sendRadioQc;

    /**
     * 是否使用规则引擎来智能分案1：是2否
     */
    @Excel(name = "是否使用规则引擎来智能分案1：是2否")
    private Integer sendRuleEngine;

    /**
     * 是否自动分配任务1：是2否
     */
    @Excel(name = "是否自动分配任务1：是2否")
    private Integer autoAllocatTask;

    /**
     * 分案策略 1：数量平均2金额平均
     */
    @Excel(name = "分案策略 1：数量平均2金额平均")
    private Integer allocatTaskStartegy;

    /**
     * 拥有查看数据权限的部门集合
     */
    private Set<Long> deptIds;

    /**
     * 是否度为小满自动评分
     */
    private String isAutoScore;

    /**
     * 是否自动发送短信，1：是吗，2：否
     */
    @Excel(name = "是否自动发送短信，1：是吗，2：否")
    private Integer isAutoSendSms;

    /**
     * 短信模板id
     */
    @Excel(name = "短信模板id")
    private String smsTemplateId;

    /**
     * 短信模板名称
     */
    @Excel(name = "短信模板名称")
    private String smsTemplateName;

}
