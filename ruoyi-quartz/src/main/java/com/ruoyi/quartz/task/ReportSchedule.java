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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/7/21 9:23
 */
@Slf4j
@Service("reportSchedule")
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
            // 查询每日过程指标数据
            Map<String, Object> processParam = new HashMap<>();
            processParam.put("startDate", DateUtils.getStartOfDay(new Date()));
            processParam.put("endDate", DateUtils.getEndOfDay(new Date()));
            processParam.put("day", 0);
            if (orgPackageList != null && orgPackageList.size() > 0) {
                orgPackageList.stream().forEach(orgPackage -> {
                    processParam.put("orgId", orgPackage.getDeptId());
                    List<TLcReportDayProcess> reportDayProcessList = this.reportDayProcessService.selectDayProcess(processParam);
                    reportDayProcessList.stream().forEach(reportRecovery -> this.reportDayProcessService.insertTLcReportDayProcess(reportRecovery));
                    log.info("{}生成每日过程指标报表成功,{}", orgPackage.getOrgName(), DateUtils.getNowDate());
                });
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
            log.info("定时生成通时通次-个人明细汇总报表任务未开启");
        } else {
            log.info("开始定时生成通时通次-个人明细汇总报表任务");
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
            log.info("生成通时通次-个人明细汇总报表成功,{}", DateUtils.getNowDate());
        }
    }
}
