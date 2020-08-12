package com.ruoyi.assetspackage.domain.luckElephant;

import lombok.Data;

import java.util.List;

/**
 * @author guozeqi
 * @create 2020-08-06
 */
@Data
public class LuckElephantAddAssetRequest {
    private static final long serialVersionUID = 1L;

    /** 数据来源 */
    private String sysCode;
    /** 机构编号 */
    private String orgId;
    /** 机构名称 */
    private String orgName;
    /** 批次号 */
    private String batchNo;
    /** 交易时间 */
    private String frontTransTime;
    /** 请求json对象 */
    private List<AddAssetEntity> facts;

    @Data
    public class AddAssetEntity{
        private static final long serialVersionUID = 1L;

        private String orgCasno;//机构案件号
        private String curName;//客户姓名
        private String age;//年龄
        private String sex;//性别
        private String workName;//单位名称
        private String email;//邮箱
        private String nation;//民族
        private String education;//教育水平
        private String familyAddr;//家庭地址
        private String houseAddr;//户籍地址
        private String curNo;//用户编号
        private String cardCode;//证件号码
        private String cardType;//证件类型
        private String workAddr;//单位地址
        private String birthday;//出生日期
        private String marriage;//婚姻状况
        private String salary;//收入水平
        private String curType;//客户类型
        private String city;//定位城市
        private String dealDays;//委案周期
        private String loanTime;//借款时间
        private String payStatus;//还款状态
        private String yhkDate;//最近应还日
        private String lastLoanDate;//贷款到期日
        private String rmbYhbjzje;//本期应还本金
        private String rmbQkzje;//贷款余额
        private String waYe;//逾期待还金额
        private String overdueDays;//逾期天数
        private String transfertype;//逾期阶段
        private String borrowNo;//还款卡账号
        private String lastRepayAmount;//最近还款金额
        private String borrowBlank;//还款卡银行
        private String loanAmount;//借款金额
        private String stagesNum;//借款期次
        private String payStages;//已还清期数
        private String rmbZdyhje;//本期应还款金额
        private String rmbYhlizje;//本期应还利息
        private String rmbYhfyzje;//本期应还担保费
        private String rmbYhfxzje;//逾期利息
        private String znj;//担保违约金
        private String ljyhje;//总已还金额
        private String rcr;//入催日期
        private String repayLevel;//还款评等
        private String lastRepayDate;//最近还款时间
        private String customerMobile;//本人手机号
        private String workTel;//单位电话
        private String firstLiaisonName;//联系人1姓名
        private String firstLiaisonMobile;//联系人1手机号
        private String firstLiaisonRelation;//联系人1与客户关系
        private String secondLiaisonName;//
        private String secondLiaisonMobile;//
        private String secondLiaisonRelation;//
        private String threeLiaisonName;//
        private String threeLiaisonMobile;//
        private String threeLiaisonRelation;//
    }

}
