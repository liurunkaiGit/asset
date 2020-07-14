package com.ruoyi.robot.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.robot.domain.TLcSendRobotApply;
import com.ruoyi.robot.service.ITLcSendRobotApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: 机器人呼叫审核管理
 * @author: liurunkai
 * @Date: 2020/7/13 9:53
 */
@Slf4j
@Controller
@RequestMapping("/robot/approval")
public class RobotApprovalController extends BaseController {

    public final static String prefix = "robot";

    @Autowired
    private ITLcSendRobotApplyService sendRobotApplyService;

    @GetMapping("/view")
    public String view() {
        return prefix + "/robotApproval";
    }

    /**
     * 查询任务列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcSendRobotApply sendRobotApply) {
        startPage();
        sendRobotApply.setOrgId(ShiroUtils.getSysUser().getOrgId());
        List<TLcSendRobotApply> list = sendRobotApplyService.selectTLcSendRobotApplyList(sendRobotApply);
        return getDataTable(list);
    }

    /**
     * 推送机器人审核
     *
     * @param sendRobotBatchNos
     * @return
     */
    @ResponseBody
    @GetMapping("/approveSendRobot")
    public AjaxResult approveSendRobot(String sendRobotBatchNos, Integer status) {
        try {
            return this.sendRobotApplyService.approveSendRobot(sendRobotBatchNos, status);
        } catch (Exception e) {
            log.error("审批失败：{}", e);
            return AjaxResult.error("审批失败");
        }
    }
}
