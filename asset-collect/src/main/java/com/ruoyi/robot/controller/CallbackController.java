package com.ruoyi.robot.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.robot.domain.CallCallback;
import com.ruoyi.robot.domain.TaskStatusCallback;
import com.ruoyi.robot.service.CallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 机器人回调接口controller
 * @author: liurunkai
 * @Date: 2020/2/21 14:15
 */
@Slf4j
@RestController
@RequestMapping("/callback")
public class CallbackController {

    @Autowired
    private CallbackService callbackService;

    /**
     * 呼入接口回调
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/call", consumes = "text/plain;charset=UTF-8")
    public String callCallback(@RequestBody String params) {
        log.info("呼入回调参数是{}", params);
        CallCallback callCallback = JSONObject.parseObject(params, CallCallback.class);
        log.info("进入了呼入回调接口，机器人任务id{}，手机号是{}", callCallback.getData().getData().getSceneInstance().getCallJobId(), callCallback.getData().getData().getSceneInstance().getCustomerTelephone());
        return this.callbackService.callCallback(callCallback);
    }

    /**
     * 任务状态回调
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/taskStatus", consumes = "text/plain;charset=UTF-8")
    public String taskStatusCallback(@RequestBody String params) {
        log.info("任务状态回调参数是{}", params);
        TaskStatusCallback taskStatusCallback = JSONObject.parseObject(params, TaskStatusCallback.class);
        log.info("进入了任务状态回调接口，任务id{}，任务状态是{}", taskStatusCallback.getData().getData().getCallJobId(), taskStatusCallback.getData().getData().getStatus());
        return this.callbackService.taskStatusCallback(taskStatusCallback);
    }
}
