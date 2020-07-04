package com.ruoyi.robot.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 机器人黑名单管理对象 t_lc_robot_black
 *
 * @author liurunkai
 * @date 2020-06-24
 */
@Data
@Builder(toBuilder = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TLcRobotBlack extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键自增
     */
    private Long id;

    /**
     * 案件编号
     */
    @Excel(name = "案件编号")
    private String caseNo;

    /**
     * 机构编号
     */
    private Integer orgId;

    /**
     * 机构名称
     */
    @Excel(name = "机构名称")
    private String orgName;

    /**
     * 导入批次号
     */
    private String importBatchNo;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称")
    private String customerName;

    /**
     * 手机号
     */
    @Excel(name = "手机号")
    private String phone;

    /**
     * 触发原因
     */
    @Excel(name = "触发原因")
    private String reason;

    /**
     * 修改人
     */
    private Integer modifyBy;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 开始创建时间
     */
    private Date startCreateTime;

    /**
     * 结束创建时间
     */
    private Date endCreateTime;

}
