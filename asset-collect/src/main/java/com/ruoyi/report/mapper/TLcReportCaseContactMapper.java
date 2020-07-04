package com.ruoyi.report.mapper;

import com.ruoyi.report.domain.TLcReportCaseContact;

import java.util.List;
import java.util.Map;

/**
 * 案件可联率报Mapper接口
 *
 * @author liurunkai
 * @date 2020-04-09
 */
public interface TLcReportCaseContactMapper {
    /**
     * 查询案件可联率报
     *
     * @param id 案件可联率报ID
     * @return 案件可联率报
     */
    public TLcReportCaseContact selectTLcReportCaseContactById(Long id);

    /**
     * 查询案件可联率报列表
     *
     * @param tLcReportCaseContact 案件可联率报
     * @return 案件可联率报集合
     */
    public List<TLcReportCaseContact> selectTLcReportCaseContactList(TLcReportCaseContact tLcReportCaseContact);

    /**
     * 新增案件可联率报
     *
     * @param tLcReportCaseContact 案件可联率报
     * @return 结果
     */
    public int insertTLcReportCaseContact(TLcReportCaseContact tLcReportCaseContact);

    /**
     * 修改案件可联率报
     *
     * @param tLcReportCaseContact 案件可联率报
     * @return 结果
     */
    public int updateTLcReportCaseContact(TLcReportCaseContact tLcReportCaseContact);

    /**
     * 删除案件可联率报
     *
     * @param id 案件可联率报ID
     * @return 结果
     */
    public int deleteTLcReportCaseContactById(Long id);

    /**
     * 批量删除案件可联率报
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcReportCaseContactByIds(String[] ids);

    List<TLcReportCaseContact> selectCaseContactList(Map<String, Object> param);
}
