package com.ruoyi.assetspackage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 任务类型枚举类
 * @author: liurunkai
 * @Date: 2019/12/16 18:14
 */
@Getter
@AllArgsConstructor
public enum TaskTypeEnum {

    FIRST_CREATE(1, "初次生成"),
    RE_ALLOCAT(2, "重新分派"),
    TEMP_AGENT(3, "临时代理"),
    HELP_COLLECT(4, "协助催收"),
    TEMP_AGENT_RECYCLE(5, "临时代理回收"),
    ERR_TASK_ALLOCAT(6, "异常案件转分配"),
    CLOSE_CASE_TRANSFER(7, "结案转移"),
    GRAY_QUEUE(8, "灰色队列"),
    REMOVE_GRAY_QUEUE(9, "从灰色队列移除"),
    HELP_COLLECT_APPLY(10, "协助催收申请"),
    STOP_COLLECT_APPLY(11, "停催申请"),
    STOP_COLLECT(12, "停止催收"),
    STOP_COLLECT_LIVE(13, "停止催收激活"),
    STOP_COLLECT_REFUSE(14, "停止催收拒绝"),
    HELP_COLLECT_REFUSE(15, "拒绝协催"),
    HELP_COLLECT_ROBOT(16, "机器人协催"),
    PULL_BACK_ROBOT(17, "机器人拉回");

    private Integer code;
    private String message;

}
