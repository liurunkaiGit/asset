package com.ruoyi.quartz.task;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.quartz.service.HisDataMigrateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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

    /**
     * 已结案的历史数据迁移
     */
    public void hisDataMigrate() {
        log.info("开始执行数据迁移，时间：{}", LocalDateTime.now(ZoneId.systemDefault()));
        this.hisDataMigrateService.hisDataMigrate();
    }
}
