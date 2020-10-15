package com.ruoyi.shareproject.attendance.mapper;

import com.ruoyi.shareproject.attendance.domain.TLpAttendance;

import java.util.List;

/**
 * 【出勤信息管理】Mapper接口
 * 
 * @author gaohg
 * @date 2020-10-15
 */
public interface TLpAttendanceMapper 
{
    /**
     * 查询【出勤信息管理】
     * 
     * @param id 【出勤信息管理】ID
     * @return 【出勤信息管理】
     */
    public TLpAttendance selectTLpAttendanceById(Long id);

    /**
     * 查询【出勤信息管理】列表
     * 
     * @param tLpAttendance 【出勤信息管理】
     * @return 【出勤信息管理】集合
     */
    public List<TLpAttendance> selectTLpAttendanceList(TLpAttendance tLpAttendance);

    /**
     * 新增【出勤信息管理】
     * 
     * @param tLpAttendance 【出勤信息管理】
     * @return 结果
     */
    public int insertTLpAttendance(TLpAttendance tLpAttendance);

    /**
     * 修改【出勤信息管理】
     * 
     * @param tLpAttendance 【出勤信息管理】
     * @return 结果
     */
    public int updateTLpAttendance(TLpAttendance tLpAttendance);

    /**
     * 删除【出勤信息管理】
     * 
     * @param id 【出勤信息管理】ID
     * @return 结果
     */
    public int deleteTLpAttendanceById(Long id);

    /**
     * 批量删除【出勤信息管理】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpAttendanceByIds(String[] ids);
}
