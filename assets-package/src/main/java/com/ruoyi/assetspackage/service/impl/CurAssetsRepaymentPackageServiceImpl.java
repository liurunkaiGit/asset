package com.ruoyi.assetspackage.service.impl;

import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.enums.IsCloseCaseEnum;
import com.ruoyi.assetspackage.enums.IsNoEnum;
import com.ruoyi.assetspackage.enums.TaskStatusEnum;
import com.ruoyi.assetspackage.enums.TaskTypeEnum;
import com.ruoyi.assetspackage.exception.ImportDataExcepion;
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
 * @date 2020-01-13
 */
@Service
public class CurAssetsRepaymentPackageServiceImpl implements ICurAssetsRepaymentPackageService {
    protected final Logger logger = LoggerFactory.getLogger(CurAssetsRepaymentPackageServiceImpl.class);
    @Autowired
    private CurAssetsRepaymentPackageMapper curAssetsRepaymentPackageMapper;
    @Autowired
    private RepaymentImportDataMapping repaymentImportDataMapping;
    @Autowired
    private RemoteConfigure remoteConfigure;
    @Autowired
    private ICurAssetsPackageService curAssetsPackageService;
    @Autowired
    private IOrgPackageService orgPackageService;
    @Autowired
    private ITemplatesPackageService templatesPackageService;
    @Autowired
    private ITemplateRelationPackageService templateRelationPackageService;
    @Autowired
    private ITLcImportFlowService tlcImportFlowService;
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private IRobotBlackService robotBlackService;

    /**
     * 查询资产还款
     *
     * @param id 资产还款ID
     * @return 资产还款
     */
    @Override
    public CurAssetsRepaymentPackage selectCurAssetsRepaymentPackageById(String id) {
        return curAssetsRepaymentPackageMapper.selectCurAssetsRepaymentPackageById(id);
    }

    /**
     * 查询资产还款列表
     *
     * @param curAssetsRepaymentPackage 资产还款
     * @return 资产还款
     */
    @Override
    public List<CurAssetsRepaymentPackage> selectCurAssetsRepaymentPackageList(CurAssetsRepaymentPackage curAssetsRepaymentPackage) {
        if (curAssetsRepaymentPackage.getEndDate() != null) {
            curAssetsRepaymentPackage.setEndDate(DateUtils.getEndOfDay(curAssetsRepaymentPackage.getEndDate()));
        }
        return curAssetsRepaymentPackageMapper.selectCurAssetsRepaymentPackageList(curAssetsRepaymentPackage);
    }

    /**
     * 新增资产还款
     *
     * @param curAssetsRepaymentPackage 资产还款
     * @return 结果
     */
    @Override
    public int insertCurAssetsRepaymentPackage(CurAssetsRepaymentPackage curAssetsRepaymentPackage) {
        curAssetsRepaymentPackage.setCreateTime(DateUtils.getNowDate());
        return curAssetsRepaymentPackageMapper.insertCurAssetsRepaymentPackage(curAssetsRepaymentPackage);
    }

    /**
     * 修改资产还款
     *
     * @param curAssetsRepaymentPackage 资产还款
     * @return 结果
     */
    @Override
    public int updateCurAssetsRepaymentPackage(CurAssetsRepaymentPackage curAssetsRepaymentPackage) {
        return curAssetsRepaymentPackageMapper.updateCurAssetsRepaymentPackage(curAssetsRepaymentPackage);
    }

    /**
     * 删除资产还款对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCurAssetsRepaymentPackageByIds(String ids) {
        return curAssetsRepaymentPackageMapper.deleteCurAssetsRepaymentPackageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除资产还款信息
     *
     * @param id 资产还款ID
     * @return 结果
     */
    @Override
    public int deleteCurAssetsRepaymentPackageById(String id) {
        return curAssetsRepaymentPackageMapper.deleteCurAssetsRepaymentPackageById(id);
    }

    /**
     * 判断重复记录查询
     *
     * @param params
     * @return
     */
    @Override
    public String findMoreByOrgCasNO(Map params) {
        return curAssetsRepaymentPackageMapper.findMoreByOrgCasNO(params);
    }

