package com.ruoyi.batchcall.service;

import com.ruoyi.batchcall.domain.TLcBatchCall;

import java.util.List;

/**
 * 批量外呼任务管理Service接口
 * 
 * @author 封志涛
 * @date 2020-07-02
 */
public interface ITLcBatchCallService 
{
    /**
     * 查询批量外呼任务管理
     * 
     * @param id 批量外呼任务管理ID
     * @return 批量外呼任务管理
     */
    public TLcBatchCall selectTLcBatchCallById(Long id);

    /**
     * 查询批量外呼任务管理列表
     * 
     * @param tLcBatchCall 批量外呼任务管理
     * @return 批量外呼任务管理集合
     */
    public List<TLcBatchCall> selectTLcBatchCallList(TLcBatchCall tLcBatchCall);

    /**
     * 查询本次案件 非本人关系的 电话
     * @param tLcBatchCall
     * @return
     */
    public List<TLcBatchCall> selectTLcBatchCall(TLcBatchCall tLcBatchCall);

    /**
     * 新增批量外呼任务管理
     * 
     * @param tLcBatchCall 批量外呼任务管理
     * @return 结果
     */
    public int insertTLcBatchCall(TLcBatchCall tLcBatchCall);

    /**
     * 修改批量外呼任务管理
     * 
     * @param tLcBatchCall 批量外呼任务管理
     * @return 结果
     */
    public int updateTLcBatchCall(TLcBatchCall tLcBatchCall);

    /**
     * 批量更新 非本人联系方式的 呼叫状态为取消
     * @param tLcBatchCall
     * @return
     */
    public int updateTLcBatchCallBatch(TLcBatchCall tLcBatchCall);
    /**
     * 批量删除批量外呼任务管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcBatchCallByIds(String ids);

    /**
     * 删除批量外呼任务管理信息
     * 
     * @param id 批量外呼任务管理ID
     * @return 结果
     */
    public int deleteTLcBatchCallById(Long id);

    /**
     * 根据批次号 修改任务状态
     * @param batchNo
     * @param sourceTaskStatus 原状态
     * @param targetTaskStatus 目标状态
     * @return
     */
    public int updateBatchCallByBatchNo(String batchNo, Integer sourceTaskStatus, Integer targetTaskStatus);

    /**
     * 根据案件 生成 批量外呼 数据
     * @param isCallOther
     * @param caseNoArray
     * @param importBatchNoArray
     * @param orgId
     * @return
     */
    public int insertTLcBatchCallByTask(String isCallOther, String exonNum, String[] caseNoArray, String[] importBatchNoArray, String orgId);

    /**
     * 根据业务员ID获取最大批次号
     * @param createBy
     * @return
     */
    public int selectMaxBatchNo(String createBy);
}
