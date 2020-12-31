package com.ruoyi.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.agent.domain.ExtPhone;
import com.ruoyi.agent.mapper.ExtPhoneMapper;
import com.ruoyi.common.config.DuYanConfig;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.service.ITLcCustContactService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.task.domain.PreCallConfig;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.domain.TlcPreCallTask;
import com.ruoyi.task.domain.preTestCall.createTask.*;
import com.ruoyi.task.domain.preTestCall.singleCallRecord.SingleCallRecordResponse;
import com.ruoyi.task.domain.preTestCall.taskResult.PreTestCallResultEnum;
import com.ruoyi.task.domain.preTestCall.taskResult.ResultEntity;
import com.ruoyi.task.domain.preTestCall.taskResult.TaskResultResponseEntity;
import com.ruoyi.task.mapper.TlcPreCallTaskMapper;
import com.ruoyi.task.service.IPreTestCallService;
import com.ruoyi.task.service.ITLcTaskService;
import com.ruoyi.utils.DesensitizationUtil;
import com.ruoyi.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author guozeqi
 * @create 2020-12-15
 */
@Slf4j
@Service
public class PreTestCallServiceImpl implements IPreTestCallService {


    @Autowired
    private TlcPreCallTaskMapper tlcPreCallTaskMapper;
    @Autowired
    private ITLcCustContactService tLcCustContactService;
    @Autowired
    private ITLcTaskService tLcTaskService;
    @Autowired
    private ExtPhoneMapper extPhoneMapper;
    @Autowired
    private DuYanConfig duYanConfig;
    @Autowired
    private DesensitizationUtil desensitizationUtil;


    /**
     * 获取联系人
     * @param caseNoStr
     * @param importBatchNoStr
     * @param relation
     * @return
     */
    @Override
    public List<TLcCustContact> getCustContactList(String caseNoStr, String importBatchNoStr, String relation){
        List<TLcCustContact> list = new ArrayList<TLcCustContact>();
        if(StringUtils.isNotEmpty(caseNoStr)){
            String[] caseNoRows = caseNoStr.split(",");
            String[] importBatchRows = importBatchNoStr.split(",");
            TLcCustContact tcc = new TLcCustContact();
            tcc.setCaseNoList(Arrays.asList(caseNoRows));
            tcc.setImportBatchNoList(Arrays.asList(importBatchRows));
            tcc.setIsClose("0");//正常，未停播
            if(StringUtils.isNotEmpty(relation)){
                if("1".equals(relation)){//只有本人
                    tcc.setRelation(1);
                }
            }
            list = this.tLcCustContactService.selectTLcCustContactList(tcc);
        }
//        List<TLcCustContact> tLcCustContacts = custContactRuleUtil.custContactRule(list);
        return list;
    }


