package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * @author guozeqi
 * @date 2020-07-13
 */
public interface AssetsImportFromXYMapper
{
    public int insetFile(Map<String,Object> param) throws Exception;

    public int insetflowForXy(TLcImportFlowForXy param) throws Exception;

    public int deleteFlowXyByBatchNo(String importBatchNo);

   /* public int updateflowForXy(TLcImportFlowForXy param) throws Exception;*/

    public String selectMaxBatchNo(Map<String,Object> param)throws Exception;

    public Long selectUpNotCompareTotal(Map<String,Object> param)throws Exception;

    public List<CurAssetsPackage> findUpNotCompareList(Map<String,Object> param)throws Exception;

   /* public void modifyUpNotCompareList(Map<String,Object> param)throws Exception;*/

  /*  public void batchUpdateIsCompare(@Param(value="CurAssetsList")List<CurAssetsPackage> CurAssetsList);*/

    public void batchAddAssets(List<TempCurAssetsPackage> paramList)throws Exception;

    public List<CurAssetsPackage> findPreSettleList(String importBatchNo)throws Exception;
    public List<CurAssetsPackage> findUrgeList(String importBatchNo)throws Exception;
    public List<CurAssetsPackage> findPartRepaymentList(String importBatchNo)throws Exception;

    public void batchUpdateCloseCase(@Param(value="CurAssetsList")List<CurAssetsPackage> CurAssetsList)throws Exception;

    public Map<String,Object> findOwner(CurAssetsPackage param)throws Exception;

    public void batchInsertTlcUrge(@Param(value="TlcUrgeList")List<TLcUrge> TlcUrgeList)throws Exception;

    public List<TLcImportFlowForXy> selectFlowList(TLcImportFlowForXy param)throws Exception;

    public List<Map<String,Object>> selectFileList(String importBatchNo)throws Exception;

    public List<TLcUrge> selectUrgeList(TLcUrge TLcUrge);

}
