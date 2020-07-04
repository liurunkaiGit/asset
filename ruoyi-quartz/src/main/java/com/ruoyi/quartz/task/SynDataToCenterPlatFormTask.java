package com.ruoyi.quartz.task;

import com.github.pagehelper.PageHelper;
import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.domain.TLcCustinfo;
import com.ruoyi.custom.service.ITLcCustContactService;
import com.ruoyi.custom.service.ITLcCustinfoService;
import com.ruoyi.duncase.domain.TLcDuncase;
import com.ruoyi.duncase.service.ITLcDuncaseService;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.service.ITLcCallRecordService;
import com.ruoyi.task.service.ITLcTaskService;
import com.ruoyi.utils.CSVUtils;
import com.ruoyi.utils.SFTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 定时任务--同步数据到公司数据中心平台
 * 
 * @author fengzhitao
 */
@Slf4j
@Component("synDataToCenterPlatFormTask")
public class SynDataToCenterPlatFormTask
{
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private IOrgPackageService orgPackageService;
    @Autowired
    private ITLcCallRecordService tLcCallRecordService;
    @Autowired
    private ITLcCustContactService tLcCustContactService;
    @Autowired
    private ITLcCustinfoService tLcCustinfoService;
    @Autowired
    private ITLcDuncaseService tLcDuncaseService;
    @Autowired
    private ITLcTaskService tLcTaskService;
    @Autowired
    private ISysConfigService sysConfigService;

    /**
     * 下载语音通话文件
     * 默认不传开始时间、结束时间
     */
    public void SynDataToCenterPlatForm(){
        this.SynDataToCenterPlatForm(null);
    }
    /**
     * 下载语音通话文件
     * @param startDate
     */
    public void SynDataToCenterPlatForm(String startDate){
        if(StringUtils.isEmpty(startDate)){
            // 获取当天的0点
            SimpleDateFormat dateFmt = new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_MM_SS);
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
//            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date dBefore = calendar.getTime();
            startDate = dateFmt.format(dBefore);
            startDate = startDate.substring(0, 10) + " 00:00:00";
        }else{//开始时间和结束时间都不为空
            startDate = startDate.substring(0, 10) + " 00:00:00";
        }
        /*startDate = "2020-06-08 00:00:00";
        String endDate = "2020-06-08 23:59:59";*/

        //获得SFTP链接
        SFTPUtil sftp = loginSftp();
        //1、生成 sys_user 表数据，并上传FTP服务器
        createUserFile(startDate,sftp);
        //2、生成 sys_dept 表数据，并上传FTP服务器
        createDeptFile(startDate,sftp);
        //3、生成 org_package 表数据，并上传FTP服务器
        createOrgPackageFile(startDate,sftp);
        //4、生成 t_lc_call_record 表数据，并上传FTP服务器
        createCallRecordFile(startDate,sftp);
        //5、生成 t_lc_cust_contact 表数据，并上传FTP服务器
        createCustContactFile(startDate,sftp);
        //6、生成 t_lc_custinfo 表数据，并上传FTP服务器
        createCustinfoFile(startDate,sftp);
        //7、生成 t_lc_duncase 表数据，并上传FTP服务器
        createDuncaseFile(startDate,sftp);
        //8、生成 t_lc_task 表数据，并上传FTP服务器
        createTaskFile(startDate,sftp);

