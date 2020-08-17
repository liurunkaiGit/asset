package com.ruoyi.inforeporting.service;

import com.ruoyi.inforeporting.domain.TLcInforeportingSet;

import java.util.List;

/**
 * @Description: 上报信息设置
 * @author: gaohg
 * @Date: 2020/8/12
 */
public interface TLcInforeportingSetService {

    /**
     * @param inforeportingSet 上报信息设置实体参数
     * @return 是否成功 成功>0 失败<0
     */
    public int insertTLcInforeportingSet(TLcInforeportingSet inforeportingSet);

    /**
     * @param inforeportingSet 上报信息设置实体参数
     * @return 返回上报信息设置数据集合
     */
    public List<TLcInforeportingSet> selectTLcInforeportingSetList(TLcInforeportingSet inforeportingSet);

    /**
     * @param inforeportingSet  上报信息设置实体参数 orgId机构id,reportingType业务类型
     * @return 返回上报信息设置数据集合
     */
    public List<TLcInforeportingSet> selectTLcInforeportingSetByOrgIdAndTypeList(TLcInforeportingSet inforeportingSet);

    /**
     * @param ids 批量删除ids
     * @return 是否成功 成功>0 失败<0
     */
    public int deleteTLcInforeportingSetByIds(String ids);

    /**
     * @param inforeportingSet 跟新上报信息设置
     * @return 是否成功 成功>0 失败<0
     */
    public int updateTLcInforeportingSet(TLcInforeportingSet inforeportingSet);

    /**
     * @param id 根据id查询 报信息设置信息
     * @return 单个TLcInforeportingSet
     */
    public TLcInforeportingSet selectTLcInforeportingSetById(Long id);
}
