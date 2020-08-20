package com.ruoyi.utils;

import com.ruoyi.enums.TaskStatusEnum;
import com.ruoyi.enums.TaskTypeEnum;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.service.ITLcTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @Description: 根据不同的规则做不同的任务分配
 * @author: liurunkai
 * @Date: 2020/1/6 9:23
 */
@Component
public class AllocatRuleUtil {

    @Autowired
    private ITLcTaskService taskService;

    /**
     * 利用取模法按照数量平均分配任务
     *
     * @param taskList
     * @param userList
     */
    public static List<TLcTask> averageAllocatTaskByNum(List<TLcTask> taskList, List<SysUser> userList) {
        for (int i = 0; i < taskList.size(); i++) {
            //为了保证案件从第一个人开始分配，需要对i % userList.size()做处理，因为i % userList.size() = 0的情况
            Integer userIndex = i % userList.size();
            SysUser sysUser = userList.get(userIndex);
            taskList.get(i).setOwnerId(sysUser.getUserId());
            taskList.get(i).setOwnerName(sysUser.getUserName());
            taskList.get(i).setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
            taskList.get(i).setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
            taskList.get(i).setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
            taskList.get(i).setRecentlyAllotDate(new Date());
            taskList.get(i).setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
            taskList.get(i).setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
            taskList.get(i).setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
            taskList.get(i).setRecentlyAllotDate(new Date());
        }
        return taskList;

    }

    /**
     * 按照金额进行平均分配任务
     *
     * @param taskList
     * @param userList
     * @return
     */
    public static List<TLcTask> averageAllocatTaskByMoney(List<TLcTask> taskList, List<SysUser> userList) {
        // 先对案件根据金额进行排序
        List<TLcTask> sortTaskByArrearsTotalList = taskList.stream().sorted(Comparator.comparing(TLcTask::getArrearsTotal).reversed()).collect(Collectors.toList());
        int uid = 0;
        for (int i = 1; i <= sortTaskByArrearsTotalList.size(); i++) {
            if ((i + (2 * userList.size() - 1)) % (2 * userList.size()) > userList.size() - 1) {
                uid = (2 * userList.size() - 1) - (i + (2 * userList.size() - 1)) % (2 * userList.size());
                sortTaskByArrearsTotalList.get(i - 1).setOwnerId(userList.get(uid).getUserId());
                sortTaskByArrearsTotalList.get(i - 1).setOwnerName(userList.get(uid).getUserName());
                sortTaskByArrearsTotalList.get(i - 1).setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                sortTaskByArrearsTotalList.get(i - 1).setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                sortTaskByArrearsTotalList.get(i - 1).setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                sortTaskByArrearsTotalList.get(i - 1).setRecentlyAllotDate(new Date());
                sortTaskByArrearsTotalList.get(i - 1).setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                sortTaskByArrearsTotalList.get(i - 1).setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                sortTaskByArrearsTotalList.get(i - 1).setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                sortTaskByArrearsTotalList.get(i - 1).setRecentlyAllotDate(new Date());
            } else {
                uid = (i + (userList.size() * 2 - 1)) % (2 * userList.size());
                sortTaskByArrearsTotalList.get(i - 1).setOwnerId(userList.get(uid).getUserId());
                sortTaskByArrearsTotalList.get(i - 1).setOwnerName(userList.get(uid).getUserName());
                sortTaskByArrearsTotalList.get(i - 1).setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                sortTaskByArrearsTotalList.get(i - 1).setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                sortTaskByArrearsTotalList.get(i - 1).setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                sortTaskByArrearsTotalList.get(i - 1).setRecentlyAllotDate(new Date());
                sortTaskByArrearsTotalList.get(i - 1).setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                sortTaskByArrearsTotalList.get(i - 1).setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                sortTaskByArrearsTotalList.get(i - 1).setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                sortTaskByArrearsTotalList.get(i - 1).setRecentlyAllotDate(new Date());
            }
        }
        return sortTaskByArrearsTotalList;
    }

