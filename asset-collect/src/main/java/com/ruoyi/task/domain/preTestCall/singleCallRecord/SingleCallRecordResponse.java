package com.ruoyi.task.domain.preTestCall.singleCallRecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author guozeqi
 * @create 2020-12-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class SingleCallRecordResponse {
    private static final long serialVersionUID = 1L;

    private Long status;
    private String message;
    private SingleCallRecordResponse.Data data;

    @lombok.Data
    public static class Data{
        private static final long serialVersionUID = 1L;

        private String agent;
        private String agent_id;
        private String caller;//主叫号码
        private String callee;//被叫号码
        private String type;//通话的呼叫类型。INBOUND：呼入，OUTBOUND：呼出
        private String hang_up_by;//主动挂断方 REMOTE：客户，LOCAL：坐席
        private Long ring_time;//响铃时长
        private Long call_time;//呼叫时间
        private Long duration;//通话时长
        private String call_uuid;//通话唯一编号
        private String outcome;//通话的呼叫结果。【常规状态】SUCCESS： 成功，FAIL：失败；【开通空号检测状态】SUCCESS：成功，FAIL：无人接听，USER_BUSY：正忙，POWER_OFF：用户关机，SUSPENDED：用户停机，NOT_EXIST：用户空号
        private String sub_type;//通话的呼叫子类型。OUTBOUND：坐席呼叫，INBOUND：坐席接听，ROBOT：机器人计划，MANUAL：人工计划，IVR：智能计划，FCFS：预测式计划
        private String team;//团队名称
    }
}
