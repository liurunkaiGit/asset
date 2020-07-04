package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/5/23 17:31
 */
@Getter
@AllArgsConstructor
public enum ColumnEnum {
    ARREARS_TOTAL("t_lc_task","arrears_total","委案金额","numberInterval"),
    CLOSE_CASE_YHJE("t_lc_task","close_case_yhje","结案应还金额","numberInterval"),
    TRANSFER_TYPE("t_lc_task","transfer_type","手别","string"),
    PHONE("t_lc_task","phone","手机号","string"),
    OWNER_ID("t_lc_task","owner_name","业务归属人","string"),
    CALL_SIGN("t_lc_task","call_sign","最近电话码","select"),
    ACTION_CODE("t_lc_task","action_code","最近行动码","select"),
    TASK_TYPE("t_lc_task","task_type","任务类型","select"),
    ENTER_COLL_DATE("t_lc_task","enter_coll_date","入催日","dateInterval"),
    RECENTLY_ALLOT_DATE("t_lc_task","recently_allot_date","最近分配日期","dateInterval"),
    RECENTLY_FOLLOW_UP_DATE("t_lc_task","recently_follow_up_date","最近跟进时间","dateInterval");

    private String tableName;
    private String column;
    private String columnComment;
    private String columnType;

    public static String getColumnComment(String tableName, String type, String column) {
        String columnComment = Arrays.stream(ColumnEnum.values())
                .filter(columnEnum -> tableName.equals(columnEnum.getTableName()) && type.equals(columnEnum.getColumnType()) && column.equals(columnEnum.getColumn()))
                .findFirst()
                .orElse(null)
                .getColumnComment();
        return columnComment;
    }
}
