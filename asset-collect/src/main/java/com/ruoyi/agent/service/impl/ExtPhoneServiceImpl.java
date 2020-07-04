package com.ruoyi.agent.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.exonNum.service.ITLcExonNumService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.agent.mapper.ExtPhoneMapper;
import com.ruoyi.agent.domain.ExtPhone;
import com.ruoyi.agent.service.IExtPhoneService;
import com.ruoyi.common.core.text.Convert;

/**
 * 分机号码Service业务层处理
 *
 * @author guozeqi
 * @date 2020-03-02
 */
@Service
public class ExtPhoneServiceImpl implements IExtPhoneService {
    @Autowired
    private ExtPhoneMapper extPhoneMapper;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ITLcExonNumService exonNumService;
    @Autowired
    private ISysDictDataService sysDictDataService;

    /**
     * 查询分机号码
     *
     * @param id 分机号码ID
     * @return 分机号码
     */
    @Override
    public ExtPhone selectExtPhoneById(String id) {
        return extPhoneMapper.selectExtPhoneById(id);
    }

    /**
     * 查询分机号码列表
     *
     * @param extPhone 分机号码
     * @return 分机号码
     */
    @Override
    public List<ExtPhone> selectExtPhoneList(ExtPhone extPhone) {
        return extPhoneMapper.selectExtPhoneList(extPhone);
    }

    /**
     * 新增分机号码
     *
     * @param extPhone 分机号码
     * @return 结果
     */
    @Override
    public int insertExtPhone(ExtPhone extPhone) {
        //OrgPackage orgPackage = new OrgPackage();
        //orgPackage.setDeptId(extPhone.getOrgId());
        //extPhone.setOrgName(this.orgPackageService.selectOrgPackageList(orgPackage).get(0).getOrgName());
        extPhone.setCreateTime(DateUtils.getNowDate());
        if (extPhone.getSeatId() != null) {
            extPhone.setSeatName(this.sysUserService.selectUserById(Long.valueOf(extPhone.getSeatId())).getUserName());
        }
        extPhone.setExonNumGroup(this.exonNumService.selectTLcExonNumById(Long.valueOf(extPhone.getExonNumGroupId())).getExonNumGroup());
        return extPhoneMapper.insertExtPhone(extPhone);
    }

    /**
     * 修改分机号码
     *
     * @param extPhone 分机号码
     * @return 结果
     */
    @Override
    public int updateExtPhone(ExtPhone extPhone) {
        //OrgPackage orgPackage = this.orgPackageService.selectOrgPackageByOrgId(String.valueOf(extPhone.getOrgId()));
        //extPhone.setOrgName(orgPackage.getOrgName());
        extPhone.setUpdateTime(DateUtils.getNowDate());
        if (extPhone.getSeatId() != null) {
            extPhone.setSeatName(this.sysUserService.selectUserById(Long.valueOf(extPhone.getSeatId())).getUserName());
        } else {
            extPhone.setSeatName(null);
        }
        if (extPhone.getExonNumGroupId() != null) {
            extPhone.setExonNumGroup(this.exonNumService.selectTLcExonNumById(Long.valueOf(extPhone.getExonNumGroupId())).getExonNumGroup());
        }
        return extPhoneMapper.updateExtPhone(extPhone);
    }

    /**
     * 删除分机号码对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteExtPhoneByIds(String ids) {
        return extPhoneMapper.deleteExtPhoneByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除分机号码信息
     *
     * @param id 分机号码ID
     * @return 结果
     */
    @Override
    public int deleteExtPhoneById(String id) {
        return extPhoneMapper.deleteExtPhoneById(id);
    }

    @Override
    public List<SysUser> selectNoBindUser(SysUser sysUser) {
        return this.extPhoneMapper.selectNoBindUser(sysUser);
    }

    @Override
    public int updateExtPhoneStatus(ExtPhone extPhone) {
        return extPhoneMapper.updateExtPhoneStatus(extPhone);
    }

    @Override
    public List<Map<String, Object>> selectExtPhoneListByUsername(String username) {
        SysUser sysUser = this.sysUserService.selectUserByLoginName(username);
        List<ExtPhone> extPhoneList = this.extPhoneMapper.selectExtPhoneListBySeatId(sysUser.getUserId());
        List<Map<String, Object>> collect = extPhoneList.stream().map(extPhone -> {
            Map<String, Object> map = new HashMap<>();
            map.put("platform", extPhone.getCallPlatform());
            map.put("platformName", this.sysDictDataService.selectDictLabel("call_platform", extPhone.getCallPlatform()));
            return map;
        }).collect(Collectors.toList());
        ArrayList<Map<String, Object>> resultList = new ArrayList<>();
        collect.stream().forEach(map -> {
            if (map.get("platform").equals("PA")) {
                resultList.add(0,map);
            } else {
                resultList.add(map);
            }
        });
        return resultList;
    }
}
