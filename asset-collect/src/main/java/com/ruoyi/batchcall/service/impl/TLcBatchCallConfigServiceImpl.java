package com.ruoyi.batchcall.service.impl;

import com.ruoyi.batchcall.domain.TLcBatchCallConfig;
import com.ruoyi.batchcall.mapper.TLcBatchCallConfigMapper;
import com.ruoyi.batchcall.service.ITLcBatchCallConfigService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 批量外呼配置Service业务层处理
 * 
 * @author fengzhitao
 * @date 2020-06-28
 */
@Service
public class TLcBatchCallConfigServiceImpl implements ITLcBatchCallConfigService
{
    @Autowired
    private TLcBatchCallConfigMapper tLcBatchCallConfigMapper;

    /**
     * 查询批量外呼配置
     * 
     * @param id 批量外呼配置ID
     * @return 批量外呼配置
     */
    @Override
    public TLcBatchCallConfig selectTLcBatchCallConfigById(Long id)
    {
        return tLcBatchCallConfigMapper.selectTLcBatchCallConfigById(id);
    }

    /**
     * 查询批量外呼配置列表
     * 
     * @param tLcBatchCallConfig 批量外呼配置
     * @return 批量外呼配置
     */
    @Override
    public List<TLcBatchCallConfig> selectTLcBatchCallConfigList(TLcBatchCallConfig tLcBatchCallConfig)
    {
        List<TLcBatchCallConfig> list = tLcBatchCallConfigMapper.selectTLcBatchCallConfigList(tLcBatchCallConfig);
        if(!list.isEmpty()){
            for(int i = 0; i < list.size(); i ++){
                TLcBatchCallConfig tc = list.get(i);
                String timeStr = "";
                if(StringUtils.isNotEmpty(tc.getStartTime1())){
                    timeStr = tc.getStartTime1() + "-" + tc.getEndTime1();
                }
                if(StringUtils.isNotEmpty(tc.getStartTime2())){
                    timeStr = timeStr + "   " +tc.getStartTime2() + "-" + tc.getEndTime2();
                }
                if(StringUtils.isNotEmpty(tc.getStartTime3())){
                    timeStr = timeStr + "   " + tc.getStartTime3() + "-" + tc.getEndTime3();
                }
                tc.setCanCallTimes(timeStr);
                if("0".equals(tc.getIsCallOther())){
                    tc.setIsCallOther("不呼叫");
                }else if("1".equals(tc.getIsCallOther())){
                    tc.setIsCallOther("呼叫");
                }
            }
        }
        return list;
    }

    /**
     * 新增批量外呼配置
     * 
     * @param tLcBatchCallConfig 批量外呼配置
     * @return 结果
     */
    @Override
    public int insertTLcBatchCallConfig(TLcBatchCallConfig tLcBatchCallConfig)
    {
        tLcBatchCallConfig.setCreateTime(DateUtils.getNowDate());
        tLcBatchCallConfig.setCreateBy(ShiroUtils.getLoginName());
        return tLcBatchCallConfigMapper.insertTLcBatchCallConfig(tLcBatchCallConfig);
    }

    /**
     * 修改批量外呼配置
     * 
     * @param tLcBatchCallConfig 批量外呼配置
     * @return 结果
     */
    @Override
    public int updateTLcBatchCallConfig(TLcBatchCallConfig tLcBatchCallConfig)
    {
        tLcBatchCallConfig.setUpdateTime(DateUtils.getNowDate());
        tLcBatchCallConfig.setUpdateBy(ShiroUtils.getLoginName());
        return tLcBatchCallConfigMapper.updateTLcBatchCallConfig(tLcBatchCallConfig);
    }

    /**
     * 删除批量外呼配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcBatchCallConfigByIds(String ids)
    {
        return tLcBatchCallConfigMapper.deleteTLcBatchCallConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除批量外呼配置信息
     * 
     * @param id 批量外呼配置ID
     * @return 结果
     */
    @Override
    public int deleteTLcBatchCallConfigById(Long id)
    {
        return tLcBatchCallConfigMapper.deleteTLcBatchCallConfigById(id);
    }

    @Override
    public TLcBatchCallConfig selectTLcBatchCallConfigByOrgId(String orgId) {
        return tLcBatchCallConfigMapper.selectTLcBatchCallConfigByOrgId(orgId);
    }
}
