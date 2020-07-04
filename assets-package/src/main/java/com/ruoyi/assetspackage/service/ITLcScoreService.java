package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.TempCurAssetsPackage;
import com.ruoyi.assetspackage.domain.score.TLcScore;

import java.util.List;

/**
 * 催收评分Service接口
 * 
 * @author guozeqi
 * @date 2020-06-23
 */
public interface ITLcScoreService 
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
     * 批量删除催收评分
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcScoreByIds(String ids);

    /**
     * 删除催收评分信息
     * 
     * @param id 催收评分ID
     * @return 结果
     */
    public int deleteTLcScoreById(Long id);

    public List<TLcScore> buildParam(List<TempCurAssetsPackage> paramList);

    public void batchInsert(List<TLcScore> paramList);

    public List<TLcScore> selectNotScoreList(TLcScore param);

    public void batchUpdateScore(List<TLcScore> TLcScoreList);
    public void batchUpdateScore2(List<TLcScore> TLcScoreList);


}
