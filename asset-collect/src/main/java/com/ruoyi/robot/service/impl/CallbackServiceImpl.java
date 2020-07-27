package com.ruoyi.robot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.callConfig.domain.TLcCallStrategyConfig;
import com.ruoyi.callConfig.service.ITLcCallStrategyConfigService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.enums.ContactRelaEnum;
import com.ruoyi.enums.IsReCallEnum;
import com.ruoyi.enums.StopCallConditionEnum;
import com.ruoyi.robot.domain.*;
import com.ruoyi.robot.enums.FinishedCallStatus;
import com.ruoyi.robot.enums.LocalRobotTaskStatus;
import com.ruoyi.robot.enums.TaskStatus;
import com.ruoyi.robot.service.CallbackService;
import com.ruoyi.robot.service.ITLcRobotTaskPandectService;
import com.ruoyi.robot.service.ITLcRobotTaskService;
import com.ruoyi.robot.service.RobotService;
import com.ruoyi.robot.utils.RobotMethodUtil;
import com.ruoyi.robot.utils.RobotResponse;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.service.ITLcCallRecordService;
import com.ruoyi.task.service.ITLcTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 机器人接口回调service实现
 * @author: liurunkai
 * @Date: 2020/2/21 14:32
 */
@Slf4j
@Service
@Transactional
public class CallbackServiceImpl implements CallbackService {

    @Autowired
    private ITLcTaskService tLcTaskService;
    @Autowired
    private ITLcCallStrategyConfigService callStrategyConfigService;
    @Autowired
    private RobotMethodUtil robotMethodUtil;
    @Autowired
    private RobotService robotService;
    @Autowired
    private ITLcRobotTaskService tLcRobotTaskService;
    @Autowired
    private ITLcCallRecordService tLcCallRecordService;
    @Autowired
    private ITLcRobotTaskPandectService robotTaskPandectService;

    @Override
    @Transactional
    public String callCallback(CallCallback callCallback) {
        CallCallback.CallCallbackData data = callCallback.getData();
        CallCallback.CallCallbackData.CallData callData = data.getData();
        TLcRobotCallRecordMeteData sceneInstance = callData.getSceneInstance();
        // 根据机器人任务id和手机号查询任务列表
        List<TLcRobotTask> tLcRobotTaskList = this.tLcRobotTaskService.selectRobotTaskByRobotTaskIdAndPhone(sceneInstance.getCallJobId(), callData.getSceneInstance().getCustomerTelephone());
        if (tLcRobotTaskList == null || tLcRobotTaskList.size() == 0) {
            return "success";
        }
        try {
            // 修改机器人任务明细表数据
            TLcRobotTask tLcRobotTask = new TLcRobotTask();
            updateRobotTask(callData, tLcRobotTask);
            log.info("修改机器人任务表数据成功...任务id{},手机号是{}", sceneInstance.getCallJobId(), sceneInstance.getCustomerTelephone());
        } catch (Exception e) {
            log.info("修改机器人任务表数据失败...任务id{},手机号是{}", sceneInstance.getCallJobId(), sceneInstance.getCustomerTelephone());
            return "error";
        }
        return "success";
//        return analyseAndReCall(callData, tLcTaskList);
    }

