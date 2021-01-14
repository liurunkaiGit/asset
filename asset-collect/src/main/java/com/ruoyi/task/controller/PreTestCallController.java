package com.ruoyi.task.controller;

import com.ruoyi.agent.domain.ExtPhone;
import com.ruoyi.agent.service.IExtPhoneService;
import com.ruoyi.common.config.DuYanConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.task.domain.PreCallConfig;
import com.ruoyi.task.domain.TlcPreCallTask;
import com.ruoyi.task.domain.preTestCall.createTask.ConvertUtil;
import com.ruoyi.task.domain.preTestCall.createTask.PreTestCallStatusEnum;
import com.ruoyi.task.domain.preTestCall.taskResult.PreTestCallResultEnum;
import com.ruoyi.task.domain.preTestCall.taskResult.ResultEntity;
import com.ruoyi.task.domain.preTestCall.taskResult.TaskResultResponseEntity;
import com.ruoyi.task.service.IPreTestCallService;
import com.ruoyi.utils.DuyanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author guozeqi
 * @create 2020-12-15
 */
@Slf4j
@Controller
@RequestMapping("/collect/pretest")
public class PreTestCallController extends BaseController {
    private String prefix = "task";

    @Autowired
    private IPreTestCallService preTestCallService;
    @Autowired
    private IExtPhoneService extPhoneService;
    @Autowired
    private DuYanConfig duYanConfig;


    @PostMapping("/getCustContactCount")
    @ResponseBody
    public AjaxResult getCustContactCount(String caseNoStr, String importBatchNoStr, String relation) {
        return AjaxResult.success("success",preTestCallService.getCustContactList(caseNoStr,importBatchNoStr,relation).size());
    }


    @PostMapping("/createPreTask")
    @ResponseBody
    public AjaxResult createPreTask(PreCallConfig preCallConfig) {
        Map map = new LinkedHashMap();
        try {
            //创建前先查询当前用户是不是有未完成的计划
            List<TlcPreCallTask> preCallTasks = this.preTestCallService.selectNotExecPlanByLoginName(ShiroUtils.getLoginName());
            if(!preCallTasks.isEmpty()){
                map.put("code","500");
                map.put("message","当前用户有未执行完成的计划");
                return AjaxResult.success("error",map);
            }
            Map<String, String> preTask = preTestCallService.createPreTask(preCallConfig);
            if(preTask != null && !preTask.isEmpty()){
                map.put("code","200");
                map.put("preTask",preTask);
            }else{
                map.put("code","500");
                map.put("message","创建个人预测式外呼失败");
            }
        } catch (Exception e) {
            log.error("创建个人预测式外呼失败{}",e);
            map.put("code","500");
            map.put("message",e.getMessage());
            return AjaxResult.success("error",map);
        }
        return AjaxResult.success("success",map);
    }

    @GetMapping(value = "/workList")
    public String workList(ModelMap modelMap,String planId,String planStatus
                          ,String totalCount,String createdTime,
                           String caller,String isDistinct)
    {
        try {
            //获取基本信息
            Map<String, String> planBaseInfo = preTestCallService.getPlanBaseInfo(planId);
            modelMap.put("planId",planId);
            modelMap.put("planStatus", ConvertUtil.convertStatus(planBaseInfo.get("planStatus")));
            modelMap.put("totalCount",totalCount);
            modelMap.put("createdTime",createdTime);
            modelMap.put("caller",caller);
            modelMap.put("isDistinct",isDistinct);
            modelMap.put("finishedCount",planBaseInfo.get("finishedCount"));

            //登录token
            ExtPhone extPhone = new ExtPhone();
            extPhone.setIsused("0");
            extPhone.setSeatId(Integer.valueOf(String.valueOf(ShiroUtils.getSysUser().getUserId())));
            extPhone.setCallPlatform(ShiroUtils.getSysUser().getPlatform());
            List<ExtPhone> list = extPhoneService.selectExtPhoneList(extPhone);
            if (list != null && list.size() > 0) {
                // 分机号码
                String accountId = list.get(0).getAgentid();
                modelMap.put("dytoken", DuyanUtil.getToken(duYanConfig.getTokenUrl(),accountId,duYanConfig.getApikey()));
                modelMap.put("accountId",accountId);
            }
        } catch (Exception e) {
            log.error("跳转预测式任务列表页面异常{}",e);
        }
        return prefix + "/workList";
    }

