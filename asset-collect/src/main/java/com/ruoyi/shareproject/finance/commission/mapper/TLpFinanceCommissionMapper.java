package com.ruoyi.shareproject.finance.commission.mapper;

import com.ruoyi.shareproject.finance.commission.domain.TLpFinanceCommission;

import java.util.List;

/**
 * 财务结佣Mapper接口
 *
 * @author liurunkai
 * @date 2020-10-27
 */
public interface TLpFinanceCommissionMapper {
    /**
     * 查询财务结佣
     *
     * @param id 财务结佣ID
     * @return 财务结佣
     */
    public TLpFinanceCommission selectTLpFinanceCommissionById(Long id);

    /**
     * 查询财务结佣列表
     *
     * @param tLpFinanceCommission 财务结佣
     * @return 财务结佣集合
     */
    public List<TLpFinanceCommission> selectTLpFinanceCommissionList(TLpFinanceCommission tLpFinanceCommission);

    /**
     * 新增财务结佣
     *
     * @param tLpFinanceCommission 财务结佣
     * @return 结果
     */
    public int insertTLpFinanceCommission(TLpFinanceCommission tLpFinanceCommission);

    /**
     * 修改财务结佣
     *
     * @param tLpFinanceCommission 财务结佣
     * @return 结果
     */
    public int updateTLpFinanceCommission(TLpFinanceCommission tLpFinanceCommission);

    /**
     * 删除财务结佣
     *
     * @param id 财务结佣ID
     * @return 结果
     */
    public int deleteTLpFinanceCommissionById(Long id);

    /**
     * 批量删除财务结佣
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpFinanceCommissionByIds(String[] ids);
}
