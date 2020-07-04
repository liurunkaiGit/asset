package com.ruoyi.assetspackage.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.*;
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
import com.ruoyi.framework.util.DataPermissionUtils;
import com.ruoyi.framework.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 资产包Controller
 *
 * @author guozeqi
 * @date 2020-01-06
 */
@Slf4j
@Controller
@RequestMapping("/assetspackage/package")
public class AssetPackageController extends BaseController {
    private String prefix = "assetspackage/package";

    @Autowired
    private IAssetPackageService assetPackageService;

    @Autowired
    private ICurAssetsPackageService curAssetsPackageService;

    @Autowired
    private ITemplatesPackageService templatesPackageService;

    @Autowired
    private IAssetsConnectPackageService assetsConnectPackageService;

    @Autowired
    private IAssetsConnectHisPackageService assetsConnectHisPackageService;

    @Autowired
    private IOrgPackageService orgPackageService;

    @Autowired
    RemoteConfigure remoteConfigure;

    @Autowired
    private DataPermissionUtils dataPermissionUtils;


    @RequiresPermissions("assetspackage:package:view")
    @GetMapping()
    public String assetspackage() {
        return prefix + "/packageView";
    }

    /**
     * 查询资产包列表
     */
    @RequiresPermissions("assetspackage:package:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AssetPackage assetPackage) {
        /*Set<Long> depts = dataPermissionUtils.selectDataPer();
        assetPackage.setDeptIds(depts);*/
        String orgId = String.valueOf(ShiroUtils.getSysUser().getOrgId());
        assetPackage.setStartOrgId(orgId);
        startPage();
        List<AssetPackage> list = assetPackageService.selectAssetPackageList(assetPackage);
        return getDataTable(list);
    }

    /**
     * 导出资产包列表
     */
    @RequiresPermissions("assetspackage:package:export")
    @Log(title = "资产包", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AssetPackage assetPackage) {
        List<AssetPackage> list = assetPackageService.selectAssetPackageList(assetPackage);
        ExcelUtil<AssetPackage> util = new ExcelUtil<AssetPackage>(AssetPackage.class);
        return util.exportExcel(list, "package");
    }

    /**
     * 新增资产包
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存资产包
     */
    @RequiresPermissions("assetspackage:package:add")
    @Log(title = "资产包", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AssetPackage assetPackage) {
        String orgId = String.valueOf(ShiroUtils.getSysUser().getOrgId());
        String uuid = UUID.randomUUID().toString().replace("-", "");
        assetPackage.setId(uuid);
        assetPackage.setCreateBy(ShiroUtils.getLoginName());
        assetPackage.setCreateTime(new Date());
        assetPackage.setIsNull(IsNullPackageEnum.NULL_PACKAGE.getValue());
        assetPackage.setIsClose(IsClosePackageEnum.NOT_CLOSE_PACKAGE.getValue());
        assetPackage.setCreateBy(ShiroUtils.getLoginName());
        assetPackage.setStartOrgId(orgId);
        return toAjax(assetPackageService.insertAssetPackage(assetPackage));
    }

    /**
     * 查询当前用户拥有的数据权限与委托方的交集
     *
     * @param request
     * @return
     */
    @RequiresPermissions("assetspackage:package:add")
    @PostMapping("/findOrgInfo")
    @ResponseBody
    public AjaxResult findOrgInfo(HttpServletRequest request) {
        List<Map<String, Object>> orgInfo = this.orgPackageService.findOrgInfo();
        String result = JSON.toJSONString(orgInfo);
        return AjaxResult.success(result);
    }

    /**
     * 修改资产包
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AssetPackage assetPackage = assetPackageService.selectAssetPackageById(id);
//        OrgPackage orgPackage = orgPackageService.selectOrgPackageById(assetPackage.getStartOrgId());//查询机构名称
        OrgPackage aPackage = new OrgPackage();
        aPackage.setDeptId(Long.valueOf(assetPackage.getStartOrgId()));
        OrgPackage orgPackage = this.orgPackageService.selectOrgPackageList(aPackage).get(0);
        assetPackage.setStartOrgName(orgPackage.getOrgName());
        mmap.put("assetPackage", assetPackage);
        return prefix + "/edit";
    }

    /**
     * 修改保存资产包
     */
    @RequiresPermissions("assetspackage:package:edit")
    @Log(title = "资产包", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AssetPackage assetPackage) {
        String orgId = String.valueOf(ShiroUtils.getSysUser().getOrgId());
        assetPackage.setStartOrgId(orgId);
        assetPackage.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(assetPackageService.updateAssetPackage(assetPackage));
    }

