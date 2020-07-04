package com.ruoyi;

import com.ruoyi.callConfig.domain.TLcCallStrategyConfig;
import com.ruoyi.callConfig.service.ITLcCallStrategyConfigService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.service.ITLcCustContactService;
import com.ruoyi.enums.ContactRelaEnum;
import com.ruoyi.orgSpeechConf.domain.TLcOrgSpeechcraftConf;
import com.ruoyi.report.domain.TLcReportRecovery;
import com.ruoyi.report.service.ITLcReportRecoveryService;
import com.ruoyi.robot.domain.TLcRobotCallRecordMeteData;
import com.ruoyi.robot.utils.RobotMethodUtil;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.mapper.TLcTaskMapper;
import com.ruoyi.task.service.ITLcCallRecordService;
import com.ruoyi.task.service.ITLcTaskService;
import com.ruoyi.utils.DownLoadFromHttpsUtil;
import com.ruoyi.utils.LocalCacheKeyUtils;
import com.ruoyi.utils.LocalCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskTest {

    @Autowired
    private TLcTaskMapper tLcTaskMapper;
    @Autowired
    private ITLcCallStrategyConfigService callStrategyConfigService;
    @Autowired
    private ITLcCustContactService custContactService;
    @Autowired
    private ITLcTaskService tLcTaskService;
    @Autowired
    private LocalCacheUtil localCacheUtil;
    @Autowired
    private RobotMethodUtil robotMethodUtil;
    @Autowired
    private ITLcCallRecordService callRecordService;
    @Autowired
    private ITLcReportRecoveryService reportRecoveryService;

    /**
     * 测试任务分配枚举
     */
    @Test
    public void testAllocatTask() {
        Set<Long> deptSet = new HashSet<>();
        deptSet.add(100L);
//        getChildDept(deptSet, 100L);
//        System.out.println("size is " + deptSet.size());
//        deptSet.stream().forEach(id -> System.out.println("部门id" + id));
    }

    @Test
    public void getChildDept() {
        List<SysDept> sysDeptList = this.tLcTaskMapper.selectDeptByParentId(0L);
        List<Long> ceshiliu = sysDeptList.stream().map(dept -> {
            dept.setDeptName("ceshiliu");
            return dept.getDeptId();
        }).collect(Collectors.toList());
        sysDeptList.stream().forEach(a -> System.out.println(a.getDeptName()));
//        for (SysDept sysDept : sysDeptList) {
//            deptSet.add(sysDept.getDeptId());
//            getChildDept(deptSet, sysDept.getDeptId());
//        }
    }

    @Test
    public void testLocalCacheUtil() {
        List<TLcRobotCallRecordMeteData> robotTaskList = localCacheUtil.getPhoneToStart(LocalCacheKeyUtils.taskWaitStartList());
        TLcRobotCallRecordMeteData tLcRobotCallRecordMeteData = new TLcRobotCallRecordMeteData();
        tLcRobotCallRecordMeteData.setCustomerTelephone("12312312300");
        robotTaskList.add(tLcRobotCallRecordMeteData);
        localCacheUtil.addPhoneToStart(LocalCacheKeyUtils.taskWaitStartList(), robotTaskList);
        TLcRobotCallRecordMeteData tLcRobotCallRecordMeteData2 = new TLcRobotCallRecordMeteData();
        tLcRobotCallRecordMeteData2.setCustomerTelephone("12312312300");
        robotTaskList.add(tLcRobotCallRecordMeteData2);
        localCacheUtil.addPhoneToStart(LocalCacheKeyUtils.taskWaitStartList(), robotTaskList);
        List<TLcRobotCallRecordMeteData> robotTaskList2 = localCacheUtil.getPhoneToStart(LocalCacheKeyUtils.taskWaitStartList());
        System.out.println(robotTaskList2);
        List<TLcRobotCallRecordMeteData> robotTaskList3 = localCacheUtil.getPhoneToStart(LocalCacheKeyUtils.taskWaitStartList());
        System.out.println(robotTaskList3);
    }

    @Test
    public void testSchedule() {
        List<TLcRobotCallRecordMeteData> robotTaskList2 = localCacheUtil.getPhoneToStart(LocalCacheKeyUtils.taskWaitStartList());
        TLcRobotCallRecordMeteData tLcRobotCallRecordMeteData = new TLcRobotCallRecordMeteData();
        tLcRobotCallRecordMeteData.setCustomerTelephone("1234563");
        robotTaskList2.add(tLcRobotCallRecordMeteData);
        localCacheUtil.addPhoneToStart(LocalCacheKeyUtils.taskWaitStartList(), robotTaskList2);
        List<TLcRobotCallRecordMeteData> robotTaskList = localCacheUtil.getPhoneToStart(LocalCacheKeyUtils.taskWaitStartList());
        robotTaskList.stream()
                .forEach(robotTask -> {
                    // 当天的呼叫次数清0
                    localCacheUtil.setPhoneCurDayCallNums(LocalCacheKeyUtils.phoneCurDayCallNumsKey(robotTask.getCustomerTelephone()), 0);
                    // 连续呼叫天数+1
                    Integer continueCallDays = localCacheUtil.getPhoneContinueCallDays(LocalCacheKeyUtils.phoneContinueCallDaysKey(robotTask.getCustomerTelephone()));
                    localCacheUtil.setPhoneContinueCallDays(LocalCacheKeyUtils.phoneContinueCallDaysKey(robotTask.getCustomerTelephone()), ++continueCallDays);
//                    robotMethodUtil.startTask(robotTask.getCallJobId());
                });
        Integer phoneCurDayCallNums = localCacheUtil.getPhoneCurDayCallNums(LocalCacheKeyUtils.phoneCurDayCallNumsKey("1234563"));
        System.out.println(phoneCurDayCallNums);
        Integer phoneContinueCallDays = localCacheUtil.getPhoneContinueCallDays(LocalCacheKeyUtils.phoneContinueCallDaysKey("1234563"));
        System.out.println(phoneContinueCallDays);
    }

    @Test
    public void testCreateRobotTask() {
        TLcTask tLcTask = this.tLcTaskMapper.selectTLcTaskById(Long.valueOf(60));
        TLcCallStrategyConfig callStrategyConfig = this.callStrategyConfigService.selectTLcCallStrategyConfigById(tLcTask.getRobotCallStrategyId());
        List<TLcCustContact> custContactList = this.custContactService.selectTLcCustContactList(TLcCustContact.builder().certificateNo(tLcTask.getCertificateNo()).relation(ContactRelaEnum.SELE.getCode()).build());
        Integer robotTaskId = this.robotMethodUtil.createTask(tLcTask, callStrategyConfig, custContactList, 1);
        // 修改任务对应的机器人任务id
        tLcTask.setRobotTaskId(robotTaskId);
        this.tLcTaskService.updateTLcTask(tLcTask);
        System.out.println("创建成功");
    }

    @Test
    public void report() {
        List<TLcReportRecovery> reportRecoveryList = this.reportRecoveryService.selectTLcReportRecoveryList(new TLcReportRecovery());
        Map<String, List<TLcReportRecovery>> allOrgMap = reportRecoveryList.stream().collect(Collectors.groupingBy(TLcReportRecovery::getOrgId));
        for (Map.Entry<String, List<TLcReportRecovery>> map : allOrgMap.entrySet()) {
            String orgId = map.getKey();
            List<TLcReportRecovery> resultList = map.getValue();
            Long totalCollingCaseNum = resultList.stream().mapToLong(result -> result.getCollingCaseNum() == null ? 0 : result.getCollingCaseNum()).sum();
            Long totalConfirmedRecycleCaseNum = resultList.stream().mapToLong(result -> result.getConfirmedRecycleCaseNum() == null ? 0 : result.getConfirmedRecycleCaseNum()).sum();
            Long totalUnconfirmedRecycleCaseNum = resultList.stream().mapToLong(result -> result.getUnconfirmedRecycleCaseNum() == null ? 0 : result.getUnconfirmedRecycleCaseNum()).sum();
            String totalCaseRecovery = "0";
            if (totalCollingCaseNum != null) {
                totalCaseRecovery = String.valueOf(totalConfirmedRecycleCaseNum / totalCollingCaseNum);
            }
            BigDecimal totalCollingCaseMoney = resultList.stream().map(result -> result.getCollingCaseMoney() == null ? new BigDecimal(0.00) : result.getCollingCaseMoney()).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal totalConfirmedRecycleCaseMoney = resultList.stream().map(result -> result.getConfirmedRecycleCaseMoney() == null ? new BigDecimal(0.00) : result.getConfirmedRecycleCaseMoney()).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal totalUnconfirmedRecycleCaseMoney = resultList.stream().map(result -> result.getUnconfirmedRecycleCaseMoney() == null ? new BigDecimal(0.00) : result.getUnconfirmedRecycleCaseMoney()).reduce(BigDecimal.ZERO, BigDecimal::add);
            String totalMoneyRecovery = "0";
            if (totalCollingCaseMoney != null) {
                totalMoneyRecovery = String.valueOf(totalConfirmedRecycleCaseMoney.divide(totalCollingCaseMoney, 1));
            }
            TLcReportRecovery tLcReportRecovery = TLcReportRecovery.builder()
                    .collingCaseNum(totalCollingCaseNum)
                    .confirmedRecycleCaseNum(totalConfirmedRecycleCaseNum)
                    .unconfirmedRecycleCaseNum(totalUnconfirmedRecycleCaseNum)
                    .caseRecovery(totalCaseRecovery)
                    .collingCaseMoney(totalCollingCaseMoney)
                    .confirmedRecycleCaseMoney(totalConfirmedRecycleCaseMoney)
                    .unconfirmedRecycleCaseMoney(totalUnconfirmedRecycleCaseMoney)
                    .moneyRecovery(totalMoneyRecovery)
                    .orgId(orgId)
                    .reportDate(reportRecoveryList.get(0).getReportDate())
                    .build();
            reportRecoveryList.add(tLcReportRecovery);
        }
    }

    @Test
    public void batchUpdate() {
        List<TLcTask> taskList = this.tLcTaskService.selectTLcTaskList(new TLcTask());
        System.out.println(taskList.size() + "条");
        long start = System.currentTimeMillis();
        System.out.println("start " + start);
        this.tLcTaskMapper.batchUpdateTask(taskList);
        long end = System.currentTimeMillis();
        System.out.println("end " + end);
        System.out.println("耗时 " + (end - start));
        System.out.println("=====================");
        long start1 = System.currentTimeMillis();
        System.out.println("start " + start1);
        for (TLcTask tLcTask : taskList) {
            this.tLcTaskMapper.updateTLcTask(tLcTask);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("end " + end2);
        System.out.println("耗时 " + (end2 - start1));
    }

    @Test
    public void testDownLoadCallFile() {
        // 获取前一天的0点和24点
        SimpleDateFormat dateFmt = new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_MM_SS);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date dBefore = calendar.getTime();
        String startDate = dateFmt.format(dBefore);
        /*startDate = startDate.substring(0, 10) + " 00:00:00";
        String endDate = startDate.substring(0, 10) + " 23:59:59";*/
        startDate = "2020-06-08 00:00:00";
        String endDate = "2020-06-08 23:59:59";

        TLcCallRecord tLcCallRecord = new TLcCallRecord();
        tLcCallRecord.setStartCreateTime(DateUtils.parseDate(startDate));
        tLcCallRecord.setEndCreateTime(DateUtils.parseDate(endDate));

        List<TLcCallRecord> list = this.callRecordService.findTLcCallRecordListByDate(tLcCallRecord);
        if(list != null && list.size() > 0){
            for(int i = 0; i < list.size(); i ++){
                TLcCallRecord tmpTLcCallRecord = list.get(i);
                if(StringUtils.isNotEmpty(tmpTLcCallRecord.getCallRadioLocation())){//存在下载地址
                    String[] str = tmpTLcCallRecord.getCallRadioLocation().split("/");
                    String fileName = "";
                    if(str.length > 1){
                        fileName = str[str.length - 1];
                        if(tmpTLcCallRecord.getCallRadioLocation().startsWith("https")){//https类型的URL下载
                            try{
                                String filePath = "E:\\temp\\" + tmpTLcCallRecord.getOrgName() + File.separator + tmpTLcCallRecord.getPlatform() + File.separator + startDate.substring(0, 10);
                                log.info("下载语音文件保存目录为，filePath={}",filePath);
                                DownLoadFromHttpsUtil.downloadFileForHttps(tmpTLcCallRecord.getCallRadioLocation(),filePath,fileName);
                            }catch (Exception e){
                                log.error("下载语音文件异常={}",e);
                            }
                        }
                    }else{
                        log.error("下载语音文件URL异常，语音记录ID={},URL={}",tmpTLcCallRecord.getId(),tmpTLcCallRecord.getCallRadioLocation());
                        continue;
                    }
                }
            }
        }
    }

}
