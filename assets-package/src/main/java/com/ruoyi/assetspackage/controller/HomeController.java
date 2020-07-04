package com.ruoyi.assetspackage.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.AssetsInfoPackage;
import com.ruoyi.assetspackage.domain.CurAssetsRepaymentPackage;
import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.enums.IsCloseCaseEnum;
import com.ruoyi.assetspackage.service.IAssetsInfoService;
import com.ruoyi.assetspackage.service.ICurAssetsPackageService;
import com.ruoyi.assetspackage.service.IHomeService;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 客户资产Controller
 * 
 * @author guozeqi
 * @date 2020-02-13
 */
@Controller
@RequestMapping("/home/info")
public class HomeController extends BaseController
{
    private String prefix = "assetspackage/home";

    @Autowired
    private IHomeService homeService;


    @GetMapping()
    public String home()
    {
        return prefix + "/main2";
    }


    /**
     * 查询客户资产每月导入量
     */
    @PostMapping("/curAssetsCount")
    @ResponseBody
    public AjaxResult curAssetsCount()
    {

        List<Map<String, String>> maps = homeService.selectCurAssetsCount();
        String result = JSON.toJSONString(maps);
        return AjaxResult.success(result);
    }


    /**
     * 查询资产还款每月导入量
     * @return
     */
    @PostMapping("/curAssetsRepaymentCount")
    @ResponseBody
    public AjaxResult curAssetsRepaymentCount()
    {

        List<Map<String, String>> maps = homeService.selectCurAssetsRepaymentCount();
        String result = JSON.toJSONString(maps);
        return AjaxResult.success(result);
    }


    /**
     * 查询每月欠款、还款金额
     * @return
     */
    @PostMapping("/selectQHKJe")
    @ResponseBody
    public AjaxResult selectQHKJe()
    {
        Map<String,List<Map<String, String>>> ajaxMap = new HashMap<String,List<Map<String, String>>> ();
        List<Map<String, String>> qkjeResult = new ArrayList<Map<String, String>>();
        List<Map<String, String>> hkjeResult = new ArrayList<Map<String, String>>();
        List<Map<String, String>> qkjes = homeService.selectQkJe();
        for (Map<String, String> map : qkjes) {
            Map<String,String> resultMap = new HashMap<String,String>();
            String month = map.get("month");
            String qkje = map.get("qkje");
            qkje=(qkje==null?"0":qkje);
            resultMap.put(month,qkje);
            qkjeResult.add(resultMap);
        }

        List<Map<String, String>> hkjes = homeService.selectHkJe();
        for (Map<String, String> map : hkjes) {
            Map<String,String> resultMap = new HashMap<String,String>();
            String month = map.get("month");
            String hkje = map.get("hkje");
            hkje=(hkje==null?"0":hkje);
            resultMap.put(month,hkje);
            hkjeResult.add(resultMap);
        }

        ajaxMap.put("qkjeResult",qkjeResult);
        ajaxMap.put("hkjeResult",hkjeResult);
        String result = JSON.toJSONString(ajaxMap);
        return AjaxResult.success(result);
    }



}
