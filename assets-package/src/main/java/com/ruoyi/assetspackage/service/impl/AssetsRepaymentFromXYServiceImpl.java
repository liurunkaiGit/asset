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



    /**
     * 查询还款明细
     * @param curAssetsRepaymentPackage
     * @return
     */
    @Override
    public List<CurAssetsRepaymentPackage> findCurAssetsRepaymentList(CurAssetsRepaymentPackage curAssetsRepaymentPackage) {
        return this.assetsRepaymentFromXYMapper.findCurAssetsRepaymentList(curAssetsRepaymentPackage);
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
    public String findOwnerName(Map<String, String> param) {
        String ownerName = "";
        List<String> ownerNameByAssign = this.assetsRepaymentFromXYMapper.findOwnerNameByAssign(param);
        if(ownerNameByAssign.size() > 0){
            for (String ownerAssign : ownerNameByAssign) {
                if(ownerAssign != null && !"".equals(ownerAssign)){
                    ownerName = ownerName + ","+ownerAssign;
                }
            }
        }
        //轨迹表数据为空，则查任务表
        if("".equals(ownerName)){
            List<String> ownerNameByTask = this.assetsRepaymentFromXYMapper.findOwnerNameByTask(param);
            if(ownerNameByTask.size() > 0){
                for (String ownerTask : ownerNameByTask) {
                    if(ownerTask != null && !"".equals(ownerTask)){
                        ownerName = ownerName + ","+ownerTask;
                    }
                }
            }
        }
        if(!"".equals(ownerName)){
            ownerName = ownerName.substring(1);
        }
        return ownerName;
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