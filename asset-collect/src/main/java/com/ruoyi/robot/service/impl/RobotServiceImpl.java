package com.ruoyi.robot.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.enums.AllocatTaskEnum;
import com.ruoyi.enums.TaskTypeEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.robot.domain.*;
import com.ruoyi.robot.enums.LocalRobotTaskStatus;
import com.ruoyi.robot.enums.TaskStatus;
import com.ruoyi.robot.mapper.TLcRobotTaskMapper;
import com.ruoyi.robot.service.ITLcRobotCallRecordMeteDataService;
import com.ruoyi.robot.service.ITLcRobotTaskPandectService;
import com.ruoyi.robot.service.RobotService;
import com.ruoyi.robot.utils.RobotMethodUtil;
import com.ruoyi.robot.utils.RobotResponse;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.mapper.TLcTaskMapper;
import com.ruoyi.task.service.ITLcTaskService;
import com.ruoyi.utils.DownLoadFromHttpsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/10 14:53
 */
@Slf4j
@Service("com.ruoyi.robot.service.impl.RobotServiceImpl")
public class RobotServiceImpl implements RobotService {

    @Autowired
    private RobotMethodUtil robotMethodUtil;
    @Autowired
    private ITLcTaskService tLcTaskService;
    @Autowired
    private ITLcRobotTaskPandectService robotTaskPandectService;
    @Autowired
    private ITLcRobotCallRecordMeteDataService callRecordMeteDataService;
    @Autowired
    private TLcRobotTaskMapper tLcRobotTaskMapper;
    @Autowired
    private TLcTaskMapper tLcTaskMapper;

    @Override
    public RobotTaskDto getRobotTaskList(RobotTask robotTask) {
        RobotTaskDto robotTaskDto = robotMethodUtil.getRobotTaskList(robotTask);
        return robotTaskDto;
    }

    @Override
    public void pullback(String robotTaskIds, Integer robotTaskStatus) {
        for (String robotTaskId : robotTaskIds.split(",")) {
            // 调用百融机器人获取当前任务状态，只有、停止或者已完成的可以拉回 todo 是否需要做重复拉回的限制
            // 未开始(为了防止拉回之后，机器人开始了，所以拉回状态只包含暂停、停止或者已完成)，如果未开始的想要拉回，可以先暂停
            RobotTask taskDetail = this.robotMethodUtil.getTaskDetail(Integer.valueOf(robotTaskId));
            if (TaskStatus.FINISHED.getCode().equals(taskDetail.getStatus()) || TaskStatus.STOP.getCode().equals(taskDetail.getStatus()) || TaskStatus.USER_PAUSE.getCode().equals(taskDetail.getStatus())) {
                if (TaskStatus.USER_PAUSE.getCode().equals(taskDetail.getStatus())) {
                    // 如果任务状态是暂停，在拉回的时候需要先停止
                    this.robotMethodUtil.stopTask(Integer.valueOf(robotTaskId));
                }
                // 修改机器人任务总览表数据状态为拉回
                TLcRobotTaskPandect robotTaskPandect = this.robotTaskPandectService.selectTLcRobotTaskPandectByRobotTaskId(Integer.valueOf(robotTaskId));
                robotTaskPandect.setRobotTaskStatus(robotTaskStatus);
                robotTaskPandect.setCallDoneCount(taskDetail.getDoneCount());
                robotTaskPandect.setCallCalledCount(taskDetail.getCalledCount());
                robotTaskPandect.setCallMissCount(taskDetail.getMissCount());
                robotTaskPandect.setCallBusyCount(taskDetail.getBusyCount());
                robotTaskPandect.setCallFromUnavailableCount(taskDetail.getFromUnavailableCount());
                robotTaskPandect.setCallUnavailableCount(taskDetail.getUnavailableCount());
                robotTaskPandect.setCallRejectedCount(taskDetail.getRejectedCount());
                robotTaskPandect.setCallBlankCount(taskDetail.getBlankCount());
                robotTaskPandect.setCallClosedCount(taskDetail.getClosedCount());
                robotTaskPandect.setCallDownCount(taskDetail.getDownCount());
                robotTaskPandect.setCallBlackCount(taskDetail.getBlacklistCount());
                robotTaskPandect.setCallFailCount(taskDetail.getFromUnavailableCount());
                robotTaskPandect.setCallLossCount(taskDetail.getLostCount());
                robotTaskPandect.setCallOverdueCount(taskDetail.getOverdueCount());
                this.robotTaskPandectService.updateTLcRobotTaskPandect(robotTaskPandect);
                // 根据机器人任务id查询机器人任务明细
                List<TLcRobotTask> robotTaskList = this.tLcRobotTaskMapper.selectListByRobotTaskId(Integer.valueOf(robotTaskId));
                this.pullback(robotTaskStatus, Integer.valueOf(robotTaskId));
            }
        }
    }

