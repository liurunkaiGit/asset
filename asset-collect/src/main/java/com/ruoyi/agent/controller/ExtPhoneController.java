package com.ruoyi.agent.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.agent.domain.ExtPhone;
import com.ruoyi.agent.service.IExtPhoneService;
import com.ruoyi.assetspackage.domain.RemoteConfigure;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.RestTemplateUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.task.service.ITLcTaskService;
import com.ruoyi.utils.DataPermissionUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * 分机号码Controller
 *
 * @author guozeqi
 * @date 2020-03-02
 */
@Controller
@RequestMapping("/agent/phone")
public class ExtPhoneController extends BaseController {
    private String prefix = "agent";

    @Autowired
    private IExtPhoneService extPhoneService;
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private ITLcTaskService taskService;
    @Autowired
    RemoteConfigure remoteConfigure;
    @Autowired
    private DataPermissionUtil dataPermissionUtil;
    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("agent:phone:view")
    @GetMapping()
    public String phone() {
        return prefix + "/phone";
    }

    /**
     * 查询分机号码列表
     */
    @RequiresPermissions("agent:phone:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ExtPhone extPhone) {
        startPage();
        List<ExtPhone> list = extPhoneService.selectExtPhoneList(extPhone);
        return getDataTable(list);
    }

    /**
     * 导出分机号码列表
     */
    @RequiresPermissions("agent:phone:export")
    @Log(title = "分机号码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExtPhone extPhone) {
        List<ExtPhone> list = extPhoneService.selectExtPhoneList(extPhone);
        ExcelUtil<ExtPhone> util = new ExcelUtil<ExtPhone>(ExtPhone.class);
        return util.exportExcel(list, "phone");
    }

    /**
     * 新增分机号码
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存分机号码
     */
    @RequiresPermissions("agent:phone:add")
    @Log(title = "分机号码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ExtPhone extPhone) {
        //获取代理地址
        String agentId = extPhone.getAgentid();
        String url = remoteConfigure.getTelphoneProxyUrl() + "/config/agent?agentId=" + agentId;
        String forObject = restTemplateUtil.getRestTemplate().getForObject(url, String.class);
        JSONObject remoteReslut = JSON.parseObject(forObject);
        String proxyUrl = (String) remoteReslut.get("proxyUrl");

        String uuid = UUID.randomUUID().toString().replace("-", "");
        String loginName = ShiroUtils.getLoginName();
        extPhone.setProxyUrl(proxyUrl);
        if (extPhone.getCallPlatform().equals("ZJ")) {
            extPhone.setProxyUrl("172.18.100.1:8088");
        }
        extPhone.setId(uuid);
        extPhone.setCreateBy(loginName);
        return toAjax(extPhoneService.insertExtPhone(extPhone));
    }

    /**
     * 修改分机号码
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        ExtPhone extPhone = extPhoneService.selectExtPhoneById(id);
        mmap.put("extPhone", extPhone);
        return prefix + "/edit";
    }

    /**
     * 修改保存分机号码
     */
    @RequiresPermissions("agent:phone:edit")
    @Log(title = "分机号码", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ExtPhone extPhone) {
        //获取代理地址
        String agentId = extPhone.getAgentid();
        String url = "https://unicom.hisancc.net:10094/config/agent?agentId=" + agentId;
        String forObject = restTemplateUtil.getRestTemplate().getForObject(url, String.class);
        JSONObject remoteReslut = JSON.parseObject(forObject);
        String proxyUrl = (String) remoteReslut.get("proxyUrl");

        String loginName = ShiroUtils.getLoginName();
        extPhone.setUpdateBy(loginName);
        extPhone.setProxyUrl(proxyUrl);
        return toAjax(extPhoneService.updateExtPhone(extPhone));
    }

    /**
     * 分机号码状态修改
     */
    @RequiresPermissions("agent:phone:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(ExtPhone extPhone) {
        return toAjax(extPhoneService.updateExtPhoneStatus(extPhone));
    }

    /**
     * 删除分机号码
     */
    @RequiresPermissions("agent:phone:remove")
    @Log(title = "分机号码", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(extPhoneService.deleteExtPhoneByIds(ids));
    }

    @GetMapping("/toBindAgent")
    public String toFindReAllocatUser(ModelMap modelMap,String callPlatform) {
        modelMap.put("callPlatform",callPlatform);
        return prefix + "/bindAgent";
    }

    @PostMapping(value = "/initAgent")
    @ResponseBody
    public TableDataInfo initAgent(SysUser sysUser,String callPlatform) {
        TableDataInfo rspData = new TableDataInfo();
        Set<Long> deptIds = dataPermissionUtil.selectDataPer();
        sysUser.setDeptIds(deptIds);
        sysUser.setDeptId(sysUser.getDeptId());
        sysUser.setAvatar(callPlatform);
        startPage();
        List<SysUser> userList = this.extPhoneService.selectNoBindUser(sysUser);
        return getDataTable(userList);
//        PageDomain pageDomain = TableSupport.buildPageRequest();
//        if (null == pageDomain.getPageNum() || null == pageDomain.getPageSize()) {
//            rspData.setRows(userList);
//            rspData.setTotal(userList.size());
//            return rspData;
//        }
//        Integer pageNum = (pageDomain.getPageNum() - 1) * 10;
//        Integer pageSize = pageDomain.getPageNum() * 10;
//        if (pageSize > userList.size()) {
//            pageSize = userList.size();
//        }
//        rspData.setRows(userList.subList(pageNum, pageSize));
//        rspData.setTotal(userList.size());
//        return rspData;
    }


}
