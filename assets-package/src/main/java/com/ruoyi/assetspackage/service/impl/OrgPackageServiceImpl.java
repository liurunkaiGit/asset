package com.ruoyi.assetspackage.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.assetspackage.util.PackageDataPermissionUtil;
import com.ruoyi.framework.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.assetspackage.mapper.OrgPackageMapper;
import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 机构Service业务层处理
 *
 * @author guozeqi
 * @date 2019-12-27
 */
@Slf4j
@Service
public class OrgPackageServiceImpl implements IOrgPackageService {
    @Autowired
    private OrgPackageMapper orgPackageMapper;
    @Autowired
    private PackageDataPermissionUtil dataPermissionUtil;

    /**
     * 查询机构
     *
     * @param id 机构ID
     * @return 机构
     */
    @Override
    public OrgPackage selectOrgPackageById(String id) {
        return orgPackageMapper.selectOrgPackageById(id);
    }

    @Override
    public OrgPackage selectOrgPackageByDeptId(String deptId) {
        return orgPackageMapper.selectOrgPackageByDeptId(deptId);
    }

    /**
     * 查询机构列表
     *
     * @param orgPackage 机构
     * @return 机构
     */
    @Override
    public List<OrgPackage> selectOrgPackageList(OrgPackage orgPackage) {
        return orgPackageMapper.selectOrgPackageList(orgPackage);
    }

    /**
     * 新增机构
     *
     * @param orgPackage 机构
     * @return 结果
     */
    @Override
    public int insertOrgPackage(OrgPackage orgPackage) {
        return orgPackageMapper.insertOrgPackage(orgPackage);
    }

    /**
     * 修改机构
     *
     * @param orgPackage 机构
     * @return 结果
     */
    @Override
    public int updateOrgPackage(OrgPackage orgPackage) {
        return orgPackageMapper.updateOrgPackage(orgPackage);
    }

    /**
     * 删除机构对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOrgPackageByIds(String ids) {
        return orgPackageMapper.deleteOrgPackageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除机构信息
     *
     * @param id 机构ID
     * @return 结果
     */
    @Override
    public int deleteOrgPackageById(String id) {
        return orgPackageMapper.deleteOrgPackageById(id);
    }

    /**
     * 根据登录用户id获取机构id
     *
     * @param UserId
     * @return
     */
    @Override
    public String selectOrgIdByUserId(Long UserId) {
        return orgPackageMapper.selectOrgIdByUserId(UserId);
    }

    /**
     * 根据登录用户id获取数据权限类型
     *
     * @return
     */
    @Override
    public String selectDataScopeByUserId(Long UserId) {
        return orgPackageMapper.selectDataScopeByUserId(UserId);
    }


    /**
     * 根据登录用户id查询该用户拥有权限的委托方
     *
     * @param UserId
     * @return
     */
    @Override
    public List<Map<String, Long>> selectOrgInfoByUserId1(Long UserId) {
        return orgPackageMapper.selectOrgInfoByUserId1(UserId);
    }

    @Override
    public List<Map<String, Long>> selectOrgInfoByUserId2(Long UserId) {
        return orgPackageMapper.selectOrgInfoByUserId2(UserId);
    }

    @Override
    public List<Map<String, Long>> selectOrgInfoByUserId3(Long UserId) {
        return orgPackageMapper.selectOrgInfoByUserId3(UserId);
    }


    @Override
    public List<Map<String, Long>> selectOrgInfoByUserId4(Long userId) {
        //根据登录名称获取部门id
        long rootDeptId = orgPackageMapper.selectDeptIdByUserId(userId);
        //递归查找所有的子部门
        Set<Long> childDeptIds = selectChildDeptIdByLoginName(new HashSet<Long>(), rootDeptId);
        childDeptIds.add(rootDeptId);
        return orgPackageMapper.selectOrgInfoByUserId4(childDeptIds);
    }

    @Override
    public List<Map<String, Object>> findOrgInfo() {
//        List<Map<String, Long>> orgInfo = null;
//        Long UserId = ShiroUtils.getUserId();
//        String dataScope = selectDataScopeByUserId(UserId);
//        if ("1".equals(dataScope)) {
//            orgInfo = selectOrgInfoByUserId1(UserId);
//        } else if ("2".equals(dataScope)) {
//            orgInfo = selectOrgInfoByUserId2(UserId);
//        } else if ("4".equals(dataScope)) {
//            orgInfo = selectOrgInfoByUserId4(UserId);
//        } else {
//            orgInfo = selectOrgInfoByUserId3(UserId);
//        }

//        boolean allDataPermissionByUser = dataPermissionUtil.findAllDataPermissionByUser(ShiroUtils.getSysUser());
//        if (!allDataPermissionByUser) {
//            Set<Long> deptDataPermission = dataPermissionUtil.findDeptDataPermissionByUser(ShiroUtils.getSysUser());
//            if (deptDataPermission != null && deptDataPermission.size() > 0) {
//                orgPackage.setDeptIds(deptDataPermission);
//            } else {
//                boolean selfDataPermission = dataPermissionUtil.findSelfDataPermissionByUser(ShiroUtils.getSysUser());
//                if (!selfDataPermission) {
//                    log.error("没有查看数据的权限，请联系管理员授权");
//                    throw new RuntimeException("没有查看数据的权限，请联系管理员授权");
//                }
//                orgPackage.setDeptId(ShiroUtils.getSysUser().getDeptId());
//            }
//        }
        OrgPackage orgPackage = new OrgPackage();
        Set<Long> deptIds = dataPermissionUtil.selectDataPer();
        orgPackage.setDeptIds(deptIds);
        List<OrgPackage> orgPackages = orgPackageMapper.selectOrgPackageList(orgPackage);
        List<Map<String, Object>> collect = orgPackages.stream()
                .map(orgPackage1 -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("orgId",orgPackage1.getDeptId());
                    map.put("orgName",orgPackage1.getOrgName());
                    return map;
                }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public OrgPackage selectOrgPackageByOrgId(String orgId) {
        return this.orgPackageMapper.selectOrgPackageByOrgId(orgId);
    }

    /**
     * 根据部门id获取委托方信息
     * @param deptId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectOrgInfoByDeptId(Long deptId) {
        return this.orgPackageMapper.selectOrgInfoByDeptId(deptId);
    }

    /**
     * 递归查询所有子节点
     *
     * @param
     * @return
     */
    private Set<Long> selectChildDeptIdByLoginName(Set<Long> childId, Long parentId) {
        Set<Long> childDeptIds = orgPackageMapper.selectChildDeptId(parentId);
        if (childDeptIds != null && childDeptIds.size() > 0) {
            for (Long childDeptId : childDeptIds) {
                childId.add(childDeptId);
                selectChildDeptIdByLoginName(childId, childDeptId);
            }
        }
        return childId;
    }

    @Override
    public List<Map<String, Object>> selectOrgPackageByTime(OrgPackage orgPackage) {
        return orgPackageMapper.selectOrgPackageByTime(orgPackage);
    }
}
