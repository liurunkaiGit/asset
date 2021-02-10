package com.ruoyi.task.service.impl;

import java.math.BigInteger;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.mapper.TLcCustContactMapper;
import com.ruoyi.duncase.mapper.TLcDuncaseMapper;
import com.ruoyi.enums.ContactOriginEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.task.domain.TLcInfoup;
import com.ruoyi.task.domain.TLcTaskInfoup;
import com.ruoyi.task.mapper.TLcInfoupMapper;
import com.ruoyi.task.mapper.TLcTaskInfoupMapper;
import com.ruoyi.task.mapper.TLcTaskMapper;
import com.ruoyi.task.service.ITLcInfoupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 【联系人增加】Service业务层处理
 * 
 * @author gaohg
 * @date 2021-02-05
 */
@Service
public class TLcInfoupServiceImpl implements ITLcInfoupService
{
    @Autowired
    private TLcInfoupMapper tLcInfoupMapper;
    @Autowired
    private TLcCustContactMapper tLcCustContactMapper;
    @Autowired
    private TLcTaskInfoupMapper tLcTaskInfoupMapper;
    @Autowired
    private TLcDuncaseMapper tLcDuncaseMapper;
    @Autowired
    private TLcTaskMapper tLcTaskMapper;
    /**
     * 查询【联系人增加】
     * 
     * @param id 【联系人增加】ID
     * @return 【联系人增加】
     */
    @Override
    public TLcInfoup selectTLcInfoupById(Long id)
    {
        return tLcInfoupMapper.selectTLcInfoupById(id);
    }

    /**
     * 查询【联系人增加】列表
     * 
     * @param tLcInfoup 【联系人增加】
     * @return 【联系人增加】
     */
    @Override
    public List<TLcInfoup> selectTLcInfoupList(TLcInfoup tLcInfoup)
    {
        return tLcInfoupMapper.selectTLcInfoupList(tLcInfoup);
    }

    /**
     * 新增【联系人增加】
     * 
     * @param tLcInfoup 【联系人增加】
     * @return 结果
     */
    @Override
    public int insertTLcInfoup(TLcInfoup tLcInfoup)
    {
        TLcTaskInfoup tup = tLcTaskInfoupMapper.selectTLcTaskInfoupById(tLcInfoup.getTaskInfoId());
        if(null == tup)throw new RuntimeException("任务不存在");
        //判断电话是否已经存在联系人表中
        lianxphone(tup,tLcInfoup);

        tLcInfoup.setTaskId(tup.getTaskId());
        tLcInfoup.setOrgId(tup.getOrgId());
        tLcInfoup.setOrgName(tup.getOrgName());
        tLcInfoup.setImportBatchNo(tup.getImportBatchNo());
        tLcInfoup.setCaseNo(tup.getCaseNo());
        tLcInfoup.setCreateBy(ShiroUtils.getSysUser().getLoginName());
        tLcInfoup.setCreateById(ShiroUtils.getUserId());
        tLcInfoup.setCreateTime(DateUtils.getNowDate());
        tLcInfoup.setUpdateBy(ShiroUtils.getSysUser().getLoginName());
        tLcInfoup.setUpdateById(ShiroUtils.getUserId());
        tLcInfoup.setUpdateTime(DateUtils.getNowDate());
        int rl = tLcInfoupMapper.insertTLcInfoup(tLcInfoup);
        //更新岗位 更新任务状态是0 更新状态为待提交
        if(tup.getInfoupStatus()==0){
            TLcTaskInfoup tLcTaskInfoup = new TLcTaskInfoup();
            tLcTaskInfoup.setInfoupBy(ShiroUtils.getSysUser().getLoginName());
            tLcTaskInfoup.setInfoupTime(DateUtils.getNowDate());
            tLcTaskInfoup.setInfoupStatus(1);
            tLcTaskInfoup.setIds(new BigInteger[]{BigInteger.valueOf(tLcInfoup.getTaskInfoId())});
            tLcTaskInfoupMapper.updateStatus(tLcTaskInfoup);
            //更新我的任务 状态待提交
            tLcTaskMapper.updateInfoUp(new BigInteger[]{BigInteger.valueOf(tLcInfoup.getTaskId())},1);
        }
        return rl;
    }

