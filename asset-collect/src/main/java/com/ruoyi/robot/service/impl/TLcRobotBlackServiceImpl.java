package com.ruoyi.robot.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.robot.domain.TLcRobotBlack;
import com.ruoyi.robot.mapper.TLcRobotBlackMapper;
import com.ruoyi.robot.service.ITLcRobotBlackService;
import com.ruoyi.task.domain.TLcTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机器人黑名单管理Service业务层处理
 *
 * @author liurunkai
 * @date 2020-06-24
 */
@Service
public class TLcRobotBlackServiceImpl implements ITLcRobotBlackService {
    @Autowired
    private TLcRobotBlackMapper tLcRobotBlackMapper;

    /**
     * 查询机器人黑名单管理
     *
     * @param id 机器人黑名单管理ID
     * @return 机器人黑名单管理
     */
    @Override
    public TLcRobotBlack selectTLcRobotBlackById(Long id) {
        return tLcRobotBlackMapper.selectTLcRobotBlackById(id);
    }

    /**
     * 查询机器人黑名单管理列表
     *
     * @param tLcRobotBlack 机器人黑名单管理
     * @return 机器人黑名单管理
     */
    @Override
    public List<TLcRobotBlack> selectTLcRobotBlackList(TLcRobotBlack tLcRobotBlack) {
        return tLcRobotBlackMapper.selectTLcRobotBlackList(tLcRobotBlack);
    }

    /**
     * 新增机器人黑名单管理
     *
     * @param tLcRobotBlack 机器人黑名单管理
     * @return 结果
     */
    @Override
    public int insertTLcRobotBlack(TLcRobotBlack tLcRobotBlack) {
        return tLcRobotBlackMapper.insertTLcRobotBlack(tLcRobotBlack);
    }

    /**
     * 新增机器人黑名单管理
     *
     * @param tLcTask 机器人黑名单管理
     * @return 结果
     */
    @Override
    public int insertTLcRobotBlack(TLcTask tLcTask, String reason, String phone) {
        TLcRobotBlack tLcRobotBlack = new TLcRobotBlack();
        tLcRobotBlack.setCaseNo(tLcTask.getCaseNo())
                .setCustomerName(tLcTask.getCustomName())
                .setImportBatchNo(tLcTask.getImportBatchNo())
                .setOrgId(Integer.valueOf(tLcTask.getOrgId()))
                .setOrgName(tLcTask.getOrgName())
                .setPhone(phone)
                .setReason(reason)
                .setCreateBy(String.valueOf(ShiroUtils.getSysUser().getUserId()));
        return tLcRobotBlackMapper.insertTLcRobotBlack(tLcRobotBlack);
    }

    /**
     * 修改机器人黑名单管理
     *
     * @param tLcRobotBlack 机器人黑名单管理
     * @return 结果
     */
    @Override
    public int updateTLcRobotBlack(TLcRobotBlack tLcRobotBlack) {
        return tLcRobotBlackMapper.updateTLcRobotBlack(tLcRobotBlack);
    }

    /**
     * 删除机器人黑名单管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcRobotBlackByIds(String ids) {
        return tLcRobotBlackMapper.deleteTLcRobotBlackByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除机器人黑名单管理信息
     *
     * @param id 机器人黑名单管理ID
     * @return 结果
     */
    @Override
    public int deleteTLcRobotBlackById(Long id) {
        return tLcRobotBlackMapper.deleteTLcRobotBlackById(id);
    }

    @Override
    public void deleteobotBlackByCaseReason(TLcRobotBlack robotBlack) {
        this.tLcRobotBlackMapper.deleteobotBlackByCaseReason(robotBlack);
    }

    @Override
    public void batchDeleteRobotBlackByCaseNo(List<String> caseNoList) {
        tLcRobotBlackMapper.batchDeleteRobotBlackByCaseNo(caseNoList);
    }
}
