package com.ruoyi.robot.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 通话记录相关元数据
 *
 * @author liurunkai
 * @date 2020-02-20
 */
@Data
@Accessors(chain = true)
public class TLcRobotCallRecordMeteData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 呼入回调标识：INBOUND_CALL_INSTANCE_RESULT
     */
    @Excel(name = "呼入回调标识：INBOUND_CALL_INSTANCE_RESULT")
    private String dataType;

    /**
     * 通话记录 id
     */
    @Excel(name = "通话记录 id")
    private Integer inboundInstanceId;

    /**
     * 公司ID
     */
    @Excel(name = "公司ID")
    private Integer companyId;

    /**
     * 任务 ID
     */
    @Excel(name = "任务 ID")
    private Integer callJobId;

    /**
     * 客户 Id
     */
    @Excel(name = "客户 Id")
    private Integer customerId;

    /**
     * 客户手机号
     */
    @Excel(name = "客户手机号")
    private String customerTelephone;

    /**
     * $column.columnComment
     */
    @Excel(name = "客户手机号")
    private String customerName;

    /**
     * 电话实例状态 0： 未开始 1： 进行中 2： 已完成
     */
    @Excel(name = "电话实例状态 0： 未开始 1： 进行中 2： 已完成")
    private Integer status;

    /**
     * 已完成通话状态枚举
     */
    @Excel(name = "已完成通话状态枚举")
    private Integer finishStatus;

    /**
     * 通话时长
     */
    @Excel(name = "通话时长")
    private Integer duration;

    /**
     * 通话轮次
     */
    @Excel(name = "通话轮次")
    private Integer chatRound;

    /**
     * 通话开始时间
     */
    @Excel(name = "通话开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * $column.columnComment
     */
    @Excel(name = "通话结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 被叫号码
     */
    @Excel(name = "被叫号码")
    private String callerPhone;

    /**
     * 通话录音（包含 Ai 和客户）
     */
    @Excel(name = "通话录音", readConverterExp = "包=含,A=i,和=客户")
    private String luyinOssUrl;

    /**
     * 通话录音（只包含客户）
     */
    @Excel(name = "通话录音", readConverterExp = "只=包含客户")
    private String userLuyinOssUrl;

    /**
     * 通话记录携带的参数(json 字符串)，包含话术变量和自定义参数， 用户可以传入自己的变量， 回调会传回给用户
     */
    @Excel(name = "通话记录携带的参数(json 字符串)，包含话术变量和自定义参数， 用户可以传入自己的变量， 回调会传回给用户")
    private String properties;

    /**
     * 是否已读， 产品中的通话记录已读未读状态 0： 未读 1： 已读
     */
    @Excel(name = "是否已读， 产品中的通话记录已读未读状态 0： 未读 1： 已读")
    private Integer readStatus;

    /**
     * 机器人 Id
     */
    @Excel(name = "机器人 Id")
    private Integer robotDefId;

    /**
     * 场景 Id
     */
    @Excel(name = "场景 Id")
    private Long sceneDefId;

    /**
     * 场景录音 id
     */
    @Excel(name = "场景录音 id")
    private Integer sceneRecordId;

    /**
     * 转人工状态:0-无转接,1-成功,2-失败
     */
    @Excel(name = "转人工状态:0-无转接,1-成功,2-失败")
    private Long transferStatus;

    /**
     * 转人工详情
     */
    @Excel(name = "转人工详情")
    private String transferInfo;

    /**
     * 用户级别
     */
    @Excel(name = "用户级别")
    private String userLevel;

    /**
     * 挂机人 0： AI 1： 用户
     */
    @Excel(name = "挂机人 0： AI 1： 用户")
    private Integer hangUp;

    /**
     * 是否回调
     */
    @Excel(name = "是否回调")
    private Integer callbacked;

    /**
     * $column.columnComment
     */
    @Excel(name = "是否回调")
    private Object propertiesMap;

    /**
     * 回调签名（需联系开通)
     */
    @Excel(name = "回调签名", readConverterExp = "回调签名（需联系开通)")
    private String sign;

    /**
     * GMT 格式日期
     */
    @Excel(name = "GMT 格式日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String dateTime;
}
