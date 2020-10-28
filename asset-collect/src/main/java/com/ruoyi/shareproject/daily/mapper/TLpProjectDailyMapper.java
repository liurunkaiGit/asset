package com.ruoyi.shareproject.daily.mapper;

import com.ruoyi.shareproject.daily.domain.TLpProjectDaily;

import java.util.List;

/**
 * 【项目日报】Mapper接口
 * 
 * @author gaohg
 * @date 2020-10-26
 */
public interface TLpProjectDailyMapper 
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
     * 删除【项目日报】
     * 
     * @param id 【项目日报】ID
     * @return 结果
     */
    public int deleteTLpProjectDailyById(Long id);

    /**
     * 批量删除【项目日报】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpProjectDailyByIds(String[] ids);
}
