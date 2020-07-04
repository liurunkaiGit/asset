package com.ruoyi.custom.service;

import com.ruoyi.custom.domain.AllCustContact;
import com.ruoyi.custom.domain.TLcCustContact;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户联系人信息Service接口
 *
 * @author liurunkai
 * @date 2019-12-26
 */
public interface ITLcCustContactService {
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
     * 批量删除客户联系人信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcCustContactByIds(String ids);

    /**
     * 删除客户联系人信息信息
     *
     * @param id 客户联系人信息ID
     * @return 结果
     */
    public int deleteTLcCustContactById(Long id);

    /**
     * 查询客户联系人
     *
     * @param certificateNo
     * @return
     */
    List<TLcCustContact> findCustContactByCertificateNo(String certificateNo);

    List<TLcCustContact> findCustContactByCaseNo(String caseNo);

    List<TLcCustContact> findAllCustContactByCaseNo(String caseNo, String orgId, String importBatchNo);

    public int updateIsClose(Map<String,String> param);

    /**
     * 根据时间只查询客户联系人信息表--定时任务同步数据中心 用
     * 2020-06-23 封志涛添加
     * @return
     */
    List<Map<String,Object>> selectCustContactByTime(Date createTime, int pageNum, int pageSize);
    /**
     * 查询 客户联系人信息表 总数--定时任务同步数据中心 用
     * 2020-06-29 封志涛添加
     * @return
     */
    Integer selectCustContactCount(Date createTime);
}
