package com.ruoyi.robot.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.robot.domain.TLcRobotCallRecordMeteData;
import com.ruoyi.robot.mapper.TLcRobotCallRecordMeteDataMapper;
import com.ruoyi.robot.service.ITLcRobotCallRecordMeteDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author liurunkai
 * @date 2020-02-21
 */
@Service
public class TLcRobotCallRecordMeteDataServiceImpl implements ITLcRobotCallRecordMeteDataService {
    @Autowired
    private TLcRobotCallRecordMeteDataMapper tLcRobotCallRecordMeteDataMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TLcRobotCallRecordMeteData selectTLcRobotCallRecordMeteDataById(Long id) {
        return tLcRobotCallRecordMeteDataMapper.selectTLcRobotCallRecordMeteDataById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcRobotCallRecordMeteData 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TLcRobotCallRecordMeteData> selectTLcRobotCallRecordMeteDataList(TLcRobotCallRecordMeteData tLcRobotCallRecordMeteData) {
        return tLcRobotCallRecordMeteDataMapper.selectTLcRobotCallRecordMeteDataList(tLcRobotCallRecordMeteData);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcRobotCallRecordMeteData 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTLcRobotCallRecordMeteData(TLcRobotCallRecordMeteData tLcRobotCallRecordMeteData) {
        tLcRobotCallRecordMeteData.setCreateTime(DateUtils.getNowDate());
        return tLcRobotCallRecordMeteDataMapper.insertTLcRobotCallRecordMeteData(tLcRobotCallRecordMeteData);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcRobotCallRecordMeteData 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTLcRobotCallRecordMeteData(TLcRobotCallRecordMeteData tLcRobotCallRecordMeteData) {
        return tLcRobotCallRecordMeteDataMapper.updateTLcRobotCallRecordMeteData(tLcRobotCallRecordMeteData);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcRobotCallRecordMeteDataByIds(String ids) {
        return tLcRobotCallRecordMeteDataMapper.deleteTLcRobotCallRecordMeteDataByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTLcRobotCallRecordMeteDataById(Long id) {
        return tLcRobotCallRecordMeteDataMapper.deleteTLcRobotCallRecordMeteDataById(id);
    }
}
