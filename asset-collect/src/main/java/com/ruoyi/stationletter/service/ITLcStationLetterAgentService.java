package com.ruoyi.stationletter.service;

import com.ruoyi.stationletter.domain.TLcStationLetterAgent;
import com.ruoyi.utils.Response;

import java.util.List;

/**
 * 站内信Service接口
 *
 * @author liurunkai
 * @date 2020-10-30
 */
public interface ITLcStationLetterAgentService {
    /**
     * 查询站内信
     *
     * @param id 站内信ID
     * @return 站内信
     */
    public TLcStationLetterAgent selectTLcStationLetterAgentById(Long id);

    /**
     * 查询站内信列表
     *
     * @param tLcStationLetterAgent 站内信
     * @return 站内信集合
     */
    public List<TLcStationLetterAgent> selectTLcStationLetterAgentList(TLcStationLetterAgent tLcStationLetterAgent);

    /**
     * 新增站内信
     *
     * @param tLcStationLetterAgent 站内信
     * @return 结果
     */
    public int insertTLcStationLetterAgent(TLcStationLetterAgent tLcStationLetterAgent);

    /**
     * 修改站内信
     *
     * @param tLcStationLetterAgent 站内信
     * @return 结果
     */
    public int updateTLcStationLetterAgent(TLcStationLetterAgent tLcStationLetterAgent);

    /**
     * 批量删除站内信
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcStationLetterAgentByIds(String ids);

    /**
     * 删除站内信信息
     *
     * @param id 站内信ID
     * @return 结果
     */
    public int deleteTLcStationLetterAgentById(Long id);

    Response getStationLetter();

    void batchInsertTLcStationLetterAgent(List<TLcStationLetterAgent> letterAgentList);

    void deleteByLetterId(Long id);

    List<TLcStationLetterAgent> getStationLetterDetail();

    void updateReadStatus(Long id);

    Response getWaitReadNum();
}
