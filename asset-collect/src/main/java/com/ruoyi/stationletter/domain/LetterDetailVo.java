package com.ruoyi.stationletter.domain;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/11/6 9:51
 */
@Data
public class LetterDetailVo {

    /**
     * 未读数量
     */
    private Long waitReadNum;

    private List<TLcStationLetterAgent> letterList;
}
