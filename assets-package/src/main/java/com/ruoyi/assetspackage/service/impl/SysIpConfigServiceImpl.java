package com.ruoyi.assetspackage.service.impl;

import java.util.List;

import com.ruoyi.assetspackage.domain.SysIpConfig;
import com.ruoyi.assetspackage.mapper.SysIpConfigMapper;
import com.ruoyi.assetspackage.service.ISysIpConfigService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;

/**
 * ip段配置Service业务层处理
 * 
 * @author guozeqi
 * @date 2020-11-02
 */
@Service
public class SysIpConfigServiceImpl implements ISysIpConfigService
{
    @Autowired
    private SysIpConfigMapper sysIpConfigMapper;

    /**
     * 查询ip段配置
     * 
     * @param id ip段配置ID
     * @return ip段配置
     */
    @Override
    public SysIpConfig selectSysIpConfigById(Long id)
    {
        return sysIpConfigMapper.selectSysIpConfigById(id);
    }

    /**
     * 查询ip段配置列表
     * 
     * @param sysIpConfig ip段配置
     * @return ip段配置
     */
    @Override
    public List<SysIpConfig> selectSysIpConfigList(SysIpConfig sysIpConfig)
    {
        return sysIpConfigMapper.selectSysIpConfigList(sysIpConfig);
    }

    /**
     * 查询配置所有ip段
     * @return
     */
    @Override
    public List<String> selectPartIpList() {
        return sysIpConfigMapper.selectPartIpList();
    }

    /**
     * 新增ip段配置
     * 
     * @param sysIpConfig ip段配置
     * @return 结果
     */
    @Override
    public int insertSysIpConfig(SysIpConfig sysIpConfig)
    {
        sysIpConfig.setCreateTime(DateUtils.getNowDate());
        String [] arr = new String[3];
        String[] split = sysIpConfig.getStartIp().split("\\.");
        arr[0]=split[0];
        arr[1]=split[1];
        arr[2]=split[2];
        String partIp = StringUtils.join(arr, ".");
        sysIpConfig.setPartIp(partIp);
        sysIpConfig.setCreateBy(ShiroUtils.getSysUser().getUserName());
        return sysIpConfigMapper.insertSysIpConfig(sysIpConfig);
    }


    /**
     * 修改ip段配置
     * 
     * @param sysIpConfig ip段配置
     * @return 结果
     */
    @Override
    public int updateSysIpConfig(SysIpConfig sysIpConfig)
    {
        sysIpConfig.setUpdateTime(DateUtils.getNowDate());
        String [] arr = new String[3];
        String[] split = sysIpConfig.getStartIp().split("\\.");
        arr[0]=split[0];
        arr[1]=split[1];
        arr[2]=split[2];
        String partIp = StringUtils.join(arr, ".");
        sysIpConfig.setPartIp(partIp);
        sysIpConfig.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        return sysIpConfigMapper.updateSysIpConfig(sysIpConfig);
    }

    /**
     * 删除ip段配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysIpConfigByIds(String ids)
    {
        return sysIpConfigMapper.deleteSysIpConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除ip段配置信息
     * 
     * @param id ip段配置ID
     * @return 结果
     */
    @Override
    public int deleteSysIpConfigById(Long id)
    {
        return sysIpConfigMapper.deleteSysIpConfigById(id);
    }
}
