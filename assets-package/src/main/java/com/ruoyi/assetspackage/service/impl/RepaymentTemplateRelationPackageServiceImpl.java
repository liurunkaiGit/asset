package com.ruoyi.assetspackage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.assetspackage.mapper.RepaymentTemplateRelationPackageMapper;
import com.ruoyi.assetspackage.domain.RepaymentTemplateRelationPackage;
import com.ruoyi.assetspackage.service.IRepaymentTemplateRelationPackageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 还款模板关系Service业务层处理
 * 
 * @author guozeqi
 * @date 2020-01-13
 */
@Service
public class RepaymentTemplateRelationPackageServiceImpl implements IRepaymentTemplateRelationPackageService 
{
    @Autowired
    private RepaymentTemplateRelationPackageMapper repaymentTemplateRelationPackageMapper;

    /**
     * 查询还款模板关系
     * 
     * @param id 还款模板关系ID
     * @return 还款模板关系
     */
    @Override
    public RepaymentTemplateRelationPackage selectRepaymentTemplateRelationPackageById(String id)
    {
        return repaymentTemplateRelationPackageMapper.selectRepaymentTemplateRelationPackageById(id);
    }

    /**
     * 查询还款模板关系列表
     * 
     * @param repaymentTemplateRelationPackage 还款模板关系
     * @return 还款模板关系
     */
    @Override
    public List<RepaymentTemplateRelationPackage> selectRepaymentTemplateRelationPackageList(RepaymentTemplateRelationPackage repaymentTemplateRelationPackage)
    {
        return repaymentTemplateRelationPackageMapper.selectRepaymentTemplateRelationPackageList(repaymentTemplateRelationPackage);
    }

    /**
     * 新增还款模板关系
     * 
     * @param repaymentTemplateRelationPackage 还款模板关系
     * @return 结果
     */
    @Override
    public int insertRepaymentTemplateRelationPackage(RepaymentTemplateRelationPackage repaymentTemplateRelationPackage)
    {
        repaymentTemplateRelationPackage.setCreateTime(DateUtils.getNowDate());
        return repaymentTemplateRelationPackageMapper.insertRepaymentTemplateRelationPackage(repaymentTemplateRelationPackage);
    }

    /**
     * 修改还款模板关系
     * 
     * @param repaymentTemplateRelationPackage 还款模板关系
     * @return 结果
     */
    @Override
    public int updateRepaymentTemplateRelationPackage(RepaymentTemplateRelationPackage repaymentTemplateRelationPackage)
    {
        return repaymentTemplateRelationPackageMapper.updateRepaymentTemplateRelationPackage(repaymentTemplateRelationPackage);
    }

    /**
     * 删除还款模板关系对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRepaymentTemplateRelationPackageByIds(String ids)
    {
        return repaymentTemplateRelationPackageMapper.deleteRepaymentTemplateRelationPackageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除还款模板关系信息
     * 
     * @param id 还款模板关系ID
     * @return 结果
     */
    @Override
    public int deleteRepaymentTemplateRelationPackageById(String id)
    {
        return repaymentTemplateRelationPackageMapper.deleteRepaymentTemplateRelationPackageById(id);
    }


    /**
     *根据模板id删除匹配关系
     * @param templateId
     * @return
     */
    @Override
    public int deleteRepaymentTemplateRelationPackageByTemplateId(String templateId) {
        return repaymentTemplateRelationPackageMapper.deleteRepaymentTemplateRelationPackageByTemplateId(templateId);
    }

    /**
     *根据模板id查询关系列表
     * @param temeplateId
     * @return
     */
    @Override
    public List<RepaymentTemplateRelationPackage> selectRepaymentTemplateRelationPackageListBytemplateId(String temeplateId) {
        return repaymentTemplateRelationPackageMapper.selectRepaymentTemplateRelationPackageListBytemplateId(temeplateId);
    }
}
