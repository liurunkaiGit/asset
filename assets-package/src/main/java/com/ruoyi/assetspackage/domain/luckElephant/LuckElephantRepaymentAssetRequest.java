package com.ruoyi.assetspackage.domain.luckElephant;

import lombok.Data;

import java.util.List;

/**
 * @author guozeqi
 * @create 2020-08-06
 */
@Data
public class LuckElephantRepaymentAssetRequest {
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
    private List<RepaymentAssetEntity> facts;

    @Data
    public class RepaymentAssetEntity{
        private static final long serialVersionUID = 1L;

        private String orgCasno;//机构案件号
        private String waYe;//逾期待还金额
        private String overdueDays;//逾期天数
        private String transfertype;//逾期阶段
        private String lastRepayAmount;//最近还款金额
        private String payStages;//已还清期数
        private String rmbQkzje;//贷款余额
        private String ljyhje;//总已还金额
        private String lastRepayDate;//最近还款时间
        private String payStatus;//还款状态
    }

}
