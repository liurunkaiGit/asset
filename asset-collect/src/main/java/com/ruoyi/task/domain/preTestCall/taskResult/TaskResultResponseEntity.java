package com.ruoyi.task.domain.preTestCall.taskResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author guozeqi
 * @create 2020-12-25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class TaskResultResponseEntity {
    private static final long serialVersionUID = 1L;

    private Long status;
    private String message;
    private String error_code;
    private TaskResultResponseEntity.Data data;

    @lombok.Data
    public static class Data{
        private static final long serialVersionUID = 1L;

        private Long total_pages;
        private Long total_elements;
        private List<ResultEntity> campaigns;

    }


}
