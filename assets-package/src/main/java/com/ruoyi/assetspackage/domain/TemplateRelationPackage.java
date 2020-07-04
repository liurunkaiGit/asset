package com.ruoyi.assetspackage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 模板关系对象 template_relation_package
 * 
 * @author guozeqi
 * @date 2020-01-03
 */
public class TemplateRelationPackage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 系统模板名称 */
    @Excel(name = "系统模板名称")
    private String systemTemplateName;

    /** 客户模板名称 */
    @Excel(name = "客户模板名称")
    private String customerTemplateName;

    /** 关联模板表id */
    @Excel(name = "关联模板表id")
    private String templateId;

    /** 委托方id */
    @Excel(name = "委托方id")
    private String orgId;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setSystemTemplateName(String systemTemplateName) 
    {
        this.systemTemplateName = systemTemplateName;
    }

    public String getSystemTemplateName() 
    {
        return systemTemplateName;
    }
    public void setCustomerTemplateName(String customerTemplateName) 
    {
        this.customerTemplateName = customerTemplateName;
    }

    public String getCustomerTemplateName() 
    {
        return customerTemplateName;
    }
    public void setTemplateId(String templateId) 
    {
        this.templateId = templateId;
    }

    public String getTemplateId() 
    {
        return templateId;
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
            .append("systemTemplateName", getSystemTemplateName())
            .append("customerTemplateName", getCustomerTemplateName())
            .append("templateId", getTemplateId())
            .append("orgId", getOrgId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
