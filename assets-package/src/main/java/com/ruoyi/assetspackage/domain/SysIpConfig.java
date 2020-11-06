package com.ruoyi.assetspackage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * ip段配置对象 sys_ip_config
 * 
 * @author guozeqi
 * @date 2020-11-02
 */
public class SysIpConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "ID")
    private Long id;

    /** 开始ip */
    @Excel(name = "开始ip")
    private String startIp;

    /** 结束ip */
    @Excel(name = "结束ip")
    private String endIp;

    /** ip段 */
    @Excel(name = "ip段")
    private String partIp;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStartIp(String startIp) 
    {
        this.startIp = startIp;
    }

    public String getStartIp() 
    {
        return startIp;
    }
    public void setEndIp(String endIp) 
    {
        this.endIp = endIp;
    }

    public String getEndIp() 
    {
        return endIp;
    }
    public void setPartIp(String partIp) 
    {
        this.partIp = partIp;
    }

    public String getPartIp() 
    {
        return partIp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("startIp", getStartIp())
            .append("endIp", getEndIp())
            .append("partIp", getPartIp())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
