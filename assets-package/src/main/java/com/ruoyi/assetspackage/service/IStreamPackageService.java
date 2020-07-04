package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.StreamPackage;

import java.util.List;

/**
 * 流水Service接口
 * 
 * @author guozeqi
 * @date 2020-01-22
 */
public interface IStreamPackageService
{
    /**
     * 查询流水
     *
     * @param streamPackage
     * @return
     */
    public List<StreamPackage> selectStreamPackageList(StreamPackage streamPackage);
}
