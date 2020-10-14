package com.ruoyi.assetspackage.domain.distribution;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 智能分案配置对象 t_lc_allocat_case_config
 *
 * @author liurunkai
 * @date 2020-04-23
 */
@Data
public class TLcAllocatCaseConfigAsset extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键自增
     */
    private Long id;

    /**
     * 委托方id
     */
    @Excel(name = "委托方id")
    private Long orgId;

    /**
     * 委托方名称
     */
    @Excel(name = "委托方名称")
    private String orgName;

    /**
     * 规则引擎
     */
    @Excel(name = "规则引擎")
    private String ruleEngine;

    /**
     * 机器人
     */
    @Excel(name = "机器人")
    private String robot;

    /**
     * 话务平台
     */
    @Excel(name = "话务平台")
    private String callPlatform;

    /**
     * 是否自动分案
     */
    @Excel(name = "是否自动分案")
    private Long autoAllocatCase;

    /**
     * 自动分案策略
     */
    @Excel(name = "自动分案策略")
    private String allocatCaseStartegy;

    @Excel(name = "自动分案规则")
    private String allocatCaseRule;

    /** 规则引擎名称 */
    @Excel(name = "规则引擎名称")
    private String ruleEngineName;

    /** 机器人名称 */
    @Excel(name = "机器人名称")
    private String robotName;

    /** 话务平台名称 */
    @Excel(name = "话务平台名称")
    private String callPlatformName;

    /** 分配策略名称 */
    @Excel(name = "分配策略名称")
    private String allocatCaseStartegyName;

    /** 分配规则名称 */
    @Excel(name = "分配规则名称")
    private String allocatCaseRuleName;
}
