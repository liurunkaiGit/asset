package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.TempCurAssetsPackage;
import com.ruoyi.assetspackage.domain.luckElephant.LuckElephantAddAssetRequest;

import java.util.List;

/**
 * @author guozeqi
 * @create 2020-08-06
 */

public interface LuckElephantRemoteMapper {

    public void batchAddTemp(List<TempCurAssetsPackage> paramList)throws Exception;

    public List<String> selectUpdateList(String importBatchNo)throws Exception;

    public void batchAddAssets(List<TempCurAssetsPackage> paramList)throws Exception;

}
