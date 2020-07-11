package com.ruoyi.batchcall.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 批量外呼对象 t_lc_batch_call_config
 * 
 * @author fengzhitao
 * @date 2020-06-28
 */
@Data
public class TLcBatchCallConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 机构ID */
    private String orgId;

    /** 批量外呼最大案件数 */
    private Long batchCallNum;

    /** 可呼叫开始时间1 */
    private String startTime1;

    /** 可呼叫结束时间1 */
    private String endTime1;

    /** 可呼叫开始时间2 */
    private String startTime2;

    /** 可呼叫结束时间2 */
    private String endTime2;

    /** 可呼叫开始时间3 */
    private String startTime3;

    /** 可呼叫结束时间3 */
    private String endTime3;

    /** 本人接通后是否继续拨打本案其他号码：0 不呼叫，1 呼叫 */
    private String isCallOther;

    /**
     * 机构名称
     */
    private String orgName;
    /**
     * 可呼叫时间段
     */
    private String canCallTimes;

}
