package com.ruoyi.task.service.impl;

import com.ruoyi.assetspackage.domain.phoneStatus.PhoneStatus;
import com.ruoyi.assetspackage.domain.phoneStatus.PhoneStatusRequestData;
import com.ruoyi.assetspackage.domain.phoneStatus.PhoneStatusResponse;
import com.ruoyi.assetspackage.mapper.PhoneStatusMapper;
import com.ruoyi.assetspackage.util.PhoneStatusUtil;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.mapper.TLcCustContactMapper;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.mapper.TLcTaskMapper;
import com.ruoyi.task.service.IAsyncSelectPhoneStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 异步号码查询
 *
 * @author guozeqi
 * @create 2020-12-7
 */
@Slf4j
@Service
public class AsyncSelectPhoneStatusImpl implements IAsyncSelectPhoneStatus {

    @Autowired
    private TLcCustContactMapper tLcCustContactMapper;

    @Autowired
    private PhoneStatusMapper phoneStatusMapper;

    @Autowired
    private TLcTaskMapper tLcTaskMapper;

    @Async
    @Override
    public void selectPhoneStatus(List<TLcCustContact> custContactList, String orgId, String orgName, String loginName) {
        //获取号码状态
        if (custContactList.size() > 0) {
            for (TLcCustContact custContact : custContactList) {
                PhoneStatusRequestData reqData = new PhoneStatusRequestData();
                String certificateNo = custContact.getCertificateNo();
                String contactName = custContact.getContactName();
                String phone = custContact.getPhone();
                if (!checkPhone(phone)) {//去除非手机号
                    continue;
                }
                reqData.setId(certificateNo);
                reqData.setName(contactName);
                reqData.setCell(phone);
                PhoneStatusResponse response = PhoneStatusUtil.getPhoneStatus(reqData);
                this.responseHandler(response, custContact, orgId, orgName, loginName);
            }
        }
    }

    @Transactional
    public void responseHandler(PhoneStatusResponse response, TLcCustContact custContact, String orgId, String orgName, String loginName) {
        Date curDate = new Date();
        Map<String, Integer> map = new HashMap<>();
        String swift_number = response.getSwift_number();
        String phoneStaus = null;
        if ("00".equals(response.getCode()) && "600000".equals(response.getCodeDetail().getPhoneStatus())) {
            phoneStaus = response.getPhoneStatus().getResult();
        }
        if (phoneStaus != null) {
            //更新联系人号码状态
            TLcCustContact updateParam = new TLcCustContact();
            updateParam.setPhone(custContact.getPhone());
            updateParam.setPhoneStatus(phoneStaus);
            this.tLcCustContactMapper.updatePhoneStatus(updateParam);
            //插入号码状态表
            PhoneStatus insertParam = new PhoneStatus();
            insertParam.setCaseNo(custContact.getCaseNo());
            insertParam.setWaje(custContact.getArrearsTotal());
            insertParam.setJayhje(custContact.getCloseCaseYhje());
            insertParam.setPhone(custContact.getPhone());
            insertParam.setRelation(custContact.getRelation());
            insertParam.setPhonestatus(phoneStaus);
            insertParam.setOrgId(orgId);
            insertParam.setOrgName(orgName);
            insertParam.setCreateBy(loginName);
            insertParam.setCreateTime(curDate);
            insertParam.setFlowNo(swift_number);
            this.phoneStatusMapper.insertPhoneStatus(insertParam);
            //如果可联更新任务表案件状态(案件状态只能由 不可联-->可联)
            if ("2".equals(phoneStaus) || "31".equals(phoneStaus) || "32".equals(phoneStaus) || "33".equals(phoneStaus)) {
                TLcTask param = new TLcTask();
                param.setCaseNo(custContact.getCaseNo());
                param.setOrgId(orgId);
                param.setPhoneStatus("1");//0不可联,1可联
                this.tLcTaskMapper.updatePhoneStatus(param);
            }
        } else {
            //更新联系人号码状态
            TLcCustContact updateParam = new TLcCustContact();
            updateParam.setPhone(custContact.getPhone());
            updateParam.setPhoneStatus("-1");//失败
            this.tLcCustContactMapper.updatePhoneStatus(updateParam);
            //插入号码状态表
            PhoneStatus insertParam = new PhoneStatus();
            insertParam.setCaseNo(custContact.getCaseNo());
            insertParam.setWaje(custContact.getArrearsTotal());
            insertParam.setJayhje(custContact.getCloseCaseYhje());
            insertParam.setPhone(custContact.getPhone());
            insertParam.setRelation(custContact.getRelation());
            insertParam.setPhonestatus("-1");//失败
            insertParam.setOrgId(orgId);
            insertParam.setOrgName(orgName);
            insertParam.setCreateBy(loginName);
            insertParam.setCreateTime(curDate);
            insertParam.setFlowNo(swift_number);
            this.phoneStatusMapper.insertPhoneStatus(insertParam);
        }
    }

    public boolean checkPhone(String phone) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        String regex = "^[1](([3|5|8][\\d])|([4][5,6,7,8,9])|([6][5,6])|([7][3,4,5,6,7,8])|([9][8,9]))[\\d]{8}$";// 验证手机号
        if (StringUtils.isNotBlank(phone) && phone.length() == 11) {
            p = Pattern.compile(regex);
            m = p.matcher(phone);
            b = m.matches();
        }
        return b;
    }


}
