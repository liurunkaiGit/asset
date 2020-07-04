package com.ruoyi.robot.domain;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/12 16:34
 */
@Data
public class UnCallBackDto {
    /**
     * 当前分页数
     */
    private Integer pageNum;
    /**
     * 数据总条数
     */
    private Integer total;
    /**
     * 当前分页数据条数
     */
    private Integer pageSize;
    /**
     * 分页总数
     */
    private Integer pages;
    /**
     * 查询回调失败记录结果集
     */
    private List<CallbackCallDetail> list;

}