    @Override
    public void pullback(Integer robotTaskStatus, Integer robotTaskId) {
        // 修改机器人任务明细
        TLcRobotTask tLcRobotTask = new TLcRobotTask();
        tLcRobotTask.setRobotTastId(robotTaskId).setRobotTaskStatus(robotTaskStatus);
        this.tLcRobotTaskMapper.updateTLcRobotTaskByRobotTaskId(tLcRobotTask);
        log.info("拉回操作时修改机器人任务明细表数据成功");
        // 修改任务类型和分配类型
//            TLcTask tLcTask = new TLcTask();
//            tLcTask.setRobotTaskId(robotTaskId).setTaskType(TaskTypeEnum.PULL_BACK_ROBOT.getCode()).setAllotType(AllocatTaskEnum.MANUAL.getAllocatCode());
//            this.tLcTaskMapper.updateTLcTaskByRobotTaskId(tLcTask);
        // 任务状态回调时，修改任务类型、分配类型、最近跟进时间、最近电话码及电话码中文
        this.tLcTaskMapper.updateTaskFromRobotTask(robotTaskId);
        log.info("拉回成功，修改任务表数据成功");
        // 插入案件历史轨迹
//        List<TLcTask> taskList = this.tLcTaskMapper.selectTaskListByRobotTaskId(robotTaskId);
//        if (taskList != null && taskList.size() > 0) {
//            SysUser sysUser = ShiroUtils.getSysUser();
//            if (sysUser == null) {
//                sysUser = new SysUser();
//                sysUser.setUserId(-1L);
//                sysUser.setUserName("机器人");
//            }
//            this.tLcTaskService.insertDuncaseAssign(taskList, sysUser);
//        }
        this.tLcTaskService.insertDuncaseAssign(robotTaskId);
    }

    @Override
    public void start(String robotTaskIds) {
        robotMethodUtil.batchStartTask(robotTaskIds);
    }

    @Override
    public void pause(String robotTaskIds) {
        Arrays.stream(robotTaskIds.split(","))
                .forEach(robotTaskId -> {
                    robotMethodUtil.pauseTask(Integer.valueOf(robotTaskId));
                });
    }

    @Override
    public void stop(String robotTaskIds) {
        Arrays.stream(robotTaskIds.split(","))
                .forEach(robotTaskId -> {
                    robotMethodUtil.stopTask(Integer.valueOf(robotTaskId));
                });
    }

