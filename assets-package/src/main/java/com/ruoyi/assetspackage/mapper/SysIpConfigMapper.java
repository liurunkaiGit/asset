package com.ruoyi.assetspackage.mapper;


import com.ruoyi.assetspackage.domain.SysIpConfig;

import java.util.List;

/**
 * ip段配置Mapper接口
 * 
 * @author guozeqi
 * @date 2020-11-02
 */
public interface SysIpConfigMapper 
{
    /**
     * 查询ip段配置
     * 
     * @param id ip段配置ID
     * @return ip段配置
     */
    public SysIpConfig selectSysIpConfigById(Long id);

    /**
     * 查询ip段配置列表
     * 
     * @param sysIpConfig ip段配置
     * @return ip段配置集合
     */
    public List<SysIpConfig> selectSysIpConfigList(SysIpConfig sysIpConfig);

    /**
     * 查询配置所有ip段
     * @return
     */
    public List<String> selectPartIpList();

    /**
     * 新增ip段配置
     * 
     * @param sysIpConfig ip段配置
     * @return 结果
     */
    public int insertSysIpConfig(SysIpConfig sysIpConfig);

    /**
     * 修改ip段配置
     * 
     * @param sysIpConfig ip段配置
     * @return 结果
     */
    public int updateSysIpConfig(SysIpConfig sysIpConfig);

    /**
     * 删除ip段配置
     * 
     * @param id ip段配置ID
     * @return 结果
     */
    public int deleteSysIpConfigById(Long id);

    /**
     * 批量删除ip段配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysIpConfigByIds(String[] ids);
}