    /**
     * 删除资产包
     */
    @RequiresPermissions("assetspackage:package:remove")
    @Log(title = "资产包", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(assetPackageService.deleteAssetPackageByIds(ids));
    }

    /**
     * 删除资产包
     */
    @Log(title = "资产包", businessType = BusinessType.DELETE)
    @GetMapping("/batchDel")
    @ResponseBody
    public AjaxResult batchDel(String packageIds) {
        this.assetPackageService.batchDel(packageIds);
        return AjaxResult.success();
    }

    /**
     * 拆包
     */
    @Log(title = "资产包", businessType = BusinessType.DELETE)
    @GetMapping("/dismantlePack")
    @ResponseBody
    public AjaxResult dismantlePack(String packageIds) {
        this.assetPackageService.dismantlePack(packageIds);
        return AjaxResult.success();
    }


    /**
     * 组包窗口
     */
    @RequiresPermissions("assetspackage:package:assemble")
    @GetMapping("/assemble/{id}")
    public String assemble(@PathVariable("id") String id, ModelMap mmap) {
        AssetPackage assetPackage = assetPackageService.selectAssetPackageById(id);
        OrgPackage orgPackage = new OrgPackage();
        orgPackage.setDeptId(Long.valueOf(assetPackage.getStartOrgId()));
        assetPackage.setStartOrgName(this.orgPackageService.selectOrgPackageList(orgPackage).get(0).getOrgName());
        mmap.put("assetPackage", assetPackage);
        List<Map<String, Object>> orgInfo = this.orgPackageService.findOrgInfo();
        mmap.put("orgId", orgInfo.get(0).get("orgId"));
        return prefix + "/assemble";
    }


    /**
     * 查找登录用户所属委托方未打包的资产
     *
     * @param request
     * @return
     */
    @PostMapping("/findAssets")
    @ResponseBody
    public TableDataInfo findAssets(HttpServletRequest request, CurAssetsPackage curAssetsPackage) {
        curAssetsPackage.setPackageFlag(PackageFlagEnum.NOT_PACKAGE.getValue());
        curAssetsPackage.setOrgId(String.valueOf(ShiroUtils.getSysUser().getOrgId()));
        startPage();
        List<CurAssetsPackage> curAssetsPackageList = curAssetsPackageService.selectCurAssetsPackageList(curAssetsPackage);
        return getDataTable(curAssetsPackageList);
    }

    /**
     * 分配资产
     *
     * @param request
     * @param packageId
     * @param assetsId
     * @return
     */
    @PostMapping("/distribution")
    @ResponseBody
    @Transactional
    public AjaxResult distribution(HttpServletRequest request, String packageId, String assetsId) {
        this.assetPackageService.distribution(packageId,assetsId);
        return AjaxResult.success(AjaxResult.Type.SUCCESS,"打包成功",null);
    }

    /**
     * 将所查询案件全部打包
     *
     * @return
     */
    @PostMapping("/assembleAll")
    @ResponseBody
    public AjaxResult assembleAll(CurAssetsPackage curAssetsPackage, String packageId) {
        this.assetPackageService.assembleAll(curAssetsPackage,packageId);
        return AjaxResult.success(AjaxResult.Type.SUCCESS,"打包成功",null);
    }


//    /**
//     * 打包资产
//     *
//     * @param request
//     * @param packageId
//     * @return
//     */
//    @RequiresPermissions("assetspackage:package:assemble")//组包权限
//    @PostMapping("/packaging")
//    @ResponseBody
//    public AjaxResult packaging(HttpServletRequest request, String packageId) {
//        //查询资产包是否为空包
//        AssetPackage assetPackage = assetPackageService.selectAssetPackageById(packageId);
//        if (IsNullPackageEnum.NOT_NULL_PACKAGE.getValue().equals(assetPackage.getIsNull())) {
//            //更新资产包
//            AssetPackage updateParam1 = new AssetPackage();
//            updateParam1.setIsClose(IsClosePackageEnum.CLOSE_PACKAGE.getValue());
//            updateParam1.setId(packageId);
//            assetPackageService.updateAssetPackage(updateParam1);
//            //查询该资产包的所有资产
//            List<CurAssetsPackage> curAssetsList = curAssetsPackageService.selectCurAssetsByPackageId(packageId);
//            for (CurAssetsPackage curAssets : curAssetsList) {
//                //更新资产为已打包状态
//                curAssets.setPackageFlag(PackageFlagEnum.IS_PACKAGE.getValue());
//                curAssetsPackageService.updateCurAssetsPackage(curAssets);
//
//            }
//
//        } else {
//            return AjaxResult.success("当前资产包为空,请选择资产！！！");
//        }
//        return AjaxResult.success("打包成功");
//    }


