package com.ruoyi.assetspackage.domain.phoneStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 号码状态对象 phone_status
 * 
 * @author guozeqi
 * @date 2020-08-31
 */
@Data
public class PhoneStatus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
//    @Excel(name = "主键")
    private Long id;

    /** 案件编号 */
    @Excel(name = "案件编号")
    private String caseNo;

    /** 委案金额 */
    @Excel(name = "委案金额")
    private BigDecimal waje;

    /** 结案应还金额 */
    @Excel(name = "结案应还金额")
    private BigDecimal jayhje;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phone;

    /** 与本人关系 */
    @Excel(name = "关系",readConverterExp = "-1=其它,1=本人,2=直系,3=亲戚,4=朋友,5=父母,6=配偶,7=兄弟,8=姐妹,9=哥哥,10=兄长,11=弟弟,12=姐姐,13=妹妹,14=家人,15=老公,16=老婆,17=同事,18=公司")
    private Integer relation;

    /** 查询时间 */
    @Excel(name = "查询时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 号码状态 */
    @Excel(name = "号码状态")
    private String phonestatus;

    private String orgId;
    private String orgName;



    private Date startCreateTime;
    private Date endCreateTime;
    private List<String> phoneStatusList;


}
