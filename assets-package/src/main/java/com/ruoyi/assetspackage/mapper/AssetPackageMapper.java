package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.AssetPackage;
import com.ruoyi.assetspackage.domain.CurAssetsPackage;

import java.util.List;
import java.util.Map;

/**
 * 资产包Mapper接口
 * 
 * @author guozeqi
 * @date 2020-01-06
 */
public interface AssetPackageMapper 
{
    /**
     * 查询资产包
     * 
     * @param id 资产包ID
     * @return 资产包
     */
    public AssetPackage selectAssetPackageById(String id);

    /**
     * 查询资产包列表
     * 
     * @param assetPackage 资产包
     * @return 资产包集合
     */
    public List<AssetPackage> selectAssetPackageList(AssetPackage assetPackage);

    /**
     * 新增资产包
     * 
     * @param assetPackage 资产包
     * @return 结果
     */
    public int insertAssetPackage(AssetPackage assetPackage);

    /**
     * 修改资产包
     * 
     * @param assetPackage 资产包
     * @return 结果
     */
    public int updateAssetPackage(AssetPackage assetPackage);

    /**
     * 删除资产包
     * 
     * @param id 资产包ID
     * @return 结果
     */
    public int deleteAssetPackageById(String id);

    /**
     * 批量删除资产包
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetPackageByIds(String[] ids);


    public List<CurAssetsPackage> selectCurAssetsList(CurAssetsPackage curAssetsPackage);


    /**
     *更新资产为已大包状态
     * @param curAssetsPackage
     * @return
     */
    public int updateCurassets(CurAssetsPackage curAssetsPackage);

    /**
     * 更新资产为未打包状态
     * @param map
     * @return
     */
    public int updateCurassets2(Map<String,String> map);


}
