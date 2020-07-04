package com.ruoyi.clientConfig.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 t_lc_client_config
 *
 * @author liurunkai
 * @date 2020-03-12
 */
public class TLcClientConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 委托方id
     */
    @Excel(name = "委托方id")
    private String clientId;

    /**
     * 委托方名称
     */
    @Excel(name = "委托方名称")
    private String clientName;

    /**
     * 推送到语音质检 1：是 2：否
     */
    @Excel(name = "推送到语音质检 1：是 2：否")
    private Long sendRadioQc;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 是否推送到规则引擎系统 1：是 2：否
     */
    @Excel(name = "是否推送到规则引擎系统 1：是 2：否")
    private Long sendRuleEngine;

    /**
     * 是否自动分配任务 1：是 2：否
     */
    @Excel(name = "是否自动分配任务 1：是 2：否")
    private Long autoAllocatTask;

    /**
     * 自动分配任务策略 1：案件数量平均分配 2：案件金额平均分配
     */
    @Excel(name = "自动分配任务策略 1：案件数量平均分配 2：案件金额平均分配")
    private Long autoAllocatTaskStrategy;

    /**
     * 推送到机器人 1：是 2：否
     */
    @Excel(name = "推送到机器人 1：是 2：否")
    private Long sendRobot;

    /**
     * 是否自动开启机器人任务 1：是 2：否
     */
    @Excel(name = "是否自动开启机器人任务 1：是 2：否")
    private Long autoStartRobotTsk;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setSendRadioQc(Long sendRadioQc) {
        this.sendRadioQc = sendRadioQc;
    }

    public Long getSendRadioQc() {
        return sendRadioQc;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setSendRuleEngine(Long sendRuleEngine) {
        this.sendRuleEngine = sendRuleEngine;
    }

    public Long getSendRuleEngine() {
        return sendRuleEngine;
    }

    public void setAutoAllocatTask(Long autoAllocatTask) {
        this.autoAllocatTask = autoAllocatTask;
    }

    public Long getAutoAllocatTask() {
        return autoAllocatTask;
    }

    public void setAutoAllocatTaskStrategy(Long autoAllocatTaskStrategy) {
        this.autoAllocatTaskStrategy = autoAllocatTaskStrategy;
    }

    public Long getAutoAllocatTaskStrategy() {
        return autoAllocatTaskStrategy;
    }

    public void setSendRobot(Long sendRobot) {
        this.sendRobot = sendRobot;
    }

    public Long getSendRobot() {
        return sendRobot;
    }

    public void setAutoStartRobotTsk(Long autoStartRobotTsk) {
        this.autoStartRobotTsk = autoStartRobotTsk;
    }

    public Long getAutoStartRobotTsk() {
        return autoStartRobotTsk;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("clientId", getClientId())
                .append("clientName", getClientName())
                .append("sendRadioQc", getSendRadioQc())
                .append("projectName", getProjectName())
                .append("sendRuleEngine", getSendRuleEngine())
                .append("autoAllocatTask", getAutoAllocatTask())
                .append("autoAllocatTaskStrategy", getAutoAllocatTaskStrategy())
                .append("sendRobot", getSendRobot())
                .append("autoStartRobotTsk", getAutoStartRobotTsk())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
