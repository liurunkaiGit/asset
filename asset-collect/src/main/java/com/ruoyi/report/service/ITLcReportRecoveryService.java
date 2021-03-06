package com.ruoyi.report.service;

import com.ruoyi.report.domain.TLcReportRecovery;
import com.ruoyi.report.domain.TLcReportZyRecovery;

import java.util.List;
import java.util.Map;

/**
 * 回收率报Service接口
 *
 * @author liurunkai
 * @date 2020-04-09
 */
public interface ITLcReportRecoveryService {
    /**
     * 查询回收率报
     *
     * @param id 回收率报ID
     * @return 回收率报
     */
    public TLcReportRecovery selectTLcReportRecoveryById(Long id);

    /**
     * 查询回收率报列表
     *
     * @param tLcReportRecovery 回收率报
     * @return 回收率报集合
     */
    public List<TLcReportRecovery> selectTLcReportRecoveryList(TLcReportRecovery tLcReportRecovery);

    /**
     * 新增回收率报
     *
     * @param tLcReportRecovery 回收率报
     * @return 结果
     */
    public int insertTLcReportRecovery(TLcReportRecovery tLcReportRecovery);

    /**
     * 修改回收率报
     *
     * @param tLcReportRecovery 回收率报
     * @return 结果
     */
    public int updateTLcReportRecovery(TLcReportRecovery tLcReportRecovery);

    /**
     * 批量删除回收率报
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcReportRecoveryByIds(String ids);

    /**
     * 删除回收率报信息
     *
     * @param id 回收率报ID
     * @return 结果
     */
    public int deleteTLcReportRecoveryById(Long id);

    List<TLcReportRecovery> selectRecoveryByPayment(Map<String, Object> param);

    List<TLcReportZyRecovery> selectTLcReportZyRecoveryList(TLcReportZyRecovery zyRecovery);
}
