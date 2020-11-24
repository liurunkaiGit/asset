package com.ruoyi.selectCaseByPhone.service;

import com.ruoyi.selectCaseByPhone.domain.SelectCaseByPhone;
import com.ruoyi.task.domain.TLcTask;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 根据手机号查案件Service接口
 * 
 * @author guozeqi
 * @date 2020-11-16
 */
public interface ISelectCaseByPhoneService
{


    /**
     * 根据手机号查案件列表
     * 
     * @param selectCaseByPhone
     * @return
     */
    public List<SelectCaseByPhone> selectCaseByPhoneList(SelectCaseByPhone selectCaseByPhone);

    Map<String, BigDecimal> selectTotalCountMoney(TLcTask tLcTask);

    List<TLcTask> selectMyTaskList(TLcTask tLcTask);


}
