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
public class TLcCallRecordForXY extends BaseEntity {
    private static final long serialVersionUID = 1L;


    @Excel(name = "序号")
    private Integer sque;
    /**
     * 案件编号
     */
    @Excel(name = "贷款合同号")
    private String caseNo;

    @Excel(name = "业务部门")//写死
    private String ywdetp;

    @Excel(name = "外包经办")
    private String wbjb;//写死
    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String customName;

    @Excel(name = "产品名称")
    private String productName;

    @Excel(name = "催收动作")
    private String csdz;//写死

    /**
     * 拨打时间
     */
    @Excel(name = "催收时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 电话码汉字
     */
    @Excel(name = "催收结果")
    private String callResult;


    /**
     * 联系人姓名 eg:关系-名称
     */
    @Excel(name = "联络人")
    private String contactName;


    /**
     * 电话号码
     */
    @Excel(name = "联络方式")
    private String phone;

    /**
     * 优先取备注，备注为空时，匹配电话码的汉字
     */
    @Excel(name = "催收详细情况")
    private String remarkDetail;

    /**
     * 入催日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "委案时间",dateFormat = "yyyy-MM-dd")
    private Date enterCollDate;

    /**
     * 退案日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到期时间",dateFormat = "yyyy-MM-dd")
    private Date tar;



    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}
