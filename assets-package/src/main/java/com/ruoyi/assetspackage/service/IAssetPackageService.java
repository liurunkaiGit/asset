package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.AssetPackage;
import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;
import java.util.Map;

/**
 * 资产包Service接口
 *
 * @author guozeqi
 * @date 2020-01-06
 */
public interface IAssetPackageService {
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
     * 批量删除资产包
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetPackageByIds(String ids);

    /**
     * 删除资产包信息
     *
     * @param id 资产包ID
     * @return 结果
     */
    public int deleteAssetPackageById(String id);

    /**
     * 修改资产包已分发并调用接口
     *
     * @param packageId
     * @param orgId
     */
    AjaxResult updateAssetPackage(String packageId, String orgId);

    /**
     * 批量删除
     *
     * @param packageIds
     */
    void batchDel(String packageIds);

    /**
     * 拆包
     *
     * @param packageIds
     */
    void dismantlePack(String packageIds);

    /**
     * 全部打包
     *
     * @param curAssetsPackage
     * @param packageId
     */
    void assembleAll(CurAssetsPackage curAssetsPackage, String packageId);

    void distribution(String packageId, String assetsId);


    /**
     * 查询客户资产集合
     * @param curAssetsPackage
     * @return
     */
    public List<CurAssetsPackage> selectCurAssetsList(CurAssetsPackage curAssetsPackage);

    /**
     * 更新资产为未打包状态
     * @param map
     * @return
     */
    public int updateCurassets2(Map<String,String> map);



}
