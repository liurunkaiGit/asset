package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.RepaymentTemplateRelationPackage;
import java.util.List;

/**
 * 还款模板关系Service接口
 * 
 * @author guozeqi
 * @date 2020-01-13
 */
public interface IRepaymentTemplateRelationPackageService 
{
    /**
     * 查询还款模板关系
     * 
     * @param id 还款模板关系ID
     * @return 还款模板关系
     */
    public RepaymentTemplateRelationPackage selectRepaymentTemplateRelationPackageById(String id);

    /**
     * 查询还款模板关系列表
     * 
     * @param repaymentTemplateRelationPackage 还款模板关系
     * @return 还款模板关系集合
     */
    public List<RepaymentTemplateRelationPackage> selectRepaymentTemplateRelationPackageList(RepaymentTemplateRelationPackage repaymentTemplateRelationPackage);

    /**
     * 新增还款模板关系
     * 
     * @param repaymentTemplateRelationPackage 还款模板关系
     * @return 结果
     */
    public int insertRepaymentTemplateRelationPackage(RepaymentTemplateRelationPackage repaymentTemplateRelationPackage);

    /**
     * 修改还款模板关系
     * 
     * @param repaymentTemplateRelationPackage 还款模板关系
     * @return 结果
     */
    public int updateRepaymentTemplateRelationPackage(RepaymentTemplateRelationPackage repaymentTemplateRelationPackage);

    /**
     * 批量删除还款模板关系
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRepaymentTemplateRelationPackageByIds(String ids);

    /**
     * 删除还款模板关系信息
     * 
     * @param id 还款模板关系ID
     * @return 结果
     */
    public int deleteRepaymentTemplateRelationPackageById(String id);

    /**
     *根据模板id删除匹配关系
     * @param templateId
     * @return
     */
    public int deleteRepaymentTemplateRelationPackageByTemplateId(String templateId);

    /**
     *根据模板id查询关系列表
     * @param temeplateId
     * @return
     */
    public List<RepaymentTemplateRelationPackage> selectRepaymentTemplateRelationPackageListBytemplateId(String temeplateId);
}
