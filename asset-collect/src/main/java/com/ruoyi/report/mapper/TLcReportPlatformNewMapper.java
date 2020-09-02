package com.ruoyi.report.mapper;

import com.ruoyi.report.domain.TLcReportPlatform;
import com.ruoyi.report.domain.TLcReportPlatformNew;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通时通次平台汇总报Mapper接口
 *
 * @author liurunkai
 * @date 2020-09-02
 */
public interface TLcReportPlatformNewMapper {
    /**
     * 查询通时通次平台汇总报
     *
     * @param reportData 通时通次平台汇总报ID
     * @return 通时通次平台汇总报
     */
    public TLcReportPlatformNew selectTLcReportPlatformNewById(Date reportData);

    /**
     * 查询通时通次平台汇总报列表
     *
     * @param tLcReportPlatformNew 通时通次平台汇总报
     * @return 通时通次平台汇总报集合
     */
    public List<TLcReportPlatformNew> selectTLcReportPlatformNewList(TLcReportPlatformNew tLcReportPlatformNew);

    /**
     * 新增通时通次平台汇总报
     *
     * @param tLcReportPlatformNew 通时通次平台汇总报
     * @return 结果
     */
    public int insertTLcReportPlatformNew(TLcReportPlatformNew tLcReportPlatformNew);

    /**
     * 修改通时通次平台汇总报
     *
     * @param tLcReportPlatformNew 通时通次平台汇总报
     * @return 结果
     */
    public int updateTLcReportPlatformNew(TLcReportPlatformNew tLcReportPlatformNew);

    /**
     * 删除通时通次平台汇总报
     *
     * @param reportData 通时通次平台汇总报ID
     * @return 结果
     */
    public int deleteTLcReportPlatformNewById(Date reportData);

    /**
     * 批量删除通时通次平台汇总报
     *
     * @param reportDatas 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcReportPlatformNewByIds(String[] reportDatas);

    List<TLcReportPlatformNew> selectReportPlatform(Map<String, Object> param);
}
