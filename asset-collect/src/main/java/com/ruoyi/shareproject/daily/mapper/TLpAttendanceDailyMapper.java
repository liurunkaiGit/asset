package com.ruoyi.shareproject.daily.mapper;

import com.ruoyi.shareproject.daily.domain.TLpAttendanceDaily;

import java.util.List;

/**
 * 【项目日报-出勤信息】Mapper接口
 * 
 * @author gaohg
 * @date 2020-10-26
 */
public interface TLpAttendanceDailyMapper 
{
    /**
     * 查询【项目日报-出勤信息】
     * 
     * @param id 【项目日报-出勤信息】ID
     * @return 【项目日报-出勤信息】
     */
    public TLpAttendanceDaily selectTLpAttendanceDailyById(Long id);

    /**
     * 查询【项目日报-出勤信息】列表
     * 
     * @param tLpAttendanceDaily 【项目日报-出勤信息】
     * @return 【项目日报-出勤信息】集合
     */
    public List<TLpAttendanceDaily> selectTLpAttendanceDailyList(TLpAttendanceDaily tLpAttendanceDaily);

    /**
     * 新增【项目日报-出勤信息】
     * 
     * @param tLpAttendanceDaily 【项目日报-出勤信息】
     * @return 结果
     */
    public int insertTLpAttendanceDaily(TLpAttendanceDaily tLpAttendanceDaily);

    /**
     * 修改【项目日报-出勤信息】
     * 
     * @param tLpAttendanceDaily 【项目日报-出勤信息】
     * @return 结果
     */
    public int updateTLpAttendanceDaily(TLpAttendanceDaily tLpAttendanceDaily);

    /**
     * 删除【项目日报-出勤信息】
     * 
     * @param id 【项目日报-出勤信息】ID
     * @return 结果
     */
    public int deleteTLpAttendanceDailyById(Long id);

    /**删除【项目日报-出勤信息】
     * @param dailyId
     * @return
     */
    public int deleteTLpAttendanceDailyByDailyId(Long dailyId);

    /**
     * 批量删除【项目日报-出勤信息】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpAttendanceDailyByIds(String[] ids);
}
