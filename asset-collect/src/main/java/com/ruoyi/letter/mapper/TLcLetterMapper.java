package com.ruoyi.letter.mapper;

import com.ruoyi.letter.domain.TLcLetter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author liurunkai
 * @date 2020-08-17
 */
public interface TLcLetterMapper {
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
    public int insertTLcLetter(TLcLetter tLcLetter);

    /**
     * 修改【请填写功能名称】
     *
     * @param tLcLetter 【请填写功能名称】
     * @return 结果
     */
    public int updateTLcLetter(TLcLetter tLcLetter);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTLcLetterById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcLetterByIds(String[] ids);

    int apply(@Param("ids") String[] ids, @Param("applyStatus") Integer applyStatus, @Param("updateBy") Long updateBy);

    List<TLcLetter> selectLetterListByIds(@Param("letterIds") String[] letterIds);

    TLcLetter selectTLcLetter(TLcLetter letter);
}
