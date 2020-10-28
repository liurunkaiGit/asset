package com.ruoyi.shareproject.daily.mapper;

import com.ruoyi.shareproject.daily.domain.TLpProcessDaily;

import java.util.List;

/**
 * 【日报管理-过程指标】Mapper接口
 * 
 * @author gaohg
 * @date 2020-10-27
 */
public interface TLpProcessDailyMapper 
{
    /**
     * 查询【日报管理-过程指标】
     * 
     * @param id 【日报管理-过程指标】ID
     * @return 【日报管理-过程指标】
     */
    public TLpProcessDaily selectTLpProcessDailyById(Long id);

    /**
     * 查询【日报管理-过程指标】列表
     * 
     * @param tLpProcessDaily 【日报管理-过程指标】
     * @return 【日报管理-过程指标】集合
     */
    public List<TLpProcessDaily> selectTLpProcessDailyList(TLpProcessDaily tLpProcessDaily);

    /**
     * 新增【日报管理-过程指标】
     * 
     * @param tLpProcessDaily 【日报管理-过程指标】
     * @return 结果
     */
    public int insertTLpProcessDaily(TLpProcessDaily tLpProcessDaily);

    /**
     * 修改【日报管理-过程指标】
     * 
     * @param tLpProcessDaily 【日报管理-过程指标】
     * @return 结果
     */
    public int updateTLpProcessDaily(TLpProcessDaily tLpProcessDaily);

    /**
     * 删除【日报管理-过程指标】
     * 
     * @param id 【日报管理-过程指标】ID
     * @return 结果
     */
    public int deleteTLpProcessDailyById(Long id);

    /**删除【日报管理-过程指标】
     * @param dailyId
     * @return
     */
    public int deleteTLpProcessDailyByDailyId(Long dailyId);

    /**
     * 批量删除【日报管理-过程指标】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpProcessDailyByIds(String[] ids);
}