        //关闭SFTP连接
        logoutSftp(sftp);
    }

    private SFTPUtil loginSftp(){
        String ftpUserName = sysConfigService.selectConfigByKey("file.ftpUserName");
        String ftpPwd = sysConfigService.selectConfigByKey("file.ftpPwd");
        String ftpIpAddr = sysConfigService.selectConfigByKey("file.ftpIpAddr");
        String ftpPort = sysConfigService.selectConfigByKey("file.ftpPort");
        SFTPUtil sftp = new SFTPUtil(ftpUserName, ftpPwd, ftpIpAddr, Integer.parseInt(ftpPort));
        sftp.login();
        return sftp;
    }

    private void logoutSftp(SFTPUtil sftp){
        sftp.logout();
    }

    /**
     * 生成并上传sys_user表数据
     * @param startDate
     * @param sftp
     */
    private void createUserFile(String startDate,SFTPUtil sftp) {
        SysUser sysUser = new SysUser();
        sysUser.setUpdateTime(DateUtils.parseDate(startDate));
        List<Map<String,Object>> list = sysUserService.selectUserByTime(sysUser);
        list = formatUserList(list);

        String tableName = "sys_user";
        uploadSftp(sftp, list, tableName);
    }

    /**
     * 生成并上传sys_dept表数据
     * @param startDate
     * @param sftp
     */
    private void createDeptFile(String startDate,SFTPUtil sftp) {
        SysDept sysDept = new SysDept();
        sysDept.setUpdateTime(DateUtils.parseDate(startDate));
        List<Map<String,Object>> list = sysDeptService.selectDeptByTime(sysDept);
        list = formatDeptList(list);

        String tableName = "sys_dept";
        uploadSftp(sftp, list, tableName);
    }

    /**
     * 生成并上传org_package表数据
     * @param startDate
     * @param sftp
     */
    private void createOrgPackageFile(String startDate,SFTPUtil sftp) {
        OrgPackage orgPackage = new OrgPackage();
        orgPackage.setUpdateDate(DateUtils.parseDate(startDate));
        List<Map<String,Object>> list = orgPackageService.selectOrgPackageByTime(orgPackage);
        list = formatOrgPackageList(list);

        String tableName = "org_package";
        uploadSftp(sftp, list, tableName);
    }

    /**
     * 生成并上传t_lc_call_record表数据
     * @param startDate
     * @param sftp
     */
    private void createCallRecordFile(String startDate,SFTPUtil sftp) {

        int pageSize = 10000;//每页的数据条数
        int pageCount = 0;//总页数
        int count = tLcCallRecordService.selectCallRecordCount(DateUtils.parseDate(startDate));//总条数
        pageCount = count/pageSize + 1;

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> temp = null;
        for(int i = 0 ; i < pageCount; i ++){
            temp = tLcCallRecordService.selectCallRecordByTime(DateUtils.parseDate(startDate),pageSize*i,pageSize);
            log.info("第{}次查询t_lc_call_record表数据结束",i+1);
            temp = formatCallRecordList(temp);
            list.addAll(temp);
        }

        String tableName = "t_lc_call_record";
        uploadSftp(sftp, list, tableName);
    }

    /**
     * 生成并上传t_lc_cust_contact表数据
     * @param startDate
     * @param sftp
     */
    private void createCustContactFile(String startDate,SFTPUtil sftp) {

        int pageSize = 10000;//每页的数据条数
        int pageCount = 0;//总页数
        int count = tLcCustContactService.selectCustContactCount(DateUtils.parseDate(startDate));//总条数
        pageCount = count/pageSize + 1;

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> temp = null;
        for(int i = 0 ; i < pageCount; i ++){
            temp = tLcCustContactService.selectCustContactByTime(DateUtils.parseDate(startDate),pageSize*i,pageSize);
            log.info("第{}次查询t_lc_cust_contact表数据结束",i+1);
            temp = formatCustContactList(temp);
            list.addAll(temp);
        }

        String tableName = "t_lc_cust_contact";
        uploadSftp(sftp, list, tableName);
    }

    /**
     * 生成并上传 t_lc_custinfo 表数据
     * @param startDate
     * @param sftp
     */
    private void createCustinfoFile(String startDate,SFTPUtil sftp) {
        int pageSize = 10000;//每页的数据条数
        int pageCount = 0;//总页数
        int count = tLcCustinfoService.selectCustinfoCount(DateUtils.parseDate(startDate));//总条数
        pageCount = count/pageSize + 1;

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> temp = null;
        for(int i = 0 ; i < pageCount; i ++){
            temp = tLcCustinfoService.selectCustinfoByTime(DateUtils.parseDate(startDate),pageSize*i,pageSize);
            log.info("第{}次查询t_lc_custinfo表数据结束",i+1);
            temp = formatCustinfoList(temp);
            list.addAll(temp);
        }

        String tableName = "t_lc_custinfo";
        uploadSftp(sftp, list, tableName);
    }

    /**
     * 生成并上传 t_lc_duncase 表数据
     * @param startDate
     * @param sftp
     */
    private void createDuncaseFile(String startDate,SFTPUtil sftp) {

        int pageSize = 10000;//每页的数据条数
        int pageCount = 0;//总页数
        int count = tLcDuncaseService.selectDuncaseCount(DateUtils.parseDate(startDate));//总条数
        pageCount = count/pageSize + 1;

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> temp = null;
        for(int i = 0 ; i < pageCount; i ++){
            temp = tLcDuncaseService.selectDuncaseByTime(DateUtils.parseDate(startDate),pageSize*i,pageSize);
            log.info("第{}次查询t_lc_duncase表数据结束",i+1);
            temp = formatDuncaseList(temp);
            list.addAll(temp);
        }

        String tableName = "t_lc_duncase";
        uploadSftp(sftp, list, tableName);
    }

    /**
     * 生成并上传 t_lc_task 表数据
     * @param startDate
     * @param sftp
     */
    private void createTaskFile(String startDate,SFTPUtil sftp) {

        int pageSize = 10000;//每页的数据条数
        int pageCount = 0;//总页数
        int count = tLcTaskService.selectTaskCount(DateUtils.parseDate(startDate));//总条数
        log.info("t_lc_task表总数={}",count);
        pageCount = count/pageSize + 1;

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> temp = null;
        for(int i = 0 ; i < pageCount; i ++){
            temp = tLcTaskService.selectTaskByTime(DateUtils.parseDate(startDate),pageSize*i,pageSize);
            log.info("第{}次查询t_lc_task表数据结束",i+1);
            temp = formatTaskList(temp);
            list.addAll(temp);
        }

        String tableName = "t_lc_task";
        uploadSftp(sftp, list, tableName);
    }

    private void uploadSftp(SFTPUtil sftp, List<Map<String, Object>> list, String tableName) {
        String synCenterPlatFormPath = sysConfigService.selectConfigByKey("file.synCenterPlatFormPath");
        String projectName = sysConfigService.selectConfigByKey("file.synCenterPlatFormProjectName");
        String ftpFileName = sysConfigService.selectConfigByKey("file.ftpFileName");

        //文件名
        String fileName = projectName + "-@-" + tableName + "-@-" + DateUtils.dateNowForYYYYMMDD() + "-@-";
        int countNum = CSVUtils.getCountNum(list.size());//产生文件的总数
        for(int i = 0; i < countNum; i ++){
            fileName = fileName + CSVUtils.getNo(i+1+"");
            File file = CSVUtils.createCSVFile(list, null, synCenterPlatFormPath, fileName + ".log");
            String fileName2 = file.getName();
            log.info("文件名称：" + fileName2);
            try{
                InputStream is = new FileInputStream(file);
                sftp.upload(ftpFileName, fileName2, is);
                //上传.ok文件
                File okFile = CSVUtils.createCSVFile(null, null, synCenterPlatFormPath, fileName + ".ok");
                InputStream okIs = new FileInputStream(okFile);
                sftp.upload(ftpFileName, okFile.getName(), okIs);
            }catch (Exception e){
                log.error("SFTP上传"+tableName+"文件失败，文件名称={},异常={}：",fileName2,e);
            }
        }
    }

    /**
     * 格式化 用户表 数据
     * @param list
     * @return
     */
    private List<Map<String,Object>> formatUserList(List<Map<String,Object>> list){
        if(!list.isEmpty()){
            for(int i = 0;i < list.size(); i ++){
                Map<String,Object> map = list.get(i);
                map.put("userId",map.get("userId") != null ? map.get("userId").toString() : "NULL");
                map.put("deptId",map.get("deptId") != null ? map.get("deptId").toString() : "NULL");
                map.put("createTime", map.get("createTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("createTime")) : "NULL");
                map.put("updateTime", map.get("updateTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("updateTime")) : "NULL");
            }
        }

        return list;
    }

    /**
     * 格式化 用户表 数据
     * @param list
     * @return
     */
    private List<Map<String,Object>> formatDeptList(List<Map<String,Object>> list){
        if(!list.isEmpty()){
            for(int i = 0;i < list.size(); i ++){
                Map<String,Object> map = list.get(i);
                map.put("parentId",map.get("parentId") != null ? map.get("parentId").toString() : "NULL");
                map.put("deptId",map.get("deptId") != null ? map.get("deptId").toString() : "NULL");
                map.put("orderNum",map.get("orderNum") != null ? map.get("orderNum").toString() : "NULL");
                map.put("createTime", map.get("createTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("createTime")) : "NULL");
                map.put("updateTime", map.get("updateTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("updateTime")) : "NULL");
            }
        }

        return list;
    }

    /**
     * 格式化 机构表 数据
     * @param list
     * @return
     */
    private List<Map<String,Object>> formatOrgPackageList(List<Map<String,Object>> list){
        if(!list.isEmpty()){
            for(int i = 0;i < list.size(); i ++){
                Map<String,Object> map = list.get(i);
                map.put("sendRobot",map.get("sendRobot") != null ? map.get("sendRobot").toString() : "NULL");
                map.put("deptId",map.get("deptId") != null ? map.get("deptId").toString() : "NULL");
                map.put("autoStartRobotTask",map.get("autoStartRobotTask") != null ? map.get("autoStartRobotTask").toString() : "NULL");
                map.put("sendRadioQc",map.get("sendRadioQc") != null ? map.get("sendRadioQc").toString() : "NULL");
                map.put("sendRuleEngine",map.get("sendRuleEngine") != null ? map.get("sendRuleEngine").toString() : "NULL");
                map.put("autoAllocatTask",map.get("autoAllocatTask") != null ? map.get("autoAllocatTask").toString() : "NULL");
                map.put("allocatTaskStartegy",map.get("allocatTaskStartegy") != null ? map.get("allocatTaskStartegy").toString() : "NULL");
                map.put("startDate", map.get("startDate") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("startDate")) : "NULL");
                map.put("endDate", map.get("endDate") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("endDate")) : "NULL");
                map.put("createDate", map.get("createDate") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("createDate")) : "NULL");
                map.put("updateDate", map.get("updateDate") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("updateDate")) : "NULL");
            }
        }

        return list;
    }

    /**
     * 格式化 录音表 数据
     * @param list
     * @return
     */
    private List<Map<String,Object>> formatCallRecordList(List<Map<String,Object>> list){
        if(!list.isEmpty()){
            for(int i = 0;i < list.size(); i ++){
                Map<String,Object> map = list.get(i);
                map.put("id",map.get("id") != null ? map.get("id").toString() : "NULL");
                map.put("contactRelation",map.get("contactRelation") != null ? map.get("contactRelation").toString() : "NULL");
                map.put("createBy",map.get("createBy") != null ? map.get("createBy").toString() : "NULL");
                map.put("star",map.get("star") != null ? map.get("star").toString() : "NULL");
                map.put("type",map.get("type") != null ? map.get("type").toString() : "NULL");
                map.put("callStartTime", map.get("callStartTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("callStartTime")) : "NULL");
                map.put("callEndTime", map.get("callEndTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("callEndTime")) : "NULL");
                map.put("createTime", map.get("createTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("createTime")) : "NULL");
                map.put("findDate", map.get("findDate") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("findDate")) : "NULL");
            }
        }

        return list;
    }

    /**
     * 格式化 客户联系人信息表 数据
     * @param list
     * @return
     */
    private List<Map<String,Object>> formatCustContactList(List<Map<String,Object>> list){
        if(!list.isEmpty()){
            for(int i = 0;i < list.size(); i ++){
                Map<String,Object> map = list.get(i);
                map.put("id",map.get("id") != null ? map.get("id").toString() : "NULL");
                map.put("relation",map.get("relation") != null ? map.get("relation").toString() : "NULL");
                map.put("address",map.get("address") != null ? map.get("address").toString().replace("|","") : "NULL");
                map.put("origin",map.get("origin") != null ? map.get("origin").toString() : "NULL");
                map.put("validateStatus",map.get("validateStatus") != null ? map.get("validateStatus").toString() : "NULL");
                map.put("createTime", map.get("createTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("createTime")) : "NULL");
                map.put("modifyTime", map.get("modifyTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("modifyTime")) : "NULL");
            }
        }

        return list;
    }

    /**
     * 格式化 客户信息表 数据
     * @param list
     * @return
     */
    private List<Map<String,Object>> formatCustinfoList(List<Map<String,Object>> list){
        if(!list.isEmpty()){
            for(int i = 0;i < list.size(); i ++){
                Map<String,Object> map = list.get(i);
                map.put("id",map.get("id") != null ? map.get("id").toString() : "NULL");
                map.put("customSex",map.get("customSex") != null ? map.get("customSex").toString() : "NULL");
                map.put("incomeYear",map.get("incomeYear") != null ? map.get("incomeYear").toString() : "NULL");
                map.put("address",map.get("address") != null ? map.get("address").toString().replace("|","") : "NULL");
                map.put("marrageStatus",map.get("marrageStatus") != null ? map.get("marrageStatus").toString() : "NULL");
                map.put("hasChild",map.get("hasChild") != null ? map.get("hasChild").toString() : "NULL");
                map.put("hasHouse",map.get("hasHouse") != null ? map.get("hasHouse").toString() : "NULL");
                map.put("hasCar",map.get("hasCar") != null ? map.get("hasCar").toString() : "NULL");
                map.put("createBy",map.get("createBy") != null ? map.get("createBy").toString() : "NULL");
                map.put("modifyBy",map.get("modifyBy") != null ? map.get("modifyBy").toString() : "NULL");
                map.put("validateStatus",map.get("validateStatus") != null ? map.get("validateStatus").toString() : "NULL");
                map.put("birthday", map.get("birthday") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("birthday")) : "NULL");
                map.put("createTime", map.get("createTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("createTime")) : "NULL");
                map.put("modifyTime", map.get("modifyTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("modifyTime")) : "NULL");
            }
        }

        return list;
    }

    /**
     * 格式化 案件表 数据
     * @param list
     * @return
     */
    private List<Map<String,Object>> formatDuncaseList(List<Map<String,Object>> list){
        if(!list.isEmpty()){
            for(int i = 0;i < list.size(); i ++){
                Map<String,Object> map = list.get(i);
                map.put("id",map.get("id") != null ? map.get("id").toString() : "NULL");
                map.put("monthRepayDay",map.get("monthRepayDay") != null ? map.get("monthRepayDay").toString() : "NULL");
                map.put("overdueDays",map.get("overdueDays") != null ? map.get("overdueDays").toString() : "NULL");
                map.put("maxOverdueDay",map.get("maxOverdueDay") != null ? map.get("maxOverdueDay").toString() : "NULL");
                map.put("borrowLine",map.get("borrowLine") != null ? map.get("borrowLine").toString() : "NULL");
                map.put("totalInterestRmb",map.get("totalInterestRmb") != null ? map.get("totalInterestRmb").toString() : "NULL");
                map.put("totalPrincipalRmb",map.get("totalPrincipalRmb") != null ? map.get("totalPrincipalRmb").toString() : "NULL");
                map.put("totalDefaultInterestRmb",map.get("totalDefaultInterestRmb") != null ? map.get("totalDefaultInterestRmb").toString() : "NULL");
                map.put("totalExpensesPayableRmb",map.get("totalExpensesPayableRmb") != null ? map.get("totalExpensesPayableRmb").toString() : "NULL");
                map.put("lowestPaymentRmb",map.get("lowestPaymentRmb") != null ? map.get("lowestPaymentRmb").toString() : "NULL");
                map.put("totalDebtAmountRmb",map.get("totalDebtAmountRmb") != null ? map.get("totalDebtAmountRmb").toString() : "NULL");
                map.put("appointCaseBalance",map.get("appointCaseBalance") != null ? map.get("appointCaseBalance").toString() : "NULL");
                map.put("createBy",map.get("createBy") != null ? map.get("createBy").toString() : "NULL");
                map.put("modifyBy",map.get("modifyBy") != null ? map.get("modifyBy").toString() : "NULL");
                map.put("closeCaseYhje",map.get("closeCaseYhje") != null ? map.get("closeCaseYhje").toString() : "NULL");
                map.put("overdueFine",map.get("overdueFine") != null ? map.get("overdueFine").toString() : "NULL");
                map.put("totalOverdueDay",map.get("totalOverdueDay") != null ? map.get("totalOverdueDay").toString() : "NULL");
                map.put("overdueFrequency",map.get("overdueFrequency") != null ? map.get("overdueFrequency").toString() : "NULL");
                map.put("ljyhje",map.get("ljyhje") != null ? map.get("ljyhje").toString() : "NULL");
                map.put("firstOverdueTime", map.get("firstOverdueTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("firstOverdueTime")) : "NULL");
                map.put("repayDate", map.get("repayDate") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("repayDate")) : "NULL");
                map.put("createTime", map.get("createTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("createTime")) : "NULL");
                map.put("modifyTime", map.get("modifyTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("modifyTime")) : "NULL");
                map.put("enterCollDate", map.get("enterCollDate") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("enterCollDate")) : "NULL");
                map.put("backCaseDate", map.get("backCaseDate") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("backCaseDate")) : "NULL");
            }
        }

        return list;
    }

    /**
     * 格式化 任务表 数据
     * @param list
     * @return
     */
    private List<Map<String,Object>> formatTaskList(List<Map<String,Object>> list){
        if(!list.isEmpty()){
            for(int i = 0;i < list.size(); i ++){
                Map<String,Object> map = list.get(i);
                map.put("id",map.get("id") != null ? map.get("id").toString() : "NULL");
                map.put("arrearsTotal",map.get("arrearsTotal") != null ? map.get("arrearsTotal").toString() : "NULL");
                map.put("taskStatus",map.get("taskStatus") != null ? map.get("taskStatus").toString() : "NULL");
                map.put("overdueDays",map.get("overdueDays") != null ? map.get("overdueDays").toString() : "NULL");
                map.put("ownerId",map.get("ownerId") != null ? map.get("ownerId").toString() : "NULL");
                map.put("oldOwnerId",map.get("oldOwnerId") != null ? map.get("oldOwnerId").toString() : "NULL");
                map.put("taskType",map.get("taskType") != null ? map.get("taskType").toString() : "NULL");
                map.put("allotType",map.get("allotType") != null ? map.get("allotType").toString() : "NULL");
                map.put("robotTaskId",map.get("robotTaskId") != null ? map.get("robotTaskId").toString() : "NULL");
                map.put("robotCallStrategyId",map.get("robotCallStrategyId") != null ? map.get("robotCallStrategyId").toString() : "NULL");
                map.put("closeCaseYhje",map.get("closeCaseYhje") != null ? map.get("closeCaseYhje").toString() : "NULL");
                map.put("dqyhje",map.get("dqyhje") != null ? map.get("dqyhje").toString() : "NULL");
                map.put("createBy",map.get("createBy") != null ? map.get("createBy").toString() : "NULL");
                map.put("modifyBy",map.get("modifyBy") != null ? map.get("modifyBy").toString() : "NULL");
                map.put("closeDate", map.get("closeDate") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("closeDate")) : "NULL");
                map.put("enterCollDate", map.get("enterCollDate") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("enterCollDate")) : "NULL");
                map.put("createTime", map.get("createTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("createTime")) : "NULL");
                map.put("modifyTime", map.get("modifyTime") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("modifyTime")) : "NULL");
                map.put("recentlyAllotDate", map.get("recentlyAllotDate") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("recentlyAllotDate")) : "NULL");
                map.put("recentlyFollowUpDate", map.get("recentlyFollowUpDate") != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,(Date)map.get("recentlyFollowUpDate")) : "NULL");
            }
        }

        return list;
    }
}
