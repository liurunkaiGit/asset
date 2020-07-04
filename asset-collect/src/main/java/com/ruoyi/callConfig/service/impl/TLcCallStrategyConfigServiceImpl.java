package com.ruoyi.callConfig.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.callConfig.service.ITLcCallStrategyConfigService;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.robot.domain.RobotPhone;
import com.ruoyi.robot.utils.RobotMethodUtil;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.callConfig.mapper.TLcCallStrategyConfigMapper;
import com.ruoyi.callConfig.domain.TLcCallStrategyConfig;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author liurunkai
 * @date 2020-02-06
 */
@Service("com.ruoyi.callConfig.service.impl.TLcCallStrategyConfigServiceImpl")
public class TLcCallStrategyConfigServiceImpl implements ITLcCallStrategyConfigService {

    @Autowired
    private IOrgPackageService orgPackageService;
    @Autowired
    private ISysDictDataService  dictDataService;
    @Autowired
    private TLcCallStrategyConfigMapper tLcCallStrategyConfigMapper;
    @Autowired
    private RobotMethodUtil robotMethodUtil;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TLcCallStrategyConfig selectTLcCallStrategyConfigById(Long id) {
        return tLcCallStrategyConfigMapper.selectTLcCallStrategyConfigById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcCallStrategyConfig 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TLcCallStrategyConfig> selectTLcCallStrategyConfigList(TLcCallStrategyConfig tLcCallStrategyConfig) {
        return tLcCallStrategyConfigMapper.selectTLcCallStrategyConfigList(tLcCallStrategyConfig);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcCallStrategyConfig 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTLcCallStrategyConfig(TLcCallStrategyConfig tLcCallStrategyConfig) {
        OrgPackage orgPackage = new OrgPackage();
        orgPackage.setDeptId(Long.valueOf(tLcCallStrategyConfig.getOrgId()));
        orgPackage = this.orgPackageService.selectOrgPackageList(orgPackage).get(0);
        String callIntervalTime = this.dictDataService.selectDictLabel("call_interval_time", tLcCallStrategyConfig.getCallIntervalTimeId());
        tLcCallStrategyConfig.setOrgName(orgPackage.getOrgName());
        tLcCallStrategyConfig.setCallIntervalTime(callIntervalTime);
        String[] speechcraftIdAndSceneDefId = tLcCallStrategyConfig.getSpeechcraftIdAndSceneDefId().split(",");
        tLcCallStrategyConfig.setSpeechcraftId(Integer.valueOf(speechcraftIdAndSceneDefId[0]));
        tLcCallStrategyConfig.setSceneDefId(Integer.valueOf(speechcraftIdAndSceneDefId[1]));
        tLcCallStrategyConfig.setSpeechcraftName(speechcraftIdAndSceneDefId[2]);
        tLcCallStrategyConfig.setStatus(IsNoEnum.IS.getCode());
        tLcCallStrategyConfig.setCallLineId(tLcCallStrategyConfig.getCallLineIdAndName().split(",")[0]);
        tLcCallStrategyConfig.setCallLineName(tLcCallStrategyConfig.getCallLineIdAndName().split(",")[1]);
        return tLcCallStrategyConfigMapper.insertTLcCallStrategyConfig(tLcCallStrategyConfig);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcCallStrategyConfig 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTLcCallStrategyConfig(TLcCallStrategyConfig tLcCallStrategyConfig) {
        OrgPackage orgPackage = new OrgPackage();
        orgPackage.setDeptId(Long.valueOf(tLcCallStrategyConfig.getOrgId()));
        orgPackage = this.orgPackageService.selectOrgPackageList(orgPackage).get(0);
        String callIntervalTime = this.dictDataService.selectDictLabel("call_interval_time", tLcCallStrategyConfig.getCallIntervalTimeId());
        tLcCallStrategyConfig.setOrgName(orgPackage.getOrgName());
        tLcCallStrategyConfig.setCallIntervalTime(callIntervalTime);
        String[] speechcraftIdAndSceneDefId = tLcCallStrategyConfig.getSpeechcraftIdAndSceneDefId().split(",");
        tLcCallStrategyConfig.setSpeechcraftId(Integer.valueOf(speechcraftIdAndSceneDefId[0]));
        tLcCallStrategyConfig.setSceneDefId(Integer.valueOf(speechcraftIdAndSceneDefId[1]));
        tLcCallStrategyConfig.setSpeechcraftName(speechcraftIdAndSceneDefId[2]);
        tLcCallStrategyConfig.setCallLineId(tLcCallStrategyConfig.getCallLineIdAndName().split(",")[0]);
        tLcCallStrategyConfig.setCallLineName(tLcCallStrategyConfig.getCallLineIdAndName().split(",")[1]);
        return tLcCallStrategyConfigMapper.updateTLcCallStrategyConfig(tLcCallStrategyConfig);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcCallStrategyConfigByIds(String ids) {
        return tLcCallStrategyConfigMapper.deleteTLcCallStrategyConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTLcCallStrategyConfigById(Long id) {
        return tLcCallStrategyConfigMapper.deleteTLcCallStrategyConfigById(id);
    }

    @Override
    public List<RobotPhone> selectCallLine() {
        List<RobotPhone> phones = this.robotMethodUtil.getPhones();
        List<RobotPhone> phoneList = phones.stream()
                .filter(phone -> phone.getUseAvailable())
                .collect(Collectors.toList());
        return phoneList;
    }

    @Override
    public TLcCallStrategyConfig selectCallStrategyConfigByOrgIdAndBusinessScene(String key, Integer code) {
        return this.tLcCallStrategyConfigMapper.selectCallStrategyConfigByOrgIdAndBusinessScene(key, code);
    }
}
