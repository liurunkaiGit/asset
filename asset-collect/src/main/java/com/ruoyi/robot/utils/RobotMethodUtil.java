package com.ruoyi.robot.utils;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.callConfig.domain.TLcCallStrategyConfig;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.config.RobotAppConfig;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.domain.TLcCustinfo;
import com.ruoyi.custom.service.ITLcCustContactService;
import com.ruoyi.custom.service.ITLcCustinfoService;
import com.ruoyi.duncase.domain.TLcDuncase;
import com.ruoyi.duncase.service.AsyncITLcDuncaseService;
import com.ruoyi.duncase.service.ITLcDuncaseService;
import com.ruoyi.enums.AllocatTaskEnum;
import com.ruoyi.enums.ContactRelaEnum;
import com.ruoyi.enums.SexEnum;
import com.ruoyi.enums.TaskTypeEnum;
import com.ruoyi.orgSpeechConf.domain.TLcOrgSpeechcraftConf;
import com.ruoyi.robot.domain.*;
import com.ruoyi.robot.enums.CallLine;
import com.ruoyi.robot.enums.CallbackTypeEnum;
import com.ruoyi.robot.enums.LocalRobotTaskStatus;
import com.ruoyi.robot.enums.TaskType;
import com.ruoyi.robot.model.*;
import com.ruoyi.robot.service.CallbackService;
import com.ruoyi.robot.service.ITLcRobotTaskPandectService;
import com.ruoyi.robot.service.ITLcRobotTaskService;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.mapper.TLcTaskMapper;
import com.ruoyi.task.service.ITLcTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/12 15:45
 */
@Slf4j
@Component("com.ruoyi.robot.utils.RobotMethodUtil")
public class RobotMethodUtil {

    @Autowired
    private RobotAppConfig robotAppConfig;
    @Autowired
    private AiccHttpUtils aiccHttpUtils;
    @Autowired
    private ITLcRobotTaskService robotTaskService;
    @Autowired
    private CallbackService callbackService;
    @Autowired
    private ITLcTaskService taskService;
    @Autowired
    private ITLcRobotTaskService tLcRobotTaskService;
    @Autowired
    private ITLcRobotTaskPandectService robotTaskPandectService;
    @Autowired
    private TLcTaskMapper tLcTaskMapper;

    /**
     * 获取公司列表
     *
     * @return
     */
    public RobotCompany getCompanys() {
        RobotResponse getCompanysResponse = aiccHttpUtils.sendGet(robotAppConfig.getGetCompanys());
        if (getCompanysResponse.getCode() != HttpStatus.OK.value()) {
            log.error("调用获取公司列表接口错误，error is {}", getCompanysResponse.getResultMsg());
            throw new RuntimeException("调用获取公司列表接口错误");
        }
        log.info("getCompanysResponse is {}", getCompanysResponse);
        JSONArray robotCompanyJsonArray = (JSONArray) getCompanysResponse.getData();
        List<RobotCompany> robotCompanyList = JSONObject.parseArray(robotCompanyJsonArray.toJSONString(), RobotCompany.class);
        RobotCompany robotCompany = robotCompanyList.size() == 1 ? robotCompanyList.get(0) : robotCompanyList.get(1);
        log.info("绑定的公司{}", robotCompany);
        return robotCompany;
    }

    /**
     * 获取公司主叫电话列表
     *
     * @return
     */
    public List<RobotPhone> getPhones() {
        String getPhonesUrl = robotAppConfig.getGetPhones() + "?companyId=" + getCompanys().getCompanyId();
        RobotResponse getPhonesResponse = aiccHttpUtils.sendGet(getPhonesUrl);
        if (getPhonesResponse.getCode() != HttpStatus.OK.value()) {
            log.error("调用获取公司列表接口错误，error is {}", getPhonesResponse.getResultMsg());
            throw new RuntimeException("调用获取公司列表接口错误");
        }
        log.info("getPhonesResponse is {}", getPhonesResponse);
        JSONArray robotPhonesJsonArray = (JSONArray) getPhonesResponse.getData();
        List<RobotPhone> robotPhoneList = JSONObject.parseArray(robotPhonesJsonArray.toJSONString(), RobotPhone.class);
        log.info("公司主叫电话列表是{}", robotPhoneList);
        return robotPhoneList;
    }

    /**
     * 获取公司的机器人话术列表,可以获取到机器人的信息robotDefId
     *
     * @return
     */
    public List<Robot> getRobots() {
        String getRobotsUrl = robotAppConfig.getGetRobots() + "?companyId=" + getCompanys().getCompanyId();
        RobotResponse getRobotsResponse = aiccHttpUtils.sendGet(getRobotsUrl);
        if (getRobotsResponse.getCode() != HttpStatus.OK.value()) {
            log.error("调用获取公司的机器人话术列表接口错误，error is {}", getRobotsResponse.getResultMsg());
            throw new RuntimeException("调用获取公司的机器人话术列表接口错误");
        }
        log.info("getRobotsResponse is {}", getRobotsResponse);
        JSONArray getRobotsJsonArray = (JSONArray) getRobotsResponse.getData();
        List<Robot> robotList = JSONObject.parseArray(getRobotsJsonArray.toJSONString(), Robot.class);
        log.info("获取公司的机器人话术列表是{}", robotList);
        return robotList;
    }

    /**
     * 创建机器人任务
     *
     * @param task
     * @return
     */
    public Integer createTask(TLcTask task, TLcCallStrategyConfig tLcCallStrategyConfig, List<TLcCustContact> custContactList, Integer robotNum) {
        Integer companyId = getCompanys().getCompanyId();
        CreateTaskParamVO createTaskParamVO = createRobotTask(companyId, task, tLcCallStrategyConfig, robotNum);
        RobotResponse createTaskResponse = aiccHttpUtils.sendPost(robotAppConfig.getCreateTask(), createTaskParamVO);
        if (createTaskResponse.getCode() != HttpStatus.OK.value()) {
            log.error("调用创建任务接口错误，error is {}", createTaskResponse.getResultMsg());
            throw new BusinessException(String.format("调用创建任务接口错误:%s", createTaskResponse.getResultMsg()));
        }
        Integer robotTaskId = (Integer) createTaskResponse.getData();
        // 向任务中导入客户信息
        // 查询话术变量
        List<String> sceneVariables = getSceneVariables(tLcCallStrategyConfig.getSceneDefId(), companyId);
        importCustomerToTask(robotTaskId, custContactList, tLcCallStrategyConfig, task, companyId, sceneVariables);
        return robotTaskId;
    }

