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

/**
 * 兴业通话结果记录对象 t_lc_call_record
 *
 * @author guozeqi
 * @date 2019-07-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class TLcCallRecordForDQ extends BaseEntity {
    private static final long serialVersionUID = 1L;


    @Excel(name = "序号")
    private Integer sque;
    /**
     * 机构案件号
     */
    @Excel(name = "机构案件号")
    private String caseNo;

    /**
     * 拨打时间
     */
    @Excel(name = "日期", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 区域中心
     */
    @Excel(name = "申请编号")
    private String areaCenter;

    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String customName;

    /**
     * 推荐网点
     */
    @Excel(name = "渠道方")
    private String tjWd;

    /**
     * 联系状态
     */
    @Excel(name = "联系状态")
    private String contactStatus;

    /**
     * 还款状态
     */
    @Excel(name = "还款状态")
    private String repayStatus;

    /**
     * 承诺还款金额
     */
    @Excel(name = "实际还款金额")
    private BigDecimal actualRepayAmount;

    /**
     * 和本人关系 1：本人，2：
     */
    @Excel(name = "关系", readConverterExp = "-1=其它,1=本人,2=直系,3=亲戚,4=朋友,5=父母,6=配偶,7=兄弟,8=姐妹,9=哥哥,10=兄长,11=弟弟,12=姐姐,13=妹妹,14=家人,15=老公,16=老婆,17=同事,18=公司")
    private Integer contactRelation;

    /**
     * 坐席
     */
    @Excel(name = "工号")
    private String agentName;

    /**
     * 通话结果标记 1:承诺还款 2:谈判 3:半失连 4:拒绝还款 5:失连
     */
    @Excel(name = "行动代码")
    private String callSign;

    /**
     * 备注
     */
    @Excel(name = "催记")
    private String collRecord;

    /**
     * 委案金额
     */
    @Excel(name = "委案金额")
    private BigDecimal rmbYe;

    /**
     * 逾期天数
     */
    @Excel(name = "逾期天数")
    private String overdueDays;

    /**
     * 行动码
     */
    @Excel(name = "行动码")
    private String actionCode;

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
