package com.ruoyi.quartz.task;

import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.report.domain.*;
import com.ruoyi.report.mapper.TLcReportPersonalMapper;
import com.ruoyi.report.mapper.TLcReportPersonalNewMapper;
import com.ruoyi.report.mapper.TLcReportPlatformMapper;
import com.ruoyi.report.mapper.TLcReportPlatformNewMapper;
import com.ruoyi.report.service.ITLcReportCaseContactService;
import com.ruoyi.report.service.ITLcReportDayProcessService;
import com.ruoyi.report.service.ITLcReportRecoveryService;
import com.ruoyi.task.service.impl.TLcCallRecordServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/7/21 9:23
 */
@Slf4j
@Component("reportSchedule")
public class ReportSchedule {

    @Value("${isEnableTimer}")
    private Boolean isEnableTimer;
    @Autowired
    private IOrgPackageService orgPackageService;
    @Autowired
    private ITLcReportRecoveryService reportRecoveryService;
    @Autowired
    private ITLcReportDayProcessService reportDayProcessService;
    @Autowired
    private ITLcReportCaseContactService caseContactService;
    @Autowired
    private TLcReportPlatformMapper platformMapper;
    @Autowired
    private TLcReportPlatformNewMapper platformNewMapper;
    @Autowired
    private TLcReportPersonalMapper personalMapper;
    @Autowired
    private TLcReportPersonalNewMapper personalNewMapper;
    @Autowired
    private TLcCallRecordServiceImpl tLcCallRecordService;

    /**
     * 每日过程指标
     */
    public void createDayProcessReport() {
        if (!isEnableTimer) {
            log.info("定时生成每日过程指标报表任务未开启");
        } else {
            log.info("开始定时生成每日过程指标报表任务任务");
            // 查找所有的委托方
            List<OrgPackage> orgPackageList = this.orgPackageService.selectOrgPackageList(new OrgPackage());
            log.error("总共有{}个机构", orgPackageList);
            // 查询每日过程指标数据
            Map<String, Object> processParam = new HashMap<>();
            processParam.put("startDate", DateUtils.getStartOfDay(new Date()));
            processParam.put("endDate", DateUtils.getEndOfDay(new Date()));
            processParam.put("day", 0);
            if (orgPackageList != null && orgPackageList.size() > 0) {
                for (OrgPackage orgPackage : orgPackageList) {
                    processParam.put("orgId", orgPackage.getDeptId());
                    log.error("{}：开始查询每日过程指标报表,{}", orgPackage.getOrgName(), DateUtils.getNowDate());
                    List<TLcReportDayProcess> reportDayProcessList = this.reportDayProcessService.selectDayProcess(processParam);
                    log.error("{}：查询每日过程指标报表完成,{}，数据量：{}", orgPackage.getOrgName(), DateUtils.getNowDate(), reportDayProcessList.size());
                    if (reportDayProcessList != null && reportDayProcessList.size() > 0) {
                        for (TLcReportDayProcess dayProcess : reportDayProcessList) {
                            this.reportDayProcessService.insertTLcReportDayProcess(dayProcess);
                        }
                        log.error("{}生成每日过程指标报表成功,{}", orgPackage.getOrgName(), DateUtils.getNowDate());
                    }
                }
//                orgPackageList.stream().forEach(orgPackage -> {
//                    processParam.put("orgId", orgPackage.getDeptId());
//                    List<TLcReportDayProcess> reportDayProcessList = this.reportDayProcessService.selectDayProcess(processParam);
//                    reportDayProcessList.stream().forEach(dayProcess -> {
//                        this.reportDayProcessService.insertTLcReportDayProcess(dayProcess);
//                    });
//                    log.info("{}生成每日过程指标报表成功,{}", orgPackage.getOrgName(), DateUtils.getNowDate());
//                });
            }
            log.info("完成定时生成每日过程指标报表任务任务...");
        }
    }

    /**
     * 回收率报表
     */
    public void createRecoveryReport() {
        if (!isEnableTimer) {
            log.info("定时生成回收率报表任务未开启");
        } else {
            log.info("开始定时生成回收率报表任务");
            // 查找所有的委托方
            List<OrgPackage> orgPackageList = this.orgPackageService.selectOrgPackageList(new OrgPackage());
            // 查询回收率报表对应的数据
            Map<String, Object> recoveryParam = new HashMap<>();
            recoveryParam.put("startDate", DateUtils.getFirstDay());
            recoveryParam.put("day", 0);
            // 生成每个机构的回收率报表数据和合计数据
            if (orgPackageList != null && orgPackageList.size() > 0) {
                orgPackageList.stream().forEach(orgPackage -> {
                    recoveryParam.put("orgId", orgPackage.getDeptId());
                    List<TLcReportRecovery> reportRecoveryList = this.reportRecoveryService.selectRecoveryByPayment(recoveryParam);
                    reportRecoveryList.stream().forEach(reportRecovery -> this.reportRecoveryService.insertTLcReportRecovery(reportRecovery));
                    log.info("{}生成回收率报表成功,{}", orgPackage.getOrgName(), DateUtils.getNowDate());
                });
            }
            log.info("完成定时生成回收率报表任务...");
        }
    }

