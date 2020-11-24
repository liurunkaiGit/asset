package com.ruoyi;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.*;
import com.ruoyi.callConfig.domain.TLcCallStrategyConfig;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.enums.StopCallConditionEnum;
import com.ruoyi.robot.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import sun.audio.AudioPlayer;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/13 11:50
 */
@Slf4j
public class Test {

    @org.junit.Test
    public void test01() throws Exception {
        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\liurunkai\\Downloads\\57_early_media.wav"));
        AudioPlayer.player.start(inputStream);
    }

    @org.junit.Test
    public void test02() throws Exception {
//        DecimalFormat df = new DecimalFormat("0.0%");
//        System.out.println(df.format(0.0943));
        DecimalFormat df1 = new DecimalFormat("#.00");
        System.out.println(df1.format(0.0213));
        BigDecimal bd = new BigDecimal(0.0213);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        System.out.println(bd);
    }

    /**
     * 判断是否达到当天停止呼叫任务条件
     *
     * @return
     */
    @org.junit.Test
    public void isCurDayStopConditon() {
        TLcCallStrategyConfig callStrategyConfig = new TLcCallStrategyConfig();
        callStrategyConfig.setStopCallCurDayCondition("0,6");
        TLcRobotCallRecordMeteData sceneInstance = new TLcRobotCallRecordMeteData();
        sceneInstance.setFinishStatus(01);
        // 获取当天任务停止呼叫条件
        String[] stopCallCurDayConditions = callStrategyConfig.getStopCallCurDayCondition().split(",");
        List<String> stopCallCurDayConditionList = new ArrayList<>(stopCallCurDayConditions.length);
        Collections.addAll(stopCallCurDayConditionList, stopCallCurDayConditions);
        boolean flag = stopCallCurDayConditionList.contains(String.valueOf(sceneInstance.getFinishStatus())) ? true : false;
        log.info("flag is {}", flag);
    }

    /**
     * 判断是否达到停止呼叫任务的条件
     *
     * @return
     */
    @org.junit.Test
    public void isStopCllContion() {
        TLcCallStrategyConfig callStrategyConfig = new TLcCallStrategyConfig();
        callStrategyConfig.setStopCallCondition("2,3");
        // 获取任务停止呼叫条件
        String[] stopCallConditions = callStrategyConfig.getStopCallCondition().split(",");
        List<String> stopCallConditionList = new ArrayList<>(stopCallConditions.length);
        Collections.addAll(stopCallConditionList, stopCallConditions);
        ArrayList<TLcRobotCallAnalyseResult> taskResultList = new ArrayList<>();
        TLcRobotCallAnalyseResult taskResult = new TLcRobotCallAnalyseResult();
        taskResult.setResultValueAlias("A");
        taskResultList.add(taskResult);
        TLcRobotCallAnalyseResult taskResult2 = new TLcRobotCallAnalyseResult();
        taskResult2.setResultValueAlias("A");
        taskResultList.add(taskResult2);
        for (TLcRobotCallAnalyseResult callAnalyseResult : taskResultList) {
            if (stopCallConditionList.contains(String.valueOf(StopCallConditionEnum.getCodeByMessage(callAnalyseResult.getResultValueAlias())))) {
                log.info("true");
                return;
            }
        }
        log.info("false");
    }

    @org.junit.Test
    public void testLambda() {
        String[] str = {"a", "b", "c"};
        List<String> strings = Arrays.asList(str);
        List<String> list = strings.stream().map(s -> {
            if (s.equals("b")) {
                return null;
            }
            return s;
        }).collect(Collectors.toList());
        log.info("list size is {}", list.size());
        list.stream().filter(s -> StringUtils.isNoneBlank(s))
                .forEach(s -> log.info("s is {}", s));
    }

