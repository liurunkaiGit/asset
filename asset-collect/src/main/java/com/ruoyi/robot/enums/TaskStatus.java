package com.ruoyi.robot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 任务状态枚举
 * @author: liurunkai
 * @Date: 2020/2/11 16:41
 */
@Getter
@AllArgsConstructor
public enum TaskStatus {

    NOT_START(0, "未开始"),
    RUNNING(1, "进行中"),
    FINISHED(2, "已完成"),
    CAN_RUNNING(3, "可运行（任务启动过程的中间状态）"),
    USER_PAUSE(4, "用户暂停"),
    SYSTEM_PAUSE(5, "系统暂停（当“ 欠费、 任务配置重拨没到拨打时间、 线路或坐席不可用时会出现这个状态）"),
    STOP(6, "已终止"),
    QUEUEING(7, "排队中（AI 坐席资源不够时进入排队， 直到资源释放）"),
    AI_EXPIRE(8, "AI 到期（购买的 saas 服务到期）"),
    LINE_ARREARS(9, "线路欠费（即用户账户余额不足时）"),
    MESSAGE_ARREARS(10, "短信欠费（短信余额不足， 跟账户余额分开的）");

    private Integer code;
    private String desc;
}
