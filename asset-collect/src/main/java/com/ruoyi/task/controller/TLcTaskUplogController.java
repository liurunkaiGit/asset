package com.ruoyi.task.controller;

import java.util.List;

import com.ruoyi.task.domain.TLcTaskUplog;
import com.ruoyi.task.service.ITLcTaskUplogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【信息更新申请-次数记录】Controller
 * 
 * @author gaohg
 * @date 2021-02-03
 */
@Controller
@RequestMapping("/infoup/uplog")
public class TLcTaskUplogController extends BaseController
{
    @Autowired
    private ITLcTaskUplogService tLcTaskUplogService;

    @PostMapping( "/findCishu")
    @ResponseBody
    public Integer findCishu(){
        return tLcTaskUplogService.findCishu();
    }
}
