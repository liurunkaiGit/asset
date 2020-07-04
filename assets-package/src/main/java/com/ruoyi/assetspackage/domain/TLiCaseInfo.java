package com.ruoyi.assetspackage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 案件导入临时对象 t_li_case_info
 * 
 * @author liurunkai
 * @date 2020-05-29
 */
public class TLiCaseInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键")
    private String tuid;

    /** 状态 0 处理中, 1 已关闭 出催 */
    @Excel(name = "状态 0 处理中, 1 已关闭 出催")
    private Integer status;

    /** 是否停催 0 未停催 1 停催 */
    @Excel(name = "是否停催 0 未停催 1 停催")
    private Integer isStop;

    /** 停催时间 */
    @Excel(name = "停催时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date stopTime;

    /** 出催时间 */
    @Excel(name = "出催时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date outTime;

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

    /** 永久催收员 */
    @Excel(name = "永久催收员")
    private String collector;

    /** 当前催收员 */
    @Excel(name = "当前催收员")
    private String collectorCurrent;

    /** 案件号 */
    @Excel(name = "案件号")
    private String caseNo;

    /** 客户号 */
    @Excel(name = "客户号")
    private String custNo;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String custName;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String mobile;

    /** 催收金额 */
    @Excel(name = "催收金额")
    private Double debt;

    /** -- 出催类型 0 满意出催 1 出催未还款 */
    @Excel(name = "-- 出催类型 0 满意出催 1 出催未还款")
    private Integer outType;

    /** 帐户数 */
    @Excel(name = "帐户数")
    private Integer cardCount;

    /** 在催导航标记颜色 */
    @Excel(name = "在催导航标记颜色")
    private String signColor;

    /** 逾期日期 */
    @Excel(name = "逾期日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date overdueDate;

    /** 逾期天数 */
    @Excel(name = "逾期天数")
    private Integer overdueDays;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 导入批次 */
    @Excel(name = "导入批次")
    private String importBatch;

    /** 队列 */
    @Excel(name = "队列")
    private String queue;

    /** 最低还款金额 */
    @Excel(name = "最低还款金额")
    private Double minRepay;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 身份证 */
    @Excel(name = "身份证")
    private String certificateNo;

    /** 生日 */
    @Excel(name = "生日")
    private String birthday;

    /** 部门 */
    @Excel(name = "部门")
    private String depart;

    /** 现居邮编 */
    @Excel(name = "现居邮编")
    private String homePostcode;

    /** 现居省 */
    @Excel(name = "现居省")
    private String homeProvince;

    /** 现居市 */
    @Excel(name = "现居市")
    private String homeCity;

    /** 现居区 */
    @Excel(name = "现居区")
    private String homeDistrict;

    /** 现居城镇 */
    @Excel(name = "现居城镇")
    private String homeTown;

    /** 现居街道 */
    @Excel(name = "现居街道")
    private String homeStreet;

    /** 现居门牌 */
    @Excel(name = "现居门牌")
    private String homePlate;

    /** 公司邮编 */
    @Excel(name = "公司邮编")
    private String companyPostcode;

    /** 公司省 */
    @Excel(name = "公司省")
    private String companyProvince;

    /** 公司市 */
    @Excel(name = "公司市")
    private String companyCity;

    /** 公司区 */
    @Excel(name = "公司区")
    private String companyDistrict;

    /** 公司城镇 */
    @Excel(name = "公司城镇")
    private String companyTown;

    /** 公司街道 */
    @Excel(name = "公司街道")
    private String companyStreet;

    /** 公司街道号 */
    @Excel(name = "公司街道号")
    private String companyStreetNo;

    /** 公司门牌 */
    @Excel(name = "公司门牌")
    private String companyPlate;

    /** 户籍邮编 */
    @Excel(name = "户籍邮编")
    private String domicilePostcode;

    /** 户籍省 */
    @Excel(name = "户籍省")
    private String domicileProvince;

    /** 户籍市 */
    @Excel(name = "户籍市")
    private String domicileCity;

    /** 户籍区 */
    @Excel(name = "户籍区")
    private String domicileDistrict;

    /** 户籍城镇 */
    @Excel(name = "户籍城镇")
    private String domicileTown;

    /** 户籍街道 */
    @Excel(name = "户籍街道")
    private String domicileStreet;

    /** 户籍街道号 */
    @Excel(name = "户籍街道号")
    private String domicileStreetNo;

    /** 户籍门牌 */
    @Excel(name = "户籍门牌")
    private String domicilePlate;

    /** 现居街道号 */
    @Excel(name = "现居街道号")
    private String homeStreetNo;

    public void setTuid(String tuid) 
    {
        this.tuid = tuid;
    }

    public String getTuid() 
    {
        return tuid;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setIsStop(Integer isStop) 
    {
        this.isStop = isStop;
    }

    public Integer getIsStop() 
    {
        return isStop;
    }
    public void setStopTime(Date stopTime) 
    {
        this.stopTime = stopTime;
    }

    public Date getStopTime() 
    {
        return stopTime;
    }
    public void setOutTime(Date outTime) 
    {
        this.outTime = outTime;
    }

    public Date getOutTime() 
    {
        return outTime;
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
    public void setCollector(String collector) 
    {
        this.collector = collector;
    }

    public String getCollector() 
    {
        return collector;
    }
    public void setCollectorCurrent(String collectorCurrent) 
    {
        this.collectorCurrent = collectorCurrent;
    }

    public String getCollectorCurrent() 
    {
        return collectorCurrent;
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
    public void setCustName(String custName) 
    {
        this.custName = custName;
    }

    public String getCustName() 
    {
        return custName;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setDebt(Double debt) 
    {
        this.debt = debt;
    }

    public Double getDebt() 
    {
        return debt;
    }
    public void setOutType(Integer outType) 
    {
        this.outType = outType;
    }

    public Integer getOutType() 
    {
        return outType;
    }
    public void setCardCount(Integer cardCount) 
    {
        this.cardCount = cardCount;
    }

    public Integer getCardCount() 
    {
        return cardCount;
    }
    public void setSignColor(String signColor) 
    {
        this.signColor = signColor;
    }

    public String getSignColor() 
    {
        return signColor;
    }
    public void setOverdueDate(Date overdueDate) 
    {
        this.overdueDate = overdueDate;
    }

    public Date getOverdueDate() 
    {
        return overdueDate;
    }
    public void setOverdueDays(Integer overdueDays) 
    {
        this.overdueDays = overdueDays;
    }

    public Integer getOverdueDays() 
    {
        return overdueDays;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setImportBatch(String importBatch) 
    {
        this.importBatch = importBatch;
    }

    public String getImportBatch() 
    {
        return importBatch;
    }
    public void setQueue(String queue) 
    {
        this.queue = queue;
    }

    public String getQueue() 
    {
        return queue;
    }
    public void setMinRepay(Double minRepay) 
    {
        this.minRepay = minRepay;
    }

    public Double getMinRepay() 
    {
        return minRepay;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setCertificateNo(String certificateNo) 
    {
        this.certificateNo = certificateNo;
    }

    public String getCertificateNo() 
    {
        return certificateNo;
    }
    public void setBirthday(String birthday) 
    {
        this.birthday = birthday;
    }

    public String getBirthday() 
    {
        return birthday;
    }
    public void setDepart(String depart) 
    {
        this.depart = depart;
    }

    public String getDepart() 
    {
        return depart;
    }
    public void setHomePostcode(String homePostcode) 
    {
        this.homePostcode = homePostcode;
    }

    public String getHomePostcode() 
    {
        return homePostcode;
    }
    public void setHomeProvince(String homeProvince) 
    {
        this.homeProvince = homeProvince;
    }

    public String getHomeProvince() 
    {
        return homeProvince;
    }
    public void setHomeCity(String homeCity) 
    {
        this.homeCity = homeCity;
    }

    public String getHomeCity() 
    {
        return homeCity;
    }
    public void setHomeDistrict(String homeDistrict) 
    {
        this.homeDistrict = homeDistrict;
    }

    public String getHomeDistrict() 
    {
        return homeDistrict;
    }
    public void setHomeTown(String homeTown) 
    {
        this.homeTown = homeTown;
    }

    public String getHomeTown() 
    {
        return homeTown;
    }
    public void setHomeStreet(String homeStreet) 
    {
        this.homeStreet = homeStreet;
    }

    public String getHomeStreet() 
    {
        return homeStreet;
    }
    public void setHomePlate(String homePlate) 
    {
        this.homePlate = homePlate;
    }

    public String getHomePlate() 
    {
        return homePlate;
    }
    public void setCompanyPostcode(String companyPostcode) 
    {
        this.companyPostcode = companyPostcode;
    }

    public String getCompanyPostcode() 
    {
        return companyPostcode;
    }
    public void setCompanyProvince(String companyProvince) 
    {
        this.companyProvince = companyProvince;
    }

    public String getCompanyProvince() 
    {
        return companyProvince;
    }
    public void setCompanyCity(String companyCity) 
    {
        this.companyCity = companyCity;
    }

    public String getCompanyCity() 
    {
        return companyCity;
    }
    public void setCompanyDistrict(String companyDistrict) 
    {
        this.companyDistrict = companyDistrict;
    }

    public String getCompanyDistrict() 
    {
        return companyDistrict;
    }
    public void setCompanyTown(String companyTown) 
    {
        this.companyTown = companyTown;
    }

    public String getCompanyTown() 
    {
        return companyTown;
    }
    public void setCompanyStreet(String companyStreet) 
    {
        this.companyStreet = companyStreet;
    }

    public String getCompanyStreet() 
    {
        return companyStreet;
    }
    public void setCompanyStreetNo(String companyStreetNo) 
    {
        this.companyStreetNo = companyStreetNo;
    }

    public String getCompanyStreetNo() 
    {
        return companyStreetNo;
    }
    public void setCompanyPlate(String companyPlate) 
    {
        this.companyPlate = companyPlate;
    }

    public String getCompanyPlate() 
    {
        return companyPlate;
    }
    public void setDomicilePostcode(String domicilePostcode) 
    {
        this.domicilePostcode = domicilePostcode;
    }

    public String getDomicilePostcode() 
    {
        return domicilePostcode;
    }
    public void setDomicileProvince(String domicileProvince) 
    {
        this.domicileProvince = domicileProvince;
    }

    public String getDomicileProvince() 
    {
        return domicileProvince;
    }
    public void setDomicileCity(String domicileCity) 
    {
        this.domicileCity = domicileCity;
    }

    public String getDomicileCity() 
    {
        return domicileCity;
    }
    public void setDomicileDistrict(String domicileDistrict) 
    {
        this.domicileDistrict = domicileDistrict;
    }

    public String getDomicileDistrict() 
    {
        return domicileDistrict;
    }
    public void setDomicileTown(String domicileTown) 
    {
        this.domicileTown = domicileTown;
    }

    public String getDomicileTown() 
    {
        return domicileTown;
    }
    public void setDomicileStreet(String domicileStreet) 
    {
        this.domicileStreet = domicileStreet;
    }

    public String getDomicileStreet() 
    {
        return domicileStreet;
    }
    public void setDomicileStreetNo(String domicileStreetNo) 
    {
        this.domicileStreetNo = domicileStreetNo;
    }

    public String getDomicileStreetNo() 
    {
        return domicileStreetNo;
    }
    public void setDomicilePlate(String domicilePlate) 
    {
        this.domicilePlate = domicilePlate;
    }

    public String getDomicilePlate() 
    {
        return domicilePlate;
    }
    public void setHomeStreetNo(String homeStreetNo) 
    {
        this.homeStreetNo = homeStreetNo;
    }

    public String getHomeStreetNo() 
    {
        return homeStreetNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tuid", getTuid())
            .append("status", getStatus())
            .append("isStop", getIsStop())
            .append("stopTime", getStopTime())
            .append("outTime", getOutTime())
            .append("created", getCreated())
            .append("createdby", getCreatedby())
            .append("updated", getUpdated())
            .append("updatedby", getUpdatedby())
            .append("collector", getCollector())
            .append("collectorCurrent", getCollectorCurrent())
            .append("caseNo", getCaseNo())
            .append("custNo", getCustNo())
            .append("custName", getCustName())
            .append("mobile", getMobile())
            .append("debt", getDebt())
            .append("outType", getOutType())
            .append("cardCount", getCardCount())
            .append("signColor", getSignColor())
            .append("overdueDate", getOverdueDate())
            .append("overdueDays", getOverdueDays())
            .append("companyName", getCompanyName())
            .append("importBatch", getImportBatch())
            .append("queue", getQueue())
            .append("minRepay", getMinRepay())
            .append("sex", getSex())
            .append("certificateNo", getCertificateNo())
            .append("birthday", getBirthday())
            .append("depart", getDepart())
            .append("homePostcode", getHomePostcode())
            .append("homeProvince", getHomeProvince())
            .append("homeCity", getHomeCity())
            .append("homeDistrict", getHomeDistrict())
            .append("homeTown", getHomeTown())
            .append("homeStreet", getHomeStreet())
            .append("homePlate", getHomePlate())
            .append("companyPostcode", getCompanyPostcode())
            .append("companyProvince", getCompanyProvince())
            .append("companyCity", getCompanyCity())
            .append("companyDistrict", getCompanyDistrict())
            .append("companyTown", getCompanyTown())
            .append("companyStreet", getCompanyStreet())
            .append("companyStreetNo", getCompanyStreetNo())
            .append("companyPlate", getCompanyPlate())
            .append("domicilePostcode", getDomicilePostcode())
            .append("domicileProvince", getDomicileProvince())
            .append("domicileCity", getDomicileCity())
            .append("domicileDistrict", getDomicileDistrict())
            .append("domicileTown", getDomicileTown())
            .append("domicileStreet", getDomicileStreet())
            .append("domicileStreetNo", getDomicileStreetNo())
            .append("domicilePlate", getDomicilePlate())
            .append("homeStreetNo", getHomeStreetNo())
            .toString();
    }
}
