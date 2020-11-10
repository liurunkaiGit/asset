package com.ruoyi.stationletter.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.stationletter.domain.LetterDetailVo;
import com.ruoyi.stationletter.domain.TLcStationLetterAgent;
import com.ruoyi.stationletter.service.ITLcStationLetterAgentService;
import com.ruoyi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 站内信Controller
 *
 * @author liurunkai
 * @date 2020-10-30
 */
@Controller
@RequestMapping("/station/letter/agent")
public class TLcStationLetterAgentController extends BaseController {
    private String prefix = "stationletter";

    @Autowired
    private ITLcStationLetterAgentService tLcStationLetterAgentService;

    /**
     * 查询站内信列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcStationLetterAgent tLcStationLetterAgent) {
        startPage();
        List<TLcStationLetterAgent> list = tLcStationLetterAgentService.selectTLcStationLetterAgentList(tLcStationLetterAgent);
        return getDataTable(list);
    }

    /**
     * 查看未读站内信数量
     */
    @GetMapping("/getStationLetter")
    @ResponseBody
    public Response getStationLetter() {
        return this.tLcStationLetterAgentService.getStationLetter();
    }

    /**
     * 查看鼠标悬浮站内信
     */
    @GetMapping("/getStationLetterDetail")
    @ResponseBody
    public Response getStationLetterDetail(ModelMap modelMap) {
        LetterDetailVo letterDetailVo = new LetterDetailVo();
        List<TLcStationLetterAgent> agentList = this.tLcStationLetterAgentService.getStationLetterDetail();
        long count = agentList.stream().filter(agent -> agent.getReadStatus().equals(IsNoEnum.NO.getCode())).count();
        letterDetailVo.setWaitReadNum(count);
        if (agentList != null && agentList.size() > 5) {
            letterDetailVo.setLetterList(agentList.subList(0, 5));
        } else {
            letterDetailVo.setLetterList(agentList);
        }
        return Response.success(letterDetailVo);
    }

    /**
     * 查看鼠标悬浮站内信
     */
    @GetMapping("/getStationLetterDetail2")
    public String getStationLetterDetail2(ModelMap modelMap) {
        LetterDetailVo letterDetailVo = new LetterDetailVo();
        List<TLcStationLetterAgent> agentList = this.tLcStationLetterAgentService.getStationLetterDetail();
        long count = agentList.stream().filter(agent -> agent.getReadStatus().equals(IsNoEnum.NO.getCode())).count();
        letterDetailVo.setWaitReadNum(count);
        letterDetailVo.setLetterList(agentList.subList(0, 5));
        modelMap.put("letterDetailVo", letterDetailVo);
        return prefix + "/indexLetter";
    }

    /**
     * 查看所有站内信
     *
     * @param modelMap
     * @return
     */
    @GetMapping("/toGetAllStationLetter")
    public String toGetAllStationLetter(ModelMap modelMap) {
        modelMap.put("userId", ShiroUtils.getSysUser().getUserId());
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        return prefix + "/userLetter";
    }

    /**
     * 查看站内信详情
     *
     * @param modelMap
     * @return
     */
    @GetMapping("/letterDetail/{id}")
    @ResponseBody
    public Response letterDetail(@PathVariable("id") Long id, ModelMap modelMap) {
        TLcStationLetterAgent letterAgent = this.tLcStationLetterAgentService.selectTLcStationLetterAgentById(id);
        this.tLcStationLetterAgentService.updateReadStatus(id);
        return Response.success(letterAgent);
    }
}
