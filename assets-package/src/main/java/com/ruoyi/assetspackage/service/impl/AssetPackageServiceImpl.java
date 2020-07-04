package com.ruoyi.assetspackage.service.impl;

import com.ruoyi.assetspackage.domain.AssetPackage;
import com.ruoyi.assetspackage.domain.AssetsConnectPackage;
import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.assetspackage.enums.IsCloseCaseEnum;
import com.ruoyi.assetspackage.enums.IsClosePackageEnum;
import com.ruoyi.assetspackage.enums.IsNullPackageEnum;
import com.ruoyi.assetspackage.enums.PackageFlagEnum;
import com.ruoyi.assetspackage.mapper.AssetPackageMapper;
import com.ruoyi.assetspackage.service.IAssetPackageService;
import com.ruoyi.assetspackage.service.IAssetsConnectPackageService;
import com.ruoyi.assetspackage.service.ICurAssetsPackageService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 资产包Service业务层处理
 *
 * @author guozeqi
 * @date 2020-01-06
 */
@Slf4j
@Service
public class AssetPackageServiceImpl implements IAssetPackageService {

    @Autowired
    private ICurAssetsPackageService curAssetsPackageService;
    @Autowired
    private IAssetPackageService assetPackageService;
    @Autowired
    private AssetPackageMapper assetPackageMapper;
    @Autowired
    private IAssetsConnectPackageService assetsConnectPackageService;

    /**
     * 查询资产包
     * @param id 资产包ID
     * @return 资产包
     */
    @Override
    public AssetPackage selectAssetPackageById(String id) {
        return assetPackageMapper.selectAssetPackageById(id);
    }

    /**
     * 查询资产包列表
     *
     * @param assetPackage 资产包
     * @return 资产包
     */
    @Override
    public List<AssetPackage> selectAssetPackageList(AssetPackage assetPackage) {
        if (assetPackage.getEndCreateTime() != null) {
            assetPackage.setEndCreateTime(DateUtils.getEndOfDay(assetPackage.getEndCreateTime()));
        }
        return assetPackageMapper.selectAssetPackageList(assetPackage);
    }

    /**
     * 新增资产包
     *
     * @param assetPackage 资产包
     * @return 结果
     */
    @Override
    public int insertAssetPackage(AssetPackage assetPackage) {
        assetPackage.setCreateTime(DateUtils.getNowDate());
        return assetPackageMapper.insertAssetPackage(assetPackage);
    }

    /**
     * 修改资产包
     *
     * @param assetPackage 资产包
     * @return 结果
     */
    @Override
    public int updateAssetPackage(AssetPackage assetPackage) {
        assetPackage.setUpdateTime(DateUtils.getNowDate());
        return assetPackageMapper.updateAssetPackage(assetPackage);
    }

    /**
     * 删除资产包对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAssetPackageByIds(String ids) {
        return assetPackageMapper.deleteAssetPackageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除资产包信息
     *
     * @param id 资产包ID
     * @return 结果
     */
    @Override
    public int deleteAssetPackageById(String id) {
        return assetPackageMapper.deleteAssetPackageById(id);
    }

    /*@Override
    @Transactional
    public AjaxResult updateAssetPackage(String packageId, String orgId) {
        //查询资产包是否封包
        AssetPackage assetPackage = assetPackageService.selectAssetPackageById(packageId);
        if (IsClosePackageEnum.CLOSE_PACKAGE.getValue().equals(assetPackage.getIsClose())) {
            //更新资产包状态
            AssetPackage updateParam = new AssetPackage();
            updateParam.setAllocationBy(ShiroUtils.getLoginName());
            updateParam.setAllocationTime(new Date());
            updateParam.setEndOrgId(orgId);
            updateParam.setId(packageId);
            updateParam.setIsClose(IsClosePackageEnum.SEND_PACKAGE.getValue());
            assetPackageService.updateAssetPackage(updateParam);
            //推送自催系统
            List<CurAssetsPackage> curAssetsList = curAssetsPackageService.selectCurAssetsByPackageId(packageId);//查询该包所有资产
            for (CurAssetsPackage curAssetsPackage : curAssetsList) {
                curAssetsPackage.setPackNo(packageId);
                curAssetsPackage.setSendOptId(ShiroUtils.getUserId());
            }
            return curAssetsPackageService.callRemote(curAssetsList);
        } else {
            return AjaxResult.error("当前资产包未打包，请先打包后再分发！");
        }
    }*/

