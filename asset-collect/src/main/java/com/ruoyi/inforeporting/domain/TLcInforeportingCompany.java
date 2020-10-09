package com.ruoyi.inforeporting.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.StringUtils;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 上报信息-对公入账
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Data
public class TLcInforeportingCompany extends TLcInforeportingTemplate implements Cloneable{
    private static final long serialVersionUID = 164661616L;

    @Excel(name = "付款人姓名")
    private String draweeName;
    /**
     * 付款人与客户关系
     */
    @Excel(name = "付款人与客户关系")
    private String relationship;
    /**
     * 付款卡前四位
     */
    @Excel(name = "付款卡前四位")
    private Integer topFourCards;
    /**
     * 付款卡后四位
     */
    @Excel(name = "付款卡后四位")
    private Integer lastFourCards;
    /**
     * 付款银行
     */
    @Excel(name = "付款银行")
    private String payingBank;
    /**
     * 存入金额
     */
    @Excel(name = "存入金额")
    private Double depositAmount;
    /**
     * 扣款金额
     */
    @Excel(name = "扣款金额")
    private Double amountOfDeduction;
    /**
     * 非本人对公还款原因
     */
    @Excel(name = "非本人对公还款原因")
    private String reasons;
    /**
     * 申请时间
     */
    @Excel(name = "申请时间",dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date applicationTime;
    /**
     * 存入日期
     */
    @Excel(name = "存入日期",dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date depositDate;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remarks;
    /**
     * 删除标记1=已删除 0=未删除
     */
    private Integer delFlag;

    /**
     * 地区
     */
    private String area;
    /**
     * 手别
     */
    private String transferType;
    /**
     * 呆账核销日期
     */
    private String dzhxrq;
    /**
     * 呆账核销日期
     */
    private Double reductionAfterAmount;
    /**
     * 还款比例
     */
    private String repaymentRatio;
    /**
     * 联系方式
     */
    private String telephone;
    /**
     * 减免失效日
     */
    private Date expirationDate;


    @Override
    public TLcInforeportingCompanyExp clone() {
        TLcInforeportingCompanyExp cep = new TLcInforeportingCompanyExp();
        cep.setProductName(getProductName());
        cep.setCaseNo(getCaseNo());
        cep.setCustomName(getCustomName());
        ///封装数据
        StringBuilder company = new StringBuilder();
        company.append("客户姓名:"+StringUtils.nvl(getCustomName(),"-")+"\r\n");
        company.append("合同号:"+getCaseNo()+"\n");
        company.append("付款人姓名:"+StringUtils.nvl(getDraweeName(),"-")+"\n");
        company.append("付款人与客户关系:"+StringUtils.nvl(getRelationship(),"-")+"\n");
        company.append("付款卡前四位:"+StringUtils.nvl(getTopFourCards(),"-")+"\n");
        company.append("付款卡后四位:"+ StringUtils.nvl(getLastFourCards(),"-")+"\n");
        company.append("付款银行:"+StringUtils.nvl(getPayingBank(),"-")+"\n");
        company.append("存入金额:"+StringUtils.nvl(getDepositAmount(),0.0)+"\n");
        company.append("扣款金额:"+StringUtils.nvl(getAmountOfDeduction(),0.0)+"\n");
        company.append("非本人对公还款原因:"+StringUtils.nvl(getReasons(),"-"));
        cep.setCompany(company.toString().replaceAll("\n", String.valueOf((char)10)));
        cep.setRemarks(getRemarks());
        cep.setApplicationTime(this.getApplicationTime());
        cep.setCreateTime(this.getCreateTime());
        return cep;
    }

}
