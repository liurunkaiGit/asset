package com.ruoyi.columnQuery.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 字段查询配置对象 t_lc_column_query
 *
 * @author liurunkai
 * @date 2020-05-22
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TLcColumnQuery extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 委托方编码
     */
    private Long orgId;

    /**
     * 委托方名称
     */
    @Excel(name = "委托方")
    private String orgName;

    /**
     * 委托方名称
     */
    @Excel(name = "表名")
    private String tableName;

    @Excel(name = "表简称")
    private String tablePrefix;

    /**
     * 字段名称
     */
    @Excel(name = "字段名称")
    private String columnName;

    /**
     * 字段中文名
     */
    @Excel(name = "字段中文名")
    private String columnNameCn;

    /**
     * 字段类型
     */
    @Excel(name = "字段类型")
    private String columnType;

    /**
     * 实体类字段
     */
    @Excel(name = "实体类字段")
    private String beanName;

    /**
     * 字段值，当选择下拉框时，让用户填入值
     */
    @Excel(name = "字段值")
    private String columnValue;

    /**
     * 修改人
     */
    private Long modifyBy;

    /**
     * 修改时间
     */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    private String orgIdAndName;
    private String tableNameAndComment;
    private String columnNameAndComment;
    private String niObj;
    private String diObj;
    private String seObj;
    private String slObj;
    private String sObj;

//    private NiObj niObj;
//
//    @Data
//    public static class NiObj{
//        private String type;
//        private List<String> value;
//    }

}
