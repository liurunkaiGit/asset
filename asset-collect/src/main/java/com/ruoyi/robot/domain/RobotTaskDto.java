package com.ruoyi.robot.domain;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/12 16:34
 */
public class RobotTaskDto {
    /**
     * 当前分页数
     */
    private Integer pageNum;
    /**
     * 数据总条数
     */
    private Integer total;
    /**
     * 分页总数
     */
    private Integer pages;
    /**
     *
     */
    private Integer size;
    /**
     *
     */
    private Integer startRow;
    /**
     * 当前分页数据条数
     */
    private Integer pageSize;
    /**
     *
     */
    private Integer endRow;
    /**
     *
     */
    private List<RobotTask> list;

    // get、set方法

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public List<RobotTask> getList() {
        return list;
    }

    public void setList(List<RobotTask> list) {
        this.list = list;
    }
}
