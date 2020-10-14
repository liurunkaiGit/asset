package com.ruoyi.assetspackage.util;

import com.ruoyi.assetspackage.domain.distribution.TLcTaskAsset;
import com.ruoyi.assetspackage.service.distribution.ITLcTaskAssetService;
import com.ruoyi.common.enums.TaskStatusEnum;
import com.ruoyi.common.enums.TaskTypeEnum;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @Description: 根据不同的规则做不同的任务分配
 * @author: liurunkai
 * @Date: 2020/1/6 9:23
 */
@Component
public class AllocatRuleUtilAsset {

    @Autowired
    private ITLcTaskAssetService taskService;

    /**
     * 利用取模法按照数量平均分配任务
     *
     * @param taskList
     * @param userList
     */
    public List<TLcTaskAsset> averageAllocatTaskByNum(List<TLcTaskAsset> taskList, List<SysUser> userList) {
        for (int i = 0; i < taskList.size(); i++) {
            //为了保证案件从第一个人开始分配，需要对i % userList.size()做处理，因为i % userList.size() = 0的情况
            Integer userIndex = i % userList.size();
            SysUser sysUser = userList.get(userIndex);
            taskList.get(i).setOwnerId(sysUser.getUserId());
            taskList.get(i).setOwnerName(sysUser.getUserName());
            taskList.get(i).setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
            taskList.get(i).setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
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
    public List<TLcTaskAsset> averageAllocatTaskByMoney(List<TLcTaskAsset> taskList, List<SysUser> userList) {
        // 先对案件根据金额进行排序
        List<TLcTaskAsset> sortTaskByArrearsTotalList = taskList.stream().sorted(Comparator.comparing(TLcTaskAsset::getArrearsTotal).reversed()).collect(Collectors.toList());
        int uid = 0;
        for (int i = 1; i <= sortTaskByArrearsTotalList.size(); i++) {
            if ((i + (2 * userList.size() - 1)) % (2 * userList.size()) > userList.size() - 1) {
                uid = (2 * userList.size() - 1) - (i + (2 * userList.size() - 1)) % (2 * userList.size());
                sortTaskByArrearsTotalList.get(i - 1).setOwnerId(userList.get(uid).getUserId());
                sortTaskByArrearsTotalList.get(i - 1).setOwnerName(userList.get(uid).getUserName());
            } else {
                uid = (i + (userList.size() * 2 - 1)) % (2 * userList.size());
                sortTaskByArrearsTotalList.get(i - 1).setOwnerId(userList.get(uid).getUserId());
                sortTaskByArrearsTotalList.get(i - 1).setOwnerName(userList.get(uid).getUserName());
            }
            sortTaskByArrearsTotalList.get(i - 1).setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
            sortTaskByArrearsTotalList.get(i - 1).setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
            sortTaskByArrearsTotalList.get(i - 1).setRecentlyAllotDate(new Date());
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
    public List<TLcTaskAsset> averageAllocatTaskByMoneyNum(List<TLcTaskAsset> taskList, List<SysUser> userList) {
        // 先对案件根据金额进行排序：默认升序，.reversed()倒叙
        List<TLcTaskAsset> sortTaskByArrearsNumTotalList = taskList.stream().sorted(Comparator.comparing(TLcTaskAsset::getArrearsTotal).reversed()).collect(Collectors.toList());
        sortTaskByArrearsNumTotalList = new CopyOnWriteArrayList(sortTaskByArrearsNumTotalList.toArray());
        // 创建一个新的任务集合用来保存分配后的任务
        CopyOnWriteArrayList<TLcTaskAsset> newTaskList = new CopyOnWriteArrayList<>();
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
                sortTaskByArrearsNumTotalList.get(0).setRecentlyAllotDate(new Date());
                newTaskList.add(sortTaskByArrearsNumTotalList.get(0));
                sortTaskByArrearsNumTotalList.remove(sortTaskByArrearsNumTotalList.get(0));
            }
            // 倒着给每个坐席分配一个任务
            for (int i = 0; i < userList.size(); i++) {
                if (sortTaskByArrearsNumTotalList == null || sortTaskByArrearsNumTotalList.size() == 0) {
                    break;
                }
                TLcTaskAsset task = sortTaskByArrearsNumTotalList.get(sortTaskByArrearsNumTotalList.size() - 1);
                task.setOwnerId(userList.get(i).getUserId());
                task.setOwnerName(userList.get(i).getUserName());
                task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                task.setRecentlyAllotDate(new Date());
                newTaskList.add(task);
                sortTaskByArrearsNumTotalList.remove(task);
            }
        }
        return newTaskList;
    }


    /**
     * 共案处理-利用取模法按照数量平均分配任务
     *
     * @param taskList
     * @param userList
     */
    public List<TLcTaskAsset> averageAllocatTaskByNumSameDeal(List<TLcTaskAsset> taskList, List<SysUser> userList, String orgId) {
        // 根据证件号码进行分组
        Map<String, List<TLcTaskAsset>> sameCaseMap = taskList.stream().collect(Collectors.groupingBy(TLcTaskAsset::getCertificateNo));
        // 查询是否有共案
        List<TLcTaskAsset> newList = new ArrayList<>();
        int sameCaseUserIndex = 0;
        int noSameCaseUserIndex = 0;
        for (Map.Entry<String, List<TLcTaskAsset>> sameCase : sameCaseMap.entrySet()) {
            List<TLcTaskAsset> sameCaseTaskList = this.taskService.selectSameCaseTaskList(sameCase.getKey(), orgId);
            if (sameCaseTaskList != null && sameCaseTaskList.size() > 1) {
                if (sameCaseTaskList.get(0).getOwnerId() != null) {
                    // 共案且有业务归属人
                    List<TLcTaskAsset> finalSameCaseTaskList = sameCaseTaskList;
                    sameCase.getValue().stream().forEach(task -> {
                        task.setOwnerId(finalSameCaseTaskList.get(0).getOwnerId());
                        task.setOwnerName(finalSameCaseTaskList.get(0).getOwnerName());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
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
     * 共案处理-利用取模法按照数量平均分配任务,优化：减少数据库的查询
     *
     * @param taskList
     * @param userList
     */
    public List<TLcTaskAsset> averageAllocatTaskByNumSameDeal2(List<TLcTaskAsset> taskList, List<SysUser> userList, String orgId) {
        List<TLcTaskAsset> newList = new ArrayList<>();
        int sameCaseUserIndex = 0;
        int noSameCaseUserIndex = 0;
        // 将需要分配的任务集合根据证件号码进行分组
        Map<String, List<TLcTaskAsset>> sameCaseMap = taskList.stream().collect(Collectors.groupingBy(TLcTaskAsset::getCertificateNo));
        // 获取需要分配的任务的证件号码集合
        Set<String> certificateNos = taskList.stream().map(task -> task.getCertificateNo()).collect(Collectors.toSet());
        // 根据证件号码和机构查询所有的任务
        List<TLcTaskAsset> allTaskList = getTaskListByCertificateNosAndOrdId(certificateNos, orgId);
        // 将根据证件号码和机构查询所有的任务根据证件号码进行分组
        Map<String, List<TLcTaskAsset>> allTaskSameCaseMap = allTaskList.stream().collect(Collectors.groupingBy(TLcTaskAsset::getCertificateNo));
        // 遍历从数据库查询的所有任务
        for (Map.Entry<String, List<TLcTaskAsset>> allTaskSameCase : allTaskSameCaseMap.entrySet()) {
            if (allTaskSameCase.getValue() != null && allTaskSameCase.getValue().size() > 1) {
                // 查询共案信息是否有归属人
                TLcTaskAsset tLcTask = allTaskSameCase.getValue().stream().filter(task -> task.getOwnerId() != null).findFirst().orElse(new TLcTaskAsset());
                if (tLcTask != null && tLcTask.getOwnerId() != null) {
                    // 共案且有业务归属人
                    sameCaseMap.get(allTaskSameCase.getKey()).stream().forEach(task -> {
                        task.setOwnerId(tLcTask.getOwnerId());
                        task.setOwnerName(tLcTask.getOwnerName());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setRecentlyAllotDate(new Date());
                    });
                    newList.addAll(sameCaseMap.get(allTaskSameCase.getKey()));
                } else {
                    // 共案但是没有业务归属人
                    int finalSameCaseUserIndex = sameCaseUserIndex;
                    sameCaseMap.get(allTaskSameCase.getKey()).stream().forEach(task -> {
                        task.setOwnerId(userList.get(finalSameCaseUserIndex).getUserId());
                        task.setOwnerName(userList.get(finalSameCaseUserIndex).getUserName());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setRecentlyAllotDate(new Date());
                    });
                    newList.addAll(sameCaseMap.get(allTaskSameCase.getKey()));
                    if (++sameCaseUserIndex > userList.size() - 1) {
                        sameCaseUserIndex = 0;
                    }
                }
            } else {
                // 不是共案信息
                int finalNoSameCaseUserIndex = noSameCaseUserIndex;
                sameCaseMap.get(allTaskSameCase.getKey()).stream().forEach(task -> {
                    task.setOwnerId(userList.get(finalNoSameCaseUserIndex).getUserId());
                    task.setOwnerName(userList.get(finalNoSameCaseUserIndex).getUserName());
                    task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                    task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                    task.setRecentlyAllotDate(new Date());
                });
                newList.addAll(sameCaseMap.get(allTaskSameCase.getKey()));
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
    public List<TLcTaskAsset> averageAllocatTaskByMoneySameDeal(List<TLcTaskAsset> taskList, List<SysUser> userList, String orgId) {
        // 根据证件号码进行分组
        Map<String, List<TLcTaskAsset>> sameCaseMap = taskList.stream().collect(Collectors.groupingBy(TLcTaskAsset::getCertificateNo));
        // 查询是否有共案
        List<TLcTaskAsset> newList = new ArrayList<>();
        int sameCaseUserIndex = 0;
        List<TLcTaskAsset> noSameCaseTaskList = new ArrayList<>();
        for (Map.Entry<String, List<TLcTaskAsset>> sameCase : sameCaseMap.entrySet()) {
            List<TLcTaskAsset> sameCaseTaskList = this.taskService.selectSameCaseTaskList(sameCase.getKey(), orgId);
            if (sameCaseTaskList != null && sameCaseTaskList.size() > 1) {
                if (sameCaseTaskList.get(0).getOwnerId() != null) {
                    // 共案且有业务归属人
                    List<TLcTaskAsset> finalSameCaseTaskList = sameCaseTaskList;
                    sameCase.getValue().stream().forEach(task -> {
                        task.setOwnerId(finalSameCaseTaskList.get(0).getOwnerId());
                        task.setOwnerName(finalSameCaseTaskList.get(0).getOwnerName());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
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
     * 按照金额进行平均分配任务,优化：减少数据库的查询
     *
     * @param taskList
     * @param userList
     * @return
     */
    public List<TLcTaskAsset> averageAllocatTaskByMoneySameDeal2(List<TLcTaskAsset> taskList, List<SysUser> userList, String orgId) {
        List<TLcTaskAsset> newList = new ArrayList<>();
        int sameCaseUserIndex = 0;
        List<TLcTaskAsset> noSameCaseTaskList = new ArrayList<>();
        // 根据证件号码进行分组
        Map<String, List<TLcTaskAsset>> sameCaseMap = taskList.stream().collect(Collectors.groupingBy(TLcTaskAsset::getCertificateNo));
        // 获取需要分配的任务的证件号码集合
        Set<String> certificateNos = taskList.stream().map(task -> task.getCertificateNo()).collect(Collectors.toSet());
        // 根据证件号码和机构查询所有的任务
        List<TLcTaskAsset> allTaskList = getTaskListByCertificateNosAndOrdId(certificateNos, orgId);
        // 将根据证件号码和机构查询所有的任务根据证件号码进行分组
        Map<String, List<TLcTaskAsset>> allTaskSameCaseMap = allTaskList.stream().collect(Collectors.groupingBy(TLcTaskAsset::getCertificateNo));
        // 遍历从数据库查询的所有任务
        for (Map.Entry<String, List<TLcTaskAsset>> allTaskSameCase : allTaskSameCaseMap.entrySet()) {
            if (allTaskSameCase.getValue() != null && allTaskSameCase.getValue().size() > 1) {
                TLcTaskAsset tLcTask = allTaskSameCase.getValue().stream().filter(task -> task.getOwnerId() != null).findFirst().orElse(new TLcTaskAsset());
                if (tLcTask != null && tLcTask.getOwnerId() != null) {
                    // 共案且有业务归属人
                    sameCaseMap.get(allTaskSameCase.getKey()).stream().forEach(task -> {
                        task.setOwnerId(tLcTask.getOwnerId());
                        task.setOwnerName(tLcTask.getOwnerName());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setRecentlyAllotDate(new Date());
                    });
                    newList.addAll(sameCaseMap.get(allTaskSameCase.getKey()));
                } else {
                    // 共案但是没有业务归属人
                    int finalSameCaseUserIndex = sameCaseUserIndex;
                    sameCaseMap.get(allTaskSameCase.getKey()).stream().forEach(task -> {
                        task.setOwnerId(userList.get(finalSameCaseUserIndex).getUserId());
                        task.setOwnerName(userList.get(finalSameCaseUserIndex).getUserName());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setRecentlyAllotDate(new Date());
                    });
                    newList.addAll(sameCaseMap.get(allTaskSameCase.getKey()));
                    if (++sameCaseUserIndex > userList.size() - 1) {
                        sameCaseUserIndex = 0;
                    }
                }
            } else {
                // 不是共案信息
                noSameCaseTaskList.addAll(sameCaseMap.get(allTaskSameCase.getKey()));
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
    public List<TLcTaskAsset> averageAllocatTaskByMoneyNumSameDeal(List<TLcTaskAsset> taskList, List<SysUser> userList, String orgId) {
        // 根据证件号码进行分组
        Map<String, List<TLcTaskAsset>> sameCaseMap = taskList.stream().collect(Collectors.groupingBy(TLcTaskAsset::getCertificateNo));
        // 查询是否有共案
        List<TLcTaskAsset> newList = new ArrayList<>();
        int sameCaseUserIndex = 0;
        List<TLcTaskAsset> noSameCaseTaskList = new ArrayList<>();
        for (Map.Entry<String, List<TLcTaskAsset>> sameCase : sameCaseMap.entrySet()) {
            List<TLcTaskAsset> sameCaseTaskList = this.taskService.selectSameCaseTaskList(sameCase.getKey(), orgId);
            if (sameCaseTaskList != null && sameCaseTaskList.size() > 1) {
                if (sameCaseTaskList.get(0).getOwnerId() != null) {
                    // 共案且有业务归属人
                    List<TLcTaskAsset> finalSameCaseTaskList = sameCaseTaskList;
                    sameCase.getValue().stream().forEach(task -> {
                        task.setOwnerId(finalSameCaseTaskList.get(0).getOwnerId());
                        task.setOwnerName(finalSameCaseTaskList.get(0).getOwnerName());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
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

    /**
     * 按照金额和数量平均分配,优化：减少数据库的查询
     *
     * @param taskList
     * @param userList
     * @return
     */
    public List<TLcTaskAsset> averageAllocatTaskByMoneyNumSameDeal2(List<TLcTaskAsset> taskList, List<SysUser> userList, String orgId) {
        List<TLcTaskAsset> newList = new ArrayList<>();
        int sameCaseUserIndex = 0;
        List<TLcTaskAsset> noSameCaseTaskList = new ArrayList<>();
        // 根据证件号码进行分组
        Map<String, List<TLcTaskAsset>> sameCaseMap = taskList.stream().collect(Collectors.groupingBy(TLcTaskAsset::getCertificateNo));
        // 获取需要分配的任务的证件号码集合
        Set<String> certificateNos = taskList.stream().map(task -> task.getCertificateNo()).collect(Collectors.toSet());
        // 根据证件号码和机构查询所有的任务
        List<TLcTaskAsset> allTaskList = getTaskListByCertificateNosAndOrdId(certificateNos, orgId);
        // 将根据证件号码和机构查询所有的任务根据证件号码进行分组
        Map<String, List<TLcTaskAsset>> allTaskSameCaseMap = allTaskList.stream().collect(Collectors.groupingBy(TLcTaskAsset::getCertificateNo));
        // 遍历从数据库查询的所有任务
        for (Map.Entry<String, List<TLcTaskAsset>> allTaskSameCase : allTaskSameCaseMap.entrySet()) {
            TLcTaskAsset tLcTask = allTaskSameCase.getValue().stream().filter(task -> task.getOwnerId() != null).findFirst().orElse(new TLcTaskAsset());
            if (allTaskSameCase.getValue() != null && allTaskSameCase.getValue().size() > 1) {
                if (tLcTask != null && tLcTask.getOwnerId() != null) {
                    // 共案且有业务归属人
                    sameCaseMap.get(allTaskSameCase.getKey()).stream().forEach(task -> {
                        task.setOwnerId(tLcTask.getOwnerId());
                        task.setOwnerName(tLcTask.getOwnerName());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setRecentlyAllotDate(new Date());
                    });
                    newList.addAll(sameCaseMap.get(allTaskSameCase.getKey()));
                } else {
                    // 共案但是没有业务归属人
                    int finalSameCaseUserIndex = sameCaseUserIndex;
                    sameCaseMap.get(allTaskSameCase.getKey()).stream().forEach(task -> {
                        task.setOwnerId(userList.get(finalSameCaseUserIndex).getUserId());
                        task.setOwnerName(userList.get(finalSameCaseUserIndex).getUserName());
                        task.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                        task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                        task.setRecentlyAllotDate(new Date());
                    });
                    newList.addAll(sameCaseMap.get(allTaskSameCase.getKey()));
                    if (++sameCaseUserIndex > userList.size() - 1) {
                        sameCaseUserIndex = 0;
                    }
                }
            } else {
                // 不是共案信息
                noSameCaseTaskList.addAll(sameCaseMap.get(allTaskSameCase.getKey()));
            }
        }
        // 不是共案信息按金额及数量平均分
        noSameCaseTaskList = averageAllocatTaskByMoneyNum(noSameCaseTaskList, userList);
        newList.addAll(noSameCaseTaskList);
        return newList;
    }

    /**
     * 批量根据证件号码集合和机构id查询任务
     *
     * @param certificateNos
     * @param orgId
     * @return
     */
    private List<TLcTaskAsset> getTaskListByCertificateNosAndOrdId(Set<String> certificateNos, String orgId) {
        List<TLcTaskAsset> taskList = new ArrayList<>();
        List<String> certificateNoList = new ArrayList<>(certificateNos);
        int total = certificateNoList.size();
        int index = 1000;
        int pagesize = total / index;
        if (total <= index) {
            taskList = this.taskService.selectTaskListByCertificateNosAndOrdId(certificateNoList, orgId);
        } else {
            for (int i = 0; i < pagesize; i++) {
                List<String> certificateNoSubList = certificateNoList.subList(i * index, (i + 1) * index);
                List<TLcTaskAsset> subTaskList = this.taskService.selectTaskListByCertificateNosAndOrdId(certificateNoList, orgId);
                taskList.addAll(subTaskList);
            }
            if (total % index != 0) {
                List<String> certificateNoSubList = certificateNoList.subList(index * pagesize, total);
                List<TLcTaskAsset> subTaskList = this.taskService.selectTaskListByCertificateNosAndOrdId(certificateNoList, orgId);
                taskList.addAll(subTaskList);
            }
        }
        return taskList;
    }
}
