package com.ruoyi.assetspackage.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.enums.AllocatRuleEnum;
import com.ruoyi.assetspackage.enums.IsNoEnum;
import com.ruoyi.assetspackage.service.IFileAccessoriesPackageService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysDictDataService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 机构Controller
 *
 * @author guozeqi
 * @date 2019-12-27
 */
@Controller
@RequestMapping("/assetspackage/org")
public class OrgPackageController extends BaseController {
    private String prefix = "assetspackage/org";

    @Autowired
    private IOrgPackageService orgPackageService;

    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysDictDataService dictDataService;
    @Autowired
    private IFileAccessoriesPackageService fileAccessoriesPackageService;


    @RequiresPermissions("assetspackage:org:view")
    @GetMapping()
    public String assetspackage() {
        return prefix + "/assetsOrg";
    }

    /**
     * 查询机构列表
     */
    @RequiresPermissions("assetspackage:org:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OrgPackage orgPackage) {
        startPage();
        List<OrgPackage> list = orgPackageService.selectOrgPackageList(orgPackage);
        for (OrgPackage org : list) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = org.getStartDate();
            Date endDate = org.getEndDate();
            String orgStatus = org.getOrgStatus();

            orgStatus = "1".equals(orgStatus) ? "正常" : "停用";
            org.setStartDate2(sdf.format(startDate));
            org.setEndDate2(sdf.format(endDate));
            org.setOrgStatus(orgStatus);


        }
        return getDataTable(list);
    }

    /**
     * 导出机构列表
     */
    @RequiresPermissions("assetspackage:org:export")
    @Log(title = "机构", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrgPackage orgPackage) {
        List<OrgPackage> list = orgPackageService.selectOrgPackageList(orgPackage);
        ExcelUtil<OrgPackage> util = new ExcelUtil<OrgPackage>(OrgPackage.class);
        return util.exportExcel(list, "assetOrg");
    }

    /**
     * 新增机构
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存机构
     */
    @RequiresPermissions("assetspackage:org:add")
    @Log(title = "机构", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OrgPackage orgPackage) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String loginName = ShiroUtils.getLoginName();
        orgPackage.setId(uuid);
        orgPackage.setCreateDate(new Date());
        orgPackage.setCreateBy(loginName);
        return toAjax(orgPackageService.insertOrgPackage(orgPackage));
    }

    /**
     * 修改机构
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        OrgPackage orgPackage = orgPackageService.selectOrgPackageById(id);
        mmap.put("orgPackage", orgPackage);
        return prefix + "/edit";
    }

    /**
     * 修改保存机构
     */
    @RequiresPermissions("assetspackage:org:edit")
    @Log(title = "机构", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OrgPackage orgPackage, HttpServletRequest request) {
        String allocatTaskStartegy = request.getParameter("allocatTaskStartegy");
        orgPackage.setUpdateBy(ShiroUtils.getLoginName());
        orgPackage.setUpdateDate(new Date());
        orgPackageService.updateOrgPackage(orgPackage);
        return AjaxResult.success(AjaxResult.Type.SUCCESS, "修改成功", null);
    }

    /**
     * 删除机构
     */
    @RequiresPermissions("assetspackage:org:remove")
    @Log(title = "机构", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(orgPackageService.deleteOrgPackageByIds(ids));
    }


    @PostMapping("/fileUpload")
    @ResponseBody
    @Log(title = "附件上传")
    public AjaxResult fileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        InputStream stream = null;
        String fileName = "";
        String fileNameFull = "";
        try {
            String uploadDir = new String("asset/assets-package/src/main/resources/temp");
            File dirPath = new File(uploadDir);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            if (file != null) {
                stream = file.getInputStream();
                fileName = file.getOriginalFilename();
                String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);

                //组装文件路径写入磁盘
                fileNameFull = uploadDir + File.separator + fileName;
                OutputStream bos = new FileOutputStream(fileNameFull);
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }
                bos.close();
                stream.close();


            }

        } catch (Exception e) {
            e.printStackTrace();
            return error("文件上传失败：" + e.getMessage());
        }
        return success();
    }

    /**
     * 查询所有部门
     *
     * @param request
     * @return
     */
    @PostMapping("/findDeptInfo")
    @ResponseBody
    public AjaxResult findDeptInfo(HttpServletRequest request) {
        List<SysDept> sysDepts = sysDeptService.selectDeptList(new SysDept());
        String result = JSON.toJSONString(sysDepts);
        return AjaxResult.success(result);
    }

    /**
     * 获取字典类型
     *
     * @return
     */
    @PostMapping("/selectSmsTemplate")
    @ResponseBody
    public AjaxResult selectSmsTemplate() {
        List<SysDictData> smsTemplateList = this.dictDataService.selectDictDataByType("sms_template");
        return AjaxResult.success(smsTemplateList);
    }

    /**
     * 初始化是否下拉框
     */
    @PostMapping("/initIsNo")
    @ResponseBody
    public TableDataInfo initIsNo() {
        List<ActionCodeObj> actionCodeList = Arrays.stream(IsNoEnum.values())
                .map(actionCode -> ActionCodeObj.builder().code(actionCode.getCode()).message(actionCode.getDes()).build())
                .collect(Collectors.toList());
        return getDataTable(actionCodeList);
    }

    /**
     * 初始化分案策略下拉框
     */
    @PostMapping("/initAllocatTaskStartegy")
    @ResponseBody
    public TableDataInfo initAllocatTaskStartegy() {
        List<ActionCodeObj> actionCodeList = Arrays.stream(AllocatRuleEnum.values())
                .map(actionCode -> ActionCodeObj.builder().code(actionCode.getCode()).message(actionCode.getMessage()).build())
                .collect(Collectors.toList());
        return getDataTable(actionCodeList);
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
class ActionCodeObj {
    private Integer code;
    private String message;
    private boolean flag;
}
