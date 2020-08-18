package com.ruoyi.letter.service.impl;

import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.enums.ApplyStatusEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.letter.domain.TLcLetter;
import com.ruoyi.letter.mapper.TLcLetterMapper;
import com.ruoyi.letter.service.ITLcLetterService;
import com.ruoyi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author liurunkai
 * @date 2020-08-17
 */
@Service
public class TLcLetterServiceImpl implements ITLcLetterService {
    @Autowired
    private TLcLetterMapper tLcLetterMapper;
    @Autowired
    private IOrgPackageService orgPackageService;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TLcLetter selectTLcLetterById(Integer id) {
        return tLcLetterMapper.selectTLcLetterById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param tLcLetter 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TLcLetter> selectTLcLetterList(TLcLetter tLcLetter) {
        return tLcLetterMapper.selectTLcLetterList(tLcLetter);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param tLcLetter 【请填写功能名称】
     * @return 结果
     */
    @Override
    public Response insertTLcLetter(TLcLetter tLcLetter) {
        tLcLetter.setOwnerId(ShiroUtils.getSysUser().getUserId());
        tLcLetter.setOwnerName(ShiroUtils.getSysUser().getUserName());
        tLcLetter.setOrgName(this.orgPackageService.selectOrgPackageByOrgId(tLcLetter.getOrgId().toString()).getOrgName());
        tLcLetter.setApplyStatus(ApplyStatusEnum.WAIT_APPLY.getCode());
        tLcLetter.setCreateBy(ShiroUtils.getSysUser().getUserId().toString());
        tLcLetterMapper.insertTLcLetter(tLcLetter);
        return Response.success(null);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcLetter 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTLcLetter(TLcLetter tLcLetter) {
        tLcLetter.setUpdateTime(DateUtils.getNowDate());
        return tLcLetterMapper.updateTLcLetter(tLcLetter);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcLetterByIds(String ids) {
        return tLcLetterMapper.deleteTLcLetterByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTLcLetterById(Integer id) {
        return tLcLetterMapper.deleteTLcLetterById(id);
    }

    @Override
    public int apply(String ids, Integer status) {
        return this.tLcLetterMapper.apply(ids.split(","),status,ShiroUtils.getSysUser().getUserId());
    }

    @Override
    public List<TLcLetter> selectLetterListByIds(String letterIds) {
        return this.tLcLetterMapper.selectLetterListByIds(letterIds.split(","));
    }

    @Override
    public TLcLetter selectTLcLetter(TLcLetter letter) {
        return this.tLcLetterMapper.selectTLcLetter(letter);
    }
}
