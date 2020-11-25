package com.ruoyi.shareproject.monthlytarget.mapper;

import com.ruoyi.shareproject.monthlytarget.domain.TLpMonthlyTarget;

import java.util.List;

/**
 * 【月度指标】Mapper接口
 * 
 * @author gaohg
 * @date 2020-10-15
 */
public interface TLpMonthlyTargetMapper 
{
    /**
     * 查询【月度指标】
     * 
     * @param id 【月度指标】ID
     * @return 【月度指标】
     */
    public TLpMonthlyTarget selectTLpMonthlyTargetById(Long id);

    /**
     * 查询【月度指标】列表
     * 
     * @param tLpMonthlyTarget 【月度指标】
     * @return 【月度指标】集合
     */
    public List<TLpMonthlyTarget> selectTLpMonthlyTargetList(TLpMonthlyTarget tLpMonthlyTarget);

    /**
     * 新增【月度指标】
     * 
     * @param tLpMonthlyTarget 【月度指标】
     * @return 结果
     */
    public int insertTLpMonthlyTarget(TLpMonthlyTarget tLpMonthlyTarget);

    /**
     * 修改【月度指标】
     * 
     * @param tLpMonthlyTarget 【月度指标】
     * @return 结果
     */
    public int updateTLpMonthlyTarget(TLpMonthlyTarget tLpMonthlyTarget);

    /**
     * 删除【月度指标】
     * 
     * @param id 【月度指标】ID
     * @return 结果
     */
    public int deleteTLpMonthlyTargetById(Long id);

    /**
     * 批量删除【月度指标】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpMonthlyTargetByIds(String[] ids);

    Integer selectMonthlyTargetUnique(TLpMonthlyTarget tLpMonthlyTarget);

}
