package com.ruoyi.shareproject.daily.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.daily.domain.TLpAttendanceDaily;
import com.ruoyi.shareproject.daily.mapper.TLpAttendanceDailyMapper;
import com.ruoyi.shareproject.daily.service.ITLpAttendanceDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 【日报管理-出勤信息】Service业务层处理
 * 
 * @author gaohg
 * @date 2020-10-26
 */
@Service
public class TLpAttendanceDailyServiceImpl implements ITLpAttendanceDailyService 
{
    @Autowired
    private TLpAttendanceDailyMapper tLpAttendanceDailyMapper;

    /**
     * 查询【日报管理-出勤信息】
     * 
     * @param id 【日报管理-出勤信息】ID
     * @return 【日报管理-出勤信息】
     */
    @Override
    public TLpAttendanceDaily selectTLpAttendanceDailyById(Long id)
    {
        return tLpAttendanceDailyMapper.selectTLpAttendanceDailyById(id);
    }

    /**
     * 查询【日报管理-出勤信息】列表
     * 
     * @param tLpAttendanceDaily 【日报管理-出勤信息】
     * @return 【日报管理-出勤信息】
     */
    @Override
    public List<TLpAttendanceDaily> selectTLpAttendanceDailyList(TLpAttendanceDaily tLpAttendanceDaily)
    {
        return tLpAttendanceDailyMapper.selectTLpAttendanceDailyList(tLpAttendanceDaily);
    }

    /**
     * 新增【日报管理-出勤信息】
     * 
     * @param tLpAttendanceDaily 【日报管理-出勤信息】
     * @return 结果
     */
    @Override
    public int insertTLpAttendanceDaily(TLpAttendanceDaily tLpAttendanceDaily)
    {
        tLpAttendanceDaily.setCreateTime(DateUtils.getNowDate());
        return tLpAttendanceDailyMapper.insertTLpAttendanceDaily(tLpAttendanceDaily);
    }

    /**
     * 修改【日报管理-出勤信息】
     * 
     * @param tLpAttendanceDaily 【日报管理-出勤信息】
     * @return 结果
     */
    @Override
    public int updateTLpAttendanceDaily(TLpAttendanceDaily tLpAttendanceDaily)
    {
        tLpAttendanceDaily.setUpdateTime(DateUtils.getNowDate());
        return tLpAttendanceDailyMapper.updateTLpAttendanceDaily(tLpAttendanceDaily);
    }

    /**
     * 删除【日报管理-出勤信息】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLpAttendanceDailyByIds(String ids)
    {
        return tLpAttendanceDailyMapper.deleteTLpAttendanceDailyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【日报管理-出勤信息】信息
     * 
     * @param id 【日报管理-出勤信息】ID
     * @return 结果
     */
    @Override
    public int deleteTLpAttendanceDailyById(Long id)
    {
        return tLpAttendanceDailyMapper.deleteTLpAttendanceDailyById(id);
    }
}
