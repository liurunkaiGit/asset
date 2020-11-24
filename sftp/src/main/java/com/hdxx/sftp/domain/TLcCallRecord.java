package com.hdxx.sftp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 通话结果记录对象 t_lc_call_record
 *
 * @author liurunkai
 * @date 2019-12-31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TLcCallRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 案件编号
     */
    private String caseNo;

    /**
     * 客户姓名
     */
    private String customName;

    /**
     * 客户证件号码
     */
    private String certificateNo;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 和本人关系 1：本人，2：
     */
    private Integer contactRelation;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 坐席
     */
    private String agentName;

    /**
     * 通话结果标记 1:承诺还款 2:谈判 3:半失连 4:拒绝还款 5:失连
     */
    private String callSign;

    /**
     * 查账日期
     */
    private String findDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 拨打时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 通话开始时间
     */
    private Date callStartTime;

    /**
     * 通话结束时间
     */
    private Date callEndTime;

    /**
     * 通话时长
     */
    private String callLen;



    /**
     * 通话结果
     */
    private String callResult;

    /**
     * 通话录音存放位置
     */
    private String callRadioLocation;

    /**
     * 入催日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enterCollDate;

    /**
     * 委案金额
     */
    private BigDecimal arrearsTotal;

    /**
     * 结案应还金额
     */
    private String closeCaseYhje;

    /**
     * 逾期天数
     */
    private String overdueDays;

    /**
     * 通话录音
     */
    private String callRadio;

    /**
     * 类型，1：打电话下电话码 2：未打电话直接下电话码3：导入催记电话码
     */
    private Integer type;

    /**
     * 拨打电话
     */
    private Date makeCallTime;

    /**
     * 电话码中文
     */
    private String dictLabel;

    private String platform;

    private String orgId;

    private String loginName;

    private String createName;

    private String orgName;

    private Integer star;

    /**
     * 拨打时间
     */
    private Date startCreateTime;
    private Date endCreateTime;

}
