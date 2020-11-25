package com.ruoyi.selectCaseByPhone.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author guozeqi
 * @create 2020-11-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SelectCaseByPhone {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 案件编号
     */
    @Excel(name = "案件编号")
    private String caseNo;

    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String customName;

    /**
     * 手别
     */
    @Excel(name = "手别")
    private String transferType;

    /**
     * 入催日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入催日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enterCollDate;

    /**
     * 委案金额
     */
    @Excel(name = "委案金额")
    private BigDecimal appointCaseBalance;

    /**
     * 结案应还金额
     */
    @Excel(name = "结案应还金额")
    private BigDecimal closeCaseYhje;

    @Excel(name = "当前已还金额")
    private BigDecimal dqyhje;

    @Excel(name = "电话码")
    private String callSign;
    /** 电话码中文 */
    private String callSignValue;

    @Excel(name = "行动码")
    private String actionCode;
    /** 行动码中文 */
    private String actionCodeValue;

    /**
     * 最近分配日期
     */
    @Excel(name = "最近分配日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date recentlyAllotDate;

    /**
     * 最近跟进时间
     */
    @Excel(name = "最近跟进日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date recentlyFollowUpDate;

    /**
     * 业务归属人
     */
    private Long ownerId;

    /**
     * 业务归属人姓名
     */
    @Excel(name = "业务归属人")
    private String ownerName;

    /**
     * 组别
     */
    @Excel(name = "组别")
    private String userGroup;

    /**
     * 任务类型1：初次生成(导入)，2：重新分派，3：临时代理，4：协助催收
     */
    @Excel(name = "任务类型",readConverterExp = "1=初次生成,2=重新分派,3=临时代理,4=协助催收,5=临时代理回收,6=异常案件转分配,7=结案转移,8=灰色队列,9=从灰色队列移除,10=协助催收申请,11=停催申请,12=停止催收,13=停止催收激活,14=停止催收拒绝,15=拒绝协催,16=机器人协催,17=机器人拉回")
    private Integer taskType;

    /**
     * 任务状态 1：未分配 2：催收中 3: 已结案
     */
    @Excel(name = "任务状态",readConverterExp = "1=未分配,2=已分配,3=已结案")
    private Integer taskStatus;

    @Excel(name = "产品名称")
    private String productName;

    /**
     * 是否出催
     */
    @Excel(name = "结案类型", readConverterExp = "1=出催结案,2=回收结案")
    private Integer closeType;


    @Excel(name = "结案时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date closeDate;

    /**
     * 催收评分
     */
    @Excel(name = "催收评分")
    private Integer score;

    /**
     * 逾期天数
     */
    private Long overdueDays;

    /**
     * 客户手机号
     */
    private String phone;

    /**
     * 证件号码
     */
    private String certificateNo;

    /**
     * 退案日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date backCaseDate;

    private String remark;

    /**
     * 业务归属机构
     */
    private String orgId;

    /**
     * 业务归属机构名称
     */
    private String orgName;

    private String importBatchNo;

    private List<String> caseNoList;


}
