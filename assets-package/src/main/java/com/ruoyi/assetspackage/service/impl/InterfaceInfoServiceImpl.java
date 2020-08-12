package com.ruoyi.assetspackage.service.impl;

import java.util.List;

import com.ruoyi.assetspackage.domain.luckElephant.InterfaceInfo;
import com.ruoyi.assetspackage.mapper.InterfaceInfoMapper;
import com.ruoyi.assetspackage.service.IInterfaceInfoService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;

/**
 * 接口信息记录Service业务层处理
 * 
 * @author guozeqi
 * @date 2020-08-07
 */
@Service
public class InterfaceInfoServiceImpl implements IInterfaceInfoService
{
    @Autowired
    private InterfaceInfoMapper interfaceInfoMapper;

    /**
     * 查询接口信息记录
     * 
     * @param id 接口信息记录ID
     * @return 接口信息记录
     */
    @Override
    public InterfaceInfo selectInterfaceInfoById(Long id)
    {
        return interfaceInfoMapper.selectInterfaceInfoById(id);
    }

    /**
     * 查询接口信息记录列表
     * 
     * @param interfaceInfo 接口信息记录
     * @return 接口信息记录
     */
    @Override
    public List<InterfaceInfo> selectInterfaceInfoList(InterfaceInfo interfaceInfo)
    {
        return interfaceInfoMapper.selectInterfaceInfoList(interfaceInfo);
    }

    /**
     * 新增接口信息记录
     * 
     * @param interfaceInfo 接口信息记录
     * @return 结果
     */
    @Override
    public int insertInterfaceInfo(InterfaceInfo interfaceInfo)
    {
        interfaceInfo.setCreateTime(DateUtils.getNowDate());
        return interfaceInfoMapper.insertInterfaceInfo(interfaceInfo);
    }

    /**
     * 修改接口信息记录
     * 
     * @param interfaceInfo 接口信息记录
     * @return 结果
     */
    @Override
    public int updateInterfaceInfo(InterfaceInfo interfaceInfo)
    {
        return interfaceInfoMapper.updateInterfaceInfo(interfaceInfo);
    }

    /**
     * 删除接口信息记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInterfaceInfoByIds(String ids)
    {
        return interfaceInfoMapper.deleteInterfaceInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除接口信息记录信息
     * 
     * @param id 接口信息记录ID
     * @return 结果
     */
    @Override
    public int deleteInterfaceInfoById(Long id)
    {
        return interfaceInfoMapper.deleteInterfaceInfoById(id);
    }
}
