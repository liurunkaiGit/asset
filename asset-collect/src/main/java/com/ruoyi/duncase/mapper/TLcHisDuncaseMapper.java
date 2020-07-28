package com.ruoyi.duncase.mapper;

import com.ruoyi.duncase.domain.TLcDuncase;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 案件Mapper接口
 *
 * @author liurunkai
 * @date 2019-12-25
 */
public interface TLcHisDuncaseMapper {
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
    TLcDuncase findDuncaseByCaseNoAndImportBatchNo(@Param("caseNo") String caseNo, @Param("orgId") String orgId, @Param("importBatchNo") String importBatchNo);

    Map<String, BigDecimal> searchHisDuncaseTotalMoney(TLcDuncase tLcDuncase);
}
