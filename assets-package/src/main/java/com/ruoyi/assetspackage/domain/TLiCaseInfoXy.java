package com.ruoyi.assetspackage.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 兴业案件对象 t_li_case_info_xy
 *
 * @author liurunkai
 * @date 2020-09-09
 */
@Data
public class TLiCaseInfoXy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String tuid;

    /** 导入批次 */
    @Excel(name = "导入批次")
    private String importBatch;

    /** 队列 */
    @Excel(name = "队列")
    private String queue;

    /** 状态 0 处理中, 1 已关闭 出催 */
    @Excel(name = "状态 0 处理中, 1 已关闭 出催")
    private String status;

    /** 是否停催 0 未停催 1 停催 */
    @Excel(name = "是否停催 0 未停催 1 停催")
    private String isStop;

    /** 停催时间 */
    @Excel(name = "停催时间")
    private String stopTime;

    /** 出催时间 */
    @Excel(name = "出催时间")
    private String outTime;

    /** $column.columnComment */
    @Excel(name = "出催时间")
    private String created;

    /** $column.columnComment */
    @Excel(name = "出催时间")
    private String createdby;

    /** $column.columnComment */
    @Excel(name = "出催时间")
    private String updated;

    /** $column.columnComment */
    @Excel(name = "出催时间")
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

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String certificateNo;

    /** 入催账单金额 */
    @Excel(name = "入催账单金额")
    private String corpus;

    /** 账单日 */
    @Excel(name = "账单日")
    private String billDate;

    /** 催收分案日期（委案日期） -- 案件发送日期 */
    @Excel(name = "催收分案日期", readConverterExp = "委=案日期")
    private String collectionDate;

    /** 催收结束日期-- 预计退案日期 --案件委托期限届满日期，外包公司停止催收 */
    @Excel(name = "催收结束日期-- 预计退案日期 --案件委托期限届满日期，外包公司停止催收")
    private String collectionDateEnd;

    /** $column.columnComment */
    @Excel(name = "催收结束日期-- 预计退案日期 --案件委托期限届满日期，外包公司停止催收")
    private String version;

    /** 导入源数据主键 */
    @Excel(name = "导入源数据主键")
    private String importTuid;

    /** 逾期周期 参照逾期天数：M1 6-30 天；M2 31-60 天；M3 61-90 天 */
    @Excel(name = "逾期周期 参照逾期天数：M1 6-30 天；M2 31-60 天；M3 61-90 天")
    private String overduePeriod;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String mobile;

    /** [初始]入催应还金额 */
    @Excel(name = "[初始]入催应还金额")
    private String debtInit;

    /** 是否失联 */
    @Excel(name = "是否失联")
    private String shiLian;

    /** 开户行名称 */
    @Excel(name = "开户行名称")
    private String bankCardName;

    /** 经办机构 */
    @Excel(name = "经办机构")
    private String agencyName;

    /** 家庭住址 */
    @Excel(name = "家庭住址")
    private String custAddress;

    /** 公司地址 */
    @Excel(name = "公司地址")
    private String companyAddr;

    /** 黑名单 */
    @Excel(name = "黑名单")
    private String black;

    /** -- 出催类型 0 满意出催 1 出催未还款 */
    @Excel(name = "-- 出催类型 0 满意出催 1 出催未还款")
    private String outType;

    /** 复贷建议 */
    @Excel(name = "复贷建议")
    private String suggest;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 出生日期 */
    @Excel(name = "出生日期")
    private String birthday;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 年龄 */
    @Excel(name = "年龄")
    private String age;

    /** 电邮 */
    @Excel(name = "电邮")
    private String email;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 分行 */
    @Excel(name = "分行")
    private String branch;

    /** 退单原因 */
    @Excel(name = "退单原因")
    private String outReason;

    /** 户籍地 */
    @Excel(name = "户籍地")
    private String household;

    /** 帐户数 */
    @Excel(name = "帐户数")
    private String cardCount;

    /** 委案金额 */
    @Excel(name = "委案金额")
    private String assignBalRmb;

    /** 告知书邮寄审批 0 不需要审批, 1 需要审批 2 已处理 */
    @Excel(name = "告知书邮寄审批 0 不需要审批, 1 需要审批 2 已处理")
    private String isNotice;

    /** 账龄 */
    @Excel(name = "账龄")
    private String overdueStatus;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String bhStaff;

    /** 还款期望值 */
    @Excel(name = "还款期望值")
    private String score;

    /** 预测概率 */
    @Excel(name = "预测概率")
    private String probability;

    /** 等级 */
    @Excel(name = "等级")
    private String level;

    /** 初始最低还款额 */
    @Excel(name = "初始最低还款额")
    private String minPayOld;

    /** 工单查询结果数据 */
    @Excel(name = "工单查询结果数据")
    private String queryData;

    /** 工单查询结果url */
    @Excel(name = "工单查询结果url")
    private String queryUrl;

    /** 工单查询状态 0 未查询, 1 已查询 */
    @Excel(name = "工单查询状态 0 未查询, 1 已查询")
    private String queryStatus;

    /** 交行工单查询结果数据 */
    @Excel(name = "交行工单查询结果数据")
    private String queryDataJh;

    /** 交行工单查询结果url */
    @Excel(name = "交行工单查询结果url")
    private String queryUrlJh;

    /** 状态 0 正常案件 1 转法务 */
    @Excel(name = "状态 0 正常案件 1 转法务")
    private String isLaw;

    /** 本月净回款 */
    @Excel(name = "本月净回款")
    private String payCurmon;

    /** 信息修复申请时间 */
    @Excel(name = "信息修复申请时间")
    private String applyTime;

    /** 申请返回时间 */
    @Excel(name = "申请返回时间")
    private String applyTimeReturn;

    /** 最新逾期期数(账龄) */
    @Excel(name = "最新逾期期数(账龄)")
    private String overdueRecent;

    /** 最新逾期期数初始值 */
    @Excel(name = "最新逾期期数初始值")
    private String overdueRecentInit;

    /** 上次账龄 */
    @Excel(name = "上次账龄")
    private String overdueRecentLast;

    /** 工单查询申请时间 */
    @Excel(name = "工单查询申请时间")
    private String queryTime;

    /** 工单修复结果状态 0 修复失败, 1 修复成功 ,2修复异常 */
    @Excel(name = "工单修复结果状态 0 修复失败, 1 修复成功 ,2修复异常")
    private String queryResult;

    /** 工单查询获取结果时间 */
    @Excel(name = "工单查询获取结果时间")
    private String queryResultTime;

    /** 同盾信修状态 0 未提交, 1 已提交 */
    @Excel(name = "同盾信修状态 0 未提交, 1 已提交")
    private String queryStatusTd;

    /** 同盾信修提交时间 */
    @Excel(name = "同盾信修提交时间")
    private String queryTimeTd;

    /** 状态 0 显示, 1 暂时不显示 */
    @Excel(name = "状态 0 显示, 1 暂时不显示")
    private String statusDispaly;

    /** 在催导航标记颜色 */
    @Excel(name = "在催导航标记颜色")
    private String signColor;

    /** 序号 */
    @Excel(name = "序号")
    private String orderNumber;

    /** 数据日期 */
    @Excel(name = "数据日期")
    private String dataTime;

    /** 地区事业部(一级) */
    @Excel(name = "地区事业部(一级)")
    private String city1;

    /** 地区事业部(二级) */
    @Excel(name = "地区事业部(二级)")
    private String city2;

    /** 催收节点 */
    @Excel(name = "催收节点")
    private String csNode;

    /** 外包经办 */
    @Excel(name = "外包经办")
    private String wbCompany;

    /** 原始客户经理名称 */
    @Excel(name = "原始客户经理名称")
    private String originalManager;

    /** 客户经理名称 */
    @Excel(name = "客户经理名称")
    private String manager;

    /** 逾期日期 */
    @Excel(name = "逾期日期")
    private String overdueDate;

    /** 新分类对应产品名称 */
    @Excel(name = "新分类对应产品名称")
    private String newProductName;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 还款方式 */
    @Excel(name = "还款方式")
    private String payType;

    /** 贷款余额 */
    @Excel(name = "贷款余额")
    private String accountBalance;

    /** 外包标的 */
    @Excel(name = "外包标的")
    private String wbbd;

    /** 逾期天数 */
    @Excel(name = "逾期天数")
    private String overdueDays;

    /** 贷款本金 */
    @Excel(name = "贷款本金")
    private String loan;

    /** 逾期本金 */
    @Excel(name = "逾期本金")
    private String amtPrinciple;

    /** 逾期利息 */
    @Excel(name = "逾期利息")
    private String fee;

    /** 逾期手续费 */
    @Excel(name = "逾期手续费")
    private String overdueServicefee;

    /** 滞纳金 */
    @Excel(name = "滞纳金")
    private String latefee;

    /** 罚息 */
    @Excel(name = "罚息")
    private String interestPenalty;

    /** 收款账号 */
    @Excel(name = "收款账号")
    private String accountNumber;

    /** 单位电话 */
    @Excel(name = "单位电话")
    private String companyPhone;

    /** 部门 */
    @Excel(name = "部门")
    private String department;

    /** 经营地址 */
    @Excel(name = "经营地址")
    private String businessAddress;

    /** 家庭住址 */
    @Excel(name = "家庭住址")
    private String homeAddr;

    /** 家庭电话 */
    @Excel(name = "家庭电话")
    private String homePhone;

    /** 联系人一姓名 */
    @Excel(name = "联系人一姓名")
    private String contactName1;

    /** 联系人一电话 */
    @Excel(name = "联系人一电话")
    private String contactPhone1;

    /** 联系人二姓名 */
    @Excel(name = "联系人二姓名")
    private String contactName2;

    /** 联系人二电话 */
    @Excel(name = "联系人二电话")
    private String contactPhone2;

    /** 联系人三姓名 */
    @Excel(name = "联系人三姓名")
    private String contactName3;

    /** 联系人三电话 */
    @Excel(name = "联系人三电话")
    private String contactPhone3;

    /** 渠道名称 */
    @Excel(name = "渠道名称")
    private String channelName;

    /** 外包手别 */
    @Excel(name = "外包手别")
    private String hands;

    /** 最后一次还款日期 */
    @Excel(name = "最后一次还款日期")
    private String lastPaymentDate;

    /** 分配时间 */
    @Excel(name = "分配时间")
    private String allocationTime;

    /** 案件回收时间 */
    @Excel(name = "案件回收时间")
    private String recoveryTime;

    /** 催收金额 */
    @Excel(name = "催收金额")
    private String debt;

    public void setTuid(String tuid)
    {
        this.tuid = tuid;
    }

    public String getTuid()
    {
        return tuid;
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
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setIsStop(String isStop)
    {
        this.isStop = isStop;
    }

    public String getIsStop()
    {
        return isStop;
    }
    public void setStopTime(String stopTime)
    {
        this.stopTime = stopTime;
    }

    public String getStopTime()
    {
        return stopTime;
    }
    public void setOutTime(String outTime)
    {
        this.outTime = outTime;
    }

    public String getOutTime()
    {
        return outTime;
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
    public void setUpdated(String updated)
    {
        this.updated = updated;
    }

    public String getUpdated()
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
    public void setCertificateNo(String certificateNo)
    {
        this.certificateNo = certificateNo;
    }

    public String getCertificateNo()
    {
        return certificateNo;
    }
    public void setCorpus(String corpus)
    {
        this.corpus = corpus;
    }

    public String getCorpus()
    {
        return corpus;
    }
    public void setBillDate(String billDate)
    {
        this.billDate = billDate;
    }

    public String getBillDate()
    {
        return billDate;
    }
    public void setCollectionDate(String collectionDate)
    {
        this.collectionDate = collectionDate;
    }

    public String getCollectionDate()
    {
        return collectionDate;
    }
    public void setCollectionDateEnd(String collectionDateEnd)
    {
        this.collectionDateEnd = collectionDateEnd;
    }

    public String getCollectionDateEnd()
    {
        return collectionDateEnd;
    }
    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getVersion()
    {
        return version;
    }
    public void setImportTuid(String importTuid)
    {
        this.importTuid = importTuid;
    }

    public String getImportTuid()
    {
        return importTuid;
    }
    public void setOverduePeriod(String overduePeriod)
    {
        this.overduePeriod = overduePeriod;
    }

    public String getOverduePeriod()
    {
        return overduePeriod;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getMobile()
    {
        return mobile;
    }
    public void setDebtInit(String debtInit)
    {
        this.debtInit = debtInit;
    }

    public String getDebtInit()
    {
        return debtInit;
    }
    public void setShiLian(String shiLian)
    {
        this.shiLian = shiLian;
    }

    public String getShiLian()
    {
        return shiLian;
    }
    public void setBankCardName(String bankCardName)
    {
        this.bankCardName = bankCardName;
    }

    public String getBankCardName()
    {
        return bankCardName;
    }
    public void setAgencyName(String agencyName)
    {
        this.agencyName = agencyName;
    }

    public String getAgencyName()
    {
        return agencyName;
    }
    public void setCustAddress(String custAddress)
    {
        this.custAddress = custAddress;
    }

    public String getCustAddress()
    {
        return custAddress;
    }
    public void setCompanyAddr(String companyAddr)
    {
        this.companyAddr = companyAddr;
    }

    public String getCompanyAddr()
    {
        return companyAddr;
    }
    public void setBlack(String black)
    {
        this.black = black;
    }

    public String getBlack()
    {
        return black;
    }
    public void setOutType(String outType)
    {
        this.outType = outType;
    }

    public String getOutType()
    {
        return outType;
    }
    public void setSuggest(String suggest)
    {
        this.suggest = suggest;
    }

    public String getSuggest()
    {
        return suggest;
    }
    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return city;
    }
    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public String getBirthday()
    {
        return birthday;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getSex()
    {
        return sex;
    }
    public void setAge(String age)
    {
        this.age = age;
    }

    public String getAge()
    {
        return age;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyName()
    {
        return companyName;
    }
    public void setBranch(String branch)
    {
        this.branch = branch;
    }

    public String getBranch()
    {
        return branch;
    }
    public void setOutReason(String outReason)
    {
        this.outReason = outReason;
    }

    public String getOutReason()
    {
        return outReason;
    }
    public void setHousehold(String household)
    {
        this.household = household;
    }

    public String getHousehold()
    {
        return household;
    }
    public void setCardCount(String cardCount)
    {
        this.cardCount = cardCount;
    }

    public String getCardCount()
    {
        return cardCount;
    }
    public void setAssignBalRmb(String assignBalRmb)
    {
        this.assignBalRmb = assignBalRmb;
    }

    public String getAssignBalRmb()
    {
        return assignBalRmb;
    }
    public void setIsNotice(String isNotice)
    {
        this.isNotice = isNotice;
    }

    public String getIsNotice()
    {
        return isNotice;
    }
    public void setOverdueStatus(String overdueStatus)
    {
        this.overdueStatus = overdueStatus;
    }

    public String getOverdueStatus()
    {
        return overdueStatus;
    }
    public void setBhStaff(String bhStaff)
    {
        this.bhStaff = bhStaff;
    }

    public String getBhStaff()
    {
        return bhStaff;
    }
    public void setScore(String score)
    {
        this.score = score;
    }

    public String getScore()
    {
        return score;
    }
    public void setProbability(String probability)
    {
        this.probability = probability;
    }

    public String getProbability()
    {
        return probability;
    }
    public void setLevel(String level)
    {
        this.level = level;
    }

    public String getLevel()
    {
        return level;
    }
    public void setMinPayOld(String minPayOld)
    {
        this.minPayOld = minPayOld;
    }

    public String getMinPayOld()
    {
        return minPayOld;
    }
    public void setQueryData(String queryData)
    {
        this.queryData = queryData;
    }

    public String getQueryData()
    {
        return queryData;
    }
    public void setQueryUrl(String queryUrl)
    {
        this.queryUrl = queryUrl;
    }

    public String getQueryUrl()
    {
        return queryUrl;
    }
    public void setQueryStatus(String queryStatus)
    {
        this.queryStatus = queryStatus;
    }

    public String getQueryStatus()
    {
        return queryStatus;
    }
    public void setQueryDataJh(String queryDataJh)
    {
        this.queryDataJh = queryDataJh;
    }

    public String getQueryDataJh()
    {
        return queryDataJh;
    }
    public void setQueryUrlJh(String queryUrlJh)
    {
        this.queryUrlJh = queryUrlJh;
    }

    public String getQueryUrlJh()
    {
        return queryUrlJh;
    }
    public void setIsLaw(String isLaw)
    {
        this.isLaw = isLaw;
    }

    public String getIsLaw()
    {
        return isLaw;
    }
    public void setPayCurmon(String payCurmon)
    {
        this.payCurmon = payCurmon;
    }

    public String getPayCurmon()
    {
        return payCurmon;
    }
    public void setApplyTime(String applyTime)
    {
        this.applyTime = applyTime;
    }

    public String getApplyTime()
    {
        return applyTime;
    }
    public void setApplyTimeReturn(String applyTimeReturn)
    {
        this.applyTimeReturn = applyTimeReturn;
    }

    public String getApplyTimeReturn()
    {
        return applyTimeReturn;
    }
    public void setOverdueRecent(String overdueRecent)
    {
        this.overdueRecent = overdueRecent;
    }

    public String getOverdueRecent()
    {
        return overdueRecent;
    }
    public void setOverdueRecentInit(String overdueRecentInit)
    {
        this.overdueRecentInit = overdueRecentInit;
    }

    public String getOverdueRecentInit()
    {
        return overdueRecentInit;
    }
    public void setOverdueRecentLast(String overdueRecentLast)
    {
        this.overdueRecentLast = overdueRecentLast;
    }

    public String getOverdueRecentLast()
    {
        return overdueRecentLast;
    }
    public void setQueryTime(String queryTime)
    {
        this.queryTime = queryTime;
    }

    public String getQueryTime()
    {
        return queryTime;
    }
    public void setQueryResult(String queryResult)
    {
        this.queryResult = queryResult;
    }

    public String getQueryResult()
    {
        return queryResult;
    }
    public void setQueryResultTime(String queryResultTime)
    {
        this.queryResultTime = queryResultTime;
    }

    public String getQueryResultTime()
    {
        return queryResultTime;
    }
    public void setQueryStatusTd(String queryStatusTd)
    {
        this.queryStatusTd = queryStatusTd;
    }

    public String getQueryStatusTd()
    {
        return queryStatusTd;
    }
    public void setQueryTimeTd(String queryTimeTd)
    {
        this.queryTimeTd = queryTimeTd;
    }

    public String getQueryTimeTd()
    {
        return queryTimeTd;
    }
    public void setStatusDispaly(String statusDispaly)
    {
        this.statusDispaly = statusDispaly;
    }

    public String getStatusDispaly()
    {
        return statusDispaly;
    }
    public void setSignColor(String signColor)
    {
        this.signColor = signColor;
    }

    public String getSignColor()
    {
        return signColor;
    }
    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }
    public void setDataTime(String dataTime)
    {
        this.dataTime = dataTime;
    }

    public String getDataTime()
    {
        return dataTime;
    }
    public void setCity1(String city1)
    {
        this.city1 = city1;
    }

    public String getCity1()
    {
        return city1;
    }
    public void setCity2(String city2)
    {
        this.city2 = city2;
    }

    public String getCity2()
    {
        return city2;
    }
    public void setCsNode(String csNode)
    {
        this.csNode = csNode;
    }

    public String getCsNode()
    {
        return csNode;
    }
    public void setWbCompany(String wbCompany)
    {
        this.wbCompany = wbCompany;
    }

    public String getWbCompany()
    {
        return wbCompany;
    }
    public void setOriginalManager(String originalManager)
    {
        this.originalManager = originalManager;
    }

    public String getOriginalManager()
    {
        return originalManager;
    }
    public void setManager(String manager)
    {
        this.manager = manager;
    }

    public String getManager()
    {
        return manager;
    }
    public void setOverdueDate(String overdueDate)
    {
        this.overdueDate = overdueDate;
    }

    public String getOverdueDate()
    {
        return overdueDate;
    }
    public void setNewProductName(String newProductName)
    {
        this.newProductName = newProductName;
    }

    public String getNewProductName()
    {
        return newProductName;
    }
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductName()
    {
        return productName;
    }
    public void setPayType(String payType)
    {
        this.payType = payType;
    }

    public String getPayType()
    {
        return payType;
    }
    public void setAccountBalance(String accountBalance)
    {
        this.accountBalance = accountBalance;
    }

    public String getAccountBalance()
    {
        return accountBalance;
    }
    public void setWbbd(String wbbd)
    {
        this.wbbd = wbbd;
    }

    public String getWbbd()
    {
        return wbbd;
    }
    public void setOverdueDays(String overdueDays)
    {
        this.overdueDays = overdueDays;
    }

    public String getOverdueDays()
    {
        return overdueDays;
    }
    public void setLoan(String loan)
    {
        this.loan = loan;
    }

    public String getLoan()
    {
        return loan;
    }
    public void setAmtPrinciple(String amtPrinciple)
    {
        this.amtPrinciple = amtPrinciple;
    }

    public String getAmtPrinciple()
    {
        return amtPrinciple;
    }
    public void setFee(String fee)
    {
        this.fee = fee;
    }

    public String getFee()
    {
        return fee;
    }
    public void setOverdueServicefee(String overdueServicefee)
    {
        this.overdueServicefee = overdueServicefee;
    }

    public String getOverdueServicefee()
    {
        return overdueServicefee;
    }
    public void setLatefee(String latefee)
    {
        this.latefee = latefee;
    }

    public String getLatefee()
    {
        return latefee;
    }
    public void setInterestPenalty(String interestPenalty)
    {
        this.interestPenalty = interestPenalty;
    }

    public String getInterestPenalty()
    {
        return interestPenalty;
    }
    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }
    public void setCompanyPhone(String companyPhone)
    {
        this.companyPhone = companyPhone;
    }

    public String getCompanyPhone()
    {
        return companyPhone;
    }
    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getDepartment()
    {
        return department;
    }
    public void setBusinessAddress(String businessAddress)
    {
        this.businessAddress = businessAddress;
    }

    public String getBusinessAddress()
    {
        return businessAddress;
    }
    public void setHomeAddr(String homeAddr)
    {
        this.homeAddr = homeAddr;
    }

    public String getHomeAddr()
    {
        return homeAddr;
    }
    public void setHomePhone(String homePhone)
    {
        this.homePhone = homePhone;
    }

    public String getHomePhone()
    {
        return homePhone;
    }
    public void setContactName1(String contactName1)
    {
        this.contactName1 = contactName1;
    }

    public String getContactName1()
    {
        return contactName1;
    }
    public void setContactPhone1(String contactPhone1)
    {
        this.contactPhone1 = contactPhone1;
    }

    public String getContactPhone1()
    {
        return contactPhone1;
    }
    public void setContactName2(String contactName2)
    {
        this.contactName2 = contactName2;
    }

    public String getContactName2()
    {
        return contactName2;
    }
    public void setContactPhone2(String contactPhone2)
    {
        this.contactPhone2 = contactPhone2;
    }

    public String getContactPhone2()
    {
        return contactPhone2;
    }
    public void setContactName3(String contactName3)
    {
        this.contactName3 = contactName3;
    }

    public String getContactName3()
    {
        return contactName3;
    }
    public void setContactPhone3(String contactPhone3)
    {
        this.contactPhone3 = contactPhone3;
    }

    public String getContactPhone3()
    {
        return contactPhone3;
    }
    public void setChannelName(String channelName)
    {
        this.channelName = channelName;
    }

    public String getChannelName()
    {
        return channelName;
    }
    public void setHands(String hands)
    {
        this.hands = hands;
    }

    public String getHands()
    {
        return hands;
    }
    public void setLastPaymentDate(String lastPaymentDate)
    {
        this.lastPaymentDate = lastPaymentDate;
    }

    public String getLastPaymentDate()
    {
        return lastPaymentDate;
    }
    public void setAllocationTime(String allocationTime)
    {
        this.allocationTime = allocationTime;
    }

    public String getAllocationTime()
    {
        return allocationTime;
    }
    public void setRecoveryTime(String recoveryTime)
    {
        this.recoveryTime = recoveryTime;
    }

    public String getRecoveryTime()
    {
        return recoveryTime;
    }
    public void setDebt(String debt)
    {
        this.debt = debt;
    }

    public String getDebt()
    {
        return debt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("tuid", getTuid())
                .append("importBatch", getImportBatch())
                .append("queue", getQueue())
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
                .append("certificateNo", getCertificateNo())
                .append("corpus", getCorpus())
                .append("billDate", getBillDate())
                .append("collectionDate", getCollectionDate())
                .append("collectionDateEnd", getCollectionDateEnd())
                .append("version", getVersion())
                .append("importTuid", getImportTuid())
                .append("overduePeriod", getOverduePeriod())
                .append("mobile", getMobile())
                .append("debtInit", getDebtInit())
                .append("shiLian", getShiLian())
                .append("bankCardName", getBankCardName())
                .append("agencyName", getAgencyName())
                .append("custAddress", getCustAddress())
                .append("companyAddr", getCompanyAddr())
                .append("black", getBlack())
                .append("outType", getOutType())
                .append("suggest", getSuggest())
                .append("city", getCity())
                .append("birthday", getBirthday())
                .append("sex", getSex())
                .append("age", getAge())
                .append("email", getEmail())
                .append("companyName", getCompanyName())
                .append("branch", getBranch())
                .append("outReason", getOutReason())
                .append("household", getHousehold())
                .append("cardCount", getCardCount())
                .append("assignBalRmb", getAssignBalRmb())
                .append("isNotice", getIsNotice())
                .append("overdueStatus", getOverdueStatus())
                .append("bhStaff", getBhStaff())
                .append("score", getScore())
                .append("probability", getProbability())
                .append("level", getLevel())
                .append("minPayOld", getMinPayOld())
                .append("queryData", getQueryData())
                .append("queryUrl", getQueryUrl())
                .append("queryStatus", getQueryStatus())
                .append("queryDataJh", getQueryDataJh())
                .append("queryUrlJh", getQueryUrlJh())
                .append("isLaw", getIsLaw())
                .append("payCurmon", getPayCurmon())
                .append("applyTime", getApplyTime())
                .append("applyTimeReturn", getApplyTimeReturn())
                .append("overdueRecent", getOverdueRecent())
                .append("overdueRecentInit", getOverdueRecentInit())
                .append("overdueRecentLast", getOverdueRecentLast())
                .append("queryTime", getQueryTime())
                .append("queryResult", getQueryResult())
                .append("queryResultTime", getQueryResultTime())
                .append("queryStatusTd", getQueryStatusTd())
                .append("queryTimeTd", getQueryTimeTd())
                .append("statusDispaly", getStatusDispaly())
                .append("signColor", getSignColor())
                .append("orderNumber", getOrderNumber())
                .append("dataTime", getDataTime())
                .append("city1", getCity1())
                .append("city2", getCity2())
                .append("csNode", getCsNode())
                .append("wbCompany", getWbCompany())
                .append("originalManager", getOriginalManager())
                .append("manager", getManager())
                .append("overdueDate", getOverdueDate())
                .append("newProductName", getNewProductName())
                .append("productName", getProductName())
                .append("payType", getPayType())
                .append("accountBalance", getAccountBalance())
                .append("wbbd", getWbbd())
                .append("overdueDays", getOverdueDays())
                .append("loan", getLoan())
                .append("amtPrinciple", getAmtPrinciple())
                .append("fee", getFee())
                .append("overdueServicefee", getOverdueServicefee())
                .append("latefee", getLatefee())
                .append("interestPenalty", getInterestPenalty())
                .append("accountNumber", getAccountNumber())
                .append("companyPhone", getCompanyPhone())
                .append("department", getDepartment())
                .append("businessAddress", getBusinessAddress())
                .append("homeAddr", getHomeAddr())
                .append("homePhone", getHomePhone())
                .append("contactName1", getContactName1())
                .append("contactPhone1", getContactPhone1())
                .append("contactName2", getContactName2())
                .append("contactPhone2", getContactPhone2())
                .append("contactName3", getContactName3())
                .append("contactPhone3", getContactPhone3())
                .append("channelName", getChannelName())
                .append("hands", getHands())
                .append("lastPaymentDate", getLastPaymentDate())
                .append("allocationTime", getAllocationTime())
                .append("recoveryTime", getRecoveryTime())
                .append("debt", getDebt())
                .toString();
    }
}