    /**
     * 启动任务
     *
     * @param taskId
     */
    public void startTask(Integer taskId) {
        // 启动任务
        TaskParamVO taskParamVO = new TaskParamVO(taskId);
        RobotResponse robotResponse = aiccHttpUtils.sendPost(robotAppConfig.getStart(), taskParamVO);
        if (robotResponse.getCode() != HttpStatus.OK.value()) {
            log.error("机器人任务启动失败，taskId is {}, error is {}", taskId, robotResponse.getResultMsg());
//            throw new BusinessException(taskId + "启动失败:" + robotResponse.getResultMsg());
        }
    }

    /**
     * 获取机器人任务列表
     *
     * @param robotTask
     * @return
     */
    public RobotTaskDto getRobotTaskList(RobotTask robotTask) {
        RobotResponse robotResponse = aiccHttpUtils.sendGet(getCreateTaskUrl(robotTask));
        if (robotResponse.getCode() != HttpStatus.OK.value()) {
            log.error("获取任务列表错误,error is {}", robotResponse.getResultMsg());
            throw new RuntimeException("获取任务列表错误");
        }
        log.info("获取任务列表的结果:{}", robotResponse.getData());
        JSONObject data = (JSONObject) robotResponse.getData();
        return JSONObject.toJavaObject(data, RobotTaskDto.class);
    }

    /**
     * 生成查询机器人任务列表的url
     *
     * @param robotTask
     * @return
     */
    private String getCreateTaskUrl(RobotTask robotTask) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        StringBuilder stringBuilder = new StringBuilder(robotAppConfig.getGetTasks());
        stringBuilder.append("?companyId=" + getCompanys().getCompanyId());
        if (pageDomain.getPageNum() != null) {
            stringBuilder.append("&pageNum=" + pageDomain.getPageNum());
        }
        if (pageDomain.getPageSize() != null) {
            stringBuilder.append("&pageSize=" + pageDomain.getPageSize());
        }
        if (robotTask.getStatus() != null) {
            stringBuilder.append("&status=" + robotTask.getStatus());
        }
        if (StringUtils.isNoneBlank(robotTask.getJobName())) {
            stringBuilder.append("&jobName=" + robotTask.getJobName());
        }
        return stringBuilder.toString();
    }

    /**
     * 构建创建任务实体
     *
     * @param companyId
     * @param task
     * @return
     */
    public CreateTaskParamVO createRobotTask(Integer companyId, TLcTask task, TLcCallStrategyConfig tLcCallStrategyConfig, Integer robotNum) {
        //创建任务信息实体
        CreateTaskParamVO createTaskParamVO = new CreateTaskParamVO();
        createTaskParamVO.setCompanyId(companyId); // 公司id
        createTaskParamVO.setTaskName(task.getCustomName() + task.getCaseNo()); // 任务名称:使用客户名称+案件编号拼接
        createTaskParamVO.setTaskType(TaskType.MANUAL.getCode()); //任务类型, 1-定时,2-手动
        List<Integer> userPhoneIds = new ArrayList<>();
        String[] callLineList = tLcCallStrategyConfig.getCallLineId().split(",");
        Arrays.stream(callLineList).forEach(callLine -> userPhoneIds.add(Integer.valueOf(callLine)));
        createTaskParamVO.setUserPhoneIds(userPhoneIds); // 主叫号码 Id,获取主叫电话列表接口可获取到
        createTaskParamVO.setCallType(CallLine.PHONE.getCode()); // 外呼方式， 对应主叫号码(线路)类型：2、1、0
        createTaskParamVO.setConcurrencyQuota(robotNum); // ai 坐席数,默认 1,一个坐席对应一个机器人
        createTaskParamVO.setStartDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, new Date())); // 任务开始日期（定时任务必填）
        createTaskParamVO.setWorkingStartTime(tLcCallStrategyConfig.getStartCallDate());// 可拨打开始时间
        createTaskParamVO.setWorkingEndTime(StringUtils.isNoneBlank(tLcCallStrategyConfig.getStopCallDate()) ? tLcCallStrategyConfig.getStopCallDate() : null); // 可拨打结束时间
        createTaskParamVO.setBreakStartTime(null);//暂时停止开始时间
        createTaskParamVO.setBreakEndTime(null);//暂时停止结束时间
        createTaskParamVO.setRobotDefId(tLcCallStrategyConfig.getSpeechcraftId()); // 机器人 id， 获取机器人话术列表接口可获取，也是话术id
        createTaskParamVO.setRemark(null); //备注
        createTaskParamVO.setRepeatCall(false); //是否开启重拨 默认 false 关闭
