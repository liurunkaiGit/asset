package com.ruoyi.shareproject.hmrule.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.hmrule.domain.TLjRuleDetails;
import com.ruoyi.shareproject.hmrule.mapper.TLjRuleDetailsMapper;
import com.ruoyi.shareproject.hmrule.service.ITLjRuleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 【居家规则详情】Service业务层处理
 * 
 * @author gaohg
 * @date 2020-11-02
 */
@Service
public class TLjRuleDetailsServiceImpl implements ITLjRuleDetailsService
{
    @Autowired
    private TLjRuleDetailsMapper tLjRuleDetailsMapper;

    /**
     * 查询【居家规则详情】
     * 
     * @param id 【居家规则详情】ID
     * @return 【居家规则详情】
     */
    @Override
    public TLjRuleDetails selectTLjRuleDetailsById(Long id)
    {
        return tLjRuleDetailsMapper.selectTLjRuleDetailsById(id);
    }

    /**
     * 查询【居家规则详情】列表
     * 
     * @param tLjRuleDetails 【居家规则详情】
     * @return 【居家规则详情】
     */
    @Override
    public List<TLjRuleDetails> selectTLjRuleDetailsList(TLjRuleDetails tLjRuleDetails)
    {
        return tLjRuleDetailsMapper.selectTLjRuleDetailsList(tLjRuleDetails);
    }

    /**
     * 新增【居家规则详情】
     * 
     * @param tLjRuleDetails 【居家规则详情】
     * @return 结果
     */
    @Override
    public int insertTLjRuleDetails(TLjRuleDetails tLjRuleDetails)
    {
        tLjRuleDetails.setCreateTime(DateUtils.getNowDate());
        return tLjRuleDetailsMapper.insertTLjRuleDetails(tLjRuleDetails);
    }

    @Override
    public int tmisuse(TLjRuleDetails tLjRuleDetails) {
        List<TLjRuleDetails> lt = tLjRuleDetailsMapper.tmisuse(tLjRuleDetails);
        if(null != lt && !lt.isEmpty())return 0;
        return 1;
    }


    /**
     * 修改【居家规则详情】
     * 
     * @param tLjRuleDetails 【居家规则详情】
     * @return 结果
     */
    @Override
    public int updateTLjRuleDetails(TLjRuleDetails tLjRuleDetails)
    {
        tLjRuleDetails.setUpdateTime(DateUtils.getNowDate());
        return tLjRuleDetailsMapper.updateTLjRuleDetails(tLjRuleDetails);
    }

    /**
     * 删除【居家规则详情】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLjRuleDetailsByIds(String ids)
    {
        return tLjRuleDetailsMapper.deleteTLjRuleDetailsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【居家规则详情】信息
     * 
     * @param id 【居家规则详情】ID
     * @return 结果
     */
    @Override
    public int deleteTLjRuleDetailsById(Long id)
    {
        return tLjRuleDetailsMapper.deleteTLjRuleDetailsById(id);
    }
}
