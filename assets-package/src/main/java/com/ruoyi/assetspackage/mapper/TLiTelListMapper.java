package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.TLiTelList;

import java.util.List;

/**
 * 电话记录临时Mapper接口
 * 
 * @author liurunkai
 * @date 2020-05-29
 */
public interface TLiTelListMapper 
{
    /**
     * 查询电话记录临时
     * 
     * @param tuid 电话记录临时ID
     * @return 电话记录临时
     */
    public TLiTelList selectTLiTelListById(String tuid);

    /**
     * 查询电话记录临时列表
     * 
     * @param tLiTelList 电话记录临时
     * @return 电话记录临时集合
     */
    public List<TLiTelList> selectTLiTelListList(TLiTelList tLiTelList);

    /**
     * 新增电话记录临时
     * 
     * @param tLiTelList 电话记录临时
     * @return 结果
     */
    public int insertTLiTelList(TLiTelList tLiTelList);

    /**
     * 修改电话记录临时
     * 
     * @param tLiTelList 电话记录临时
     * @return 结果
     */
    public int updateTLiTelList(TLiTelList tLiTelList);

    /**
     * 删除电话记录临时
     * 
     * @param tuid 电话记录临时ID
     * @return 结果
     */
    public int deleteTLiTelListById(String tuid);

    /**
     * 批量删除电话记录临时
     * 
     * @param tuids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLiTelListByIds(String[] tuids);
}
