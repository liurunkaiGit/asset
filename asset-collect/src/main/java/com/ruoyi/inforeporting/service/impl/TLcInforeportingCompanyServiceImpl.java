package com.ruoyi.inforeporting.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.inforeporting.domain.TLcInforeportingBuckleXing;
import com.ruoyi.inforeporting.domain.TLcInforeportingCompany;
import com.ruoyi.inforeporting.domain.TLcInforeportingCompanyExp;
import com.ruoyi.inforeporting.domain.TLcInforeportingCompanyExpXing;
import com.ruoyi.inforeporting.mapper.TLcInforeportingCompanyMapper;
import com.ruoyi.inforeporting.service.TLcInforeportingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public AjaxResult exportExcel(TLcInforeportingCompany inforeportingCompany) {
        List<TLcInforeportingCompany> list = ibm.selectTLcInforeportingCompanyList(inforeportingCompany);
        if(null != inforeportingCompany.getOrgId() && 207 == inforeportingCompany.getOrgId().longValue()){
            //兴业消费金融
            return new ExcelUtil<TLcInforeportingCompanyExpXing>(TLcInforeportingCompanyExpXing.class).exportExcel(transformationCompanyXing(list),"对公入账");
        }
        return new ExcelUtil<TLcInforeportingCompanyExp>(TLcInforeportingCompanyExp.class).exportExcel(transformationCompany(list),"对公入账");
    }

    /**
     * 将对象信息封装导出对象中
     */
    private  List<TLcInforeportingCompanyExp> transformationCompany(List<TLcInforeportingCompany> list){
        List<TLcInforeportingCompanyExp> listExp = new ArrayList<TLcInforeportingCompanyExp>();
        int i=0;
        for(TLcInforeportingCompany cy:list){
            TLcInforeportingCompanyExp tp = cy.clone();
            tp.setXh(++i);
            listExp.add(tp);
        }
        return listExp;
    }
    /**
     * 将对象信息封装导出对象中 兴业消费金融
     */
    private  List<TLcInforeportingCompanyExpXing> transformationCompanyXing(List<TLcInforeportingCompany> list){
        List<TLcInforeportingCompanyExpXing> listExp = new ArrayList<TLcInforeportingCompanyExpXing>();
        int i =0;
        for(TLcInforeportingCompany cy:list){
            TLcInforeportingCompanyExpXing cep = new TLcInforeportingCompanyExpXing();
            cep.setXh(++i);
            cep.setProductName(cy.getProductName());
            cep.setCaseNo(cy.getCaseNo());
            cep.setCustomName(cy.getCustomName());
            ///封装数据
            StringBuilder company = new StringBuilder();
            company.append("客户姓名:"+cy.getCustomName()+"\r\n");
            company.append("借据号:"+cy.getCaseNo()+"\n");
            company.append("付款人姓名:"+cy.getDraweeName()+"\n");
            company.append("付款人与客户关系:"+cy.getRelationship()+"\n");
            company.append("付款卡前四位:"+cy.getTopFourCards()+"\n");
            company.append("付款卡后四位:"+cy.getLastFourCards()+"\n");
            company.append("付款银行:"+cy.getPayingBank()+"\n");
            company.append("存入金额:"+cy.getDepositAmount()+"\n");
            company.append("扣款金额:"+cy.getAmountOfDeduction()+"\n");
            company.append("非本人对公还款原因:"+cy.getReasons()+"\n");
            company.append("对公凭证上传附件");
            cep.setCompany(company.toString().replaceAll("\n", String.valueOf((char)10)));
            cep.setRemarks(cy.getRemarks());
            cep.setApplicationTime(cy.getApplicationTime());
            cep.setCreateTime(cy.getCreateTime());
            listExp.add(cep);
        }
        return listExp;
    }

}
