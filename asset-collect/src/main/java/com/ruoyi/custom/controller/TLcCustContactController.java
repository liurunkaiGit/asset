package com.ruoyi.custom.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.service.ITLcCustContactService;
import com.ruoyi.enums.ContactOriginEnum;
import com.ruoyi.utils.CustContactRuleUtil;
import com.ruoyi.utils.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户联系人信息Controller
 *
 * @author liurunkai
 * @date 2019-12-26
 */
@Controller
@RequestMapping("/collect/cust/contact")
public class TLcCustContactController extends BaseController {
    private String prefix = "custom/contact";

    @Autowired
    private ITLcCustContactService tLcCustContactService;

    @Autowired
    private CustContactRuleUtil custContactRuleUtil;

    @RequiresPermissions("system:contact:view")
    @GetMapping()
    public String contact() {
        return prefix + "/contact";
    }

    /**
     * 查询客户联系人信息列表
     */
    @RequiresPermissions("system:contact:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcCustContact tLcCustContact) {
        startPage();
        List<TLcCustContact> list = tLcCustContactService.selectTLcCustContactList(tLcCustContact);
        return getDataTable(list);
    }

    /**
     * 导出客户联系人信息列表
     */
    @RequiresPermissions("system:contact:export")
    @Log(title = "客户联系人信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcCustContact tLcCustContact) {
        List<TLcCustContact> list = tLcCustContactService.selectTLcCustContactList(tLcCustContact);
        ExcelUtil<TLcCustContact> util = new ExcelUtil<TLcCustContact>(TLcCustContact.class);
        return util.exportExcel(list, "contact");
    }

    /**
     * 新增客户联系人信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存客户联系人信息
     */
    @RequiresPermissions("system:contact:add")
    @Log(title = "客户联系人信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcCustContact tLcCustContact) {
        return toAjax(tLcCustContactService.insertTLcCustContact(tLcCustContact));
    }

    /**
     * 新增保存客户联系人信息
     */
    @PostMapping("/addContact")
    @ResponseBody
    public AjaxResult addContact(TLcCustContact tLcCustContact) {
        logger.info("保存客户联系人信息开始");
        tLcCustContactService.insertTLcCustContact(tLcCustContact);
        logger.info("保存客户联系人信息结束");
        return success();
    }

    /**
     * 修改客户联系人信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcCustContact tLcCustContact = tLcCustContactService.selectTLcCustContactById(id);
        mmap.put("tLcCustContact", tLcCustContact);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户联系人信息
     */
    @RequiresPermissions("system:contact:edit")
    @Log(title = "客户联系人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcCustContact tLcCustContact) {
        return toAjax(tLcCustContactService.updateTLcCustContact(tLcCustContact));
    }

    /**
     * 删除客户联系人信息
     */
    @RequiresPermissions("system:contact:remove")
    @Log(title = "客户联系人信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcCustContactService.deleteTLcCustContactByIds(ids));
    }

    /**
     * 根据案件编号，得到联系人
     */
    @Log(title = "客户联系人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/getCustCount")
    @ResponseBody
    public AjaxResult getCustContact(String caseNoStr,String importBatchNoStr,String isHasOther) {
        List<TLcCustContact> list = new ArrayList<TLcCustContact>();
        if(StringUtils.isNotEmpty(caseNoStr)){
            String[] caseNoRows = caseNoStr.split(",");
            String[] importBatchRows = importBatchNoStr.split(",");
            TLcCustContact tcc = new TLcCustContact();
            tcc.setCaseNoList(Arrays.asList(caseNoRows));
            tcc.setImportBatchNoList(Arrays.asList(importBatchRows));
            tcc.setIsClose("0");//正常，未停播
            if(StringUtils.isNotEmpty(isHasOther)){
                if("0".equals(isHasOther)){//只有本人
                    tcc.setRelation(1);
                }
            }
            list = this.tLcCustContactService.selectTLcCustContactList(tcc);
            /*for (int i = 0; i < caseNoRows.length ; i ++){
                List<TLcCustContact> tmplist = tLcCustContactService.findCustContactByCaseNo(caseNoRows[i]);
                if(!tmplist.isEmpty()){
                    list.addAll(tmplist) ;
                }
            }*/
        }
        List<TLcCustContact> tLcCustContacts = custContactRuleUtil.custContactRule(list);
        return AjaxResult.success(tLcCustContacts.size());
    }
}
