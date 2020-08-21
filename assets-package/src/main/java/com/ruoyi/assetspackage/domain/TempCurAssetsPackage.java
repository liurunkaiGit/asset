package com.ruoyi.assetspackage.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Set;

/**
 * 客户资产对象 cur_assets_package
 *
 * @author guozeqi
 * @String 2019-12-25
 */
@Data
public class TempCurAssetsPackage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键")
    private String id;

    /** 机构案件号 */
    @Excel(name = "机构案件号")
    private String orgCasno;

    /** 所属机构 */
    @Excel(name = "所属机构")
    private String org;

    /** null */
    @Excel(name = "null")
    private String transfertype;

    /** 人民币账户余额 */
    @Excel(name = "人民币账户余额")
    private String rmbYe;

    /** 人民币账户应还罚息总额 */
    @Excel(name = "人民币账户应还罚息总额")
    private String rmbYhfxzje;

    /** 人民币账户应还利息总额 */
    @Excel(name = "人民币账户应还利息总额")
    private String rmbYhlizje;

    /** 人民币账户应还本金总额 */
    @Excel(name = "人民币账户应还本金总额")
    private String rmbYhbjzje;

    /** 人民币账户应还费用总额 */
    @Excel(name = "人民币账户应还费用总额")
    private String rmbYhfyzje;

    /** 人民币账户最低应还金额 */
    @Excel(name = "人民币账户最低应还金额")
    private String rmbZdyhje;

    /** 人民币账户欠款总金额 */
    @Excel(name = "人民币账户欠款总金额")
    private String rmbQkzje;

    /** null */
    @Excel(name = "null")
    private String borrowNo;

    /** 借款卡银行 */
    @Excel(name = "借款卡银行")
    private String borrowBlank;

    /** 借款额度 */
    @Excel(name = "借款额度")
    private String borrowEd;

    /** 入催日 */
//    @Excel(name = "入催日", width = 30, dateFormat = "yyyy-MM-dd")
    private String rcr;

    /** 分值 */
    @Excel(name = "分值")
    private String fz;

    /** 区域中心 */
    @Excel(name = "区域中心")
    private String areaCenter;

    /** 历史最大逾期天数 */
    @Excel(name = "历史最大逾期天数")
    private String maxYqtsHis;

    /** 历史累计逾期天数 */
    @Excel(name = "历史累计逾期天数")
    private String sumYqtsHis;

    /** 历史累计逾期次数 */
    @Excel(name = "历史累计逾期次数")
    private String sumYqcsHis;

    /** 委外城市名称 */
    @Excel(name = "委外城市名称")
    private String wwCityName;

    /** 委案余额 */
    @Excel(name = "委案余额")
    private String waYe;

    /** 帐单地址邮编 */
    @Excel(name = "帐单地址")
    private String billAddress;

    /** 年利率 */
    @Excel(name = "年利率")
    private String yearRates;

    /** null */
    @Excel(name = "null")
    private String tjFirm;

    /** null */
    @Excel(name = "null")
    private String tjWd;

    /** 日利率 */
    @Excel(name = "日利率")
    private String dayRates;

    /** 账单日 */
    @Excel(name = "账单日")
    private String accountDate;

    /** 逾期天数 */
    @Excel(name = "逾期天数")
    private String overdueDays;

    /** 首次逾期日期 */
//    @Excel(name = "首次逾期日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String firstYqDate;

    /** 首次逾期解除日期 */
