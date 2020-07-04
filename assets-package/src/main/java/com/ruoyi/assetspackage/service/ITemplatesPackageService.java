package com.ruoyi.assetspackage.service;


import com.ruoyi.assetspackage.domain.TemplatesPackage;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 模板管理Service接口
 *
 * @author guozeqi
 * @date 2020-01-02
 */
public interface ITemplatesPackageService {
    /**
     * 查询模板管理
     *
     * @param id 模板管理ID
     * @return 模板管理
     */
    public TemplatesPackage selectTemplatesPackageById(String id);

    /**
     * 查询模板管理列表
     *
     * @param templatesPackage 模板管理
     * @return 模板管理集合
     */
    public List<TemplatesPackage> selectTemplatesPackageList(TemplatesPackage templatesPackage);

    /**
     * 新增模板管理
     *
     * @param templatesPackage 模板管理
     * @return 结果
     */
    public int insertTemplatesPackage(TemplatesPackage templatesPackage);

    /**
     * 修改模板管理
     *
     * @param templatesPackage 模板管理
     * @return 结果
     */
    public int updateTemplatesPackage(TemplatesPackage templatesPackage);

    /**
     * 批量删除模板管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public void deleteTemplatesPackageByIds(String ids);

    /**
     * 删除模板管理信息
     *
     * @param id 模板管理ID
     * @return 结果
     */
    public int deleteTemplatesPackageById(String id);


    /**
     * 根据登录用户id获取机构id
     *
     * @return
     */
    public String selectOrgIdByUserId(Long userId);


    /**
     * 根据模板名称查询模板信息
     *
     * @param templateName
     * @return
     */
    public TemplatesPackage selectTemplateByTemplateName(String templateName, String templateType);


    /**
     * 模板上传
     *
     * @param request
     * @param file
     * @param orgId
     * @param orgName
     * @param type
     */
    void fileUpload(HttpServletRequest request, MultipartFile file, String orgId, String orgName, String type);

    /**
     * 模板下载
     *
     * @param request
     * @param response
     * @param templateId
     */
    void downLoad(HttpServletRequest request, HttpServletResponse response, String templateId);

    /**
     * 解析模板头信息
     *
     * @param request
     * @param response
     * @param url
     * @param templateId
     * @return
     */
    String parseHead(HttpServletRequest request, HttpServletResponse response, String url, String templateId);

    public AjaxResult addRelation(HttpServletRequest request, String id)throws Exception;
    public AjaxResult addRelation2(HttpServletRequest request, String id)throws Exception;
}
