package com.ruoyi.task.service;


import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.task.domain.PreCallConfig;
import com.ruoyi.task.domain.TlcPreCallTask;
import com.ruoyi.task.domain.preTestCall.taskResult.TaskResultResponseEntity;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

/**
 * @author guozeqi
 * @create 2020-12-15
 */
public interface IPreTestCallService {

    public List<TLcCustContact> getCustContactList(String caseNoStr, String importBatchNoStr, String relation);

    public Map<String,String> createPreTask(PreCallConfig preCallConfig);

    public String changerStatus(String status,String planId);

    public Map<String,String> getPlanBaseInfo(String planId);

    public List<TlcPreCallTask> selectTlcPreCallTaskList(TlcPreCallTask tlcPreCallTask);

    public List<TlcPreCallTask> selectNotExecPlanByLoginName(String loginName);

    public TlcPreCallTask selectNotExecPlanByLoginName2(String loginName);

    public List<TlcPreCallTask> selectAllNotExecPlan();

    /**
     * 更新未完成的为 已完成 未接通状态
     */
    public void updateNotConnect();

    public void removeTask(String planId, String phone, String accountId);

    public void updateExecStatus2(TlcPreCallTask tlcPreCallTask);

    public TaskResultResponseEntity getPlanResult(String planId, Integer pageNo, Integer pageSize);

    public void updateTlcPreCallTask2(String planId, List<Map<String,String>> list);

    public ModelMap toPreCollJobHandler(ModelMap modelMap, String planId, String caseNo, String phone, String uuid);

    public void saveCallRecordHandler (String orgId, String importBatchNo, String caseNo, String phone, String callRecordId, String uuid);


}