    /**
     * 创建计划
     * @param preCallConfig
     * @return
     */
    @Override
    @Transactional
    public Map<String,String> createPreTask(PreCallConfig preCallConfig) {
        Map<String, String> retMap = null;
        Date curDate = new Date();
        String orgId = String.valueOf(ShiroUtils.getSysUser().getOrgId());
        String loginName = ShiroUtils.getLoginName();
        Long userId = ShiroUtils.getSysUser().getUserId();
        Boolean is_distinct = "1".equals(preCallConfig.getIsFilter()) ? true : false;
        //判断用户是否已有映射关系
        ExtPhone param = new ExtPhone();
        param.setIsused("0");
        param.setSeatId(Integer.valueOf(String.valueOf(userId)));
        param.setCallPlatform(ShiroUtils.getSysUser().getPlatform());
        List<ExtPhone> extPhones = extPhoneMapper.selectExtPhoneList(param);
        if(extPhones.size() > 0){
            //查询联系人
            List<TLcCustContact> custContactList = this.getCustContactList(preCallConfig.getCaseNoStr(), preCallConfig.getImportBatchNoStr(), preCallConfig.getRelation());
            //构建参数对象
            List<TlcPreCallTask> taskList = new ArrayList<TlcPreCallTask>();
            List<CreateTaskRequestEntity.Content> contentList = new ArrayList<CreateTaskRequestEntity.Content>();
            if(custContactList.size() > 0){
                Integer batchNo = tlcPreCallTaskMapper.selectMaxBatchNo();
                for (TLcCustContact tLcCustContact : custContactList) {
                    TlcPreCallTask insertParam = new TlcPreCallTask();
                    insertParam.setCaseNo(tLcCustContact.getCaseNo());
                    insertParam.setImportBatchNo(tLcCustContact.getImportBatchNo());
                    insertParam.setOrgId(orgId);
                    insertParam.setBatchNo(batchNo+1);
                    insertParam.setPhone(tLcCustContact.getPhone());
                    insertParam.setContactName(tLcCustContact.getContactName());
                    insertParam.setContactRelation(tLcCustContact.getRelation());
                    insertParam.setExecStatus(2);//未执行
                    insertParam.setCreateBy(loginName);
                    insertParam.setCreateTime(curDate);
                    insertParam.setCaller(preCallConfig.getExoNum());
                    insertParam.setIsDistinct(preCallConfig.getIsFilter());
                    taskList.add(insertParam);
                    CreateTaskRequestEntity.Content content = new CreateTaskRequestEntity.Content();
                    content.setU_phone(tLcCustContact.getPhone());
                    content.setU_TAG(tLcCustContact.getCaseNo());
                    contentList.add(content);
                }
            }
            //推送接口
            if(!contentList.isEmpty()){
                CreateTaskRequestEntity requestEntity = new CreateTaskRequestEntity();
                requestEntity.setContent(contentList);
                requestEntity.setApikey(duYanConfig.getApikey());
                requestEntity.setName("个人预测式外呼计划"+loginName);
                requestEntity.setAccount_id(Long.valueOf(extPhones.get(0).getAgentid()));
                requestEntity.setCaller(preCallConfig.getExoNum());
                requestEntity.setIs_distinct(is_distinct);
                requestEntity.setPriority("HIGH");
                retMap = this.createTaskHandler(requestEntity);
            }
            if(retMap.size() == 1){
                throw new PreTestCallException(retMap.get("message"));
            }
            //循环获取计划结果(因计划成功结果异步返回)
            while("上传中".equals(retMap.get("status"))){
                Map<String, String> baseInfo = this.getPlanBaseInfo(retMap.get("planId"));
                if(!"UPLOADING".equals(baseInfo.get("planStatus"))){
                    break;
                }
            }
            //调用状态切换接口
            String chagMsg = this.changerStatus(PreTestCallStatusEnum.getCode("执行中"), retMap.get("planId"));
            if(!"success".equals(chagMsg)){
                throw new PreTestCallException("预测式外呼状态切换失败");
            }else{
                retMap.put("status","执行中");//最新状态
            }
            //新增记录
            if(!taskList.isEmpty()){
                this.batchAddPreCallTask(taskList,retMap.get("planId"));
            }
        }

        return retMap;
    }


