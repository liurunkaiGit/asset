package com.ruoyi.assetspackage.service.impl;

import com.ruoyi.assetspackage.domain.AssetsInfoPackage;
import com.ruoyi.assetspackage.domain.CurAssetsRepaymentPackage;
import com.ruoyi.assetspackage.mapper.AssetsInfoMapper;
import com.ruoyi.assetspackage.mapper.HomeMapper;
import com.ruoyi.assetspackage.service.IAssetsInfoService;
import com.ruoyi.assetspackage.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 客户资产Service业务层处理
 * 
 * @author guozeqi
 * @date 2020-02-13
 */
@Service
public class HomeServiceImpl implements IHomeService
{
    @Autowired
    private HomeMapper homeMapper;


    @Override
    public List<Map<String, String>> selectCurAssetsCount() {
        return homeMapper.selectCurAssetsCount();
    }

    @Override
    public List<Map<String, String>> selectCurAssetsRepaymentCount() {
        return homeMapper.selectCurAssetsRepaymentCount();
    }

    @Override
    public List<Map<String, String>> selectQkJe() {
        return homeMapper.selectQkJe();
    }

    @Override
    public List<Map<String, String>> selectHkJe() {
        return homeMapper.selectHkJe();
    }
}
