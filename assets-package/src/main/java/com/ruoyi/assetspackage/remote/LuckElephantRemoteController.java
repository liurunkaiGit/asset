package com.ruoyi.assetspackage.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.assetspackage.domain.luckElephant.*;
import com.ruoyi.assetspackage.service.IInterfaceInfoService;
import com.ruoyi.assetspackage.service.ILuckElephantRemoteService;
import com.ruoyi.common.utils.AesUtils;
import com.ruoyi.system.service.ISysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guozeqi
 * @create 2020-08-06
 */
@RestController
@RequestMapping("/remote/luckElephant")
public class LuckElephantRemoteController {

    private Logger logger = LoggerFactory.getLogger(AssetsRemoteController.class);

    private  String token = "";

    @Autowired
    private ILuckElephantRemoteService luckElephantRemoteService;
    @Autowired
    private IInterfaceInfoService interfaceInfoService;
    @Autowired
    private ISysConfigService sysConfigService;

    @ModelAttribute
    public void getConfig(){
        token = sysConfigService.selectConfigByKey("jxphToken");
    }

    /**
     * 资产下发接口
     * @param requestStr
     * @return
     */
    @PostMapping(value = "/addAssets",consumes = "text/plain;charset=utf-8")
    @ResponseBody
    public String addAssets(@RequestBody String requestStr){
        LuckElephantAddAssetResponse response = new LuckElephantAddAssetResponse();
        LuckElephantAddAssetRequest request = null;
        String curDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        logger.info("吉象添加资产接收到的参数是request:"+requestStr);
        if(requestStr==null || "".equals(requestStr)){
            response.setRetTime(curDate);
            response.setRetCode(LuckElephantCodeEnum.success.getCode());
            response.setRetMsg("请求参数为空");
            return JSON.toJSONString(response);
        }
        try {
            //解密字符串
            String content = AesUtils.decrypt(requestStr, token);
            logger.info("吉象添加资产解密后的字符串content:"+content);
            //字符串转对象
            String jsonStr = JSON.parseObject(content).toJSONString();
            request = JSONObject.parseObject(jsonStr, LuckElephantAddAssetRequest.class);
            //新增资产
            response = this.luckElephantRemoteService.batchAddAssets(request,curDate);
            //插入日志记录表
            this.addInterfaceLog(response);
            return JSON.toJSONString(response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("吉象添加资产异常:",e);
            response.setRetTime(curDate);
            response.setRetCode(LuckElephantCodeEnum.error.getCode());
            response.setRetMsg("失败");
            //插入日志记录表
            this.addInterfaceLog(response);
            return JSON.toJSONString(response);
        }finally {
            if(request != null){
                String batchNo = request.getBatchNo();
                if(batchNo != null && !"".equals(batchNo)){
                    this.luckElephantRemoteService.deleteTempTable(batchNo);
                }
            }
        }
    }

    /**
     * 资产更新接口
     * @param requestStr
     * @return
     */
    @PostMapping(value = "/updateAssets",consumes = "text/plain;charset=utf-8")
    @ResponseBody
    public String updateAssets(@RequestBody String requestStr){
        LuckElephantUpdateAssetResponse response = new LuckElephantUpdateAssetResponse();
        LuckElephantUpdateAssetRequest request = null;
        String curDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        logger.info("吉象更新资产接收到的参数是request:"+requestStr);
        if(requestStr==null || "".equals(requestStr)){
            response.setRetTime(curDate);
            response.setRetCode(LuckElephantCodeEnum.success.getCode());
            response.setRetMsg("请求参数为空");
            return JSON.toJSONString(response);
        }
        try {
            //解密字符串
            String content = AesUtils.decrypt(requestStr, token);
            logger.info("吉象更新资产解密后的字符串content:"+content);
            //字符串转对象
            String jsonStr = JSON.parseObject(content).toJSONString();
            request = JSONObject.parseObject(jsonStr, LuckElephantUpdateAssetRequest.class);
            //更新资产
            response = this.luckElephantRemoteService.batchUpdateAssets(request,curDate);
            //插入日志记录表
            this.addInterfaceLog(response);
            return JSON.toJSONString(response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("吉象更新资产异常:",e);
            response.setRetTime(curDate);
            response.setRetCode(LuckElephantCodeEnum.error.getCode());
            response.setRetMsg("失败");
            //插入日志记录表
            this.addInterfaceLog(response);
            return JSON.toJSONString(response);
        }finally {
            if(request != null){
                String batchNo = request.getBatchNo();
                if(batchNo != null && !"".equals(batchNo)){
                    this.luckElephantRemoteService.deleteTempTable(batchNo);
                }
            }
        }
    }


    /**
     * 资产还款接口
     * @param requestStr
     * @return
     */
    @PostMapping(value = "/repaymentAssets",consumes = "text/plain;charset=utf-8")
    @ResponseBody
    public String repaymentAssets(@RequestBody String requestStr){
        LuckElephantRepaymentAssetResponse response = new LuckElephantRepaymentAssetResponse();
        LuckElephantRepaymentAssetRequest request = null;
        String curDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        logger.info("吉象资产还款接收到的参数是request:"+requestStr);
        if(requestStr==null || "".equals(requestStr)){
            response.setRetTime(curDate);
            response.setRetCode(LuckElephantCodeEnum.success.getCode());
            response.setRetMsg("请求参数为空");
            return JSON.toJSONString(response);
        }
        try {
            //解密字符串
            String content = AesUtils.decrypt(requestStr, token);
            logger.info("吉象资产还款解密后的字符串content:"+content);
            //字符串转对象
            String jsonStr = JSON.parseObject(content).toJSONString();
            request = JSONObject.parseObject(jsonStr, LuckElephantRepaymentAssetRequest.class);
            //资产还款
            response = this.luckElephantRemoteService.batchRepaymentAssets(request,curDate);
            //插入日志记录表
            this.addInterfaceLog(response);
            return JSON.toJSONString(response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("吉象资产还款异常:",e);
            response.setRetTime(curDate);
            response.setRetCode(LuckElephantCodeEnum.error.getCode());
            response.setRetMsg("失败");
            //插入日志记录表
            this.addInterfaceLog(response);
            return JSON.toJSONString(response);
        }finally {
            if(request != null){
                this.luckElephantRemoteService.deleteRepaymentTempTable();
            }
        }
    }


    /**
     * 添加接口日志
     * @param param
     */
    private void addInterfaceLog(Object param){
        if(param instanceof LuckElephantAddAssetResponse){
            LuckElephantAddAssetResponse response = (LuckElephantAddAssetResponse) param;
            //插入日志记录表
            InterfaceInfo info = new InterfaceInfo();
            try {
                info.setName("新案下发接口");
                info.setCode(response.getRetCode());
                info.setInfo(response.getRetMsg());
                info.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(response.getRetTime()));
                String status = "0000".equals(response.getRetCode())?"0":"1";
                info.setStatus(status);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            interfaceInfoService.insertInterfaceInfo(info);
        }else if(param instanceof LuckElephantUpdateAssetResponse){
            LuckElephantUpdateAssetResponse response = (LuckElephantUpdateAssetResponse) param;
            //插入日志记录表
            InterfaceInfo info = new InterfaceInfo();
            try {
                info.setName("案件更新接口");
                info.setCode(response.getRetCode());
                info.setInfo(response.getRetMsg());
                info.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(response.getRetTime()));
                String status = "0000".equals(response.getRetCode())?"0":"1";
                info.setStatus(status);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            interfaceInfoService.insertInterfaceInfo(info);
        }else {
            LuckElephantRepaymentAssetResponse response = (LuckElephantRepaymentAssetResponse) param;
            //插入日志记录表
            InterfaceInfo info = new InterfaceInfo();
            try {
                info.setName("还款更新接口");
                info.setCode(response.getRetCode());
                info.setInfo(response.getRetMsg());
                info.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(response.getRetTime()));
                String status = "0000".equals(response.getRetCode())?"0":"1";
                info.setStatus(status);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            interfaceInfoService.insertInterfaceInfo(info);
        }
    }



}
