package com.ruoyi.shareproject.hmrule.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.hmrule.domain.TLjRule;
import com.ruoyi.shareproject.hmrule.domain.TLjRuleDetails;
import com.ruoyi.shareproject.hmrule.domain.TLjRuleRange;
import com.ruoyi.shareproject.hmrule.mapper.TLjRuleDetailsMapper;
import com.ruoyi.shareproject.hmrule.mapper.TLjRuleMapper;
import com.ruoyi.shareproject.hmrule.mapper.TLjRuleRangeMapper;
import com.ruoyi.shareproject.hmrule.service.ITLjRuleService;
import com.ruoyi.system.mapper.SysUserMapper;
import net.sf.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 【居家规则】Service业务层处理
 * 
 * @author gaohg
 * @date 2020-11-02
 */
@Service
public class TLjRuleServiceImpl implements ITLjRuleService
{
    @Autowired
    private TLjRuleMapper tLjRuleMapper;
    @Autowired
    private TLjRuleDetailsMapper tLjRuleDetailsMapper;
    @Autowired
    private TLjRuleRangeMapper tLjRuleRangeMapper;
    @Autowired
    private SysUserMapper userMapper;
    /**
     * 查询【居家规则】
     * 
     * @param id 【居家规则】ID
     * @return 【居家规则】
     */
    @Override
    public TLjRule selectTLjRuleById(Long id)
    {
        TLjRule te = tLjRuleMapper.selectTLjRuleById(id);
        TLjRuleRange tr = new TLjRuleRange();
        tr.setRuleId(id);
        List<TLjRuleRange> lt = tLjRuleRangeMapper.selectTLjRuleRangeList(tr);
        List<String> ids = new ArrayList<String>();
        List<String> names = new ArrayList<String>();
        for(TLjRuleRange ts:lt){
            ids.add(ts.getDporusId());
            names.add(ts.getNames());
        }
        //te.setRrList(lt);
        te.setIds(String.join(",", ids));
        te.setNames(String.join(",", names));
        return te;
    }

    @Override
    public int guizeuse(TLjRule tLjRule) {
        if("1".equals(tLjRule.getRuleType())){
            //是部门
            tLjRule.setArray(tLjRule.getIds().split(","));
            tLjRule.setRuleStatus("2");
            List<TLjRule> list = tLjRuleMapper.selectTLjRuleListIsUse(tLjRule);
            //如果不为空 则说明已经存在范围内 不能添加规则
            if(null != list && !list.isEmpty())return -1;
        }else if("2".equals(tLjRule.getRuleType())){
            tLjRule.setArray(tLjRule.getIds().split(","));
            tLjRule.setRuleStatus("2");
            //检测规则范围表中用户是否存在
            List<TLjRule> list = tLjRuleMapper.selectTLjRuleListIsUse(tLjRule);
            //如果不为空 则说明已经存在范围内 不能添加规则
            if(null != list && !list.isEmpty())return -1;
            //检测部门中是否已经包含用户
            tLjRule.setRuleType("1");
            List<TLjRule> listT = tLjRuleMapper.selectTLjRuleListIsUseByLoginName(tLjRule);
            if(null != listT && !listT.isEmpty()){
                return -1;
            }
        }
        return 1;
    }

    /**
     * 查询【居家规则】列表
     * 
     * @param tLjRule 【居家规则】
     * @return 【居家规则】
     */
    @Override
    public List<TLjRule> selectTLjRuleList(TLjRule tLjRule)
    {
        return tLjRuleMapper.selectTLjRuleList(tLjRule);
    }

    /**
     * 新增【居家规则】
     * 
     * @param tLjRule 【居家规则】
     * @return 结果
     */
    @Override
    @Transactional
    public int insertTLjRule(TLjRule tLjRule)
    {
        int rl = tLjRuleMapper.insertTLjRule(tLjRule);
        addRange(tLjRule);
        return rl;
    }

    private void addRange(TLjRule tLjRule){
        String [] ids ,names;

        //存储选择的部门或人员
        if(tLjRule.getRuleType().equals("1")){
            //0=部门
            ids = tLjRule.getDeptIds().split(",");
            names = tLjRule.getDeptNames().split(",");
        }else{
            ids = tLjRule.getUserIds().split(",");
            names = tLjRule.getUserNames().split(",");
        }
        int i=0;
        for(String id:ids){
            TLjRuleRange te = new TLjRuleRange();
            te.setOrgId(tLjRule.getOrgId());
            te.setOrgName(tLjRule.getOrgName());
            te.setCreateBy(tLjRule.getCreateBy());
            te.setCreateTime(tLjRule.getCreateTime());
            te.setUpdateBy(tLjRule.getUpdateBy());
            te.setUpdateTime(tLjRule.getUpdateTime());
            te.setRuleId(tLjRule.getId());
            te.setTypes(tLjRule.getRuleType());
            te.setDporusId(id);
            te.setNames(names[i]);
            i++;
            tLjRuleRangeMapper.insertTLjRuleRange(te);
        }
    }

    /**
     * 修改【居家规则】
     * 
     * @param tLjRule 【居家规则】
     * @return 结果
     */
    @Override
    @Transactional
    public int updateTLjRule(TLjRule tLjRule)
    {
        tLjRuleRangeMapper.deleteTLjRuleRangeByRuleId(tLjRule.getId());
        addRange(tLjRule);
        return tLjRuleMapper.updateTLjRule(tLjRule);
    }

    /**
     * 删除【居家规则】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLjRuleByIds(String ids)
    {
        tLjRuleRangeMapper.deleteTLjRuleRangeByRuleIds(Convert.toStrArray(ids));
        tLjRuleDetailsMapper.deleteTLjRuleDetailsByRuleIds(Convert.toStrArray(ids));
        return tLjRuleMapper.deleteTLjRuleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【居家规则】信息
     * 
     * @param id 【居家规则】ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteTLjRuleById(Long id)
    {
        tLjRuleRangeMapper.deleteTLjRuleRangeByRuleId(id);
        tLjRuleDetailsMapper.deleteTLjRuleDetailsById(id);
        return tLjRuleMapper.deleteTLjRuleById(id);
    }
}
