package com.ruoyi.config;

import com.ruoyi.enums.AllocatRuleEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/20 16:33
 */
@Data
@Slf4j
@Component("com.ruoyi.config.AppConfig")
@Configuration
@ConfigurationProperties(prefix = "enable", ignoreInvalidFields = true)
public class AppConfig {

    private Boolean allocatTaskAverageNum;
    private Boolean allocatTaskAverageMoney;

    // 是否开启定时创建并启动机器人任务
    private Boolean startRobotTask;
    // 是否开启定时生成报表任务
    private Boolean createReport;

    //限制只能开启一个自动分配任务
    public AllocatRuleEnum validOnlyTrue() {
        int trueNum = 0;
        AllocatRuleEnum allocatRuleEnum = null;
        if(this.allocatTaskAverageNum) {
            trueNum++;
            allocatRuleEnum = AllocatRuleEnum.DUNCASE_NUM_AVERAGE;
        }
        if(this.allocatTaskAverageMoney) {
            trueNum++;
            allocatRuleEnum = AllocatRuleEnum.DUNCASE_MONEY_AVERAGE;
        }
        if(trueNum > 1) {
            log.error("自动分配任务规则只能开启一个");
            throw new RuntimeException("自动分配任务规则只能开启一个");
        }
        return allocatRuleEnum;
    }
}
