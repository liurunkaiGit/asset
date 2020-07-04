package com.ruoyi.assetspackage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文件附件对象 file_accessories_package
 * 
 * @author guozeqi
 * @date 2019-12-27
 */
public class FileAccessoriesPackage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 附件名称 */
    @Excel(name = "附件名称")
    private String fileName;

    /** 附件url */
    @Excel(name = "附件url")
    private String fileUrl;

    /** 关联主键（机构id或资产包id） */
    @Excel(name = "关联主键", readConverterExp = "机=构id或资产包id")
    private String connId;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFileUrl(String fileUrl) 
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() 
    {
        return fileUrl;
    }
    public void setConnId(String connId) 
    {
        this.connId = connId;
    }

    public String getConnId() 
    {
        return connId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileName", getFileName())
            .append("fileUrl", getFileUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("connId", getConnId())
            .toString();
    }
}
