package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.AssetsConnectPackage;
import java.util.List;

/**
 * 资产包与资产关系Service接口
 * 
 * @author guozeqi
 * @date 2020-01-08
 */
public interface IAssetsConnectPackageService 
{
    /**
     * 查询资产包与资产关系
     * 
     * @param id 资产包与资产关系ID
     * @return 资产包与资产关系
     */
    public AssetsConnectPackage selectAssetsConnectPackageById(String id);

    /**
     * 查询资产包与资产关系列表
     * 
     * @param assetsConnectPackage 资产包与资产关系
     * @return 资产包与资产关系集合
     */
    public List<AssetsConnectPackage> selectAssetsConnectPackageList(AssetsConnectPackage assetsConnectPackage);

    /**
     * 新增资产包与资产关系
     * 
     * @param assetsConnectPackage 资产包与资产关系
     * @return 结果
     */
    public int insertAssetsConnectPackage(AssetsConnectPackage assetsConnectPackage);

    /**
     * 修改资产包与资产关系
     * 
     * @param assetsConnectPackage 资产包与资产关系
     * @return 结果
     */
    public int updateAssetsConnectPackage(AssetsConnectPackage assetsConnectPackage);

    /**
     * 批量删除资产包与资产关系
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetsConnectPackageByIds(String ids);

    /**
     * 删除资产包与资产关系信息
     * 
     * @param id 资产包与资产关系ID
     * @return 结果
     */
    public int deleteAssetsConnectPackageById(String id);


    /**
     * 删除资产包与资产关系
     * @param packageId
     * @return
     */
    public int deleteAssetsConnectPackageByPackageId(String packageId);

}
