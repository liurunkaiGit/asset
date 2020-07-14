package com.ruoyi.batchcall.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 批量外呼任务管理对象 t_lc_batch_call
 * 
 * @author 封志涛
 * @date 2020-07-02
 */
@Data
public class TLcBatchCall extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    public static final int WHZ = 0;//外呼中
    public static final int DWH = 1;//待外呼
    public static final int ZT = 2;//暂停
    public static final int YQX = 3;//已取消
    public static final int YWC = 4;//已完成



    public static final String SHOUJI = "1";
    public static final String GUHUA = "2";


    /** 主键ID */
    private Long id;

    /** 批次号，自增长 */
    @Excel(name = "批次号，自增长")
    private Integer batchNo;

    /** 案件号 */
    @Excel(name = "案件号")
    private String caseNo;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phone;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String contactName;

    /** 与本人关系 */
    @Excel(name = "与本人关系")
    private Integer contactRelation;

    /** 电话类型：1 手机；2 固话 */
    @Excel(name = "电话类型：1 手机；2 固话")
    private String phoneType;

    /** 任务状态 */
    /**
     * 外呼中：0
     *
     * 待外呼：1
     *
     * 已完成：2
     *
     * 已取消：3
     *
     * 已暂停:4
     */
    @Excel(name = "任务状态")
    private Integer taskStatus;

    /**
     * 外呼任务状态 集合
     */
    private List<Integer> taskStatusList;
    /**
     * 外显号码
     */
    private String exonNum;
    /**
     * 委托机构ID
     */
    private String orgId;
    /**
     * 案件导入批次编号
     */
    private String importBatchNo;
    /**
     * 备注
     */
    private String remark;
    /**
     * 虚字段：是否只查询排序第一的 一条数据
     */
    private String isOnlyOne;

}
