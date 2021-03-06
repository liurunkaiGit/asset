package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 兴业资产还款Mapper接口
 * 
 * @author guozeqi
 * @date 2020-07-27
 */
public interface AssetsRepaymentFromXYMapper
{

    /**
     * 查询委案金额、手别
     *
     */
    public CurAssetsPackage findAssetsInfo(Map<String,String> param);

    /**
     * 查询业务归属人
     *
     */
    public List<Map<String,String>> findOwnerNameByAssign(Map<String,String> param);
    public Map<String,Object> findOwnerNameByAssign2(Map<String,String> param);
    public Map<String,String> findOwnerNameByAssign3(Map<String,String> param);

    /**
     * 查询还款明细
     * @param curAssetsRepaymentPackage
     * @return
     */
    public List<CurAssetsRepaymentPackage> findCurAssetsRepaymentList(CurAssetsRepaymentPackage curAssetsRepaymentPackage);

    public List<CurAssetsRepaymentPackageXy> findCurAssetsRepaymentXyList(CurAssetsRepaymentPackage curAssetsRepaymentPackage);

}