    @org.junit.Test
    public void testDate() throws ParseException {
//        DateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化一下时间
//        Date dNow = new Date(); //当前时间
//        Date dBefore = new Date();
//        Calendar calendar = Calendar.getInstance(); //得到日历
//        calendar.setTime(dNow);//把当前时间赋给日历
//        calendar.add(Calendar.DAY_OF_MONTH, -1); //设置为前一天
//        dBefore = calendar.getTime(); //得到前一天的时间
//        String defaultStartDate = dateFmt.format(dBefore); //格式化前一天
//        defaultStartDate = defaultStartDate.substring(0, 10) + " 00:00:00";
//        String defaultEndDate = defaultStartDate.substring(0, 10) + " 23:59:59";
//        System.out.println(defaultStartDate);
//        System.out.println(defaultEndDate);
//        System.out.println("今天开始时间：" + getStartOfDay(new Date()));
//        System.out.println("今天结束时间：" + getEndOfDay(new Date()));
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        System.out.println(sdf.format(getStartOfDay(new Date())));
//        System.out.println(sdf.format(getEndOfDay(new Date())));
//        Date firstDay = DateUtils.getFirstDay();
//        System.out.println("当月第一天" + sdf.format(firstDay));
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_MM_SS);
        String time = "2020-07-15 17:35:03.081" ;
        Date date = DateUtils.stringConvertDate(time, DateUtils.YYYY_MM_DD_HH_MM_SS);
//        Date date = sdf.parse(time);
        System.out.println(date);
    }

    @org.junit.Test
    public void testSubIndex() {
        ArrayList<Integer> remoteList = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            remoteList.add(i);
        }
//        List<Integer> integers = remoteList.subList(10, 1);
//        integers.forEach(data -> System.out.println(data));
        int total = remoteList.size();
        int index = 5;
        int targetSize = 5;
        int subIndex = 0;
        if (total > index) {
            int count = total / index;
            int over = total % index;
            if (over > 0) {
                for (int i = 0; i < count; i++) {
                    List lt = remoteList.subList(subIndex, index);
                    subIndex = subIndex + index;
                    index = index + index;
                    System.out.println("i=" + i);
                    lt.forEach(data -> System.out.println(data));
                }
                subIndex = count * targetSize;
                List lt = remoteList.subList(subIndex, count * targetSize + over);
                lt.forEach(data -> System.out.println(data));
            } else {
                for (int i = 0; i < count; i++) {
                    List lt = remoteList.subList(subIndex, index);
                    subIndex = subIndex + index;
                    index = index + index;
                    System.out.println("i=" + i);
                    lt.forEach(data -> System.out.println(data));
                }
            }
        } else {
            remoteList.forEach(data -> System.out.println(data));
        }
    }

    public Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    @org.junit.Test
    public void testEqual() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String s = sdf.format(new Date());
        String s = "2020-05-08" ;
        Date date = sdf.parse(s);
        System.out.println(date);
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        java.util.Date date2 = Date.from(instant);
        System.out.println(date2);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar calendar = Calendar.getInstance();
