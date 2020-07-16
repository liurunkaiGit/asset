package com.ruoyi.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: 录音质量检核系统返回对象
 * @author: liurunkai
 * @Date: 2020/3/10 15:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QualityCheckResponse {
    private Integer code;
    private String message;
    private Boolean success;
    private List<ResponseData> data;
    private Integer total;
    private Integer failed_num;
    private Integer success_num;
    private String traceId;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseData {
        private String message;
        private String source_id;
        private boolean success;
    }
}
