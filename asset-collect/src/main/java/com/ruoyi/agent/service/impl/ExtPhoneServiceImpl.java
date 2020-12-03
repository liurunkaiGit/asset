package com.ruoyi.agent.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.ruoyi.agent.domain.DuYanUser;
import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.common.config.DuYanConfig;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RestTemplateUtil;
import com.ruoyi.exonNum.domain.TLcExonNum;
import com.ruoyi.exonNum.service.ITLcExonNumService;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class ExtPhoneServiceImpl implements IExtPhoneService {
    @Autowired
    private DuYanConfig duYanConfig;
    @Autowired
    private ExtPhoneMapper extPhoneMapper;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ITLcExonNumService exonNumService;
    @Autowired
    private ISysDictDataService sysDictDataService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private IOrgPackageService orgPackageService;
    @Autowired
    private RestTemplateUtil restTemplateUtil;

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
            SysUser sysUser = this.sysUserService.selectUserById(Long.valueOf(extPhone.getSeatId()));
            SysDept sysDept = this.sysDeptService.selectDeptById(sysUser.getDeptId());
            extPhone.setOrgId(sysDept.getDeptId());
            extPhone.setOrgName(sysDept.getDeptName());
//            OrgPackage orgPackage = this.orgPackageService.selectOrgPackageByOrgId(String.valueOf(sysDept.getDeptId()));
//            extPhone.setOrgId(sysDept.getDeptId());
//            extPhone.setOrgName(orgPackage.getOrgName());
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
            SysUser sysUser = this.sysUserService.selectUserById(Long.valueOf(extPhone.getSeatId()));
            SysDept sysDept = this.sysDeptService.selectDeptById(sysUser.getDeptId());
            extPhone.setOrgId(sysDept.getDeptId());
            extPhone.setOrgName(sysDept.getDeptName());
//            OrgPackage orgPackage = this.orgPackageService.selectOrgPackageByOrgId(String.valueOf(sysDept.getDeptId()));
//            extPhone.setOrgId(sysDept.getDeptId());
//            extPhone.setOrgName(orgPackage.getOrgName());
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

    @Override
    public List<String> selectExtNumBySeat(String seatId, String agentId, String callPlatform) {
        ExtPhone extPhone = new ExtPhone();
        extPhone.setIsused("0");
        extPhone.setSeatId(Integer.valueOf(seatId));
        extPhone.setAgentid(agentId);
        extPhone.setCallPlatform(callPlatform);
        List<ExtPhone> list = selectExtPhoneList(extPhone);
        List<String> exonNumGroupList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            TLcExonNum tLcExonNum = this.exonNumService.selectTLcExonNumById(Long.valueOf(list.get(0).getExonNumGroupId()));
            String[] exonNumGroups = tLcExonNum.getExonNum().split(";");
            exonNumGroupList = Arrays.asList(exonNumGroups);
        }
        return exonNumGroupList;
    }

    @Override
    public ExtPhone selectExtPhoneByAgent(ExtPhone extPhone) {
        return this.extPhoneMapper.selectExtPhoneByAgent(extPhone);
    }

    @Override
    public List<DuYanUser.DataBean.AccountsBean> selectDyAccount() {
        Integer pageNum = 1;
        Integer pageSize = 100;
        ArrayList<DuYanUser.DataBean.AccountsBean> allDuYanUserList = new ArrayList<>();
        while (true) {
            DuYanUser duYanUser = restTemplateUtil.getRestTemplate().getForObject("https://open.duyansoft.com/api/v1/account?apikey="+duYanConfig.getApikey()+"&page_num="+pageNum+"&page_size="+pageSize, DuYanUser.class);
            if (duYanUser != null && 1 == duYanUser.getStatus()) {
                List<DuYanUser.DataBean.AccountsBean> accountsBeanList = duYanUser.getData().getAccounts();
                allDuYanUserList.addAll(accountsBeanList);
                log.info("第{}页查询完成，有{}条数据", pageNum, accountsBeanList.size());
                if (accountsBeanList != null && accountsBeanList.size() >= pageSize) {
                    pageNum++;
                } else {
                    break;
                }
            } else {
                log.error("获取度言接口异常，status is {}", duYanUser.getStatus());
            }
        }
        log.info("度言用户是：{}", JSON.toJSONString(allDuYanUserList));
        return allDuYanUserList;
    }
}
