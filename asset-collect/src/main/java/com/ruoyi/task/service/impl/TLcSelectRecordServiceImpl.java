package com.ruoyi.task.service.impl;

import com.ruoyi.task.domain.TLcSelectRecord;
import com.ruoyi.task.mapper.TLcSelectRecordMapper;
import com.ruoyi.task.service.ITLcSelectRecordService;
import com.ruoyi.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查找记录Service业务层处理
 *
 * @author fengzhitao
 * @date 2020-06-10
 */
@Slf4j
@Service
public class TLcSelectRecordServiceImpl implements ITLcSelectRecordService {

    private static final String AUTO_SEND_QUALITY_CHECK = "auto_send_quality_check";

    @Autowired
    private TLcSelectRecordMapper tLcSelectRecordMapper;


    /**
     * 新增 查找记录
     *
     * @param tLcSelectRecord 查找记录
     * @return 结果
     */
    @Override
    public Response insertTLcCallRecord(TLcSelectRecord tLcSelectRecord) {
        this.tLcSelectRecordMapper.insertTLcSelectRecord(tLcSelectRecord);
        return Response.success(tLcSelectRecord.getId());
    }

    @Override
    public List<TLcSelectRecord> findSelectRecordByCaseNo(String caseNo) {
        return this.tLcSelectRecordMapper.findSelectRecordByCaseNo(caseNo);
    }

    @Override
    public List<TLcSelectRecord> findHisSelectRecordByCaseNo(String caseNo) {
        return this.tLcSelectRecordMapper.findHisSelectRecordByCaseNo(caseNo);
    }

}
