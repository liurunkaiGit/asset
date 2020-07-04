package com.ruoyi.assetspackage.util;

import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.enums.IsCloseCaseEnum;
import com.ruoyi.assetspackage.enums.IsNoEnum;
import com.ruoyi.assetspackage.enums.PackageFlagEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.domain.server.Sys;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guozeqi
 * @create 2020-05-09
 */
@Component
public class RecordDataImportUtil {

    public static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat format4 = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM");

    /**
     * 数据替换
     * @param list
     * @param bean
     * @return
     */
    @SuppressWarnings("all")
    public static List<Map<String, String>> dataReplace(List<Map<String, String>> list, RecordImportDataMapping bean) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        for (Map<String, String> rowMap : list) {
            Map<String, String> map = new HashMap<>();//转换后每一行的k,v
            for (Map.Entry<String, String> colum : rowMap.entrySet()) {
                String key = colum.getKey().trim().replace(" ", "");//导入的key
                String value = colum.getValue();//导入的value

                String orgCaseNo = bean.getOrgCaseNo();//机构案件号的配置值
                if (key.equals(orgCaseNo)) {
                    map.put("机构案件号", value);
                }
                String certificateNo = bean.getCertificateNo();
                if (key.equals(certificateNo)) {
                    map.put("身份证", value);
                }
                String phone = bean.getPhone();
                if (key.equals(phone)) {
                    map.put("手机号", value);
                }
                String relation = bean.getRelation();
                if (key.equals(relation)) {
                    map.put("关系", value);
                }
                /*String phoneCode = bean.getPhoneCode();
                if (key.equals(phoneCode)) {
                    map.put("电话码", value);
                }*/
                String remark = bean.getRemark();
                if (key.equals(remark)) {
                    map.put("备注", value);
                }
                String seat = bean.getSeat();
                if (key.equals(seat)) {
                    map.put("坐席", value);
                }
                String makeCallTime = bean.getMakeCallTime();
                if (key.equals(makeCallTime)) {
                    map.put("拨打时间", value);
                }
                String callStartTime = bean.getCallStartTime();
                if (key.equals(callStartTime)) {
                    map.put("通话开始时间", value);
                }
                String callEndTime = bean.getCallEndTime();
                if (key.equals(callEndTime)) {
                    map.put("通话结束时间", value);
                }
                String callLength = bean.getCallLength();
                if (key.equals(callLength)) {
                    map.put("通话时长", value);
                }
                String callRecordId = bean.getCallRecordId();
                if (key.equals(callRecordId)) {
                    map.put("通话录音ID", value);
                }
                String grade = bean.getGrade();
                if (key.equals(grade)) {
                    map.put("客户意向等级", value);
                }
                String name = bean.getName();
                if (key.equals(name)) {
                    map.put("客户名称", value);
                }
                String callStatus = bean.getCallStatus();
                if (key.equals(callStatus)) {
                    map.put("通话状态", value);
                }
            }

            result.add(map);
        }

