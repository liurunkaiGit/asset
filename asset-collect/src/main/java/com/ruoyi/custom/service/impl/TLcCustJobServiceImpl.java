package com.ruoyi.custom.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.custom.domain.TLcCustJob;
import com.ruoyi.custom.mapper.TLcCustJobMapper;
import com.ruoyi.custom.service.ITLcCustJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;


/**
 * 客户工作单位信息Service业务层处理
 *
 * @author liurunkai
 * @date 2019-12-26
 */
@Service
public class TLcCustJobServiceImpl implements ITLcCustJobService {
    @Autowired
    private TLcCustJobMapper tLcCustJobMapper;

    /**
     * 查询客户工作单位信息
     *
     * @param id 客户工作单位信息ID
     * @return 客户工作单位信息
     */
    @Override
    public TLcCustJob selectTLcCustJobById(Long id) {
        return tLcCustJobMapper.selectTLcCustJobById(id);
    }

    /**
     * 查询客户工作单位信息列表
     *
     * @param tLcCustJob 客户工作单位信息
     * @return 客户工作单位信息
     */
    @Override
    public List<TLcCustJob> selectTLcCustJobList(TLcCustJob tLcCustJob) {
        return tLcCustJobMapper.selectTLcCustJobList(tLcCustJob);
    }

    /**
     * 新增客户工作单位信息
     *
     * @param tLcCustJob 客户工作单位信息
     * @return 结果
     */
    @Override
    public int insertTLcCustJob(TLcCustJob tLcCustJob) {
        tLcCustJob.setCreateTime(DateUtils.getNowDate());
        return tLcCustJobMapper.insertTLcCustJob(tLcCustJob);
    }

    /**
     * 修改客户工作单位信息
     *
     * @param tLcCustJob 客户工作单位信息
     * @return 结果
     */
    @Override
    public int updateTLcCustJob(TLcCustJob tLcCustJob) {
        return tLcCustJobMapper.updateTLcCustJob(tLcCustJob);
    }

    /**
     * 删除客户工作单位信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcCustJobByIds(String ids) {
        return tLcCustJobMapper.deleteTLcCustJobByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户工作单位信息信息
     *
     * @param id 客户工作单位信息ID
     * @return 结果
     */
    @Override
    public int deleteTLcCustJobById(Long id) {
        return tLcCustJobMapper.deleteTLcCustJobById(id);
    }

    @Override
    public TLcCustJob findCustJobByCertificateNo(String certificateNo) {
        TLcCustJob tLcCustJob = this.tLcCustJobMapper.findCustJobByCertificateNo(certificateNo);
        return tLcCustJob;
    }

    @Override
    public TLcCustJob findCustJobByCaseNo(String caseNo, String orgId, String importBatchNo) {
        return this.tLcCustJobMapper.findCustJobByCaseNo(caseNo,orgId,importBatchNo);
    }
}
