package com.ruoyi.quartz.task;

import com.ruoyi.config.AppConfig;
import com.ruoyi.quartz.service.HisDataMigrateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @Description: 资产表、案件表、任务表、客户基本信息表、客户工作信息表、客户联系人表、电催记录表、行动码表、案件轨迹表 数据迁移
 * @author: liurunkai
 * @Date: 2020/7/6 14:09
 */
@Slf4j
@Component("hisDataMigrate")
public class HisDataMigrate {

    @Autowired
    private HisDataMigrateService hisDataMigrateService;
    @Autowired
    private AppConfig appConfig;

    /**
     * 已结案的历史数据迁移
     */
    public void hisDataMigrate() {
        if (!appConfig.getStartRobotTask()) {
            log.info("定时历史数据迁移任务未开启");
            return;
        }
        log.info("开始执行数据迁移，时间：{}", LocalDateTime.now(ZoneId.systemDefault()));
        this.hisDataMigrateService.hisDataMigrate();
    }
}
