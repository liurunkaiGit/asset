package com.ruoyi.robot.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/12 20:51
 */
@Data
public class Robot {
    private Integer robotDefId;
    private String robotName;
    private Integer sceneDefId;
    private List<SceneRecord> sceneRecords;
    private String industryOneName;
    private String industryTwoName;
    private Date gmtModify;
    private boolean flag;
    private String robotDefIdAndName;
}

@Data
class SceneRecord{
    private Integer sceneRecordId;
    private String sceneRecordName;
}