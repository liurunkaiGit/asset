package com.ruoyi.assetspackage.mapper;



import com.ruoyi.assetspackage.domain.TemplatesPackage;

import java.util.List;
import java.util.Map;

/**
 * 模板管理Mapper接口
 * 
 * @author guozeqi
 * @date 2020-01-02
 */
public interface TemplatesPackageMapper 
{
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
     * 删除模板管理
     * 
     * @param id 模板管理ID
     * @return 结果
     */
    public int deleteTemplatesPackageById(String id);

    /**
     * 批量删除模板管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTemplatesPackageByIds(String[] ids);


    /**
     * 根据登录用户id获取机构id
     * @return
     */
    public String selectOrgIdByUserId(Long userId);


    /**
     *
     * 根据模板名称查询模板信息
     * @param param
     * @return
     */
    public TemplatesPackage selectTemplateByTemplateName(Map<String,String> param);






}
