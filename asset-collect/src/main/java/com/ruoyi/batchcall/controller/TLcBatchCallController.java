package com.ruoyi.batchcall.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.agent.domain.ExtPhone;
import com.ruoyi.agent.service.IExtPhoneService;
import com.ruoyi.batchcall.domain.TLcBatchCall;
import com.ruoyi.batchcall.domain.TLcBatchCallConfig;
import com.ruoyi.batchcall.service.ITLcBatchCallConfigService;
import com.ruoyi.batchcall.service.ITLcBatchCallService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.DuYanConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.service.ITLcTaskService;
import com.ruoyi.utils.DesensitizationUtil;
import com.ruoyi.utils.DuyanUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 批量外呼任务管理Controller
 *
 * @author 封志涛
 * @date 2020-07-02
 */
@Controller
@RequestMapping("/batchcall")
public class TLcBatchCallController extends BaseController {
    private String prefix = "batchcall";

    @Autowired
    private ITLcBatchCallService tLcBatchCallService;
    @Autowired
    private ITLcBatchCallConfigService tLcBatchCallConfigService;
    @Autowired
    private IExtPhoneService extPhoneService;
    @Autowired
    private DuYanConfig duYanConfig;
    @Autowired
    private DesensitizationUtil desensitizationUtil;
    @Autowired
    private ITLcTaskService tLcTaskService;

    @RequiresPermissions("ruoyi:batchcall:view")
    @GetMapping()
    public String batchcall(ModelMap modelMap, String isCanAutoCall) {
//        String isCanAutoCall = "1";//可自动外呼
        String orgId = ShiroUtils.getSysUser().getOrgId() + "";
        TLcBatchCallConfig tbcc = this.tLcBatchCallConfigService.selectTLcBatchCallConfigByOrgId(orgId);
        modelMap.put("isCanAutoCall", isCanAutoCall);
        modelMap.put("tLcBatchCallConfig", tbcc);//该部门的批量外呼配置信息

        ExtPhone extPhone = new ExtPhone();
        extPhone.setIsused("0");
        extPhone.setSeatId(Integer.valueOf(String.valueOf(ShiroUtils.getSysUser().getUserId())));
        extPhone.setCallPlatform(ShiroUtils.getSysUser().getPlatform());
        List<ExtPhone> list = extPhoneService.selectExtPhoneList(extPhone);
        String accountId = null;
        if (list != null && list.size() > 0) {
            if (list != null && list.size() > 0) {
                // 分机号码
                accountId = list.get(0).getAgentid();
                modelMap.put("extPhone", list.get(0));
                // 查询外显号码
            }
        } else {
            modelMap.put("extPhone", new ExtPhone());
        }
        TLcBatchCall tLcBatchCall = new TLcBatchCall();
        tLcBatchCall.setCreateBy(ShiroUtils.getLoginName() + "");
        tLcBatchCall.setOrgId(ShiroUtils.getSysUser().getOrgId() + "");
        tLcBatchCall.setIsOnlyOne("1");//查询第一条待拨电话
        //只查询状态为 暂停、外呼中、待外呼 的数据
        tLcBatchCall.setTaskStatusList(Arrays.asList(TLcBatchCall.WHZ, TLcBatchCall.DWH));
        List<TLcBatchCall> batchCallList = tLcBatchCallService.selectTLcBatchCallList(tLcBatchCall);
        if (batchCallList != null && batchCallList.size() > 0) {
            modelMap.put("batchCall", batchCallList);
        }
        modelMap.put("callPlatform", ShiroUtils.getSysUser().getPlatform());
        if ("DY".equals(ShiroUtils.getSysUser().getPlatform())) {
            try {
                modelMap.put("dytoken", DuyanUtil.getToken(duYanConfig.getTokenUrl(), accountId, duYanConfig.getApikey()));
                modelMap.put("accountId", accountId);
            } catch (Exception e) {
                logger.info("度言获取token失败accountId=" + accountId);
                e.printStackTrace();
            }
        }
        //查询是否脱敏
        boolean desensitization = desensitizationUtil.isDesensitization(String.valueOf(ShiroUtils.getSysUser().getOrgId()), ShiroUtils.getLoginName());
        modelMap.put("desensitization", desensitization);
        return prefix + "/batchcall";
    }

