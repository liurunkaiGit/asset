package com.ruoyi.assetspackage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 资产包与资产关系历史对象 assets_connect_his_package
 * 
 * @author guozeqi
 * @date 2020-01-08
 */
public class AssetsConnectHisPackage extends BaseEntity
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

    /** 原创建人 */
    @Excel(name = "原创建人")
    private String createByHis;

    /** 原创建时间 */
    @Excel(name = "原创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTimeHis;

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
    public void setCreateByHis(String createByHis) 
    {
        this.createByHis = createByHis;
    }

    public String getCreateByHis() 
    {
        return createByHis;
    }
    public void setCreateTimeHis(Date createTimeHis) 
    {
        this.createTimeHis = createTimeHis;
    }

    public Date getCreateTimeHis() 
    {
        return createTimeHis;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("assetsId", getAssetsId())
            .append("packageId", getPackageId())
            .append("createByHis", getCreateByHis())
            .append("createTimeHis", getCreateTimeHis())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
