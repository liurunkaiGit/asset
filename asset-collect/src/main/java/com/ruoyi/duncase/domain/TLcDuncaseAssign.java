package com.ruoyi.duncase.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 案件轨迹对象 t_lc_duncase_assign
 *
 * @author liurunkai
 * @date 2019-12-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TLcDuncaseAssign extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String customName;

    /**
     * 证件号
     */
    @Excel(name = "证件号")
    private String certificateNo;

    /**
     * 案件编号
     */
    @Excel(name = "案件编号")
    private String caseNo;

    /**
     * 任务编号
     */
    @Excel(name = "任务编号")
    private String taskId;

    /**
     * 操作员ID
     */
    @Excel(name = "操作员ID")
    private Long operationId;

    /**
     * 操作员姓名
     */
    @Excel(name = "操作员姓名")
    private String operationName;

    /**
     * 转移类型(0：初次导入，1：分派转移，2：委上转移，3：结案转移，4：协助催收，5：临时代理)
     */
    @Excel(name = "转移类型(0：初次导入，1：分派转移，2：委上转移，3：结案转移，4：协助催收，5：临时代理)")
    private Integer transferType;

    /**
     * 任务状态
     */
    @Excel(name = "任务状态")
    private Integer taskStatus;

    /**
     * 业务归属人
     */
    @Excel(name = "业务归属人")
    private Long ownerId;

    /**
     * 业务归属人姓名
     */
    @Excel(name = "业务归属人姓名")
    private String ownerName;

    /**
     * 业务归属机构
     */
    @Excel(name = "业务归属机构")
    private String orgId;

    /**
     * 业务归属机构名称
     */
    @Excel(name = "业务归属机构名称")
    private String orgName;

    /**
     * 催收组ID
     */
    @Excel(name = "催收组ID")
    private Integer collectTeamId;

    /**
     * 催收组名称
     */
    @Excel(name = "催收组名称")
    private String collectTeamName;

    /**
     * 是否有效 1：是，2：否
     */
    @Excel(name = "是否有效 1：是，2：否")
    private Integer validateStatus;

    private String createBy;
}