//    @Excel(name = "首次逾期解除日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String firstYqjcDate;

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

    /** null */
    @Excel(name = "null")
    private String certificateAddress;

    /** null */
    @Excel(name = "null")
    private String registAddress;

    /** null */
    @Excel(name = "null")
    private String email;

    /** 婚姻状况 */
    @Excel(name = "婚姻状况")
    private String marriage;

    /** 教育程度 */
    @Excel(name = "教育程度")
    private String education;

    /** null */
    @Excel(name = "null")
    private String customerMobile;

    /** null */
    @Excel(name = "null")
    private String customerHomeTel;

    /** null */
    @Excel(name = "null")
    private String customerHomeAddress;

    /** null */
    @Excel(name = "null")
    private String workName;

    /** null */
    @Excel(name = "null")
    private String workAddress;

    /** null */
    @Excel(name = "null")
    private String workTel;

    /** 第一联系人姓名 */
    @Excel(name = "第一联系人姓名")
    private String firstLiaisonName;

    /** 第一联系人与客户关系 */
    @Excel(name = "第一联系人与客户关系")
    private String firstLiaisonRelation;

    /** null */
    @Excel(name = "null")
    private String firstLiaisonMobile;

    /** null */
    @Excel(name = "null")
    private String firstLiaisonTel;

    /** 第二联系人姓名 */
    @Excel(name = "第二联系人姓名")
    private String secondLiaisonName;

    /** 第二联系人与客户关系 */
    @Excel(name = "第二联系人与客户关系")
    private String secondLiaisonRelation;

    /** null */
    @Excel(name = "null")
    private String secondLiaisonMobile;

    /** null */
    @Excel(name = "null")
    private String secondLiaisonTel;

    /** 第三联系人姓名 */
    @Excel(name = "第三联系人姓名")
    private String threeLiaisonName;

    /** 第三联系人与客户关系 */
    @Excel(name = "第三联系人与客户关系")
    private String threeLiaisonRelation;

    /** null */
    @Excel(name = "null")
    private String threeLiaisonMobile;

    /** null */
    @Excel(name = "null")
    private String threeLiaisonTel;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String cpmc;

    /** 还款方式 */
    @Excel(name = "还款方式")
    private String hkType;

    /** 滞纳金 */
    @Excel(name = "滞纳金")
    private String znj;

    /**
     * 案件回收时间
     */
    @Excel(name = "案件回收时间", width = 30)
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private String ajhssj;


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
//    @Excel(name = "退案日", width = 30, dateFormat = "yyyy-MM-dd")
    private String tar;

    /** 借款日期 */
//    @Excel(name = "借款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String jkrq;

    /** 最近一次还款日 */
//    @Excel(name = "最近一次还款日", width = 30, dateFormat = "yyyy-MM-dd")
    private String zhychkr;

    /** 每期还款金额 */
    @Excel(name = "每期还款金额")
    private String mqhkje;

    /** 当期欠款金额 */
    @Excel(name = "当期欠款金额")
    private String dqqkje;

    /** 累计已还金额 */
    @Excel(name = "累计已还金额")
    private String ljyhje;

    /** 首付金额 */
    @Excel(name = "首付金额")
    private String sfje;

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
    private String spjg;

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

    private String freeImport;


    /**
     * 结案状态
     */
    @Excel(name = "结案状态")
    private String closeCase;

    /**
     * 是否出催
     */
    @Excel(name = "是否出催")
    private String isExitCollect;

    /**
     * 导入批次号，年月日时分秒生成
     */
    @Excel(name = "导入批次号，年月日时分秒生成")
    private String importBatchNo;

    /** 资产包id */
    @Excel(name = "资产包id")
    private String packageId;

    /**
     * 结案日期
     */
    private String closeCaseDate;

    /** 是否异常（0正常，1异常） */
    @Excel(name = "是否异常")
    private String isException;

    /**
     * 委托机构id
     */
    private String orgId;

    /**
     * 打包标识（0,已打包，1,未打包）
     */
    private String packageFlag;

    private String createBy;

    /**
     * 起始和终止委案余额，供查询使用
     */
    private String startWaYe;
    private String endWaYe;
    /**
     * 起始和终止导入时间，供查询使用
     */
    private String startDate;
    private String endDate;

    /**
     * 拥有查看数据权限的部门集合
     */
    private Set<Long> deptIds;

    //吉象新增字段
    private String curNo;//用户编号
    private String payStatus;//还款状态
    private String lastLoanDate;//贷款到期日
    private String lastRepayAmount;//最近还款金额

    //中银新增
    private String dzhxrq;//呆账核销日期

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }


}
