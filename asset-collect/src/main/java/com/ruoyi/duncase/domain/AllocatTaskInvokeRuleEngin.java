package com.ruoyi.duncase.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/10 16:38
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AllocatTaskInvokeRuleEngin {
    /**
     * 系统来源
     */
    private String sysCode;
    /**
     * 主键id:案件号
     */
    private String frontTransNo;
    /**
     * 交易时间
     */
    private String frontTransTime;
    /**
     * 资源包
     */
    private String rulePackage;
    /**
     * 流程id
     */
    private String processId;
    /**
     * 请求实体
     */
    private Fact facts;

    @Data
    @Accessors(chain = true)
    public class Fact {
        /**
         * 案件信息
         */
        private CaseInfoDTO caseInfoDTO;
    }

    @Data
    @Accessors(chain = true)
    public class CaseInfoDTO {
        /**
         * 类路径
         */
        private String className = "com.fintech.urule.entity.CaseInfoDTO";
        /**
         * 案件编号
         */
        private String caseCd;
        /**
         * 客户姓名
         */
        private String customerName;
        /**
         * 城市名称
         */
        private String cityName;
        /**
         * 手别
         */
        private String hands;
        /**
         * BLK
         */
        private String blk;
        /**
         * 委外开始日期
         */
        private String startDate;
        /**
         * 委外截止日期
         */
        private String endDate;
        /**
         * 性别
         */
        private String sex;
        /**
         * 逾期天数
         */
        private String overdueDay;
        /**
         * 人民币余额
         */
        private String balanceRMB;
        /**
         * 人民币最低应缴款金额
         */
        private String lastPayRMB;
        /**
         * 美元余额
         */
        private String balanceDollar;
        /**
         * 美元最低应缴款金额
         */
        private String lastPayDollar;
        /**
         * 账单日
         */
        private String billDay;
        /**
         * 直系人与持卡人关系
         */
        private String directHoldRelation;
        /**
         * 客户家庭地址
         */
        private String familyAddr;
        /**
         * 客户单位地址
         */
        private String organAddr;
        /**
         * 账单地址
         */
        private String billAddr;
        /**
         * 百融评分
         */
        private String baiRongScore;
        /**
         * 行方评分
         */
        private String bankScore;
        /**
         * 度小满评分
         */
        private String dxmScore;
        /**
         * 分期标识
         */
        private String hirePurchaseFlag;
        /**
         * 取现标识
         */
        private String enchashmentFlag;
        /**
         * 共债标识
         */
        private String debtFlag;
        /**
         * 套现标识
         */
        private String cashFlag;
        /**
         * 近24月CD值
         */
        private String near24cd;
        /**
         * 历史逾期M2
         */
        private String hisOverdueM2;
        /**
         * CD上一次未逾期
         */
        private String cdLastNoOverdue;
        /**
         * CD历史M1逾期次数
         */
        private String cdHisM1Num;
        /**
         * CD历史M2逾期次数
         */
        private String cdHisM2Num;
        /**
         * CD历史逾期次数
         */
        private String cdHistoryNum;
        /**
         * CD连续逾期次数
         */
        private String cdToOverdueNum;
        /**
         * 电话码
         */
        private String telNo;
        /**
         * 行动码
         */
        private String actionNo;

    }

}
