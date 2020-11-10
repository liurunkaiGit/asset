package com.ruoyi.shareproject.hmuserst.mapper;

import com.ruoyi.shareproject.hmuserst.domain.TLjRuleUserLogs;

import java.util.List;

/**
 * 【员工状态】Mapper接口
 * 
 * @author gaohg
 * @date 2020-11-05
 */
public interface TLjRuleUserLogsMapper 
{
    /**
     * 查询【员工状态】
     * 
     * @param id 【员工状态】ID
     * @return 【员工状态】
     */
    public TLjRuleUserLogs selectTLjRuleUserLogsById(Long id);

    /**
     * 查询【员工状态】列表
     * 
     * @param tLjRuleUserLogs 【员工状态】
     * @return 【员工状态】集合
     */
    public List<TLjRuleUserLogs> selectTLjRuleUserLogsList(TLjRuleUserLogs tLjRuleUserLogs);

    /**根据规则id和规则详情id寻找 是否存在记录
     * @param tLjRuleUserLogs
     * @return
     */
    public Long selectTLjRuleUserLogsByRuleIdAnddetailsId(TLjRuleUserLogs tLjRuleUserLogs);
    /**
     * 新增【员工状态】
     * 
     * @param tLjRuleUserLogs 【员工状态】
     * @return 结果
     */
    public int insertTLjRuleUserLogs(TLjRuleUserLogs tLjRuleUserLogs);

    /**
     * 修改【员工状态】
     * 
     * @param tLjRuleUserLogs 【员工状态】
     * @return 结果
     */
    public int updateTLjRuleUserLogs(TLjRuleUserLogs tLjRuleUserLogs);

    /**
     * 删除【员工状态】
     * 
     * @param id 【员工状态】ID
     * @return 结果
     */
    public int deleteTLjRuleUserLogsById(Long id);

    /**
     * 批量删除【员工状态】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLjRuleUserLogsByIds(String[] ids);
}