//        Date date = new Date(); // date 包括时分秒
//        String s = sdf.format(date); // 把带时分秒的 date 转为 yyyy-MM-dd 格式的字符串
//        try {
//            Date date2 = sdf.parse(s); // 把上面的字符串解析为日期类型
//            System.out.println(date2);
//            String format = sdf.format(date2);
//            System.out.println(sdf.format(date2));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Date date = DateUtils.dateFormat(DateUtils.YYYY_MM_DD, new Date());
////        String s1 = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,date);
////        Date date3 = DateUtils.parseDate(s1);
////        System.out.println(date3);
////        System.out.println(s1);
//        System.out.println(date);
//        String s = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
//        Date date1 = DateUtils.parseDate(s);
//        System.out.println(date1);
//        String date2 = DateUtils.getDate();
//        System.out.println(date2);
//        System.out.println("===============");
//        LocalDate now = LocalDate.now();
//        System.out.println(now);
    }

    @org.junit.Test
    public void testSub() {
        ArrayList<Integer> remoteList = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            remoteList.add(i);
        }
        int total = remoteList.size();
        int index = 500;
        int pagesize = total / index;
        if (total <= index) {
//                this.curAssetsPackageMapper.batchAddTemp(paramList);
            remoteList.forEach(data -> System.out.println(data));
//            ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, remoteList, Response.class);
//            if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
//                throw new ImportDataExcepion("调用资产导入接口调用失败:" + responseResponseEntity.getBody().getMessage());
//            }
        } else {
            for (int i = 0; i < pagesize; i++) {
                List lt = remoteList.subList(i * index, (i + 1) * index);
//                    this.curAssetsPackageMapper.batchAddTemp(lt);
//                ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, lt, Response.class);
//                if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
//                    throw new ImportDataExcepion("调用资产导入接口调用失败:" + responseResponseEntity.getBody().getMessage());
//                }
            }
            if (total % index != 0) {
                List lt = remoteList.subList(index * pagesize, total);
//                    this.curAssetsPackageMapper.batchAddTemp(lt);
//                ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, lt, Response.class);
//                if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
//                    throw new ImportDataExcepion("调用资产导入接口调用失败:" + responseResponseEntity.getBody().getMessage());
//                }
            }
        }
    }

    @org.junit.Test
    public void testTwoListSameValue() {
        String[] str1 = {"123", "abc", "bbb", "ccc"};
        String[] str2 = {"123", "abc", "bbb2", "ccc"};
        List<String> list1 = Arrays.asList(str1);
        List<String> list2 = Arrays.asList(str2);
        List<String> list = list1.stream()
                .map(s1 -> list2.stream()
                        .filter(s2 -> Objects.equals(s2, s1))
                        .findFirst()
                        .orElse(null))
                .filter(Objects::isNull)
                .collect(Collectors.toList());
        list.stream().forEach(s -> System.out.println(s));
        list.stream().forEach(s -> {
            if (StringUtils.isNoneBlank(s)) {
                System.out.println(s);
            }
        });
    }

    @org.junit.Test
    public void testJson() {
        String jsonString = "{\"code\":200,\"data\":{\"jobFinished\":false,\"phoneLog\":{\"luyinOssUrl\":\"https://pre-crm.indata.cc/ftp/RobotPhoneCommunicate/29905_early_media.wav\",\"phoneLogs\":[]},\"voipCallerAccount\":{\"callerAccountLevel\":1,\"available\":0,\"callerAccountId\":1,\"registerType\":0,\"deviceId\":\"123\",\"gwCodec\":\"PCMU,PCMA\",\"concurrencyQuota\":1,\"provider\":0,\"industryTwoId\":0,\"defaultCallPrefix\":\"960\",\"phoneType\":2,\"industryOneId\":0,\"voipGatewayId\":-1,\"gwType\":1,\"phone\":\"智能外呼线路\",\"sceneType\":1,\"account\":\"1234567\"},\"sceneInstance\":{\"callUserId\":79,\"readStatus\":0,\"chatRound\":0,\"gmtModified\":\"2020-05-11 09:42:16\",\"callJobId\":309,\"secondaryCallTime\":\"1970-01-01 09:00:00\",\"industry\":\"29905_3_1\",\"userLuyinOssUrl\":\"https://pre-crm.indata.cc/ftp/RobotPhoneCommunicate/29905_early_media.wav\",\"trackResult\":\"D级(需筛选)\",\"callType\":1,\"customerStatus\":1,\"duration\":0,\"callbacked\":1,\"sceneDefId\":101,\"customerId\":23,\"userProperties\":\"{}\",\"startTime\":\"2020-05-11 09:41:36\",\"secondaryCallTimes\":0,\"jobName\":\"20200511094047\",\"callInstanceId\":29905,\"cost\":0,\"handlePerson\":\"测试话术\",\"customerTelephone\":\"13453000630\",\"corpName\":\"{\\\"calledTimes\\\":1,\\\"callerId\\\":1,\\\"city\\\":\\\"忻州\\\",\\\"host\\\":\\\"iZbp1f06x9fl4mihthdyhbZ\\\",\\\"ip\\\":\\\"172.16.189.10\\\",\\\"province\\\":\\\"山西\\\",\\\"useAsr\\\":\\\"pay\\\"}\",\"propertiesMap\":{\"客户名称\":\"刘林\",\"联系方式\":\"13453000630\"},\"gmtCreate\":\"2020-05-11 09:40:47\",\"customerName\":\"刘林\",\"hangUp\":1,\"robotDefId\":1393,\"companyId\":3,\"finishStatus\":6,\"luyinOssUrl\":\"https://pre-crm.indata.cc/ftp/RobotPhoneCommunicate/29905_early_media.wav\",\"sceneRecordId\":200,\"callIndex\":0,\"endTime\":\"2020-05-11 09:42:10\",\"callerPhone\":\"智能外呼线路\",\"properties\":\"{}\",\"status\":2},\"taskResult\":[{\"sceneInstanceId\":29905,\"resultValue\":\"D级(需筛选)\",\"artificialResultValue\":\"D级(需筛选)\",\"companyId\":3,\"gmtModified\":\"2020-05-11 09:42:15\",\"sceneInstanceResultId\":183057,\"extra\":\"{\\\"hitUserLevelConfigId\\\":12}\",\"resultDesc\":\"通话状态 = 占线、未接、拒接、关机\",\"gmtCreate\":\"2020-05-11 09:42:15\",\"analyzeType\":\"DYNAMIC_ANALYZE_USER_LEVEL\",\"resultName\":\"客户意向等级\"}]},\"resultMsg\":\"获取成功\"}" ;
        PhoneLogInfo phoneLogInfo = JSONObject.parseObject(jsonString, PhoneLogInfo.class);
        PhoneLogInfo.PhoneLog.TLcRobotCallRecordMeteData sceneInstance = phoneLogInfo.getData().getSceneInstance();
        List<PhoneLogInfo.PhoneLog.TaskResultBean> taskResultList = phoneLogInfo.getData().getTaskResult();
        PhoneLogInfo.PhoneLog.PhoneLogBean phoneLog = phoneLogInfo.getData().getPhoneLog();
        List<PhoneLogInfo.PhoneLog.PhoneLogBean.PhoneLogsBean> phoneLogList = phoneLog.getPhoneLogs();
        CallCallback callCallback = new CallCallback();
        CallCallback.CallCallbackData callCallbackData = callCallback.new CallCallbackData();
        CallCallback.CallCallbackData.CallData callData = callCallbackData.new CallData();
        CallCallback.CallCallbackData.CallData.PhoneLog callbackPhoneLog = callData.new PhoneLog();
        TLcRobotCallRecordMeteData tLcRobotCallRecordMeteData = new TLcRobotCallRecordMeteData();
        tLcRobotCallRecordMeteData.setDataType("INBOUND_CALL_INSTANCE_RESULT")
                .setInboundInstanceId(sceneInstance.getCallInstanceId())
                .setCompanyId(sceneInstance.getCompanyId())
                .setCallJobId(sceneInstance.getCallJobId())
                .setCustomerId(sceneInstance.getCustomerId())
                .setCustomerTelephone(sceneInstance.getCustomerTelephone())
                .setCustomerName(sceneInstance.getCustomerName())
                .setStatus(sceneInstance.getStatus())
                .setFinishStatus(sceneInstance.getFinishStatus())
                .setDuration(sceneInstance.getDuration())
                .setChatRound(sceneInstance.getChatRound())
                .setStartTime(StringUtils.isNoneBlank(sceneInstance.getStartTime()) ? DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, sceneInstance.getStartTime()) : null)
                .setEndTime(StringUtils.isNoneBlank(sceneInstance.getEndTime()) ? DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, sceneInstance.getEndTime()) : null)
                .setCallerPhone(sceneInstance.getCallerPhone())
                .setLuyinOssUrl(sceneInstance.getLuyinOssUrl())
                .setUserLuyinOssUrl(sceneInstance.getUserLuyinOssUrl())
                .setProperties(sceneInstance.getProperties())
                .setReadStatus(sceneInstance.getReadStatus())
                .setRobotDefId(sceneInstance.getRobotDefId())
                .setSceneRecordId(sceneInstance.getSceneRecordId())
                .setHangUp(sceneInstance.getHangUp());
        callData.setSceneInstance(tLcRobotCallRecordMeteData);
