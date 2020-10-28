package com.ruoyi.shareproject.process.mapper;

import com.ruoyi.shareproject.process.domain.TLpProcess;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author liurunkai
 * @date 2020-10-16
 */
public interface TLpProcessMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TLpProcess selectTLpProcessById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLpProcess 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLpProcess> selectTLpProcessList(TLpProcess tLpProcess);

    /**
     * 新增【请填写功能名称】
     *
     * @param tLpProcess 【请填写功能名称】
     * @return 结果
     */
    public int insertTLpProcess(TLpProcess tLpProcess);

    /**
     * 修改【请填写功能名称】
     *
     * @param tLpProcess 【请填写功能名称】
     * @return 结果
     */
    public int updateTLpProcess(TLpProcess tLpProcess);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLpProcessById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpProcessByIds(String[] ids);

    Integer selectProjectProcessUnique(TLpProcess tLpProcess);
}
