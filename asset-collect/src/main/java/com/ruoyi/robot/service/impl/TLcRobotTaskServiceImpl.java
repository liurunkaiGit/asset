package com.ruoyi.robot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.robot.domain.CallContent;
import com.ruoyi.robot.domain.TLcRobotTask;
import com.ruoyi.robot.mapper.TLcRobotTaskMapper;
import com.ruoyi.robot.service.ITLcRobotTaskService;
import com.ruoyi.robot.service.RobotService;
import com.ruoyi.utils.DataPermissionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author liurunkai
 * @date 2020-03-18
 */
@Slf4j
@Service("com.ruoyi.robot.service.impl.TLcRobotTaskServiceImpl")
public class TLcRobotTaskServiceImpl extends BaseController implements ITLcRobotTaskService {

    @Autowired
    private RobotService robotService;
    @Autowired
    private TLcRobotTaskMapper tLcRobotTaskMapper;

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
    public List<TLcRobotTask> selectTLcRobotTaskList(TLcRobotTask tLcRobotTask, HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (tLcRobotTask.getEndCallEndDate() != null) {
            tLcRobotTask.setEndCallEndDate(DateUtils.getEndOfDay(tLcRobotTask.getEndCallEndDate()));
        }
        if (tLcRobotTask.getEndCreateTime() != null) {
            tLcRobotTask.setEndCreateTime(DateUtils.getEndOfDay(tLcRobotTask.getEndCreateTime()));
        }
//        StringBuilder sb = new StringBuilder();
//        if (StringUtils.isNoneBlank(tLcRobotTask.getTransfer_type())) {
//            for (Map.Entry<String, String[]> map : parameterMap.entrySet()) {
//                if (map.getKey().equals("compareMethod_transfer_type")) {
//                    List<String> values = Arrays.asList(map.getValue());
//                    if (values.get(0).equals("equal")) {
//                        sb.append(" and t.transfer_type = '"+tLcRobotTask.getTransfer_type()+"'");
//                    }
//                    if (values.get(0).equals("like")) {
//                        sb.append(" and t.transfer_type like '%"+tLcRobotTask.getTransfer_type()+"%'");
//                    }
//                    break;
//                }
//            }
//        }
//        if (StringUtils.isNoneBlank(tLcRobotTask.getPhone())) {
//            for (Map.Entry<String, String[]> map : parameterMap.entrySet()) {
//                if (map.getKey().equals("compareMethod_phone")) {
//                    List<String> values = Arrays.asList(map.getValue());
//                    if (values.get(0).equals("equal")) {
//                        sb.append(" and t.phone = " + tLcRobotTask.getPhone());
//                    }
//                    if (values.get(0).equals("like")) {
//                        sb.append(" and t.phone like '%"+tLcRobotTask.getPhone()+"%'");
//                    }
//                    break;
//                }
//            }
//        }
//        if (StringUtils.isNoneBlank(tLcRobotTask.getOwner_name())) {
//            for (Map.Entry<String, String[]> map : parameterMap.entrySet()) {
//                if (map.getKey().equals("compareMethod_owner_name")) {
//                    List<String> values = Arrays.asList(map.getValue());
//                    if (values.get(0).equals("equal")) {
//                        sb.append(" and t.owner_name = '" + tLcRobotTask.getOwner_name()+"'");
//                    }
//                    if (values.get(0).equals("like")) {
//                        sb.append(" and t.owner_name like '%"+tLcRobotTask.getOwner_name()+"%'");
//                    }
//                    break;
//                }
//            }
//        }
//        if (StringUtils.isNoneBlank(tLcRobotTask.getCur_name())) {
//            for (Map.Entry<String, String[]> map : parameterMap.entrySet()) {
//                if (map.getKey().equals("compareMethod_cur_name")) {
//                    List<String> values = Arrays.asList(map.getValue());
//                    if (values.get(0).equals("equal")) {
//                        sb.append(" and t.cur_name = '" + tLcRobotTask.getCur_name()+"'");
//                    }
//                    if (values.get(0).equals("like")) {
//                        sb.append(" and t.cur_name like '%"+tLcRobotTask.getCur_name()+"%'");
//                    }
//                    break;
//                }
//            }
//        }
//        if (tLcRobotTask.getStart_call_len() != null) {
//            sb.append(" and t.call_len >= " + tLcRobotTask.getStart_call_len());
//        }
//        if (tLcRobotTask.getEnd_call_len() != null) {
//            sb.append(" and t.call_len <= " + tLcRobotTask.getEnd_call_len());
//        }
//        if (StringUtils.isNoneBlank(tLcRobotTask.getSpeechCraftName())) {
//            sb.append(" and t.speech_craft_name = '" + tLcRobotTask.getSpeechCraftName()+"'");
//        }
//        if (StringUtils.isNoneBlank(tLcRobotTask.getResult_value_alias())) {
//            sb.append(" and t.result_value_alias = '" + tLcRobotTask.getResult_value_alias()+"'");
//        }
//        if (StringUtils.isNoneBlank(tLcRobotTask.getTask_type())) {
//            sb.append(" and t.task_type = " + tLcRobotTask.getTask_type());
//        }
//        if (tLcRobotTask.getStart_create_time() != null) {
//            sb.append(" and t.create_time >= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,tLcRobotTask.getStart_create_time())+"'");
//        }
//        if (tLcRobotTask.getEnd_create_time() != null) {
//            sb.append(" and t.create_time <= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,DateUtils.getEndOfDay(tLcRobotTask.getEnd_create_time()))+"'");
//        }
//        if (tLcRobotTask.getStart_call_end_date() != null) {
//            sb.append(" and t.call_end_date >= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,tLcRobotTask.getStart_call_end_date())+"'");
//        }
//        if (tLcRobotTask.getEnd_call_end_date() != null) {
//            sb.append(" and t.call_end_date <= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,DateUtils.getEndOfDay(tLcRobotTask.getEnd_call_end_date()))+"'");
//        }
//        if (StringUtils.isNoneBlank(sb.toString())) {
//            tLcRobotTask.setSql(sb.toString());
//        }
//        log.info("动态查询的sql是{}", tLcRobotTask.getSql());
        return tLcRobotTaskMapper.selectTLcRobotTaskList(tLcRobotTask);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcRobotTask 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TLcRobotTask> selectTLcRobotTaskList(TLcRobotTask tLcRobotTask) {
        if (tLcRobotTask.getEndCallEndDate() != null) {
            tLcRobotTask.setEndCallEndDate(DateUtils.getEndOfDay(tLcRobotTask.getEndCallEndDate()));
        }
        if (tLcRobotTask.getEndCreateTime() != null) {
            tLcRobotTask.setEndCreateTime(DateUtils.getEndOfDay(tLcRobotTask.getEndCreateTime()));
        }
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
        tLcRobotTask.setCreateTime(DateUtils.getNowDate());
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
    public TLcRobotTask selectTLcRobotTaskByRobotTaskId(Integer callJobId) {
        return this.tLcRobotTaskMapper.selectTLcRobotTaskByRobotTaskId(callJobId);
    }

    @Override
    public List<CallContent> viewCallContent(String id) {
        TLcRobotTask tLcRobotTask = this.tLcRobotTaskMapper.selectTLcRobotTaskById(Long.valueOf(id));
        List<CallContent> callContentList = JSONObject.parseArray(tLcRobotTask.getCallContent(), CallContent.class);
        return callContentList;
    }

    @Override
    public List<TLcRobotTask> selectListByRobotTaskId(Integer robotTaskId) {
        return this.tLcRobotTaskMapper.selectListByRobotTaskId(robotTaskId);
    }

    @Override
    public TLcRobotTask selectRobotTaskByRobotTaskIdAndTaskId(Integer robotTaskId, Long taskId) {
        return this.tLcRobotTaskMapper.selectRobotTaskByRobotTaskIdAndTaskId(robotTaskId, taskId);
    }

    @Override
    public TLcRobotTask selectRobotTaskByRobotTaskIdAndPhone(Integer robotTaskId, String customerTelephone) {
        return this.tLcRobotTaskMapper.selectRobotTaskByRobotTaskIdAndPhone(robotTaskId,customerTelephone);
    }

    @Override
    public List<TLcRobotTask> selectCallbackFaild(int callJobId) {
        return this.tLcRobotTaskMapper.selectCallbackFaild(callJobId);
    }

    @Override
    public void batchAddRobotTask(List<TLcRobotTask> tLcRobotTaskList) {
        this.tLcRobotTaskMapper.batchAddRobotTask(tLcRobotTaskList);
    }

    @Override
    public void updateTLcRobotTaskByRobotTaskIdAndPhone(TLcRobotTask tLcRobotTask) {
        this.tLcRobotTaskMapper.updateTLcRobotTaskByRobotTaskIdAndPhone(tLcRobotTask);
    }

    @Override
    public void updateRobotTaskStatusByRobotTaskId(TLcRobotTask tLcRobotTask) {
        this.tLcRobotTaskMapper.updateRobotTaskStatusByRobotTaskId(tLcRobotTask);
    }
}
