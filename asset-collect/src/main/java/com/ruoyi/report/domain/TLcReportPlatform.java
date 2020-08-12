package com.ruoyi.report.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 【请填写功能名称】对象 t_lc_report_platform
 *
 * @author liurunkai
 * @date 2020-08-04
 */
@Data
public class TLcReportPlatform extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 报表日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
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
    private Integer paCalledNum;

    /**
     * 平安通话时长
     */
    @Excel(name = "平安通话时长(分)")
    private String paCallLen;

    /**
     * 平安拨打次数
     */
    @Excel(name = "平安拨打次数")
    private Integer paCallNum;

    /**
     * 自建通话次数
     */
    @Excel(name = "自建通话次数")
    private Integer zjCalledNum;

    /**
     * 自建通话时长
     */
    @Excel(name = "自建通话时长(分)")
    private String zjCallLen;

    /**
     * 自建拨打次数
     */
    @Excel(name = "自建拨打次数")
    private Integer zjCallNum;

    /**
     * 全部通话次数
     */
    @Excel(name = "全部通话次数")
    private Integer totalCalledNum;

    /**
     * 全部通话时长
     */
    @Excel(name = "全部通话时长(分)")
    private String totalCallLen;

    /**
     * 全部拨打次数
     */
    @Excel(name = "全部拨打次数")
    private Integer totalCallNum;
}
