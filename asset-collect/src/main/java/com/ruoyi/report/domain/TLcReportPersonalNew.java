package com.ruoyi.report.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 通时通次个人明细汇总报对象 t_lc_report_personal_new
 *
 * @author liurunkai
 * @date 2020-09-02
 */
public class TLcReportPersonalNew extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 报表日期
     */
    @Excel(name = "报表日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reportData;

    /**
     * 时间段
     */
    @Excel(name = "时间段")
    private String timePeriod;

    /**
     * 平安通话次数
     */
    @Excel(name = "平安通话次数")
    private String platform;

    /**
     * 平安通话时长
     */
    @Excel(name = "平安通话时长")
    private Double callLen;

    /**
     * 平安拨打次数
     */
    @Excel(name = "平安拨打次数")
    private Integer callNum;

    /**
     * 自建通话次数
     */
    @Excel(name = "自建通话次数")
    private Integer calledNum;

    /**
     * 自建通话时长
     */
    @Excel(name = "自建通话时长")
    private String userId;
}
