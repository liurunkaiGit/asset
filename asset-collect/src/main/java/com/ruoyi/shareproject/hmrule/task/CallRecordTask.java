package com.ruoyi.shareproject.hmrule.task;

import com.ruoyi.task.mapper.TLcCallRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author guozeqi
 * @create 2020-12-03
 */
@Slf4j
@Component("CallRecordTask")
public class CallRecordTask {

    @Autowired
    private TLcCallRecordMapper tLcCallRecordMapper;

    public void createCallRecordView(){
        log.error("================执行创建视图定时任务开始=========================");
        tLcCallRecordMapper.createView();
        log.error("================执行创建视图定时任务结束=========================");
    }
}
