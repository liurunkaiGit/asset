package com.ruoyi.shareproject.daily.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.shareproject.attendance.domain.TLpAttendance;
import com.ruoyi.shareproject.attendance.mapper.TLpAttendanceMapper;
import com.ruoyi.shareproject.daily.domain.*;
import com.ruoyi.shareproject.daily.mapper.*;
import com.ruoyi.shareproject.daily.service.ITLpProjectDailyService;
import com.ruoyi.shareproject.monthlytarget.domain.TLpMonthlyTarget;
import com.ruoyi.shareproject.monthlytarget.mapper.TLpMonthlyTargetMapper;
import com.ruoyi.shareproject.process.domain.TLpProcess;
import com.ruoyi.shareproject.process.mapper.TLpProcessMapper;
import com.ruoyi.shareproject.projectinformation.domain.TLpProjectInformation;
import com.ruoyi.shareproject.projectinformation.mapper.TLpProjectInformationMapper;
import com.ruoyi.shareproject.result.domain.TLpResult;
import com.ruoyi.shareproject.result.mapper.TLpResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 【项目日报】Service业务层处理
 * 
 * @author gaohg
 * @date 2020-10-26
 */
@Service
public class TLpProjectDailyServiceImpl implements ITLpProjectDailyService
{
    @Autowired
    private TLpProjectDailyMapper tLpProjectDailyMapper;
    @Autowired
    private TLpMonthlyTargetMapper tLpMonthlyTargetMapper;
    @Autowired
    private TLpResultMapper tLpResultMapper;
    @Autowired
    private TLpProcessMapper tLpProcessMapper;
    @Autowired
    private TLpAttendanceDailyMapper tLpAttendanceDailyMapper;
    @Autowired
    private TLpAttendanceMapper tLpAttendanceMapper;
    @Autowired
    private TLpProjectInformationDailyMapper tLpProjectInformationDailyMapper;
    @Autowired
    private TLpProjectInformationMapper tLpProjectInformationMapper;
    @Autowired
    private TLpMonthlyTargetDailyMapper tLpMonthlyTargetDailyMapper;
    @Autowired
    private TLpProcessDailyMapper tLpProcessDailyMapper;
    @Autowired
    private TLpResultDailyMapper tLpResultDailyMapper;



    /**
     * 查询【项目日报】
     * 
     * @param id 【项目日报】ID
     * @return 【项目日报】
     */
    @Override
    public TLpProjectDaily selectTLpProjectDailyById(Long id)
    {
        return tLpProjectDailyMapper.selectTLpProjectDailyById(id);
    }

    /**
     * 查询【项目日报】列表
     * 
     * @param tLpProjectDaily 【项目日报】
     * @return 【项目日报】
     */
    @Override
    public List<TLpProjectDaily> selectTLpProjectDailyList(TLpProjectDaily tLpProjectDaily)
    {
        return tLpProjectDailyMapper.selectTLpProjectDailyList(tLpProjectDaily);
    }

    /**
     * 新增【项目日报】
     * 
     * @param tLpProjectDaily 【项目日报】
     * @return 结果
     */
    @Override
    @Transactional
    public int insertTLpProjectDaily(TLpProjectDaily tLpProjectDaily)
    {
        tLpProjectDaily.setDailyName(tLpProjectDaily.getProName()+DateUtils.parseDateToStr(DateUtils.YYYYMMDD,tLpProjectDaily.getDailyTime()));
        int rt = tLpProjectDailyMapper.insertTLpProjectDaily(tLpProjectDaily);
        shengcheng(tLpProjectDaily.getProId(),tLpProjectDaily.getDailyTime(),tLpProjectDaily.getDailyName(),tLpProjectDaily.getId());
        return rt;
    }

    private void shengcheng(Long proId,Date dailyTime,String dailyName,Long dailyId){
        //新增日报--出勤信息
        TLpAttendance te = findTLpAttendance(proId,dailyTime);
        TLpAttendanceDaily ty = new TLpAttendanceDaily();
        BeanUtils.copyBeanProp(ty,te);
        ty.setDailyId(dailyId);
        ty.setDailyName(dailyName);
        tLpAttendanceDailyMapper.insertTLpAttendanceDaily(ty);

        //新增日报--项目信息
        TLpProjectInformation tfn = findTLpProjectInformation(proId);
        TLpProjectInformationDaily tdy = new TLpProjectInformationDaily();
        BeanUtils.copyBeanProp(tdy,tfn);
        tdy.setDailyId(dailyId);
        tdy.setDailyName(dailyName);
        tLpProjectInformationDailyMapper.insertTLpProjectInformationDaily(tdy);

        //新增日报--月度指标
        TLpMonthlyTarget mt = findTLpMonthlyTarget(proId,dailyTime);
        TLpMonthlyTargetDaily tLpMonthlyTargetDaily = new TLpMonthlyTargetDaily();
        BeanUtils.copyBeanProp(tLpMonthlyTargetDaily,mt);
        tLpMonthlyTargetDaily.setDailyId(dailyId);
        tLpMonthlyTargetDaily.setDailyName(dailyName);
        tLpMonthlyTargetDailyMapper.insertTLpMonthlyTargetDaily(tLpMonthlyTargetDaily);

       //结果指标 批量增加
        TLpResultDaily tLpResultDaily = new TLpResultDaily();
        tLpResultDaily.setOrgId(proId);
        tLpResultDaily.setReportDate(dailyTime);
        tLpResultDaily.setDailyId(dailyId);
        tLpResultDaily.setDailyName(dailyName);
        tLpResultDailyMapper.insertTLpResultDailyBatch(tLpResultDaily);

        //过程指标
        TLpProcess ts = findTLpProcess(proId,dailyTime);
        TLpProcessDaily tLpProcessDaily = new TLpProcessDaily();
        BeanUtils.copyBeanProp(tLpProcessDaily,ts);
        tLpProcessDaily.setDailyId(dailyId);
        tLpProcessDaily.setDailyName(dailyName);
        tLpProcessDailyMapper.insertTLpProcessDaily(tLpProcessDaily);
    }

