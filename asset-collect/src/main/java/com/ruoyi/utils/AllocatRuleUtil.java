package com.ruoyi.utils;

import com.ruoyi.duncase.domain.TLcDuncase;
import com.ruoyi.enums.TaskStatusEnum;
import com.ruoyi.enums.TaskTypeEnum;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.task.domain.TLcTask;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @Description: 根据不同的规则做不同的任务分配
 * @author: liurunkai
 * @Date: 2020/1/6 9:23
 */
public class AllocatRuleUtil {

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
            } else {
                uid = (i + (userList.size() * 2 - 1)) % (2 * userList.size());
                sortTaskByArrearsTotalList.get(i - 1).setOwnerId(userList.get(uid).getUserId());
                sortTaskByArrearsTotalList.get(i - 1).setOwnerName(userList.get(uid).getUserName());
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
                newTaskList.add(task);
                sortTaskByArrearsNumTotalList.remove(task);
            }
        }
        return newTaskList;
    }
}
