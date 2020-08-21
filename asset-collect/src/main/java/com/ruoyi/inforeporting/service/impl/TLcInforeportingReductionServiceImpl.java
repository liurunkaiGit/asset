package com.ruoyi.inforeporting.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.inforeporting.domain.*;
import com.ruoyi.inforeporting.mapper.TLcInforeportingReductionMapper;
import com.ruoyi.inforeporting.service.TLcInforeportingReductionService;
import com.ruoyi.task.domain.TLcTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
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

    @Override
    public AjaxResult exportExcel(TLcInforeportingReduction inforeportingReduction) {
        if(null != inforeportingReduction.getOrgId() && 207 == inforeportingReduction.getOrgId().longValue()){
            //兴业消费金融
            List<TLcInforeportingReductionXing> list = irm.selectTLcInforeportingReductionXingList(inforeportingReduction);
            return new ExcelUtil<TLcInforeportingReductionXing>(TLcInforeportingReductionXing.class).exportExcel(list,"减免");
        }else if(null != inforeportingReduction.getOrgId() && 212 == inforeportingReduction.getOrgId().longValue()){
            //捷信消金
            List<TLcInforeportingReduction> list = irm.selectTLcInforeportingReductionList(inforeportingReduction);
            return new ExcelUtil<TLcInforeportingReductionJiexin>(TLcInforeportingReductionJiexin.class).exportExcel(transformationTLcInforeportingReductionJiexin(list),"减免");
        }else if(null != inforeportingReduction.getOrgId() && 206 == inforeportingReduction.getOrgId().longValue()){
            //中银减免
            List<TLcInforeportingReduction> list = irm.selectTLcInforeportingReductionList(inforeportingReduction);
            return new ExcelUtil<TLcInforeportingReductionZhongyin>(TLcInforeportingReductionZhongyin.class).exportExcel(transformationTLcInforeportingReductionZhongyin(list),"减免");
        }
        List<TLcInforeportingReduction> list = irm.selectTLcInforeportingReductionListExp(inforeportingReduction);
        return new ExcelUtil<TLcInforeportingReduction>(TLcInforeportingReduction.class).exportExcel(list,"减免");
    }

    /**
     * @param list 减免数据集合
     * @return 将集合转换 捷信 导出集合
     */
    private List<TLcInforeportingReductionJiexin> transformationTLcInforeportingReductionJiexin(List<TLcInforeportingReduction> list ){
        List<TLcInforeportingReductionJiexin> listxin = new ArrayList<TLcInforeportingReductionJiexin>();
        int i=0;
        for(TLcInforeportingReduction tn:list){
            TLcInforeportingReductionJiexin jx = new TLcInforeportingReductionJiexin();
            jx.setXh(++i);
            jx.setCaseNo(tn.getCaseNo());
            jx.setCustomName(tn.getCustomName());
            jx.setArrearsTotal(tn.getArrearsTotal());
            jx.setDeductionAmount(tn.getDeductionAmount());
            jx.setOverdueDays(tn.getOverdueDays());
            double afterAmount = 0;
            String result="";
            if(null != tn.getArrearsTotal()){
                afterAmount = tn.getArrearsTotal()-tn.getDeductionAmount();
                if(null != tn.getDeductionAmount()){
                    NumberFormat numberFormat = NumberFormat.getInstance();
                    numberFormat.setMaximumFractionDigits(2);
                    result = numberFormat.format( afterAmount /  tn.getArrearsTotal() * 100)+"%";
                }
            }

            jx.setReductionAfterAmount(afterAmount);
            jx.setRepaymentRatio(result);
            jx.setArea(tn.getArea());
            jx.setTelephone(tn.getTelephone());
            jx.setRelationship(tn.getRelationship());
            jx.setExpirationDate(tn.getExpirationDate());
            listxin.add(jx);
        }
        return listxin;
    }

    /**
     * @param list 减免数据集合
     * @return 将集合转换 捷信 导出集合
     *
     */
    private List<TLcInforeportingReductionZhongyin> transformationTLcInforeportingReductionZhongyin(List<TLcInforeportingReduction> list ){
        List<TLcInforeportingReductionZhongyin> listzy = new ArrayList<TLcInforeportingReductionZhongyin>();
        int i=0;
        for(TLcInforeportingReduction tn:list){
            TLcInforeportingReductionZhongyin tr = new TLcInforeportingReductionZhongyin();
            i++;
            tr.setId(i);
            tr.setCreateTime(tn.getCreateTime());
            tr.setCompany("华道");
            tr.setCustomName(tn.getCustomName());
            tr.setProductName(tn.getProductName());
            tr.setOverdueDays(tn.getOverdueDays());
            tr.setDzhxrq(tn.getDzhxrq());
            tr.setCaseNo(tn.getCaseNo());
            tr.setRemarks(tn.getRemarks());
            tr.setDeductionAmount(tn.getDeductionAmount());
            listzy.add(tr);
        }
        return listzy;
    }

}
