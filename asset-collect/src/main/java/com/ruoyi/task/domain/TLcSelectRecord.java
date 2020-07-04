package com.ruoyi.task.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 查找记录表对象 t_lc_select_record
 *
 * @author fengzhitao
 * @date 2020-06-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class TLcSelectRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 案件编号
     */
    private String caseNo;

    /**
     * 查找方式 1：114、2：12580、3：天眼查、4：其他
     */
    private Integer searchType;

    /**
     * 和本人关系 1：本人，2：
     */
    private Integer contactRelation;

    /**
     * 是否有效 Y：是，N：否
     */
    private String validateStatus;

    /**
     * 其他 备注
     */
    private String otherRemark;

    /**
     * 其他关系 备注
     */
    private String otherContactRelation;


    /**
     * 查找记录
     */
    private String content;

    /**
     * 查找对象
     */
    private String objName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人名称
     */
    private String createName;

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }





}
