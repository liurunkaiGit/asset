package com.ruoyi.task.mapper;

import com.ruoyi.task.domain.TLcTaskInfoup;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * 信息更新Mapper接口
 * 
 * @author gaohg
 * @date 2021-02-04
 */
public interface TLcTaskInfoupMapper 
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
     * @param tLcTaskInfoup 信息更新
     * @return 结果
     */
    public int insertTLcTaskInfoup(TLcTaskInfoup tLcTaskInfoup);

    /**
     * 批量更新
     * @param ids
     * @return
     */
    public int insertBatchTLcTaskInfoup(@Param("ids") BigInteger[] ids);

    /**
     * 修改信息更新
     * 
     * @param tLcTaskInfoup 信息更新
     * @return 结果
     */
    public int updateTLcTaskInfoup(TLcTaskInfoup tLcTaskInfoup);

    /**
     * 批量更新状态
     * @param tLcTaskInfoup
     * @return
     */
    public int updateStatus(TLcTaskInfoup tLcTaskInfoup);

    /**
     * 将电话信息写入到联系人表中
     * @param ids
     * @return
     */
    public int insertBatchLianxiren(@Param("ids") BigInteger[] ids);
    /**
     * 删除信息更新
     * 
     * @param id 信息更新ID
     * @return 结果
     */
    public int deleteTLcTaskInfoupById(Long id);

    /**
     * 批量删除信息更新
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcTaskInfoupByIds(String[] ids);
}