    private void deleteProDt(Long dailyId){
        //删除--出勤信息
        tLpAttendanceDailyMapper.deleteTLpAttendanceDailyByDailyId(dailyId);
        //删除--项目信息
        tLpProjectInformationDailyMapper.deleteTLpProjectInformationDailyByDailyId(dailyId);
        //删除--月度指标
        tLpMonthlyTargetDailyMapper.deleteTLpMonthlyTargetDailyByDailyId(dailyId);
        //删除--结果指标
        tLpResultDailyMapper.deleteTLpResultDailyByDailyId(dailyId);
        //删除--过程指标
        tLpProcessDailyMapper.deleteTLpProcessDailyByDailyId(dailyId);
    }

    private TLpAttendance findTLpAttendance(Long proId,Date date){
        TLpAttendance tt = new TLpAttendance();
        tt.setProId(proId);
        tt.setAttendanceDate(date);
        List<TLpAttendance>  lt =  tLpAttendanceMapper.selectTLpAttendanceList(tt);
        if(null == lt || lt.isEmpty())return null;
        return lt.get(0);
    }

    private TLpProjectInformation findTLpProjectInformation(Long id){
        return tLpProjectInformationMapper.selectTLpProjectInformationById(id);
    }


    /**
     * 修改【项目日报】
     * 
     * @param tLpProjectDaily 【项目日报】
     * @return 结果
     */
    @Override
    @Transactional
    public int updateTLpProjectDaily(TLpProjectDaily tLpProjectDaily)
    {
        deleteProDt(tLpProjectDaily.getId());
        tLpProjectDaily.setDailyName(tLpProjectDaily.getProName()+DateUtils.parseDateToStr(DateUtils.YYYYMMDD,tLpProjectDaily.getDailyTime()));
        int rl = tLpProjectDailyMapper.updateTLpProjectDaily(tLpProjectDaily);
        shengcheng(tLpProjectDaily.getProId(),tLpProjectDaily.getDailyTime(),tLpProjectDaily.getDailyName(),tLpProjectDaily.getId());
        return rl;
    }

    private TLpMonthlyTarget  findTLpMonthlyTarget(Long proId,Date date){
        TLpMonthlyTarget tt = new TLpMonthlyTarget();
        tt.setProId(proId);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);
        tt.setParticularMonth(month);
        tt.setParticularYear(year);
        List<TLpMonthlyTarget> lt = tLpMonthlyTargetMapper.selectTLpMonthlyTargetList(tt);
        if(null == lt || lt.isEmpty())return null;
        return  lt.get(0);

    }

    private TLpProcess findTLpProcess(Long proId,Date date){
        TLpProcess ts = new TLpProcess();
        ts.setOrgId(proId);
        ts.setReportDate(date);
        List<TLpProcess> gcl = tLpProcessMapper.selectTLpProcessList(ts);
        if(null == gcl || gcl.isEmpty())return null;
        return gcl.get(0);
    }

    @Override
    public int findZhibiao(Long proId, Date date) {
        //月度指标是否有记录
        TLpMonthlyTarget tt = findTLpMonthlyTarget(proId,date);
        if(null == tt)return -1;
        //查询结果指标是否存在记录
        TLpResult tr = new TLpResult();
        tr.setOrgId(proId);
        tr.setReportDate(date);
        List<TLpResult> jgl = tLpResultMapper.selectTLpResultList(tr);
        if(null == jgl || jgl.isEmpty())return -1;
        //查询过程指标是否存在记录
        TLpProcess ts = findTLpProcess(proId,date);
        if(null == ts)return -1;
        return 1;
    }

    /**
     * 删除【项目日报】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLpProjectDailyByIds(String ids)
    {
        return tLpProjectDailyMapper.deleteTLpProjectDailyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【项目日报】信息
     * 
     * @param id 【项目日报】ID
     * @return 结果
     */
    @Override
    public int deleteTLpProjectDailyById(Long id)
    {
        return tLpProjectDailyMapper.deleteTLpProjectDailyById(id);
    }
}
