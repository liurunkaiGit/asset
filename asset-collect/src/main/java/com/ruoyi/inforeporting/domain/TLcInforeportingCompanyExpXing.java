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
public class TLcInforeportingCompanyExpXing {
    private static final long serialVersionUID = 164665551616L;
    @Excel(name = "序号")
    private int xh;
    @Excel(name = "产品")
    private String productName;
    /**
     * 合同号
     */
    @Excel(name = "借据号")
    private String caseNo;
    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String customName;
    /**
     * 付款人姓名
     */
    @Excel(name = "对公入账",height = 100)
    private String company;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remarks;
    /**
     * 申请时间
     */
    @Excel(name = "申请时间",dateFormat = "yyyy-MM-dd")
    private Date applicationTime;
    /**
     * 创建时间
     */
    private Date createTime;
}