    /**
     * 分析通话结果并判断是否需要重复拨打(重复开启任务)
     *
     * @param callData
     */
    private String analyseAndReCall(CallCallback.CallCallbackData.CallData callData, List<TLcTask> tLcTaskList) {
        try {
            TLcRobotCallRecordMeteData sceneInstance = callData.getSceneInstance();
            log.info("进入了判断是否重复呼叫的方法...任务id{},手机号是{}", sceneInstance.getCallJobId(), sceneInstance.getCustomerTelephone());
            List<TLcRobotCallAnalyseResult> taskResultList = callData.getTaskResult();
            TLcRobotTask tLcRobotTask = new TLcRobotTask();
            // 判断是否是协催，协催一天呼叫一次，只呼叫一天
            if (tLcTaskList.get(0).getRobotCallStrategyId() != null && tLcTaskList.get(0).getRobotCallStrategyId() != -1L) {
                TLcCallStrategyConfig callStrategyConfig = this.callStrategyConfigService.selectTLcCallStrategyConfigById(tLcTaskList.get(0).getRobotCallStrategyId());
                // 是否需要重复呼叫
                Integer reCallCode = isReCall(callData, callStrategyConfig, taskResultList);
                if (reCallCode.equals(IsReCallEnum.RE_CALL.getCode())) {
                    tLcRobotTask.setIsRecall(IsReCallEnum.RE_CALL.getCode());
                } else if (reCallCode.equals(IsReCallEnum.NEXT_DAY_CALL.getCode())) {
                    //第二天呼叫
                    tLcRobotTask.setIsRecall(IsReCallEnum.NEXT_DAY_CALL.getCode());
                } else if (reCallCode.equals(IsReCallEnum.STOP_CALL.getCode())) {
                    tLcRobotTask.setIsRecall(IsReCallEnum.STOP_CALL.getCode());
                }
            } else {
                // 协催一天呼叫一次，只呼叫一天，协催的号码只要回调就停止呼叫
                tLcRobotTask.setIsRecall(IsReCallEnum.STOP_CALL.getCode());
            }
            // 修改机器人任务明细表数据
//            updateRobotTask(callData, tLcRobotTask);
//            log.info("修改机器人任务表数据成功...任务id{},手机号是{}", sceneInstance.getCallJobId(), sceneInstance.getCustomerTelephone());
            // 插入到电催记录表
//            insertCallRecord(tLcTaskList, callData);
//            log.info("插入到电催记录表成功...任务id{},手机号是{}", sceneInstance.getCallJobId(), sceneInstance.getCustomerTelephone());
            // 根据机器人任务id和手机号修改任务表和案件表中的最近跟进时间及电话码
//            updateTaskByRobotTaskIdAndPhone(sceneInstance);
//            log.info("根据机器人任务id和手机号修改任务表中的最近跟进时间及电话码成功...");
        } catch (Exception e) {
            log.error("呼入回调接口分析通话是否重复拨打发生异常，exception is {}", e);
            return "error";
        }
        return "success";
    }

    /**
     * 插入到电催记录表
     *
     * @param callData
     */
    public void insertCallRecord(List<TLcTask> tLcTaskList, CallCallback.CallCallbackData.CallData callData) {
        TLcRobotCallRecordMeteData sceneInstance = callData.getSceneInstance();
        // 创建对话详情
        if (tLcTaskList != null && tLcTaskList.size() > 0) {
            tLcTaskList.stream().forEach(tLcTask -> {
                JSONArray jsonArray = new JSONArray();
                JSONArray callContentJsonArray = createCllConten(jsonArray, callData);
                TLcCallRecord tLcCallRecord = TLcCallRecord.builder().callRadioLocation(sceneInstance.getLuyinOssUrl())
                        .contactName(tLcTask.getCustomName())
                        .phone(sceneInstance.getCustomerTelephone())
                        .contactRelation(ContactRelaEnum.SELE.getCode())
                        .callStartTime(sceneInstance.getStartTime())
                        .callEndTime(sceneInstance.getEndTime())
                        .callLen(String.valueOf(sceneInstance.getDuration() * 1000))
                        .callSign(FinishedCallStatus.getSignByCode(sceneInstance.getFinishStatus()))
                        .callResult(FinishedCallStatus.getDescByCode(sceneInstance.getFinishStatus()))
                        .caseNo(tLcTask.getCaseNo())
                        .build();
                // 通话记录里面创建人是-1则说明是机器人
                tLcCallRecord.setCreateBy("-1");
                tLcCallRecord.setAgentName("-1");
                tLcCallRecord.setRemark(callContentJsonArray.toJSONString());
                tLcCallRecord.setOrgId(tLcTask.getOrgId());
                this.tLcCallRecordService.insertTLcCallRecord(tLcCallRecord);
            });
        }
    }

    /**
     * 根据机器人任务id和手机号修改任务表中的最近跟进时间及电话码，因为会有重复手机号
     *
     * @param sceneInstance
     */
    private void updateTaskByRobotTaskIdAndPhone(TLcRobotCallRecordMeteData sceneInstance) {
        TLcTask tLcTask = new TLcTask();
        tLcTask.setRobotTaskId(sceneInstance.getCallJobId());
        tLcTask.setPhone(sceneInstance.getCustomerTelephone());
        tLcTask.setRecentlyFollowUpDate(new Date());
        tLcTask.setCallSign(FinishedCallStatus.getSignByCode(sceneInstance.getFinishStatus()));
        tLcTask.setCallSignValue(FinishedCallStatus.getDescByCode(sceneInstance.getFinishStatus()));
        this.tLcTaskService.updateTLcTaskByRobotTaskIdAndPhone(tLcTask);
    }

