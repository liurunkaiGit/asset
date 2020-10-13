package com.ruoyi.shareproject.information.service;

import com.ruoyi.shareproject.information.domain.TLpInformationCenter;

import java.util.List;

/**
 * @description: 共享信息管理-基础信息管理-中心信息service层
 *
 * @author 麻超
 *
 * @date 2020-10-12
 */
public interface CenterInformationService {

    List<TLpInformationCenter> selectCenterInformationList(TLpInformationCenter informationCenter);

    int insertCenterInformation(TLpInformationCenter informationCenter);

    int checkCenterNameUnique(String centerName,Long id);

    TLpInformationCenter selectCenterInformationById(Long id);

    int updateInformationCenter(TLpInformationCenter informationCenter);

    List<TLpInformationCenter> selectCenterInformationByIds(String centerIds);

    int deleteCenterInformation(String ids);
}
