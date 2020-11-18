package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.SysLoginStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 登录状态Mapper接口
 * 
 * @author guozeqi
 * @date 2020-08-06
 */
public interface SysLoginStatusMapper 
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
     * 根据loginName获取最新ip
     * @param loginName
     * @return
     */
    public List<SysLoginStatus> selIpByLoginName(String loginName);
    /**
     * 查询登录状态列表
     *
     * @param sysLoginStatus 登录状态
     * @return 登录状态集合
     */
    public List<SysLoginStatus> selectSysLoginStatusListTask(SysLoginStatus sysLoginStatus);

    /**
     * 时间段 查询用户 最新一条数据
     * @param sysLoginStatus
     * @return
     */
    public List<SysLoginStatus> selectSysLoginStatusListTaskOne(SysLoginStatus sysLoginStatus);

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

    /**
     * 查询用户当天最后一次登录信息
     * @return
     */
    public SysLoginStatus selectSysLoginStatus(@Param("orgId")String orgId, @Param("loginName")String loginName);

    /**
     * 查询用户当天最大的退出次数
     * @return
     */
    public Integer selectMaxLogoutCount(@Param("orgId")String orgId, @Param("loginName")String loginName);

    /**
     * 查询当天未退出系统的登录信息
     * @return
     */
    public List<SysLoginStatus> selectNotLogoutStatus(String hostAddr);


}
