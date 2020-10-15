package com.ruoyi.shareproject.projectinformation.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.projectinformation.domain.TLpProjectInformation;
import com.ruoyi.shareproject.projectinformation.mapper.TLpProjectInformationMapper;
import com.ruoyi.shareproject.projectinformation.service.ITLpProjectInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TLpProjectInformationServiceImpl implements ITLpProjectInformationService {
    @Autowired
    private TLpProjectInformationMapper tLpProjectInformationMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TLpProjectInformation selectTLpProjectInformationById(Long id)
    {
        return tLpProjectInformationMapper.selectTLpProjectInformationById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLpProjectInformation 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TLpProjectInformation> selectTLpProjectInformationList(TLpProjectInformation tLpProjectInformation)
    {
        return tLpProjectInformationMapper.selectTLpProjectInformationList(tLpProjectInformation);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param tLpProjectInformation 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTLpProjectInformation(TLpProjectInformation tLpProjectInformation)
    {
        return tLpProjectInformationMapper.insertTLpProjectInformation(tLpProjectInformation);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param tLpProjectInformation 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTLpProjectInformation(TLpProjectInformation tLpProjectInformation)
    {
        return tLpProjectInformationMapper.updateTLpProjectInformation(tLpProjectInformation);
    }

    /**
     * 删除【请填写功能名称】对象
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
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTLpProjectInformationById(Long id)
    {
        return tLpProjectInformationMapper.deleteTLpProjectInformationById(id);
    }
}
