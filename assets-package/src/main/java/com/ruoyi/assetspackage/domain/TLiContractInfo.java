package com.ruoyi.assetspackage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 合同导入临时对象 t_li_contract_info
 * 
 * @author liurunkai
 * @date 2020-05-29
 */
public class TLiContractInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键")
    private String tuid;

    /** 导入批次 */
    @Excel(name = "导入批次")
    private String importBatch;

    /** 队列 */
    @Excel(name = "队列")
    private String queue;

    /** 状态 0 处理中, 1 已关闭 出催 */
    @Excel(name = "状态 0 处理中, 1 已关闭 出催")
    private Integer status;

    /** 是否停催 0 未停催 1 停催 */
    @Excel(name = "是否停催 0 未停催 1 停催")
    private Integer isStop;

    /** 停催时间 */
    @Excel(name = "停催时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date stopTime;

    /** 出催时间 */
    @Excel(name = "出催时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date outTime;

    /** -- 出催类型 0 满意出催 1 出催未还款 */
    @Excel(name = "-- 出催类型 0 满意出催 1 出催未还款")
    private Integer outType;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date created;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdby;

    /** 更新时间 */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updated;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedby;

    /** 案件tuid */
    @Excel(name = "案件tuid")
    private String caseTuid;

    /** 案件号 */
    @Excel(name = "案件号")
    private String caseNo;

    /** 单据号(卡号_期数) */
    @Excel(name = "单据号(卡号_期数)")
    private String debtNo;

    /** 姓名 */
    @Excel(name = "姓名")
    private String custName;

    /** 初始入催金额 */
    @Excel(name = "初始入催金额")
    private Double debtInit;

    /** 总欠款金额 */
    @Excel(name = "总欠款金额")
    private String debt;

    /** 最低还款额 */
    @Excel(name = "最低还款额")
    private Double minRepay;

    /** 账单日 */
    @Excel(name = "账单日")
    private String billDate;

    /** 保险 */
    @Excel(name = "保险")
    private String insurance;

    /** 合同号 */
    @Excel(name = "合同号")
    private String contractId;

    /** 机构 */
    @Excel(name = "机构")
    private String org;

    /** 考核地区 */
    @Excel(name = "考核地区")
    private String checkArea;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private Double productPrice;

    /** 最后一次还款的时间 */
    @Excel(name = "最后一次还款的时间")
    private String lastPaymentDate;

    /** 下个还款日 */
    @Excel(name = "下个还款日")
    private String nextPaymentDate;

    /** 贷款金额 */
    @Excel(name = "贷款金额")
    private Double loans;

    /** 逾期阶段 */
    @Excel(name = "逾期阶段")
    private String overduePhase;

    /** 期款 */
    @Excel(name = "期款")
    private Double termPayment;

    /** 期数 */
    @Excel(name = "期数")
    private Integer termNum;

    /** 贷款类型 */
    @Excel(name = "贷款类型")
    private String loansType;

    /** 预付款 */
    @Excel(name = "预付款")
    private Double advancePayment;

    /** 总合同数 */
    @Excel(name = "总合同数")
    private Integer contractNum;

    /** 期款未付 */
    @Excel(name = "期款未付")
    private Double unpaidTermPayment;

    /** 滞纳金 */
    @Excel(name = "滞纳金")
    private Double overdueFine;

    /** 捷乐贷客户实收金额 */
    @Excel(name = "捷乐贷客户实收金额")
    private Double jlAmount;

    /** JL_总欠款 */
    @Excel(name = "JL_总欠款")
    private Double jlDebt;

    /** JL_FLAG */
    @Excel(name = "JL_FLAG")
    private String jlFlag;

    /** 累计已还款 */
    @Excel(name = "累计已还款")
    private Double repayment;

    /** 商户名称 */
    @Excel(name = "商户名称")
    private String merchant;

    /** 销售地 */
    @Excel(name = "销售地")
    private String saleArea;

    /** 厂商 */
    @Excel(name = "厂商")
    private String firm;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brand;

    /** 产品 */
    @Excel(name = "产品")
    private String product;

    /** 合同类别 */
    @Excel(name = "合同类别")
    private String contractType;

    /** 销售备注 */
    @Excel(name = "销售备注")
    private String saleMemo;

    /** 账户 */
    @Excel(name = "账户")
    private String accountId;

    /** 首付金额 */
    @Excel(name = "首付金额")
    private Double downPayment;

    /** 客户代扣的银行卡号 */
    @Excel(name = "客户代扣的银行卡号")
    private String cardNo;

    /** 线上销售 */
    @Excel(name = "线上销售")
    private String saleOnline;

    /** 属性 */
    @Excel(name = "属性")
    private String property;

    /** 户名 */
    @Excel(name = "户名")
    private String accountName;

    /** 开户银行 */
    @Excel(name = "开户银行")
    private String depositBank;

    /** 邮政储蓄银行还款账号 */
    @Excel(name = "邮政储蓄银行还款账号")
    private String yzAccount;

    /** 已还期数 */
    @Excel(name = "已还期数")
    private String paidTerm;

    /** 首次还款月 */
    @Excel(name = "首次还款月")
    private String firstRepayMonth;

    /** 是否为罚息合同 */
    @Excel(name = "是否为罚息合同")
    private String penaltyContract;

    /** 是否为自动延长委案合同 */
    @Excel(name = "是否为自动延长委案合同")
    private String extendContract;

    /** 之前是否停催 */
    @Excel(name = "之前是否停催")
    private String outBefore;

    /** 最近一次停催日期 */
    @Excel(name = "最近一次停催日期")
    private String lastOutDate;

    /** 总计停催次数 */
    @Excel(name = "总计停催次数")
    private String outNum;

    /** 新系统最近一次停催日 */
    @Excel(name = "新系统最近一次停催日")
    private String newOutDate;

    /** 新系统总计停催次数 */
    @Excel(name = "新系统总计停催次数")
    private String newOutNum;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private String productType;

    /** 逾期天数 */
    @Excel(name = "逾期天数")
    private Integer overdueNum;

    /** 退案日期 */
    @Excel(name = "退案日期")
    private String outcaseDate;

    /** 申请日 */
    @Excel(name = "申请日")
    private String applyDate;

    /** 委派日期 */
    @Excel(name = "委派日期")
    private String assignDate;

    /** 卡号 */
    @Excel(name = "卡号")
    private String cardNum;

    /** 补充QQ */
    @Excel(name = "补充QQ")
    private String qqMail;

    /** 相关QQ */
    @Excel(name = "相关QQ")
    private String qqMail2;

    public void setTuid(String tuid) 
    {
        this.tuid = tuid;
    }

    public String getTuid() 
    {
        return tuid;
    }
    public void setImportBatch(String importBatch) 
    {
        this.importBatch = importBatch;
    }

    public String getImportBatch() 
    {
        return importBatch;
    }
    public void setQueue(String queue) 
    {
        this.queue = queue;
    }

    public String getQueue() 
    {
        return queue;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setIsStop(Integer isStop) 
    {
        this.isStop = isStop;
    }

    public Integer getIsStop() 
    {
        return isStop;
    }
    public void setStopTime(Date stopTime) 
    {
        this.stopTime = stopTime;
    }

    public Date getStopTime() 
    {
        return stopTime;
    }
    public void setOutTime(Date outTime) 
    {
        this.outTime = outTime;
    }

    public Date getOutTime() 
    {
        return outTime;
    }
    public void setOutType(Integer outType) 
    {
        this.outType = outType;
    }

    public Integer getOutType() 
    {
        return outType;
    }
    public void setCreated(Date created) 
    {
        this.created = created;
    }

    public Date getCreated() 
    {
        return created;
    }
    public void setCreatedby(String createdby) 
    {
        this.createdby = createdby;
    }

    public String getCreatedby() 
    {
        return createdby;
    }
    public void setUpdated(Date updated) 
    {
        this.updated = updated;
    }

    public Date getUpdated() 
    {
        return updated;
    }
    public void setUpdatedby(String updatedby) 
    {
        this.updatedby = updatedby;
    }

    public String getUpdatedby() 
    {
        return updatedby;
    }
    public void setCaseTuid(String caseTuid) 
    {
        this.caseTuid = caseTuid;
    }

    public String getCaseTuid() 
    {
        return caseTuid;
    }
    public void setCaseNo(String caseNo) 
    {
        this.caseNo = caseNo;
    }

    public String getCaseNo() 
    {
        return caseNo;
    }
    public void setDebtNo(String debtNo) 
    {
        this.debtNo = debtNo;
    }

    public String getDebtNo() 
    {
        return debtNo;
    }
    public void setCustName(String custName) 
    {
        this.custName = custName;
    }

    public String getCustName() 
    {
        return custName;
    }
    public void setDebtInit(Double debtInit) 
    {
        this.debtInit = debtInit;
    }

    public Double getDebtInit() 
    {
        return debtInit;
    }
    public void setDebt(String debt)
    {
        this.debt = debt;
    }

    public String getDebt()
    {
        return debt;
    }
    public void setMinRepay(Double minRepay) 
    {
        this.minRepay = minRepay;
    }

    public Double getMinRepay() 
    {
        return minRepay;
    }
    public void setBillDate(String billDate) 
    {
        this.billDate = billDate;
    }

    public String getBillDate() 
    {
        return billDate;
    }
    public void setInsurance(String insurance) 
    {
        this.insurance = insurance;
    }

    public String getInsurance() 
    {
        return insurance;
    }
    public void setContractId(String contractId) 
    {
        this.contractId = contractId;
    }

    public String getContractId() 
    {
        return contractId;
    }
    public void setOrg(String org) 
    {
        this.org = org;
    }

    public String getOrg() 
    {
        return org;
    }
    public void setCheckArea(String checkArea) 
    {
        this.checkArea = checkArea;
    }

    public String getCheckArea() 
    {
        return checkArea;
    }
    public void setProductPrice(Double productPrice) 
    {
        this.productPrice = productPrice;
    }

    public Double getProductPrice() 
    {
        return productPrice;
    }
    public void setLastPaymentDate(String lastPaymentDate) 
    {
        this.lastPaymentDate = lastPaymentDate;
    }

    public String getLastPaymentDate() 
    {
        return lastPaymentDate;
    }
    public void setNextPaymentDate(String nextPaymentDate) 
    {
        this.nextPaymentDate = nextPaymentDate;
    }

    public String getNextPaymentDate() 
    {
        return nextPaymentDate;
    }
    public void setLoans(Double loans) 
    {
        this.loans = loans;
    }

    public Double getLoans() 
    {
        return loans;
    }
    public void setOverduePhase(String overduePhase) 
    {
        this.overduePhase = overduePhase;
    }

    public String getOverduePhase() 
    {
        return overduePhase;
    }
    public void setTermPayment(Double termPayment) 
    {
        this.termPayment = termPayment;
    }

    public Double getTermPayment() 
    {
        return termPayment;
    }
    public void setTermNum(Integer termNum) 
    {
        this.termNum = termNum;
    }

    public Integer getTermNum() 
    {
        return termNum;
    }
    public void setLoansType(String loansType) 
    {
        this.loansType = loansType;
    }

    public String getLoansType() 
    {
        return loansType;
    }
    public void setAdvancePayment(Double advancePayment) 
    {
        this.advancePayment = advancePayment;
    }

    public Double getAdvancePayment() 
    {
        return advancePayment;
    }
    public void setContractNum(Integer contractNum) 
    {
        this.contractNum = contractNum;
    }

    public Integer getContractNum() 
    {
        return contractNum;
    }
    public void setUnpaidTermPayment(Double unpaidTermPayment) 
    {
        this.unpaidTermPayment = unpaidTermPayment;
    }

    public Double getUnpaidTermPayment() 
    {
        return unpaidTermPayment;
    }
    public void setOverdueFine(Double overdueFine) 
    {
        this.overdueFine = overdueFine;
    }

    public Double getOverdueFine() 
    {
        return overdueFine;
    }
    public void setJlAmount(Double jlAmount) 
    {
        this.jlAmount = jlAmount;
    }

    public Double getJlAmount() 
    {
        return jlAmount;
    }
    public void setJlDebt(Double jlDebt) 
    {
        this.jlDebt = jlDebt;
    }

    public Double getJlDebt() 
    {
        return jlDebt;
    }
    public void setJlFlag(String jlFlag) 
    {
        this.jlFlag = jlFlag;
    }

    public String getJlFlag() 
    {
        return jlFlag;
    }
    public void setRepayment(Double repayment) 
    {
        this.repayment = repayment;
    }

    public Double getRepayment() 
    {
        return repayment;
    }
    public void setMerchant(String merchant) 
    {
        this.merchant = merchant;
    }

    public String getMerchant() 
    {
        return merchant;
    }
    public void setSaleArea(String saleArea) 
    {
        this.saleArea = saleArea;
    }

    public String getSaleArea() 
    {
        return saleArea;
    }
    public void setFirm(String firm) 
    {
        this.firm = firm;
    }

    public String getFirm() 
    {
        return firm;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setProduct(String product) 
    {
        this.product = product;
    }

    public String getProduct() 
    {
        return product;
    }
    public void setContractType(String contractType) 
    {
        this.contractType = contractType;
    }

    public String getContractType() 
    {
        return contractType;
    }
    public void setSaleMemo(String saleMemo) 
    {
        this.saleMemo = saleMemo;
    }

    public String getSaleMemo() 
    {
        return saleMemo;
    }
    public void setAccountId(String accountId) 
    {
        this.accountId = accountId;
    }

    public String getAccountId() 
    {
        return accountId;
    }
    public void setDownPayment(Double downPayment) 
    {
        this.downPayment = downPayment;
    }

    public Double getDownPayment() 
    {
        return downPayment;
    }
    public void setCardNo(String cardNo) 
    {
        this.cardNo = cardNo;
    }

    public String getCardNo() 
    {
        return cardNo;
    }
    public void setSaleOnline(String saleOnline) 
    {
        this.saleOnline = saleOnline;
    }

    public String getSaleOnline() 
    {
        return saleOnline;
    }
    public void setProperty(String property) 
    {
        this.property = property;
    }

    public String getProperty() 
    {
        return property;
    }
    public void setAccountName(String accountName) 
    {
        this.accountName = accountName;
    }

    public String getAccountName() 
    {
        return accountName;
    }
    public void setDepositBank(String depositBank) 
    {
        this.depositBank = depositBank;
    }

    public String getDepositBank() 
    {
        return depositBank;
    }
    public void setYzAccount(String yzAccount) 
    {
        this.yzAccount = yzAccount;
    }

    public String getYzAccount() 
    {
        return yzAccount;
    }
    public void setPaidTerm(String paidTerm) 
    {
        this.paidTerm = paidTerm;
    }

    public String getPaidTerm() 
    {
        return paidTerm;
    }
    public void setFirstRepayMonth(String firstRepayMonth) 
    {
        this.firstRepayMonth = firstRepayMonth;
    }

    public String getFirstRepayMonth() 
    {
        return firstRepayMonth;
    }
    public void setPenaltyContract(String penaltyContract) 
    {
        this.penaltyContract = penaltyContract;
    }

    public String getPenaltyContract() 
    {
        return penaltyContract;
    }
    public void setExtendContract(String extendContract) 
    {
        this.extendContract = extendContract;
    }

    public String getExtendContract() 
    {
        return extendContract;
    }
    public void setOutBefore(String outBefore) 
    {
        this.outBefore = outBefore;
    }

    public String getOutBefore() 
    {
        return outBefore;
    }
    public void setLastOutDate(String lastOutDate) 
    {
        this.lastOutDate = lastOutDate;
    }

    public String getLastOutDate() 
    {
        return lastOutDate;
    }
    public void setOutNum(String outNum) 
    {
        this.outNum = outNum;
    }

    public String getOutNum() 
    {
        return outNum;
    }
    public void setNewOutDate(String newOutDate) 
    {
        this.newOutDate = newOutDate;
    }

    public String getNewOutDate() 
    {
        return newOutDate;
    }
    public void setNewOutNum(String newOutNum) 
    {
        this.newOutNum = newOutNum;
    }

    public String getNewOutNum() 
    {
        return newOutNum;
    }
    public void setProductType(String productType) 
    {
        this.productType = productType;
    }

    public String getProductType() 
    {
        return productType;
    }
    public void setOverdueNum(Integer overdueNum) 
    {
        this.overdueNum = overdueNum;
    }

    public Integer getOverdueNum() 
    {
        return overdueNum;
    }
    public void setOutcaseDate(String outcaseDate) 
    {
        this.outcaseDate = outcaseDate;
    }

    public String getOutcaseDate() 
    {
        return outcaseDate;
    }
    public void setApplyDate(String applyDate) 
    {
        this.applyDate = applyDate;
    }

    public String getApplyDate() 
    {
        return applyDate;
    }
    public void setAssignDate(String assignDate) 
    {
        this.assignDate = assignDate;
    }

    public String getAssignDate() 
    {
        return assignDate;
    }
    public void setCardNum(String cardNum) 
    {
        this.cardNum = cardNum;
    }

    public String getCardNum() 
    {
        return cardNum;
    }
    public void setQqMail(String qqMail) 
    {
        this.qqMail = qqMail;
    }

    public String getQqMail() 
    {
        return qqMail;
    }
    public void setQqMail2(String qqMail2) 
    {
        this.qqMail2 = qqMail2;
    }

    public String getQqMail2() 
    {
        return qqMail2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tuid", getTuid())
            .append("importBatch", getImportBatch())
            .append("queue", getQueue())
            .append("status", getStatus())
            .append("isStop", getIsStop())
            .append("stopTime", getStopTime())
            .append("outTime", getOutTime())
            .append("outType", getOutType())
            .append("created", getCreated())
            .append("createdby", getCreatedby())
            .append("updated", getUpdated())
            .append("updatedby", getUpdatedby())
            .append("caseTuid", getCaseTuid())
            .append("caseNo", getCaseNo())
            .append("debtNo", getDebtNo())
            .append("custName", getCustName())
            .append("debtInit", getDebtInit())
            .append("debt", getDebt())
            .append("minRepay", getMinRepay())
            .append("billDate", getBillDate())
            .append("insurance", getInsurance())
            .append("contractId", getContractId())
            .append("org", getOrg())
            .append("checkArea", getCheckArea())
            .append("productPrice", getProductPrice())
            .append("lastPaymentDate", getLastPaymentDate())
            .append("nextPaymentDate", getNextPaymentDate())
            .append("loans", getLoans())
            .append("overduePhase", getOverduePhase())
            .append("termPayment", getTermPayment())
            .append("termNum", getTermNum())
            .append("loansType", getLoansType())
            .append("advancePayment", getAdvancePayment())
            .append("contractNum", getContractNum())
            .append("unpaidTermPayment", getUnpaidTermPayment())
            .append("overdueFine", getOverdueFine())
            .append("jlAmount", getJlAmount())
            .append("jlDebt", getJlDebt())
            .append("jlFlag", getJlFlag())
            .append("repayment", getRepayment())
            .append("merchant", getMerchant())
                .append("firm", getFirm())
            .append("saleArea", getSaleArea())
            .append("brand", getBrand())
            .append("product", getProduct())
            .append("contractType", getContractType())
            .append("saleMemo", getSaleMemo())
            .append("accountId", getAccountId())
            .append("downPayment", getDownPayment())
            .append("cardNo", getCardNo())
            .append("saleOnline", getSaleOnline())
            .append("property", getProperty())
            .append("accountName", getAccountName())
            .append("depositBank", getDepositBank())
            .append("yzAccount", getYzAccount())
            .append("paidTerm", getPaidTerm())
            .append("firstRepayMonth", getFirstRepayMonth())
            .append("penaltyContract", getPenaltyContract())
            .append("extendContract", getExtendContract())
            .append("outBefore", getOutBefore())
            .append("lastOutDate", getLastOutDate())
            .append("outNum", getOutNum())
            .append("newOutDate", getNewOutDate())
            .append("newOutNum", getNewOutNum())
            .append("productType", getProductType())
            .append("overdueNum", getOverdueNum())
            .append("outcaseDate", getOutcaseDate())
            .append("applyDate", getApplyDate())
            .append("assignDate", getAssignDate())
            .append("cardNum", getCardNum())
            .append("qqMail", getQqMail())
            .append("qqMail2", getQqMail2())
            .toString();
    }
}
