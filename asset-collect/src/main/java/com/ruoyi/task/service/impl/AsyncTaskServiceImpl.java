package com.ruoyi.task.service.impl;

import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.mapper.TLcTaskMapper;
import com.ruoyi.task.service.AsyncTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/7/27 10:44
 */
@Slf4j
@Service
public class AsyncTaskServiceImpl implements AsyncTaskService {

    @Autowired
    private TLcTaskMapper tLcTaskMapper;

    /**
     * 更新任务表数据--新增或者修改电催记录时
     *
     * @param tLcCallRecord
     * @param importBatchNo
     */
    @Async
    @Override
    public void updateTask(TLcCallRecord tLcCallRecord, String importBatchNo) {
        TLcTask tLcTask = TLcTask.builder()
                .caseNo(tLcCallRecord.getCaseNo())
                .importBatchNo(importBatchNo)
                .orgId(tLcCallRecord.getOrgId())
                .callSign(tLcCallRecord.getCallSign())
                .callSignValue(tLcCallRecord.getCallResult())
                .recentlyFollowUpDate(new Date())
                .build();
        this.tLcTaskMapper.updateTaskByCaseNoAndImportBatchNoAndOrgId(tLcTask);
    }
}
