package com.ruoyi.inforeporting.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.inforeporting.domain.TLcInforeportingSet;
import com.ruoyi.inforeporting.mapper.TLcInforeportingSetMapper;
import com.ruoyi.inforeporting.service.TLcInforeportingSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TLcInforeportingSetServiceImpl implements TLcInforeportingSetService {

    @Autowired
    private TLcInforeportingSetMapper inforeportingSetMapper;



    @Override
    public int insertTLcInforeportingSet(TLcInforeportingSet inforeportingSet) {
        return inforeportingSetMapper.insertTLcInforeportingSet(inforeportingSet);
    }

    @Override
    public List<TLcInforeportingSet> selectTLcInforeportingSetList(TLcInforeportingSet inforeportingSet) {
        return inforeportingSetMapper.selectTLcInforeportingSetList(inforeportingSet);
    }

    @Override
    public List<TLcInforeportingSet> selectTLcInforeportingSetByOrgIdAndTypeList(TLcInforeportingSet inforeportingSet) {
        if( inforeportingSet.getOrgId() == 0){
            return null;
        }
        if(inforeportingSet.getReportingType() == 0){
            return null;
        }
        return inforeportingSetMapper.selectTLcInforeportingSetByOrgIdAndTypeList(inforeportingSet);
    }

    @Override
    public int deleteTLcInforeportingSetByIds(String ids) {
        return inforeportingSetMapper.deleteTLcInforeportingSetByIds(Convert.toLongArray(ids));
    }

    @Override
    public int updateTLcInforeportingSet(TLcInforeportingSet inforeportingSet) {
        return inforeportingSetMapper.updateTLcInforeportingSet(inforeportingSet);
    }

    @Override
    public TLcInforeportingSet selectTLcInforeportingSetById(Long id) {
        return inforeportingSetMapper.selectTLcInforeportingSetById(id);
    }

}