    @Override
    public String findMoreByHt(Map params) {
        return curAssetsRepaymentPackageMapper.findMoreByHt(params);
    }


    /**
     * 通过机构案件号查询还款金额的值
     *
     * @param OrgCasNo
     * @return
     */
    @Override
    public BigDecimal selectHkjeByOrgCasNo(String OrgCasNo) {
        return curAssetsRepaymentPackageMapper.selectHkjeByOrgCasNo(OrgCasNo);
    }

    /**
     * 根据机构案件号更新结案状态
     *
     * @param param
     * @return
     */
    @Override
    public int updateJaztByCasNo(Map<String, Object> param) {
        return curAssetsRepaymentPackageMapper.updateJaztByCasNo(param);
    }



    @Override
    public void batchAddTemp(List<TempCurAssetsRepaymentPackage> paramList) throws Exception {
        int total = paramList.size();
        int index = 500;
        int pagesize = total/index;
        if(total <=index){
            this.curAssetsRepaymentPackageMapper.batchAddTemp(paramList);
        }else{
            for(int i=0;i<pagesize;i++){
                List lt = paramList.subList(i*index, (i+1)*index);
                this.curAssetsRepaymentPackageMapper.batchAddTemp(paramList);

            }
            if(total % index != 0){
                List lt = paramList.subList(index * pagesize,total);
                this.curAssetsRepaymentPackageMapper.batchAddTemp(paramList);
            }
        }
    }


    @Override
    public List<TempCurAssetsRepaymentPackage> selectTempTableList(TempCurAssetsRepaymentPackage param) throws Exception {
        return this.curAssetsRepaymentPackageMapper.selectTempTableList(param);
    }

    @Override
    public int updateExceptionStatus(String id) throws Exception {
        return this.curAssetsRepaymentPackageMapper.updateExceptionStatus(id);
    }

    /**
     * 查询不存在的案件数量
     * @param importBatchNo
     * @return
     * @throws Exception
     */
    @Override
    public int selectNotExistsNum(String importBatchNo) throws Exception {
        return this.curAssetsRepaymentPackageMapper.selectNotExistsNum(importBatchNo);
    }

    /**
     * 更新不存在状态
     * @param importBatchNo
     * @return
     * @throws Exception
     */
    @Override
    public int updateNotExists(String importBatchNo) throws Exception {
        return this.curAssetsRepaymentPackageMapper.updateNotExists(importBatchNo);
    }

    /**
     * 查询已经结案的数量
     * @param importBatchNo
     * @return
     * @throws Exception
     */
    @Override
    public int selectCloseCaseNum(String importBatchNo) throws Exception {
        return this.curAssetsRepaymentPackageMapper.selectCloseCaseNum(importBatchNo);
    }

    /**
     * 更新结案状态
     * @param importBatchNo
     * @return
     * @throws Exception
     */
    @Override
    public int updateCloseCaseNum(String importBatchNo) throws Exception {
        return this.curAssetsRepaymentPackageMapper.updateCloseCaseNum(importBatchNo);
    }

    /**
     * 查询正常的数量
     * @param importBatchNo
     * @return
     * @throws Exception
     */
    @Override
    public int selectNormalTotal(String importBatchNo) throws Exception {
        return this.curAssetsRepaymentPackageMapper.selectNormalTotal(importBatchNo);
    }

