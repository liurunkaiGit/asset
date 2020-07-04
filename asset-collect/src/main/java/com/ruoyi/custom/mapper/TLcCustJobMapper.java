package com.ruoyi.custom.mapper;

import com.ruoyi.custom.domain.TLcCustJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户工作单位信息Mapper接口
 *
 * @author liurunkai
 * @date 2019-12-26
 */
public interface TLcCustJobMapper {
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
     * 删除客户工作单位信息
     *
     * @param id 客户工作单位信息ID
     * @return 结果
     */
    public int deleteTLcCustJobById(Long id);

    /**
     * 批量删除客户工作单位信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcCustJobByIds(String[] ids);

    /**
     * 批量修改客户工作信息
     *
     * @param jobUpdateList
     */
    void batchUpdateCustJob(@Param("jobUpdateList") List<TLcCustJob> jobUpdateList);

    /**
     * 批量插入客户工作信息
     *
     * @param jobInsertList
     */
    void batchInsertCustJob(@Param("jobInsertList") List<TLcCustJob> jobInsertList);

    TLcCustJob findCustJobByCertificateNo(@Param("certificateNo") String certificateNo);

    TLcCustJob findCustJobByCaseNo(@Param("caseNo") String caseNo, @Param("orgId") String orgId, @Param("importBatchNo") String importBatchNo);
}
