package com.ruoyi.assetspackage.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * 资产包对象 asset_package
 *
 * @author guozeqi
 * @date 2020-01-06
 */
@Data
public class AssetPackage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 资产包名称
     */
    @Excel(name = "资产包名称")
    private String packageName;

    /**
     * 案件所属地
     */
    @Excel(name = "案件所属地")
    private String caseAddress;

    /**
     * 案件所属地代码
     */
    @Excel(name = "案件所属地代码")
    private String caseAddressCode;

    /**
     * 案件帐龄标识
     */
    @Excel(name = "案件帐龄标识")
    private String caseAccountAge;

    /**
     * 最后一次催收代码分类
     */
    @Excel(name = "最后一次催收代码分类")
    private String lastCode;

    /**
     * 评分机构名称
     */
    @Excel(name = "评分机构名称")
    private String gradeOrgName;

    /**
     * 评分值
     */
    @Excel(name = "评分值")
    private String gradeValue;

    /**
     * 资产包总金额
     */
    @Excel(name = "资产包总金额")
    private BigDecimal packageAmount;

    /**
     * 资产包总笔数
     */
    @Excel(name = "资产包总笔数")
    private Long packageNum;

    /**
     * 是否空包（0是,1否）
     */
    @Excel(name = "是否空包", readConverterExp = "0=是,1否")
    private String isNull;

    /**
     * 是否封包（0是,1否）
     */
//    @Excel(name = "是否封包", readConverterExp = "0=是,1否")
    private String isClose;

    /**
     * 分发操作人
     */
    @Excel(name = "分发操作人")
    private String allocationBy;

    /**
     * 分发时间
     */
    @Excel(name = "分发时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date allocationTime;

    /**
     * 分发前机构id
     */
    @Excel(name = "分发前机构id")
    private String startOrgId;

    /**
     * 分发后机构id
     */
    @Excel(name = "分发后机构id")
    private String endOrgId;

    /**
     * 到期回收时间
     */
    @Excel(name = "到期回收时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expireTime;

    /**
     * 回收金额
     */
    @Excel(name = "回收金额")
    private BigDecimal recoverAmount;

    /**
     * 回收时间
     */
    @Excel(name = "回收时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recoverTime;

    /**
     * 回收方式
     */
    @Excel(name = "回收方式")
    private String recoverWay;

    /**
     * 回收操作人
     */
    @Excel(name = "回收操作人")
    private String recoverBy;

    /**
     * 回收原因
     */
    @Excel(name = "回收原因")
    private String recoverCause;

    /**
     * 回收状态（0正常,1异常,2费件）
     */
    @Excel(name = "回收状态", readConverterExp = "0=正常,1异常,2费件")
    private String recoverStatus;

    /**
     * 分发前机构名称
     */
    private String startOrgName;

    private Date startCreateTime;
    private Date endCreateTime;

    /**
     * 拥有查看数据权限的部门集合
     */
    private Set<Long> deptIds;

}
