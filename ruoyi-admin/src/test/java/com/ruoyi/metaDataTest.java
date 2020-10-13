package com.ruoyi;

import com.github.pagehelper.PageHelper;
import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.mapper.*;
import com.ruoyi.assetspackage.util.AESUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guozeqi
 * @create 2020-05-29
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class metaDataTest {
    @Autowired
    private TLiCaseInfoMapper TLiCaseInfoMapper;
    @Autowired
    private TLiContractInfoMapper TLiContractInfoMapper;
    @Autowired
    private TLiTelListMapper TLiTelListMapper;
    @Autowired
    private TLiTelInfoMapper TLiTelInfoMapper;


    /**
     * 案件表：cust_name 客户姓名、certificate_no 身份证
     */
    @Test
    public void DataDeCodeTest1(){
        try {
            //查询案件表元数据
            List<TLiCaseInfo> tLiCaseInfos = TLiCaseInfoMapper.selectTLiCaseInfoList(new TLiCaseInfo());
            //循环解密
            for (TLiCaseInfo tLiCaseInfo : tLiCaseInfos) {
                String custName = tLiCaseInfo.getCustName();
                String certificateNo = tLiCaseInfo.getCertificateNo();
                custName = AESUtil.decrypt("custName");
                certificateNo = AESUtil.decrypt("certificateNo");
                tLiCaseInfo.setCustName(custName);
                tLiCaseInfo.setCertificateNo(certificateNo);
                //更新元数据
                TLiCaseInfoMapper.updateTLiCaseInfo(tLiCaseInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 合同表：debt  金额
     */
    @Test
    public void DataDeCodeTest2(){
        try {
            //查询合同表元数据
            List<TLiContractInfo> tLiContractInfos = TLiContractInfoMapper.selectTLiContractInfoList(new TLiContractInfo());
            //循环解密
            for (TLiContractInfo tLiContractInfo : tLiContractInfos) {
//                Double debt = tLiContractInfo.getDebt();
                String debt2 = AESUtil.decrypt(String.valueOf(tLiContractInfo.getDebt()));
//                tLiContractInfo.setDebt(Double.valueOf(debt2));
                //更新元数据
                TLiContractInfoMapper.updateTLiContractInfo(tLiContractInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 电话记录表 telephone 手机号
     */
    @Test
    public void DataDeCodeTest3(){
        try {
            //查询电话记录表元数据
            List<TLiTelList> tLiTelLists = TLiTelListMapper.selectTLiTelListList(new TLiTelList());
            //循环解密
            for (TLiTelList tLiTelList : tLiTelLists) {
                String telephone = tLiTelList.getTelephone();
                telephone = AESUtil.decrypt(telephone);
                tLiTelList.setTelephone(telephone);
                //更新元数据
                TLiTelListMapper.updateTLiTelList(tLiTelList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Autowired
    private TLiCaseInfoXyMapper tLiCaseInfoXyMapper;

    @Test
    public void anjianloadTLiCaseInfoXy22(){
        System.out.println("案件开始处理");
        String fg = "|";
        String path = "d:/2-case/";
        String fileName = "aj-1";
        // String tableName = "t_li_case_info_xy1";
        // String title = "tuid|case_tuid|cust_no|tel_type|telephone|cust_name|cust_name_pinyin|relation|data_source|last_tel_code|last_call_time|last_call_user|stop_flag|shi_lian|created|createdby|updated|updatedby|import_batch|recheck_date|import_tuid|postcode|address|max_tel_code|appoint_time|effective_flag|sign_status|stress|display_flag|weight|dial_count\n";
        String hc = "\r\n";
        try {
            //查询电话表元数据
            long start = System.currentTimeMillis();
            List<TLiCaseInfoXy> tLiTelInfos = tLiCaseInfoXyMapper.selectTLiCaseInfoXyList(new TLiCaseInfoXy());
            //System.out.println("数据库获取到数据----"+tLiTelInfos.size());
            //循环解密
            File file =new File(path+fileName+".csv");
            Writer out =new FileWriter(file);
            StringBuilder   srr = new StringBuilder();
            // out.write(title);
            int i= 0;
            for (TLiCaseInfoXy to : tLiTelInfos) {
                StringBuilder sr = new StringBuilder();
                sr.append(to.getTuid()+fg);
                sr.append(to.getImportBatch()+fg);
                sr.append(to.getQueue()+fg);
                sr.append(to.getStatus()+fg);
                sr.append(to.getIsStop()+fg);
                sr.append(to.getStopTime()+fg);
                sr.append(to.getOutTime()+fg);
                sr.append(to.getCreated()+fg);
                sr.append(to.getCreatedby()+fg);
                sr.append(to.getUpdated()+fg);
                sr.append(to.getUpdatedby()+fg);
                sr.append(to.getCollector()+fg);
                sr.append(to.getCollectorCurrent()+fg);
                sr.append(to.getCaseNo()+fg);
                sr.append(to.getCustNo()+fg);
                // 客户姓名 需要解密
                //sr.append(to.getCustName()+fg);
                sr.append(AESUtil.decrypt(to.getCustName())+fg);
                //身份证需要解密
                //sr.append(to.getCertificateNo()+fg);
                sr.append(AESUtil.decrypt(to.getCertificateNo())+fg);
                sr.append(to.getCorpus()+fg);
                sr.append(to.getBillDate()+fg);
                sr.append(to.getCollectionDate()+fg);
                sr.append(to.getCollectionDateEnd()+fg);
                sr.append(to.getVersion()+fg);
                sr.append(to.getImportTuid()+fg);
                sr.append(to.getOverduePeriod()+fg);
                sr.append(to.getMobile()+fg);
                sr.append(to.getDebtInit()+fg);
                sr.append(to.getShiLian()+fg);
                sr.append(to.getBankCardName()+fg);
                sr.append(to.getAgencyName()+fg);
                sr.append(to.getCustAddress()+fg);
                sr.append(to.getCompanyAddr()+fg);
                sr.append(to.getBlack()+fg);
                sr.append(to.getOutType()+fg);
                sr.append(to.getSuggest()+fg);
                sr.append(to.getCity()+fg);
                sr.append(to.getBirthday()+fg);
                sr.append(to.getSex()+fg);
                sr.append(to.getAge()+fg);
                sr.append(to.getEmail()+fg);
                sr.append(to.getCompanyName()+fg);
                sr.append(to.getBranch()+fg);
                sr.append(to.getOutReason()+fg);
                sr.append(to.getHousehold()+fg);
                sr.append(to.getCardCount()+fg);
                sr.append(to.getAssignBalRmb()+fg);
                sr.append(to.getIsNotice()+fg);
                sr.append(to.getOverdueStatus()+fg);
                sr.append(to.getBhStaff()+fg);
                sr.append(to.getScore()+fg);
                sr.append(to.getProbability()+fg);
                sr.append(to.getLevel()+fg);
                sr.append(to.getMinPayOld()+fg);
                sr.append(to.getQueryData()+fg);
                sr.append(to.getQueryUrl()+fg);
                sr.append(to.getQueryStatus()+fg);
                sr.append(to.getQueryDataJh()+fg);
                sr.append(to.getQueryUrlJh()+fg);
                sr.append(to.getIsLaw()+fg);
                sr.append(to.getPayCurmon()+fg);
                sr.append(to.getApplyTime()+fg);
                sr.append(to.getApplyTimeReturn()+fg);
                sr.append(to.getOverdueRecent()+fg);
                sr.append(to.getOverdueRecentInit()+fg);
                sr.append(to.getOverdueRecentLast()+fg);
                sr.append(to.getQueryTime()+fg);
                sr.append(to.getQueryResult()+fg);
                sr.append(to.getQueryResultTime()+fg);
                sr.append(to.getQueryStatusTd()+fg);
                sr.append(to.getQueryTimeTd()+fg);
                sr.append(to.getStatusDispaly()+fg);
                sr.append(to.getSignColor()+fg);
                sr.append(to.getOrderNumber()+fg);
                sr.append(to.getDataTime()+fg);
                sr.append(to.getCity1()+fg);
                sr.append(to.getCity2()+fg);
                sr.append(to.getCsNode()+fg);
                sr.append(to.getWbCompany()+fg);
                sr.append(to.getOriginalManager()+fg);
                sr.append(to.getManager()+fg);
                sr.append(to.getOverdueDate()+fg);
                sr.append(to.getNewProductName()+fg);
                sr.append(to.getProductName()+fg);
                sr.append(to.getPayType()+fg);
                sr.append(to.getAccountBalance()+fg);
                sr.append(to.getWbbd()+fg);
                sr.append(to.getOverdueDays()+fg);
                sr.append(to.getLoan()+fg);
                sr.append(to.getAmtPrinciple()+fg);
                sr.append(to.getFee()+fg);
                sr.append(to.getOverdueServicefee()+fg);
                sr.append(to.getLatefee()+fg);
                sr.append(to.getInterestPenalty()+fg);
                sr.append(to.getAccountNumber()+fg);
                sr.append(to.getCompanyPhone()+fg);
                sr.append(to.getDepartment()+fg);
                sr.append(to.getBusinessAddress()+fg);
                sr.append(to.getHomeAddr()+fg);
                sr.append(to.getHomePhone()+fg);
                sr.append(to.getContactName1()+fg);
                sr.append(to.getContactPhone1()+fg);
                sr.append(to.getContactName2()+fg);
                sr.append(to.getContactPhone2()+fg);
                sr.append(to.getContactName3()+fg);
                sr.append(to.getContactPhone3()+fg);
                sr.append(to.getChannelName()+fg);
                sr.append(to.getHands()+fg);
                sr.append(to.getLastPaymentDate()+fg);
                sr.append(to.getAllocationTime()+fg);
                sr.append(to.getRecoveryTime()+fg);
                i++;
                if(i == tLiTelInfos.size()){
                    hc="";
                }
                sr.append(((to.getDebt()==null || "".equals(to.getDebt()))?"1":to.getDebt())+hc);
                out.write(sr.toString().replace("null",""));
            }
            out.close();
            System.out.println("所用时长:"+(System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 电话表：telephone 手机号
     */
    @Test
    public void DataDeCodeTest4(){
            System.out.println("开始处理");
            int size = 10000;
            String fg = "|";
            String path = "d:/";
            String fileName = "1";
            String tableName = "t_li_tel_info";
            String hc = "\r\n";
            int k = 0;
            ///解密 AESUtil.decrypt(telephone);
           // String title = "tuid|case_tuid|cust_no|tel_type|telephone|cust_name|cust_name_pinyin|relation|data_source|last_tel_code|last_call_time|last_call_user|stop_flag|shi_lian|created|createdby|updated|updatedby|import_batch|recheck_date|import_tuid|postcode|address|max_tel_code|appoint_time|effective_flag|sign_status|stress|display_flag|weight|dial_count\n";
            for(int i=1;i<2;i++){
                try {
                    //查询电话表元数据
                    long start = System.currentTimeMillis();
                    List<TLiTelInfo> tLiTelInfos = null;//TLiTelInfoMapper.selectTLiTelInfoList(tableName);
                    //System.out.println("数据库获取到数据----"+tLiTelInfos.size());
                    System.out.println("数据库获取到数据----，所用时长:"+(System.currentTimeMillis() - start));
                    //循环解密
                    File file =new File(path+fileName+".csv");
                    Writer out =new FileWriter(file);
                    StringBuilder   srr = new StringBuilder();
                    //out.write(title);
                    for (TLiTelInfo to : tLiTelInfos) {
                        StringBuilder sr = new StringBuilder();
                        sr.append(to.getTuid()+fg);
                        sr.append(to.getCaseTuid()+fg);
                        sr.append(to.getCustNo()+fg);
                        sr.append(to.getTelType()+fg);
                        sr.append(AESUtil.decrypt(to.getTelephone())+fg);
                        sr.append(to.getCustName()+fg);
                        sr.append(to.getCustNamePinyin()+fg);
                        sr.append(to.getRelation()+fg);
                        sr.append(to.getDataSource()+fg);
                        sr.append(to.getLastTelCode()+fg);
                        sr.append(to.getLastCallTime()+fg);
                        sr.append(to.getLastCallUser()+fg);
                        sr.append(to.getStopFlag()+fg);
                        sr.append(to.getShiLian()+fg);
                        sr.append(to.getCreated()+fg);
                        sr.append(to.getCreatedby()+fg);
                        sr.append(to.getUpdated()+fg);
                        sr.append(to.getUpdatedby()+fg);
                        sr.append(to.getImportBatch()+fg);
                        sr.append(to.getRecheckDate()+fg);
                        sr.append(to.getImportTuid()+fg);
                        sr.append(to.getPostcode()+fg);
                        sr.append(to.getAddress()+fg);
                        sr.append(to.getMaxTelCode()+fg);
                        sr.append(to.getAppointTime()+fg);
                        sr.append(to.getEffectiveFlag()+fg);
                        sr.append(to.getSignStatus()+fg);
                        sr.append(to.getStress()+fg);
                        sr.append(to.getDisplayFlag()+fg);
                        sr.append(to.getWeight()+fg);
                        k++;
                        if(k == tLiTelInfos.size()){
                            hc="";
                        }
                        sr.append(to.getDialCount()+hc);
                        out.write(sr.toString().replace("null",""));
                    }
                   // out.flush();
                    out.close();
                    System.out.println("第"+i+"次，所用时长:"+(System.currentTimeMillis() - start));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    }










    public static void main(String[] args) {
//        TLiContractInfo tLiContractInfo = new TLiContractInfo();
//        Double debt = 19.12;
//        String debt1 = AESUtil.encrypt(String.valueOf(debt));
//        System.out.println("加密后："+debt1);
//        String debt2 = AESUtil.decrypt(debt1);
//        System.out.println("解密后："+debt2);
//        tLiContractInfo.setDebt(debt2);
//        System.out.println("重新赋值后："+tLiContractInfo);
    }

}
