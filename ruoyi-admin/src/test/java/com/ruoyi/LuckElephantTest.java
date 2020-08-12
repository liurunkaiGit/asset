package com.ruoyi;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.luckElephant.*;
import com.ruoyi.common.utils.AesUtils;
import com.ruoyi.common.utils.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guozeqi
 * @create 2020-08-06
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LuckElephantTest {

    private static final String URL = "http://localhost:8080/remote/luckElephant/addAssets";

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Test
    public void testAddAsset() throws Exception{
        List<LuckElephantAddAssetRequest.AddAssetEntity> dataList = new ArrayList<>();
        LuckElephantAddAssetRequest request = new LuckElephantAddAssetRequest();
        request.setSysCode("JXPH");
        request.setBatchNo("20200806142000");
        request.setOrgId("206");
        request.setOrgName("吉象普惠");
        request.setFrontTransTime("2020-08-06 09:00:00");

        LuckElephantAddAssetRequest.AddAssetEntity param = new LuckElephantAddAssetRequest().new AddAssetEntity();
        param.setOrgCasno("HT0000001");
        param.setCurName("张三");
        param.setAge("20");
        param.setSex("男");
        param.setWorkName("北京华道");
        param.setEmail("123456@qq.com");
        param.setNation("汉");
        param.setEducation("大学");
        param.setFamilyAddr("北京家庭地址");
        param.setHouseAddr("北京户籍地址");
        param.setCurNo("123");
        param.setCardCode("123456199008******");
        param.setCardType("身份证");
        param.setWorkAddr("北京单位地址");
        param.setBirthday("2020-01-01");
        param.setMarriage("未婚");
        param.setSalary("中等");
        param.setCurType("aaa");
        param.setCity("北京定位城市");
        param.setDealDays("20");
        param.setLoanTime("2020-08-01");
        param.setPayStatus("未还");
        param.setYhkDate("2020-08-02");
        param.setLastLoanDate("2020-09-01");
        param.setRmbYhbjzje("2000");
        param.setRmbQkzje("28000");
        param.setWaYe("30000");
        param.setOverdueDays("20");
        param.setTransfertype("M1");
        param.setBorrowNo("1213456789");
        param.setLastRepayAmount("1000");
        param.setBorrowBlank("工商银行");
        param.setLoanAmount("30001");
        param.setStagesNum("16");
        param.setPayStages("10");
        param.setRmbZdyhje("2000");
        param.setRmbYhlizje("500");
        param.setRmbYhfyzje("15");
        param.setRmbYhfxzje("16");
        param.setZnj("600");
        param.setLjyhje("18000");
        param.setRcr("2020-08-03");
        param.setRepayLevel("ccc");
        param.setLastRepayDate("2020-08-02");
        param.setCustomerMobile("13312135670");
        param.setWorkTel("010-0000123");
        param.setFirstLiaisonName("丽丽");
        param.setFirstLiaisonMobile("13312135671");
        param.setFirstLiaisonRelation("姐妹");
        param.setSecondLiaisonName("路西");
        param.setSecondLiaisonMobile("13312135672");
        param.setSecondLiaisonRelation("妻子");
        param.setThreeLiaisonName("大卫");
        param.setThreeLiaisonMobile("13312135672");
        param.setThreeLiaisonRelation("朋友");
        dataList.add(param);

        /*LuckElephantAddAssetRequest.AddAssetEntity param2 = new LuckElephantAddAssetRequest().new AddAssetEntity();
        param2.setOrgCasno("HT6301201907180000394373213");
        param2.setCurName("李四");
        dataList.add(param2);*/

        request.setFacts(dataList);
        String requestStr = JSON.toJSONString(request);

        String str = AesUtils.encrypt(requestStr, "1234567890abcDEF");

        ResponseEntity<LuckElephantAddAssetResponse> response = restTemplateUtil.getRestTemplate().postForEntity(URL, str, LuckElephantAddAssetResponse.class);
        LuckElephantAddAssetResponse body = response.getBody();
        System.out.println(body.toString());

    }


    @Test
    public void testUpdateAsset() throws Exception{
        List<LuckElephantUpdateAssetRequest.UpdateAssetEntity> dataList = new ArrayList<>();
        LuckElephantUpdateAssetRequest request = new LuckElephantUpdateAssetRequest();
        request.setSysCode("JXPH");
        request.setBatchNo("20200806142000");
        request.setOrgId("220");
        request.setOrgName("吉象普惠");
        request.setFrontTransTime("2020-08-06 09:00:00");

        LuckElephantUpdateAssetRequest.UpdateAssetEntity param = new LuckElephantUpdateAssetRequest().new UpdateAssetEntity();
        param.setOrgCasno("3");
        param.setPayStatus("已还");
        param.setYhkDate("2020-08-02");
        param.setRepayAmount("500");
        param.setRmbYhfyzje("15");
        param.setZnj("600");
        param.setWaYe("30000");
        param.setOverdueDays("20");
        param.setTransfertype("M2");
        param.setBorrowNo("1213456789");
        param.setLastRepayAmount("1000");
        param.setPayStages("10");
        param.setRmbYhbjzje("2000");
        param.setRmbYhlizje("500");
        param.setRmbYhfxzje("16");
        param.setRmbQkzje("28000");
        param.setLjyhje("18000");
        param.setRcr("2020-08-03");
        param.setBorrowBlank("工商银行");
        param.setLastRepayDate("2020-08-02");

        dataList.add(param);

        request.setFacts(dataList);
        String requestStr = JSON.toJSONString(request);

        String str = AesUtils.encrypt(requestStr, "1234567890abcDEF");

        ResponseEntity<LuckElephantUpdateAssetResponse> response = restTemplateUtil.getRestTemplate().postForEntity(URL, str, LuckElephantUpdateAssetResponse.class);
        LuckElephantUpdateAssetResponse body = response.getBody();
        System.out.println(body.toString());

    }

    @Test
    public void testRepaymentAsset() throws Exception{
        List<LuckElephantRepaymentAssetRequest.RepaymentAssetEntity> dataList = new ArrayList<>();
        LuckElephantRepaymentAssetRequest request = new LuckElephantRepaymentAssetRequest();
        request.setSysCode("JXPH");
        request.setBatchNo("20200806142000");
        request.setOrgId("220");
        request.setOrgName("吉象普惠");
        request.setFrontTransTime("2020-08-06 09:00:00");

        LuckElephantRepaymentAssetRequest.RepaymentAssetEntity param = new LuckElephantRepaymentAssetRequest().new RepaymentAssetEntity();
        param.setOrgCasno("45");
        param.setPayStatus("已还");
        param.setWaYe("30000");
        param.setOverdueDays("20");
        param.setTransfertype("M2");
        param.setLastRepayAmount("1000");
        param.setPayStages("10");
        param.setRmbQkzje("28000");
        param.setLjyhje("18000");
        param.setLastRepayDate("2020-08-02");

        dataList.add(param);

        request.setFacts(dataList);
        String requestStr = JSON.toJSONString(request);

        String str = AesUtils.encrypt(requestStr, "1234567890abcDEF");

        ResponseEntity<LuckElephantRepaymentAssetResponse> response = restTemplateUtil.getRestTemplate().postForEntity(URL, str, LuckElephantRepaymentAssetResponse.class);
        LuckElephantRepaymentAssetResponse body = response.getBody();
        System.out.println(body.toString());

    }

}
