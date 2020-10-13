package com.ruoyi.shareproject.information.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @description: 共享信息管理-基础信息管理-中心信息实体类
 * @auther 麻超
 * @date 2020/10/12
 */
@Data
public class TLpInformationCenter extends BaseEntity {

    /**
     * 主键
     */
    @Excel(name = "编号")
    private Long id;
    /**
     * 中心名称
     */
    @Excel(name = "中心名称")
    private String centerName;
    /**
     * 中心地址
     */
    @Excel(name = "中心地址")
    private String centerAddress;

    /**
     * 工位数量
     */
    @Excel(name = "工位数量")
    private Integer workSeatNum;

    /**
     * 坐席数量
     */
    @Excel(name = "坐席数量")
    private Integer agentNum;
    /**
     * 培训室
     */
    @Excel(name = "培训室")
    private String trainningRoom;

    /**
     * 休息区
     */
    @Excel(name = "休息区")
    private String restArea;

    /**
     * 满席率
     */
    @Excel(name = "满席率")
    private String seatRate;

    /**
     * 负责人
     */
    @Excel(name = "负责人")
    private String leader;

    /**
     * 更新者
     */
    @Excel(name = "操作人")
    private String updateBy;

    /**
     * 更新者
     */
    @Excel(name = "操作时间",width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;
    /**
     * 修改时间开始 (搜索条件)
     */
    private Date beginUpdateTime;
    /**
     * 修改时间结束 (搜索条件)
     */
    private Date endUpdateTime;

    private String centerIds;
}
