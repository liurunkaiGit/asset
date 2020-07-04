package com.ruoyi.robot.domain;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/5/11 16:14
 */
@Data
public class DoneTaskPhones {
    private int pageNum;
    private int pageSize;
    private int size;
    private int total;
    private int pages;
    private List<DoneTaskPhone> list;

    @Data
    public static class DoneTaskPhone {

        /**
         * 通话记录id
         */
        private int callInstanceId;
        private int sceneDefId;
        private int callJobId;
        private String customerTelephone;
        private String customerName;
        private int status;
        private int finishStatus;
        private String duration;
        private int chatRound;
        private String startTime;
        private String endTime;
        private String callerPhone;
        private String luyinOssUrl;
        private String secondaryCallTime;
        private int secondaryCallTimes;
        private String jobName;
        private List<ResultListBean> resultList;

        @Data
        public static class ResultListBean {
            private String name;
            private String value;
        }
    }
}
