package com.ruoyi.robot.mapper;

import com.ruoyi.robot.domain.TLcRobotTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author liurunkai
 * @date 2020-03-18
 */
public interface TLcRobotTaskMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TLcRobotTask selectTLcRobotTaskById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcRobotTask 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLcRobotTask> selectTLcRobotTaskList(TLcRobotTask tLcRobotTask);

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcRobotTask 【请填写功能名称】
     * @return 结果
     */
    public int insertTLcRobotTask(TLcRobotTask tLcRobotTask);

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcRobotTask 【请填写功能名称】
     * @return 结果
     */
    public int updateTLcRobotTask(TLcRobotTask tLcRobotTask);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLcRobotTaskById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcRobotTaskByIds(String[] ids);

    TLcRobotTask selectTLcRobotTaskByRobotTaskId(@Param("robotTaskId") Integer robotTaskId);

    List<TLcRobotTask> selectListByRobotTaskId(Integer robotTaskId);

    TLcRobotTask selectRobotTaskByRobotTaskIdAndTaskId(@Param("robotTaskId") Integer robotTaskId, @Param("taskId") Long taskId);

    List<TLcRobotTask> selectRobotTaskByRobotTaskIdAndPhone(@Param("robotTaskId") Integer robotTaskId, @Param("phone") String phone);

    List<TLcRobotTask> selectCallbackFaild(int callJobId);

    void batchAddRobotTask(@Param("tLcRobotTaskList") List<TLcRobotTask> tLcRobotTaskList);

    void updateTLcRobotTaskByRobotTaskIdAndPhone(TLcRobotTask tLcRobotTask);

    void updateTLcRobotTaskByRobotTaskId(TLcRobotTask tLcRobotTask);

    void updateRobotTaskStatusByRobotTaskId(TLcRobotTask tLcRobotTask);

    List<TLcRobotTask> selectTLcRobotTaskHisList(TLcRobotTask tLcRobotTask);

    TLcRobotTask selectHisTLcRobotTaskById(Long id);

    void batchInsertCallRecord(@Param("robotTaskId") Integer robotTaskId);
}
