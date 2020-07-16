package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.Task;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysRoleDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务Mapper接口
 *
 * @author liurunkai
 * @date 2019-12-25
 */
public interface TaskMapper {
    /**
     * 查询任务
     *
     * @param id 任务ID
     * @return 任务
     */
    public Task selectTLcTaskById(Long id);

    /**
     * 查询任务列表
     *
     * @param tLcTask 任务
     * @return 任务集合
     */
    public List<Task> selectTLcTaskList(Task tLcTask);

    /**
     * 查询任务列表
     *
     * @param tLcTask 任务
     * @return 任务集合
     */
    public List<Task> selectTLcTaskByPage(Task tLcTask);

    /**
     * 新增任务
     *
     * @param tLcTask 任务
     * @return 结果
     */
    public int insertTLcTask(Task tLcTask);

    /**
     * 修改任务
     *
     * @param tLcTask 任务
     * @return 结果
     */
    public int updateTLcTask(Task tLcTask);

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
    void batchInsertTask(@Param("taskList") List<Task> taskList);

    /**
     * 根据证件号码查询对应的任务详情
     *
     * @param certificateNo
     * @return
     */
    Task findTaskByCertificateNo(@Param("certificateNo") String certificateNo);

    /**
     * 根据坐席编号查询对应的任务列表
     *
     * @param
     * @return
     */
    public List<Task> findTaskByOwner(String ownerId);

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
    void batchUpdateTask(@Param("taskList") List<Task> taskList);

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
    Task selectTaskByCaseNo(@Param("caseNo") String caseNo, @Param("orgId") String orgId);

    Task selectTaskByCaseNo3(@Param("caseNo") String caseNo, @Param("orgId") String orgId);


    /**
     * 查询我的任务列表
     *
     * @param tLcTask
     * @return
     */
    List<Task> selectMyTaskList(Task tLcTask);

    /**
     * 根据机器人任务id查询对应的任务
     *
     * @param robotTaskId
     * @return
     */
    Task selectTaskByRobotTaskId(@Param("robotTaskId") String robotTaskId);

    Task selectTaskByRobotTaskIdAndPhone(@Param("robotTaskId") String robotTaskId, @Param("phone") String phone);

    Integer isHasCloseCaseTask(Task tLcTask);

    Integer isHasCTSTask(Task tLcTask);

    List<Task> selectTaskListByRobotTaskIdAndPhone(@Param("robotTaskId") String robotTaskId, @Param("phone") String phone);

    void updateTLcTaskByRobotTaskIdAndPhone(Task tLcTask);
}
