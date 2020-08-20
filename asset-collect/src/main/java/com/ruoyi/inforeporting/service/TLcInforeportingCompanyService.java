package com.ruoyi.inforeporting.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.inforeporting.domain.TLcInforeportingCompany;

import java.util.List;

/**
 * @Description: 上报信息-对公入账
 * @author: gaohg
 * @Date: 2020/8/12
 */
public interface TLcInforeportingCompanyService {
    /**
     * @param inforeportingCompany 新增上报信息对公入账
     * @return 是否成功 成功>0 失败<0
     */
    public int insertTLcInforeportingCompany(TLcInforeportingCompany inforeportingCompany);

    /**
     * @param inforeportingCompany 查询上报信息 对公入账
     * @return 上报信息 对公入账 集合数据
     */
    public List<TLcInforeportingCompany> selectTLcInforeportingCompanyList(TLcInforeportingCompany inforeportingCompany);

    /**
     * @param ids 驳回上报信息 对公入账
     * @return 是否成功 成功>0 失败<0
     */
    public int rejectTLcInforeportingCompanyByIds(String ids);

    /**
     * @param inforeportingCompany 对公入账 导出
     * @return 是否成功 成功>0 失败<0
     */
    public AjaxResult exportExcel(TLcInforeportingCompany inforeportingCompany);
}
