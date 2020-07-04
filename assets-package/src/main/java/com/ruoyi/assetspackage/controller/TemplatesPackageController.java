package com.ruoyi.assetspackage.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.enums.ImportTypeEnum;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.assetspackage.service.ITemplateRelationPackageService;
import com.ruoyi.assetspackage.service.ITemplatesPackageService;
import com.ruoyi.assetspackage.util.FastDFSUtil;
import com.ruoyi.assetspackage.util.ParseExcelUtil;
import com.ruoyi.assetspackage.util.dataHeadMappingUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * 模板管理Controller
 *
 * @author guozeqi
 * @date 2020-01-02
 */
@Controller
@RequestMapping("/assetspackage/template")
public class TemplatesPackageController extends BaseController {
    private String prefix = "assetspackage/template";
    private String repaymentPrefix = "assetspackage/repaymentTemplate";

    @Autowired
    private ITemplatesPackageService templatesPackageService;

    @Autowired
    private ITemplateRelationPackageService templateRelationPackageService;

    @Autowired
    private IOrgPackageService orgPackageService;

    @Autowired
    private SystemHeadMapping systemHeadMapping;

    @Autowired
    private RepaymentSystemHeadMapping repaymentSystemHeadMapping;

    @Autowired
    private RecordSystemHeadMapping recordSystemHeadMapping;


    @RequiresPermissions("assetspackage:template:view")
    @GetMapping()
    public String assetspackage() {
        return prefix + "/template";
    }


    /**
     * 查询模板管理列表
     */
    @RequiresPermissions("assetspackage:template:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TemplatesPackage templatesPackage) {
        startPage();
        List<TemplatesPackage> list = templatesPackageService.selectTemplatesPackageList(templatesPackage);
        for (TemplatesPackage template : list) {
            String type = template.getType();
            if ("1".equals(type)) {
                template.setType("资产模板");
            }
            if ("2".equals(type)) {
                template.setType("还款模板");
            }
            if ("3".equals(type)) {
                template.setType("催收记录模板");
            }
            if("4".equals(type)){
                template.setType("更新模板");
            }
        }
        return getDataTable(list);
    }

