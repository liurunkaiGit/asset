package com.ruoyi.shareproject.result.mapper;

import com.ruoyi.shareproject.result.domain.TLpResult;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author liurunkai
 * @date 2020-10-16
 */
public interface TLpResultMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TLpResult selectTLpResultById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLpResult 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLpResult> selectTLpResultList(TLpResult tLpResult);

    /**
     * 新增【请填写功能名称】
     *
     * @param tLpResult 【请填写功能名称】
     * @return 结果
     */
    public int insertTLpResult(TLpResult tLpResult);

    /**
     * 修改【请填写功能名称】
     *
     * @param tLpResult 【请填写功能名称】
     * @return 结果
     */
    public int updateTLpResult(TLpResult tLpResult);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLpResultById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpResultByIds(String[] ids);

    Integer selectProjectResultUnique(TLpResult tLpResult);
}
