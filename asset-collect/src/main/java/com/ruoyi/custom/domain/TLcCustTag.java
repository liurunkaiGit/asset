package com.ruoyi.custom.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户标签对象 t_lc_cust_tag
 *
 * @author liurunkai
 * @date 2020-01-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class TLcCustTag extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键自增
     */
    private Long id;

    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String customName;

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
     * 客户与联系人之间的关系  1：本人，2：直系，3：亲戚，4：朋友
     */
    @Excel(name = "客户与联系人之间的关系  1：本人，2：直系，3：亲戚，4：朋友")
    private Long relation;

    /**
     * 联系结果
     */
    @Excel(name = "联系结果")
    private String contactResult;

    /**
     * 催收方式 1：电催，2：委外，3：机器人，4：法催
     */
    @Excel(name = "催收方式 1：电催，2：委外，3：机器人，4：法催")
    private Long collType;

    /**
     * 最小次数
     */
    @Excel(name = "最小次数")
    private Long minCount;

    /**
     * 最大次数
     */
    @Excel(name = "最大次数")
    private Long maxCount;

    /**
     * 客户标签
     */
    @Excel(name = "客户标签")
    private String customTag;

    /**
     * 是否可用 1：是，2：否
     */
    @Excel(name = "是否可用 1：是，2：否")
    private Long isUse;

}
