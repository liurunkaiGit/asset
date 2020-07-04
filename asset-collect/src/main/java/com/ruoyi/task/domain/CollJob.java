package com.ruoyi.task.domain;

import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.domain.TLcCustJob;
import com.ruoyi.custom.domain.TLcCustinfo;
import com.ruoyi.duncase.domain.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: 催收作业页面相关实体
 * @author: liurunkai
 * @Date: 2019/12/30 14:25
 */
@Data
@Accessors(chain = true)
public class CollJob {

    private TLcCustinfo tLcCustinfo;
    private TLcCustJob tLcCustJob;
    private TLcDuncase tLcDuncase;
    private TLcTask tLcTask;
    private List<TLcDuncaseAssign> tLcDuncaseAssignList;
    private List<TLcCustContact> tLcCustContactList;
    private List<TLcCallRecord> callRecordList;
    private List<TLcDuncaseActionRecord> actionRecordList;
    private List<AssetsRepayment> repaymentList;
    private Assets Assets;

}
