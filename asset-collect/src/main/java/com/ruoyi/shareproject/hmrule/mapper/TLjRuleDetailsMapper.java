package com.ruoyi.shareproject.hmrule.mapper;

import com.ruoyi.shareproject.hmrule.domain.TLjRuleDetails;

import java.util.List;

/**
 * 【居家规则详情】Mapper接口
 * 
 * @author gaohg
 * @date 2020-11-02
 */
public interface TLjRuleDetailsMapper 
{
    /**
     * 查询【居家规则详情】
     * 
     * @param id 【居家规则详情】ID
     * @return 【居家规则详情】
     */
    public TLjRuleDetails selectTLjRuleDetailsById(Long id);

    /**
     * 查询【居家规则详情】列表
     * 
     * @param tLjRuleDetails 【居家规则详情】
     * @return 【居家规则详情】集合
     */
    public List<TLjRuleDetails> selectTLjRuleDetailsList(TLjRuleDetails tLjRuleDetails);

    /**
     * 新增【居家规则详情】
     * 
     * @param tLjRuleDetails 【居家规则详情】
     * @return 结果
     */
    public int insertTLjRuleDetails(TLjRuleDetails tLjRuleDetails);

    /**查询时间段是否可用
     * @param tLjRuleDetails
     * @return
     */
    public List<TLjRuleDetails> tmisuse(TLjRuleDetails tLjRuleDetails);
    /**
     * 修改【居家规则详情】
     * 
     * @param tLjRuleDetails 【居家规则详情】
     * @return 结果
     */
    public int updateTLjRuleDetails(TLjRuleDetails tLjRuleDetails);

    /**
     * 删除【居家规则详情】
     * 
     * @param id 【居家规则详情】ID
     * @return 结果
     */
    public int deleteTLjRuleDetailsById(Long id);

    /**删除【居家规则详情】
     * @param id
     * @return
     */
    public int deleteTLjRuleDetailsByRuleId(Long ruleId);

    /**
     * 批量删除【居家规则详情】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLjRuleDetailsByIds(String[] ids);

    /**批量删除【居家规则详情】
     * @param ids
     * @return
     */
    public int deleteTLjRuleDetailsByRuleIds(String[] ids);
}
