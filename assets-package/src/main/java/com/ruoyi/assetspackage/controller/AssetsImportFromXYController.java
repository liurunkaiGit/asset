package com.ruoyi.assetspackage.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.mapper.AssetsImportFromXYMapper;
import com.ruoyi.assetspackage.service.IAssetsImportFromXYService;
import com.ruoyi.assetspackage.service.ICurAssetsPackageService;
import com.ruoyi.assetspackage.service.ITemplatesPackageService;
import com.ruoyi.assetspackage.service.impl.AssetsImportFromXYServiceImpl;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户资产Controller
 *
 * @author guozeqi
 * @date 2020-07-13
 */
@Controller
@RequestMapping("/xyImport/assets")
public class AssetsImportFromXYController extends BaseController {
    private String prefix = "assetspackage/xyAssetImport";

    @Autowired
    ImportDataMapping importDataMapping;

    @Autowired
    RemoteConfigure remoteConfigure;

    @Autowired
    private IAssetsImportFromXYService assetsImportFromXYService;

    @Autowired
    private ICurAssetsPackageService curAssetsPackageService;

    @Autowired
    private ITemplatesPackageService templatesPackageService;

    @Autowired
    private AssetsImportFromXYServiceImpl assetsImportFromXYServiceImpl;

    @Autowired
    private AssetsImportFromXYMapper assetsImportFromXYMapper;


    @RequiresPermissions("xyImport:assets:view")
    @GetMapping()
    public String assetspackage() {
        return prefix + "/xyImport";
    }


    @GetMapping("toStatistics/{importBatchNo}")
    public String toStatistics(@PathVariable("importBatchNo") String importBatchNo, ModelMap modelMap) {
        modelMap.put("importBatchNo",importBatchNo);
        return prefix + "/statistics";
    }

    @GetMapping("toStatisticsDetail")
    public String toStatisticsDetail(String importBatchNo, ModelMap modelMap) {
        return prefix + "/statisticsDetail";
    }

    @GetMapping("fileDetail")
    public String fileDetail(String importBatchNo, ModelMap modelMap) {
        modelMap.put("importBatchNo",importBatchNo);
        return prefix + "/filestatics";
    }


