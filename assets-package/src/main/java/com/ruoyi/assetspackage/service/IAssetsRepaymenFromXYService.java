package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.assetspackage.domain.CurAssetsRepaymentPackage;
import com.ruoyi.assetspackage.domain.CurAssetsRepaymentPackageXy;
import com.ruoyi.assetspackage.domain.TempCurAssetsRepaymentPackage;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 资产还款Service接口
 * 
 * @author guozeqi
 * @date 2020-07-27
 */
public interface IAssetsRepaymenFromXYService
{

    /**
     * 查询还款明细
     * @param curAssetsRepaymentPackage
     * @return
     */
    public List<CurAssetsRepaymentPackage> findCurAssetsRepaymentList(CurAssetsRepaymentPackage curAssetsRepaymentPackage);
    public List<CurAssetsRepaymentPackageXy> findCurAssetsRepaymentXyList(CurAssetsRepaymentPackage curAssetsRepaymentPackage);

    /**
     * 查询委案金额、手别、资产批次号
     *
     */
    public CurAssetsPackage findAssetsInfo(Map<String,String> param);

    /**
     * 查询业务归属人
     *
     */
    public Map<String,String> findOwnerName(Map<String,String> param);

}