//        ArrayList<TLcRobotCallAnalyseResult> analyseResultList = new ArrayList<>();
//        TLcRobotCallAnalyseResult callAnalyseResult = new TLcRobotCallAnalyseResult();
        List<TLcRobotCallAnalyseResult> analyseResultList = taskResultList.stream().map(taskResultBean -> {
            TLcRobotCallAnalyseResult callAnalyseResult = new TLcRobotCallAnalyseResult();
            callAnalyseResult.setSceneInstanceResultId(taskResultBean.getSceneInstanceResultId())
                    .setCompanyId(taskResultBean.getCompanyId())
                    .setCallJobId(sceneInstance.getCallJobId())
                    .setInboundInstanceId(sceneInstance.getCallInstanceId())
                    .setSceneInstanceId(taskResultBean.getSceneInstanceId())
                    .setResultName(taskResultBean.getResultName())
                    .setResultValue(taskResultBean.getResultValue())
                    .setResultDesc(String.valueOf(taskResultBean.getResultDesc()))
                    .setDataType("INBOUND_CALL_INSTANCE_RESULT");
            return callAnalyseResult;
        }).collect(Collectors.toList());
        callData.setTaskResult(analyseResultList);
        List<TLcRobotCallDetail> callDetailList = phoneLogList.stream()
                .map(phoneLogsBean -> {
                    TLcRobotCallDetail tLcRobotCallDetail = new TLcRobotCallDetail();
                    tLcRobotCallDetail.setSceneInstanceId(phoneLogsBean.getSceneInstanceId())
                            .setSceneInstanceLogId(phoneLogsBean.getSceneInstanceLogId())
                            .setInboundInstanceId(sceneInstance.getCallInstanceId())
                            .setCompanyId(sceneInstance.getCompanyId())
                            .setRobotDefId(sceneInstance.getRobotDefId())
                            .setSpeaker(phoneLogsBean.getSpeaker())
                            .setContent(phoneLogsBean.getContent())
                            .setUserMean(String.valueOf(phoneLogsBean.getUserMean()))
                            .setUserMeanDetail(phoneLogsBean.getUserMeanDetail())
                            .setAiUnknownValue(phoneLogsBean.getAiUnknown())
                            .setStartTime(StringUtils.isNoneBlank(phoneLogsBean.getStartTime()) ? DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, phoneLogsBean.getStartTime()) : null)
                            .setEndTime(StringUtils.isNoneBlank(phoneLogsBean.getEndTime()) ? DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, phoneLogsBean.getEndTime()) : null)
                            .setLuyinOssUrl(sceneInstance.getLuyinOssUrl());
                    return tLcRobotCallDetail;
                }).collect(Collectors.toList());
        callbackPhoneLog.setPhoneLogs(callDetailList);
        callbackPhoneLog.setLuyinOssUrl(sceneInstance.getLuyinOssUrl());
        System.out.println("=====================");
    }

    @org.junit.Test
    public void testListToJson() {
        ArrayList<String> integers = new ArrayList<>();
        integers.add("姓名");
        integers.add("性别");
        System.out.println(integers);
        System.out.println(JSON.toJSONString(integers));
        List<String> list = JSONUtil.toList(JSONUtil.parseArray(JSON.toJSONString(integers)), String.class);
        list.forEach(s -> System.out.println(s));
    }

    @org.junit.Test
    public void testCopyOnWriteArrayList() {
        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            userList.add(new User(i));
        }
        userList = new CopyOnWriteArrayList(userList.toArray());
        userList.stream().forEach(user -> log.info("user is:{}", user));
    }

    /**
     * 新增金额&数量均分规则
     */
    @org.junit.Test
    public void testMoneyNumAllocat() {
        CopyOnWriteArrayList<User> userList = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 3; i++) {
            userList.add(new User(i));
        }
