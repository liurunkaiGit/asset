package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.TLiTelInfo;

import java.util.List;

/**
 * 电话导入临时Mapper接口
 * 
 * @author liurunkai
 * @date 2020-05-29
 */
public interface TLiTelInfoMapper 
{
    /**
     * 查询电话导入临时
     * 
     * @param tuid 电话导入临时ID
     * @return 电话导入临时
     */
    public TLiTelInfo selectTLiTelInfoById(String tuid);

    /**
     * 查询电话导入临时列表
     * 
     * @param tLiTelInfo 电话导入临时
     * @return 电话导入临时集合
     */
    public List<TLiTelInfo> selectTLiTelInfoList(TLiTelInfo tLiTelInfo);

    /**
     * 新增电话导入临时
     * 
     * @param tLiTelInfo 电话导入临时
     * @return 结果
     */
    public int insertTLiTelInfo(TLiTelInfo tLiTelInfo);

    /**
     * 修改电话导入临时
     * 
     * @param tLiTelInfo 电话导入临时
     * @return 结果
     */
    public int updateTLiTelInfo(TLiTelInfo tLiTelInfo);

    /**
     * 删除电话导入临时
     * 
     * @param tuid 电话导入临时ID
     * @return 结果
     */
    public int deleteTLiTelInfoById(String tuid);

    /**
     * 批量删除电话导入临时
     * 
     * @param tuids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLiTelInfoByIds(String[] tuids);
}
