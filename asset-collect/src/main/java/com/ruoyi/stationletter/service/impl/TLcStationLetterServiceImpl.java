package com.ruoyi.stationletter.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.enums.StationLetterEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.stationletter.domain.TLcStationLetter;
import com.ruoyi.stationletter.domain.TLcStationLetterAgent;
import com.ruoyi.stationletter.mapper.TLcStationLetterMapper;
import com.ruoyi.stationletter.service.ITLcStationLetterAgentService;
import com.ruoyi.stationletter.service.ITLcStationLetterService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 站内信Service业务层处理
 *
 * @author liurunkai
 * @date 2020-10-30
 */
@Service
public class TLcStationLetterServiceImpl implements ITLcStationLetterService {

    @Autowired
    private TLcStationLetterMapper tLcStationLetterMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ITLcStationLetterAgentService stationLetterAgentService;

    /**
     * 查询站内信
     *
     * @param id 站内信ID
     * @return 站内信
     */
    @Override
    public TLcStationLetter selectTLcStationLetterById(Long id) {
        return tLcStationLetterMapper.selectTLcStationLetterById(id);
    }

    /**
     * 查询站内信列表
     *
     * @param tLcStationLetter 站内信
     * @return 站内信
     */
    @Override
    public List<TLcStationLetter> selectTLcStationLetterList(TLcStationLetter tLcStationLetter) {
        tLcStationLetter.setOrgId(ShiroUtils.getSysUser().getOrgId());
        return tLcStationLetterMapper.selectTLcStationLetterList(tLcStationLetter);
    }

    /**
     * 新增站内信
     *
     * @param tLcStationLetter 站内信
     * @return 结果
     */
    @Override
    @Transactional
    public int insertTLcStationLetter(TLcStationLetter tLcStationLetter) {
        Long orgId = ShiroUtils.getSysUser().getOrgId();
        String orgName = ShiroUtils.getSysUser().getOrgName();
        tLcStationLetter.setCreateBy(ShiroUtils.getSysUser().getUserId().toString());
        tLcStationLetter.setUpdateBy(ShiroUtils.getSysUser().getUserId().toString());
        tLcStationLetter.setCreateTime(DateUtils.getNowDate());
        tLcStationLetter.setUpdateTime(tLcStationLetter.getCreateTime());
        tLcStationLetter.setOrgId(orgId);
        tLcStationLetter.setOrgName(orgName);
        if (tLcStationLetter.getSendType().equals(IsNoEnum.IS.getCode())) {
            // 立即发送
            tLcStationLetter.setSendTime(tLcStationLetter.getCreateTime());
            tLcStationLetter.setSendStatus(IsNoEnum.IS.getCode());
        } else {
            tLcStationLetter.setSendStatus(IsNoEnum.NO.getCode());
        }
        int i = tLcStationLetterMapper.insertTLcStationLetter(tLcStationLetter);
        // 将站内信添加到用户
        addLetterToUser(tLcStationLetter, orgId, orgName);
        return i;
    }

    /**
     * 将站内信添加到用户
     * @param tLcStationLetter
     */
    private void addLetterToUser(TLcStationLetter tLcStationLetter, Long orgId, String orgName) {
        List<String> userIdList = new ArrayList<>();
        if (tLcStationLetter.getSendRange().equals(IsNoEnum.IS.getCode())) {
            // 查询所有拥有此机构权限的所有用户
            userIdList = this.sysUserMapper.searchUserIdByDeptAndHaveDept(ShiroUtils.getSysUser().getOrgId());
        } else {
            String userIds = tLcStationLetter.getUserIds();
            userIdList = Arrays.asList(userIds.split(","));
        }
        if (userIdList != null && userIdList.size() > 0) {
            List<TLcStationLetterAgent> letterAgentList = userIdList.stream().map(userId -> {
                TLcStationLetterAgent tLcStationLetterAgent = new TLcStationLetterAgent();
                tLcStationLetterAgent.setLetterId(tLcStationLetter.getId());
                tLcStationLetterAgent.setTitle(tLcStationLetter.getTitle());
                tLcStationLetterAgent.setContent(tLcStationLetter.getContent());
                tLcStationLetterAgent.setAgentId(userId);
                tLcStationLetterAgent.setSendBy(Integer.valueOf(tLcStationLetter.getCreateBy()));
                tLcStationLetterAgent.setSendTime(tLcStationLetter.getSendTime());
                tLcStationLetterAgent.setReadStatus(IsNoEnum.NO.getCode());
                tLcStationLetterAgent.setCreateBy(tLcStationLetter.getCreateBy());
                tLcStationLetterAgent.setCreateTime(tLcStationLetter.getCreateTime());
                tLcStationLetterAgent.setOrgId(orgId);
                tLcStationLetterAgent.setOrgName(orgName);
                return tLcStationLetterAgent;
            }).collect(Collectors.toList());
            this.stationLetterAgentService.batchInsertTLcStationLetterAgent(letterAgentList);
        }
    }

