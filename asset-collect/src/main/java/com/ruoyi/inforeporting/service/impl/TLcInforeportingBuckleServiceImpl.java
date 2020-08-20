package com.ruoyi.inforeporting.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.inforeporting.domain.TLcInforeportingBuckle;
import com.ruoyi.inforeporting.domain.TLcInforeportingBuckleXing;
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

    @Override
    public AjaxResult exportExcel(TLcInforeportingBuckle inforeportingBuckle) {
        if(null != inforeportingBuckle.getOrgId() && 207 == inforeportingBuckle.getOrgId().longValue()){
            //兴业消费金融
            List<TLcInforeportingBuckleXing> list = ibm.selectTLcInforeportingBuckleXingList(inforeportingBuckle);
            return new ExcelUtil<TLcInforeportingBuckleXing>(TLcInforeportingBuckleXing.class).exportExcel(list,"逾期划扣");
        }
        List<TLcInforeportingBuckle> list = ibm.selectTLcInforeportingBuckleListExp(inforeportingBuckle);
        return new ExcelUtil<TLcInforeportingBuckle>(TLcInforeportingBuckle.class).exportExcel(list,"逾期划扣");
    }

}
