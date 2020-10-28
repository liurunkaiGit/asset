package com.ruoyi.shareproject.daily.mapper;

import com.ruoyi.shareproject.daily.domain.TLpMonthlyTargetDaily;

import java.util.List;

/**
 * 【项目日报-月度信息】Mapper接口
 * 
 * @author gaohg
 * @date 2020-10-26
 */
public interface TLpMonthlyTargetDailyMapper 
{
    /**
     * 查询【项目日报-月度信息】
     * 
     * @param id 【项目日报-月度信息】ID
     * @return 【项目日报-月度信息】
     */
    public TLpMonthlyTargetDaily selectTLpMonthlyTargetDailyById(Long id);

    /**
     * 查询【项目日报-月度信息】列表
     * 
     * @param tLpMonthlyTargetDaily 【项目日报-月度信息】
     * @return 【项目日报-月度信息】集合
     */
    public List<TLpMonthlyTargetDaily> selectTLpMonthlyTargetDailyList(TLpMonthlyTargetDaily tLpMonthlyTargetDaily);

    /**
     * 新增【项目日报-月度信息】
     * 
     * @param tLpMonthlyTargetDaily 【项目日报-月度信息】
     * @return 结果
     */
    public int insertTLpMonthlyTargetDaily(TLpMonthlyTargetDaily tLpMonthlyTargetDaily);

    /**
     * 修改【项目日报-月度信息】
     * 
     * @param tLpMonthlyTargetDaily 【项目日报-月度信息】
     * @return 结果
     */
    public int updateTLpMonthlyTargetDaily(TLpMonthlyTargetDaily tLpMonthlyTargetDaily);

    /**
     * 删除【项目日报-月度信息】
     * 
     * @param id 【项目日报-月度信息】ID
     * @return 结果
     */
    public int deleteTLpMonthlyTargetDailyById(Long id);

    /**删除【项目日报-月度信息】
     * @param dailyId
     * @return
     */
    public int deleteTLpMonthlyTargetDailyByDailyId(Long dailyId);

    /**
     * 批量删除【项目日报-月度信息】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpMonthlyTargetDailyByIds(String[] ids);
}
