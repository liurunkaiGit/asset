package com.ruoyi.stationletter.mapper;

import com.ruoyi.stationletter.domain.TLcStationLetter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 站内信Mapper接口
 *
 * @author liurunkai
 * @date 2020-10-30
 */
public interface TLcStationLetterMapper {
    /**
     * 查询站内信
     *
     * @param id 站内信ID
     * @return 站内信
     */
    public TLcStationLetter selectTLcStationLetterById(Long id);

    /**
     * 查询站内信列表
     *
     * @param tLcStationLetter 站内信
     * @return 站内信集合
     */
    public List<TLcStationLetter> selectTLcStationLetterList(TLcStationLetter tLcStationLetter);

    /**
     * 新增站内信
     *
     * @param tLcStationLetter 站内信
     * @return 结果
     */
    public int insertTLcStationLetter(TLcStationLetter tLcStationLetter);

    /**
     * 修改站内信
     *
     * @param tLcStationLetter 站内信
     * @return 结果
     */
    public int updateTLcStationLetter(TLcStationLetter tLcStationLetter);

    /**
     * 删除站内信
     *
     * @param id 站内信ID
     * @return 结果
     */
    public int deleteTLcStationLetterById(Long id);

    /**
     * 批量删除站内信
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcStationLetterByIds(String[] ids);

    List<TLcStationLetter> selectWaitSendLetter(TLcStationLetter tLcStationLetter);

    void updateLetterSendStatus();
}