//            CreateTaskParamVO.RuleDetail ruleDetail = new CreateTaskParamVO.RuleDetail();
//            ruleDetail.setPhoneStatus(10);
//            ruleDetail.setInterval(5);
//            ruleDetail.setTimes(2);
//            createTaskParamVO.setRepeatCallRule(Arrays.asList(ruleDetail)); // 重拨详细规则
        return createTaskParamVO;
    }

    /**
     * 删除任务
     *
     * @param robotTaskId
     */
    public void deleteTask(Integer robotTaskId) {
        TaskParamVO taskParamVO = new TaskParamVO(robotTaskId);
        RobotResponse robotResponse = aiccHttpUtils.sendPost(robotAppConfig.getDelete(), taskParamVO);
        if (robotResponse.getCode() != HttpStatus.OK.value()) {
            log.error("任务删除错误,error is {},robotTaskId is {}", robotResponse.getResultMsg(), robotTaskId);
            throw new BusinessException(robotTaskId + "任务删除错误:" + robotResponse.getResultMsg());
        }
        log.info("任务删除成功，robotTaskId is {}", robotTaskId);
    }

    /**
     * 暂停任务
     *
     * @param robotTaskId
     */
    public void pauseTask(Integer robotTaskId) {
        TaskParamVO taskParamVO = new TaskParamVO(robotTaskId);
        RobotResponse robotResponse = aiccHttpUtils.sendPost(robotAppConfig.getPause(), taskParamVO);
        if (robotResponse.getCode() != HttpStatus.OK.value()) {
            log.error("暂停任务错误,error is {},robotTaskId is {}", robotResponse.getResultMsg(), robotTaskId);
//            throw new BusinessException(robotTaskId + "暂停失败:" + robotResponse.getResultMsg());
        } else {
            log.info("任务暂停成功，robotTaskId is {}", robotTaskId);
            // 修改机器人任务明细表和任务总览表状态
            TLcRobotTask tLcRobotTask = new TLcRobotTask();
            tLcRobotTask.setRobotTastId(Integer.valueOf(robotTaskId));
            tLcRobotTask.setRobotTaskStatus(LocalRobotTaskStatus.USER_PAUSE.getCode());
            this.tLcRobotTaskService.updateRobotTaskStatusByRobotTaskId(tLcRobotTask);
            TLcRobotTaskPandect robotTaskPandect = this.robotTaskPandectService.selectTLcRobotTaskPandectByRobotTaskId(robotTaskId);
            robotTaskPandect.setRobotTaskStatus(LocalRobotTaskStatus.USER_PAUSE.getCode());
            this.robotTaskPandectService.updateTLcRobotTaskPandect(robotTaskPandect);
        }
    }

    /**
     * 停止任务
     *
     * @param robotTaskId
     */
    public void stopTask(Integer robotTaskId) {
        TaskParamVO taskParamVO = new TaskParamVO(robotTaskId);
        RobotResponse robotResponse = aiccHttpUtils.sendPost(robotAppConfig.getStop(), taskParamVO);
        if (robotResponse.getCode() != HttpStatus.OK.value()) {
            log.error("停止任务错误,error is {},robotTaskId is {}", robotResponse.getResultMsg(), robotTaskId);
//            throw new BusinessException(robotTaskId + "停止失败:" + robotResponse.getResultMsg());
        } else {
            log.info("任务停止成功，robotTaskId is {}", robotTaskId);
            // 修改机器人任务明细表和任务总览表状态
            List<TLcRobotTask> tLcRobotTaskList = this.robotTaskService.selectListByRobotTaskId(robotTaskId);
            tLcRobotTaskList.stream().forEach(tLcRobotTask -> {
                tLcRobotTask.setRobotTaskStatus(LocalRobotTaskStatus.STOP.getCode());
                this.robotTaskService.updateTLcRobotTask(tLcRobotTask);
            });
            TLcRobotTaskPandect robotTaskPandect = this.robotTaskPandectService.selectTLcRobotTaskPandectByRobotTaskId(robotTaskId);
            robotTaskPandect.setRobotTaskStatus(LocalRobotTaskStatus.STOP.getCode());
            this.robotTaskPandectService.updateTLcRobotTaskPandect(robotTaskPandect);
        }
    }

    /**
     * 向任务中导入客户
     *
     * @param robotTaskId
     * @param custContactList
     */
    public void importCustomerToTask(Integer robotTaskId, List<TLcCustContact> custContactList, TLcCallStrategyConfig tLcCallStrategyConfig, TLcTask task, Integer companyId, List<String> sceneVariables) {
        ImportTaskCustomerVO importTaskCustomerVO = new ImportTaskCustomerVO();
        importTaskCustomerVO.setCompanyId(companyId);
        importTaskCustomerVO.setTaskId(robotTaskId);
        importTaskCustomerVO.setForceTransferCustomer(1);
        List<CustomerInfoExtVO> customerInfoList = new CopyOnWriteArrayList<>();
        //客户信息
        // 填充话术变量
        Map<String, String> map = getProperties(sceneVariables, task);
        CustomerInfoExtVO customerInfoExtVO1 = new CustomerInfoExtVO();
        customerInfoExtVO1.setName(custContactList.get(0).getContactName());
        customerInfoExtVO1.setPhone(custContactList.get(0).getPhone());
        customerInfoExtVO1.setProperties(map);
        customerInfoList.add(customerInfoExtVO1);
//        for (TLcCustContact tLcCustContact : custContactList) {
//            CustomerInfoExtVO customerInfoExtVO1 = new CustomerInfoExtVO();
//            customerInfoExtVO1.setName(tLcCustContact.getContactName());
//            customerInfoExtVO1.setPhone(tLcCustContact.getPhone());
//            customerInfoExtVO1.setProperties(map);
//            customerInfoList.add(customerInfoExtVO1);
//        }
        importTaskCustomerVO.setCustomerInfoList(customerInfoList);
        aiccHttpUtils.sendPost(robotAppConfig.getImportTaskCustomer(), importTaskCustomerVO);
        log.info("任务{}导入客户成功{}", robotTaskId, customerInfoList);
    }

    /**
     * 填充话术变量
     *
     * @param sceneVariables
     * @return
     */
    public Map<String, String> getProperties(List<String> sceneVariables, TLcTask task) {
        Map<String, String> map = new HashMap<>();
        sceneVariables.stream()
                .forEach(sceneVariable -> {
                    if (sceneVariable.equals("orgName")) {
                        map.put("orgName", task.getOrgName());
                    } else if (sceneVariable.equals("customName")) {
                        map.put("customName", task.getCustomName());
                    } else if (sceneVariable.equals("sex")) {
                        map.put("sex", SexEnum.getSexByCode(task.getCustomSex()));
                    } else if (sceneVariable.equals("cellPhone")) {
                        map.put("cellPhone", task.getPhone());
                    } else if (sceneVariable.equals("productName")) {
                        map.put("productName", task.getProductName());
                    } else if (sceneVariable.equals("overdueDate")) {
                        map.put("overdueDate", String.valueOf(task.getFirstOverdueTime()));
                    } else if (sceneVariable.equals("overdueDays")) {
                        map.put("overdueDays", String.valueOf(task.getOverdueDays()));
                    } else if (sceneVariable.equals("miniRepay")) {
                        map.put("miniRepay", String.valueOf(task.getLowestPaymentRmb()));
                    } else if (sceneVariable.equals("overAmount")) {
                        map.put("overAmount", String.valueOf(task.getCloseCaseYhje()));
                    }
                });
        return map;
    }

    /**
     * 填充话术变量
     *
     * @param sceneDefId
     * @return
     */
    public List<String> getSceneVariables(Integer sceneDefId, Integer companyId) {
        String getSceneVariablesUrl = robotAppConfig.getGetSceneVariables();
        StringBuilder stringBuilder = new StringBuilder(getSceneVariablesUrl);
        stringBuilder.append("?companyId=" + companyId)
                .append("&sceneDefId=" + sceneDefId);
        RobotResponse robotResponse = aiccHttpUtils.sendGet(stringBuilder.toString());
        if (robotResponse.getCode() != HttpStatus.OK.value()) {
            log.error("查询话术变量接口错误,error is {}", robotResponse.getResultMsg());
            throw new BusinessException("查询话术变量接口错误");
        }
        log.info("话术变量是{},话术id是{}", robotResponse.getData(), sceneDefId);
        List<String> data = (List<String>) robotResponse.getData();
        return data;
    }

    /**
     * 获取未开始的电话列表
     *
     * @param robotTaskId
     */
    public void queryNoStartPhones(Integer robotTaskId) {
        String url = robotAppConfig.getNotDialedCustomerList() + "?taskId=" + robotTaskId;
        RobotResponse robotResponse = aiccHttpUtils.sendGet(url);
        if (robotResponse.getCode() != HttpStatus.OK.value()) {
            log.error("停止任务错误,error is {}", robotResponse.getResultMsg());
            throw new BusinessException(robotTaskId + "停止失败:" + robotResponse.getResultMsg());
        }
        log.info("获取未开始的电话列表成功");
    }

    /**
     * 获取已经完成任务电话号码列表
     *
     * @param robotTaskId
     * @return
     */
    public DoneTaskPhones queryDoneTaskPhones(Integer robotTaskId, Integer pageNum, Integer pageSize) {
        DialedPhoneQueryVO dialedPhoneQueryVO = new DialedPhoneQueryVO();
        dialedPhoneQueryVO.setCallJobId(robotTaskId);
        dialedPhoneQueryVO.setPageNum(pageNum);
        dialedPhoneQueryVO.setPageSize(pageSize);
       /* dialedPhoneQueryVO.setDurationLeft(10);
        dialedPhoneQueryVO.setDurationRight(59);
        dialedPhoneQueryVO.setChatRoundLeft(0);
        dialedPhoneQueryVO.setChatRoundRight(9);*/
        //dialedPhoneQueryVO.setFinishStatus(0);
        //按分析结果作查询条件
        /*List<NameValueBO> resultQueryList = new ArrayList<>();
        NameValueBO nameValueBO = new NameValueBO();
        nameValueBO.setName("客户意向等级");
        nameValueBO.setValue("B级(一般)");*/
        //resultQueryList.add(nameValueBO);
//        dialedPhoneQueryVO.setResultQueryList(resultQueryList);
        RobotResponse robotResponse = aiccHttpUtils.sendPost(robotAppConfig.getQueryDoneTaskPhones(), dialedPhoneQueryVO);
        if (robotResponse.getCode() != HttpStatus.OK.value()) {
            log.error("获取已经完成任务电话号码列表错误,error is {}", robotResponse.getResultMsg());
            throw new BusinessException("获取已经完成任务电话号码列表错误");
        }
        JSONObject data = (JSONObject) robotResponse.getData();
        return JSONObject.toJavaObject(data, DoneTaskPhones.class);
    }

    /**
     * 获取任务详情
     *
     * @param robotTaskId
     * @return
     */
    public RobotTask getTaskDetail(Integer robotTaskId) {
        String url = robotAppConfig.getGetTaskDetail() + "?companyId=" + getCompanys().getCompanyId() + "&taskId=" + robotTaskId;
        RobotResponse robotResponse = aiccHttpUtils.sendGet(url);
        if (robotResponse.getCode() != HttpStatus.OK.value()) {
            log.error("获取任务详情错误,error is {}", robotResponse.getResultMsg());
            throw new BusinessException("获取任务详情:" + robotResponse.getResultMsg());
        }
        JSONObject data = (JSONObject) robotResponse.getData();
        return JSONObject.toJavaObject(data, RobotTask.class);
    }

    /**
     * 查询回调失败记录
     *
     * @param robotTaskIds
     */
    public void queryUnCallBack(String robotTaskIds) {
        // 当前页码
        Integer pageNum = 1;
        Integer companyId = getCompanys().getCompanyId();
        List<TLcRobotTask> robotTaskList = Arrays.stream(robotTaskIds.split(","))
                .map(robotTaskId -> this.robotTaskService.selectTLcRobotTaskByRobotTaskId(Integer.valueOf(robotTaskId)))
                .collect(Collectors.toList());
        Map<Integer, List<TLcRobotTask>> tLcRobotTaskMap = robotTaskList.stream().collect(Collectors.groupingBy(TLcRobotTask::getRobotTastId));
        for (Map.Entry<Integer, List<TLcRobotTask>> map : tLcRobotTaskMap.entrySet()) {
            // 按照创建时间升序排列
            robotTaskList = map.getValue().stream().sorted(Comparator.comparing(TLcRobotTask::getEndCreateTime)).collect(Collectors.toList());
            UnCallBackDto unCallBackDto = getUnCallBackDto(pageNum, companyId, map);
            String url;
            RobotResponse robotResponse;
            JSONObject data;
            // 获取一个通话的详情接口
            callCallback(unCallBackDto, robotTaskList);
            if (unCallBackDto.getPages() > 1) {
                for (Integer i = 2; i <= unCallBackDto.getPages(); i++) {
                    url = getQueryUnCallBackUrl(companyId, pageNum, robotTaskList);
                    robotResponse = aiccHttpUtils.sendGet(url);
                    if (robotResponse.getCode() != HttpStatus.OK.value()) {
                        log.error("查询回调失败记录接口错误,error is {},robotTaskId is {}", robotResponse.getResultMsg(), robotTaskList.get(0).getRobotTastId());
                        throw new BusinessException("查询回调失败记录接口:" + robotResponse.getResultMsg());
                    }
                    data = (JSONObject) robotResponse.getData();
                    unCallBackDto = JSONObject.toJavaObject(data, UnCallBackDto.class);
                    // 获取一个通话的详情接口
                    callCallback(unCallBackDto, robotTaskList);
                }
            }
        }
    }

    /**
     * 查询回调失败记录接口
     *
     * @param pageNum
     * @param companyId
     * @param map
     * @return
     */
    public UnCallBackDto getUnCallBackDto(Integer pageNum, Integer companyId, Map.Entry<Integer, List<TLcRobotTask>> map) {
        String url = getQueryUnCallBackUrl(companyId, pageNum, map.getValue());
        RobotResponse robotResponse = aiccHttpUtils.sendGet(url);
        if (robotResponse.getCode() != HttpStatus.OK.value()) {
            log.error("查询回调失败记录接口错误,error is {},robotTaskId is {}", robotResponse.getResultMsg(), map.getKey());
            throw new BusinessException("查询回调失败记录接口:" + robotResponse.getResultMsg());
        }
        JSONObject data = (JSONObject) robotResponse.getData();
        return JSONObject.toJavaObject(data, UnCallBackDto.class);
    }

    /**
     * 获取一个通话的详情接口
     *
     * @param unCallBackDto
     */
    private void callCallback(UnCallBackDto unCallBackDto, List<TLcRobotTask> robotTaskList) {
        List<CallbackCallDetail> callbackCallDetailList = robotTaskList.stream()
                .map(tLcRobotTask -> unCallBackDto.getList().stream()
                        .filter(callbackCallDetail -> Objects.equals(tLcRobotTask.getPhone(), callbackCallDetail.getCustomerTelephone()))
                        .findFirst()
                        .orElse(null))
                .collect(Collectors.toList());
        callbackCallDetailList.stream()
                .forEach(callbackCallDetail -> {
//                    Long callInstanceId = callbackCallDetail.getCallInstanceId();
                    if (callbackCallDetail != null) {
                        String url = robotAppConfig.getPhoneLogInfo() + "?callInstanceId=" + callbackCallDetail.getCallInstanceId();
                        RobotResponse robotResponse = aiccHttpUtils.sendGet(url);
                        if (robotResponse.getCode() != HttpStatus.OK.value()) {
                            log.error("获取一个通话的详情接口错误,error is {},通话记录id is {}", robotResponse.getResultMsg(), callbackCallDetail.getCallInstanceId());
                            throw new BusinessException("获取一个通话的详情接口错误:" + robotResponse.getResultMsg());
                        }
                        // 执行手工通话回调逻辑
                        handleCallCallback(robotResponse);
                    }
                });
    }

    /**
     * 获取一个通话的详情
     *
     * @param callInstanceId
     * @return
     */
    public RobotResponse getPhoneLogInfo(Integer callInstanceId) {
        String url = robotAppConfig.getPhoneLogInfo() + "?callInstanceId=" + callInstanceId;
        RobotResponse robotResponse = aiccHttpUtils.sendGet(url);
        if (robotResponse.getCode() != HttpStatus.OK.value()) {
            log.error("获取一个通话的详情接口错误,error is {},通话记录id is {}", robotResponse.getResultMsg(), callInstanceId);
            throw new BusinessException("获取一个通话的详情接口错误:" + robotResponse.getResultMsg());
        }
        return robotResponse;
    }

    /**
     * 执行手工通话回调逻辑
     *
     * @param robotResponse
     */
    public void handleCallCallback(RobotResponse robotResponse) {
        String jsonString = JSON.toJSONString(robotResponse);
        PhoneLogInfo phoneLogInfo = JSONObject.parseObject(jsonString, PhoneLogInfo.class);
        PhoneLogInfo.PhoneLog.TLcRobotCallRecordMeteData sceneInstance = phoneLogInfo.getData().getSceneInstance();
        List<PhoneLogInfo.PhoneLog.TaskResultBean> taskResultList = phoneLogInfo.getData().getTaskResult();
        PhoneLogInfo.PhoneLog.PhoneLogBean phoneLog = phoneLogInfo.getData().getPhoneLog();
        List<PhoneLogInfo.PhoneLog.PhoneLogBean.PhoneLogsBean> phoneLogList = phoneLog.getPhoneLogs();
        CallCallback callCallback = new CallCallback();
        CallCallback.CallCallbackData callCallbackData = callCallback.new CallCallbackData();
        callCallbackData.setDataType("INBOUND_CALL_INSTANCE_RESULT");
        CallCallback.CallCallbackData.CallData callData = callCallbackData.new CallData();
        CallCallback.CallCallbackData.CallData.PhoneLog callbackPhoneLog = callData.new PhoneLog();
        // 创建通话元数据
        TLcRobotCallRecordMeteData tLcRobotCallRecordMeteData = buildTLcRobotCallRecordMeteData(sceneInstance);
        callData.setSceneInstance(tLcRobotCallRecordMeteData);
        // 创建通话分析结果
        List<TLcRobotCallAnalyseResult> analyseResultList = buildTLcRobotCallAnalyseResult(taskResultList, sceneInstance);
        callData.setTaskResult(analyseResultList);
        // 创建通话详情
        List<TLcRobotCallDetail> callDetailList = buildTLcRobotCallDetail(phoneLogList, sceneInstance);
        callbackPhoneLog.setLuyinOssUrl(sceneInstance.getLuyinOssUrl());
        callbackPhoneLog.setPhoneLogs(callDetailList);
        callCallbackData.setData(callData);
        callCallback.setData(callCallbackData);
        callData.setPhoneLog(callbackPhoneLog);
        this.callbackService.callCallback(callCallback);
    }

    /**
     * 创建通话详情
     *
     * @param phoneLogList
     * @param sceneInstance
     * @return
     */
    private List<TLcRobotCallDetail> buildTLcRobotCallDetail(List<PhoneLogInfo.PhoneLog.PhoneLogBean.PhoneLogsBean> phoneLogList, PhoneLogInfo.PhoneLog.TLcRobotCallRecordMeteData sceneInstance) {
        List<TLcRobotCallDetail> callDetailList = phoneLogList.stream()
                .map(phoneLogsBean -> {
                    TLcRobotCallDetail tLcRobotCallDetail = new TLcRobotCallDetail();
                    tLcRobotCallDetail.setSceneInstanceId(phoneLogsBean.getSceneInstanceId())
                            .setSceneInstanceLogId(phoneLogsBean.getSceneInstanceLogId())
                            .setInboundInstanceId(sceneInstance.getCallInstanceId())
                            .setCompanyId(sceneInstance.getCompanyId())
                            .setRobotDefId(sceneInstance.getRobotDefId())
                            .setSpeaker(phoneLogsBean.getSpeaker())
                            .setContent(phoneLogsBean.getContent())
                            .setUserMean(String.valueOf(phoneLogsBean.getUserMean()))
                            .setUserMeanDetail(phoneLogsBean.getUserMeanDetail())
                            .setAiUnknownValue(phoneLogsBean.getAiUnknown())
//                            .setStartTime(StringUtils.isNoneBlank(phoneLogsBean.getStartTime()) ? DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, phoneLogsBean.getStartTime()) : null)
//                            .setEndTime(StringUtils.isNoneBlank(phoneLogsBean.getEndTime()) ? DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, phoneLogsBean.getEndTime()) : null)
                            .setLuyinOssUrl(sceneInstance.getLuyinOssUrl());
                    return tLcRobotCallDetail;
                }).collect(Collectors.toList());
        return callDetailList;
    }

    /**
     * 创建通话分析结果对象
     *
     * @param taskResultList
     * @param sceneInstance
     * @return
     */
    private List<TLcRobotCallAnalyseResult> buildTLcRobotCallAnalyseResult(List<PhoneLogInfo.PhoneLog.TaskResultBean> taskResultList, PhoneLogInfo.PhoneLog.TLcRobotCallRecordMeteData sceneInstance) {
        List<TLcRobotCallAnalyseResult> analyseResultList = taskResultList.stream().map(taskResultBean -> {
            TLcRobotCallAnalyseResult callAnalyseResult = new TLcRobotCallAnalyseResult();
            callAnalyseResult.setSceneInstanceResultId(taskResultBean.getSceneInstanceResultId())
                    .setCompanyId(taskResultBean.getCompanyId())
                    .setCallJobId(sceneInstance.getCallJobId())
                    .setInboundInstanceId(sceneInstance.getCallInstanceId())
                    .setSceneInstanceId(taskResultBean.getSceneInstanceId())
                    .setResultName(taskResultBean.getResultName())
                    .setResultValue(taskResultBean.getResultValue())
                    .setResultDesc(String.valueOf(taskResultBean.getResultDesc()))
                    .setResultValueAlias(taskResultBean.getResultValue())
                    .setDataType("INBOUND_CALL_INSTANCE_RESULT");
            return callAnalyseResult;
        }).collect(Collectors.toList());
        return analyseResultList;
    }

    /**
     * 创建通话元数据对象
     *
     * @param sceneInstance
     * @return
     */
    private TLcRobotCallRecordMeteData buildTLcRobotCallRecordMeteData(PhoneLogInfo.PhoneLog.TLcRobotCallRecordMeteData sceneInstance) {
        TLcRobotCallRecordMeteData tLcRobotCallRecordMeteData = new TLcRobotCallRecordMeteData();
        tLcRobotCallRecordMeteData.setDataType("INBOUND_CALL_INSTANCE_RESULT")
                .setInboundInstanceId(sceneInstance.getCallInstanceId())
                .setCompanyId(sceneInstance.getCompanyId())
                .setCallJobId(sceneInstance.getCallJobId())
                .setCustomerId(sceneInstance.getCustomerId())
                .setCustomerTelephone(sceneInstance.getCustomerTelephone())
                .setCustomerName(sceneInstance.getCustomerName())
                .setStatus(sceneInstance.getStatus())
                .setFinishStatus(sceneInstance.getFinishStatus())
                .setDuration(sceneInstance.getDuration())
                .setChatRound(sceneInstance.getChatRound())
                .setStartTime(StringUtils.isNoneBlank(sceneInstance.getStartTime()) ? DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, sceneInstance.getStartTime()) : null)
                .setEndTime(StringUtils.isNoneBlank(sceneInstance.getEndTime()) ? DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, sceneInstance.getEndTime()) : null)
                .setCallerPhone(sceneInstance.getCallerPhone())
                .setLuyinOssUrl(sceneInstance.getLuyinOssUrl())
                .setUserLuyinOssUrl(sceneInstance.getUserLuyinOssUrl())
                .setProperties(sceneInstance.getProperties())
                .setReadStatus(sceneInstance.getReadStatus())
                .setRobotDefId(sceneInstance.getRobotDefId())
                .setSceneRecordId(sceneInstance.getSceneRecordId())
                .setHangUp(sceneInstance.getHangUp());
        return tLcRobotCallRecordMeteData;
    }

    /**
     * 拼接查询回调失败记录url地址
     *
     * @param pageNum
     * @param robotTaskList
     * @return
     */
    private String getQueryUnCallBackUrl(Integer companyId, Integer pageNum, List<TLcRobotTask> robotTaskList) {
//        String startDate = DateUtils.dateFormat(DateUtils.YYYY_MM_DD, robotTaskList.get(0).getCreateTime());
//        String endDate = DateUtils.dateFormat(DateUtils.YYYY_MM_DD, new Date());
        String startDate = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, robotTaskList.get(0).getCreateTime());
        String endDate = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
        String url = robotAppConfig.getQueryUnCallBack();
        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append("?companyId=" + companyId)
                .append("&callJobId=" + robotTaskList.get(0).getRobotTastId())
                .append("&dataType=" + CallbackTypeEnum.OUT_CALL.getCode())
                .append("&startDate=" + startDate)
                .append("&endDate=" + endDate)
                .append("&pageNum=" + pageNum)
                .append("&pageSize=500");
        return stringBuilder.toString();
    }

    /**
     * 创建机器人任务，一个任务中包含多个号码
     *
     * @param taskList
     * @param tLcCallStrategyConfig
     */
    public Integer createTask(List<TLcTask> taskList, TLcCallStrategyConfig tLcCallStrategyConfig, Integer continueCallDays, Integer continueCallFrequency, TLcOrgSpeechcraftConf orgSpeechcraftConf) {
        Integer companyId = orgSpeechcraftConf.getCompanyId();
        CreateTaskParamVO createTaskParamVO = createRobotTask(companyId, tLcCallStrategyConfig, orgSpeechcraftConf.getConcurrentValue());
        RobotResponse createTaskResponse = aiccHttpUtils.sendPost(robotAppConfig.getCreateTask(), createTaskParamVO);
        if (createTaskResponse.getCode() != HttpStatus.OK.value()) {
            log.error("调用创建任务接口错误，error is {}", createTaskResponse.getResultMsg());
            throw new BusinessException(String.format("调用创建任务接口错误:%s", createTaskResponse.getResultMsg()));
        }
        Integer robotTaskId = (Integer) createTaskResponse.getData();
        // 获取话术变量
        List<String> sceneVariables = getSceneVariables(tLcCallStrategyConfig, orgSpeechcraftConf);
        // 封装客户信息
        ImportTaskCustomerVO importTaskCustomerVO = getImportTaskCustomerVO(companyId, robotTaskId);
        List<CustomerInfoExtVO> customerInfoList = new CopyOnWriteArrayList<>();
        List<TLcRobotTask> tLcRobotTaskList = taskList.stream().map(task -> {
            // 设置任务类型、机器人任务id、呼叫策略id、分配类型
            task.setTaskType(TaskTypeEnum.HELP_COLLECT_ROBOT.getCode())
                    .setRobotTaskId(robotTaskId)
                    .setRobotCallStrategyId(tLcCallStrategyConfig.getId())
                    .setAllotType(AllocatTaskEnum.ROBOT.getAllocatCode());
            // 填充话术信息并生成客户集合
            Map<String, String> map = getProperties(sceneVariables, task);
            CustomerInfoExtVO customerInfoExtVO1 = new CustomerInfoExtVO();
            customerInfoExtVO1.setName(task.getCustomName());
            customerInfoExtVO1.setPhone(task.getPhone());
            customerInfoExtVO1.setProperties(map);
            customerInfoList.add(customerInfoExtVO1);
            // 创建机器人任务明细数据
            TLcRobotTask tLcRobotTask = this.taskService.createRobotTask(task, robotTaskId, tLcCallStrategyConfig.getSpeechcraftId(), tLcCallStrategyConfig.getSpeechcraftName(), "BR", continueCallDays, continueCallFrequency);
            return tLcRobotTask;
        }).collect(Collectors.toList());
        // 向任务中导入客户信息
        importTaskCustomerVO.setCustomerInfoList(customerInfoList);
        aiccHttpUtils.sendPost(robotAppConfig.getImportTaskCustomer(), importTaskCustomerVO);
        log.info("任务{}导入客户成功{}", robotTaskId, customerInfoList);
        // 批量添加机器人任务明细
        this.tLcRobotTaskService.batchAddRobotTask(tLcRobotTaskList);
        // 将该任务添加到机器人任务总览表
        TLcRobotTaskPandect tLcRobotTaskPandect = createRobotTaskPandect(taskList, tLcCallStrategyConfig, robotTaskId, "BR");
        this.robotTaskPandectService.insertTLcRobotTaskPandect(tLcRobotTaskPandect);
        // 修改任务表
        this.tLcTaskMapper.batchUpdateTask(taskList);
        // 启动机器人任务
        startTask(robotTaskId);
        log.info("任务启动成功，robotTaskId is {}", robotTaskId);
        return robotTaskId;
    }

    /**
     * 封装客户信息
     * @param companyId
     * @param robotTaskId
     * @return
     */
    private ImportTaskCustomerVO getImportTaskCustomerVO(Integer companyId, Integer robotTaskId) {
        ImportTaskCustomerVO importTaskCustomerVO = new ImportTaskCustomerVO();
        importTaskCustomerVO.setCompanyId(companyId);
        importTaskCustomerVO.setTaskId(robotTaskId);
        importTaskCustomerVO.setForceTransferCustomer(1);
        return importTaskCustomerVO;
    }

    /**
     * 获取话术变量
     *
     * @param tLcCallStrategyConfig
     * @param orgSpeechcraftConf
     * @return
     */
    private List<String> getSceneVariables(TLcCallStrategyConfig tLcCallStrategyConfig, TLcOrgSpeechcraftConf orgSpeechcraftConf) {
        String[] scendDefIds = orgSpeechcraftConf.getSceneDefId().split(",");
        List<String> sceneVariables = new ArrayList<>();
        for (int i = 0; i < scendDefIds.length; i++) {
            if (Integer.valueOf(tLcCallStrategyConfig.getSceneDefId()).equals(Integer.valueOf(scendDefIds[i]))) {
                String speechcraftVariable = orgSpeechcraftConf.getSpeechcraftVariable().split("\\|")[i];
                sceneVariables = JSONUtil.toList(JSONUtil.parseArray(speechcraftVariable), String.class);
                break;
            }
        }
        return sceneVariables;
    }

    private void importCustomerToTaskBatch(Integer robotTaskId, List<TLcTask> taskList, Integer companyId, List<String> sceneVariables) {
        ImportTaskCustomerVO importTaskCustomerVO = getImportTaskCustomerVO(companyId, robotTaskId);
        List<CustomerInfoExtVO> customerInfoList = new CopyOnWriteArrayList<>();
        //客户信息
        // 填充话术变量
        taskList.stream().forEach(task -> {
            Map<String, String> map = getProperties(sceneVariables, task);
            CustomerInfoExtVO customerInfoExtVO1 = new CustomerInfoExtVO();
            customerInfoExtVO1.setName(task.getCustomName());
            customerInfoExtVO1.setPhone(task.getPhone());
            customerInfoExtVO1.setProperties(map);
            customerInfoList.add(customerInfoExtVO1);
        });
        importTaskCustomerVO.setCustomerInfoList(customerInfoList);
        aiccHttpUtils.sendPost(robotAppConfig.getImportTaskCustomer(), importTaskCustomerVO);
        log.info("任务{}导入客户成功{}", robotTaskId, customerInfoList);
    }

    /**
     * 创建机器人任务总览对象
     *
     * @param taskList
     * @param tLcCallStrategyConfig
     * @param robotTaskId
     * @return
     */
    private TLcRobotTaskPandect createRobotTaskPandect(List<TLcTask> taskList, TLcCallStrategyConfig tLcCallStrategyConfig, Integer robotTaskId, String robot) {
        TLcRobotTaskPandect tLcRobotTaskPandect = new TLcRobotTaskPandect();
        tLcRobotTaskPandect.setRobotTaskId(robotTaskId);
        tLcRobotTaskPandect.setTaskName(DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS, new Date()));
        tLcRobotTaskPandect.setOrgId(Long.valueOf(taskList.get(0).getOrgId()));
        tLcRobotTaskPandect.setOrgName(taskList.get(0).getOrgName());
        tLcRobotTaskPandect.setSpeechCraftNameId(tLcCallStrategyConfig.getSpeechcraftId());
        tLcRobotTaskPandect.setSpeechCraftName(tLcCallStrategyConfig.getSpeechcraftName());
        tLcRobotTaskPandect.setCallTotalCount(taskList.size());
        tLcRobotTaskPandect.setRobot(robot);
        tLcRobotTaskPandect.setRobotTaskStatus(LocalRobotTaskStatus.RUNNING.getCode());
