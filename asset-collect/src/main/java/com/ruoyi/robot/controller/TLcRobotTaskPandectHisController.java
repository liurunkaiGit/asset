package com.ruoyi.robot.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.robot.domain.TLcRobotTaskPandect;
import com.ruoyi.robot.service.ITLcRobotTaskPandectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 机器人历史呼叫任务管理Controller
 *
 * @author liurunkai
 * @date 2020-05-09
 */
@Controller
@RequestMapping("/robot/pandect/his")
public class TLcRobotTaskPandectHisController extends BaseController {
    private String prefix = "robot/pandect";

    @Autowired
    private ITLcRobotTaskPandectService tLcRobotTaskPandectService;

    @RequiresPermissions("robot:pandect:his:view")
    @GetMapping("/view")
    public String pandect(ModelMap modelMap) {
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        return prefix + "/pandectHis";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("robot:pandect:his:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcRobotTaskPandect tLcRobotTaskPandect) {
        startPage();
        List<TLcRobotTaskPandect> list = tLcRobotTaskPandectService.selectTLcRobotTaskPandectHisList(tLcRobotTaskPandect);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("robot:pandect:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcRobotTaskPandect tLcRobotTaskPandect) {
        List<TLcRobotTaskPandect> list = tLcRobotTaskPandectService.selectTLcRobotTaskPandectHisList(tLcRobotTaskPandect);
        ExcelUtil<TLcRobotTaskPandect> util = new ExcelUtil<TLcRobotTaskPandect>(TLcRobotTaskPandect.class);
        return util.exportExcel(list, "pandect");
    }
}
