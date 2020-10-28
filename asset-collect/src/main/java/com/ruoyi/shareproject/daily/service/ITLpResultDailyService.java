package com.ruoyi.shareproject.daily.service;

import com.ruoyi.shareproject.daily.domain.TLpResultDaily;

import java.util.List;

/**
 * 【日报管理-结果指标】Service接口
 * 
 * @author gaohg
 * @date 2020-10-27
 */
public interface ITLpResultDailyService 
{
    /**
     * 查询【日报管理-结果指标】
     * 
     * @param id 【日报管理-结果指标】ID
     * @return 【日报管理-结果指标】
     */
    public TLpResultDaily selectTLpResultDailyById(Long id);

    /**
     * 查询【日报管理-结果指标】列表
     * 
     * @param tLpResultDaily 【日报管理-结果指标】
     * @return 【日报管理-结果指标】集合
     */
    public List<TLpResultDaily> selectTLpResultDailyList(TLpResultDaily tLpResultDaily);

    /**
     * 新增【日报管理-结果指标】
     * 
     * @param tLpResultDaily 【日报管理-结果指标】
     * @return 结果
     */
    public int insertTLpResultDaily(TLpResultDaily tLpResultDaily);

    /**
     * 修改【日报管理-结果指标】
     * 
     * @param tLpResultDaily 【日报管理-结果指标】
     * @return 结果
     */
    public int updateTLpResultDaily(TLpResultDaily tLpResultDaily);

    /**
     * 批量删除【日报管理-结果指标】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpResultDailyByIds(String ids);

    /**
     * 删除【日报管理-结果指标】信息
     * 
     * @param id 【日报管理-结果指标】ID
     * @return 结果
     */
    public int deleteTLpResultDailyById(Long id);
}
