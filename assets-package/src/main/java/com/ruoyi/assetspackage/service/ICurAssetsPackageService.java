package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.assetspackage.domain.FreeImport;
import com.ruoyi.assetspackage.domain.ImportDataMapping;
import com.ruoyi.assetspackage.domain.TempCurAssetsPackage;
import com.ruoyi.assetspackage.util.Response;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.domain.CloseCase;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 客户资产Service接口
 *
 * @author guozeqi
 * @date 2019-12-25
 */
public interface ICurAssetsPackageService {
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

    /**
     * 查询客户资产列表
     *
     * @param curAssetsPackage 客户资产
     * @return 客户资产集合
     */
    public List<CurAssetsPackage> selectCurAssetsPackageByPage(CurAssetsPackage curAssetsPackage);

    public List<CurAssetsPackage> selectCurAssetsPackageList2(List<String> param, CurAssetsPackage curAssetsPackage);

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
     * 批量删除客户资产
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCurAssetsPackageByIds(String ids);

    /**
     * 删除客户资产信息
     *
     * @param id 客户资产ID
     * @return 结果
     */
    public int deleteCurAssetsPackageById(String id);


    /**
     * 根据资产包id查询该包的所有资产
     *
     * @param packageId
     * @return
     */
    public List<CurAssetsPackage> selectCurAssetsByPackageId(String packageId);

    /**
     * 调用自催系统接口
     *
     * @param curAssetsList
     * @return
     */
    AjaxResult callRemote(List<CurAssetsPackage> curAssetsList);

    AjaxResult callRemoteUpdate(List<CurAssetsPackage> curAssetsList);

    /**
     * 结案
     *
     * @param caseList
     */
//    Response closeCase(List<CloseCase> caseList);
    public void closeCase(List<CloseCase> caseList);


    public CurAssetsPackage selectCurAssetsPackageByCaseNo(Map<String,String> param);

    /**
     * 插入临时表
     */

    public void batchAddTemp(List<TempCurAssetsPackage> paramList)throws Exception;

    /**
     * 批量添加资产
     * @param paramList
     * @throws Exception
     */
    public void batchAddAssets(List<TempCurAssetsPackage> paramList)throws Exception;

    /**
     * 用临时表的数据更新主表
     * @param param
     * @throws Exception
     */
    public void updateCurAssetsPackage2(TempCurAssetsPackage param,List<CurAssetsPackage> remoteList)throws Exception;

    /**
     *根据条件查询临时表
     * @param param
     * @throws Exception
     */
    public List<TempCurAssetsPackage> selectTempCurAssetsPackageList(TempCurAssetsPackage param)throws Exception;


    /**
     *清空临时表
     * @throws Exception
     */
    public void deleteTempCurAssetsPackage(String importBatchNo)throws Exception;

    /**
     * 根据机构案件号和委托方查询主表
     * @param curAssetsPackage
     * @return
     */
    public List<Map<String,Object>> selectCurAssetsPackageList3(CurAssetsPackage curAssetsPackage);


    /**
     * 过滤临时表中的资产
     * @param param
     * @return
     * @throws Exception
     */
    public List<TempCurAssetsPackage> selectTempCurAssetsPackageList2(List<String> param)throws Exception;

    /**
     * 根据id更新异常状态
     * @throws Exception
     */
    public int updateExceptionStatus(String id)throws Exception;

    /**
     * 查询临时表需要更新的数据
     * @param improtBatchNo
     * @return
     * @throws Exception
     */
    public List<TempCurAssetsPackage> selectUpdateList(String improtBatchNo)throws Exception;


    /**
     * 根据id修改状态为更新状态
     * @param id
     * @return
     * @throws Exception
     */
    public int modifyUpdateStatus(String id)throws Exception;

    /**
     * 根据批次号查询需要新增的集合
     * @param improtBatchNo
     * @return
     * @throws Exception
     */
    public List<TempCurAssetsPackage> selectInsertList(String improtBatchNo)throws Exception;

    public int selectUpdateCount(String improtBatchNo)throws Exception;
    public int selectInsertCount(String improtBatchNo)throws Exception;

    public void modifyUpdateStatus2(String improtBatchNo)throws Exception;

    public List<TempCurAssetsPackage> selectUpdateList2(String improtBatchNo)throws Exception;

    public ImportDataMapping voluation(ImportDataMapping bean, String templateId);

    public String handler(HttpServletRequest request, MultipartFile file, String templateId, String orgId, String type) throws Exception;

    public Map<String, String> checkData(HttpServletRequest request,String importBatchNo) throws Exception;

    public Map<String, String> checkUpdateData(HttpServletRequest request,String importBatchNo) throws Exception;

    public CurAssetsPackage selectAsset(String orgCasNo, String importBatchNo) throws Exception;

    public CurAssetsPackage selectHisAsset(String orgCasNo, String importBatchNo) throws Exception;

    public List<Map<String,String>> findNotExistsList(String improtBatchNo)throws Exception;

    public List<TempCurAssetsPackage> findNormalList(String improtBatchNo)throws Exception;

    public void updateHandler(HttpServletRequest request,String importBatchNo) throws Exception;

    public void updateImortHandler(HttpServletRequest request,String importBatchNo) throws Exception;

    public List<Map<String,String>> selectFreeImportByCaseno(String orgCasno)throws Exception;

    public void updateCloseCase(@Param(value="CurAssetsList")List<CurAssetsPackage> CurAssetsList)throws Exception;

    public void updateNoCloseCase(@Param(value="CurAssetsList")List<CurAssetsPackage> CurAssetsList)throws Exception;


    List<Map<String, String>> selectHisFreeImportByCaseno(String caseNo) throws Exception;

    public void clearTempTable();
}
