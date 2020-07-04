package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.OrgPackage;

import java.util.List;
import java.util.Map;

/**
 * 机构Service接口
 *
 * @author guozeqi
 * @date 2019-12-27
 */
public interface IOrgPackageService {
    /**
     * 查询机构
     *
     * @param id 机构ID
     * @return 机构
     */
    public OrgPackage selectOrgPackageById(String id);
    public OrgPackage selectOrgPackageByDeptId(String deptId);

    /**
     * 查询机构列表
     *
     * @param orgPackage 机构
     * @return 机构集合
     */
    public List<OrgPackage> selectOrgPackageList(OrgPackage orgPackage);

    /**
     * 新增机构
     *
     * @param orgPackage 机构
     * @return 结果
     */
    public int insertOrgPackage(OrgPackage orgPackage);

    /**
     * 修改机构
     *
     * @param orgPackage 机构
     * @return 结果
     */
    public int updateOrgPackage(OrgPackage orgPackage);

    /**
     * 批量删除机构
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrgPackageByIds(String ids);

    /**
     * 删除机构信息
     *
     * @param id 机构ID
     * @return 结果
     */
    public int deleteOrgPackageById(String id);

    /**
     * 根据登录用户id获取机构id
     *
     * @return
     */
    public String selectOrgIdByUserId(Long UserId);

    /**
     * 根据登录用户id获取数据权限类型
     *
     * @return
     */
    public String selectDataScopeByUserId(Long UserId);

    /**
     * 根据登录用户id查询该用户拥有权限的委托方
     *
     * @param UserId
     * @return
     */
    public List<Map<String, Long>> selectOrgInfoByUserId1(Long UserId);//全部数据权限

    public List<Map<String, Long>> selectOrgInfoByUserId2(Long UserId);//自定义数据权限

    public List<Map<String, Long>> selectOrgInfoByUserId3(Long UserId);//本部门数据权限

    public List<Map<String, Long>> selectOrgInfoByUserId4(Long userId);//本部门及以下数据权限

    /**
     * 查询当前用户拥有的数据权限与委托方的交集
     *
     * @return
     */
    List<Map<String, Object>> findOrgInfo();

    /**
     * 根据委托方编码查询对应的委托方信息
     *
     * @param orgId
     * @return
     */
    OrgPackage selectOrgPackageByOrgId(String orgId);

    /**
     * 根据部门id获取委托方信息
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> selectOrgInfoByDeptId(Long deptId);

    /**
     * 根据时间只查询机构表--定时任务同步数据中心 用
     * 2020-06-23 封志涛添加
     * @param orgPackage
     * @return
     */
    List<Map<String,Object>> selectOrgPackageByTime(OrgPackage orgPackage);
}
