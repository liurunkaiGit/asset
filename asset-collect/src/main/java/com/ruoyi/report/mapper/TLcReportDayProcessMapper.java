package com.ruoyi.report.mapper;

import com.ruoyi.report.domain.TLcReportDayProcess;

import java.util.List;
import java.util.Map;

/**
 * 每日过程指标报Mapper接口
 *
 * @author liurunkai
 * @date 2020-04-09
 */
public interface TLcReportDayProcessMapper {
    /**
     * 查询每日过程指标报
     *
     * @param id 每日过程指标报ID
     * @return 每日过程指标报
     */
    public TLcReportDayProcess selectTLcReportDayProcessById(Long id);

    /**
     * 查询每日过程指标报列表
     *
     * @param tLcReportDayProcess 每日过程指标报
     * @return 每日过程指标报集合
     */
    public List<TLcReportDayProcess> selectTLcReportDayProcessList(TLcReportDayProcess tLcReportDayProcess);

    /**
     * 新增每日过程指标报
     *
     * @param tLcReportDayProcess 每日过程指标报
     * @return 结果
     */
    public int insertTLcReportDayProcess(TLcReportDayProcess tLcReportDayProcess);

    /**
     * 修改每日过程指标报
     *
     * @param tLcReportDayProcess 每日过程指标报
     * @return 结果
     */
    public int updateTLcReportDayProcess(TLcReportDayProcess tLcReportDayProcess);

    /**
     * 删除每日过程指标报
     *
     * @param id 每日过程指标报ID
     * @return 结果
     */
    public int deleteTLcReportDayProcessById(Long id);

    /**
     * 批量删除每日过程指标报
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcReportDayProcessByIds(String[] ids);

    List<TLcReportDayProcess> selectDayProcess(Map<String, Object> param);

    /**
     * 居家规则 案件处理量 参数：开始、结束时间和userId
     * @param param
     * @return
     */
    List<TLcReportDayProcess> selectDayProcessTask(Map<String, Object> param);

    /**
     * 居家规则 还款达成 参数：userId
     * @param param
     * @return
     */
    List<TLcReportDayProcess> selectTaskReach(Map<String, Object> param);

    /** 用户还款总额
     * @param param
     * @return
     */
    List<TLcReportDayProcess> selectTaskHuankuanZong(Map<String, Object> param);

    /**
     * @param param
     * @return
     */
    List<TLcReportDayProcess> selectTaskYiHuankuanZong(Map<String, Object> param);

}
