package com.ruoyi.orgSpeechConf.service;

import com.ruoyi.orgSpeechConf.domain.TLcOrgSpeechcraftConf;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author liurunkai
 * @date 2020-05-18
 */
public interface ITLcOrgSpeechcraftConfService {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TLcOrgSpeechcraftConf selectTLcOrgSpeechcraftConfById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcOrgSpeechcraftConf 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLcOrgSpeechcraftConf> selectTLcOrgSpeechcraftConfList(TLcOrgSpeechcraftConf tLcOrgSpeechcraftConf);

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcOrgSpeechcraftConf 【请填写功能名称】
     * @return 结果
     */
    public int insertTLcOrgSpeechcraftConf(TLcOrgSpeechcraftConf tLcOrgSpeechcraftConf);

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcOrgSpeechcraftConf 【请填写功能名称】
     * @return 结果
     */
    public int updateTLcOrgSpeechcraftConf(TLcOrgSpeechcraftConf tLcOrgSpeechcraftConf);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcOrgSpeechcraftConfByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLcOrgSpeechcraftConfById(Long id);

    TLcOrgSpeechcraftConf selectTLcOrgSpeechcraftConfByOrgId(Long orgId);

    Integer selectUsedTotalConcurrentValue();

    String checkOrgIdUnique(String orgId);
}
