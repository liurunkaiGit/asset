package com.ruoyi.stationletter.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 站内信对象 t_lc_station_letter_agent
 *
 * @author liurunkai
 * @date 2020-10-30
 */
@Data
public class TLcStationLetterAgent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 站内信主键
     */
    @Excel(name = "站内信主键")
    private Long letterId;

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
     * 坐席id，多个之前用逗号分隔
     */
    @Excel(name = "坐席id，多个之前用逗号分隔")
    private String agentId;

    /**
     * 发送人
     */
    @Excel(name = "发送人")
    private Integer sendBy;

    /**
     * 发送时间，如果立即发送则等于创建时间
     */
    @Excel(name = "发送时间，如果立即发送则等于创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /**
     * 状态，1：已读，2：未读
     */
    @Excel(name = "状态，1：已读，2：未读")
    private Integer readStatus;

    private String sendUserName;

}
