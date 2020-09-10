package com.ruoyi.task.mapper;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysRoleDept;
import com.ruoyi.task.domain.TLcTask;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 任务Mapper接口
 *
 * @author liurunkai
 * @date 2019-12-25
 */
public interface TLcTaskMapper {
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
     * 删除任务
     *
     * @param id 任务ID
     * @return 结果
     */
    public int deleteTLcTaskById(Long id);

    /**
     * 批量删除任务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcTaskByIds(String[] ids);

    /**
     * 批量生成任务
     *
     * @param taskList
     */
    void batchInsertTask(@Param("taskList") List<TLcTask> taskList);

    /**
     * 根据证件号码查询对应的任务详情
     *
     * @param certificateNo
     * @return
     */
    TLcTask findTaskByCertificateNo(@Param("certificateNo") String certificateNo);

    /**
     * 根据坐席编号查询对应的任务列表
     *
     * @param
     * @return
     */
    public List<TLcTask> findTaskByOwner(String ownerId);

    /**
     * 任务重新分配
     *
     * @param taskIds
     * @param userId
     */
    void updateOwnerById(@Param("taskIds") List<String> taskIds, @Param("userId") String userId);

    /**
     * 批量更新任务
     *
     * @param taskList
     */
    void batchUpdateTask(@Param("taskList") List<TLcTask> taskList);

    /**
     * 通过组织机构id查询对应的部门id
     *
     * @param orgId
     * @return
     */
    Long findDeptIdByOrgId(@Param("orgId") String orgId);

    /**
     * 根据父部门id查询所有子部门
     *
     * @param parentId
     * @return
     */
    List<SysDept> selectDeptByParentId(@Param("parentId") Long parentId);

    /**
     * 根据角色id查询有权限查看的部门
     *
     * @param roleId
     * @return
     */
    List<SysRoleDept> selectDeptIdByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据案件号查询对应的任务
     *
     * @param caseNo
     * @return
     */
    TLcTask selectTaskByCaseNo(@Param("caseNo") String caseNo, @Param("orgId") String orgId, @Param("importBatchNo") String importBatchNo);

    TLcTask selectHisTaskByCaseNo(@Param("caseNo") String caseNo, @Param("orgId") String orgId, @Param("importBatchNo") String importBatchNo);

    /**
     * 查询我的任务列表
     *
     * @param tLcTask
     * @return
     */
    List<TLcTask> selectMyTaskList(TLcTask tLcTask);

    /**
     * 根据机器人任务id查询对应的任务
     *
     * @param robotTaskId
     * @return
     */
    TLcTask selectTaskByRobotTaskId(@Param("robotTaskId") String robotTaskId);

    TLcTask selectTaskByRobotTaskIdAndPhone(@Param("robotTaskId") String robotTaskId, @Param("phone") String phone);

    Integer isHasCloseCaseTask(TLcTask tLcTask);

    Integer isHasCTSTask(TLcTask tLcTask);

    List<TLcTask> selectTaskListByRobotTaskIdAndPhone(@Param("robotTaskId") String robotTaskId, @Param("phone") String phone);

    void updateTLcTaskByRobotTaskIdAndPhone(TLcTask tLcTask);

    void updateTLcTaskByRobotTaskId(TLcTask tLcTask);

    List<TLcTask> selectTaskListByRobotTaskId(@Param("robotTaskId") Integer robotTaskId);

    List<TLcTask> selectTaskList(TLcTask tLcTask);

    TLcTask selectTaskByCaseAndNoCloseCase(@Param("caseNo") String caseNo);

    List<TLcTask> selectTaskListNotExistRobotBlack(TLcTask tLcTask);

    Long selectCountExistRobotBlack(TLcTask tLcTask);

    List<TLcTask> selectTLcTaskByIdsNotExistRobotBlack(@Param("taskIds") String[] taskIds);

    Long selectCountByIdsNotExistRobotBlack(@Param("taskIds") String[] taskIds);
    /**
     * 根据时间只查询 任务表--定时任务同步数据中心 用
     * 2020-06-24 封志涛添加
     * 参数命名为 pageNum，pageSize，会自动触发 PageHelper，然后系统会自动给查询语句追加limit语句
     * @return
     */
    List<Map<String,Object>> selectTaskByTime(@Param("createTime")Date createTime, @Param("modifyTime")Date modifyTime, @Param("pnum")int pnum,@Param("psize")int psize);
    /**
     * 查询任务表总数--定时任务同步数据中心 用
     * 2020-06-29 封志涛添加
     * @return
     */
    Integer selectTaskCount(@Param("createTime")Date createTime, @Param("modifyTime")Date modifyTime);

    Map<String, BigDecimal> selectTotalCountMoney(TLcTask tLcTask);

    List<TLcTask> selectTaskListBySendRobotBatchNo(@Param("sendRobotBatchNo") String sendRobotBatchNo);

    void updateTLcTaskBySendRobotBatchNo(TLcTask tLcTask);

    void updateTaskByCaseNoAndImportBatchNoAndOrgId(TLcTask tLcTask);

    void updateTaskFromRobotTask(@Param("robotTaskId") Integer robotTaskId);

    void insertDuncaseAssign(@Param("robotTaskId") Integer robotTaskId);

    void updateTaskTypeAllocatType(@Param("robotTaskId") Integer robotTaskId);

    Map<String, BigDecimal> searchAllTaskTotalMoney(TLcTask tLcTask);

    List<TLcTask> selectSameCaseTaskList(@Param("certificateNo") String certificateNo, @Param("orgId") String orgId);


    void updateNotebook(TLcTask tLcTask);

    void updateCaseRecycle(@Param("taskIdList") List<Long> taskIdList);

    int updateColor(TLcTask tLcTask);
}
