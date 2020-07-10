package com.ruoyi.task.service;

import com.ruoyi.robot.domain.CallContent;
import com.ruoyi.task.domain.TLcCallRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通话结果记录Service接口
 *
 * @author liurunkai
 * @date 2019-12-31
 */
public interface ITLcCallRecordService {
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
     * 批量删除通话结果记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcCallRecordByIds(String ids);

    /**
     * 删除通话结果记录信息
     *
     * @param id 通话结果记录ID
     * @return 结果
     */
    public int deleteTLcCallRecordById(Long id);

    /**
     * 根据客户证件号码查询电催记录
     *
     * @param certificateNo
     * @return
     */
    List<TLcCallRecord> findCallRecordByCertificateNo(String certificateNo);

    /**
     * 从平安下载通话录音
     *
     * @param request
     * @param response
     * @param id
     */
    void downLoadRadio(HttpServletRequest request, HttpServletResponse response, String id);

    /**
     * 根据案件编号查询案件历史轨迹
     *
     * @param caseNo
     * @return
     */
    List<TLcCallRecord> findCallRecordByCaseNo(String caseNo);

    List<TLcCallRecord> findHisDuncaseCallRecordByCaseNo(String caseNo);

    List<CallContent> viewCallContent(String id);

    public void downRecord(HttpServletRequest request, HttpServletResponse response, String ids);

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
     * @return
     */
    List<Map<String,Object>> selectCallRecordByTime(Date createTime, int pageNum, int pageSize);
    /**
     * 查询 录音表 总数--定时任务同步数据中心 用
     * 2020-06-29 封志涛添加
     * @return
     */
    Integer selectCallRecordCount(Date createTime);
}
