package com.ruoyi.shareproject.daily.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.daily.domain.TLpProcessDaily;
import com.ruoyi.shareproject.daily.mapper.TLpProcessDailyMapper;
import com.ruoyi.shareproject.daily.service.ITLpProcessDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 【日报管理-过程指标】Service业务层处理
 * 
 * @author gaohg
 * @date 2020-10-27
 */
@Service
public class TLpProcessDailyServiceImpl implements ITLpProcessDailyService
{
    @Autowired
    private TLpProcessDailyMapper tLpProcessDailyMapper;

    /**
     * 查询【日报管理-过程指标】
     * 
     * @param id 【日报管理-过程指标】ID
     * @return 【日报管理-过程指标】
     */
    @Override
    public TLpProcessDaily selectTLpProcessDailyById(Long id)
    {
        return tLpProcessDailyMapper.selectTLpProcessDailyById(id);
    }

    /**
     * 查询【日报管理-过程指标】列表
     * 
     * @param tLpProcessDaily 【日报管理-过程指标】
     * @return 【日报管理-过程指标】
     */
    @Override
    public List<TLpProcessDaily> selectTLpProcessDailyList(TLpProcessDaily tLpProcessDaily)
    {
        return tLpProcessDailyMapper.selectTLpProcessDailyList(tLpProcessDaily);
    }

    /**
     * 新增【日报管理-过程指标】
     * 
     * @param tLpProcessDaily 【日报管理-过程指标】
     * @return 结果
     */
    @Override
    public int insertTLpProcessDaily(TLpProcessDaily tLpProcessDaily)
    {
        tLpProcessDaily.setCreateTime(DateUtils.getNowDate());
        return tLpProcessDailyMapper.insertTLpProcessDaily(tLpProcessDaily);
    }

    /**
     * 修改【日报管理-过程指标】
     * 
     * @param tLpProcessDaily 【日报管理-过程指标】
     * @return 结果
     */
    @Override
    public int updateTLpProcessDaily(TLpProcessDaily tLpProcessDaily)
    {
        tLpProcessDaily.setUpdateTime(DateUtils.getNowDate());
        return tLpProcessDailyMapper.updateTLpProcessDaily(tLpProcessDaily);
    }

    /**
     * 删除【日报管理-过程指标】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLpProcessDailyByIds(String ids)
    {
        return tLpProcessDailyMapper.deleteTLpProcessDailyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【日报管理-过程指标】信息
     * 
     * @param id 【日报管理-过程指标】ID
     * @return 结果
     */
    @Override
    public int deleteTLpProcessDailyById(Long id)
    {
        return tLpProcessDailyMapper.deleteTLpProcessDailyById(id);
    }
}
