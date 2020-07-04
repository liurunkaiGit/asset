package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.AssetsInfoPackage;
import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.assetspackage.domain.CurAssetsRepaymentPackage;

import java.util.List;
import java.util.Map;

/**
 * 客户资产Service接口
 * 
 * @author guozeqi
 * @date 2020-02-07
 */
public interface IAssetsInfoService
{

    /**
     * 查询客户资产
     *
     * @param orgCasno
     * @return 客户资产
     */
    public AssetsInfoPackage selectCurAssetsPackageByOrgCasno(String orgCasno);


    /**
     * 查询客户资产列表
     *
     * @param assetsInfoPackage 客户资产
     * @return 客户资产集合
     */
    public List<AssetsInfoPackage> selectCurAssetsPackageList(AssetsInfoPackage assetsInfoPackage);


    /**
     * 查询资产还款
     *
     * @param orgCasno
     * @return 资产还款
     */
    public List<CurAssetsRepaymentPackage> selectCurAssetsRepaymentPackageByOrgCasNo(String orgCasno);



}
