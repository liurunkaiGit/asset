package com.ruoyi.custom.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/4/1 11:27
 */
@Data
@Builder(toBuilder = true)
public class AllCustContact {
    private String contactName;
    private String phone;
    private String certificateNo;
    private Integer relation;
    private long id;
    private String isClose;
}
