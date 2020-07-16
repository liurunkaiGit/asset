package com.ruoyi.duncase.service;

import com.ruoyi.assetspackage.domain.score.TLcScore;
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
public interface ITLcDuncaseService {
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
     * 接收资产包传过来的案件信息及用户信息
     *
     * @param assetsPackageList
     */
    Response acceptDuncase(List<Assets> assetsPackageList);

    Response acceptDuncaseUpdate(List<Assets> assetsPackageList);

    /**
     * 根据机构案件号查询对应的案件
     *
     *
     * @param caseNo
     * @param orgId
     * @return
     */
    TLcDuncase findDuncaseByCaseNo(String caseNo, String orgId);

    /**
     * 根据案件编号、所属机构和导入批次号查询案件信息
     *
     * @param caseNo
     * @param importBatchNo
     * @return
     */
    TLcDuncase findDuncaseByCaseNoAndImportBatchNo(String caseNo, String orgId, String importBatchNo);

    /**
     * 根据时间只查询 案件表--定时任务同步数据中心 用
     * 2020-06-24 封志涛添加
     * @return
     */
    List<Map<String,Object>> selectDuncaseByTime(Date createTime, Date modifyTime, int pageNum, int pageSize);
    /**
     * 查询案件表总数--定时任务同步数据中心 用
     * 2020-06-29 封志涛添加
     * @return
     */
    Integer selectDuncaseCount(Date createTime, Date modifyTime);

    public void updateScore(List<TLcScore> TLcScoreList);

}
