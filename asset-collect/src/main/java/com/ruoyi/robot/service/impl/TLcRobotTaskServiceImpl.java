package com.ruoyi.robot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.robot.domain.CallContent;
import com.ruoyi.robot.domain.PhoneLogInfo;
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

    @Override
    public List<TLcRobotTask> selectTLcRobotTaskHisList(TLcRobotTask tLcRobotTask, HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (tLcRobotTask.getEndCallEndDate() != null) {
            tLcRobotTask.setEndCallEndDate(DateUtils.getEndOfDay(tLcRobotTask.getEndCallEndDate()));
        }
        if (tLcRobotTask.getEndCreateTime() != null) {
            tLcRobotTask.setEndCreateTime(DateUtils.getEndOfDay(tLcRobotTask.getEndCreateTime()));
        }
        return tLcRobotTaskMapper.selectTLcRobotTaskHisList(tLcRobotTask);
    }

    @Override
    public TLcRobotTask selectHisTLcRobotTaskById(Long id) {
        return tLcRobotTaskMapper.selectHisTLcRobotTaskById(id);
    }

    @Override
    public List<CallContent>  viewHisCallContent(String id) {
        TLcRobotTask tLcRobotTask = this.tLcRobotTaskMapper.selectHisTLcRobotTaskById(Long.valueOf(id));
        List<CallContent> callContentList = JSONObject.parseArray(tLcRobotTask.getCallContent(), CallContent.class);
        return callContentList;
    }

    @Override
    public List<TLcRobotTask> selectRobotTaskByRobotTaskIdAndPhone(Integer callJobId, String customPhone) {
        return this.tLcRobotTaskMapper.selectRobotTaskByRobotTaskIdAndPhone(callJobId, customPhone);
    }

    @Override
    public void batchInsertCallRecord(int callJobId) {
        this.tLcRobotTaskMapper.batchInsertCallRecord(callJobId);
    }
}
