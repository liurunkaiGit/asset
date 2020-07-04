package com.ruoyi.radioQc.mapper;

import com.ruoyi.radioQc.domain.TLcSendRadioQcRecord;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author liurunkai
 * @date 2020-03-10
 */
public interface TLcSendRadioQcRecordMapper {
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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLcSendRadioQcRecordById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcSendRadioQcRecordByIds(String[] ids);
}
