package com.ruoyi.radioQc.service;

import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.radioQc.domain.TLcSendRadioQcRecord;
import com.ruoyi.task.domain.TLcCallRecord;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author liurunkai
 * @date 2020-03-10
 */
public interface ITLcSendRadioQcRecordService {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TLcSendRadioQcRecord selectTLcSendRadioQcRecordById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcSendRadioQcRecord 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLcSendRadioQcRecord> selectTLcSendRadioQcRecordList(TLcSendRadioQcRecord tLcSendRadioQcRecord);

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcSendRadioQcRecord 【请填写功能名称】
     * @return 结果
     */
    public int insertTLcSendRadioQcRecord(TLcSendRadioQcRecord tLcSendRadioQcRecord);

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcSendRadioQcRecord 【请填写功能名称】
     * @return 结果
     */
    public int updateTLcSendRadioQcRecord(TLcSendRadioQcRecord tLcSendRadioQcRecord);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcSendRadioQcRecordByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLcSendRadioQcRecordById(Long id);

    /**
     * 异步将录音文件推送到录音质检系统
     *
     * @param tLcCallRecord
     */
    void sendRadioToQualityCheck(TLcCallRecord tLcCallRecord, String jobNo,String loginName);
}
