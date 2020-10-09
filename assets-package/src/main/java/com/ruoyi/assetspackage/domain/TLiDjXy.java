package com.ruoyi.assetspackage.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 兴业单据对象 t_li_dj_xy
 * 
 * @author liurunkai
 * @date 2020-09-09
 */
@Data
public class TLiDjXy
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String tuid;

    /** 导入批次 */
    @Excel(name = "导入批次")
    private String importBatch;

    /** 队列 */
    @Excel(name = "队列")
    private String queue;

    /** 状态 0 处理中, 1 已关闭 出催 */
    @Excel(name = "状态 0 处理中, 1 已关闭 出催")
    private String status;

    /** 是否停催 0 未停催 1 停催 */
    @Excel(name = "是否停催 0 未停催 1 停催")
    private String isStop;

    /** 停催时间 */
    @Excel(name = "停催时间")
    private String stopTime;

    /** 出催时间 */
    @Excel(name = "出催时间")
    private String outTime;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String created;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdby;

    /** 更新时间 */
    @Excel(name = "更新时间")
    private String updated;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedby;

    /** 案件tuid */
    @Excel(name = "案件tuid")
    private String caseTuid;

    /** 案件号 */
    @Excel(name = "案件号")
    private String caseNo;

    /** 委托时点本息费合计加停计息费 */
    @Excel(name = "委托时点本息费合计加停计息费")
    private String debt;

    /** 贷款金额 */
    @Excel(name = "贷款金额")
    private String loan;

    /** 导入源数据主键 */
    @Excel(name = "导入源数据主键")
    private String importTuid;

    /** 最大逾期天数 */
    @Excel(name = "最大逾期天数")
    private String overdueDays;

    /** -- 出催类型 0 满意出催 1 出催未还款 */
    @Excel(name = "-- 出催类型 0 满意出催 1 出催未还款")
    private String outType;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 卡号 */
    @Excel(name = "卡号")
    private String cardNumber;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 手别 */
    @Excel(name = "手别")
    private String hands;

    /** 委案金额（美金） */
    @Excel(name = "委案金额", readConverterExp = "美=金")
    private String assignBalUsa;

    /** 账单日 */
    @Excel(name = "账单日")
    private String cycle;

    /** 最低还款额剩余未还金额 */
    @Excel(name = "最低还款额剩余未还金额")
    private String minPay;

    /** 最后一次还款 */
    @Excel(name = "最后一次还款")
    private String lastPayment;

    /** 电邮 */
    @Excel(name = "电邮")
    private String email;

    /** TRUNS */
    @Excel(name = "TRUNS")
    private String truns;

    /** 记录 */
    @Excel(name = "记录")
    private String notes;

    /** 委案时逾期天数 */
    @Excel(name = "委案时逾期天数")
    private String assignDpd;

    /** 本金 */
    @Excel(name = "本金")
    private String amtPrinciple;

    /** 分期及万用金本金 */
    @Excel(name = "分期及万用金本金")
    private String amtEppbal;

    /** 额度 */
    @Excel(name = "额度")
    private String amtCrelmt;

    /** lendingline */
    @Excel(name = "lendingline")
    private String lendingline;

    /** mp_limit */
    @Excel(name = "mp_limit")
    private String mpLimit;

    /** mp_remy_ppl */
    @Excel(name = "mp_remy_ppl")
    private String mpRemyPpl;

    /** 账号 */
    @Excel(name = "账号")
    private String accountId;

    /** 卡名称 */
    @Excel(name = "卡名称")
    private String productname;

    /** sourcetype */
    @Excel(name = "sourcetype")
    private String sourcetype;

    /** 分行 */
    @Excel(name = "分行")
    private String branch;

    /** 委案日期 */
    @Excel(name = "委案日期")
    private String assignDate;

    /** 委案金额（人民币） */
    @Excel(name = "委案金额", readConverterExp = "人=民币")
    private String assignBalRmb;

    /** 到期日期 */
    @Excel(name = "到期日期")
    private String expireDate;

    /** 委案逾期段 */
    @Excel(name = "委案逾期段")
    private String assignBucket;

    /** 最后一次还款日期 */
    @Excel(name = "最后一次还款日期")
    private String lastPaymentDate;

    /** 开卡日 */
    @Excel(name = "开卡日")
    private String datOpen;

    /** 管制码解析 */
    @Excel(name = "管制码解析")
    private String orgCodeValue;

    /** 委案时逾期天数 */
    @Excel(name = "委案时逾期天数")
    private String assignDpdOld;

    /** 委案逾期段 */
    @Excel(name = "委案逾期段")
    private String assignBucketOld;

    /** 委案金额（人民币） */
    @Excel(name = "委案金额", readConverterExp = "人=民币")
    private String assignBalRmbOld;

    /** 委案金额（美金） */
    @Excel(name = "委案金额", readConverterExp = "美=金")
    private String assignBalUsaOld;

    /** 本金 */
    @Excel(name = "本金")
    private String amtPrincipleOld;

    /** 最后一次还款 */
    @Excel(name = "最后一次还款")
    private String lastPaymentOld;

    /** 最后一次还款日期 */
    @Excel(name = "最后一次还款日期")
    private String lastPaymentDateOld;

    /** 初始最低还款额 */
    @Excel(name = "初始最低还款额")
    private String minPayOld;

    /** 委外机构 */
    @Excel(name = "委外机构")
    private String outsource;

    /** 委托批次 */
    @Excel(name = "委托批次")
    private String outsourceBatch;

    /** 本月任务类型 */
    @Excel(name = "本月任务类型")
    private String monthType;

    /** 账户类型 */
    @Excel(name = "账户类型")
    private String accountType;

    /** 账户分包 */
    @Excel(name = "账户分包")
    private String accountSubcontract;

    /** 来源 */
    @Excel(name = "来源")
    private String source;

    /** 委托时点账户状态 */
    @Excel(name = "委托时点账户状态")
    private String outAccountStatus;

    /** 委托时点是否有外币欠款 */
    @Excel(name = "委托时点是否有外币欠款")
    private String outForeignDebt;

    /** 委托时点利息 */
    @Excel(name = "委托时点利息")
    private String outFee;

    /** 委托时点费用 */
    @Excel(name = "委托时点费用")
    private String outCost;

    /** 委托时点本金 */
    @Excel(name = "委托时点本金")
    private String outCapital;

    /** 委托时点本金_part */
    @Excel(name = "委托时点本金_part")
    private String outCapitalPart;

    /** 委托时点本息费合计 */
    @Excel(name = "委托时点本息费合计")
    private String outCapitalTotal;

    /** 委托时点逾期期数 */
    @Excel(name = "委托时点逾期期数")
    private String outOverdue;

    /** 跑批开始日期 */
    @Excel(name = "跑批开始日期")
    private String outDate;

    /** 跑批停止日期 */
    @Excel(name = "跑批停止日期")
    private String outDateEnd;

    /** 委外英文名 */
    @Excel(name = "委外英文名")
    private String nameEn;

    /** 备注1 */
    @Excel(name = "备注1")
    private String memo1;

    /** 备注2 */
    @Excel(name = "备注2")
    private String memo2;

    /** 备注3 */
    @Excel(name = "备注3")
    private String memo3;

    /** 统计日期 */
    @Excel(name = "统计日期")
    private String statisticsDate;

    /** 核销日期 */
    @Excel(name = "核销日期")
    private String transDate;

    /** 部分转卖交割日期 */
    @Excel(name = "部分转卖交割日期")
    private String sellDate;

    /** 账户级别 */
    @Excel(name = "账户级别")
    private String accountLevel;

    /** 账户类型描述 */
    @Excel(name = "账户类型描述")
    private String accountTypeDetail;

    /** 开户日期 */
    @Excel(name = "开户日期")
    private String openDate;

    /** 账户信用额度 */
    @Excel(name = "账户信用额度")
    private String accountLimit;

    /** 是否有外币欠款 */
    @Excel(name = "是否有外币欠款")
    private String foreignDebt;

    /** 是否网申 */
    @Excel(name = "是否网申")
    private String webApply;

    /** 帐单日 */
    @Excel(name = "帐单日")
    private String billDate;

    /** 账户余额 */
    @Excel(name = "账户余额")
    private String accountBalance;

    /** 本息费合计 */
    @Excel(name = "本息费合计")
    private String capitalTotal;

    /** 本息费合计加停计息费 */
    @Excel(name = "本息费合计加停计息费")
    private String capitalTotalDebt;

    /** 利息 */
    @Excel(name = "利息")
    private String fee;

    /** 费用 */
    @Excel(name = "费用")
    private String cost;

    /** 本金 */
    @Excel(name = "本金")
    private String capital;

    /** 本息费合计_part */
    @Excel(name = "本息费合计_part")
    private String capitalTotalPart;

    /** 利息_part */
    @Excel(name = "利息_part")
    private String feePart;

    /** 费用_part */
    @Excel(name = "费用_part")
    private String costPart;

    /** 本金_part */
    @Excel(name = "本金_part")
    private String capitalPart;

    /** 普通分期未摊本金 */
    @Excel(name = "普通分期未摊本金")
    private String nomalUncapital;

    /** 大额分期未摊本金 */
    @Excel(name = "大额分期未摊本金")
    private String largeUncapital;

    /** 账户级_逾期n期应收未收利息 */
    @Excel(name = "账户级_逾期n期应收未收利息")
    private String accountUnfee;

    /** 账户级_逾期n期滞纳金 */
    @Excel(name = "账户级_逾期n期滞纳金")
    private String accountLatefee;

    /** 账户级_逾期n期停收手续费 */
    @Excel(name = "账户级_逾期n期停收手续费")
    private String accountUnservicefee;

    /** F_交易手续费 */
    @Excel(name = "F_交易手续费")
    private String fServicefee;

    /** F_按日计手续费 */
    @Excel(name = "F_按日计手续费")
    private String fServicefeeDay;

    /** F_违约金 */
    @Excel(name = "F_违约金")
    private String fPenalty;

    /** F_分期付款手续费 */
    @Excel(name = "F_分期付款手续费")
    private String fServicefeeFenq;

    /** F_年费 */
    @Excel(name = "F_年费")
    private String fFeeYear;

    /** odue_1 */
    @Excel(name = "odue_1")
    private String overdue1;

    /** odue_2 */
    @Excel(name = "odue_2")
    private String overdue2;

    /** 最新逾期期数 */
    @Excel(name = "最新逾期期数")
    private String overdueRecent;

    /** 逾期阶段 */
    @Excel(name = "逾期阶段")
    private String overduePhase;

    /** 降至0期金额 */
    @Excel(name = "降至0期金额")
    private String down0Pay;

    /** 降至1期金额 */
    @Excel(name = "降至1期金额")
    private String down1Pay;

    /** 降至2期金额 */
    @Excel(name = "降至2期金额")
    private String down2Pay;

    /** 降至3期金额 */
    @Excel(name = "降至3期金额")
    private String down3Pay;

    /** 委托期净回款 */
    @Excel(name = "委托期净回款")
    private String outPay;

    /** 本月净回款 */
    @Excel(name = "本月净回款")
    private String payCurmon;

    /** 末次回款日期 */
    @Excel(name = "末次回款日期")
    private String payDateLast;

    /** 末次回款金额 */
    @Excel(name = "末次回款金额")
    private String payLast;

    /** 委托期本币回款额 */
    @Excel(name = "委托期本币回款额")
    private String outLocalPay;

    /** 委托期外币回款额 */
    @Excel(name = "委托期外币回款额")
    private String outForeignPay;

    /** 委托期本外币回款额 */
    @Excel(name = "委托期本外币回款额")
    private String outTotalPay;

    /** 本月本币回款额 */
    @Excel(name = "本月本币回款额")
    private String curmonLocalPay;

    /** 本月外币回款额 */
    @Excel(name = "本月外币回款额")
    private String curmonForeignPay;

    /** 本月本外币回款额 */
    @Excel(name = "本月本外币回款额")
    private String curmonTotalPay;

    /** 当日本币回款额 */
    @Excel(name = "当日本币回款额")
    private String curdayLocalPay;

    /** 当日外币回款额 */
    @Excel(name = "当日外币回款额")
    private String curdayForeignPay;

    /** 当日本外币回款额 */
    @Excel(name = "当日本外币回款额")
    private String curdayTotalPay;

    /** 帐户状态代码 */
    @Excel(name = "帐户状态代码")
    private String accountStatusNum;

    /** 委托天数 */
    @Excel(name = "委托天数")
    private String outDayNum;

    /** 是否有净回款 */
    @Excel(name = "是否有净回款")
    private String isPay;

    /** 委托期日均回款 */
    @Excel(name = "委托期日均回款")
    private String outDayaverPay;

    /** 本月委托天数 */
    @Excel(name = "本月委托天数")
    private String curmonDay;

    /** 本月日均回款 */
    @Excel(name = "本月日均回款")
    private String curmonDayaverPay;

    /** 源系统标识 */
    @Excel(name = "源系统标识")
    private String systemSign;

    /** 业务标识 */
    @Excel(name = "业务标识")
    private String businessSign;

    /** 标识分类 */
    @Excel(name = "标识分类")
    private String signType;

    /** 标识意义 */
    @Excel(name = "标识意义")
    private String signMeaning;

    /** 总余额_剔溢缴款用 */
    @Excel(name = "总余额_剔溢缴款用")
    private String signDate;

    /** $column.columnComment */
    @Excel(name = "总余额_剔溢缴款用")
    private String totalBalance;

    /** 一级机构 */
    @Excel(name = "一级机构")
    private String org1;

    /** 二级机构 */
    @Excel(name = "二级机构")
    private String org2;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String company;

    /** 行业代码 */
    @Excel(name = "行业代码")
    private String industry;

    /** 单位地址 */
    @Excel(name = "单位地址")
    private String companyAddr;

    /** 单位邮编 */
    @Excel(name = "单位邮编")
    private String postcode;

    /** 家庭地址 */
    @Excel(name = "家庭地址")
    private String homeAddr;

    /** 家庭邮编 */
    @Excel(name = "家庭邮编")
    private String homePostcode;

    /** 户籍地址 */
    @Excel(name = "户籍地址")
    private String residenceAddress;

    /** 户籍邮编 */
    @Excel(name = "户籍邮编")
    private String residencePostcode;

    /** 房产地址 */
    @Excel(name = "房产地址")
    private String houseAddr;

    /** 房产邮编 */
    @Excel(name = "房产邮编")
    private String housePostcode;

    /** 行业细类 */
    @Excel(name = "行业细类")
    private String industryCategory;

    /** 行业大类 */
    @Excel(name = "行业大类")
    private String industryKind;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String custName;

    /** 手机 */
    @Excel(name = "手机")
    private String mobile;

    /** 核销日期 */
    @Excel(name = "核销日期")
    private String cancelDate;

    /** 最新逾期期数初始值 */
    @Excel(name = "最新逾期期数初始值")
    private String overdueRecentInit;

    /** 信息页面有新还款标红  0标红 1 不红 */
    @Excel(name = "信息页面有新还款标红  0标红 1 不红")
    private String payState;

    /** 借据号 */
    @Excel(name = "借据号")
    private String borrowId;

    /** 外包标的 */
    @Excel(name = "外包标的")
    private String wbbd;

    /** 数据日期 */
    @Excel(name = "数据日期")
    private String dataTime;

    /** 催收节点 */
    @Excel(name = "催收节点")
    private String csNode;

    /** 外包经办 */
    @Excel(name = "外包经办")
    private String wbCompany;

    /** 原始客户经理名称 */
    @Excel(name = "原始客户经理名称")
    private String originalManager;

    /** 客户经理名称 */
    @Excel(name = "客户经理名称")
    private String manager;

    /** 收款账号 */
    @Excel(name = "收款账号")
    private String accountNumber;

    /** 新分类对应产品名称 */
    @Excel(name = "新分类对应产品名称")
    private String newProductName;

    /** 还款方式 */
    @Excel(name = "还款方式")
    private String payType;

    /** 到期日期 */
    @Excel(name = "到期日期")
    private String collectionDateEnd;

    /** 起息日期 */
    @Excel(name = "起息日期")
    private String collectionDate;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    public String getProductName(){
        return productName;
    }

    private String productName1;

    /** 逾期日期 */
    @Excel(name = "逾期日期")
    private String overdueDate;

    /** 渠道名称 */
    @Excel(name = "渠道名称")
    private String channelName;

    /** 案件回收时间 */
    @Excel(name = "案件回收时间")
    private String recoveryTime;

    /** 分配时间 */
    @Excel(name = "分配时间")
    private String allocationTime;

    /** 序号 */
    @Excel(name = "序号")
    private String orderNumber;

    /** 地区事业部二级 */
    @Excel(name = "地区事业部二级")
    private String city2;

    /** 合同号 */
    @Excel(name = "合同号")
    private String contractId;

    /** 罚息 */
    @Excel(name = "罚息")
    private String interestPenalty;

    /** 滞纳金 */
    @Excel(name = "滞纳金")
    private String latefee;

    /** 逾期手续费 */
    @Excel(name = "逾期手续费")
    private String overdueServicefee;

    /** 地区事业部一级 */
    @Excel(name = "地区事业部一级")
    private String city1;

    /** 单据号 */
    @Excel(name = "单据号")
    private String debtNo;

    /** 初始入催金额 */
    @Excel(name = "初始入催金额")
    private String debtInit;


}