    @Override
    @Transactional
    public AjaxResult updateAssetPackage(String packageId, String orgId) {
        try {
            //查询资产包是否封包
            AssetPackage assetPackage = assetPackageService.selectAssetPackageById(packageId);
            if (IsClosePackageEnum.CLOSE_PACKAGE.getValue().equals(assetPackage.getIsClose())) {
                //更新资产包状态
                AssetPackage updateParam = new AssetPackage();
                updateParam.setAllocationBy(ShiroUtils.getLoginName());
                updateParam.setAllocationTime(new Date());
                updateParam.setEndOrgId(orgId);
                updateParam.setId(packageId);
                updateParam.setIsClose(IsClosePackageEnum.SEND_PACKAGE.getValue());
                assetPackageService.updateAssetPackage(updateParam);
                //推送自催系统
                CurAssetsPackage queryParam = new CurAssetsPackage();
                queryParam.setPackageId(packageId);
                List<CurAssetsPackage> curAssetsList = this.assetPackageMapper.selectCurAssetsList(queryParam);//查询该包所有资产
                for (CurAssetsPackage curAssetsPackage : curAssetsList) {
                    curAssetsPackage.setPackNo(packageId);
                    curAssetsPackage.setSendOptId(ShiroUtils.getUserId());
                }
                curAssetsPackageService.callRemote(curAssetsList);
                return AjaxResult.success("分发成功，本次分发"+curAssetsList.size()+"条");
            } else {
                return AjaxResult.error("当前资产包未打包，请先打包后再分发！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("分发失败",e);
            return AjaxResult.error("分发失败！"+e.getMessage());
        }
    }

    @Override
    public void batchDel(String packageIds) {
        try {
            this.assetPackageMapper.deleteAssetPackageByIds(packageIds.split(","));
        } catch (Exception e) {
            log.error("删除包失败,error is {}", e);
            throw new RuntimeException("删除包失败");
        }

    }

    /**
     * 拆包
     * @param packageIds
     */
    @Override
    @Transactional
    public void dismantlePack(String packageIds) {
        try {
            Arrays.stream(packageIds.split(","))
                    .forEach(packageId -> {
                        // 修改包信息
                        AssetPackage assetPackage = this.assetPackageMapper.selectAssetPackageById(packageId);
                        assetPackage.setIsClose(IsClosePackageEnum.NOT_CLOSE_PACKAGE.getValue());
                        assetPackage.setIsNull(IsNullPackageEnum.NULL_PACKAGE.getValue());
                        assetPackage.setPackageAmount(new BigDecimal(0));
                        assetPackage.setPackageNum(0L);
                        this.assetPackageMapper.updateAssetPackage(assetPackage);
                        /*//查询该包的所有资产
                        CurAssetsPackage param = new CurAssetsPackage();
                        param.setPackageId(packageId);
                        List<CurAssetsPackage> list = this.assetPackageMapper.selectCurAssetsList(param);
                        // 修改资产信息
                        list.stream().forEach(curAsset -> {
                            CurAssetsPackage curAssetsPackage = this.curAssetsPackageService.selectCurAssetsPackageById(curAsset.getId());
                            curAssetsPackage.setPackageFlag(PackageFlagEnum.NOT_PACKAGE.getValue());
                            curAssetsPackage.setPackageId("");
                            this.curAssetsPackageService.updateCurAssetsPackage(curAssetsPackage);
                        });*/

                        // 修改资产信息
                        Map<String,String> param = new HashMap<String,String>();
                        param.put("packageId",packageId);
                        param.put("packageFlag",PackageFlagEnum.NOT_PACKAGE.getValue());
                        this.assetPackageMapper.updateCurassets2(param);


                    });
        } catch (Exception e) {
            log.error("拆包失败，error is {}",e);
            throw new RuntimeException("拆包失败");
        }
    }

    /*@Override
    public void dismantlePack(String packageIds) {
        try {
            Arrays.stream(packageIds.split(","))
                    .forEach(packageId -> {
                        // 修改包信息
                        AssetPackage assetPackage = this.assetPackageMapper.selectAssetPackageById(packageId);
                        assetPackage.setIsClose(IsClosePackageEnum.NOT_CLOSE_PACKAGE.getValue());
                        assetPackage.setPackageAmount(new BigDecimal(0));
                        assetPackage.setPackageNum(0L);
                        this.assetPackageMapper.updateAssetPackage(assetPackage);
                        //查询该包的所有资产
                        AssetsConnectPackage selectParam = new AssetsConnectPackage();
                        selectParam.setPackageId(packageId);
                        List<AssetsConnectPackage> list = assetsConnectPackageService.selectAssetsConnectPackageList(selectParam);
                        // 修改资产信息
                        list.stream().forEach(assetsConnectPackage -> {
                            CurAssetsPackage curAssetsPackage = this.curAssetsPackageService.selectCurAssetsPackageById(assetsConnectPackage.getAssetsId());
                            curAssetsPackage.setPackageFlag(PackageFlagEnum.NOT_PACKAGE.getValue());
                            this.curAssetsPackageService.updateCurAssetsPackage(curAssetsPackage);
                        });
                        // 删除该包下面
                        this.assetsConnectPackageService.deleteAssetsConnectPackageByPackageId(packageId);
                    });
        } catch (Exception e) {
            log.error("拆包失败，error is {}",e);
            throw new RuntimeException("拆包失败");
        }
    }*/



    /*@Override
    @Transactional
    public void assembleAll(CurAssetsPackage curAssetsPackage, String packageId) {
        curAssetsPackage.setPackageFlag(PackageFlagEnum.NOT_PACKAGE.getValue());
        List<CurAssetsPackage> curAssetsPackages = this.curAssetsPackageService.selectCurAssetsPackageList(curAssetsPackage);
        BigDecimal waYe = new BigDecimal(0.00);
        for (CurAssetsPackage asset : curAssetsPackages) {
            //保存关系
            AssetsConnectPackage param = new AssetsConnectPackage();
            param.setId(UUID.randomUUID().toString().replace("-", ""));
            param.setAssetsId(asset.getId());
            param.setPackageId(packageId);
            param.setCreateBy(ShiroUtils.getLoginName());
            param.setCreateTime(new Date());
            assetsConnectPackageService.insertAssetsConnectPackage(param);
            //查询该资产的委案余额
            waYe = waYe.add(asset.getRmbYe() != null ? asset.getRmbYe() : new BigDecimal(0.00));
            //更新资产为已打包状态
            asset.setPackageFlag(PackageFlagEnum.IS_PACKAGE.getValue());
            curAssetsPackageService.updateCurAssetsPackage(asset);
        }
        //更新资产包状态
        AssetPackage assetPackage = this.assetPackageService.selectAssetPackageById(packageId);
        assetPackage.setIsNull(IsNullPackageEnum.NOT_NULL_PACKAGE.getValue());
        assetPackage.setPackageNum(Long.valueOf(curAssetsPackages.size()));
        assetPackage.setIsClose(IsClosePackageEnum.CLOSE_PACKAGE.getValue());
        assetPackage.setPackageAmount(waYe);
        updateAssetPackage(assetPackage);
    }*/

    /*@Override
    public void distribution(String packageId, String assetsId) {
        String[] assetsIds = assetsId.split(",");
        BigDecimal waYe = new BigDecimal(0.00);
        for (String id : assetsIds) {
            //保存关系
            AssetsConnectPackage param = new AssetsConnectPackage();
            param.setId(UUID.randomUUID().toString().replace("-", ""));
            param.setAssetsId(id);
            param.setPackageId(packageId);
            param.setCreateBy(ShiroUtils.getLoginName());
            param.setCreateTime(new Date());
            assetsConnectPackageService.insertAssetsConnectPackage(param);
            //查询该资产的委案余额
            CurAssetsPackage curAssetsPackage = curAssetsPackageService.selectCurAssetsPackageById(id);
            waYe = waYe.add(curAssetsPackage.getRmbYe() != null ? curAssetsPackage.getRmbYe() : new BigDecimal(0.00));
            //更新资产为已打包状态
            curAssetsPackage.setPackageFlag(PackageFlagEnum.IS_PACKAGE.getValue());
            curAssetsPackageService.updateCurAssetsPackage(curAssetsPackage);
        }
        //更新资产包状态
        AssetPackage assetPackage = this.assetPackageService.selectAssetPackageById(packageId);
        assetPackage.setIsNull(IsNullPackageEnum.NOT_NULL_PACKAGE.getValue());
        assetPackage.setPackageNum(Long.valueOf(assetsIds.length));
        assetPackage.setIsClose(IsClosePackageEnum.CLOSE_PACKAGE.getValue());
        assetPackage.setPackageAmount(waYe);
        updateAssetPackage(assetPackage);
    }*/

    @Override
    @Transactional
    public void distribution(String packageId, String assetsId) {
        String[] assetsIds = assetsId.split(",");
        BigDecimal waYe = new BigDecimal(0.00);
        for (String id : assetsIds) {
            //查询该资产的委案余额
            CurAssetsPackage curAssetsPackage = curAssetsPackageService.selectCurAssetsPackageById(id);
            waYe = waYe.add(curAssetsPackage.getRmbYe() != null ? curAssetsPackage.getRmbYe() : new BigDecimal(0.00));
            //更新资产为已打包状态
            curAssetsPackage.setPackageFlag(PackageFlagEnum.IS_PACKAGE.getValue());
            curAssetsPackage.setPackageId(packageId);
            curAssetsPackageService.updateCurAssetsPackage(curAssetsPackage);
        }
        //更新资产包状态
        AssetPackage assetPackage = this.assetPackageService.selectAssetPackageById(packageId);
        assetPackage.setIsNull(IsNullPackageEnum.NOT_NULL_PACKAGE.getValue());
        assetPackage.setPackageNum(Long.valueOf(assetsIds.length));
        assetPackage.setIsClose(IsClosePackageEnum.CLOSE_PACKAGE.getValue());
        assetPackage.setPackageAmount(waYe);
        updateAssetPackage(assetPackage);
    }




    @Override
    @Transactional
    public void assembleAll(CurAssetsPackage curAssetsPackage, String packageId) {
        curAssetsPackage.setPackageFlag(PackageFlagEnum.NOT_PACKAGE.getValue());
        curAssetsPackage.setOrgId(String.valueOf(ShiroUtils.getSysUser().getOrgId()));
        List<CurAssetsPackage> curAssetsPackages = this.curAssetsPackageService.selectCurAssetsPackageList(curAssetsPackage);
        BigDecimal waYe = new BigDecimal(0.00);
        for (CurAssetsPackage asset : curAssetsPackages) {
            //查询该资产的委案余额
            waYe = waYe.add(asset.getRmbYe() != null ? asset.getRmbYe() : new BigDecimal(0.00));
            /*//更新资产为已打包状态
            asset.setPackageFlag(PackageFlagEnum.IS_PACKAGE.getValue());
            asset.setPackageId(packageId);
            curAssetsPackageService.updateCurAssetsPackage(asset);*/
        }
        //更新资产为已打包状态
        if (curAssetsPackage.getEndDate() != null) {
            curAssetsPackage.setEndDate(DateUtils.getEndOfDay(curAssetsPackage.getEndDate()));
        }
        curAssetsPackage.setPackageId(packageId);
        curAssetsPackage.setPackageFlag(PackageFlagEnum.IS_PACKAGE.getValue());
        this.assetPackageMapper.updateCurassets(curAssetsPackage);

        //更新资产包状态
        AssetPackage assetPackage = this.assetPackageService.selectAssetPackageById(packageId);
        assetPackage.setIsNull(IsNullPackageEnum.NOT_NULL_PACKAGE.getValue());
        assetPackage.setPackageNum(Long.valueOf(curAssetsPackages.size()));
        assetPackage.setIsClose(IsClosePackageEnum.CLOSE_PACKAGE.getValue());
        assetPackage.setPackageAmount(waYe);
        updateAssetPackage(assetPackage);
    }


    @Override
    public List<CurAssetsPackage> selectCurAssetsList(CurAssetsPackage curAssetsPackage) {
        return this.assetPackageMapper.selectCurAssetsList(curAssetsPackage);
    }

    @Override
    public int updateCurassets2(Map<String, String> map) {
        return this.assetPackageMapper.updateCurassets2(map);
    }


}
