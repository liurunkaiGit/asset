package com.ruoyi.report.mapper;

import com.ruoyi.report.domain.TLcReportPlatform;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author liurunkai
 * @date 2020-08-04
 */
public interface TLcReportPlatformMapper {

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcReportPlatform 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLcReportPlatform> selectTLcReportPlatformList(TLcReportPlatform tLcReportPlatform);

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcReportPlatform 【请填写功能名称】
     * @return 结果
     */
    public int insertTLcReportPlatform(TLcReportPlatform tLcReportPlatform);

    List<TLcReportPlatform> selectReportPlatformList(Map<String, Object> param);

    /**
     * 查询指定时间段数据
     *
     * @param param
     * @return
     */
    TLcReportPlatform selectReportPlatform(Map<String, Object> param);
}
