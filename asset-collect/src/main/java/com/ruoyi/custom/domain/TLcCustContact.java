package com.ruoyi.custom.domain;

import com.ruoyi.assetspackage.domain.phoneStatus.PhoneStatusResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * 客户联系人信息对象 t_lc_cust_contact
 *
 * @author ruoyi
 * @date 2019-12-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class TLcCustContact extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 客户证件号码
     */
    @Excel(name = "客户证件号码")
    private String certificateNo;

    /**
     * 联系人姓名
     */
    @Excel(name = "联系人姓名")
    private String contactName;

    /**
     * 和本人关系 1：本人，2：直系，3：亲戚，4：朋友
     */
    @Excel(name = "和本人关系")
    private Integer relation;

    /**
     * 手机号
     */
    @Excel(name = "手机号")
    private String phone;

    /**
     * 固定电话
     */
    @Excel(name = "固定电话")
    private String tel;

    /**
     * 联系人家庭地址
     */
    @Excel(name = "联系人家庭地址")
    private String address;

    /**
     * 联系人数据来源0：资产导入1：页面添加
     */
    @Excel(name = "联系人数据来源0：资产导入1：页面添加")
    private Integer origin;

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
     * 公司电话
     */
    private String companyTel;

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
    private String isClose;

    private List<String> caseNoList;

    private List<String> importBatchNoList;

    /**
     * 手机号码状态
     */
    private String phoneStatus;

    /**
     * 委案金额
     */
    private BigDecimal arrearsTotal;

    /**
     *结案应还金额
     */
    private BigDecimal closeCaseYhje;
    /**
     *信息更新id
     */
    private Long infoupId;
    /**
     *信息更新状态
     */
    private Integer infoupStatus;

}
