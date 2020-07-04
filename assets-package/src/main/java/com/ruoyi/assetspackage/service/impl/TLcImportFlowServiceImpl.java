package com.ruoyi.assetspackage.service.impl;

import com.ruoyi.assetspackage.domain.TLcImportFlow;
import com.ruoyi.assetspackage.mapper.TLcImportFlowMapper;
import com.ruoyi.assetspackage.service.ITLcImportFlowService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.DataPermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author liurunkai
 * @date 2020-03-24
 */
@Service
public class TLcImportFlowServiceImpl extends BaseController implements ITLcImportFlowService {
    @Autowired
    private TLcImportFlowMapper tLcImportFlowMapper;
    @Autowired
    private DataPermissionUtils dataPermissionUtils;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TLcImportFlow selectTLcImportFlowById(Long id) {
        return tLcImportFlowMapper.selectTLcImportFlowById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcImportFlow 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TLcImportFlow> selectTLcImportFlowList(TLcImportFlow tLcImportFlow) {
        Set<Long> depts = dataPermissionUtils.selectDataPer();
        tLcImportFlow.setDeptIds(depts);
        startPage();
        if (tLcImportFlow.getEndCreateTime() != null) {
            tLcImportFlow.setEndCreateTime(DateUtils.getEndOfDay(tLcImportFlow.getEndCreateTime()));
        }
        return tLcImportFlowMapper.selectTLcImportFlowList(tLcImportFlow);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcImportFlow 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTLcImportFlow(TLcImportFlow tLcImportFlow) {
        tLcImportFlow.setCreateTime(DateUtils.getNowDate());
        return tLcImportFlowMapper.insertTLcImportFlow(tLcImportFlow);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcImportFlow 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTLcImportFlow(TLcImportFlow tLcImportFlow) {
        return tLcImportFlowMapper.updateTLcImportFlow(tLcImportFlow);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcImportFlowByIds(String ids) {
        return tLcImportFlowMapper.deleteTLcImportFlowByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTLcImportFlowById(Long id) {
        return tLcImportFlowMapper.deleteTLcImportFlowById(id);
    }
}
