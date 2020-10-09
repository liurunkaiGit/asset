package com.ruoyi.duncase.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.domain.TLcCustinfo;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcTask;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 案件对象 t_lc_duncase
 *
 * @author liurunkai
 * @date 2019-12-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TLcDuncase extends BaseEntity {

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
     * 客户编号
     */
//    @Excel(name = "客户编号")
    private String customNo;
    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String customName;

    /**
     * 证件类型
     */
//    @Excel(name = "证件类型")
    private Integer certificateType;

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

    /** 电话码键值 */
    @Excel(name = "电话码")
    private String callSign;

    /**
     * 电话码 数组
     */
    private List<String> callCodeList;
    /**
     * 历史电话码 数组
     */
    private List<String> callCodeHistoryList;

    /** 电话码中文 */
//    @Excel(name = "电话码中文")
    private String callSignValue;

    /** 行动码键值 */
    @Excel(name = "行动码")
    private String actionCode;

    /**
     * 行动码 数组
     */
    private List<String> actionCodeList;

    /** 行动码中文 */
//    @Excel(name = "行动码中文")
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
//    @Excel(name = "业务归属人")
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
    private Integer caseStatus;

    private Integer taskStatus;

    /**
     * 证件号码
     */
//    @Excel(name = "证件号码")
    private String certificateNo;

    /**
     * 客户手机号
     */
//    @Excel(name = "客户手机号")
    private String customPhone;

    /**
     * 账单日
     */
//    @Excel(name = "账单日")
    private Long monthRepayDay;

    /**
     * 首次逾期时间--逾期起始日
     */
//    @Excel(name = "首次逾期时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date firstOverdueTime;

    /**
     * 逾期天数
     */
//    @Excel(name = "逾期天数")
    private Long overdueDays;
    private Long startOverdueDays;
    private Long endOverdueDays;
    /**
     * 逾期账龄
     */
//    @Excel(name = "逾期账龄")
    private String overdueAging;

    /**
     * 最大逾期天数
     */
//    @Excel(name = "最大逾期天数")
    private Long maxOverdueDay;

    /**
     * 还款账号
     */
//    @Excel(name = "还款账号")
    private String repayAccountNo;

    /**
     * 还款银行
     */
//    @Excel(name = "还款银行")
    private String repayBank;

    /**
     * 业务归属机构
     */
//    @Excel(name = "业务归属机构")
    private String orgId;

    /**
     * 业务归属机构名称
     */
//    @Excel(name = "业务归属机构名称")
    private String orgName;

    /**
     * 手别
     */
//    @Excel(name = "手别")
    private String handSeparation;

    /**
     * 应还日期
     */
//    @Excel(name = "应还日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date repayDate;

    /**
     * 授信额度
     */
//    @Excel(name = "授信额度")
    private BigDecimal creditLine;

    /**
     * 放款金额
     */
//    @Excel(name = "放款金额")
    private BigDecimal borrowLine;

    /**
     * 借款卡号
     */
//    @Excel(name = "借款卡号")
    private String borrowCardNo;

    /**
     * 借款卡银行
     */
//    @Excel(name = "借款卡银行")
    private String borrowBank;

    /**
     * 人民币账户最后缴款金额
     */
//    @Excel(name = "人民币账户最后缴款金额")
    private BigDecimal lastRepayAmountRmb;

    /**
     * 人民币账户余额
     */
//    @Excel(name = "人民币账户余额")
    private BigDecimal balanceRmb;

    /**
     * 人民币账户当前CD值
     */
//    @Excel(name = "人民币账户当前CD值")
    private String currentCdValue;

    /**
     * 人民币账户最后一次缴款日
     */
//    @Excel(name = "人民币账户最后一次缴款日", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastRepayDateRmb;

    /**
     * 人民币账户最后还款笔数
     */
//    @Excel(name = "人民币账户最后还款笔数")
    private Long lastRepayNum;

    /**
     * 人民币账户应还利息总额
     */
//    @Excel(name = "人民币账户应还利息总额")
    private BigDecimal totalInterestRmb;

    /**
     * 人民币账户应还本金1
     */
//    @Excel(name = "人民币账户应还本金1")
    private BigDecimal principalOneRmb;

    /**
     * 人民币账户应还本金2
     */
//    @Excel(name = "人民币账户应还本金2")
    private BigDecimal principalTwoRmb;

    /**
     * 人民币账户应还本金总额
     */
//    @Excel(name = "人民币账户应还本金总额")
    private BigDecimal totalPrincipalRmb;

    /**
     * 人民币账户应还罚息1
     */
//    @Excel(name = "人民币账户应还罚息1")
    private BigDecimal defaultInterestOneRmb;

    /**
     * 人民币账户应还罚息2
     */
//    @Excel(name = "人民币账户应还罚息2")
    private BigDecimal defaultInterestTwoRmb;

    /**
     * 人民币账户应还罚息总额
     */
//    @Excel(name = "人民币账户应还罚息总额")
    private BigDecimal totalDefaultInterestRmb;

    /**
     * 人民币账户应还费用1
     */
//    @Excel(name = "人民币账户应还费用1")
    private BigDecimal expensesPayableOneRmb;

    /**
     * 人民币账户应还费用2
     */
//    @Excel(name = "人民币账户应还费用2")
    private BigDecimal expensesPayableTwoRmb;

    /**
     * 人民币账户应还费用总金额
     */
//    @Excel(name = "人民币账户应还费用总金额")
    private BigDecimal totalExpensesPayableRmb;

    /**
     * 人民币账户最低应还金额
     */
//    @Excel(name = "人民币账户最低应还金额")
    private BigDecimal lowestPaymentRmb;

    /**
     * 人民币账户欠款总金额--欠款总金额
     */
//    @Excel(name = "人民币账户欠款总金额")
    private BigDecimal totalDebtAmountRmb;

    /**
     * 人民币账户额度固定额度
     */
//    @Excel(name = "人民币账户额度固定额度")
    private BigDecimal fixLimitRmb;

    /**
     * 外币账户应还罚息1
     */
//    @Excel(name = "外币账户应还罚息1")
    private BigDecimal defaultInterestOneFc;

    /**
     * 外币账户应还罚息2
     */
//    @Excel(name = "外币账户应还罚息2")
    private BigDecimal defaultInterestTwoFc;

    /**
     * 外币账户应还罚息总额
     */
//    @Excel(name = "外币账户应还罚息总额")
    private BigDecimal totalDefaultInterestFc;

    /**
     * 外币账户应还利息总额
     */
//    @Excel(name = "外币账户应还利息总额")
    private BigDecimal totalInterestFc;

    /**
     * 外币账户应还本金1
     */
//    @Excel(name = "外币账户应还本金1")
    private BigDecimal principalOneFc;

    /**
     * 外币账户应还本金2
     */
//    @Excel(name = "外币账户应还本金2")
    private BigDecimal principalTwoFc;

    /**
     * 外币账户应还本金总额
     */
//    @Excel(name = "外币账户应还本金总额")
    private BigDecimal totalPrincipalFc;

    /**
     * 外币账户应还费用1
     */
//    @Excel(name = "外币账户应还费用1")
    private BigDecimal expensesPayableOneFc;

    /**
     * 外币账户应还费用2
     */
//    @Excel(name = "外币账户应还费用2")
    private BigDecimal expensesPayableTwoFc;

    /**
     * 外币账户应还费用总金额
     */
//    @Excel(name = "外币账户应还费用总金额")
    private BigDecimal totalExpensesPayableFc;

    /**
     * 外币账户最低应还金额
     */
//    @Excel(name = "外币账户最低应还金额")
    private BigDecimal lowestPaymentFc;

    /**
     * 外币账户欠款总金额
     */
//    @Excel(name = "外币账户欠款总金额")
    private BigDecimal totalDebtAmountFc;

    /**
     * 是否有效 1：是，2：否
     */
//    @Excel(name = "是否有效 1：是，2：否")
    private Integer validateStatus;

    /**
     * 修改时间
     */
//    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     * 修改人
     */
//    @Excel(name = "修改人")
    private Long modifyBy;

    /**
     * 案件分配类型
     */
    private Integer allocatType;

    /**
     * 滞纳金
     */
//    @Excel(name = "滞纳金")
    private BigDecimal overdueFine;

    /**
     * 所属城市
     */
//    @Excel(name = "所属城市")
    private String city;

    /**
     * 所属区域
     */
//    @Excel(name = "所属区域")
    private String area;

    /**
     * 推荐商户
     */
//    @Excel(name = "推荐商户")
    private String recommendVendor;

    /**
     * 推荐网点
     */
//    @Excel(name = "推荐网点")
    private String recommendWebsite;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String productName;

    /**
     * 还款方式
     */
//    @Excel(name = "还款方式")
    private String repayMethod;

    /**
     * 分期期数
     */
//    @Excel(name = "分期期数")
    private String agingPeriods;

    /**
     * 账单地址
     */
//    @Excel(name = "账单地址")
    private String billAddress;

    /**
     * 年利率
     */
//    @Excel(name = "年利率")
    private String yearInterestRate;

    /**
     * 日利率
     */
//    @Excel(name = "日利率")
    private String dayInterestRate;

    /**
     * 首逾标识
     */
//    @Excel(name = "首逾标识")
    private String firstOverdueSign;

    /**
     * 累计逾期天数
     */
//    @Excel(name = "累计逾期天数")
    private Integer totalOverdueDay;

    /**
     * 逾期次数
     */
//    @Excel(name = "逾期次数")
    private Integer overdueFrequency;

    /**
     * 是否出催
     */
//    @Excel(name = "是否出催")
    private String isExitCollect;

    //=====================以下是调用规则引擎接口获取分配方式所需要的的参数，数据库不做存储

    /**
     * 电话码
     */
    private String callCode;

    /**
     * BLK
     */
//    @Excel(name = "BLK")
    private String blk;

    /**
     * 委外城市名称
     */
//    @Excel(name = "委外城市名称")
    private String wwCityName;

    /**
     * 委外计划截止日期
     */
//    @Excel(name = "委外计划截止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date wwJhEnddate;

    /**
     * 委外起始日期
     */
//    @Excel(name = "委外起始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date wwQsrq;

    /**
     * 客户性别
     */
//    @Excel(name = "客户性别")
    private String curSex;

    /**
     * 美元余额
     */
//    @Excel(name = "美元余额")
    private BigDecimal dollarYe;

    /**
     * 美元当前CD值
     */
//    @Excel(name = "美元当前CD值")
    private String dollarCd;

    /**
     * 美元最低应缴款金额
     */
//    @Excel(name = "美元最低应缴款金额")
    private BigDecimal dollarZdyjkje;

    /**
     * 委案日期
     */
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date appointCaseDate;

    /**
     * 委案金额
     */
    private BigDecimal startAppointCaseBalance;
    private BigDecimal endAppointCaseBalance;

    /**
     * 结案应还金额
     */
    private BigDecimal startCloseCaseYhje;
    private BigDecimal endCloseCaseYhje;

    /**
     * 最近分配日期
     */
    private Date startRecentlyAllotDate;
    private Date endRecentlyAllotDate;

    /**
     * 最近跟进日期
     */
    private Date startRecentlyFollowUpDate;
    private Date endRecentlyFollowUpDate;

    /**
     * 入催日
     */
    private Date startEnterCollDate;
    private Date endEnterCollDate;

    /**
     *
     */
    private TLcTask tLcTask;

    private TLcCustinfo tLcCustinfo;

    private List<TLcDuncaseAssign> tlcDuncaseAssignList;

    private List<TLcCustContact> tlcCustContactList;

    private List<TLcCallRecord> callRecordList;

    private List<TLcDuncaseActionRecord> actionRecordList;

    //===============数据权限相关
    /**
     * 部门id集合
     */
    private Set<Long> deptIds;

    /**
     * 客户手机号
     */
    private String phone;

    private String hitRule;
    private String hitRuleDesc;
    private String distributionStrategy;
    /**
     * 导入批次号
     */
    private String importBatchNo;
    /**
     * 打包号
     */
    private String packNo;
    /*退案日*/
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "退案日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date backCaseDate;
    /*贷款类型*/
    private String loanType;
    /*留案标签*/
    private String stayCaseFlag;
    /*风险标签*/
    private String riskFlag;
    /*合同类型*/
    private String contractType;
    /*减免标签*/
    private String reductionFlag;
    /*法催标签*/
    private String legalFlag;

    /**
     * 退案日
     */
    private Date startBackCaseDate;
    private Date endBackCaseDate;

    /**
     * 累计已还金额
     */
    private BigDecimal ljyhje;

    /**
     * 自由查询sql
     */
    private String freeSQL;


    @Excel(name = "结案类型", readConverterExp = "1=出催结案,2=回收结案")
    private Integer closeType;

    @Excel(name = "结案时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date closeDate;

    private String remark;

    /**
     * 催收评分
     */
    @Excel(name = "催收评分")
    private Integer score;
    private String startScore;
    private String endScore;





    /**
     * 自由查询sql
     */
    private String freeSQL2;

    //省市信息
    private String provinceId;
    private String cityId;


    //百融号码状态
    private String phoneStatus;

    // 动态查询条件使用
//    private BigDecimal start_arrears_total;
//    private BigDecimal end_arrears_total;
//    private BigDecimal start_close_case_yhje;
//    private BigDecimal end_close_case_yhje;
//    private String transfer_type;
//    private String owner_name;
//    private String call_sign;
//    private String action_code;
//    private String task_type;
//    private Date start_enter_coll_date;
//    private Date end_enter_coll_date;
//    private Date start_recently_allot_date;
//    private Date end_recently_allot_date;
//    private Date start_recently_follow_up_date;
//    private Date end_recently_follow_up_date;
//    private String compareMethod;
//    private String sql;

}