        return result;
    }


    /**
     * 参数校验
     *
     * @param params
     * @return
     */
    @SuppressWarnings("all")
    public static List<Map<String, String>> checkData(List<CollectionRecordImportTemp> dataList) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (CollectionRecordImportTemp collectionRecordImportTemp : dataList) {
            Map<String, String> map = new HashMap<String, String>();
            String orgCaseNo = collectionRecordImportTemp.getOrgCaseNo();
            String id = collectionRecordImportTemp.getId();

            String callStartTime = collectionRecordImportTemp.getCallStartTime();
            if(callStartTime !=null && !"".equals(callStartTime)){
                if(!checkRQ3(callStartTime,"yyyy-MM-dd HH:mm:ss")) {
                    String msg = "通话开始时间的格式不正确";
                    map.put("orgCaseNo", orgCaseNo);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }
            String makeCallTime = collectionRecordImportTemp.getMakeCallTime();
            if(makeCallTime==null || "".equals(makeCallTime)){
                String msg = "拨打时间为空";
                map.put("orgCaseNo", orgCaseNo);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }else{
                if(!checkRQ3(makeCallTime,"yyyy-MM-dd HH:mm:ss")) {
                    String msg = "拨打时间的格式不正确";
                    map.put("orgCaseNo", orgCaseNo);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }

            }


            String callEndTime = collectionRecordImportTemp.getCallEndTime();
            if(callEndTime !=null && !"".equals(callEndTime)){
                if(!checkRQ3(callEndTime,"yyyy-MM-dd HH:mm:ss")) {
                    String msg = "通话结束时间的格式不正确";
                    map.put("orgCaseNo", orgCaseNo);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String callLength = collectionRecordImportTemp.getCallLength();
            if(callLength==null || "".equals(callLength)){
                String msg = "通话时长为空";
                map.put("orgCaseNo", orgCaseNo);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }else{
                if(!checkSZ(callLength)) {
                    String msg = "通话时长的格式不正确";
                    map.put("orgCaseNo", orgCaseNo);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }
            ///////////////////////////////////////////

            String phone = collectionRecordImportTemp.getPhone();
            if(phone==null || "".equals(phone)){
                String msg = "手机号为空";
                map.put("orgCaseNo", orgCaseNo);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }

           /* String phoneCode = collectionRecordImportTemp.getPhoneCode();
            if(phoneCode==null || "".equals(phoneCode)){
                String msg = "电话码为空";
                map.put("orgCaseNo", orgCaseNo);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }*/

            /*String seat = collectionRecordImportTemp.getSeat();
            if(seat==null || "".equals(seat)){
                String msg = "坐席为空";
                map.put("orgCaseNo", orgCaseNo);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }*/

            String callStatus = collectionRecordImportTemp.getCallStatus();
            if(callStatus==null || "".equals(callStatus)){
                String msg = "通话状态为空";
                map.put("orgCaseNo", orgCaseNo);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }
        }
        return list;
    }

    /**
     * 转换
     * @param datas
     * @return
     * @throws Exception
     */
    @SuppressWarnings("all")
    public static List<CollectionRecordImportTemp> dataConvert(List<Map<String, String>> datas,
                                                           String orgId, String importBatchNo,
                                                           String orgName) throws Exception {

        List<CollectionRecordImportTemp> list = new ArrayList<CollectionRecordImportTemp>();
        String loginName = ShiroUtils.getLoginName();
        for (Map<String, String> data : datas) {
            CollectionRecordImportTemp dto = new CollectionRecordImportTemp();
            for (Map.Entry<String, String> entry : data.entrySet()) {
                /*if ("机构案件号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setOrgCaseNo(entry.getValue());//机构案件号
                        continue;
                    }
                }*/
                if ("机构案件号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setOrgCaseNo(entry.getValue().replaceAll("'",""));
                        continue;
                    }
                }
                if ("身份证".equals(entry.getKey())) {
                    if (entry.getValue() != null) {
                        dto.setCertificateNo(entry.getValue());
                        continue;
                    }
                }
                if ("手机号".equals(entry.getKey())) {
                    if (entry.getValue() != null) {
                        dto.setPhone(entry.getValue());
                        continue;
                    }
                }
                if ("关系".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRelation(entry.getValue());
                        continue;
                    }
                }
                /*if ("电话码".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setPhoneCode(entry.getValue());
                        continue;
                    }
                }*/
                if ("备注".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRemark(entry.getValue());
                        continue;
                    }
                }
                if ("坐席".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSeat(entry.getValue());
                        continue;
                    }
                }
                if ("拨打时间".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setMakeCallTime(entry.getValue());
                        continue;
                    }
                }
                if ("通话开始时间".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCallStartTime(entry.getValue());
                        continue;
                    }
                }
                if ("通话结束时间".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCallEndTime(entry.getValue());
                        continue;
                    }
                }
                if ("通话时长".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCallLength(entry.getValue());
                        continue;
                    }
                }
                if ("通话录音ID".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCallRecordId(entry.getValue());
                        continue;
                    }
                }
                if ("客户意向等级".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setGrade(entry.getValue());
                        continue;
                    }
                }
                if ("客户名称".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setName(entry.getValue());
                        continue;
                    }
                }
                if ("通话状态".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCallStatus(entry.getValue());
                        continue;
                    }
                }
            }
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            dto.setId(id);
            dto.setOrgId(orgId);//委托方id
            dto.setCreateBy(loginName);  //导入人
            dto.setCreateTime(new Date());
            dto.setImportBatchNo(importBatchNo);
            dto.setCreateId(ShiroUtils.getUserId());
            list.add(dto);
        }
        return list;
    }

    private static Date getDateByFormat(String value) throws ParseException {
        if (value.length() == 8) {
            return format.parse(value);
        } else if (value.length() == 10) {
            if (value.indexOf("-") != -1) {
                return format3.parse(value);
            } else if (value.indexOf("/") != -1) {
                return format4.parse(value);
            }
        }
        return null;
    }


    /**
     * 金额校验
     *
     * @param value
     * @return
     */
    private static boolean checkJE(String value) {
        String regex = "^[+]?(([0-9]\\d*[.]?)|(0.))(\\d{0,2})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        boolean b = matcher.matches();
        return b;
    }

    /**
     * 日期校验
     *
     * @param value
     * @return
     */
    public static boolean checkRQ(String value) {
//        String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
//        boolean flag = false;
//        String regexs = "[0-9]{4}-[0-9]{2}-[0-9]{2},[0-9]{4}/[0-9]{2}/[0-9]{2},[0-9]{4}[0-9]{2}[0-9]{2}";
        String regexs = "[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]),[1-9]\\d{3}/(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1]),[1-9]\\d{3}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])";
        for (String regex : regexs.split(",")) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(value);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkRQ2(String value) {
        String regex = "[0-9]{4}-[0-9]{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean checkRQ3(String str, String formatString) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }



    /**
     * 数字校验
     *
     * @param value
     * @return
     */
    private static boolean checkSZ(String value) {

        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     * 卡号校验
     *
     * @param value
     * @return
     */
    private static boolean checkKH(String value) {
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches() && value.length() >= 16) {
            return true;
        }
        return false;
    }

    /**
     * 手机校验
     *
     * @param value
     * @return
     */
    private static boolean checkSJ(String value) {
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches() && value.length() == 11) {
            return true;
        }
        return false;
    }

    /**
     * 电话校验
     *
     * @param value
     * @return
     */
    private static boolean checkDH(String value) {
        String regex = "0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}|13[0-9]\\d{8}|15[1089]\\d{8}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     * 邮箱校验
     *
     * @param value
     * @return
     */
    private static boolean checkYX(String value) {
        String mailRegex, mailName, mailDomain;
//        mailName="^[0-9a-z]+\\w*";       //^表明一行以什么开头；^[0-9a-z]表明要以数字或小写字母开头；\\w*表明匹配任意个大写小写字母或数字或下划线
//        mailDomain="([0-9a-z]+\\.)+[0-9a-z]+$";       //***.***.***格式的域名，其中*为小写字母或数字;第一个括号代表有至少一个***.匹配单元，而[0-9a-z]$表明以小写字母或数字结尾
//        mailRegex=mailName+"@"+mailDomain;          //邮箱正则表达式      ^[0-9a-z]+\w*@([0-9a-z]+\.)+[0-9a-z]+$
        mailRegex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        Pattern pattern = Pattern.compile(mailRegex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     * 利率校验
     *
     * @param value
     * @return
     */
    private static boolean checkLL(String value) {
        String[] split = value.split("%");
        String regex = "^[0-9]+(.[0-9]{0,2})?$";
        Pattern pattern = Pattern.compile(regex);
        if (split.length > 0) {
            Matcher matcher = pattern.matcher(split[0]);
            if (matcher.matches() && value.endsWith("%")) {
                return true;
            }
        }

        return false;
    }

    /**
     * 身份证校验
     *
     * @param value
     * @return
     */
    private static boolean checkSFZ(String value) {
        String reg = "\\d{15}(\\d{2}[0-9xX])?";
        Pattern compile = Pattern.compile(reg);
        Matcher matcher = compile.matcher(value.replaceAll("\uFEFF",""));
        return matcher.matches();
    }





}