//        userList.stream().forEach(user -> log.info("user is:{}",user));
        CopyOnWriteArrayList<Task> taskList = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 100; i++) {
            taskList.add(new Task(i, new BigDecimal(i), null));
        }
//        long start = System.currentTimeMillis();
        CopyOnWriteArrayList<Task> newTaskList = new CopyOnWriteArrayList<>();
        Integer taskNum = taskList.size();
        for (int j = 0; j < taskNum; j++) {
            if (taskList == null || taskList.size() == 0) {
                break;
            }
            for (int i = 0; i < userList.size(); i++) {
                if (taskList == null || taskList.size() == 0) {
                    break;
                }
                taskList.get(0).setUserId(userList.get(i).getUserId());
                newTaskList.add(taskList.get(0));
                taskList.remove(taskList.get(0));
            }
            for (int i = 0; i < userList.size(); i++) {
                if (taskList == null || taskList.size() == 0) {
                    break;
                }
                Task task = taskList.get(taskList.size() - 1);
                task.setUserId(userList.get(i).getUserId());
                newTaskList.add(task);
                taskList.remove(task);
            }
        }
//        long end = System.currentTimeMillis();
//        log.info("分配所用时间：{}", end - start);
        log.info("分配后旧的任务集合+++++++++++++++++++++++++++++++++");
        taskList.stream().forEach(task -> log.info("task is:{}",task));
        log.info("分配新的任务集合---------------------------");
        newTaskList.stream().forEach(task -> log.info("newTask is:{}",task));
    }

    /**
     * 新增金额均分规则
     */
    @org.junit.Test
    public void testMoneyAllocat() {
        CopyOnWriteArrayList<User> userList = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 2; i++) {
            userList.add(new User(i));
        }
