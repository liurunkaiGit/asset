package com.ruoyi.report.service.impl;

import com.ruoyi.report.domain.TLcReportPersonal;
import com.ruoyi.report.domain.TLcReportPlatform;
import com.ruoyi.report.mapper.TLcReportPersonalMapper;
import com.ruoyi.report.service.ITLcReportPersonalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 通时通次个人明细汇总报Service业务层处理
 *
 * @author liurunkai
 * @date 2020-08-05
 */
@Service
public class TLcReportPersonalServiceImpl implements ITLcReportPersonalService {
    @Autowired
    private TLcReportPersonalMapper tLcReportPersonalMapper;

    /**
     * 查询通时通次个人明细汇总报列表
     *
     * @param tLcReportPersonal 通时通次个人明细汇总报
     * @return 通时通次个人明细汇总报
     */
    @Override
    public List<TLcReportPersonal> selectTLcReportPersonalList(TLcReportPersonal tLcReportPersonal) {
        List<TLcReportPersonal> list = this.tLcReportPersonalMapper.selectReportPersonalList(tLcReportPersonal);
        if (list != null && list.size() > 0) {
            Map<String, List<TLcReportPersonal>> map = list.stream().collect(Collectors.groupingBy(TLcReportPersonal::getLoginName));
            for (Map.Entry<String, List<TLcReportPersonal>> personalMap : map.entrySet()) {
                Integer paCalledNum = 0;
                Integer paCallNum = 0;
                BigDecimal paCalledLen = new BigDecimal(0.00);
                Integer zjCalledNum = 0;
                Integer zjCallNum = 0;
                BigDecimal zjCalledLen = new BigDecimal(0.00);
                Integer dyCalledNum = 0;
                Integer dyCallNum = 0;
                BigDecimal dyCalledLen = new BigDecimal(0.00);
                Integer totalCalledNum = 0;
                Integer totalCallNum = 0;
                BigDecimal totalCalledLen = new BigDecimal(0.00);
                TLcReportPersonal reportPersonal = personalMap.getValue().get(0);
                List<String> timePeriodList = new ArrayList<>();
                for (TLcReportPersonal personal : personalMap.getValue()) {
                    timePeriodList.add(personal.getTimePeriod());
                    // 计算每一行的合计值
                    Integer rowTotalCalledNum = (personal.getPaCalledNum() == null ? 0 : personal.getPaCalledNum()) + (personal.getZjCalledNum() == null ? 0 : personal.getZjCalledNum()) + (personal.getDyCalledNum() == null ? 0 : personal.getDyCalledNum());
                    Integer rowTotalCallNum = (personal.getPaCallNum() == null ? 0 : personal.getPaCallNum()) + (personal.getZjCallNum() == null ? 0 : personal.getZjCallNum()) + (personal.getDyCallNum() == null ? 0 : personal.getDyCallNum());
                    BigDecimal paCallLen = StringUtils.isEmpty(personal.getPaCallLen()) ? new BigDecimal(0.00) : new BigDecimal(personal.getPaCallLen());
                    BigDecimal zjCallLen = StringUtils.isEmpty(personal.getZjCallLen()) ? new BigDecimal(0.00) : new BigDecimal(personal.getZjCallLen());
                    BigDecimal dyCallLen = StringUtils.isEmpty(personal.getDyCallLen()) ? new BigDecimal(0.00) : new BigDecimal(personal.getDyCallLen());
                    String rowTotalCallLen = String.valueOf(paCallLen.add(zjCallLen).add(dyCallLen));
                    // 设置每一行的合计值
                    personal.setTotalCalledNum(rowTotalCalledNum);
                    personal.setTotalCallNum(rowTotalCallNum);
                    personal.setTotalCallLen(rowTotalCallLen);
                    // 计算合计行的合计值
                    paCalledNum += personal.getPaCalledNum() == null ? 0 : personal.getPaCalledNum();
                    paCallNum += personal.getPaCallNum() == null ? 0 : personal.getPaCallNum();
                    paCalledLen = paCalledLen.add(StringUtils.isEmpty(personal.getPaCallLen()) ? new BigDecimal(0.00) : new BigDecimal(personal.getPaCallLen()));
                    zjCalledNum += personal.getZjCalledNum() == null ? 0 : personal.getZjCalledNum();
                    zjCallNum += personal.getZjCallNum() == null ? 0 : personal.getZjCallNum();
                    zjCalledLen = zjCalledLen.add(StringUtils.isEmpty(personal.getZjCallLen()) ? new BigDecimal(0.00) : new BigDecimal(personal.getZjCallLen()));
                    dyCalledNum += personal.getDyCalledNum() == null ? 0 : personal.getDyCalledNum();
                    dyCallNum += personal.getDyCallNum() == null ? 0 : personal.getDyCallNum();
                    dyCalledLen = dyCalledLen.add(StringUtils.isEmpty(personal.getDyCallLen()) ? new BigDecimal(0.00) : new BigDecimal(personal.getDyCallLen()));
                    totalCalledNum += personal.getTotalCalledNum();
                    totalCallNum += personal.getTotalCallNum();
                    totalCalledLen = totalCalledLen.add(new BigDecimal(personal.getTotalCallLen()));
                }
                if (!timePeriodList.contains("0-9")) {
                    TLcReportPersonal personal = buildReportPersonal(tLcReportPersonal, reportPersonal);
                    personal = personal.setTimePeriod("0-9");
                    list.add(personal);
                }
                if (!timePeriodList.contains("09-10")) {
                    TLcReportPersonal personal = buildReportPersonal(tLcReportPersonal, reportPersonal);
                    personal = personal.setTimePeriod("09-10");
                    list.add(personal);
                }
                if (!timePeriodList.contains("10-11")) {
                    TLcReportPersonal personal = buildReportPersonal(tLcReportPersonal, reportPersonal);
                    personal = personal.setTimePeriod("10-11");
                    list.add(personal);
                }
                if (!timePeriodList.contains("11-12")) {
                    TLcReportPersonal personal = buildReportPersonal(tLcReportPersonal, reportPersonal);
                    personal = personal.setTimePeriod("11-12");
                    list.add(personal);
                }
                if (!timePeriodList.contains("12-13")) {
                    TLcReportPersonal personal = buildReportPersonal(tLcReportPersonal, reportPersonal);
                    personal = personal.setTimePeriod("12-13");
                    list.add(personal);
                }
                if (!timePeriodList.contains("13-14")) {
                    TLcReportPersonal personal = buildReportPersonal(tLcReportPersonal, reportPersonal);
                    personal = personal.setTimePeriod("13-14");
                    list.add(personal);
                }
                if (!timePeriodList.contains("14-15")) {
                    TLcReportPersonal personal = buildReportPersonal(tLcReportPersonal, reportPersonal);
                    personal = personal.setTimePeriod("14-15");
                    list.add(personal);
                }
                if (!timePeriodList.contains("15-16")) {
                    TLcReportPersonal personal = buildReportPersonal(tLcReportPersonal, reportPersonal);
                    personal = personal.setTimePeriod("15-16");
                    list.add(personal);
                }
                if (!timePeriodList.contains("16-17")) {
                    TLcReportPersonal personal = buildReportPersonal(tLcReportPersonal, reportPersonal);
                    personal = personal.setTimePeriod("16-17");
                    list.add(personal);
                }
                if (!timePeriodList.contains("17-18")) {
                    TLcReportPersonal personal = buildReportPersonal(tLcReportPersonal, reportPersonal);
                    personal = personal.setTimePeriod("17-18");
                    list.add(personal);
                }
                if (!timePeriodList.contains("18-19")) {
                    TLcReportPersonal personal = buildReportPersonal(tLcReportPersonal, reportPersonal);
                    personal = personal.setTimePeriod("18-19");
                    list.add(personal);
                }
                if (!timePeriodList.contains("19-20")) {
                    TLcReportPersonal personal = buildReportPersonal(tLcReportPersonal, reportPersonal);
                    personal = personal.setTimePeriod("19-20");
                    list.add(personal);
                }
                if (!timePeriodList.contains("20-24")) {
                    TLcReportPersonal personal = buildReportPersonal(tLcReportPersonal, reportPersonal);
                    personal = personal.setTimePeriod("20-24");
                    list.add(personal);
                }
                TLcReportPersonal totalPersonal = TLcReportPersonal.builder()
                        .reportData(tLcReportPersonal.getReportData())
                        .timePeriod("合计")
                        .paCalledNum(paCalledNum)
                        .paCallNum(paCallNum)
                        .paCallLen(String.valueOf(paCalledLen))
                        .zjCalledNum(zjCalledNum)
                        .zjCallNum(zjCallNum)
                        .zjCallLen(String.valueOf(zjCalledLen))
                        .dyCalledNum(dyCalledNum)
                        .dyCallNum(dyCallNum)
                        .dyCallLen(String.valueOf(dyCalledLen))
                        .totalCalledNum(totalCalledNum)
                        .totalCallNum(totalCallNum)
                        .totalCallLen(String.valueOf(totalCalledLen))
                        .userGroup(reportPersonal.getUserGroup())
                        .loginName(reportPersonal.getLoginName())
                        .userName(reportPersonal.getUserName())
                        .build();
                list.add(totalPersonal);
            }
            list = list.stream().sorted(Comparator.comparing(TLcReportPersonal::getLoginName).thenComparing(TLcReportPersonal::getTimePeriod)).collect(Collectors.toList());
        }
        return list;
    }

    private TLcReportPersonal buildReportPersonal(TLcReportPersonal tLcReportPersonal, TLcReportPersonal reportPersonal) {
        return TLcReportPersonal.builder()
                        .reportData(tLcReportPersonal.getReportData())
                        .userGroup(reportPersonal.getUserGroup())
                        .loginName(reportPersonal.getLoginName())
                        .userName(reportPersonal.getUserName())
                        .build();
    }

//    private List<TLcReportPersonal> selectReportPersonalList(Map<String, Object> param) {
//        return this.tLcReportPersonalMapper.selectReportPersonalList(param);
//    }

    /**
     * 新增通时通次个人明细汇总报
     *
     * @param tLcReportPersonal 通时通次个人明细汇总报
     * @return 结果
     */
    @Override
    public int insertTLcReportPersonal(TLcReportPersonal tLcReportPersonal) {
        return tLcReportPersonalMapper.insertTLcReportPersonal(tLcReportPersonal);
    }

}
