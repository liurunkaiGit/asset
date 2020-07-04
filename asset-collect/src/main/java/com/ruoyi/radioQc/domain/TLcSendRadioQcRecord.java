package com.ruoyi.radioQc.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 【请填写功能名称】对象 t_lc_send_radio_qc_record
 *
 * @author liurunkai
 * @date 2020-03-10
 */
@Data
@Builder(toBuilder = true)
public class TLcSendRadioQcRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 通话记录id
     */
    @Excel(name = "通话记录id")
    private Long callRecordId;

    /**
     * 通话录音地址
     */
    @Excel(name = "通话录音地址")
    private String radioUrl;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称")
    private String custName;

    /**
     * 客户手机号
     */
    @Excel(name = "客户手机号")
    private String custPhone;

    /**
     * 通话开始时间
     */
    @Excel(name = "通话开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date callStartTime;

    /**
     * 通话结束时间
     */
    @Excel(name = "通话结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date callEndTime;

    /**
     * 是否推送成功 1：是，2：否
     */
    @Excel(name = "是否推送成功 1：是，2：否")
    private Integer status;
}