//        tLcRobotTaskPandect.setTaskStartTime(new Date());
        return tLcRobotTaskPandect;
    }

    /**
     * 构建创建任务实体
     *
     * @param companyId
     * @return
     */
    public CreateTaskParamVO createRobotTask(Integer companyId, TLcCallStrategyConfig tLcCallStrategyConfig, Integer robotNum) {
        //创建任务信息实体
        CreateTaskParamVO createTaskParamVO = new CreateTaskParamVO();
        createTaskParamVO.setCompanyId(companyId); // 公司id
        createTaskParamVO.setTaskName(DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS, new Date())); // 任务名称:使用客户名称+案件编号拼接
        createTaskParamVO.setTaskType(TaskType.MANUAL.getCode()); //任务类型, 1-定时,2-手动
        List<Integer> userPhoneIds = new ArrayList<>();
        String[] callLineList = tLcCallStrategyConfig.getCallLineId().split(",");
        Arrays.stream(callLineList).forEach(callLine -> userPhoneIds.add(Integer.valueOf(callLine)));
        createTaskParamVO.setUserPhoneIds(userPhoneIds); // 主叫号码 Id,获取主叫电话列表接口可获取到
        createTaskParamVO.setCallType(CallLine.PHONE.getCode()); // 外呼方式， 对应主叫号码(线路)类型：2、1、0
        createTaskParamVO.setConcurrencyQuota(robotNum); // ai 坐席数,默认 1,一个坐席对应一个机器人
        createTaskParamVO.setStartDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, new Date())); // 任务开始日期（定时任务必填）
        createTaskParamVO.setWorkingStartTime(tLcCallStrategyConfig.getStartCallDate());// 可拨打开始时间
        createTaskParamVO.setWorkingEndTime(StringUtils.isNoneBlank(tLcCallStrategyConfig.getStopCallDate()) ? tLcCallStrategyConfig.getStopCallDate() : null); // 可拨打结束时间
        createTaskParamVO.setBreakStartTime(null);//暂时停止开始时间
        createTaskParamVO.setBreakEndTime(null);//暂时停止结束时间
        createTaskParamVO.setRobotDefId(tLcCallStrategyConfig.getSpeechcraftId()); // 机器人 id， 获取机器人话术列表接口可获取，也是话术id
        createTaskParamVO.setRemark(null); //备注
        createTaskParamVO.setRepeatCall(false); //是否开启重拨 默认 false 关闭
