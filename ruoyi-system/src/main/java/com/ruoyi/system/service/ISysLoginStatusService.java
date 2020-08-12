package com.ruoyi.system.service;


import com.ruoyi.system.domain.SysLoginStatus;

import java.util.List;

/**
 * 登录状态Service接口
 * 
 * @author guozeqi
 * @date 2020-08-06
 */
public interface ISysLoginStatusService 
{
    /**
     * 查询登录状态
     * 
     * @param id 登录状态ID
     * @return 登录状态
     */
    public SysLoginStatus selectSysLoginStatusById(String id);

    /**
     * 查询登录状态列表
     * 
     * @param sysLoginStatus 登录状态
     * @return 登录状态集合
     */
    public List<SysLoginStatus> selectSysLoginStatusList(SysLoginStatus sysLoginStatus);

    /**
     * 新增登录状态
     * 
     * @param sysLoginStatus 登录状态
     * @return 结果
     */
    public int insertSysLoginStatus(SysLoginStatus sysLoginStatus);

    /**
     * 修改登录状态
     * 
     * @param sysLoginStatus 登录状态
     * @return 结果
     */
    public int updateSysLoginStatus(SysLoginStatus sysLoginStatus);


}
