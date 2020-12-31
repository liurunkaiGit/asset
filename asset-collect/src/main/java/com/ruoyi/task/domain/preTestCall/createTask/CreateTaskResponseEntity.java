package com.ruoyi.task.domain.preTestCall.createTask;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author guozeqi
 * @create 2020-12-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class CreateTaskResponseEntity {
    private static final long serialVersionUID = 1L;

    private Long status;
    private String message;
    private Data data;

    @lombok.Data
    public static class Data{
        private static final long serialVersionUID = 1L;

        private String error_code;
        private Long id;//此处等同于campaign_id，外呼计划编号
        private String name;//外呼计划的名称
        private Long created_time;//外呼计划的创建时间
        private Long account_id;//计划执行坐席的坐席编号
        private String caller;//主叫号码
        private String status;//计划状态
        private Long total_count;//计划数据总数
        private String priority;//计划执行优先级
    }

}
