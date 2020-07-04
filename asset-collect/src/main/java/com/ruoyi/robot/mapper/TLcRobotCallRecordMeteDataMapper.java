package com.ruoyi.robot.mapper;

import com.ruoyi.robot.domain.TLcRobotCallRecordMeteData;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author liurunkai
 * @date 2020-02-20
 */
public interface TLcRobotCallRecordMeteDataMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TLcRobotCallRecordMeteData selectTLcRobotCallRecordMeteDataById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcRobotCallRecordMeteData 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLcRobotCallRecordMeteData> selectTLcRobotCallRecordMeteDataList(TLcRobotCallRecordMeteData tLcRobotCallRecordMeteData);

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcRobotCallRecordMeteData 【请填写功能名称】
     * @return 结果
     */
    public int insertTLcRobotCallRecordMeteData(TLcRobotCallRecordMeteData tLcRobotCallRecordMeteData);

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcRobotCallRecordMeteData 【请填写功能名称】
     * @return 结果
     */
    public int updateTLcRobotCallRecordMeteData(TLcRobotCallRecordMeteData tLcRobotCallRecordMeteData);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLcRobotCallRecordMeteDataById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcRobotCallRecordMeteDataByIds(String[] ids);
}
