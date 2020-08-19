package com.ruoyi.inforeporting.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
/**
 * @Description: 上报信息设置
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Data
public class TLcInforeportingSet extends BaseEntity {
    private static final long serialVersionUID = 16465561616L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 部门id
     */
    private Long orgId;
    /**
     * 部门名称
     */
    private String orgName;
    /**
     * 业务类型
     */
    private Integer reportingType;
    /**
     * 业务类型名称
     */
    private String reportingTypeName;
    /**
     * 原数据字段
     */
    private String fromColumn;
    /**
     * 前台显示名称
     */
    private String fromColumnName;
    /**
     * 是否显示 1=是 0=否
     */
    private Integer showStatus;
    /**
     * 是否可编辑 1=是 0=否
     */
    private Integer editStatus;
    /**
     * 排序字段
     */
    private Integer customSorts;
    /**
     * 字段长度限制0没有限制
     */
    private Integer columnLength;

}
