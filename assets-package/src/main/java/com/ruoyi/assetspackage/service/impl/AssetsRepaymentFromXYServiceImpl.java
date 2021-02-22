package com.ruoyi.assetspackage.service.impl;

import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.enums.IsCloseCaseEnum;
import com.ruoyi.assetspackage.enums.IsNoEnum;
import com.ruoyi.assetspackage.enums.TaskStatusEnum;
import com.ruoyi.assetspackage.enums.TaskTypeEnum;
import com.ruoyi.assetspackage.exception.ImportDataExcepion;
import com.ruoyi.assetspackage.mapper.AssetsRepaymentFromXYMapper;
import com.ruoyi.assetspackage.mapper.CurAssetsRepaymentPackageMapper;
import com.ruoyi.assetspackage.mapper.TaskMapper;
import com.ruoyi.assetspackage.service.*;
import com.ruoyi.assetspackage.util.ParseExcelUtil;
import com.ruoyi.assetspackage.util.RepaymentDataImportUtil;
import com.ruoyi.assetspackage.util.Response;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.domain.CloseCase;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RestTemplateUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 资产还款Service业务层处理
 *
 * @author guozeqi
 * @date 2020-07-27
 */
@Service
public class AssetsRepaymentFromXYServiceImpl implements IAssetsRepaymenFromXYService {
    protected final Logger logger = LoggerFactory.getLogger(AssetsRepaymentFromXYServiceImpl.class);
    @Autowired
    private AssetsRepaymentFromXYMapper assetsRepaymentFromXYMapper;
    @Autowired
    private ISysConfigService sysConfigService;



    /**
     * 查询还款明细
     * @param curAssetsRepaymentPackage
     * @return
     */
    @Override
    public List<CurAssetsRepaymentPackage> findCurAssetsRepaymentList(CurAssetsRepaymentPackage curAssetsRepaymentPackage) {
        return this.assetsRepaymentFromXYMapper.findCurAssetsRepaymentList(curAssetsRepaymentPackage);
    }

    @Override
    public List<CurAssetsRepaymentPackageXy> findCurAssetsRepaymentXyList(CurAssetsRepaymentPackage curAssetsRepaymentPackage) {
        return assetsRepaymentFromXYMapper.findCurAssetsRepaymentXyList(curAssetsRepaymentPackage);
    }

    /**
     * 查询委案金额、手别、资产批次号
     *
     */
    @Override
    public CurAssetsPackage findAssetsInfo(Map<String, String> param) {
        return this.assetsRepaymentFromXYMapper.findAssetsInfo(param);
    }

    /**
     * 查询业务归属人
     *
     */
    @Override
    public Map<String,String> findOwnerName(Map<String, String> param) {
        Map ownerNameAndJobNo = new HashMap();
        String ownerName = "";
        String jobNo = "";
        //匹配当天的
//        List<String> ownerNameByAssign = this.assetsRepaymentFromXYMapper.findOwnerNameByAssign(param);
        List<Map<String, String>> ownerNameByAssign = this.assetsRepaymentFromXYMapper.findOwnerNameByAssign(param);
        if(ownerNameByAssign.size() > 0){
            for (Map<String, String> map : ownerNameByAssign) {
                if(map != null){
                    String name = map.get("ownerName");
                    String no = map.get("jobNo");
                    if(name != null && !"".equals(name)){
                        ownerName = ownerName + ","+name;
                        jobNo = jobNo + ","+no;
                    }
                }
            }
        }
        //匹配当天之前的最后一个(结案的为空，未结案的展示)
        if("".equals(ownerName)){
            Map<String, Object> resultMap = this.assetsRepaymentFromXYMapper.findOwnerNameByAssign2(param);
            if(resultMap != null){
                Integer taskType = (Integer)resultMap.get("taskType");
                if(taskType != 7){
                    String name = (String)resultMap.get("ownerName");
                    String no = (String)resultMap.get("jobNo");
                    if(name != null && !"".equals(name)){
                        ownerName = ownerName + ","+name;
                        jobNo = jobNo + ","+no;
                    }
                }
            }
        }
        //兴业消金匹配任务表当前业务归属人
        String orgId = param.get("orgId");
        String xyOrgId = this.sysConfigService.selectConfigByKey("xyOrgId");
        if("".equals(ownerName) && xyOrgId.equals(orgId)){
//            String ownerNameByAssign3 = this.assetsRepaymentFromXYMapper.findOwnerNameByAssign3(param);
            Map<String, String> map = this.assetsRepaymentFromXYMapper.findOwnerNameByAssign3(param);
            if(map != null){
                String name = map.get("ownerName");
                String no = map.get("jobNo");
                if(name != null && !"".equals(name)){
                    ownerName = ownerName + ","+name;
                    jobNo = jobNo + ","+no;
                }
            }
        }
        if(!"".equals(ownerName)){
            ownerName = ownerName.substring(1);
        }
        if(!"".equals(jobNo)){
            jobNo = jobNo.substring(1);
        }

        ownerNameAndJobNo.put("ownerName",ownerName);
        ownerNameAndJobNo.put("jobNo",jobNo);
        return ownerNameAndJobNo;
    }


    /**
     * @param str 原字符串
     * @param sToFind 需要查找的字符串
     * @return 返回在原字符串中sToFind出现的次数
     */
    private static int countStr(String str,String sToFind) {
        int num = 0;
        while (str.contains(sToFind)) {
            str = str.substring(str.indexOf(sToFind) + sToFind.length());
            num ++;
        }
        return num;
    }
}
