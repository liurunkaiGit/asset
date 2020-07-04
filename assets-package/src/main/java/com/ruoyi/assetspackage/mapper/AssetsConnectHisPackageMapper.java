package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.AssetsConnectHisPackage;
import java.util.List;

/**
 * 资产包与资产关系历史Mapper接口
 * 
 * @author guozeqi
 * @date 2020-01-08
 */
public interface AssetsConnectHisPackageMapper 
{
    /**
     * 查询资产包与资产关系历史
     * 
     * @param id 资产包与资产关系历史ID
     * @return 资产包与资产关系历史
     */
    public AssetsConnectHisPackage selectAssetsConnectHisPackageById(String id);

    /**
     * 查询资产包与资产关系历史列表
     * 
     * @param assetsConnectHisPackage 资产包与资产关系历史
     * @return 资产包与资产关系历史集合
     */
    public List<AssetsConnectHisPackage> selectAssetsConnectHisPackageList(AssetsConnectHisPackage assetsConnectHisPackage);

    /**
     * 新增资产包与资产关系历史
     * 
     * @param assetsConnectHisPackage 资产包与资产关系历史
     * @return 结果
     */
    public int insertAssetsConnectHisPackage(AssetsConnectHisPackage assetsConnectHisPackage);

    /**
     * 修改资产包与资产关系历史
     * 
     * @param assetsConnectHisPackage 资产包与资产关系历史
     * @return 结果
     */
    public int updateAssetsConnectHisPackage(AssetsConnectHisPackage assetsConnectHisPackage);

    /**
     * 删除资产包与资产关系历史
     * 
     * @param id 资产包与资产关系历史ID
     * @return 结果
     */
    public int deleteAssetsConnectHisPackageById(String id);

    /**
     * 批量删除资产包与资产关系历史
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetsConnectHisPackageByIds(String[] ids);
}
