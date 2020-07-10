package com.ruoyi.duncase.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.duncase.domain.TLcDuncase;
import com.ruoyi.duncase.mapper.TLcHisDuncaseMapper;
import com.ruoyi.duncase.service.ITLcHisDuncaseService;
import com.ruoyi.framework.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 案件Service业务层处理
 *
 * @author liurunkai
 * @date 2019-12-25
 */
@Slf4j
@Service
public class TLcHisDuncaseServiceImpl implements ITLcHisDuncaseService {

    @Autowired
    private TLcHisDuncaseMapper hisDuncaseMapper;

    /**
     * 查询案件
     *
     * @param id 案件ID
     * @return 案件
     */
    @Override
    public TLcDuncase selectTLcDuncaseById(Long id) {
        return hisDuncaseMapper.selectTLcDuncaseById(id);
    }

    /**
     * 查询案件列表
     *
     * @param tLcDuncase 案件
     * @return 案件
     */
    @Override
    public List<TLcDuncase> selectTLcDuncaseList(TLcDuncase tLcDuncase) {
        tLcDuncase.setDeptIds(ShiroUtils.getSysUser().getDeptIds());
        if (tLcDuncase.getEndRecentlyAllotDate() != null) {
            tLcDuncase.setEndRecentlyAllotDate(DateUtils.getEndOfDay(tLcDuncase.getEndRecentlyAllotDate()));
        }
        if (tLcDuncase.getEndRecentlyFollowUpDate() != null) {
            tLcDuncase.setEndRecentlyFollowUpDate(DateUtils.getEndOfDay(tLcDuncase.getEndRecentlyFollowUpDate()));
        }
        return hisDuncaseMapper.selectTLcDuncaseList(tLcDuncase);
    }

    /**
     * 查询案件列表
     *
     * @param tLcDuncase 案件
     * @return 案件
     */
    @Override
    public List<TLcDuncase> selectTLcDuncaseByPage(TLcDuncase tLcDuncase) {
        return hisDuncaseMapper.selectTLcDuncaseByPage(tLcDuncase);
    }

    @Override
    public TLcDuncase findDuncaseByCaseNoAndImportBatchNo(String caseNo, String orgId, String importBatchNo) {
        return this.hisDuncaseMapper.findDuncaseByCaseNoAndImportBatchNo(caseNo, orgId, importBatchNo);
    }

}
