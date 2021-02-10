package com.ruoyi.task.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.duncase.domain.AssetsRepayment;
import com.ruoyi.robot.domain.TLcRobotTask;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.task.domain.CollJob;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.utils.CloseCaseUser;
import com.ruoyi.utils.Response;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 任务Service接口
 *
 * @author liurunkai
 * @date 2019-12-25
 */
public interface ITLcTaskService {
    /**
     * 查询任务
     *
     * @param id 任务ID
     * @return 任务
     */
    public TLcTask selectTLcTaskById(Long id);

    /**
     * 查询任务列表
     *
     * @param tLcTask 任务
     * @return 任务集合
     */
    public List<TLcTask> selectTLcTaskList(TLcTask tLcTask);

    /**
     * 查询任务列表
     *
     * @param tLcTask 任务
     * @return 任务集合
     */
    public List<TLcTask> selectTLcTaskByPage(TLcTask tLcTask);

    /**
     * 查询任务列表
     *
     * @param tLcTask 任务
     * @return 任务集合
     */
    public List<TLcTask> findTaskByOwner(String ownerId);

    /**
     * 新增任务
     *
     * @param tLcTask 任务
     * @return 结果
     */
    public int insertTLcTask(TLcTask tLcTask);

    /**
     * 修改任务
     *
     * @param tLcTask 任务
     * @return 结果
     */
    public int updateTLcTask(TLcTask tLcTask);

    /**
     * 批量删除任务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcTaskByIds(String ids);

    /**
     * 删除任务信息
     *
     * @param id 任务ID
     * @return 结果
     */
    public int deleteTLcTaskById(Long id);

    /**
     * 批量生成任务
     *
     * @param taskList
     */
    void batchInsertTask(List<TLcTask> taskList);

    /**
     * 根据证件号码查询对应的任务详情
     *
     * @param certificateNo
     * @return
     */
    TLcTask findTaskByCertificateNo(String certificateNo);

    /**
     * 任务重新分配
     *
     * @param userId
     * @param taskIds
     * @param orgId
     */
    void reAllocat(String userId, String taskIds, String orgId);

    /**
     * 任务重新分配给多个人
     *
     * @param userId
     * @param taskIds
     * @param orgId
     * @param allocatNum
     * @param allocatRule
     */
    AjaxResult reAllocat(String userId, String taskIds, String orgId, Integer allocatNum, Integer allocatRule, String caseNos, String certificateNos,String arrearsTotals, SysUser sysUser);

    /**
     * 临时代理
     *
     * @param userId
     * @param taskIds
     * @param orgId
     * @param oldOwnerIds
     */
    void tempAgent(String userId, String taskIds, String orgId, String oldOwnerIds, String caseNos);

    /**
     * 临时代理任务回收
     *
     * @param oldOwnerIds
     * @param taskIds
     */
    void tempAgentRecycle(String oldOwnerIds, String taskIds, String caseNos);

    /**
     * 协助催收申请
     *
     * @param userId
     * @param taskIds
     * @param ownerIds
     */
    void helpColl(String userId, String taskIds, String ownerIds);

    /**
     * 通过组织机构id查询对应的部门id
     *
     * @param orgId
     * @return
     */
    Long findDeptIdByOrgId(String orgId);

    /**
     * 结案
     *
     * @param taskIds
     */
    void closeCase(String taskIds, String closeCaseType);

    void closeAllCase(TLcTask tLcTask, String closeCaseType);

    /**
     * 通过安检编号进行结案
     *
     * @param closeCaseUser
     */
    void closeCaseByCaseNo(CloseCaseUser closeCaseUser);

    /**
     * 将任务变更插入到案件轨迹表
     *
     * @param taskList
     */
    void insertDuncaseAssign(List<TLcTask> taskList, SysUser sysUser);

    /**
     * 根据案件号查询对应的还款记录
     *
     * @param caseNo
     * @return
     */
    List<AssetsRepayment> viewRepayHis(String caseNo);

    /**
     * 查询我的任务列表
     *
     * @param tLcTask
     * @return
     */
    List<TLcTask> selectMyTaskList(TLcTask tLcTask);
    List<TLcTask> selectMyTaskList2(TLcTask tLcTask);

    /**
     * 信息审批列表
     * @param tLcTask
     * @return
     */
    List<TLcTask> selectMyTaskInfoUpList(TLcTask tLcTask);
    /**
     * 查询用户信息
     *
     * @return
     */
    List<SysUser> searchAllUser(SysUser sysUser);

    /**
     * 协助催收审批
     *
     * @param taskIds
     * @param status
     */
    void approveHelpColl(String taskIds, Integer status);

    /**
     * 停催申请
     *
     * @param taskId
     */
    void stopCollApply(String taskId);

    /**
     * 停催审核、激活
     *
     * @param taskIds
     * @param status
     */
    void approveStopColl(String taskIds, Integer status);

