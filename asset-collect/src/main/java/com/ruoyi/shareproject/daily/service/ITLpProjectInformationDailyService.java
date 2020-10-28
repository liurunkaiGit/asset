package com.ruoyi.shareproject.daily.service;

import com.ruoyi.shareproject.daily.domain.TLpProjectInformationDaily;

import java.util.List;

/**
 * 【日报管理-项目信息】Service接口
 * 
 * @author gaohg
 * @date 2020-10-26
 */
public interface ITLpProjectInformationDailyService 
{
    /**
     * 查询【日报管理-项目信息】
     * 
     * @param id 【日报管理-项目信息】ID
     * @return 【日报管理-项目信息】
     */
    public TLpProjectInformationDaily selectTLpProjectInformationDailyById(Long id);

    /**
     * 查询【日报管理-项目信息】列表
     * 
     * @param tLpProjectInformationDaily 【日报管理-项目信息】
     * @return 【日报管理-项目信息】集合
     */
    public List<TLpProjectInformationDaily> selectTLpProjectInformationDailyList(TLpProjectInformationDaily tLpProjectInformationDaily);

    /**
     * 新增【日报管理-项目信息】
     * 
     * @param tLpProjectInformationDaily 【日报管理-项目信息】
     * @return 结果
     */
    public int insertTLpProjectInformationDaily(TLpProjectInformationDaily tLpProjectInformationDaily);

    /**
     * 修改【日报管理-项目信息】
     * 
     * @param tLpProjectInformationDaily 【日报管理-项目信息】
     * @return 结果
     */
    public int updateTLpProjectInformationDaily(TLpProjectInformationDaily tLpProjectInformationDaily);

    /**
     * 批量删除【日报管理-项目信息】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpProjectInformationDailyByIds(String ids);

    /**
     * 删除【日报管理-项目信息】信息
     * 
     * @param id 【日报管理-项目信息】ID
     * @return 结果
     */
    public int deleteTLpProjectInformationDailyById(Long id);
}
