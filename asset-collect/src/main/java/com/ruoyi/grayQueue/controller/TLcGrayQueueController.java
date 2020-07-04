package com.ruoyi.grayQueue.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.enums.GrayQueueReasonEnum;
import com.ruoyi.grayQueue.domain.TLcGrayQueue;
import com.ruoyi.grayQueue.service.ITLcGrayQueueService;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.service.ITLcTaskService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 灰色队列Controller
 *
 * @author liurunkai
 * @date 2020-01-09
 */
@Controller
@RequestMapping("/collect/gray/queue")
public class TLcGrayQueueController extends BaseController {
    private String prefix = "grayQueue";

    @Autowired
    private ITLcGrayQueueService tLcGrayQueueService;
    @Autowired
    private ITLcTaskService tLcTaskService;

    @RequiresPermissions("ruoyi:queue:view")
    @GetMapping()
    public String queue() {
        return prefix + "/queue";
    }

    /**
     * 查询灰色队列列表
     */
    @RequiresPermissions("ruoyi:queue:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcGrayQueue tLcGrayQueue) {
        startPage();
        List<TLcGrayQueue> list = tLcGrayQueueService.selectTLcGrayQueueList(tLcGrayQueue);
        return getDataTable(list);
    }

    /**
     * 初始化灰色原因下拉框
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/initGrayReason")
    public TableDataInfo initGrayReason() {
        List<ActionCodeObj> actionCodeList = Arrays.stream(GrayQueueReasonEnum.values())
                .map(actionCode -> ActionCodeObj.builder().code(actionCode.getCode()).message(actionCode.getMessage()).build())
                .collect(Collectors.toList());
        return getDataTable(actionCodeList);
    }

    /**
     * 导出灰色队列列表
     */
    @RequiresPermissions("ruoyi:queue:export")
    @Log(title = "灰色队列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcGrayQueue tLcGrayQueue) {
        List<TLcGrayQueue> list = tLcGrayQueueService.selectTLcGrayQueueList(tLcGrayQueue);
        ExcelUtil<TLcGrayQueue> util = new ExcelUtil<TLcGrayQueue>(TLcGrayQueue.class);
        return util.exportExcel(list, "queue");
    }

    /**
     * 跳转到添加灰色队列页面
     *
     * @return
     */
    @GetMapping("/toAddGrayQueue")
    public String toAddGrayQueue() {
        return prefix + "/addGrayQueue";
    }

    @ResponseBody
    @PostMapping("/findTaskList")
    public TableDataInfo list(String customName, String certificateNo) {
        TableDataInfo rspData = new TableDataInfo();
        TLcTask tLcTask = TLcTask.builder().customName(customName).certificateNo(certificateNo).build();
        List<TLcTask> taskList = this.tLcTaskService.selectTLcTaskList(tLcTask);
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (null == pageDomain.getPageNum() || null == pageDomain.getPageSize()) {
            rspData.setRows(taskList);
            rspData.setTotal(taskList.size());
            return rspData;
        }
        Integer pageNum = (pageDomain.getPageNum() - 1) * 10;
        Integer pageSize = pageDomain.getPageNum() * 10;
        if (pageSize > taskList.size()) {
            pageSize = taskList.size();
        }
        rspData.setRows(taskList.subList(pageNum, pageSize));
        rspData.setTotal(taskList.size());
        return rspData;
    }

    /**
     * 新增灰色队列
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存灰色队列
     */
    @RequiresPermissions("ruoyi:queue:add")
    @Log(title = "灰色队列", businessType = BusinessType.INSERT)
    @PostMapping("/addSave")
    @ResponseBody
    public AjaxResult addSave(TLcGrayQueue tLcGrayQueue) {
        return toAjax(tLcGrayQueueService.insertTLcGrayQueue(tLcGrayQueue));
    }

    @PostMapping("/addGrayQueue")
    @ResponseBody
    public AjaxResult addGrayQueue(TLcGrayQueue tLcGrayQueue, String taskIds) {
        return toAjax(tLcGrayQueueService.addGrayQueue(tLcGrayQueue,taskIds));
    }

    /**
     * 修改灰色队列
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcGrayQueue tLcGrayQueue = tLcGrayQueueService.selectTLcGrayQueueById(id);
        mmap.put("tLcGrayQueue", tLcGrayQueue);
        return prefix + "/edit";
    }

    /**
     * 修改保存灰色队列
     */
    @RequiresPermissions("ruoyi:queue:edit")
    @Log(title = "灰色队列", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcGrayQueue tLcGrayQueue) {
        return toAjax(tLcGrayQueueService.updateTLcGrayQueue(tLcGrayQueue));
    }

    /**
     * 删除灰色队列
     */
    @RequiresPermissions("ruoyi:queue:remove")
    @Log(title = "灰色队列", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcGrayQueueService.deleteTLcGrayQueueByIds(ids));
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
class ActionCodeObj {
    private Integer code;
    private String message;
}
