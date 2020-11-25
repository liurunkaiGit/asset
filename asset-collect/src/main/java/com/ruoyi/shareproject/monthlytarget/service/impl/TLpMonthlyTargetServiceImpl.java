package com.ruoyi.shareproject.monthlytarget.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.monthlytarget.domain.TLpMonthlyTarget;
import com.ruoyi.shareproject.monthlytarget.mapper.TLpMonthlyTargetMapper;
import com.ruoyi.shareproject.monthlytarget.service.ITLpMonthlyTargetService;
import com.ruoyi.shareproject.result.domain.TLpResult;
import com.ruoyi.shareproject.result.service.ITLpResultService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 【月度指标】Service业务层处理
 * 
 * @author gaohg
 * @date 2020-10-15
 */
@Service
public class TLpMonthlyTargetServiceImpl implements ITLpMonthlyTargetService
{
    @Autowired
    private TLpMonthlyTargetMapper tLpMonthlyTargetMapper;
    @Autowired
    private ITLpResultService resultService;

    /**
     * 查询【月度指标】
     * 
     * @param id 【月度指标】ID
     * @return 【月度指标】
     */
    @Override
    public TLpMonthlyTarget selectTLpMonthlyTargetById(Long id)
    {
        return tLpMonthlyTargetMapper.selectTLpMonthlyTargetById(id);
    }

    /**
     * 查询【月度指标】列表
     * 
     * @param tLpMonthlyTarget 【月度指标】
     * @return 【月度指标】
     */
    @Override
    public List<TLpMonthlyTarget> selectTLpMonthlyTargetList(TLpMonthlyTarget tLpMonthlyTarget)
    {
        return tLpMonthlyTargetMapper.selectTLpMonthlyTargetList(tLpMonthlyTarget);
    }

    /**
     * 新增【月度指标】
     * 
     * @param tLpMonthlyTarget 【月度指标】
     * @return 结果
     */
    @Override
    public int insertTLpMonthlyTarget(TLpMonthlyTarget tLpMonthlyTarget)
    {
        return tLpMonthlyTargetMapper.insertTLpMonthlyTarget(tLpMonthlyTarget);
    }

    /**
     * 修改【月度指标】
     * 
     * @param tLpMonthlyTarget 【月度指标】
     * @return 结果
     */
    @Override
    public int updateTLpMonthlyTarget(TLpMonthlyTarget tLpMonthlyTarget)
    {
        tLpMonthlyTarget.setUpdateTime(DateUtils.getNowDate());
        return tLpMonthlyTargetMapper.updateTLpMonthlyTarget(tLpMonthlyTarget);
    }

    /**
     * 删除【月度指标】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLpMonthlyTargetByIds(String ids)
    {
        return tLpMonthlyTargetMapper.deleteTLpMonthlyTargetByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【月度指标】信息
     * 
     * @param id 【月度指标】ID
     * @return 结果
     */
    @Override
    public int deleteTLpMonthlyTargetById(Long id)
    {
        return tLpMonthlyTargetMapper.deleteTLpMonthlyTargetById(id);
    }

    @Override
    public Integer selectMonthlyTargetUnique(TLpMonthlyTarget tLpMonthlyTarget) {
        return this.tLpMonthlyTargetMapper.selectMonthlyTargetUnique(tLpMonthlyTarget);
    }

}