    /**
     * 拉回
     *
     * @param request
     * @param packageId
     * @return
     */

    @RequiresPermissions("assetspackage:package:assemble")//组包权限
    @PostMapping("/unPacking")
    @ResponseBody
    public AjaxResult unPacking(HttpServletRequest request, String packageId) {
        //查询该资产包是否已经打包
        AssetPackage assetPackage = assetPackageService.selectAssetPackageById(packageId);
        if (IsClosePackageEnum.CLOSE_PACKAGE.getValue().equals(assetPackage.getIsClose()) || IsClosePackageEnum.SEND_PACKAGE.getValue().equals(assetPackage.getIsClose())) {
            //查询该资产包中的所有资产
            CurAssetsPackage queryParam = new CurAssetsPackage();
            queryParam.setPackageId(packageId);
            List<CurAssetsPackage> curAssetsList = this.assetPackageService.selectCurAssetsList(queryParam);
            //更新资产为未打包状态
            Map<String,String> param = new HashMap<String,String>();
            param.put("packageId",packageId);
            param.put("packageFlag",PackageFlagEnum.NOT_PACKAGE.getValue());
            this.assetPackageService.updateCurassets2(param);
            //更新资产包
            AssetPackage updateParam2 = new AssetPackage();
            updateParam2.setIsClose(IsClosePackageEnum.PULL_BACK.getValue());
            updateParam2.setId(packageId);
            assetPackageService.updateAssetPackage(updateParam2);
        } else {
            return AjaxResult.success("当前资产包未打包,未分发,不需要拉回！！！");
        }
        return AjaxResult.success("拉回成功");
    }


    /**
     * 跳转分发页面
     *
     * @param packageId
     * @param mmap
     * @return
     */
    @RequiresPermissions("assetspackage:package:assemble")//组包权限
    @GetMapping("/packageSend/{id}")
    public String packageSend(@PathVariable("id") String packageId, ModelMap mmap) {
        AssetPackage assetPackage = assetPackageService.selectAssetPackageById(packageId);
        mmap.put("assetPackage", assetPackage);
        return prefix + "/packageSend";
    }


    /**
     * 查询所有机构
     *
     * @param request
     * @return
     */
    @RequiresPermissions("assetspackage:package:assemble")//组包权限
    @PostMapping("/findOrg")
    @ResponseBody
    public AjaxResult findOrg(HttpServletRequest request) {
        //List<OrgPackage> orgPackages = orgPackageService.selectOrgPackageList(new OrgPackage());
        List<Map<String, Long>> orgInfo = null;
        Long UserId = ShiroUtils.getUserId();
        String dataScope = orgPackageService.selectDataScopeByUserId(UserId);
        if ("1".equals(dataScope)) {
            orgInfo = orgPackageService.selectOrgInfoByUserId1(UserId);
        } else if ("2".equals(dataScope)) {
            orgInfo = orgPackageService.selectOrgInfoByUserId2(UserId);
        } else if ("4".equals(dataScope)) {
            orgInfo = orgPackageService.selectOrgInfoByUserId4(UserId);
        } else {
            orgInfo = orgPackageService.selectOrgInfoByUserId3(UserId);
        }
        String result = JSON.toJSONString(orgInfo);
        return AjaxResult.success(result);
    }


    /**
     * 分发保存
     *
     * @param request
     * @param packageId
     * @param orgId
     * @return
     */
    @RequiresPermissions("assetspackage:package:assemble")//组包权限
    @PostMapping("/sendSave")
    @ResponseBody
    public AjaxResult sendSave(HttpServletRequest request, @RequestParam("id") String packageId, @RequestParam("org") String orgId) {
        return assetPackageService.updateAssetPackage(packageId, orgId);
    }

    /**
     * 跳转详情页面
     * @param packageId
     * @param mmap
     * @return
     */
    @GetMapping("/packageDetil/{id}")
    public String packageDetil(@PathVariable("id") String packageId, ModelMap mmap) {
//        AssetPackage assetPackage = assetPackageService.selectAssetPackageById(packageId);
        mmap.put("id", packageId);
        return prefix + "/packageDetail";
    }


    @PostMapping("/findDetailAsset")
    @ResponseBody
    public TableDataInfo findDetailAsset(String packageId) {
        CurAssetsPackage param = new CurAssetsPackage();
        param.setPackageId(packageId);
        startPage();
        List<CurAssetsPackage> list = assetPackageService.selectCurAssetsList(param);
        return getDataTable(list);
    }

}
