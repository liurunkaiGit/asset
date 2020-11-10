package com.ruoyi.shareproject.hmrule.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【规则类型】对象 t_lj_rule_range
 * 
 * @author gaohg
 * @date 2020-11-03
 */
@Data
public class TLjRuleRange extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 规则主键 */
    @Excel(name = "规则主键")
    private Long ruleId;

    /** $column.columnComment */
    @Excel(name = "关联id")
    private String dporusId;

    /** 0=部门 1=人员 */
    @Excel(name = "0=部门 1=人员")
    private String types;

    /** $column.columnComment */
    @Excel(name = "0=部门 1=人员")
    private String names;

    /** 机构id */
    @Excel(name = "机构id")
    private Long orgId;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String orgName;

}
