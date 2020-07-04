package com.ruoyi.assetspackage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资产包与资产关系对象 assets_connect_package
 * 
 * @author guozeqi
 * @date 2020-01-08
 */
public class AssetsConnectPackage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 资产表id */
    @Excel(name = "资产表id")
    private String assetsId;

    /** 资产包表id */
    @Excel(name = "资产包表id")
    private String packageId;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setAssetsId(String assetsId) 
    {
        this.assetsId = assetsId;
    }

    public String getAssetsId() 
    {
        return assetsId;
    }
    public void setPackageId(String packageId) 
    {
        this.packageId = packageId;
    }

    public String getPackageId() 
    {
        return packageId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("assetsId", getAssetsId())
            .append("packageId", getPackageId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
