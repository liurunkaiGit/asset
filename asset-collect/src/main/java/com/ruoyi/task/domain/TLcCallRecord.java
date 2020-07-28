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
import java.util.Date;
import java.util.List;

/**
 * 通话结果记录对象 t_lc_call_record
 *
 * @author liurunkai
 * @date 2019-12-31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class TLcCallRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 案件编号
     */
    @Excel(name = "机构案件号")
    private String caseNo;

    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String customName;

    /**
     * 客户证件号码
     */
    @Excel(name = "证件号码")
    private String certificateNo;



    /**
     * 联系人姓名
     */
    @Excel(name = "联系人姓名")
    private String contactName;

    /**
     * 和本人关系 1：本人，2：
     */
    @Excel(name = "关系",readConverterExp = "-1=其它,1=本人,2=直系,3=亲戚,4=朋友,5=父母,6=配偶,7=兄弟,8=姐妹,9=哥哥,10=兄长,11=弟弟,12=姐姐,13=妹妹,14=家人,15=老公,16=老婆,17=同事,18=公司")
    private Integer contactRelation;

    /**
     * 电话号码
     */
    @Excel(name = "电话号码")
    private String phone;

    /**
     * 坐席
     */
    @Excel(name = "坐席")
    private String agentName;

    /**
     * 通话结果标记 1:承诺还款 2:谈判 3:半失连 4:拒绝还款 5:失连
     */
    @Excel(name = "电话码")
    private String callSign;

    /**
     * 查账日期
     */
    @Excel(name = "查账日期")
    private String findDate;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    /**
     * 拨打时间
     */
    @Excel(name = "拨打时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;



    /**
     * 通话开始时间
     */
    @Excel(name = "通话开始时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date callStartTime;

    /**
     * 通话结束时间
     */
    @Excel(name = "通话结束时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date callEndTime;

    /**
     * 通话时长
     */
    @Excel(name = "通话时长")
    private String callLen;



    /**
     * 通话结果
     */
    private String callResult;

    /**
     * 通话录音存放位置
     */
    @Excel(name = "录音地址")
    private String callRadioLocation;

    /**
     * 入催日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入催日",dateFormat = "yyyy-MM-dd")
    private Date enterCollDate;

    /**
     * 委案金额
     */
    @Excel(name = "委案金额")
    private BigDecimal arrearsTotal;

    /**
     * 结案应还金额
     */
    @Excel(name = "结案应还金额")
    private String closeCaseYhje;

    /**
     * 逾期天数
     */
    @Excel(name = "逾期天数")
    private String overdueDays;

    /**
     * 通话录音
     */
    private String callRadio;

    /**
     * 类型，1：打电话下电话码 2：未打电话直接下电话码3：导入催记电话码
     */
    private Integer type;

    /**
     * 拨打电话
     */
    private Date makeCallTime;

    /**
     * 电话码中文
     */
    private String dictLabel;

    private String platform;

    private String orgId;

    private String loginName;

    private String createName;

    private String orgName;

    private Integer star;

    /**
     * 通话时长
     */
    private Integer startCallLen;
    private Integer endCallLen;




    /**
     * 拨打时间
     */
    private Date startCreateTime;
    private Date endCreateTime;

    /**
     * 通话时间
     */
    private Date startCallStartTime;
    private Date endCallStartTime;

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 是否推送到语音质检，1：推送
     */
    private Integer sendRadioCheck;
    /**
     * 平台集合 List,
     */
    private List<String> platFormList;


    /**
     *兴业导出使用字段
     */
    private Integer sque;
    private String productName;
    private Date tar;
    private String ywdetp;
    private String wbjb;
    private String csdz;





}
