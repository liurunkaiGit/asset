package com.ruoyi.robot.mapper;

import com.ruoyi.robot.domain.TLcRobotBlack;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 机器人黑名单管理Mapper接口
 *
 * @author liurunkai
 * @date 2020-06-24
 */
public interface TLcRobotBlackMapper {
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
     * 修改机器人黑名单管理
     *
     * @param tLcRobotBlack 机器人黑名单管理
     * @return 结果
     */
    public int updateTLcRobotBlack(TLcRobotBlack tLcRobotBlack);

    /**
     * 删除机器人黑名单管理
     *
     * @param id 机器人黑名单管理ID
     * @return 结果
     */
    public int deleteTLcRobotBlackById(Long id);

    /**
     * 批量删除机器人黑名单管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcRobotBlackByIds(String[] ids);

    void deleteobotBlackByCaseReason(TLcRobotBlack robotBlack);

    void batchDeleteRobotBlackByCaseNo(@Param("caseNoList") List<String> caseNoList);
}
