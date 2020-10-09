package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.TLiDjXy;

import java.util.List;

/**
 * 兴业单据Mapper接口
 * 
 * @author liurunkai
 * @date 2020-09-09
 */
public interface TLiDjXyMapper 
{
    /**
     * 查询兴业单据
     * 
     * @param tuid 兴业单据ID
     * @return 兴业单据
     */
    public TLiDjXy selectTLiDjXyById(String tuid);

    /**
     * 查询兴业单据列表
     * 
     * @param tLiDjXy 兴业单据
     * @return 兴业单据集合
     */
    public List<TLiDjXy> selectTLiDjXyList(TLiDjXy tLiDjXy);

    /**
     * 新增兴业单据
     * 
     * @param tLiDjXy 兴业单据
     * @return 结果
     */
    public int insertTLiDjXy(TLiDjXy tLiDjXy);

    /**
     * 修改兴业单据
     * 
     * @param tLiDjXy 兴业单据
     * @return 结果
     */
    public int updateTLiDjXy(TLiDjXy tLiDjXy);

    /**
     * 删除兴业单据
     * 
     * @param tuid 兴业单据ID
     * @return 结果
     */
    public int deleteTLiDjXyById(String tuid);

    /**
     * 批量删除兴业单据
     * 
     * @param tuids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLiDjXyByIds(String[] tuids);
}
