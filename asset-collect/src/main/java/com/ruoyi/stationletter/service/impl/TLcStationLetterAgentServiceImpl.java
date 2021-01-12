package com.ruoyi.stationletter.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.stationletter.domain.TLcStationLetter;
import com.ruoyi.stationletter.domain.TLcStationLetterAgent;
import com.ruoyi.stationletter.mapper.TLcStationLetterAgentMapper;
import com.ruoyi.stationletter.service.ITLcStationLetterAgentService;
import com.ruoyi.stationletter.service.ITLcStationLetterService;
import com.ruoyi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 站内信Service业务层处理
 *
 * @author liurunkai
 * @date 2020-10-30
 */
@Service
public class TLcStationLetterAgentServiceImpl implements ITLcStationLetterAgentService {
    @Autowired
    private TLcStationLetterAgentMapper tLcStationLetterAgentMapper;
    @Autowired
    private ITLcStationLetterService stationLetterService;

    /**
     * 查询站内信
     *
     * @param id 站内信ID
     * @return 站内信
     */
    @Override
    public TLcStationLetterAgent selectTLcStationLetterAgentById(Long id) {
        return tLcStationLetterAgentMapper.selectTLcStationLetterAgentById(id);
    }

    /**
     * 查询站内信列表
     *
     * @param tLcStationLetterAgent 站内信
     * @return 站内信
     */
    @Override
    public List<TLcStationLetterAgent> selectTLcStationLetterAgentList(TLcStationLetterAgent tLcStationLetterAgent) {
        return tLcStationLetterAgentMapper.selectTLcStationLetterAgentList(tLcStationLetterAgent);
    }

    /**
     * 新增站内信
     *
     * @param tLcStationLetterAgent 站内信
     * @return 结果
     */
    @Override
    public int insertTLcStationLetterAgent(TLcStationLetterAgent tLcStationLetterAgent) {
        tLcStationLetterAgent.setCreateTime(DateUtils.getNowDate());
        return tLcStationLetterAgentMapper.insertTLcStationLetterAgent(tLcStationLetterAgent);
    }

    /**
     * 修改站内信
     *
     * @param tLcStationLetterAgent 站内信
     * @return 结果
     */
    @Override
    public int updateTLcStationLetterAgent(TLcStationLetterAgent tLcStationLetterAgent) {
        tLcStationLetterAgent.setUpdateTime(DateUtils.getNowDate());
        return tLcStationLetterAgentMapper.updateTLcStationLetterAgent(tLcStationLetterAgent);
    }

    /**
     * 删除站内信对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcStationLetterAgentByIds(String ids) {
        return tLcStationLetterAgentMapper.deleteTLcStationLetterAgentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除站内信信息
     *
     * @param id 站内信ID
     * @return 结果
     */
    @Override
    public int deleteTLcStationLetterAgentById(Long id) {
        return tLcStationLetterAgentMapper.deleteTLcStationLetterAgentById(id);
    }

    @Override
    public Response getStationLetter() {
        TLcStationLetterAgent tLcStationLetterAgent = new TLcStationLetterAgent();
        tLcStationLetterAgent.setAgentId(ShiroUtils.getSysUser().getUserId().toString());
        tLcStationLetterAgent.setOrgId(ShiroUtils.getSysUser().getOrgId());
        List<TLcStationLetterAgent> letterAgentList = this.tLcStationLetterAgentMapper.selectWaitSendLetterAgentList(tLcStationLetterAgent);
        // 修改状态为已发送
//        this.stationLetterService.updateLetterSendStatus();
        return Response.success(letterAgentList);
    }

    @Override
    public void batchInsertTLcStationLetterAgent(List<TLcStationLetterAgent> letterAgentList) {
        this.tLcStationLetterAgentMapper.batchInsertTLcStationLetterAgent(letterAgentList);
    }

    @Override
    public void deleteByLetterId(Long id) {
        this.tLcStationLetterAgentMapper.deleteByLetterId(id);
    }

    @Override
    public List<TLcStationLetterAgent> getStationLetterDetail() {
        TLcStationLetterAgent tLcStationLetterAgent = new TLcStationLetterAgent();
        tLcStationLetterAgent.setAgentId(ShiroUtils.getSysUser().getUserId().toString());
        tLcStationLetterAgent.setOrgId(ShiroUtils.getSysUser().getOrgId());
        List<TLcStationLetterAgent> letterAgentList = this.tLcStationLetterAgentMapper.selectTLcStationLetterAgentList(tLcStationLetterAgent);
        return letterAgentList;
    }

    @Override
    public void updateReadStatus(Integer status, Long id) {
        this.tLcStationLetterAgentMapper.updateReadStatus(status, id);
    }

    @Override
    public Response getWaitReadNum() {
        TLcStationLetterAgent tLcStationLetterAgent = new TLcStationLetterAgent();
        tLcStationLetterAgent.setAgentId(ShiroUtils.getSysUser().getUserId().toString());
        tLcStationLetterAgent.setOrgId(ShiroUtils.getSysUser().getOrgId());
        Long watiReadNum = this.tLcStationLetterAgentMapper.getWaitReadNum(tLcStationLetterAgent);
        return Response.success(watiReadNum);
    }
}
