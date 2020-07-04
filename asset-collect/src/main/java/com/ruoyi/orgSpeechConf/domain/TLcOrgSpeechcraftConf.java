package com.ruoyi.orgSpeechConf.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 t_lc_org_speechcraft_conf
 *
 * @author liurunkai
 * @date 2020-05-18
 */
@Data
public class TLcOrgSpeechcraftConf extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 委托方编码
     */
    @Excel(name = "委托方编码")
    private Long orgId;

    /**
     * 委托方名称
     */
    @Excel(name = "委托方名称")
    private String orgName;

    private String orgIdAndName;

    /**
     * 并发量：每个委托方可配的ai坐席数
     */
    @Excel(name = "并发量：每个委托方可配的ai坐席数")
    private Integer concurrentValue;

    /**
     * 机器人话术id，多个之间逗号分隔
     */
    @Excel(name = "机器人话术id，多个之间逗号分隔")
    private String speechcraftId;

    /**
     * 话术名称，多个之间逗号分隔
     */
    @Excel(name = "话术名称，多个之间逗号分隔")
    private String speechcraftName;

    /**
     * 场景 id
     */
    private String sceneDefId;

    private String robotDefIdAndName;

    /**
     * 是否删除，1：是2：否
     */
    private Integer delFlag;

    /**
     * 呼叫开始时间
     */
    @Excel(name = "呼叫开始时间")
    private String startCallTime;

    /**
     * 呼叫结束时间
     */
    @Excel(name = "呼叫结束时间")
    private String endCallTime;

    /**
     * 公司id
     */
    @Excel(name = "公司id")
    private Integer companyId;

    /**
     * 话术变量
     */
    @Excel(name = "话术变量")
    private String speechcraftVariable;
}