//        userList.stream().forEach(user -> log.info("user is:{}",user));
        CopyOnWriteArrayList<Task> taskList = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 3; i++) {
            taskList.add(new Task(i, new BigDecimal(i * 10000), null));
        }
        taskList = new CopyOnWriteArrayList(taskList.stream().sorted(Comparator.comparing(Task::getMoney).reversed()).collect(Collectors.toList()).toArray());
        for (int i = 1; i <= taskList.size(); i++) {
            if ((i + (2 * userList.size() - 1)) % (2 * userList.size()) > userList.size() - 1) {
                Integer uid = (2 * userList.size() - 1) - (i + (2 * userList.size() - 1)) % (2 * userList.size());
                taskList.get(i - 1).setUserId(userList.get(uid).getUserId());
            } else {
                Integer uid = (i + (userList.size() * 2 - 1)) % (2 * userList.size());
                taskList.get(i - 1).setUserId(userList.get(uid).getUserId());
            }
        }
        taskList.stream().forEach(task -> log.info("newTask is:{}", task));
//        log.info("分配后旧的任务集合+++++++++++++++++++++++++++++++++");
//        taskList.stream().forEach(task -> log.info("task is:{}",task));
//        log.info("分配新旧的任务集合---------------------------");
//        newTaskList.stream().forEach(task -> log.info("newTask is:{}",task));
    }

    @ToString
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class User {
        private Integer userId;
    }

    @ToString
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Task {
        private Integer taskId;
        private BigDecimal money;
        private Integer userId;
    }

    @org.junit.Test
    public void testMoneyNum() {
        List<DemoUser> userList = new ArrayList<>();
        DemoUser entity = new DemoUser();
        entity.setUserId(1);
        userList.add(entity);
        DemoUser entity2 = new DemoUser();
        entity2.setUserId(2);
        userList.add(entity2);
//        DemoUser entity3 = new DemoUser();
//        entity3.setUserId(3);
//        userList.add(entity3);

        List<DemoTask> taskList = new ArrayList<>();
        DemoTask apply1 = new DemoTask();
        apply1.setTaskId(1);
        apply1.setAmount(new BigDecimal(10000));
        taskList.add(apply1);
        DemoTask apply2 = new DemoTask();
        apply2.setTaskId(2);
        apply2.setAmount(new BigDecimal(5000));
        taskList.add(apply2);
        DemoTask apply3 = new DemoTask();
        apply3.setTaskId(3);
        apply3.setAmount(new BigDecimal(5000.29));
        taskList.add(apply3);
//        DemoTask apply4 = new DemoTask();
//        apply4.setTaskId(5);
//        apply4.setAmount(new BigDecimal(600));
//        taskList.add(apply4);
//        DemoTask apply5 = new DemoTask();
//        apply5.setTaskId(5);
//        apply5.setAmount(new BigDecimal(1600));
//        taskList.add(apply5);
//        DemoTask apply6 = new DemoTask();
//        apply6.setTaskId(6);
//        apply6.setAmount(new BigDecimal(2100));
//        taskList.add(apply6);
//        DemoTask apply7 = new DemoTask();
//        apply7.setTaskId(7);
//        apply7.setAmount(new BigDecimal(1210));
//        taskList.add(apply7);
//
//        DemoTask apply8 = new DemoTask();
//        apply8.setTaskId(8);
//        apply8.setAmount(new BigDecimal(1120));
//        taskList.add(apply8);
//
//        DemoTask apply9 = new DemoTask();
//        apply9.setTaskId(9);
//        apply9.setAmount(new BigDecimal(1310));
//        taskList.add(apply9);
//
//        DemoTask apply10 = new DemoTask();
//        apply10.setTaskId(10);
//        apply10.setAmount(new BigDecimal(1050));
//        taskList.add(apply10);
//
//        DemoTask apply11 = new DemoTask();
//        apply11.setTaskId(11);
//        apply11.setAmount(new BigDecimal(1500));
//        taskList.add(apply11);
        List<DemoTask> copyTask = depCopy(taskList);

        Map<String, List<DemoTask>> map = allotOfAmount(userList, copyTask);

        System.out.println(map);
    }

    public static class DemoUser {
        private Integer userId;
        private String userName;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public String toString() {
            return "DemoUser{" +
                    "userId=" + userId +
                    ", userName='" + userName + '\'' +
                    '}';
        }
    }


    public static class DemoTask implements Serializable {

        private Integer taskId;

        private BigDecimal amount;

        public Integer getTaskId() {
            return taskId;
        }

        public void setTaskId(Integer taskId) {
            this.taskId = taskId;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "DemoTask{" +
                    "taskId=" + taskId +
                    ", amount=" + amount +
                    '}' + '\n';
        }
    }

    /**
     * 深拷贝
     *
     * @param srcList
     * @param <T>
     * @return
     */
    public static <T> List<T> depCopy(List<T> srcList) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(srcList);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream inStream = new ObjectInputStream(byteIn);
            List<T> destList = (List<T>) inStream.readObject();
            return destList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按金额分配
     *
     * @param users 人
     * @param tasks 任务
     * @return
     */
    public static Map<String, List<DemoTask>> allotOfAmount(List<DemoUser> users, List<DemoTask> tasks) {
        //Collections.shuffle(users);//随机排序
        Map<String, List<DemoTask>> allot = new ConcurrentHashMap<>(); //保存分配的信息
        if (users != null && users.size() > 0 && tasks != null && tasks.size() > 0) {
            List<DemoTask> copyTask = depCopy(tasks);//深拷贝,在处理过程中会删除已经分配的任务此处方便记录原始数据使用
            for (int n = 0; n < copyTask.size() + n; n++) {
                if (n % 2 == 0) {
                    copyTask = sortTask(copyTask, "desc");//正排
                } else {
                    copyTask = sortTask(copyTask, "asc");//倒排
                }
                for (int j = 0; j < users.size(); j++) {
                    if (copyTask.isEmpty()) break;
                    if (allot.containsKey(users.get(j).getUserId().toString())) {
                        List<DemoTask> list = allot.get(users.get(j).getUserId().toString());
                        list.add(copyTask.get(0));//每次取排序后的第一条
                        allot.put(users.get(j).getUserId().toString(), list);
                    } else {
                        List<DemoTask> list = new ArrayList<>();
                        list.add(copyTask.get(0));
                        allot.put(users.get(j).getUserId().toString(), list);
                    }
                    copyTask.remove(copyTask.get(0));//分配后删除

                }
            }
        }
        return allot;
    }

    /**
     * 排序
     *
     * @param taskList
     * @param sort
     * @return
     */
    private static List<DemoTask> sortTask(List<DemoTask> taskList, String sort) {
        List<DemoTask> newList;
        if ("asc".equalsIgnoreCase(sort)) {//正序
            newList = taskList.stream().sorted(Comparator.comparing(DemoTask::getAmount))
                    .collect(Collectors.toList());
        } else if ("desc".equalsIgnoreCase(sort)) {//倒叙 reversed()即可
            newList = taskList.stream().sorted(Comparator.comparing(DemoTask::getAmount).reversed())
                    .collect(Collectors.toList());
        } else {
            newList = taskList;
        }
        return newList;
    }

    @org.junit.Test
    public void testCreateTime() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.now().minusDays(1).toLocalDate().atTime(10,0,0);
        LocalDateTime endTime = LocalDateTime.now().minusDays(1).toLocalDate().atTime(18,59,59);
        String startTimeStr = df.format(startTime);
        String endTimeStr = df.format(endTime);
        System.out.println(startTimeStr);
        System.out.println(endTimeStr);
    }

    @org.junit.Test
    public void sftpTest()  throws Exception{
        String hostName= "192.168.37.129";
        String username="sftp";
        String password="sftp";
        String srcFilePath="/mydata/sftp/upload";
        String targetFilePath="C:/Users/liurunkai/Desktop/a.txt";

        downloadFileBySftp(hostName,username,password,srcFilePath,targetFilePath);
    }

    public void downloadFileBySftp(String remoteServerIp,
                                          String username,
                                          String password,
                                          String remoteFilePath,
                                          String localFilePath) throws Exception{
        JSch jsch = new JSch();
        try {
            Session session = jsch.getSession(username, remoteServerIp, 2222);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.get(remoteFilePath, localFilePath);
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            //To change body of catch statement use File | Settings | File Templates.
            log.error(e.getMessage(),e);
        } catch (SftpException e) {
            log.error(e.getMessage(),e);
        }
    }
}
