package com.ruoyi.report.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.report.domain.TLcReportPersonal;
import com.ruoyi.report.domain.TLcReportPlatform;
import com.ruoyi.report.mapper.TLcReportPersonalMapper;
import com.ruoyi.report.service.ITLcReportPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通时通次个人明细汇总报Service业务层处理
 *
 * @author liurunkai
 * @date 2020-08-05
 */
@Service
public class TLcReportPersonalServiceImpl implements ITLcReportPersonalService {
    @Autowired
    private TLcReportPersonalMapper tLcReportPersonalMapper;

    /**
     * 查询通时通次个人明细汇总报列表
     *
     * @param tLcReportPersonal 通时通次个人明细汇总报
     * @return 通时通次个人明细汇总报
     */
    @Override
    public List<TLcReportPersonal> selectTLcReportPersonalList(TLcReportPersonal tLcReportPersonal) {
//        List<TLcReportPersonal> list;
//        if (tLcReportPersonal.getReportData() == null || org.apache.commons.lang3.time.DateUtils.isSameDay(tLcReportPersonal.getReportData(), new Date())) {
//            Map<String, Object> param = new HashMap<>();
////            param.put("day", 0);
//            param.put("date", LocalDate.now());
//            if (StringUtils.isNotBlank(tLcReportPersonal.getUserName())) {
//                param.put("agentName", tLcReportPersonal.getUserName());
//            }
//            list = selectReportPersonalList(param);
//        } else {
//            list = this.tLcReportPersonalMapper.selectTLcReportPersonalList(tLcReportPersonal);
//        }
        List<TLcReportPersonal> list = this.tLcReportPersonalMapper.selectTLcReportPersonalList(tLcReportPersonal);
        return list;
    }

    private List<TLcReportPersonal> selectReportPersonalList(Map<String, Object> param) {
        return this.tLcReportPersonalMapper.selectReportPersonalList(param);
    }

    /**
     * 新增通时通次个人明细汇总报
     *
     * @param tLcReportPersonal 通时通次个人明细汇总报
     * @return 结果
     */
    @Override
    public int insertTLcReportPersonal(TLcReportPersonal tLcReportPersonal) {
        return tLcReportPersonalMapper.insertTLcReportPersonal(tLcReportPersonal);
    }

}
