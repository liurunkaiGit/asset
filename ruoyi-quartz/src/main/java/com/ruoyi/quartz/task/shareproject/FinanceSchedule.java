package com.ruoyi.quartz.task.shareproject;

import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.enums.IsNoEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.report.domain.TLcReportDayProcess;
import com.ruoyi.shareproject.finance.commission.domain.TLpFinanceCommission;
import com.ruoyi.shareproject.finance.commission.service.ITLpFinanceCommissionService;
import com.ruoyi.shareproject.projectinformation.domain.TLpProjectInformation;
import com.ruoyi.shareproject.projectinformation.service.ITLpProjectInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 共享信息管理-财务定时
 * @author: liurunkai
 * @Date: 2020/10/27 18:27
 */
@Slf4j
@Component("financeSchedule")
public class FinanceSchedule {

    @Value("${isEnableTimer}")
    private Boolean isEnableTimer;
    @Autowired
    private ITLpProjectInformationService projectInformationService;
    @Autowired
    private ITLpFinanceCommissionService financeCommissionService;

    /**
     * 每个月1号定时生成项目结佣数据
     */
    public void createCommission() {
        if (!isEnableTimer) {
            log.info("定时生成项目结佣任务未开启");
        } else {
            log.info("开始定时项目结佣任务");
            // 查找所有的项目
            List<TLpProjectInformation> projectList = this.projectInformationService.selectTLpProjectInformationList(new TLpProjectInformation());
            String month = DateUtils.parseDateToStr(DateUtils.YYYY_MM, new Date());
            if (projectList != null && projectList.size() > 0) {
                projectList.stream().forEach(project -> {
                    TLpFinanceCommission financeCommission = TLpFinanceCommission.builder()
                            .month(month)
                            .projectId(project.getId())
                            .projectName(project.getNames())
                            .feeStatus(IsNoEnum.NO.getCode())
                            .build();
                    financeCommissionService.insertTLpFinanceCommission(financeCommission);
                    log.info("{}生成项目结佣成功,{}", project.getNames());
                });
            }
            log.info("完成定时生成项目结佣任务...");
        }
    }
}
