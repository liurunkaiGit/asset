package com.ruoyi.report.mapper;

import com.ruoyi.report.domain.TLcReportPersonal;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通时通次个人明细汇总报Mapper接口
 *
 * @author liurunkai
 * @date 2020-08-05
 */
public interface TLcReportPersonalMapper {

    /**
     * 查询通时通次个人明细汇总报列表
     *
     * @param tLcReportPersonal 通时通次个人明细汇总报
     * @return 通时通次个人明细汇总报集合
     */
    public List<TLcReportPersonal> selectTLcReportPersonalList(TLcReportPersonal tLcReportPersonal);

    /**
     * 新增通时通次个人明细汇总报
     *
     * @param tLcReportPersonal 通时通次个人明细汇总报
     * @return 结果
     */
    public int insertTLcReportPersonal(TLcReportPersonal tLcReportPersonal);

//    List<TLcReportPersonal> selectReportPersonalList(Map<String, Object> param);

    List<TLcReportPersonal> selectReportPersonalListByTimePeriod(Map<String, Object> param);

    List<TLcReportPersonal> selectReportPersonalList(TLcReportPersonal tLcReportPersonal);
}
