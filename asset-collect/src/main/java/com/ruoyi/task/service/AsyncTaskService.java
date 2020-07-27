package com.ruoyi.task.service;

import com.ruoyi.task.domain.TLcCallRecord;

/**
 * @Description: 异步执行任务逻辑
 * @author: liurunkai
 * @Date: 2020/7/27 10:44
 */
public interface AsyncTaskService {

    void updateTask(TLcCallRecord tLcCallRecord, String importBatchNo);
}
