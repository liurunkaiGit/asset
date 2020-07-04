package com.ruoyi.robot.mapper;

import com.ruoyi.robot.domain.TLcRobotCallDetail;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author liurunkai
 * @date 2020-02-20
 */
public interface TLcRobotCallDetailMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TLcRobotCallDetail selectTLcRobotCallDetailById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcRobotCallDetail 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLcRobotCallDetail> selectTLcRobotCallDetailList(TLcRobotCallDetail tLcRobotCallDetail);

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcRobotCallDetail 【请填写功能名称】
     * @return 结果
     */
    public int insertTLcRobotCallDetail(TLcRobotCallDetail tLcRobotCallDetail);

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcRobotCallDetail 【请填写功能名称】
     * @return 结果
     */
    public int updateTLcRobotCallDetail(TLcRobotCallDetail tLcRobotCallDetail);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLcRobotCallDetailById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcRobotCallDetailByIds(String[] ids);
}
