package com.ruoyi.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description: 结案接口使用
 * @author: liurunkai
 * @Date: 2020/3/23 10:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CloseCase {
    private String caseNo;// 案件编号
    private String orgId; // 所属机构
    private String importBatchNo; // 案件导入批次号
    private String isExitCollect; // 是否出催
    private Integer isClose; // 是否结案
    private BigDecimal repayMoney; // 还款金额
    private BigDecimal dqyhje; // 当前已还金额
    private BigDecimal jayhje; // 当前已还金额
}
