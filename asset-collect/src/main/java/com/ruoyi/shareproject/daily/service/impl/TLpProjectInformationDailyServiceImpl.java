package com.ruoyi.shareproject.daily.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.daily.domain.TLpProjectInformationDaily;
import com.ruoyi.shareproject.daily.mapper.TLpProjectInformationDailyMapper;
import com.ruoyi.shareproject.daily.service.ITLpProjectInformationDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 【日报管理-项目信息】Service业务层处理
 * 
 * @author gaohg
 * @date 2020-10-26
 */
@Service
public class TLpProjectInformationDailyServiceImpl implements ITLpProjectInformationDailyService
{
    @Autowired
    private TLpProjectInformationDailyMapper tLpProjectInformationDailyMapper;

    /**
     * 查询【日报管理-项目信息】
     * 
     * @param id 【日报管理-项目信息】ID
     * @return 【日报管理-项目信息】
     */
    @Override
    public TLpProjectInformationDaily selectTLpProjectInformationDailyById(Long id)
    {
        return tLpProjectInformationDailyMapper.selectTLpProjectInformationDailyById(id);
    }

    /**
     * 查询【日报管理-项目信息】列表
     * 
     * @param tLpProjectInformationDaily 【日报管理-项目信息】
     * @return 【日报管理-项目信息】
     */
    @Override
    public List<TLpProjectInformationDaily> selectTLpProjectInformationDailyList(TLpProjectInformationDaily tLpProjectInformationDaily)
    {
        return tLpProjectInformationDailyMapper.selectTLpProjectInformationDailyList(tLpProjectInformationDaily);
    }

    /**
     * 新增【日报管理-项目信息】
     * 
     * @param tLpProjectInformationDaily 【日报管理-项目信息】
     * @return 结果
     */
    @Override
    public int insertTLpProjectInformationDaily(TLpProjectInformationDaily tLpProjectInformationDaily)
    {
        tLpProjectInformationDaily.setCreateTime(DateUtils.getNowDate());
        return tLpProjectInformationDailyMapper.insertTLpProjectInformationDaily(tLpProjectInformationDaily);
    }

    /**
     * 修改【日报管理-项目信息】
     * 
     * @param tLpProjectInformationDaily 【日报管理-项目信息】
     * @return 结果
     */
    @Override
    public int updateTLpProjectInformationDaily(TLpProjectInformationDaily tLpProjectInformationDaily)
    {
        tLpProjectInformationDaily.setUpdateTime(DateUtils.getNowDate());
        return tLpProjectInformationDailyMapper.updateTLpProjectInformationDaily(tLpProjectInformationDaily);
    }

    /**
     * 删除【日报管理-项目信息】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLpProjectInformationDailyByIds(String ids)
    {
        return tLpProjectInformationDailyMapper.deleteTLpProjectInformationDailyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【日报管理-项目信息】信息
     * 
     * @param id 【日报管理-项目信息】ID
     * @return 结果
     */
    @Override
    public int deleteTLpProjectInformationDailyById(Long id)
    {
        return tLpProjectInformationDailyMapper.deleteTLpProjectInformationDailyById(id);
    }
}
