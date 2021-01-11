package com.ruoyi.task.taskTimer;

import com.ruoyi.task.domain.TlcPreCallTask;
import com.ruoyi.task.domain.preTestCall.createTask.ConvertUtil;
import com.ruoyi.task.domain.preTestCall.taskResult.PreTestCallResultEnum;
import com.ruoyi.task.domain.preTestCall.taskResult.ResultEntity;
import com.ruoyi.task.domain.preTestCall.taskResult.TaskResultResponseEntity;
import com.ruoyi.task.mapper.TlcPreCallTaskMapper;
import com.ruoyi.task.service.IPreTestCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author guozeqi
 * @create 2020-12-30
 */
@Slf4j
@Component("PreTestTaskTimer")
public class PreTestTaskTimer {
    @Autowired
    private IPreTestCallService preTestCallService;
    @Autowired
    private TlcPreCallTaskMapper tlcPreCallTaskMapper;


    /**
     * 定时更新（关闭页面后，已经执行完）遗漏的状态
     */
    public void updateCallResultStatus(){
        //查询所有未执行计划的Id
        List<TlcPreCallTask> preCallTaskList = preTestCallService.selectAllNotExecPlan();
        for (TlcPreCallTask tlcPreCallTask : preCallTaskList) {
            String planId = tlcPreCallTask.getPlanId();
            //获取计划的执行结果
            TaskResultResponseEntity planResult = this.preTestCallService.getPlanResult(planId, 1, 100);
            if(planResult.getStatus() == 1){
                TaskResultResponseEntity.Data data = planResult.getData();
                if(data != null){
                    List<ResultEntity> campaigns = data.getCampaigns();
                    if(!campaigns.isEmpty()){
                        for (ResultEntity resultEntity : campaigns) {
                            String call_time = resultEntity.getCall_time() != null ? ConvertUtil.convertDate(resultEntity.getCall_time()) : null;
                            String callResult = resultEntity.getOutcome();
                            String phone = resultEntity.getPhone();
                            TlcPreCallTask updateParam = new TlcPreCallTask();
                            updateParam.setPlanId(planId);
                            updateParam.setPhone(phone);
                            updateParam.setCallResult(PreTestCallResultEnum.getName(callResult));
                            updateParam.setLastCallTime(call_time);
                            updateParam.setExecStatus(1);
                            //根据手机号与计划id更新状态
                            this.tlcPreCallTaskMapper.updateTlcPreCallTask2(updateParam);
                        }
                    }
                }
            }
        }
    }

}
