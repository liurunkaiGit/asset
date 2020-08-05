package com.ruoyi.report.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.report.domain.TLcReportDayProcess;
import com.ruoyi.report.domain.TLcReportPlatform;
import com.ruoyi.report.mapper.TLcReportPlatformMapper;
import com.ruoyi.report.service.ITLcReportPlatformService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author liurunkai
 * @date 2020-08-04
 */
@Service
public class TLcReportPlatformServiceImpl implements ITLcReportPlatformService {
    @Autowired
    private TLcReportPlatformMapper tLcReportPlatformMapper;

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcReportPlatform 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TLcReportPlatform> selectTLcReportPlatformList(TLcReportPlatform tLcReportPlatform) {
        List<TLcReportPlatform> list;
        if (tLcReportPlatform.getReportData() == null || org.apache.commons.lang3.time.DateUtils.isSameDay(tLcReportPlatform.getReportData(), new Date())) {
            Map<String, Object> param = new HashMap<>();
            param.put("day", 0);
            list = selectReportPlatformList(param);
        } else {
            list = this.tLcReportPlatformMapper.selectTLcReportPlatformList(tLcReportPlatform);
        }
        return list;
    }

    private List<TLcReportPlatform> selectReportPlatformList(Map<String, Object> param) {
        return this.tLcReportPlatformMapper.selectReportPlatformList(param);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcReportPlatform 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTLcReportPlatform(TLcReportPlatform tLcReportPlatform) {
        return tLcReportPlatformMapper.insertTLcReportPlatform(tLcReportPlatform);
    }
}
