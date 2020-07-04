package com.ruoyi.assetspackage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.assetspackage.mapper.RepaymentTemplatesPackageMapper;
import com.ruoyi.assetspackage.domain.RepaymentTemplatesPackage;
import com.ruoyi.assetspackage.service.IRepaymentTemplatesPackageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 还款模板管理Service业务层处理
 * 
 * @author guozeqi
 * @date 2020-01-13
 */
@Service
public class RepaymentTemplatesPackageServiceImpl implements IRepaymentTemplatesPackageService 
{
    @Autowired
    private RepaymentTemplatesPackageMapper repaymentTemplatesPackageMapper;

    /**
     * 查询还款模板管理
     * 
     * @param id 还款模板管理ID
     * @return 还款模板管理
     */
    @Override
    public RepaymentTemplatesPackage selectRepaymentTemplatesPackageById(String id)
    {
        return repaymentTemplatesPackageMapper.selectRepaymentTemplatesPackageById(id);
    }

    /**
     * 查询还款模板管理列表
     * 
     * @param repaymentTemplatesPackage 还款模板管理
     * @return 还款模板管理
     */
    @Override
    public List<RepaymentTemplatesPackage> selectRepaymentTemplatesPackageList(RepaymentTemplatesPackage repaymentTemplatesPackage)
    {
        return repaymentTemplatesPackageMapper.selectRepaymentTemplatesPackageList(repaymentTemplatesPackage);
    }

    /**
     * 新增还款模板管理
     * 
     * @param repaymentTemplatesPackage 还款模板管理
     * @return 结果
     */
    @Override
    public int insertRepaymentTemplatesPackage(RepaymentTemplatesPackage repaymentTemplatesPackage)
    {
        repaymentTemplatesPackage.setCreateTime(DateUtils.getNowDate());
        return repaymentTemplatesPackageMapper.insertRepaymentTemplatesPackage(repaymentTemplatesPackage);
    }

    /**
     * 修改还款模板管理
     * 
     * @param repaymentTemplatesPackage 还款模板管理
     * @return 结果
     */
    @Override
    public int updateRepaymentTemplatesPackage(RepaymentTemplatesPackage repaymentTemplatesPackage)
    {
        repaymentTemplatesPackage.setUpdateTime(DateUtils.getNowDate());
        return repaymentTemplatesPackageMapper.updateRepaymentTemplatesPackage(repaymentTemplatesPackage);
    }

    /**
     * 删除还款模板管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRepaymentTemplatesPackageByIds(String ids)
    {
        return repaymentTemplatesPackageMapper.deleteRepaymentTemplatesPackageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除还款模板管理信息
     * 
     * @param id 还款模板管理ID
     * @return 结果
     */
    @Override
    public int deleteRepaymentTemplatesPackageById(String id)
    {
        return repaymentTemplatesPackageMapper.deleteRepaymentTemplatesPackageById(id);
    }


    /**
     *
     * 根据模板名称查询模板信息
     * @param templateName
     * @return
     */
    @Override
    public RepaymentTemplatesPackage selectTemplateByTemplateName(String templateName) {
        return repaymentTemplatesPackageMapper.selectTemplateByTemplateName(templateName);
    }
}
