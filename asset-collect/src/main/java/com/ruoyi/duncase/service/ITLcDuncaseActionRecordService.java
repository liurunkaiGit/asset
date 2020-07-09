package com.ruoyi.duncase.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.duncase.domain.TLcDuncaseActionRecord;
import com.ruoyi.utils.Response;

import java.util.List;

/**
 * 案件行动码记录Service接口
 *
 * @author liurunkai
 * @date 2020-01-04
 */
public interface ITLcDuncaseActionRecordService {
    /**
     * 查询案件行动码记录
     *
     * @param id 案件行动码记录ID
     * @return 案件行动码记录
     */
    public TLcDuncaseActionRecord selectTLcDuncaseActionRecordById(Long id);

    /**
     * 查询案件行动码记录列表
     *
     * @param tLcDuncaseActionRecord 案件行动码记录
     * @return 案件行动码记录集合
     */
    public List<TLcDuncaseActionRecord> selectTLcDuncaseActionRecordList(TLcDuncaseActionRecord tLcDuncaseActionRecord);

    public List<TLcDuncaseActionRecord> selectTLcHisDuncaseActionRecordList(TLcDuncaseActionRecord tLcDuncaseActionRecord);

    /**
     * 新增案件行动码记录
     *
     * @param tLcDuncaseActionRecord 案件行动码记录
     * @return 结果
     */
    public int insertTLcDuncaseActionRecord(TLcDuncaseActionRecord tLcDuncaseActionRecord, String orgId, String importBatchNo);

    /**
     * 修改案件行动码记录
     *
     * @param tLcDuncaseActionRecord 案件行动码记录
     * @return 结果
     */
    public int updateTLcDuncaseActionRecord(TLcDuncaseActionRecord tLcDuncaseActionRecord);

    /**
     * 批量删除案件行动码记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcDuncaseActionRecordByIds(String ids);

    /**
     * 删除案件行动码记录信息
     *
     * @param id 案件行动码记录ID
     * @return 结果
     */
    public int deleteTLcDuncaseActionRecordById(Long id);

    /**
     * 批量插入行动历史
     *
     * @param taskIds
     * @param actionCode
     * @return
     */
    AjaxResult batchAddActionCode(String taskIds, String actionCode);

    TLcDuncaseActionRecord selectTLcDuncaseActionRecordByTaskId(Long id);
}
