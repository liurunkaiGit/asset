package com.ruoyi.custom.mapper;

import com.ruoyi.custom.domain.TLcCustContact;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户联系人信息Mapper接口
 *
 * @author liurunkai
 * @date 2019-12-26
 */
public interface TLcCustContactMapper {
    /**
     * 查询客户联系人信息
     *
     * @param id 客户联系人信息ID
     * @return 客户联系人信息
     */
    public TLcCustContact selectTLcCustContactById(Long id);

    /**
     * 查询客户联系人信息列表
     *
     * @param tLcCustContact 客户联系人信息
     * @return 客户联系人信息集合
     */
    public List<TLcCustContact> selectTLcCustContactList(TLcCustContact tLcCustContact);

    /**
     * 新增客户联系人信息
     *
     * @param tLcCustContact 客户联系人信息
     * @return 结果
     */
    public int insertTLcCustContact(TLcCustContact tLcCustContact);

    /**
     * 修改客户联系人信息
     *
     * @param tLcCustContact 客户联系人信息
     * @return 结果
     */
    public int updateTLcCustContact(TLcCustContact tLcCustContact);

    /**
     * 删除客户联系人信息
     *
     * @param id 客户联系人信息ID
     * @return 结果
     */
    public int deleteTLcCustContactById(Long id);

    /**
     * 批量删除客户联系人信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcCustContactByIds(String[] ids);

    /**
     * 批量修改客户联系人信息
     *
     * @param contactUpdateList
     */
    void batchUpdateContact(@Param("contactUpdateList") List<TLcCustContact> contactUpdateList);

    /**
     * 批量插入客户联系人信息
     *
     * @param contactInsertList
     */
    void batchInsertContact(@Param("contactInsertList") List<TLcCustContact> contactInsertList);

    List<TLcCustContact> findCustContactByCertificateNo(@Param("certificateNo") String certificateNo);

    List<TLcCustContact> findCustContactByCaseNo(@Param("caseNo") String caseNo);

    List<TLcCustContact> findAllCustContactByCaseNo(@Param("caseNo") String caseNo, @Param("orgId") String orgId, @Param("importBatchNo") String importBatchNo);

    public int updateIsClose(Map<String,String> param);

    /**
     * 根据时间只查询客户联系人信息表--定时任务同步数据中心 用
     * 2020-06-23 封志涛添加
     * 参数命名为 pageNum，pageSize，会自动触发 PageHelper，然后系统会自动给查询语句追加limit语句
     * @return
     */
    List<Map<String,Object>> selectCustContactByTime(@Param("createTime") Date createTime, @Param("pnum")int pnum, @Param("psize")int psize);
    /**
     * 查询客户联系人信息表总数--定时任务同步数据中心 用
     * 2020-06-29 封志涛添加
     * @return
     */
    Integer selectCustContactCount(@Param("createTime") Date createTime);
}
