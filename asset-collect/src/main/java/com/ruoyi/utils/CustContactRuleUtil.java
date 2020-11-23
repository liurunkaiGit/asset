package com.ruoyi.utils;

import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.mapper.TLcCustContactMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guozeqi
 * @create 2020-11-17
 */

@Component
public class CustContactRuleUtil {

    @Autowired
    private TLcCustContactMapper tLcCustContactMapper;

    private String areaCodes = null;

    @PostConstruct
    private void init()
    {
        Map<String, String> findResult = tLcCustContactMapper.selectAreaCodes();
        areaCodes = "," + findResult.get("areaCodes") + ",";
    }


    /**
     *按规则重构联系人集合
     * @param list
     * @return
     */
    public List<TLcCustContact> custContactRule(List<TLcCustContact> list){
        List<TLcCustContact> resultList = new ArrayList<>();
        for (TLcCustContact tLcCustContact : list) {
            String phone = tLcCustContact.getPhone();
            if(phone != null && this.checkPhone(phone)){
                resultList.add(tLcCustContact);
            }
        }
        return resultList;
    }


    /**
     * 电话校验
     * @param phone
     * @return
     */
    private boolean checkPhone(String phone){
        int length = phone.length();
        if(length == 10){//座机
            return this.checkTel(phone);
        }
        if(length == 11){//座机和手机
            if(this.checkMobile(phone)){//手机
                return true;
            }else{//座机
                return this.checkTel(phone);
            }
        }
        if(length == 12){//座机和手机
            if("0".equals(phone.substring(0,1)) && this.checkMobile(phone.substring(1))){//手机
                return true;
            }else{//座机
                return this.checkTel(phone);
            }
        }
        return false;
    }


    /**
     * 手机号码校验
     * @param phone
     * @return
     */
    private boolean checkMobile(String phone) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        String regex = "^[1](([3|5|8][\\d])|([4][5,6,7,8,9])|([6][5,6])|([7][3,4,5,6,7,8])|([9][8,9]))[\\d]{8}$";// 验证手机号
        if (StringUtils.isNotBlank(phone) && phone.length() == 11) {
            p = Pattern.compile(regex);
            m = p.matcher(phone);
            b = m.matches();
        }
        return b;
    }

    /**
     * 座机号码校验
     * @param phone
     * @return
     */
    private boolean checkTel(String phone) {
        String sub3 = "," + phone.substring(0, 3) + ",";
        String sub4 = "," + phone.substring(0, 4) + ",";
        if(areaCodes.contains(sub3) || areaCodes.contains(sub4)){
            return true;
        }
        return false;
    }







}