    /**
     * 查询数据不存在信息
     * @param importBatchNo
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, String>> selectNotExistsInfo(String importBatchNo) throws Exception {
        return this.curAssetsRepaymentPackageMapper.selectNotExistsInfo(importBatchNo);
    }

    /**
     * 查询数据已结案信息
     * @param importBatchNo
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, String>> selectCloseCaseInfo(String importBatchNo) throws Exception {
        return this.curAssetsRepaymentPackageMapper.selectCloseCaseInfo(importBatchNo);
    }

    @Override
    public void batchAddRepaymentTable(String importBatchNo) throws Exception {
        this.curAssetsRepaymentPackageMapper.batchAddRepaymentTable(importBatchNo);
    }

    @Override
    public int deleteTempTable(String importBatchNo) throws Exception {
        return this.curAssetsRepaymentPackageMapper.deleteTempTable(importBatchNo);
    }

    @Override
    @Transactional
    public String handler(HttpServletRequest request, MultipartFile file, String templateId, String orgId) throws Exception {
        List<CurAssetsRepaymentPackage> paramList = null;
        List<TempCurAssetsRepaymentPackage> paramList2 = null;
        InputStream stream = null;
        String fileName = "";
        String fileNameFull = "";
        String importBatchNo ="";
        String uploadDir = new String("/usr/local/temp/assets/repayment/");
        File dirPath = new File(uploadDir);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        if (file != null) {
            stream = file.getInputStream();
            fileName = file.getOriginalFilename();
            String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
            //组装文件路径写入磁盘
            fileNameFull = uploadDir + fileName;
            OutputStream bos = new FileOutputStream(fileNameFull);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.close();
            stream.close();
            orgId=String.valueOf(ShiroUtils.getSysUser().getOrgId());
            repaymentImportDataMapping = this.voluation(repaymentImportDataMapping, templateId);
            int headNum = Integer.valueOf(repaymentImportDataMapping.getHeadRowNum());
            int dataNum = Integer.valueOf(repaymentImportDataMapping.getDataRowNum());
            List<Map<String, String>> datas = ParseExcelUtil.resolveExcel(fileNameFull, headNum, dataNum);
            datas = RepaymentDataImportUtil.dataReplace(datas, repaymentImportDataMapping);
            importBatchNo = DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS, new Date());// 生成导入批次号年月日时分秒
            OrgPackage orgPackage = this.orgPackageService.selectOrgPackageByDeptId(orgId);
            String orgName = orgPackage.getOrgName();
            paramList2 = RepaymentDataImportUtil.dataConvert2(datas, orgId,importBatchNo,orgName);
            //插入临时表
            this.batchAddTemp(paramList2);
            datas = null;
        }
        return importBatchNo;
    }


    @Override
    public Map<String, String> checkData(HttpServletRequest request, String importBatchNo) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        List<Map<String, String>> exectionList = null;
        //查询临时表信息
        TempCurAssetsRepaymentPackage param = new TempCurAssetsRepaymentPackage();
        param.setImportBatchNo(importBatchNo);
        List<TempCurAssetsRepaymentPackage> tempRepaymentList = this.selectTempTableList(param);
        //规则校验
        exectionList = RepaymentDataImportUtil.checkData2(tempRepaymentList);
        //去除异常数据
        if(exectionList.size() > 0) {
            for (Map<String, String> exceptionidmap : exectionList) {
                String exceptionid = exceptionidmap.get("id");
                //更新状态
                this.updateExceptionStatus(exceptionid);
            }
        }
        //查询案件不存在的数量并更新
        int notExistsNumResult = this.selectNotExistsNum(importBatchNo);
        if(notExistsNumResult>0){
            this.updateNotExists(importBatchNo);
        }
        //查询已经结案的案件数量并更新
//        int closeCaseNum = this.selectCloseCaseNum(importBatchNo);
//        if(closeCaseNum>0){
//            this.updateCloseCaseNum(importBatchNo);
//        }
        //查询需要新增的数据集合
        int normalTotalResult = this.selectNormalTotal(importBatchNo);
        request.getSession().setAttribute("exectionList",exectionList);
        map.put("exectionSize",String.valueOf(exectionList.size()));
        map.put("notExistsSize",String.valueOf(notExistsNumResult));
//        map.put("closeCaseSize",String.valueOf(closeCaseNum));
        map.put("normalTotalSize",String.valueOf(normalTotalResult));
        return map;
    }


    private RepaymentImportDataMapping voluation(RepaymentImportDataMapping bean, String templateId) {
        /** 查询匹配关系 */
        //根据模板id查询模板信息
        TemplatesPackage templateInfo = templatesPackageService.selectTemplatesPackageById(templateId);
        String headRowNum = templateInfo.getHeadRowNum();
        String dataRowNum = templateInfo.getDataRowNum();
        //根据模板id查询模板表头映射关系
//        List<RepaymentTemplateRelationPackage> relations = repaymentTemplateRelationPackageService.selectRepaymentTemplateRelationPackageListBytemplateId(templateId);
        List<TemplateRelationPackage> relations = templateRelationPackageService.selectTemplateRelationPackageListBytemplateId(templateId);

