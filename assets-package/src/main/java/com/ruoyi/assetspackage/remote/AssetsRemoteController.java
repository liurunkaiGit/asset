package com.ruoyi.assetspackage.remote;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.assetspackage.domain.CurAssetsRepaymentPackage;
import com.ruoyi.assetspackage.enums.RemoteResult;
import com.ruoyi.assetspackage.service.ICurAssetsPackageService;
import com.ruoyi.assetspackage.service.ICurAssetsRepaymentPackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author guozeqi
 * @create 2020-01-13
 */

@RestController
@RequestMapping("/assetspackage/remote")
public class AssetsRemoteController {

    @Autowired
    private ICurAssetsPackageService curAssetsPackageService;

    @Autowired
    private ICurAssetsRepaymentPackageService curAssetsRepaymentPackageService;

    private Logger logger = LoggerFactory.getLogger(AssetsRemoteController.class);
    /**
     * 根据机构案件号查询还款明细
     * @param orgCasNoStr
     * @return
     */
    @PostMapping("/getAssetsRepaymentInfoByOrgCasNo")
    @ResponseBody
    public RemoteResult getAssetsRepaymentInfoByOrgCasNo(@RequestBody String orgCasNoStr){
        logger.info("查询还款明细接口接收参数："+orgCasNoStr);
        List<CurAssetsRepaymentPackage> resultList = new ArrayList<CurAssetsRepaymentPackage>();
        String result="";
        try {
            orgCasNoStr = JSON.parseObject(orgCasNoStr).get("orgCasNoStr").toString();
            String[] orgCasNos = orgCasNoStr.split(",");
            for (String orgCasNo : orgCasNos) {
                //根据机构案件号查询还款明细
                CurAssetsRepaymentPackage curAssetsRepaymentPackage = new CurAssetsRepaymentPackage();
                curAssetsRepaymentPackage.setOrgCasno(orgCasNo);
                resultList = curAssetsRepaymentPackageService.selectCurAssetsRepaymentPackageList(curAssetsRepaymentPackage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RemoteResult.error("失败",null);
        }
        return RemoteResult.success("成功",resultList);
    }

    /**
     * 查询单条资产接口
     * @param
     * @param
     * @return
     */
    @PostMapping("/selectOneAsset")
    @ResponseBody
    public RemoteResult selectOneAsset(String orgCaseNo,String importBatchNo){
        logger.info("查询单条资产接口接收参数：orgCaseNo="+orgCaseNo+",importBatchNo="+importBatchNo);
        CurAssetsPackage result = null;
        if(orgCaseNo==null || "".equals(orgCaseNo) || importBatchNo==null || "".equals(importBatchNo) ){
            return RemoteResult.error("失败",result);
        }
        try {
            result = this.curAssetsPackageService.selectAsset(orgCaseNo,importBatchNo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用查询单条资产接口失败{}",e);
            return RemoteResult.error("失败",result);
        }
        return RemoteResult.success("成功",result);
    }

}
