package com.ruoyi.orgSpeechConf.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.caseConfig.domain.TLcAllocatCaseConfig;
import com.ruoyi.caseConfig.service.ITLcAllocatCaseConfigService;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.orgSpeechConf.domain.TLcOrgSpeechcraftConf;
import com.ruoyi.orgSpeechConf.mapper.TLcOrgSpeechcraftConfMapper;
import com.ruoyi.orgSpeechConf.service.ITLcOrgSpeechcraftConfService;
import com.ruoyi.robot.domain.RobotCompany;
import com.ruoyi.robot.utils.RobotMethodUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author liurunkai
 * @date 2020-05-18
 */
@Slf4j
@Service("com.ruoyi.orgSpeechConf.service.impl.TLcOrgSpeechcraftConfServiceImpl")
public class TLcOrgSpeechcraftConfServiceImpl implements ITLcOrgSpeechcraftConfService {

    @Autowired
    private RobotMethodUtil robotMethodUtil;
    @Autowired
    private TLcOrgSpeechcraftConfMapper tLcOrgSpeechcraftConfMapper;
    @Autowired
    private ITLcAllocatCaseConfigService allocatCaseConfigService;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TLcOrgSpeechcraftConf selectTLcOrgSpeechcraftConfById(Long id) {
        return tLcOrgSpeechcraftConfMapper.selectTLcOrgSpeechcraftConfById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcOrgSpeechcraftConf 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TLcOrgSpeechcraftConf> selectTLcOrgSpeechcraftConfList(TLcOrgSpeechcraftConf tLcOrgSpeechcraftConf) {
        return tLcOrgSpeechcraftConfMapper.selectTLcOrgSpeechcraftConfList(tLcOrgSpeechcraftConf);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcOrgSpeechcraftConf 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTLcOrgSpeechcraftConf(TLcOrgSpeechcraftConf tLcOrgSpeechcraftConf) {
        tLcOrgSpeechcraftConf.setOrgId(Long.valueOf(tLcOrgSpeechcraftConf.getOrgIdAndName().split(",")[0]));
        tLcOrgSpeechcraftConf.setOrgName(tLcOrgSpeechcraftConf.getOrgIdAndName().split(",")[1]);
        tLcOrgSpeechcraftConf.setCreateBy(String.valueOf(ShiroUtils.getSysUser().getUserId()));
        tLcOrgSpeechcraftConf.setUpdateBy(String.valueOf(ShiroUtils.getSysUser().getUserId()));
        tLcOrgSpeechcraftConf.setDelFlag(IsNoEnum.NO.getCode());
        TLcAllocatCaseConfig caseConfig = this.allocatCaseConfigService.selectTLcAllocatCaseConfigByOrgId(tLcOrgSpeechcraftConf.getOrgIdAndName().split(",")[0]);
        if (caseConfig == null) {
            log.error("机构{}对应的智能分案配置为空",tLcOrgSpeechcraftConf.getOrgIdAndName().split(",")[1]);
            throw new BusinessException("请配置智能分案配置");
        }
        Integer companyId = null;
        if ("BR".equalsIgnoreCase(caseConfig.getRobot())) {
            companyId = this.robotMethodUtil.getCompanys().getCompanyId();
        }
        tLcOrgSpeechcraftConf.setCompanyId(companyId);
        String[] idAndNames = tLcOrgSpeechcraftConf.getRobotDefIdAndName().split(",");
        String ids = "";
        String names = "";
        String scendIds = "";
        String sceneVariables = "";
        for (int i = 0; i < idAndNames.length; i++) {
            if (i == idAndNames.length -1) {
                ids += idAndNames[i].split("\\|")[0];
                names += idAndNames[i].split("\\|")[1];
                scendIds += idAndNames[i].split("\\|")[2];
                List<String> sceneVariableList = this.robotMethodUtil.getSceneVariables(Integer.valueOf(idAndNames[i].split("\\|")[2]), companyId);
                sceneVariables += JSON.toJSONString(sceneVariableList);
            } else {
                ids += idAndNames[i].split("\\|")[0].concat(",");
                names += idAndNames[i].split("\\|")[1].concat(",");
                scendIds += idAndNames[i].split("\\|")[2].concat(",");
                List<String> sceneVariableList = this.robotMethodUtil.getSceneVariables(Integer.valueOf(idAndNames[i].split("\\|")[2]), companyId);
                sceneVariables += JSON.toJSONString(sceneVariableList).concat("|");
            }
        }
        tLcOrgSpeechcraftConf.setSpeechcraftVariable(sceneVariables);
        tLcOrgSpeechcraftConf.setSpeechcraftId(ids);
        tLcOrgSpeechcraftConf.setSpeechcraftName(names);
        tLcOrgSpeechcraftConf.setSceneDefId(scendIds);
        return tLcOrgSpeechcraftConfMapper.insertTLcOrgSpeechcraftConf(tLcOrgSpeechcraftConf);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcOrgSpeechcraftConf 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTLcOrgSpeechcraftConf(TLcOrgSpeechcraftConf tLcOrgSpeechcraftConf) {
        tLcOrgSpeechcraftConf.setOrgId(Long.valueOf(tLcOrgSpeechcraftConf.getOrgIdAndName().split(",")[0]));
        tLcOrgSpeechcraftConf.setOrgName(tLcOrgSpeechcraftConf.getOrgIdAndName().split(",")[1]);
        tLcOrgSpeechcraftConf.setUpdateBy(String.valueOf(ShiroUtils.getSysUser().getUserId()));
        tLcOrgSpeechcraftConf.setDelFlag(IsNoEnum.NO.getCode());
        TLcAllocatCaseConfig caseConfig = this.allocatCaseConfigService.selectTLcAllocatCaseConfigByOrgId(tLcOrgSpeechcraftConf.getOrgIdAndName().split(",")[0]);
        if (caseConfig == null) {
            log.error("机构{}对应的智能分案配置为空",tLcOrgSpeechcraftConf.getOrgIdAndName().split(",")[1]);
            throw new BusinessException("请配置智能分案配置");
        }
        Integer companyId = null;
        if ("BR".equalsIgnoreCase(caseConfig.getRobot())) {
            companyId = this.robotMethodUtil.getCompanys().getCompanyId();
        }
        tLcOrgSpeechcraftConf.setCompanyId(companyId);
        String[] idAndNames = tLcOrgSpeechcraftConf.getRobotDefIdAndName().split(",");
        String ids = "";
        String names = "";
        String scendIds = "";
        String sceneVariables = "";
        for (int i = 0; i < idAndNames.length; i++) {
            if (i == idAndNames.length -1) {
                ids += idAndNames[i].split("\\|")[0];
                names += idAndNames[i].split("\\|")[1];
                scendIds += idAndNames[i].split("\\|")[2];
                List<String> sceneVariableList = this.robotMethodUtil.getSceneVariables(Integer.valueOf(idAndNames[i].split("\\|")[2]), companyId);
                sceneVariables += JSON.toJSONString(sceneVariableList);
            } else {
                ids += idAndNames[i].split("\\|")[0].concat(",");
                names += idAndNames[i].split("\\|")[1].concat(",");
                scendIds += idAndNames[i].split("\\|")[2].concat(",");
                List<String> sceneVariableList = this.robotMethodUtil.getSceneVariables(Integer.valueOf(idAndNames[i].split("\\|")[2]), companyId);
                sceneVariables += JSON.toJSONString(sceneVariableList).concat("|");
            }
        }
        tLcOrgSpeechcraftConf.setSpeechcraftVariable(sceneVariables);
        tLcOrgSpeechcraftConf.setSpeechcraftId(ids);
        tLcOrgSpeechcraftConf.setSpeechcraftName(names);
        tLcOrgSpeechcraftConf.setSceneDefId(scendIds);
        return tLcOrgSpeechcraftConfMapper.updateTLcOrgSpeechcraftConf(tLcOrgSpeechcraftConf);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcOrgSpeechcraftConfByIds(String ids) {
        return tLcOrgSpeechcraftConfMapper.deleteTLcOrgSpeechcraftConfByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTLcOrgSpeechcraftConfById(Long id) {
        return tLcOrgSpeechcraftConfMapper.deleteTLcOrgSpeechcraftConfById(id);
    }

    @Override
    public TLcOrgSpeechcraftConf selectTLcOrgSpeechcraftConfByOrgId(Long orgId) {
        return tLcOrgSpeechcraftConfMapper.selectTLcOrgSpeechcraftConfByOrgId(orgId);
    }

    @Override
    public Integer selectUsedTotalConcurrentValue() {
        return tLcOrgSpeechcraftConfMapper.selectUsedTotalConcurrentValue();
    }

    @Override
    public String checkOrgIdUnique(String orgId) {
        int count = this.tLcOrgSpeechcraftConfMapper.checkOrgIdUnique(orgId);
        if (count > 0) {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }
}
