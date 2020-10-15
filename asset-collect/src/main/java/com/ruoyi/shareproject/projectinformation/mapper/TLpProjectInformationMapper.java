package com.ruoyi.shareproject.projectinformation.mapper;

import com.ruoyi.shareproject.projectinformation.domain.TLpProjectInformation;

import java.util.List;
public interface TLpProjectInformationMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TLpProjectInformation selectTLpProjectInformationById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLpProjectInformation 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLpProjectInformation> selectTLpProjectInformationList(TLpProjectInformation tLpProjectInformation);

    /**
     * 新增【请填写功能名称】
     *
     * @param tLpProjectInformation 【请填写功能名称】
     * @return 结果
     */
    public int insertTLpProjectInformation(TLpProjectInformation tLpProjectInformation);

    /**
     * 修改【请填写功能名称】
     *
     * @param tLpProjectInformation 【请填写功能名称】
     * @return 结果
     */
    public int updateTLpProjectInformation(TLpProjectInformation tLpProjectInformation);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLpProjectInformationById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpProjectInformationByIds(String[] ids);
}
