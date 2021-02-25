package com.ruoyi.task.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【联系人增加】对象 t_lc_infoup
 * 
 * @author gaohg
 * @date 2021-02-05
 */
@Data
public class TLcInfoup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    private Long taskInfoId;

    /** 关联任务id */
    @Excel(name = "关联任务id")
    private Long taskId;

    /** 更新类型 */
    @Excel(name = "更新类型")
    private Integer types;

    /** 姓名 */
    @Excel(name = "姓名")
    private String names;

    /** 与本人关系 */
    @Excel(name = "与本人关系")
    private Integer relations;

    /** $column.columnComment */
    @Excel(name = "地址")
    private String contents;
    private String phone;
    private String orgId;
    private String orgName;
    private String importBatchNo;
    private String caseNo;
    private int sign =0;
    private Long createById;
    private Long updateById;
    private Integer cunzai;
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("types", getTypes())
            .append("names", getNames())
            .append("relations", getRelations())
            .append("contents", getContents())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