    /**
     * 查询批量外呼任务管理列表
     */
    @RequiresPermissions("ruoyi:batchcall:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcBatchCall tLcBatchCall) {
        startPage();
        tLcBatchCall.setCreateBy(ShiroUtils.getLoginName());
        tLcBatchCall.setOrgId(ShiroUtils.getSysUser().getOrgId() + "");
        //只查询状态为 暂停、外呼中、待外呼 的数据
//        tLcBatchCall.setTaskStatusList(Arrays.asList(TLcBatchCall.ZT,TLcBatchCall.WHZ,TLcBatchCall.DWH));
        List<TLcBatchCall> list = tLcBatchCallService.selectTLcBatchCallList(tLcBatchCall);
        Map<String, List<TLcBatchCall>> batchCallMap = list.stream().collect(Collectors.groupingBy(TLcBatchCall::getCaseNo));
        ArrayList<TLcBatchCall> result = new ArrayList<>();
        for (Map.Entry<String, List<TLcBatchCall>> map : batchCallMap.entrySet()) {
            List<TLcBatchCall> batchCallList = map.getValue();
            List<TLcBatchCall> selfList = batchCallList.stream().filter(batchCall -> batchCall.getContactRelation() == 1).collect(Collectors.toList());
            List<TLcBatchCall> noSelfList = batchCallList.stream().filter(batchCall -> batchCall.getContactRelation() != 1).collect(Collectors.toList());
            result.addAll(selfList);
            result.addAll(noSelfList);
        }
        return getDataTable(result);
    }

    @RequiresPermissions("batchcall:his:view")
    @GetMapping(value = "/his/view")
    public String batchcallHis() {
        return prefix + "/batchcallHis";
    }

    /**
     * 查询批量外呼历史任务管理列表
     */
    @RequiresPermissions("batchcall:his:list")
    @PostMapping("/his/list")
    @ResponseBody
    public TableDataInfo hisList(TLcBatchCall tLcBatchCall) {
        startPage();
        tLcBatchCall.setCreateBy(ShiroUtils.getLoginName());
        tLcBatchCall.setOrgId(ShiroUtils.getSysUser().getOrgId() + "");
        //只查询状态为 暂停、外呼中、待外呼 的数据
//        tLcBatchCall.setTaskStatusList(Arrays.asList(TLcBatchCall.ZT,TLcBatchCall.WHZ,TLcBatchCall.DWH));
        List<TLcBatchCall> list = tLcBatchCallService.selectBatchCallHisList(tLcBatchCall);
        return getDataTable(list);
    }

    @RequiresPermissions("ruoyi:batchcall:view")
    @GetMapping("/all")
    public String batchcallAll(ModelMap modelMap) {
        //查询是否脱敏
        boolean desensitization = desensitizationUtil.isDesensitization(String.valueOf(ShiroUtils.getSysUser().getOrgId()), ShiroUtils.getLoginName());
        modelMap.put("desensitization", desensitization);
        return prefix + "/batchcallall";
    }

    @RequiresPermissions("ruoyi:batchcall:alllist")
    @PostMapping("/allList")
    @ResponseBody
    public TableDataInfo allList(TLcBatchCall tLcBatchCall) {
        startPage();
        tLcBatchCall.setOrgId(ShiroUtils.getSysUser().getOrgId() + "");
//        tLcBatchCall.setCreateBy(ShiroUtils.getLoginName()+"");
        //只查询状态为 暂停、外呼中、待外呼 的数据
//        tLcBatchCall.setTaskStatusList(Arrays.asList(TLcBatchCall.ZT,TLcBatchCall.WHZ,TLcBatchCall.DWH));
        List<TLcBatchCall> list = tLcBatchCallService.selectTLcBatchCallList(tLcBatchCall);
        return getDataTable(list);
    }

