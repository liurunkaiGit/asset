package com.ruoyi.shareproject.finance.commission.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.shareproject.finance.commission.domain.TLpFinanceCommission;
import com.ruoyi.shareproject.finance.commission.mapper.TLpFinanceCommissionMapper;
import com.ruoyi.shareproject.finance.commission.service.ITLpFinanceCommissionService;
import com.ruoyi.shareproject.result.domain.TLpResult;
import com.ruoyi.shareproject.result.service.ITLpResultService;
import com.ruoyi.utils.LocalDateTimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 财务结佣Service业务层处理
 *
 * @author liurunkai
 * @date 2020-10-27
 */
@Service
public class TLpFinanceCommissionServiceImpl implements ITLpFinanceCommissionService {

    @Autowired
    private ITLpResultService resultService;
    @Autowired
    private TLpFinanceCommissionMapper tLpFinanceCommissionMapper;

    /**
     * 查询财务结佣
     *
     * @param id 财务结佣ID
     * @return 财务结佣
     */
    @Override
    public TLpFinanceCommission selectTLpFinanceCommissionById(Long id) {
        return tLpFinanceCommissionMapper.selectTLpFinanceCommissionById(id);
    }

    /**
     * 查询财务结佣列表
     *
     * @param tLpFinanceCommission 财务结佣
     * @return 财务结佣
     */
    @Override
    public List<TLpFinanceCommission> selectTLpFinanceCommissionList(TLpFinanceCommission tLpFinanceCommission) {
        if (StringUtils.isNotBlank(tLpFinanceCommission.getStartMonth())) {
            tLpFinanceCommission.setStartMonth(tLpFinanceCommission.getStartMonth().substring(0, 7));
        }
        if (StringUtils.isNotBlank(tLpFinanceCommission.getEndMonth())) {
            tLpFinanceCommission.setEndMonth(tLpFinanceCommission.getEndMonth().substring(0, 7));
        }
        List<TLpFinanceCommission> list = tLpFinanceCommissionMapper.selectTLpFinanceCommissionList(tLpFinanceCommission);
        // 未填写的总笔数和最新预计结佣取项目结果指标数据
        if (list != null && list.size() > 0) {
            list.stream()
                    .filter(financeCommission1 -> financeCommission1.getFeeStatus().equals(IsNoEnum.NO.getCode()))
                    .forEach(financeCommission1 -> {
                        // 查询当前项目和月份所有的项目结果指标数据
                        List<TLpResult> tLpResultList = getResultList(financeCommission1);
                        if (tLpResultList != null && tLpResultList.size() > 0) {
                            // 设置总笔数，根据项目结果指标数据的日期分组
                            Map<Date, List<TLpResult>> map = tLpResultList.stream().collect(Collectors.groupingBy(TLpResult::getReportDate));
                            financeCommission1.setTotalNum(map.size());
                            // 设置最新预计结佣，根据项目结果指标数据的日期排序取最大一个
                            List<TLpResult> tLpResults = map.get(getMapMaxKey(map));
                            BigDecimal reduce = tLpResults.stream().map(tLpResult -> tLpResult.getPredictCommission()).reduce(BigDecimal.ZERO, BigDecimal::add);
                            financeCommission1.setLatestPredictCommission(reduce);
                        }
                    });
        }
        return list;
    }

    /**
     * 根据项目id和月份获取结果指标数据
     *
     * @param financeCommission1
     * @return
     */
    private List<TLpResult> getResultList(TLpFinanceCommission financeCommission1) {
        TLpResult result = TLpResult.builder()
                .orgId(financeCommission1.getProjectId())
                .startReportDate(getFirstDay(financeCommission1.getMonth()))
                .endReportDate(getLastDay(financeCommission1.getMonth()))
                .build();
        return this.resultService.selectTLpResultList(result);
    }

    /**
     * 获取map中最大的key
     *
     * @param map
     * @return
     */
    private Object getMapMaxKey(Map<Date, List<TLpResult>> map) {
        Set<Date> dates = map.keySet();
        Object[] objects = dates.toArray();
        Arrays.sort(objects);
        return objects[objects.length - 1];
    }

    private Date getLastDay(String month) {
        LocalDateTime localDateTime = LocalDateTime.parse(month + "-01T00:00:00");
        LocalDateTime lastDay = localDateTime.with(TemporalAdjusters.lastDayOfMonth());
        Date date = LocalDateTimeUtil.date(lastDay.toLocalDate());
        return date;
    }

    private Date getFirstDay(String month) {
        LocalDateTime localDateTime = LocalDateTime.parse(month + "-01T00:00:00");
        LocalDateTime firstday = localDateTime.with(TemporalAdjusters.firstDayOfMonth());
        Date date = LocalDateTimeUtil.date(firstday.toLocalDate());
        return date;
    }

    /**
     * 新增财务结佣
     *
     * @param tLpFinanceCommission 财务结佣
     * @return 结果
     */
    @Override
    public int insertTLpFinanceCommission(TLpFinanceCommission tLpFinanceCommission) {
        return tLpFinanceCommissionMapper.insertTLpFinanceCommission(tLpFinanceCommission);
    }

    /**
     * 修改财务结佣
     *
     * @param tLpFinanceCommission 财务结佣
     * @return 结果
     */
    @Override
    public int updateTLpFinanceCommission(TLpFinanceCommission tLpFinanceCommission) {
        tLpFinanceCommission.setUpdateTime(DateUtils.getNowDate());
        return tLpFinanceCommissionMapper.updateTLpFinanceCommission(tLpFinanceCommission);
    }

    /**
     * 删除财务结佣对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLpFinanceCommissionByIds(String ids) {
        return tLpFinanceCommissionMapper.deleteTLpFinanceCommissionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除财务结佣信息
     *
     * @param id 财务结佣ID
     * @return 结果
     */
    @Override
    public int deleteTLpFinanceCommissionById(Long id) {
        return tLpFinanceCommissionMapper.deleteTLpFinanceCommissionById(id);
    }

    @Override
    public AjaxResult setActualCommission(Long id, BigDecimal actualCommission, BigDecimal latestPredictCommission, Integer totalNum) {
        TLpFinanceCommission financeCommission = TLpFinanceCommission.builder()
                .id(id)
                .actualCommission(actualCommission)
                .commissionDifferent(latestPredictCommission.subtract(actualCommission))
                .feeStatus(IsNoEnum.IS.getCode())
                .latestPredictCommission(latestPredictCommission)
                .totalNum(totalNum)
                .build();
        financeCommission.setUpdateBy(ShiroUtils.getSysUser().getUserId().toString());
        this.tLpFinanceCommissionMapper.updateTLpFinanceCommission(financeCommission);
        return AjaxResult.success();
    }
}
