package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.RobotBlack;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 机器人黑名单管理Mapper接口
 *
 * @author liurunkai
 * @date 2020-06-24
 */
public interface RobotBlackMapper {
    /**
     * 查询机器人黑名单管理
     *
     * @param id 机器人黑名单管理ID
     * @return 机器人黑名单管理
     */
    public RobotBlack selectTLcRobotBlackById(Long id);

    /**
     * 查询机器人黑名单管理列表
     *
     * @param tLcRobotBlack 机器人黑名单管理
     * @return 机器人黑名单管理集合
     */
    public List<RobotBlack> selectTLcRobotBlackList(RobotBlack tLcRobotBlack);

    /**
     * 新增机器人黑名单管理
     *
     * @param tLcRobotBlack 机器人黑名单管理
     * @return 结果
     */
    public int insertTLcRobotBlack(RobotBlack tLcRobotBlack);

    /**
     * 修改机器人黑名单管理
     *
     * @param tLcRobotBlack 机器人黑名单管理
     * @return 结果
     */
    public int updateTLcRobotBlack(RobotBlack tLcRobotBlack);

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

    void deleteobotBlackByCaseReason(RobotBlack robotBlack);

    void batchDeleteRobotBlackByCaseNo(@Param("caseNoList") List<String> caseNoList);
}
