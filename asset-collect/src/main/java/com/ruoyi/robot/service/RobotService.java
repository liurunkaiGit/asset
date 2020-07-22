package com.ruoyi.robot.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.robot.domain.RobotTask;
import com.ruoyi.robot.domain.RobotTaskDto;
import com.ruoyi.robot.domain.TLcRobotTask;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/10 14:53
 */
public interface RobotService {
    /**
     * 查询机器人任务列表
     *
     * @param robotTask
     * @return
     */
    RobotTaskDto getRobotTaskList(RobotTask robotTask);

    /**
     * 根据id任务拉回
     *
     * @param robotTaskIds
     */
    AjaxResult pullback(String robotTaskIds, Integer robotTaskStatus);

    /**
     * 任务拉回
     *
     */
    void pullback(Integer robotTaskStatus, Integer robotTaskId);

    /**
     * 启动任务
     *
     * @param robotTaskIds
     */
    void start(String robotTaskIds);

    /**
     * 暂停任务
     *
     * @param robotTaskIds
     */
    void pause(String robotTaskIds);

    /**
     * 停止任务
     *
     * @param robotTaskIds
     */
    void stop(String robotTaskIds);

    /**
     * 播放通话录音
     *
     * @param luyinUrl
     */
    void plyaLuyin(String luyinUrl);

    /**
     * 删除任务
     *
     * @param robotTaskIds
     */
    void delete(String robotTaskIds);

    /**
     * 下载通话录音
     *
     * @param ids
     */
    Integer downLoadLuyin(HttpServletResponse response, String ids);

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
     * 下载通话录音
     *
     */
    Integer downLoadRobotRadio(HttpServletResponse response, String luyinUrl);

    /**
     * 通过机器人任务id查询对应的机器人任务
     *
     * @param robotTaskId
     * @return
     */
    TLcRobotTask selectRobotTaskByRobotTaskId(Integer robotTaskId);

    void handCallback(String ids);
}
