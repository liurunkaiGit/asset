package com.ruoyi.assetspackage.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.StreamPackage;
import com.ruoyi.assetspackage.domain.TemplatesPackage;
import com.ruoyi.assetspackage.enums.IsCloseCaseEnum;
import com.ruoyi.assetspackage.service.ICurAssetsPackageService;
import com.ruoyi.assetspackage.service.ICurAssetsRepaymentPackageService;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.assetspackage.service.IStreamPackageService;
import com.ruoyi.assetspackage.util.DynamicSqlUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 *动态sql Controller
 * 
 * @author guozeqi
 * @date 2020-04-15
 */
@Controller
@RequestMapping("/assetspackage/dynamicsql")
public class DynamicSqlController extends BaseController
{
    private String prefix = "assetspackage/dynamicsql";


    @Autowired
    private IStreamPackageService streamPackageService;
    @Autowired
    private DynamicSqlUtil dynamicSqlUtil;

    @Autowired
    private IOrgPackageService orgPackageService;

    @Autowired
    private ICurAssetsPackageService curAssetsPackageService;

    @Autowired
    private ICurAssetsRepaymentPackageService curAssetsRepaymentPackageService;





    @RequiresPermissions("assetspackage:dynamicsql:view")
    @GetMapping()
    public String assetspackage()
    {
        return prefix + "/view";
    }

    /**
     * 执行窗口
     */
    @GetMapping("/execute")
    public String execute() {
        return prefix + "/execute";
    }


    /**
     * 执行sql
     * @param
     * @return
     */
    /*@PostMapping("/apply")
    @ResponseBody
    public AjaxResult apply(String sql){
        boolean boo = checkSql(sql);
        if(!boo){
            int result = dynamicSqlUtil.toExecute(sql);
            if(result > 0){
                return AjaxResult.success("执行成功");
            }
            return AjaxResult.error("执行失败");
        }else{
            List<Map<String, Object>> queryResult = dynamicSqlUtil.toQuery(sql);
            Set<String> heads = queryResult.get(0).keySet();
            StringBuffer buffer = new StringBuffer();
            buffer.append("{url: prefix + \"/list\",").append("exportUrl: prefix + \"/export\",").append("clickToSelect:true,").append("pagination:false,")
                    .append("columns: [{checkbox: false}");
            for (String head : heads) {
                String field = ",{field : "+head+", title : "+head+"}";
                buffer.append(field);
            }
            buffer.append(" ]}");
            String option = buffer.toString();
            return AjaxResult.success(option);
        }

    }*/

    @PostMapping("/apply")
    @ResponseBody
    public AjaxResult apply(String sql, HttpServletResponse response)throws Exception{
//        boolean boo = checkSql(sql);
//        if(!boo){
            int result = dynamicSqlUtil.toExecute(sql);
            if(result > 0){
                return AjaxResult.success("执行成功");
            }
            return AjaxResult.error("执行失败");
//        }else{
//            List<Map<String, Object>> queryResult = dynamicSqlUtil.toQuery(sql);
//            HSSFWorkbook wb = dynamicSqlUtil.getHSSFWorkbook("sheet1", queryResult);
//            response.setHeader("Content-Disposition", "attachment;Filename=" + System.currentTimeMillis() + ".xls");
//            OutputStream outputStream = response.getOutputStream();
//            wb.write(outputStream);
//            outputStream.close();
//            return AjaxResult.success("执行成功");
//        }

    }

    @GetMapping("/apply2")
    @ResponseBody
    public void apply2(String sql, HttpServletResponse response)throws Exception {
//        List<Map<String, Object>> queryResult = dynamicSqlUtil.toQuery(sql);
        Map<String, Object> queryResult = dynamicSqlUtil.toQuery(sql);
        HSSFWorkbook wb = dynamicSqlUtil.getHSSFWorkbook("sheet1", queryResult);
        response.setHeader("Content-Disposition", "attachment;Filename=" + System.currentTimeMillis() + ".xls");
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.close();
    }



    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StreamPackage streamPackage)
    {
        startPage();
        List<StreamPackage> list = streamPackageService.selectStreamPackageList(streamPackage);
        for (StreamPackage stream : list) {
            String jazt = stream.getJazt();
            if(jazt != null && IsCloseCaseEnum.CLOSE_CASE.getValue().equals(jazt)){
                stream.setJazt("已结案");
            }else {
                stream.setJazt("未结案");
            }
        }
        return getDataTable(list);
    }



    private boolean checkSql(String sql){
        if(sql.contains("select") || sql.contains("SELECT")){
            return true;
        }
        return false;
    }

    /**
     *查询当前用户拥有的数据权限与委托方的交集
     * @param request
     * @return
     */
    @RequiresPermissions("assetspackage:stream:list")
    @PostMapping("/findOrgInfo")
    @ResponseBody
    public AjaxResult findOrgInfo(HttpServletRequest request){
        List<Map<String, Long>> orgInfo = null;
        Long UserId = ShiroUtils.getUserId();
        String dataScope = orgPackageService.selectDataScopeByUserId(UserId);
        if("1".equals(dataScope)){
            orgInfo = orgPackageService.selectOrgInfoByUserId1(UserId);
        }else if("2".equals(dataScope)){
            orgInfo = orgPackageService.selectOrgInfoByUserId2(UserId);
        }else if("4".equals(dataScope)){
            orgInfo = orgPackageService.selectOrgInfoByUserId4(UserId);
        }else {
            orgInfo = orgPackageService.selectOrgInfoByUserId3(UserId);
        }
        String result = JSON.toJSONString(orgInfo);
        return AjaxResult.success(result);
    }


    /**
     * 导出资产包列表
     */
    @RequiresPermissions("assetspackage:stream:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StreamPackage streamPackage)
    {
        List<StreamPackage> list = streamPackageService.selectStreamPackageList(streamPackage);
        ExcelUtil<StreamPackage> util = new ExcelUtil<StreamPackage>(StreamPackage.class);
        return util.exportExcel(list, "package");
    }


    @GetMapping("/downLoad")
    @ResponseBody
    @Log(title = "文件下载")
    public void downLoad(HttpServletRequest request, HttpServletResponse response, String downloadName,String downloadPath) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        } catch (IOException e) {
            logger.error("文件下载失败，失败原因{}", e);
        }
    }


}
