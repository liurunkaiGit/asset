package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.AssetsInfoPackage;
import com.ruoyi.assetspackage.domain.CurAssetsRepaymentPackage;

import java.util.List;
import java.util.Map;

/**
 * 客户资产Service接口
 * 
 * @author guozeqi
 * @date 2020-02-13
 */
public interface IHomeService
{

    /**
     * 查询客户资产每月导入量
     *
     */
    public List<Map<String,String>> selectCurAssetsCount();


    /**
     * 查询资产还款每月导入量
     *
     */
    public List<Map<String,String>> selectCurAssetsRepaymentCount();



    /**
     * 查询欠款金额
     * @return
     */
    public List<Map<String,String>> selectQkJe();


    /**
     * 查询还款金额
     * @return
     */
    public List<Map<String,String>> selectHkJe();


}
