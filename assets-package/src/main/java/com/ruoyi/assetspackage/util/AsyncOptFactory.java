package com.ruoyi.assetspackage.util;

import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.assetspackage.domain.score.CollectionScoreRequest;
import com.ruoyi.assetspackage.domain.score.CollectionScoreResponse;
import com.ruoyi.assetspackage.domain.score.TLcScore;
import com.ruoyi.assetspackage.service.ICurAssetsPackageService;
import com.ruoyi.assetspackage.service.ITLcScoreService;
import com.ruoyi.common.utils.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author guozeqi
 * @create 2020-06-09
 */

public class AsyncOptFactory {

    private static final Logger logger = LoggerFactory.getLogger("AsyncOptFactory");



    /**
     * 单例模式
     */
    private AsyncOptFactory(){}

    private static AsyncOptFactory me = new AsyncOptFactory();

    public static AsyncOptFactory getFactory()
    {
        return me;
    }



    /**
     * 自动评分执行器
     * @param paramList
     * @return
     */
    public TimerTask synAutoScoreExecute(final List<TLcScore> paramList)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                List<TLcScore> updateList = new ArrayList<>();
                for (TLcScore tLcScore : paramList) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    CurAssetsPackage assetParam = new CurAssetsPackage();
                    assetParam.setImportBatchNo(tLcScore.getImportBatchNo());
                    assetParam.setOrgCasno(tLcScore.getOrgCasno());
                    assetParam.setOrgId(tLcScore.getOrgId());
                    List<CurAssetsPackage> curAssetsPackageList = SpringUtils.getBean(ICurAssetsPackageService.class).selectCurAssetsPackageList(assetParam);
                    CurAssetsPackage curAsset = null;
                    if(curAssetsPackageList.size()>0){
                        curAsset = curAssetsPackageList.get(0);
                    }
                    if(curAsset==null){
                        logger.error("获取资产信息失败,参数为orgId="+tLcScore.getOrgId()+",caseNo="+tLcScore.getOrgCasno()+",importBatchNo="+tLcScore.getImportBatchNo());
                        continue;
                    }
                    //查询度小满获取评分
                    Map<String,String> map = new HashMap<>();
                    map.put("curName",curAsset.getCurName());
                    map.put("certificateNo",curAsset.getCertificateNo());
                    map.put("customerMobile",curAsset.getCustomerMobile());
                    if(curAsset.getOverdueDays() != null && !"".equals(curAsset.getOverdueDays())){
                        map.put("overdueDays",curAsset.getOverdueDays());
                    }
                    if(curAsset.getWaYe() != null){
                        map.put("waYe",curAsset.getWaYe().toPlainString());
                    }
                    if(curAsset.getFirstYqDate() != null){
                        map.put("firstYqDate",sdf.format(curAsset.getFirstYqDate()));
                    }
                    map.put("orgName",curAsset.getOrg());
                    try {
                        CollectionScoreRequest collectionScoreRequest = CollectionScoreUtil.buildParam(map);
                        logger.info("获取度小满评分请求参数{}",collectionScoreRequest);
                        CollectionScoreResponse result = CollectionScoreUtil.doPost(collectionScoreRequest);
                        logger.info("获取度小满评分结果{}",result);
                        Long score = CollectionScoreUtil.getScore(result);
                        tLcScore.setScore(score);
                        tLcScore.setUpdateTime(new Date());
                        updateList.add(tLcScore);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("获取度小满评分失败{}",e);
                    }
                }
                //更新评分
                if(updateList.size()>0){
                    SpringUtils.getBean(ITLcScoreService.class).batchUpdateScore(updateList);
                }
            }
        };
    }



    /**
     * 更新案件表评分执行器
     * @param paramList
     * @return
     */
    public TimerTask synUpdateScoreForDuncaseExecute(final List<TLcScore> paramList)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                //更新评分
                if(paramList.size()>0){
                    SpringUtils.getBean(ITLcScoreService.class).batchUpdateDuncaseScore(paramList);
                }
            }
        };
    }






}
