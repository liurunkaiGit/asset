package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.assetspackage.domain.FreeImport;
import com.ruoyi.assetspackage.domain.TempCurAssetsPackage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 客户资产Mapper接口
 * 
 * @author guozeqi
 * @date 2019-12-25
 */
public interface CurAssetsPackageMapper
{
    /**
     * 查询客户资产
     * 
     * @param id 客户资产ID
     * @return 客户资产
     */
    public CurAssetsPackage selectCurAssetsPackageById(String id);

    /**
     * 查询客户资产列表
     * 
     * @param curAssetsPackage 客户资产
     * @return 客户资产集合
     */
    public List<CurAssetsPackage> selectCurAssetsPackageList(CurAssetsPackage curAssetsPackage);

    public List<CurAssetsPackage> selectHisCurAssetsPackageList(CurAssetsPackage curAssetsPackage);

    /**
     * 查询客户资产列表
     *
     * @param curAssetsPackage 客户资产
     * @return 客户资产集合
     */
    public List<CurAssetsPackage> selectCurAssetsPackageByPage(CurAssetsPackage curAssetsPackage);

    public List<CurAssetsPackage> selectCurAssetsPackageList2(@Param("param") List<String> param, @Param("curAssetsPackage") CurAssetsPackage curAssetsPackage);


    /**
     * 新增客户资产
     * 
     * @param curAssetsPackage 客户资产
     * @return 结果
     */
    public int insertCurAssetsPackage(CurAssetsPackage curAssetsPackage);

    /**
     * 修改客户资产
     * 
     * @param curAssetsPackage 客户资产
     * @return 结果
     */
    public int updateCurAssetsPackage(CurAssetsPackage curAssetsPackage);

    /**
     * 删除客户资产
     * 
     * @param id 客户资产ID
     * @return 结果
     */
    public int deleteCurAssetsPackageById(String id);

    /**
     * 批量删除客户资产
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCurAssetsPackageByIds(String[] ids);


    /**
     * 判断重复记录查询
     * @param params
     * @return
     */
 /*   public String findMoreByOrg(Map params);

    public String findMoreByHt(Map params);
*/

    /**
     * 根据资产包id查询该包的所有资产
     * @param
     * @return
     */
    /*public List<CurAssetsPackage> selectCurAssetsByPackageId(String packageId);*/

   /* CurAssetsPackage selectCurAssetsPackageByCaseNo(@Param("caseNo") String caseNo);*/

    public CurAssetsPackage selectCurAssetsPackageByCaseNo( Map<String,String> param);

    public void batchAddTemp(List<TempCurAssetsPackage> paramList)throws Exception;

    public void batchAddAssets(List<TempCurAssetsPackage> paramList)throws Exception;

    public void updateCurAssetsPackage2(TempCurAssetsPackage param)throws Exception;

    public List<TempCurAssetsPackage> selectTempCurAssetsPackageList(TempCurAssetsPackage param)throws Exception;

    public void deleteTempCurAssetsPackage(String importBatchNo)throws Exception;

    public List<Map<String,Object>> selectCurAssetsPackageList3(CurAssetsPackage curAssetsPackage);

    public List<TempCurAssetsPackage> selectTempCurAssetsPackageList2(List<String> param)throws Exception;

    public int updateExceptionStatus(String id)throws Exception;

    public List<TempCurAssetsPackage> selectUpdateList(String improtBatchNo)throws Exception;

    public int modifyUpdateStatus(String id)throws Exception;

    public List<TempCurAssetsPackage> selectInsertList(String improtBatchNo)throws Exception;

    public int selectUpdateCount(String improtBatchNo)throws Exception;
    public int selectInsertCount(String improtBatchNo)throws Exception;

    public void modifyUpdateStatus2(String improtBatchNo)throws Exception;

    public List<TempCurAssetsPackage> selectUpdateList2(String improtBatchNo)throws Exception;

    public void updateNotExists(String improtBatchNo)throws Exception;

    public int findNotExistsCount(String improtBatchNo)throws Exception;

    public List<Map<String,String>> findNotExistsList(String improtBatchNo)throws Exception;

    public int findNormalCount(String improtBatchNo)throws Exception;

    public List<TempCurAssetsPackage> findNormalList(String improtBatchNo)throws Exception;

    public void updateCurAssetsPackage3(@Param(value="TempCurAssetsList")List<TempCurAssetsPackage> TempCurAssetsList)throws Exception;

    public String selectBatchNo(TempCurAssetsPackage param);

    public void insertFreeImport(@Param(value="FreeImportList")List<FreeImport> FreeImportList)throws Exception;

    public List<Map<String,String>> selectFreeImportByCaseno(String orgCasno)throws Exception;

    public void updateCloseCase(@Param(value="CurAssetsList")List<CurAssetsPackage> CurAssetsList)throws Exception;

    public void updateCloseCase2(@Param(value="CurAssetsList")List<CurAssetsPackage> CurAssetsList)throws Exception;


    public void updateNoCloseCase(@Param(value="CurAssetsList")List<CurAssetsPackage> CurAssetsList)throws Exception;


    List<Map<String, String>> selectHisFreeImportByCaseno(String orgCasno);

    public void clearTempTable();

}
