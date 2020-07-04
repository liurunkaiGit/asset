package com.ruoyi.robot.domain;

import com.ruoyi.robot.enums.TaskStatus;
import lombok.Data;

/**
 * @Description: 任务状态回调接口对象
 * @author: liurunkai
 * @Date: 2020/5/11 14:25
 */
@Data
public class TaskStatusCallback {

    private int code;
    private TaskStatusCallbackData data;
    private String resultMsg;
    private String errorStackTrace;

    @Data
    public static class TaskStatusCallbackData {
        private String dataType;
        private taskStatusData data;

        @Data
        public static class taskStatusData {
            private int callJobId;
            private int companyId;
            private String jobName;
            private int jobType;
            private String startDate;
            private String endDate;
            private String workingStartTime;
            private String workingEndTime;
            private Object breakStartTime;
            private Object breakEndTime;
            private int status;
            private int callType;
            private int robotDefId;
            private int sceneDefId;
            private int sceneRecordId;
            private String remark;
            private int smsType;
            private Object smsCondition;
            private Object smsTemplateId;
            private int userId;
            private String userName;
        }
    }
}
