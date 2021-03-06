package com.ruoyi.robot.service;

import com.ruoyi.robot.domain.TLcRobotTaskPandect;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author liurunkai
 * @date 2020-05-09
 */
public interface ITLcRobotTaskPandectService {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TLcRobotTaskPandect selectTLcRobotTaskPandectById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcRobotTaskPandect 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLcRobotTaskPandect> selectTLcRobotTaskPandectList(TLcRobotTaskPandect tLcRobotTaskPandect);

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcRobotTaskPandect 【请填写功能名称】
     * @return 结果
     */
    public int insertTLcRobotTaskPandect(TLcRobotTaskPandect tLcRobotTaskPandect);

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcRobotTaskPandect 【请填写功能名称】
     * @return 结果
     */
    public int updateTLcRobotTaskPandect(TLcRobotTaskPandect tLcRobotTaskPandect);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcRobotTaskPandectByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLcRobotTaskPandectById(Long id);

    TLcRobotTaskPandect selectTLcRobotTaskPandectByRobotTaskId(Integer robotTaskId);

    void updateRobotTaskPandectStatusByRobotTaskId(TLcRobotTaskPandect robotTaskPandect);

    List<TLcRobotTaskPandect> selectRobotTaskPandectByRobotTaskStatus(Integer robotTaskStatus);

    List<TLcRobotTaskPandect> selectTLcRobotTaskPandectHisList(TLcRobotTaskPandect tLcRobotTaskPandect);

    void updateTLcRobotTaskPandectByRobotTaskId(TLcRobotTaskPandect robotTaskPandect);
}
