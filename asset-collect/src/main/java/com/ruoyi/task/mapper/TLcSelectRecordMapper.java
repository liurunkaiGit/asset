package com.ruoyi.task.mapper;

import com.ruoyi.task.domain.TLcSelectRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 查找记录Mapper接口
 *
 * @author ruoyi
 * @date 2020-06-10
 */
public interface TLcSelectRecordMapper {


    /**
     * 新增查找记录
     *
     * @param tLcSelectRecord 查找记录
     * @return 结果
     */
    public void insertTLcSelectRecord(TLcSelectRecord tLcSelectRecord);


    /**
     * 根据案件编号查询 查找记录
     *
     * @param caseNo
     * @return
     */
    List<TLcSelectRecord> findSelectRecordByCaseNo(@Param("caseNo") String caseNo);


    List<TLcSelectRecord> findHisSelectRecordByCaseNo(@Param("caseNo") String caseNo);
}
