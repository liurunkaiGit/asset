package com.ruoyi.task.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 【信息更新申请-次数记录】对象 t_lc_task_uplog
 * 
 * @author liurunkai
 * @date 2021-02-03
 */
public class TLcTaskUplog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** $column.columnComment */
    @Excel(name = "用户id", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateLog;

    /** 次数 */
    @Excel(name = "次数")
    private Integer cishu;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setDateLog(Date dateLog) 
    {
        this.dateLog = dateLog;
    }

    public Date getDateLog() 
    {
        return dateLog;
    }
    public void setCishu(Integer cishu) 
    {
        this.cishu = cishu;
    }

    public Integer getCishu() 
    {
        return cishu;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("dateLog", getDateLog())
            .append("cishu", getCishu())
            .toString();
    }
}
