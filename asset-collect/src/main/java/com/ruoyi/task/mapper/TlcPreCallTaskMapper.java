package com.ruoyi.task.mapper;


import com.ruoyi.task.domain.TlcPreCallTask;

import java.util.List;
import java.util.Map;

/**
 * 预测式外呼任务Mapper接口
 * 
 * @author guozeqi
 * @date 2020-12-15
 */
public interface TlcPreCallTaskMapper 
{
    /**
     * 查询预测式外呼任务
     * 
     * @param id 预测式外呼任务ID
     * @return 预测式外呼任务
     */
    public TlcPreCallTask selectTlcPreCallTaskById(Long id);

    /**
     * 查询预测式外呼任务列表
     * 
     * @param tlcPreCallTask 预测式外呼任务
     * @return 预测式外呼任务集合
     */
    public List<TlcPreCallTask> selectTlcPreCallTaskList(TlcPreCallTask tlcPreCallTask);

    public List<TlcPreCallTask> selectNotExecPlanByLoginName(String loginName);

    public TlcPreCallTask selectNotExecPlanByLoginName2(String loginName);

    public List<TlcPreCallTask> selectAllNotExecPlan();

    /**
     * 新增预测式外呼任务
     * 
     * @param tlcPreCallTask 预测式外呼任务
     * @return 结果
     */
    public int insertTlcPreCallTask(TlcPreCallTask tlcPreCallTask);

    public void batchAddPreCallTask(List<TlcPreCallTask> list);

    /**
     * 修改预测式外呼任务
     * 
     * @param tlcPreCallTask 预测式外呼任务
     * @return 结果
     */
    public int updateTlcPreCallTask(TlcPreCallTask tlcPreCallTask);

    /**
     * 更新未完成的为 已完成 未接通状态
     */
    public void updateNotConnect();

    /**
     * 更新执行状态
     * @param tlcPreCallTask
     */
    public void updateExecStatus(TlcPreCallTask tlcPreCallTask);

    public void updateExecStatus2(TlcPreCallTask tlcPreCallTask);

    public void updateTlcPreCallTask2(TlcPreCallTask tlcPreCallTask);


    /**
     * 删除预测式外呼任务
     * 
     * @param id 预测式外呼任务ID
     * @return 结果
     */
    public int deleteTlcPreCallTaskById(Long id);

    /**
     * 批量删除预测式外呼任务
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTlcPreCallTaskByIds(String[] ids);

    public Integer selectMaxBatchNo();
}