    private void lianxphone(TLcTaskInfoup tup,TLcInfoup tLcInfoup){
        //判断电话是否已经存在联系人表中 如果是地址类型则返回
        if(1 == tLcInfoup.getTypes())return;
        TLcCustContact tLcCustContact = new TLcCustContact();
        tLcCustContact.setPhone(tLcInfoup.getPhone());
        tLcCustContact.setCaseNo(tup.getCaseNo());
        tLcCustContact.setOrgId(tup.getOrgId());
        tLcCustContact.setImportBatchNo(tup.getImportBatchNo());
        //根据电话、编号、机构id、批次 查询是否存在
        List<TLcCustContact> list = tLcCustContactMapper.selectTLcCustContactList(tLcCustContact);
        if(null != list && !list.isEmpty()){
            throw new RuntimeException("电话号码已经存在联系人中");
        }
        TLcInfoup tLcInfoupN = new TLcInfoup();
        tLcInfoupN.setOrgId(tup.getOrgId());
        tLcInfoupN.setId(tLcInfoup.getId());
        tLcInfoupN.setImportBatchNo(tup.getImportBatchNo());
        tLcInfoupN.setCaseNo(tup.getCaseNo());
        tLcInfoupN.setPhone(tLcInfoup.getPhone());
        List<TLcInfoup> lt = tLcInfoupMapper.selectTLcInfoupList(tLcInfoupN);
        if(null != lt && !lt.isEmpty()){
            throw new RuntimeException("电话号码已经存在");
        }
    }



    /**
     * 修改【联系人增加】
     * 
     * @param tLcInfoup 【联系人增加】
     * @return 结果
     */
    @Override
    public int updateTLcInfoup(TLcInfoup tLcInfoup)
    {
        TLcTaskInfoup tup = tLcTaskInfoupMapper.selectTLcTaskInfoupById(tLcInfoup.getTaskInfoId());
        if(null == tup)throw new RuntimeException("任务不存在");
        lianxphone(tup,tLcInfoup);
        tLcInfoup.setUpdateBy(ShiroUtils.getSysUser().getLoginName());
        tLcInfoup.setUpdateById(ShiroUtils.getUserId());
        tLcInfoup.setUpdateTime(DateUtils.getNowDate());
        return tLcInfoupMapper.updateTLcInfoup(tLcInfoup);
    }

    /**
     * 联系人新增或更新
     * @param tLcInfoup
     */
    private void tLcCustContactIf(TLcInfoup tLcInfoup){
        //增加联系人
        TLcTaskInfoup tLcTaskInfoup = tLcTaskInfoupMapper.selectTLcTaskInfoupById(tLcInfoup.getTaskId());
        TLcCustContact tLcCustContact = new TLcCustContact();
        tLcCustContact.setPhone(tLcInfoup.getPhone());
        tLcCustContact.setCaseNo(tLcTaskInfoup.getCaseNo());
        tLcCustContact.setOrgId(tLcTaskInfoup.getOrgId());
        tLcCustContact.setImportBatchNo(tLcTaskInfoup.getImportBatchNo());
        //根据电话、编号、机构id、批次 查询是否存在
        List<TLcCustContact> list = tLcCustContactMapper.selectTLcCustContactList(tLcCustContact);
        tLcCustContact.setInfoupId(tLcInfoup.getId());
        tLcCustContact.setInfoupStatus(1);
        tLcCustContact.setContactName(tLcInfoup.getNames());
        tLcCustContact.setRelation(tLcInfoup.getRelations());
        tLcCustContact.setRemark(tLcInfoup.getRemark());
        tLcCustContact.setCreateBy(ShiroUtils.getUserId().toString());
        tLcCustContact.setModifyBy(ShiroUtils.getUserId());
        tLcCustContact.setOrigin(ContactOriginEnum.PAGE_ADD.getCode());
        if(null != tLcInfoup.getId()){
            tLcCustContact.setInfoupId(tLcInfoup.getId());
        }
        //没有存在则新增联系人数据
        if(null == list || list.isEmpty()){
            tLcCustContact.setCertificateNo(tLcTaskInfoup.getCertificateNo());
            tLcCustContactMapper.insertTLcCustContact(tLcCustContact);
        }else{
            tLcCustContactMapper.updateTLcCustContact(tLcCustContact);
        }
    }

    /**
     * 删除【联系人增加】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcInfoupByIds(String ids)
    {
        return tLcInfoupMapper.deleteTLcInfoupByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【联系人增加】信息
     * 
     * @param id 【联系人增加】ID
     * @return 结果
     */
    @Override
    public int deleteTLcInfoupById(Long id)
    {
        return tLcInfoupMapper.deleteTLcInfoupById(id);
    }
}
