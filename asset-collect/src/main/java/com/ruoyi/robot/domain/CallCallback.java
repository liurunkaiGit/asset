package com.ruoyi.robot.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description: 接收呼入回调参数实体
 * @author: liurunkai
 * @Date: 2020/2/21 14:19
 */
@Data
public class CallCallback {
    private Integer code;
    private CallCallbackData data;
    private String resultMsg;
    private String errorStackTrace;

    @Data
    public class CallCallbackData {
        private String dataType;
        private CallData data;

        @Data
        public class CallData{
            private TLcRobotCallRecordMeteData sceneInstance;
            private List<TLcRobotCallAnalyseResult> taskResult;
            private PhoneLog phoneLog;
            private String sign;
            private String dateTime;

            @Data
            public class PhoneLog {
                private List<TLcRobotCallDetail> phoneLogs;
                private String luyinOssUrl;
            }
        }
    }
}
