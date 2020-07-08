package com.ruoyi.radioQc.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RestTemplateUtil;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.service.ITLcCustContactService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.radioQc.domain.TLcSendRadioQcRecord;
import com.ruoyi.radioQc.mapper.TLcSendRadioQcRecordMapper;
import com.ruoyi.radioQc.service.ITLcSendRadioQcRecordService;
import com.ruoyi.task.domain.QualityCheckResponse;
import com.ruoyi.task.domain.RadioQualityCheck;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.mapper.TLcCallRecordMapper;
import com.ruoyi.task.service.ITLcTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author liurunkai
 * @date 2020-03-10
 */
@Slf4j
@Service
public class TLcSendRadioQcRecordServiceImpl implements ITLcSendRadioQcRecordService {

    @Value("${radioQualityCheck.sendRadio}")
    private String sendRadioQualityCheck;

    @Autowired
    private TLcCallRecordMapper tLcCallRecordMapper;
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private TLcSendRadioQcRecordMapper tLcSendRadioQcRecordMapper;
    @Autowired
    private ITLcCustContactService tLcCustContactService;
    @Autowired
    private ITLcTaskService tLcTaskService;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TLcSendRadioQcRecord selectTLcSendRadioQcRecordById(Long id) {
        return tLcSendRadioQcRecordMapper.selectTLcSendRadioQcRecordById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcSendRadioQcRecord 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TLcSendRadioQcRecord> selectTLcSendRadioQcRecordList(TLcSendRadioQcRecord tLcSendRadioQcRecord) {
        return tLcSendRadioQcRecordMapper.selectTLcSendRadioQcRecordList(tLcSendRadioQcRecord);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcSendRadioQcRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTLcSendRadioQcRecord(TLcSendRadioQcRecord tLcSendRadioQcRecord) {
        tLcSendRadioQcRecord.setCreateTime(DateUtils.getNowDate());
        return tLcSendRadioQcRecordMapper.insertTLcSendRadioQcRecord(tLcSendRadioQcRecord);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcSendRadioQcRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTLcSendRadioQcRecord(TLcSendRadioQcRecord tLcSendRadioQcRecord) {
        return tLcSendRadioQcRecordMapper.updateTLcSendRadioQcRecord(tLcSendRadioQcRecord);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcSendRadioQcRecordByIds(String ids) {
        return tLcSendRadioQcRecordMapper.deleteTLcSendRadioQcRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTLcSendRadioQcRecordById(Long id) {
        return tLcSendRadioQcRecordMapper.deleteTLcSendRadioQcRecordById(id);
    }

    @Override
    @Async
    public void sendRadioToQualityCheck(TLcCallRecord tLcCallRecord, OrgPackage orgPackage) {
        RadioQualityCheck radioQualityCheck = createRadioQualityCheck(tLcCallRecord,orgPackage);
        ResponseEntity<QualityCheckResponse> qualityCheckResponse = restTemplateUtil.getRestTemplate().postForEntity(sendRadioQualityCheck, radioQualityCheck, QualityCheckResponse.class);
        log.info("推送语音质检返回的结果数据：{}", JSON.toJSONString(qualityCheckResponse));
        if (qualityCheckResponse.getStatusCodeValue() == HttpStatus.OK.value() && qualityCheckResponse.getBody().getCode() == 0) {
//            QualityCheckResponse checkResponseBody = qualityCheckResponse.getBody();
//            TLcSendRadioQcRecord tLcSendRadioQcRecord = TLcSendRadioQcRecord.builder()
//                    .callRecordId(tLcCallRecord.getId())
//                    .custName(tLcCallRecord.getContactName())
//                    .custPhone(tLcCallRecord.getPhone())
//                    .radioUrl(tLcCallRecord.getCallRadioLocation())
//                    .callStartTime(tLcCallRecord.getCallStartTime())
//                    .callEndTime(tLcCallRecord.getCallEndTime())
//                    .status(checkResponseBody.getSuccess() ? IsNoEnum.IS.getCode() : IsNoEnum.NO.getCode())
//                    .build();
//            this.tLcSendRadioQcRecordMapper.insertTLcSendRadioQcRecord(tLcSendRadioQcRecord);
            log.info("通话记录id为：{}推送录音到录音质检系统成功",tLcCallRecord.getId());
        } else {
            log.error("通话记录id为：{}推送录音到录音质检系统失败",tLcCallRecord.getId());
        }
    }

    /**
     * 创建通话录音质量检核对象
     *
     * @param tLcCallRecord
     * @param orgPackage
     * @return
     */
    private RadioQualityCheck createRadioQualityCheck(TLcCallRecord tLcCallRecord, OrgPackage orgPackage) {
        //查询联系人信息
        TLcCustContact findParam = new TLcCustContact();
        findParam.setContactName(tLcCallRecord.getContactName());
        findParam.setPhone(tLcCallRecord.getPhone());
        findParam.setCaseNo(tLcCallRecord.getCaseNo());
        List<TLcCustContact> tLcCustContacts = tLcCustContactService.selectTLcCustContactList(findParam);

        RadioQualityCheck radioQualityCheck = new RadioQualityCheck();
        RadioQualityCheck.RequestData requestData = radioQualityCheck.new RequestData();
        RadioQualityCheck.Staff staff = radioQualityCheck.new Staff();
        RadioQualityCheck.Customer customer = radioQualityCheck.new Customer();
        ArrayList<RadioQualityCheck.RequestData> requestDataList = new ArrayList<>();
//        staff.setId(ShiroUtils.getUserId().toString()).setName(ShiroUtils.getSysUser().getLoginName());
        staff.setId(ShiroUtils.getSysUser().getJobNo()).setName(ShiroUtils.getSysUser().getLoginName());
//        customer.setId(String.valueOf(ShiroUtils.getUserId())).setName(tLcCallRecord.getContactName()).setPhone(tLcCallRecord.getPhone());
        if(tLcCustContacts.size()>0){
            String id = String.valueOf(tLcCustContacts.get(0).getId());
            customer.setId(id).setName(tLcCallRecord.getContactName()).setPhone(tLcCallRecord.getPhone());
        }else {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            customer.setId(uuid).setName(tLcCallRecord.getContactName()).setPhone(tLcCallRecord.getPhone());
        }
        requestData.setSource_id(tLcCallRecord.getId().toString()).setUrl(tLcCallRecord.getCallRadioLocation()).setTimestamp(tLcCallRecord.getCallStartTime().getTime()/1000).setCategory("").setCustomer(customer).setStaff(staff);
        requestDataList.add(requestData);
        radioQualityCheck.setData(requestDataList).setProjectName(orgPackage.getOrgCode());
        return radioQualityCheck;
    }





}


