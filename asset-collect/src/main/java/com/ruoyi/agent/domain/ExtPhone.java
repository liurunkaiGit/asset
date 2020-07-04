package com.ruoyi.agent.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 分机号码对象 ext_phone
 * 
 * @author guozeqi
 * @date 2020-03-02
 */
@Data
public class ExtPhone extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 分机号码 */
    @Excel(name = "分机号码")
    private String agentid;

    /** 坐席技能 */
    @Excel(name = "坐席技能")
    private String skilldesc;

    /** 外呼前缀 */
    @Excel(name = "外呼前缀")
    private String dialprefix;

    /** 外显号码 */
    @Excel(name = "外显号码")
    private String dialcaller;

    /** 代理地址 */
    @Excel(name = "代理地址")
    private String proxyUrl;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private String isused;

    /** 外显号码组 */
    @Excel(name = "外显号码组")
    private String exonNumGroup;

    /** 外显号码组id */
    @Excel(name = "外显号码组id")
    private Integer exonNumGroupId;

    /** 绑定坐席名称 */
    @Excel(name = "绑定坐席名称")
    private String seatName;

    /** 绑定坐席id */
    @Excel(name = "绑定坐席id")
    private Integer seatId;

    /** 话务平台 */
    @Excel(name = "所属话务平台")
    private String callPlatform;

    /** 自建坐席登录时密码 */
    @Excel(name = "分机密码")
    private String password;

    /** 自建企业编号或者域名 */
    @Excel(name = "域名")
    private String domain;

    private Long orgId;
    private String orgName;
}
