package com.ruoyi.quartz.service.impl;

import com.ruoyi.quartz.mapper.HisDataMigrateMapper;
import com.ruoyi.quartz.service.HisDataMigrateService;
import com.ruoyi.system.service.ISysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/7/6 15:02
 */
@Slf4j
@Service
@Transactional
public class HisDataMigrateServiceImpl implements HisDataMigrateService {

    @Autowired
    private HisDataMigrateMapper hisDataMigrateMapper;
    @Autowired
    private ISysConfigService sysConfigService;

    @Override
    public void hisDataMigrate() {
        Map<String, Object> param = new HashMap<>();
        String hisDataMigrateDays = this.sysConfigService.selectConfigByKey("hisDataMigrateDays");
        param.put("days", Integer.valueOf(hisDataMigrateDays));
        this.hisDataMigrateMapper.hisDataMigrate(param);
        // 批量外呼历史数据迁移
        Map<String, Object> batchCallParam = new HashMap<>();
        String batchCallHisDataMigrateDays = this.sysConfigService.selectConfigByKey("batchCallHisDataMigrateDays");
        batchCallParam.put("days", Integer.valueOf(batchCallHisDataMigrateDays));
        this.hisDataMigrateMapper.batchCallHisDataMigrate(batchCallParam);
    }
}
