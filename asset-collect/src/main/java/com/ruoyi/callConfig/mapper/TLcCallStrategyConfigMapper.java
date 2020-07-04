package com.ruoyi.callConfig.mapper;

import com.ruoyi.callConfig.domain.TLcCallStrategyConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author liurunkai
 * @date 2020-02-06
 */
public interface TLcCallStrategyConfigMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TLcCallStrategyConfig selectTLcCallStrategyConfigById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcCallStrategyConfig 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLcCallStrategyConfig> selectTLcCallStrategyConfigList(TLcCallStrategyConfig tLcCallStrategyConfig);

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcCallStrategyConfig 【请填写功能名称】
     * @return 结果
     */
    public int insertTLcCallStrategyConfig(TLcCallStrategyConfig tLcCallStrategyConfig);

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcCallStrategyConfig 【请填写功能名称】
     * @return 结果
     */
    public int updateTLcCallStrategyConfig(TLcCallStrategyConfig tLcCallStrategyConfig);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLcCallStrategyConfigById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcCallStrategyConfigByIds(String[] ids);

    TLcCallStrategyConfig selectCallStrategyConfigByOrgIdAndBusinessScene(@Param("orgId") String orgId, @Param("businessScene") Integer businessScene);
}
