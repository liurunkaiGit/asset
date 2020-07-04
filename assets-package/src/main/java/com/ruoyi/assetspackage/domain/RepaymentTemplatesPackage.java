package com.ruoyi.assetspackage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 还款模板管理对象 repayment_templates_package
 * 
 * @author guozeqi
 * @date 2020-01-13
 */
public class RepaymentTemplatesPackage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String name;

    /** 模板URL */
    @Excel(name = "模板URL")
    private String url;

    /** 表头行 */
    @Excel(name = "表头行")
    private String headRowNum;

    /** 数据起始行 */
    @Excel(name = "数据起始行")
    private String dataRowNum;

    /** 机构id */
    @Excel(name = "机构id")
    private String orgId;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setHeadRowNum(String headRowNum) 
    {
        this.headRowNum = headRowNum;
    }

    public String getHeadRowNum() 
    {
        return headRowNum;
    }
    public void setDataRowNum(String dataRowNum) 
    {
        this.dataRowNum = dataRowNum;
    }

    public String getDataRowNum() 
    {
        return dataRowNum;
    }
    public void setOrgId(String orgId) 
    {
        this.orgId = orgId;
    }

    public String getOrgId() 
    {
        return orgId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("url", getUrl())
            .append("headRowNum", getHeadRowNum())
            .append("dataRowNum", getDataRowNum())
            .append("orgId", getOrgId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
