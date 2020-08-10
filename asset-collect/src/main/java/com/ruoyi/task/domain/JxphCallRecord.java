package com.ruoyi.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/8/10 9:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JxphCallRecord implements Serializable {

    private String name;
    private String mobile;
    private String relType;
    private String teletphoneCode;
    private String remark;
    private Long userId;
    private String customerConnectTime;
    private String tsrConnectTime;
    private BigDecimal duration;
    private String recordUrl;

}
