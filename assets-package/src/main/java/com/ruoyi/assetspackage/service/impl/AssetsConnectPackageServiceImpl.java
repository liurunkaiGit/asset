package com.ruoyi.assetspackage.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.assetspackage.mapper.AssetsConnectPackageMapper;
import com.ruoyi.assetspackage.domain.AssetsConnectPackage;
import com.ruoyi.assetspackage.service.IAssetsConnectPackageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资产包与资产关系Service业务层处理
 *
 * @author guozeqi
 * @date 2020-01-08
 */
@Service
public class AssetsConnectPackageServiceImpl implements IAssetsConnectPackageService {
    @Autowired
    private AssetsConnectPackageMapper assetsConnectPackageMapper;

    /**
     * 查询资产包与资产关系
     *
     * @param id 资产包与资产关系ID
     * @return 资产包与资产关系
     */
    @Override
    public AssetsConnectPackage selectAssetsConnectPackageById(String id) {
        return assetsConnectPackageMapper.selectAssetsConnectPackageById(id);
    }

    /**
     * 查询资产包与资产关系列表
     *
     * @param assetsConnectPackage 资产包与资产关系
     * @return 资产包与资产关系
     */
    @Override
    public List<AssetsConnectPackage> selectAssetsConnectPackageList(AssetsConnectPackage assetsConnectPackage) {
        return assetsConnectPackageMapper.selectAssetsConnectPackageList(assetsConnectPackage);
    }

    /**
     * 新增资产包与资产关系
     *
     * @param assetsConnectPackage 资产包与资产关系
     * @return 结果
     */
    @Override
    public int insertAssetsConnectPackage(AssetsConnectPackage assetsConnectPackage) {
        assetsConnectPackage.setCreateTime(DateUtils.getNowDate());
        return assetsConnectPackageMapper.insertAssetsConnectPackage(assetsConnectPackage);
    }

    /**
     * 修改资产包与资产关系
     *
     * @param assetsConnectPackage 资产包与资产关系
     * @return 结果
     */
    @Override
    public int updateAssetsConnectPackage(AssetsConnectPackage assetsConnectPackage) {
        return assetsConnectPackageMapper.updateAssetsConnectPackage(assetsConnectPackage);
    }

    /**
     * 删除资产包与资产关系对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAssetsConnectPackageByIds(String ids) {
        return assetsConnectPackageMapper.deleteAssetsConnectPackageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除资产包与资产关系信息
     *
     * @param id 资产包与资产关系ID
     * @return 结果
     */
    @Override
    public int deleteAssetsConnectPackageById(String id) {
        return assetsConnectPackageMapper.deleteAssetsConnectPackageById(id);
    }


    /**
     * 删除资产包与资产关系
     *
     * @param packageId
     * @return
     */
    @Override
    public int deleteAssetsConnectPackageByPackageId(String packageId) {
        return assetsConnectPackageMapper.deleteAssetsConnectPackageByPackageId(packageId);
    }
}
