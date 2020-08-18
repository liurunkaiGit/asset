package com.ruoyi.letter.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.letter.domain.TLcLetter;
import com.ruoyi.letter.service.ITLcLetterService;
import com.ruoyi.utils.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author liurunkai
 * @date 2020-08-17
 */
@Controller
@RequestMapping("/coll/letter")
public class TLcLetterController extends BaseController {
    private String prefix = "letter";

    @Autowired
    private ITLcLetterService tLcLetterService;

    @RequiresPermissions("coll:letter:view")
    @GetMapping()
    public String letter() {
        return prefix + "/letter";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("coll:letter:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcLetter tLcLetter) {
        startPage();
        tLcLetter.setOrgId(ShiroUtils.getSysUser().getOrgId());
        List<TLcLetter> list = tLcLetterService.selectTLcLetterList(tLcLetter);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("coll:letter:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcLetter tLcLetter) {
        List<TLcLetter> list;
        if (StringUtils.isNotBlank(tLcLetter.getLetterIds())) {
            list = tLcLetterService.selectLetterListByIds(tLcLetter.getLetterIds());
        } else {
            list = tLcLetterService.selectTLcLetterList(tLcLetter);
        }
        ExcelUtil<TLcLetter> util = new ExcelUtil<TLcLetter>(TLcLetter.class);
        return util.exportExcel(list, "letter");
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @PostMapping("/addLetter")
    @ResponseBody
    public Response addLetter(TLcLetter tLcLetter) {
        return this.tLcLetterService.insertTLcLetter(tLcLetter);
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @PostMapping("/letterApply")
    @ResponseBody
    public AjaxResult letterApply(TLcLetter tLcLetter) {
        return toAjax(tLcLetterService.updateTLcLetter(tLcLetter));
    }

    /**
     * 审批
     */
    @RequiresPermissions("coll:letter:apply")
    @GetMapping("/letterApply")
    @ResponseBody
    public AjaxResult letterApply(String letterIds,Integer status) {
        return toAjax(tLcLetterService.apply(letterIds,status));
    }

    /**
     * 查询信函状态
     */
    @PostMapping("/letterStatus")
    @ResponseBody
    public Response letterStatus(TLcLetter letter) {
        List<TLcLetter> tLcLetters = this.tLcLetterService.selectTLcLetterList(letter);
        return Response.success(tLcLetters);
    }
}
