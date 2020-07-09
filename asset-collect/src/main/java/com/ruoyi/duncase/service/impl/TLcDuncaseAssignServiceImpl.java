package com.ruoyi.duncase.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.duncase.domain.TLcDuncaseAssign;
import com.ruoyi.duncase.mapper.TLcDuncaseAssignMapper;
import com.ruoyi.duncase.service.ITLcDuncaseAssignService;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.task.domain.TLcTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 案件轨迹Service业务层处理
 *
 * @author liurunkai
 * @date 2019-12-27
 */
@Service
public class TLcDuncaseAssignServiceImpl implements ITLcDuncaseAssignService {
    @Autowired
    private TLcDuncaseAssignMapper tLcDuncaseAssignMapper;

    /**
     * 查询案件轨迹
     *
     * @param id 案件轨迹ID
     * @return 案件轨迹
     */
    @Override
    public TLcDuncaseAssign selectTLcDuncaseAssignById(Long id) {
        return tLcDuncaseAssignMapper.selectTLcDuncaseAssignById(id);
    }

    /**
     * 查询案件轨迹列表
     *
     * @param tLcDuncaseAssign 案件轨迹
     * @return 案件轨迹
     */
    @Override
    public List<TLcDuncaseAssign> selectTLcDuncaseAssignList(TLcDuncaseAssign tLcDuncaseAssign) {
        return tLcDuncaseAssignMapper.selectTLcDuncaseAssignList(tLcDuncaseAssign);
    }

    /**
     * 新增案件轨迹
     *
     * @param tLcDuncaseAssign 案件轨迹
     * @return 结果
     */
    @Override
    public int insertTLcDuncaseAssign(TLcDuncaseAssign tLcDuncaseAssign) {
        tLcDuncaseAssign.setCreateTime(DateUtils.getNowDate());
        return tLcDuncaseAssignMapper.insertTLcDuncaseAssign(tLcDuncaseAssign);
    }

    /**
     * 修改案件轨迹
     *
     * @param tLcDuncaseAssign 案件轨迹
     * @return 结果
     */
    @Override
    public int updateTLcDuncaseAssign(TLcDuncaseAssign tLcDuncaseAssign) {
        return tLcDuncaseAssignMapper.updateTLcDuncaseAssign(tLcDuncaseAssign);
    }

    /**
     * 删除案件轨迹对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcDuncaseAssignByIds(String ids) {
        return tLcDuncaseAssignMapper.deleteTLcDuncaseAssignByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除案件轨迹信息
     *
     * @param id 案件轨迹ID
     * @return 结果
     */
    @Override
    public int deleteTLcDuncaseAssignById(Long id) {
        return tLcDuncaseAssignMapper.deleteTLcDuncaseAssignById(id);
    }

    @Override
    public List<TLcDuncaseAssign> findDuncaseAssignByCertificateNo(String certificateNo) {
        return this.tLcDuncaseAssignMapper.findDuncaseAssignByCertificateNo(certificateNo);
    }

    @Override
    public List<TLcDuncaseAssign> findDuncaseAssignByCaseNo(String caseNo) {
        return this.tLcDuncaseAssignMapper.findDuncaseAssignByCaseNo(caseNo);
    }

    @Override
    public List<TLcDuncaseAssign> findHisDuncaseAssignByCaseNo(String caseNo) {
        return this.tLcDuncaseAssignMapper.findHisDuncaseAssignByCaseNo(caseNo);
    }

    @Override
    @Async
    public void batchInsertDuncaseAssign(List<TLcTask> taskList, SysUser sysUser, Integer taskType) {
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
                            .transferType(taskType)
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
