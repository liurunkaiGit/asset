package com.ruoyi;

import com.ruoyi.callConfig.domain.TLcCallStrategyConfig;
import com.ruoyi.robot.domain.*;
import com.ruoyi.robot.utils.RobotMethodUtil;
import com.ruoyi.robot.utils.RobotResponse;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.service.ITLcTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/3/27 15:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RobotTest {

    @Autowired
    private RobotMethodUtil robotMethodUtil;
    @Autowired
    private ITLcTaskService taskService;

    @Test
    public void getCompanysTest() {
        RobotCompany companys = this.robotMethodUtil.getCompanys();
        System.out.println(companys);
//        RobotPhone phones = this.robotMethodUtil.getPhones();
//        System.out.println(phones);
//        this.robotMethodUtil.queryNoStartPhones(199);
//        this.robotMethodUtil.queryDoneTaskPhonesTest(199);
//        this.robotMethodUtil.getRobots();
//        RobotTask robotTask = new RobotTask();
//        RobotTaskDto robotTaskList = this.robotMethodUtil.getRobotTaskList(robotTask);
        RobotTask taskDetail = this.robotMethodUtil.getTaskDetail(966);
        System.out.println(taskDetail);
//        RobotPhone phones = this.robotMethodUtil.getPhones();
//        TLcCallStrategyConfig tLcCallStrategyConfig = new TLcCallStrategyConfig();
//        tLcCallStrategyConfig.setSpeechcraftId(129);
//        List<String> sceneVariables = this.robotMethodUtil.getSceneVariables(tLcCallStrategyConfig);
//        TLcTask tLcTask = this.taskService.selectTLcTaskById(2132L);
//        this.robotMethodUtil.getProperties(sceneVariables,tLcTask);
//        this.robotMethodUtil.getUnCallBackDto(1,companys.getCompanyId(),302);
//        RobotResponse phoneLogInfo = this.robotMethodUtil.getPhoneLogInfo(29909);
//        System.out.println("==================");
    }
}
