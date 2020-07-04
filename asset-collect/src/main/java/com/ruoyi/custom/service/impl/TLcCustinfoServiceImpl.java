package com.ruoyi.custom.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.custom.domain.TLcCustinfo;
import com.ruoyi.custom.mapper.TLcCustinfoMapper;
import com.ruoyi.custom.service.ITLcCustinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 客户信息Service业务层处理
 *
 * @author liurunkai
 * @date 2019-12-26
 */
@Service
public class TLcCustinfoServiceImpl implements ITLcCustinfoService {
    @Autowired
    private TLcCustinfoMapper tLcCustinfoMapper;

    /**
     * 查询客户信息
     *
     * @param id 客户信息ID
     * @return 客户信息
     */
    @Override
    public TLcCustinfo selectTLcCustinfoById(Long id) {
        return tLcCustinfoMapper.selectTLcCustinfoById(id);
    }

    /**
     * 查询客户信息列表
     *
     * @param tLcCustinfo 客户信息
     * @return 客户信息
     */
    @Override
    public List<TLcCustinfo> selectTLcCustinfoList(TLcCustinfo tLcCustinfo) {
        return tLcCustinfoMapper.selectTLcCustinfoList(tLcCustinfo);
    }

    /**
     * 新增客户信息
     *
     * @param tLcCustinfo 客户信息
     * @return 结果
     */
    @Override
    public int insertTLcCustinfo(TLcCustinfo tLcCustinfo) {
        tLcCustinfo.setCreateTime(DateUtils.getNowDate());
        return tLcCustinfoMapper.insertTLcCustinfo(tLcCustinfo);
    }

    /**
     * 修改客户信息
     *
     * @param tLcCustinfo 客户信息
     * @return 结果
     */
    @Override
    public int updateTLcCustinfo(TLcCustinfo tLcCustinfo) {
        return tLcCustinfoMapper.updateTLcCustinfo(tLcCustinfo);
    }

    /**
     * 删除客户信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcCustinfoByIds(String ids) {
        return tLcCustinfoMapper.deleteTLcCustinfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户信息信息
     *
     * @param id 客户信息ID
     * @return 结果
     */
    @Override
    public int deleteTLcCustinfoById(Long id) {
        return tLcCustinfoMapper.deleteTLcCustinfoById(id);
    }

    /**
     * 通过身份证号查询客户基本信息
     *
     * @param certificateNo
     * @return
     */
    @Override
    public TLcCustinfo findCustByCertificateNo(String certificateNo) {
        return this.tLcCustinfoMapper.findCustByCertificateNo(certificateNo);
    }

    @Override
    public TLcCustinfo findCustByCaseNo(String caseNo, String orgId, String importBatchNo) {
        return this.tLcCustinfoMapper.findCustByCaseNo(caseNo, orgId, importBatchNo);
    }

    @Override
    public List<Map<String, Object>> selectCustinfoByTime(Date createTime, int pageNum, int pageSize) {
        return this.tLcCustinfoMapper.selectCustinfoByTime(createTime,pageNum,pageSize);
    }

    @Override
    public Integer selectCustinfoCount(Date createTime) {
        return this.tLcCustinfoMapper.selectCustinfoCount(createTime);
    }
}