    /**
     * 案件可联率报表
     */
    public void createCaseContactReport() {
        if (!isEnableTimer) {
            log.info("定时生成案件可联率报表任务未开启");
        } else {
            log.info("开始定时生成案件可联率报表任务任务");
            // 查询可联案件渗透率报表数据
            Map<String, Object> contactParam = new HashMap<>();
            contactParam.put("startDate", DateUtils.getFirstDay());
            contactParam.put("day", 0);
            List<TLcReportCaseContact> caseContactList = this.caseContactService.selectCaseContactList(contactParam);
            caseContactList.stream().forEach(caseContact -> this.caseContactService.insertTLcReportCaseContact(caseContact));
            log.info("生成案件可联率报表成功,{}", DateUtils.getNowDate());
        }
    }

    /**
     * 通时通次-平台汇总报表
     */
    public void createPlatformReport(String date) {
        if (!isEnableTimer) {
            log.info("定时生成通时通次-平台汇总报表任务未开启");
        } else {
            log.info("开始定时生成通时通次-平台汇总报表任务");
            // 查询通时通次-平台汇总报表数据
            List<TLcReportPlatformNew> platformList = new ArrayList<>();
            Map<String, Object> param = new HashMap<>();
            long days = 1;
            if (StringUtils.isNotBlank(date)) {
                param.put("date", date);
                LocalDate localDate = LocalDate.parse(date);
                LocalDate now = LocalDate.now();
                days = now.toEpochDay() - localDate.toEpochDay();
            } else {
                param.put("date", LocalDate.now().minusDays(1));
            }
            for (int i = 8; i <= 20; i++) {
                if (i == 8) {
                    param.put("timePeriod", "0-9");
                    param.put("startTimePeriod", DateUtils.getTimePeriod(days, 0, 0, 0));
                    param.put("endTimePeriod", DateUtils.getTimePeriod(days, 8, 59, 59));
                } else if (i == 20) {
                    param.put("timePeriod", "20-24");
                    param.put("startTimePeriod", DateUtils.getTimePeriod(days, 20, 0, 0));
                    param.put("endTimePeriod", DateUtils.getTimePeriod(days, 23, 59, 59));
                    // 查询当前时间段是否有数据
                    Long count = this.tLcCallRecordService.selectCountByTimePeriod(param);
                    if (count == 0) {
                        continue;
                    }
                } else {
                    if (i == 9) {
                        param.put("timePeriod", "09-10");
                    } else {
                        param.put("timePeriod", i + "-" + (i + 1));
                    }
                    param.put("startTimePeriod", DateUtils.getTimePeriod(days, i, 0, 0));
                    param.put("endTimePeriod", DateUtils.getTimePeriod(days, i, 59, 59));
                }
                List<TLcReportPlatformNew> platformNewList = this.platformNewMapper.selectReportPlatform(param);
                platformNewList.stream().forEach(platformNew -> platformList.add(platformNew));
            }
            platformList.stream().forEach(reportPlatform -> this.platformNewMapper.insertTLcReportPlatformNew(reportPlatform));
            log.info("生成通时通次-平台汇总报表成功,{}", DateUtils.getNowDate());
        }
    }

    /**
     * 通时通次-个人明细汇总报表
     */
    public void createPersonalReport(String date) {
        if (!isEnableTimer) {
            log.info("定时生成通时通次-个人明细汇总报表查询电催记录表任务未开启");
        } else {
            log.info("开始定时生成通时通次-个人明细汇总报表查询电催记录表任务");
            // 查询通时通次-平台汇总报表数据
            List<TLcReportPersonalNew> personalList = new ArrayList<>();
            Map<String, Object> param = new HashMap<>();
            long days = 1;
            if (StringUtils.isNotBlank(date)) {
                param.put("date", date);
                LocalDate localDate = LocalDate.parse(date);
                LocalDate now = LocalDate.now();
                days = now.toEpochDay() - localDate.toEpochDay();
            } else {
                param.put("date", LocalDate.now().plusDays(-1));
            }
            for (int i = 8; i <= 20; i++) {
                if (i == 8) {
                    param.put("timePeriod", "0-9");
                    param.put("startTimePeriod", DateUtils.getTimePeriod(days, 0, 0, 0));
                    param.put("endTimePeriod", DateUtils.getTimePeriod(days, 8, 59, 59));
                } else if (i == 20) {
                    param.put("timePeriod", "20-24");
                    param.put("startTimePeriod", DateUtils.getTimePeriod(days, 20, 0, 0));
                    param.put("endTimePeriod", DateUtils.getTimePeriod(days, 23, 59, 59));
                    // 查询当前时间段是否有数据
                    Long count = this.tLcCallRecordService.selectCountByTimePeriod(param);
                    if (count == 0) {
                        continue;
                    }
                } else {
                    if (i == 9) {
                        param.put("timePeriod", "09-10");
                    } else {
                        param.put("timePeriod", i + "-" + (i + 1));
                    }
                    param.put("startTimePeriod", DateUtils.getTimePeriod(days, i, 0, 0));
                    param.put("endTimePeriod", DateUtils.getTimePeriod(days, i, 59, 59));
                }
                List<TLcReportPersonalNew> reportPersonalList = this.personalNewMapper.selectReportPersonalListByTimePeriod(param);
                reportPersonalList.stream().forEach(personal -> personalList.add(personal));
            }
            personalList.stream().forEach(personal -> this.personalNewMapper.insertTLcReportPersonalNew(personal));
            log.info("生成通时通次-个人明细汇总报表查询电催记录表成功,{}", DateUtils.getNowDate());
        }
    }

