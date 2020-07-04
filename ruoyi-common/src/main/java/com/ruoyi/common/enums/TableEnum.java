package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/5/23 15:01
 */
@Getter
@AllArgsConstructor
public enum TableEnum {

    TASK("t_lc_task", "任务管理"),
    DUNCASE("t_lc_duncase", "案件综合查询"),
    ROBOT_TASK("t_lc_robot_task", "机器人呼叫明细查询");

    private String tableName;
    private String tableNameComment;
}
