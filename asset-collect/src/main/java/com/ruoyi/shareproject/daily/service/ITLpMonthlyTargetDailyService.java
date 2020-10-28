package com.ruoyi.shareproject.daily.service;

import com.ruoyi.shareproject.daily.domain.TLpMonthlyTargetDaily;

import java.util.List;

/**
 * 【日报管理-月度指标】Service接口
 * 
 * @author gaohg
 * @date 2020-10-26
 */
public interface ITLpMonthlyTargetDailyService 
{
    /**
     * 查询【日报管理-月度指标】
     * 
     * @param id 【日报管理-月度指标】ID
     * @return 【日报管理-月度指标】
     */
    public TLpMonthlyTargetDaily selectTLpMonthlyTargetDailyById(Long id);

    /**
     * 查询【日报管理-月度指标】列表
     * 
     * @param tLpMonthlyTargetDaily 【日报管理-月度指标】
     * @return 【日报管理-月度指标】集合
     */
    public List<TLpMonthlyTargetDaily> selectTLpMonthlyTargetDailyList(TLpMonthlyTargetDaily tLpMonthlyTargetDaily);

    /**
     * 新增【日报管理-月度指标】
     * 
     * @param tLpMonthlyTargetDaily 【日报管理-月度指标】
     * @return 结果
     */
    public int insertTLpMonthlyTargetDaily(TLpMonthlyTargetDaily tLpMonthlyTargetDaily);

    /**
     * 修改【日报管理-月度指标】
     * 
     * @param tLpMonthlyTargetDaily 【日报管理-月度指标】
     * @return 结果
     */
    public int updateTLpMonthlyTargetDaily(TLpMonthlyTargetDaily tLpMonthlyTargetDaily);

    /**
     * 批量删除【日报管理-月度指标】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpMonthlyTargetDailyByIds(String ids);

    /**
     * 删除【日报管理-月度指标】信息
     * 
     * @param id 【日报管理-月度指标】ID
     * @return 结果
     */
    public int deleteTLpMonthlyTargetDailyById(Long id);
}
