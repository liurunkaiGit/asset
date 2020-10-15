package com.ruoyi.shareproject.attendance.service.impl;

import java.util.List;

import com.ruoyi.shareproject.attendance.domain.TLpAttendance;
import com.ruoyi.shareproject.attendance.mapper.TLpAttendanceMapper;
import com.ruoyi.shareproject.attendance.service.ITLpAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 【出勤信息管理】Service业务层处理
 * 
 * @author gaohg
 * @date 2020-10-15
 */
@Service
public class TLpAttendanceServiceImpl implements ITLpAttendanceService
{
    @Autowired
    private TLpAttendanceMapper tLpAttendanceMapper;

    /**
     * 查询【出勤信息管理】
     * 
     * @param id 【出勤信息管理】ID
     * @return 【出勤信息管理】
     */
    @Override
    public TLpAttendance selectTLpAttendanceById(Long id)
    {
        return tLpAttendanceMapper.selectTLpAttendanceById(id);
    }

    /**
     * 查询【出勤信息管理】列表
     * 
     * @param tLpAttendance 【出勤信息管理】
     * @return 【出勤信息管理】
     */
    @Override
    public List<TLpAttendance> selectTLpAttendanceList(TLpAttendance tLpAttendance)
    {
        return tLpAttendanceMapper.selectTLpAttendanceList(tLpAttendance);
    }

    /**
     * 新增【出勤信息管理】
     * 
     * @param tLpAttendance 【出勤信息管理】
     * @return 结果
     */
    @Override
    public int insertTLpAttendance(TLpAttendance tLpAttendance)
    {
        return tLpAttendanceMapper.insertTLpAttendance(tLpAttendance);
    }

    /**
     * 修改【出勤信息管理】
     * 
     * @param tLpAttendance 【出勤信息管理】
     * @return 结果
     */
    @Override
    public int updateTLpAttendance(TLpAttendance tLpAttendance)
    {
        return tLpAttendanceMapper.updateTLpAttendance(tLpAttendance);
    }

    /**
     * 删除【出勤信息管理】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLpAttendanceByIds(String ids)
    {
        return tLpAttendanceMapper.deleteTLpAttendanceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【出勤信息管理】信息
     * 
     * @param id 【出勤信息管理】ID
     * @return 结果
     */
    @Override
    public int deleteTLpAttendanceById(Long id)
    {
        return tLpAttendanceMapper.deleteTLpAttendanceById(id);
    }
}
