package com.ruoyi.assetspackage.task;

import com.ruoyi.assetspackage.service.ICurAssetsPackageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author guozeqi
 * @create 2020-07-21
 */
@Slf4j
@Component("TableTimer")
public class TableTimer {

    @Autowired
    private ICurAssetsPackageService curAssetsPackageService;

    @Value("${isEnableTimer}")
    private Boolean isEnableTimer;

    /**
     * 定时清空资产临时表
     */
    public void autoClearTable()
    {
        if(!isEnableTimer){
            return;
        }
        log.info("执行定时清空资产临时表任务开始============================");
        //定时清空资产临时表
        curAssetsPackageService.clearTempTable();

    }

}
