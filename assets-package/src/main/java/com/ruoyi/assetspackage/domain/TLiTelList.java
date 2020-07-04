package com.ruoyi.assetspackage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 电话记录临时对象 t_li_tel_list
 * 
 * @author liurunkai
 * @date 2020-05-29
 */
public class TLiTelList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键")
    private String tuid;

    /** 电话主键 */
    @Excel(name = "电话主键")
    private String telTuid;

    /** 任务主键 */
    @Excel(name = "任务主键")
    private String taskTuid;

    /** 案件主键 */
    @Excel(name = "案件主键")
    private String caseTuid;

    /** null */
    @Excel(name = "null")
    private String obTuid;

    /** 案件编号 */
    @Excel(name = "案件编号")
    private String caseNo;

    /** 客户编号 */
    @Excel(name = "客户编号")
    private String custNo;

    /** 所在队列 */
    @Excel(name = "所在队列")
    private String queue;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String telephone;

    /** 电话码 */
    @Excel(name = "电话码")
    private String telCode;

    /** 行动码 */
    @Excel(name = "行动码")
    private String actCode;

    /** 行动日期 */
    @Excel(name = "行动日期")
    private String actDate;

    /** 行动金额 */
    @Excel(name = "行动金额")
    private String actMoney;

    /** 复核日期 */
    @Excel(name = "复核日期")
    private String recheckDate;

    /** 当时任务所属人 */
    @Excel(name = "当时任务所属人")
    private String dataOwner;

    /** null */
    @Excel(name = "null")
    private String created;

    /** null */
    @Excel(name = "null")
    private String createdby;

    /** 沟通编号 */
    @Excel(name = "沟通编号")
    private String incidentId;

    /** 录音关联类型  0-系统录音 1-软电话录音 2-手机录音 */
    @Excel(name = "录音关联类型  0-系统录音 1-软电话录音 2-手机录音")
    private String recordType;

    /** 系统录音id */
    @Excel(name = "系统录音id")
    private String obId;

    /** 软电话录音信息 */
    @Excel(name = "软电话录音信息")
    private String obValue;

    /** 电话总结类型：1 法务催记 */
    @Excel(name = "电话总结类型：1 法务催记")
    private String isLaw;

    public void setTuid(String tuid) 
    {
        this.tuid = tuid;
    }

    public String getTuid() 
    {
        return tuid;
    }
    public void setTelTuid(String telTuid) 
    {
        this.telTuid = telTuid;
    }

    public String getTelTuid() 
    {
        return telTuid;
    }
    public void setTaskTuid(String taskTuid) 
    {
        this.taskTuid = taskTuid;
    }

    public String getTaskTuid() 
    {
        return taskTuid;
    }
    public void setCaseTuid(String caseTuid) 
    {
        this.caseTuid = caseTuid;
    }

    public String getCaseTuid() 
    {
        return caseTuid;
    }
    public void setObTuid(String obTuid) 
    {
        this.obTuid = obTuid;
    }

    public String getObTuid() 
    {
        return obTuid;
    }
    public void setCaseNo(String caseNo) 
    {
        this.caseNo = caseNo;
    }

    public String getCaseNo() 
    {
        return caseNo;
    }
    public void setCustNo(String custNo) 
    {
        this.custNo = custNo;
    }

    public String getCustNo() 
    {
        return custNo;
    }
    public void setQueue(String queue) 
    {
        this.queue = queue;
    }

    public String getQueue() 
    {
        return queue;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setTelCode(String telCode) 
    {
        this.telCode = telCode;
    }

    public String getTelCode() 
    {
        return telCode;
    }
    public void setActCode(String actCode) 
    {
        this.actCode = actCode;
    }

    public String getActCode() 
    {
        return actCode;
    }
    public void setActDate(String actDate) 
    {
        this.actDate = actDate;
    }

    public String getActDate() 
    {
        return actDate;
    }
    public void setActMoney(String actMoney) 
    {
        this.actMoney = actMoney;
    }

    public String getActMoney() 
    {
        return actMoney;
    }
    public void setRecheckDate(String recheckDate) 
    {
        this.recheckDate = recheckDate;
    }

    public String getRecheckDate() 
    {
        return recheckDate;
    }
    public void setDataOwner(String dataOwner) 
    {
        this.dataOwner = dataOwner;
    }

    public String getDataOwner() 
    {
        return dataOwner;
    }
    public void setCreated(String created) 
    {
        this.created = created;
    }

    public String getCreated() 
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
    public void setIncidentId(String incidentId) 
    {
        this.incidentId = incidentId;
    }

    public String getIncidentId() 
    {
        return incidentId;
    }
    public void setRecordType(String recordType) 
    {
        this.recordType = recordType;
    }

    public String getRecordType() 
    {
        return recordType;
    }
    public void setObId(String obId) 
    {
        this.obId = obId;
    }

    public String getObId() 
    {
        return obId;
    }
    public void setObValue(String obValue) 
    {
        this.obValue = obValue;
    }

    public String getObValue() 
    {
        return obValue;
    }
    public void setIsLaw(String isLaw) 
    {
        this.isLaw = isLaw;
    }

    public String getIsLaw() 
    {
        return isLaw;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tuid", getTuid())
            .append("telTuid", getTelTuid())
            .append("taskTuid", getTaskTuid())
            .append("caseTuid", getCaseTuid())
            .append("obTuid", getObTuid())
            .append("caseNo", getCaseNo())
            .append("custNo", getCustNo())
            .append("queue", getQueue())
            .append("telephone", getTelephone())
            .append("telCode", getTelCode())
            .append("actCode", getActCode())
            .append("actDate", getActDate())
            .append("actMoney", getActMoney())
            .append("recheckDate", getRecheckDate())
            .append("dataOwner", getDataOwner())
            .append("remark", getRemark())
            .append("created", getCreated())
            .append("createdby", getCreatedby())
            .append("incidentId", getIncidentId())
            .append("recordType", getRecordType())
            .append("obId", getObId())
            .append("obValue", getObValue())
            .append("isLaw", getIsLaw())
            .toString();
    }
}
