package com.ruoyi.robot.service;

import com.ruoyi.robot.domain.TLcRobotBlack;
import com.ruoyi.task.domain.TLcTask;

import java.util.List;

/**
 * 机器人黑名单管理Service接口
 *
 * @author liurunkai
 * @date 2020-06-24
 */
public interface ITLcRobotBlackService {
    /**
     * 查询机器人黑名单管理
     *
     * @param id 机器人黑名单管理ID
     * @return 机器人黑名单管理
     */
    public TLcRobotBlack selectTLcRobotBlackById(Long id);

    /**
     * 查询机器人黑名单管理列表
     *
     * @param tLcRobotBlack 机器人黑名单管理
     * @return 机器人黑名单管理集合
     */
    public List<TLcRobotBlack> selectTLcRobotBlackList(TLcRobotBlack tLcRobotBlack);

    /**
     * 新增机器人黑名单管理
     *
     * @param tLcRobotBlack 机器人黑名单管理
     * @return 结果
     */
    public int insertTLcRobotBlack(TLcRobotBlack tLcRobotBlack);

    /**
     * 新增机器人黑名单管理
     *
     * @param tLcTask 机器人黑名单管理
     * @return 结果
     */
    public int insertTLcRobotBlack(TLcTask tLcTask, String reason, String phone);

    /**
     * 修改机器人黑名单管理
     *
     * @param tLcRobotBlack 机器人黑名单管理
     * @return 结果
     */
    public int updateTLcRobotBlack(TLcRobotBlack tLcRobotBlack);

    /**
     * 批量删除机器人黑名单管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcRobotBlackByIds(String ids);

    /**
     * 删除机器人黑名单管理信息
     *
     * @param id 机器人黑名单管理ID
     * @return 结果
     */
    public int deleteTLcRobotBlackById(Long id);

    /**
     * 从机器人黑名单管理移除
     * @param robotBlack
     */
    void deleteobotBlackByCaseReason(TLcRobotBlack robotBlack);

    void batchDeleteRobotBlackByCaseNo(List<String> caseNoList);
}
