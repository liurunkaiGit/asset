package com.ruoyi.assetspackage.service.impl;

import com.ruoyi.assetspackage.domain.CollectionRecordImportTemp;
import com.ruoyi.assetspackage.mapper.IcollectionRecordImprotMapper;
import com.ruoyi.assetspackage.service.IcollectionRecordImprotAsyncService;
import com.ruoyi.assetspackage.service.IcollectionRecordImprotService;
import com.ruoyi.common.core.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 异步更新案件表 任务表 Service
 *
 * @author guozeqi
 * @date 2020-05-11
 */
@Slf4j
@Service
public class CollectionRecordImprotServiceAsyncImpl implements IcollectionRecordImprotAsyncService {

    @Autowired
    private IcollectionRecordImprotMapper collectionRecordImprotMapper;


    @Async
    public void modifyTableData(String importBatchNo) throws Exception{
        log.info("=========执行异步更新案件表、任务表开始========"+importBatchNo);
//        collectionRecordImprotMapper.updateDuncaseTable(importBatchNo);
        collectionRecordImprotMapper.updateTaskTable(importBatchNo);
        //删除临时表
        collectionRecordImprotMapper.deleteTempTable(importBatchNo);
        log.info("=========执行异步更新案件表、任务表结束========"+importBatchNo);
    }

}
