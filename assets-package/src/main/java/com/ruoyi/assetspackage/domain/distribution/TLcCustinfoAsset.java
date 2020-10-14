package com.ruoyi.assetspackage.domain.distribution;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户信息对象 t_lc_custinfo
 *
 * @author liurunkai
 * @date 2019-12-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TLcCustinfoAsset extends BaseEntity {
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
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String customName;

    /**
     * 客户性别 0：男1：女
     */
    @Excel(name = "客户性别 0：男1：女")
    private Integer customSex;

    /**
     * 出生日期
     */
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 证件类型
     */
    @Excel(name = "证件类型")
    private Integer certificateType;

    /**
     * 证件编号
     */
    @Excel(name = "证件编号")
    private String certificateNo;

    /**
     * 证件地址
     */
    @Excel(name = "证件地址")
    private String certificateAddress;

    /**
     * 户籍详细地址
     */
    @Excel(name = "户籍详细地址")
    private String censusAddress;

    /**
     * 职业
     */
    @Excel(name = "职业")
    private String profession;

    /**
     * 所在城市
     */
    @Excel(name = "所在城市")
    private String city;

    /**
     * 学历
     */
    @Excel(name = "学历")
    private String education;

    /**
     * 手机号
     */
    @Excel(name = "手机号")
    private String phone;

    /**
     * 家庭电话
     */
    @Excel(name = "家庭电话")
    private String tel;

    /**
     * 电子邮件
     */
    @Excel(name = "电子邮件")
    private String email;

    /**
     * 年收入
     */
    @Excel(name = "年收入")
    private BigDecimal incomeYear;

    /**
     * 现住址
     */
    @Excel(name = "现住址")
    private String address;

    /**
     * 是否已婚 1：是，2：否
     */
    @Excel(name = "是否已婚 1：是，2：否")
    private Integer marrageStatus;

    /**
     * 是否有孩 1：是，2：否
     */
    @Excel(name = "是否有孩 1：是，2：否")
    private Integer hasChild;

    /**
     * 是否有房 1：是，2：否
     */
    @Excel(name = "是否有房 1：是，2：否")
    private Integer hasHouse;

    /**
     * 是否有车 1：是，2：否
     */
    @Excel(name = "是否有车 1：是，2：否")
    private Integer hasCar;

    /**
     * 所属机构
     */
    @Excel(name = "所属机构")
    private String orgId;

    /**
     * 所属机构名称
     */
    @Excel(name = "所属机构名称")
    private String orgName;

    /**
     * 是否有效 1：是，2：否
     */
    @Excel(name = "是否有效 1：是，2：否")
    private Integer validateStatus;

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
     * 案件号
     */
    @Excel(name = "案件号")
    private String caseNo;

    /**
     * 导入批次号
     */
    @Excel(name = "导入批次号")
    private String importBatchNo;

}
