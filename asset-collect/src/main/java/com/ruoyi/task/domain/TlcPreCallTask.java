package com.ruoyi.task.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 预测式外呼任务对象 tlc_pre_call_task
 * 
 * @author guozeqi
 * @date 2020-12-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class TlcPreCallTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @Excel(name = "主键ID")
    private Long id;

    /** 案件号 */
    @Excel(name = "案件号")
    private String caseNo;

    /** 案件导入批次编号 */
    @Excel(name = "案件导入批次编号")
    private String importBatchNo;

    /** 委托机构ID */
    @Excel(name = "委托机构ID")
    private String orgId;

    /** 批次号 */
    @Excel(name = "批次号")
    private Integer batchNo;

    /** 预测式计划id */
    @Excel(name = "预测式计划id")
    private String planId;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phone;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String contactName;

    /** 与本人关系 */
    @Excel(name = "与本人关系")
    private Integer contactRelation;

    /** 呼叫结果 */
    @Excel(name = "呼叫结果")
    private String callResult;

    /** 最后呼叫时间 */
    @Excel(name = "最后呼叫时间")
    private String lastCallTime;

    /** 执行状态，0：已暂停 1：已完成 2：未执行 3：取消 */
    @Excel(name = "执行状态，0：已暂停 1：已完成 2：未执行 3：取消")
    private Integer execStatus;

    /** 主叫号码 */
    private String caller;

    /** 是否去重，0：不去重 1：去重 */
    private String isDistinct;

    /** 委案金额 */
    private String arrearsTotal;
    /** 结案应还金额 */
    private String closeCaseYhje;
    /** 当前已还金额 */
    private String dqyhje;

    private Integer statusCondition;//状态条件

    private String startTime;
    private String endTime;



}