    /**
     * 查询当前用户拥有的数据权限与委托方的交集
     *
     * @param request
     * @return
     */
    @RequiresPermissions("assetspackage:template:list")
    @PostMapping("/findOrgInfo")
    @ResponseBody
    public AjaxResult findOrgInfo(HttpServletRequest request) {
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
     * 导出模板管理列表
     */
    @RequiresPermissions("assetspackage:template:export")
    @Log(title = "模板管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TemplatesPackage templatesPackage) {
        List<TemplatesPackage> list = templatesPackageService.selectTemplatesPackageList(templatesPackage);
        ExcelUtil<TemplatesPackage> util = new ExcelUtil<TemplatesPackage>(TemplatesPackage.class);
        return util.exportExcel(list, "package");
    }

    /**
     * 新增模板管理
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存模板管理
     */
    @RequiresPermissions("assetspackage:template:add")
    @Log(title = "模板管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TemplatesPackage templatesPackage) {
        return toAjax(templatesPackageService.insertTemplatesPackage(templatesPackage));
    }

    /**
     * 修改模板管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        TemplatesPackage templatesPackage = templatesPackageService.selectTemplatesPackageById(id);
        mmap.put("templatesPackage", templatesPackage);
        return prefix + "/edit";
    }

    /**
     * 修改保存模板管理
     */
    @RequiresPermissions("assetspackage:template:edit")
    @Log(title = "模板管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TemplatesPackage templatesPackage) {
        return toAjax(templatesPackageService.updateTemplatesPackage(templatesPackage));
    }

    /**
     * 删除模板管理
     */
    @RequiresPermissions("assetspackage:template:remove")
    @Log(title = "模板管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        templatesPackageService.deleteTemplatesPackageByIds(ids);
        return AjaxResult.success();
    }

    /**
     * 模板上传不使用fastdfs
     *
     * @param request
     * @param file
     * @param orgId
     * @param orgName
     * @param type
     * @return
     */
    @RequiresPermissions("assetspackage:template:upload")
    @PostMapping("/fileUpload")
    @ResponseBody
    @Log(title = "模板上传")
    public AjaxResult fileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file, String orgId, String orgName, String type) {
        this.templatesPackageService.fileUpload(request, file, orgId, orgName, type);
        return AjaxResult.success();
    }


//    @RequiresPermissions("assetspackage:template:upload")
//    @PostMapping("/fileUpload")
//    @ResponseBody
//    @Log(title = "模板上传")
//    public AjaxResult fileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file, String orgId, String orgName, String type) {
//        logger.info("============模板上传开始==================");
//        InputStream stream = null;
//        String fileName = "";
//        String fileNameFull = "";
//        try {
//            String uploadDir = new String("/usr/local/temp/templates/");
//            File dirPath = new File(uploadDir);
//            if (!dirPath.exists()) {
//                dirPath.mkdirs();
//            }
//            if (file != null) {
//                stream = file.getInputStream();
//                fileName = file.getOriginalFilename();
//                String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
//                if ("xls".equals(extension) || "xlsx".equals(extension)) {
//                    //组装文件路径写入磁盘
//                    fileNameFull = uploadDir + fileName;
//                    OutputStream bos = new FileOutputStream(fileNameFull);
//                    int bytesRead = 0;
//                    byte[] buffer = new byte[8192];
//                    while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
//                        bos.write(buffer, 0, bytesRead);
//                    }
//                    bos.close();
//                    stream.close();
//                    //上传fastDFS
//                    logger.info("============FastDFS开始==================");
//                    String url = FastDFSUtil.UploadFile(fileNameFull);
//                    logger.info("============FastDFSUrl=================url=" + url);
//                    //存入数据库中
//                    // String orgid = templatesPackageService.selectOrgIdByUserId(ShiroUtils.getUserId());//委托方id
//                    TemplatesPackage param = new TemplatesPackage();
//                    String uuid = UUID.randomUUID().toString().replace("-", "");
//                    String loginName = ShiroUtils.getLoginName();
//                    param.setId(uuid);
//                    param.setName(fileName);
//                    param.setUrl(url);
//                    param.setType(type);
//                    param.setOrgId(orgId);
//                    param.setOrgName(orgName);
//                    param.setDelflag("0");
//                    param.setCreateBy(loginName);
//                    param.setCreateTime(new Date());
//                    //根据模板名称查询模板id
//                    TemplatesPackage templatesPackage = this.templatesPackageService.selectTemplateByTemplateName(fileName, type);
//                    if (templatesPackage != null) {
//                        String templateid = templatesPackage.getId();
//                        param.setId(templateid);
//                        this.templatesPackageService.updateTemplatesPackage(param);//更新
//                    } else {
//                        this.templatesPackageService.insertTemplatesPackage(param);//插入
//                    }
//
//                } else {
//                    return error("您上传的不是Excel文件，请正确上传Excel数据文件");
//                }
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return error("模板上传失败：" + e.getMessage());
//        }
//        return success();
//    }

