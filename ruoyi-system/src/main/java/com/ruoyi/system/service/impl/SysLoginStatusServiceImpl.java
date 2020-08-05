package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysLoginStatus;
import com.ruoyi.system.mapper.SysLoginStatusMapper;
import com.ruoyi.system.service.ISysLoginStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



/**
 * 登录状态Service业务层处理
 * 
 * @author guozeqi
 * @date 2020-08-06
 */
@Service
public class SysLoginStatusServiceImpl implements ISysLoginStatusService
{
    @Autowired
    private SysLoginStatusMapper sysLoginStatusMapper;

    /**
     * 查询登录状态
     * 
     * @param id 登录状态ID
     * @return 登录状态
     */
    @Override
    public SysLoginStatus selectSysLoginStatusById(String id)
    {
        return sysLoginStatusMapper.selectSysLoginStatusById(id);
    }

    /**
     * 查询登录状态列表
     * 
     * @param sysLoginStatus 登录状态
     * @return 登录状态
     */
    @Override
    public List<SysLoginStatus> selectSysLoginStatusList(SysLoginStatus sysLoginStatus)
    {
        return sysLoginStatusMapper.selectSysLoginStatusList(sysLoginStatus);
    }

    /**
     * 新增登录状态
     * 
     * @param sysLoginStatus 登录状态
     * @return 结果
     */
    @Override
    public int insertSysLoginStatus(SysLoginStatus sysLoginStatus)
    {
        return sysLoginStatusMapper.insertSysLoginStatus(sysLoginStatus);
    }

    /**
     * 修改登录状态
     * 
     * @param sysLoginStatus 登录状态
     * @return 结果
     */
    @Override
    public int updateSysLoginStatus(SysLoginStatus sysLoginStatus)
    {
        return sysLoginStatusMapper.updateSysLoginStatus(sysLoginStatus);
    }


}
