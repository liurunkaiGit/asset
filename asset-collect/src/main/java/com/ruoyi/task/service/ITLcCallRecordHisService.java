package com.ruoyi.task.service;

import com.ruoyi.robot.domain.CallContent;
import com.ruoyi.task.domain.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通话结果记录Service接口
 *
 * @author gaohg
 * @date 2021-3-10
 */
public interface ITLcCallRecordHisService {


    public List<TLcCallRecordForXY> selectTLcCallRecordHisListForXY(TLcCallRecordHis tLcCallRecord);
    public List<TLcCallRecordForJX> selectTLcCallRecordListForJX(TLcCallRecordHis tLcCallRecordHis);
    public List<TLcCallRecordForDQ> selectTLcCallRecordListForDQ(TLcCallRecordHis tLcCallRecordHis);
    /**
     * 查询通话结果记录列表
     *
     * @param tLcCallRecord 通话结果记录
     * @return 通话结果记录集合
     */
    public List<TLcCallRecordHis> selectTLcCallRecordHisList(TLcCallRecordHis tLcCallRecord);





//    List<JxphCallRecord> selectJxphCallRecord(Map<String, Object> param);
//
//    List<TLcCallRecordHis> findZjCallRecordListByDate(TLcCallRecordHis tLcCallRecord);
//
//    List<TLcCallRecordForJX> selectTLcCallRecordHisListForJX(TLcCallRecordHis tLcCallRecord);
//
//    List<TLcCallRecordForDQ> selectTLcCallRecordHisListForDQ(TLcCallRecordHis tLcCallRecord);
//
//    List<TLcCallRecordHis> selectCallRecordListByOrgIdAndTime(TLcCallRecordHis callRecord);

}
