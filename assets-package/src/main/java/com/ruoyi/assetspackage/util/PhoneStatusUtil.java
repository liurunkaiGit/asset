package com.ruoyi.assetspackage.util;

import com.alibaba.fastjson.JSON;
import com.bfd.facade.MerchantServer;
import com.bfd.util.PropertiesUtil;
import com.ruoyi.assetspackage.domain.phoneStatus.PhoneStatusRequestData;
import com.ruoyi.assetspackage.domain.phoneStatus.PhoneStatusResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

/**
 * @author guozeqi
 * @create 2020-08-28
 */
@Slf4j
public class PhoneStatusUtil {

    public static PhoneStatusResponse getPhoneStatus(PhoneStatusRequestData data){
        MerchantServer ms = new MerchantServer();
        JSONObject jso = new JSONObject();
        JSONObject reqData = new JSONObject();
        jso.put("apiName", "verificationApi");
        reqData.put("conf_id", PropertiesUtil.getStringValue("conf_id"));
        reqData.put("id", data.getId());
        reqData.put("cell", data.getCell());
        reqData.put("name", data.getName());
        jso.put("reqData", reqData);
        log.info("startTime="+System.currentTimeMillis()+"百融电话虫请求数据{}",jso.toString());
        String result = ms.getApiData(jso.toString());
        log.info("endTime="+System.currentTimeMillis()+"百融电话虫返回结果{}",result);
        PhoneStatusResponse response = JSON.parseObject(result, PhoneStatusResponse.class);
        return response;
    }


}
