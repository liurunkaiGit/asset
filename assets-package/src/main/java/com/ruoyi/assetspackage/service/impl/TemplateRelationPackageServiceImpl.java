package com.ruoyi.assetspackage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.assetspackage.mapper.TemplateRelationPackageMapper;
import com.ruoyi.assetspackage.domain.TemplateRelationPackage;
import com.ruoyi.assetspackage.service.ITemplateRelationPackageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 模板关系Service业务层处理
 * 
 * @author guozeqi
 * @date 2020-01-03
 */
@Service
public class TemplateRelationPackageServiceImpl implements ITemplateRelationPackageService 
{
    @Autowired
    private TemplateRelationPackageMapper templateRelationPackageMapper;

    /**
     * 查询模板关系
     * 
     * @param id 模板关系ID
     * @return 模板关系
     */
    @Override
    public TemplateRelationPackage selectTemplateRelationPackageById(String id)
    {
        return templateRelationPackageMapper.selectTemplateRelationPackageById(id);
    }

    /**
     * 查询模板关系列表
     * 
     * @param templateRelationPackage 模板关系
     * @return 模板关系
     */
    @Override
    public List<TemplateRelationPackage> selectTemplateRelationPackageList(TemplateRelationPackage templateRelationPackage)
    {
        return templateRelationPackageMapper.selectTemplateRelationPackageList(templateRelationPackage);
    }

    /**
     * 新增模板关系
     * 
     * @param templateRelationPackage 模板关系
     * @return 结果
     */
    @Override
    public int insertTemplateRelationPackage(TemplateRelationPackage templateRelationPackage)
    {
        templateRelationPackage.setCreateTime(DateUtils.getNowDate());
        return templateRelationPackageMapper.insertTemplateRelationPackage(templateRelationPackage);
    }

    /**
     * 修改模板关系
     * 
     * @param templateRelationPackage 模板关系
     * @return 结果
     */
    @Override
    public int updateTemplateRelationPackage(TemplateRelationPackage templateRelationPackage)
    {
        return templateRelationPackageMapper.updateTemplateRelationPackage(templateRelationPackage);
    }

    /**
     * 删除模板关系对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTemplateRelationPackageByIds(String ids)
    {
        return templateRelationPackageMapper.deleteTemplateRelationPackageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除模板关系信息
     * 
     * @param id 模板关系ID
     * @return 结果
     */
    @Override
    public int deleteTemplateRelationPackageById(String id)
    {
        return templateRelationPackageMapper.deleteTemplateRelationPackageById(id);
    }

    /**
     *根据模板id删除匹配关系
     * @param temeplateId
     * @return
     */

    public int deleteTemplateRelationPackageByTemplateId(String temeplateId){
        return templateRelationPackageMapper.deleteTemplateRelationPackageByTemplateId(temeplateId);
    }

    /**
     *根据模板id查询关系列表
     * @param temeplateId
     * @return
     */
    public List<TemplateRelationPackage> selectTemplateRelationPackageListBytemplateId(String temeplateId){
        return templateRelationPackageMapper.selectTemplateRelationPackageListBytemplateId(temeplateId);
    }

    /**
     * 查询自由导入的关系
     * @param temeplateId
     * @return
     */
    @Override
    public List<String> selectFreeTemplateRelationBytemplateId(String temeplateId) {
        return templateRelationPackageMapper.selectFreeTemplateRelationBytemplateId(temeplateId);
    }


}
