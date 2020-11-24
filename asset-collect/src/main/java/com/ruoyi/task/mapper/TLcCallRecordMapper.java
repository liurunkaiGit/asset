package com.ruoyi.task.mapper;

import com.ruoyi.task.domain.JxphCallRecord;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcCallRecordForXY;
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
public interface TLcCallRecordMapper {
    /**
     * 查询通话结果记录
     *
     * @param id 通话结果记录ID
     * @return 通话结果记录
     */
    public TLcCallRecord selectTLcCallRecordById(Long id);

    /**
     * 查询通话结果记录列表
     *
     * @param tLcCallRecord 通话结果记录
     * @return 通话结果记录集合
     */
    public List<TLcCallRecord> selectTLcCallRecordList(TLcCallRecord tLcCallRecord);

    public List<TLcCallRecord> selectTLcCallRecordXYList(TLcCallRecord tLcCallRecord);

    public Integer selectTLcCallRecordCount(TLcCallRecord tLcCallRecord);
    public TlcCallRecordTotal selectUserTotal(TLcCallRecord tLcCallRecord);
    /**
     * 新增通话结果记录
     *
     * @param tLcCallRecord 通话结果记录
     * @return 结果
     */
    public void insertTLcCallRecord(TLcCallRecord tLcCallRecord);

    /**
     * 修改通话结果记录
     *
     * @param tLcCallRecord 通话结果记录
     * @return 结果
     */
    public int updateTLcCallRecord(TLcCallRecord tLcCallRecord);

    /**
     * 删除通话结果记录
     *
     * @param id 通话结果记录ID
     * @return 结果
     */
    public int deleteTLcCallRecordById(Long id);

    /**
     * 批量删除通话结果记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcCallRecordByIds(String[] ids);

    /**
     * 根据客户证件号码查询电催记录
     *
     * @param certificateNo
     * @return
     */
    List<TLcCallRecord> findCallRecordByCertificateNo(@Param("certificateNo") String certificateNo);

    /**
     * 根据案件编号查询电催记录
     *
     * @param caseNo
     * @return
     */
    List<TLcCallRecord> findCallRecordByCaseNo(@Param("caseNo") String caseNo);

    List<TLcCallRecord> findHisDuncaseCallRecordByCaseNo(@Param("caseNo") String caseNo);


    public List<TLcCallRecord> findListenCallRecord(TLcCallRecord tLcCallRecord);

    public int updateStar(TLcCallRecord tLcCallRecord);

    /**
     * 定时任务
     * @param tLcCallRecord
     * @return
     */
    public List<TLcCallRecord> findTLcCallRecordListByDate(TLcCallRecord tLcCallRecord);

    /**
     * 根据时间只查询录音表--定时任务同步数据中心 用
     * 2020-06-23 封志涛添加
     * 参数命名为 pageNum，pageSize，会自动触发 PageHelper，然后系统会自动给查询语句追加limit语句
     * @return
     */
    List<Map<String,Object>> selectCallRecordByTime(@Param("createTime") Date createTime, @Param("pnum")int pnum, @Param("psize")int psize);
    /**
     * 查询录音表总数--定时任务同步数据中心 用
     * 2020-06-29 封志涛添加
     * @return
     */
    Integer selectCallRecordCount(@Param("createTime") Date createTime);

    TLcCallRecord selectTLcHisCallRecordById(@Param("id") Long id);

    List<JxphCallRecord> selectJxphCallRecord(Map<String, Object> param);

    List<TLcCallRecord> findZjCallRecordListByDate(TLcCallRecord tLcCallRecord);

    Long selectCountByTimePeriod(Map<String, Object> param);

    List<TLcCallRecord> selectCallRecordListByOrgIdAndTime(TLcCallRecord callRecord);
}
