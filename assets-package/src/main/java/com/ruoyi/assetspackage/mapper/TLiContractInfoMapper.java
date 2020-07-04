package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.TLiContractInfo;

import java.util.List;

/**
 * 合同导入临时Mapper接口
 * 
 * @author liurunkai
 * @date 2020-05-29
 */
public interface TLiContractInfoMapper 
{
    /**
     * 查询合同导入临时
     * 
     * @param tuid 合同导入临时ID
     * @return 合同导入临时
     */
    public TLiContractInfo selectTLiContractInfoById(String tuid);

    /**
     * 查询合同导入临时列表
     * 
     * @param tLiContractInfo 合同导入临时
     * @return 合同导入临时集合
     */
    public List<TLiContractInfo> selectTLiContractInfoList(TLiContractInfo tLiContractInfo);

    /**
     * 新增合同导入临时
     * 
     * @param tLiContractInfo 合同导入临时
     * @return 结果
     */
    public int insertTLiContractInfo(TLiContractInfo tLiContractInfo);

    /**
     * 修改合同导入临时
     * 
     * @param tLiContractInfo 合同导入临时
     * @return 结果
     */
    public int updateTLiContractInfo(TLiContractInfo tLiContractInfo);

    /**
     * 删除合同导入临时
     * 
     * @param tuid 合同导入临时ID
     * @return 结果
     */
    public int deleteTLiContractInfoById(String tuid);

    /**
     * 批量删除合同导入临时
     * 
     * @param tuids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLiContractInfoByIds(String[] tuids);
}