    /**
     * 导出批量外呼任务管理列表
     */
    /*@RequiresPermissions("ruoyi:batchcall:export")
    @Log(title = "批量外呼任务管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcBatchCall tLcBatchCall)
    {
        List<TLcBatchCall> list = tLcBatchCallService.selectTLcBatchCallList(tLcBatchCall);
        ExcelUtil<TLcBatchCall> util = new ExcelUtil<TLcBatchCall>(TLcBatchCall.class);
        return util.exportExcel(list, "batchcall");
    }*/

    /**
     * 新增批量外呼任务管理
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存批量外呼任务管理
     */
//    @RequiresPermissions("ruoyi:batchcall:add")
    @Log(title = "批量外呼任务管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcBatchCall tLcBatchCall, HttpServletRequest request) {

        String isCallOther = request.getParameter("isCallOther");//是否拨打其他人 0 只拨打本人；1 拨打本人+其他
        String exonNum = request.getParameter("exonNum").trim();//外显号码
        String caseNoStr = request.getParameter("caseNoStr");//案件编号
        String importBatchNoStr = request.getParameter("importBatchNoStr");//批次号
        String callNum = request.getParameter("callNum");//一共有多少电话
        String[] caseNoArray = {};
        String[] importBatchNoArray = {};
        String orgId = ShiroUtils.getSysUser().getOrgId() + "";
        if (StringUtils.isNotEmpty(caseNoStr)) {
            caseNoArray = caseNoStr.split(",");
            TLcBatchCallConfig tbcc = this.tLcBatchCallConfigService.selectTLcBatchCallConfigByOrgId(orgId);
            if (tbcc != null) {
                if (caseNoArray.length > tbcc.getBatchCallNum()) {
                    return error("该委托机构配置的自动外呼案件数最大为" + tbcc.getBatchCallNum() + ",你选择的案件数已经超过");
                }
            }
        } else {
            return error("案件编号参数错误，请联系管理员");
        }
        if (StringUtils.isNotEmpty(importBatchNoStr)) {
            importBatchNoArray = importBatchNoStr.split(",");
        } else {
            return error("批次号参数错误，请联系管理员");
        }
        TLcBatchCall tbc = new TLcBatchCall();
        tbc.setCreateBy(ShiroUtils.getLoginName());
        tbc.setOrgId(orgId);
        tbc.setTaskStatusList(Arrays.asList(TLcBatchCall.DWH, TLcBatchCall.WHZ, TLcBatchCall.ZT));
        List<TLcBatchCall> list = this.tLcBatchCallService.selectTLcBatchCallList(tbc);
        if (!list.isEmpty()) {
            return error("已经存在没有完成的自动外呼任务，请勿再发起自动外呼任务");
        }

        return toAjax(tLcBatchCallService.insertTLcBatchCallByTask(isCallOther, exonNum, caseNoArray, importBatchNoArray, orgId));
    }

    /**
     * 修改批量外呼任务管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap map) {
        TLcBatchCall tLcBatchCall = tLcBatchCallService.selectTLcBatchCallById(id);
        map.put("tLcBatchCall", tLcBatchCall);
        return prefix + "/edit";
    }

    /**
     * 修改保存批量外呼任务管理
     */
    @RequiresPermissions("ruoyi:batchcall:edit")
    @Log(title = "批量外呼任务管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcBatchCall tLcBatchCall) {

        /*TLcBatchCall tmp = this.tLcBatchCallService.selectTLcBatchCallById(tLcBatchCall.getId());
        tmp.setTaskStatus(tLcBatchCall.getTaskStatus());*/
        return toAjax(tLcBatchCallService.updateTLcBatchCall(tLcBatchCall));
    }

