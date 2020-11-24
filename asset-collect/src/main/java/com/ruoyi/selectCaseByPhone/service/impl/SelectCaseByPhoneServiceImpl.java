package com.ruoyi.selectCaseByPhone.service.impl;

import com.ruoyi.selectCaseByPhone.domain.SelectCaseByPhone;
import com.ruoyi.selectCaseByPhone.mapper.SelectCaseByPhoneMapper;
import com.ruoyi.selectCaseByPhone.service.ISelectCaseByPhoneService;
import com.ruoyi.task.domain.TLcTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 根据手机号码查询案件Service业务层处理
 * 
 * @author guozeqi
 * @date 2020-11-16
 */
@Service
public class SelectCaseByPhoneServiceImpl implements ISelectCaseByPhoneService
{
    @Autowired
    private SelectCaseByPhoneMapper selectCaseByPhoneMapper;


    /**
     * 查询证件号码配置列表
     * 
     * @param selectCaseByPhone
     * @return
     */
    @Override
    public List<SelectCaseByPhone> selectCaseByPhoneList(SelectCaseByPhone selectCaseByPhone)
    {
        return selectCaseByPhoneMapper.selectCaseByPhoneList(selectCaseByPhone);
    }

    @Override
    public Map<String, BigDecimal> selectTotalCountMoney(TLcTask tLcTask) {
        return selectCaseByPhoneMapper.selectTotalCountMoney(tLcTask);
    }

    @Override
    public List<TLcTask> selectMyTaskList(TLcTask tLcTask) {
        return selectCaseByPhoneMapper.selectMyTaskList(tLcTask);
    }


}