    /**
     * 根据案件号查询任务信息
     *
     * @param caseNo
     * @return
     */
    TLcTask selectTaskByCaseNo(String caseNo, String orgId, String importBatchNo);

    TLcTask selectHisTaskByCaseNo(String caseNo, String orgId, String importBatchNo);

    /**
     * 根据机器人任务id查询对应的任务
     *
     * @param robotTaskId
     * @return
     */
    TLcTask selectTaskByRobotTaskId(String robotTaskId);

    /**
     * 发送给机器人进行协催
     *
     * @param taskIds
     */
    AjaxResult sendRobot(String taskIds, String orgId, String speechcraftId, Integer callLineId, String sendRobotBatchNos, Integer callType);

    CollJob collJobDetail(String caseNo, String orgId, String importBatchNo);

    CollJob hisDuncaseCollJobDetail(String caseNo, String orgId, String importBatchNo);

    Response addCallRecord(TLcCallRecord tLcCallRecord, String importBatchNo, String callStartTime, String callEndTime);

    /**
     * 操作全部数据
     *
     * @param tLcTask
     * @return
     */
    List<TLcTask> opreAllFindData(TLcTask tLcTask);

    List<SysUser> searchUserByDept(SysUser sysUser);

    AjaxResult allDataReAllocat(String userId, TLcTask tLcTask, Integer allocatNum, Integer allocatRule, SysUser sysUser);

    TLcRobotTask createRobotTask(TLcTask task, Integer robotTaskId, Integer speechcraftId, String speechcraftName, String robot, Integer continueCallDays, Integer continueCallFrequency);

    TLcTask selectTaskByRobotTaskIdAndPhone(String robotTaskId, String phone);

    List<TLcTask> selectTaskListByRobotTaskIdAndPhone(String robotTaskId, String phone);

    Integer isHasCloseCaseTask(TLcTask tLcTask, HttpServletRequest request);

    AjaxResult allDataSendRobot(String speechcraftIdAndSceneDefId, TLcTask tLcTask, Integer callLineId, Integer callType);

    Integer isHasCTSTask(TLcTask tLcTask);

    void updateTLcTaskByRobotTaskIdAndPhone(TLcTask tLcTask);

    List<SysUser> searchUserByDeptAndHaveDept(SysUser sysUser);

    /**
     * 通过案件编号查询未结案的任务
     *
     * @param caseNo
     * @return
     */
    TLcTask selectTaskByCaseAndNoCloseCase(String caseNo);

    /**
     * 根据时间只查询 任务表--定时任务同步数据中心 用
     * 2020-06-24 封志涛添加
     * @return
     */
    List<Map<String,Object>> selectTaskByTime(Date createTime, Date modifyTime, int pageNum, int pageSize);

    /**
     * 查询任务表总数--定时任务同步数据中心 用
     * 2020-06-29 封志涛添加
     * @return
     */
    Integer selectTaskCount(Date createTime, Date modifyTime);

    Map<String, BigDecimal> selectTotalCountMoney(TLcTask tLcTask);
    Map<String, BigDecimal> selectTotalCountMoney2(TLcTask tLcTask);

    Response sendRobotApply(String taskIds);

    List<TLcTask> selectTaskListBySendRobotBatchNo(String sendRobotBatchNo);

    void updateTLcTaskBySendRobotBatchNo(TLcTask tLcTask);

    List<AssetsRepayment> viewHisRepayHis(String caseNo);

    void insertDuncaseAssign(Integer robotTaskId);

    Map<String, BigDecimal> searchAllTaskTotalMoney(TLcTask tLcTask);

    List<TLcTask> selectSameCaseTaskList(String certificateNo, String orgId);

    Response updateNotebook(TLcTask tLcTask);

//    public Map<String,Integer> selectPhoneStatus(String caseNo, String phoneStatus, String orgId, String orgName, String loginName);
    public void selectPhoneStatus(String caseNo, String phoneStatus, String orgId, String orgName, String loginName);

    AjaxResult caseRecyle(String taskIds, String certificateNos, Integer caseRecycleNum, String agentIds);

    AjaxResult allCaseRecyle(TLcTask tLcTask, Integer caseRecycleNum);

    int updateColor(TLcTask tLcTask);
    /**
     * 查询ids 当前状态是否有值
     * @param ids
     * @param infoUp
     * @return
     */
    Integer findInfoUpCnt(@Param("ids") BigInteger[] ids, @Param("infoUp") int infoUp);
    /**
     * 信息更新状态
     * @param ids
     * @param status
     * @return
     */
    Integer updateInfoUp(BigInteger[] ids, int status);

    List<TLcTask> selectTaskList(TLcTask tLcTask);

    List<TLcTask> selectTaskListByCertificateNosAndOrdId(List<String> certificateNos, String orgId);

    Long selectAllocatedCount(TLcTask tLcTask);

    List<SysUser> selectCaseRecycleSelectAgent(TLcTask tLcTask);
}
