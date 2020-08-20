package com.ruoyi.inforeporting.mapper;

import com.ruoyi.inforeporting.domain.TLcInforeportingBuckle;
import com.ruoyi.inforeporting.domain.TLcInforeportingCompany;
import com.ruoyi.inforeporting.domain.TLcInforeportingCompanyExp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @Description: 上报信息-对公入账
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Mapper
public interface TLcInforeportingCompanyMapper {
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
     * @param id 驳回上报信息 对公入账
     * @return 是否成功 成功>0 失败<0
     */
    public int rejectTLcInforeportingCompanyByIds(Long[] id);
}