    /**
     * 修改机器人任务表数据
     *
     * @param callData
     */
//    @Override
//    public void updateRobotTask(CallCallback.CallCallbackData.CallData callData) {
//        TLcRobotCallRecordMeteData sceneInstance = callData.getSceneInstance();
//        List<TLcRobotCallAnalyseResult> taskResultList = callData.getTaskResult();
//        String resultValueAlias = taskResultList.stream()
//                .filter(taskResult -> StringUtils.isNotEmpty(taskResult.getResultValueAlias()))
//                .map(taskResult -> taskResult.getResultValueAlias())
//                .collect(Collectors.joining(","));
//        // 创建对话详情
//        JSONArray jsonArray = new JSONArray();
//        JSONArray callContentJsonArray = createCllConten(jsonArray, callData);
//        // 修改机器人任务
//        TLcRobotTask tLcRobotTask = this.tLcRobotTaskService.selectRobotTaskByRobotTaskIdAndPhone(sceneInstance.getCallJobId(), sceneInstance.getCustomerTelephone());
//        tLcRobotTask.setCallEndDate(sceneInstance.getEndTime())
//                .setCallStartDate(sceneInstance.getStartTime())
//                .setRobotTastId(sceneInstance.getCallJobId())
//                .setRobotTaskStatus(LocalRobotTaskStatus.FINISHED.getCode())
//                .setResultValueAlias(resultValueAlias)
//                .setCallStatus(String.valueOf(sceneInstance.getFinishStatus()))
//                .setCallLen(String.valueOf(sceneInstance.getDuration()))
//                .setCallContent(callContentJsonArray.toJSONString())
//                .setCallRadio(sceneInstance.getLuyinOssUrl());
//        this.tLcRobotTaskService.updateTLcRobotTask(tLcRobotTask);
//    }

