package com.ruoyi.duncase.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.callConfig.domain.TLcCallStrategyConfig;
import com.ruoyi.callConfig.service.ITLcCallStrategyConfigService;
import com.ruoyi.caseConfig.domain.TLcAllocatCaseConfig;
import com.ruoyi.caseConfig.service.ITLcAllocatCaseConfigService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RestTemplateUtil;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.domain.TLcCustJob;
import com.ruoyi.custom.domain.TLcCustinfo;
import com.ruoyi.custom.mapper.TLcCustContactMapper;
import com.ruoyi.custom.mapper.TLcCustJobMapper;
import com.ruoyi.custom.mapper.TLcCustinfoMapper;
import com.ruoyi.custom.service.ITLcCustContactService;
import com.ruoyi.duncase.domain.AllocatTaskInvokeRuleEngin;
import com.ruoyi.duncase.domain.Assets;
import com.ruoyi.duncase.domain.TLcDuncase;
import com.ruoyi.duncase.domain.TLcDuncaseAssign;
import com.ruoyi.duncase.mapper.TLcDuncaseAssignMapper;
import com.ruoyi.duncase.mapper.TLcDuncaseMapper;
import com.ruoyi.duncase.service.AsyncITLcDuncaseService;
import com.ruoyi.duncase.service.ITLcDuncaseService;
import com.ruoyi.enums.*;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.robot.domain.TLcRobotTask;
import com.ruoyi.robot.service.ITLcRobotTaskService;
import com.ruoyi.robot.utils.RobotMethodUtil;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.mapper.TLcTaskMapper;
import com.ruoyi.task.service.ITLcTaskService;
import com.ruoyi.utils.AllocatRuleUtil;
import com.ruoyi.utils.DataPermissionUtil;
import com.ruoyi.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 案件Service业务层处理
 *
 * @author liurunkai
 * @date 2019-12-25
 */
@Slf4j
@Service
public class AsyncTLcDuncaseServiceImpl implements AsyncITLcDuncaseService {

    @Autowired
    private TLcDuncaseMapper tLcDuncaseMapper;
    @Autowired
    private TLcDuncaseAssignMapper tLcDuncaseAssignMapper;

    /**
     * 修改案件信息
     *
     * @param taskList
     */
    @Override
    @Async
    public void updateDuncase(List<TLcTask> taskList, Integer taskType, Integer taskStatus) {
        List<TLcDuncase> duncaseList = taskList.stream().map(tLcTask -> {
            TLcDuncase tLcDuncase = this.tLcDuncaseMapper.findDuncaseByCaseNoAndImportBatchNo(tLcTask.getCaseNo(), tLcTask.getOrgId(), tLcTask.getImportBatchNo());
            tLcDuncase.setOwnerId(tLcTask.getOwnerId());
            tLcDuncase.setOwnerName(tLcTask.getOwnerName());
            tLcDuncase.setRecentlyAllotDate(new Date());
            tLcDuncase.setTaskType(taskType);
            tLcDuncase.setCaseStatus(taskStatus);
            return tLcDuncase;
        }).collect(Collectors.toList());
        this.tLcDuncaseMapper.batchUpdateDuncase(duncaseList);
        // 添加到案件轨迹表中
        insertDuncaseAssign(taskList, ShiroUtils.getSysUser());
    }

    @Override
    public void updateDuncase(List<TLcTask> taskList) {
        List<TLcDuncase> duncaseList = taskList.stream().map(tLcTask -> {
            TLcDuncase duncase = this.tLcDuncaseMapper.findDuncaseByCaseNoAndImportBatchNo(tLcTask.getCaseNo(), tLcTask.getOrgId(), tLcTask.getImportBatchNo());
            duncase.setTaskType(TaskTypeEnum.HELP_COLLECT_ROBOT.getCode());
            duncase.setAllocatType(AllocatTaskEnum.ROBOT.getAllocatCode());
            return duncase;
        }).collect(Collectors.toList());
        this.tLcDuncaseMapper.batchUpdateDuncase(duncaseList);
        insertDuncaseAssign(taskList, ShiroUtils.getSysUser());
    }

    /**
     * 添加到案件轨迹表中
     *
     * @param taskList
     */
    public void insertDuncaseAssign(List<TLcTask> taskList, SysUser sysUser) {
        List<TLcDuncaseAssign> duncaseAssignList = taskList.stream()
                .map(task -> {
                    TLcDuncaseAssign tLcDuncaseAssign = TLcDuncaseAssign.builder()
                            .ownerId(task.getOwnerId())
                            .ownerName(task.getOwnerName())
                            .taskId(task.getId().toString())
                            .operationId(sysUser.getUserId())
                            .customName(task.getCustomName())
                            .collectTeamName(task.getCollectTeamName())
                            .collectTeamId(task.getCollectTeamId())
                            .certificateNo(task.getCertificateNo())
                            .caseNo(task.getCaseNo())
                            .operationName(sysUser.getUserName())
                            .transferType(task.getTaskType())
                            .orgId(task.getOrgId())
                            .orgName(task.getOrgName())
                            .taskStatus(task.getTaskStatus())
                            .validateStatus(IsNoEnum.IS.getCode())
                            .build();
                    return tLcDuncaseAssign;
                }).collect(Collectors.toList());
        // 将该任务添加到案件历史轨迹表
        this.tLcDuncaseAssignMapper.batchInsertDuncaseAssign(duncaseAssignList);
    }

}