    /**
     * 按照金额和数量平均分配
     *
     * @param taskList
     * @param userList
     * @return
     */
    public static List<TLcTask> averageAllocatTaskByMoneyNum(List<TLcTask> taskList, List<SysUser> userList) {
        // 先对案件根据金额进行排序：默认升序，.reversed()倒叙
        List<TLcTask> sortTaskByArrearsNumTotalList = taskList.stream().sorted(Comparator.comparing(TLcTask::getArrearsTotal).reversed()).collect(Collectors.toList());
        sortTaskByArrearsNumTotalList = new CopyOnWriteArrayList(sortTaskByArrearsNumTotalList.toArray());
        // 创建一个新的任务集合用来保存分配后的任务
        CopyOnWriteArrayList<TLcTask> newTaskList = new CopyOnWriteArrayList<>();
        Integer taskNum = sortTaskByArrearsNumTotalList.size();
        for (int j = 0; j < taskNum; j++) {
            if (sortTaskByArrearsNumTotalList == null || sortTaskByArrearsNumTotalList.size() == 0) {
                break;
            }
            // 正着给每个坐席分配一个任务
            for (int i = 0; i < userList.size(); i++) {
                if (sortTaskByArrearsNumTotalList == null || sortTaskByArrearsNumTotalList.size() == 0) {
                    break;
                }
                sortTaskByArrearsNumTotalList.get(0).setOwnerId(userList.get(i).getUserId());
                sortTaskByArrearsNumTotalList.get(0).setOwnerName(userList.get(i).getUserName());
                sortTaskByArrearsNumTotalList.get(0).setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                sortTaskByArrearsNumTotalList.get(0).setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                sortTaskByArrearsNumTotalList.get(0).setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                sortTaskByArrearsNumTotalList.get(0).setRecentlyAllotDate(new Date());
                sortTaskByArrearsNumTotalList.get(0).setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                sortTaskByArrearsNumTotalList.get(0).setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                sortTaskByArrearsNumTotalList.get(0).setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                sortTaskByArrearsNumTotalList.get(0).setRecentlyAllotDate(new Date());
                newTaskList.add(sortTaskByArrearsNumTotalList.get(0));
                sortTaskByArrearsNumTotalList.remove(sortTaskByArrearsNumTotalList.get(0));
            }
            // 倒着给每个坐席分配一个任务
            for (int i = 0; i < userList.size(); i++) {
                if (sortTaskByArrearsNumTotalList == null || sortTaskByArrearsNumTotalList.size() == 0) {
                    break;
                }
                TLcTask task = sortTaskByArrearsNumTotalList.get(sortTaskByArrearsNumTotalList.size() - 1);
                task.setOwnerId(userList.get(i).getUserId());
                task.setOwnerName(userList.get(i).getUserName());
                task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                task.setRecentlyAllotDate(new Date());
                task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                task.setRecentlyAllotDate(new Date());
                newTaskList.add(task);
                sortTaskByArrearsNumTotalList.remove(task);
            }
        }
        return newTaskList;
    }


    /**
     * 利用取模法按照数量平均分配任务
     *
     * @param taskList
     * @param userList
     */
    public List<TLcTask> averageAllocatTaskByNumSameDeal(List<TLcTask> taskList, List<SysUser> userList, String orgId) {
        // 根据证件号码进行分组
        Map<String, List<TLcTask>> sameCaseMap = taskList.stream().collect(Collectors.groupingBy(TLcTask::getCertificateNo));
        // 查询是否有共案
        List<TLcTask> newList = new ArrayList<>();
        int sameCaseUserIndex = 0;
        int noSameCaseUserIndex = 0;
        for (Map.Entry<String, List<TLcTask>> sameCase : sameCaseMap.entrySet()) {
            List<TLcTask> sameCaseTaskList = this.taskService.selectSameCaseTaskList(sameCase.getKey(), orgId);
            if (sameCaseTaskList != null && sameCaseTaskList.size() > 1) {
                if (sameCaseTaskList.get(0).getOwnerId() != null) {
                    // 共案且有业务归属人
                    List<TLcTask> finalSameCaseTaskList = sameCaseTaskList;
                    sameCase.getValue().stream().forEach(task -> {
                        task.setOwnerId(finalSameCaseTaskList.get(0).getOwnerId());
                        task.setOwnerName(finalSameCaseTaskList.get(0).getOwnerName());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                        task.setRecentlyAllotDate(new Date());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                        task.setRecentlyAllotDate(new Date());
                    });
                    newList.addAll(sameCase.getValue());
                } else {
                    // 共案但是没有业务归属人
                    int finalSameCaseUserIndex = sameCaseUserIndex;
                    sameCase.getValue().stream().forEach(task -> {
                        task.setOwnerId(userList.get(finalSameCaseUserIndex).getUserId());
                        task.setOwnerName(userList.get(finalSameCaseUserIndex).getUserName());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                        task.setRecentlyAllotDate(new Date());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                        task.setRecentlyAllotDate(new Date());
                    });
                    newList.addAll(sameCase.getValue());
                    if (++sameCaseUserIndex > userList.size() - 1) {
                        sameCaseUserIndex = 0;
                    }
                }
            } else {
                // 不是共案信息
                int finalNoSameCaseUserIndex = noSameCaseUserIndex;
                sameCase.getValue().stream().forEach(task -> {
                    task.setOwnerId(userList.get(finalNoSameCaseUserIndex).getUserId());
                    task.setOwnerName(userList.get(finalNoSameCaseUserIndex).getUserName());
                    task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                    task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                    task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                    task.setRecentlyAllotDate(new Date());
                    task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                    task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                    task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                    task.setRecentlyAllotDate(new Date());
                });
                newList.addAll(sameCase.getValue());
                if (++noSameCaseUserIndex > userList.size() - 1) {
                    noSameCaseUserIndex = 0;
                }
            }
        }
        return newList;
    }

