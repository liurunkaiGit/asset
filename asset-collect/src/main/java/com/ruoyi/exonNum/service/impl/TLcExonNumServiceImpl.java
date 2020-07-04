package com.ruoyi.exonNum.service.impl;

import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.exonNum.domain.TLcExonNum;
import com.ruoyi.exonNum.mapper.TLcExonNumMapper;
import com.ruoyi.exonNum.service.ITLcExonNumService;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author liurunkai
 * @date 2020-04-21
 */
@Service
public class TLcExonNumServiceImpl implements ITLcExonNumService {
    @Autowired
    private TLcExonNumMapper tLcExonNumMapper;
    @Autowired
    private IOrgPackageService orgPackageService;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TLcExonNum selectTLcExonNumById(Long id) {
        return tLcExonNumMapper.selectTLcExonNumById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcExonNum 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TLcExonNum> selectTLcExonNumList(TLcExonNum tLcExonNum) {
        return tLcExonNumMapper.selectTLcExonNumList(tLcExonNum);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcExonNum 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTLcExonNum(TLcExonNum tLcExonNum) {
        OrgPackage orgPackage = new OrgPackage();
        orgPackage.setDeptId(tLcExonNum.getOrgId());
        tLcExonNum.setOrgName(this.orgPackageService.selectOrgPackageList(orgPackage).get(0).getOrgName());
        tLcExonNum.setExonNumCount(tLcExonNum.getExonNum().split(";").length);
        tLcExonNum.setCreateBy(ShiroUtils.getSysUser().getUserId().toString());
        tLcExonNum.setUpdateBy(ShiroUtils.getSysUser().getUserId().toString());
        return tLcExonNumMapper.insertTLcExonNum(tLcExonNum);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcExonNum 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTLcExonNum(TLcExonNum tLcExonNum) {
        OrgPackage orgPackage = new OrgPackage();
        orgPackage.setDeptId(tLcExonNum.getOrgId());
        tLcExonNum.setOrgName(this.orgPackageService.selectOrgPackageList(orgPackage).get(0).getOrgName());
        tLcExonNum.setExonNumCount(tLcExonNum.getExonNum().split(";").length);
        tLcExonNum.setUpdateBy(ShiroUtils.getSysUser().getUserId().toString());
        return tLcExonNumMapper.updateTLcExonNum(tLcExonNum);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcExonNumByIds(String ids) {
        return tLcExonNumMapper.deleteTLcExonNumByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTLcExonNumById(Long id) {
        return tLcExonNumMapper.deleteTLcExonNumById(id);
    }
}
