package com.ruoyi.assetspackage.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.domain.score.TLcScore;
import com.ruoyi.assetspackage.enums.ImportTypeEnum;
import com.ruoyi.assetspackage.enums.IsCloseCaseEnum;
import com.ruoyi.assetspackage.enums.IsNoEnum;
import com.ruoyi.assetspackage.exception.ImportDataExcepion;
import com.ruoyi.assetspackage.mapper.CurAssetsPackageMapper;
import com.ruoyi.assetspackage.service.*;
import com.ruoyi.assetspackage.util.*;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.domain.CloseCase;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RestTemplateUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.framework.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * 客户资产Service业务层处理
 *
 * @author guozeqi
 * @date 2019-12-25
 */
@Slf4j
@Service
public class CurAssetsPackageServiceImpl extends BaseController implements ICurAssetsPackageService {

    @Autowired
    private CurAssetsPackageMapper curAssetsPackageMapper;
    @Autowired
    ImportDataMapping importDataMapping;
    @Autowired
    RemoteConfigure remoteConfigure;
    @Autowired
    private PackageDataPermissionUtil dataPermissionUtil;
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private IOrgPackageService orgPackageService;
    @Autowired
    private ITLcImportFlowService tlcImportFlowService;
    @Autowired
    private ITemplatesPackageService templatesPackageService;
    @Autowired
    private ITemplateRelationPackageService templateRelationPackageService;
    @Autowired
    private IDuncaseService tlcDuncaseService;
    @Resource
    private GeneralConvertor convertor;
    @Autowired
    private ITLcScoreService tlcScoreService;

    /**
     * 查询客户资产
     *
     * @param id 客户资产ID
     * @return 客户资产
     */
    @Override
    public CurAssetsPackage selectCurAssetsPackageById(String id) {
        return curAssetsPackageMapper.selectCurAssetsPackageById(id);
    }

    /**
     * 查询客户资产列表
     *
     * @param curAssetsPackage 客户资产
     * @return 客户资产
     */
    @Override
    public List<CurAssetsPackage> selectCurAssetsPackageList(CurAssetsPackage curAssetsPackage) {
        if (curAssetsPackage.getEndDate() != null) {
            curAssetsPackage.setEndDate(DateUtils.getEndOfDay(curAssetsPackage.getEndDate()));
        }
        return curAssetsPackageMapper.selectCurAssetsPackageList(curAssetsPackage);
    }

    /**
     * 查询客户资产列表
     *
     * @param curAssetsPackage 客户资产
     * @return 客户资产
     */
    @Override
    public List<CurAssetsPackage> selectCurAssetsPackageByPage(CurAssetsPackage curAssetsPackage) {
        if (curAssetsPackage.getEndDate() != null) {
            curAssetsPackage.setEndDate(DateUtils.getEndOfDay(curAssetsPackage.getEndDate()));
        }
        return curAssetsPackageMapper.selectCurAssetsPackageByPage(curAssetsPackage);
    }

    @Override
    public List<CurAssetsPackage> selectCurAssetsPackageList2(List<String> param, CurAssetsPackage curAssetsPackage) {
        return curAssetsPackageMapper.selectCurAssetsPackageList2(param, curAssetsPackage);
    }

    /**
     * 新增客户资产
     *
     * @param curAssetsPackage 客户资产
     * @return 结果
     */
    @Override
    public int insertCurAssetsPackage(CurAssetsPackage curAssetsPackage) {
        curAssetsPackage.setCreateTime(DateUtils.getNowDate());
        return curAssetsPackageMapper.insertCurAssetsPackage(curAssetsPackage);
    }

    /**
     * 修改客户资产
     *
     * @param curAssetsPackage 客户资产
     * @return 结果
     */
    @Override
    public int updateCurAssetsPackage(CurAssetsPackage curAssetsPackage) {
        return curAssetsPackageMapper.updateCurAssetsPackage(curAssetsPackage);
    }

    /**
     * 删除客户资产对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCurAssetsPackageByIds(String ids) {
        return curAssetsPackageMapper.deleteCurAssetsPackageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户资产信息
     *
     * @param id 客户资产ID
     * @return 结果
     */
    @Override
    public int deleteCurAssetsPackageById(String id) {
        return curAssetsPackageMapper.deleteCurAssetsPackageById(id);
    }

    /**
     * 根据资产包id查询该包的所有资产
     *
     * @param packageId
     * @return
     */
    @Override
    public List<CurAssetsPackage> selectCurAssetsByPackageId(String packageId) {
//        return curAssetsPackageMapper.selectCurAssetsByPackageId(packageId);
        return null;
    }

    @Override
    @Transactional()
    public AjaxResult callRemote(List<CurAssetsPackage> remoteList) {
        try {
            String url = remoteConfigure.getAssetsInterfaceUrl();
//        CurAssetsPackage selectParam = new CurAssetsPackage();
//        List<CurAssetsPackage> remoteList = curAssetsPackageService.selectCurAssetsPackageList(selectParam);
            for (CurAssetsPackage assets : remoteList) {
                assets.setCreateBy(ShiroUtils.getLoginName());
            }
            int total = remoteList.size();
            int index = 500;
            int pagesize = total/index;
            if(total <=index){
//                this.curAssetsPackageMapper.batchAddTemp(paramList);
                ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, remoteList, Response.class);
                if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
                    throw new ImportDataExcepion("调用资产分发接口调用失败:" + responseResponseEntity.getBody().getMessage());
                }
            }else{
                for(int i=0;i<pagesize;i++){
                    List lt = remoteList.subList(i*index, (i+1)*index);
//                    this.curAssetsPackageMapper.batchAddTemp(lt);
                    ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, lt, Response.class);
                    if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
                        throw new ImportDataExcepion("调用资产分发接口调用失败:" + responseResponseEntity.getBody().getMessage());
                    }
                }
                if(total % index != 0){
                    List lt = remoteList.subList(index * pagesize,total);
//                    this.curAssetsPackageMapper.batchAddTemp(lt);
                    ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, lt, Response.class);
                    if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
                        throw new ImportDataExcepion("调用资产分发接口调用失败:" + responseResponseEntity.getBody().getMessage());
                    }
                }
            }
