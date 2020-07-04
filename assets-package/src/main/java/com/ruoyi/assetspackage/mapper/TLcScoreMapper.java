package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.score.TLcScore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 催收评分Mapper接口
 * 
 * @author guozeqi
 * @date 2020-06-23
 */
public interface TLcScoreMapper 
{
    /**
     * 查询催收评分
     * 
     * @param id 催收评分ID
     * @return 催收评分
     */
    public TLcScore selectTLcScoreById(Long id);

    /**
     * 查询催收评分列表
     * 
     * @param tLcScore 催收评分
     * @return 催收评分集合
     */
    public List<TLcScore> selectTLcScoreList(TLcScore tLcScore);

    /**
     * 新增催收评分
     * 
     * @param tLcScore 催收评分
     * @return 结果
     */
    public int insertTLcScore(TLcScore tLcScore);

    /**
     * 修改催收评分
     * 
     * @param tLcScore 催收评分
     * @return 结果
     */
    public int updateTLcScore(TLcScore tLcScore);

    /**
     * 删除催收评分
     * 
     * @param id 催收评分ID
     * @return 结果
     */
    public int deleteTLcScoreById(Long id);

    /**
     * 批量删除催收评分
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcScoreByIds(String[] ids);

    public void batchInsert(List<TLcScore> paramList);

    public List<TLcScore> selectNotScoreList(TLcScore param);

    public void batchUpdateScore(@Param(value="TLcScoreList")List<TLcScore> TLcScoreList);

    public void batchUpdateScore2(@Param(value="TLcScoreList")List<TLcScore> TLcScoreList);
}
