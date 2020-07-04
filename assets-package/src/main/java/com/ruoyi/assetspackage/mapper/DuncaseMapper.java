package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.Duncase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 案件Mapper接口
 *
 * @author liurunkai
 * @date 2019-12-25
 */
public interface DuncaseMapper {
    /**
     * 查询案件
     *
     * @param id 案件ID
     * @return 案件
     */
    public Duncase selectTLcDuncaseById(Long id);

    /**
     * 查询案件列表
     *
     * @param tLcDuncase 案件
     * @return 案件集合
     */
    public List<Duncase> selectTLcDuncaseList(Duncase tLcDuncase);

    /**
     * 查询案件列表
     *
     * @param tLcDuncase 案件
     * @return 案件集合
     */
    public List<Duncase> selectTLcDuncaseByPage(Duncase tLcDuncase);

    /**
     * 新增案件
     *
     * @param tLcDuncase 案件
     * @return 结果
     */
    public int insertTLcDuncase(Duncase tLcDuncase);

    /**
     * 修改案件
     *
     * @param tLcDuncase 案件
     * @return 结果
     */
    public int updateTLcDuncase(Duncase tLcDuncase);

    /**
     * 根据案件号查询对应的案件信息
     *
     * @param caseNo
     * @return
     */
    Duncase findDuncaseByCaseNo(@Param("caseNo") String caseNo, @Param("orgId") String orgId);

    /**
     * 根据案件号、机构号批量修改案件信息
     *
     * @param duncaseUpdateList
     */
    void batchUpdateDuncase(@Param("duncaseUpdateList") List<Duncase> duncaseUpdateList);

    /**
     * 批量插入案件信息
     *
     * @param duncaseInsertList
     */
    void batchInsertDuncase(@Param("duncaseInsertList") List<Duncase> duncaseInsertList);

    /**
     * 根据机构号查询机构名称
     *
     * @param orgId
     * @return
     */
    String selectOrgNameByOrgId(@Param("orgId") String orgId);

    /**
     * 根据案件编号、所属机构和导入批次号查询案件信息
     *
     * @param caseNo
     * @param importBatchNo
     * @return
     */
    Duncase findDuncaseByCaseNoAndImportBatchNo(@Param("caseNo") String caseNo, @Param("orgId") String orgId, @Param("importBatchNo") String importBatchNo);
}
