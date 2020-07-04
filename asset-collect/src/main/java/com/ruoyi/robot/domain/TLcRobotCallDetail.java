package com.ruoyi.robot.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 【请填写功能名称】对象 t_lc_robot_call_detail
 *
 * @author liurunkai
 * @date 2020-02-20
 */
@Data
@Accessors(chain = true)
public class TLcRobotCallDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 通话记录日志 Id
     */
    @Excel(name = "通话记录日志 Id")
    private Integer sceneInstanceLogId;

    /**
     * 通话记录 Id（对应 scene_instance_id)
     */
    @Excel(name = "通话记录 Id", readConverterExp = "通话记录 Id（对应 scene_instance_id)")
    private Integer inboundInstanceId;

    /**
     * 通话记录 Id（对应 scene_instance_id)
     */
    @Excel(name = "通话记录 Id", readConverterExp = "通话记录 Id（对应 scene_instance_id)")
    private Integer sceneInstanceId;

    /**
     * 公司id
     */
    @Excel(name = "公司id")
    private Integer companyId;

    /**
     * 机器人id
     */
    @Excel(name = "机器人id")
    private Integer robotDefId;

    /**
     * 对应决策 Id
     */
    @Excel(name = "对应决策 Id")
    private Long decisionId;

    /**
     * 说话人 ME： 用户 AI:机器人
     */
    @Excel(name = "说话人 ME： 用户 AI:机器人")
    private String speaker;

    /**
     * 说话内容
     */
    @Excel(name = "说话内容")
    private String content;

    /**
     * 用户说话语义,客户说话内容命中的话术节点分支或知识库问题
     */
    @Excel(name = "用户说话语义,客户说话内容命中的话术节点分支或知识库问题")
    private String userMean;

    /**
     * 用户说话语义详情,客户说话内容命中详情（包括命中的用户说话内容和话术节点分支或知识库分支问题）
     */
    @Excel(name = "用户说话语义详情,客户说话内容命中详情", readConverterExp = "包=括命中的用户说话内容和话术节点分支或知识库分支问题")
    private Object userMeanDetail;

    /**
     * 是否是 ai 无法应答的问题， 1-是，0-否
     */
    @Excel(name = "是否是 ai 无法应答的问题， 1-是，0-否")
    private Boolean aiUnknown;

    /**
     * 是否是 ai 无法应答的问题， 1-是，0-否
     */
    @Excel(name = "是否是 ai 无法应答的问题， 1-是，0-否")
    private Integer aiUnknownValue;

    /**
     * 回答问题状态： 0-分支， 1-问题，2-忽略， 表示命中流程分支或者知识库问题
     */
    @Excel(name = "回答问题状态： 0-分支， 1-问题，2-忽略， 表示命中流程分支或者知识库问题")
    private Long answerStatus;

    /**
     * 学习状态： 0-未学习， 1-已学习，在问题学习板块里面的问题学习状态（默认为空）
     */
    @Excel(name = "学习状态： 0-未学习， 1-已学习，在问题学习板块里面的问题学习状态", readConverterExp = "默=认为空")
    private Long studyStatus;

    /**
     * 说话的开始时间,本句话在录音中的结束时间
     */
    @Excel(name = "说话的开始时间,本句话在录音中的结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 说话的结束时间,本句话在录音中的结束时间
     */
    @Excel(name = "说话的结束时间,本句话在录音中的结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 通话记录纠错内容， 通话记录中的人工纠错功能的纠错内容
     */
    @Excel(name = "通话记录纠错内容， 通话记录中的人工纠错功能的纠错内容")
    private String correctionContent;

    /**
     * 通话记录录音
     */
    @Excel(name = "通话记录录音")
    private String luyinOssUrl;

    /**
     * 回调签名（需联系开通）
     */
    @Excel(name = "回调签名", readConverterExp = "需=联系开通")
    private String sign;

    /**
     * GMT 格式日期
     */
    @Excel(name = "GMT 格式日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String dateTime;

    /**
     * 呼入回调标识：INBOUND_CALL_INSTANCE_RESULT
     */
    @Excel(name = "呼入回调标识：INBOUND_CALL_INSTANCE_RESULT")
    private String dataType;
}
