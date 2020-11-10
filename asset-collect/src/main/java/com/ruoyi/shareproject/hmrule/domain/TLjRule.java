package com.ruoyi.shareproject.hmrule.domain;

import com.ruoyi.system.domain.SysUser;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 【居家规则】对象 t_lj_rule
 * 
 * @author gaohg
 * @date 2020-11-02
 */
@Data
public class TLjRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 规则编码 */
    @Excel(name = "规则编码")
    private String ruleCode;

    /** 规则状态 */
    @Excel(name = "规则状态")
    private String ruleStatus;

    /** 在岗状态 */
    @Excel(name = "在岗状态")
    private String onthejobStatus;

    /** 规则种类0=部门 1=人员 */
    @Excel(name = "规则种类0=部门 1=人员")
    private String ruleType;

    /** 规则名称 */
    @Excel(name = "规则名称")
    private String ruleName;

    /** 规则有效开始时间 */
    @Excel(name = "规则有效开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 规则有效结束时间 */
    @Excel(name = "规则有效结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 机构id */
    @Excel(name = "机构id")
    private Long orgId;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String orgName;

    private String deptIds;
    private String deptNames;
    private String userIds;
    private String userNames;

    private String ids;
    private String [] array;
    private String names;

    private List<TLjRuleRange> rrList;
    //最终需要检索的用户
    private List<SysUser> userInfos;

}
