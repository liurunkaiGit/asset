package com.ruoyi.shareproject.process.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 【请填写功能名称】对象 t_lp_process
 *
 * @author liurunkai
 * @date 2020-10-16
 */
@Data
public class TLpProcess extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 报表日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报表日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reportDate;

    /**
     * 机构id
     */
    @Excel(name = "机构id")
    private Long orgId;

    /**
     * 机构名称
     */
    @Excel(name = "机构名称")
    private String orgName;

    private String projectIdName;

    /**
     * 拨打量(次)
     */
    @Excel(name = "拨打量(次)")
    private Integer totalCallNum;

    /**
     * 人均拨打量
     */
    @Excel(name = "人均拨打量")
    private BigDecimal aveCallNum;

    /**
     * 接通量(次)
     */
    @Excel(name = "接通量(次)")
    private Integer totalCalledNum;

    /**
     * 人均接通次数
     */
    @Excel(name = "人均接通次数")
    private BigDecimal avgCalledNum;

    /**
     * 通话时长
     */
    @Excel(name = "通话时长")
    private BigDecimal totalCallLen;

    /**
     * 人均通话时长
     */
    @Excel(name = "人均通话时长")
    private BigDecimal avgCallLen;

    /**
     * 接通率
     */
    @Excel(name = "接通率")
    private String totalCalledRate;

    /**
     * 人均接通率
     */
    @Excel(name = "人均接通率")
    private String avgCalledRate;

    /**
     * 外显号码标记情况
     */
    @Excel(name = "外显号码标记情况")
    private String extPhoneSign;

    private Date startReportDate;
    private Date endReportDate;

    private String updateName;

}
