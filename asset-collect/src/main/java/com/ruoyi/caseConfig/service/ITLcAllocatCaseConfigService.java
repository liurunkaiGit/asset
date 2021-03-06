package com.ruoyi.caseConfig.service;

import com.ruoyi.caseConfig.domain.TLcAllocatCaseConfig;

import java.util.List;

/**
 * 智能分案配置Service接口
 *
 * @author liurunkai
 * @date 2020-04-23
 */
public interface ITLcAllocatCaseConfigService {
    /**
     * 查询智能分案配置
     *
     * @param id 智能分案配置ID
     * @return 智能分案配置
     */
    public TLcAllocatCaseConfig selectTLcAllocatCaseConfigById(Long id);

    /**
     * 查询智能分案配置列表
     *
     * @param tLcAllocatCaseConfig 智能分案配置
     * @return 智能分案配置集合
     */
    public List<TLcAllocatCaseConfig> selectTLcAllocatCaseConfigList(TLcAllocatCaseConfig tLcAllocatCaseConfig);

    /**
     * 新增智能分案配置
     *
     * @param tLcAllocatCaseConfig 智能分案配置
     * @return 结果
     */
    public int insertTLcAllocatCaseConfig(TLcAllocatCaseConfig tLcAllocatCaseConfig);

    /**
     * 修改智能分案配置
     *
     * @param tLcAllocatCaseConfig 智能分案配置
     * @return 结果
     */
    public int updateTLcAllocatCaseConfig(TLcAllocatCaseConfig tLcAllocatCaseConfig);

    /**
     * 批量删除智能分案配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcAllocatCaseConfigByIds(String ids);

    /**
     * 删除智能分案配置信息
     *
     * @param id 智能分案配置ID
     * @return 结果
     */
    public int deleteTLcAllocatCaseConfigById(Long id);

    TLcAllocatCaseConfig selectTLcAllocatCaseConfigByOrgId(String orgId);
}
