package com.ruoyi.assetspackage.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.assetspackage.domain.score.CollectionScoreRequest;
import com.ruoyi.assetspackage.domain.score.CollectionScoreResponse;
import com.ruoyi.common.utils.security.Md5Utils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author guozeqi
 * @create 2020-06-23
 */

public class CollectionScoreUtil {

    private static String url = null;
    private static String securityKey = null;
    private static String modelVersion = null;

    static {
        List<SysDictData> list = SpringUtils.getBean(ISysDictDataService.class).selectDictDataByType("score");
        for (SysDictData sysdatum : list) {
            if("url".equals(sysdatum.getDictLabel())){
                url = sysdatum.getDictValue();
                continue;
            }
            if("securityKey".equals(sysdatum.getDictLabel())){
                securityKey = sysdatum.getDictValue();
                continue;
            }
            if("modelVersion".equals(sysdatum.getDictLabel())){
                modelVersion = sysdatum.getDictValue();
                continue;
            }
        }
    }

    public static CollectionScoreRequest buildParam(Map<String,String> sourMap){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        //签名字段
        String bizLine="HUADAO";
        String version="1.0";
        String time=sdf.format(new Date());
        String reqId=String.valueOf(System.currentTimeMillis());
        String sign="";
        //业务字段
        String model = modelVersion;//模型版本
        String version2 = sdf2.format(new Date());
        String name = md5(sourMap.get("curName"));
        String card = md5(sourMap.get("certificateNo"));
        String phone = md5(sourMap.get("customerMobile"));
        String dueDay = sourMap.get("overdueDays");
        String dueMoney = sourMap.get("waYe");
        String dueDate = sourMap.get("firstYqDate");
        String orgStr= sourMap.get("orgName");
        //构建参数
        CollectionScoreRequest.QueryData inner = new CollectionScoreRequest().new QueryData();
        inner.setName(name);
        inner.setCard(card);
        inner.setPhone(phone);
        inner.setModel(model);
        inner.setVersion(version2);
        if(dueDay != null){
            inner.setDueDay(Integer.parseInt(dueDay));
        }
        if(dueMoney != null){
            inner.setDueMoney(Float.valueOf(dueMoney));
        }
        if(dueDate != null){
            inner.setDueDate(dueDate);
        }
        inner.setOrgStr(orgStr);
        String innerJsonStr = JSON.toJSONString(inner);
        String query = Base64(innerJsonStr);
        //sign顺序["bizLine","query","reqId","time","version"]
        CollectionScoreRequest param = new CollectionScoreRequest();
        param.setBizLine(bizLine);
        param.setTime(time);
        param.setReqId(reqId);
        param.setVersion(version);
        param.setQuery(query);
        sign = md5(bizLine+query+reqId+time+version+securityKey);
        param.setSign(sign);
        return param;
    }


    public static String Base64(String str) {
        String secretKey = securityKey;//密钥
        String resultStr = "";
        byte[] result = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] byteContent = str.getBytes("utf-8");
            SecretKeySpec skp = new SecretKeySpec(secretKey.getBytes(), "AES");
            IvParameterSpec iv = new IvParameterSpec(secretKey.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skp, iv);
            result = cipher.doFinal(byteContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultStr = Base64.encodeBase64String(result);
        return resultStr;
    }


    public static String md5(String param) {
        String m1 = Md5Utils.hash(param);
        return m1;
    }


    public static CollectionScoreResponse doPost(CollectionScoreRequest collectionScoreRequest) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        CollectionScoreResponse body = null;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(
                popHeaders(collectionScoreRequest), headers);
        ResponseEntity<CollectionScoreResponse> responseEntity = restTemplate.postForEntity(url, request, CollectionScoreResponse.class);
        body = responseEntity.getBody();
        return body;
    }
    //组装请求体
    protected static MultiValueMap<String, String> popHeaders(CollectionScoreRequest collectionScoreRequest) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        String bizLine = collectionScoreRequest.getBizLine();
        String reqId = collectionScoreRequest.getReqId();
        String query = collectionScoreRequest.getQuery();
        String sign = collectionScoreRequest.getSign();
        String time = collectionScoreRequest.getTime();
        String version = collectionScoreRequest.getVersion();
        map.add("bizLine", bizLine);
        map.add("reqId",reqId);
        map.add("query",query);
        map.add("sign",sign);
        map.add("time",time);
        map.add("version",version);
        return map;
    }

    public static Long getScore(CollectionScoreResponse response){
        Long result = null;
        String data = response.getData();
        if(data==null || "".equals(data)){
            result = -1L;
        }else{
            result = Long.valueOf(JSONObject.parseObject(data).get(modelVersion).toString());
            if(result == null){
                result = -1L;
            }
        }
        return result;
    }

}
