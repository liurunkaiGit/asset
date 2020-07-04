package com.ruoyi.assetspackage.service.impl;

import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.mapper.IcollectionRecordImprotMapper;
import com.ruoyi.assetspackage.service.*;
import com.ruoyi.assetspackage.util.ParseExcelUtil;
import com.ruoyi.assetspackage.util.RecordDataImportUtil;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * 催收记录service业务层处理
 *
 * @author guozeqi
 * @date 2020-05-09
 */
@Slf4j
@Service
public class CollectionRecordImprotServiceImpl implements IcollectionRecordImprotService {

    @Autowired
    private IcollectionRecordImprotMapper icollectionRecordImprotMapper;

    @Autowired
    private IcollectionRecordImprotAsyncService collectionRecordImprotAsyncService;

    @Autowired
    private RecordImportDataMapping recordImportDataMapping;

    @Autowired
    private ITemplatesPackageService templatesPackageService;

    @Autowired
    private IOrgPackageService orgPackageService;

    @Autowired
    private ITemplateRelationPackageService templateRelationPackageService;

    @Override
    public void batchAddTemp(List<CollectionRecordImportTemp> paramList) throws Exception {
        int total = paramList.size();
        int index = 500;
        int pagesize = total/index;
        if(total <=index){
            this.icollectionRecordImprotMapper.batchAddTemp(paramList);
        }else{
            for(int i=0;i<pagesize;i++){
                List lt = paramList.subList(i*index, (i+1)*index);
                this.icollectionRecordImprotMapper.batchAddTemp(lt);

            }
            if(total % index != 0){
                List lt = paramList.subList(index * pagesize,total);
                this.icollectionRecordImprotMapper.batchAddTemp(lt);
            }
        }
    }

    @Override
    public void deleteTempTable(String importBatchNo) throws Exception {
        this.icollectionRecordImprotMapper.deleteTempTable(importBatchNo);
    }

    /**
     * 查询临时表
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public List<CollectionRecordImportTemp> selectTempCollectionRecordList(CollectionRecordImportTemp param) throws Exception {
        return this.icollectionRecordImprotMapper.selectTempCollectionRecordList(param);
    }

    @Override
    public int selectTempCollectionRecordCount(CollectionRecordImportTemp param) throws Exception {
        return this.icollectionRecordImprotMapper.selectTempCollectionRecordCount(param);
    }

    /**
     * 根据id更新异常状态
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int updateExceptionStatus(String id) throws Exception {
        return this.icollectionRecordImprotMapper.updateExceptionStatus(id);
    }

    @Override
    public int updateExceptionStatus2(String id) throws Exception {
        return this.icollectionRecordImprotMapper.updateExceptionStatus2(id);
    }

    /**
     * 查找临时表中有而案件表中不存在的案件
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, String>> findNotExistsCase(String importBatchNo) throws Exception {

        return this.icollectionRecordImprotMapper.findNotExistsCase(importBatchNo);
    }

    /**
     * 批量执行
     * @param importBatchNo
     * @throws Exception
     */
    @Override
    public AjaxResult batchExecute(String importBatchNo) throws Exception {
        //查询导入临时表的信息
        CollectionRecordImportTemp findParam = new CollectionRecordImportTemp();
        findParam.setImportBatchNo(importBatchNo);
        Map<String, Object> batchInfo = this.icollectionRecordImprotMapper.selectBatchInfo(findParam);
        //插入流水表
        CollectionrecordimprotFlow insertParam = new CollectionrecordimprotFlow();
        insertParam.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        insertParam.setImportBatchNo((String)batchInfo.get("importBatchNo"));
        insertParam.setTotal(String.valueOf(batchInfo.get("total")));
        insertParam.setOrgId((String)batchInfo.get("orgId"));
        insertParam.setOrgName((String)batchInfo.get("orgName"));
        insertParam.setCreateBy((String)batchInfo.get("createBy"));
        insertParam.setCreateTime((Date)batchInfo.get("createTime"));
        this.icollectionRecordImprotMapper.insertTLcCollectionrecordimprotFlow(insertParam);
        //批量插入通话记录表
        CollectionRecordImportTemp param = new CollectionRecordImportTemp();
        param.setImportBatchNo(importBatchNo);
        this.icollectionRecordImprotMapper.batchAddCallRecord(param);
        //更新案件表 任务表
        collectionRecordImprotAsyncService.modifyTableData(importBatchNo);
        return AjaxResult.success("新增成功");
    }

