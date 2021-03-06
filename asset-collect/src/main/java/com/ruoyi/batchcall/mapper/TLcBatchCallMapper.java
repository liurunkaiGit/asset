package com.ruoyi.batchcall.mapper;

import com.ruoyi.batchcall.domain.TLcBatchCall;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 批量外呼任务管理Mapper接口
 * 
 * @author 封志涛
 * @date 2020-07-02
 */
public interface TLcBatchCallMapper 
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
     * 查询批量外呼任务管理列表 案件非本人 待外呼或正在呼
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
     * 删除批量外呼任务管理
     * 
     * @param id 批量外呼任务管理ID
     * @return 结果
     */
    public int deleteTLcBatchCallById(Long id);

    /**
     * 批量删除批量外呼任务管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcBatchCallByIds(String[] ids);

    /**
     * 根据创建人 修改任务状态
     * @param createBy
     * @param sourceTaskStatus 原状态
     * @param targetTaskStatus 目标状态
     * @return
     */
    public int updateBatchCallByBatchNo(@Param("createBy")String createBy, @Param("sourceTaskStatus")Integer sourceTaskStatus, @Param("targetTaskStatus")Integer targetTaskStatus);

    /**
     * 根据业务员ID获取最大批次号
     * @param createBy
     * @return
     */
    public Map<String,Object> selectMaxBatchNo(String createBy);

    List<TLcBatchCall> selectBatchCallHisList(TLcBatchCall tLcBatchCall);
}
