package com.ruoyi.shareproject.information.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.shareproject.information.domain.TLpInformationCenter;
import com.ruoyi.shareproject.information.mapper.CenterInformationMapper;
import com.ruoyi.shareproject.information.service.CenterInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 共享信息管理-基础信息管理-中心信息service层
 *
 * @author 麻超
 *
 * @date 2020-10-12
 */
@Service
@Transactional
public class CenterInformationServiceImpl implements CenterInformationService {

    @Autowired
    private CenterInformationMapper centerInformationMapper;

    @Override
    public List<TLpInformationCenter> selectCenterInformationList(TLpInformationCenter informationCenter) {
        return centerInformationMapper.selectCenterInformationList(informationCenter);
    }

    @Override
    public int insertCenterInformation(TLpInformationCenter informationCenter) {
        if(checkCenterNameUnique(informationCenter.getCenterName(),informationCenter.getId()) > 0){
            throw new RuntimeException("中心名称已经存在!");
        }
        informationCenter.setCreateBy(ShiroUtils.getUserId().toString());
        informationCenter.setUpdateBy(ShiroUtils.getUserId().toString());
        return centerInformationMapper.insertCenterInformation(informationCenter);
    }

    /**
     * 验证中心名称是否重复
     * @param informationCenter
     */
    @Override
    public int checkCenterNameUnique(String centerName,Long id) {
        return centerInformationMapper.selectCenterNameCount(centerName,id);
    }

    @Override
    public TLpInformationCenter selectCenterInformationById(Long id) {
        return centerInformationMapper.selectCenterInformationById(id);
    }

    @Override
    public int updateInformationCenter(TLpInformationCenter informationCenter) {
        if(checkCenterNameUnique(informationCenter.getCenterName(),informationCenter.getId()) > 0){
            throw new RuntimeException("中心名称已经存在!");
        }
        informationCenter.setUpdateBy(ShiroUtils.getUserId().toString());
        return centerInformationMapper.updateInformationCenter(informationCenter);
    }

    @Override
    public List<TLpInformationCenter> selectCenterInformationByIds(String centerIds) {

        return centerInformationMapper.selectCenterInformationByIds(centerIds.split(","));
    }

    @Override
    public int deleteCenterInformation(String ids) {
        return centerInformationMapper.deleteCenterInformation(Convert.toStrArray(ids));
    }
}
