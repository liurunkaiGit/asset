package com.ruoyi.assetspackage.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.enums.ImportTypeEnum;
import com.ruoyi.assetspackage.mapper.TemplatesPackageMapper;
import com.ruoyi.assetspackage.service.ITemplateRelationPackageService;
import com.ruoyi.assetspackage.service.ITemplatesPackageService;
import com.ruoyi.assetspackage.util.FastDFSUtil;
import com.ruoyi.assetspackage.util.ParseExcelUtil;
import com.ruoyi.assetspackage.util.dataHeadMappingUtil;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * 模板管理Service业务层处理
 *
 * @author guozeqi
 * @date 2020-01-02
 */
@Slf4j
@Service
public class TemplatesPackageServiceImpl implements ITemplatesPackageService {

    @Autowired
    private ITemplateRelationPackageService templateRelationPackageService;
    @Autowired
    private TemplatesPackageMapper templatesPackageMapper;
    @Autowired
    private SystemHeadMapping systemHeadMapping;
    @Autowired
    private RepaymentSystemHeadMapping repaymentSystemHeadMapping;
    @Autowired
    private RecordSystemHeadMapping recordSystemHeadMapping;

    /**
     * 查询模板管理
     *
     * @param id 模板管理ID
     * @return 模板管理
     */
    @Override
    public TemplatesPackage selectTemplatesPackageById(String id) {
        return templatesPackageMapper.selectTemplatesPackageById(id);
    }

    /**
     * 查询模板管理列表
     *
     * @param templatesPackage 模板管理
     * @return 模板管理
     */
    @Override
    public List<TemplatesPackage> selectTemplatesPackageList(TemplatesPackage templatesPackage) {
        return templatesPackageMapper.selectTemplatesPackageList(templatesPackage);
    }

    /**
     * 新增模板管理
     *
     * @param templatesPackage 模板管理
     * @return 结果
     */
    @Override
    public int insertTemplatesPackage(TemplatesPackage templatesPackage) {
        templatesPackage.setCreateTime(DateUtils.getNowDate());
        return templatesPackageMapper.insertTemplatesPackage(templatesPackage);
    }

    /**
     * 修改模板管理
     *
     * @param templatesPackage 模板管理
     * @return 结果
     */
    @Override
    public int updateTemplatesPackage(TemplatesPackage templatesPackage) {
        templatesPackage.setUpdateTime(DateUtils.getNowDate());
        return templatesPackageMapper.updateTemplatesPackage(templatesPackage);
    }

    /**
     * 删除模板管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public void deleteTemplatesPackageByIds(String ids) {
        String localPath = Global.getProfile();
        Arrays.stream(ids.split(","))
                .forEach(id -> {
                    TemplatesPackage templatesPackage = selectTemplatesPackageById(id);
                    // 数据库资源地址
                    String downloadPath = localPath + StringUtils.substringAfter(templatesPackage.getUrl(), Constants.RESOURCE_PREFIX);
                    try {
                        File file = new File(downloadPath);
                        if (!file.exists()) {
                            log.error("文件不存在，文件名称是：{}", StringUtils.substringAfterLast(downloadPath, "/"));
                        } else {
                            file.delete();
                            log.error("文件删除成功，文件名称是：{}", StringUtils.substringAfterLast(downloadPath, "/"));
                        }
                        templatesPackageMapper.deleteTemplatesPackageById(id);
                    } catch (Exception e) {
                        log.error("文件删除失败，文件名称是{}",StringUtils.substringAfterLast(downloadPath, "/"));
                    }
                });
    }

    /**
     * 删除模板管理信息
     *
     * @param id 模板管理ID
     * @return 结果
     */
    @Override
    public int deleteTemplatesPackageById(String id) {
        return templatesPackageMapper.deleteTemplatesPackageById(id);
    }


    /**
     * 根据登录用户id获取机构id
     *
     * @return
     */
    @Override
    public String selectOrgIdByUserId(Long userId) {
        return templatesPackageMapper.selectOrgIdByUserId(userId);
    }

