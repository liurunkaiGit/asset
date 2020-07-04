package com.ruoyi.assetspackage.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产流水
 * 
 * @author guozeqi
 * @date 2020-01-22
 */
public class StreamPackage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 机构案件号 */
    @Excel(name = "机构案件号")
    private String orgCasno;

    /** 所属机构 */
    @Excel(name = "所属机构")
    private String org;

    /** 手别 */
    @Excel(name = "手别")
    private String transfertype;

    /** 24CD值 */
    @Excel(name = "24CD值")
    private String twentyfourcd;

    /** BLK */
    @Excel(name = "BLK")
    private String blk;

    /** 产品线 */
    @Excel(name = "产品线")
    private String productline;

    /** 人民币账户last缴款金额 */
    @Excel(name = "人民币账户last缴款金额")
    private BigDecimal rmbLastJkje;

    /** 人民币账户余额 */
    @Excel(name = "人民币账户余额")
    private BigDecimal rmbYe;

    /** 人民币账户应还罚息总额 */
    @Excel(name = "人民币账户应还罚息总额")
    private BigDecimal rmbYhfxzje;

    /** 人民币账户当前CD值 */
    @Excel(name = "人民币账户当前CD值")
    private BigDecimal rmbCd;

    /** 人民币账户最后一次缴款日 */
    @Excel(name = "人民币账户最后一次缴款日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rmbZhycjkr;

    /** 人民币账户最后还款笔数 */
    @Excel(name = "人民币账户最后还款笔数")
    private String rmbZhhkbs;

    /** 人民币账户应还利息总额 */
    @Excel(name = "人民币账户应还利息总额")
    private BigDecimal rmbYhlizje;

    /** 人民币账户应还本金1 */
    @Excel(name = "人民币账户应还本金1")
    private BigDecimal rmbYhbj1;

    /** 人民币账户应还本金2 */
    @Excel(name = "人民币账户应还本金2")
    private BigDecimal rmbYhbj2;

    /** 人民币账户应还本金总额 */
    @Excel(name = "人民币账户应还本金总额")
    private BigDecimal rmbYhbjzje;

    /** 人民币账户应还罚息1 */
    @Excel(name = "人民币账户应还罚息1")
    private BigDecimal rmbYhfx1;

    /** 人民币账户应还罚息2 */
    @Excel(name = "人民币账户应还罚息2")
    private BigDecimal rmbYhfx2;

    /** 人民币账户应还费用1 */
    @Excel(name = "人民币账户应还费用1")
    private BigDecimal rmbYhfy1;

    /** 人民币账户应还费用2 */
    @Excel(name = "人民币账户应还费用2")
    private BigDecimal rmbYhfy2;

    /** 人民币账户应还费用总额 */
    @Excel(name = "人民币账户应还费用总额")
    private BigDecimal rmbYhfyzje;

    /** 人民币账户最低应还金额 */
    @Excel(name = "人民币账户最低应还金额")
    private BigDecimal rmbZdyhje;

    /** 人民币账户欠款总金额 */
    @Excel(name = "人民币账户欠款总金额")
    private BigDecimal rmbQkzje;

    /** 人民币账户额度固定额度 */
    @Excel(name = "人民币账户额度固定额度")
    private BigDecimal rmbGded;

    /** 代码 */
    @Excel(name = "代码")
    private String code;

    /** 借款卡号 */
    @Excel(name = "借款卡号")
    private String borrowNo;

    /** 借款卡银行 */
    @Excel(name = "借款卡银行")
    private String borrowBlank;

    /** 借款额度 */
    @Excel(name = "借款额度")
    private BigDecimal borrowEd;

    /** 债项类型 */
    @Excel(name = "债项类型")
    private String zxType;

    /** 停卡日 */
    @Excel(name = "停卡日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date stopCard;

    /** 停止计息日期 */
    @Excel(name = "停止计息日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date stopJxrq;

    /** 催收公司 */
    @Excel(name = "催收公司")
    private String csCompany;

    /** 入催日 */
    @Excel(name = "入催日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rcr;

    /** 共债标识 */
    @Excel(name = "共债标识")
    private String gzFlag;

    /** 分值 */
    @Excel(name = "分值")
    private String fz;

    /** 分期标识 */
    @Excel(name = "分期标识")
    private String fqFlag;

    /** 剩余核销其他费用 */
    @Excel(name = "剩余核销其他费用")
    private BigDecimal syhxQtfy;

    /** 剩余核销手续费 */
    @Excel(name = "剩余核销手续费")
    private BigDecimal syhxSxf;

    /** 剩余核销本金 */
    @Excel(name = "剩余核销本金")
    private BigDecimal syhxBj;

    /** 剩余核销滞纳费 */
    @Excel(name = "剩余核销滞纳费")
    private BigDecimal syhxZnf;

    /** 剩余核销逾期息 */
    @Excel(name = "剩余核销逾期息")
    private BigDecimal syhxYqx;

    /** 剩余核销金额合计 */
    @Excel(name = "剩余核销金额合计")
    private BigDecimal syhxJehj;

    /** 区域中心 */
    @Excel(name = "区域中心")
    private String areaCenter;

    /** 历史催收备注 */
    @Excel(name = "历史催收备注")
    private String csRemarkHis;

    /** 历史最大逾期天数 */
    @Excel(name = "历史最大逾期天数")
    private String maxYqtsHis;

    /** 历史累计逾期天数 */
    @Excel(name = "历史累计逾期天数")
    private String sumYqtsHis;

    /** 历史累计逾期次数 */
    @Excel(name = "历史累计逾期次数")
    private String sumYqcsHis;

    /** 历史经历催收机构个数 */
    @Excel(name = "历史经历催收机构个数")
    private String csjggsHis;

    /** 取现标识 */
    @Excel(name = "取现标识")
    private String qxFlag;

    /** 受托公司 */
    @Excel(name = "受托公司")
    private String stCompany;

    /** 受理商户 */
    @Excel(name = "受理商户")
    private String acceptFirm;

    /** 受理城市 */
    @Excel(name = "受理城市")
    private String acceptCity;

    /** 受理网点 */
    @Excel(name = "受理网点")
    private String acceptWd;

    /** 受理网点代码 */
    @Excel(name = "受理网点代码")
    private String acceptWdCode;

    /** 呆账核销日期 */
    @Excel(name = "呆账核销日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dzhxrq;

    /** 外币账户应还罚息总额 */
    @Excel(name = "外币账户应还罚息总额")
    private BigDecimal wbYhfxze;

    /** 外币账户应还利息总额 */
    @Excel(name = "外币账户应还利息总额")
    private BigDecimal wbYhlxze;

    /** 外币账户应还本金1 */
    @Excel(name = "外币账户应还本金1")
    private BigDecimal wbYhbj1;

    /** 外币账户应还本金2 */
    @Excel(name = "外币账户应还本金2")
    private BigDecimal wbYhbj2;

    /** 外币账户应还本金总额 */
    @Excel(name = "外币账户应还本金总额")
    private BigDecimal wbYhbjzje;

    /** 外币账户应还罚息1 */
    @Excel(name = "外币账户应还罚息1")
    private BigDecimal wbYhfx1;

    /** 外币账户应还罚息2 */
    @Excel(name = "外币账户应还罚息2")
    private BigDecimal wbYhfx2;

    /** 外币账户应还费用1 */
    @Excel(name = "外币账户应还费用1")
    private BigDecimal wbYhfy1;

    /** 外币账户应还费用2 */
    @Excel(name = "外币账户应还费用2")
    private BigDecimal wbYhfy2;

    /** 外币账户应还费用总额 */
    @Excel(name = "外币账户应还费用总额")
    private BigDecimal wbYhfyze;

    /** 外币账户最低应还额 */
    @Excel(name = "外币账户最低应还额")
    private BigDecimal wbZdyhe;

    /** 外币账户欠款总金额 */
    @Excel(name = "外币账户欠款总金额")
    private BigDecimal wbQkzje;

    /** 套现标识 */
    @Excel(name = "套现标识")
    private String txFlag;

    /** 委外公司代码 */
    @Excel(name = "委外公司代码")
    private String wwCompanyCode;

    /** 委外城市名称 */
    @Excel(name = "委外城市名称")
    private String wwCityName;

    /** 委外计划截止日期 */
    @Excel(name = "委外计划截止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date wwJhEnddate;

    /** 委外起始日期 */
    @Excel(name = "委外起始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date wwQsrq;

    /** 委托批次 */
    @Excel(name = "委托批次")
    private String wwPc;

    /** 委案余额 */
    @Excel(name = "委案余额")
    private BigDecimal waYe;

    /** 帐单地址 */
    @Excel(name = "帐单地址")
    private String billAddress;

    /** 帐单地址邮编 */
    @Excel(name = "帐单地址邮编")
    private String billAddressPostcode;

    /** 年利率 */
    @Excel(name = "年利率")
    private String yearRates;

    /** 应还款日 */
    @Excel(name = "应还款日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yhkDate;

    /** 开始逾期日期 */
    @Excel(name = "开始逾期日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startYqDate;

    /** 开户日 */
    @Excel(name = "开户日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date khDate;

    /** 投诉标识 */
    @Excel(name = "投诉标识")
    private String tsFlag;

    /** 授信额度 */
    @Excel(name = "授信额度")
    private BigDecimal creditValue;

    /** 推荐商户 */
    @Excel(name = "推荐商户")
    private String tjFirm;

    /** 推荐城市 */
    @Excel(name = "推荐城市")
    private String tjCity;

    /** 推荐网点 */
    @Excel(name = "推荐网点")
    private String tjWd;

    /** 收回核销手续费 */
    @Excel(name = "收回核销手续费")
    private BigDecimal shhxSxf;

    /** 收回核销本金 */
    @Excel(name = "收回核销本金")
    private BigDecimal shhxBj;

    /** 收回核销滞纳费 */
    @Excel(name = "收回核销滞纳费")
    private BigDecimal shhxZnf;

    /** 收回核销费用 */
    @Excel(name = "收回核销费用")
    private BigDecimal shhxFy;

    /** 收回核销逾期息 */
    @Excel(name = "收回核销逾期息")
    private BigDecimal shhxYqx;

    /** 收回核销金额合计 */
    @Excel(name = "收回核销金额合计")
    private BigDecimal shhxJehj;

    /** 整合30 DAY */
    @Excel(name = "整合30 DAY")
    private String zh30Day;

    /** 整合X DA */
    @Excel(name = "整合X DA")
    private String zhXDay;

    /** 是否仲裁 */
    @Excel(name = "是否仲裁")
    private String isZc;

    /** 是否债务重组 */
    @Excel(name = "是否债务重组")
    private String isZwcz;

    /** 是否计息 */
    @Excel(name = "是否计息")
    private String isJx;

    /** 是否诉讼 */
    @Excel(name = "是否诉讼")
    private String isSx;

    /** 最近逾期月份 */
    @Excel(name = "最近逾期月份", width = 30, dateFormat = "yyyy-MM-dd")
    private Date zjyqMonth;

    /** 核销手续费催收 */
    @Excel(name = "核销手续费催收")
    private String hxSxfcs;

    /** 核销收回状态 */
    @Excel(name = "核销收回状态")
    private String hxShzt;

    /** 核销本金催收 */
    @Excel(name = "核销本金催收")
    private String hxBjcs;

    /** 核销滞纳费催收 */
    @Excel(name = "核销滞纳费催收")
    private String hxZnfcs;

    /** 核销费用催收 */
    @Excel(name = "核销费用催收")
    private String hxFycs;

    /** 核销逾期息催收 */
    @Excel(name = "核销逾期息催收")
    private String hxYqlxcs;

    /** 核销金额合计催收 */
    @Excel(name = "核销金额合计催收")
    private String hxJehjcs;

    /** 案件核准日期 */
    @Excel(name = "案件核准日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ajHzDate;

    /** 消费金融账号 */
    @Excel(name = "消费金融账号")
    private String xfjzzh;

    /** 特殊案件类型 */
    @Excel(name = "特殊案件类型")
    private String tsajType;

    /** 日利率 */
    @Excel(name = "日利率")
    private String dayRates;

    /** 统计日期 */
    @Excel(name = "统计日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tjDate;

    /** 美元余额 */
    @Excel(name = "美元余额")
    private BigDecimal dollarYe;

    /** 美元当前CD值 */
    @Excel(name = "美元当前CD值")
    private String dollarCd;

    /** 美元最低应缴款金额 */
    @Excel(name = "美元最低应缴款金额")
    private BigDecimal dollarZdyjkje;

    /** 美元最后一次缴款日 */
    @Excel(name = "美元最后一次缴款日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dollarZhycjkr;

    /** 美元最后一次缴款金额 */
    @Excel(name = "美元最后一次缴款金额")
    private BigDecimal dollarZhycjkje;

    /** 美元还款日还款笔数 */
    @Excel(name = "美元还款日还款笔数")
    private String dollarHkrhkbs;

    /** 获客方式 */
    @Excel(name = "获客方式")
    private String hkfs;

    /** 获客渠道 */
    @Excel(name = "获客渠道")
    private String hkqd;

    /** 行方评分 */
    @Excel(name = "行方评分")
    private String xwpf;

    /** 账单日 */
    @Excel(name = "账单日")
    private String accountDate;

    /** 账户余额本金 */
    @Excel(name = "账户余额本金")
    private BigDecimal accountYebj;

    /** 账户结清日期 */
    @Excel(name = "账户结清日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date accountJqrq;

    /** 辅助组别 */
    @Excel(name = "辅助组别")
    private String fzzb;

    /** 还款卡号 */
    @Excel(name = "还款卡号")
    private String revertCardNo;

    /** 还款卡银行 */
    @Excel(name = "还款卡银行")
    private String revertCardBlank;

    /** 进件渠道 */
    @Excel(name = "进件渠道")
    private String jjqd;

    /** 逾期天数 */
    @Excel(name = "逾期天数")
    private String overdueDays;

    /** 逾期标记 */
    @Excel(name = "逾期标记")
    private String overdueFlag;

    /** 长委次数 */
    @Excel(name = "长委次数")
    private String cwcs;

    /** 额度产品 */
    @Excel(name = "额度产品")
    private String quotaProduct;

    /** 额度代码 */
    @Excel(name = "额度代码")
    private String quotaCode;

    /** 额度激活日期 */
    @Excel(name = "额度激活日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date quotaDate;

    /** 风险 */
    @Excel(name = "风险")
    private String risk;

    /** 首次放款日期 */
    @Excel(name = "首次放款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstFkDate;

    /** 首次逾期日期 */
    @Excel(name = "首次逾期日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstYqDate;

    /** 首次逾期解除日期 */
    @Excel(name = "首次逾期解除日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstYqjcDate;

    /** 首逾标识 */
    @Excel(name = "首逾标识")
    private String firstYqFlag;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String curName;

    /** 客户性别 */
    @Excel(name = "客户性别")
    private String curSex;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String certificateNo;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String certificateType;

    /** 身份证地址 */
    @Excel(name = "身份证地址")
    private String certificateAddress;

    /** 出生日期 */
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 户籍地址 */
    @Excel(name = "户籍地址")
    private String registAddress;

    /** 电子邮箱 */
    @Excel(name = "电子邮箱")
    private String email;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 婚姻状况 */
    @Excel(name = "婚姻状况")
    private String marriage;

    /** 教育程度 */
    @Excel(name = "教育程度")
    private String education;

    /** 经济类型 */
    @Excel(name = "经济类型")
    private String economy;

    /** 当前收入 */
    @Excel(name = "当前收入")
    private BigDecimal curIncome;

    /** 新增地址 */
    @Excel(name = "新增地址")
    private String newAddress;

    /** 客户号 */
    @Excel(name = "客户号")
    private String customerNo;

    /** 客户手机号码 */
    @Excel(name = "客户手机号码")
    private String customerMobile;

    /** 客户家庭电话 */
    @Excel(name = "客户家庭电话")
    private String customerHomeTel;

    /** 客户家庭地址 */
    @Excel(name = "客户家庭地址")
    private String customerHomeAddress;

    /** 客户家庭地址邮编 */
    @Excel(name = "客户家庭地址邮编")
    private String customerHomePostcode;

    /** 卡片寄送地址 */
    @Excel(name = "卡片寄送地址")
    private String cardPostAddress;

    /** 职务 */
    @Excel(name = "职务")
    private String job;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 行业性质 */
    @Excel(name = "行业性质")
    private String indust;

    /** 客户单位名称 */
    @Excel(name = "客户单位名称")
    private String workName;

    /** 客户单位地址 */
    @Excel(name = "客户单位地址")
    private String workAddress;

    /** 单位电话 */
    @Excel(name = "单位电话")
    private String workTel;

    /** 单位邮编 */
    @Excel(name = "单位邮编")
    private String workPostcode;

    /** 第一联系人姓名 */
    @Excel(name = "第一联系人姓名")
    private String firstLiaisonName;

    /** 第一联系人与客户关系 */
    @Excel(name = "第一联系人与客户关系")
    private String firstLiaisonRelation;

    /** 第一联系人手机 */
    @Excel(name = "第一联系人手机")
    private String firstLiaisonMobile;

    /** 第一联系人电话 */
    @Excel(name = "第一联系人电话")
    private String firstLiaisonTel;

    /** 第二联系人姓名 */
    @Excel(name = "第二联系人姓名")
    private String secondLiaisonName;

    /** 第二联系人与客户关系 */
    @Excel(name = "第二联系人与客户关系")
    private String secondLiaisonRelation;

    /** 第二联系人手机 */
    @Excel(name = "第二联系人手机")
    private String secondLiaisonMobile;

    /** 第二联系人电话 */
    @Excel(name = "第二联系人电话")
    private String secondLiaisonTel;

    /** 第三联系人姓名 */
    @Excel(name = "第三联系人姓名")
    private String threeLiaisonName;

    /** 第三联系人与客户关系 */
    @Excel(name = "第三联系人与客户关系")
    private String threeLiaisonRelation;

    /** 第三联系人手机 */
    @Excel(name = "第三联系人手机")
    private String threeLiaisonMobile;

    /** 第三联系人电话 */
    @Excel(name = "第三联系人电话")
    private String threeLiaisonTel;

    /** 数据日期 */
    @Excel(name = "数据日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sjrq;

    /** 合同号 */
    @Excel(name = "合同号")
    private String hth;

    /** 借据号 */
    @Excel(name = "借据号")
    private String jjh;

    /** 地区事业部(一级) */
    @Excel(name = "地区事业部(一级)")
    private String dqsybYj;

    /** 地区事业部(二级) */
    @Excel(name = "地区事业部(二级)")
    private String dqsybEj;

    /** 催收节点 */
    @Excel(name = "催收节点")
    private String csjd;

    /** 外包经办 */
    @Excel(name = "外包经办")
    private String wbjb;

    /** 原始客户经理名称 */
    @Excel(name = "原始客户经理名称")
    private String ysKhjlmc;

    /** 客户经理名称 */
    @Excel(name = "客户经理名称")
    private String khjlmc;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String cpmc;

    /** 新分类对应产品名称 */
    @Excel(name = "新分类对应产品名称")
    private String xfldycpmc;

    /** 起息日期 */
    @Excel(name = "起息日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date qxrq;

    /** 到期日期 */
    @Excel(name = "到期日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dqrq;

    /** 还款方式 */
    @Excel(name = "还款方式")
    private String hkType;

    /** 贷款余额 */
    @Excel(name = "贷款余额")
    private BigDecimal dkye;

    /** 外包标的 */
    @Excel(name = "外包标的")
    private String wbbs;

    /** 逾期金额 */
    @Excel(name = "逾期金额")
    private BigDecimal yqje;

    /** 逾期本金 */
    @Excel(name = "逾期本金")
    private BigDecimal yqbj;

    /** 逾期利息 */
    @Excel(name = "逾期利息")
    private BigDecimal yqlx;

    /** 逾期手续费 */
    @Excel(name = "逾期手续费")
    private BigDecimal yqsxf;

    /** 滞纳金 */
    @Excel(name = "滞纳金")
    private BigDecimal znj;

    /** 渠道名称 */
    @Excel(name = "渠道名称")
    private String qdmc;

    /** 外包手别 */
    @Excel(name = "外包手别")
    private String wbsb;

    /** 外包期数 */
    @Excel(name = "外包期数")
    private String wbqs;

    /** 分配时间 */
    @Excel(name = "分配时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fpsj;

    /** 案件回收时间 */
    @Excel(name = "案件回收时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ajhssj;

    /** 委托机构id */
    private String orgId;

    /** 打包标识（0,已打包，1,未打包） */
    private String packageFlag;

///////////////////////////////////////////////////////////////////////////////
    /** 交易其他费用 */
    @Excel(name = "交易其他费用")
    private BigDecimal jyqtfy;

    /** 交易利息 */
    @Excel(name = "交易利息")
    private BigDecimal jylx;

    /** 交易本金 */
    @Excel(name = "交易本金")
    private BigDecimal jybj;

    /** 交易滞纳费 */
    @Excel(name = "交易滞纳费")
    private BigDecimal jyznf;

    /** 交易类型 */
    @Excel(name = "交易类型")
    private String jyType;

    /** 交易金额 */
    @Excel(name = "交易金额")
    private BigDecimal jyje;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private String productType;

    /** 借据号 */
    @Excel(name = "借据号")
    private String jjh2;

    /** 催收人 */
    @Excel(name = "催收人")
    private String csr;

    /** 催收节点 */
    @Excel(name = "催收节点")
    private String csjd2;

    /** 分配日期 */
    @Excel(name = "分配日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fprq;

    /** 区域中心 */
    @Excel(name = "区域中心")
    private String areaCenter2;

    /** 受理城市 */
    @Excel(name = "受理城市")
    private String acceptCity2;

    /** 合同号 */
    @Excel(name = "合同号")
    private String hth2;

    /** 地区事业部(一级) */
    @Excel(name = "地区事业部(一级)")
    private String dqsybYj2;

    /** 地区事业部(二级) */
    @Excel(name = "地区事业部(二级)")
    private String dqsybEj2;

    /** 外包期数 */
    @Excel(name = "外包期数")
    private String wbqs2;

    /** 外包经办 */
    @Excel(name = "外包经办")
    private String wbjb2;

    /** 委案日期 */
    @Excel(name = "委案日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date warq;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String curName2;

    /** 客户经理姓名 */
    @Excel(name = "客户经理姓名")
    private String khjlxm;

    /** 数据日期 */
    @Excel(name = "数据日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sjrq2;

    /** 是否外包催收 */
    @Excel(name = "是否外包催收")
    private String sfwbcs;

    /** 是否结清 */
    @Excel(name = "是否结清")
    private String sfjq;

    /** 本月委案 */
    @Excel(name = "本月委案")
    private String bywa;

    /** 案件回收日期 */
    @Excel(name = "案件回收日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ajhsrq;

    /** 消费金融账号 */
    @Excel(name = "消费金融账号")
    private String xfjrzh;

    /** 调整事项 */
    @Excel(name = "调整事项")
    private String tzsx;

    /** 调整金额 */
    @Excel(name = "调整金额")
    private BigDecimal tzje;

    /** 账户状态 */
    @Excel(name = "账户状态")
    private String zhzt;

    /** 还款日期 */
    @Excel(name = "还款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date hkrq;

    /** 还款时逾期期数 */
    @Excel(name = "还款时逾期期数")
    private String hksyqqs;

    /** 还款金额 */
    @Excel(name = "还款金额")
    private BigDecimal hkje;

    /** 逾期产品类型 */
    @Excel(name = "逾期产品类型")
    private String yqcplx;

    /** 逾期阶段 */
    @Excel(name = "逾期阶段")
    private String yqjd;

    /** 额度产品 */
    @Excel(name = "额度产品")
    private String quotaProduct2;

    /** 结案状态 */
    @Excel(name = "结案状态")
    private String jazt;


    /** 资产创建人 */
    @Excel(name = "资产创建人")
    private String createBy;
    /** 资产创建时间 */
    @Excel(name = "资产创建时间", width = 50, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 还款创建人 */
    @Excel(name = "还款创建人" )
    private String createBy2;

    /** 还款创建时间 */
    @Excel(name = "还款创建时间", width = 50, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime2;


    private Date startDate;

    private Date endDate;

    private Date startDate2;

    private Date endDate2;


    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setOrgCasno(String orgCasno) 
    {
        this.orgCasno = orgCasno;
    }

    public String getOrgCasno() 
    {
        return orgCasno;
    }
    public void setOrg(String org) 
    {
        this.org = org;
    }

    public String getOrg() 
    {
        return org;
    }
    public void setTransfertype(String transfertype) 
    {
        this.transfertype = transfertype;
    }

    public String getTransfertype() 
    {
        return transfertype;
    }
    public void setTwentyfourcd(String twentyfourcd) 
    {
        this.twentyfourcd = twentyfourcd;
    }

    public String getTwentyfourcd() 
    {
        return twentyfourcd;
    }
    public void setBlk(String blk) 
    {
        this.blk = blk;
    }

    public String getBlk() 
    {
        return blk;
    }
    public void setProductline(String productline) 
    {
        this.productline = productline;
    }

    public String getProductline() 
    {
        return productline;
    }
    public void setRmbLastJkje(BigDecimal rmbLastJkje) 
    {
        this.rmbLastJkje = rmbLastJkje;
    }

    public BigDecimal getRmbLastJkje() 
    {
        return rmbLastJkje;
    }
    public void setRmbYe(BigDecimal rmbYe) 
    {
        this.rmbYe = rmbYe;
    }

    public BigDecimal getRmbYe() 
    {
        return rmbYe;
    }
    public void setRmbYhfxzje(BigDecimal rmbYhfxzje) 
    {
        this.rmbYhfxzje = rmbYhfxzje;
    }

    public BigDecimal getRmbYhfxzje() 
    {
        return rmbYhfxzje;
    }
    public void setRmbCd(BigDecimal rmbCd) 
    {
        this.rmbCd = rmbCd;
    }

    public BigDecimal getRmbCd() 
    {
        return rmbCd;
    }
    public void setRmbZhycjkr(Date rmbZhycjkr) 
    {
        this.rmbZhycjkr = rmbZhycjkr;
    }

    public Date getRmbZhycjkr() 
    {
        return rmbZhycjkr;
    }
    public void setRmbZhhkbs(String rmbZhhkbs) 
    {
        this.rmbZhhkbs = rmbZhhkbs;
    }

    public String getRmbZhhkbs() 
    {
        return rmbZhhkbs;
    }
    public void setRmbYhlizje(BigDecimal rmbYhlizje) 
    {
        this.rmbYhlizje = rmbYhlizje;
    }

    public BigDecimal getRmbYhlizje() 
    {
        return rmbYhlizje;
    }
    public void setRmbYhbj1(BigDecimal rmbYhbj1) 
    {
        this.rmbYhbj1 = rmbYhbj1;
    }

    public BigDecimal getRmbYhbj1() 
    {
        return rmbYhbj1;
    }
    public void setRmbYhbj2(BigDecimal rmbYhbj2) 
    {
        this.rmbYhbj2 = rmbYhbj2;
    }

    public BigDecimal getRmbYhbj2() 
    {
        return rmbYhbj2;
    }
    public void setRmbYhbjzje(BigDecimal rmbYhbjzje) 
    {
        this.rmbYhbjzje = rmbYhbjzje;
    }

    public BigDecimal getRmbYhbjzje() 
    {
        return rmbYhbjzje;
    }
    public void setRmbYhfx1(BigDecimal rmbYhfx1) 
    {
        this.rmbYhfx1 = rmbYhfx1;
    }

    public BigDecimal getRmbYhfx1() 
    {
        return rmbYhfx1;
    }
    public void setRmbYhfx2(BigDecimal rmbYhfx2) 
    {
        this.rmbYhfx2 = rmbYhfx2;
    }

    public BigDecimal getRmbYhfx2() 
    {
        return rmbYhfx2;
    }
    public void setRmbYhfy1(BigDecimal rmbYhfy1) 
    {
        this.rmbYhfy1 = rmbYhfy1;
    }

    public BigDecimal getRmbYhfy1() 
    {
        return rmbYhfy1;
    }
    public void setRmbYhfy2(BigDecimal rmbYhfy2) 
    {
        this.rmbYhfy2 = rmbYhfy2;
    }

    public BigDecimal getRmbYhfy2() 
    {
        return rmbYhfy2;
    }
    public void setRmbYhfyzje(BigDecimal rmbYhfyzje) 
    {
        this.rmbYhfyzje = rmbYhfyzje;
    }

    public BigDecimal getRmbYhfyzje() 
    {
        return rmbYhfyzje;
    }
    public void setRmbZdyhje(BigDecimal rmbZdyhje) 
    {
        this.rmbZdyhje = rmbZdyhje;
    }

    public BigDecimal getRmbZdyhje() 
    {
        return rmbZdyhje;
    }
    public void setRmbQkzje(BigDecimal rmbQkzje) 
    {
        this.rmbQkzje = rmbQkzje;
    }

    public BigDecimal getRmbQkzje() 
    {
        return rmbQkzje;
    }
    public void setRmbGded(BigDecimal rmbGded) 
    {
        this.rmbGded = rmbGded;
    }

    public BigDecimal getRmbGded() 
    {
        return rmbGded;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setBorrowNo(String borrowNo) 
    {
        this.borrowNo = borrowNo;
    }

    public String getBorrowNo() 
    {
        return borrowNo;
    }
    public void setBorrowBlank(String borrowBlank) 
    {
        this.borrowBlank = borrowBlank;
    }

    public String getBorrowBlank() 
    {
        return borrowBlank;
    }
    public void setBorrowEd(BigDecimal borrowEd) 
    {
        this.borrowEd = borrowEd;
    }

    public BigDecimal getBorrowEd() 
    {
        return borrowEd;
    }
    public void setZxType(String zxType) 
    {
        this.zxType = zxType;
    }

    public String getZxType() 
    {
        return zxType;
    }
    public void setStopCard(Date stopCard) 
    {
        this.stopCard = stopCard;
    }

    public Date getStopCard() 
    {
        return stopCard;
    }
    public void setStopJxrq(Date stopJxrq) 
    {
        this.stopJxrq = stopJxrq;
    }

    public Date getStopJxrq() 
    {
        return stopJxrq;
    }
    public void setCsCompany(String csCompany) 
    {
        this.csCompany = csCompany;
    }

    public String getCsCompany() 
    {
        return csCompany;
    }
    public void setRcr(Date rcr) 
    {
        this.rcr = rcr;
    }

    public Date getRcr() 
    {
        return rcr;
    }
    public void setGzFlag(String gzFlag) 
    {
        this.gzFlag = gzFlag;
    }

    public String getGzFlag() 
    {
        return gzFlag;
    }
    public void setFz(String fz) 
    {
        this.fz = fz;
    }

    public String getFz() 
    {
        return fz;
    }
    public void setFqFlag(String fqFlag) 
    {
        this.fqFlag = fqFlag;
    }

    public String getFqFlag() 
    {
        return fqFlag;
    }
    public void setSyhxQtfy(BigDecimal syhxQtfy) 
    {
        this.syhxQtfy = syhxQtfy;
    }

    public BigDecimal getSyhxQtfy() 
    {
        return syhxQtfy;
    }
    public void setSyhxSxf(BigDecimal syhxSxf) 
    {
        this.syhxSxf = syhxSxf;
    }

    public BigDecimal getSyhxSxf() 
    {
        return syhxSxf;
    }
    public void setSyhxBj(BigDecimal syhxBj) 
    {
        this.syhxBj = syhxBj;
    }

    public BigDecimal getSyhxBj() 
    {
        return syhxBj;
    }
    public void setSyhxZnf(BigDecimal syhxZnf) 
    {
        this.syhxZnf = syhxZnf;
    }

    public BigDecimal getSyhxZnf() 
    {
        return syhxZnf;
    }
    public void setSyhxYqx(BigDecimal syhxYqx) 
    {
        this.syhxYqx = syhxYqx;
    }

    public BigDecimal getSyhxYqx() 
    {
        return syhxYqx;
    }
    public void setSyhxJehj(BigDecimal syhxJehj) 
    {
        this.syhxJehj = syhxJehj;
    }

    public BigDecimal getSyhxJehj() 
    {
        return syhxJehj;
    }
    public void setAreaCenter(String areaCenter) 
    {
        this.areaCenter = areaCenter;
    }

    public String getAreaCenter() 
    {
        return areaCenter;
    }
    public void setCsRemarkHis(String csRemarkHis) 
    {
        this.csRemarkHis = csRemarkHis;
    }

    public String getCsRemarkHis() 
    {
        return csRemarkHis;
    }
    public void setMaxYqtsHis(String maxYqtsHis) 
    {
        this.maxYqtsHis = maxYqtsHis;
    }

    public String getMaxYqtsHis() 
    {
        return maxYqtsHis;
    }
    public void setSumYqtsHis(String sumYqtsHis) 
    {
        this.sumYqtsHis = sumYqtsHis;
    }

    public String getSumYqtsHis() 
    {
        return sumYqtsHis;
    }
    public void setSumYqcsHis(String sumYqcsHis) 
    {
        this.sumYqcsHis = sumYqcsHis;
    }

    public String getSumYqcsHis() 
    {
        return sumYqcsHis;
    }
    public void setCsjggsHis(String csjggsHis) 
    {
        this.csjggsHis = csjggsHis;
    }

    public String getCsjggsHis() 
    {
        return csjggsHis;
    }
    public void setQxFlag(String qxFlag) 
    {
        this.qxFlag = qxFlag;
    }

    public String getQxFlag() 
    {
        return qxFlag;
    }
    public void setStCompany(String stCompany) 
    {
        this.stCompany = stCompany;
    }

    public String getStCompany() 
    {
        return stCompany;
    }
    public void setAcceptFirm(String acceptFirm) 
    {
        this.acceptFirm = acceptFirm;
    }

    public String getAcceptFirm() 
    {
        return acceptFirm;
    }
    public void setAcceptCity(String acceptCity) 
    {
        this.acceptCity = acceptCity;
    }

    public String getAcceptCity() 
    {
        return acceptCity;
    }
    public void setAcceptWd(String acceptWd) 
    {
        this.acceptWd = acceptWd;
    }

    public String getAcceptWd() 
    {
        return acceptWd;
    }
    public void setAcceptWdCode(String acceptWdCode) 
    {
        this.acceptWdCode = acceptWdCode;
    }

    public String getAcceptWdCode() 
    {
        return acceptWdCode;
    }
    public void setDzhxrq(Date dzhxrq) 
    {
        this.dzhxrq = dzhxrq;
    }

    public Date getDzhxrq() 
    {
        return dzhxrq;
    }
    public void setWbYhfxze(BigDecimal wbYhfxze) 
    {
        this.wbYhfxze = wbYhfxze;
    }

    public BigDecimal getWbYhfxze() 
    {
        return wbYhfxze;
    }
    public void setWbYhlxze(BigDecimal wbYhlxze) 
    {
        this.wbYhlxze = wbYhlxze;
    }

    public BigDecimal getWbYhlxze() 
    {
        return wbYhlxze;
    }
    public void setWbYhbj1(BigDecimal wbYhbj1) 
    {
        this.wbYhbj1 = wbYhbj1;
    }

    public BigDecimal getWbYhbj1() 
    {
        return wbYhbj1;
    }
    public void setWbYhbj2(BigDecimal wbYhbj2) 
    {
        this.wbYhbj2 = wbYhbj2;
    }

    public BigDecimal getWbYhbj2() 
    {
        return wbYhbj2;
    }
    public void setWbYhbjzje(BigDecimal wbYhbjzje) 
    {
        this.wbYhbjzje = wbYhbjzje;
    }

    public BigDecimal getWbYhbjzje() 
    {
        return wbYhbjzje;
    }
    public void setWbYhfx1(BigDecimal wbYhfx1) 
    {
        this.wbYhfx1 = wbYhfx1;
    }

    public BigDecimal getWbYhfx1() 
    {
        return wbYhfx1;
    }
    public void setWbYhfx2(BigDecimal wbYhfx2) 
    {
        this.wbYhfx2 = wbYhfx2;
    }

    public BigDecimal getWbYhfx2() 
    {
        return wbYhfx2;
    }
    public void setWbYhfy1(BigDecimal wbYhfy1) 
    {
        this.wbYhfy1 = wbYhfy1;
    }

    public BigDecimal getWbYhfy1() 
    {
        return wbYhfy1;
    }
    public void setWbYhfy2(BigDecimal wbYhfy2) 
    {
        this.wbYhfy2 = wbYhfy2;
    }

    public BigDecimal getWbYhfy2() 
    {
        return wbYhfy2;
    }
    public void setWbYhfyze(BigDecimal wbYhfyze) 
    {
        this.wbYhfyze = wbYhfyze;
    }

    public BigDecimal getWbYhfyze() 
    {
        return wbYhfyze;
    }
    public void setWbZdyhe(BigDecimal wbZdyhe) 
    {
        this.wbZdyhe = wbZdyhe;
    }

    public BigDecimal getWbZdyhe() 
    {
        return wbZdyhe;
    }
    public void setWbQkzje(BigDecimal wbQkzje) 
    {
        this.wbQkzje = wbQkzje;
    }

    public BigDecimal getWbQkzje() 
    {
        return wbQkzje;
    }
    public void setTxFlag(String txFlag) 
    {
        this.txFlag = txFlag;
    }

    public String getTxFlag() 
    {
        return txFlag;
    }
    public void setWwCompanyCode(String wwCompanyCode) 
    {
        this.wwCompanyCode = wwCompanyCode;
    }

    public String getWwCompanyCode() 
    {
        return wwCompanyCode;
    }
    public void setWwCityName(String wwCityName) 
    {
        this.wwCityName = wwCityName;
    }

    public String getWwCityName() 
    {
        return wwCityName;
    }
    public void setWwJhEnddate(Date wwJhEnddate) 
    {
        this.wwJhEnddate = wwJhEnddate;
    }

    public Date getWwJhEnddate() 
    {
        return wwJhEnddate;
    }
    public void setWwQsrq(Date wwQsrq) 
    {
        this.wwQsrq = wwQsrq;
    }

    public Date getWwQsrq() 
    {
        return wwQsrq;
    }
    public void setWwPc(String wwPc) 
    {
        this.wwPc = wwPc;
    }

    public String getWwPc() 
    {
        return wwPc;
    }
    public void setWaYe(BigDecimal waYe) 
    {
        this.waYe = waYe;
    }

    public BigDecimal getWaYe() 
    {
        return waYe;
    }
    public void setBillAddress(String billAddress) 
    {
        this.billAddress = billAddress;
    }

    public String getBillAddress() 
    {
        return billAddress;
    }
    public void setBillAddressPostcode(String billAddressPostcode) 
    {
        this.billAddressPostcode = billAddressPostcode;
    }

    public String getBillAddressPostcode() 
    {
        return billAddressPostcode;
    }
    public void setYearRates(String yearRates) 
    {
        this.yearRates = yearRates;
    }

    public String getYearRates() 
    {
        return yearRates;
    }
    public void setYhkDate(Date yhkDate) 
    {
        this.yhkDate = yhkDate;
    }

    public Date getYhkDate() 
    {
        return yhkDate;
    }
    public void setStartYqDate(Date startYqDate) 
    {
        this.startYqDate = startYqDate;
    }

    public Date getStartYqDate() 
    {
        return startYqDate;
    }
    public void setKhDate(Date khDate) 
    {
        this.khDate = khDate;
    }

    public Date getKhDate() 
    {
        return khDate;
    }
    public void setTsFlag(String tsFlag) 
    {
        this.tsFlag = tsFlag;
    }

    public String getTsFlag() 
    {
        return tsFlag;
    }
    public void setCreditValue(BigDecimal creditValue) 
    {
        this.creditValue = creditValue;
    }

    public BigDecimal getCreditValue() 
    {
        return creditValue;
    }
    public void setTjFirm(String tjFirm) 
    {
        this.tjFirm = tjFirm;
    }

    public String getTjFirm() 
    {
        return tjFirm;
    }
    public void setTjCity(String tjCity) 
    {
        this.tjCity = tjCity;
    }

    public String getTjCity() 
    {
        return tjCity;
    }
    public void setTjWd(String tjWd) 
    {
        this.tjWd = tjWd;
    }

    public String getTjWd() 
    {
        return tjWd;
    }
    public void setShhxSxf(BigDecimal shhxSxf) 
    {
        this.shhxSxf = shhxSxf;
    }

    public BigDecimal getShhxSxf() 
    {
        return shhxSxf;
    }
    public void setShhxBj(BigDecimal shhxBj) 
    {
        this.shhxBj = shhxBj;
    }

    public BigDecimal getShhxBj() 
    {
        return shhxBj;
    }
    public void setShhxZnf(BigDecimal shhxZnf) 
    {
        this.shhxZnf = shhxZnf;
    }

    public BigDecimal getShhxZnf() 
    {
        return shhxZnf;
    }
    public void setShhxFy(BigDecimal shhxFy) 
    {
        this.shhxFy = shhxFy;
    }

    public BigDecimal getShhxFy() 
    {
        return shhxFy;
    }
    public void setShhxYqx(BigDecimal shhxYqx) 
    {
        this.shhxYqx = shhxYqx;
    }

    public BigDecimal getShhxYqx() 
    {
        return shhxYqx;
    }
    public void setShhxJehj(BigDecimal shhxJehj) 
    {
        this.shhxJehj = shhxJehj;
    }

    public BigDecimal getShhxJehj() 
    {
        return shhxJehj;
    }
    public void setZh30Day(String zh30Day) 
    {
        this.zh30Day = zh30Day;
    }

    public String getZh30Day() 
    {
        return zh30Day;
    }
    public void setZhXDay(String zhXDay) 
    {
        this.zhXDay = zhXDay;
    }

    public String getZhXDay() 
    {
        return zhXDay;
    }
    public void setIsZc(String isZc) 
    {
        this.isZc = isZc;
    }

    public String getIsZc() 
    {
        return isZc;
    }
    public void setIsZwcz(String isZwcz) 
    {
        this.isZwcz = isZwcz;
    }

    public String getIsZwcz() 
    {
        return isZwcz;
    }
    public void setIsJx(String isJx) 
    {
        this.isJx = isJx;
    }

    public String getIsJx() 
    {
        return isJx;
    }
    public void setIsSx(String isSx) 
    {
        this.isSx = isSx;
    }

    public String getIsSx() 
    {
        return isSx;
    }
    public void setZjyqMonth(Date zjyqMonth) 
    {
        this.zjyqMonth = zjyqMonth;
    }

    public Date getZjyqMonth() 
    {
        return zjyqMonth;
    }
    public void setHxSxfcs(String hxSxfcs) 
    {
        this.hxSxfcs = hxSxfcs;
    }

    public String getHxSxfcs() 
    {
        return hxSxfcs;
    }
    public void setHxShzt(String hxShzt) 
    {
        this.hxShzt = hxShzt;
    }

    public String getHxShzt() 
    {
        return hxShzt;
    }
    public void setHxBjcs(String hxBjcs) 
    {
        this.hxBjcs = hxBjcs;
    }

    public String getHxBjcs() 
    {
        return hxBjcs;
    }
    public void setHxZnfcs(String hxZnfcs) 
    {
        this.hxZnfcs = hxZnfcs;
    }

    public String getHxZnfcs() 
    {
        return hxZnfcs;
    }
    public void setHxFycs(String hxFycs) 
    {
        this.hxFycs = hxFycs;
    }

    public String getHxFycs() 
    {
        return hxFycs;
    }
    public void setHxYqlxcs(String hxYqlxcs) 
    {
        this.hxYqlxcs = hxYqlxcs;
    }

    public String getHxYqlxcs() 
    {
        return hxYqlxcs;
    }
    public void setHxJehjcs(String hxJehjcs) 
    {
        this.hxJehjcs = hxJehjcs;
    }

    public String getHxJehjcs() 
    {
        return hxJehjcs;
    }
    public void setAjHzDate(Date ajHzDate) 
    {
        this.ajHzDate = ajHzDate;
    }

    public Date getAjHzDate() 
    {
        return ajHzDate;
    }
    public void setXfjzzh(String xfjzzh) 
    {
        this.xfjzzh = xfjzzh;
    }

    public String getXfjzzh() 
    {
        return xfjzzh;
    }
    public void setTsajType(String tsajType) 
    {
        this.tsajType = tsajType;
    }

    public String getTsajType() 
    {
        return tsajType;
    }
    public void setDayRates(String dayRates) 
    {
        this.dayRates = dayRates;
    }

    public String getDayRates() 
    {
        return dayRates;
    }
    public void setTjDate(Date tjDate) 
    {
        this.tjDate = tjDate;
    }

    public Date getTjDate() 
    {
        return tjDate;
    }
    public void setDollarYe(BigDecimal dollarYe) 
    {
        this.dollarYe = dollarYe;
    }

    public BigDecimal getDollarYe() 
    {
        return dollarYe;
    }
    public void setDollarCd(String dollarCd) 
    {
        this.dollarCd = dollarCd;
    }

    public String getDollarCd() 
    {
        return dollarCd;
    }
    public void setDollarZdyjkje(BigDecimal dollarZdyjkje) 
    {
        this.dollarZdyjkje = dollarZdyjkje;
    }

    public BigDecimal getDollarZdyjkje() 
    {
        return dollarZdyjkje;
    }
    public void setDollarZhycjkr(Date dollarZhycjkr) 
    {
        this.dollarZhycjkr = dollarZhycjkr;
    }

    public Date getDollarZhycjkr() 
    {
        return dollarZhycjkr;
    }
    public void setDollarZhycjkje(BigDecimal dollarZhycjkje) 
    {
        this.dollarZhycjkje = dollarZhycjkje;
    }

    public BigDecimal getDollarZhycjkje() 
    {
        return dollarZhycjkje;
    }
    public void setDollarHkrhkbs(String dollarHkrhkbs) 
    {
        this.dollarHkrhkbs = dollarHkrhkbs;
    }

    public String getDollarHkrhkbs() 
    {
        return dollarHkrhkbs;
    }
    public void setHkfs(String hkfs) 
    {
        this.hkfs = hkfs;
    }

    public String getHkfs() 
    {
        return hkfs;
    }
    public void setHkqd(String hkqd) 
    {
        this.hkqd = hkqd;
    }

    public String getHkqd() 
    {
        return hkqd;
    }
    public void setXwpf(String xwpf) 
    {
        this.xwpf = xwpf;
    }

    public String getXwpf() 
    {
        return xwpf;
    }
    public void setAccountDate(String accountDate)
    {
        this.accountDate = accountDate;
    }

    public String getAccountDate()
    {
        return accountDate;
    }
    public void setAccountYebj(BigDecimal accountYebj) 
    {
        this.accountYebj = accountYebj;
    }

    public BigDecimal getAccountYebj() 
    {
        return accountYebj;
    }
    public void setAccountJqrq(Date accountJqrq) 
    {
        this.accountJqrq = accountJqrq;
    }

    public Date getAccountJqrq() 
    {
        return accountJqrq;
    }
    public void setFzzb(String fzzb) 
    {
        this.fzzb = fzzb;
    }

    public String getFzzb() 
    {
        return fzzb;
    }
    public void setRevertCardNo(String revertCardNo) 
    {
        this.revertCardNo = revertCardNo;
    }

    public String getRevertCardNo() 
    {
        return revertCardNo;
    }
    public void setRevertCardBlank(String revertCardBlank) 
    {
        this.revertCardBlank = revertCardBlank;
    }

    public String getRevertCardBlank() 
    {
        return revertCardBlank;
    }
    public void setJjqd(String jjqd) 
    {
        this.jjqd = jjqd;
    }

    public String getJjqd() 
    {
        return jjqd;
    }
    public void setOverdueDays(String overdueDays) 
    {
        this.overdueDays = overdueDays;
    }

    public String getOverdueDays() 
    {
        return overdueDays;
    }
    public void setOverdueFlag(String overdueFlag) 
    {
        this.overdueFlag = overdueFlag;
    }

    public String getOverdueFlag() 
    {
        return overdueFlag;
    }
    public void setCwcs(String cwcs) 
    {
        this.cwcs = cwcs;
    }

    public String getCwcs() 
    {
        return cwcs;
    }
    public void setQuotaProduct(String quotaProduct) 
    {
        this.quotaProduct = quotaProduct;
    }

    public String getQuotaProduct() 
    {
        return quotaProduct;
    }
    public void setQuotaCode(String quotaCode) 
    {
        this.quotaCode = quotaCode;
    }

    public String getQuotaCode() 
    {
        return quotaCode;
    }
    public void setQuotaDate(Date quotaDate) 
    {
        this.quotaDate = quotaDate;
    }

    public Date getQuotaDate() 
    {
        return quotaDate;
    }
    public void setRisk(String risk) 
    {
        this.risk = risk;
    }

    public String getRisk() 
    {
        return risk;
    }
    public void setFirstFkDate(Date firstFkDate) 
    {
        this.firstFkDate = firstFkDate;
    }

    public Date getFirstFkDate() 
    {
        return firstFkDate;
    }
    public void setFirstYqDate(Date firstYqDate) 
    {
        this.firstYqDate = firstYqDate;
    }

    public Date getFirstYqDate() 
    {
        return firstYqDate;
    }
    public void setFirstYqjcDate(Date firstYqjcDate) 
    {
        this.firstYqjcDate = firstYqjcDate;
    }

    public Date getFirstYqjcDate() 
    {
        return firstYqjcDate;
    }
    public void setFirstYqFlag(String firstYqFlag) 
    {
        this.firstYqFlag = firstYqFlag;
    }

    public String getFirstYqFlag() 
    {
        return firstYqFlag;
    }
    public void setCurName(String curName) 
    {
        this.curName = curName;
    }

    public String getCurName() 
    {
        return curName;
    }
    public void setCurSex(String curSex) 
    {
        this.curSex = curSex;
    }

    public String getCurSex() 
    {
        return curSex;
    }
    public void setCertificateNo(String certificateNo) 
    {
        this.certificateNo = certificateNo;
    }

    public String getCertificateNo() 
    {
        return certificateNo;
    }
    public void setCertificateType(String certificateType) 
    {
        this.certificateType = certificateType;
    }

    public String getCertificateType() 
    {
        return certificateType;
    }
    public void setCertificateAddress(String certificateAddress) 
    {
        this.certificateAddress = certificateAddress;
    }

    public String getCertificateAddress() 
    {
        return certificateAddress;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setRegistAddress(String registAddress) 
    {
        this.registAddress = registAddress;
    }

    public String getRegistAddress() 
    {
        return registAddress;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setMarriage(String marriage) 
    {
        this.marriage = marriage;
    }

    public String getMarriage() 
    {
        return marriage;
    }
    public void setEducation(String education) 
    {
        this.education = education;
    }

    public String getEducation() 
    {
        return education;
    }
    public void setEconomy(String economy) 
    {
        this.economy = economy;
    }

    public String getEconomy() 
    {
        return economy;
    }
    public void setCurIncome(BigDecimal curIncome) 
    {
        this.curIncome = curIncome;
    }

    public BigDecimal getCurIncome() 
    {
        return curIncome;
    }
    public void setNewAddress(String newAddress) 
    {
        this.newAddress = newAddress;
    }

    public String getNewAddress() 
    {
        return newAddress;
    }
    public void setCustomerNo(String customerNo) 
    {
        this.customerNo = customerNo;
    }

    public String getCustomerNo() 
    {
        return customerNo;
    }
    public void setCustomerMobile(String customerMobile) 
    {
        this.customerMobile = customerMobile;
    }

    public String getCustomerMobile() 
    {
        return customerMobile;
    }
    public void setCustomerHomeTel(String customerHomeTel) 
    {
        this.customerHomeTel = customerHomeTel;
    }

    public String getCustomerHomeTel() 
    {
        return customerHomeTel;
    }
    public void setCustomerHomeAddress(String customerHomeAddress) 
    {
        this.customerHomeAddress = customerHomeAddress;
    }

    public String getCustomerHomeAddress() 
    {
        return customerHomeAddress;
    }
    public void setCustomerHomePostcode(String customerHomePostcode) 
    {
        this.customerHomePostcode = customerHomePostcode;
    }

    public String getCustomerHomePostcode() 
    {
        return customerHomePostcode;
    }
    public void setCardPostAddress(String cardPostAddress) 
    {
        this.cardPostAddress = cardPostAddress;
    }

    public String getCardPostAddress() 
    {
        return cardPostAddress;
    }
    public void setJob(String job) 
    {
        this.job = job;
    }

    public String getJob() 
    {
        return job;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setIndust(String indust) 
    {
        this.indust = indust;
    }

    public String getIndust() 
    {
        return indust;
    }
    public void setWorkName(String workName) 
    {
        this.workName = workName;
    }

    public String getWorkName() 
    {
        return workName;
    }
    public void setWorkAddress(String workAddress) 
    {
        this.workAddress = workAddress;
    }

    public String getWorkAddress() 
    {
        return workAddress;
    }
    public void setWorkTel(String workTel) 
    {
        this.workTel = workTel;
    }

    public String getWorkTel() 
    {
        return workTel;
    }
    public void setWorkPostcode(String workPostcode) 
    {
        this.workPostcode = workPostcode;
    }

    public String getWorkPostcode() 
    {
        return workPostcode;
    }
    public void setFirstLiaisonName(String firstLiaisonName) 
    {
        this.firstLiaisonName = firstLiaisonName;
    }

    public String getFirstLiaisonName() 
    {
        return firstLiaisonName;
    }
    public void setFirstLiaisonRelation(String firstLiaisonRelation) 
    {
        this.firstLiaisonRelation = firstLiaisonRelation;
    }

    public String getFirstLiaisonRelation() 
    {
        return firstLiaisonRelation;
    }
    public void setFirstLiaisonMobile(String firstLiaisonMobile) 
    {
        this.firstLiaisonMobile = firstLiaisonMobile;
    }

    public String getFirstLiaisonMobile() 
    {
        return firstLiaisonMobile;
    }
    public void setFirstLiaisonTel(String firstLiaisonTel) 
    {
        this.firstLiaisonTel = firstLiaisonTel;
    }

    public String getFirstLiaisonTel() 
    {
        return firstLiaisonTel;
    }
    public void setSecondLiaisonName(String secondLiaisonName) 
    {
        this.secondLiaisonName = secondLiaisonName;
    }

    public String getSecondLiaisonName() 
    {
        return secondLiaisonName;
    }
    public void setSecondLiaisonRelation(String secondLiaisonRelation) 
    {
        this.secondLiaisonRelation = secondLiaisonRelation;
    }

    public String getSecondLiaisonRelation() 
    {
        return secondLiaisonRelation;
    }
    public void setSecondLiaisonMobile(String secondLiaisonMobile) 
    {
        this.secondLiaisonMobile = secondLiaisonMobile;
    }

    public String getSecondLiaisonMobile() 
    {
        return secondLiaisonMobile;
    }
    public void setSecondLiaisonTel(String secondLiaisonTel) 
    {
        this.secondLiaisonTel = secondLiaisonTel;
    }

    public String getSecondLiaisonTel() 
    {
        return secondLiaisonTel;
    }
    public void setThreeLiaisonName(String threeLiaisonName) 
    {
        this.threeLiaisonName = threeLiaisonName;
    }

    public String getThreeLiaisonName() 
    {
        return threeLiaisonName;
    }
    public void setThreeLiaisonRelation(String threeLiaisonRelation) 
    {
        this.threeLiaisonRelation = threeLiaisonRelation;
    }

    public String getThreeLiaisonRelation() 
    {
        return threeLiaisonRelation;
    }
    public void setThreeLiaisonMobile(String threeLiaisonMobile) 
    {
        this.threeLiaisonMobile = threeLiaisonMobile;
    }

    public String getThreeLiaisonMobile() 
    {
        return threeLiaisonMobile;
    }
    public void setThreeLiaisonTel(String threeLiaisonTel) 
    {
        this.threeLiaisonTel = threeLiaisonTel;
    }

    public String getThreeLiaisonTel() 
    {
        return threeLiaisonTel;
    }

    public Date getSjrq() {
        return sjrq;
    }

    public void setSjrq(Date sjrq) {
        this.sjrq = sjrq;
    }

    public String getHth() {
        return hth;
    }

    public void setHth(String hth) {
        this.hth = hth;
    }

    public String getJjh() {
        return jjh;
    }

    public void setJjh(String jjh) {
        this.jjh = jjh;
    }

    public String getDqsybYj() {
        return dqsybYj;
    }

    public void setDqsybYj(String dqsybYj) {
        this.dqsybYj = dqsybYj;
    }

    public String getDqsybEj() {
        return dqsybEj;
    }

    public void setDqsybEj(String dqsybEj) {
        this.dqsybEj = dqsybEj;
    }

    public String getCsjd() {
        return csjd;
    }

    public void setCsjd(String csjd) {
        this.csjd = csjd;
    }

    public String getWbjb() {
        return wbjb;
    }

    public void setWbjb(String wbjb) {
        this.wbjb = wbjb;
    }

    public String getYsKhjlmc() {
        return ysKhjlmc;
    }

    public void setYsKhjlmc(String ysKhjlmc) {
        this.ysKhjlmc = ysKhjlmc;
    }

    public String getKhjlmc() {
        return khjlmc;
    }

    public void setKhjlmc(String khjlmc) {
        this.khjlmc = khjlmc;
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public String getXfldycpmc() {
        return xfldycpmc;
    }

    public void setXfldycpmc(String xfldycpmc) {
        this.xfldycpmc = xfldycpmc;
    }

    public Date getQxrq() {
        return qxrq;
    }

    public void setQxrq(Date qxrq) {
        this.qxrq = qxrq;
    }

    public Date getDqrq() {
        return dqrq;
    }

    public void setDqrq(Date dqrq) {
        this.dqrq = dqrq;
    }

    public String getHkType() {
        return hkType;
    }

    public void setHkType(String hkType) {
        this.hkType = hkType;
    }

    public BigDecimal getDkye() {
        return dkye;
    }

    public void setDkye(BigDecimal dkye) {
        this.dkye = dkye;
    }

    public String getWbbs() {
        return wbbs;
    }

    public void setWbbs(String wbbs) {
        this.wbbs = wbbs;
    }

    public BigDecimal getYqje() {
        return yqje;
    }

    public void setYqje(BigDecimal yqje) {
        this.yqje = yqje;
    }

    public BigDecimal getYqbj() {
        return yqbj;
    }

    public void setYqbj(BigDecimal yqbj) {
        this.yqbj = yqbj;
    }

    public BigDecimal getYqlx() {
        return yqlx;
    }

    public void setYqlx(BigDecimal yqlx) {
        this.yqlx = yqlx;
    }

    public BigDecimal getYqsxf() {
        return yqsxf;
    }

    public void setYqsxf(BigDecimal yqsxf) {
        this.yqsxf = yqsxf;
    }

    public BigDecimal getZnj() {
        return znj;
    }

    public void setZnj(BigDecimal znj) {
        this.znj = znj;
    }

    public String getQdmc() {
        return qdmc;
    }

    public void setQdmc(String qdmc) {
        this.qdmc = qdmc;
    }

    public String getWbsb() {
        return wbsb;
    }

    public void setWbsb(String wbsb) {
        this.wbsb = wbsb;
    }

    public String getWbqs() {
        return wbqs;
    }

    public void setWbqs(String wbqs) {
        this.wbqs = wbqs;
    }

    public Date getFpsj() {
        return fpsj;
    }

    public void setFpsj(Date fpsj) {
        this.fpsj = fpsj;
    }

    public Date getAjhssj() {
        return ajhssj;
    }

    public void setAjhssj(Date ajhssj) {
        this.ajhssj = ajhssj;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


    public String getPackageFlag() {
        return packageFlag;
    }

    public void setPackageFlag(String packageFlag) {
        this.packageFlag = packageFlag;
    }

/////////////////////////////////////////////////////////////////////////////////////////////


    public BigDecimal getJyqtfy() {
        return jyqtfy;
    }

    public void setJyqtfy(BigDecimal jyqtfy) {
        this.jyqtfy = jyqtfy;
    }

    public BigDecimal getJylx() {
        return jylx;
    }

    public void setJylx(BigDecimal jylx) {
        this.jylx = jylx;
    }

    public BigDecimal getJybj() {
        return jybj;
    }

    public void setJybj(BigDecimal jybj) {
        this.jybj = jybj;
    }

    public BigDecimal getJyznf() {
        return jyznf;
    }

    public void setJyznf(BigDecimal jyznf) {
        this.jyznf = jyznf;
    }

    public String getJyType() {
        return jyType;
    }

    public void setJyType(String jyType) {
        this.jyType = jyType;
    }

    public BigDecimal getJyje() {
        return jyje;
    }

    public void setJyje(BigDecimal jyje) {
        this.jyje = jyje;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getCsr() {
        return csr;
    }

    public void setCsr(String csr) {
        this.csr = csr;
    }

    public Date getFprq() {
        return fprq;
    }

    public void setFprq(Date fprq) {
        this.fprq = fprq;
    }

    public Date getWarq() {
        return warq;
    }

    public void setWarq(Date warq) {
        this.warq = warq;
    }

    public String getKhjlxm() {
        return khjlxm;
    }

    public void setKhjlxm(String khjlxm) {
        this.khjlxm = khjlxm;
    }

    public String getSfwbcs() {
        return sfwbcs;
    }

    public void setSfwbcs(String sfwbcs) {
        this.sfwbcs = sfwbcs;
    }

    public String getSfjq() {
        return sfjq;
    }

    public void setSfjq(String sfjq) {
        this.sfjq = sfjq;
    }

    public String getBywa() {
        return bywa;
    }

    public void setBywa(String bywa) {
        this.bywa = bywa;
    }

    public Date getAjhsrq() {
        return ajhsrq;
    }

    public void setAjhsrq(Date ajhsrq) {
        this.ajhsrq = ajhsrq;
    }

    public String getXfjrzh() {
        return xfjrzh;
    }

    public void setXfjrzh(String xfjrzh) {
        this.xfjrzh = xfjrzh;
    }

    public String getTzsx() {
        return tzsx;
    }

    public void setTzsx(String tzsx) {
        this.tzsx = tzsx;
    }

    public BigDecimal getTzje() {
        return tzje;
    }

    public void setTzje(BigDecimal tzje) {
        this.tzje = tzje;
    }

    public String getZhzt() {
        return zhzt;
    }

    public void setZhzt(String zhzt) {
        this.zhzt = zhzt;
    }

    public Date getHkrq() {
        return hkrq;
    }

    public void setHkrq(Date hkrq) {
        this.hkrq = hkrq;
    }

    public String getHksyqqs() {
        return hksyqqs;
    }

    public void setHksyqqs(String hksyqqs) {
        this.hksyqqs = hksyqqs;
    }

    public BigDecimal getHkje() {
        return hkje;
    }

    public void setHkje(BigDecimal hkje) {
        this.hkje = hkje;
    }

    public String getYqcplx() {
        return yqcplx;
    }

    public void setYqcplx(String yqcplx) {
        this.yqcplx = yqcplx;
    }

    public String getYqjd() {
        return yqjd;
    }

    public void setYqjd(String yqjd) {
        this.yqjd = yqjd;
    }

    public String getJazt() {
        return jazt;
    }

    public void setJazt(String jazt) {
        this.jazt = jazt;
    }



//////////////////////////////////////////////


    public String getJjh2() {
        return jjh2;
    }

    public void setJjh2(String jjh2) {
        this.jjh2 = jjh2;
    }

    public String getCsjd2() {
        return csjd2;
    }

    public void setCsjd2(String csjd2) {
        this.csjd2 = csjd2;
    }

    public String getAreaCenter2() {
        return areaCenter2;
    }

    public void setAreaCenter2(String areaCenter2) {
        this.areaCenter2 = areaCenter2;
    }

    public String getAcceptCity2() {
        return acceptCity2;
    }

    public void setAcceptCity2(String acceptCity2) {
        this.acceptCity2 = acceptCity2;
    }

    public String getHth2() {
        return hth2;
    }

    public void setHth2(String hth2) {
        this.hth2 = hth2;
    }

    public String getDqsybYj2() {
        return dqsybYj2;
    }

    public void setDqsybYj2(String dqsybYj2) {
        this.dqsybYj2 = dqsybYj2;
    }

    public String getDqsybEj2() {
        return dqsybEj2;
    }

    public void setDqsybEj2(String dqsybEj2) {
        this.dqsybEj2 = dqsybEj2;
    }

    public String getWbqs2() {
        return wbqs2;
    }

    public void setWbqs2(String wbqs2) {
        this.wbqs2 = wbqs2;
    }

    public String getWbjb2() {
        return wbjb2;
    }

    public void setWbjb2(String wbjb2) {
        this.wbjb2 = wbjb2;
    }

    public String getCurName2() {
        return curName2;
    }

    public void setCurName2(String curName2) {
        this.curName2 = curName2;
    }

    public Date getSjrq2() {
        return sjrq2;
    }

    public void setSjrq2(Date sjrq2) {
        this.sjrq2 = sjrq2;
    }

    public String getQuotaProduct2() {
        return quotaProduct2;
    }

    public void setQuotaProduct2(String quotaProduct2) {
        this.quotaProduct2 = quotaProduct2;
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy2() {
        return createBy2;
    }

    public void setCreateBy2(String createBy2) {
        this.createBy2 = createBy2;
    }

    public Date getCreateTime2() {
        return createTime2;
    }

    public void setCreateTime2(Date createTime2) {
        this.createTime2 = createTime2;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate2() {
        return startDate2;
    }

    public void setStartDate2(Date startDate2) {
        this.startDate2 = startDate2;
    }

    public Date getEndDate2() {
        return endDate2;
    }

    public void setEndDate2(Date endDate2) {
        this.endDate2 = endDate2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orgCasno", getOrgCasno())
                .append("org", getOrg())
                .append("transfertype", getTransfertype())
                .append("twentyfourcd", getTwentyfourcd())
                .append("blk", getBlk())
                .append("productline", getProductline())
                .append("rmbLastJkje", getRmbLastJkje())
                .append("rmbYe", getRmbYe())
                .append("rmbYhfxzje", getRmbYhfxzje())
                .append("rmbCd", getRmbCd())
                .append("rmbZhycjkr", getRmbZhycjkr())
                .append("rmbZhhkbs", getRmbZhhkbs())
                .append("rmbYhlizje", getRmbYhlizje())
                .append("rmbYhbj1", getRmbYhbj1())
                .append("rmbYhbj2", getRmbYhbj2())
                .append("rmbYhbjzje", getRmbYhbjzje())
                .append("rmbYhfx1", getRmbYhfx1())
                .append("rmbYhfx2", getRmbYhfx2())
                .append("rmbYhfy1", getRmbYhfy1())
                .append("rmbYhfy2", getRmbYhfy2())
                .append("rmbYhfyzje", getRmbYhfyzje())
                .append("rmbZdyhje", getRmbZdyhje())
                .append("rmbQkzje", getRmbQkzje())
                .append("rmbGded", getRmbGded())
                .append("code", getCode())
                .append("borrowNo", getBorrowNo())
                .append("borrowBlank", getBorrowBlank())
                .append("borrowEd", getBorrowEd())
                .append("zxType", getZxType())
                .append("stopCard", getStopCard())
                .append("stopJxrq", getStopJxrq())
                .append("csCompany", getCsCompany())
                .append("rcr", getRcr())
                .append("gzFlag", getGzFlag())
                .append("fz", getFz())
                .append("fqFlag", getFqFlag())
                .append("syhxQtfy", getSyhxQtfy())
                .append("syhxSxf", getSyhxSxf())
                .append("syhxBj", getSyhxBj())
                .append("syhxZnf", getSyhxZnf())
                .append("syhxYqx", getSyhxYqx())
                .append("syhxJehj", getSyhxJehj())
                .append("areaCenter", getAreaCenter())
                .append("csRemarkHis", getCsRemarkHis())
                .append("maxYqtsHis", getMaxYqtsHis())
                .append("sumYqtsHis", getSumYqtsHis())
                .append("sumYqcsHis", getSumYqcsHis())
                .append("csjggsHis", getCsjggsHis())
                .append("qxFlag", getQxFlag())
                .append("stCompany", getStCompany())
                .append("acceptFirm", getAcceptFirm())
                .append("acceptCity", getAcceptCity())
                .append("acceptWd", getAcceptWd())
                .append("acceptWdCode", getAcceptWdCode())
                .append("dzhxrq", getDzhxrq())
                .append("remark", getRemark())
                .append("wbYhfxze", getWbYhfxze())
                .append("wbYhlxze", getWbYhlxze())
                .append("wbYhbj1", getWbYhbj1())
                .append("wbYhbj2", getWbYhbj2())
                .append("wbYhbjzje", getWbYhbjzje())
                .append("wbYhfx1", getWbYhfx1())
                .append("wbYhfx2", getWbYhfx2())
                .append("wbYhfy1", getWbYhfy1())
                .append("wbYhfy2", getWbYhfy2())
                .append("wbYhfyze", getWbYhfyze())
                .append("wbZdyhe", getWbZdyhe())
                .append("wbQkzje", getWbQkzje())
                .append("txFlag", getTxFlag())
                .append("wwCompanyCode", getWwCompanyCode())
                .append("wwCityName", getWwCityName())
                .append("wwJhEnddate", getWwJhEnddate())
                .append("wwQsrq", getWwQsrq())
                .append("wwPc", getWwPc())
                .append("waYe", getWaYe())
                .append("billAddress", getBillAddress())
                .append("billAddressPostcode", getBillAddressPostcode())
                .append("yearRates", getYearRates())
                .append("yhkDate", getYhkDate())
                .append("startYqDate", getStartYqDate())
                .append("khDate", getKhDate())
                .append("tsFlag", getTsFlag())
                .append("creditValue", getCreditValue())
                .append("tjFirm", getTjFirm())
                .append("tjCity", getTjCity())
                .append("tjWd", getTjWd())
                .append("shhxSxf", getShhxSxf())
                .append("shhxBj", getShhxBj())
                .append("shhxZnf", getShhxZnf())
                .append("shhxFy", getShhxFy())
                .append("shhxYqx", getShhxYqx())
                .append("shhxJehj", getShhxJehj())
                .append("zh30Day", getZh30Day())
                .append("zhXDay", getZhXDay())
                .append("isZc", getIsZc())
                .append("isZwcz", getIsZwcz())
                .append("isJx", getIsJx())
                .append("isSx", getIsSx())
                .append("zjyqMonth", getZjyqMonth())
                .append("hxSxfcs", getHxSxfcs())
                .append("hxShzt", getHxShzt())
                .append("hxBjcs", getHxBjcs())
                .append("hxZnfcs", getHxZnfcs())
                .append("hxFycs", getHxFycs())
                .append("hxYqlxcs", getHxYqlxcs())
                .append("hxJehjcs", getHxJehjcs())
                .append("ajHzDate", getAjHzDate())
                .append("xfjzzh", getXfjzzh())
                .append("tsajType", getTsajType())
                .append("dayRates", getDayRates())
                .append("tjDate", getTjDate())
                .append("dollarYe", getDollarYe())
                .append("dollarCd", getDollarCd())
                .append("dollarZdyjkje", getDollarZdyjkje())
                .append("dollarZhycjkr", getDollarZhycjkr())
                .append("dollarZhycjkje", getDollarZhycjkje())
                .append("dollarHkrhkbs", getDollarHkrhkbs())
                .append("hkfs", getHkfs())
                .append("hkqd", getHkqd())
                .append("xwpf", getXwpf())
                .append("accountDate", getAccountDate())
                .append("accountYebj", getAccountYebj())
                .append("accountJqrq", getAccountJqrq())
                .append("fzzb", getFzzb())
                .append("revertCardNo", getRevertCardNo())
                .append("revertCardBlank", getRevertCardBlank())
                .append("jjqd", getJjqd())
                .append("overdueDays", getOverdueDays())
                .append("overdueFlag", getOverdueFlag())
                .append("cwcs", getCwcs())
                .append("quotaProduct", getQuotaProduct())
                .append("quotaCode", getQuotaCode())
                .append("quotaDate", getQuotaDate())
                .append("risk", getRisk())
                .append("firstFkDate", getFirstFkDate())
                .append("firstYqDate", getFirstYqDate())
                .append("firstYqjcDate", getFirstYqjcDate())
                .append("firstYqFlag", getFirstYqFlag())
                .append("curName", getCurName())
                .append("curSex", getCurSex())
                .append("certificateNo", getCertificateNo())
                .append("certificateType", getCertificateType())
                .append("certificateAddress", getCertificateAddress())
                .append("birthday", getBirthday())
                .append("registAddress", getRegistAddress())
                .append("email", getEmail())
                .append("city", getCity())
                .append("marriage", getMarriage())
                .append("education", getEducation())
                .append("economy", getEconomy())
                .append("curIncome", getCurIncome())
                .append("newAddress", getNewAddress())
                .append("customerNo", getCustomerNo())
                .append("customerMobile", getCustomerMobile())
                .append("customerHomeTel", getCustomerHomeTel())
                .append("customerHomeAddress", getCustomerHomeAddress())
                .append("customerHomePostcode", getCustomerHomePostcode())
                .append("cardPostAddress", getCardPostAddress())
                .append("job", getJob())
                .append("deptName", getDeptName())
                .append("indust", getIndust())
                .append("workName", getWorkName())
                .append("workAddress", getWorkAddress())
                .append("workTel", getWorkTel())
                .append("workPostcode", getWorkPostcode())
                .append("firstLiaisonName", getFirstLiaisonName())
                .append("firstLiaisonRelation", getFirstLiaisonRelation())
                .append("firstLiaisonMobile", getFirstLiaisonMobile())
                .append("firstLiaisonTel", getFirstLiaisonTel())
                .append("secondLiaisonName", getSecondLiaisonName())
                .append("secondLiaisonRelation", getSecondLiaisonRelation())
                .append("secondLiaisonMobile", getSecondLiaisonMobile())
                .append("secondLiaisonTel", getSecondLiaisonTel())
                .append("threeLiaisonName", getThreeLiaisonName())
                .append("threeLiaisonRelation", getThreeLiaisonRelation())
                .append("threeLiaisonMobile", getThreeLiaisonMobile())
                .append("threeLiaisonTel", getThreeLiaisonTel())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("sjrq", getSjrq())
                .append("hth", getHth())
                .append("jjh", getJjh())
                .append("dqsybYj", getDqsybYj())
                .append("dqsybEj", getDqsybEj())
                .append("csjd", getCsjd())
                .append("wbjb", getWbjb())
                .append("ysKhjlmc", getYsKhjlmc())
                .append("khjlmc", getKhjlmc())
                .append("cpmc", getCpmc())
                .append("xfldycpmc", getXfldycpmc())
                .append("qxrq", getQxrq())
                .append("dqrq", getDqrq())
                .append("hkType", getHkType())
                .append("dkye", getDkye())
                .append("wbbs", getWbbs())
                .append("yqje", getYqje())
                .append("yqbj", getYqbj())
                .append("yqlx", getYqlx())
                .append("yqsxf", getYqsxf())
                .append("znj", getZnj())
                .append("qdmc", getQdmc())
                .append("wbsb", getWbsb())
                .append("wbqs", getWbqs())
                .append("fpsj", getFpsj())
                .append("ajhssj", getAjhssj())
                .toString();
    }

}