//            int total = remoteList.size();
//            int index = 500;
//            int targetSize = 500;
//            int subIndex = 0;
//            if (total > index) {
//                int count = total / index;
//                int over = total % index;
//                if (over > 0) {
//                    for (int i = 0; i < count; i++) {
//                        List lt = remoteList.subList(subIndex, index);
//                        subIndex = subIndex + index;
//                        index = index + index;
//                        ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, lt, Response.class);
//                        if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
//                            throw new ImportDataExcepion("调用资产导入接口调用失败:" + responseResponseEntity.getBody().getMessage());
//                        }
//                    }
//                    subIndex = count * targetSize;
//                    List lt = remoteList.subList(subIndex, count * targetSize + over);
//                    ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, lt, Response.class);
//                    if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
//                        throw new ImportDataExcepion("调用资产导入接口调用失败:" + responseResponseEntity.getBody().getMessage());
//                    }
//                } else {
//                    for (int i = 0; i < count; i++) {
//                        List lt = remoteList.subList(subIndex, index);
//                        subIndex = subIndex + index;
//                        index = index + index;
//                        ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, lt, Response.class);
//                        if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
//                            throw new ImportDataExcepion("调用资产导入接口调用失败:" + responseResponseEntity.getBody().getMessage());
//                        }
//                    }
//                }
//
//            } else {
//                ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, remoteList, Response.class);
//                if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
//                    throw new ImportDataExcepion("调用资产导入接口调用失败:" + responseResponseEntity.getBody().getMessage());
//                }
//            }
        } catch (Exception e) {
            log.error("调用资产分发接口调用失败，error is {}", e);
            throw new RuntimeException("调用资产分发接口调用失败");
        }
        return AjaxResult.success();
    }


    @Override
    @Transactional()
    public AjaxResult callRemoteUpdate(List<CurAssetsPackage> remoteList) {
        try {
            String url = remoteConfigure.getAssetsUpdateInterfaceUrl();
            for (CurAssetsPackage assets : remoteList) {
                assets.setCreateBy(ShiroUtils.getLoginName());
            }
            int total = remoteList.size();
            int index = 500;
            int pagesize = total/index;
            if(total <=index){
                ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, remoteList, Response.class);
                if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
                    throw new ImportDataExcepion("调用更新接口调用失败:" + responseResponseEntity.getBody().getMessage());
                }
            }else{
                for(int i=0;i<pagesize;i++){
                    List lt = remoteList.subList(i*index, (i+1)*index);
                    ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, lt, Response.class);
                    if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
                        throw new ImportDataExcepion("调用更新接口调用失败:" + responseResponseEntity.getBody().getMessage());
                    }
                }
                if(total % index != 0){
                    List lt = remoteList.subList(index * pagesize,total);
                    ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, lt, Response.class);
                    if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
                        throw new ImportDataExcepion("调用更新接口调用失败:" + responseResponseEntity.getBody().getMessage());
                    }
                }
            }

        } catch (Exception e) {
            log.error("调用更新接口调用失败，error is {}", e);
            throw new RuntimeException("调用更新接口调用失败");
        }
        return AjaxResult.success();
    }



    /*@Override
    public Response closeCase(List<CloseCase> caseList) {
        try {
            caseList.stream()
                    .forEach(assetCloseCase -> {
                        CurAssetsPackage curAssetsPackage = new CurAssetsPackage();
                        curAssetsPackage.setOrgCasno(assetCloseCase.getCaseNo());
                        curAssetsPackage.setCloseCase(IsCloseCaseEnum.NOT_CLOSE_CASE.getValue());
                        List<CurAssetsPackage> curAssetsPackageList = this.curAssetsPackageMapper.selectCurAssetsPackageList(curAssetsPackage);
                        if (curAssetsPackageList == null || curAssetsPackageList.size() == 0) {
                            log.error("案件结束失败，资产管理案件不存在，案件编号是{}", assetCloseCase.getCaseNo());
//                            throw new RuntimeException("案件结束失败，资产管理案件不存在");
                        } else {
                            curAssetsPackage = curAssetsPackageList.get(0);
                            curAssetsPackage.setOrgCasno(assetCloseCase.getCaseNo());
                            curAssetsPackage.setCloseCase(IsCloseCaseEnum.CLOSE_CASE.getValue());
                            curAssetsPackage.setIsExitCollect(assetCloseCase.getIsExitCollect());
                            curAssetsPackage.setCloseCaseDate(new Date());
                            if(String.valueOf(IsNoEnum.IS.getCode()).equals(assetCloseCase.getIsExitCollect())){
                                curAssetsPackage.setAjhssj(new Date());
                            }
                            this.curAssetsPackageMapper.updateCurAssetsPackage(curAssetsPackage);
                        }
                    });
            return Response.success(Response.ResponseStatusEnum.SUCCESS.getStatus(), Response.ResponseStatusEnum.SUCCESS.getMessage(), null);
        } catch (Exception e) {
            log.error("结案失败，exception is {}", e);
            return Response.success(Response.ResponseStatusEnum.SUCCESS.getStatus(), e.getMessage(), null);
        }
    }*/

    @Override
    public void closeCase(List<CloseCase> caseList) {
        try {
            List<CurAssetsPackage> curAssetsPackageList = BuildCloseCaseParam(caseList);
            this.curAssetsPackageMapper.updateCloseCase2(curAssetsPackageList);
        } catch (Exception e) {
            log.error("结案失败，exception is {}", e);
        }
    }

    public List<CurAssetsPackage> BuildCloseCaseParam(List<CloseCase> caseList) throws Exception{
        List<CurAssetsPackage> resultList = new ArrayList<>();
        caseList.stream()
                .forEach(assetCloseCase -> {
                    CurAssetsPackage curAssetsPackage = new CurAssetsPackage();
                    curAssetsPackage.setOrgCasno(assetCloseCase.getCaseNo());
                    curAssetsPackage.setCloseCase(IsCloseCaseEnum.CLOSE_CASE.getValue());
                    curAssetsPackage.setCloseCaseDate(new Date());
                    curAssetsPackage.setIsExitCollect(assetCloseCase.getIsExitCollect());
                    if(String.valueOf(IsNoEnum.IS.getCode()).equals(assetCloseCase.getIsExitCollect())){
                        curAssetsPackage.setAjhssj(new Date());
                    }
                    resultList.add(curAssetsPackage);
                });
        return resultList;
    }

    @Override
    public CurAssetsPackage selectCurAssetsPackageByCaseNo(Map<String,String> param) {
        return this.curAssetsPackageMapper.selectCurAssetsPackageByCaseNo(param);
    }


    /**
     * 插入临时表
     */
    @Override
    public void batchAddTemp(List<TempCurAssetsPackage> paramList)throws Exception{

        int total = paramList.size();
        int index = 500;
        int pagesize = total/index;
        if(total <=index){
            this.curAssetsPackageMapper.batchAddTemp(paramList);
        }else{
            for(int i=0;i<pagesize;i++){
                List lt = paramList.subList(i*index, (i+1)*index);
                this.curAssetsPackageMapper.batchAddTemp(lt);

            }
            if(total % index != 0){
                List lt = paramList.subList(index * pagesize,total);
                this.curAssetsPackageMapper.batchAddTemp(lt);
            }
        }

    }


    /**
     * 批量添加资产
     * @param paramList
     * @throws Exception
     */
    @Override
    public void batchAddAssets(List<TempCurAssetsPackage> paramList) throws Exception {
        String orgId = paramList.get(0).getOrgId();
        String orgName= paramList.get(0).getOrg();
        String importBatchNo= paramList.get(0).getImportBatchNo();

        //导入总金额
        BigDecimal totalMoney = new BigDecimal(0.00);
        // 新增流水集合，如果案件是修改的话，不修改批次号
        List<TempCurAssetsPackage> tLcImportFlowList = new ArrayList<>(paramList.size());
        //参数计算、赋值
        for (TempCurAssetsPackage tempCurAssetsPackage : paramList) {
            totalMoney = totalMoney.add(tempCurAssetsPackage.getRmbYe() != null ? new BigDecimal(tempCurAssetsPackage.getRmbYe()) : new BigDecimal(0.00));
            tLcImportFlowList.add(tempCurAssetsPackage);
        }

        int total = paramList.size();
        int index = 500;
        int pagesize = total/index;
        if(total <=index){
            this.curAssetsPackageMapper.batchAddAssets(paramList);
        }else{
            for(int i=0;i<pagesize;i++){
                List lt = paramList.subList(i*index, (i+1)*index);
                this.curAssetsPackageMapper.batchAddAssets(lt);

            }
            if(total % index != 0){
                List lt = paramList.subList(index * pagesize,total);
                this.curAssetsPackageMapper.batchAddAssets(lt);
            }
        }

        //插入流水表
        if (tLcImportFlowList != null && tLcImportFlowList.size() > 0) {
                        TLcImportFlow tLcImportFlow = new TLcImportFlow();
                        tLcImportFlow.setImportBatchNo(importBatchNo)
                                .setImportType(ImportTypeEnum.ASSET_TEMPLETE.getCode())
                                .setOrgId(orgId)
                                .setOrgName(orgName)
                                .setTotalNum(tLcImportFlowList.size())
                                .setTotalMoney(totalMoney)
                                .setCreateBy(String.valueOf(ShiroUtils.getUserId()));
                        this.tlcImportFlowService.insertTLcImportFlow(tLcImportFlow);
                    }
        OrgPackage orgPackage = orgPackageService.selectOrgPackageByOrgId(orgId);
        if("1".equals(orgPackage.getIsAutoScore())){
            //插入催收评分表
            List<TLcScore> tLcScores = tlcScoreService.buildParam(paramList);
            tlcScoreService.batchInsert(tLcScores);
        }
    }


    /**
     *用临时表的数据更新主表
     * @param param
     * @throws Exception
     */
    @Override
    public void updateCurAssetsPackage2(TempCurAssetsPackage param,List<CurAssetsPackage> remoteList) throws Exception {
        CurAssetsPackage selectParam = new CurAssetsPackage();
        selectParam.setOrgCasno(param.getOrgCasno());
        selectParam.setOrgId(param.getOrgId());
        List<Map<String, Object>> findResult = this.curAssetsPackageMapper.selectCurAssetsPackageList3(selectParam);
        String curAssetsId = (String)findResult.get(0).get("id");
        param.setId(curAssetsId);
        this.curAssetsPackageMapper.updateCurAssetsPackage2(param);
        //查询资产信息
        CurAssetsPackage curAssetsPackage = this.curAssetsPackageMapper.selectCurAssetsPackageById(curAssetsId);
        curAssetsPackage.setUpdateId(ShiroUtils.getUserId());
        remoteList.add(curAssetsPackage);
    }

    /**
     *根据条件查询临时表
     * @param param
     * @throws Exception
     */
    @Override
    public List<TempCurAssetsPackage> selectTempCurAssetsPackageList(TempCurAssetsPackage param) throws Exception {
        return this.curAssetsPackageMapper.selectTempCurAssetsPackageList(param);
    }



    /**
     *清空临时表
     * @throws Exception
     */
    @Override
    public void deleteTempCurAssetsPackage(String importBatchNo) throws Exception {
        curAssetsPackageMapper.deleteTempCurAssetsPackage(importBatchNo);
    }

    /**
     * 根据机构案件号和委托方查询主表
     * @param curAssetsPackage
     * @return
     */
    @Override
    public List<Map<String,Object>> selectCurAssetsPackageList3(CurAssetsPackage curAssetsPackage) {
        return curAssetsPackageMapper.selectCurAssetsPackageList3(curAssetsPackage);
    }

    /**
     * 过滤临时表中的资产
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public List<TempCurAssetsPackage> selectTempCurAssetsPackageList2(List<String> param) throws Exception {
        return curAssetsPackageMapper.selectTempCurAssetsPackageList2(param);
    }

    /**
     * 更新异常状态
     * @throws Exception
     */
    @Override
    public int updateExceptionStatus(String id) throws Exception {
        return curAssetsPackageMapper.updateExceptionStatus(id);
    }


    /**
     * 查询临时表需要更新的数据
     * @param improtBatchNo
     * @return
     * @throws Exception
     */
    @Override
    public List<TempCurAssetsPackage> selectUpdateList(String improtBatchNo) throws Exception {
        return curAssetsPackageMapper.selectUpdateList(improtBatchNo);
    }

    /**
     * 根据id修改状态为更新状态
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int modifyUpdateStatus(String id) throws Exception {
        return curAssetsPackageMapper.modifyUpdateStatus(id);
    }

    /**
     * 根据批次号查询需要新增的集合
     * @param improtBatchNo
     * @return
     * @throws Exception
     */
    @Override
    public List<TempCurAssetsPackage> selectInsertList(String improtBatchNo) throws Exception {
        return curAssetsPackageMapper.selectInsertList(improtBatchNo);
    }


    @Override
    public int selectUpdateCount(String improtBatchNo) throws Exception {
        return this.curAssetsPackageMapper.selectUpdateCount(improtBatchNo);
    }

    @Override
    public int selectInsertCount(String improtBatchNo) throws Exception {
        return this.curAssetsPackageMapper.selectInsertCount(improtBatchNo);
    }

    /**
     * 批量更新状态
     * @param improtBatchNo
     * @throws Exception
     */
    @Override
    public void modifyUpdateStatus2(String improtBatchNo) throws Exception {
        this.curAssetsPackageMapper.modifyUpdateStatus2(improtBatchNo);
    }

    /**
     * 查出需要更新的数据
     * @param improtBatchNo
     * @return
     * @throws Exception
     */
    @Override
    public List<TempCurAssetsPackage> selectUpdateList2(String improtBatchNo) throws Exception {
        return this.curAssetsPackageMapper.selectUpdateList2(improtBatchNo);
    }


    @Override
    public CurAssetsPackage selectAsset(String orgCaseNo, String importBatchNo) throws Exception {
        CurAssetsPackage param = new CurAssetsPackage();
        param.setOrgCasno(orgCaseNo);
        param.setImportBatchNo(importBatchNo);
        CurAssetsPackage curAssets = this.curAssetsPackageMapper.selectCurAssetsPackageList(param).get(0);
        curAssets.setPackNo(curAssets.getPackageId());
        return curAssets;
    }

    @Override
    public CurAssetsPackage selectHisAsset(String orgCaseNo, String importBatchNo) throws Exception {
        CurAssetsPackage param = new CurAssetsPackage();
        param.setOrgCasno(orgCaseNo);
        param.setImportBatchNo(importBatchNo);
        CurAssetsPackage curAssets = this.curAssetsPackageMapper.selectHisCurAssetsPackageList(param).get(0);
        curAssets.setPackNo(curAssets.getPackageId());
        return curAssets;
    }

    @Override
    public List<Map<String,String>> findNotExistsList(String improtBatchNo) throws Exception {
        return this.curAssetsPackageMapper.findNotExistsList(improtBatchNo);
    }

    @Override
    public List<TempCurAssetsPackage> findNormalList(String improtBatchNo) throws Exception {
        return this.curAssetsPackageMapper.findNormalList(improtBatchNo);
    }

    @Override
    public void updateHandler(HttpServletRequest request, String importBatchNo) throws Exception {
        List<TempCurAssetsPackage> updateList = this.curAssetsPackageMapper.selectUpdateList2(importBatchNo);
        //分批次批量执行
        if(updateList.size()>0){
            int total = updateList.size();
            int index = 500;
            int pagesize = total/index;
            if(total <=index){
                this.curAssetsPackageMapper.updateCurAssetsPackage3(updateList);
                updateCollect(updateList);
            }else{
                for(int i=0;i<pagesize;i++){
                    List lt = updateList.subList(i*index, (i+1)*index);
                    this.curAssetsPackageMapper.updateCurAssetsPackage3(lt);
                    updateCollect(lt);

                }
                if(total % index != 0){
                    List lt = updateList.subList(index * pagesize,total);
                    this.curAssetsPackageMapper.updateCurAssetsPackage3(lt);
                    updateCollect(lt);
                }
            }
        }


    }

    /**
     * 更新导入处理
     * @param request
     * @param importBatchNo
     * @throws Exception
     */
    @Override
    @Transactional
    public void updateImortHandler(HttpServletRequest request, String importBatchNo) throws Exception {
        /**查出正常需要更新的集合*/
        List<TempCurAssetsPackage> normalList = this.curAssetsPackageMapper.findNormalList(importBatchNo);
        /**流水表参数组装*/
        String orgId = normalList.get(0).getOrgId();
        String orgName= normalList.get(0).getOrg();
        //导入总金额
        BigDecimal totalMoney = new BigDecimal(0.00);
        // 新增流水集合，如果案件是修改的话，不修改批次号
        List<TempCurAssetsPackage> tLcImportFlowList = new ArrayList<>(normalList.size());
        //参数计算、赋值
        for (TempCurAssetsPackage tempCurAssetsPackage : normalList) {
            totalMoney = totalMoney.add(tempCurAssetsPackage.getRmbYe() != null ? new BigDecimal(tempCurAssetsPackage.getRmbYe()) : new BigDecimal(0.00));
            tLcImportFlowList.add(tempCurAssetsPackage);
        }
        /**插入流水表*/
        if (tLcImportFlowList != null && tLcImportFlowList.size() > 0) {
            TLcImportFlow tLcImportFlow = new TLcImportFlow();
            tLcImportFlow.setImportBatchNo(importBatchNo)
                    .setImportType(ImportTypeEnum.UPDATE_TEMPLETE.getCode())
                    .setOrgId(orgId)
                    .setOrgName(orgName)
                    .setTotalNum(tLcImportFlowList.size())
                    .setTotalMoney(totalMoney)
                    .setCreateBy(String.valueOf(ShiroUtils.getUserId()));
            this.tlcImportFlowService.insertTLcImportFlow(tLcImportFlow);
        }
        //分批次批量执行
        int total = normalList.size();
        int index = 500;
        int pagesize = total/index;
        if(total <=index){
            /**执行更新*/
            this.curAssetsPackageMapper.updateCurAssetsPackage3(normalList);
            /**更新案件、表任务表*/
            updateCollect(normalList);
            /**插入自由导入表*/
            this.insertFreeImport(normalList);
        }else{
            for(int i=0;i<pagesize;i++){
                List lt = normalList.subList(i*index, (i+1)*index);
                /**执行更新*/
                this.curAssetsPackageMapper.updateCurAssetsPackage3(lt);
                /**更新案件、表任务表*/
                updateCollect(lt);
                /**插入自由导入表*/
                this.insertFreeImport(lt);

            }
            if(total % index != 0){
                List lt = normalList.subList(index * pagesize,total);
                /**执行更新*/
                this.curAssetsPackageMapper.updateCurAssetsPackage3(lt);
                /**更新案件、表任务表*/
                updateCollect(lt);
                /**插入自由导入表*/
                this.insertFreeImport(lt);
            }
        }
    }

    @Override
    public List<Map<String,String>> selectFreeImportByCaseno(String orgCasno) throws Exception {
        return this.curAssetsPackageMapper.selectFreeImportByCaseno(orgCasno);
    }

    @Override
    public List<Map<String,String>> selectHisFreeImportByCaseno(String orgCasno) throws Exception {
        return this.curAssetsPackageMapper.selectHisFreeImportByCaseno(orgCasno);
    }

    @Override
    public void updateCloseCase(List<CurAssetsPackage> CurAssetsList) throws Exception {
        this.curAssetsPackageMapper.updateCloseCase(CurAssetsList);
    }

    @Override
    public void updateNoCloseCase(List<CurAssetsPackage> CurAssetsList) throws Exception {
        this.curAssetsPackageMapper.updateNoCloseCase(CurAssetsList);
    }

    @Override
    public void clearTempTable(){
        this.curAssetsPackageMapper.clearTempTable();
    }


    @Override
    @Transactional
    public String handler(HttpServletRequest request, MultipartFile file, String templateId, String orgId, String type) throws Exception {
        List<CurAssetsPackage> paramList = null;
        List<TempCurAssetsPackage> paramList2 = null;
        InputStream stream = null;
        String fileName = "";
        String fileNameFull = "";
        String importBatchNo ="";
        TemplatesPackage templatesPackage = this.templatesPackageService.selectTemplatesPackageById(templateId);
        // 本地资源路径
        String localPath = Global.getProfile();
        // 数据库资源地址
        String uploadDir = localPath + StringUtils.substringAfter(templatesPackage.getUrl(), Constants.RESOURCE_PREFIX);
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
            orgId = String.valueOf(ShiroUtils.getSysUser().getOrgId());
            importDataMapping = this.voluation(importDataMapping, templateId);
            int headNum = Integer.valueOf(importDataMapping.getHeadRowNum());
            int dataNum = Integer.valueOf(importDataMapping.getDataRowNum());
            List<Map<String, String>> datas = ParseExcelUtil.resolveExcel(fileNameFull, headNum, dataNum);
            //自由导入数据组装、更新
            String templateType = templatesPackage.getType();
            if("4".equals(templateType)){//更新模板
                //查询自由导入匹配关系
                List<String> freeRelation = this.templateRelationPackageService.selectFreeTemplateRelationBytemplateId(templateId);
                datas = DataImportUtil.dataReplace2(datas, importDataMapping,freeRelation);
            }else{
                datas = DataImportUtil.dataReplace(datas, importDataMapping);
            }
            importBatchNo = DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS, new Date());// 生成导入批次号年月日时分秒
            OrgPackage orgPackage = this.orgPackageService.selectOrgPackageByDeptId(orgId);
            String orgName = orgPackage.getOrgName();
            paramList2 = DataImportUtil.dataConvert(datas, orgId,importBatchNo,orgName);
            //插入临时表
            this.batchAddTemp(paramList2);
            datas = null;
        }
        return importBatchNo;
    }

    @Override
    public Map<String, String> checkData(HttpServletRequest request, String importBatchNo) throws Exception{
        Map<String, String> map = new HashMap<String, String>();
        List<Map<String, String>> exectionList = null;
        List<String> exceptionIds = new ArrayList<String>();//异常的机构案件号
        //查询临时表信息
        TempCurAssetsPackage param = new TempCurAssetsPackage();
        param.setImportBatchNo(importBatchNo);
        List<TempCurAssetsPackage> tempCurAssetList = this.selectTempCurAssetsPackageList(param);
        //规则校验
        exectionList = DataImportUtil.checkData2(tempCurAssetList);
        //去除异常数据
        if(exectionList.size() > 0) {
            for (Map<String, String> exceptionidmap : exectionList) {
                String exceptionid = exceptionidmap.get("id");
                exceptionIds.add(exceptionid);
                //更新状态
                this.updateExceptionStatus(exceptionid);
            }
        }
        //查询需要更新的数据集合
        int updateCount = this.selectUpdateCount(importBatchNo);
        //修改状态为需要更新状态
        if(updateCount>0){
            this.modifyUpdateStatus2(importBatchNo);
        }
        //查询需要新增的数据集合
        int insertCount = this.selectInsertCount(importBatchNo);

        request.getSession().setAttribute("exectionList",exectionList);
        map.put("insertSize",String.valueOf(insertCount));
        map.put("updateSize",String.valueOf(updateCount));
        map.put("exectionSize",String.valueOf(exectionList.size()));

        return map;
    }

    @Override
    public Map<String, String> checkUpdateData(HttpServletRequest request, String importBatchNo) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        List<Map<String, String>> exectionList = null;
        //查询临时表信息
        TempCurAssetsPackage param = new TempCurAssetsPackage();
        param.setImportBatchNo(importBatchNo);
        List<TempCurAssetsPackage> tempCurAssetList = this.selectTempCurAssetsPackageList(param);
        //规则校验
        exectionList = DataImportUtil.checkUpdateData(tempCurAssetList);
        //去除异常数据
        if(exectionList.size() > 0) {
            for (Map<String, String> exceptionidmap : exectionList) {
                String exceptionid = exceptionidmap.get("id");
                //更新状态
                this.updateExceptionStatus(exceptionid);
            }
        }
        //去除不存在的数据
        this.curAssetsPackageMapper.updateNotExists(importBatchNo);
        //查询不存在的数据数量
        int notExistsCount = this.curAssetsPackageMapper.findNotExistsCount(importBatchNo);
        //查询正常的的数据数量
        int normalCount = this.curAssetsPackageMapper.findNormalCount(importBatchNo);

        request.getSession().setAttribute("exectionList",exectionList);
        map.put("exectionCount",String.valueOf(exectionList.size()));
        map.put("notExistsCount",String.valueOf(notExistsCount));
        map.put("normalCount",String.valueOf(normalCount));

        return map;
    }


    @Override
    public ImportDataMapping voluation(ImportDataMapping bean, String templateId) {
        /** 查询匹配关系 */
        //根据模板名称查询模板信息
        TemplatesPackage templateInfo = templatesPackageService.selectTemplatesPackageById(templateId);
        String headRowNum = templateInfo.getHeadRowNum();
        String dataRowNum = templateInfo.getDataRowNum();
        //根据模板id查询模板表头映射关系
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
            if ("所属机构".equals(systemTemplateName)) {
                bean.setOrg(customerTemplateName);
                continue;
            }
            if ("案件回收时间".equals(systemTemplateName)) {
                bean.setAjhssj(customerTemplateName);
                continue;
            }
            if ("手别".equals(systemTemplateName)) {
                bean.setTransferType(customerTemplateName);
                continue;
            }
            if ("姓名".equals(systemTemplateName)) {
                bean.setCurName(customerTemplateName);
                continue;}
            if ("证件号".equals(systemTemplateName)) {
                bean.setCertificateNo(customerTemplateName);
                continue;}
            if ("性别".equals(systemTemplateName)) {
                bean.setCurSex(customerTemplateName);
                continue;}
            if ("婚姻状况".equals(systemTemplateName)) {
                bean.setMarriage(customerTemplateName);
                continue;}
            if ("教育程度".equals(systemTemplateName)) {
                bean.setEducation(customerTemplateName);
                continue;}
            if ("委案金额".equals(systemTemplateName)) {
                bean.setRmbYe(customerTemplateName);
                continue;}
            if ("结案应还金额".equals(systemTemplateName)) {
                bean.setWaYe(customerTemplateName);
                continue;}
            if ("入催日".equals(systemTemplateName)) {
                bean.setRcr(customerTemplateName);
                continue;}
            if ("账单日".equals(systemTemplateName)) {
                bean.setAccountDate(customerTemplateName);
                continue;}
            if ("逾期天数".equals(systemTemplateName)) {
                bean.setOverdueDays(customerTemplateName);
                continue;}
            if ("逾期起始日".equals(systemTemplateName)) {
                bean.setFirstYqDate(customerTemplateName);
                continue;}
            if ("应还日期".equals(systemTemplateName)) {
                bean.setFirstYqjcDate(customerTemplateName);
                continue;}
            if ("欠款总金额".equals(systemTemplateName)) {
                bean.setRmbQkzje(customerTemplateName);
                continue;}
            if ("最低应还金额".equals(systemTemplateName)) {
                bean.setRmbZdyhje(customerTemplateName);
                continue;}
            if ("应还本金".equals(systemTemplateName)) {
                bean.setRmbYhbjzje(customerTemplateName);
                continue;}
            if ("应还利息".equals(systemTemplateName)) {
                bean.setRmbYhlizje(customerTemplateName);
                continue;}
            if ("应还罚息".equals(systemTemplateName)) {
                bean.setRmbYhfxzje(customerTemplateName);
                continue;}
            if ("应还费用".equals(systemTemplateName)) {
                bean.setRmbYhfyzje(customerTemplateName);
                continue;}
            if ("滞纳金".equals(systemTemplateName)) {
                bean.setZnj(customerTemplateName);
                continue;}
            if ("所属城市".equals(systemTemplateName)) {
                bean.setWwCityName(customerTemplateName);
                continue;}
            if ("所属区域".equals(systemTemplateName)) {
                bean.setAreaCenter(customerTemplateName);
                continue;}
            if ("推荐商户".equals(systemTemplateName)) {
                bean.setTjFirm(customerTemplateName);
                continue;}
            if ("推荐网点".equals(systemTemplateName)) {
                bean.setTjWd(customerTemplateName);
                continue;}
            if ("产品名称".equals(systemTemplateName)) {
                bean.setCpmc(customerTemplateName);
                continue;}
            if ("还款方式".equals(systemTemplateName)) {
                bean.setHkType(customerTemplateName);
                continue;}
            if ("放款金额".equals(systemTemplateName)) {
                bean.setBorrowEd(customerTemplateName);
                continue;}
            if ("分期期数".equals(systemTemplateName)) {
                bean.setFz(customerTemplateName);
                continue;}
            if ("年利率".equals(systemTemplateName)) {
                bean.setYearRates(customerTemplateName);
                continue;}
            if ("日利率".equals(systemTemplateName)) {
                bean.setDayRates(customerTemplateName);
                continue;}
            if ("借款卡号".equals(systemTemplateName)) {
                bean.setBorrowNo(customerTemplateName);
                continue;}
            if ("借款卡银行".equals(systemTemplateName)) {
                bean.setBorrowBlank(customerTemplateName);
                continue;}
            if ("单位名称".equals(systemTemplateName)) {
                bean.setWorkName(customerTemplateName);
                continue;}
            if ("电子邮箱".equals(systemTemplateName)) {
                bean.setEmail(customerTemplateName);
                continue;}
            if ("单位地址".equals(systemTemplateName)) {
                bean.setWorkAddress(customerTemplateName);
                continue;}
            if ("住宅地址".equals(systemTemplateName)) {
                bean.setCustomerHomeAddress(customerTemplateName);
                continue;}
            if ("户籍地址".equals(systemTemplateName)) {
                bean.setRegistAddress(customerTemplateName);
                continue;}
            if ("身份证地址".equals(systemTemplateName)) {
                bean.setCertificateAddress(customerTemplateName);
                continue;}
            if ("账单地址".equals(systemTemplateName)) {
                bean.setBillAddress(customerTemplateName);
                continue;}
            if ("首逾标识".equals(systemTemplateName)) {
                bean.setFirstYqFlag(customerTemplateName);
                continue;}
            if ("最大逾期天数".equals(systemTemplateName)) {
                bean.setMaxYqtsHis(customerTemplateName);
                continue;}
            if ("累计逾期天数".equals(systemTemplateName)) {
                bean.setSumYqtsHis(customerTemplateName);
                continue;}
            if ("逾期次数".equals(systemTemplateName)) {
                bean.setSumYqcsHis(customerTemplateName);
                continue;}
            if ("移动电话".equals(systemTemplateName)) {
                bean.setCustomerMobile(customerTemplateName);
                continue;}
            if ("联系人1姓名".equals(systemTemplateName)) {
                bean.setFirstLiaisonName(customerTemplateName);
                continue;}
            if ("联系人1关系".equals(systemTemplateName)) {
                bean.setFirstLiaisonRelation(customerTemplateName);
                continue;}
            if ("联系人2姓名".equals(systemTemplateName)) {
                bean.setSecondLiaisonName(customerTemplateName);
                continue;}
            if ("联系人2关系".equals(systemTemplateName)) {
                bean.setSecondLiaisonRelation(customerTemplateName);
                continue;}
            if ("联系人3姓名".equals(systemTemplateName)) {
                bean.setThreeLiaisonName(customerTemplateName);
                continue;}
            if ("联系人3关系".equals(systemTemplateName)) {
                bean.setThreeLiaisonRelation(customerTemplateName);
                continue;}
            if ("住宅电话".equals(systemTemplateName)) {
                bean.setCustomerHomeTel(customerTemplateName);
                continue;}
            if ("联系人1电话1".equals(systemTemplateName)) {
                bean.setFirstLiaisonMobile(customerTemplateName);
                continue;}
            if ("联系人1电话2".equals(systemTemplateName)) {
                bean.setFirstLiaisonTel(customerTemplateName);
                continue;}
            if ("联系人2电话1".equals(systemTemplateName)) {
                bean.setSecondLiaisonMobile(customerTemplateName);
                continue;}
            if ("联系人2电话2".equals(systemTemplateName)) {
                bean.setSecondLiaisonTel(customerTemplateName);
                continue;}
            if ("联系人3电话1".equals(systemTemplateName)) {
                bean.setThreeLiaisonMobile(customerTemplateName);
                continue;}
            if ("联系人3电话2".equals(systemTemplateName)) {
                bean.setThreeLiaisonTel(customerTemplateName);
                continue;}
            if ("单位电话".equals(systemTemplateName)) {
                bean.setWorkTel(customerTemplateName);
                continue;}

            if ("账龄".equals(systemTemplateName)) {
                bean.setAccountAge(customerTemplateName);
                continue;}
            if ("留案标签".equals(systemTemplateName)) {
                bean.setLaFlag(customerTemplateName);
                continue;}
            if ("风险标签".equals(systemTemplateName)) {
                bean.setFxFlag(customerTemplateName);
                continue;}
            if ("合同类型".equals(systemTemplateName)) {
                bean.setHtlx(customerTemplateName);
                continue;}
            if ("减免标签".equals(systemTemplateName)) {
                bean.setJmbq(customerTemplateName);
                continue;}
            if ("法催标签".equals(systemTemplateName)) {
                bean.setFcbq(customerTemplateName);
                continue;}
            if ("罚息是否变化".equals(systemTemplateName)) {
                bean.setFxsfbh(customerTemplateName);
                continue;}
            if ("备注".equals(systemTemplateName)) {
                bean.setRemark(customerTemplateName);
                continue;}
            if ("退案日".equals(systemTemplateName)) {
                bean.setTar(customerTemplateName);
                continue;}
            if ("借款日期".equals(systemTemplateName)) {
                bean.setJkrq(customerTemplateName);
                continue;}
            if ("最近一次还款日".equals(systemTemplateName)) {
                bean.setZhychkr(customerTemplateName);
                continue;}
            if ("每期还款金额".equals(systemTemplateName)) {
                bean.setMqhkje(customerTemplateName);
                continue;}
            if ("当期欠款金额".equals(systemTemplateName)) {
                bean.setDqqkje(customerTemplateName);
                continue;}
            if ("累计已还金额".equals(systemTemplateName)) {
                bean.setLjyhje(customerTemplateName);
                continue;}
            if ("首付金额".equals(systemTemplateName)) {
                bean.setSfje(customerTemplateName);
                continue;}
            if ("指定还款账号1".equals(systemTemplateName)) {
                bean.setZdhkzh1(customerTemplateName);
                continue;}
            if ("指定还款账号2".equals(systemTemplateName)) {
                bean.setZdhkzh2(customerTemplateName);
                continue;}
            if ("指定还款账户户名1".equals(systemTemplateName)) {
                bean.setZdhkzhhm1(customerTemplateName);
                continue;}
            if ("指定还款账户户名2".equals(systemTemplateName)) {
                bean.setZdhkzhhm2(customerTemplateName);
                continue;}
            if ("指定还款渠道1".equals(systemTemplateName)) {
                bean.setZdhkqd1(customerTemplateName);
                continue;}
            if ("指定还款渠道2".equals(systemTemplateName)) {
                bean.setZdhkqd2(customerTemplateName);
                continue;}
            if ("考核目标".equals(systemTemplateName)) {
                bean.setKhmb(customerTemplateName);
                continue;}
            if ("商品价格".equals(systemTemplateName)) {
                bean.setSpjg(customerTemplateName);
                continue;}
            if ("贷款类型".equals(systemTemplateName)) {
                bean.setDklx(customerTemplateName);
                continue;}
            if ("借款笔数".equals(systemTemplateName)) {
                bean.setJkbs(customerTemplateName);
                continue;}
            if ("商品信息".equals(systemTemplateName)) {
                bean.setSpxx(customerTemplateName);
                continue;}
            if ("委案次数".equals(systemTemplateName)) {
                bean.setWacs(customerTemplateName);
                continue;}
            if ("已还期数".equals(systemTemplateName)) {
                bean.setYkqs(customerTemplateName);
                continue;}
            if ("工作部门".equals(systemTemplateName)) {
                bean.setWorkDept(customerTemplateName);
                continue;}
            if ("客户电话2".equals(systemTemplateName)) {
                bean.setCustomerMobile2(customerTemplateName);
                continue;}
            if ("客户电话3".equals(systemTemplateName)) {
                bean.setCustomerMobile3(customerTemplateName);
                continue;}
            if ("客户电话4".equals(systemTemplateName)) {
                bean.setCustomerMobile4(customerTemplateName);
                continue;}
            if ("联系人4姓名".equals(systemTemplateName)) {
                bean.setFourthLiaisonName(customerTemplateName);
                continue;}
            if ("联系人4关系".equals(systemTemplateName)) {
                bean.setFourthLiaisonRelation(customerTemplateName);
                continue;}
            if ("联系人4电话".equals(systemTemplateName)) {
                bean.setFourthLiaisonMobile(customerTemplateName);
                continue;}
            if ("联系人5姓名".equals(systemTemplateName)) {
                bean.setFifthLiaisonName(customerTemplateName);
                continue;}
            if ("联系人5关系".equals(systemTemplateName)) {
                bean.setFifthLiaisonRelation(customerTemplateName);
                continue;}
            if ("联系人5电话".equals(systemTemplateName)) {
                bean.setFifthLiaisonMobile(customerTemplateName);
                continue;}
        }
        return bean;
    }


    public void updateCollect(List<TempCurAssetsPackage> paramList) throws Exception{
        List<Assets2> assets = buildParam(paramList);
        this.tlcDuncaseService.DuncaseUpdate(assets);
    }


    public List<Assets2> buildParam(List<TempCurAssetsPackage> paramList)throws Exception{
        List<Assets2> desList = new ArrayList<>(paramList.size());
        paramList.stream().forEach(tempCurAssets ->{
//            String importBatchNo = this.curAssetsPackageMapper.selectBatchNo(tempCurAssets);
//            tempCurAssets.setImportBatchNo(importBatchNo);
            String jsonStr = JSON.toJSONString(tempCurAssets);
            Assets2 assets = JSONObject.parseObject(jsonStr, Assets2.class);
            desList.add(assets);
        });
        return desList;
    }

    private void insertFreeImport(List<TempCurAssetsPackage> paramList)throws Exception{
        List<FreeImport> freeImports = buildFreeImport(paramList);
        if(freeImports.size()>0){
            this.curAssetsPackageMapper.insertFreeImport(freeImports);
        }
    }

    private List<FreeImport> buildFreeImport(List<TempCurAssetsPackage> paramList){
        List<FreeImport> resultList = new ArrayList<>();
        paramList.stream().forEach(tempCurAssets ->{
            String freeImport = tempCurAssets.getFreeImport();
            if(freeImport != null && !"".equals(freeImport)){
                FreeImport dto = new FreeImport();
                dto.setOrgCasno(tempCurAssets.getOrgCasno());
                dto.setOrgId(tempCurAssets.getOrgId());
                dto.setImportBatchNo(tempCurAssets.getImportBatchNo());
                dto.setValue(freeImport);
                dto.setCreateBy(ShiroUtils.getSysUser().getUserName());
                dto.setCreateTime(tempCurAssets.getCreateTime());
                resultList.add(dto);
            }
        });
        return resultList;
    }

}
