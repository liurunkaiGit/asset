package com.ruoyi.shareproject.result.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.shareproject.result.domain.TLpResult;
import com.ruoyi.shareproject.result.mapper.TLpResultMapper;
import com.ruoyi.shareproject.result.service.ITLpResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author liurunkai
 * @date 2020-10-16
 */
@Service
public class TLpResultServiceImpl implements ITLpResultService {
    @Autowired
    private TLpResultMapper tLpResultMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TLpResult selectTLpResultById(Long id) {
        return tLpResultMapper.selectTLpResultById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLpResult 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TLpResult> selectTLpResultList(TLpResult tLpResult) {
        if (tLpResult.getEndReportDate() != null) {
            tLpResult.setEndReportDate(DateUtils.getEndOfDay(tLpResult.getEndReportDate()));
        }
        return tLpResultMapper.selectTLpResultList(tLpResult);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param tLpResult 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTLpResult(TLpResult tLpResult) {
        String[] projectIdName = tLpResult.getProjectIdName().split(",");
        tLpResult.setOrgId(Long.valueOf(projectIdName[0]));
        tLpResult.setOrgName(projectIdName[1]);
        tLpResult.setCreateBy(ShiroUtils.getSysUser().getUserId().toString());
        return tLpResultMapper.insertTLpResult(tLpResult);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param tLpResult 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTLpResult(TLpResult tLpResult) {
        String[] projectIdName = tLpResult.getProjectIdName().split(",");
        tLpResult.setOrgId(Long.valueOf(projectIdName[0]));
        tLpResult.setOrgName(projectIdName[1]);
        tLpResult.setUpdateBy(ShiroUtils.getSysUser().getUserId().toString());
        return tLpResultMapper.updateTLpResult(tLpResult);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLpResultByIds(String ids) {
        return tLpResultMapper.deleteTLpResultByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTLpResultById(Long id) {
        return tLpResultMapper.deleteTLpResultById(id);
    }

    @Override
    public Integer selectProjectResultUnique(TLpResult tLpResult) {
        return this.tLpResultMapper.selectProjectResultUnique(tLpResult);
    }
}
