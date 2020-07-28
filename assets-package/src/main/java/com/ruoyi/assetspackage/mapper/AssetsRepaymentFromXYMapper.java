package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.assetspackage.domain.CurAssetsRepaymentPackage;
import com.ruoyi.assetspackage.domain.DuncaseAssign;
import com.ruoyi.assetspackage.domain.TempCurAssetsRepaymentPackage;
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
    public List<String> findOwnerNameByAssign(Map<String,String> param);
    public List<String> findOwnerNameByTask(Map<String,String> param);

    /**
     * 查询还款明细
     * @param curAssetsRepaymentPackage
     * @return
     */
    public List<CurAssetsRepaymentPackage> findCurAssetsRepaymentList(CurAssetsRepaymentPackage curAssetsRepaymentPackage);

}
