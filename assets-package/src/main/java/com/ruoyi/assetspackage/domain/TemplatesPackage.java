package com.ruoyi.assetspackage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 模板管理对象 templates_package
 * 
 * @author guozeqi
 * @date 2020-01-02
 */
public class TemplatesPackage extends BaseEntity
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
    private String headRowNum;

    /** 数据起始行 */
    private String dataRowNum;

    /** 机构id */
    private String orgId;

    /** 机构名称 */
    private String orgName;

    /** 模板类型 */
    private String type;

    /** 删除状态（0，未删除，1已删除） */
    private String delflag;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeadRowNum() {
        return headRowNum;
    }

    public void setHeadRowNum(String headRowNum) {
        this.headRowNum = headRowNum;
    }

    public String getDataRowNum() {
        return dataRowNum;
    }

    public void setDataRowNum(String dataRowNum) {
        this.dataRowNum = dataRowNum;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("url", getUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
