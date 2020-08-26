package com.ruoyi.duncase.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户资产对象 cur_assets_package
 *
 * @author guozeqi
 * @date 2019-12-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Assets extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 机构案件号
     */
    @Excel(name = "机构案件号")
    private String orgCasno;

    /**
     * 所属机构
     */
    @Excel(name = "所属机构")
    private String org;

    /**
     * 机构id
     */
    private String orgId;

    /**
     * 手别
     */
    @Excel(name = "手别")
    private String transfertype;

    /**
     * 24CD值
     */
    @Excel(name = "24CD值")
    private String twentyfourcd;

    /**
     * BLK
     */
    @Excel(name = "BLK")
    private String blk;

    /**
     * 产品线
     */
    @Excel(name = "产品线")
    private String productline;

    /**
     * 人民币账户last缴款金额
     */
    @Excel(name = "人民币账户last缴款金额")
    private BigDecimal rmbLastJkje;

    /**
     * 人民币账户余额
     */
    @Excel(name = "人民币账户余额")
    private BigDecimal rmbYe;

    /**
     * 人民币账户应还罚息总额
     */
    @Excel(name = "人民币账户应还罚息总额")
    private BigDecimal rmbYhfxzje;

    /**
     * 人民币账户当前CD值
     */
    @Excel(name = "人民币账户当前CD值")
    private BigDecimal rmbCd;

    /**
     * 人民币账户最后一次缴款日
     */
    @Excel(name = "人民币账户最后一次缴款日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rmbZhycjkr;

    /**
     * 人民币账户最后还款笔数
     */
    @Excel(name = "人民币账户最后还款笔数")
    private String rmbZhhkbs;

    /**
     * 人民币账户应还利息总额
     */
    @Excel(name = "人民币账户应还利息总额")
    private BigDecimal rmbYhlizje;

    /**
     * 人民币账户应还本金1
     */
    @Excel(name = "人民币账户应还本金1")
    private BigDecimal rmbYhbj1;

    /**
     * 人民币账户应还本金2
     */
    @Excel(name = "人民币账户应还本金2")
    private BigDecimal rmbYhbj2;

    /**
     * 人民币账户应还本金总额
     */
    @Excel(name = "人民币账户应还本金总额")
    private BigDecimal rmbYhbjzje;

    /**
     * 人民币账户应还罚息1
     */
    @Excel(name = "人民币账户应还罚息1")
    private BigDecimal rmbYhfx1;

    /**
     * 人民币账户应还罚息2
     */
    @Excel(name = "人民币账户应还罚息2")
    private BigDecimal rmbYhfx2;

    /**
     * 人民币账户应还费用1
     */
    @Excel(name = "人民币账户应还费用1")
    private BigDecimal rmbYhfy1;

    /**
     * 人民币账户应还费用2
     */
    @Excel(name = "人民币账户应还费用2")
    private BigDecimal rmbYhfy2;

    /**
     * 人民币账户应还费用总额
     */
    @Excel(name = "人民币账户应还费用总额")
    private BigDecimal rmbYhfyzje;

    /**
     * 人民币账户最低应还金额
     */
    @Excel(name = "人民币账户最低应还金额")
    private BigDecimal rmbZdyhje;

    /**
     * 人民币账户欠款总金额
     */
    @Excel(name = "人民币账户欠款总金额")
    private BigDecimal rmbQkzje;

    /**
     * 人民币账户额度固定额度
     */
    @Excel(name = "人民币账户额度固定额度")
    private BigDecimal rmbGded;

    /**
     * 代码
     */
    @Excel(name = "代码")
    private String code;

    /**
     * 借款卡号
     */
    @Excel(name = "借款卡号")
    private String borrowNo;

    /**
     * 借款卡银行
     */
    @Excel(name = "借款卡银行")
    private String borrowBlank;

    /**
     * 借款额度
     */
    @Excel(name = "借款额度")
    private BigDecimal borrowEd;

    /**
     * 债项类型
     */
    @Excel(name = "债项类型")
    private String zxType;

    /**
     * 停卡日
     */
    @Excel(name = "停卡日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date stopCard;

    /**
     * 停止计息日期
     */
    @Excel(name = "停止计息日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date stopJxrq;

    /**
     * 催收公司
     */
    @Excel(name = "催收公司")
    private String csCompany;

    /**
     * 入催日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入催日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rcr;

    /**
     * 共债标识
     */
    @Excel(name = "共债标识")
    private String gzFlag;

    /**
     * 分值
     */
    @Excel(name = "分值")
    private String fz;

    /**
     * 分期标识
     */
    @Excel(name = "分期标识")
    private String fqFlag;

    /**
     * 剩余核销其他费用
     */
    @Excel(name = "剩余核销其他费用")
    private BigDecimal syhxQtfy;

    /**
     * 剩余核销手续费
     */
    @Excel(name = "剩余核销手续费")
    private BigDecimal syhxSxf;

    /**
     * 剩余核销本金
     */
    @Excel(name = "剩余核销本金")
    private BigDecimal syhxBj;

    /**
     * 剩余核销滞纳费
     */
    @Excel(name = "剩余核销滞纳费")
    private BigDecimal syhxZnf;

    /**
     * 剩余核销逾期息
     */
    @Excel(name = "剩余核销逾期息")
    private BigDecimal syhxYqx;

    /**
     * 剩余核销金额合计
     */
    @Excel(name = "剩余核销金额合计")
    private BigDecimal syhxJehj;

    /**
     * 区域中心
     */
    @Excel(name = "区域中心")
    private String areaCenter;

    /**
     * 历史催收备注
     */
    @Excel(name = "历史催收备注")
    private String csRemarkHis;

    /**
     * 历史最大逾期天数
     */
    @Excel(name = "历史最大逾期天数")
    private String maxYqtsHis;

    /**
     * 历史累计逾期天数
     */
    @Excel(name = "历史累计逾期天数")
    private Integer sumYqtsHis;

    /**
     * 历史累计逾期次数
     */
    @Excel(name = "历史累计逾期次数")
    private Integer sumYqcsHis;

    /**
     * 历史经历催收机构个数
     */
    @Excel(name = "历史经历催收机构个数")
    private String csjggsHis;

    /**
     * 取现标识
     */
    @Excel(name = "取现标识")
    private String qxFlag;

    /**
     * 受托公司
     */
    @Excel(name = "受托公司")
    private String stCompany;

    /**
     * 受理商户
     */
    @Excel(name = "受理商户")
    private String acceptFirm;

    /**
     * 受理城市
     */
    @Excel(name = "受理城市")
    private String acceptCity;

    /**
     * 受理网点
     */
    @Excel(name = "受理网点")
    private String acceptWd;

    /**
     * 受理网点代码
     */
    @Excel(name = "受理网点代码")
    private String acceptWdCode;

    /**
     * 外币账户应还罚息总额
     */
    @Excel(name = "外币账户应还罚息总额")
    private BigDecimal wbYhfxze;

    /**
     * 外币账户应还利息总额
     */
    @Excel(name = "外币账户应还利息总额")
    private BigDecimal wbYhlxze;

    /**
     * 外币账户应还本金1
     */
    @Excel(name = "外币账户应还本金1")
    private BigDecimal wbYhbj1;

    /**
     * 外币账户应还本金2
     */
    @Excel(name = "外币账户应还本金2")
    private BigDecimal wbYhbj2;

    /**
     * 外币账户应还本金总额
     */
    @Excel(name = "外币账户应还本金总额")
    private BigDecimal wbYhbjzje;

    /**
     * 外币账户应还罚息1
     */
    @Excel(name = "外币账户应还罚息1")
    private BigDecimal wbYhfx1;

    /**
     * 外币账户应还罚息2
     */
    @Excel(name = "外币账户应还罚息2")
    private BigDecimal wbYhfx2;

    /**
     * 外币账户应还费用1
     */
    @Excel(name = "外币账户应还费用1")
    private BigDecimal wbYhfy1;

    /**
     * 外币账户应还费用2
     */
    @Excel(name = "外币账户应还费用2")
    private BigDecimal wbYhfy2;

    /**
     * 外币账户应还费用总额
     */
    @Excel(name = "外币账户应还费用总额")
    private BigDecimal wbYhfyze;

    /**
     * 外币账户最低应还额
     */
    @Excel(name = "外币账户最低应还额")
    private BigDecimal wbZdyhe;

    /**
     * 外币账户欠款总金额
     */
    @Excel(name = "外币账户欠款总金额")
    private BigDecimal wbQkzje;

    /**
     * 套现标识
     */
    @Excel(name = "套现标识")
    private String txFlag;

    /**
     * 委外公司代码
     */
    @Excel(name = "委外公司代码")
    private String wwCompanyCode;

    /**
     * 委外城市名称
     */
    @Excel(name = "委外城市名称")
    private String wwCityName;

    /**
     * 委外计划截止日期
     */
    @Excel(name = "委外计划截止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date wwJhEnddate;

    /**
     * 委外起始日期
     */
    @Excel(name = "委外起始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date wwQsrq;

    /**
     * 委托批次
     */
    @Excel(name = "委托批次")
    private String wwPc;

    /**
     * 委案余额
     */
    @Excel(name = "委案余额")
    private BigDecimal waYe;

    /**
     * 帐单地址
     */
    @Excel(name = "帐单地址")
    private String billAddress;

    /**
     * 帐单地址邮编
     */
    @Excel(name = "帐单地址邮编")
    private String billAddressPostcode;

    /**
     * 年利率
     */
    @Excel(name = "年利率")
    private String yearRates;

    /**
     * 应还款日
     */
    @Excel(name = "应还款日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yhkDate;

    /**
     * 开始逾期日期
     */
    @Excel(name = "开始逾期日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startYqDate;

    /**
     * 开户日
     */
    @Excel(name = "开户日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date khDate;

    /**
     * 投诉标识
     */
    @Excel(name = "投诉标识")
    private String tsFlag;

    /**
     * 授信额度
     */
    @Excel(name = "授信额度")
    private BigDecimal creditValue;

    /**
     * 推荐商户
     */
    @Excel(name = "推荐商户")
    private String tjFirm;

    /**
     * 推荐城市
     */
    @Excel(name = "推荐城市")
    private String tjCity;

    /**
     * 推荐网点
     */
    @Excel(name = "推荐网点")
    private String tjWd;

    /**
     * 收回核销手续费
     */
    @Excel(name = "收回核销手续费")
    private BigDecimal shhxSxf;

    /**
     * 收回核销本金
     */
    @Excel(name = "收回核销本金")
    private BigDecimal shhxBj;

    /**
     * 收回核销滞纳费
     */
    @Excel(name = "收回核销滞纳费")
    private BigDecimal shhxZnf;

    /**
     * 收回核销费用
     */
    @Excel(name = "收回核销费用")
    private BigDecimal shhxFy;

    /**
     * 收回核销逾期息
     */
    @Excel(name = "收回核销逾期息")
    private BigDecimal shhxYqx;

    /**
     * 收回核销金额合计
     */
    @Excel(name = "收回核销金额合计")
    private BigDecimal shhxJehj;

    /**
     * 整合30 DAY
     */
    @Excel(name = "整合30 DAY")
    private String zh30Day;

    /**
     * 整合X DA
     */
    @Excel(name = "整合X DA")
    private String zhXDay;

    /**
     * 是否仲裁
     */
    @Excel(name = "是否仲裁")
    private String isZc;

    /**
     * 是否债务重组
     */
    @Excel(name = "是否债务重组")
    private String isZwcz;

    /**
     * 是否计息
     */
    @Excel(name = "是否计息")
    private String isJx;

    /**
     * 是否诉讼
     */
    @Excel(name = "是否诉讼")
    private String isSx;

    /**
     * 最近逾期月份
     */
    @Excel(name = "最近逾期月份", width = 30, dateFormat = "yyyy-MM-dd")
    private Date zjyqMonth;

    /**
     * 核销手续费催收
     */
    @Excel(name = "核销手续费催收")
    private String hxSxfcs;

    /**
     * 核销收回状态
     */
    @Excel(name = "核销收回状态")
    private String hxShzt;

    /**
     * 核销本金催收
     */
    @Excel(name = "核销本金催收")
    private String hxBjcs;

    /**
     * 核销滞纳费催收
     */
    @Excel(name = "核销滞纳费催收")
    private String hxZnfcs;

    /**
     * 核销费用催收
     */
    @Excel(name = "核销费用催收")
    private String hxFycs;

    /**
     * 核销逾期息催收
     */
    @Excel(name = "核销逾期息催收")
    private String hxYqlxcs;

    /**
     * 核销金额合计催收
     */
    @Excel(name = "核销金额合计催收")
    private String hxJehjcs;

    /**
     * 案件核准日期
     */
    @Excel(name = "案件核准日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ajHzDate;

    /**
     * 消费金融账号
     */
    @Excel(name = "消费金融账号")
    private String xfjzzh;

    /**
     * 特殊案件类型
     */
    @Excel(name = "特殊案件类型")
    private String tsajType;

    /**
     * 日利率
     */
    @Excel(name = "日利率")
    private String dayRates;

    /**
     * 统计日期
     */
    @Excel(name = "统计日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tjDate;

    /**
     * 美元余额
     */
    @Excel(name = "美元余额")
    private BigDecimal dollarYe;

    /**
     * 美元当前CD值
     */
    @Excel(name = "美元当前CD值")
    private String dollarCd;

    /**
     * 美元最低应缴款金额
     */
    @Excel(name = "美元最低应缴款金额")
    private BigDecimal dollarZdyjkje;

    /**
     * 美元最后一次缴款日
     */
    @Excel(name = "美元最后一次缴款日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dollarZhycjkr;

    /**
     * 美元最后一次缴款金额
     */
    @Excel(name = "美元最后一次缴款金额")
    private BigDecimal dollarZhycjkje;

    /**
     * 美元还款日还款笔数
     */
    @Excel(name = "美元还款日还款笔数")
    private String dollarHkrhkbs;

    /**
     * 获客方式
     */
    @Excel(name = "获客方式")
    private String hkfs;

    /**
     * 获客渠道
     */
    @Excel(name = "获客渠道")
    private String hkqd;

    /**
     * 行方评分
     */
    @Excel(name = "行方评分")
    private String xwpf;

    /**
     * 账单日
     */
    @Excel(name = "账单日")
    private String accountDate;

    /**
     * 账户余额本金
     */
    @Excel(name = "账户余额本金")
    private BigDecimal accountYebj;

    /**
     * 账户结清日期
     */
    @Excel(name = "账户结清日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date accountJqrq;

    /**
     * 辅助组别
     */
    @Excel(name = "辅助组别")
    private String fzzb;

    /**
     * 还款卡号
     */
    @Excel(name = "还款卡号")
    private String revertCardNo;

    /**
     * 还款卡银行
     */
    @Excel(name = "还款卡银行")
    private String revertCardBlank;

    /**
     * 进件渠道
     */
    @Excel(name = "进件渠道")
    private String jjqd;

    /**
     * 逾期天数
     */
    @Excel(name = "逾期天数")
    private String overdueDays;

    /**
     * 逾期标记
     */
    @Excel(name = "逾期标记")
    private String overdueFlag;

    /**
     * 长委次数
     */
    @Excel(name = "长委次数")
    private String cwcs;

    /**
     * 额度产品
     */
    @Excel(name = "额度产品")
    private String quotaProduct;

    /**
     * 额度代码
     */
    @Excel(name = "额度代码")
    private String quotaCode;

    /**
     * 额度激活日期
     */
    @Excel(name = "额度激活日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date quotaDate;

    /**
     * 风险
     */
    @Excel(name = "风险")
    private String risk;

    /**
     * 首次放款日期
     */
    @Excel(name = "首次放款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstFkDate;

    /**
     * 首次逾期日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "首次逾期日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstYqDate;

    /**
     * 首次逾期解除日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "首次逾期解除日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstYqjcDate;

    /**
     * 首逾标识
     */
    @Excel(name = "首逾标识")
    private String firstYqFlag;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称")
    private String curName;

    /**
     * 客户性别
     */
    @Excel(name = "客户性别")
    private String curSex;

    /**
     * 证件号码
     */
    @Excel(name = "证件号码")
    private String certificateNo;

    /**
     * 证件类型
     */
    @Excel(name = "证件类型")
    private String certificateType;

    /**
     * 身份证地址
     */
    @Excel(name = "身份证地址")
    private String certificateAddress;

    /**
     * 出生日期
     */
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 户籍地址
     */
    @Excel(name = "户籍地址")
    private String registAddress;

    /**
     * 电子邮箱
     */
    @Excel(name = "电子邮箱")
    private String email;

    /**
     * 城市
     */
    @Excel(name = "城市")
    private String city;

    /**
     * 婚姻状况
     */
    @Excel(name = "婚姻状况")
    private String marriage;

    /**
     * 教育程度
     */
    @Excel(name = "教育程度")
    private String education;

    /**
     * 经济类型
     */
    @Excel(name = "经济类型")
    private String economy;

    /**
     * 当前收入
     */
    @Excel(name = "当前收入")
    private BigDecimal curIncome;

    /**
     * 新增地址
     */
    @Excel(name = "新增地址")
    private String newAddress;

    /**
     * 客户号
     */
    @Excel(name = "客户号")
    private String customerNo;

    /**
     * 客户手机号码
     */
    @Excel(name = "客户手机号码")
    private String customerMobile;

    /**
     * 客户家庭电话
     */
    @Excel(name = "客户家庭电话")
    private String customerHomeTel;

    /**
     * 客户家庭地址
     */
    @Excel(name = "客户家庭地址")
    private String customerHomeAddress;

    /**
     * 客户家庭地址邮编
     */
    @Excel(name = "客户家庭地址邮编")
    private String customerHomePostcode;

    /**
     * 卡片寄送地址
     */
    @Excel(name = "卡片寄送地址")
    private String cardPostAddress;

    /**
     * 职务
     */
    @Excel(name = "职务")
    private String job;

    /**
     * 部门名称
     */
    @Excel(name = "部门名称")
    private String deptName;

    /**
     * 行业性质
     */
    @Excel(name = "行业性质")
    private String indust;

    /**
     * 客户单位名称
     */
    @Excel(name = "客户单位名称")
    private String workName;

    /**
     * 客户单位地址
     */
    @Excel(name = "客户单位地址")
    private String workAddress;

    /**
     * 单位电话
     */
    @Excel(name = "单位电话")
    private String workTel;

    /**
     * 单位邮编
     */
    @Excel(name = "单位邮编")
    private String workPostcode;

    /**
     * 第一联系人姓名
     */
    @Excel(name = "第一联系人姓名")
    private String firstLiaisonName;

    /**
     * 第一联系人与客户关系
     */
    @Excel(name = "第一联系人与客户关系")
    private String firstLiaisonRelation;

    /**
     * 第一联系人手机
     */
    @Excel(name = "第一联系人手机")
    private String firstLiaisonMobile;

    /**
     * 第一联系人电话
     */
    @Excel(name = "第一联系人电话")
    private String firstLiaisonTel;

    /**
     * 第二联系人姓名
     */
    @Excel(name = "第二联系人姓名")
    private String secondLiaisonName;

    /**
     * 第二联系人与客户关系
     */
    @Excel(name = "第二联系人与客户关系")
    private String secondLiaisonRelation;

    /**
     * 第二联系人手机
     */
    @Excel(name = "第二联系人手机")
    private String secondLiaisonMobile;

    /**
     * 第二联系人电话
     */
    @Excel(name = "第二联系人电话")
    private String secondLiaisonTel;

    /**
     * 第三联系人姓名
     */
    @Excel(name = "第三联系人姓名")
    private String threeLiaisonName;

    /**
     * 第三联系人与客户关系
     */
    @Excel(name = "第三联系人与客户关系")
    private String threeLiaisonRelation;

    /**
     * 第三联系人手机
     */
    @Excel(name = "第三联系人手机")
    private String threeLiaisonMobile;

    /**
     * 第三联系人电话
     */
    @Excel(name = "第三联系人电话")
    private String threeLiaisonTel;

    /**
     * 数据日期
     */
    @Excel(name = "数据日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sjrq;

    /**
     * 合同号
     */
    @Excel(name = "合同号")
    private String hth;

    /**
     * 借据号
     */
    @Excel(name = "借据号")
    private String jjh;

    /**
     * 地区事业部(一级)
     */
    @Excel(name = "地区事业部(一级)")
    private String dqsybYj;

    /**
     * 地区事业部(二级)
     */
    @Excel(name = "地区事业部(二级)")
    private String dqsybEj;

    /**
     * 催收节点
     */
    @Excel(name = "催收节点")
    private String csjd;

    /**
     * 外包经办
     */
    @Excel(name = "外包经办")
    private String wbjb;

    /**
     * 原始客户经理名称
     */
    @Excel(name = "原始客户经理名称")
    private String ysKhjlmc;

    /**
     * 客户经理名称
     */
    @Excel(name = "客户经理名称")
    private String khjlmc;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String cpmc;

    /**
     * 新分类对应产品名称
     */
    @Excel(name = "新分类对应产品名称")
    private String xfldycpmc;

    /**
     * 起息日期
     */
    @Excel(name = "起息日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date qxrq;

    /**
     * 到期日期
     */
    @Excel(name = "到期日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dqrq;

    /**
     * 还款方式
     */
    @Excel(name = "还款方式")
    private String hkType;

    /**
     * 贷款余额
     */
    @Excel(name = "贷款余额")
    private BigDecimal dkye;

    /**
     * 外包标的
     */
    @Excel(name = "外包标的")
    private String wbbs;

    /**
     * 逾期金额
     */
    @Excel(name = "逾期金额")
    private BigDecimal yqje;

    /**
     * 逾期本金
     */
    @Excel(name = "逾期本金")
    private BigDecimal yqbj;

    /**
     * 逾期利息
     */
    @Excel(name = "逾期利息")
    private BigDecimal yqlx;

    /**
     * 逾期手续费
     */
    @Excel(name = "逾期手续费")
    private BigDecimal yqsxf;

    /**
     * 滞纳金
     */
    @Excel(name = "滞纳金")
    private BigDecimal znj;

    /**
     * 渠道名称
     */
    @Excel(name = "渠道名称")
    private String qdmc;

    /**
     * 外包手别
     */
    @Excel(name = "外包手别")
    private String wbsb;

    /**
     * 外包期数
     */
    @Excel(name = "外包期数")
    private String wbqs;

    /**
     * 分配时间
     */
    @Excel(name = "分配时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fpsj;

    /**
     * 案件回收时间
     */
    @Excel(name = "案件回收时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ajhssj;

    /**
     * 导入批次号
     */
    private String importBatchNo;
    /**
     * 打包号
     */
    private String packNo;

    /**
     * 分发操作人id
     */
    private Long sendOptId;

    /**
     * 修改操作人id
     */
    private Long updateId;
    /**
     * 操作标识
     */
    private String operFlag;

//    /*退案日*/
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "退案日", width = 30, dateFormat = "yyyy-MM-dd")
//    private Date tar;
//    /*贷款类型*/
//    private String dklx;
//    /*留案标签*/
//    private String laFlag;
//    /*风险标签*/
//    private String fxFlag;
//    /*合同类型*/
//    private String htlx;
//    /*减免标签*/
//    private String jmbq;
//    /*法催标签*/
//    private String fcbq;
//    /*第四联系人*/
//    @Excel(name = "第4联系人姓名")
//    private String fourthLiaisonName;
//    @Excel(name = "第4联系人与客户关系")
//    private String fourthLiaisonRelation;
//    @Excel(name = "第4联系人手机")
//    private String customerMobile4;
//    @Excel(name = "第4联系人电话")
//    private String fourthLiaisonMobile;
//
//    /*第5联系人*/
//    @Excel(name = "第5联系人姓名")
//    private String fifthLiaisonName;
//    @Excel(name = "第5联系人与客户关系")
//    private String fifthLiaisonRelation;
//    @Excel(name = "第5联系人手机")
//    private String fifthLiaisonMobile;

    /** 账龄 */
    @Excel(name = "账龄")
    private String accountAge;

    /** 留案标签 */
    @Excel(name = "留案标签")
    private String laFlag;

    /** 风险标签 */
    @Excel(name = "风险标签")
    private String fxFlag;

    /** 合同类型 */
    @Excel(name = "合同类型")
    private String htlx;

    /** 减免标签 */
    @Excel(name = "减免标签")
    private String jmbq;

    /** 法催标签 */
    @Excel(name = "法催标签")
    private String fcbq;

    /** 罚息是否变化 */
    @Excel(name = "罚息是否变化")
    private String fxsfbh;

    /** 退案日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退案日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tar;

    /** 借款日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "借款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date jkrq;

    /** 最近一次还款日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近一次还款日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date zhychkr;

    /** 每期还款金额 */
    @Excel(name = "每期还款金额")
    private BigDecimal mqhkje;

    /** 当期欠款金额 */
    @Excel(name = "当期欠款金额")
    private BigDecimal dqqkje;

    /** 累计已还金额 */
    @Excel(name = "累计已还金额")
    private BigDecimal ljyhje;

    /** 首付金额 */
    @Excel(name = "首付金额")
    private BigDecimal sfje;

    /** 指定还款账号1 */
    @Excel(name = "指定还款账号1")
    private String zdhkzh1;

    /** 指定还款账号2 */
    @Excel(name = "指定还款账号2")
    private String zdhkzh2;

    /** 指定还款账户户名1 */
    @Excel(name = "指定还款账户户名1")
    private String zdhkzhhm1;

    /** 指定还款账户户名2 */
    @Excel(name = "指定还款账户户名2")
    private String zdhkzhhm2;

    /** 指定还款渠道1 */
    @Excel(name = "指定还款渠道1")
    private String zdhkqd1;

    /** 指定还款渠道2 */
    @Excel(name = "指定还款渠道2")
    private String zdhkqd2;

    /** 考核目标 */
    @Excel(name = "考核目标")
    private String khmb;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal spjg;

    /** 贷款类型 */
    @Excel(name = "贷款类型")
    private String dklx;

    /** 借款笔数 */
    @Excel(name = "借款笔数")
    private String jkbs;

    /** 商品信息 */
    @Excel(name = "商品信息")
    private String spxx;

    /** 委案次数 */
    @Excel(name = "委案次数")
    private String wacs;

    /** 已还期数 */
    @Excel(name = "已还期数")
    private String ykqs;

    /** 工作部门 */
    @Excel(name = "工作部门")
    private String workDept;

    /** 客户电话2 */
    @Excel(name = "客户电话2")
    private String customerMobile2;

    /** 客户电话3 */
    @Excel(name = "客户电话3")
    private String customerMobile3;

    /** 客户电话4 */
    @Excel(name = "客户电话4")
    private String customerMobile4;

    /** 联系人4姓名 */
    @Excel(name = "联系人4姓名")
    private String fourthLiaisonName;

    /** 联系人4关系 */
    @Excel(name = "联系人4关系")
    private String fourthLiaisonRelation;

    /** 联系人4电话 */
    @Excel(name = "联系人4电话")
    private String fourthLiaisonMobile;

    /** 联系人5姓名 */
    @Excel(name = "联系人5姓名")
    private String fifthLiaisonName;

    /** 联系人5关系 */
    @Excel(name = "联系人5关系")
    private String fifthLiaisonRelation;

    /** 联系人5电话 */
    @Excel(name = "联系人5电话")
    private String fifthLiaisonMobile;

    /**
     * 呆账核销日期
     */
    @Excel(name = "呆账核销日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dzhxrq;

    private String freeImport;


    //吉象新增字段
    private String curNo;//用户编号
    private String payStatus;//还款状态
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastLoanDate;//贷款到期日
    private BigDecimal lastRepayAmount;//最近还款金额

}
