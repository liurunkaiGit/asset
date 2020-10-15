package com.ruoyi.shareproject.projectinformation.service;

import com.ruoyi.shareproject.projectinformation.domain.TLpProjectInformation;

import java.util.List;

/**
 * 【项目信息管理】Service接口
 *
 * @author gaohg
 * @date 2020-10-14
 */
public interface ITLpProjectInformationService {

    /**
     * 查询【项目信息管理】
     *
     * @param id 【项目信息管理】ID
     * @return 【项目信息管理】
     */
    public TLpProjectInformation selectTLpProjectInformationById(Long id);

    /**
     * 查询【项目信息管理】列表
     *
     * @param tLpProjectInformation 【项目信息管理】
     * @return 【项目信息管理】集合
     */
    public List<TLpProjectInformation> selectTLpProjectInformationList(TLpProjectInformation tLpProjectInformation);

    /**
     * 新增【项目信息管理】
     *
     * @param tLpProjectInformation 【项目信息管理】
     * @return 结果
     */
    public int insertTLpProjectInformation(TLpProjectInformation tLpProjectInformation);

    /**
     * 修改【项目信息管理】
     *
     * @param tLpProjectInformation 【项目信息管理】
     * @return 结果
     */
    public int updateTLpProjectInformation(TLpProjectInformation tLpProjectInformation);

    /**
     * 批量删除【项目信息管理】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLpProjectInformationByIds(String ids);

    /**
     * 删除【项目信息管理】信息
     *
     * @param id 【项目信息管理】ID
     * @return 结果
     */
    public int deleteTLpProjectInformationById(Long id);
}
