package com.ruoyi.shareproject.daily.mapper;

import com.ruoyi.shareproject.daily.domain.TLpProjectInformationDaily;

import java.util.List;

/**
 * 【项目日报-项目信息】Mapper接口
 * 
 * @author gaohg
 * @date 2020-10-26
 */
public interface TLpProjectInformationDailyMapper 
{
    /**
     * 查询【项目日报-项目信息】
     * 
     * @param id 【项目日报-项目信息】ID
     * @return 【项目日报-项目信息】
     */
    public TLpProjectInformationDaily selectTLpProjectInformationDailyById(Long id);

    /**
     * 查询【项目日报-项目信息】列表
     * 
     * @param tLpProjectInformationDaily 【项目日报-项目信息】
     * @return 【项目日报-项目信息】集合
     */
    public List<TLpProjectInformationDaily> selectTLpProjectInformationDailyList(TLpProjectInformationDaily tLpProjectInformationDaily);

    /**
     * 新增【项目日报-项目信息】
     * 
     * @param tLpProjectInformationDaily 【项目日报-项目信息】
     * @return 结果
     */
    public int insertTLpProjectInformationDaily(TLpProjectInformationDaily tLpProjectInformationDaily);

    /**
     * 修改【项目日报-项目信息】
     * 
     * @param tLpProjectInformationDaily 【项目日报-项目信息】
     * @return 结果
     */
    public int updateTLpProjectInformationDaily(TLpProjectInformationDaily tLpProjectInformationDaily);

    /**
     * 删除【项目日报-项目信息】
     * 
     * @param id 【项目日报-项目信息】ID
     * @return 结果
     */
    public int deleteTLpProjectInformationDailyById(Long id);

    /**删除【项目日报-项目信息】
     * @param dailyId 项目日报id
     * @return
     */
    public int deleteTLpProjectInformationDailyByDailyId(Long dailyId);

    /**
     * 批量删除【项目日报-项目信息】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpProjectInformationDailyByIds(String[] ids);
}
