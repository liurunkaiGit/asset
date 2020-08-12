package com.ruoyi.assetspackage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.domain.TempCurAssetsPackage;
import com.ruoyi.assetspackage.domain.score.TLcScore;
import com.ruoyi.assetspackage.mapper.OrgPackageMapper;
import com.ruoyi.assetspackage.mapper.TLcScoreMapper;
import com.ruoyi.assetspackage.service.ITLcScoreService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 催收评分Service业务层处理
 * 
 * @author guozeqi
 * @date 2020-06-23
 */
@Service
public class TLcScoreServiceImpl implements ITLcScoreService
{
    @Autowired
    private TLcScoreMapper tLcScoreMapper;
    @Autowired
    private OrgPackageMapper orgPackageMapper;

    /**
     * 查询催收评分
     * 
     * @param id 催收评分ID
     * @return 催收评分
     */
    @Override
    public TLcScore selectTLcScoreById(Long id)
    {
        return tLcScoreMapper.selectTLcScoreById(id);
    }

    /**
     * 查询催收评分列表
     * 
     * @param tLcScore 催收评分
     * @return 催收评分
     */
    @Override
    public List<TLcScore> selectTLcScoreList(TLcScore tLcScore)
    {
        return tLcScoreMapper.selectTLcScoreList(tLcScore);
    }

    /**
     * 新增催收评分
     * 
     * @param tLcScore 催收评分
     * @return 结果
     */
    @Override
    public int insertTLcScore(TLcScore tLcScore)
    {
        tLcScore.setCreateTime(DateUtils.getNowDate());
        return tLcScoreMapper.insertTLcScore(tLcScore);
    }

    /**
     * 修改催收评分
     * 
     * @param tLcScore 催收评分
     * @return 结果
     */
    @Override
    public int updateTLcScore(TLcScore tLcScore)
    {
        tLcScore.setUpdateTime(DateUtils.getNowDate());
        return tLcScoreMapper.updateTLcScore(tLcScore);
    }

    /**
     * 删除催收评分对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcScoreByIds(String ids)
    {
        return tLcScoreMapper.deleteTLcScoreByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除催收评分信息
     * 
     * @param id 催收评分ID
     * @return 结果
     */
    @Override
    public int deleteTLcScoreById(Long id)
    {
        return tLcScoreMapper.deleteTLcScoreById(id);
    }

    /**
     * 参数构建
     * @param paramList
     * @return
     */
    @Override
    public List<TLcScore> buildParam(List<TempCurAssetsPackage> paramList) {
        OrgPackage orgPackage = orgPackageMapper.selectOrgPackageByDeptId(paramList.get(0).getOrgId());
        String isAutoScore = orgPackage.getIsAutoScore();
        List<TLcScore> list = new ArrayList<>();
        for (TempCurAssetsPackage temp : paramList) {
            TLcScore tlcScore = new TLcScore();
            tlcScore.setOrgCasno(temp.getOrgCasno());
            tlcScore.setOrgId(temp.getOrgId());
            tlcScore.setOrgName(temp.getOrg());
            tlcScore.setImportBatchNo(temp.getImportBatchNo());
            tlcScore.setIsAutoScore(isAutoScore);
            tlcScore.setCreateBy(ShiroUtils.getLoginName());
            tlcScore.setCreateTime(new Date());
            tlcScore.setSendflag("0");
            list.add(tlcScore);
        }
        return list;
    }

    @Override
    public List<TLcScore> buildParam2(List<TempCurAssetsPackage> paramList) {
        OrgPackage orgPackage = orgPackageMapper.selectOrgPackageByDeptId(paramList.get(0).getOrgId());
        String isAutoScore = orgPackage.getIsAutoScore();
        List<TLcScore> list = new ArrayList<>();
        for (TempCurAssetsPackage temp : paramList) {
            TLcScore tlcScore = new TLcScore();
            tlcScore.setOrgCasno(temp.getOrgCasno());
            tlcScore.setOrgId(temp.getOrgId());
            tlcScore.setOrgName(temp.getOrg());
            tlcScore.setImportBatchNo(temp.getImportBatchNo());
            tlcScore.setIsAutoScore(isAutoScore);
            tlcScore.setCreateBy("0000");//吉象接口创建人
            tlcScore.setCreateTime(new Date());
            tlcScore.setSendflag("0");
            list.add(tlcScore);
        }
        return list;
    }


    @Override
    public void batchInsert(List<TLcScore> paramList) {
        int total = paramList.size();
        int index = 800;
        int pagesize = total/index;
        if(total <=index){
            this.tLcScoreMapper.batchInsert(paramList);
        }else{
            for(int i=0;i<pagesize;i++){
                List lt = paramList.subList(i*index, (i+1)*index);
                this.tLcScoreMapper.batchInsert(lt);

            }
            if(total % index != 0){
                List lt = paramList.subList(index * pagesize,total);
                this.tLcScoreMapper.batchInsert(lt);
            }
        }
    }

    @Override
    public List<TLcScore> selectNotScoreList(TLcScore param) {
        return this.tLcScoreMapper.selectNotScoreList(param);
    }

    @Override
    public List<TLcScore> selectScoreListForDuncase(TLcScore param) {
        return this.tLcScoreMapper.selectScoreListForDuncase(param);
    }

    @Override
    public void batchUpdateScore(List<TLcScore> TLcScoreList){
        this.tLcScoreMapper.batchUpdateScore(TLcScoreList);
    }

    @Override
    public void batchUpdateScore2(List<TLcScore> TLcScoreList){
        this.tLcScoreMapper.batchUpdateScore2(TLcScoreList);
    }

    @Override
    @Transactional
    public void batchUpdateDuncaseScore(List<TLcScore> TLcScoreList){
        this.tLcScoreMapper.batchUpdateDuncaseScore(TLcScoreList);
    }
}
