package com.ruoyi.columnQuery.service;

import com.ruoyi.columnQuery.domain.TLcColumnQuery;

import java.util.List;

/**
 * Service接口
 *
 * @author liurunkai
 * @date 2020-05-22
 */
public interface ITLcColumnQueryService {
    /**
     * 查询
     *
     * @param id
     * @return
     */
    public TLcColumnQuery selectTLcColumnQueryById(Long id);

    /**
     * 查询列表
     *
     * @param tLcColumnQuery
     * @return
     */
    public List<TLcColumnQuery> selectTLcColumnQueryList(TLcColumnQuery tLcColumnQuery);

    /**
     * 新增
     *
     * @param tLcColumnQuery
     * @return 结果
     */
    public int insertTLcColumnQuery(TLcColumnQuery tLcColumnQuery);

    /**
     * 修改
     *
     * @param tLcColumnQuery
     * @return 结果
     */
    public int updateTLcColumnQuery(TLcColumnQuery tLcColumnQuery);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcColumnQueryByIds(String ids);

    /**
     * 删除
     *
     * @param id
     * @return 结果
     */
    public int deleteTLcColumnQueryById(Long id);
}
