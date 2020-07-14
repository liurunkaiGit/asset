package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.assetspackage.domain.TLcImportFlowForXy;
import com.ruoyi.assetspackage.domain.TLcUrge;
import com.ruoyi.assetspackage.domain.TempCurAssetsPackage;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 客户资产Service接口
 *
 * @author guozeqi
 * @date 2020-07-13
 */
public interface IAssetsImportFromXYService {


    public void handler(HttpServletRequest request, MultipartFile file, String templateId, String orgId, String importBatchNo) throws Exception;

    public Map<String, String> checkData(HttpServletRequest request, String importBatchNo) throws Exception;

    /**
     * 批量添加资产
     * @param paramList
     * @throws Exception
     */
    public void batchAddAssets(List<TempCurAssetsPackage> paramList)throws Exception;

    public int deleteFlowXyByBatchNo(String importBatchNo);

    public void batchUpdateIsCompare(@Param(value="CurAssetsList")List<CurAssetsPackage> CurAssetsList);

    public List<TLcImportFlowForXy> selectFlowList(TLcImportFlowForXy param)throws Exception;

    public List<Map<String,Object>> selectFileList(String importBatchNo)throws Exception;

    public List<TLcUrge> selectUrgeList(TLcUrge TLcUrge);







}