    /**
     * 根据模板名称查询模板信息
     *
     * @param templateName
     * @return
     */
    @Override
    public TemplatesPackage selectTemplateByTemplateName(String templateName, String templateType) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("templateName", templateName);
        param.put("templateType", templateType);
        return templatesPackageMapper.selectTemplateByTemplateName(param);
    }

    @Override
    public void fileUpload(HttpServletRequest request, MultipartFile file, String orgId, String orgName, String type) {
        try {
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            TemplatesPackage param = new TemplatesPackage();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String loginName = ShiroUtils.getLoginName();
            param.setId(uuid);
            param.setName(file.getOriginalFilename());
            param.setUrl(fileName);
            param.setType(type);
            param.setOrgId(orgId);
            param.setOrgName(orgName);
            param.setDelflag("0");
            param.setCreateBy(loginName);
            param.setCreateTime(new Date());
            //根据模板名称查询模板id
            TemplatesPackage templatesPackage = selectTemplateByTemplateName(fileName, type);
            if (templatesPackage != null) {
                String templateid = templatesPackage.getId();
                param.setId(templateid);
                this.templatesPackageMapper.updateTemplatesPackage(param);//更新
            } else {
                this.templatesPackageMapper.insertTemplatesPackage(param);//插入
            }
        } catch (IOException e) {
            log.error("模板上传失败，模板名称：{}，失败原因：{}", file.getOriginalFilename(), e);
            throw new RuntimeException("模板上传失败");
        }
    }

    @Override
    public void downLoad(HttpServletRequest request, HttpServletResponse response, String templateId) {
        //根据模板id查询模板信息
        TemplatesPackage templatesInfo = null;
        try {
            templatesInfo = selectTemplatesPackageById(templateId);
            // 本地资源路径
            String localPath = Global.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(templatesInfo.getUrl(), Constants.RESOURCE_PREFIX);
            // 下载名称
            String downloadName = templatesInfo.getName();
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        } catch (IOException e) {
            log.error("模板下载失败，名称是：{}，失败原因{}", templatesInfo.getName(), e);
            throw new RuntimeException("模板下载 失败");
        }
    }

    @Override
    public String parseHead(HttpServletRequest request, HttpServletResponse response, String url, String templateId) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> templateHead = null;
        List<String> systemHead = null;
        Map<String, String> relations = null;
        try {
            TemplatesPackage template = selectTemplatesPackageById(templateId);
            // 本地资源路径
            String localPath = Global.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(template.getUrl(), Constants.RESOURCE_PREFIX);
            templateHead = ParseExcelUtil.resolveExcelHead(downloadPath);//模板头
            if (ImportTypeEnum.ASSET_TEMPLETE.getCode().toString().equals(template.getType()) || ImportTypeEnum.UPDATE_TEMPLETE.getCode().toString().equals(template.getType())) {
                systemHead = dataHeadMappingUtil.getMappingData(systemHeadMapping);//配置文件映射的头
            } else if(ImportTypeEnum.REPAYMENT_TEMPLETE.getCode().toString().equals(template.getType())){
                systemHead = dataHeadMappingUtil.getMappingData(repaymentSystemHeadMapping);//配置文件映射的头
            }else{
                systemHead = dataHeadMappingUtil.getMappingData(recordSystemHeadMapping);//配置文件映射的头
            }
            if(ImportTypeEnum.UPDATE_TEMPLETE.getCode().toString().equals(template.getType())){
                relations = findRelations3(templateId);
            }else{
                relations = findRelations2(templateId);
            }

        } catch (Exception e) {
            log.error("解析模板头异常，error is {}：", e);
            throw new RuntimeException("解析模板头异常");
        }
        map.put("templateHead", templateHead);
        map.put("systemHead", systemHead);
        map.put("relations", relations);
        String result = JSON.toJSONString(map);
        return result;
    }

    @Override
    public AjaxResult addRelation(HttpServletRequest request, String id) throws Exception {
        //更新模板表该模板的表头行、数据起始行
        TemplatesPackage updateparam = new TemplatesPackage();
        updateparam.setHeadRowNum(request.getParameter("表头行"));
        updateparam.setDataRowNum(request.getParameter("数据起始行"));
        updateparam.setId(id);
        this.updateTemplatesPackage(updateparam);

        //清空当前模板的所有匹配关系
        templateRelationPackageService.deleteTemplateRelationPackageByTemplateId(id);
        TemplatesPackage templateInfo = this.selectTemplatesPackageById(id);
        List<String> sysTbHeadList = null;
        if ("1".equals(templateInfo.getType())) {
            sysTbHeadList = dataHeadMappingUtil.getMappingData(systemHeadMapping);
        } else if("2".equals(templateInfo.getType())) {
            sysTbHeadList = dataHeadMappingUtil.getMappingData(repaymentSystemHeadMapping);
        }else {
            sysTbHeadList = dataHeadMappingUtil.getMappingData(recordSystemHeadMapping);
        }
        for (String head : sysTbHeadList) {
            TemplateRelationPackage param = new TemplateRelationPackage();
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
//            TemplatesPackage templateInfo = templatesPackageService.selectTemplatesPackageById(id);
            param.setOrgId(templateInfo.getOrgId());

            //保存模板关系表中
            templateRelationPackageService.insertTemplateRelationPackage(param);
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult addRelation2(HttpServletRequest request, String id) throws Exception {
        //更新模板表该模板的表头行、数据起始行
        TemplatesPackage updateparam = new TemplatesPackage();
        updateparam.setHeadRowNum(request.getParameter("表头行"));
        updateparam.setDataRowNum(request.getParameter("数据起始行"));
        updateparam.setId(id);
        this.updateTemplatesPackage(updateparam);

        //清空当前模板的所有匹配关系
        templateRelationPackageService.deleteTemplateRelationPackageByTemplateId(id);
        TemplatesPackage templateInfo = this.selectTemplatesPackageById(id);
        List<String> templateHead = null;
        // 本地资源路径
        String localPath = Global.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(templateInfo.getUrl(), Constants.RESOURCE_PREFIX);
        templateHead = ParseExcelUtil.resolveExcelHead(downloadPath);//模板头
        for (String head : templateHead) {
            TemplateRelationPackage param = new TemplateRelationPackage();
            String macthhead = request.getParameter(head + "macth");
            if (macthhead != null && !"".equals(macthhead) && !"请选择".equals(macthhead)) {
                param.setSystemTemplateName(macthhead);
                param.setCustomerTemplateName(head);
            } else {
                param.setCustomerTemplateName(head);
            }
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String loginName = ShiroUtils.getLoginName();
            param.setId(uuid);
            param.setTemplateId(id);
            param.setCreateBy(loginName);
            param.setCreateTime(new Date());
            param.setOrgId(templateInfo.getOrgId());

            //保存模板关系表中
            templateRelationPackageService.insertTemplateRelationPackage(param);
        }
        return AjaxResult.success();
    }

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

    private Map<String, String> findRelations3(String templateId) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            //根据模板id查询模板表头映射关系
            List<TemplateRelationPackage> relations = templateRelationPackageService.selectTemplateRelationPackageListBytemplateId(templateId);
            for (TemplateRelationPackage relation : relations) {
                String customerTemplateName = relation.getCustomerTemplateName();
                String systemTemplateName = relation.getSystemTemplateName();
                if (systemTemplateName != null && !"".equals(systemTemplateName)) {
                    map.put(customerTemplateName, systemTemplateName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
