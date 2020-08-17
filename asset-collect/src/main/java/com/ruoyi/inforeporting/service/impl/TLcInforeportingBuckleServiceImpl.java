package com.ruoyi.inforeporting.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.inforeporting.domain.TLcInforeportingBuckle;
import com.ruoyi.inforeporting.mapper.TLcInforeportingBuckleMapper;
import com.ruoyi.inforeporting.service.TLcInforeportingBuckleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 上报信息-逾期划扣
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Service
public class TLcInforeportingBuckleServiceImpl implements TLcInforeportingBuckleService {

    @Autowired
    private TLcInforeportingBuckleMapper ibm;

    @Override
    public int insertTLcInforeportingBuckle(TLcInforeportingBuckle inforeportingBuckle) {
        return ibm.insertTLcInforeportingBuckle(inforeportingBuckle);
    }

    @Override
    public List<TLcInforeportingBuckle> selectTLcInforeportingBuckleList(TLcInforeportingBuckle inforeportingBuckle) {
        return ibm.selectTLcInforeportingBuckleList(inforeportingBuckle);
    }

    @Override
    public int rejectTLcInforeportingBuckleByIds(String ids) {
        return ibm.rejectTLcInforeportingBuckleByIds(Convert.toLongArray(ids));
    }
}
