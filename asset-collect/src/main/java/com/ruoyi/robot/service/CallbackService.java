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

//    void updateRobotTask(CallCallback.CallCallbackData.CallData callData);

    String taskStatusCallback(TaskStatusCallback taskStatusCallback);
}
