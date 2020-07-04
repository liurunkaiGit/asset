package com.ruoyi.exonNum.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 t_lc_exon_num
 *
 * @author liurunkai
 * @date 2020-04-21
 */
@Data
public class TLcExonNum extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 外显号码组名称
     */
    @Excel(name = "外显号码组名称")
    private String exonNumGroup;

    /**
     * 外显号码个数
     */
    @Excel(name = "外显号码个数")
    private Integer exonNumCount;

    /**
     * 外显号码，多个号码；分隔
     */
    @Excel(name = "外显号码，多个号码；分隔")
    private String exonNum;

    /**
     * 所属话务平台
     */
    @Excel(name = "所属话务平台")
    private String  callPlatform;

    /**
     * 创建人
     */
    private String createUser;

    private Long orgId;
    private String orgName;

}