        /** 给bean赋值 */
        bean.setHeadRowNum(headRowNum);//表头行
        bean.setDataRowNum(dataRowNum);//数据行
        for (TemplateRelationPackage relation : relations) {
            String systemTemplateName = relation.getSystemTemplateName();
            String customerTemplateName = relation.getCustomerTemplateName();

            if ("机构案件号".equals(systemTemplateName)) {
                bean.setOrgCasNo(customerTemplateName);
                continue;
            }
            if ("交易其他费用".equals(systemTemplateName)) {
                bean.setJyqtfy(customerTemplateName);
                continue;
            }
            if ("交易利息".equals(systemTemplateName)) {
                bean.setJylx(customerTemplateName);
                continue;
            }
            if ("交易本金".equals(systemTemplateName)) {
                bean.setJybj(customerTemplateName);
                continue;
            }
            if ("交易滞纳费".equals(systemTemplateName)) {
                bean.setJyznf(customerTemplateName);
                continue;
            }
            if ("交易类型".equals(systemTemplateName)) {
                bean.setJyType(customerTemplateName);
                continue;
            }
            if ("交易金额".equals(systemTemplateName)) {
                bean.setJyje(customerTemplateName);
                continue;
            }
            if ("产品类型".equals(systemTemplateName)) {
                bean.setProductType(customerTemplateName);
                continue;
            }
            if ("借据号".equals(systemTemplateName)) {
                bean.setJjh(customerTemplateName);
                continue;
            }
            if ("催收人".equals(systemTemplateName)) {
                bean.setCsr(customerTemplateName);
                continue;
            }
            if ("催收节点".equals(systemTemplateName)) {
                bean.setCsjd(customerTemplateName);
                continue;
            }
            if ("分配日期".equals(systemTemplateName)) {
                bean.setFprq(customerTemplateName);
                continue;
            }
            if ("区域中心".equals(systemTemplateName)) {
                bean.setAreaCenter(customerTemplateName);
                continue;
            }
            if ("受理城市".equals(systemTemplateName)) {
                bean.setAcceptCity(customerTemplateName);
                continue;
            }
            if ("合同号".equals(systemTemplateName)) {
                bean.setHth(customerTemplateName);
                continue;
            }
            if ("地区事业部(一级)".equals(systemTemplateName)) {
                bean.setDqsybYj(customerTemplateName);
                continue;
            }
            if ("地区事业部(二级)".equals(systemTemplateName)) {
                bean.setDqsybEj(customerTemplateName);
                continue;
            }
            if ("外包期数".equals(systemTemplateName)) {
                bean.setWbqs(customerTemplateName);
                continue;
            }
            if ("外包经办".equals(systemTemplateName)) {
                bean.setWbjb(customerTemplateName);
                continue;
            }
            if ("委案日期".equals(systemTemplateName)) {
                bean.setWarq(customerTemplateName);
                continue;
            }
            if ("客户名称".equals(systemTemplateName)) {
                bean.setCurName(customerTemplateName);
                continue;
            }
            if ("客户经理姓名".equals(systemTemplateName)) {
                bean.setKhjlxm(customerTemplateName);
                continue;
            }
            if ("数据日期".equals(systemTemplateName)) {
                bean.setSjrq(customerTemplateName);
                continue;
            }
            if ("是否外包催收".equals(systemTemplateName)) {
                bean.setSfwbcs(customerTemplateName);
                continue;
            }
            if ("是否出催".equals(systemTemplateName)) {
                bean.setSfjq(customerTemplateName);
                continue;
            }
            if ("本月委案".equals(systemTemplateName)) {
                bean.setBywa(customerTemplateName);
                continue;
            }
            if ("出催日期".equals(systemTemplateName)) {
                bean.setAjhsrq(customerTemplateName);
                continue;
            }
            if ("消费金融账号".equals(systemTemplateName)) {
                bean.setXfjrzh(customerTemplateName);
                continue;
            }
            if ("调整事项".equals(systemTemplateName)) {
                bean.setTzsx(customerTemplateName);
                continue;
            }
            if ("调整金额".equals(systemTemplateName)) {
                bean.setTzje(customerTemplateName);
                continue;
            }
            if ("账户状态".equals(systemTemplateName)) {
                bean.setZhzt(customerTemplateName);
                continue;
            }
            if ("还款日期".equals(systemTemplateName)) {
                bean.setHkrq(customerTemplateName);
                continue;
            }
            if ("还款时逾期期数".equals(systemTemplateName)) {
                bean.setHksyqqs(customerTemplateName);
                continue;
            }
            if ("还款金额".equals(systemTemplateName)) {
                bean.setHkje(customerTemplateName);
                continue;
            }
            if ("逾期产品类型".equals(systemTemplateName)) {
                bean.setYqcplx(customerTemplateName);
                continue;
            }
            if ("逾期阶段".equals(systemTemplateName)) {
                bean.setYqjd(customerTemplateName);
                continue;
            }
            if ("额度产品".equals(systemTemplateName)) {
                bean.setQuotaProduct(customerTemplateName);
                continue;
            }
        }
        return bean;
    }



    /**
     * 结案信息
     *
     * @param list
     * @return
     * @throws Exception
     */
    public AjaxResult callRemote(List<CurAssetsRepaymentPackage> list,String importBatchNo) throws Exception{
        List<CloseCase> remoteList = new ArrayList<>();
        List<CurAssetsPackage> remoteList2 = new ArrayList<>();
        for (CurAssetsRepaymentPackage assetsRepayment : list) {
            //根据机构案件号查询资产信息
            Map<String,String> findparam = new HashMap<String,String>();
            findparam.put("caseNo",assetsRepayment.getOrgCasno());
            CurAssetsPackage curAssetsPackage = curAssetsPackageService.selectCurAssetsPackageByCaseNo(findparam);
            if (curAssetsPackage == null) {
                logger.error("案件不存在，案件编号是{}", assetsRepayment.getOrgCasno());
                continue;
            }
            // 判断是否出催
            if (Integer.valueOf(assetsRepayment.getIsExitCollect()).equals(IsNoEnum.IS.getCode())) {
                // 修改资产结案信息
                updateAssetCloseCaseInfo(curAssetsPackage, assetsRepayment);
                // 将结案数据添加到结案列表
                remoteList.add(buildCloseCase(assetsRepayment,curAssetsPackage));
            } else {
                // 未出催判断还款金额是否大于委案金额 或者大于结案应还金额
                BigDecimal hkje = this.selectHkjeByOrgCasNo(assetsRepayment.getOrgCasno());
                if (hkje.compareTo(curAssetsPackage.getRmbYe()) > -1 || hkje.compareTo(curAssetsPackage.getWaYe()) > -1) {
                    // 修改资产结案信息
                    updateAssetCloseCaseInfo(curAssetsPackage, assetsRepayment);
                    // 将结案数据添加到结案列表
                    remoteList.add(buildCloseCase(assetsRepayment,curAssetsPackage));
                }else{
                    String id = curAssetsPackage.getId();
                    BigDecimal jayhje = curAssetsPackage.getWaYe();
                    BigDecimal subtract = jayhje.subtract(hkje);
                    CurAssetsPackage updateAssetParam = new CurAssetsPackage();
                    updateAssetParam.setId(id);
                    updateAssetParam.setWaYe(subtract);
                    curAssetsPackageService.updateCurAssetsPackage(updateAssetParam);
                    remoteList.add(buildNotCloseCase(assetsRepayment,curAssetsPackage));
                }
            }
        }
//        this.curAssetsPackageService.callRemoteUpdate(remoteList2);//调用更新接口
        // 调用催收模块结案接口
        if (remoteList != null && remoteList.size() > 0) {
            closeCase(remoteList);
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult callRemote2(List<CurAssetsRepaymentPackage> list,String importBatchNo) throws Exception{
        List<CloseCase> remoteList = new ArrayList<>();
        List<CurAssetsPackage> closeCaseList = new ArrayList<>();
        List<CurAssetsPackage> noCloseCaseList = new ArrayList<>();
        for (CurAssetsRepaymentPackage assetsRepayment : list) {
            //根据机构案件号查询资产信息
            Map<String,String> findparam = new HashMap<String,String>();
            findparam.put("caseNo",assetsRepayment.getOrgCasno());
            CurAssetsPackage curAssetsPackage = curAssetsPackageService.selectCurAssetsPackageByCaseNo(findparam);
            if (curAssetsPackage == null) {
                logger.error("案件不存在，案件编号是{}", assetsRepayment.getOrgCasno());
                continue;
            }
            //当前已还金额
            BigDecimal dqyhje = curAssetsPackage.getDqyhje();
            if(dqyhje==null){
                dqyhje=new BigDecimal("0.0");
            }
            curAssetsPackage.setDqyhje(dqyhje.add(assetsRepayment.getHkje()));//当前已还金额
            //结案应还金额
            BigDecimal jayhje = curAssetsPackage.getWaYe();
            BigDecimal hkje = assetsRepayment.getHkje();
            if(hkje==null){
                hkje = new BigDecimal("0.0");
            }
            BigDecimal subtract = jayhje.subtract(hkje);
            curAssetsPackage.setWaYe(subtract);
            // 判断是否出催
            if (Integer.valueOf(assetsRepayment.getIsExitCollect()).equals(IsNoEnum.IS.getCode())) {
                // 修改资产结案信息
                closeCaseList = updateAssetCloseCaseInfo2(curAssetsPackage, assetsRepayment,closeCaseList);
                // 将结案数据添加到结案列表
                remoteList.add(buildCloseCase(assetsRepayment,curAssetsPackage));
            } else {
                // 未出催判断还款金额是否大于委案金额 或者大于结案应还金额
//                BigDecimal hkje = this.selectHkjeByOrgCasNo(assetsRepayment.getOrgCasno());
                if (hkje.compareTo(curAssetsPackage.getRmbYe()) > -1 || hkje.compareTo(curAssetsPackage.getWaYe()) > -1) {
                    // 修改资产结案信息
                    closeCaseList = updateAssetCloseCaseInfo2(curAssetsPackage, assetsRepayment,closeCaseList);
                    // 将结案数据添加到结案列表
                    remoteList.add(buildCloseCase(assetsRepayment,curAssetsPackage));
                }else{
                    String id = curAssetsPackage.getId();
                    CurAssetsPackage updateAssetParam = new CurAssetsPackage();
                    updateAssetParam.setId(id);
                    updateAssetParam.setWaYe(subtract);//结案应还金额
                    updateAssetParam.setDqyhje(curAssetsPackage.getDqyhje());//当前应还金额
                    noCloseCaseList.add(updateAssetParam);
                    remoteList.add(buildNotCloseCase(assetsRepayment,curAssetsPackage));
                }
            }
        }
        if(closeCaseList.size() > 0){//更新已结案的数据
            this.curAssetsPackageService.updateCloseCase(closeCaseList);
        }
        if(noCloseCaseList.size() > 0){//更新未结案的数据
            this.curAssetsPackageService.updateNoCloseCase(noCloseCaseList);
        }
        // 更新催收模块结案信息
        if (remoteList != null && remoteList.size() > 0) {
            closeCase2(remoteList);
        }
        return AjaxResult.success();
    }


    /**
     * 修改资产结案信息
     *
     * @param assetsRepayment
     */
    private void updateAssetCloseCaseInfo(CurAssetsPackage curAssetsPackage, CurAssetsRepaymentPackage assetsRepayment) {
        curAssetsPackage.setCloseCase(IsCloseCaseEnum.CLOSE_CASE.getValue());
//        if (curAssetsPackage.getCloseCase().equals(IsCloseCaseEnum.NOT_CLOSE_CASE.getValue())) {
//            curAssetsPackage.setCloseCaseDate(null);
//        } else {
//            curAssetsPackage.setCloseCaseDate(new Date());
//        }
        curAssetsPackage.setCloseCaseDate(new Date());
        curAssetsPackage.setIsExitCollect(assetsRepayment.getIsExitCollect());
        if (assetsRepayment.getIsExitCollect().equals(IsNoEnum.IS.getCode().toString())) {
            curAssetsPackage.setAjhssj(assetsRepayment.getAjhsrq());
        }
        this.curAssetsPackageService.updateCurAssetsPackage(curAssetsPackage);
    }

    private List<CurAssetsPackage> updateAssetCloseCaseInfo2(CurAssetsPackage curAssetsPackage, CurAssetsRepaymentPackage assetsRepayment,List<CurAssetsPackage> closeCaseList) {
        curAssetsPackage.setCloseCase(IsCloseCaseEnum.CLOSE_CASE.getValue());
        curAssetsPackage.setCloseCaseDate(new Date());
        curAssetsPackage.setIsExitCollect(assetsRepayment.getIsExitCollect());
        if (assetsRepayment.getIsExitCollect().equals(IsNoEnum.IS.getCode().toString())) {
            curAssetsPackage.setAjhssj(assetsRepayment.getAjhsrq());
        }
        closeCaseList.add(curAssetsPackage);
        return closeCaseList;
    }

    /**
     * 构建结案对象
     *
     * @param assetsRepayment
     * @return
     */
    private CloseCase buildCloseCase(CurAssetsRepaymentPackage assetsRepayment,CurAssetsPackage curAssetsPackage) {
        return CloseCase.builder()
                .isExitCollect(assetsRepayment.getIsExitCollect())
                .caseNo(assetsRepayment.getOrgCasno())
                .importBatchNo(curAssetsPackage.getImportBatchNo())
                .orgId(assetsRepayment.getOrgId())
                .isClose(3)
                .repayMoney(assetsRepayment.getHkje())
                .dqyhje(curAssetsPackage.getDqyhje())
                .jayhje(curAssetsPackage.getWaYe())
                .build();
    }

    /**
     * 未结案
     * @param assetsRepayment
     * @param curAssetsPackage
     * @return
     */
    private CloseCase buildNotCloseCase(CurAssetsRepaymentPackage assetsRepayment,CurAssetsPackage curAssetsPackage) {
        return CloseCase.builder()
                .isExitCollect(assetsRepayment.getIsExitCollect())
                .caseNo(assetsRepayment.getOrgCasno())
                .importBatchNo(curAssetsPackage.getImportBatchNo())
                .orgId(assetsRepayment.getOrgId())
                .repayMoney(assetsRepayment.getHkje())
                .dqyhje(curAssetsPackage.getDqyhje())
                .jayhje(curAssetsPackage.getWaYe())
                .build();
    }
    /**
     * 调用催收模块结案接口
     *
     * @param remoteList
     */
    private void closeCase(List<CloseCase> remoteList) {
        String url = remoteConfigure.getRepaymentInterfaceUrl();
        int total = remoteList.size();
        int index = 500;
        int pagesize = total/index;
        if(total <=index){
            CloseCaseUser closeCaseUser = CloseCaseUser.builder().closeCaseList(remoteList).sysUser(ShiroUtils.getSysUser()).build();
            ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, closeCaseUser, Response.class);
            if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
                throw new ImportDataExcepion("还款接口调用失败:" + responseResponseEntity.getBody().getMessage());
            }
        }else{
            for(int i=0;i<pagesize;i++){
                List lt = remoteList.subList(i*index, (i+1)*index);
                CloseCaseUser closeCaseUser = CloseCaseUser.builder().closeCaseList(lt).sysUser(ShiroUtils.getSysUser()).build();
                ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, closeCaseUser, Response.class);
                if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
                    throw new ImportDataExcepion("还款接口调用失败:" + responseResponseEntity.getBody().getMessage());
                }

            }
            if(total % index != 0){
                List lt = remoteList.subList(index * pagesize,total);
                CloseCaseUser closeCaseUser = CloseCaseUser.builder().closeCaseList(lt).sysUser(ShiroUtils.getSysUser()).build();
                ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, closeCaseUser, Response.class);
                if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
                    throw new ImportDataExcepion("还款接口调用失败:" + responseResponseEntity.getBody().getMessage());
                }
            }
        }

    }



    public void closeCase2(List<CloseCase> remoteList) {
        List<String> caseNoList = new ArrayList<>();
        List<Task> closeList = new ArrayList<>();
        List<Task> tLcTaskList = remoteList.stream()
                .map(closeCase -> {
                    Task tLcTask = this.taskMapper.selectTaskByCaseNo(closeCase.getCaseNo(), closeCase.getOrgId());
                    Assert.notNull(tLcTask, String.format("案件号不存在，案件号是%s", closeCase.getCaseNo()));
                    tLcTask.setCloseCaseYhje(closeCase.getJayhje());
                    if (closeCase.getDqyhje() != null) {
                        tLcTask.setDqyhje(closeCase.getDqyhje());
                    }
                    tLcTask.setModifyBy(ShiroUtils.getSysUser().getUserId());
                    if (closeCase.getIsClose() != null && TaskStatusEnum.CLOSE.getStatus().equals(closeCase.getIsClose())) {
                        tLcTask.setTaskStatus(TaskStatusEnum.CLOSE.getStatus());
                        tLcTask.setTaskType(TaskTypeEnum.CLOSE_CASE_TRANSFER.getCode());
                        tLcTask.setCloseDate(new Date());
                        caseNoList.add(tLcTask.getCaseNo());
                        closeList.add(tLcTask);
                    }
                    return tLcTask;
                }).collect(Collectors.toList());
        this.taskMapper.batchUpdateTask(tLcTaskList);
        this.robotBlackService.batchDeleteRobotBlackByCaseNo(caseNoList);
        this.insertDuncaseAssign(closeList, ShiroUtils.getSysUser());
    }

  /*  public void closeCase3(List<CloseCase> remoteList) {
        List<String> caseNoList = new ArrayList<>();
        List<Task> closeList = new ArrayList<>();
        List<Task> tLcTaskList = remoteList.stream()
                .map(closeCase -> {
                    Task tLcTask = this.taskMapper.selectTaskByCaseNo3(closeCase.getCaseNo(), closeCase.getOrgId());
                    Assert.notNull(tLcTask, String.format("案件号不存在，案件号是%s", closeCase.getCaseNo()));
                    tLcTask.setCloseCaseYhje(closeCase.getJayhje());
                    if (closeCase.getDqyhje() != null) {
                        tLcTask.setDqyhje(closeCase.getDqyhje());
                    }
                    tLcTask.setModifyBy(ShiroUtils.getSysUser().getUserId());
                    if (closeCase.getIsClose() != null && TaskStatusEnum.CLOSE.getStatus().equals(closeCase.getIsClose())) {
                        tLcTask.setTaskStatus(TaskStatusEnum.CLOSE.getStatus());
                        tLcTask.setTaskType(TaskTypeEnum.CLOSE_CASE_TRANSFER.getCode());
                        tLcTask.setCloseDate(new Date());
                        caseNoList.add(tLcTask.getCaseNo());
                        closeList.add(tLcTask);
                    }
                    return tLcTask;
                }).collect(Collectors.toList());
        this.taskMapper.batchUpdateTask(tLcTaskList);
        this.robotBlackService.batchDeleteRobotBlackByCaseNo(caseNoList);
        this.insertDuncaseAssign(closeList, ShiroUtils.getSysUser());
    }*/

    /**
     * 添加到案件轨迹表中
     *
     * @param taskList
     */
    public void insertDuncaseAssign(List<Task> taskList, SysUser sysUser) {
        List<DuncaseAssign> duncaseAssignList = taskList.stream()
                .map(task -> {
                    DuncaseAssign tLcDuncaseAssign = DuncaseAssign.builder()
                            .ownerId(task.getOwnerId())
                            .ownerName(task.getOwnerName())
                            .taskId(task.getId().toString())
                            .operationId(sysUser.getUserId())
                            .customName(task.getCustomName())
                            .collectTeamName(task.getCollectTeamName())
                            .collectTeamId(task.getCollectTeamId())
                            .certificateNo(task.getCertificateNo())
                            .caseNo(task.getCaseNo())
                            .operationName(sysUser.getUserName())
                            .transferType(task.getTaskType())
                            .orgId(task.getOrgId())
                            .orgName(task.getOrgName())
                            .taskStatus(task.getTaskStatus())
                            .validateStatus(IsNoEnum.IS.getCode())
                            .build();
                    return tLcDuncaseAssign;
                }).collect(Collectors.toList());
        // 将该任务添加到案件历史轨迹表
        this.curAssetsRepaymentPackageMapper.batchInsertDuncaseAssign(duncaseAssignList);
    }

}
