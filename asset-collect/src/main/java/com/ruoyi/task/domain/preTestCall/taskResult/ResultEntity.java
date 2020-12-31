package com.ruoyi.task.domain.preTestCall.taskResult;

import lombok.Data;

/**
 * @author guozeqi
 * @create 2020-12-25
 */
@Data
public class ResultEntity {
    private static final long serialVersionUID = 1L;

    private String phone;
    private variables variables;
    private Long call_count;//呼叫客户次数
    private Long call_time;//呼叫客户时间
    private Long duration;//呼叫通话时长
    private String caller;//主叫号码
    private String outcome;//执行结果
    private String tag;//客户标签
    private String call_uuid;//通话唯一编号
    private Boolean is_calling;//是否正在呼叫
    private Boolean is_agent_answered;//坐席是否接听

    @Data
    public static class variables{
        private static final long serialVersionUID = 1L;
        private String U_TAG;
    }

}
