package com.ruoyi.report.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.report.domain.TLcReportDayProcess;
import com.ruoyi.report.domain.TLcReportPersonal;
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
import java.util.stream.Collectors;

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
        List<TLcReportPlatform> list = this.tLcReportPlatformMapper.selectReportPlatformList(tLcReportPlatform);
        if (list != null && list.size() > 0) {
            List<String> timePeriodList = new ArrayList<>();
            for (TLcReportPlatform platform : list) {
                timePeriodList.add(platform.getTimePeriod());
            }
            if (!timePeriodList.contains("0-9")) {
                TLcReportPlatform platform = new TLcReportPlatform();
                platform.setReportData(tLcReportPlatform.getReportData());
                platform.setTimePeriod("0-9");
                list.add(platform);
            }
            if (!timePeriodList.contains("09-10")) {
                TLcReportPlatform platform = new TLcReportPlatform();
                platform.setReportData(tLcReportPlatform.getReportData());
                platform.setTimePeriod("09-10");
                list.add(platform);
            }
            if (!timePeriodList.contains("10-11")) {
                TLcReportPlatform platform = new TLcReportPlatform();
                platform.setReportData(tLcReportPlatform.getReportData());
                platform.setTimePeriod("10-11");
                list.add(platform);
            }
            if (!timePeriodList.contains("11-12")) {
                TLcReportPlatform platform = new TLcReportPlatform();
                platform.setReportData(tLcReportPlatform.getReportData());
                platform.setTimePeriod("11-12");
                list.add(platform);
            }
            if (!timePeriodList.contains("12-13")) {
                TLcReportPlatform platform = new TLcReportPlatform();
                platform.setReportData(tLcReportPlatform.getReportData());
                platform.setTimePeriod("12-13");
                list.add(platform);
            }
            if (!timePeriodList.contains("13-14")) {
                TLcReportPlatform platform = new TLcReportPlatform();
                platform.setReportData(tLcReportPlatform.getReportData());
                platform.setTimePeriod("13-14");
                list.add(platform);
            }
            if (!timePeriodList.contains("14-15")) {
                TLcReportPlatform platform = new TLcReportPlatform();
                platform.setReportData(tLcReportPlatform.getReportData());
                platform.setTimePeriod("14-15");
                list.add(platform);
            }
            if (!timePeriodList.contains("15-16")) {
                TLcReportPlatform platform = new TLcReportPlatform();
                platform.setReportData(tLcReportPlatform.getReportData());
                platform.setTimePeriod("15-16");
                list.add(platform);
            }
            if (!timePeriodList.contains("16-17")) {
                TLcReportPlatform platform = new TLcReportPlatform();
                platform.setReportData(tLcReportPlatform.getReportData());
                platform.setTimePeriod("16-17");
                list.add(platform);
            }
            if (!timePeriodList.contains("17-18")) {
                TLcReportPlatform platform = new TLcReportPlatform();
                platform.setReportData(tLcReportPlatform.getReportData());
                platform.setTimePeriod("17-18");
                list.add(platform);
            }
            if (!timePeriodList.contains("18-19")) {
                TLcReportPlatform platform = new TLcReportPlatform();
                platform.setReportData(tLcReportPlatform.getReportData());
                platform.setTimePeriod("18-19");
                list.add(platform);
            }
            if (!timePeriodList.contains("19-20")) {
                TLcReportPlatform platform = new TLcReportPlatform();
                platform.setReportData(tLcReportPlatform.getReportData());
                platform.setTimePeriod("19-20");
                list.add(platform);
            }
            if (!timePeriodList.contains("20-24")) {
                TLcReportPlatform platform = new TLcReportPlatform();
                platform.setReportData(tLcReportPlatform.getReportData());
                platform.setTimePeriod("20-24");
                list.add(platform);
            }
            Integer paCalledNum = 0;
            Integer paCallNum = 0;
            BigDecimal paCalledLen = new BigDecimal(0.00);
            Integer zjCalledNum = 0;
            Integer zjCallNum = 0;
            BigDecimal zjCalledLen = new BigDecimal(0.00);
            Integer dyCalledNum = 0;
            Integer dyCallNum = 0;
            BigDecimal dyCalledLen = new BigDecimal(0.00);
            Integer totalCalledNum = 0;
            Integer totalCallNum = 0;
            BigDecimal totalCalledLen = new BigDecimal(0.00);
            // 遍历计算合计行的合计值
            for (TLcReportPlatform platform : list) {
                // 计算每一行的合计值
                Integer rowTotalCalledNum = (platform.getPaCalledNum() == null ? 0 : platform.getPaCalledNum()) + (platform.getZjCalledNum() == null ? 0 : platform.getZjCalledNum()) + (platform.getDyCalledNum() == null ? 0 : platform.getDyCalledNum());
                Integer rowTotalCallNum = (platform.getPaCallNum() == null ? 0 : platform.getPaCallNum()) + (platform.getZjCallNum() == null ? 0 : platform.getZjCallNum()) + (platform.getDyCallNum() == null ? 0 : platform.getDyCallNum());
//                String rowTotalCallLen = String.valueOf(StringUtils.isEmpty(platform.getPaCallLen()) ? new BigDecimal(0.00) : new BigDecimal(platform.getPaCallLen()).add(StringUtils.isEmpty(platform.getZjCallLen()) ? new BigDecimal(0.00) : new BigDecimal(platform.getZjCallLen())).add(StringUtils.isEmpty(platform.getDyCallLen()) ? new BigDecimal(0.00) : new BigDecimal(platform.getDyCallLen())));
                BigDecimal paCallLen = StringUtils.isEmpty(platform.getPaCallLen()) ? new BigDecimal(0.00) : new BigDecimal(platform.getPaCallLen());
                BigDecimal zjCallLen = StringUtils.isEmpty(platform.getZjCallLen()) ? new BigDecimal(0.00) : new BigDecimal(platform.getZjCallLen());
                BigDecimal dyCallLen = StringUtils.isEmpty(platform.getDyCallLen()) ? new BigDecimal(0.00) : new BigDecimal(platform.getDyCallLen());
                String rowTotalCallLen = String.valueOf(paCallLen.add(zjCallLen).add(dyCallLen));
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
                dyCalledNum += platform.getDyCalledNum() == null ? 0 : platform.getDyCalledNum();
                dyCallNum += platform.getDyCallNum() == null ? 0 : platform.getDyCallNum();
                dyCalledLen = dyCalledLen.add(StringUtils.isEmpty(platform.getDyCallLen()) ? new BigDecimal(0.00) : new BigDecimal(platform.getDyCallLen()));
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
                    .dyCalledNum(dyCalledNum)
                    .dyCallNum(dyCallNum)
                    .dyCallLen(String.valueOf(dyCalledLen))
                    .totalCalledNum(totalCalledNum)
                    .totalCallNum(totalCallNum)
                    .totalCallLen(String.valueOf(totalCalledLen))
                    .build();
            list.add(total);
        }
        return list.stream().sorted(Comparator.comparing(TLcReportPlatform::getTimePeriod)).collect(Collectors.toList());
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
