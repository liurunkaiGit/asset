package com.ruoyi.custom.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 客户工作单位信息对象 t_lc_cust_job
 *
 * @author liurunkai
 * @date 2019-12-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TLcCustJob extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 客户编号
     */
    @Excel(name = "客户编号")
    private String customCode;

    /**
     * 客户身份证号
     */
    @Excel(name = "客户身份证号")
    private String certificateNo;

    /**
     * 职业
     */
    @Excel(name = "职业")
    private String profession;

    /**
     * 单位名称
     */
    @Excel(name = "单位名称")
    private String companyName;

    /**
     * 单位电话
     */
    @Excel(name = "单位电话")
    private String companyTel;

    /**
     * 单位地址
     */
    @Excel(name = "单位地址")
    private String companyAddress;

    /**
     * 邮政编码
     */
    @Excel(name = "邮政编码")
    private String companyPostcode;

    /**
     * 单位所属行业
     */
    @Excel(name = "单位所属行业")
    private String companyIndustry;

    /**
     * 修改时间
     */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /**
     * 修改人
     */
    @Excel(name = "修改人")
    private Long modifyBy;

    /**
     * 是否有效 1：是，2：否
     */
    @Excel(name = "是否有效 1：是，2：否")
    private Integer validateStatus;

    /**
     * 案件号
     */
    @Excel(name = "案件号")
    private String caseNo;

    /**
     * 机构编码
     */
    @Excel(name = "机构编码")
    private String orgId;

    /**
     * 机构名称
     */
    @Excel(name = "机构名称")
    private String orgName;

    /**
     * 导入批次号
     */
    @Excel(name = "导入批次号")
    private String importBatchNo;

}
