package com.ruoyi.assetspackage.task;

import com.ruoyi.assetspackage.domain.score.TLcScore;
import com.ruoyi.assetspackage.service.ITLcScoreService;
import com.ruoyi.assetspackage.util.AsyncOptFactory;
import com.ruoyi.framework.manager.AsyncManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author guozeqi
 * @create 2020-06-23
 */

@Slf4j
@Component("ScoreTimer")
public class ScoreTimer
{

    @Autowired
    private ITLcScoreService tlcScoreService;


    /**
     * 获取度小满评分并更新
     */
    public void autoScoreTimer()
    {
        log.info("执行自动评分定时任务开始============================");
        //根据条件查询自动评分机构未评分的资产
        TLcScore tLcScore = new TLcScore();
        tLcScore.setIsAutoScore("1");//自动评分
        List<TLcScore> tLcScoreList = tlcScoreService.selectNotScoreList(tLcScore);//默认100条
        if(tLcScoreList.size()>0){
            AsyncManager.me().execute(AsyncOptFactory.getFactory().synAutoScoreExecute(tLcScoreList));
        }

    }



}