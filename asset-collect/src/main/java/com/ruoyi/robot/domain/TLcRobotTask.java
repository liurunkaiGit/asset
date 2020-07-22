package com.ruoyi.robot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * 【请填写功能名称】对象 t_lc_robot_task
 *
 * @author liurunkai
 * @date 2020-03-18
 */
@Data
@Accessors(chain = true)
public class TLcRobotTask extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 任务id
     */
//    @Excel(name = "任务id")
    private Long taskId;

    /**
     * 机器人任务id
     */
    @Excel(name = "任务id")
    private Integer robotTastId;

    /**
     * 案件编号
     */
    @Excel(name = "机构案件号")
    private String caseNo;

    /**
     * 任务名称
     */
    @Excel(name = "任务名称")
    private String taskName;

    /**
     * 业务归属人
     */
    @Excel(name = "业务归属人 ")
    private String ownerName;

    /**
     * 手别
     */
    @Excel(name = "手别")
    private String transferType;

    /**
     * 委案金额
     */
    @Excel(name = "委案金额")
    private BigDecimal arrearsTotal;

    /**
     * 话术名称
     */
    @Excel(name = "话术名称")
    private String speechCraftName;

    /**
     * 任务创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "任务创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 任务拨打结束时间
     */
    @Excel(name = "拨打结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date callEndDate;

    /**
     * 机器人任务状态
     */
    @Excel(name = "机器人任务状态", readConverterExp = "1=外呼中,2=已完成,4=暂停,6=停止,50=拉回")
    private Integer robotTaskStatus;

    /**
     * 客户意向标签
     */
    @Excel(name = "意向标签")
    private String resultValueAlias;

    /**
     * 通话状态
     */
    @Excel(name = "通话状态", readConverterExp = "0=已接听,1=拒接,2=无法接通,3=主叫号码不可用,4=空号,5=关机,6=占线,7=停机,8=未接,9=主叫欠费,10=呼损,11=黑名单,22=线路盲区")
    private String callStatus;

    /**
     * 通话时长
     */
    @Excel(name = "通话时长")
    private String callLen;

    /**
     * 任务状态
     */
    @Excel(name = "任务状态", readConverterExp = "1=未分配,2=已分配,3=已结案")
    private Integer taskStatus;

    /**
     * 任务类型
     */
    @Excel(name = "任务类型", readConverterExp = "1=初次生成,2=重新分派,3=临时代理,4=协助催收,5=临时代理回收,6=异常案件转分配,7=结案转移,8=灰色队列,9=从灰色队列移除,10=协助催收申请,11=停催申请,12=停止催收,13=停止催收激活,14=停止催收拒绝,15=拒绝协催,16=机器人协催,17=机器人拉回")
    private Integer taskType;

    /**
     * 通话内容
     */
//    @Excel(name = "通话内容")
    private String callContent;

    /**
     * 通话录音
     */
//    @Excel(name = "通话录音")
    private String callRadio;

    /**
     * 拨打开始时间
     */
//    @Excel(name = "拨打开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date callStartDate;

    /**
     * 客户名称
     */
//    @Excel(name = "客户名称")
    private String curName;

    /**
     * 客户手机号
     */
//    @Excel(name = "客户手机号")
    private String phone;

    /**
     * 所属机构id
     */
//    @Excel(name = "所属机构id")
    private String orgId;

    /**
     * 所属机构名称
     */
//    @Excel(name = "所属机构名称")
    private String orgName;

    private String robot;

    /**
     * 呼叫类型,1：重复呼叫，2：停止呼叫，3：第二天呼叫
     */
//    @Excel(name = "呼叫类型,1：重复呼叫，2：停止呼叫，3：第二天呼叫")
    private Integer isRecall;

    /**
     * 连续呼叫天数
     */
//    @Excel(name = "连续呼叫天数")
    private Integer continueDays;

    /**
     * 当天连续呼叫次数
     */
//    @Excel(name = "当天连续呼叫次数")
    private Integer continueFrequency;

    // =====
    private Date startCreateTime;
    private Date endCreateTime;
    private Date startCallEndDate;
    private Date endCallEndDate;
    private String startCallLen;
    private String endCallLen;

    private Set<Long> deptIds;

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 自由查询SQL
     */
    private String freeSQL;

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
     * 修改人
     */
    @Excel(name = "修改人")
    private Long modifyBy;

    /**
     * 修改时间
     */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /**
     * 呼入回调时间
     */
    @Excel(name = "呼入回调时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date callBackTime;


    // ====字段动态查询======
//    private Date start_create_time;
//    private Date end_create_time;
//    private Date start_call_end_date;
//    private Date end_call_end_date;
//    private String owner_name;
//    private String task_type;
//    private String 	start_call_len;
//    private String 	end_call_len;
//    private String 	result_value_alias;
//    private String 	transfer_type;
//    private String 	speech_craft_name;
//    private String 	cur_name;
//    private String 	sql;
}
