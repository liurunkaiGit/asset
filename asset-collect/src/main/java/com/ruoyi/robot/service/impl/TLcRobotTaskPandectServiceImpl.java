package com.ruoyi.robot.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.robot.domain.TLcRobotTaskPandect;
import com.ruoyi.robot.mapper.TLcRobotTaskPandectMapper;
import com.ruoyi.robot.service.ITLcRobotTaskPandectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author liurunkai
 * @date 2020-05-09
 */
@Service
public class TLcRobotTaskPandectServiceImpl implements ITLcRobotTaskPandectService {
    @Autowired
    private TLcRobotTaskPandectMapper tLcRobotTaskPandectMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TLcRobotTaskPandect selectTLcRobotTaskPandectById(Long id) {
        return tLcRobotTaskPandectMapper.selectTLcRobotTaskPandectById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcRobotTaskPandect 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TLcRobotTaskPandect> selectTLcRobotTaskPandectList(TLcRobotTaskPandect tLcRobotTaskPandect) {
        return tLcRobotTaskPandectMapper.selectTLcRobotTaskPandectList(tLcRobotTaskPandect);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcRobotTaskPandect 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTLcRobotTaskPandect(TLcRobotTaskPandect tLcRobotTaskPandect) {
        tLcRobotTaskPandect.setCreateTime(DateUtils.getNowDate());
        return tLcRobotTaskPandectMapper.insertTLcRobotTaskPandect(tLcRobotTaskPandect);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcRobotTaskPandect 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTLcRobotTaskPandect(TLcRobotTaskPandect tLcRobotTaskPandect) {
        return tLcRobotTaskPandectMapper.updateTLcRobotTaskPandect(tLcRobotTaskPandect);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcRobotTaskPandectByIds(String ids) {
        return tLcRobotTaskPandectMapper.deleteTLcRobotTaskPandectByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTLcRobotTaskPandectById(Long id) {
        return tLcRobotTaskPandectMapper.deleteTLcRobotTaskPandectById(id);
    }

    @Override
    public TLcRobotTaskPandect selectTLcRobotTaskPandectByRobotTaskId(Integer robotTaskId) {
        return tLcRobotTaskPandectMapper.selectTLcRobotTaskPandectByRobotTaskId(robotTaskId);
    }

    @Override
    public void updateRobotTaskPandectStatusByRobotTaskId(TLcRobotTaskPandect robotTaskPandect) {
        tLcRobotTaskPandectMapper.updateRobotTaskPandectStatusByRobotTaskId(robotTaskPandect);
    }
}
