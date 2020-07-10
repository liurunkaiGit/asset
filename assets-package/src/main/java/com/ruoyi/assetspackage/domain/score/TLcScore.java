package com.ruoyi.assetspackage.domain.score;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 催收评分对象 t_lc_score
 * 
 * @author guozeqi
 * @date 2020-06-23
 */
public class TLcScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键")
    private Long id;

    /** 机构案件号 */
    @Excel(name = "机构案件号")
    private String orgCasno;

    /** 催收评分 */
    @Excel(name = "催收评分")
    private Long score;

    /** null */
    @Excel(name = "null")
    private String orgId;

    /** null */
    @Excel(name = "null")
    private String orgName;

    /** 导入批次号 */
    @Excel(name = "导入批次号")
    private String importBatchNo;

    /** 是否自动评分(1是,2否) */
    @Excel(name = "是否自动评分(1是,2否)")
    private String isAutoScore;

    /** null */
    @Excel(name = "null")
    private String upadteBy;

    private String sendflag;

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
    public void setScore(Long score) 
    {
        this.score = score;
    }

    public Long getScore() 
    {
        return score;
    }
    public void setOrgId(String orgId) 
    {
        this.orgId = orgId;
    }

    public String getOrgId() 
    {
        return orgId;
    }
    public void setOrgName(String orgName) 
    {
        this.orgName = orgName;
    }

    public String getOrgName() 
    {
        return orgName;
    }
    public void setImportBatchNo(String importBatchNo) 
    {
        this.importBatchNo = importBatchNo;
    }

    public String getImportBatchNo() 
    {
        return importBatchNo;
    }
    public void setIsAutoScore(String isAutoScore) 
    {
        this.isAutoScore = isAutoScore;
    }

    public String getIsAutoScore() 
    {
        return isAutoScore;
    }
    public void setUpadteBy(String upadteBy) 
    {
        this.upadteBy = upadteBy;
    }

    public String getUpadteBy() 
    {
        return upadteBy;
    }

    public String getSendflag() {
        return sendflag;
    }

    public void setSendflag(String sendflag) {
        this.sendflag = sendflag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orgCasno", getOrgCasno())
            .append("score", getScore())
            .append("orgId", getOrgId())
            .append("orgName", getOrgName())
            .append("importBatchNo", getImportBatchNo())
            .append("isAutoScore", getIsAutoScore())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("upadteBy", getUpadteBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
