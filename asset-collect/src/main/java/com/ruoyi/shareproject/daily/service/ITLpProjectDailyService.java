package com.ruoyi.shareproject.daily.service;

import com.ruoyi.shareproject.daily.domain.TLpProjectDaily;

import java.util.Date;
import java.util.List;

/**
 * 【项目日报】Service接口
 * 
 * @author gaohg
 * @date 2020-10-26
 */
public interface ITLpProjectDailyService 
{
    /**
     * 查询【项目日报】
     * 
     * @param id 【项目日报】ID
     * @return 【项目日报】
     */
    public TLpProjectDaily selectTLpProjectDailyById(Long id);

    /**
     * 查询【项目日报】列表
     * 
     * @param tLpProjectDaily 【项目日报】
     * @return 【项目日报】集合
     */
    public List<TLpProjectDaily> selectTLpProjectDailyList(TLpProjectDaily tLpProjectDaily);

    /**
     * 新增【项目日报】
     * 
     * @param tLpProjectDaily 【项目日报】
     * @return 结果
     */
    public int insertTLpProjectDaily(TLpProjectDaily tLpProjectDaily);

    /**
     * 修改【项目日报】
     * 
     * @param tLpProjectDaily 【项目日报】
     * @return 结果
     */
    public int updateTLpProjectDaily(TLpProjectDaily tLpProjectDaily);

    /**
     * 查询指标是否都有数据 过程指标、结果指标、月度指标
     * @param proId
     * @param date
     * @return
     */
    public int findZhibiao(Long proId, Date date);

    /**
     * 批量删除【项目日报】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpProjectDailyByIds(String ids);

    /**
     * 删除【项目日报】信息
     * 
     * @param id 【项目日报】ID
     * @return 结果
     */
    public int deleteTLpProjectDailyById(Long id);
}
