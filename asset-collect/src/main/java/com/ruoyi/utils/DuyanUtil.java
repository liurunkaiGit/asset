package com.ruoyi.utils;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 度言工具类
 */
public class DuyanUtil {
    /**
     * 获取度言 token
     * @param url
     * @param accountId
     * @param apikey
     * @return
     */
    public static Object getToken(String url,String accountId,String apikey){
        Object dytokenO = ShiroUtils.getSession().getAttribute("dytoken");
        if(null == dytokenO){
            String rl = HttpUtils.sendPost(url, "account_id="+accountId+"&apikey="+apikey);
            JSONObject ro = JSONObject.parseObject(rl);
            JSONObject jt = (JSONObject) ro.get("data");
            dytokenO = jt.get("token");
            ShiroUtils.getSession().setAttribute("dytoken",dytokenO);
        }
        return dytokenO;
    }
}
