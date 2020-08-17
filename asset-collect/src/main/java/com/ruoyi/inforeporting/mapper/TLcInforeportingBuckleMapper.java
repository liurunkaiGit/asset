package com.ruoyi.inforeporting.mapper;

import com.ruoyi.inforeporting.domain.TLcInforeportingBuckle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @Description: 上报信息-逾期划扣
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Mapper
public interface TLcInforeportingBuckleMapper {
    /**
     * @param inforeportingBuckle 新增上报信息 逾期划扣
     * @return 是否成功 成功>0 失败<0
     */
    public int insertTLcInforeportingBuckle(TLcInforeportingBuckle inforeportingBuckle);

    /**
     * @param inforeportingBuckle 查询上报信息 逾期划扣
     * @return 上报信息 逾期划扣集合
     */
    public List<TLcInforeportingBuckle> selectTLcInforeportingBuckleList(TLcInforeportingBuckle inforeportingBuckle);

    /**
     * @param id 批量驳回上报信息 逾期划扣
     * @return 是否成功 成功>0 失败<0
     */
    public int rejectTLcInforeportingBuckleByIds(Long[] id);
}
