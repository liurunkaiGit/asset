package com.ruoyi.custom.service;

import com.ruoyi.custom.domain.TLcCustJob;

import java.util.List;

/**
 * 客户工作单位信息Service接口
 *
 * @author liurunkai
 * @date 2019-12-26
 */
public interface ITLcCustJobService {
    /**
     * 查询客户工作单位信息
     *
     * @param id 客户工作单位信息ID
     * @return 客户工作单位信息
     */
    public TLcCustJob selectTLcCustJobById(Long id);

    /**
     * 查询客户工作单位信息列表
     *
     * @param tLcCustJob 客户工作单位信息
     * @return 客户工作单位信息集合
     */
    public List<TLcCustJob> selectTLcCustJobList(TLcCustJob tLcCustJob);

    /**
     * 新增客户工作单位信息
     *
     * @param tLcCustJob 客户工作单位信息
     * @return 结果
     */
    public int insertTLcCustJob(TLcCustJob tLcCustJob);

    /**
     * 修改客户工作单位信息
     *
     * @param tLcCustJob 客户工作单位信息
     * @return 结果
     */
    public int updateTLcCustJob(TLcCustJob tLcCustJob);

    /**
     * 批量删除客户工作单位信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcCustJobByIds(String ids);

    /**
     * 删除客户工作单位信息信息
     *
     * @param id 客户工作单位信息ID
     * @return 结果
     */
    public int deleteTLcCustJobById(Long id);

    TLcCustJob findCustJobByCertificateNo(String certificateNo);

    TLcCustJob findCustJobByCaseNo(String caseNo, String orgId, String importBatchNo);
}
