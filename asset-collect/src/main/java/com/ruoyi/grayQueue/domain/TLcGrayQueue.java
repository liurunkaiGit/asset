package com.ruoyi.grayQueue.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 灰色队列对象 t_lc_gray_queue
 *
 * @author liurunkai
 * @date 2020-01-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TLcGrayQueue extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 任务编号
     */
    @Excel(name = "任务编号")
    private Long taskId;

    /**
     * 客户编码
     */
    @Excel(name = "客户编码")
    private String custCode;

    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String custName;

    /**
     * 证件号码
     */
    @Excel(name = "证件号码")
    private String certificateNo;

    /**
     * 机构名称
     */
    @Excel(name = "机构名称")
    private String orgName;

    /**
     * 业务归属机构
     */
    @Excel(name = "业务归属机构")
    private String orgId;

    /**
     * 业务归属人
     */
    @Excel(name = "业务归属人")
    private Long ownerId;

    /**
     * 业务归属人名称
     */
    @Excel(name = "业务归属人名称")
    private String ownerName;

    /**
     * 灰色队列原因
     */
    @Excel(name = "灰色队列原因")
    private String grayReason;

}
