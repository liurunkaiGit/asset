package com.ruoyi.task.service;

import com.ruoyi.custom.domain.TLcCustContact;

import java.util.List;

/**
 * 异步号码查询
 * @author guozeqi
 * @create 2020-12-7
 */

public interface IAsyncSelectPhoneStatus {

    public void selectPhoneStatus(List<TLcCustContact> custContactList, String orgId, String orgName, String loginName);

}