    /**
     * @param tLcBatchCall
     * @return
     */
    @PostMapping("/editBatchCall")
    @ResponseBody
    public AjaxResult editBatchCall(TLcBatchCall tLcBatchCall, String isCall, String onlyUpdate) {
        if ("1".equals(onlyUpdate)) {//只做状态更新
            return toAjax(tLcBatchCallService.updateTLcBatchCall(tLcBatchCall));
        }
        int flag = tLcBatchCallService.updateTLcBatchCall(tLcBatchCall);
        if (flag > 0) {//更新状态成功
            if ("1".equals(isCall)) {//是接通电话之后 ，然后挂断的电话
                String orgId = ShiroUtils.getSysUser().getOrgId() + "";
                TLcBatchCallConfig tbcc = this.tLcBatchCallConfigService.selectTLcBatchCallConfigByOrgId(orgId);
                if (tbcc != null) {
                    if ("0".equals(tbcc.getIsCallOther())) {//批量配置为：本人接通后是否继续拨打本案其他号码：0 不呼叫
                        TLcBatchCall tmp = this.tLcBatchCallService.selectTLcBatchCallById(tLcBatchCall.getId());
                        //需要查询出该案件下所有非本人的电话，然后把 呼叫状态 修改为 取消
                        TLcBatchCall tbc = new TLcBatchCall();
                        tbc.setCreateBy(ShiroUtils.getLoginName());
                        tbc.setOrgId(ShiroUtils.getSysUser().getOrgId() + "");
                        tbc.setCaseNo(tmp.getCaseNo());
                        tbc.setIsOnlyOne("1");//查询一条记录 是否有指
                        tbc.setTaskStatusList(Arrays.asList(TLcBatchCall.DWH, TLcBatchCall.WHZ));
                        List<TLcBatchCall> batchCallList = tLcBatchCallService.selectTLcBatchCall(tbc);
                        if (batchCallList != null && batchCallList.size() > 0) {
                            tbc.setTaskStatus(TLcBatchCall.YQX);
                            tbc.setRemark("配置为本人拨打成功后，不拨打其他，状态置为取消");
                            tLcBatchCallService.updateTLcBatchCallBatch(tbc);
                            logger.info("该案件下所有非本人的电话的通话状态修改已取消");
                        }
                    }
                }
            }

            TLcBatchCall tbc = new TLcBatchCall();
            tbc.setCreateBy(ShiroUtils.getLoginName());
            tbc.setOrgId(ShiroUtils.getSysUser().getOrgId() + "");
            tbc.setIsOnlyOne("1");//查询第一条待拨电话
            //只查询状态为 待外呼 的数据
            tbc.setTaskStatusList(Arrays.asList(TLcBatchCall.DWH, TLcBatchCall.WHZ));
            List<TLcBatchCall> batchCallList = tLcBatchCallService.selectTLcBatchCallList(tbc);
            String result = "";
            if (batchCallList != null && batchCallList.size() > 0) {//有要待外呼的数据
//                result = JSON.toJSONString(batchCallList.get(0));
                logger.info("下一条待拨数据为：{}", JSON.toJSONString(batchCallList.get(0)));
                return AjaxResult.success(batchCallList.get(0));
            } else {//没有待外呼的数据
                logger.info("没有下一条待拨数据了");
                return AjaxResult.success(result);
            }
        } else {
            return error("通话状态更新失败");
        }

    }

    /**
     * @param
     * @return
     */
    @PostMapping("/getNextBatchCall")
    @ResponseBody
    public AjaxResult getNextBatchCall() {

        TLcBatchCall tLcBatchCall = new TLcBatchCall();
        tLcBatchCall.setCreateBy(ShiroUtils.getLoginName());
        tLcBatchCall.setOrgId(ShiroUtils.getSysUser().getOrgId() + "");
        tLcBatchCall.setIsOnlyOne("1");//查询第一条待拨电话
        //只查询状态为 待外呼、外呼中 的数据
        tLcBatchCall.setTaskStatusList(Arrays.asList(TLcBatchCall.DWH, TLcBatchCall.WHZ));
        List<TLcBatchCall> batchCallList = tLcBatchCallService.selectTLcBatchCallList(tLcBatchCall);

        if (batchCallList != null && batchCallList.size() > 0) {
            return AjaxResult.success(batchCallList.get(0));
        }
        logger.info("getNextBatchCall:没有下一条待拨数据了");
        return AjaxResult.success("");
    }

