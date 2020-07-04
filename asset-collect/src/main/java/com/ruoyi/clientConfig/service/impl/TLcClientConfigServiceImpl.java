package com.ruoyi.clientConfig.service.impl;

import com.ruoyi.clientConfig.domain.TLcClientConfig;
import com.ruoyi.clientConfig.mapper.TLcClientConfigMapper;
import com.ruoyi.clientConfig.service.ITLcClientConfigService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author liurunkai
 * @date 2020-03-11
 */
@Service
public class TLcClientConfigServiceImpl implements ITLcClientConfigService {

    @Autowired
    private TLcClientConfigMapper tLcClientConfigMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TLcClientConfig selectTLcClientConfigById(Long id) {
        return tLcClientConfigMapper.selectTLcClientConfigById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcClientConfig 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TLcClientConfig> selectTLcClientConfigList(TLcClientConfig tLcClientConfig) {
        return tLcClientConfigMapper.selectTLcClientConfigList(tLcClientConfig);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcClientConfig 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTLcClientConfig(TLcClientConfig tLcClientConfig) {
        tLcClientConfig.setCreateTime(DateUtils.getNowDate());
        return tLcClientConfigMapper.insertTLcClientConfig(tLcClientConfig);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcClientConfig 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTLcClientConfig(TLcClientConfig tLcClientConfig) {
        tLcClientConfig.setUpdateTime(DateUtils.getNowDate());
        return tLcClientConfigMapper.updateTLcClientConfig(tLcClientConfig);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcClientConfigByIds(String ids) {
        return tLcClientConfigMapper.deleteTLcClientConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTLcClientConfigById(Long id) {
        return tLcClientConfigMapper.deleteTLcClientConfigById(id);
    }
}
