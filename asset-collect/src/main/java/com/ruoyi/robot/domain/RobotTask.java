package com.ruoyi.robot.domain;

import com.ruoyi.common.annotation.Excel;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/11 17:33
 */
public class RobotTask{
    /**
     * 任务 id
     */
    @Excel(name = "任务id")
    private Integer callJobId;
    /**
     * 电话号码
     */
    @Excel(name = "电话号码")
    private Integer phoneNum;
    /**
     * 是否开启重拨
     */
    @Excel(name = "是否开启重拨",readConverterExp = "false=否,true=是")
    private Boolean repeatCall;
    /**
     * 外呼方式， 对应主叫号码(线路)类型： 2、 1、 0
     */
    @Excel(name = "外呼方式",readConverterExp = "0=手机号,1=固话,2=无主叫线路")
    private Integer callType;
    /**
     * 失联数量
     */
    @Excel(name = "失联数量")
    private Integer lostCount;
    /**
     * 坐席数量
     */
    @Excel(name = "坐席数量")
    private Integer seatNum;
    /**
     * 任务已完成呼通的号码总数
     */
    @Excel(name = "任务已完成呼通的号码总数")
    private Integer calledCount;
    /**
     * 关闭数量
     */
    @Excel(name = "关闭数量")
    private Integer closedCount;
    /**
     * 逾期数量
     */
    @Excel(name = "逾期数量")
    private Integer overdueCount;
    /**
     * 任务名称
     */
    @Excel(name = "任务名称")
    private String jobName;
    /**
     * 计划规则
     */
    @Excel(name = "计划规则")
    private String intentionRule;
    /**
     * 任务已完成拨打的号码总数
     */
    @Excel(name = "任务已完成拨打的号码总数")
    private Integer doneCount;
    /**
     * 版本
     */
    @Excel(name = "版本")
    private String version;
    /**
     * 空号数量
     */
    @Excel(name = "空号数量")
    private Integer blankCount;
    /**
     * 可拨打开始时间
     */
    @Excel(name = "可拨打开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String workingStartTime;
    /**
     * 公司id
     */
    @Excel(name = "公司id")
    private Integer companyId;
    /**
     * 坐席最少限制
     */
    @Excel(name = "坐席最少限制")
    private Integer seatLowerLimit;
    /**
     * 开始日期
     */
    @Excel(name = "开始日期")
    private String startDate;
    /**
     * 状态
     */
    @Excel(name = "状态")
    private Integer status;
    /**
     * 任务 id
     */
    private IntentionRuleVO intentionRuleVO;
    /**
     * 当前最大限制
     */
    @Excel(name = "当前最大限制")
    private Integer concurrencyUpperLimit;
    /**
     * 开始时间
     */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String secondaryStartTime;
    /**
     * 修改时间
     */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String gmtModified;
    /**
     * 任务主叫号码不可用的号码总数
     */
    @Excel(name = "任务主叫号码不可用的号码总数")
    private Integer fromUnavailableCount;
    /**
     * 坐席最大数量
     */
    @Excel(name = "坐席最大数量")
    private Integer seatUpperLimit;
    /**
     * 拒绝数量
     */
    @Excel(name = "拒绝数量")
    private Integer rejectedCount;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;
    /**
     * 总数
     */
    @Excel(name = "总数")
    private Integer totalCount;
    /**
     * 场景id
     */
    @Excel(name = "场景id")
    private Integer sceneDefId;
    /**
     * 是否发送挂机短信： 0-否， 1-是
     */
    @Excel(name = "是否发送挂机短信： 0-否， 1-是")
    private String smsType;
    /**
     * 任务类型
     */
    @Excel(name = "任务类型")
    private Integer jobType;
    /**
     * 规则
     */
    private String intentionRuleStr;
    /**
     * 任务呼叫无法接通的号码总数
     */
    @Excel(name = "任务呼叫无法接通的号码总数")
    private Integer unavailableCount;
    /**
     * 是否当前
     */
    private Integer isConcurrent;
    /**
     * 用户名称
     */
    @Excel(name = "用户名称")
    private String userName;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String gmtCreate;
    /**
     * 空号列表数量
     */
    private Integer blacklistCount;
    /**
     * 用户 id
     */
    @Excel(name = "用户id")
    private Integer userId;
    /**
     * 可拨打结束时间
     */
    @Excel(name = "可拨打结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String workingEndTime;
    /**
     * 机器人 id
     */
    @Excel(name = "机器人id")
    private Integer robotDefId;
    /**
     * 占线数量
     */
    private Integer busyCount;
    /**
     *
     */
    private Integer downCount;
    /**
     * 场景记录id
     */
    private Integer sceneRecordId;
    /**
     * 任务是否删除
     */
    private Boolean jobIsDelete;
    /**
     * 分析类型
     */
    private String analyzeType;
    /**
     * 失联数量
     */
    @Excel(name = "失联数量")
    private Integer missCount;

    public Integer getCallJobId() {
        return callJobId;
    }

    public void setCallJobId(Integer callJobId) {
        this.callJobId = callJobId;
    }

    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Boolean getRepeatCall() {
        return repeatCall;
    }

    public void setRepeatCall(Boolean repeatCall) {
        this.repeatCall = repeatCall;
    }

    public Integer getCallType() {
        return callType;
    }

    public void setCallType(Integer callType) {
        this.callType = callType;
    }

    public Integer getLostCount() {
        return lostCount;
    }

    public void setLostCount(Integer lostCount) {
        this.lostCount = lostCount;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public Integer getCalledCount() {
        return calledCount;
    }

    public void setCalledCount(Integer calledCount) {
        this.calledCount = calledCount;
    }

    public Integer getClosedCount() {
        return closedCount;
    }

    public void setClosedCount(Integer closedCount) {
        this.closedCount = closedCount;
    }

    public Integer getOverdueCount() {
        return overdueCount;
    }

    public void setOverdueCount(Integer overdueCount) {
        this.overdueCount = overdueCount;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getIntentionRule() {
        return intentionRule;
    }

    public void setIntentionRule(String intentionRule) {
        this.intentionRule = intentionRule;
    }

    public Integer getDoneCount() {
        return doneCount;
    }

    public void setDoneCount(Integer doneCount) {
        this.doneCount = doneCount;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getBlankCount() {
        return blankCount;
    }

    public void setBlankCount(Integer blankCount) {
        this.blankCount = blankCount;
    }

    public String getWorkingStartTime() {
        return workingStartTime;
    }

    public void setWorkingStartTime(String workingStartTime) {
        this.workingStartTime = workingStartTime;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getSeatLowerLimit() {
        return seatLowerLimit;
    }

    public void setSeatLowerLimit(Integer seatLowerLimit) {
        this.seatLowerLimit = seatLowerLimit;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public IntentionRuleVO getIntentionRuleVO() {
        return intentionRuleVO;
    }

    public void setIntentionRuleVO(IntentionRuleVO intentionRuleVO) {
        this.intentionRuleVO = intentionRuleVO;
    }

    public Integer getConcurrencyUpperLimit() {
        return concurrencyUpperLimit;
    }

    public void setConcurrencyUpperLimit(Integer concurrencyUpperLimit) {
        this.concurrencyUpperLimit = concurrencyUpperLimit;
    }

    public String getSecondaryStartTime() {
        return secondaryStartTime;
    }

    public void setSecondaryStartTime(String secondaryStartTime) {
        this.secondaryStartTime = secondaryStartTime;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getFromUnavailableCount() {
        return fromUnavailableCount;
    }

    public void setFromUnavailableCount(Integer fromUnavailableCount) {
        this.fromUnavailableCount = fromUnavailableCount;
    }

    public Integer getSeatUpperLimit() {
        return seatUpperLimit;
    }

    public void setSeatUpperLimit(Integer seatUpperLimit) {
        this.seatUpperLimit = seatUpperLimit;
    }

    public Integer getRejectedCount() {
        return rejectedCount;
    }

    public void setRejectedCount(Integer rejectedCount) {
        this.rejectedCount = rejectedCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getSceneDefId() {
        return sceneDefId;
    }

    public void setSceneDefId(Integer sceneDefId) {
        this.sceneDefId = sceneDefId;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public String getIntentionRuleStr() {
        return intentionRuleStr;
    }

    public void setIntentionRuleStr(String intentionRuleStr) {
        this.intentionRuleStr = intentionRuleStr;
    }

    public Integer getUnavailableCount() {
        return unavailableCount;
    }

    public void setUnavailableCount(Integer unavailableCount) {
        this.unavailableCount = unavailableCount;
    }

    public Integer getIsConcurrent() {
        return isConcurrent;
    }

    public void setIsConcurrent(Integer isConcurrent) {
        this.isConcurrent = isConcurrent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getBlacklistCount() {
        return blacklistCount;
    }

    public void setBlacklistCount(Integer blacklistCount) {
        this.blacklistCount = blacklistCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getWorkingEndTime() {
        return workingEndTime;
    }

    public void setWorkingEndTime(String workingEndTime) {
        this.workingEndTime = workingEndTime;
    }

    public Integer getRobotDefId() {
        return robotDefId;
    }

    public void setRobotDefId(Integer robotDefId) {
        this.robotDefId = robotDefId;
    }

    public Integer getBusyCount() {
        return busyCount;
    }

    public void setBusyCount(Integer busyCount) {
        this.busyCount = busyCount;
    }

    public Integer getDownCount() {
        return downCount;
    }

    public void setDownCount(Integer downCount) {
        this.downCount = downCount;
    }

    public Integer getSceneRecordId() {
        return sceneRecordId;
    }

    public void setSceneRecordId(Integer sceneRecordId) {
        this.sceneRecordId = sceneRecordId;
    }

    public Boolean getJobIsDelete() {
        return jobIsDelete;
    }

    public void setJobIsDelete(Boolean jobIsDelete) {
        this.jobIsDelete = jobIsDelete;
    }

    public String getAnalyzeType() {
        return analyzeType;
    }

    public void setAnalyzeType(String analyzeType) {
        this.analyzeType = analyzeType;
    }

    public Integer getMissCount() {
        return missCount;
    }

    public void setMissCount(Integer missCount) {
        this.missCount = missCount;
    }
}

class IntentionRuleVO {
    private Integer dLevelOption;
    private Integer bLevelOption;
    private List<Integer> bLevelAssign;
    private Integer fLevelOption;
    private Integer userId;
    private Boolean wechatPush;
    private Integer companyId;
    private Integer eLevelOption;
    private List<Integer> aLevelAssign;
    private Integer cLevelOption;
    private Integer aLevelOption;

    public Integer getdLevelOption() {
        return dLevelOption;
    }

    public void setdLevelOption(Integer dLevelOption) {
        this.dLevelOption = dLevelOption;
    }

    public Integer getbLevelOption() {
        return bLevelOption;
    }

    public void setbLevelOption(Integer bLevelOption) {
        this.bLevelOption = bLevelOption;
    }

    public List<Integer> getbLevelAssign() {
        return bLevelAssign;
    }

    public void setbLevelAssign(List<Integer> bLevelAssign) {
        this.bLevelAssign = bLevelAssign;
    }

    public Integer getfLevelOption() {
        return fLevelOption;
    }

    public void setfLevelOption(Integer fLevelOption) {
        this.fLevelOption = fLevelOption;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getWechatPush() {
        return wechatPush;
    }

    public void setWechatPush(Boolean wechatPush) {
        this.wechatPush = wechatPush;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer geteLevelOption() {
        return eLevelOption;
    }

    public void seteLevelOption(Integer eLevelOption) {
        this.eLevelOption = eLevelOption;
    }

    public List<Integer> getaLevelAssign() {
        return aLevelAssign;
    }

    public void setaLevelAssign(List<Integer> aLevelAssign) {
        this.aLevelAssign = aLevelAssign;
    }

    public Integer getcLevelOption() {
        return cLevelOption;
    }

    public void setcLevelOption(Integer cLevelOption) {
        this.cLevelOption = cLevelOption;
    }

    public Integer getaLevelOption() {
        return aLevelOption;
    }

    public void setaLevelOption(Integer aLevelOption) {
        this.aLevelOption = aLevelOption;
    }
}