    /**
     * 跳转模板匹配页
     *
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("assetspackage:template:upload")
    @GetMapping("/matching/{id}")
    public String matching(@PathVariable("id") String id, ModelMap mmap) {
        TemplatesPackage templatesPackage = templatesPackageService.selectTemplatesPackageById(id);
        mmap.put("templatesPackage", templatesPackage);
        /*if (ImportTypeEnum.REPAYMENT_TEMPLETE.getCode().toString().equals(templatesPackage.getType())) {
            return repaymentPrefix + "/matching";
        }*/
        return prefix + "/matching";
    }

    @RequiresPermissions("assetspackage:template:upload")
    @GetMapping("/tempelateMatching/{id}")
    public String tempelateMatching(@PathVariable("id") String id, ModelMap mmap) {
        TemplatesPackage templatesPackage = templatesPackageService.selectTemplatesPackageById(id);
        mmap.put("templatesPackage", templatesPackage);
        return prefix + "/tempelateMatching";
    }

    /**
     * 跳转关系查看页
     *
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("assetspackage:template:upload")
    @GetMapping("/look/{id}")
    public String lookUp(@PathVariable("id") String id, ModelMap mmap) {
        TemplatesPackage templatesPackage = templatesPackageService.selectTemplatesPackageById(id);
        mmap.put("templatesPackage", templatesPackage);
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
    public AjaxResult parseHead(HttpServletRequest request, HttpServletResponse response, String url, String templateId) {
        String result = this.templatesPackageService.parseHead(request,response,url,templateId);
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
        try {
            return templatesPackageService.addRelation(request, id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加关系失败{}",e);
            return error("添加关系失败");
        }

    }

    /**
     * 添加模板对应关系
     *
     * @param request
     * @param id(模板id)
     * @return
     */
    @PostMapping("/addRelation2")
    @ResponseBody
    public AjaxResult addRelation2(HttpServletRequest request, String id) {
        try {
            return templatesPackageService.addRelation2(request, id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加关系失败{}",e);
            return error("添加关系失败");
        }

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
            List<TemplateRelationPackage> relations = templateRelationPackageService.selectTemplateRelationPackageListBytemplateId(templateId);
            for (TemplateRelationPackage relation : relations) {
                String customerTemplateName = relation.getCustomerTemplateName();
                String systemTemplateName = relation.getSystemTemplateName();
                if (customerTemplateName != null && !"".equals(customerTemplateName)) {
                    map.put(customerTemplateName, systemTemplateName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询匹配关系失败{}",e);
        }
        String result = JSON.toJSONString(map);
        return AjaxResult.success(result);
    }

    @RequiresPermissions("assetspackage:template:upload")
    @GetMapping("/downLoad")
    @ResponseBody
    @Log(title = "模板下载")
    public void downLoad(HttpServletRequest request, HttpServletResponse response, String templateId) {
        this.templatesPackageService.downLoad(request, response, templateId);
    }

//    @RequiresPermissions("assetspackage:template:upload")
//    @GetMapping("/downLoad")
//    @ResponseBody
//    @Log(title = "模板下载")
//    public void downLoad(HttpServletRequest request, HttpServletResponse response, String templateId) {
//        try {
//            //根据模板id查询模板信息
//            TemplatesPackage templatesInfo = templatesPackageService.selectTemplatesPackageById(templateId);
//            if (templatesInfo != null) {
//                String url = templatesInfo.getUrl();
//                String name = templatesInfo.getName();
//                response.reset();
//                response.setContentType("application/x-excel");
//                response.setCharacterEncoding("UTF-8");
//                response.setHeader("Content-Disposition", "attachment; filename="
//                        + new String(name.getBytes("gb2312"), "iso-8859-1"));
//
//                OutputStream outStream = response.getOutputStream();
//                byte[] bytes = FastDFSUtil.DownloadFile(url);
//                ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
//                byte[] buffer = new byte[1024];
//                int count = -1;
//                while ((count = inputStream.read(buffer)) != -1) {
//                    outStream.write(buffer, 0, count);
//
//                }
//                outStream.flush();
//                outStream.close();
//                inputStream.close();
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            logger.error("执行方法downLoad异常：", e);
//        } catch (MyException e) {
//            e.printStackTrace();
//        }
//
//    }

    private Map<String, String> findRelations2(String templateId) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            //根据模板id查询模板表头映射关系
            List<TemplateRelationPackage> relations = templateRelationPackageService.selectTemplateRelationPackageListBytemplateId(templateId);
            for (TemplateRelationPackage relation : relations) {
                String customerTemplateName = relation.getCustomerTemplateName();
                String systemTemplateName = relation.getSystemTemplateName();
                if (customerTemplateName != null && !"".equals(customerTemplateName)) {
                    map.put(systemTemplateName, customerTemplateName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }



}
