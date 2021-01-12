package com.ruoyi.report.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 每日过程指标报对象 t_lc_report_day_process
 *
 * @author liurunkai
 * @date 2020-04-09
 */
@Data
@Accessors(chain = true)
public class TLcReportMonthProcess extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 报表日期
     */
    @NotNull(message = "日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date reportDate;

    /**
     * 用户组别
     */
    @Excel(name = "用户组别")
    private String seatGroup;

    /**
     * 登录名称
     */
    @Excel(name = "登录名称")
    private String seatNum;

    /**
     * 坐席名称
     */
    @Excel(name = "坐席名称")
    private String seatName;

    /**
     * 处理客户数
     */
    private Integer dealWithConsumerCount;

    /**
     * 在催案件金额
     */
    private BigDecimal collingCaseMoney;

    /**
     * 覆盖账户数
     */
    private Integer userCoverNum;

    /**
     * 拨打次数
     */
    @Excel(name = "拨打次数")
    private Integer callNum;

    /**
     * 接通次数
     */
    @Excel(name = "接通次数")
    private Integer connectedCallNum;

    /**
     * 通话时长
     */
    @Excel(name = "通话时长")
    private String callLen;

    /**
     * 电话接通率
     */
    @Excel(name = "电话接通率")
    private String callConnectedRecovery;

    /**
     * 行动码数量
     */
    private Integer actionCodeNum;

    /**
     * 电话码量
     */
    private Integer callCodeNum;

    /**
     * 电话码于行动码比值
     */
    private String callActionCodeRecovery;

    /**
     * 案件平均电话码量
     */
    private String averageCallCode;

    /**
     * 案件平均行动码量
     */
    private String averageActionCode;

    /**
     * 本人有效电话码量
     */
    private Integer selfEffectiveCallCodeNum;

    /**
     * 三方有效电话码量
     */
    private Integer thirdEffectiveCallCodeNum;

    /**
     * 案均有效电话码量
     */
    private String averageEffectiveCallCodeNum;

    /**
     * 投诉代码量
     */
    private Integer complaintNum;

    /**
     * 所属机构
     */
    private String orgId;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 应还金额总
     */
    private BigDecimal amountDueSum;
    /**
     * 实际还金额总
     */
    private BigDecimal amountActualSum;

    // 是否组内查询
    private Integer isGroup;
    // 组内对应的用户
    private List<Long> userIds;

    private Date startReportDate;
    private Date endReportDate;

}
