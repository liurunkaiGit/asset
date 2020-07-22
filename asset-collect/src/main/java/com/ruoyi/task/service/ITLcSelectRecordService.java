package com.ruoyi.task.service;

import com.ruoyi.task.domain.TLcSelectRecord;
import com.ruoyi.utils.Response;

import java.util.List;

/**
 * 查找记录Service接口
 *
 * @author fengzhitao
 * @date 2020-06-10
 */
public interface ITLcSelectRecordService {

    /**
     * 新增 查找记录
     *
     * @param tLcSelectRecord 查找记录
     * @return 结果
     */
    public Response insertTLcCallRecord(TLcSelectRecord tLcSelectRecord);


    /**
     * 根据案件编号查询 查找记录
     *
     * @param caseNo
     * @return
     */
    List<TLcSelectRecord> findSelectRecordByCaseNo(String caseNo);

    List<TLcSelectRecord> findHisSelectRecordByCaseNo(String caseNo);
}
