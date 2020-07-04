package com.ruoyi.task.domain;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: 录音质量检核
 * @author: liurunkai
 * @Date: 2020/3/10 15:35
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class RadioQualityCheck {

    private List<RequestData> data;
    private String realmName = "cs";
    private String projectName;

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public class RequestData {
        private String source_id;
        private String url;
        private Long timestamp;
        private String category;
        private Staff staff;
        private Customer customer;
    }

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public class Staff{
        private String name;
        private String id;
    }

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public class Customer{
        private String id;
        private String phone;
        private String name;
//        private String source;
//        private String level;
    }
}
