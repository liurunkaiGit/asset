package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.TempCurAssetsPackage;
import com.ruoyi.assetspackage.domain.luckElephant.*;

import java.util.List;

/**
 * @author guozeqi
 * @create 2020-08-06
 */

public interface ILuckElephantRemoteService {

    public LuckElephantAddAssetResponse batchAddAssets(LuckElephantAddAssetRequest param,String curDate)throws Exception;

    public LuckElephantUpdateAssetResponse batchUpdateAssets(LuckElephantUpdateAssetRequest param,String curDate)throws Exception;

    public LuckElephantRepaymentAssetResponse batchRepaymentAssets(LuckElephantRepaymentAssetRequest param,String curDate)throws Exception;

    public void deleteTempTable(String importBatchNo);

    public void deleteRepaymentTempTable(String orgId);

}
