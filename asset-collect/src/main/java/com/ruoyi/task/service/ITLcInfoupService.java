package com.ruoyi.task.service;

import com.ruoyi.task.domain.TLcInfoup;

import java.util.List;

/**
 * 【联系人增加】Service接口
 * 
 * @author liurunkai
 * @date 2021-02-05
 */
public interface ITLcInfoupService 
{
    /**
     * 查询【联系人增加】
     * 
     * @param id 【联系人增加】ID
     * @return 【联系人增加】
     */
    public TLcInfoup selectTLcInfoupById(Long id);

    /**
     * 查询【联系人增加】列表
     * 
     * @param tLcInfoup 【联系人增加】
     * @return 【联系人增加】集合
     */
    public List<TLcInfoup> selectTLcInfoupList(TLcInfoup tLcInfoup);

    /**
     * 新增【联系人增加】
     * 
     * @param tLcInfoup 【联系人增加】
     * @return 结果
     */
    public int insertTLcInfoup(TLcInfoup tLcInfoup);

    /**
     * 修改【联系人增加】
     * 
     * @param tLcInfoup 【联系人增加】
     * @return 结果
     */
    public int updateTLcInfoup(TLcInfoup tLcInfoup);

    /**
     * 批量删除【联系人增加】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcInfoupByIds(String ids);

    /**
     * 删除【联系人增加】信息
     * 
     * @param id 【联系人增加】ID
     * @return 结果
     */
    public int deleteTLcInfoupById(Long id);
}
