package com.ruoyi.assetspackage.service.impl;

import com.ruoyi.assetspackage.domain.AssetsInfoPackage;
import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.assetspackage.domain.CurAssetsRepaymentPackage;
import com.ruoyi.assetspackage.mapper.AssetsInfoMapper;
import com.ruoyi.assetspackage.mapper.CurAssetsPackageMapper;
import com.ruoyi.assetspackage.service.IAssetsInfoService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 客户资产Service业务层处理
 *
 * @author guozeqi
 * @date 2020-02-07
 */
@Service
public class AssetsInfoServiceImpl implements IAssetsInfoService {
    @Autowired
    private AssetsInfoMapper assetsInfoMapper;


    @Override
    public AssetsInfoPackage selectCurAssetsPackageByOrgCasno(String orgCasno) {
        return assetsInfoMapper.selectCurAssetsPackageByOrgCasno(orgCasno);
    }

    /**
     * 查询客户资产列表
     *
     * @param assetsInfoPackage 客户资产
     * @return 客户资产
     */
    @Override
    public List<AssetsInfoPackage> selectCurAssetsPackageList(AssetsInfoPackage assetsInfoPackage) {
        if (assetsInfoPackage.getEndDate() != null) {
            assetsInfoPackage.setEndDate(DateUtils.getEndOfDay(assetsInfoPackage.getEndDate()));
        }
        return assetsInfoMapper.selectCurAssetsPackageList(assetsInfoPackage);
    }

    @Override
    public List<CurAssetsRepaymentPackage> selectCurAssetsRepaymentPackageByOrgCasNo(String orgCasno) {
        return assetsInfoMapper.selectCurAssetsRepaymentPackageByOrgCasNo(orgCasno);
    }


}
