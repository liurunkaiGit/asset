package com.ruoyi.grayQueue.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.duncase.domain.TLcDuncaseAssign;
import com.ruoyi.duncase.mapper.TLcDuncaseAssignMapper;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.enums.TaskTypeEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.grayQueue.domain.TLcGrayQueue;
import com.ruoyi.grayQueue.mapper.TLcGrayQueueMapper;
import com.ruoyi.grayQueue.service.ITLcGrayQueueService;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.service.ITLcTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 灰色队列Service业务层处理
 *
 * @author liurunkai
 * @date 2020-01-09
 */
@Service
public class TLcGrayQueueServiceImpl implements ITLcGrayQueueService {

    @Autowired
    private ITLcTaskService tLcTaskService;
    @Autowired
    private TLcGrayQueueMapper tLcGrayQueueMapper;
    @Autowired
    private TLcDuncaseAssignMapper tLcDuncaseAssignMapper;

    /**
     * 查询灰色队列
     *
     * @param id 灰色队列ID
     * @return 灰色队列
     */
    @Override
    public TLcGrayQueue selectTLcGrayQueueById(Long id) {
        return tLcGrayQueueMapper.selectTLcGrayQueueById(id);
    }

    /**
     * 查询灰色队列列表
     *
     * @param tLcGrayQueue 灰色队列
     * @return 灰色队列
     */
    @Override
    public List<TLcGrayQueue> selectTLcGrayQueueList(TLcGrayQueue tLcGrayQueue) {
        return tLcGrayQueueMapper.selectTLcGrayQueueList(tLcGrayQueue);
    }

    /**
     * 新增灰色队列
     *
     * @param tLcGrayQueue 灰色队列
     * @return 结果
     */
    @Override
    public int insertTLcGrayQueue(TLcGrayQueue tLcGrayQueue) {
        tLcGrayQueue.setCreateTime(DateUtils.getNowDate());
        return tLcGrayQueueMapper.insertTLcGrayQueue(tLcGrayQueue);
    }

    /**
     * 修改灰色队列
     *
     * @param tLcGrayQueue 灰色队列
     * @return 结果
     */
    @Override
    public int updateTLcGrayQueue(TLcGrayQueue tLcGrayQueue) {
        return tLcGrayQueueMapper.updateTLcGrayQueue(tLcGrayQueue);
    }

    /**
     * 删除灰色队列对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcGrayQueueByIds(String ids) {
        String[] grayQueueIds = Convert.toStrArray(ids);
        List<TLcTask> taskList = Arrays.stream(grayQueueIds)
                .map(id -> {
                    TLcGrayQueue tLcGrayQueue = this.tLcGrayQueueMapper.selectTLcGrayQueueById(Long.valueOf(id));
                    TLcTask tLcTask = this.tLcTaskService.selectTLcTaskById(tLcGrayQueue.getTaskId());
                    tLcTask.setTaskType(TaskTypeEnum.REMOVE_GRAY_QUEUE.getCode());
                    this.tLcTaskService.updateTLcTask(tLcTask);
                    return tLcTask;
                }).collect(Collectors.toList());
        this.tLcTaskService.insertDuncaseAssign(taskList,ShiroUtils.getSysUser());
        return tLcGrayQueueMapper.deleteTLcGrayQueueByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除灰色队列信息
     *
     * @param id 灰色队列ID
     * @return 结果
     */
    @Override
    public int deleteTLcGrayQueueById(Long id) {
        return tLcGrayQueueMapper.deleteTLcGrayQueueById(id);
    }

    @Override
    public int addGrayQueue(TLcGrayQueue tLcGrayQueue, String taskIds) {
        String[] ids = taskIds.split(",");
        List<TLcDuncaseAssign> duncaseAssignList = Arrays.stream(ids)
                .map(taskId -> {
                    TLcTask tLcTask = this.tLcTaskService.selectTLcTaskById(Long.valueOf(taskId));
                    tLcGrayQueue.setCertificateNo(tLcTask.getCertificateNo())
                            .setCustCode(tLcTask.getCustomCode())
                            .setCustName(tLcTask.getCustomName())
                            .setOrgId(tLcTask.getOrgId())
                            .setOrgName(tLcTask.getOrgName())
                            .setOwnerId(tLcTask.getOwnerId())
                            .setOwnerName(tLcTask.getOwnerName())
                            .setTaskId(Long.valueOf(taskId))
                            .setCreateBy(ShiroUtils.getUserId().toString());
                    this.tLcGrayQueueMapper.insertTLcGrayQueue(tLcGrayQueue);
                    tLcTask.setTaskType(TaskTypeEnum.GRAY_QUEUE.getCode());
                    this.tLcTaskService.updateTLcTask(tLcTask);
                    TLcDuncaseAssign tLcDuncaseAssign = TLcDuncaseAssign.builder()
                            .ownerId(tLcTask.getOwnerId())
                            .taskId(taskId)
                            .operationId(ShiroUtils.getUserId())
                            .customName(tLcTask.getCustomName())
                            .collectTeamName(tLcTask.getCollectTeamName())
                            .collectTeamId(tLcTask.getCollectTeamId())
                            .certificateNo(tLcTask.getCertificateNo())
                            .caseNo(tLcTask.getCaseNo())
                            .operationName(ShiroUtils.getSysUser().getUserName())
                            .transferType(TaskTypeEnum.GRAY_QUEUE.getCode())
                            .orgId(tLcTask.getOrgId())
                            .taskStatus(tLcTask.getTaskStatus())
                            .validateStatus(IsNoEnum.IS.getCode())
                            .build();
                    return tLcDuncaseAssign;
                }).collect(Collectors.toList());
        return this.tLcDuncaseAssignMapper.batchInsertDuncaseAssign(duncaseAssignList);
    }
}