    /**
     * 查询导入首页展示数据
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public List<CollectionrecordimprotFlow> selectTLcCollectionrecordimprotFlowList(CollectionRecordImportTemp param) throws Exception {
//        return this.icollectionRecordImprotMapper.selectBatchList(param);
        return this.icollectionRecordImprotMapper.selectTLcCollectionrecordimprotFlowList(param);
    }

    @Override
    @Transactional
    public String handler(HttpServletRequest request, MultipartFile file, String templateId, String orgId, String type) throws Exception {
        List<CollectionRecordImportTemp> paramList = null;
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
            orgId=String.valueOf(ShiroUtils.getSysUser().getOrgId());
            recordImportDataMapping = this.voluation(recordImportDataMapping, templateId);
            int headNum = Integer.valueOf(recordImportDataMapping.getHeadRowNum());
            int dataNum = Integer.valueOf(recordImportDataMapping.getDataRowNum());
            List<Map<String, String>> datas = ParseExcelUtil.resolveExcel(fileNameFull, headNum, dataNum);
            datas = RecordDataImportUtil.dataReplace(datas, recordImportDataMapping);
            importBatchNo = DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS, new Date());// 生成导入批次号年月日时分秒
            OrgPackage orgPackage = this.orgPackageService.selectOrgPackageByDeptId(orgId);
            String orgName = orgPackage.getOrgName();
            paramList = RecordDataImportUtil.dataConvert(datas, orgId,importBatchNo,orgName);
            //插入临时表
            this.batchAddTemp(paramList);
            datas = null;
        }
        return importBatchNo;
    }

    @Override
    public Map<String, String> checkData(HttpServletRequest request, String importBatchNo) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
//        List<CollectionRecordImportTemp> insertCallRecordList = new ArrayList<CollectionRecordImportTemp>();
        List<Map<String, String>>  notExistsList = new ArrayList<Map<String, String>>();
        List<Map<String, String>> exectionList = null;
        List<String> exceptionIds = new ArrayList<String>();//异常的机构案件号
        int total =0;
        //案件不存在的
        notExistsList = this.findNotExistsCase(importBatchNo);
        //去除案件不存在数据
        if(notExistsList.size() > 0) {
            for (Map<String, String> notExistsMap : notExistsList) {
                String exceptionid = notExistsMap.get("id");
                //更新状态
                this.updateExceptionStatus2(exceptionid);
            }
        }
        //查询临时表信息
        CollectionRecordImportTemp param = new CollectionRecordImportTemp();
        param.setImportBatchNo(importBatchNo);
        param.setIsException("0");
        List<CollectionRecordImportTemp> tempCollectionRecordList = this.selectTempCollectionRecordList(param);
        //规则校验
        exectionList = RecordDataImportUtil.checkData(tempCollectionRecordList);
        //去除异常数据
        if(exectionList.size() > 0) {
            for (Map<String, String> exceptionidmap : exectionList) {
                String exceptionid = exceptionidmap.get("id");
                //更新状态
                this.updateExceptionStatus(exceptionid);
            }
        }
        //查询需要新增电催记录的数据
        CollectionRecordImportTemp findparam = new CollectionRecordImportTemp();
        findparam.setIsException("0");
        findparam.setImportBatchNo(importBatchNo);
//            insertCallRecordList = this.collectionRecordImprotService.selectTempCollectionRecordList(findparam);
        total = this.selectTempCollectionRecordCount(findparam);
        request.getSession().setAttribute("RecordExectionList",exectionList);
        request.getSession().setAttribute("RecordNotExistsList",notExistsList);
        map.put("insertSize",String.valueOf(total));
        map.put("exectionSize",String.valueOf(exectionList.size()));
        map.put("notExistsSize",String.valueOf(notExistsList.size()));
        return map;
    }

    private RecordImportDataMapping voluation(RecordImportDataMapping bean, String templateId) {
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
                bean.setOrgCaseNo(customerTemplateName);
                continue;
            }
            if ("身份证".equals(systemTemplateName)) {
                bean.setCertificateNo(customerTemplateName);
                continue;
            }
            if ("手机号".equals(systemTemplateName)) {
                bean.setPhone(customerTemplateName);
                continue;
            }
            if ("关系".equals(systemTemplateName)) {
                bean.setRelation(customerTemplateName);
                continue;
            }
           /* if ("电话码".equals(systemTemplateName)) {
                bean.setPhoneCode(customerTemplateName);
                continue;
            }*/
            if ("备注".equals(systemTemplateName)) {
                bean.setRemark(customerTemplateName);
                continue;
            }
            if ("坐席".equals(systemTemplateName)) {
                bean.setSeat(customerTemplateName);
                continue;
            }
            if ("拨打时间".equals(systemTemplateName)) {
                bean.setMakeCallTime(customerTemplateName);
                continue;
            }
            if ("通话开始时间".equals(systemTemplateName)) {
                bean.setCallStartTime(customerTemplateName);
                continue;
            }
            if ("通话结束时间".equals(systemTemplateName)) {
                bean.setCallEndTime(customerTemplateName);
                continue;
            }
            if ("通话时长".equals(systemTemplateName)) {
                bean.setCallLength(customerTemplateName);
                continue;
            }
            if ("通话录音ID".equals(systemTemplateName)) {
                bean.setCallRecordId(customerTemplateName);
                continue;
            }
            if ("客户意向等级".equals(systemTemplateName)) {
                bean.setGrade(customerTemplateName);
                continue;
            }
            if ("客户名称".equals(systemTemplateName)) {
                bean.setName(customerTemplateName);
                continue;
            }
            if ("通话状态".equals(systemTemplateName)) {
                bean.setCallStatus(customerTemplateName);
                continue;
            }
        }
        return bean;
    }

}
