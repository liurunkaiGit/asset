package com.ruoyi.assetspackage.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.assetspackage.service.IRepaymentTemplateRelationPackageService;
import com.ruoyi.assetspackage.util.ParseExcelUtil;
import com.ruoyi.assetspackage.util.dataHeadMappingUtil;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.assetspackage.service.IRepaymentTemplatesPackageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 还款模板管理Controller
 *
 * @author guozeqi
 * @date 2020-01-13
 */
@Controller
@RequestMapping("/assetspackage/repaymentTemplate")
public class RepaymentTemplatesPackageController extends BaseController {
    private String prefix = "assetspackage/repaymentTemplate";

    @Autowired
    private IRepaymentTemplatesPackageService repaymentTemplatesPackageService;

    @Autowired
    private IOrgPackageService orgPackageService;

    @Autowired
    private IRepaymentTemplateRelationPackageService repaymentTemplateRelationPackageService;

    @Autowired
    private RepaymentSystemHeadMapping repaymentSystemHeadMapping;


    @RequiresPermissions("assetspackage:repaymentTemplate:view")
    @GetMapping()
    public String repaymentTemplate() {
        return prefix + "/repaymentTemplate";
    }

    /**
     * 查询还款模板管理列表
     */
    @RequiresPermissions("assetspackage:repaymentTemplate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RepaymentTemplatesPackage repaymentTemplatesPackage) {
        startPage();
        List<RepaymentTemplatesPackage> list = repaymentTemplatesPackageService.selectRepaymentTemplatesPackageList(repaymentTemplatesPackage);
        return getDataTable(list);
    }

    /**
     * 导出还款模板管理列表
     */
    @RequiresPermissions("assetspackage:repaymentTemplate:export")
    @Log(title = "还款模板管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RepaymentTemplatesPackage repaymentTemplatesPackage) {
        List<RepaymentTemplatesPackage> list = repaymentTemplatesPackageService.selectRepaymentTemplatesPackageList(repaymentTemplatesPackage);
        ExcelUtil<RepaymentTemplatesPackage> util = new ExcelUtil<RepaymentTemplatesPackage>(RepaymentTemplatesPackage.class);
        return util.exportExcel(list, "assetspackage");
    }

    /**
     * 新增还款模板管理
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存还款模板管理
     */
    @RequiresPermissions("assetspackage:repaymentTemplate:add")
    @Log(title = "还款模板管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RepaymentTemplatesPackage repaymentTemplatesPackage) {
        return toAjax(repaymentTemplatesPackageService.insertRepaymentTemplatesPackage(repaymentTemplatesPackage));
    }

    /**
     * 修改还款模板管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        RepaymentTemplatesPackage repaymentTemplatesPackage = repaymentTemplatesPackageService.selectRepaymentTemplatesPackageById(id);
        mmap.put("repaymentTemplatesPackage", repaymentTemplatesPackage);
        return prefix + "/edit";
    }

    /**
     * 修改保存还款模板管理
     */
    @RequiresPermissions("assetspackage:repaymentTemplate:edit")
    @Log(title = "还款模板管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RepaymentTemplatesPackage repaymentTemplatesPackage) {
        return toAjax(repaymentTemplatesPackageService.updateRepaymentTemplatesPackage(repaymentTemplatesPackage));
    }

    /**
     * 删除还款模板管理
     */
    @RequiresPermissions("assetspackage:repaymentTemplate:remove")
    @Log(title = "还款模板管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(repaymentTemplatesPackageService.deleteRepaymentTemplatesPackageByIds(ids));
    }


    @PostMapping("/fileUpload")
    @ResponseBody
    @Log(title = "模板上传")
    public AjaxResult fileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        InputStream stream = null;
        String fileName = "";
        String fileNameFull = "";
        try {
            String uploadDir = new String("/usr/local/temp/repaymentTemplate/");
            File dirPath = new File(uploadDir);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            if (file != null) {
                stream = file.getInputStream();
                fileName = file.getOriginalFilename();
                String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
                if ("xls".equals(extension) || "xlsx".equals(extension)) {
                    //组装文件路径写入磁盘
                    fileNameFull = uploadDir + fileName;
                    OutputStream bos = new FileOutputStream(fileNameFull);
                    int bytesRead = 0;
                    byte[] buffer = new byte[8192];
                    while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                        bos.write(buffer, 0, bytesRead);
                    }
                    bos.close();
                    stream.close();
                    //存入数据库中
                    String orgid = orgPackageService.selectOrgIdByUserId(ShiroUtils.getUserId());//委托方id
                    RepaymentTemplatesPackage param = new RepaymentTemplatesPackage();
                    String uuid = UUID.randomUUID().toString().replace("-", "");
                    String loginName = ShiroUtils.getLoginName();
                    param.setId(uuid);
                    param.setName(fileName);
                    param.setUrl(fileNameFull);
                    param.setOrgId(orgid);
                    param.setCreateBy(loginName);
                    param.setCreateTime(new Date());
                    //根据模板名称查询模板id
                    RepaymentTemplatesPackage repaymentTemplatesPackage = this.repaymentTemplatesPackageService.selectTemplateByTemplateName(fileName);
                    if (repaymentTemplatesPackage != null) {
                        String templateid = repaymentTemplatesPackage.getId();
                        param.setId(templateid);
                        this.repaymentTemplatesPackageService.updateRepaymentTemplatesPackage(param);//更新
                    } else {
                        this.repaymentTemplatesPackageService.insertRepaymentTemplatesPackage(param);//插入
                    }

                } else {
                    return error("您上传的不是Excel文件，请正确上传Excel数据文件");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return error("模板上传失败：" + e.getMessage());
        }
        return success();
    }


