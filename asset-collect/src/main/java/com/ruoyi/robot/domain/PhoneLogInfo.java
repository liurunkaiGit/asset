package com.ruoyi.robot.domain;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/5/12 10:22
 */
@Data
public class PhoneLogInfo {

    private int code;
    private PhoneLog data;
    private String resultMsg;
    private Object errorStackTrace;

    @Data
    public static class PhoneLog {

        private PhoneLogBean phoneLog;
        private TLcRobotCallRecordMeteData sceneInstance;
        private List<TaskResultBean> taskResult;

        @Data
        public static class PhoneLogBean {

            private String luyinOssUrl;
            private List<PhoneLogsBean> phoneLogs;

            @Data
            public static class PhoneLogsBean {

                private int sceneInstanceLogId;
                private int sceneInstanceId;
                private String speaker;
                private String content;
                private Object userMean;
                private Object userMeanDetail;
                private int aiUnknown;
                private String startTime;
                private String endTime;
            }
        }

        @Data
        public static class TLcRobotCallRecordMeteData {
            private int callInstanceId;
            private int companyId;
            private int callJobId;
            private int customerId;
            private String customerTelephone;
            private String customerName;
            private int status;
            private int finishStatus;
            private int duration;
            private int chatRound;
            private String startTime;
            private String endTime;
            private String callerPhone;
            private String luyinOssUrl;
            private String userLuyinOssUrl;
            private String properties;
            private String handlePerson;
            private int callType;
            private int readStatus;
            private String jobName;
            private int robotDefId;
            private int sceneDefId;
            private int sceneRecordId;
            private Object trackResult;
            private int hangUp;
            private String secondaryCallTime;
            private int secondaryCallTimes;
        }

        @Data
        public static class TaskResultBean {
            private int sceneInstanceResultId;
            private int companyId;
            private int sceneInstanceId;
            private String resultName;
            private String resultValue;
            private Object resultDesc;

        }
    }
}
