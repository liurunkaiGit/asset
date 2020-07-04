package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.TemplateRelationPackage;
import java.util.List;

/**
 * 模板关系Service接口
 * 
 * @author guozeqi
 * @date 2020-01-03
 */
public interface ITemplateRelationPackageService 
{
    /**
     * 查询模板关系
     * 
     * @param id 模板关系ID
     * @return 模板关系
     */
    public TemplateRelationPackage selectTemplateRelationPackageById(String id);

    /**
     * 查询模板关系列表
     * 
     * @param templateRelationPackage 模板关系
     * @return 模板关系集合
     */
    public List<TemplateRelationPackage> selectTemplateRelationPackageList(TemplateRelationPackage templateRelationPackage);

    /**
     * 新增模板关系
     * 
     * @param templateRelationPackage 模板关系
     * @return 结果
     */
    public int insertTemplateRelationPackage(TemplateRelationPackage templateRelationPackage);

    /**
     * 修改模板关系
     * 
     * @param templateRelationPackage 模板关系
     * @return 结果
     */
    public int updateTemplateRelationPackage(TemplateRelationPackage templateRelationPackage);

    /**
     * 批量删除模板关系
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTemplateRelationPackageByIds(String ids);

    /**
     * 删除模板关系信息
     * 
     * @param id 模板关系ID
     * @return 结果
     */
    public int deleteTemplateRelationPackageById(String id);

    /**
     *根据模板id删除匹配关系
     * @param templateId
     * @return
     */

    public int deleteTemplateRelationPackageByTemplateId(String templateId);

    /**
     *根据模板id查询关系列表
     * @param temeplateId
     * @return
     */
    public List<TemplateRelationPackage> selectTemplateRelationPackageListBytemplateId(String temeplateId);

    public List<String> selectFreeTemplateRelationBytemplateId(String temeplateId);
}
