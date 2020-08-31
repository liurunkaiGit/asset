package com.ruoyi.report.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.report.domain.TLcReportDayProcess;
import com.ruoyi.report.domain.TLcReportPlatform;
import com.ruoyi.report.mapper.TLcReportPlatformMapper;
import com.ruoyi.report.service.ITLcReportPlatformService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
//        List<TLcReportPlatform> list;
//        if (tLcReportPlatform.getReportData() == null || org.apache.commons.lang3.time.DateUtils.isSameDay(tLcReportPlatform.getReportData(), new Date())) {
//            Map<String, Object> param = new HashMap<>();
////            param.put("day", 0);
//            param.put("date", LocalDate.now());
//            list = selectReportPlatformList(param);
//        } else {
//            list = this.tLcReportPlatformMapper.selectTLcReportPlatformList(tLcReportPlatform);
//        }
        List<TLcReportPlatform> list = this.tLcReportPlatformMapper.selectTLcReportPlatformList(tLcReportPlatform);
        Integer paCalledNum = 0;
        Integer paCallNum = 0;
        BigDecimal paCalledLen = new BigDecimal(0.00);
        Integer zjCalledNum = 0;
        Integer zjCallNum = 0;
        BigDecimal zjCalledLen = new BigDecimal(0.00);
        Integer totalCalledNum = 0;
        Integer totalCallNum = 0;
        BigDecimal totalCalledLen = new BigDecimal(0.00);
        // 遍历计算合计行的合计值
        for (TLcReportPlatform platform : list) {
            // 计算每一行的合计值
            Integer rowTotalCalledNum = (platform.getPaCalledNum() == null ? 0 : platform.getPaCalledNum()) + (platform.getZjCalledNum() == null ? 0 : platform.getZjCalledNum());
            Integer rowTotalCallNum = (platform.getPaCallNum() == null ? 0 : platform.getPaCallNum()) + (platform.getZjCallNum() == null ? 0 : platform.getZjCallNum());
            String rowTotalCallLen = String.valueOf(StringUtils.isEmpty(platform.getPaCallLen()) ? new BigDecimal(0.00) : new BigDecimal(platform.getPaCallLen()).add(StringUtils.isEmpty(platform.getZjCallLen()) ? new BigDecimal(0.00) : new BigDecimal(platform.getZjCallLen())));
            // 设置每一行的合计
            platform.setTotalCalledNum(rowTotalCalledNum);
            platform.setTotalCallNum(rowTotalCallNum);
            platform.setTotalCallLen(rowTotalCallLen);
            // 计算合计行的合计值
            paCalledNum += platform.getPaCalledNum() == null ? 0 : platform.getPaCalledNum();
            paCallNum += platform.getPaCallNum() == null ? 0 : platform.getPaCallNum();
            paCalledLen = paCalledLen.add(StringUtils.isEmpty(platform.getPaCallLen()) ? new BigDecimal(0.00) : new BigDecimal(platform.getPaCallLen()));
            zjCalledNum += platform.getZjCalledNum() == null ? 0 : platform.getZjCalledNum();
            zjCallNum += platform.getZjCallNum() == null ? 0 : platform.getZjCallNum();
            zjCalledLen = zjCalledLen.add(StringUtils.isEmpty(platform.getZjCallLen()) ? new BigDecimal(0.00) : new BigDecimal(platform.getZjCallLen()));
            totalCalledNum += platform.getTotalCalledNum();
            totalCallNum += platform.getTotalCallNum();
            totalCalledLen = totalCalledLen.add(new BigDecimal(platform.getTotalCallLen()));
        }
        TLcReportPlatform total = TLcReportPlatform.builder()
                .reportData(tLcReportPlatform.getReportData())
                .timePeriod("合计")
                .paCalledNum(paCalledNum)
                .paCallNum(paCallNum)
                .paCallLen(String.valueOf(paCalledLen))
                .zjCalledNum(zjCalledNum)
                .zjCallNum(zjCallNum)
                .zjCallLen(String.valueOf(zjCalledLen))
                .totalCalledNum(totalCalledNum)
                .totalCallNum(totalCallNum)
                .totalCallLen(String.valueOf(totalCalledLen))
                .build();
        list.add(total);
        return list;
    }

//    private List<TLcReportPlatform> selectReportPlatformList(Map<String, Object> param) {
//        return this.tLcReportPlatformMapper.selectReportPlatformList(param);
//    }

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