    /**
     * 跳转模板匹配页
     *
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/matching/{id}")
    public String matching(@PathVariable("id") String id, ModelMap mmap) {
        RepaymentTemplatesPackage repaymentTemplates = repaymentTemplatesPackageService.selectRepaymentTemplatesPackageById(id);
        mmap.put("repaymentTemplates", repaymentTemplates);
        return prefix + "/matching";
    }

    /**
     * 跳转关系查看页
     *
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/look/{id}")
    public String lookUp(@PathVariable("id") String id, ModelMap mmap) {
        RepaymentTemplatesPackage repaymentTemplates = repaymentTemplatesPackageService.selectRepaymentTemplatesPackageById(id);
        mmap.put("repaymentTemplates", repaymentTemplates);
        return prefix + "/lookUp";
    }

    /**
     * 解析模板头信息
     *
     * @param request
     * @param url
     * @return
     */
    @PostMapping("/paseHead")
    @ResponseBody
    public AjaxResult parseHead(HttpServletRequest request, String url) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> templateHead = null;
        List<String> systemHead = null;
        try {
            templateHead = ParseExcelUtil.resolveExcelHead(url);//模板头
            systemHead = dataHeadMappingUtil.getMappingData(repaymentSystemHeadMapping);//配置文件映射的头
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("templateHead", templateHead);
        map.put("systemHead", systemHead);
        String result = JSON.toJSONString(map);
        return AjaxResult.success(result);
    }

    /**
     * 添加模板对应关系
     *
     * @param request
     * @param id(模板id)
     * @return
     */
    @PostMapping("/addRelation")
    @ResponseBody
    public AjaxResult addRelation(HttpServletRequest request, String id) {
        //更新模板表该模板的表头行、数据起始行
        RepaymentTemplatesPackage updateparam = new RepaymentTemplatesPackage();
        updateparam.setHeadRowNum(request.getParameter("表头行"));
        updateparam.setDataRowNum(request.getParameter("数据起始行"));
        updateparam.setId(id);
        repaymentTemplatesPackageService.updateRepaymentTemplatesPackage(updateparam);

        //清空当前模板的所有匹配关系
        repaymentTemplateRelationPackageService.deleteRepaymentTemplateRelationPackageByTemplateId(id);
        List<String> sysTbHeadList = dataHeadMappingUtil.getMappingData(repaymentSystemHeadMapping);
        for (String head : sysTbHeadList) {
            RepaymentTemplateRelationPackage param = new RepaymentTemplateRelationPackage();
            String macthhead = request.getParameter(head + "macth");
            if (macthhead != null && !"".equals(macthhead) && !"请选择".equals(macthhead)) {
                param.setSystemTemplateName(head);
                param.setCustomerTemplateName(macthhead);
            } else {
                param.setSystemTemplateName(head);
            }
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String loginName = ShiroUtils.getLoginName();
            param.setId(uuid);
            param.setTemplateId(id);
            param.setCreateBy(loginName);
            param.setCreateTime(new Date());
            /** 根据登录用户id获取机构id */
            String orgid = orgPackageService.selectOrgIdByUserId(ShiroUtils.getUserId());
            param.setOrgId(orgid);

            //保存模板关系表中
            repaymentTemplateRelationPackageService.insertRepaymentTemplateRelationPackage(param);
        }
        return AjaxResult.success();
    }


    /**
     * 根据模板id查询匹配关系
     *
     * @param request
     * @param templateId
     * @return
     */
    @PostMapping("/findRelations")
    @ResponseBody
    public AjaxResult findRelations(HttpServletRequest request, String templateId, String templateUrl) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            //根据模板id查询模板表头映射关系
            List<RepaymentTemplateRelationPackage> relations = repaymentTemplateRelationPackageService.selectRepaymentTemplateRelationPackageListBytemplateId(templateId);
            for (RepaymentTemplateRelationPackage relation : relations) {
                String customerTemplateName = relation.getCustomerTemplateName();
                String systemTemplateName = relation.getSystemTemplateName();
                if (customerTemplateName != null && !"".equals(customerTemplateName)) {
                    map.put(customerTemplateName, systemTemplateName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = JSON.toJSONString(map);
        return AjaxResult.success(result);
    }


}
