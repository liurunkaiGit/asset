package com.ruoyi.assetspackage.service.impl;

import com.ruoyi.assetspackage.domain.AssetPackage;
import com.ruoyi.assetspackage.domain.StreamPackage;
import com.ruoyi.assetspackage.mapper.AssetPackageMapper;
import com.ruoyi.assetspackage.mapper.StreamPackageMapper;
import com.ruoyi.assetspackage.service.IAssetPackageService;
import com.ruoyi.assetspackage.service.IStreamPackageService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流水Service业务层处理
 * 
 * @author guozeqi
 * @date 2020-01-22
 */
@Service
public class StreamPackageServiceImpl implements IStreamPackageService
{
    @Autowired
    private StreamPackageMapper streamPackageMapper;


    /**
     *查询流水
     * @param streamPackage
     * @return
     */
    @Override
    public List<StreamPackage> selectStreamPackageList(StreamPackage streamPackage) {
        return streamPackageMapper.selectStreamPackageList(streamPackage);
    }
}
