package com.ruoyi;

import com.ruoyi.assetspackage.domain.TLiCaseInfo;
import com.ruoyi.assetspackage.domain.TLiContractInfo;
import com.ruoyi.assetspackage.domain.TLiTelInfo;
import com.ruoyi.assetspackage.domain.TLiTelList;
import com.ruoyi.assetspackage.mapper.TLiCaseInfoMapper;
import com.ruoyi.assetspackage.mapper.TLiContractInfoMapper;
import com.ruoyi.assetspackage.mapper.TLiTelInfoMapper;
import com.ruoyi.assetspackage.mapper.TLiTelListMapper;
import com.ruoyi.assetspackage.util.AESUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    /**
     * 电话表：telephone 手机号
     */
    @Test
    public void DataDeCodeTest4(){
        try {
            //查询电话表元数据
            List<TLiTelInfo> tLiTelInfos = TLiTelInfoMapper.selectTLiTelInfoList(new TLiTelInfo());
            //循环解密
            for (TLiTelInfo tLiTelInfo : tLiTelInfos) {
                String telephone = tLiTelInfo.getTelephone();
                telephone = AESUtil.decrypt(telephone);
                tLiTelInfo.setTelephone(telephone);
                //更新元数据
                TLiTelInfoMapper.updateTLiTelInfo(tLiTelInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        TLiContractInfo tLiContractInfo = new TLiContractInfo();
        Double debt = 19.12;
        String debt1 = AESUtil.encrypt(String.valueOf(debt));
        System.out.println("加密后："+debt1);
        String debt2 = AESUtil.decrypt(debt1);
        System.out.println("解密后："+debt2);
        tLiContractInfo.setDebt(debt2);
        System.out.println("重新赋值后："+tLiContractInfo);

    }


}
