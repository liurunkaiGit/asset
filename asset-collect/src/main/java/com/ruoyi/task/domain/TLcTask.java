package com.ruoyi.task.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 任务对象 t_lc_task
 *
 * @author liurunkai
 * @date 2019-12-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class TLcTask extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主鍵ID
     */
    private Long id;
    /**
     * 案件号
     */
    @Excel(name = "案件号")
    private String caseNo;

    /**
     * 证件号
     */
    @Excel(name = "证件号")
    private String certificateNo;

    /**
     * 证件类型
     */
    @Excel(name = "证件类型")
    private Integer certificateType;

    /**
     * 客户编号
     */
    @Excel(name = "客户编号")
    private String customCode;

    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String customName;

    /**
     * 委案金额
     */
    @Excel(name = "委案金额")
    private BigDecimal arrearsTotal;

    /**
     * 任务状态 1：未分配 2：催收中 3: 已结案
     */
    @Excel(name = "任务状态 1：未分配 2：催收中 3: 已结案")
    private Integer taskStatus;
    /**
     * 任务状态 多选时候的值存储 例如：1,2,3
     */
    private String taskStatusStr;

    /**
     * 逾期天数
     */
    @Excel(name = "逾期天数")
    private Long overdueDays;
    private Long startOverdueDays;
    private Long endOverdueDays;

    /**
     * 逾期账龄
     */
    @Excel(name = "逾期账龄")
    private String overdueAging;

    /**
     * 催收限时天数
     */
    @Excel(name = "催收限时天数")
    private Long collectTimeLimit;

    /**
     * 最后催记时间
     */
    @Excel(name = "最后催记时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date collectLastTime;

    /**
     * 催收组ID
     */
    @Excel(name = "催收组ID")
    private Integer collectTeamId;

    /**
     * 催收组名称
     */
    @Excel(name = "催收组名称")
    private String collectTeamName;

    /**
     * 业务归属人
     */
    @Excel(name = "业务归属人")
    private Long ownerId;

    /**
     * 业务所属人名称
     */
    private String ownerName;

    /**
     * 业务归属机构
     */
    @Excel(name = "业务归属机构")
    private String orgId;

    /**
     * 业务归属机构
     */
    @Excel(name = "业务归属机构")
    private String firstOrgId;

    /**
     * 任务结束日期
     */
    @Excel(name = "任务结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date closeDate;

    /**
     * 修改任务归属人时间：用来记录在催收员名下天数使用
     */
    @Excel(name = "修改任务归属人时间")
    private LocalDateTime modifyOwnerTime;

    /**
     * 原先的业务归属人(催收员)
     */
    private Long oldOwnerId;

    /**
     * 原先的业务归属人名称(催收员)
     */
    private String oldOwnerName;

    /**
     * 任务类型1：初次生成(导入)，2：重新分派，3：临时代理，4：协助催收
     */
    private Integer taskType;

    /**
     * 任务类型1：初次生成(导入)，2：重新分派，3：临时代理，4：协助催收
     */
    private Set<Integer> taskTypes;

    /**
     * 分配类型1：手动，2：委外，3：机器人，4：法催
     */
    @Excel(name = "分配类型1：手动，2：委外，3：机器人，4：法催")
    private Integer allotType;

    /**
     * 机器人任务id，当分配类型是机器人时必填
     */
    private Integer robotTaskId;

    /**
     * 机器人呼叫策略id
     */
    private Long robotCallStrategyId;

    /**
     * 是否有效 1：是，2：否
     */
    @Excel(name = "是否有效 1：是，2：否")
    private Integer validateStatus;

    /**
     * 修改时间
     */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /**
     * 修改人
     */
    @Excel(name = "修改人")
    private Long modifyBy;

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
     * 结案应还金额
     */
    @Excel(name = "结案应还金额")
    private BigDecimal closeCaseYhje;

    /**
     * 最近分配日期
     */
    @Excel(name = "最近分配日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recentlyAllotDate;

    /**
     * 最近跟进时间：取电话记录表和行动表里面最近的一个创建时间
     */
    @Excel(name = "最近分配日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recentlyFollowUpDate;


    //=====================

    /**
     * 机器人话术 id
     */
    @Excel(name = "机器人话术 id")
    private Integer speechcraftId;

    /**
     * 机器人话术列表
     */
    @Excel(name = "机器人话术列表")
    private String speechcraftName;

    /**
     * 电话码
     */
    private String callCode;

    /**
     * 电话码 数组
     */
    private List<String> callCodeList;
    /**
     * 历史电话码 数组
     */
    private List<String> callCodeHistoryList;
    /**
     * 行动码
     */
    private String actionCode;

    /**
     * 行动码 数组
     */
    private List<String> actionCodeList;
    /**
     * 业务归属机构名称
     */
    private String orgName;

    /**
     * 组别
     */
    private String userGroup;

    /**
     * 电话码中文
     */
    private String dictLabel;

    /**
     * 行动码中文
     */
    @Excel(name = "行动码中文")
    private String actionCodeValue;

    /**
     * 电话码键值
     */
    @Excel(name = "电话码键值")
    private String callSign;

    /**
     * 电话码中文
     */
    @Excel(name = "电话码中文")
    private String callSignValue;

    /**
     * 客户手机号码
     */
    @Excel(name = "客户手机号码")
    private String phone;

    /**
     * 是否查询异常任务
     */
    private String isErr;

    /**
     * 拥有查看数据权限的部门集合
     */
    private Set<Long> deptIds;

    /**
     * 查询催收页面的任务
     */
    private String collJob;

    /**
     * 所属区域
     */
//    @Excel(name = "所属区域")
    private String area;

    /**
     * 退案日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退案日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date backCaseDate;
    /**
     * 留案标签
     */
    private String stayCaseFlag;
    /**
     * 风险标签
     */
    private String riskFlag;
    /**
     * 合同类型
     */
    private String contractType;
    /**
     * 减免标签
     */
    private String reductionFlag;
    /**
     * 法催标签
     */
    private String legalFlag;

    /**
     * 委案金额
     */
    private BigDecimal startArrearsTotal;
    private BigDecimal endArrearsTotal;

    /**
     * 结案应还金额
     */
    private BigDecimal startCloseCaseYhje;
    private BigDecimal endCloseCaseYhje;

    /**
     * 退案日
     */
    private Date startBackCaseDate;
    private Date endBackCaseDate;

    /**
     * 最近分配日期
     */
    private Date startRecentlyAllotDate;
    private Date endRecentlyAllotDate;

    /**
     * 入催日
     */
    private Date startEnterCollDate;
    private Date endEnterCollDate;

    /**
     * 最近跟进日期
     */
    private Date startRecentlyFollowUpDate;
    private Date endRecentlyFollowUpDate;

    /**
     * 操作全部数据时使用
     */
    private Integer checkAllType;

    private String hitRule;
    private String hitRuleDesc;
    private String distributionStrategy;
    /**
     * 导入批次号
     */
    private String importBatchNo;

    /**
     * 产品名称
     */
//    @Excel(name = "产品名称")
    private String productName;

    /**
     * 客户性别
     */
    private Integer customSex;

    /**
     * 首次逾期时间--逾期起始日
     */
    private Date firstOverdueTime;

    /**
     * 人民币账户最低应还金额
     */
    private BigDecimal lowestPaymentRmb;

    /**
     * 当前已还金额
     */
    private BigDecimal dqyhje;
    private BigDecimal startDqyhje;
    private BigDecimal endDqyhje;

    /**
     * 累计已还金额
     */
    private BigDecimal ljyhje;
    private BigDecimal startLjyhje;
    private BigDecimal endLjyhje;

    /**
     * 自由查询
     */
    private String  freeSQL;

    private Integer score;
    private String startScore;
    private String endScore;

    /**
     * 自由查询sql
     */
    private String freeSQL2;



}
