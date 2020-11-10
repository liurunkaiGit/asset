package com.ruoyi.task.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TlcCallRecordTotal {

    /**
     * 通话次数
     */
    private Long frequencys;
    /**
     * 已接通次数
     */
    private Long connections;
    /**
     * 总通话时长
     */
    private Double durations;
}
