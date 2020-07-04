package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.RepaymentTemplatesPackage;

import java.util.List;

/**
 * 还款模板管理Service接口
 * 
 * @author guozeqi
 * @date 2020-01-13
 */
public interface IRepaymentTemplatesPackageService 
{
    /**
     * 查询还款模板管理
     * 
     * @param id 还款模板管理ID
     * @return 还款模板管理
     */
    public RepaymentTemplatesPackage selectRepaymentTemplatesPackageById(String id);

    /**
     * 查询还款模板管理列表
     * 
     * @param repaymentTemplatesPackage 还款模板管理
     * @return 还款模板管理集合
     */
    public List<RepaymentTemplatesPackage> selectRepaymentTemplatesPackageList(RepaymentTemplatesPackage repaymentTemplatesPackage);

    /**
     * 新增还款模板管理
     * 
     * @param repaymentTemplatesPackage 还款模板管理
     * @return 结果
     */
    public int insertRepaymentTemplatesPackage(RepaymentTemplatesPackage repaymentTemplatesPackage);

    /**
     * 修改还款模板管理
     * 
     * @param repaymentTemplatesPackage 还款模板管理
     * @return 结果
     */
    public int updateRepaymentTemplatesPackage(RepaymentTemplatesPackage repaymentTemplatesPackage);

    /**
     * 批量删除还款模板管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRepaymentTemplatesPackageByIds(String ids);

    /**
     * 删除还款模板管理信息
     * 
     * @param id 还款模板管理ID
     * @return 结果
     */
    public int deleteRepaymentTemplatesPackageById(String id);

    /**
     *
     * 根据模板名称查询模板信息
     * @param templateName
     * @return
     */
    public RepaymentTemplatesPackage selectTemplateByTemplateName(String templateName);
}
