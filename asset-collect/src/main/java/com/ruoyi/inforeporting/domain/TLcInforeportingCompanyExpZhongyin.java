package com.ruoyi.inforeporting.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 上报信息-对公入账 导出
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Data
public class TLcInforeportingCompanyExpZhongyin {
    private static final long serialVersionUID = 1646656551616L;
    /**
     * 阶段
     */
    @Excel(name = "阶段")
    private String stage;
    /**
     * 序号
     */
    @Excel(name = "序号")
    private int xh;
    /**
     * 创建时间
     */
    @Excel(name = "日期",dateFormat = "yyyy-MM-dd")
    private Date applicationTime;
    /**
     * 合同号
     */
    @Excel(name = "消费金融账号")
    private String caseNo;
    /**
     * 产品
     */
    @Excel(name = "产品名称")
    private String productName;
    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String customName;
    /**
     * 还款金额
     */
    @Excel(name = "还款金额")
    private Double amountOfDeduction;
    /**
     * 创建时间
     */
    @Excel(name = "还款时间",dateFormat = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 还款人
     */
    @Excel(name = "还款人")
    private String draweeName;
}