    /**
     * 查询文件列表
     */
    @PostMapping("/fileList/{importBatchNo}")
    @ResponseBody
    public TableDataInfo fileList(@PathVariable("importBatchNo")String importBatchNo) {
        List<Map<String,Object>> fileList = null;
        try {
            fileList = this.assetsImportFromXYService.selectFileList(importBatchNo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询导入流水异常",e);
        }
        return getDataTable(fileList);
    }

    /**
     * 查询流水列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcImportFlowForXy tlcImportFlowForXy) {
        List<TLcImportFlowForXy> tLcImportFlowForXIES = null;
        try {
            startPage();
            tLcImportFlowForXIES = this.assetsImportFromXYService.selectFlowList(tlcImportFlowForXy);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询导入流水异常",e);
        }
        return getDataTable(tLcImportFlowForXIES);
    }


    @PostMapping("/findTemplate")
    @ResponseBody
    public AjaxResult findTemplate(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        //查询所有的资产模板
        TemplatesPackage param = new TemplatesPackage();
        param.setType("4");//资产模板(兴业导入改为余额表，导入模板字段少于资产表字段，必输项校验不通过，改为更新模板)
        param.setOrgId(String.valueOf(ShiroUtils.getSysUser().getOrgId()));
        List<TemplatesPackage> templatesList = this.templatesPackageService.selectTemplatesPackageList(param);
        for (TemplatesPackage templateInfo : templatesList) {
            String id = templateInfo.getId();
            String name = templateInfo.getName();
            map.put(id, name);
        }
        String result = JSON.toJSONString(map);
        return AjaxResult.success(result);
    }


    @GetMapping("deleteTempTable/{importBatchNo}")
    @ResponseBody
    public AjaxResult deleteTempTable(@PathVariable("importBatchNo") String importBatchNo,HttpServletRequest request) {
        try {
            /**1删除临时表*/
            this.curAssetsPackageService.deleteTempCurAssetsPackage(importBatchNo);
            /**2获取标识*/
            String insertFlag = null;//新增标识
            String updateFlag = null;//更新标识
            Object value1 = request.getSession().getAttribute("insertFlag");
            Object value2 = request.getSession().getAttribute("updateFlag");
            if(value1 != null && value1 instanceof String){
                insertFlag = (String)value1;
            }
            if(value2 != null && value2 instanceof String){
                updateFlag = (String)value2;
            }
            /**3判断标识*/
            if(insertFlag==null && updateFlag==null){//没有操作
                this.assetsImportFromXYService.deleteFlowXyByBatchNo(importBatchNo);//删除流水表
            }
            if("error".equals(insertFlag)){//新增失败
                if(!"success".equals(updateFlag)){
                    this.assetsImportFromXYService.deleteFlowXyByBatchNo(importBatchNo);//删除流水表
                }
            }
            if("error".equals(updateFlag)){//更新失败
                if(!"success".equals(insertFlag)){
                    this.assetsImportFromXYService.deleteFlowXyByBatchNo(importBatchNo);//删除流水表
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("清空临时表异常",e);
            return error("清空临时表异常");
        }
        return success();
    }


    /**
     * 导入
     * @param request
     * @param files
     * @return
     */
    @RequiresPermissions("import:assets:upload")
    @PostMapping("/upload")
    @ResponseBody
    @Log(title = "数据导入")
    public AjaxResult upload(HttpServletRequest request, @RequestParam("file") MultipartFile[] files, String templateId) {
        logger.error("兴业消金导入时间="+DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, new Date())+"，导入人="+ShiroUtils.getSysUser().getUserName());
        if(StringUtils.isEmpty(templateId)){
            return AjaxResult.success("请先选择模板");
        }
        String importBatchNo =DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS, new Date());// 生成导入批次号年月日时分秒;
        String orgId = String.valueOf(ShiroUtils.getSysUser().getOrgId());
        try {
            for (MultipartFile file : files) {
                this.assetsImportFromXYService.handler(request, file, templateId, orgId, importBatchNo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据导入失败:{}",e);
            if(e instanceof BusinessException){
                return AjaxResult.success(e.getMessage());
            }
            return AjaxResult.success("数据导入失败",e.getMessage());
        }
        return AjaxResult.success("导入临时表成功",importBatchNo);
    }


    /**
     * 数据校验
     * @param request
     * @return
     */
    @PostMapping("/checkData")
    @ResponseBody
    public AjaxResult checkData(HttpServletRequest request,String importBatchNo) {
        Map<String, String> map = null;
        String result = "";
        try {
            map = this.assetsImportFromXYService.checkData(request, importBatchNo);
            result = JSON.toJSONString(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("资产导入数据检查异常"+e.getMessage(),e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }


    /**
     * 新增主表记录
     * @return
     */
    @PostMapping("/insert")
    @ResponseBody
    public AjaxResult insert(HttpServletRequest request,String importBatchNo,
                             String orgId,String orgName,String totalNum,String addNum,
                             String modifyNum,String urgeNum,String createBy)
    {
        try {
            List<TempCurAssetsPackage> insertList = this.curAssetsPackageService.selectInsertList(importBatchNo);
            this.assetsImportFromXYService.batchAddAssets(insertList);

            Date createTime = (Date)request.getSession().getAttribute("urgeCreateTime");//临时表的创建时间
            //流水表参数
            TLcImportFlowForXy importFlow = new TLcImportFlowForXy();
            importFlow.setImportBatchNo(importBatchNo)
                    .setOrgId(orgId)
                    .setOrgName(orgName)
                    .setTotalNum(Integer.valueOf(totalNum))
                    .setAddNum(Integer.valueOf(addNum))
                    .setModifyNum(Integer.valueOf(modifyNum))
                    .setUrgeNum(Integer.valueOf(urgeNum));
            importFlow.setCreateTime(createTime);
            importFlow.setCreateBy(createBy);

            Map<String,String> param = new HashMap<>();
            param.put("importBatchNo",importBatchNo);
            param.put("orgId",orgId);
            long xyFlow = assetsImportFromXYMapper.findXyFlow(param);//查询流水是否存在
            if(xyFlow > 0){
                //更新流水表
                assetsImportFromXYMapper.updateflowForXy(importFlow);
            }else{
                //插入流水表
                assetsImportFromXYMapper.insetflowForXy(importFlow);
            }
            request.getSession().setAttribute("insertFlag","success");
        } catch (Exception e) {
            request.getSession().setAttribute("insertFlag","error");
            e.printStackTrace();
            logger.error("新增失败"+e.getMessage(),e);
            return AjaxResult.success("新增失败");
        }
        return AjaxResult.success("新增成功");
    }


    /**
     * 更新主表记录
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    @Transactional
    public AjaxResult update(HttpServletRequest request,String importBatchNo,
                             String orgId,String orgName,String totalNum,String addNum,
                             String modifyNum,String urgeNum,String createBy)
    {
        try {
            //更新资产表
            this.curAssetsPackageService.updateHandler(request,importBatchNo);

            List<CurAssetsPackage> preSettleList = request.getSession().getAttribute("preSettleList") != null ?(List<CurAssetsPackage>)request.getSession().getAttribute("preSettleList") :null;//回收结案=需要结案-出催案件
            List<CurAssetsPackage> urgeList = request.getSession().getAttribute("urgeList") != null ?(List<CurAssetsPackage>)request.getSession().getAttribute("urgeList") :null;//出催案件
            Date createTime = (Date)request.getSession().getAttribute("urgeCreateTime");//临时表的创建时间
            //结案处理
            if(preSettleList !=null && urgeList != null){
                assetsImportFromXYServiceImpl.updateCloseCase(preSettleList,createTime,"2");//回收结案=需要结案-出催案件
                assetsImportFromXYServiceImpl.updateCloseCase(urgeList,createTime,"1");//出催案件
            }
            //出催统计临时表复制到正式表
            assetsImportFromXYMapper.insertTlcUrge(importBatchNo);
            assetsImportFromXYMapper.deleteTlcUrgeTemp(importBatchNo);
            //流水表参数
            TLcImportFlowForXy importFlow = new TLcImportFlowForXy();
            importFlow.setImportBatchNo(importBatchNo)
                    .setOrgId(orgId)
                    .setOrgName(orgName)
                    .setTotalNum(Integer.valueOf(totalNum))
                    .setAddNum(Integer.valueOf(addNum))
                    .setModifyNum(Integer.valueOf(modifyNum))
                    .setUrgeNum(Integer.valueOf(urgeNum));
            importFlow.setCreateTime(createTime);
            importFlow.setCreateBy(createBy);

            Map<String,String> param = new HashMap<>();
            param.put("importBatchNo",importBatchNo);
            param.put("orgId",orgId);
            long xyFlow = assetsImportFromXYMapper.findXyFlow(param);//查询流水是否存在
            if(xyFlow > 0){
                //更新流水表
                assetsImportFromXYMapper.updateflowForXy(importFlow);
            }else{
                //插入流水表
                assetsImportFromXYMapper.insetflowForXy(importFlow);
            }
            request.getSession().setAttribute("updateFlag","success");
        } catch (Exception e) {
            request.getSession().setAttribute("updateFlag","error");
            e.printStackTrace();
            logger.error("更新失败"+e.getMessage(),e);
            return AjaxResult.error("更新失败");
        }
        return AjaxResult.success("更新成功");
    }


    /**
     * 出催统计页面
     * @param modelMap
     * @return
     */
    @GetMapping("urge")
    public String urge(ModelMap modelMap) {
        return prefix + "/urge";
    }


    /**
     *出催统计列表
     * @param tLcUrge
     * @return
     */
    @PostMapping("/urgeList")
    @ResponseBody
    public TableDataInfo urgeList(TLcUrge tLcUrge) {
        List<TLcUrge> tLcUrges = null;
        try {
            startPage();
            tLcUrges = this.assetsImportFromXYService.selectUrgeList(tLcUrge);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询出催统计列表失败{}",e);
        }
        return getDataTable(tLcUrges);
    }

    /**
     * 出催统计导出
     */
    @PostMapping("/urgeExport")
    @ResponseBody
    public AjaxResult urgeExport(TLcUrge tLcUrge) {
        List<TLcUrge> tLcUrges = this.assetsImportFromXYService.selectUrgeList(tLcUrge);
        ExcelUtil<TLcUrge> util = new ExcelUtil<TLcUrge>(TLcUrge.class);
        return util.exportExcel(tLcUrges, "出催统计");

    }
}
