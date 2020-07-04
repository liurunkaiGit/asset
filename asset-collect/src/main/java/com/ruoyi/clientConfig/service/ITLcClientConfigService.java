package com.ruoyi.clientConfig.service;

import com.ruoyi.clientConfig.domain.TLcClientConfig;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author liurunkai
 * @date 2020-03-11
 */
public interface ITLcClientConfigService {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TLcClientConfig selectTLcClientConfigById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcClientConfig 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLcClientConfig> selectTLcClientConfigList(TLcClientConfig tLcClientConfig);

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcClientConfig 【请填写功能名称】
     * @return 结果
     */
    public int insertTLcClientConfig(TLcClientConfig tLcClientConfig);

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcClientConfig 【请填写功能名称】
     * @return 结果
     */
    public int updateTLcClientConfig(TLcClientConfig tLcClientConfig);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcClientConfigByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLcClientConfigById(Long id);
}
