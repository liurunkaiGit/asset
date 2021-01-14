package com.ruoyi.report.domain;

import com.alibaba.druid.sql.visitor.functions.If;
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
 * 回收率报对象 t_lc_report_recovery
 *
 * @author liurunkai
 * @date 2020-04-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class TLcReportZyRecovery extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 委案公司
     */
    @Excel(name = "委案公司")
    private String company = "华道";

    /**
     * 委案日期
     */
    @Excel(name = "委案日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enterCollDate;

    /**
     * 逾期阶段
     */
    @Excel(name = "逾期阶段")
    private String transferType;

    /**
     * 月初逾期现贷余额
     */
    @Excel(name = "月初逾期现贷余额")
    private BigDecimal mEaOdClBa;

    /**
     * 月底逾期现贷余额
     */
    @Excel(name = "月底逾期现贷余额")
    private BigDecimal mEnOdClBa;

    public BigDecimal getmEnOdClBa() {
        if (this.mEaOdClBa == null) {
            return null;
        }
        return this.mEaOdClBa.subtract(getReturnedMoney());
    }

    /**
     * 月初核销未收回本金
     */
    @Excel(name = "月初核销未收回本金")
    private BigDecimal mEaWoNrPr;

    public BigDecimal getmEaWoNrPr() {
        if (this.mEaWoNrPr == null) {
            return new BigDecimal("0.00");
        }
        return mEaWoNrPr;
    }

    /**
     * 月底核销未收回本金
     */
    @Excel(name = "月底核销未收回本金")
    private BigDecimal mEnWoNrPr;

    public BigDecimal getmEnWoNrPr() {
        return getmEaWoNrPr().subtract(getReturnedMoney());
    }

    /**
     * 月初
     */
    @Excel(name = "月初")
    private BigDecimal mEa;

    public BigDecimal getmEa() {
        if (getMEaOdClBa() == null && getmEaWoNrPr() == null) {
            return null;
        } else if (getMEaOdClBa() == null) {
            return getmEaWoNrPr();
        } else if (getmEaWoNrPr() == null) {
            return getMEaOdClBa();
        }
        return getMEaOdClBa().add(getmEaWoNrPr());
    }

    /**
     * 月末
     */
    @Excel(name = "月末")
    private BigDecimal mEn;

    public BigDecimal getmEn() {
        if (getmEnOdClBa() == null && getmEnWoNrPr() == null) {
            return null;
        } else if (getmEnOdClBa() == null) {
            return getmEnWoNrPr();
        } else if (getmEnWoNrPr() == null) {
            return getmEnOdClBa();
        }
        return getmEnOdClBa().add(getmEnWoNrPr());
    }

    /**
     * 回收率=[1-（月末/月初）]*100%
     */
    @Excel(name = "回收率")
    private String recovery;

    public String getRecovery() {
        BigDecimal divide = new BigDecimal("0.00");
        if (!new BigDecimal("0.00").equals(getmEa())) {
            divide = getmEn().divide(getmEa(), 2, BigDecimal.ROUND_HALF_UP);
        }
        BigDecimal subtract = new BigDecimal(1).subtract(divide);
        BigDecimal multiply = subtract.multiply(new BigDecimal(100));
        return multiply.toString() + "%";
    }

    /**
     * 已还金额
     */
    private BigDecimal returnedMoney;

    public BigDecimal getReturnedMoney() {
        if (this.returnedMoney == null) {
            return new BigDecimal("0.00");
        }
        return returnedMoney;
    }

    private Long orgId;

    private Date startEnterCollDate;
    private Date endEnterCollDate;

}
