package com.ruoyi.assetspackage.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * 【请填写功能名称】对象 t_lc_import_flow
 *
 * @author liurunkai
 * @date 2020-03-24
 */
@Data
@Accessors(chain = true)
public class TLcImportFlow extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 导入批次号，年月日时分秒生成
     */
    @Excel(name = "导入批次号，年月日时分秒生成")
    private String importBatchNo;

    /**
     * 委托方id
     */
    @Excel(name = "委托方id")
    private String orgId;

    /**
     * 委托方名称
     */
    @Excel(name = "委托方名称")
    private String orgName;

    /**
     * 总金额
     */
    @Excel(name = "总金额")
    private BigDecimal totalMoney;

    /**
     * 总笔数
     */
    @Excel(name = "总笔数")
    private Integer totalNum;

    /**
     * 导入类型，资产导入 还款导入
     */
    @Excel(name = "导入类型，资产导入 还款导入")
    private Integer importType;

    /**
     * 操作人
     */
    @Excel(name = "操作人")
    private String optionUser;

    private Date startCreateTime;
    private Date endCreateTime;

    /**
     * 拥有查看数据权限的部门集合
     */
    private Set<Long> deptIds;
}
