package com.ruoyi.assetspackage.task;

import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.assetspackage.mapper.AssetsImportFromXYMapper;
import com.ruoyi.assetspackage.service.ICurAssetsPackageService;
import com.ruoyi.assetspackage.service.impl.AssetsImportFromXYServiceImpl;
import com.ruoyi.assetspackage.service.impl.CurAssetsPackageServiceImpl;
import com.ruoyi.assetspackage.service.impl.CurAssetsRepaymentPackageServiceImpl;
import com.ruoyi.common.domain.CloseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author guozeqi
 * @create 2020-07-21
 */
@Slf4j
@Component("CloseTimer")
public class CloseTimer {

    @Autowired
    private CurAssetsPackageServiceImpl CurAssetsPackageServiceImpl;
    @Autowired
    private AssetsImportFromXYMapper assetsImportFromXYMapper;
    @Autowired
    private CurAssetsRepaymentPackageServiceImpl curAssetsRepaymentPackageServiceImpl;

    @Value("${isEnableTimer}")
    private Boolean isEnableTimer;

    /**
     * 定时结案
     */
    public void autoCloseCaseTimer()
    {
        if(!isEnableTimer){
            return;
        }
        log.info("执行定时结案任务开始============================");

        Date date = new Date();
        long now = date.getTime();
        try {
            List<CurAssetsPackage> closeList = new ArrayList<>();
            CurAssetsPackage param = new CurAssetsPackage();
            param.setCloseCase("0");
            List<CurAssetsPackage> curAssetsList = CurAssetsPackageServiceImpl.selectCurAssetsPackageList(param);
            for (CurAssetsPackage assets : curAssetsList) {
                long tar = assets.getTar().getTime();
                if(tar < now){
                    //加入结案集合
                    closeList.add(assets);
                }
            }
            //结案处理
            this.updateCloseCase(closeList,date,"2");//到期回收结案
        } catch (Exception e) {
            e.printStackTrace();
            log.error("执行定时结案失败{}",e);
        }
    }


    /**
     * 结案
     * @param paramList
     * @param closeCaseDate 临时表的创建时间
     * @throws Exception
     */
    private void updateCloseCase(List<CurAssetsPackage> paramList,Date closeCaseDate,String isExitCollect) throws Exception{
        if(paramList != null && paramList.size() >0) {
            List<CloseCase> remoteList = new ArrayList<>();
            for (CurAssetsPackage curAssetsPackage : paramList) {
                curAssetsPackage.setCloseCaseDate(closeCaseDate);//临时表的创建时间
                curAssetsPackage.setIsExitCollect(isExitCollect);
                remoteList.add(buildCloseCase(curAssetsPackage));
            }
            int total = paramList.size();
            int index = 500;
            int pagesize = total / index;
            if (total <= index) {
                this.assetsImportFromXYMapper.batchUpdateCloseCase(paramList);
            } else {
                for (int i = 0; i < pagesize; i++) {
                    List lt = paramList.subList(i * index, (i + 1) * index);
                    this.assetsImportFromXYMapper.batchUpdateCloseCase(lt);

                }
                if (total % index != 0) {
                    List lt = paramList.subList(index * pagesize, total);
                    this.assetsImportFromXYMapper.batchUpdateCloseCase(lt);
                }
            }

            //催收模块结案
            curAssetsRepaymentPackageServiceImpl.closeCase2(remoteList);
        }

    }

    private CloseCase buildCloseCase(CurAssetsPackage curAssetsPackage) {
        return CloseCase.builder()
                .isExitCollect(curAssetsPackage.getIsExitCollect())
                .caseNo(curAssetsPackage.getOrgCasno())
                .importBatchNo(curAssetsPackage.getImportBatchNo())
                .orgId(curAssetsPackage.getOrgId())
                .isClose(3)
                .dqyhje(curAssetsPackage.getDqyhje())
                .jayhje(curAssetsPackage.getWaYe())
                .build();
    }


}
