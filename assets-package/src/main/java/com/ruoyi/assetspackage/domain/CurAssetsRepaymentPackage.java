package com.ruoyi.assetspackage.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * 资产还款对象 cur_assets_repayment_package
 *
 * @author guozeqi
 * @date 2020-01-13
 */
@Data
public class CurAssetsRepaymentPackage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    @Excel(name = "序号")
    private Integer sque;

    /**
     * 机构案件号
     */
    @Excel(name = "机构案件号")
    private String orgCasno;

    @Excel(name = "委托方名称")
    private String orgName;

    @Excel(name = "手别")
    private String transferType;

    /**
     * 还款时逾期期数
     */
    @Excel(name = "还款时逾期期数")
    private String hksyqqs;
    /**
     * 客户名称
     */
    @Excel(name = "姓名")
    private String curName;

    @Excel(name = "委案金额")
    private BigDecimal rmbYe;

    /**
     * 还款金额
     */
    @Excel(name = "还款金额")
    private BigDecimal hkje;

    /**
     * 还款日期
     */
    @Excel(name = "还款日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hkrq;


    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "业务归属人")
    private String ownerName;


    /**
     * 交易其他费用
     */
//    @Excel(name = "交易其他费用")
    private BigDecimal jyqtfy;

    /**
     * 交易利息
     */
//    @Excel(name = "交易利息")
    private BigDecimal jylx;

    /**
     * 交易本金
     */
//    @Excel(name = "交易本金")
    private BigDecimal jybj;

    /**
     * 交易滞纳费
     */
//    @Excel(name = "交易滞纳费")
    private BigDecimal jyznf;

    /**
     * 交易类型
     */
//    @Excel(name = "交易类型")
    private String jyType;

    /**
     * 交易金额
     */
//    @Excel(name = "交易金额")
    private BigDecimal jyje;

    /**
     * 产品类型
     */
//    @Excel(name = "产品类型")
    private String productType;

    /**
     * 借据号
     */
//    @Excel(name = "借据号")
    private String jjh;

    /**
     * 催收人
     */
//    @Excel(name = "催收人")
    private String csr;

    /**
     * 催收节点
     */
//    @Excel(name = "催收节点")
    private String csjd;

    /**
     * 分配日期
     */
//    @Excel(name = "分配日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date fprq;

    /**
     * 区域中心
     */
//    @Excel(name = "区域中心")
    private String areaCenter;

    /**
     * 受理城市
     */
//    @Excel(name = "受理城市")
    private String acceptCity;

    /**
     * 合同号
     */
//    @Excel(name = "合同号")
    private String hth;

    /**
     * 地区事业部(一级)
     */
//    @Excel(name = "地区事业部(一级)")
    private String dqsybYj;

    /**
     * 地区事业部(二级)
     */
//    @Excel(name = "地区事业部(二级)")
    private String dqsybEj;

    /**
     * 外包期数
     */
//    @Excel(name = "外包期数")
    private String wbqs;

    /**
     * 外包经办
     */
//    @Excel(name = "外包经办")
    private String wbjb;

    /**
     * 委案日期
     */
//    @Excel(name = "委案日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date warq;



    /**
     * 客户经理姓名
     */
//    @Excel(name = "客户经理姓名")
    private String khjlxm;

    /**
     * 数据日期
     */
//    @Excel(name = "数据日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sjrq;

    /**
     * 是否外包催收
     */
//    @Excel(name = "是否外包催收")
    private String sfwbcs;

    /**
     * 是否结清
     */
//    @Excel(name = "是否结清")
    private String sfjq;

    /**
     * 本月委案
     */
//    @Excel(name = "本月委案")
    private String bywa;

    /**
     * 案件回收日期
     */
//    @Excel(name = "案件回收日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date ajhsrq;

    /**
     * 消费金融账号
     */
//    @Excel(name = "消费金融账号")
    private String xfjrzh;

    /**
     * 调整事项
     */
//    @Excel(name = "调整事项")
    private String tzsx;

    /**
     * 调整金额
     */
//    @Excel(name = "调整金额")
    private BigDecimal tzje;

    /**
     * 账户状态
     */
//    @Excel(name = "账户状态")
    private String zhzt;



  /*  *//**
     * 还款时逾期期数
     *//*
    @Excel(name = "还款时逾期期数")
    private String hksyqqs;
*/

    /**
     * 逾期产品类型
     */
//    @Excel(name = "逾期产品类型")
    private String yqcplx;

    /**
     * 逾期阶段
     */
//    @Excel(name = "逾期阶段")
    private String yqjd;

    /**
     * 额度产品
     */
//    @Excel(name = "额度产品")
    private String quotaProduct;

    /**
     * 机构id
     */
//    @Excel(name = "机构id")
    private String orgId;

    /**
     * 结案状态
     */
//    @Excel(name = "结案状态")
    private String jazt;

    /**
     * 是否出催
     */
//    @Excel(name = "是否出催")
    private String isExitCollect;

    /**
     * 导入批次号，年月日时分秒生成
     */
//    @Excel(name = "导入批次号，年月日时分秒生成")
    private String importBatchNo;

    private String createBy;


    /**
     * 起始和终止导入时间，供查询使用
     */
    private Date startDate;
    private Date endDate;

    /**
     * 拥有查看数据权限的部门集合
     */
    private Set<Long> deptIds;

    /**
     * 资产批次号
     */
    private String assetBatchNO;

    @Excel(name = "归属人工号")
    private String jobNo;
}
