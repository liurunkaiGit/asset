package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.TempCurAssetsPackage;
import com.ruoyi.assetspackage.domain.luckElephant.LuckElephantAddAssetRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author guozeqi
 * @create 2020-08-06
 */

public interface LuckElephantRemoteMapper {

    public void batchAddTemp(List<TempCurAssetsPackage> paramList)throws Exception;

    public List<String> selectUpdateList(String importBatchNo)throws Exception;

    public void batchAddAssets(List<TempCurAssetsPackage> paramList)throws Exception;

    public void deleteTempTable(String importBatchNo);
    public void deleteRepaymentTempTable();

    public List<String> selectNotExists(String importBatchNo)throws Exception;

    public void batchUpdate(@Param(value="TempCurAssetsList")List<TempCurAssetsPackage> TempCurAssetsList)throws Exception;

    public List<String> selectRepaymentNotExists(String importBatchNo)throws Exception;

}
