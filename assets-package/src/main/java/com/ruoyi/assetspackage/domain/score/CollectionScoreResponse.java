package com.ruoyi.assetspackage.domain.score;

import lombok.Data;

import java.io.Serializable;

/**
 * @author guozeqi
 * @create 2020-06-22
 */
@Data
public class CollectionScoreResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status;
    private String desc;
    private String data;
}