    @Override
    public void plyaLuyin(String luyinUrl) {
        AudioInputStream audioInputStream = null;
        SourceDataLine sourceDataLine = null;
        try {
//            audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\liurunkai\\Downloads\\57_early_media.wav"));
            audioInputStream = AudioSystem.getAudioInputStream(new URL(luyinUrl));
            AudioFormat audioFormat = audioInputStream.getFormat();
            log.info("每秒播放帧数{}，总帧数{}，音频时长（秒）{}", audioFormat.getSampleRate(), audioInputStream.getFrameLength(), audioInputStream.getFrameLength() / audioFormat.getSampleRate());
            DataLine.Info dataLine_info = new DataLine.Info(SourceDataLine.class, audioFormat);
            log.info("dataLine_info is {}", dataLine_info);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLine_info);
            log.info("sourceDataLine is {}", sourceDataLine);
            byte[] b = new byte[1024];
            int len = 0;
            sourceDataLine.open(audioFormat, 1024);
            sourceDataLine.start();
            log.info("sourceDataLine已开始");
            while ((len = audioInputStream.read(b)) > 0) {
                sourceDataLine.write(b, 0, len);
            }
            log.info("语音播放完成");
        } catch (Exception e) {
            log.error("录音播放异常,error is {}", e);
            throw new RuntimeException("录音播放异常");
        } finally {
            try {
                audioInputStream.close();
                log.info("audioInputStream关闭成功");
                sourceDataLine.drain();
                log.info("sourceDataLine清空数据缓冲");
                sourceDataLine.close();
                log.info("sourceDataLine关闭");
            } catch (IOException e) {
                log.error("io流关闭异常:{}", e);
                throw new RuntimeException("io流关闭异常");
            }
        }
    }

    @Override
    public void delete(String robotTaskIds) {
        Arrays.stream(robotTaskIds.split(","))
                .forEach(robotTaskId -> {
                    robotMethodUtil.deleteTask(Integer.valueOf(robotTaskId));
                });
    }

    @Override
    public Integer downLoadLuyin(HttpServletResponse response, String ids) {
        TLcRobotCallRecordMeteData callRecordMeteData = null;
        String[] idss = ids.split(",");
        for (String id : idss) {
            callRecordMeteData = this.callRecordMeteDataService.selectTLcRobotCallRecordMeteDataById(Long.valueOf(id));
            // 下载机器人与客户通话录音
            DownLoadFromHttpsUtil.downLoadFromHttps(response, callRecordMeteData.getLuyinOssUrl());
            // 下载客户录音
//            DownLoadFromHttpsUtil.downLoadFromHttps(response, callRecordMeteData.getUserLuyinOssUrl());
        }
        return callRecordMeteData.getCallJobId();
    }

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TLcRobotTask selectTLcRobotTaskById(Long id) {
        return tLcRobotTaskMapper.selectTLcRobotTaskById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcRobotTask 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TLcRobotTask> selectTLcRobotTaskList(TLcRobotTask tLcRobotTask) {
        return tLcRobotTaskMapper.selectTLcRobotTaskList(tLcRobotTask);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcRobotTask 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTLcRobotTask(TLcRobotTask tLcRobotTask) {
        return tLcRobotTaskMapper.insertTLcRobotTask(tLcRobotTask);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcRobotTask 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTLcRobotTask(TLcRobotTask tLcRobotTask) {
        return tLcRobotTaskMapper.updateTLcRobotTask(tLcRobotTask);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcRobotTaskByIds(String ids) {
        return tLcRobotTaskMapper.deleteTLcRobotTaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTLcRobotTaskById(Long id) {
        return tLcRobotTaskMapper.deleteTLcRobotTaskById(id);
    }

    @Override
    public Integer downLoadRobotRadio(HttpServletResponse response, String luyinUrl) {
        TLcRobotCallRecordMeteData callRecordMeteData = null;
        // 下载机器人与客户通话录音
        DownLoadFromHttpsUtil.downLoadFromHttps(response, luyinUrl);
        return callRecordMeteData.getCallJobId();
    }

    @Override
    public TLcRobotTask selectRobotTaskByRobotTaskId(Integer robotTaskId) {
        return this.tLcRobotTaskMapper.selectTLcRobotTaskByRobotTaskId(robotTaskId);
    }

    @Override
    public void handCallback(String ids) {
        List<TLcRobotTask> robotTaskList = Arrays.stream(ids.split(","))
                .map(id -> this.tLcRobotTaskMapper.selectTLcRobotTaskById(Long.valueOf(id)))
                .collect(Collectors.toList());
        Integer pageNum = 1;
        Integer pageSize = 50;
        Map<Integer, List<TLcRobotTask>> robotTaskListMap = robotTaskList.stream().collect(Collectors.groupingBy(TLcRobotTask::getRobotTastId));
        for (Map.Entry<Integer, List<TLcRobotTask>> robotTaskMap : robotTaskListMap.entrySet()) {
            // 只有已完成的或者停止的可以手工回调
            RobotTask taskDetail = this.robotMethodUtil.getTaskDetail(robotTaskMap.getKey());
            if (TaskStatus.FINISHED.getCode().equals(taskDetail.getStatus()) || TaskStatus.STOP.getCode().equals(taskDetail.getStatus())) {
                DoneTaskPhones doneTaskPhones = this.robotMethodUtil.queryDoneTaskPhones(robotTaskMap.getKey(), pageNum, pageSize);
                getPhoneLogInfoCallback(robotTaskMap, doneTaskPhones);
                if (doneTaskPhones.getPages() > 1) {
                    for (int i = 2; i <= doneTaskPhones.getPages(); i++) {
                        doneTaskPhones = this.robotMethodUtil.queryDoneTaskPhones(robotTaskMap.getKey(), i, pageSize);
                        getPhoneLogInfoCallback(robotTaskMap, doneTaskPhones);
                    }
                }
            }
        }
//        robotTaskList.stream()
//                .collect(Collectors.groupingBy(TLcRobotTask::getRobotTastId))
//                .entrySet()
//                .stream()
//                .forEach(robotTaskMap -> {
//                    DoneTaskPhones doneTaskPhones = this.robotMethodUtil.queryDoneTaskPhones(robotTaskMap.getKey(), pageNum, pageSize);
//                    getPhoneLogInfoCallback(robotTaskMap, doneTaskPhones);
//                    if (doneTaskPhones.getPages() > 1) {
//                        for (int i = 2; i <= doneTaskPhones.getPages(); i++) {
//                            doneTaskPhones = this.robotMethodUtil.queryDoneTaskPhones(robotTaskMap.getKey(), i, pageSize);
//                            getPhoneLogInfoCallback(robotTaskMap, doneTaskPhones);
//                        }
//                    }
//                });
    }

    /**
     * 获取通话记录id并执行回调逻辑
     *
     * @param robotTaskMap
     * @param doneTaskPhones
     */
    private void getPhoneLogInfoCallback(Map.Entry<Integer, List<TLcRobotTask>> robotTaskMap, DoneTaskPhones doneTaskPhones) {
//        List<DoneTaskPhones.DoneTaskPhone> callbackFaildList = robotTaskMap.getValue().stream()
//                .map(tLcRobotTask -> doneTaskPhones.getList().stream()
//                        .filter(doneTaskPhone -> Objects.equals(tLcRobotTask.getPhone(), doneTaskPhone.getCustomerTelephone()))
//                        .findFirst()
//                        .orElse(null))
//                .filter(Objects::isNull)
//                .collect(Collectors.toList());
        List<DoneTaskPhones.DoneTaskPhone> callbackFaildList = new ArrayList<>();
        for (TLcRobotTask tLcRobotTask : robotTaskMap.getValue()) {
            for (DoneTaskPhones.DoneTaskPhone doneTaskPhone : doneTaskPhones.getList()) {
                if (tLcRobotTask.getPhone().equals(doneTaskPhone.getCustomerTelephone())) {
                    callbackFaildList.add(doneTaskPhone);
                }
            }
        }
        callbackFaildList.stream()
                .filter(doneTaskPhone -> doneTaskPhone != null)
                .forEach(doneTaskPhone -> {
                    RobotResponse robotResponse = this.robotMethodUtil.getPhoneLogInfo(doneTaskPhone.getCallInstanceId());
                    this.robotMethodUtil.handleCallCallback(robotResponse);
                });
    }

}
