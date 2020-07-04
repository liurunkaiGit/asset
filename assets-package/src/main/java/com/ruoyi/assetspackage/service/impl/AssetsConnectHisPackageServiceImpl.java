package com.ruoyi.assetspackage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.assetspackage.mapper.AssetsConnectHisPackageMapper;
import com.ruoyi.assetspackage.domain.AssetsConnectHisPackage;
import com.ruoyi.assetspackage.service.IAssetsConnectHisPackageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资产包与资产关系历史Service业务层处理
 * 
 * @author guozeqi
 * @date 2020-01-08
 */
@Service
public class AssetsConnectHisPackageServiceImpl implements IAssetsConnectHisPackageService 
{
    @Autowired
    private AssetsConnectHisPackageMapper assetsConnectHisPackageMapper;

    /**
     * 查询资产包与资产关系历史
     * 
     * @param id 资产包与资产关系历史ID
     * @return 资产包与资产关系历史
     */
    @Override
    public AssetsConnectHisPackage selectAssetsConnectHisPackageById(String id)
    {
        return assetsConnectHisPackageMapper.selectAssetsConnectHisPackageById(id);
    }

    /**
     * 查询资产包与资产关系历史列表
     * 
     * @param assetsConnectHisPackage 资产包与资产关系历史
     * @return 资产包与资产关系历史
     */
    @Override
    public List<AssetsConnectHisPackage> selectAssetsConnectHisPackageList(AssetsConnectHisPackage assetsConnectHisPackage)
    {
        return assetsConnectHisPackageMapper.selectAssetsConnectHisPackageList(assetsConnectHisPackage);
    }

    /**
     * 新增资产包与资产关系历史
     * 
     * @param assetsConnectHisPackage 资产包与资产关系历史
     * @return 结果
     */
    @Override
    public int insertAssetsConnectHisPackage(AssetsConnectHisPackage assetsConnectHisPackage)
    {
        assetsConnectHisPackage.setCreateTime(DateUtils.getNowDate());
        return assetsConnectHisPackageMapper.insertAssetsConnectHisPackage(assetsConnectHisPackage);
    }

    /**
     * 修改资产包与资产关系历史
     * 
     * @param assetsConnectHisPackage 资产包与资产关系历史
     * @return 结果
     */
    @Override
    public int updateAssetsConnectHisPackage(AssetsConnectHisPackage assetsConnectHisPackage)
    {
        return assetsConnectHisPackageMapper.updateAssetsConnectHisPackage(assetsConnectHisPackage);
    }

    /**
     * 删除资产包与资产关系历史对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAssetsConnectHisPackageByIds(String ids)
    {
        return assetsConnectHisPackageMapper.deleteAssetsConnectHisPackageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除资产包与资产关系历史信息
     * 
     * @param id 资产包与资产关系历史ID
     * @return 结果
     */
    @Override
    public int deleteAssetsConnectHisPackageById(String id)
    {
        return assetsConnectHisPackageMapper.deleteAssetsConnectHisPackageById(id);
    }
}
