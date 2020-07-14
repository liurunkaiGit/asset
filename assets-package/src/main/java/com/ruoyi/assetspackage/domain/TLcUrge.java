package com.ruoyi.assetspackage.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author guozeqi
 * @date 2020-07-13
 */
@Data
@Accessors(chain = true)
public class TLcUrge extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;


    /**
     * 批次号
     */
    private String importBatchNo;
    /**
     * 委托方id
     */
//    @Excel(name = "委托方id")
    private String orgId;

    /**
     * 委托方名称
     */
    @Excel(name = "委托方")
    private String orgName;

    /**
     * 机构案件号
     */
    @Excel(name = "机构案件号")
    private String orgCasNo;

    /**
     * 客户姓名
     */
    @Excel(name = "姓名")
    private String customeName;

    /**
     * 委案金额
     */
    @Excel(name = "委案金额")
    private BigDecimal waje;

    /**
     * 当前已还金额
     */
    @Excel(name = "当前已还金额")
    private BigDecimal dqyhje;

    /**
     * 业务归属人id
     */
    private Long ownerId;

    /**
     * 业务归属人名称
     */
    @Excel(name = "业务归属人")
    private String ownerName;

    /**
     * 类型
     */
    @Excel(name = "类型", readConverterExp = "1=预测结清,2=部分还款")
    private String type;

    /**
     * 生成时间
     */
    @Excel(name = "生成时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime1;

    private Date startCreateTime;
    private Date endCreateTime;

}
