package com.ruoyi.custom.service;

import com.ruoyi.custom.domain.TLcCustTag;

import java.util.List;

/**
 * 客户标签Service接口
 *
 * @author liurunkai
 * @date 2020-01-09
 */
public interface ITLcCustTagService {
    /**
     * 查询客户标签
     *
     * @param id 客户标签ID
     * @return 客户标签
     */
    public TLcCustTag selectTLcCustTagById(Long id);

    /**
     * 查询客户标签列表
     *
     * @param tLcCustTag 客户标签
     * @return 客户标签集合
     */
    public List<TLcCustTag> selectTLcCustTagList(TLcCustTag tLcCustTag);

    /**
     * 新增客户标签
     *
     * @param tLcCustTag 客户标签
     * @return 结果
     */
    public int insertTLcCustTag(TLcCustTag tLcCustTag);

    /**
     * 修改客户标签
     *
     * @param tLcCustTag 客户标签
     * @return 结果
     */
    public int updateTLcCustTag(TLcCustTag tLcCustTag);

    /**
     * 批量删除客户标签
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcCustTagByIds(String ids);

    /**
     * 删除客户标签信息
     *
     * @param id 客户标签ID
     * @return 结果
     */
    public int deleteTLcCustTagById(Long id);
}
