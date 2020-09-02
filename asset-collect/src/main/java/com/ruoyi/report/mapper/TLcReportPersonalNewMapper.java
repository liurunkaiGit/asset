package com.ruoyi.report.mapper;

import com.ruoyi.report.domain.TLcReportPersonalNew;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通时通次个人明细汇总报Mapper接口
 *
 * @author liurunkai
 * @date 2020-09-02
 */
public interface TLcReportPersonalNewMapper {
    /**
     * 查询通时通次个人明细汇总报
     *
     * @param reportData 通时通次个人明细汇总报ID
     * @return 通时通次个人明细汇总报
     */
    public TLcReportPersonalNew selectTLcReportPersonalNewById(Date reportData);

    /**
     * 查询通时通次个人明细汇总报列表
     *
     * @param tLcReportPersonalNew 通时通次个人明细汇总报
     * @return 通时通次个人明细汇总报集合
     */
    public List<TLcReportPersonalNew> selectTLcReportPersonalNewList(TLcReportPersonalNew tLcReportPersonalNew);

    /**
     * 新增通时通次个人明细汇总报
     *
     * @param tLcReportPersonalNew 通时通次个人明细汇总报
     * @return 结果
     */
    public int insertTLcReportPersonalNew(TLcReportPersonalNew tLcReportPersonalNew);

    /**
     * 修改通时通次个人明细汇总报
     *
     * @param tLcReportPersonalNew 通时通次个人明细汇总报
     * @return 结果
     */
    public int updateTLcReportPersonalNew(TLcReportPersonalNew tLcReportPersonalNew);

    /**
     * 删除通时通次个人明细汇总报
     *
     * @param reportData 通时通次个人明细汇总报ID
     * @return 结果
     */
    public int deleteTLcReportPersonalNewById(Date reportData);

    /**
     * 批量删除通时通次个人明细汇总报
     *
     * @param reportDatas 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcReportPersonalNewByIds(String[] reportDatas);

    List<TLcReportPersonalNew> selectReportPersonalListByTimePeriod(Map<String, Object> param);
}
