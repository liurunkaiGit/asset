package com.ruoyi.inforeporting.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 上报信息-减免 中银
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Data
public class TLcInforeportingReductionZhongyin {
    private static final long serialVersionUID = 164662625561616L;
    /**
     * 序号
     */
    @Excel(name = "序号")
    private int id;
    /**
     * 申请日期
     */
    @Excel(name = "申请日期",dateFormat = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 公司
     */
    @Excel(name = "公司")
    private String company;
    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    protected String customName;
    /**
     * 产品
     */
    @Excel(name = "产品")
    protected String productName;
    /**
     * 逾期天数
     */
    @Excel(name = "逾期天数（M+）")
    private String overdueDays;
    /**
     * 呆账核销日期
     */
    @Excel(name = "呆账核销日期")
    private String dzhxrq;
    /**
     * 合同号
     */
    @Excel(name = "消费金融账号")
    protected String caseNo;
    /**
     * 备注
     */
    @Excel(name = "情况说明")
    private String remarks;
    /**
     * 预计还款金额
     */
    @Excel(name = "预计还款金额")
    private Double deductionAmount;


    /**
     * 银行批复
     */
    @Excel(name = "银行批复")
    private String yhpf;
    /**
     * 批复人
     */
    @Excel(name = "批复人")
    private String yhpfr;
    /**
     * 批复时间
     */
    @Excel(name = "批复时间")
    private String yhpfrTm;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String bz;
}
