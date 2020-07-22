package com.ruoyi.robot.controller;

import com.ruoyi.columnQuery.service.ITLcColumnQueryService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.robot.domain.CallContent;
import com.ruoyi.robot.domain.TLcRobotCallRecordMeteData;
import com.ruoyi.robot.domain.TLcRobotTask;
import com.ruoyi.robot.service.ITLcRobotCallRecordMeteDataService;
import com.ruoyi.robot.service.ITLcRobotTaskService;
import com.ruoyi.robot.service.RobotService;
import com.ruoyi.utils.DataPermissionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * @Description: 机器人历史呼叫明细controller
 * @author: liurunkai
 * @Date: 2020/2/7 16:57
 */
@Slf4j
@Controller
@RequestMapping("/robot/his")
public class RobotHisController extends BaseController {

    private String prefix = "robot";
    private String taskPrefix = "task";

    @Autowired
    private RobotService robotService;
    @Autowired
    private ITLcRobotTaskService tLcRobotTaskService;
    @Autowired
    private ITLcRobotCallRecordMeteDataService callRecordMeteDataService;
    @Autowired
    private DataPermissionUtil dataPermissionUtil;

    @RequiresPermissions("robot:his:view")
    @GetMapping(value = "/view")
    public String toView(ModelMap modelMap) {
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        return prefix + "/robotHis";
    }

    /**
     * 查询机器人任务列表
     */
    @RequiresPermissions("robot:his:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcRobotTask tLcRobotTask, HttpServletRequest request) {
        startPage();
        List<TLcRobotTask> tLcRobotTasks = this.tLcRobotTaskService.selectTLcRobotTaskHisList(tLcRobotTask, request);
        return getDataTable(tLcRobotTasks);
    }

    /**
     * 导出机器人任务列表
     */
    @RequiresPermissions("collect:robot:export")
    @Log(title = "任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcRobotTask tLcRobotTask, HttpServletRequest request) {
        startPage();
        List<TLcRobotTask> tLcRobotTasks = this.tLcRobotTaskService.selectTLcRobotTaskHisList(tLcRobotTask, request);
        ExcelUtil<TLcRobotTask> util = new ExcelUtil<TLcRobotTask>(TLcRobotTask.class);
        return util.exportExcel(tLcRobotTasks, "robotTask");
    }

    /**
     * 催记页面
     */
    @RequiresPermissions("collect:robot:viewCollectRecord")
    @GetMapping("/viewCollectRecord")
    public String viewCollectRecord(String robotTaskId, ModelMap modelMap) {
        modelMap.put("callJobId", Long.valueOf(robotTaskId.split(",")[0]));
        return prefix + "/viewCollectRecord";
    }

    /**
     * 查看对应的催收记录
     */
    @RequiresPermissions("collect:robot:viewCollectRecord")
    @PostMapping("/viewCollectRecord")
    @ResponseBody
    public TableDataInfo viewCollectRecord(TLcRobotCallRecordMeteData callRecordMeteData) {
        List<TLcRobotCallRecordMeteData> callRecordMeteDataList = this.callRecordMeteDataService.selectTLcRobotCallRecordMeteDataList(callRecordMeteData);
        return getDataTable(callRecordMeteDataList);
    }

    /**
     * 播放通话录音
     */
    @GetMapping("/plyaLuyin")
    @ResponseBody
    public AjaxResult plyaLuyin(String luyinUrl) {
        this.robotService.plyaLuyin(luyinUrl);
        return AjaxResult.success();
    }

    /**
     * 下载通话录音
     */
    @GetMapping("/downLoad")
    public String downLoadLuyin(HttpServletResponse response, String ids, ModelMap modelMap) {
        Integer robotTaskId = this.robotService.downLoadLuyin(response, ids);
        modelMap.put("callJobId", robotTaskId);
        return prefix + "/viewCollectRecord";
    }

    /**
     * 下载通话录音
     */
    @GetMapping("/downLoadRobotRadio")
    public String downLoadRobotRadio(HttpServletResponse response, String luyinUrl, ModelMap modelMap) {
        Integer robotTaskId = this.robotService.downLoadRobotRadio(response, luyinUrl);
        modelMap.put("callJobId", robotTaskId);
        return prefix + "/viewCollectRecord";
    }

    /**
     * 跳转到查看通话内容页面
     *
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/toViewCallContent")
    public String toViewCallContent(String id, ModelMap modelMap, String caseNo) {
        modelMap.put("id", id);
        return prefix + "/viewCallContent";
    }

    /**
     * 跳转到查看通话内容页面
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/viewCallContent")
    public TableDataInfo viewCallContent(String id) {
        List<CallContent> callContentList = this.tLcRobotTaskService.viewCallContent(id);
        return getDataTable(callContentList);
    }

    /**
     * 跳转到查看通话内容页面
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/viewHisCallContentFromRobot")
    public TableDataInfo viewCallContentFromRobot(String id) {
        TableDataInfo rspData = new TableDataInfo();
        List<CallContent> callContentList = this.tLcRobotTaskService.viewHisCallContent(id);
        if (callContentList != null && callContentList.size() > 0) {
            PageDomain pageDomain = TableSupport.buildPageRequest();
            if (null == pageDomain.getPageNum() || null == pageDomain.getPageSize()) {
                rspData.setRows(callContentList);
                rspData.setTotal(callContentList.size());
                return rspData;
            }
            Integer pageNum = (pageDomain.getPageNum() - 1) * 10;
            Integer pageSize = pageDomain.getPageNum() * 10;
            if (pageSize > callContentList.size()) {
                pageSize = callContentList.size();
            }
            rspData.setRows(callContentList.subList(pageNum, pageSize));
            rspData.setTotal(callContentList.size());
        }
        return rspData;
    }

    /**
     * 录音播放
     */
    @GetMapping("/recordAudio")
    public String recordAudio(String id, ModelMap mmap) {
        TLcRobotTask tLcRobotTask = tLcRobotTaskService.selectHisTLcRobotTaskById(Long.valueOf(id));
        String callRadioLocation = tLcRobotTask.getCallRadio();
        mmap.put("callRadioLocation", callRadioLocation);
        return taskPrefix + "/recordAudio";
    }

    /**
     * 跳转到查看通话内容页面
     *
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/toViewHisRobotCallContent")
    public String toViewHisRobotCallContent(String id, ModelMap modelMap) {
        modelMap.put("id", id);
        return prefix + "/viewHisRobotCallContent";
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list, Integer total) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(total);
        return rspData;
    }

}
