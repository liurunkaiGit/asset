package com.ruoyi.task.domain.preTestCall.createTask;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author guozeqi
 * @create 2020-12-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class CreateTaskRequestEntity {
    private static final long serialVersionUID = 1L;

    private String apikey;
    private String name;//个人外呼计划名称
    private Long account_id;//计划执行坐席的坐席编号
    private String caller;//执行计划所用外呼线路，如未填写默认使用随机号码池线路
    private Boolean is_distinct;
    private String priority;
    private List<Content> content;

    @Data
    public static class Content{
        private static final long serialVersionUID = 1L;
        private String U_phone;
        private String U_TAG;
    }
}
