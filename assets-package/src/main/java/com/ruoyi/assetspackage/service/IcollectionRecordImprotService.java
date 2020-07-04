package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.CollectionRecordImport;
import com.ruoyi.assetspackage.domain.CollectionRecordImportTemp;
import com.ruoyi.assetspackage.domain.CollectionrecordimprotFlow;
import com.ruoyi.assetspackage.domain.TempCurAssetsPackage;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 催收记录Service接口
 *
 * @author guozeqi
 * @date 2020-05-09
 */
public interface IcollectionRecordImprotService {


    /**
     * 插入临时表
     */

    public void batchAddTemp(List<CollectionRecordImportTemp> paramList)throws Exception;

    public void deleteTempTable(String importBatchNo)throws Exception;

    /**
     * 查询临时表
     * @param param
     * @return
     * @throws Exception
     */
    public List<CollectionRecordImportTemp> selectTempCollectionRecordList(CollectionRecordImportTemp param)throws Exception;

    public int selectTempCollectionRecordCount(CollectionRecordImportTemp param)throws Exception;


    /**
     * 根据id更新异常状态
     * @throws Exception
     */
    public int updateExceptionStatus(String id)throws Exception;
    public int updateExceptionStatus2(String id)throws Exception;


    /**
     * 查找临时表中有而案件表中不存在的案件
     * @return
     * @throws Exception
     */
    public List<Map<String,String>> findNotExistsCase(String importBatchNo)throws Exception;

    /**
     * 执行新增操作
     *
     */
    public AjaxResult batchExecute(String importBatchNo)throws Exception;


    /**
     * 查询导入首页展示数据
     * @param param
     * @return
     * @throws Exception
     */
    public List<CollectionrecordimprotFlow> selectTLcCollectionrecordimprotFlowList(CollectionRecordImportTemp param)throws Exception;

    public String handler(HttpServletRequest request, MultipartFile file, String templateId, String orgId, String type) throws Exception;

    public Map<String, String> checkData(HttpServletRequest request,String importBatchNo) throws Exception;



}
