package com.ruoyi.batchcall.service;

import com.ruoyi.batchcall.domain.TLcBatchCallConfig;

import java.util.List;

/**
 * 批量外呼配置Service接口
 * 
 * @author fengzhitao
 * @date 2020-06-28
 */
public interface ITLcBatchCallConfigService 
{
    /**
     * 查询批量外呼配置
     * 
     * @param id 批量外呼配置ID
     * @return 批量外呼配置
     */
    public TLcBatchCallConfig selectTLcBatchCallConfigById(Long id);

    /**
     * 查询批量外呼配置列表
     * 
     * @param tLcBatchCallConfig 批量外呼配置
     * @return 批量外呼配置集合
     */
    public List<TLcBatchCallConfig> selectTLcBatchCallConfigList(TLcBatchCallConfig tLcBatchCallConfig);

    /**
     * 新增批量外呼配置
     * 
     * @param tLcBatchCallConfig 批量外呼配置
     * @return 结果
     */
    public int insertTLcBatchCallConfig(TLcBatchCallConfig tLcBatchCallConfig);

    /**
     * 修改批量外呼配置
     * 
     * @param tLcBatchCallConfig 批量外呼配置
     * @return 结果
     */
    public int updateTLcBatchCallConfig(TLcBatchCallConfig tLcBatchCallConfig);

    /**
     * 批量删除批量外呼配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcBatchCallConfigByIds(String ids);

    /**
     * 删除批量外呼配置信息
     * 
     * @param id 批量外呼配置ID
     * @return 结果
     */
    public int deleteTLcBatchCallConfigById(Long id);

    /**
     * 根据机构ID查询批量外呼配置
     *
     * @param orgId 批量外呼配置机构ID
     * @return 批量外呼配置
     */
    public TLcBatchCallConfig selectTLcBatchCallConfigByOrgId(String orgId);
}