    /**
     * 计划菜单
     * @return
     */
    @GetMapping(value = "/workListForMenu")
    public String workListForMenu(ModelMap modelMap)
    {
        try {
            //查询当前未执行完成的计划
            TlcPreCallTask preCallTask = this.preTestCallService.selectNotExecPlanByLoginName2(ShiroUtils.getLoginName());
            //获取基本信息
            if(preCallTask != null){
                Map<String, String> planBaseInfo = preTestCallService.getPlanBaseInfo(preCallTask.getPlanId());
                modelMap.put("planId",preCallTask.getPlanId());
                modelMap.put("planStatus", ConvertUtil.convertStatus(planBaseInfo.get("planStatus")));
                modelMap.put("totalCount",planBaseInfo.get("totalCount"));
                modelMap.put("createdTime",planBaseInfo.get("createdTime"));
                modelMap.put("caller",preCallTask.getCaller());
                modelMap.put("isDistinct","1".equals(preCallTask.getIsDistinct()) ? "true" : "false");
                modelMap.put("finishedCount",planBaseInfo.get("finishedCount"));
            }
            //登录token
            ExtPhone extPhone = new ExtPhone();
            extPhone.setIsused("0");
            extPhone.setSeatId(Integer.valueOf(String.valueOf(ShiroUtils.getSysUser().getUserId())));
            extPhone.setCallPlatform(ShiroUtils.getSysUser().getPlatform());
            List<ExtPhone> list = extPhoneService.selectExtPhoneList(extPhone);
            if (list != null && list.size() > 0) {
                // 分机号码
                String accountId = list.get(0).getAgentid();
                modelMap.put("dytoken", DuyanUtil.getToken(duYanConfig.getTokenUrl(),accountId,duYanConfig.getApikey()));
                modelMap.put("accountId",accountId);
            }
        } catch (Exception e) {
            log.error("跳转预测式任务列表页面异常{}",e);
        }
        return prefix + "/workList";
    }


    /**
     * 获取计划详细列表
     * @return
     */
    @PostMapping("/getTaskDetailList")
    @ResponseBody
    public TableDataInfo getTaskDetailList(TlcPreCallTask tlcPreCallTask) {
        startPage();
        tlcPreCallTask.setCreateBy(ShiroUtils.getLoginName());
        List<TlcPreCallTask> list = this.preTestCallService.selectTlcPreCallTaskList(tlcPreCallTask);
        return getDataTable(list);
    }

    /**
     * 删除任务
     * @return
     */
    @PostMapping("/removeTask")
    @ResponseBody
    public AjaxResult removeTask(String planId,String phone,String accountId){
        try {
            if(StringUtils.isNotEmpty(phone)){
                Map<String, String> planBaseInfo = preTestCallService.getPlanBaseInfo(planId);
                String planStatus = ConvertUtil.convertStatus(planBaseInfo.get("planStatus"));
                if("已完成".equals(planStatus) || "执行中".equals(planStatus)){
                    return AjaxResult.success("error","计划已完成或执行中，不允许停止！");
                }
                this.preTestCallService.removeTask(planId,phone,accountId);
            }else{
                List<TlcPreCallTask> taskList = this.preTestCallService.selectNotExecPlanByLoginName(ShiroUtils.getLoginName());
                for (TlcPreCallTask tlcPreCallTask : taskList) {
                    Map<String, String> planBaseInfo = preTestCallService.getPlanBaseInfo(tlcPreCallTask.getPlanId());
                    String planStatus = ConvertUtil.convertStatus(planBaseInfo.get("planStatus"));
                    if("已完成".equals(planStatus) || "执行中".equals(planStatus)){
                        return AjaxResult.success("error","计划已完成或执行中，不允许停止！");
                    }
                    this.preTestCallService.removeTask(tlcPreCallTask.getPlanId(),tlcPreCallTask.getPhone(),accountId);
                }
            }
        } catch (Exception e) {
            log.error("删除预测式任务失败{}",e);
            return AjaxResult.success("error",e.getMessage());
        }
        return AjaxResult.success("success");
    }

    /**
     *  状态切换
     * @param planId
     * @param status
     * @return
     */
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(String planId,String status){
        try {
            String flag = this.preTestCallService.changerStatus(PreTestCallStatusEnum.getCode(status), planId);
            if("success".equals(flag)){
                TlcPreCallTask updateParam = new TlcPreCallTask();
                updateParam.setPlanId(planId);
                if("已暂停".equals(status)){
                    updateParam.setExecStatus(0);
                    updateParam.setStatusCondition(2);
                }else {
                    updateParam.setExecStatus(2);
                    updateParam.setStatusCondition(0);
                }
                this.preTestCallService.updateExecStatus2(updateParam);
                return AjaxResult.success("success","操作成功");
            }else{
                return AjaxResult.success("success","操作失败");
            }
        } catch (Exception e) {
            log.error("状态切换失败{}",e);
            return AjaxResult.success("error",e.getMessage());
        }
    }

