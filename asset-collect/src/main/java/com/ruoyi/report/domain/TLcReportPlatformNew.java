package com.ruoyi.report.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 通时通次平台汇总报对象 t_lc_report_platform_new
 *
 * @author liurunkai
 * @date 2020-09-02
 */
@Data
public class TLcReportPlatformNew extends BaseEntity {
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
     * 通话次数
     */
    @Excel(name = "通话次数")
    private Integer calledNum;

    /**
     * 通话时长
     */
    @Excel(name = "通话时长")
    private Double callLen;

    /**
     * 拨打次数
     */
    @Excel(name = "拨打次数")
    private Integer callNum;

    /**
     * 平台
     */
    @Excel(name = "平台")
    private String platform;

}
