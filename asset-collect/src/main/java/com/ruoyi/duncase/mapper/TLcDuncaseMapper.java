package com.ruoyi.duncase.mapper;

import com.ruoyi.assetspackage.domain.score.TLcScore;
import com.ruoyi.duncase.domain.TLcDuncase;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 案件Mapper接口
 *
 * @author liurunkai
 * @date 2019-12-25
 */
public interface TLcDuncaseMapper {
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
     * 新增案件
     *
     * @param tLcDuncase 案件
     * @return 结果
     */
    public int insertTLcDuncase(TLcDuncase tLcDuncase);

    /**
     * 修改案件
     *
     * @param tLcDuncase 案件
     * @return 结果
     */
    public int updateTLcDuncase(TLcDuncase tLcDuncase);

    /**
     * 根据案件号查询对应的案件信息
     *
     * @param caseNo
     * @return
     */
    TLcDuncase findDuncaseByCaseNo(@Param("caseNo") String caseNo, @Param("orgId") String orgId);

    /**
     * 根据案件号、机构号批量修改案件信息
     *
     * @param duncaseUpdateList
     */
    void batchUpdateDuncase(@Param("duncaseUpdateList") List<TLcDuncase> duncaseUpdateList);

    /**
     * 批量插入案件信息
     *
     * @param duncaseInsertList
     */
    void batchInsertDuncase(@Param("duncaseInsertList") List<TLcDuncase> duncaseInsertList);

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
    TLcDuncase findDuncaseByCaseNoAndImportBatchNo(@Param("caseNo") String caseNo, @Param("orgId") String orgId, @Param("importBatchNo") String importBatchNo);

    /**
     * 根据时间只查询 案件表--定时任务同步数据中心 用
     * 2020-06-24 封志涛添加
     * 参数命名为 pageNum，pageSize，会自动触发 PageHelper，然后系统会自动给查询语句追加limit语句
     * @return
     */
    List<Map<String,Object>> selectDuncaseByTime(@Param("createTime") Date createTime, @Param("modifyTime")Date modifyTime, @Param("pnum")int pnum, @Param("psize")int psize);
    /**
     * 查询案件表总数--定时任务同步数据中心 用
     * 2020-06-29 封志涛添加
     * @return
     */
    Integer selectDuncaseCount(@Param("createTime") Date createTime, @Param("modifyTime")Date modifyTime);

    public void updateScore(@Param(value="TLcScoreList")List<TLcScore> TLcScoreList);
}
