package com.ruoyi.shareproject.daily.service;

import com.ruoyi.shareproject.daily.domain.TLpAttendanceDaily;

import java.util.List;

/**
 * 【日报管理-出勤信息】Service接口
 * 
 * @author gaohg
 * @date 2020-10-26
 */
public interface ITLpAttendanceDailyService 
{
    /**
     * 查询【日报管理-出勤信息】
     * 
     * @param id 【日报管理-出勤信息】ID
     * @return 【日报管理-出勤信息】
     */
    public TLpAttendanceDaily selectTLpAttendanceDailyById(Long id);

    /**
     * 查询【日报管理-出勤信息】列表
     * 
     * @param tLpAttendanceDaily 【日报管理-出勤信息】
     * @return 【日报管理-出勤信息】集合
     */
    public List<TLpAttendanceDaily> selectTLpAttendanceDailyList(TLpAttendanceDaily tLpAttendanceDaily);

    /**
     * 新增【日报管理-出勤信息】
     * 
     * @param tLpAttendanceDaily 【日报管理-出勤信息】
     * @return 结果
     */
    public int insertTLpAttendanceDaily(TLpAttendanceDaily tLpAttendanceDaily);

    /**
     * 修改【日报管理-出勤信息】
     * 
     * @param tLpAttendanceDaily 【日报管理-出勤信息】
     * @return 结果
     */
    public int updateTLpAttendanceDaily(TLpAttendanceDaily tLpAttendanceDaily);

    /**
     * 批量删除【日报管理-出勤信息】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpAttendanceDailyByIds(String ids);

    /**
     * 删除【日报管理-出勤信息】信息
     * 
     * @param id 【日报管理-出勤信息】ID
     * @return 结果
     */
    public int deleteTLpAttendanceDailyById(Long id);
}
