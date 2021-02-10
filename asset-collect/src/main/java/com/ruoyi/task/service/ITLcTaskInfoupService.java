package com.ruoyi.task.service;

import com.ruoyi.task.domain.TLcTaskInfoup;

import java.math.BigInteger;
import java.util.List;

/**
 * 信息更新Service接口
 * 
 * @author gaohg
 * @date 2021-02-04
 */
public interface ITLcTaskInfoupService 
{
    /**
     * 查询信息更新
     * 
     * @param id 信息更新ID
     * @return 信息更新
     */
    public TLcTaskInfoup selectTLcTaskInfoupById(Long id);

    /**
     * 查询信息更新列表
     * 
     * @param tLcTaskInfoup 信息更新
     * @return 信息更新集合
     */
    public List<TLcTaskInfoup> selectTLcTaskInfoupList(TLcTaskInfoup tLcTaskInfoup);

    /**
     * 新增信息更新
     * 
     * @param ids 信息更新
     * @return 结果
     */
    public int insertBatchTLcTaskInfoup( BigInteger[] ids);
    /**
     * 新增信息更新
     *
     * @param tLcTaskInfoup 信息更新
     * @return 结果
     */
    public int insertTLcTaskInfoup( TLcTaskInfoup tLcTaskInfoup);

    /**
     * 修改信息更新
     * 
     * @param tLcTaskInfoup 信息更新
     * @return 结果
     */
    public int updateTLcTaskInfoup(TLcTaskInfoup tLcTaskInfoup);

    /**
     * 更新状态
     * @param tLcTaskInfoup
     * @return
     */
    public int updateStatus(TLcTaskInfoup tLcTaskInfoup);

    /**
     * @param tLcTaskInfoup
     * @return
     */
    public int updateStatusGx(TLcTaskInfoup tLcTaskInfoup);
    /**
     * 批量删除信息更新
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcTaskInfoupByIds(String ids);

    /**
     * 删除信息更新信息
     * 
     * @param id 信息更新ID
     * @return 结果
     */
    public int deleteTLcTaskInfoupById(Long id);
}
