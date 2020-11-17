package com.ruoyi.shareproject.hmrule.mapper;

import com.ruoyi.shareproject.hmrule.domain.TLjRule;

import java.util.List;

/**
 * 【居家规则】Mapper接口
 * 
 * @author gaohg
 * @date 2020-11-02
 */
public interface TLjRuleMapper 
{
    /**
     * 查询【居家规则】
     * 
     * @param id 【居家规则】ID
     * @return 【居家规则】
     */
    public TLjRule selectTLjRuleById(Long id);

    /**
     * 查询【居家规则】列表
     * 
     * @param tLjRule 【居家规则】
     * @return 【居家规则】集合
     */
    public List<TLjRule> selectTLjRuleList(TLjRule tLjRule);

    /**
     * 查询 部门包含的人员是否已经 有规则
     * @param tLjRule
     * @return
     */
    public List<TLjRule> selectDeptIncludeUser(TLjRule tLjRule);

    /**
     * 根据部门检测是否可用
     * @param tLjRule
     * @return
     */
    public List<TLjRule> selectTLjRuleListIsUse(TLjRule tLjRule);

    /**
     * @param tLjRule
     * @return
     */
    public List<TLjRule> selectTLjRuleListIsUseByLoginName(TLjRule tLjRule);
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
     * 删除【居家规则】
     * 
     * @param id 【居家规则】ID
     * @return 结果
     */
    public int deleteTLjRuleById(Long id);

    /**
     * 批量删除【居家规则】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLjRuleByIds(String[] ids);
}