    /**
     * 通时通次-生成个人明细汇总报表
     */
    public void createPersonalReportResult(String date) throws Exception{
        if (!isEnableTimer) {
            log.info("定时生成通时通次-生成个人明细汇总报表任务未开启");
        } else {
            log.info("开始定时生成通时通次-生成个人明细汇总报表任务");
            // 查询通时通次-平台汇总报表数据
            TLcReportPersonal tLcReportPersonal = new TLcReportPersonal();
            if (StringUtils.isNotBlank(date)) {
                Date reportDate = DateUtils.parseDate(date, DateUtils.YYYY_MM_DD);
                tLcReportPersonal.setReportData(reportDate);
            } else {
                String sDate = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
                tLcReportPersonal.setReportData(DateUtils.stringConvertDate(sDate, DateUtils.YYYY_MM_DD));
            }
            List<TLcReportPersonal> list = this.personalMapper.selectReportPersonalList(tLcReportPersonal);
            if (list == null || list.size() == 0) {
                return;
            }
            List<TLcReportPersonal> resultList = completionData(list, tLcReportPersonal);
            resultList.stream().forEach(personal -> this.personalMapper.insertTLcReportPersonal(personal));
            log.info("生成通时通次-生成个人明细汇总报表成功,{}", DateUtils.getNowDate());
        }
    }

    private List<TLcReportPersonal> completionData(List<TLcReportPersonal> list, TLcReportPersonal tLcReportPersonal) {
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
                    BigDecimal paCallLen = org.apache.commons.lang3.StringUtils.isEmpty(personal.getPaCallLen()) ? new BigDecimal(0.00) : new BigDecimal(personal.getPaCallLen());
                    BigDecimal zjCallLen = org.apache.commons.lang3.StringUtils.isEmpty(personal.getZjCallLen()) ? new BigDecimal(0.00) : new BigDecimal(personal.getZjCallLen());
                    BigDecimal dyCallLen = org.apache.commons.lang3.StringUtils.isEmpty(personal.getDyCallLen()) ? new BigDecimal(0.00) : new BigDecimal(personal.getDyCallLen());
                    String rowTotalCallLen = String.valueOf(paCallLen.add(zjCallLen).add(dyCallLen));
                    // 设置每一行的合计值
                    personal.setTotalCalledNum(rowTotalCalledNum);
                    personal.setTotalCallNum(rowTotalCallNum);
                    personal.setTotalCallLen(rowTotalCallLen);
                    // 计算合计行的合计值
                    paCalledNum += personal.getPaCalledNum() == null ? 0 : personal.getPaCalledNum();
                    paCallNum += personal.getPaCallNum() == null ? 0 : personal.getPaCallNum();
                    paCalledLen = paCalledLen.add(org.apache.commons.lang3.StringUtils.isEmpty(personal.getPaCallLen()) ? new BigDecimal(0.00) : new BigDecimal(personal.getPaCallLen()));
                    zjCalledNum += personal.getZjCalledNum() == null ? 0 : personal.getZjCalledNum();
                    zjCallNum += personal.getZjCallNum() == null ? 0 : personal.getZjCallNum();
                    zjCalledLen = zjCalledLen.add(org.apache.commons.lang3.StringUtils.isEmpty(personal.getZjCallLen()) ? new BigDecimal(0.00) : new BigDecimal(personal.getZjCallLen()));
                    dyCalledNum += personal.getDyCalledNum() == null ? 0 : personal.getDyCalledNum();
                    dyCallNum += personal.getDyCallNum() == null ? 0 : personal.getDyCallNum();
                    dyCalledLen = dyCalledLen.add(org.apache.commons.lang3.StringUtils.isEmpty(personal.getDyCallLen()) ? new BigDecimal(0.00) : new BigDecimal(personal.getDyCallLen()));
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
//            list = list.stream().sorted(Comparator.comparing(TLcReportPersonal::getLoginName).thenComparing(TLcReportPersonal::getTimePeriod)).collect(Collectors.toList());
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
}