    private Map<String,String> createTaskHandler(CreateTaskRequestEntity requestEntity){
        Map<String,String> resultMap = new HashMap<>();
        String url = duYanConfig.getCreateUrl()
                +"?apikey="+requestEntity.getApikey()
                +"&name="+requestEntity.getName()
                +"&account_id="+requestEntity.getAccount_id()
                +"&caller="+requestEntity.getCaller()
                +"&is_distinct="+requestEntity.getIs_distinct()
                +"&priority="+requestEntity.getPriority();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN,MediaType.ALL}));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> request = new HttpEntity<String>(
                JSON.toJSONString(requestEntity.getContent()).replaceAll("u","U"), headers);
        log.error("创建个人预测式计划url:{}",url);
        log.error("创建个人预测式计划content{}",JSON.toJSONString(requestEntity.getContent()).replaceAll("u","U"));

        ResponseEntity<CreateTaskResponseEntity> responseEntity = restTemplate.postForEntity(url, request, CreateTaskResponseEntity.class);
        CreateTaskResponseEntity body = responseEntity.getBody();
        log.error("创建个人预测式计划结果{}",JSON.toJSONString(body));
        if(body != null && (body.getStatus() == 1 || "1".equals(body.getStatus()))){
            CreateTaskResponseEntity.Data data = body.getData();
            Long id = data.getId();
            String status = data.getStatus();
            Long total_count = data.getTotal_count();
            Long created_time = data.getCreated_time();
            String caller = data.getCaller();
            Boolean is_distinct = requestEntity.getIs_distinct();
            resultMap.put("planId",String.valueOf(id));
            resultMap.put("status",ConvertUtil.convertStatus(status));
            resultMap.put("total_count",String.valueOf(total_count));
            resultMap.put("created_time", ConvertUtil.convertDate(created_time));
            resultMap.put("caller",requestEntity.getCaller());//页面传进来的
            resultMap.put("is_distinct",String.valueOf(is_distinct));
        }else{
            resultMap.put("message",body.getMessage());
        }
        return resultMap;
    }

    private void batchAddPreCallTask(List<TlcPreCallTask> taskList,String planId){
        for (TlcPreCallTask tlcPreCallTask : taskList) {
            tlcPreCallTask.setPlanId(planId);
        }
        tlcPreCallTaskMapper.batchAddPreCallTask(taskList);
    }

    /**
     * 状态切换
     * @param status
     * @param planId
     * @return
     */
    @Override
    public String changerStatus(String status,String planId){
        String url = duYanConfig.getStatusUrl();
        url = url.replaceAll("campaign_id",planId);
        url = url +"?apikey="+duYanConfig.getApikey()
                +"&campaign_status="+status;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN,MediaType.ALL}));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> request = new HttpEntity<String>(
                "", headers);
        log.error("切换个人预测式计划状态url:{}",url);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        String body = response.getBody();
        log.error("切换个人预测式计划状态结果{}",JSON.toJSONString(body));
        Integer stas = null;
        if(StringUtils.isNotEmpty(body)){
            JSONObject jsonObject = JSONObject.parseObject(body);
            stas = (Integer)jsonObject.get("status");
        }
        if(stas != null && stas == 1){
            return "success";
        }else{
            return "error";
        }
    }


    /**
     * 获取计划基本信息
     * @param planId
     * @return
     */
    @Override
    public Map<String,String> getPlanBaseInfo(String planId){
        Map<String,String> map = new HashMap<>();
        String url = duYanConfig.getSelectBaseUrl().replaceAll("campaign_id",planId);
        url = url + "?apikey="+duYanConfig.getApikey()
                + "&campaign_id="+planId;

        RestTemplate restTemplate = new RestTemplate();
        log.error("获取计划基本信息url:{}",url);
        String resultStr = restTemplate.getForObject(url, String.class);
        log.error("获取计划基本信息结果{}",JSON.toJSONString(resultStr));
        if(StringUtils.isNotEmpty(resultStr)){
            JSONObject jsonObject = JSONObject.parseObject(resultStr);
            Integer status = (Integer)jsonObject.get("status");
            if(status != null && status == 1){
                JSONObject data = (JSONObject)jsonObject.get("data");
                String lastStatus = (String)data.get("status");//最新状态
                Integer finished_count = (Integer)data.get("finished_count");
                Integer total_count = (Integer)data.get("total_count");
                Long created_time = (Long)data.get("created_time");
                map.put("planStatus",lastStatus);
                map.put("finishedCount",String.valueOf(finished_count));
                map.put("totalCount",String.valueOf(total_count));
                map.put("createdTime",ConvertUtil.convertDate(created_time));
            }else{
                throw new PreTestCallException("获取计划基本信息失败");
            }
        }else{
            throw new PreTestCallException("获取计划基本信息失败");
        }

        return map;
    }

    /**
     * 查询任务详细
     * @param tlcPreCallTask
     * @return
     */
    @Override
    public List<TlcPreCallTask> selectTlcPreCallTaskList(TlcPreCallTask tlcPreCallTask) {
        return tlcPreCallTaskMapper.selectTlcPreCallTaskList(tlcPreCallTask);
    }

    /**
     * 查询当前登录用户未执行完成的计划
     * @param loginName
     * @return
     */
    @Override
    public List<TlcPreCallTask> selectNotExecPlanByLoginName(String loginName) {
        return this.tlcPreCallTaskMapper.selectNotExecPlanByLoginName(loginName);
    }

    /**
     * 询当前登录用户未执行完成的计划
     * @param loginName
     * @return
     */
    @Override
    public TlcPreCallTask selectNotExecPlanByLoginName2(String loginName) {
        return this.tlcPreCallTaskMapper.selectNotExecPlanByLoginName2(loginName);
    }

    /**
     * 查询所有未执行的计划
     * @return
     */
    @Override
    public List<TlcPreCallTask> selectAllNotExecPlan() {
        return this.tlcPreCallTaskMapper.selectAllNotExecPlan();
    }

    /**
     * 更新未完成的为 已完成 未接通状态
     */
    @Override
    public void updateNotConnect() {
        this.tlcPreCallTaskMapper.updateNotConnect();
    }

    /**
     * 删除任务
     * @param planId
     * @param phone
     */
    @Override
    @Transactional
    public void removeTask(String planId, String phone, String accountId) {

        String apikey = duYanConfig.getApikey();
        String url = duYanConfig.getDeleteUrl();
        url = url +"?apikey="+apikey
                +"&agent_id="+accountId;
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("campaign_id",planId);
        paramMap.put("phone_number",phone);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN,MediaType.ALL}));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> request = new HttpEntity<String>(
                JSON.toJSONString(paramMap), headers);
        log.error("删除预测式计划url:{}",url);
        log.error("删除预测式计划content{}",JSON.toJSONString(paramMap));

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        String body = responseEntity.getBody();
        log.error("删除预测式计划结果{}",JSON.toJSONString(body));
        Integer stas = null;
        String message = "";
        String code = "";
        if(StringUtils.isNotEmpty(body)){
            JSONObject jsonObject = JSONObject.parseObject(body);
            stas = (Integer)jsonObject.get("status");
            message = (String) jsonObject.get("message");
            code = (String) jsonObject.get("code");
        }
        if(stas != 1 || !"OK".equals(code)){
            throw new PreTestCallException(body);
        }

        TlcPreCallTask updateParam = new TlcPreCallTask();
        updateParam.setPlanId(planId);
        updateParam.setPhone(phone);
        updateParam.setExecStatus(3);
        this.tlcPreCallTaskMapper.updateExecStatus(updateParam);


    }

    /**
     * 根据条件更新表里的状态
     * @param tlcPreCallTask
     */
    @Override
    public void updateExecStatus2(TlcPreCallTask tlcPreCallTask) {
        this.tlcPreCallTaskMapper.updateExecStatus2(tlcPreCallTask);
    }

    /**
     * 获取计划的执行结果
     * @param planId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public TaskResultResponseEntity getPlanResult(String planId, Integer pageNo, Integer pageSize){
        Map<String,String> map = new HashMap<>();
        String url = duYanConfig.getSelectResultUrl().replaceAll("campaign_id",planId);
        url = url + "?apikey="+duYanConfig.getApikey()
                + "&page_num=" + pageNo
                + "&page_size=" + pageSize;

        RestTemplate restTemplate = new RestTemplate();
        log.error("获取计划的执行结果url:{}",url);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String body = responseEntity.getBody();
        log.error("获取计划的执行结果返回值{}",body);

        TaskResultResponseEntity entity = resultHandler(body);
        return entity;
    }

    /**
     * 更具计划id和手机号 更新 呼叫结果 最后呼叫时间 执行状态
     * @param planId
     * @param list
     */
    @Override
    public void updateTlcPreCallTask2(String planId, List<Map<String,String>> list) {
        for (Map<String, String> map : list) {
            String phone = map.get("phone");
            String callResult = map.get("callResult");
            String lastCallTime = map.get("lastCallTime");
            String execStatus = map.get("execStatus");
            TlcPreCallTask updateParam = new TlcPreCallTask();
            updateParam.setPlanId(planId);
            updateParam.setPhone(phone);
            if(StringUtils.isNotEmpty(callResult) && StringUtils.isNotEmpty(lastCallTime) && "已完成".equals(execStatus)){
                updateParam.setCallResult(callResult);
                updateParam.setLastCallTime(lastCallTime);
                updateParam.setExecStatus(1);
                this.tlcPreCallTaskMapper.updateTlcPreCallTask2(updateParam);
            }
        }

    }

    /**
     * 跳转作业页面前处理
     * @param modelMap
     * @param planId
     * @param caseNo
     * @param phone
     * @param uuid
     * @return
     */
    @Override
    @Transactional
    public ModelMap toPreCollJobHandler(ModelMap modelMap, String planId, String caseNo, String phone, String uuid) {
        //查询拨打案件的详细信息
        TlcPreCallTask selectParam = new TlcPreCallTask();
        selectParam.setPlanId(planId);
        selectParam.setCaseNo(caseNo);
        selectParam.setPhone(phone);
        TlcPreCallTask preCallTask = this.tlcPreCallTaskMapper.selectTlcPreCallTaskList(selectParam).get(0);
        //更新状态
        TlcPreCallTask updateParam = new TlcPreCallTask();
        updateParam.setPlanId(planId);
        updateParam.setPhone(phone);
        updateParam.setCallResult("成功");
        updateParam.setLastCallTime(ConvertUtil.convertDate(System.currentTimeMillis()));
        updateParam.setExecStatus(1);//已完成
        this.tlcPreCallTaskMapper.updateTlcPreCallTask2(updateParam);
        //下催记
        TLcTask tLcTask = this.tLcTaskService.selectTaskByCaseNo(preCallTask.getCaseNo(), preCallTask.getOrgId(), preCallTask.getImportBatchNo());
        String actionCode = tLcTask.getActionCode();
        if("Fresh-新任务".equals(actionCode) || "Search2-联系方式无效".equals(actionCode)){
            actionCode = "Search1-联系方式有效";
        }
        TLcCallRecord callRecord = new TLcCallRecord();
        callRecord.setContactName(preCallTask.getContactName());
        callRecord.setPhone(phone);
        callRecord.setContactRelation(preCallTask.getContactRelation());
        callRecord.setCallSign("R02");
        callRecord.setCallResult("无法接通");
        callRecord.setCaseNo(caseNo);
        callRecord.setRemark("无法接通");
        callRecord.setOrgId(preCallTask.getOrgId());
        callRecord.setType(1);
        callRecord.setPlatform("DY");
        callRecord.setActionCode(actionCode);
        callRecord.setUcid(uuid);
        Response response = this.tLcTaskService.addCallRecord(callRecord, preCallTask.getImportBatchNo(), "", "");
        //脱敏
        boolean desensitization = desensitizationUtil.isDesensitization(preCallTask.getOrgId(), ShiroUtils.getLoginName());
        modelMap.put("desensitization",desensitization);
        TLcTask task = this.tLcTaskService.selectTaskByCaseNo(caseNo,preCallTask.getOrgId(),preCallTask.getImportBatchNo());
        modelMap.put("curActionCode", task.getActionCode());
        modelMap.put("currentCaseNo", caseNo);
        modelMap.put("currentImportBatchNo", preCallTask.getImportBatchNo());
        modelMap.put("userId", ShiroUtils.getSysUser().getUserId());
        modelMap.put("orgId", preCallTask.getOrgId());
        modelMap.put("phone", phone);
        modelMap.put("contactName", preCallTask.getContactName());
        modelMap.put("callRecordId", response.getResult());
        modelMap.put("uuid", uuid);
        return modelMap;
    }

    /**
     * 挂断电话后保存催记处理
     * @param orgId
     * @param importBatchNo
     * @param caseNo
     * @param phone
     * @param callRecordId
     * @param uuid
     */
    @Override
    @Transactional
    public void saveCallRecordHandler(String orgId, String importBatchNo, String caseNo, String phone, String callRecordId, String uuid) {
        //查询通话记录
        String url = duYanConfig.getSelectSingleRecordUrl();
        url = url + "?apikey="+duYanConfig.getApikey()
                +"&call_uuid="+uuid
                +"&include_vars="+false;
        RestTemplate restTemplate = new RestTemplate();
        log.error("获取单条通话记录url:{}",url);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String body = responseEntity.getBody();
        log.error("获取单条通话记录返回值{}",body);
        SingleCallRecordResponse response = JSONObject.parseObject(body,SingleCallRecordResponse.class);
        if(!"1".equals(response.getStatus()) && response.getStatus() != 1){
            throw new PreTestCallException("催记保存失败，获取单条通话记录异常"+response.getMessage());
        }
        if(response.getData() == null){
            throw new PreTestCallException("催记保存失败，获取单条通话记录数据为空");
        }
        //查询任务最新行动码
        TLcTask tLcTask = this.tLcTaskService.selectTaskByCaseNo(caseNo, orgId, importBatchNo);
        String actionCode = tLcTask.getActionCode();
        if("Fresh-新任务".equals(actionCode) || "Search2-联系方式无效".equals(actionCode)){
            actionCode = "Search1-联系方式有效";
        }
        //计算通话结束时间，录音地址等
        String startTime = ConvertUtil.convertDate(response.getData().getCall_time());
        String endTime = ConvertUtil.convertDate(response.getData().getCall_time()+response.getData().getDuration()*1000);
        Long callLen = response.getData().getDuration();
        String callRadioLocation = duYanConfig.getSoundRecordingUrl()+"?apikey="+duYanConfig.getApikey()+"&call_uuid="+uuid+"&/"+uuid+".wav";
        //保存催记
        TLcCallRecord callRecord = new TLcCallRecord();
        callRecord.setId(Long.valueOf(callRecordId));
        callRecord.setCallSign("R01");
        callRecord.setCallResult("已接听");
        callRecord.setCallLen(String.valueOf(callLen));
        callRecord.setCallRadioLocation(callRadioLocation);
        callRecord.setCaseNo(caseNo);
        callRecord.setRemark("已接听");
        callRecord.setOrgId(orgId);
        callRecord.setSendRadioCheck(1);
        callRecord.setType(1);
        callRecord.setPlatform("DY");
        callRecord.setActionCode(actionCode);
        callRecord.setIsHangUp(1);
        callRecord.setUcid(uuid);
        this.tLcTaskService.addCallRecord(callRecord,importBatchNo,startTime,endTime);
    }


    public static TaskResultResponseEntity resultHandler(String jsonStr){
        TaskResultResponseEntity result = new TaskResultResponseEntity();
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        result.setStatus(Long.valueOf((Integer)jsonObject.get("status")));
        result.setMessage((String)jsonObject.get("message"));
        result.setError_code(String.valueOf((Integer)jsonObject.get("error_code")));

        TaskResultResponseEntity.Data resultData = new TaskResultResponseEntity.Data();
        JSONObject data = (JSONObject)jsonObject.get("data");
        resultData.setTotal_pages(Long.valueOf((Integer)data.get("total_pages")));
        resultData.setTotal_elements(Long.valueOf((Integer)data.get("total_elements")));

        List<ResultEntity> resultEntities = new ArrayList<>();
        JSONArray campaigns = (JSONArray)data.get("campaigns");

        for (Object obj : campaigns) {
            ResultEntity entity = new ResultEntity();
            JSONObject campaign = (JSONObject)obj;

            entity.setPhone((String)campaign.get("phone"));
            entity.setCall_count(Long.valueOf((Integer)campaign.get("call_count")));
            entity.setCall_time((Long)campaign.get("call_time"));
            Integer duration = (Integer)campaign.get("duration");
            if(duration != null){
                entity.setDuration(Long.valueOf((Integer)campaign.get("duration")));
            }
            entity.setCaller((String)campaign.get("caller"));
            entity.setOutcome((String)campaign.get("outcome"));
            entity.setTag((String)campaign.get("tag"));
            entity.setCall_uuid((String)campaign.get("call_uuid"));
            entity.setIs_calling((Boolean)campaign.get("is_calling"));
            entity.setIs_agent_answered((Boolean)campaign.get("is_agent_answered"));

            ResultEntity.variables resultVariables = new ResultEntity.variables();
            String variables = (String)campaign.get("variables");
            JSONObject object = JSON.parseObject(variables);
            String u_tag = (String)object.get("U_TAG");
            resultVariables.setU_TAG(u_tag);

            entity.setVariables(resultVariables);
            resultEntities.add(entity);
        }

        resultData.setCampaigns(resultEntities);
        result.setData(resultData);

        return result;
    }
