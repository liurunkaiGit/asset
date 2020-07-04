package com.ruoyi.robot.service;

import com.ruoyi.robot.domain.CallContent;
import com.ruoyi.robot.domain.TLcRobotTask;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author liurunkai
 * @date 2020-03-18
 */
public interface ITLcRobotTaskService {
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
    public List<TLcRobotTask> selectTLcRobotTaskList(TLcRobotTask tLcRobotTask, HttpServletRequest request);

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
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcRobotTaskByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLcRobotTaskById(Long id);

    /**
     * 根据机器人任务id查询对应的机器人任务
     *
     * @param callJobId
     * @return
     */
    TLcRobotTask selectTLcRobotTaskByRobotTaskId(Integer callJobId);

    /**
     * 查看通话内容
     *
     * @param id
     * @return
     */
    List<CallContent> viewCallContent(String id);

    List<TLcRobotTask> selectListByRobotTaskId(Integer taskId);

    TLcRobotTask selectRobotTaskByRobotTaskIdAndTaskId(Integer robotTaskId, Long taskId);

    TLcRobotTask selectRobotTaskByRobotTaskIdAndPhone(Integer callJobId, String customerTelephone);

    List<TLcRobotTask> selectCallbackFaild(int callJobId);

    void batchAddRobotTask(List<TLcRobotTask> tLcRobotTaskList);

    void updateTLcRobotTaskByRobotTaskIdAndPhone(TLcRobotTask tLcRobotTask);

    void updateRobotTaskStatusByRobotTaskId(TLcRobotTask tLcRobotTask);
}
