package com.ruoyi.assetspackage.domain.score;

import lombok.Data;

import java.io.Serializable;

/**
 * @author guozeqi
 * @create 2020-06-22
 */
@Data
public class CollectionScoreRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String bizLine;
    private String version;
    private String time;
    private String reqId;
    private String sign;
    private String query;


    @Data
    public class QueryData{
        private String name;
        private String card;
        private String phone;
        private String model;
        private String version;
        private int dueDay;
        private float dueMoney;
        private String dueDate;
        private String orgStr;
    }

}
