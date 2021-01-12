package com.ruoyi.report.service.impl;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.report.domain.TLcReportDayProcess;
import com.ruoyi.report.mapper.TLcReportDayProcessMapper;
import com.ruoyi.report.service.ITLcReportDayProcessService;
import com.ruoyi.system.domain.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 每日过程指标报Service业务层处理
 *
 * @author liurunkai
 * @date 2020-04-09
 */
@Service
public class TLcReportDayProcessServiceImpl extends BaseController implements ITLcReportDayProcessService {
    @Autowired
    private TLcReportDayProcessMapper tLcReportDayProcessMapper;

    /**
     * 查询每日过程指标报
     *
     * @param id 每日过程指标报ID
     * @return 每日过程指标报
     */
    @Override
    public TLcReportDayProcess selectTLcReportDayProcessById(Long id) {
        return tLcReportDayProcessMapper.selectTLcReportDayProcessById(id);
    }

    /**
     * 查询每日过程指标报列表
     *
     * @param tLcReportDayProcess 每日过程指标报
     * @return 每日过程指标报
     */
    @Override
    public List<TLcReportDayProcess> selectTLcReportDayProcessList(TLcReportDayProcess tLcReportDayProcess) {
        List<TLcReportDayProcess> list = new ArrayList<>();
        if (StringUtils.isBlank(tLcReportDayProcess.getOrgId())) {
            return list;
        }
        if (org.apache.commons.lang3.time.DateUtils.isSameDay(tLcReportDayProcess.getReportDate(), new Date())) {
            Map<String, Object> param = new HashMap<>();
            DateFormat dateFmt = new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_MM_SS);
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Date dBefore = calendar.getTime();
            String startDate = dateFmt.format(dBefore);
            startDate = startDate.substring(0, 10) + " 00:00:00";
            String endDate = startDate.substring(0, 10) + " 23:59:59";
            param.put("startDate", DateUtils.parseDate(startDate));
            param.put("endDate", DateUtils.parseDate(endDate));
            param.put("day", 0);
            param.put("orgId", tLcReportDayProcess.getOrgId());
            if (tLcReportDayProcess.getIsGroup() != null && tLcReportDayProcess.getIsGroup().equals(IsNoEnum.IS.getCode())) {
                param.put("userGroup",ShiroUtils.getSysUser().getUserGroup());
            }

            list = selectDayProcess(param);
        } else {
            list = this.tLcReportDayProcessMapper.selectTLcReportDayProcessList(tLcReportDayProcess);
        }
        return list;
    }

    /**
     * 新增每日过程指标报
     *
     * @param tLcReportDayProcess 每日过程指标报
     * @return 结果
     */
    @Override
    public int insertTLcReportDayProcess(TLcReportDayProcess tLcReportDayProcess) {
        return tLcReportDayProcessMapper.insertTLcReportDayProcess(tLcReportDayProcess);
    }

    /**
     * 修改每日过程指标报
     *
     * @param tLcReportDayProcess 每日过程指标报
     * @return 结果
     */
    @Override
    public int updateTLcReportDayProcess(TLcReportDayProcess tLcReportDayProcess) {
        return tLcReportDayProcessMapper.updateTLcReportDayProcess(tLcReportDayProcess);
    }

    /**
     * 删除每日过程指标报对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcReportDayProcessByIds(String ids) {
        return tLcReportDayProcessMapper.deleteTLcReportDayProcessByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除每日过程指标报信息
     *
     * @param id 每日过程指标报ID
     * @return 结果
     */
    @Override
    public int deleteTLcReportDayProcessById(Long id) {
        return tLcReportDayProcessMapper.deleteTLcReportDayProcessById(id);
    }

    @Override
    public List<TLcReportDayProcess> selectDayProcess(Map<String, Object> param) {
        return this.tLcReportDayProcessMapper.selectDayProcess(param);
    }

    @Override
    public List<TLcReportDayProcess> selectTLcReportMonthProcessList(TLcReportDayProcess tLcReportDayProcess) {
        List<TLcReportDayProcess> list = new ArrayList<>();
        if (StringUtils.isBlank(tLcReportDayProcess.getOrgId())) {
            return list;
        }
        list = this.tLcReportDayProcessMapper.selectTLcReportMonthProcessList(tLcReportDayProcess);
        if (list != null && list.size() > 0) {
            list.stream().forEach(process -> {
                if (process.getCallNum() == null || process.getConnectedCallNum() == null) {
                    process.setCallConnectedRecovery("0.00");
                } else {
                    BigDecimal connectedCallNum = new BigDecimal(process.getConnectedCallNum());
                    BigDecimal callNum = new BigDecimal(process.getCallNum());
                    process.setCallConnectedRecovery(connectedCallNum.divide(callNum, 1, BigDecimal.ROUND_HALF_UP).toString());
                }
            });
        }
        return list;
    }

}
