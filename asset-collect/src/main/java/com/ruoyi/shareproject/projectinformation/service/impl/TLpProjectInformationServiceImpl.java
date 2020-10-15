package com.ruoyi.shareproject.projectinformation.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.projectinformation.domain.TLpProjectInformation;
import com.ruoyi.shareproject.projectinformation.mapper.TLpProjectInformationMapper;
import com.ruoyi.shareproject.projectinformation.service.ITLpProjectInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 【项目信息管理】Service业务层处理
 *
 * @author gaohg
 * @date 2020-10-14
 */
@Service
public class TLpProjectInformationServiceImpl implements ITLpProjectInformationService {
    @Autowired
    private TLpProjectInformationMapper tLpProjectInformationMapper;

    /**
     * 查询【项目信息管理】
     *
     * @param id 【项目信息管理】ID
     * @return 【项目信息管理】
     */
    @Override
    public TLpProjectInformation selectTLpProjectInformationById(Long id)
    {
        return tLpProjectInformationMapper.selectTLpProjectInformationById(id);
    }

    /**
     * 查询【项目信息管理】列表
     *
     * @param tLpProjectInformation 【项目信息管理】
     * @return 【项目信息管理】
     */
    @Override
    public List<TLpProjectInformation> selectTLpProjectInformationList(TLpProjectInformation tLpProjectInformation)
    {
        return tLpProjectInformationMapper.selectTLpProjectInformationList(tLpProjectInformation);
    }

    /**
     * 新增【项目信息管理】
     *
     * @param tLpProjectInformation 【项目信息管理】
     * @return 结果
     */
    @Override
    public int insertTLpProjectInformation(TLpProjectInformation tLpProjectInformation)
    {
        return tLpProjectInformationMapper.insertTLpProjectInformation(tLpProjectInformation);
    }

    /**
     * 修改【项目信息管理】
     *
     * @param tLpProjectInformation 【项目信息管理】
     * @return 结果
     */
    @Override
    public int updateTLpProjectInformation(TLpProjectInformation tLpProjectInformation)
    {
        return tLpProjectInformationMapper.updateTLpProjectInformation(tLpProjectInformation);
    }

    /**
     * 删除【项目信息管理】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLpProjectInformationByIds(String ids)
    {
        return tLpProjectInformationMapper.deleteTLpProjectInformationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【项目信息管理】信息
     *
     * @param id 【项目信息管理】ID
     * @return 结果
     */
    @Override
    public int deleteTLpProjectInformationById(Long id)
    {
        return tLpProjectInformationMapper.deleteTLpProjectInformationById(id);
    }
}
