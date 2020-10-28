package com.ruoyi.shareproject.process.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.report.domain.TLcReportDayProcess;
import com.ruoyi.report.service.ITLcReportDayProcessService;
import com.ruoyi.shareproject.attendance.domain.TLpAttendance;
import com.ruoyi.shareproject.attendance.service.ITLpAttendanceService;
import com.ruoyi.shareproject.process.domain.TLpProcess;
import com.ruoyi.shareproject.process.mapper.TLpProcessMapper;
import com.ruoyi.shareproject.process.service.ITLpProcessService;
import com.ruoyi.shareproject.projectinformation.domain.TLpProjectInformation;
import com.ruoyi.shareproject.projectinformation.service.ITLpProjectInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author liurunkai
 * @date 2020-10-16
 */
@Service
public class TLpProcessServiceImpl implements ITLpProcessService {

    @Autowired
    private TLpProcessMapper tLpProcessMapper;

    @Autowired
    private ITLpProjectInformationService projectInformationService;
    @Autowired
    private ITLcReportDayProcessService tLcReportDayProcessService;
    @Autowired
    private ITLpAttendanceService attendanceService;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TLpProcess selectTLpProcessById(Long id) {
        return tLpProcessMapper.selectTLpProcessById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLpProcess 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TLpProcess> selectTLpProcessList(TLpProcess tLpProcess) {
        if (tLpProcess.getEndReportDate() != null) {
            tLpProcess.setEndReportDate(DateUtils.getEndOfDay(tLpProcess.getEndReportDate()));
        }
        return tLpProcessMapper.selectTLpProcessList(tLpProcess);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param tLpProcess 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTLpProcess(TLpProcess tLpProcess) {
        String[] projectIdName = tLpProcess.getProjectIdName().split(",");
        tLpProcess.setOrgId(Long.valueOf(projectIdName[0]));
        tLpProcess.setOrgName(projectIdName[1]);
        tLpProcess.setCreateBy(ShiroUtils.getSysUser().getUserId().toString());
        return tLpProcessMapper.insertTLpProcess(tLpProcess);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param tLpProcess 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTLpProcess(TLpProcess tLpProcess) {
        String[] projectIdName = tLpProcess.getProjectIdName().split(",");
        tLpProcess.setOrgId(Long.valueOf(projectIdName[0]));
        tLpProcess.setOrgName(projectIdName[1]);
        tLpProcess.setUpdateBy(ShiroUtils.getSysUser().getUserId().toString());
        return tLpProcessMapper.updateTLpProcess(tLpProcess);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLpProcessByIds(String ids) {
        return tLpProcessMapper.deleteTLpProcessByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTLpProcessById(Long id) {
        return tLpProcessMapper.deleteTLpProcessById(id);
    }

    @Override
    public TLpProcess selectDayProcess(TLpProcess tLpProcess) {
        // 获取部门id
        TLpProjectInformation projectInformation = this.projectInformationService.selectTLpProjectInformationById(tLpProcess.getId());
        // 查询每日过程指标
        TLcReportDayProcess tLcReportDayProcess = new TLcReportDayProcess();
        tLcReportDayProcess.setOrgId(projectInformation.getDeptId().toString());
        tLcReportDayProcess.setReportDate(tLpProcess.getReportDate());
        List<TLcReportDayProcess> dayProcessList = this.tLcReportDayProcessService.selectTLcReportDayProcessList(tLcReportDayProcess);
        // 获取出勤人数
        TLpAttendance tLpAttendance = new TLpAttendance();
        tLpAttendance.setProId(tLpProcess.getId());
        tLpAttendance.setAttendanceDate(tLpProcess.getReportDate());
        List<TLpAttendance> tLpAttendances = this.attendanceService.selectTLpAttendanceList(tLpAttendance);
        Integer actualAttendance = 0;
        if (tLpAttendances != null && tLpAttendances.size() > 0) {
            actualAttendance = tLpAttendances.get(0).getActualAttendance();
        }
        // 计算拨打量，人均拨打量，通话时长，人均通话时长，接通率
        Integer callNum = 0;
        BigDecimal callLen = new BigDecimal("0.00");
        Integer connectedCallNum = 0;
        if (dayProcessList != null && dayProcessList.size() > 0) {
            for (TLcReportDayProcess dayProcess : dayProcessList) {
                if (dayProcess.getCallNum() != null) {
                    callNum += dayProcess.getCallNum();
                }
                if (StringUtils.isNotBlank(dayProcess.getCallLen())) {
                    callLen = callLen.add(new BigDecimal(dayProcess.getCallLen()));
                }
                if (dayProcess.getConnectedCallNum() != null) {
                    connectedCallNum += dayProcess.getConnectedCallNum();
                }
            }
        }
        tLpProcess.setTotalCallNum(callNum);
        if (actualAttendance > 0) {
            tLpProcess.setAveCallNum(new BigDecimal(callNum).divide(new BigDecimal(actualAttendance), 2, BigDecimal.ROUND_HALF_UP));
            tLpProcess.setAvgCallLen(callLen.divide(new BigDecimal(actualAttendance), 2, BigDecimal.ROUND_HALF_UP));
        } else {
            tLpProcess.setAveCallNum(new BigDecimal("0.00"));
            tLpProcess.setAvgCallLen(new BigDecimal("0.00"));
        }
        tLpProcess.setTotalCallLen(callLen.setScale(2, BigDecimal.ROUND_HALF_UP));
        tLpProcess.setTotalCalledNum(connectedCallNum);
        if (connectedCallNum > 0) {
            tLpProcess.setTotalCalledRate(new BigDecimal(connectedCallNum * 100 ).divide(new BigDecimal(callNum), 2, BigDecimal.ROUND_HALF_UP) + "%");
        } else {
            tLpProcess.setTotalCalledRate("0.00%");
        }
        return tLpProcess;
    }

    @Override
    public Integer selectProjectProcessUnique(TLpProcess tLpProcess) {
        return this.tLpProcessMapper.selectProjectProcessUnique(tLpProcess);
    }
}