    /**
     * 删除批量外呼任务管理
     */
    @RequiresPermissions("ruoyi:batchcall:remove")
    @Log(title = "批量外呼任务管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcBatchCallService.deleteTLcBatchCallByIds(ids));
    }

    /**
     * 修改保存批量外呼任务管理
     */
    @Log(title = "批量外呼任务管理", businessType = BusinessType.UPDATE)
    @PostMapping("/editBatchCallStatus")
    @ResponseBody
    public AjaxResult editBatchCallStatus(HttpServletRequest request) {
        String flag = request.getParameter("flag");
        String userId = ShiroUtils.getLoginName();
        if ("pause".equals(flag)) {
            return success(tLcBatchCallService.updateBatchCallByBatchNo(userId, 1, 2) + "");
//            return toAjax(tLcBatchCallService.updateBatchCallByBatchNo(userId,1,2));//待外呼状态 修改为 暂停状态
        } else if ("start".equals(flag)) {
            return success(tLcBatchCallService.updateBatchCallByBatchNo(userId, 2, 1) + "");//暂停状态 修改为 待外呼状态
        } else if ("cancle".equals(flag)) {
            return success(tLcBatchCallService.updateBatchCallByBatchNo(userId, null, 3) + "");//取消
        }
        return error();
    }

    /**
     * 跳转到作业自动催收页面
     *
     * @return
     */
    @GetMapping(value = "/toAutoCollJob")
    public String toCollJob(String importBatchNo, String caseNo, String orgId, String exonNum, ModelMap modelMap, String id) {
//        modelMap.put("tLcTask", tLcTask);
        modelMap.put("currentCaseNo", caseNo);
        modelMap.put("currentImportBatchNo", importBatchNo);
        modelMap.put("orgId", orgId);
        modelMap.put("exonNum", exonNum);//外显号码
        modelMap.put("ownerId", ShiroUtils.getSysUser().getUserId());

        ExtPhone extPhone = new ExtPhone();
        extPhone.setIsused("0");
        extPhone.setSeatId(Integer.valueOf(String.valueOf(ShiroUtils.getSysUser().getUserId())));
        extPhone.setCallPlatform(ShiroUtils.getSysUser().getPlatform());
        List<ExtPhone> list = extPhoneService.selectExtPhoneList(extPhone);
        if (list != null && list.size() > 0) {
            if (list != null && list.size() > 0) {
                // 分机号码
                modelMap.put("extPhone", list.get(0));
                // 查询外显号码
            }
        }
        TLcBatchCall tLcBatchCall = this.tLcBatchCallService.selectTLcBatchCallById(Long.parseLong(id));
        modelMap.put("batchCall", tLcBatchCall);
        modelMap.put("callPlatform", ShiroUtils.getSysUser().getPlatform());
        //查询是否脱敏
        boolean desensitization = desensitizationUtil.isDesensitization(String.valueOf(ShiroUtils.getSysUser().getOrgId()), ShiroUtils.getLoginName());
        modelMap.put("desensitization", desensitization);
        TLcTask tLcTask = this.tLcTaskService.selectTaskByCaseNo(caseNo, orgId, importBatchNo);
        modelMap.put("curActionCode", tLcTask.getActionCode());
        return prefix + "/autoCollJob";
    }

    /**
     * 获取案件当前行动码
     *
     * @param importBatchNo
     * @param caseNo
     * @param orgId
     * @return
     */
    @PostMapping("/getCurActionCode")
    @ResponseBody
    public AjaxResult getCurActionCode(String importBatchNo, String caseNo, String orgId) {
        TLcTask tLcTask = this.tLcTaskService.selectTaskByCaseNo(caseNo, orgId, importBatchNo);
        return AjaxResult.success("成功", tLcTask.getActionCode());
    }
}
