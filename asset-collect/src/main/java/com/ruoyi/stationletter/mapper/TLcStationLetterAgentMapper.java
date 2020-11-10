package com.ruoyi.stationletter.mapper;

import com.ruoyi.stationletter.domain.TLcStationLetterAgent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 站内信Mapper接口
 *
 * @author liurunkai
 * @date 2020-10-30
 */
public interface TLcStationLetterAgentMapper {
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
     * 删除站内信
     *
     * @param id 站内信ID
     * @return 结果
     */
    public int deleteTLcStationLetterAgentById(Long id);

    /**
     * 批量删除站内信
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcStationLetterAgentByIds(String[] ids);

    void batchInsertTLcStationLetterAgent(@Param("letterAgentList") List<TLcStationLetterAgent> letterAgentList);

    void deleteByLetterId(@Param("letterId") Long letterId);

    List<TLcStationLetterAgent> selectWaitSendLetterAgentList(TLcStationLetterAgent tLcStationLetterAgent);

    void updateReadStatus(@Param("id") Long id);
}
