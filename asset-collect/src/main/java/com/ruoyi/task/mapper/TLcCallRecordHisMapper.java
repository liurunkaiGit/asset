package com.ruoyi.task.mapper;

import com.ruoyi.task.domain.JxphCallRecord;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcCallRecordHis;
import com.ruoyi.task.domain.TlcCallRecordTotal;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通话结果记录Mapper接口
 *
 * @author ruoyi
 * @date 2019-12-31
 */
public interface TLcCallRecordHisMapper {

    /**
     * 查询通话结果记录列表
     *
     * @param tLcCallRecordHis 通话结果记录
     * @return 通话结果记录集合
     */
    public List<TLcCallRecordHis> selectTLcCallRecordList(TLcCallRecordHis tLcCallRecordHis);

    public List<TLcCallRecordHis> selectTLcCallRecordXYList(TLcCallRecordHis tLcCallRecordHis);
    public List<TLcCallRecordHis> selectTLcCallRecordHisList(TLcCallRecordHis tLcCallRecordHis);

    /**
     * 根据客户证件号码查询电催记录
     *
     * @param certificateNo
     * @return
     */
    List<TLcCallRecordHis> findCallRecordByCertificateNo(@Param("certificateNo") String certificateNo);

    /**
     * 根据案件编号查询电催记录
     *
     * @param caseNo
     * @return
     */
    List<TLcCallRecordHis> findCallRecordByCaseNo(@Param("caseNo") String caseNo);

    List<TLcCallRecordHis> findHisDuncaseCallRecordByCaseNo(@Param("caseNo") String caseNo);

    public List<TLcCallRecordHis> findListenCallRecord(TLcCallRecordHis tLcCallRecordHis);


    /**
     * 定时任务
     * @param tLcCallRecordHis
     * @return
     */
    public List<TLcCallRecordHis> findTLcCallRecordListByDate(TLcCallRecordHis tLcCallRecordHis);

    /**
     * 根据时间只查询录音表--定时任务同步数据中心 用
     * 2020-06-23 封志涛添加
     * 参数命名为 pageNum，pageSize，会自动触发 PageHelper，然后系统会自动给查询语句追加limit语句
     * @return
     */
    List<Map<String,Object>> selectCallRecordByTime(@Param("createTime") Date createTime, @Param("pnum") int pnum, @Param("psize") int psize);


    List<JxphCallRecord> selectJxphCallRecord(Map<String, Object> param);

    List<TLcCallRecordHis> findZjCallRecordListByDate(TLcCallRecordHis tLcCallRecordHis);

    List<TLcCallRecordHis> selectCallRecordListByOrgIdAndTime(TLcCallRecordHis tLcCallRecordHis);
}
