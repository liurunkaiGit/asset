package com.ruoyi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/10 15:35
 */
@Data
@Component("com.ruoyi.config.RobotAppConfig")
public class RobotAppConfig {

    /**
     * appKey
     */
    @Value("${robot.appKey:N1q1UvrrrHh53koJ}")
    private String appKey;

    /**
     * appSecret
     */
    @Value("${robot.appSecret:eI2IwDBklMfWbEj8B8rKvwkXPmlTwQ}")
    private String appSecret;

    /**
     * 创建任务接口
     */
    @Value("${robot.task.createTask:http://api.byrobot.cn/openapi/v1/task/createTask}")
    private String createTask;

    /**
     * 启动任务接口
     */
    @Value("${robot.task.start:http://api.byrobot.cn/openapi/v1/task/start}")
    private String start;

    /**
     * 暂停任务接口
     */
    @Value("${robot.task.pause:http://api.byrobot.cn/openapi/v1/task/pause}")
    private String pause;

    /**
     * 停止任务接口
     */
    @Value("${robot.task.stop:http://api.byrobot.cn/openapi/v1/task/stop}")
    private String stop;

    /**
     * 删除任务接口
     */
    @Value("${robot.task.delete:http://api.byrobot.cn/openapi/v1/task/delete}")
    private String delete;

    /**
     * 向任务中导入客户接口
     */
    @Value("${robot.task.importTaskCustomer:http://api.byrobot.cn/openapi/v1/task/importTaskCustomer}")
    private String importTaskCustomer;

    /**
     * 修改任务接口
     */
    @Value("${robot.task.update:http://api.byrobot.cn/openapi/v1/task/update}")
    private String update;

    /**
     * 查询话术变量接口
     */
    @Value("${robot.task.getSceneVariables:http://api.byrobot.cn/openapi/v1/task/getSceneVariables}")
    private String getSceneVariables;

    /**
     * 获取任务列表接口
     */
    @Value("${robot.task.getTasks:http://api.byrobot.cn/openapi/v1/task/getTasks}")
    private String getTasks;

    /**
     * 获取任务详情接口
     */
    @Value("${robot.task.getTaskDetail:http://api.byrobot.cn/openapi/v1/task/getTaskDetail}")
    private String getTaskDetail;

    /**
     * 获取已经完成任务电话号码接口
     */
    @Value("${robot.task.queryDoneTaskPhones:http://api.byrobot.cn/openapi/v1/task/queryDoneTaskPhones}")
    private String queryDoneTaskPhones;

    /**
     * 获取任务未开始的电话列表接口
     */
    @Value("${robot.task.notDialedCustomerList:http://api.byrobot.cn/openapi/v1/task/notDialedCustomerList}")
    private String notDialedCustomerList;

    /**
     * 获取一个通话的详情接口接口
     */
    @Value("${robot.task.phoneLogInfo:http://api.byrobot.cn/openapi/v1/task/phoneLogInfo}")
    private String phoneLogInfo;

    /**
     * 获取绑定公司列表接口
     */
    @Value("${robot.company.getCompanys:http://api.byrobot.cn/openapi/v1/company/getCompanys}")
    private String getCompanys;

    /**
     * 获取公司的主叫电话列表接口
     */
    @Value("${robot.company.getPhones:http://api.byrobot.cn/openapi/v1/company/getPhones}")
    private String getPhones;

    /**
     * 获取公司的机器人话术列表接口
     */
    @Value("${robot.company.getRobots:http://api.byrobot.cn/openapi/v1/company/getRobots}")
    private String getRobots;

    /**
     * 添加单个黑名单到公司默认黑名单组接口
     */
    @Value("${robot.company.addBlackList:http://api.byrobot.cn/openapi/v1/company/addBlackList}")
    private String addBlackList;

    /**
     * 查询回调失败记录
     */
    @Value("${robot.callback.queryUnCallBack:http://api.byrobot.cn/openapi/v1/callBack/queryUnCallBack}")
    private String queryUnCallBack;

}
