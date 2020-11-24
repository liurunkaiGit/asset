package com.ruoyi.stationletter.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 站内信对象 t_lc_station_letter
 *
 * @author liurunkai
 * @date 2020-10-30
 */
@Data
@Accessors(chain = true)
@Builder(toBuilder = true)
public class TLcStationLetter extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 消息标题
     */
    @Excel(name = "消息标题")
    private String title;

    /**
     * 消息内容
     */
    @Excel(name = "消息内容")
    private String content;

    /**
     * 发送范围，是否全部用户，1：是，2：否(指定用户)
     */
    @Excel(name = "发送范围，是否全部用户，1：是，2：否(指定用户)")
    private Integer sendRange;

    /**
     * 坐席id，多个之前用逗号分隔
     */
    @Excel(name = "坐席id，多个之前用逗号分隔")
    private String userIds;

    /**
     * 发送方式，是否立即发送，1：是，2：否(定时发送)
     */
    @Excel(name = "发送方式，是否立即发送，1：是，2：否(定时发送)")
    private Integer sendType;

    /**
     * 发送时间，如果立即发送则等于创建时间
     */
    @Excel(name = "发送时间，如果立即发送则等于创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /**
     * 发送状态，1：已发送，2：待发送
     */
    @Excel(name = "发送状态，1：已发送，2：待发送")
    private Integer sendStatus;

    /**
     * 操作人
     */
    private String operateUser;

    /**
     * 所属机构
     */
    @Excel(name = "所属机构")
    private Long orgId;

    /**
     * 所属机构名称
     */
    @Excel(name = "所属机构名称")
    private String orgName;

    private Date startCreateTime;
    private Date endCreateTime;
}
