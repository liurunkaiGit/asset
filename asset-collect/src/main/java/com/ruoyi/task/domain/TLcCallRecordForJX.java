package com.ruoyi.task.domain;

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
 * 兴业通话结果记录对象 t_lc_call_record
 *
 * @author guozeqi
 * @date 2019-07-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class TLcCallRecordForJX extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Excel(name = "周开始日期",dateFormat = "yyyy-MM-dd")
    private Date weekStartDate;
    @Excel(name = "周结束日期",dateFormat = "yyyy-MM-dd")
    private Date weekEndDate;
    @Excel(name = "公司名称")
    private String companyName = "huadao";
    @Excel(name = "机构案件号")
    private String caseNo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入催日",dateFormat = "yyyy-MM-dd")
    private Date enterCollDate;
    @Excel(name = "委案金额")
    private BigDecimal arrearsTotal;
    @Excel(name = "结案应还金额")
    private BigDecimal closeCaseYhje;
    @Excel(name = "逾期天数")
    private Integer overdueDays;
    @Excel(name = "拨打时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date callTime;
    @Excel(name = "催收方式")
    private String collType = "电话催收";
    @Excel(name = "电话号码")
    private String phone;
    @Excel(name = "是否承诺还款")
    private String isPromisePay;
    @Excel(name = "是否安排外访")
    private String isOutColl = "N";
    @Excel(name = "催收结果")
    private String collResult;
    @Excel(name = "坐席")
    private String agent;
    @Excel(name = "更新的电话号码")
    private String updatePhone;
    @Excel(name = "更新的地址")
    private String updateAddress;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "电话码")
    private String callCode;

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
