package com.ruoyi.task.service;

import com.ruoyi.task.domain.TLcTaskUplog;

import java.util.List;

/**
 * 【信息更新申请-次数记录】Service接口
 * 
 * @author liurunkai
 * @date 2021-02-03
 */
public interface ITLcTaskUplogService 
{
    /**
     * 查询【信息更新申请-次数记录】
     * 
     * @param id 【信息更新申请-次数记录】ID
     * @return 【信息更新申请-次数记录】
     */
    public TLcTaskUplog selectTLcTaskUplogById(Long id);

    /**
     * 查询【信息更新申请-次数记录】列表
     * 
     * @param tLcTaskUplog 【信息更新申请-次数记录】
     * @return 【信息更新申请-次数记录】集合
     */
    public List<TLcTaskUplog> selectTLcTaskUplogList(TLcTaskUplog tLcTaskUplog);

    /**
     * @return 查询当天提交次数
     */
    public Integer findCishu();
    /**
     * 新增【信息更新申请-次数记录】
     * 
     * @param tLcTaskUplog 【信息更新申请-次数记录】
     * @return 结果
     */
    public int insertTLcTaskUplog(TLcTaskUplog tLcTaskUplog);

    /**
     * 修改【信息更新申请-次数记录】
     * 
     * @param tLcTaskUplog 【信息更新申请-次数记录】
     * @return 结果
     */
    public int updateTLcTaskUplog(TLcTaskUplog tLcTaskUplog);

    /**
     * 批量删除【信息更新申请-次数记录】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcTaskUplogByIds(String ids);

    /**
     * 删除【信息更新申请-次数记录】信息
     * 
     * @param id 【信息更新申请-次数记录】ID
     * @return 结果
     */
    public int deleteTLcTaskUplogById(Long id);
}
