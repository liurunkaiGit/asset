package com.ruoyi.assetspackage.service;



import com.ruoyi.assetspackage.domain.CertificateConfig;

import java.util.List;

/**
 * 证件号码配置Service接口
 * 
 * @author guozeqi
 * @date 2020-08-24
 */
public interface ICertificateConfigService 
{
    /**
     * 查询证件号码配置
     * 
     * @param id 证件号码配置ID
     * @return 证件号码配置
     */
    public CertificateConfig selectCertificateConfigById(Long id);

    /**
     * 查询证件号码配置列表
     * 
     * @param certificateConfig 证件号码配置
     * @return 证件号码配置集合
     */
    public List<CertificateConfig> selectCertificateConfigList(CertificateConfig certificateConfig);

    /**
     * 新增证件号码配置
     * 
     * @param certificateConfig 证件号码配置
     * @return 结果
     */
    public int insertCertificateConfig(CertificateConfig certificateConfig);

    /**
     * 修改证件号码配置
     * 
     * @param certificateConfig 证件号码配置
     * @return 结果
     */
    public int updateCertificateConfig(CertificateConfig certificateConfig);

    /**
     * 批量删除证件号码配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCertificateConfigByIds(String ids);

    /**
     * 删除证件号码配置信息
     * 
     * @param id 证件号码配置ID
     * @return 结果
     */
    public int deleteCertificateConfigById(Long id);
}
