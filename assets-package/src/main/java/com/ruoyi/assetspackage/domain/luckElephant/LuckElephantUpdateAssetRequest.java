package com.ruoyi.assetspackage.domain.luckElephant;

import lombok.Data;

import java.util.List;

/**
 * @author guozeqi
 * @create 2020-08-06
 */
@Data
public class LuckElephantUpdateAssetRequest {
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
    private List<UpdateAssetEntity> facts;

    @Data
    public class UpdateAssetEntity{
        private static final long serialVersionUID = 1L;

        private String orgCasno;//机构案件号
        private String yhkDate;//最近应还日
        private String repayAmount;//本期还款金额
        private String rmbYhfyzje;//本期应还担保费
        private String znj;//担保违约金
        private String waYe;//逾期待还金额
        private String overdueDays;//逾期天数
        private String transfertype;//逾期阶段
        private String borrowNo;//还款卡账号
        private String lastRepayAmount;//最近还款金额
        private String payStages;//已还清期数
        private String rmbYhbjzje;//本期应还本金
        private String rmbYhlizje;//本期应还利息
        private String rmbYhfxzje;//逾期利息
        private String rmbQkzje;//贷款余额
        private String ljyhje;//总已还金额
        private String rcr;//入催日期
        private String repayLevel;//还款评等
        private String borrowBlank;//还款卡银行
        private String lastRepayDate;//最近还款时间
        private String payStatus;//还款状态

    }

}
