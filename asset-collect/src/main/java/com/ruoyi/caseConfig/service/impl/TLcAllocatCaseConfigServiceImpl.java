package com.ruoyi.caseConfig.service.impl;

import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.caseConfig.domain.TLcAllocatCaseConfig;
import com.ruoyi.caseConfig.mapper.TLcAllocatCaseConfigMapper;
import com.ruoyi.caseConfig.service.ITLcAllocatCaseConfigService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.enums.AllocatRuleEnum;
import com.ruoyi.enums.AllocatTaskEnum;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 智能分案配置Service业务层处理
 *
 * @author liurunkai
 * @date 2020-04-23
 */
@Service
public class TLcAllocatCaseConfigServiceImpl implements ITLcAllocatCaseConfigService {

    @Autowired
    private TLcAllocatCaseConfigMapper tLcAllocatCaseConfigMapper;
    @Autowired
    private IOrgPackageService orgPackageService;
    @Autowired
    private ISysDictDataService sysDictDataService;

    /**
     * 查询智能分案配置
     *
     * @param id 智能分案配置ID
     * @return 智能分案配置
     */
    @Override
    public TLcAllocatCaseConfig selectTLcAllocatCaseConfigById(Long id) {
        return tLcAllocatCaseConfigMapper.selectTLcAllocatCaseConfigById(id);
    }

    /**
     * 查询智能分案配置列表
     *
     * @param tLcAllocatCaseConfig 智能分案配置
     * @return 智能分案配置
     */
    @Override
    public List<TLcAllocatCaseConfig> selectTLcAllocatCaseConfigList(TLcAllocatCaseConfig tLcAllocatCaseConfig) {
        return tLcAllocatCaseConfigMapper.selectTLcAllocatCaseConfigList(tLcAllocatCaseConfig);
    }

    /**
     * 新增智能分案配置
     *
     * @param tLcAllocatCaseConfig 智能分案配置
     * @return 结果
     */
    @Override
    public int insertTLcAllocatCaseConfig(TLcAllocatCaseConfig tLcAllocatCaseConfig) {
        tLcAllocatCaseConfig.setCreateTime(DateUtils.getNowDate());
        OrgPackage orgPackage = new OrgPackage();
        orgPackage.setDeptId(tLcAllocatCaseConfig.getOrgId());
        tLcAllocatCaseConfig.setOrgName(this.orgPackageService.selectOrgPackageList(orgPackage).get(0).getOrgName());
        tLcAllocatCaseConfig.setRuleEngineName(this.sysDictDataService.selectDictLabel("rule_engine",tLcAllocatCaseConfig.getRuleEngine()));
        tLcAllocatCaseConfig.setRobotName(this.sysDictDataService.selectDictLabel("robot",tLcAllocatCaseConfig.getRobot()));
        tLcAllocatCaseConfig.setCallPlatformName(this.sysDictDataService.selectDictLabel("call_platform",tLcAllocatCaseConfig.getCallPlatform()));
        tLcAllocatCaseConfig.setAllocatCaseStartegyName(this.sysDictDataService.selectDictLabel("collect_model",tLcAllocatCaseConfig.getAllocatCaseStartegy()));
        return tLcAllocatCaseConfigMapper.insertTLcAllocatCaseConfig(tLcAllocatCaseConfig);
    }

    /**
     * 修改智能分案配置
     *
     * @param tLcAllocatCaseConfig 智能分案配置
     * @return 结果
     */
    @Override
    public int updateTLcAllocatCaseConfig(TLcAllocatCaseConfig tLcAllocatCaseConfig) {
        OrgPackage orgPackage = new OrgPackage();
        orgPackage.setDeptId(tLcAllocatCaseConfig.getOrgId());
        tLcAllocatCaseConfig.setOrgName(this.orgPackageService.selectOrgPackageList(orgPackage).get(0).getOrgName());
        tLcAllocatCaseConfig.setRuleEngineName(this.sysDictDataService.selectDictLabel("rule_engine",tLcAllocatCaseConfig.getRuleEngine()));
        tLcAllocatCaseConfig.setRobotName(this.sysDictDataService.selectDictLabel("robot",tLcAllocatCaseConfig.getRobot()));
        tLcAllocatCaseConfig.setCallPlatformName(this.sysDictDataService.selectDictLabel("call_platform",tLcAllocatCaseConfig.getCallPlatform()));
        tLcAllocatCaseConfig.setAllocatCaseStartegyName(this.sysDictDataService.selectDictLabel("collect_model",tLcAllocatCaseConfig.getAllocatCaseStartegy()));
        tLcAllocatCaseConfig.setUpdateTime(DateUtils.getNowDate());
        return tLcAllocatCaseConfigMapper.updateTLcAllocatCaseConfig(tLcAllocatCaseConfig);
    }

    /**
     * 删除智能分案配置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcAllocatCaseConfigByIds(String ids) {
        return tLcAllocatCaseConfigMapper.deleteTLcAllocatCaseConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除智能分案配置信息
     *
     * @param id 智能分案配置ID
     * @return 结果
     */
    @Override
    public int deleteTLcAllocatCaseConfigById(Long id) {
        return tLcAllocatCaseConfigMapper.deleteTLcAllocatCaseConfigById(id);
    }

    @Override
    public TLcAllocatCaseConfig selectTLcAllocatCaseConfigByOrgId(String orgId) {
        return this.tLcAllocatCaseConfigMapper.selectTLcAllocatCaseConfigByOrgId(orgId);
    }
}
