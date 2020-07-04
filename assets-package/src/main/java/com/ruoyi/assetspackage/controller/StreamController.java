package com.ruoyi.assetspackage.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.enums.IsCloseCaseEnum;
import com.ruoyi.assetspackage.enums.IsClosePackageEnum;
import com.ruoyi.assetspackage.enums.IsNullPackageEnum;
import com.ruoyi.assetspackage.enums.PackageFlagEnum;
import com.ruoyi.assetspackage.service.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 流水Controller
 * 
 * @author guozeqi
 * @date 2020-01-22
 */
@Controller
@RequestMapping("/assetspackage/stream")
public class StreamController extends BaseController
{
    private String prefix = "assetspackage/stream";


    @Autowired
    private IStreamPackageService streamPackageService;

    @Autowired
    private IOrgPackageService orgPackageService;

    @Autowired
    private ICurAssetsPackageService curAssetsPackageService;

    @Autowired
    private ICurAssetsRepaymentPackageService curAssetsRepaymentPackageService;





    @RequiresPermissions("assetspackage:stream:view")
    @GetMapping()
    public String assetspackage()
    {
        return prefix + "/streamView";
    }

   /**
     * 查询流水列表
     */
    @RequiresPermissions("assetspackage:stream:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StreamPackage streamPackage)
    {
        startPage();
        List<StreamPackage> list = streamPackageService.selectStreamPackageList(streamPackage);
        for (StreamPackage stream : list) {
            String jazt = stream.getJazt();
            if(jazt != null && IsCloseCaseEnum.CLOSE_CASE.getValue().equals(jazt)){
                stream.setJazt("已结案");
            }else {
                stream.setJazt("未结案");
            }
        }
        return getDataTable(list);
    }



    /**
     *查询当前用户拥有的数据权限与委托方的交集
     * @param request
     * @return
     */
    @RequiresPermissions("assetspackage:stream:list")
    @PostMapping("/findOrgInfo")
    @ResponseBody
    public AjaxResult findOrgInfo(HttpServletRequest request){
        List<Map<String, Long>> orgInfo = null;
        Long UserId = ShiroUtils.getUserId();
        String dataScope = orgPackageService.selectDataScopeByUserId(UserId);
        if("1".equals(dataScope)){
            orgInfo = orgPackageService.selectOrgInfoByUserId1(UserId);
        }else if("2".equals(dataScope)){
            orgInfo = orgPackageService.selectOrgInfoByUserId2(UserId);
        }else if("4".equals(dataScope)){
            orgInfo = orgPackageService.selectOrgInfoByUserId4(UserId);
        }else {
            orgInfo = orgPackageService.selectOrgInfoByUserId3(UserId);
        }
        String result = JSON.toJSONString(orgInfo);
        return AjaxResult.success(result);
    }


    /**
     * 导出资产包列表
     */
    @RequiresPermissions("assetspackage:stream:export")
    @Log(title = "资产包", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StreamPackage streamPackage)
    {
        List<StreamPackage> list = streamPackageService.selectStreamPackageList(streamPackage);
        ExcelUtil<StreamPackage> util = new ExcelUtil<StreamPackage>(StreamPackage.class);
        return util.exportExcel(list, "package");
    }



}
