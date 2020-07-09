package com.ruoyi.duncase.service;

import com.ruoyi.duncase.domain.Assets;
import com.ruoyi.duncase.domain.TLcDuncase;
import com.ruoyi.utils.Response;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 案件Service接口
 *
 * @author liurunkai
 * @date 2019-12-25
 */
public interface ITLcHisDuncaseService {
    /**
     * 查询案件
     *
     * @param id 案件ID
     * @return 案件
     */
    public TLcDuncase selectTLcDuncaseById(Long id);

    /**
     * 查询案件列表
     *
     * @param tLcDuncase 案件
     * @return 案件集合
     */
    public List<TLcDuncase> selectTLcDuncaseList(TLcDuncase tLcDuncase);

    /**
     * 查询案件列表
     *
     * @param tLcDuncase 案件
     * @return 案件集合
     */
    public List<TLcDuncase> selectTLcDuncaseByPage(TLcDuncase tLcDuncase);

    /**
     * 根据案件编号、所属机构和导入批次号查询案件信息
     *
     * @param caseNo
     * @param importBatchNo
     * @return
     */
    TLcDuncase findDuncaseByCaseNoAndImportBatchNo(String caseNo, String orgId, String importBatchNo);
}
