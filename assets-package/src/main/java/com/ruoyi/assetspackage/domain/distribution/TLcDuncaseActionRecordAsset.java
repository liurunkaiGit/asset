package com.ruoyi.assetspackage.domain.distribution;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 案件行动码记录对象 t_lc_duncase_action_record
 *
 * @author liurunkai
 * @date 2020-01-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class TLcDuncaseActionRecordAsset extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主鍵ID
     */
    private Long id;

    /**
     * 案件号
     */
    @Excel(name = "案件号")
    private String caseNo;

    /**
     * 任务编号
     */
    @Excel(name = "任务编号")
    private String taskId;

    /**
     * 还款日期
     */
    @Excel(name = "还款日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date repayDate;

    /**
     * 还款金额
     */
    @Excel(name = "还款金额")
    private BigDecimal repayAmount;

    /**
     * 复核日期
     */
    @Excel(name = "复核日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recheckDate;

    /**
     * 再次联系时间
     */
    @Excel(name = "再次联系时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recontactDate;

    /**
     * 行动码
     */
    @Excel(name = "行动码")
    private String actionCode;

    /**
     * 是否有效 1：是，2：否
     */
    @Excel(name = "是否有效 1：是，2：否")
    private Integer validateStatus;

    /**
     * 修改时间
     */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /**
     * 修改人
     */
    @Excel(name = "修改人")
    private Long modifyBy;

    /**
     * 坐席
     */
    @Excel(name = "坐席")
    private String agentName;

    /**
     * 行动码中文
     */
    private String dictLabel;

}
