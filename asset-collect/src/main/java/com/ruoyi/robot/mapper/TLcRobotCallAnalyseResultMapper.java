package com.ruoyi.robot.mapper;

import com.ruoyi.robot.domain.TLcRobotCallAnalyseResult;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author liurunkai
 * @date 2020-02-20
 */
public interface TLcRobotCallAnalyseResultMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TLcRobotCallAnalyseResult selectTLcRobotCallAnalyseResultById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcRobotCallAnalyseResult 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLcRobotCallAnalyseResult> selectTLcRobotCallAnalyseResultList(TLcRobotCallAnalyseResult tLcRobotCallAnalyseResult);

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcRobotCallAnalyseResult 【请填写功能名称】
     * @return 结果
     */
    public int insertTLcRobotCallAnalyseResult(TLcRobotCallAnalyseResult tLcRobotCallAnalyseResult);

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcRobotCallAnalyseResult 【请填写功能名称】
     * @return 结果
     */
    public int updateTLcRobotCallAnalyseResult(TLcRobotCallAnalyseResult tLcRobotCallAnalyseResult);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLcRobotCallAnalyseResultById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcRobotCallAnalyseResultByIds(String[] ids);
}
