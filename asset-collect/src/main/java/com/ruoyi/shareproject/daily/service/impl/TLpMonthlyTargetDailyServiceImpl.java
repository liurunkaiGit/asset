package com.ruoyi.shareproject.daily.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.daily.domain.TLpMonthlyTargetDaily;
import com.ruoyi.shareproject.daily.mapper.TLpMonthlyTargetDailyMapper;
import com.ruoyi.shareproject.daily.service.ITLpMonthlyTargetDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 【日报管理-月度指标】Service业务层处理
 * 
 * @author gaohg
 * @date 2020-10-26
 */
@Service
public class TLpMonthlyTargetDailyServiceImpl implements ITLpMonthlyTargetDailyService
{
    @Autowired
    private TLpMonthlyTargetDailyMapper tLpMonthlyTargetDailyMapper;

    /**
     * 查询【日报管理-月度指标】
     * 
     * @param id 【日报管理-月度指标】ID
     * @return 【日报管理-月度指标】
     */
    @Override
    public TLpMonthlyTargetDaily selectTLpMonthlyTargetDailyById(Long id)
    {
        return tLpMonthlyTargetDailyMapper.selectTLpMonthlyTargetDailyById(id);
    }

    /**
     * 查询【日报管理-月度指标】列表
     * 
     * @param tLpMonthlyTargetDaily 【日报管理-月度指标】
     * @return 【日报管理-月度指标】
     */
    @Override
    public List<TLpMonthlyTargetDaily> selectTLpMonthlyTargetDailyList(TLpMonthlyTargetDaily tLpMonthlyTargetDaily)
    {
        return tLpMonthlyTargetDailyMapper.selectTLpMonthlyTargetDailyList(tLpMonthlyTargetDaily);
    }

    /**
     * 新增【日报管理-月度指标】
     * 
     * @param tLpMonthlyTargetDaily 【日报管理-月度指标】
     * @return 结果
     */
    @Override
    public int insertTLpMonthlyTargetDaily(TLpMonthlyTargetDaily tLpMonthlyTargetDaily)
    {
        tLpMonthlyTargetDaily.setCreateTime(DateUtils.getNowDate());
        return tLpMonthlyTargetDailyMapper.insertTLpMonthlyTargetDaily(tLpMonthlyTargetDaily);
    }

    /**
     * 修改【日报管理-月度指标】
     * 
     * @param tLpMonthlyTargetDaily 【日报管理-月度指标】
     * @return 结果
     */
    @Override
    public int updateTLpMonthlyTargetDaily(TLpMonthlyTargetDaily tLpMonthlyTargetDaily)
    {
        tLpMonthlyTargetDaily.setUpdateTime(DateUtils.getNowDate());
        return tLpMonthlyTargetDailyMapper.updateTLpMonthlyTargetDaily(tLpMonthlyTargetDaily);
    }

    /**
     * 删除【日报管理-月度指标】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLpMonthlyTargetDailyByIds(String ids)
    {
        return tLpMonthlyTargetDailyMapper.deleteTLpMonthlyTargetDailyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【日报管理-月度指标】信息
     * 
     * @param id 【日报管理-月度指标】ID
     * @return 结果
     */
    @Override
    public int deleteTLpMonthlyTargetDailyById(Long id)
    {
        return tLpMonthlyTargetDailyMapper.deleteTLpMonthlyTargetDailyById(id);
    }
}