    /**
     * 修改站内信
     *
     * @param tLcStationLetter 站内信
     * @return 结果
     */
    @Override
    @Transactional
    public int updateTLcStationLetter(TLcStationLetter tLcStationLetter) {
        Long orgId = ShiroUtils.getSysUser().getOrgId();
        String orgName = ShiroUtils.getSysUser().getOrgName();
        Date now = DateUtils.getNowDate();
        tLcStationLetter.setUpdateTime(now);
        tLcStationLetter.setUpdateBy(ShiroUtils.getSysUser().getUserId().toString());
        if (tLcStationLetter.getSendRange().equals(IsNoEnum.IS.getCode())) {
            tLcStationLetter.setUserIds(null);
        }
        if (tLcStationLetter.getSendType().equals(IsNoEnum.IS.getCode())) {
            tLcStationLetter.setSendTime(now);
        }
        tLcStationLetter.setOrgId(orgId);
        tLcStationLetter.setOrgName(orgName);
        updateStationLetterAgent(tLcStationLetter);
        return tLcStationLetterMapper.updateTLcStationLetter(tLcStationLetter);
    }

    private void updateStationLetterAgent(TLcStationLetter tLcStationLetter) {
        this.stationLetterAgentService.deleteByLetterId(tLcStationLetter.getId());
        TLcStationLetter stationLetter = this.tLcStationLetterMapper.selectTLcStationLetterById(tLcStationLetter.getId());
        tLcStationLetter.setCreateBy(stationLetter.getCreateBy());
        tLcStationLetter.setCreateTime(stationLetter.getCreateTime());
        // 指定用户的时候如果没有选择自己需要将自己添加上
        if (tLcStationLetter.getSendRange().equals(IsNoEnum.NO.getCode())) {
            List<String> userIds = new ArrayList(Arrays.asList(tLcStationLetter.getUserIds().split(",")));
            if (!userIds.contains(ShiroUtils.getSysUser().getUserId().toString())) {
                userIds.add(ShiroUtils.getSysUser().getUserId().toString());
                tLcStationLetter.setUserIds(userIds.stream().collect(Collectors.joining(",")));
            }
        }
        addLetterToUser(tLcStationLetter, tLcStationLetter.getOrgId(), tLcStationLetter.getOrgName());
    }

    /**
     * 删除站内信对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcStationLetterByIds(String ids) {
        return tLcStationLetterMapper.deleteTLcStationLetterByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除站内信信息
     *
     * @param id 站内信ID
     * @return 结果
     */
    @Override
    public int deleteTLcStationLetterById(Long id) {
        return tLcStationLetterMapper.deleteTLcStationLetterById(id);
    }

    @Override
    public List<SysUser> selectSendLetterUser(SysUser sysUser) {
        List<SysUser> userList = this.sysUserMapper.searchUserByDeptAndHaveDept(sysUser);
        return userList;
    }

    @Override
    public List<TLcStationLetter> selectWaitSendLetter(TLcStationLetter tLcStationLetter) {
        return this.tLcStationLetterMapper.selectWaitSendLetter(tLcStationLetter);
    }

    @Override
    public void updateLetterSendStatus() {
        this.tLcStationLetterMapper.updateLetterSendStatus();
    }

    @Override
    @Transactional
    public void sendReplyStationLetter(TLcStationLetter tLcStationLetter) {
        // 生成站内信
        Date date = new Date();
        tLcStationLetter.setSendTime(date);
        tLcStationLetter.setCreateBy(ShiroUtils.getSysUser().getUserId().toString());
        tLcStationLetter.setCreateTime(date);
        tLcStationLetter.setUpdateBy(ShiroUtils.getSysUser().getUserId().toString());
        tLcStationLetter.setUpdateTime(date);
        tLcStationLetter.setOrgId(ShiroUtils.getSysUser().getOrgId());
        tLcStationLetter.setOrgName(ShiroUtils.getSysUser().getOrgName());
        this.tLcStationLetterMapper.insertTLcStationLetter(tLcStationLetter);
        // 生成用户接收记录
        TLcStationLetterAgent tLcStationLetterAgent = new TLcStationLetterAgent();
        tLcStationLetterAgent.setLetterId(tLcStationLetter.getId());
        tLcStationLetterAgent.setTitle(tLcStationLetter.getTitle());
        tLcStationLetterAgent.setContent(tLcStationLetter.getContent());
        tLcStationLetterAgent.setAgentId(tLcStationLetter.getUserIds());
        tLcStationLetterAgent.setSendBy(Integer.valueOf(tLcStationLetter.getCreateBy()));
        tLcStationLetterAgent.setSendTime(tLcStationLetter.getSendTime());
        tLcStationLetterAgent.setReadStatus(StationLetterEnum.WAIT_READ.getStatus());
        tLcStationLetterAgent.setCreateBy(tLcStationLetter.getCreateBy());
        tLcStationLetterAgent.setCreateTime(tLcStationLetter.getCreateTime());
        tLcStationLetterAgent.setOrgId(ShiroUtils.getSysUser().getOrgId());
        tLcStationLetterAgent.setOrgName(ShiroUtils.getSysUser().getOrgName());
        this.stationLetterAgentService.insertTLcStationLetterAgent(tLcStationLetterAgent);
        // 修改状态为已回复
        this.stationLetterAgentService.updateReadStatus(StationLetterEnum.HAVE_REPLY.getStatus(), tLcStationLetter.getLetterAgentId());
    }
}
