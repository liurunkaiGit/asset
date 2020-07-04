package com.ruoyi.exonNum.service;

import com.ruoyi.exonNum.domain.TLcExonNum;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author liurunkai
 * @date 2020-04-21
 */
public interface ITLcExonNumService {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TLcExonNum selectTLcExonNumById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcExonNum 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLcExonNum> selectTLcExonNumList(TLcExonNum tLcExonNum);

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcExonNum 【请填写功能名称】
     * @return 结果
     */
    public int insertTLcExonNum(TLcExonNum tLcExonNum);

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcExonNum 【请填写功能名称】
     * @return 结果
     */
    public int updateTLcExonNum(TLcExonNum tLcExonNum);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcExonNumByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLcExonNumById(Long id);
}
