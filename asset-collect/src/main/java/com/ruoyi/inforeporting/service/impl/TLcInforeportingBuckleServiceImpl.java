package com.ruoyi.inforeporting.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.inforeporting.domain.*;
import com.ruoyi.inforeporting.mapper.TLcInforeportingBuckleMapper;
import com.ruoyi.inforeporting.service.TLcInforeportingBuckleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        }else if(null != inforeportingBuckle.getOrgId() && 206 == inforeportingBuckle.getOrgId().longValue()){
            //中银
            List<TLcInforeportingBuckle> list = ibm.selectTLcInforeportingBuckleListExp(inforeportingBuckle);
            return new ExcelUtil<TLcInforeportingBuckleZhongyin>(TLcInforeportingBuckleZhongyin.class).exportExcel(transformationBuckleZhongyin(list),"逾期划扣");
        }
        List<TLcInforeportingBuckle> list = ibm.selectTLcInforeportingBuckleListExp(inforeportingBuckle);
        return new ExcelUtil<TLcInforeportingBuckle>(TLcInforeportingBuckle.class).exportExcel(list,"逾期划扣");
    }

    /**
     * @param list
     * @return 中银 -导出模板转化
     */
    private  List<TLcInforeportingBuckleZhongyin> transformationBuckleZhongyin(List<TLcInforeportingBuckle> list){
        List<TLcInforeportingBuckleZhongyin> listExp = new ArrayList<TLcInforeportingBuckleZhongyin>();
        int i=0;
        for(TLcInforeportingBuckle te:list){
            TLcInforeportingBuckleZhongyin tn = new TLcInforeportingBuckleZhongyin();
            tn.setXh(++i);
            tn.setCaseNo(te.getCaseNo());
            tn.setCreateBy(te.getCreateBy());
            tn.setCreateTime(te.getCreateTime());
            tn.setCreateTime2(te.getCreateTime());
            tn.setCustomName(te.getCustomName());
            tn.setDeductionAmount(te.getDeductionAmount());
            tn.setProductName(te.getProductName());
            tn.setRemarks(te.getRemarks());
            if("已核销".equals(te.getTransferType())){
                tn.setStage("已核销");
            }else{
                tn.setStage("未核销");
            }
            listExp.add(tn);
        }
        return listExp;
    }

}
