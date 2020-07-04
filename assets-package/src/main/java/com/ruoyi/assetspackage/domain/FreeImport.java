package com.ruoyi.assetspackage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 自由导入对象 free_import
 * 
 * @author guozeqi
 * @date 2020-06-09
 */
public class FreeImport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键")
    private Long id;

    /** 机构案件号 */
    @Excel(name = "机构案件号")
    private String orgCasno;

    /** null */
    @Excel(name = "null")
    private String orgId;

    /** null */
    @Excel(name = "null")
    private String importBatchNo;

    /** 自由导入数据 */
    @Excel(name = "自由导入数据")
    private String value;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrgCasno(String orgCasno) 
    {
        this.orgCasno = orgCasno;
    }

    public String getOrgCasno() 
    {
        return orgCasno;
    }
    public void setOrgId(String orgId) 
    {
        this.orgId = orgId;
    }

    public String getOrgId() 
    {
        return orgId;
    }
    public void setImportBatchNo(String importBatchNo) 
    {
        this.importBatchNo = importBatchNo;
    }

    public String getImportBatchNo() 
    {
        return importBatchNo;
    }
    public void setValue(String value) 
    {
        this.value = value;
    }

    public String getValue() 
    {
        return value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orgCasno", getOrgCasno())
            .append("orgId", getOrgId())
            .append("importBatchNo", getImportBatchNo())
            .append("value", getValue())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
