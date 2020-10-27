package com.ruoyi.report.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 通时通次个人明细汇总报对象 t_lc_report_personal
 *
 * @author liurunkai
 * @date 2020-08-05
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TLcReportPersonal extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 报表日期
     */
    @Excel(name = "报表日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date reportData;

    /**
     * 组别
     */
    @Excel(name = "组别")
    private String userGroup;

    /**
     * 坐席编码
     */
    @Excel(name = "坐席编码")
    private String loginName;

    /**
     * 坐席姓名
     */
    @Excel(name = "坐席姓名")
    private String userName;

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
     * 度言通话次数
     */
    @Excel(name = "度言通话次数")
    private Integer dyCalledNum;

    /**
     * 度言通话时长
     */
    @Excel(name = "度言通话时长(分)")
    private String dyCallLen;

    /**
     * 度言拨打次数
     */
    @Excel(name = "度言拨打次数")
    private Integer dyCallNum;

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
