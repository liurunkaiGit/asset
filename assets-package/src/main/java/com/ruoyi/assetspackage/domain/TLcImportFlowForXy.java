package com.ruoyi.assetspackage.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author guozeqi
 * @date 2020-07-13
 */
@Data
@Accessors(chain = true)
public class TLcImportFlowForXy extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
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
     * 新增案件数
     */
    private Integer addNum;

    /**
     * 更新案件数
     */
    private Integer modifyNum;

    /**
     * 出催案件数
     */
    private Integer urgeNum;


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
