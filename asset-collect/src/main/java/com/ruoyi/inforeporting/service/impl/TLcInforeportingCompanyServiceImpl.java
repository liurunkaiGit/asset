package com.ruoyi.inforeporting.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.inforeporting.domain.*;
import com.ruoyi.inforeporting.mapper.TLcInforeportingCompanyMapper;
import com.ruoyi.inforeporting.service.TLcInforeportingCompanyService;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
/**
 * @Description: 上报信息-对公入账
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Service
public class TLcInforeportingCompanyServiceImpl implements TLcInforeportingCompanyService {

    @Autowired
    private TLcInforeportingCompanyMapper ibm;
    @Autowired
    private ISysDictDataService dictDataService;
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
        // 字典表查询 机构 配置的模板
        String template = dictDataService.selectDictLabel("company",inforeportingCompany.getOrgId().toString());
        List<TLcInforeportingCompany> list = ibm.selectTLcInforeportingCompanyList(inforeportingCompany);
        if("company_xy".equals(template)){
            //兴业消费金融
            return new ExcelUtil<TLcInforeportingCompanyExpXing>(TLcInforeportingCompanyExpXing.class).exportExcel(transformationCompanyXing(list),"对公入账");
        }else if("company_zy".equals(template)){
            //中银
            return new ExcelUtil<TLcInforeportingCompanyExpZhongyin>(TLcInforeportingCompanyExpZhongyin.class).exportExcel(transformationCompanyZhongyin(list),"对公入账");
        }
        return new ExcelUtil<TLcInforeportingCompanyExp>(TLcInforeportingCompanyExp.class).exportExcel(transformationCompany(list),"对公入账");
    }

    /**
     * 将对象信息封装导出对象中
     */
    private  List<TLcInforeportingCompanyExp> transformationCompany(List<TLcInforeportingCompany> list){
        if(null == list || list.isEmpty()||list.size()==0)return null;
        List<TLcInforeportingCompanyExp> listExp = new ArrayList<TLcInforeportingCompanyExp>(list.size());
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
        if(null == list || list.isEmpty()||list.size()==0)return null;
        List<TLcInforeportingCompanyExpXing> listExp = new ArrayList<TLcInforeportingCompanyExpXing>(list.size());
        int i =0;
        for(TLcInforeportingCompany cy:list){
            TLcInforeportingCompanyExpXing cep = new TLcInforeportingCompanyExpXing();
            cep.setXh(++i);
            cep.setProductName(cy.getProductName());
            cep.setCaseNo(cy.getCaseNo());
            cep.setCustomName(cy.getCustomName());
            ///封装数据
            StringBuilder company = new StringBuilder();
            company.append("客户姓名:"+StringUtils.nvl(cy.getCustomName(),"-")+"\r\n");
            company.append("借据号:"+cy.getCaseNo()+"\n");
            company.append("付款人姓名:"+StringUtils.nvl(cy.getDraweeName(),"-")+"\n");
            company.append("付款人与客户关系:"+StringUtils.nvl(cy.getRelationship(),"-")+"\n");
            company.append("付款卡前四位:"+StringUtils.nvl(cy.getTopFourCards(),"-")+"\n");
            company.append("付款卡后四位:"+StringUtils.nvl(cy.getLastFourCards(),"-")+"\n");
            company.append("付款银行:"+StringUtils.nvl(cy.getPayingBank(),"-")+"\n");
            company.append("存入金额:"+StringUtils.nvl(cy.getDepositAmount(),"0.0")+"\n");
            company.append("存入日期:"+loadDate(cy.getDepositDate())+"\n");
            company.append("扣款金额:"+StringUtils.nvl(cy.getAmountOfDeduction(),"0.0")+"\n");
            company.append("非本人对公还款原因:"+ StringUtils.nvl(cy.getReasons(),"-")+"\n");
            company.append("对公凭证上传附件");
            cep.setCompany(company.toString().replaceAll("\n", String.valueOf((char)10)));
            cep.setRemarks(cy.getRemarks());
            cep.setApplicationTime(cy.getApplicationTime());
            listExp.add(cep);
        }
        return listExp;
    }

    private String loadDate( Date date){
         if(null == date)return "";
        return DateUtils.dateTime(date);
    }

    /**
     * 对公入账 -中银转化导出模板
     * @param list 默认查询结果集
     * @return 准换后的模板集合
     */
    private  List<TLcInforeportingCompanyExpZhongyin> transformationCompanyZhongyin(List<TLcInforeportingCompany> list){
        if(null == list || list.isEmpty()||list.size()==0)return null;
        List<TLcInforeportingCompanyExpZhongyin> listExp = new ArrayList<TLcInforeportingCompanyExpZhongyin>(list.size());
        int i =0;
        for(TLcInforeportingCompany cy:list){
            TLcInforeportingCompanyExpZhongyin tn = new TLcInforeportingCompanyExpZhongyin();
            if("已核销".equals(cy.getTransferType())){
                tn.setStage("已核销");
            }else{
                tn.setStage("未核销");
            }
            tn.setXh(++i);
            tn.setApplicationTime(cy.getApplicationTime());
            tn.setCaseNo(cy.getCaseNo());
            tn.setProductName(StringUtils.nvl(cy.getProductName(),"-"));
            tn.setCustomName(StringUtils.nvl(cy.getCustomName(),""));
            tn.setAmountOfDeduction(cy.getAmountOfDeduction());
            tn.setCreateTime(cy.getCreateTime());
            tn.setDraweeName(cy.getDraweeName());
            listExp.add(tn);
        }
        return listExp;
    }
}
