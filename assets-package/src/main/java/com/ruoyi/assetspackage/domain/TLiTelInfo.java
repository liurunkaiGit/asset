package com.ruoyi.assetspackage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 电话导入临时对象 t_li_tel_info
 * 
 * @author liurunkai
 * @date 2020-05-29
 */
public class TLiTelInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键")
    private String tuid;

    /** 案件主键 */
    @Excel(name = "案件主键")
    private String caseTuid;

    /** 客户编号 */
    @Excel(name = "客户编号")
    private String custNo;

    /** 电话类型 */
    @Excel(name = "电话类型")
    private String telType;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String telephone;

    /** 称呼 */
    @Excel(name = "称呼")
    private String custName;

    /** 拼音 */
    @Excel(name = "拼音")
    private String custNamePinyin;

    /** 与客户关系 */
    @Excel(name = "与客户关系")
    private String relation;

    /** 数据来源 0 主机  1 华道 */
    @Excel(name = "数据来源 0 主机  1 华道")
    private String dataSource;

    /** 最近电话码 */
    @Excel(name = "最近电话码")
    private String lastTelCode;

    /** 最近拨打时间 */
    @Excel(name = "最近拨打时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastCallTime;

    /** 最近拨打人 */
    @Excel(name = "最近拨打人")
    private String lastCallUser;

    /** 电话停拨标志 1停拨 */
    @Excel(name = "电话停拨标志 1停拨")
    private Integer stopFlag;

    /** 是否失联 0 否 1 是 */
    @Excel(name = "是否失联 0 否 1 是")
    private Integer shiLian;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date created;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdby;

    /** 更新时间 */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updated;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedby;

    /** 导入批次 */
    @Excel(name = "导入批次")
    private String importBatch;

    /** 复核日期 */
    @Excel(name = "复核日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recheckDate;

    /** 导入源数据主键 */
    @Excel(name = "导入源数据主键")
    private String importTuid;

    /** 邮编 */
    @Excel(name = "邮编")
    private String postcode;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 最高电话码 */
    @Excel(name = "最高电话码")
    private String maxTelCode;

    /** 约定联系时间 */
    @Excel(name = "约定联系时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date appointTime;

    /** 号码有效性 -1 未查询 0 有效 1 无效 */
    @Excel(name = "号码有效性 -1 未查询 0 有效 1 无效")
    private Integer effectiveFlag;

    /** 号码标记状态 1 接听 2 无人接听 3 关机停机 4空号停拨投诉 */
    @Excel(name = "号码标记状态 1 接听 2 无人接听 3 关机停机 4空号停拨投诉")
    private Integer signStatus;

    /** 星标 1 标记 null 未标记 */
    @Excel(name = "星标 1 标记 null 未标记")
    private Integer stress;

    /** 号码显示  0 显示 1 不显示 */
    @Excel(name = "号码显示  0 显示 1 不显示")
    private Integer displayFlag;

    /** 电话权重 */
    @Excel(name = "电话权重")
    private String weight;

    /** 拨打次数 */
    @Excel(name = "拨打次数")
    private Integer dialCount;

    public void setTuid(String tuid) 
    {
        this.tuid = tuid;
    }

    public String getTuid() 
    {
        return tuid;
    }
    public void setCaseTuid(String caseTuid) 
    {
        this.caseTuid = caseTuid;
    }

    public String getCaseTuid() 
    {
        return caseTuid;
    }
    public void setCustNo(String custNo) 
    {
        this.custNo = custNo;
    }

    public String getCustNo() 
    {
        return custNo;
    }
    public void setTelType(String telType) 
    {
        this.telType = telType;
    }

    public String getTelType() 
    {
        return telType;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setCustName(String custName) 
    {
        this.custName = custName;
    }

    public String getCustName() 
    {
        return custName;
    }
    public void setCustNamePinyin(String custNamePinyin) 
    {
        this.custNamePinyin = custNamePinyin;
    }

    public String getCustNamePinyin() 
    {
        return custNamePinyin;
    }
    public void setRelation(String relation) 
    {
        this.relation = relation;
    }

    public String getRelation() 
    {
        return relation;
    }
    public void setDataSource(String dataSource) 
    {
        this.dataSource = dataSource;
    }

    public String getDataSource() 
    {
        return dataSource;
    }
    public void setLastTelCode(String lastTelCode) 
    {
        this.lastTelCode = lastTelCode;
    }

    public String getLastTelCode() 
    {
        return lastTelCode;
    }
    public void setLastCallTime(Date lastCallTime) 
    {
        this.lastCallTime = lastCallTime;
    }

    public Date getLastCallTime() 
    {
        return lastCallTime;
    }
    public void setLastCallUser(String lastCallUser) 
    {
        this.lastCallUser = lastCallUser;
    }

    public String getLastCallUser() 
    {
        return lastCallUser;
    }
    public void setStopFlag(Integer stopFlag) 
    {
        this.stopFlag = stopFlag;
    }

    public Integer getStopFlag() 
    {
        return stopFlag;
    }
    public void setShiLian(Integer shiLian) 
    {
        this.shiLian = shiLian;
    }

    public Integer getShiLian() 
    {
        return shiLian;
    }
    public void setCreated(Date created) 
    {
        this.created = created;
    }

    public Date getCreated() 
    {
        return created;
    }
    public void setCreatedby(String createdby) 
    {
        this.createdby = createdby;
    }

    public String getCreatedby() 
    {
        return createdby;
    }
    public void setUpdated(Date updated) 
    {
        this.updated = updated;
    }

    public Date getUpdated() 
    {
        return updated;
    }
    public void setUpdatedby(String updatedby) 
    {
        this.updatedby = updatedby;
    }

    public String getUpdatedby() 
    {
        return updatedby;
    }
    public void setImportBatch(String importBatch) 
    {
        this.importBatch = importBatch;
    }

    public String getImportBatch() 
    {
        return importBatch;
    }
    public void setRecheckDate(Date recheckDate) 
    {
        this.recheckDate = recheckDate;
    }

    public Date getRecheckDate() 
    {
        return recheckDate;
    }
    public void setImportTuid(String importTuid) 
    {
        this.importTuid = importTuid;
    }

    public String getImportTuid() 
    {
        return importTuid;
    }
    public void setPostcode(String postcode) 
    {
        this.postcode = postcode;
    }

    public String getPostcode() 
    {
        return postcode;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setMaxTelCode(String maxTelCode) 
    {
        this.maxTelCode = maxTelCode;
    }

    public String getMaxTelCode() 
    {
        return maxTelCode;
    }
    public void setAppointTime(Date appointTime) 
    {
        this.appointTime = appointTime;
    }

    public Date getAppointTime() 
    {
        return appointTime;
    }
    public void setEffectiveFlag(Integer effectiveFlag) 
    {
        this.effectiveFlag = effectiveFlag;
    }

    public Integer getEffectiveFlag() 
    {
        return effectiveFlag;
    }
    public void setSignStatus(Integer signStatus) 
    {
        this.signStatus = signStatus;
    }

    public Integer getSignStatus() 
    {
        return signStatus;
    }
    public void setStress(Integer stress) 
    {
        this.stress = stress;
    }

    public Integer getStress() 
    {
        return stress;
    }
    public void setDisplayFlag(Integer displayFlag) 
    {
        this.displayFlag = displayFlag;
    }

    public Integer getDisplayFlag() 
    {
        return displayFlag;
    }
    public void setWeight(String weight) 
    {
        this.weight = weight;
    }

    public String getWeight() 
    {
        return weight;
    }
    public void setDialCount(Integer dialCount) 
    {
        this.dialCount = dialCount;
    }

    public Integer getDialCount() 
    {
        return dialCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tuid", getTuid())
            .append("caseTuid", getCaseTuid())
            .append("custNo", getCustNo())
            .append("telType", getTelType())
            .append("telephone", getTelephone())
            .append("custName", getCustName())
            .append("custNamePinyin", getCustNamePinyin())
            .append("relation", getRelation())
            .append("dataSource", getDataSource())
            .append("lastTelCode", getLastTelCode())
            .append("lastCallTime", getLastCallTime())
            .append("lastCallUser", getLastCallUser())
            .append("stopFlag", getStopFlag())
            .append("shiLian", getShiLian())
            .append("created", getCreated())
            .append("createdby", getCreatedby())
            .append("updated", getUpdated())
            .append("updatedby", getUpdatedby())
            .append("importBatch", getImportBatch())
            .append("recheckDate", getRecheckDate())
            .append("importTuid", getImportTuid())
            .append("postcode", getPostcode())
            .append("address", getAddress())
            .append("maxTelCode", getMaxTelCode())
            .append("appointTime", getAppointTime())
            .append("effectiveFlag", getEffectiveFlag())
            .append("signStatus", getSignStatus())
            .append("stress", getStress())
            .append("displayFlag", getDisplayFlag())
            .append("weight", getWeight())
            .append("dialCount", getDialCount())
            .toString();
    }
}
