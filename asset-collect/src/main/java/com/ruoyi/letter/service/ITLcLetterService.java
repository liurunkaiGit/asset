package com.ruoyi.letter.service;

import com.ruoyi.letter.domain.TLcLetter;
import com.ruoyi.utils.Response;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author liurunkai
 * @date 2020-08-17
 */
public interface ITLcLetterService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TLcLetter selectTLcLetterById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tLcLetter 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TLcLetter> selectTLcLetterList(TLcLetter tLcLetter);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tLcLetter 【请填写功能名称】
     * @return 结果
     */
    public Response insertTLcLetter(TLcLetter tLcLetter);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tLcLetter 【请填写功能名称】
     * @return 结果
     */
    public int updateTLcLetter(TLcLetter tLcLetter);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcLetterByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLcLetterById(Integer id);

    int apply(String ids, Integer status);

    List<TLcLetter> selectLetterListByIds(String letterIds);

    TLcLetter selectTLcLetter(TLcLetter letter);
}
