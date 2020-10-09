package com.ruoyi.task.controller;

import com.ruoyi.assetspackage.util.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.config.DuYanConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 度言后台接口对接
 */
@Controller
@RequestMapping("/duyan/info")
public class DuyanController {
    private String prefix = "batchcall";
    @Autowired
    private DuYanConfig duYanConfig;
    /**
     * @param uuid
     * @return   获取通过记录详情
     * @throws Exception
     */
    @PostMapping("/call_log")
    @ResponseBody
    public JSONObject info(String uuid) throws Exception {
        return HttpUtil.doGet(duYanConfig.getLogUrl()+"?apikey="+duYanConfig.getApikey()+"&call_uuid="+uuid);
    }

    /** 批量拨打 弹出窗口
     * @param modelMap spring参数封装对象
     * @param accountId 度言账号id
     * @param dytoken  账号登录的token
     * @param phone 拨打的电话
     * @return
     */
    @GetMapping("/duyanBatch")
    public String add(ModelMap modelMap , String accountId, String dytoken,String phone)
    {
        modelMap.put("accountId",accountId);
        modelMap.put("dytoken",dytoken);
        modelMap.put("phone",phone);
        modelMap.put("soundRecordingUrl",duYanConfig.getSoundRecordingUrl());
        modelMap.put("apikey",duYanConfig.getApikey());
        return prefix + "/duyanBatch";
    }
}
