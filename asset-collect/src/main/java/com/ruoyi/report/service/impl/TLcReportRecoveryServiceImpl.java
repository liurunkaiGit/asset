package com.ruoyi.report.service.impl;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.report.domain.TLcReportRecovery;
import com.ruoyi.report.mapper.TLcReportRecoveryMapper;
import com.ruoyi.report.service.ITLcReportRecoveryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 回收率报Service业务层处理
 *
 * @author liurunkai
 * @date 2020-04-09
 */
@Service
public class TLcReportRecoveryServiceImpl extends BaseController implements ITLcReportRecoveryService {
    @Autowired
    private TLcReportRecoveryMapper tLcReportRecoveryMapper;

    /**
     * 查询回收率报
     *
     * @param id 回收率报ID
     * @return 回收率报
     */
    @Override
    public TLcReportRecovery selectTLcReportRecoveryById(Long id) {
        return tLcReportRecoveryMapper.selectTLcReportRecoveryById(id);
    }

    /**
     * 查询回收率报列表
     *
     * @param tLcReportRecovery 回收率报
     * @return 回收率报
     */
    @Override
    public List<TLcReportRecovery> selectTLcReportRecoveryList(TLcReportRecovery tLcReportRecovery) {
        List<TLcReportRecovery> list = new ArrayList<>();
        if (StringUtils.isBlank(tLcReportRecovery.getOrgId())) {
            return list;
        }
        if (org.apache.commons.lang3.time.DateUtils.isSameDay(tLcReportRecovery.getReportDate(), new Date())) {
            Map<String, Object> param = new HashMap<>();
            param.put("day", 0);
            param.put("orgId", tLcReportRecovery.getOrgId());
            param.put("startDate",DateUtils.getFirstDay());
            startPage();
            list = selectRecoveryByPayment(param);
        } else {
            startPage();
            list = this.tLcReportRecoveryMapper.selectTLcReportRecoveryList(tLcReportRecovery);
        }
        return list;
    }

    /**
     * 新增回收率报
     *
     * @param tLcReportRecovery 回收率报
     * @return 结果
     */
    @Override
    public int insertTLcReportRecovery(TLcReportRecovery tLcReportRecovery) {
        tLcReportRecovery.setCreateTime(DateUtils.getNowDate());
        return tLcReportRecoveryMapper.insertTLcReportRecovery(tLcReportRecovery);
    }

    /**
     * 修改回收率报
     *
     * @param tLcReportRecovery 回收率报
     * @return 结果
     */
    @Override
    public int updateTLcReportRecovery(TLcReportRecovery tLcReportRecovery) {
        return tLcReportRecoveryMapper.updateTLcReportRecovery(tLcReportRecovery);
    }

    /**
     * 删除回收率报对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcReportRecoveryByIds(String ids) {
        return tLcReportRecoveryMapper.deleteTLcReportRecoveryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除回收率报信息
     *
     * @param id 回收率报ID
     * @return 结果
     */
    @Override
    public int deleteTLcReportRecoveryById(Long id) {
        return tLcReportRecoveryMapper.deleteTLcReportRecoveryById(id);
    }

    @Override
    public List<TLcReportRecovery> selectRecoveryByPayment(Map<String, Object> param) {
        return this.tLcReportRecoveryMapper.selectRecovery(param);
    }
}
