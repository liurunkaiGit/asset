package com.ruoyi.custom.mapper;

import com.ruoyi.custom.domain.TLcCustinfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户信息Mapper接口
 *
 * @author liurunkai
 * @date 2019-12-26
 */
public interface TLcCustinfoMapper {
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
     * 删除客户信息
     *
     * @param id 客户信息ID
     * @return 结果
     */
    public int deleteTLcCustinfoById(Long id);

    /**
     * 批量删除客户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcCustinfoByIds(String[] ids);

    /**
     * 批量修改客户信息
     *
     * @param custUpdateList
     */
    void batchUpdateCustinfo(@Param("custUpdateList") List<TLcCustinfo> custUpdateList);

    /**
     * 批量插入客户信息
     *
     * @param custInsertList
     */
    void batchInsertCustinfo(@Param("custInsertList") List<TLcCustinfo> custInsertList);

    /**
     * 通过身份证号查询客户基本信息
     *
     * @param certificateNo
     * @return
     */
    TLcCustinfo findCustByCertificateNo(@Param("certificateNo") String certificateNo);

    TLcCustinfo findCustByCaseNo(@Param("caseNo") String caseNo, @Param("orgId") String orgId, @Param("importBatchNo") String importBatchNo);

    /**
     * 根据时间只查询 客户信息表--定时任务同步数据中心 用
     * 2020-06-24 封志涛添加
     * 参数命名为 pageNum，pageSize，会自动触发 PageHelper，然后系统会自动给查询语句追加limit语句
     * @return
     */
    List<Map<String,Object>> selectCustinfoByTime(@Param("createTime") Date createTime, @Param("modifyTime")Date modifyTime, @Param("pnum")int pnum, @Param("psize")int psize);
    /**
     * 查询客户信息表总数--定时任务同步数据中心 用
     * 2020-06-29 封志涛添加
     * @return
     */
    Integer selectCustinfoCount(@Param("createTime") Date createTime, @Param("modifyTime")Date modifyTime);
}
