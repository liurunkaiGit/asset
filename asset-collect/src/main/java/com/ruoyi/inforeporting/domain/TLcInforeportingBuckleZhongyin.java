package com.ruoyi.inforeporting.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 上报信息-逾期划扣-中银模板
 * @author: gaohg
 * @Date: 2020/9/07
 */
@Data
public class TLcInforeportingBuckleZhongyin  {

    private static final long serialVersionUID = 164655461666616L;
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

    @Excel(name = "日期",dateFormat = "yyyy-MM-dd")
    private Date createTime2;
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
     * 划扣金额
     */
    @Excel(name = "代扣金额")
    private Double deductionAmount;
    /**
     * 申请时间
     */
    @Excel(name = "申请时间",dateFormat = "yyyy-MM-dd")
    private Date createTime;

    @Excel(name = "申请人")
    private String createBy;

    @Excel(name = "备注")
    private String remarks;
}
