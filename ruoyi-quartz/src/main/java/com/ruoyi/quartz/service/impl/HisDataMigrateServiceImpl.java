package com.ruoyi.quartz.service.impl;

import com.ruoyi.quartz.mapper.HisDataMigrateMapper;
import com.ruoyi.quartz.service.HisDataMigrateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void hisDataMigrate() {
        this.hisDataMigrateMapper.hisDataMigrate();
    }
}
