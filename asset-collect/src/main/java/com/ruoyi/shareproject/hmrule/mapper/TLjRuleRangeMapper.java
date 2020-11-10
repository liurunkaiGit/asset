package com.ruoyi.shareproject.hmrule.mapper;

import com.ruoyi.shareproject.hmrule.domain.TLjRuleRange;

import java.util.List;

/**
 * 【规则类型】Mapper接口
 * 
 * @author gaohg
 * @date 2020-11-03
 */
public interface TLjRuleRangeMapper 
{
    /**
     * 查询【规则类型】
     * 
     * @param id 【规则类型】ID
     * @return 【规则类型】
     */
    public TLjRuleRange selectTLjRuleRangeById(Long id);

    /**
     * 查询【规则类型】列表
     * 
     * @param tLjRuleRange 【规则类型】
     * @return 【规则类型】集合
     */
    public List<TLjRuleRange> selectTLjRuleRangeList(TLjRuleRange tLjRuleRange);

    /**
     * 新增【规则类型】
     * 
     * @param tLjRuleRange 【规则类型】
     * @return 结果
     */
    public int insertTLjRuleRange(TLjRuleRange tLjRuleRange);

    /**
     * 修改【规则类型】
     * 
     * @param tLjRuleRange 【规则类型】
     * @return 结果
     */
    public int updateTLjRuleRange(TLjRuleRange tLjRuleRange);

    /**
     * 删除【规则类型】
     * 
     * @param id 【规则类型】ID
     * @return 结果
     */
    public int deleteTLjRuleRangeById(Long id);

    /**删除【规则类型】
     * @param ruleId
     * @return
     */
    public int deleteTLjRuleRangeByRuleId(Long ruleId);


    /**
     * 批量删除【规则类型】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLjRuleRangeByIds(String[] ids);

    /**批量删除【规则类型】
     * @param ids
     * @return
     */
    public int deleteTLjRuleRangeByRuleIds(String[] ids);
}
