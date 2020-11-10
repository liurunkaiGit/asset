package com.ruoyi.shareproject.hmrule.service;

import com.ruoyi.shareproject.hmrule.domain.TLjRule;
import com.ruoyi.shareproject.hmrule.domain.TLjRuleDetails;

import java.util.List;

/**
 * 【居家规则】Service接口
 * 
 * @author gaohg
 * @date 2020-11-02
 */
public interface ITLjRuleService 
{
    /**
     * 查询【居家规则】
     * 
     * @param id 【居家规则】ID
     * @return 【居家规则】
     */
    public TLjRule selectTLjRuleById(Long id);

    /**
     * 检测规则时间段是否可用
     * @param tLjRule
     * @return
     */
    public int guizeuse(TLjRule tLjRule);

    /**
     * 查询【居家规则】列表
     * 
     * @param tLjRule 【居家规则】
     * @return 【居家规则】集合
     */
    public List<TLjRule> selectTLjRuleList(TLjRule tLjRule);

    /**
     * 新增【居家规则】
     * 
     * @param tLjRule 【居家规则】
     * @return 结果
     */
    public int insertTLjRule(TLjRule tLjRule);

    /**
     * 修改【居家规则】
     * 
     * @param tLjRule 【居家规则】
     * @return 结果
     */
    public int updateTLjRule(TLjRule tLjRule);

    /**
     * 批量删除【居家规则】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLjRuleByIds(String ids);

    /**
     * 删除【居家规则】信息
     * 
     * @param id 【居家规则】ID
     * @return 结果
     */
    public int deleteTLjRuleById(Long id);
}