    /**
     * 创建对话详情
     *
     * @param jsonArray
     * @param callData
     * @return
     */
    private JSONArray createCllConten(JSONArray jsonArray, CallCallback.CallCallbackData.CallData callData) {
        CallCallback.CallCallbackData.CallData.PhoneLog phoneLog = callData.getPhoneLog();
        List<TLcRobotCallDetail> phoneLogs = new ArrayList<>();
        if (phoneLog.getPhoneLogs() != null && phoneLog.getPhoneLogs().size() > 0) {
            phoneLogs = phoneLog.getPhoneLogs();
        }
        for (TLcRobotCallDetail tLcRobotCallDetail : phoneLogs) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("speaker", tLcRobotCallDetail.getSpeaker());
            jsonObject.put("content", tLcRobotCallDetail.getContent());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     * 是否重复呼叫
     *
     * @param callData
     * @param callStrategyConfig
     * @return
     */
    private Integer isReCall(CallCallback.CallCallbackData.CallData callData, TLcCallStrategyConfig callStrategyConfig, List<TLcRobotCallAnalyseResult> taskResultList) {
        TLcRobotCallRecordMeteData sceneInstance = callData.getSceneInstance();
        // 获取通话手机号
        String customPhone = sceneInstance.getCustomerTelephone();
        log.info("手机号{}的通话结果状态是{}", customPhone, sceneInstance.getFinishStatus());
        // 判断是否达到停止呼叫任务的条件
        boolean stopFlag = isStopCllContion(callStrategyConfig, taskResultList);
        //说明通话结果【客户意向等级】没有达到策略里面配置的停止呼叫任务条件
        TLcRobotTask tLcRobotTask = this.tLcRobotTaskService.selectRobotTaskByRobotTaskIdAndPhone(sceneInstance.getCallJobId(), customPhone).get(0);
        if (!stopFlag) {
            //连续呼叫天数没有达到上限
            Integer phoneContinueCallDays = tLcRobotTask.getContinueDays();
            if (phoneContinueCallDays < callStrategyConfig.getContinueCallDays()) {
                // 判断是否达到当天停止呼叫任务条件
                boolean curDayStopFlag = isCurDayStopConditon(callStrategyConfig, sceneInstance);
                if (!curDayStopFlag) {
                    // 获取呼叫次数并且呼叫次数+1，判断呼叫次数是否达到当天的呼叫次数上限
                    Integer phoneCurDayCallNums = tLcRobotTask.getContinueFrequency();
                    if (phoneCurDayCallNums < callStrategyConfig.getCallFrequencyDay()) {
                        // 说明当天呼叫次数没有达到上限
                        log.info("需要继续呼叫，次数没有达到上限，手机号是{}，外呼策略是id{},呼叫次数是{}", customPhone, callStrategyConfig.getId(), phoneCurDayCallNums);
                        // 继续呼叫
                        return IsReCallEnum.RE_CALL.getCode();
                    } else {
                        // 第二天呼叫：将当前这个手机号添加到第二天定时启动的任务列表中
                        log.info("需要第二天呼叫，次数达到上限，手机号是{}，外呼策略是id{},呼叫次数是{}", customPhone, callStrategyConfig.getId(), phoneCurDayCallNums);
                        return IsReCallEnum.NEXT_DAY_CALL.getCode();
                    }
                } else {
                    // 第二天呼叫：将当前这个手机号添加到第二天定时启动的任务列表中
                    log.info("需要第二天呼叫，达到当天停止呼叫条件，手机号是{}，外呼策略是id{}", customPhone, callStrategyConfig.getId());
                    return IsReCallEnum.NEXT_DAY_CALL.getCode();
                }
            }
        }
        log.info("停止呼叫，手机号是{}，外呼策略是id{}", customPhone, callStrategyConfig.getId());
        return IsReCallEnum.STOP_CALL.getCode();
    }

    /**
     * 修改机器人任务表数据
     *
     * @param callData
     * @param tLcRobotTask
     */
    private void updateRobotTask(CallCallback.CallCallbackData.CallData callData, TLcRobotTask tLcRobotTask) {
        TLcRobotCallRecordMeteData sceneInstance = callData.getSceneInstance();
        List<TLcRobotCallAnalyseResult> taskResultList = callData.getTaskResult();
        String resultValueAlias = taskResultList.stream()
                .filter(taskResult -> StringUtils.isNotEmpty(taskResult.getResultValueAlias()))
                .map(taskResult -> taskResult.getResultValueAlias())
                .collect(Collectors.joining(","));
        // 创建对话详情
        JSONArray jsonArray = new JSONArray();
        JSONArray callContentJsonArray = createCllConten(jsonArray, callData);
        // 修改机器人任务
        tLcRobotTask.setCallEndDate(sceneInstance.getEndTime())
                .setCallStartDate(sceneInstance.getStartTime())
                .setRobotTastId(sceneInstance.getCallJobId())
                .setPhone(sceneInstance.getCustomerTelephone())
                .setRobotTaskStatus(LocalRobotTaskStatus.FINISHED.getCode())
                .setResultValueAlias(resultValueAlias)
                .setCallStatus(String.valueOf(sceneInstance.getFinishStatus()))
                .setCallSign(FinishedCallStatus.getSignByCode(sceneInstance.getFinishStatus()))
                .setCallSignValue(FinishedCallStatus.getDescByCode(sceneInstance.getFinishStatus()))
                .setModifyTime(new Date())
                .setCallBackTime(new Date())
                .setCallLen(String.valueOf(sceneInstance.getDuration()))
                .setCallContent(callContentJsonArray.toJSONString())
                .setCallRadio(sceneInstance.getLuyinOssUrl());
        this.tLcRobotTaskService.updateTLcRobotTaskByRobotTaskIdAndPhone(tLcRobotTask);
    }

    /**
     * 判断是否达到当天停止呼叫任务条件
     *
     * @param callStrategyConfig
     * @param sceneInstance
     * @return
     */
    private boolean isCurDayStopConditon(TLcCallStrategyConfig callStrategyConfig, TLcRobotCallRecordMeteData sceneInstance) {
        // 获取当天任务停止呼叫条件
        String[] stopCallCurDayConditions = callStrategyConfig.getStopCallCurDayCondition().split(",");
        List<String> stopCallCurDayConditionList = new ArrayList<>(stopCallCurDayConditions.length);
        Collections.addAll(stopCallCurDayConditionList, stopCallCurDayConditions);
        boolean flag = stopCallCurDayConditionList.contains(String.valueOf(sceneInstance.getFinishStatus())) ? true : false;
        return flag;
    }

    /**
     * 判断是否达到停止呼叫任务的条件
     *
     * @param callStrategyConfig
     * @param taskResultList
     * @return
     */
    private boolean isStopCllContion(TLcCallStrategyConfig callStrategyConfig, List<TLcRobotCallAnalyseResult> taskResultList) {
        // 获取任务停止呼叫条件
        String[] stopCallConditions = callStrategyConfig.getStopCallCondition().split(",");
        List<String> stopCallConditionList = new ArrayList<>(stopCallConditions.length);
        Collections.addAll(stopCallConditionList, stopCallConditions);
        for (TLcRobotCallAnalyseResult callAnalyseResult : taskResultList) {
            if (StringUtils.isNotEmpty(callAnalyseResult.getResultValueAlias())) {
                if (stopCallConditionList.contains(String.valueOf(StopCallConditionEnum.getCodeByMessage(callAnalyseResult.getResultValueAlias())))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 任务状态接口回调
     *
     * @param taskStatusCallback
     */
    @Override
    public String taskStatusCallback(TaskStatusCallback taskStatusCallback) {
        TaskStatusCallback.TaskStatusCallbackData.taskStatusData data = taskStatusCallback.getData().getData();
        TLcRobotTaskPandect robotTaskPandect = this.robotTaskPandectService.selectTLcRobotTaskPandectByRobotTaskId(data.getCallJobId());
        if (robotTaskPandect == null) {
            log.info("根据机器人任务id找不到对应的机器人任务总览数据，id是{}", data.getCallJobId());
            return "success";
        }
        if (data.getStatus() == TaskStatus.FINISHED.getCode()) {
            try {
                // 查询呼入回调失败的通话记录进行手工回调
//                Integer pageNum = 1;
//                Integer pageSize = 50;
//                List<TLcRobotTask> callbackFaildList = this.tLcRobotTaskService.selectCallbackFaild(data.getCallJobId());
//                if (callbackFaildList != null && callbackFaildList.size() > 0) {
//                    DoneTaskPhones doneTaskPhones = this.robotMethodUtil.queryDoneTaskPhones(data.getCallJobId(), pageNum, pageSize);
//                    getPhoneLogInfoCallback(callbackFaildList, doneTaskPhones);
//                    if (doneTaskPhones.getPages() > 1) {
//                        for (int i = 2; i <= doneTaskPhones.getPages(); i++) {
//                            doneTaskPhones = this.robotMethodUtil.queryDoneTaskPhones(data.getCallJobId(), i, pageSize);
//                            getPhoneLogInfoCallback(callbackFaildList, doneTaskPhones);
//                        }
//                    }
//                }
                // 查询当天任务状态为已完成并且是继续呼叫的任务 // 查询当前任务下状态为已完成并且是继续呼叫的任务
//                TLcRobotTask tLcRobotTask = new TLcRobotTask();
//                tLcRobotTask.setIsRecall(IsReCallEnum.RE_CALL.getCode());
//                tLcRobotTask.setRobotTastId(data.getCallJobId());
                // 不能查当天的，因为任务有可能隔天打完，将当前任务需要继续呼叫的任务重新创建并启动任务
//                List<TLcRobotTask> robotTaskList = this.tLcRobotTaskService.selectTLcRobotTaskList(tLcRobotTask);
//                List<TLcTask> taskList = robotTaskList.stream().map(robotTask -> this.tLcTaskService.selectTLcTaskById(robotTask.getTaskId())).collect(Collectors.toList());
//                if (taskList != null && taskList.size() > 0) {
//                    TLcCallStrategyConfig tLcCallStrategyConfig = this.callStrategyConfigService.selectTLcCallStrategyConfigById(taskList.get(0).getRobotCallStrategyId());
//                    // 获取ai坐席数
//                    TLcOrgSpeechcraftConf orgSpeechcraftConf = this.orgSpeechcraftConfService.selectTLcOrgSpeechcraftConfByOrgId(Long.valueOf(tLcCallStrategyConfig.getOrgId()));
//                    this.robotMethodUtil.createTask(taskList, tLcCallStrategyConfig, robotTaskList.get(0).getContinueDays(), robotTaskList.get(0).getContinueFrequency() + 1, orgSpeechcraftConf.getConcurrentValue());
//                }
                // 查询停止呼叫的任务并拉回
//                tLcRobotTask.setIsRecall(IsReCallEnum.STOP_CALL.getCode());
//                List<TLcRobotTask> stopCallRobotTaskList = this.tLcRobotTaskService.selectTLcRobotTaskList(tLcRobotTask);
//                log.info("任务状态是已完成，需要拉回的任务明细有{}条，任务id{}", stopCallRobotTaskList.size(), data.getCallJobId());
//                if (stopCallRobotTaskList != null && stopCallRobotTaskList.size() > 0) {
//                    robotService.pullback(LocalRobotTaskStatus.FINISHED.getCode(), data.getCallJobId());
//                    log.info("任务状态是已完成，任务拉回成功...任务id{}", data.getCallJobId());
//                }
                // 任务拉回
                robotService.pullback(LocalRobotTaskStatus.FINISHED.getCode(), data.getCallJobId());
                // 修改机器人任务总览表
                RobotTask taskDetail = this.robotMethodUtil.getTaskDetail(data.getCallJobId());
                log.info("任务id：{}的任务详情：{}", data.getCallJobId(), JSON.toJSONString(taskDetail));
                updateRobotTaskPandect(data, taskDetail);
                log.info("任务状态是已完成，修改任务总览表成功...任务id{}", data.getCallJobId());
            } catch (NumberFormatException e) {
                log.error("任务状态成功回调发生异常，exception is {}", e);
                return "error";
            }
        }
        return "success";
    }

    /**
     * 修改机器人任务总览表
     *
     * @param data
     * @param taskDetail
     */
    private void updateRobotTaskPandect(TaskStatusCallback.TaskStatusCallbackData.taskStatusData data, RobotTask taskDetail) {
//        TLcRobotTaskPandect robotTaskPandect = this.robotTaskPandectService.selectTLcRobotTaskPandectByRobotTaskId(data.getCallJobId());
        TLcRobotTaskPandect robotTaskPandect = new TLcRobotTaskPandect();
        robotTaskPandect.setRobotTaskId(data.getCallJobId());
        robotTaskPandect.setRobotTaskStatus(LocalRobotTaskStatus.FINISHED.getCode());
        robotTaskPandect.setTaskEndTime(DateUtils.dateTime(DateUtils.YYYY_MM_DD, data.getEndDate()));
        robotTaskPandect.setPhoneNum(taskDetail.getTotalCount());
        robotTaskPandect.setCallDoneCount(taskDetail.getDoneCount());
        robotTaskPandect.setCallCalledCount(taskDetail.getCalledCount());
        robotTaskPandect.setCallRejectedCount(taskDetail.getRejectedCount());
        robotTaskPandect.setCallUnavailableCount(taskDetail.getUnavailableCount());
        robotTaskPandect.setCallFromUnavailableCount(taskDetail.getFromUnavailableCount());
        robotTaskPandect.setCallBusyCount(taskDetail.getBusyCount());
        robotTaskPandect.setCallMissCount(taskDetail.getMissCount());
        robotTaskPandect.setCallBlankCount(taskDetail.getBlankCount());
        robotTaskPandect.setCallClosedCount(taskDetail.getClosedCount());
        robotTaskPandect.setCallDownCount(taskDetail.getDownCount());
        robotTaskPandect.setCallBlackCount(taskDetail.getBlacklistCount());
        robotTaskPandect.setCallFailCount(taskDetail.getFromUnavailableCount());
        robotTaskPandect.setCallLossCount(taskDetail.getLostCount());
        robotTaskPandect.setCallOverdueCount(taskDetail.getOverdueCount());
        this.robotTaskPandectService.updateTLcRobotTaskPandectByRobotTaskId(robotTaskPandect);
    }

    /**
     * 根据通话记录id获取通话记录详情并执行手工回调
     *
     * @param callbackFaildList
     * @param doneTaskPhones
     */
    private void getPhoneLogInfoCallback(List<TLcRobotTask> callbackFaildList, DoneTaskPhones doneTaskPhones) {
//        List<DoneTaskPhones.DoneTaskPhone> doneTaskPhoneList = callbackFaildList.stream()
//                .map(tLcRobotTask -> doneTaskPhones.getList().stream()
//                        .filter(doneTaskPhone -> Objects.equals(tLcRobotTask.getPhone(), doneTaskPhone.getCustomerTelephone()))
//                        .findFirst()
//                        .orElse(null)
//                ).collect(Collectors.toList());
        List<DoneTaskPhones.DoneTaskPhone> doneTaskPhoneList = new ArrayList<>();
        for (TLcRobotTask tLcRobotTask : callbackFaildList) {
            for (DoneTaskPhones.DoneTaskPhone doneTaskPhone : doneTaskPhones.getList()) {
                if (tLcRobotTask.getPhone().equals(doneTaskPhone.getCustomerTelephone())) {
                    doneTaskPhoneList.add(doneTaskPhone);
                }
            }
        }
        doneTaskPhoneList.stream()
                .filter(doneTaskPhone -> doneTaskPhone != null)
                .forEach(doneTaskPhone -> {
                    RobotResponse robotResponse = this.robotMethodUtil.getPhoneLogInfo(doneTaskPhone.getCallInstanceId());
                    this.robotMethodUtil.handleCallCallback(robotResponse);
                });
    }
}

