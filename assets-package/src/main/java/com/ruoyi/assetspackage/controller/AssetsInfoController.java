package com.ruoyi.assetspackage.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.AssetsInfoPackage;
import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.assetspackage.domain.CurAssetsRepaymentPackage;
import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.enums.IsCloseCaseEnum;
import com.ruoyi.assetspackage.service.IAssetsInfoService;
import com.ruoyi.assetspackage.service.ICurAssetsPackageService;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户资产Controller
 *
 * @author guozeqi
 * @date 2020-02-06
 */
@Controller
@RequestMapping("/assetspackage/info")
public class AssetsInfoController extends BaseController {
    private String prefix = "assetspackage/info";

    @Autowired
    private IAssetsInfoService assetsInfoService;

    @Autowired
    private ICurAssetsPackageService curAssetsPackageService;

    @Autowired
    private IOrgPackageService orgPackageService;


    @RequiresPermissions("assetspackage:info:view")
    @GetMapping()
    public String AssetsInfo(ModelMap modelMap) {
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        return prefix + "/assetsInfo";
    }

    @RequiresPermissions("assetspackage:info:view")
    @GetMapping("/details")
    public String AssetsInfoDetails(String orgCasno, ModelMap mmap) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        AssetsInfoPackage assetsInfoPackage = assetsInfoService.selectCurAssetsPackageByOrgCasno(orgCasno);
        Date birthday = assetsInfoPackage.getBirthday();
        assetsInfoPackage.setBirthday2(sdf.format(birthday));

        OrgPackage orgPackage = orgPackageService.selectOrgPackageById(assetsInfoPackage.getOrgId());
        Date startDate = orgPackage.getStartDate();
        Date endDate = orgPackage.getEndDate();
        String orgStatus = orgPackage.getOrgStatus();
        orgStatus = "1".equals(orgStatus) ? "正常" : "停用";
        orgPackage.setStartDate2(sdf.format(startDate));
        orgPackage.setEndDate2(sdf.format(endDate));
        orgPackage.setOrgStatus(orgStatus);
        mmap.put("assetsInfoPackage", assetsInfoPackage);
        mmap.put("orgPackage", orgPackage);

        return prefix + "/details";
    }

    /**
     * 查询客户资产列表
     */
    @RequiresPermissions("assetspackage:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AssetsInfoPackage assetsInfoPackage) {
        //如果参数有委托方则用参数的，如果没有则用当前用户的
        if (assetsInfoPackage.getOrgId() == null || "".equals(assetsInfoPackage.getOrgId()) || "请选择".equals(assetsInfoPackage.getOrgId())) {
            // 根据登录用户id获取机构id
            String orgid = orgPackageService.selectOrgIdByUserId(ShiroUtils.getUserId());
            assetsInfoPackage.setOrgId(orgid);
        }
        startPage();
        List<AssetsInfoPackage> list = assetsInfoService.selectCurAssetsPackageList(assetsInfoPackage);
        return getDataTable(list);
    }

    /**
     * 查询当前用户拥有的数据权限与委托方的交集
     *
     * @param request
     * @return
     */
//    @RequiresPermissions("assetspackage:info:list")
    @PostMapping("/findOrgInfo")
    @ResponseBody
    public AjaxResult findOrgInfo(HttpServletRequest request) {
//        List<Map<String, Long>> orgInfo = null;
        /*Long UserId = ShiroUtils.getUserId();
        String dataScope = orgPackageService.selectDataScopeByUserId(UserId);
        if ("1".equals(dataScope)) {
            orgInfo = orgPackageService.selectOrgInfoByUserId1(UserId);
        } else if ("2".equals(dataScope)) {
            orgInfo = orgPackageService.selectOrgInfoByUserId2(UserId);
        } else if ("4".equals(dataScope)) {
            orgInfo = orgPackageService.selectOrgInfoByUserId4(UserId);
        } else {
            orgInfo = orgPackageService.selectOrgInfoByUserId3(UserId);
        }*/
        List<Map<String, Object>> orgInfo = this.orgPackageService.findOrgInfo();
        String result = JSON.toJSONString(orgInfo);
        return AjaxResult.success(result);
    }

    /**
     * 查询客户资产还款列表
     */
    @PostMapping("/repaymentList")
    @ResponseBody
    public TableDataInfo repaymentList(String orgCasno) {
        startPage();
        List<CurAssetsRepaymentPackage> list = assetsInfoService.selectCurAssetsRepaymentPackageByOrgCasNo(orgCasno);
        for (CurAssetsRepaymentPackage assetsRepayment : list) {
            String jazt = assetsRepayment.getJazt();
            if (jazt != null && IsCloseCaseEnum.CLOSE_CASE.getValue().equals(jazt)) {
                assetsRepayment.setJazt("已结案");
            } else {
                assetsRepayment.setJazt("未结案");
            }
        }
        return getDataTable(list);
    }


}
