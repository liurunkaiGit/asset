package com.ruoyi.custom.service;

import com.ruoyi.custom.domain.TLcCustinfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户信息Service接口
 *
 * @author liurunkai
 * @date 2019-12-26
 */
public interface ITLcCustinfoService {
    /**
     * 查询客户信息
     *
     * @param id 客户信息ID
     * @return 客户信息
     */
    public TLcCustinfo selectTLcCustinfoById(Long id);

    /**
     * 查询客户信息列表
     *
     * @param tLcCustinfo 客户信息
     * @return 客户信息集合
     */
    public List<TLcCustinfo> selectTLcCustinfoList(TLcCustinfo tLcCustinfo);

    /**
     * 新增客户信息
     *
     * @param tLcCustinfo 客户信息
     * @return 结果
     */
    public int insertTLcCustinfo(TLcCustinfo tLcCustinfo);

    /**
     * 修改客户信息
     *
     * @param tLcCustinfo 客户信息
     * @return 结果
     */
    public int updateTLcCustinfo(TLcCustinfo tLcCustinfo);

    /**
     * 批量删除客户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcCustinfoByIds(String ids);

    /**
     * 删除客户信息信息
     *
     * @param id 客户信息ID
     * @return 结果
     */
    public int deleteTLcCustinfoById(Long id);

    /**
     * 通过身份证号查询客户基本信息
     *
     * @param certificateNo
     * @return
     */
    TLcCustinfo findCustByCertificateNo(String certificateNo);

    TLcCustinfo findCustByCaseNo(String caseNo, String orgId, String importBatchNo);
    /**
     * 根据时间只查询 客户信息表--定时任务同步数据中心 用
     * 2020-06-24 封志涛添加
     * @return
     */
    List<Map<String,Object>> selectCustinfoByTime(Date createTime, Date modifyTime, int pageNum, int pageSize);
    /**
     * 查询 客户信息表 总数--定时任务同步数据中心 用
     * 2020-06-29 封志涛添加
     * @return
     */
    Integer selectCustinfoCount(Date createTime, Date modifyTime);
}
