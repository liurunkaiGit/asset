package com.ruoyi.task.service.impl;

import java.math.BigInteger;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.mapper.TLcCustContactMapper;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.task.domain.TLcInfoup;
import com.ruoyi.task.domain.TLcTaskInfoup;
import com.ruoyi.task.domain.TLcTaskUplog;
import com.ruoyi.task.mapper.TLcInfoupMapper;
import com.ruoyi.task.mapper.TLcTaskInfoupMapper;
import com.ruoyi.task.mapper.TLcTaskMapper;
import com.ruoyi.task.mapper.TLcTaskUplogMapper;
import com.ruoyi.task.service.ITLcTaskInfoupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 信息更新Service业务层处理
 * 
 * @author gaohg
 * @date 2021-02-04
 */
@Service
public class TLcTaskInfoupServiceImpl implements ITLcTaskInfoupService
{
    @Autowired
    private TLcTaskInfoupMapper tLcTaskInfoupMapper;
    @Autowired
    private TLcTaskUplogMapper tLcTaskUplogMapper;
    @Autowired
    private TLcTaskMapper tLcTaskMapper;
    @Autowired
    private TLcInfoupMapper tLcInfoupMapper;

    @Transactional
    @Override
    public int insertBatchTLcTaskInfoup(BigInteger[] ids) {
        //记录每天更新条数
        TLcTaskUplog tLcTaskUplog = new  TLcTaskUplog();
        tLcTaskUplog.setUserId(ShiroUtils.getSysUser().getUserId());
        tLcTaskUplog.setDateLog(DateUtils.stringConvertDate(DateUtils.getDate(),DateUtils.YYYY_MM_DD));
        tLcTaskUplog.setCishu(ids.length);
        tLcTaskUplogMapper.updateTLcTaskUplogCishu(tLcTaskUplog);
        //更新任务表状态
        tLcTaskMapper.updateInfoUp(ids,4);
        return tLcTaskInfoupMapper.insertBatchTLcTaskInfoup(ids);
    }

    /**
     * 查询信息更新
     * 
     * @param id 信息更新ID
     * @return 信息更新
     */
    @Override
    public TLcTaskInfoup selectTLcTaskInfoupById(Long id)
    {
        return tLcTaskInfoupMapper.selectTLcTaskInfoupById(id);
    }

    /**
     * 查询信息更新列表
     * 
     * @param tLcTaskInfoup 信息更新
     * @return 信息更新
     */
    @Override
    public List<TLcTaskInfoup> selectTLcTaskInfoupList(TLcTaskInfoup tLcTaskInfoup)
    {
        return tLcTaskInfoupMapper.selectTLcTaskInfoupList(tLcTaskInfoup);
    }

    @Override
    public List<TLcTaskInfoup> selectTLcTaskInfoupListExp(TLcTaskInfoup tLcTaskInfoup) {
        List<TLcTaskInfoup> list = tLcTaskInfoupMapper.selectTLcTaskInfoupList(tLcTaskInfoup);
        TLcInfoup tLcInfoup = new TLcInfoup();

        for(TLcTaskInfoup tp : list){
            tLcInfoup.setTaskInfoId(tp.getId());
            tp.setXinxiStatus(zt(tp.getInfoupStatus()));
            StringBuilder sr = new StringBuilder("更新类型|姓名|与本人关系|电话|联系人地址|备注\n");
            List<TLcInfoup> dhList = tLcInfoupMapper.selectTLcInfoupList(tLcInfoup);
            for(TLcInfoup tf : dhList){
              sr.append((tf.getTypes()==0?"电话号码":"联系人地址")+"|");
              sr.append(tf.getNames()+"|");
              sr.append(guanxi(tf.getRelations())+"|");
              sr.append((tf.getPhone()==null?"":tf.getPhone())+"|");
              sr.append((tf.getContents()==null?"":tf.getContents())+"|");
              sr.append((tf.getRemark()==null?"":tf.getRemark())+"\n");
            }
            tp.setContent(sr.toString().replaceAll("\n", String.valueOf((char)10)));
        }
        return list;
    }

    private String guanxi(Integer gx){
        if(null == gx)return "其他";
        else if(1 == gx)return "本人";
        else if(2 == gx)return "直系";
        else if(1 == gx)return "朋友";
        else if(1 == gx)return "父母";
        else if(1 == gx)return "配偶";
        else if(1 == gx)return "家人";
        else if(1 == gx)return "同事";
        return "其他";
    }

    private String zt(Integer zt){
        if(null == zt)return "未更新";
        else if(1 == zt)return "待提交";
        else if(2 == zt)return "已更新";
        else if(3 == zt)return "更新失败";
        return "未更新";
    }


    @Override
    public int insertTLcTaskInfoup(TLcTaskInfoup tLcTaskInfoup) {
        return tLcTaskInfoupMapper.insertTLcTaskInfoup(tLcTaskInfoup);
    }


    /**
     * 修改信息更新
     * 
     * @param tLcTaskInfoup 信息更新
     * @return 结果
     */
    @Override
    public int updateTLcTaskInfoup(TLcTaskInfoup tLcTaskInfoup)
    {
        return tLcTaskInfoupMapper.updateTLcTaskInfoup(tLcTaskInfoup);
    }

    @Override
    public int updateStatus(TLcTaskInfoup tLcTaskInfoup) {
        return tLcTaskInfoupMapper.updateStatus(tLcTaskInfoup);
    }

    @Override
    public int updateStatusGx(TLcTaskInfoup tLcTaskInfoup) {
        //更新主任务状态
        tLcTaskMapper.updateInfoUp(tLcTaskInfoup.getTaskIds(),tLcTaskInfoup.getInfoupStatus());
        if(2 == tLcTaskInfoup.getInfoupStatus()){
            //提交=已更新状态 将电话信息 批量写入到  联系人表中
            tLcTaskInfoupMapper.insertBatchLianxiren(tLcTaskInfoup.getIds());
        }
        return tLcTaskInfoupMapper.updateStatus(tLcTaskInfoup);
    }

    /**
     * 删除信息更新对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcTaskInfoupByIds(String ids)
    {
        return tLcTaskInfoupMapper.deleteTLcTaskInfoupByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除信息更新信息
     * 
     * @param id 信息更新ID
     * @return 结果
     */
    @Override
    public int deleteTLcTaskInfoupById(Long id)
    {
        return tLcTaskInfoupMapper.deleteTLcTaskInfoupById(id);
    }
}
