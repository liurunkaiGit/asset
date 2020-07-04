package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.CollectionRecordImportTemp;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;
import java.util.Map;

/**
 * 异步更新案件表 任务表 Service接口
 *
 * @author guozeqi
 * @date 2020-05-09
 */
public interface IcollectionRecordImprotAsyncService {


    /**
     * 异步修改案件表、任务表
     */

    public void modifyTableData(String importBatchNo) throws Exception;




}
