package com.ruoyi.task.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.mapper.TLcCustContactMapper;
import com.ruoyi.framework.config.ShiroConfig;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.task.domain.TLcTaskUplog;
import com.ruoyi.task.mapper.TLcTaskUplogMapper;
import com.ruoyi.task.service.ITLcTaskUplogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 【信息更新申请-次数记录】Service业务层处理
 * 
 * @author gaohg
 * @date 2021-02-03
 */
@Service
public class TLcTaskUplogServiceImpl implements ITLcTaskUplogService
{
    @Autowired
    private TLcTaskUplogMapper tLcTaskUplogMapper;
    /**
     * 查询【信息更新申请-次数记录】
     * 
     * @param id 【信息更新申请-次数记录】ID
     * @return 【信息更新申请-次数记录】
     */
    @Override
    public TLcTaskUplog selectTLcTaskUplogById(Long id)
    {
        return tLcTaskUplogMapper.selectTLcTaskUplogById(id);
    }

    /**
     * 查询【信息更新申请-次数记录】列表
     * 
     * @param tLcTaskUplog 【信息更新申请-次数记录】
     * @return 【信息更新申请-次数记录】
     */
    @Override
    public List<TLcTaskUplog> selectTLcTaskUplogList(TLcTaskUplog tLcTaskUplog)
    {
        return tLcTaskUplogMapper.selectTLcTaskUplogList(tLcTaskUplog);
    }

    @Override
    public Integer findCishu() {
        TLcTaskUplog tLcTaskUplog = new  TLcTaskUplog();
        tLcTaskUplog.setUserId(ShiroUtils.getSysUser().getUserId());
        tLcTaskUplog.setDateLog(DateUtils.stringConvertDate(DateUtils.getDate(),DateUtils.YYYY_MM_DD));
        List<TLcTaskUplog> list = tLcTaskUplogMapper.selectTLcTaskUplogList(tLcTaskUplog);
        if(null == list || list.isEmpty()){
            tLcTaskUplog.setCishu(0);
            tLcTaskUplogMapper.insertTLcTaskUplog(tLcTaskUplog);
            return 0;
        }else{
            return list.get(0).getCishu();
        }
    }

    /**
     * 新增【信息更新申请-次数记录】
     * 
     * @param tLcTaskUplog 【信息更新申请-次数记录】
     * @return 结果
     */
    @Override
    public int insertTLcTaskUplog(TLcTaskUplog tLcTaskUplog)
    {
        return tLcTaskUplogMapper.insertTLcTaskUplog(tLcTaskUplog);
    }

    /**
     * 修改【信息更新申请-次数记录】
     * 
     * @param tLcTaskUplog 【信息更新申请-次数记录】
     * @return 结果
     */
    @Override
    public int updateTLcTaskUplog(TLcTaskUplog tLcTaskUplog)
    {
        return tLcTaskUplogMapper.updateTLcTaskUplog(tLcTaskUplog);
    }

    /**
     * 删除【信息更新申请-次数记录】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcTaskUplogByIds(String ids)
    {
        return tLcTaskUplogMapper.deleteTLcTaskUplogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【信息更新申请-次数记录】信息
     * 
     * @param id 【信息更新申请-次数记录】ID
     * @return 结果
     */
    @Override
    public int deleteTLcTaskUplogById(Long id)
    {
        return tLcTaskUplogMapper.deleteTLcTaskUplogById(id);
    }
}
