package com.ruoyi.assetspackage.mapper;


import com.ruoyi.assetspackage.domain.luckElephant.InterfaceInfo;

import java.util.List;

/**
 * 接口信息记录Mapper接口
 * 
 * @author guozeqi
 * @date 2020-08-07
 */
public interface InterfaceInfoMapper 
{
    /**
     * 查询接口信息记录
     * 
     * @param id 接口信息记录ID
     * @return 接口信息记录
     */
    public InterfaceInfo selectInterfaceInfoById(Long id);

    /**
     * 查询接口信息记录列表
     * 
     * @param interfaceInfo 接口信息记录
     * @return 接口信息记录集合
     */
    public List<InterfaceInfo> selectInterfaceInfoList(InterfaceInfo interfaceInfo);

    /**
     * 新增接口信息记录
     * 
     * @param interfaceInfo 接口信息记录
     * @return 结果
     */
    public int insertInterfaceInfo(InterfaceInfo interfaceInfo);

    /**
     * 修改接口信息记录
     * 
     * @param interfaceInfo 接口信息记录
     * @return 结果
     */
    public int updateInterfaceInfo(InterfaceInfo interfaceInfo);

    /**
     * 删除接口信息记录
     * 
     * @param id 接口信息记录ID
     * @return 结果
     */
    public int deleteInterfaceInfoById(Long id);

    /**
     * 批量删除接口信息记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInterfaceInfoByIds(String[] ids);
}
