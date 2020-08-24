package com.ruoyi.assetspackage.service.impl;


import com.ruoyi.assetspackage.domain.CertificateConfig;
import com.ruoyi.assetspackage.mapper.CertificateConfigMapper;
import com.ruoyi.assetspackage.service.ICertificateConfigService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 证件号码配置Service业务层处理
 * 
 * @author guozeqi
 * @date 2020-08-24
 */
@Service
public class CertificateConfigServiceImpl implements ICertificateConfigService
{
    @Autowired
    private CertificateConfigMapper certificateConfigMapper;

    /**
     * 查询证件号码配置
     * 
     * @param id 证件号码配置ID
     * @return 证件号码配置
     */
    @Override
    public CertificateConfig selectCertificateConfigById(Long id)
    {
        return certificateConfigMapper.selectCertificateConfigById(id);
    }

    /**
     * 查询证件号码配置列表
     * 
     * @param certificateConfig 证件号码配置
     * @return 证件号码配置
     */
    @Override
    public List<CertificateConfig> selectCertificateConfigList(CertificateConfig certificateConfig)
    {
        return certificateConfigMapper.selectCertificateConfigList(certificateConfig);
    }

    /**
     * 新增证件号码配置
     * 
     * @param certificateConfig 证件号码配置
     * @return 结果
     */
    @Override
    public int insertCertificateConfig(CertificateConfig certificateConfig)
    {
        certificateConfig.setCreateTime(DateUtils.getNowDate());
        return certificateConfigMapper.insertCertificateConfig(certificateConfig);
    }

    /**
     * 修改证件号码配置
     * 
     * @param certificateConfig 证件号码配置
     * @return 结果
     */
    @Override
    public int updateCertificateConfig(CertificateConfig certificateConfig)
    {
        certificateConfig.setUpdateTime(DateUtils.getNowDate());
        return certificateConfigMapper.updateCertificateConfig(certificateConfig);
    }

    /**
     * 删除证件号码配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCertificateConfigByIds(String ids)
    {
        return certificateConfigMapper.deleteCertificateConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除证件号码配置信息
     * 
     * @param id 证件号码配置ID
     * @return 结果
     */
    @Override
    public int deleteCertificateConfigById(Long id)
    {
        return certificateConfigMapper.deleteCertificateConfigById(id);
    }
}
