package com.ruoyi.inforeporting.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.inforeporting.domain.TLcInforeportingReduction;
import com.ruoyi.inforeporting.domain.TLcInforeportingTemplate;
import com.ruoyi.inforeporting.mapper.TLcInforeportingReductionMapper;
import com.ruoyi.inforeporting.service.TLcInforeportingReductionService;
import com.ruoyi.task.domain.TLcTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 上报信息-减免
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Service
public class TLcInforeportingReductionServiceImpl implements TLcInforeportingReductionService {

    @Autowired
    private TLcInforeportingReductionMapper irm;

    @Override
    public int insertTLcInforeportingReduction(TLcInforeportingReduction inforeportingReduction) {
        return irm.insertTLcInforeportingReduction(inforeportingReduction);
    }

    @Override
    public TLcTask selectTLcTaskByCaseNo(String caseNo, String orgId, String importBatchNo) {
        return irm.selectTLcTaskByCaseNo(caseNo,orgId,importBatchNo);
    }

    @Override
    public List<TLcInforeportingReduction> selectTLcInforeportingReductionList(TLcInforeportingReduction inforeportingReduction) {
        return irm.selectTLcInforeportingReductionList(inforeportingReduction);
    }

    @Override
    public List<TLcInforeportingTemplate> selectTLcInforeportingAllList(TLcInforeportingTemplate tLcInforeportingTemplate) {
        return irm.selectTLcInforeportingAllList(tLcInforeportingTemplate);
    }

    @Override
    public int rejectTLcInforeportingReductionByIds(String ids) {
        return irm.rejectTLcInforeportingReductionByIds(Convert.toLongArray(ids));
    }

}
