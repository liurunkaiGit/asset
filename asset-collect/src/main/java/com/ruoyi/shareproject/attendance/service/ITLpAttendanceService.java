package com.ruoyi.shareproject.attendance.service;

import com.ruoyi.shareproject.attendance.domain.TLpAttendance;

import java.util.List;

/**
 * 【项目信息管理】Service接口
 * 
 * @author gaohg
 * @date 2020-10-15
 */
public interface ITLpAttendanceService 
{
    /**
     * 查询【项目信息管理】
     * 
     * @param id 【项目信息管理】ID
     * @return 【项目信息管理】
     */
    public TLpAttendance selectTLpAttendanceById(Long id);

    /**
     * 查询【项目信息管理】列表
     * 
     * @param tLpAttendance 【项目信息管理】
     * @return 【项目信息管理】集合
     */
    public List<TLpAttendance> selectTLpAttendanceList(TLpAttendance tLpAttendance);

    /**
     * 新增【项目信息管理】
     * 
     * @param tLpAttendance 【项目信息管理】
     * @return 结果
     */
    public int insertTLpAttendance(TLpAttendance tLpAttendance);

    /**
     * 修改【项目信息管理】
     * 
     * @param tLpAttendance 【项目信息管理】
     * @return 结果
     */
    public int updateTLpAttendance(TLpAttendance tLpAttendance);

    /**
     * 批量删除【项目信息管理】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpAttendanceByIds(String ids);

    /**
     * 删除【项目信息管理】信息
     * 
     * @param id 【项目信息管理】ID
     * @return 结果
     */
    public int deleteTLpAttendanceById(Long id);
}
