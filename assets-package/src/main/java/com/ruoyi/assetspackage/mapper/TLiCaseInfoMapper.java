package com.ruoyi.assetspackage.mapper;


import com.ruoyi.assetspackage.domain.TLiCaseInfo;

import java.util.List;

/**
 * 案件导入临时Mapper接口
 * 
 * @author liurunkai
 * @date 2020-05-29
 */
public interface TLiCaseInfoMapper 
{
    /**
     * 查询案件导入临时
     * 
     * @param tuid 案件导入临时ID
     * @return 案件导入临时
     */
    public TLiCaseInfo selectTLiCaseInfoById(String tuid);

    /**
     * 查询案件导入临时列表
     * 
     * @param tLiCaseInfo 案件导入临时
     * @return 案件导入临时集合
     */
    public List<TLiCaseInfo> selectTLiCaseInfoList(TLiCaseInfo tLiCaseInfo);

    /**
     * 新增案件导入临时
     * 
     * @param tLiCaseInfo 案件导入临时
     * @return 结果
     */
    public int insertTLiCaseInfo(TLiCaseInfo tLiCaseInfo);

    /**
     * 修改案件导入临时
     * 
     * @param tLiCaseInfo 案件导入临时
     * @return 结果
     */
    public int updateTLiCaseInfo(TLiCaseInfo tLiCaseInfo);

    /**
     * 删除案件导入临时
     * 
     * @param tuid 案件导入临时ID
     * @return 结果
     */
    public int deleteTLiCaseInfoById(String tuid);

    /**
     * 批量删除案件导入临时
     * 
     * @param tuids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLiCaseInfoByIds(String[] tuids);
}