/*
    public static void main(String[] args) {
//        String aaa = "{\"status\":1,\"data\":{\"campaigns\":[{\"phone\":\"12345678901\",\"variables\":\"{\\\"U_TAG\\\":\\\"91112018a0284203_078\\\"}\",\"call_count\":0,\"is_calling\":false},{\"phone\":\"12345678901\",\"variables\":\"{\\\"U_TAG\\\":\\\"91112018a0284203_078\\\"}\",\"call_count\":0,\"is_calling\":false},{\"phone\":\"12345678901\",\"variables\":\"{\\\"U_TAG\\\":\\\"91112018a0284203_078\\\"}\",\"call_count\":0,\"is_calling\":false}],\"total_elements\":3,\"total_pages\":1}}";
//        String aaa = "{\"status\":1,\"data\":{\"campaigns\":[{\"phone\":\"12345678901\",\"variables\":\"{\\\"U_TAG\\\":\\\"91112018a0284203_078\\\"}\",\"call_count\":1,\"outcome\":\"SUCCESS\",\"call_time\":1608889006000,\"duration\":4,\"agent_org_id\":10174143,\"caller\":\"1051371996\",\"call_uuid\":\"d0436e44-5d56-4a6e-a7ad-d16c423eeb69\",\"is_calling\":false},{\"phone\":\"12345678901\",\"variables\":\"{\\\"U_TAG\\\":\\\"91112018a0284203_078\\\"}\",\"call_count\":1,\"outcome\":\"FAIL\",\"call_time\":1608889006000,\"duration\":0,\"agent_org_id\":10174143,\"caller\":\"1051371996\",\"call_uuid\":\"1c1b7827-c275-4765-bc95-ee137aa8a0dd\",\"is_calling\":false},{\"phone\":\"12345678901\",\"variables\":\"{\\\"U_TAG\\\":\\\"91112018a0284203_078\\\"}\",\"call_count\":1,\"outcome\":\"FAIL\",\"call_time\":1608889006000,\"duration\":0,\"agent_org_id\":10174143,\"caller\":\"1051371996\",\"call_uuid\":\"6b9a1bbc-6125-4b8c-84f2-c347353def45\",\"is_calling\":false}],\"total_elements\":3,\"total_pages\":1}}";
        String aaa = "{\"status\": 1,\"data\": {\"campaigns\": [],\"total_elements\": 0,\"total_pages\": 0}}";
        JSONObject jsonObject = JSON.parseObject(aaa);

        Integer status = (Integer)jsonObject.get("status");
        String message = (String)jsonObject.get("message");
        Integer error_code = (Integer) jsonObject.get("error_code");
        JSONObject data = (JSONObject)jsonObject.get("data");

        Integer total_pages = (Integer)data.get("total_pages");
        Integer total_elements = (Integer)data.get("total_elements");
        JSONArray campaigns = (JSONArray)data.get("campaigns");

        for (Object obj : campaigns) {
            JSONObject campaign = (JSONObject)obj;
            String phone = (String)campaign.get("phone");
            Integer call_count = (Integer)campaign.get("call_count");
            Long call_time = (Long)campaign.get("call_time");
            Integer duration = (Integer)campaign.get("duration");
            String caller = (String)campaign.get("caller");
            String outcome = (String)campaign.get("outcome");
            String tag = (String)campaign.get("tag");
            String call_uuid = (String)campaign.get("call_uuid");
            Boolean is_calling = (Boolean)campaign.get("is_calling");
            Boolean is_agent_answered = (Boolean)campaign.get("is_agent_answered");
            String variables = (String)campaign.get("variables");

            JSONObject object = JSON.parseObject(variables);
            String u_tag = (String)object.get("U_TAG");
        }
        System.out.println(campaigns);

    }*/

    public static void main(String[] args) {
        Long aaa = 1609222559000L;
        Long bbb = 32L;
        String s1 = ConvertUtil.convertDate(aaa);
        System.out.println(s1);
        String s = ConvertUtil.convertDate(aaa + bbb*1000);
        System.out.println(s);
    }


}
