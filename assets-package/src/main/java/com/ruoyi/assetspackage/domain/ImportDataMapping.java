package com.ruoyi.assetspackage.domain;

import lombok.Data;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * @author guozeqi
 * @create 2019-12-26
 */
@Data
@Component
public class ImportDataMapping {
    private String orgCasNo;//机构案件号
    private String org;//所属机构
    private String transferType;//手别
    private String rmbYe;//人民币账户余额
    private String rmbYhfxzje;//人民币账户应还罚息总额
    private String rmbYhlizje;//人民币账户应还利息总额
    private String rmbYhbjzje;//人民币账户应还本金总额
    private String rmbYhfyzje;//人民币账户应还费用总额
    private String rmbZdyhje;//人民币账户最低应还金额
    private String rmbQkzje;//人民币账户欠款总金额
    private String borrowNo;//借款卡号
    private String borrowBlank;//借款卡银行
    private String borrowEd;//借款额度
    private String rcr;//入催日
    private String fz;//分值
    private String areaCenter;//区域中心
    private String maxYqtsHis;//历史最大逾期天数
    private String sumYqtsHis;//历史累计逾期天数
    private String sumYqcsHis;//历史累计逾期次数
    private String wwCityName;//委外城市名称
    private String waYe;//委案余额
    private String billAddress;//帐单地址
    private String yearRates;//年利率
    private String tjFirm;//
    private String tjWd;//
    private String dayRates;//日利率
    private String accountDate;//账单日
    private String overdueDays;//逾期天数
    private String firstYqDate;//首次逾期日期
    private String firstYqjcDate;//首次逾期解除日期
    private String firstYqFlag;//首逾标识
    private String curName;//客户名称
    private String curSex;//客户性别
    private String certificateNo;//证件号码
    private String certificateAddress;//
    private String registAddress;//
    private String email;//
    private String marriage;//婚姻状况
    private String education;//教育程度
    private String customerMobile;//
    private String customerHomeTel;//
    private String customerHomeAddress;//
    private String workName;//
    private String workAddress;//
    private String workTel;//
    private String firstLiaisonName;//第一联系人姓名
    private String firstLiaisonRelation;//第一联系人与客户关系
    private String firstLiaisonMobile;//
    private String firstLiaisonTel;//
    private String secondLiaisonName;//第二联系人姓名
    private String secondLiaisonRelation;//第二联系人与客户关系
    private String secondLiaisonMobile;//
    private String secondLiaisonTel;//
    private String threeLiaisonName;//第三联系人姓名
    private String threeLiaisonRelation;//第三联系人与客户关系
    private String threeLiaisonMobile;//
    private String threeLiaisonTel;//
    private String cpmc;//产品名称
    private String hkType;//还款方式
    private String znj;//滞纳金
    private String ajhssj;//案件回收时间
    private String accountAge;//账龄
    private String laFlag;//留案标签
    private String fxFlag;//风险标签
    private String htlx;//合同类型
    private String jmbq;//减免标签
    private String fcbq;//法催标签
    private String fxsfbh;//罚息是否变化
    private String remark;//备注
    private String tar;//退案日
    private String jkrq;//借款日期
    private String zhychkr;//最近一次还款日
    private String mqhkje;//每期还款金额
    private String dqqkje;//当期欠款金额
    private String ljyhje;//累计已还金额
    private String sfje;//首付金额
    private String zdhkzh1;//指定还款账号1
    private String zdhkzh2;//指定还款账号2
    private String zdhkzhhm1;//指定还款账户户名1
    private String zdhkzhhm2;//指定还款账户户名2
    private String zdhkqd1;//指定还款渠道1
    private String zdhkqd2;//指定还款渠道2
    private String khmb;//考核目标
    private String spjg;//商品价格
    private String dklx;//贷款类型
    private String jkbs;//借款笔数
    private String spxx;//商品信息
    private String wacs;//委案次数
    private String ykqs;//已还期数
    private String workDept;//工作部门
    private String customerMobile2;//客户电话2
    private String customerMobile3;//客户电话3
    private String customerMobile4;//客户电话4
    private String fourthLiaisonName;//联系人4姓名
    private String fourthLiaisonRelation;//联系人4关系
    private String fourthLiaisonMobile;//联系人4电话
    private String fifthLiaisonName;//联系人5姓名
    private String fifthLiaisonRelation;//联系人5关系
    private String fifthLiaisonMobile;//联系人5电话

    private String headRowNum; //表头行
    private String dataRowNum; //数据起始行

}
