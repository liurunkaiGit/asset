package com.ruoyi.report.service.impl;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.report.domain.TLcReportCaseContact;
import com.ruoyi.report.mapper.TLcReportCaseContactMapper;
import com.ruoyi.report.service.ITLcReportCaseContactService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 案件可联率报Service业务层处理
 *
 * @author liurunkai
 * @date 2020-04-09
 */
@Service
public class TLcReportCaseContactServiceImpl extends BaseController implements ITLcReportCaseContactService {
    @Autowired
    private TLcReportCaseContactMapper tLcReportCaseContactMapper;

    /**
     * 查询案件可联率报
     *
     * @param id 案件可联率报ID
     * @return 案件可联率报
     */
    @Override
    public TLcReportCaseContact selectTLcReportCaseContactById(Long id) {
        return tLcReportCaseContactMapper.selectTLcReportCaseContactById(id);
    }

    /**
     * 查询案件可联率报列表
     *
     * @param tLcReportCaseContact 案件可联率报
     * @return 案件可联率报
     */
    @Override
    public List<TLcReportCaseContact> selectTLcReportCaseContactList(TLcReportCaseContact tLcReportCaseContact) {
        List<TLcReportCaseContact> list = new ArrayList<>();
        if (StringUtils.isBlank(tLcReportCaseContact.getOrgId())) {
            return list;
        }
        if (org.apache.commons.lang3.time.DateUtils.isSameDay(tLcReportCaseContact.getReportDate(), new Date())) {
            Map<String, Object> param = new HashMap<>();
            param.put("day", 0);
            param.put("orgId", tLcReportCaseContact.getOrgId());
            param.put("startDate",DateUtils.getFirstDay());
            startPage();
            list = selectCaseContactList(param);
        } else {
            startPage();
            list = this.tLcReportCaseContactMapper.selectTLcReportCaseContactList(tLcReportCaseContact);
        }
        return list;
    }

    /**
     * 新增案件可联率报
     *
     * @param tLcReportCaseContact 案件可联率报
     * @return 结果
     */
    @Override
    public int insertTLcReportCaseContact(TLcReportCaseContact tLcReportCaseContact) {
        tLcReportCaseContact.setCreateTime(DateUtils.getNowDate());
        return tLcReportCaseContactMapper.insertTLcReportCaseContact(tLcReportCaseContact);
    }

    /**
     * 修改案件可联率报
     *
     * @param tLcReportCaseContact 案件可联率报
     * @return 结果
     */
    @Override
    public int updateTLcReportCaseContact(TLcReportCaseContact tLcReportCaseContact) {
        return tLcReportCaseContactMapper.updateTLcReportCaseContact(tLcReportCaseContact);
    }

    /**
     * 删除案件可联率报对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcReportCaseContactByIds(String ids) {
        return tLcReportCaseContactMapper.deleteTLcReportCaseContactByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除案件可联率报信息
     *
     * @param id 案件可联率报ID
     * @return 结果
     */
    @Override
    public int deleteTLcReportCaseContactById(Long id) {
        return tLcReportCaseContactMapper.deleteTLcReportCaseContactById(id);
    }

    /**
     * 查询实时的可联率报表
     *
     * @param param
     * @return
     */
    @Override
    public List<TLcReportCaseContact> selectCaseContactList(Map<String, Object> param) {
        return this.tLcReportCaseContactMapper.selectCaseContactList(param);
    }
}
