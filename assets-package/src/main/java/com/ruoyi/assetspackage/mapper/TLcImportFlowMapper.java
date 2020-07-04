package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.TLcImportFlow;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author liurunkai
 * @date 2020-03-24
 */
public interface TLcImportFlowMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TLcImportFlow selectTLcImportFlowById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcImportFlow 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLcImportFlow> selectTLcImportFlowList(TLcImportFlow tLcImportFlow);

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcImportFlow 【请填写功能名称】
     * @return 结果
     */
    public int insertTLcImportFlow(TLcImportFlow tLcImportFlow);

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcImportFlow 【请填写功能名称】
     * @return 结果
     */
    public int updateTLcImportFlow(TLcImportFlow tLcImportFlow);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLcImportFlowById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcImportFlowByIds(String[] ids);
}
