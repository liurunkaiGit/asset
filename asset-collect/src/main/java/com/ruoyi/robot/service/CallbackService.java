package com.ruoyi.robot.service;

import com.ruoyi.robot.domain.CallCallback;
import com.ruoyi.robot.domain.TaskStatusCallback;
import com.ruoyi.task.domain.TLcTask;

import java.util.List;

/**
 * @Description: 机器人接口回调service
 * @author: liurunkai
 * @Date: 2020/2/21 14:31
 */
public interface CallbackService {

    /**
     * 呼入接口回调
     *
     * @param callCallback
     */
    String callCallback(CallCallback callCallback);

    /**
     * 构建并插入通话记录相关元数据
     *
     * @param dataType
     * @param callData
     */
    void buildCallRecordMeteData(String dataType, CallCallback.CallCallbackData.CallData callData);

    /**
     * 构建并插入通话分析的结果
     *
     * @param dataType
     * @param callData
     */
    void buildCallAnalyseResult(String dataType, CallCallback.CallCallbackData.CallData callData);

    /**
     * 构建并插入对话详情
     *
     * @param dataType
     * @param callData
     */
    void buildCallDetail(String dataType, CallCallback.CallCallbackData.CallData callData);

    void updateRobotTask(CallCallback.CallCallbackData.CallData callData);

    String taskStatusCallback(TaskStatusCallback taskStatusCallback);
}
