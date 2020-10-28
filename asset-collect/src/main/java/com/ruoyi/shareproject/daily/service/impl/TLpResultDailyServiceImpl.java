package com.ruoyi.shareproject.daily.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.daily.domain.TLpResultDaily;
import com.ruoyi.shareproject.daily.mapper.TLpResultDailyMapper;
import com.ruoyi.shareproject.daily.service.ITLpResultDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 【日报管理-结果指标】Service业务层处理
 * 
 * @author gaohg
 * @date 2020-10-27
 */
@Service
public class TLpResultDailyServiceImpl implements ITLpResultDailyService
{
    @Autowired
    private TLpResultDailyMapper tLpResultDailyMapper;

    /**
     * 查询【日报管理-结果指标】
     * 
     * @param id 【日报管理-结果指标】ID
     * @return 【日报管理-结果指标】
     */
    @Override
    public TLpResultDaily selectTLpResultDailyById(Long id)
    {
        return tLpResultDailyMapper.selectTLpResultDailyById(id);
    }

    /**
     * 查询【日报管理-结果指标】列表
     * 
     * @param tLpResultDaily 【日报管理-结果指标】
     * @return 【日报管理-结果指标】
     */
    @Override
    public List<TLpResultDaily> selectTLpResultDailyList(TLpResultDaily tLpResultDaily)
    {
        return tLpResultDailyMapper.selectTLpResultDailyList(tLpResultDaily);
    }

    /**
     * 新增【日报管理-结果指标】
     * 
     * @param tLpResultDaily 【日报管理-结果指标】
     * @return 结果
     */
    @Override
    public int insertTLpResultDaily(TLpResultDaily tLpResultDaily)
    {
        tLpResultDaily.setCreateTime(DateUtils.getNowDate());
        return tLpResultDailyMapper.insertTLpResultDaily(tLpResultDaily);
    }

    /**
     * 修改【日报管理-结果指标】
     * 
     * @param tLpResultDaily 【日报管理-结果指标】
     * @return 结果
     */
    @Override
    public int updateTLpResultDaily(TLpResultDaily tLpResultDaily)
    {
        tLpResultDaily.setUpdateTime(DateUtils.getNowDate());
        return tLpResultDailyMapper.updateTLpResultDaily(tLpResultDaily);
    }

    /**
     * 删除【日报管理-结果指标】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLpResultDailyByIds(String ids)
    {
        return tLpResultDailyMapper.deleteTLpResultDailyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【日报管理-结果指标】信息
     * 
     * @param id 【日报管理-结果指标】ID
     * @return 结果
     */
    @Override
    public int deleteTLpResultDailyById(Long id)
    {
        return tLpResultDailyMapper.deleteTLpResultDailyById(id);
    }
}