    /**
     * 定时获取计划的基本信息
     * @param planId
     * @return
     */
    @PostMapping("/getBaseInfo")
    @ResponseBody
    public AjaxResult getBaseInfo(String planId){
        try {
            if(planId==null || "".equals(planId)){
                return AjaxResult.success("success",null);
            }
            Map<String,Object> result = new HashMap<>();
            //获取基本信息
            Map<String, String> planBaseInfo = this.preTestCallService.getPlanBaseInfo(planId);
            String planStatus = ConvertUtil.convertStatus(planBaseInfo.get("planStatus"));
            String totalCount = planBaseInfo.get("totalCount");
            planBaseInfo.put("planStatus",planStatus);
            planBaseInfo.put("totalCount",totalCount);

            //获取计划的执行结果
            List<Map<String,String>> listDetail = new ArrayList<>();
            TaskResultResponseEntity planResult = this.preTestCallService.getPlanResult(planId, 1, 100);
            if(planResult != null && planResult.getStatus() == 1){
                TaskResultResponseEntity.Data data = planResult.getData();
                if(data != null){
                    List<ResultEntity> campaigns = data.getCampaigns();
                    if(campaigns != null && campaigns.size() > 0){
                        for (ResultEntity campaign : campaigns) {
                            Map<String,String> map = new HashMap<>();
                            String phone = campaign.getPhone();
                            String caseNo = campaign.getVariables().getU_TAG();
                            String outcome = campaign.getOutcome();
                            Long call_time = campaign.getCall_time();
                            Boolean is_calling = campaign.getIs_calling();
                            map.put("phone",phone);
                            map.put("caseNo",caseNo);
                            map.put("isCalling",String.valueOf(is_calling));
                            if(StringUtils.isNotEmpty(outcome)){
                                map.put("callResult", PreTestCallResultEnum.getName(outcome));
                            }
                            if(call_time != null){
                                map.put("lastCallTime",ConvertUtil.convertDate(call_time));
                                map.put("execStatus","已完成");
                            }
                            listDetail.add(map);
                        }
                    }
                }
            }
            //更新数据库状态
            if(!listDetail.isEmpty()){
                this.preTestCallService.updateTlcPreCallTask2(planId,listDetail);
            }
            result.put("planBaseInfo",planBaseInfo);
//            result.put("listData",listDetail);
            return AjaxResult.success("success",result);
        } catch (Exception e) {
            log.error("获取计划基本信息失败{}",e);
            return AjaxResult.success("error",e.getMessage());
        }
    }


    /**
     * 作业页面跳转
     * @param modelMap
     * @param planId
     * @param caseNo
     * @param phone
     * @param uuid
     * @return
     */
    @GetMapping("/toPreCollJob")
    public String toPreCollJob(ModelMap modelMap, String planId, String caseNo, String phone, String uuid){
        modelMap = this.preTestCallService.toPreCollJobHandler(modelMap, planId, caseNo, phone, uuid);
        return prefix + "/preCollJob";
    }


    /**
     * 挂断电话后保存催记处理
     * @param orgId
     * @param importBatchNo
     * @param caseNo
     * @param phone
     * @param callRecordId
     * @param uuid
     * @return
     */
    @PostMapping("/saveCallRecord")
    @ResponseBody
    public AjaxResult saveCallRecord(String orgId, String importBatchNo, String caseNo,
                                     String phone, String callRecordId, String uuid)
    {
        try {
            this.preTestCallService.saveCallRecordHandler(orgId,importBatchNo,caseNo,phone,callRecordId,uuid);
            return AjaxResult.success("success","催记保存成功");
        } catch (Exception e) {
            log.error("催记保存失败{}",e);
            return AjaxResult.error("error",e.getMessage());
        }
    }


    /**
     * 查询计划详细页面
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/selectPreTestDetail")
    public String selectPreTestDetail(ModelMap modelMap)
    {
        return prefix + "/preDetailList";
    }

    /**
     * 查询计划详细列表
     * @param tlcPreCallTask
     * @return
     */
    @PostMapping("/selectPreTestDetailList")
    @ResponseBody
    public TableDataInfo selectPreTestDetailList(TlcPreCallTask tlcPreCallTask) {
        startPage();
        tlcPreCallTask.setCreateBy(ShiroUtils.getLoginName());
        List<TlcPreCallTask> list = this.preTestCallService.selectTlcPreCallTaskList(tlcPreCallTask);
        return getDataTable(list);
    }


}
