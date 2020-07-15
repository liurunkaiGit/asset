package com.ruoyi.batchcall.service.impl;

import com.ruoyi.batchcall.domain.TLcBatchCall;
import com.ruoyi.batchcall.mapper.TLcBatchCallMapper;
import com.ruoyi.batchcall.service.ITLcBatchCallService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.service.ITLcCustContactService;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 批量外呼任务管理Service业务层处理
 * 
 * @author 封志涛
 * @date 2020-07-02
 */
@Service
public class TLcBatchCallServiceImpl implements ITLcBatchCallService
{
    @Autowired
    private TLcBatchCallMapper tLcBatchCallMapper;
    @Autowired
    private ITLcCustContactService tLcCustContactService;

    /**
     * 查询批量外呼任务管理
     * 
     * @param id 批量外呼任务管理ID
     * @return 批量外呼任务管理
     */
    @Override
    public TLcBatchCall selectTLcBatchCallById(Long id)
    {
        return tLcBatchCallMapper.selectTLcBatchCallById(id);
    }

    /**
     * 查询批量外呼任务管理列表
     * 
     * @param tLcBatchCall 批量外呼任务管理
     * @return 批量外呼任务管理
     */
    @Override
    public List<TLcBatchCall> selectTLcBatchCallList(TLcBatchCall tLcBatchCall)
    {
        return tLcBatchCallMapper.selectTLcBatchCallList(tLcBatchCall);
    }

    /**
     * 新增批量外呼任务管理
     * 
     * @param tLcBatchCall 批量外呼任务管理
     * @return 结果
     */
    @Override
    public int insertTLcBatchCall(TLcBatchCall tLcBatchCall)
    {
        tLcBatchCall.setCreateTime(DateUtils.getNowDate());
        return tLcBatchCallMapper.insertTLcBatchCall(tLcBatchCall);
    }

    /**
     * 修改批量外呼任务管理
     * 
     * @param tLcBatchCall 批量外呼任务管理
     * @return 结果
     */
    @Override
    public int updateTLcBatchCall(TLcBatchCall tLcBatchCall)
    {
        return tLcBatchCallMapper.updateTLcBatchCall(tLcBatchCall);
    }

    /**
     * 删除批量外呼任务管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcBatchCallByIds(String ids)
    {
        return tLcBatchCallMapper.deleteTLcBatchCallByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除批量外呼任务管理信息
     * 
     * @param id 批量外呼任务管理ID
     * @return 结果
     */
    @Override
    public int deleteTLcBatchCallById(Long id)
    {
        return tLcBatchCallMapper.deleteTLcBatchCallById(id);
    }

    @Override
    public int updateBatchCallByBatchNo(String createBy, Integer sourceTaskStatus, Integer targetTaskStatus) {
        return tLcBatchCallMapper.updateBatchCallByBatchNo(createBy,sourceTaskStatus,targetTaskStatus);
    }

    @Override
    public int insertTLcBatchCallByTask(String isCallOther,String exonNum, String[] caseNoArray, String[] importBatchNoArray,String orgId) {
        List<TLcCustContact> totalCustContactList = new ArrayList<TLcCustContact>();
        if(caseNoArray != null && caseNoArray.length > 0){
            TLcCustContact tcc = new TLcCustContact();
            tcc.setCaseNoList(Arrays.asList(caseNoArray));
            tcc.setImportBatchNoList(Arrays.asList(importBatchNoArray));
            if("0".equals(isCallOther)){//只有本人
                tcc.setRelation(1);
            }
            totalCustContactList = this.tLcCustContactService.selectTLcCustContactList(tcc);
            /*TLcCustContact tLcCustContact = null;
            for(int i = 0;i < caseNoArray.length; i ++){
                tLcCustContact = new TLcCustContact();
                tLcCustContact.setCaseNo(caseNoArray[i]);
                tLcCustContact.setImportBatchNo(importBatchNoArray[i]);
                tLcCustContact.setIsClose("0");//正常，未停播
                if("0".equals(isCallOther)){//只拨打本人
                    tLcCustContact.setRelation(1);
                }
                List<TLcCustContact> tmpList = this.tLcCustContactService.selectTLcCustContactList(tLcCustContact);
                if(!tmpList.isEmpty()){
                    totalCustContactList.addAll(tmpList);
                }
            }*/
        }

        if(totalCustContactList.size() > 0){
            TLcCustContact tLcCustContact = null;
            TLcBatchCall tLcBatchCall = null;
            int batchNo = this.selectMaxBatchNo(ShiroUtils.getUserId().toString());
            batchNo = batchNo + 1;
            for(int i = 0;i < totalCustContactList.size();i ++){
                tLcCustContact = totalCustContactList.get(i);
                tLcBatchCall = new TLcBatchCall();
                tLcBatchCall.setBatchNo(batchNo);//自动外呼批次号
                tLcBatchCall.setCaseNo(tLcCustContact.getCaseNo());
                tLcBatchCall.setContactName(tLcCustContact.getContactName());
                tLcBatchCall.setContactRelation(tLcCustContact.getRelation());
                tLcBatchCall.setPhone(tLcCustContact.getPhone());//手机号
                tLcBatchCall.setExonNum(exonNum);//外显号码
                tLcBatchCall.setOrgId(orgId);//委托结构ID
                tLcBatchCall.setImportBatchNo(tLcCustContact.getImportBatchNo());//案件导入批次号
                tLcBatchCall.setPhoneType(TLcBatchCall.SHOUJI);//手机
                tLcBatchCall.setTaskStatus(TLcBatchCall.DWH);//待外呼
                tLcBatchCall.setCreateTime(new Date());
                tLcBatchCall.setCreateBy(ShiroUtils.getUserId().toString());
                this.tLcBatchCallMapper.insertTLcBatchCall(tLcBatchCall);
                //暂不考虑固话
                /*if(StringUtils.isNotEmpty(tLcCustContact.getTel())){//固话不为空，则要再新增一条固话的外呼数据
                    *//*tLcBatchCall = new TLcBatchCall();
                    tLcBatchCall.setBatchNo(1);
                    tLcBatchCall.setCaseNo(tLcCustContact.getCaseNo());
                    tLcBatchCall.setContactName(tLcCustContact.getContactName());
                    tLcBatchCall.setContactRelation(tLcCustContact.getRelation());*//*
                    tLcBatchCall.setPhone(tLcCustContact.getTel());//固话
                    tLcBatchCall.setPhoneType(TLcBatchCall.GUHUA);//固话
                    *//*tLcBatchCall.setTaskStatus(1);//待外呼
                    tLcBatchCall.setCreateTime(new Date());
                    tLcBatchCall.setCreateBy(ShiroUtils.getUserId().toString());*//*
                    this.tLcBatchCallMapper.insertTLcBatchCall(tLcBatchCall);
                }*/
            }
        }
        return 1;
    }

    @Override
    public int selectMaxBatchNo(String createBy) {
        Map<String,Object> map = this.tLcBatchCallMapper.selectMaxBatchNo(createBy);
        int batchNo = Integer.parseInt(map.get("batch_no").toString());
        return batchNo;
    }
}