    /**
     * 按照金额进行平均分配任务
     *
     * @param taskList
     * @param userList
     * @return
     */
    public List<TLcTask> averageAllocatTaskByMoneySameDeal(List<TLcTask> taskList, List<SysUser> userList, String orgId) {
        // 根据证件号码进行分组
        Map<String, List<TLcTask>> sameCaseMap = taskList.stream().collect(Collectors.groupingBy(TLcTask::getCertificateNo));
        // 查询是否有共案
        List<TLcTask> newList = new ArrayList<>();
        int sameCaseUserIndex = 0;
        List<TLcTask> noSameCaseTaskList = new ArrayList<>();
        for (Map.Entry<String, List<TLcTask>> sameCase : sameCaseMap.entrySet()) {
            List<TLcTask> sameCaseTaskList = this.taskService.selectSameCaseTaskList(sameCase.getKey(), orgId);
            if (sameCaseTaskList != null && sameCaseTaskList.size() > 1) {
                if (sameCaseTaskList.get(0).getOwnerId() != null) {
                    // 共案且有业务归属人
                    List<TLcTask> finalSameCaseTaskList = sameCaseTaskList;
                    sameCase.getValue().stream().forEach(task -> {
                        task.setOwnerId(finalSameCaseTaskList.get(0).getOwnerId());
                        task.setOwnerName(finalSameCaseTaskList.get(0).getOwnerName());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                        task.setRecentlyAllotDate(new Date());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                        task.setRecentlyAllotDate(new Date());
                    });
                    newList.addAll(sameCase.getValue());
                } else {
                    // 共案但是没有业务归属人
                    int finalSameCaseUserIndex = sameCaseUserIndex;
                    sameCase.getValue().stream().forEach(task -> {
                        task.setOwnerId(userList.get(finalSameCaseUserIndex).getUserId());
                        task.setOwnerName(userList.get(finalSameCaseUserIndex).getUserName());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                        task.setRecentlyAllotDate(new Date());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                        task.setRecentlyAllotDate(new Date());
                    });
                    newList.addAll(sameCase.getValue());
                    if (++sameCaseUserIndex > userList.size() - 1) {
                        sameCaseUserIndex = 0;
                    }
                }
            } else {
                // 不是共案信息
                noSameCaseTaskList.addAll(sameCaseTaskList);
            }
        }
        // 不是共案信息按金额平均分
        noSameCaseTaskList = averageAllocatTaskByMoney(noSameCaseTaskList, userList);
        newList.addAll(noSameCaseTaskList);
        return newList;
    }

    /**
     * 按照金额和数量平均分配
     *
     * @param taskList
     * @param userList
     * @return
     */
    public List<TLcTask> averageAllocatTaskByMoneyNumSameDeal(List<TLcTask> taskList, List<SysUser> userList, String orgId) {
        // 根据证件号码进行分组
        Map<String, List<TLcTask>> sameCaseMap = taskList.stream().collect(Collectors.groupingBy(TLcTask::getCertificateNo));
        // 查询是否有共案
        List<TLcTask> newList = new ArrayList<>();
        int sameCaseUserIndex = 0;
        List<TLcTask> noSameCaseTaskList = new ArrayList<>();
        for (Map.Entry<String, List<TLcTask>> sameCase : sameCaseMap.entrySet()) {
            List<TLcTask> sameCaseTaskList = this.taskService.selectSameCaseTaskList(sameCase.getKey(), orgId);
            if (sameCaseTaskList != null && sameCaseTaskList.size() > 1) {
                if (sameCaseTaskList.get(0).getOwnerId() != null) {
                    // 共案且有业务归属人
                    List<TLcTask> finalSameCaseTaskList = sameCaseTaskList;
                    sameCase.getValue().stream().forEach(task -> {
                        task.setOwnerId(finalSameCaseTaskList.get(0).getOwnerId());
                        task.setOwnerName(finalSameCaseTaskList.get(0).getOwnerName());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                        task.setRecentlyAllotDate(new Date());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                        task.setRecentlyAllotDate(new Date());
                    });
                    newList.addAll(sameCase.getValue());
                } else {
                    // 共案但是没有业务归属人
//                    haveSameCaseTaskList.addAll(sameCaseTaskList);
                    int finalSameCaseUserIndex = sameCaseUserIndex;
                    sameCase.getValue().stream().forEach(task -> {
                        task.setOwnerId(userList.get(finalSameCaseUserIndex).getUserId());
                        task.setOwnerName(userList.get(finalSameCaseUserIndex).getUserName());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                        task.setRecentlyAllotDate(new Date());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                        task.setRecentlyAllotDate(new Date());
                    });
                    newList.addAll(sameCase.getValue());
                    if (++sameCaseUserIndex > userList.size() - 1) {
                        sameCaseUserIndex = 0;
                    }
                }
            } else {
                // 不是共案信息
                noSameCaseTaskList.addAll(sameCaseTaskList);
            }
        }
        // 不是共案信息按金额及数量平均分
        noSameCaseTaskList = averageAllocatTaskByMoneyNum(noSameCaseTaskList, userList);
        newList.addAll(noSameCaseTaskList);
        return newList;
    }
}
