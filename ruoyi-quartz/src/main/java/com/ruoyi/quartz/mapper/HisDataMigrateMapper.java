package com.ruoyi.quartz.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/7/6 15:05
 */
@Mapper
public interface HisDataMigrateMapper {

    void hisDataMigrate(Map<String, Object> param);
}
