package com.ruoyi.columnQuery.controller;

import com.ruoyi.columnQuery.domain.TLcColumnQuery;
import com.ruoyi.columnQuery.service.ITLcColumnQueryService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.ColumnEnum;
import com.ruoyi.common.enums.TableEnum;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.robot.enums.TaskStatus;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.utils.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字段查询配置Controller
 *
 * @author liurunkai
 * @date 2020-05-22
 */
@Controller
@RequestMapping("/column/query")
public class TLcColumnQueryController extends BaseController {
    private String prefix = "columnQuery";

    @Autowired
    private ITLcColumnQueryService tLcColumnQueryService;
    @Autowired
    private ISysDictDataService dictDataService;

    @RequiresPermissions("column:query:view")
    @GetMapping()
    public String query() {
        return prefix + "/query";
    }

    /**
     * 查询字段查询配置列表
     */
    @RequiresPermissions("column:query:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcColumnQuery tLcColumnQuery) {
        startPage();
        List<TLcColumnQuery> list = tLcColumnQueryService.selectTLcColumnQueryList(tLcColumnQuery);
        return getDataTable(list);
    }

    /**
     * 导出字段查询配置列表
     */
    @RequiresPermissions("column:query:export")
    @Log(title = "字段查询配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcColumnQuery tLcColumnQuery) {
        List<TLcColumnQuery> list = tLcColumnQueryService.selectTLcColumnQueryList(tLcColumnQuery);
        ExcelUtil<TLcColumnQuery> util = new ExcelUtil<TLcColumnQuery>(TLcColumnQuery.class);
        return util.exportExcel(list, "query");
    }

    /**
     * 新增字段查询配置
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        Map<String, List<ColumnEnum>> columnListMap = Arrays.stream(ColumnEnum.values())
                .collect(Collectors.groupingBy(ColumnEnum::getColumnType));
        for (Map.Entry columnMap : columnListMap.entrySet()) {
            modelMap.put(String.valueOf(columnMap.getKey()), columnMap.getValue());
        }
        return prefix + "/add";
    }

    /**
     * 新增保存字段查询配置
     */
    @RequiresPermissions("column:query:add")
    @Log(title = "字段查询配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public Response addSave(TLcColumnQuery tLcColumnQuery) {
        tLcColumnQueryService.insertTLcColumnQuery(tLcColumnQuery);
        return Response.success(Response.ResponseStatusEnum.SUCCESS.getStatus(), "保存成功", null);
    }

    /**
     * 修改字段查询配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcColumnQuery tLcColumnQuery = tLcColumnQueryService.selectTLcColumnQueryById(id);
        mmap.put("tLcColumnQuery", tLcColumnQuery);
        return prefix + "/edit";
    }

    /**
     * 修改保存字段查询配置
     */
    @RequiresPermissions("column:query:edit")
    @Log(title = "字段查询配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcColumnQuery tLcColumnQuery) {
        return toAjax(tLcColumnQueryService.updateTLcColumnQuery(tLcColumnQuery));
    }

    /**
     * 删除字段查询配置
     */
    @RequiresPermissions("column:query:remove")
    @Log(title = "字段查询配置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcColumnQueryService.deleteTLcColumnQueryByIds(ids));
    }

    @PostMapping(value = "/initTableName")
    @ResponseBody
    public Response initTableName() {
//        List<Map<String, Object>> tableList = Arrays.stream(TableEnum.values())
//                .map(table -> {
//                    Map<String, Object> tableMap = new HashMap<>();
//                    tableMap.put("tableName", table.getTableName());
//                    tableMap.put("tableNameComment", table.getTableNameComment());
//                    return tableMap;
//                }).collect(Collectors.toList());
        List<SysDictData> sysDictDataList = this.dictDataService.selectDictDataByType("colume_query_table");
        return Response.success(Response.ResponseStatusEnum.SUCCESS.getStatus(), "success", sysDictDataList);
    }

    @PostMapping(value = "/initColumnType")
    @ResponseBody
    public Response initColumnType() {
        List<SysDictData> sysDictDataList = this.dictDataService.selectDictDataByType("column_query_compare_type");
        return Response.success(Response.ResponseStatusEnum.SUCCESS.getStatus(), "success", sysDictDataList);
    }

    @PostMapping(value = "/getDictData")
    @ResponseBody
    public Response getDictData(String columnName, String tableName, String columnType) {
        TLcColumnQuery columnQuery = TLcColumnQuery.builder().tableName(tableName).columnName(columnName).columnType(columnType).orgId(ShiroUtils.getSysUser().getOrgId()).build();
        columnQuery = this.tLcColumnQueryService.selectTLcColumnQueryList(columnQuery).get(0);
        List<Map<String, Object>> dictDataList = Arrays.stream(columnQuery.getColumnValue().split(","))
                .map(dictData -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("key", dictData.split(":")[0]);
                    map.put("value", dictData.split(":")[1]);
                    return map;
                }).collect(Collectors.toList());
        return Response.success(Response.ResponseStatusEnum.SUCCESS.getStatus(), "success", dictDataList);
    }

    @PostMapping(value = "/appendCompareMethod")
    @ResponseBody
    public Response appendCompareMethod(String columnName, String tableName, String columnType) {
        TLcColumnQuery columnQuery = TLcColumnQuery.builder().tableName(tableName).columnName(columnName).columnType(columnType).orgId(ShiroUtils.getSysUser().getOrgId()).build();
        columnQuery = this.tLcColumnQueryService.selectTLcColumnQueryList(columnQuery).get(0);
        List<Map<String, Object>> dictDataList = Arrays.stream(columnQuery.getColumnValue().split(","))
                .map(compareMethod -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("key", compareMethod);
                    if (compareMethod.equals("equal")) {
                        map.put("value", "等于");
                    } else if (compareMethod.equals("like")) {
                        map.put("value", "包含");
                    }
                    return map;
                }).collect(Collectors.toList());
        return Response.success(Response.ResponseStatusEnum.SUCCESS.getStatus(), "success", dictDataList);
    }

    @PostMapping(value = "/getColumnByTable")
    @ResponseBody
    public Response getColumnByTable(String tableName) {
//        List<Map<String, Object>> columnList = this.tLcColumnQueryService.getColumnByTable(tableName.split(",")[0]);
        List<SysDictData> sysDictDataList = this.dictDataService.selectDictDataByType(tableName);
        return Response.success(Response.ResponseStatusEnum.SUCCESS.getStatus(), "success", sysDictDataList);
    }

    @ResponseBody
    @PostMapping("/initColumnQuery")
    public TableDataInfo initColumnQuery(TLcColumnQuery tLcColumnQuery) {
        List<TLcColumnQuery> columnQueryList = this.tLcColumnQueryService.selectTLcColumnQueryList(tLcColumnQuery);
        return getDataTable(columnQueryList);
    }
}