//            CreateTaskParamVO.RuleDetail ruleDetail = new CreateTaskParamVO.RuleDetail();
//            ruleDetail.setPhoneStatus(10);
//            ruleDetail.setInterval(5);
//            ruleDetail.setTimes(2);
//            createTaskParamVO.setRepeatCallRule(Arrays.asList(ruleDetail)); // 重拨详细规则
        return createTaskParamVO;
    }

    public void batchStartTask(String robotTaskIds) {
        Arrays.stream(robotTaskIds.split(","))
                .forEach(robotTaskId -> {
                    TaskParamVO taskParamVO = new TaskParamVO(Integer.valueOf(robotTaskId));
                    RobotResponse robotResponse = aiccHttpUtils.sendPost(robotAppConfig.getStart(), taskParamVO);
                    if (robotResponse.getCode() != HttpStatus.OK.value()) {
                        log.error("机器人任务启动失败，taskId is {}, error is {}", Integer.valueOf(robotTaskId), robotResponse.getResultMsg());
                    }else {
                        // 修改机器人任务明细表和任务总览表状态为外呼中
                        TLcRobotTask tLcRobotTask = new TLcRobotTask();
                        tLcRobotTask.setRobotTastId(Integer.valueOf(robotTaskId));
                        tLcRobotTask.setRobotTaskStatus(LocalRobotTaskStatus.RUNNING.getCode());
                        this.tLcRobotTaskService.updateRobotTaskStatusByRobotTaskId(tLcRobotTask);
                        TLcRobotTaskPandect robotTaskPandect = new TLcRobotTaskPandect();
                        robotTaskPandect.setRobotTaskStatus(LocalRobotTaskStatus.RUNNING.getCode());
                        robotTaskPandect.setRobotTaskId(Integer.valueOf(robotTaskId));
                        this.robotTaskPandectService.updateRobotTaskPandectStatusByRobotTaskId(robotTaskPandect);
                    }
                });
    }
}
