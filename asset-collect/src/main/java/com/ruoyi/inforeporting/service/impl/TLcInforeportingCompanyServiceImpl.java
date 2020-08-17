package com.ruoyi.inforeporting.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.inforeporting.domain.TLcInforeportingCompany;
import com.ruoyi.inforeporting.mapper.TLcInforeportingCompanyMapper;
import com.ruoyi.inforeporting.service.TLcInforeportingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 上报信息-对公入账
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Service
public class TLcInforeportingCompanyServiceImpl implements TLcInforeportingCompanyService {

    @Autowired
    private TLcInforeportingCompanyMapper ibm;

    @Override
    public int insertTLcInforeportingCompany(TLcInforeportingCompany inforeportingCompany) {
        return ibm.insertTLcInforeportingCompany(inforeportingCompany);
    }

    @Override
    public List<TLcInforeportingCompany> selectTLcInforeportingCompanyList(TLcInforeportingCompany inforeportingCompany) {
        return ibm.selectTLcInforeportingCompanyList(inforeportingCompany);
    }

    @Override
    public int rejectTLcInforeportingCompanyByIds(String ids) {
        return ibm.rejectTLcInforeportingCompanyByIds(Convert.toLongArray(ids));
    }
}
