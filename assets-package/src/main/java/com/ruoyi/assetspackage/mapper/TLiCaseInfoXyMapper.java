package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.TLiCaseInfoXy;

import java.util.List;

public interface TLiCaseInfoXyMapper {
    /**
     * 查询兴业案件
     *
     * @param tuid 兴业案件ID
     * @return 兴业案件
     */
    public TLiCaseInfoXy selectTLiCaseInfoXyById(String tuid);

    /**
     * 查询兴业案件列表
     *
     * @param tLiCaseInfoXy 兴业案件
     * @return 兴业案件集合
     */
    public List<TLiCaseInfoXy> selectTLiCaseInfoXyList(TLiCaseInfoXy tLiCaseInfoXy);

    /**
     * 新增兴业案件
     *
     * @param tLiCaseInfoXy 兴业案件
     * @return 结果
     */
    public int insertTLiCaseInfoXy(TLiCaseInfoXy tLiCaseInfoXy);

    /**
     * 修改兴业案件
     *
     * @param tLiCaseInfoXy 兴业案件
     * @return 结果
     */
    public int updateTLiCaseInfoXy(TLiCaseInfoXy tLiCaseInfoXy);

    /**
     * 删除兴业案件
     *
     * @param tuid 兴业案件ID
     * @return 结果
     */
    public int deleteTLiCaseInfoXyById(String tuid);

    /**
     * 批量删除兴业案件
     *
     * @param tuids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLiCaseInfoXyByIds(String[] tuids);
}
