package com.ruoyi.assetspackage.util;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.assetspackage.domain.ImportDataMapping;
import com.ruoyi.assetspackage.domain.TempCurAssetsPackage;
import com.ruoyi.assetspackage.enums.IsCloseCaseEnum;
import com.ruoyi.assetspackage.enums.IsNoEnum;
import com.ruoyi.assetspackage.enums.PackageFlagEnum;
import com.ruoyi.assetspackage.service.ITemplateRelationPackageService;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guozeqi
 * @create 2019-12-27
 */
@Component
public class DataImportUtil {

    @Autowired
    private ITemplateRelationPackageService templateRelationPackageService;

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
    public static List<Map<String, String>> dataReplace(List<Map<String, String>> list, ImportDataMapping bean) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        for (Map<String, String> rowMap : list) {
            Map<String, String> map = new HashMap<>();//转换后每一行的k,v
            for (Map.Entry<String, String> colum : rowMap.entrySet()) {
                String key = colum.getKey().trim().replace(" ", "");//导入的key
                String value = colum.getValue();//导入的value

                String orgCasNo = bean.getOrgCasNo();//机构案件号的配置值
                if (key.equals(orgCasNo)) {
                    map.put("机构案件号", value);
                }
                String org = bean.getOrg();//机构的配置值
                if (key.equals(org)) {
                    map.put("所属机构", value);
                }
                String transferType = bean.getTransferType();//手别
                if (key.equals(transferType)) {
                    map.put("手别", value);
                }
                String ajhssj = bean.getAjhssj();//案件回收时间
                if (key.equals(ajhssj)) {
                    map.put("案件回收时间", value);
                }
                String sex = bean.getCurSex();//性别的配置值
                if (key.equals(sex)) {
                    map.put("性别", value);
                }
                String curName = bean.getCurName();//姓名
                if (key.equals(curName)) {
                    map.put("姓名", value);
                }
                String certificateNo = bean.getCertificateNo();//证件号
                if (key.equals(certificateNo)) {
                    map.put("证件号", value);
                }
                String marriage = bean.getMarriage();//婚姻状况
                if (key.equals(marriage)) {
                    map.put("婚姻状况", value);
                }
                String education = bean.getEducation();//教育程度
                if (key.equals(education)) {
                    map.put("教育程度", value);
                }
                String rmbYe = bean.getRmbYe();//委案金额
                if (key.equals(rmbYe)) {
                    map.put("委案金额", value);
                }
                String waYe = bean.getWaYe();//结案应还金额
                if (key.equals(waYe)) {
                    map.put("结案应还金额", value);
                }
                String rcr = bean.getRcr();//入催日
                if (key.equals(rcr)) {
                    map.put("入催日", value);
                }
                String accountDate = bean.getAccountDate();//账单日
                if (key.equals(accountDate)) {
                    map.put("账单日", value);
                }
                String overdueDays = bean.getOverdueDays();//逾期天数
                if (key.equals(overdueDays)) {
                    map.put("逾期天数", value);
                }
                String firstYqDate = bean.getFirstYqDate();//逾期起始日
                if (key.equals(firstYqDate)) {
                    map.put("逾期起始日", value);
                }
                String firstYqjcDate = bean.getFirstYqjcDate();//应还日期
                if (key.equals(firstYqjcDate)) {
                    map.put("应还日期", value);
                }
                String rmbQkzje = bean.getRmbQkzje();//欠款总金额
                if (key.equals(rmbQkzje)) {
                    map.put("欠款总金额", value);
                }
                String rmbZdyhje = bean.getRmbZdyhje();//最低应还金额
                if (key.equals(rmbZdyhje)) {
                    map.put("最低应还金额", value);
                }
                String rmbYhbjzje = bean.getRmbYhbjzje();//应还本金
                if (key.equals(rmbYhbjzje)) {
                    map.put("应还本金", value);
                }
                String rmbYhlizje = bean.getRmbYhlizje();//应还利息
                if (key.equals(rmbYhlizje)) {
                    map.put("应还利息", value);
                }
                String rmbYhfxzje = bean.getRmbYhfxzje();//应还罚息
                if (key.equals(rmbYhfxzje)) {
                    map.put("应还罚息", value);
                }
                String rmbYhfyzje = bean.getRmbYhfyzje();//应还费用
                if (key.equals(rmbYhfyzje)) {
                    map.put("应还费用", value);
                }
                String znj = bean.getZnj();//滞纳金
                if (key.equals(znj)) {
                    map.put("滞纳金", value);
                }
                String wwCityName = bean.getWwCityName();//所属城市
                if (key.equals(wwCityName)) {
                    map.put("所属城市", value);
                }
                String areaCenter = bean.getAreaCenter();//所属区域
                if (key.equals(areaCenter)) {
                    map.put("所属区域", value);
                }
                String tjFirm = bean.getTjFirm();//推荐商户
                if (key.equals(tjFirm)) {
                    map.put("推荐商户", value);
                }
                String tjWd = bean.getTjWd();//推荐网点
                if (key.equals(tjWd)) {
                    map.put("推荐网点", value);
                }
                String cpmc = bean.getCpmc();//产品名称
                if (key.equals(cpmc)) {
                    map.put("产品名称", value);
                }
                String hkType = bean.getHkType();//还款方式
                if (key.equals(hkType)) {
                    map.put("还款方式", value);
                }
                String borrowEd = bean.getBorrowEd();//放款金额
                if (key.equals(borrowEd)) {
                    map.put("放款金额", value);
                }
                String fz = bean.getFz();//分期期数
                if (key.equals(fz)) {
                    map.put("分期期数", value);
                }
                String yearRates = bean.getYearRates();//年利率
                if (key.equals(yearRates)) {
                    map.put("年利率", value);
                }
                String dayRates = bean.getDayRates();//日利率
                if (key.equals(dayRates)) {
                    map.put("日利率", value);
                }
                String borrowNo = bean.getBorrowNo();//借款卡号
                if (key.equals(borrowNo)) {
                    map.put("借款卡号", value);
                }
                String borrowBlank = bean.getBorrowBlank();//借款卡银行
                if (key.equals(borrowBlank)) {
                    map.put("借款卡银行", value);
                }
                String workName = bean.getWorkName();//单位名称
                if (key.equals(workName)) {
                    map.put("单位名称", value);
                }
                String email = bean.getEmail();//电子邮箱
                if (key.equals(email)) {
                    map.put("电子邮箱", value);
                }
                String workAddress = bean.getWorkAddress();//单位地址
                if (key.equals(workAddress)) {
                    map.put("单位地址", value);
                }
                String customerHomeAddress = bean.getCustomerHomeAddress();//住宅地址
                if (key.equals(customerHomeAddress)) {
                    map.put("住宅地址", value);
                }
                String registAddress = bean.getRegistAddress();//户籍地址
                if (key.equals(registAddress)) {
                    map.put("户籍地址", value);
                }
                String certificateAddress = bean.getCertificateAddress();//身份证地址
                if (key.equals(certificateAddress)) {
                    map.put("身份证地址", value);
                }
                String billAddress = bean.getBillAddress();//账单地址
                if (key.equals(billAddress)) {
                    map.put("账单地址", value);
                }
                String firstYqFlag = bean.getFirstYqFlag();//首逾标识
                if (key.equals(firstYqFlag)) {
                    map.put("首逾标识", value);
                }
                String maxYqtsHis = bean.getMaxYqtsHis();//最大逾期天数
                if (key.equals(maxYqtsHis)) {
                    map.put("最大逾期天数", value);
                }
                String sumYqtsHis = bean.getSumYqtsHis();//累计逾期天数
                if (key.equals(sumYqtsHis)) {
                    map.put("累计逾期天数", value);
                }
                String sumYqcsHis = bean.getSumYqcsHis();//逾期次数
                if (key.equals(sumYqcsHis)) {
                    map.put("逾期次数", value);
                }
                String customerMobile = bean.getCustomerMobile();//移动电话
                if (key.equals(customerMobile)) {
                    map.put("移动电话", value);
                }
                String firstLiaisonName = bean.getFirstLiaisonName();//联系人1姓名
                if (key.equals(firstLiaisonName)) {
                    map.put("联系人1姓名", value);
                }
                String firstLiaisonRelation = bean.getFirstLiaisonRelation();//联系人1关系
                if (key.equals(firstLiaisonRelation)) {
                    map.put("联系人1关系", value);
                }
                String secondLiaisonName = bean.getSecondLiaisonName();//联系人2姓名
                if (key.equals(secondLiaisonName)) {
                    map.put("联系人2姓名", value);
                }
                String secondLiaisonRelation = bean.getSecondLiaisonRelation();//联系人2关系
                if (key.equals(secondLiaisonRelation)) {
                    map.put("联系人2关系", value);
                }
                String threeLiaisonName = bean.getThreeLiaisonName();//联系人3姓名
                if (key.equals(threeLiaisonName)) {
                    map.put("联系人3姓名", value);
                }
                String threeLiaisonRelation = bean.getThreeLiaisonRelation();//联系人3关系
                if (key.equals(threeLiaisonRelation)) {
                    map.put("联系人3关系", value);
                }
                String customerHomeTel = bean.getCustomerHomeTel();//住宅电话
                if (key.equals(customerHomeTel)) {
                    map.put("住宅电话", value);
                }
                String firstLiaisonMobile = bean.getFirstLiaisonMobile();//联系人1电话1
                if (key.equals(firstLiaisonMobile)) {
                    map.put("联系人1电话1", value);
                }
                String firstLiaisonTel = bean.getFirstLiaisonTel();//联系人1电话2
                if (key.equals(firstLiaisonTel)) {
                    map.put("联系人1电话2", value);
                }
                String secondLiaisonMobile = bean.getSecondLiaisonMobile();//联系人2电话1
                if (key.equals(secondLiaisonMobile)) {
                    map.put("联系人2电话1", value);
                }
                String secondLiaisonTel = bean.getSecondLiaisonTel();//联系人2电话2
                if (key.equals(secondLiaisonTel)) {
                    map.put("联系人2电话2", value);
                }
                String threeLiaisonMobile = bean.getThreeLiaisonMobile();//联系人3电话1
                if (key.equals(threeLiaisonMobile)) {
                    map.put("联系人3电话1", value);
                }
                String threeLiaisonTel = bean.getThreeLiaisonTel();//联系人3电话2
                if (key.equals(threeLiaisonTel)) {
                    map.put("联系人3电话2", value);
                }
                String workTel = bean.getWorkTel();//单位电话
                if (key.equals(workTel)) {
                    map.put("单位电话", value);
                }
                String accountAge = bean.getAccountAge();//账龄
                if (key.equals(accountAge)) {
                    map.put("账龄", value);
                }
                String laFlag = bean.getLaFlag();//留案标签
                if (key.equals(laFlag)) {
                    map.put("留案标签", value);
                }
                String fxFlag = bean.getFxFlag();//风险标签
                if (key.equals(fxFlag)) {
                    map.put("风险标签", value);
                }
                String htlx = bean.getHtlx();//合同类型
                if (key.equals(htlx)) {
                    map.put("合同类型", value);
                }
                String jmbq = bean.getJmbq();//减免标签
                if (key.equals(jmbq)) {
                    map.put("减免标签", value);
                }
                String fcbq = bean.getFcbq();//法催标签
                if (key.equals(fcbq)) {
                    map.put("法催标签", value);
                }
                String fxsfbh = bean.getFxsfbh();//罚息是否变化
                if (key.equals(fxsfbh)) {
                    map.put("罚息是否变化", value);
                }
                String remark = bean.getRemark();//备注
                if (key.equals(remark)) {
                    map.put("备注", value);
                }
                String tar = bean.getTar();//退案日
                if (key.equals(tar)) {
                    map.put("退案日", value);
                }
                String jkrq = bean.getJkrq();//借款日期
                if (key.equals(jkrq)) {
                    map.put("借款日期", value);
                }
                String zhychkr = bean.getZhychkr();//最近一次还款日
                if (key.equals(zhychkr)) {
                    map.put("最近一次还款日", value);
                }
                String mqhkje = bean.getMqhkje();//每期还款金额
                if (key.equals(mqhkje)) {
                    map.put("每期还款金额", value);
                }
                String dqqkje = bean.getDqqkje();//当期欠款金额
                if (key.equals(dqqkje)) {
                    map.put("当期欠款金额", value);
                }
                String ljyhje = bean.getLjyhje();//累计已还金额
                if (key.equals(ljyhje)) {
                    map.put("累计已还金额", value);
                }
                String sfje = bean.getSfje();//首付金额
                if (key.equals(sfje)) {
                    map.put("首付金额", value);
                }
                String zdhkzh1 = bean.getZdhkzh1();//指定还款账号1
                if (key.equals(zdhkzh1)) {
                    map.put("指定还款账号1", value);
                }
                String zdhkzh2 = bean.getZdhkzh2();//指定还款账号2
                if (key.equals(zdhkzh2)) {
                    map.put("指定还款账号2", value);
                }
                String zdhkzhhm1 = bean.getZdhkzhhm1();//指定还款账户户名1
                if (key.equals(zdhkzhhm1)) {
                    map.put("指定还款账户户名1", value);
                }
                String zdhkzhhm2 = bean.getZdhkzhhm2();//指定还款账户户名2
                if (key.equals(zdhkzhhm2)) {
                    map.put("指定还款账户户名2", value);
                }
                String zdhkqd1 = bean.getZdhkqd1();//指定还款渠道1
                if (key.equals(zdhkqd1)) {
                    map.put("指定还款渠道1", value);
                }
                String zdhkqd2 = bean.getZdhkqd2();//指定还款渠道2
                if (key.equals(zdhkqd2)) {
                    map.put("指定还款渠道2", value);
                }
                String khmb = bean.getKhmb();//考核目标
                if (key.equals(khmb)) {
                    map.put("考核目标", value);
                }
                String spjg = bean.getSpjg();//商品价格
                if (key.equals(spjg)) {
                    map.put("商品价格", value);
                }
                String dklx = bean.getDklx();//贷款类型
                if (key.equals(dklx)) {
                    map.put("贷款类型", value);
                }
                String jkbs = bean.getJkbs();//借款笔数
                if (key.equals(jkbs)) {
                    map.put("借款笔数", value);
                }
                String spxx = bean.getSpxx();//商品信息
                if (key.equals(spxx)) {
                    map.put("商品信息", value);
                }
                String wacs = bean.getWacs();//委案次数
                if (key.equals(wacs)) {
                    map.put("委案次数", value);
                }
                String ykqs = bean.getYkqs();//已还期数
                if (key.equals(ykqs)) {
                    map.put("已还期数", value);
                }
                String workDept = bean.getWorkDept();//工作部门
                if (key.equals(workDept)) {
                    map.put("工作部门", value);
                }
                String customerMobile2 = bean.getCustomerMobile2();//客户电话2
                if (key.equals(customerMobile2)) {
                    map.put("客户电话2", value);
                }
                String customerMobile3 = bean.getCustomerMobile3();//客户电话3
                if (key.equals(customerMobile3)) {
                    map.put("客户电话3", value);
                }
                String customerMobile4 = bean.getCustomerMobile4();//客户电话4
                if (key.equals(customerMobile4)) {
                    map.put("客户电话4", value);
                }
                String fourthLiaisonName = bean.getFourthLiaisonName();//联系人4姓名
                if (key.equals(fourthLiaisonName)) {
                    map.put("联系人4姓名", value);
                }
                String fourthLiaisonRelation = bean.getFourthLiaisonRelation();//联系人4关系
                if (key.equals(fourthLiaisonRelation)) {
                    map.put("联系人4关系", value);
                }
                String fourthLiaisonMobile = bean.getFourthLiaisonMobile();//联系人4电话
                if (key.equals(fourthLiaisonMobile)) {
                    map.put("联系人4电话", value);
                }
                String fifthLiaisonName = bean.getFifthLiaisonName();//联系人5姓名
                if (key.equals(fifthLiaisonName)) {
                    map.put("联系人5姓名", value);
                }
                String fifthLiaisonRelation = bean.getFifthLiaisonRelation();//联系人5关系
                if (key.equals(fifthLiaisonRelation)) {
                    map.put("联系人5关系", value);
                }
                String fifthLiaisonMobile = bean.getFifthLiaisonMobile();//联系人5电话
                if (key.equals(fifthLiaisonMobile)) {
                    map.put("联系人5电话", value);
                }
                String dzhxrq = bean.getDzhxrq();
                if (key.equals(dzhxrq)) {
                    map.put("呆账核销日期", value);
                }
                String customerNo = bean.getCustomerNo();
                if (key.equals(customerNo)) {
                    map.put("客户号", value);
                }
                String sjrq = bean.getSjrq();
                if (key.equals(sjrq)) {
                    map.put("数据日期", value);
                }
                String jjh = bean.getJjh();
                if (key.equals(jjh)) {
                    map.put("借据号", value);
                }
            }

            result.add(map);
        }

        return result;
    }

    @SuppressWarnings("all")
    public static List<Map<String, String>> dataReplace2(List<Map<String, String>> list, ImportDataMapping bean,List<String> freeRelations) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
            for (Map<String, String> rowMap : list) {
                Map<String, String> map = new HashMap<>();//转换后每一行的k,v
                Map<String, String> freeRowMap = new LinkedHashMap<>();//自由导入行map
                for (Map.Entry<String, String> colum : rowMap.entrySet()) {
                    String key = colum.getKey().trim().replace(" ", "");//导入的key
                    String value = colum.getValue();//导入的value

                    String orgCasNo = bean.getOrgCasNo();//机构案件号的配置值
                    if (key.equals(orgCasNo)) {
                        map.put("机构案件号", value);
                    }
                    String org = bean.getOrg();//机构的配置值
                    if (key.equals(org)) {
                        map.put("所属机构", value);
                    }
                    String transferType = bean.getTransferType();//手别
                    if (key.equals(transferType)) {
                        map.put("手别", value);
                    }
                    String ajhssj = bean.getAjhssj();//案件回收时间
                    if (key.equals(ajhssj)) {
                        map.put("案件回收时间", value);
                    }
                    String sex = bean.getCurSex();//性别的配置值
                    if (key.equals(sex)) {
                        map.put("性别", value);
                    }
                    String curName = bean.getCurName();//姓名
                    if (key.equals(curName)) {
                        map.put("姓名", value);
                    }
                    String certificateNo = bean.getCertificateNo();//证件号
                    if (key.equals(certificateNo)) {
                        map.put("证件号", value);
                    }
                    String marriage = bean.getMarriage();//婚姻状况
                    if (key.equals(marriage)) {
                        map.put("婚姻状况", value);
                    }
                    String education = bean.getEducation();//教育程度
                    if (key.equals(education)) {
                        map.put("教育程度", value);
                    }
                    String rmbYe = bean.getRmbYe();//委案金额
                    if (key.equals(rmbYe)) {
                        map.put("委案金额", value);
                    }
                    String waYe = bean.getWaYe();//结案应还金额
                    if (key.equals(waYe)) {
                        map.put("结案应还金额", value);
                    }
                    String rcr = bean.getRcr();//入催日
                    if (key.equals(rcr)) {
                        map.put("入催日", value);
                    }
                    String accountDate = bean.getAccountDate();//账单日
                    if (key.equals(accountDate)) {
                        map.put("账单日", value);
                    }
                    String overdueDays = bean.getOverdueDays();//逾期天数
                    if (key.equals(overdueDays)) {
                        map.put("逾期天数", value);
                    }
                    String firstYqDate = bean.getFirstYqDate();//逾期起始日
                    if (key.equals(firstYqDate)) {
                        map.put("逾期起始日", value);
                    }
                    String firstYqjcDate = bean.getFirstYqjcDate();//应还日期
                    if (key.equals(firstYqjcDate)) {
                        map.put("应还日期", value);
                    }
                    String rmbQkzje = bean.getRmbQkzje();//欠款总金额
                    if (key.equals(rmbQkzje)) {
                        map.put("欠款总金额", value);
                    }
                    String rmbZdyhje = bean.getRmbZdyhje();//最低应还金额
                    if (key.equals(rmbZdyhje)) {
                        map.put("最低应还金额", value);
                    }
                    String rmbYhbjzje = bean.getRmbYhbjzje();//应还本金
                    if (key.equals(rmbYhbjzje)) {
                        map.put("应还本金", value);
                    }
                    String rmbYhlizje = bean.getRmbYhlizje();//应还利息
                    if (key.equals(rmbYhlizje)) {
                        map.put("应还利息", value);
                    }
                    String rmbYhfxzje = bean.getRmbYhfxzje();//应还罚息
                    if (key.equals(rmbYhfxzje)) {
                        map.put("应还罚息", value);
                    }
                    String rmbYhfyzje = bean.getRmbYhfyzje();//应还费用
                    if (key.equals(rmbYhfyzje)) {
                        map.put("应还费用", value);
                    }
                    String znj = bean.getZnj();//滞纳金
                    if (key.equals(znj)) {
                        map.put("滞纳金", value);
                    }
                    String wwCityName = bean.getWwCityName();//所属城市
                    if (key.equals(wwCityName)) {
                        map.put("所属城市", value);
                    }
                    String areaCenter = bean.getAreaCenter();//所属区域
                    if (key.equals(areaCenter)) {
                        map.put("所属区域", value);
                    }
                    String tjFirm = bean.getTjFirm();//推荐商户
                    if (key.equals(tjFirm)) {
                        map.put("推荐商户", value);
                    }
                    String tjWd = bean.getTjWd();//推荐网点
                    if (key.equals(tjWd)) {
                        map.put("推荐网点", value);
                    }
                    String cpmc = bean.getCpmc();//产品名称
                    if (key.equals(cpmc)) {
                        map.put("产品名称", value);
                    }
                    String hkType = bean.getHkType();//还款方式
                    if (key.equals(hkType)) {
                        map.put("还款方式", value);
                    }
                    String borrowEd = bean.getBorrowEd();//放款金额
                    if (key.equals(borrowEd)) {
                        map.put("放款金额", value);
                    }
                    String fz = bean.getFz();//分期期数
                    if (key.equals(fz)) {
                        map.put("分期期数", value);
                    }
                    String yearRates = bean.getYearRates();//年利率
                    if (key.equals(yearRates)) {
                        map.put("年利率", value);
                    }
                    String dayRates = bean.getDayRates();//日利率
                    if (key.equals(dayRates)) {
                        map.put("日利率", value);
                    }
                    String borrowNo = bean.getBorrowNo();//借款卡号
                    if (key.equals(borrowNo)) {
                        map.put("借款卡号", value);
                    }
                    String borrowBlank = bean.getBorrowBlank();//借款卡银行
                    if (key.equals(borrowBlank)) {
                        map.put("借款卡银行", value);
                    }
                    String workName = bean.getWorkName();//单位名称
                    if (key.equals(workName)) {
                        map.put("单位名称", value);
                    }
                    String email = bean.getEmail();//电子邮箱
                    if (key.equals(email)) {
                        map.put("电子邮箱", value);
                    }
                    String workAddress = bean.getWorkAddress();//单位地址
                    if (key.equals(workAddress)) {
                        map.put("单位地址", value);
                    }
                    String customerHomeAddress = bean.getCustomerHomeAddress();//住宅地址
                    if (key.equals(customerHomeAddress)) {
                        map.put("住宅地址", value);
                    }
                    String registAddress = bean.getRegistAddress();//户籍地址
                    if (key.equals(registAddress)) {
                        map.put("户籍地址", value);
                    }
                    String certificateAddress = bean.getCertificateAddress();//身份证地址
                    if (key.equals(certificateAddress)) {
                        map.put("身份证地址", value);
                    }
                    String billAddress = bean.getBillAddress();//账单地址
                    if (key.equals(billAddress)) {
                        map.put("账单地址", value);
                    }
                    String firstYqFlag = bean.getFirstYqFlag();//首逾标识
                    if (key.equals(firstYqFlag)) {
                        map.put("首逾标识", value);
                    }
                    String maxYqtsHis = bean.getMaxYqtsHis();//最大逾期天数
                    if (key.equals(maxYqtsHis)) {
                        map.put("最大逾期天数", value);
                    }
                    String sumYqtsHis = bean.getSumYqtsHis();//累计逾期天数
                    if (key.equals(sumYqtsHis)) {
                        map.put("累计逾期天数", value);
                    }
                    String sumYqcsHis = bean.getSumYqcsHis();//逾期次数
                    if (key.equals(sumYqcsHis)) {
                        map.put("逾期次数", value);
                    }
                    String customerMobile = bean.getCustomerMobile();//移动电话
                    if (key.equals(customerMobile)) {
                        map.put("移动电话", value);
                    }
                    String firstLiaisonName = bean.getFirstLiaisonName();//联系人1姓名
                    if (key.equals(firstLiaisonName)) {
                        map.put("联系人1姓名", value);
                    }
                    String firstLiaisonRelation = bean.getFirstLiaisonRelation();//联系人1关系
                    if (key.equals(firstLiaisonRelation)) {
                        map.put("联系人1关系", value);
                    }
                    String secondLiaisonName = bean.getSecondLiaisonName();//联系人2姓名
                    if (key.equals(secondLiaisonName)) {
                        map.put("联系人2姓名", value);
                    }
                    String secondLiaisonRelation = bean.getSecondLiaisonRelation();//联系人2关系
                    if (key.equals(secondLiaisonRelation)) {
                        map.put("联系人2关系", value);
                    }
                    String threeLiaisonName = bean.getThreeLiaisonName();//联系人3姓名
                    if (key.equals(threeLiaisonName)) {
                        map.put("联系人3姓名", value);
                    }
                    String threeLiaisonRelation = bean.getThreeLiaisonRelation();//联系人3关系
                    if (key.equals(threeLiaisonRelation)) {
                        map.put("联系人3关系", value);
                    }
                    String customerHomeTel = bean.getCustomerHomeTel();//住宅电话
                    if (key.equals(customerHomeTel)) {
                        map.put("住宅电话", value);
                    }
                    String firstLiaisonMobile = bean.getFirstLiaisonMobile();//联系人1电话1
                    if (key.equals(firstLiaisonMobile)) {
                        map.put("联系人1电话1", value);
                    }
                    String firstLiaisonTel = bean.getFirstLiaisonTel();//联系人1电话2
                    if (key.equals(firstLiaisonTel)) {
                        map.put("联系人1电话2", value);
                    }
                    String secondLiaisonMobile = bean.getSecondLiaisonMobile();//联系人2电话1
                    if (key.equals(secondLiaisonMobile)) {
                        map.put("联系人2电话1", value);
                    }
                    String secondLiaisonTel = bean.getSecondLiaisonTel();//联系人2电话2
                    if (key.equals(secondLiaisonTel)) {
                        map.put("联系人2电话2", value);
                    }
                    String threeLiaisonMobile = bean.getThreeLiaisonMobile();//联系人3电话1
                    if (key.equals(threeLiaisonMobile)) {
                        map.put("联系人3电话1", value);
                    }
                    String threeLiaisonTel = bean.getThreeLiaisonTel();//联系人3电话2
                    if (key.equals(threeLiaisonTel)) {
                        map.put("联系人3电话2", value);
                    }
                    String workTel = bean.getWorkTel();//单位电话
                    if (key.equals(workTel)) {
                        map.put("单位电话", value);
                    }
                    String accountAge = bean.getAccountAge();//账龄
                    if (key.equals(accountAge)) {
                        map.put("账龄", value);
                    }
                    String laFlag = bean.getLaFlag();//留案标签
                    if (key.equals(laFlag)) {
                        map.put("留案标签", value);
                    }
                    String fxFlag = bean.getFxFlag();//风险标签
                    if (key.equals(fxFlag)) {
                        map.put("风险标签", value);
                    }
                    String htlx = bean.getHtlx();//合同类型
                    if (key.equals(htlx)) {
                        map.put("合同类型", value);
                    }
                    String jmbq = bean.getJmbq();//减免标签
                    if (key.equals(jmbq)) {
                        map.put("减免标签", value);
                    }
                    String fcbq = bean.getFcbq();//法催标签
                    if (key.equals(fcbq)) {
                        map.put("法催标签", value);
                    }
                    String fxsfbh = bean.getFxsfbh();//罚息是否变化
                    if (key.equals(fxsfbh)) {
                        map.put("罚息是否变化", value);
                    }
                    String remark = bean.getRemark();//备注
                    if (key.equals(remark)) {
                        map.put("备注", value);
                    }
                    String tar = bean.getTar();//退案日
                    if (key.equals(tar)) {
                        map.put("退案日", value);
                    }
                    String jkrq = bean.getJkrq();//借款日期
                    if (key.equals(jkrq)) {
                        map.put("借款日期", value);
                    }
                    String zhychkr = bean.getZhychkr();//最近一次还款日
                    if (key.equals(zhychkr)) {
                        map.put("最近一次还款日", value);
                    }
                    String mqhkje = bean.getMqhkje();//每期还款金额
                    if (key.equals(mqhkje)) {
                        map.put("每期还款金额", value);
                    }
                    String dqqkje = bean.getDqqkje();//当期欠款金额
                    if (key.equals(dqqkje)) {
                        map.put("当期欠款金额", value);
                    }
                    String ljyhje = bean.getLjyhje();//累计已还金额
                    if (key.equals(ljyhje)) {
                        map.put("累计已还金额", value);
                    }
                    String sfje = bean.getSfje();//首付金额
                    if (key.equals(sfje)) {
                        map.put("首付金额", value);
                    }
                    String zdhkzh1 = bean.getZdhkzh1();//指定还款账号1
                    if (key.equals(zdhkzh1)) {
                        map.put("指定还款账号1", value);
                    }
                    String zdhkzh2 = bean.getZdhkzh2();//指定还款账号2
                    if (key.equals(zdhkzh2)) {
                        map.put("指定还款账号2", value);
                    }
                    String zdhkzhhm1 = bean.getZdhkzhhm1();//指定还款账户户名1
                    if (key.equals(zdhkzhhm1)) {
                        map.put("指定还款账户户名1", value);
                    }
                    String zdhkzhhm2 = bean.getZdhkzhhm2();//指定还款账户户名2
                    if (key.equals(zdhkzhhm2)) {
                        map.put("指定还款账户户名2", value);
                    }
                    String zdhkqd1 = bean.getZdhkqd1();//指定还款渠道1
                    if (key.equals(zdhkqd1)) {
                        map.put("指定还款渠道1", value);
                    }
                    String zdhkqd2 = bean.getZdhkqd2();//指定还款渠道2
                    if (key.equals(zdhkqd2)) {
                        map.put("指定还款渠道2", value);
                    }
                    String khmb = bean.getKhmb();//考核目标
                    if (key.equals(khmb)) {
                        map.put("考核目标", value);
                    }
                    String spjg = bean.getSpjg();//商品价格
                    if (key.equals(spjg)) {
                        map.put("商品价格", value);
                    }
                    String dklx = bean.getDklx();//贷款类型
                    if (key.equals(dklx)) {
                        map.put("贷款类型", value);
                    }
                    String jkbs = bean.getJkbs();//借款笔数
                    if (key.equals(jkbs)) {
                        map.put("借款笔数", value);
                    }
                    String spxx = bean.getSpxx();//商品信息
                    if (key.equals(spxx)) {
                        map.put("商品信息", value);
                    }
                    String wacs = bean.getWacs();//委案次数
                    if (key.equals(wacs)) {
                        map.put("委案次数", value);
                    }
                    String ykqs = bean.getYkqs();//已还期数
                    if (key.equals(ykqs)) {
                        map.put("已还期数", value);
                    }
                    String workDept = bean.getWorkDept();//工作部门
                    if (key.equals(workDept)) {
                        map.put("工作部门", value);
                    }
                    String customerMobile2 = bean.getCustomerMobile2();//客户电话2
                    if (key.equals(customerMobile2)) {
                        map.put("客户电话2", value);
                    }
                    String customerMobile3 = bean.getCustomerMobile3();//客户电话3
                    if (key.equals(customerMobile3)) {
                        map.put("客户电话3", value);
                    }
                    String customerMobile4 = bean.getCustomerMobile4();//客户电话4
                    if (key.equals(customerMobile4)) {
                        map.put("客户电话4", value);
                    }
                    String fourthLiaisonName = bean.getFourthLiaisonName();//联系人4姓名
                    if (key.equals(fourthLiaisonName)) {
                        map.put("联系人4姓名", value);
                    }
                    String fourthLiaisonRelation = bean.getFourthLiaisonRelation();//联系人4关系
                    if (key.equals(fourthLiaisonRelation)) {
                        map.put("联系人4关系", value);
                    }
                    String fourthLiaisonMobile = bean.getFourthLiaisonMobile();//联系人4电话
                    if (key.equals(fourthLiaisonMobile)) {
                        map.put("联系人4电话", value);
                    }
                    String fifthLiaisonName = bean.getFifthLiaisonName();//联系人5姓名
                    if (key.equals(fifthLiaisonName)) {
                        map.put("联系人5姓名", value);
                    }
                    String fifthLiaisonRelation = bean.getFifthLiaisonRelation();//联系人5关系
                    if (key.equals(fifthLiaisonRelation)) {
                        map.put("联系人5关系", value);
                    }
                    String fifthLiaisonMobile = bean.getFifthLiaisonMobile();//联系人5电话
                    if (key.equals(fifthLiaisonMobile)) {
                        map.put("联系人5电话", value);
                    }
                    String dzhxrq = bean.getDzhxrq();
                    if (key.equals(dzhxrq)) {
                        map.put("呆账核销日期", value);
                    }
                    String customerNo = bean.getCustomerNo();
                    if (key.equals(customerNo)) {
                        map.put("客户号", value);
                    }
                    String sjrq = bean.getSjrq();
                    if (key.equals(sjrq)) {
                        map.put("数据日期", value);
                    }
                    String jjh = bean.getJjh();
                    if (key.equals(jjh)) {
                        map.put("借据号", value);
                    }
                    if(freeRelations.size()>0){
                        for (String freeRelation : freeRelations) {//客户模板头
                            if(key.equals(freeRelation) && value !=null && !"".equals(value)){
                                freeRowMap.put(key,value);
                            }
                        }
                    }
                }

                String freeStr = JSON.toJSONString(freeRowMap);
                map.put("自由导入",freeStr);
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
    public static List<String> checkData(List<Map<String, String>> datas, ImportDataMapping bean) throws Exception {
        String key = "message";//数据导入失败：请检查第1行哪个字段的内容或格式是否正确
        //Map<String,String> map = new HashMap<String, String>();
//        List<List<String>> result = new ArrayList<List<String>>();
        List<String> list = new ArrayList<String>();
        int dataRowNum = Integer.valueOf(bean.getDataRowNum());

        for (int i = dataRowNum; i < datas.size() + dataRowNum; i++) {
            //Map<String,String> map = new HashMap<String, String>();
            //List<String> list = new ArrayList<String>();
            Map<String, String> data = datas.get(i - dataRowNum);
            for (Map.Entry<String, String> entry : data.entrySet()) {

                if ("机构案件号".equals(entry.getKey())) {
                    if (entry.getValue() == null || "".equals(entry.getValue().trim())) {
                        list.add("数据导入失败，请检查第" + i + "行" + "机构案件号" + "的内容或格式是否正确");
                        //continue;
                    }
                }
                if ("人民币账户last缴款金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "人民币账户last缴款金额" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("委案金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "委案金额" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("应还罚息".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "应还罚息" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                /*if ("人民币账户当前CD值".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "人民币账户当前CD值" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }*/
                if ("应还利息".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "应还利息" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }
                if ("人民币账户应还本金1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "人民币账户应还本金1" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("人民币账户应还本金2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "人民币账户应还本金2" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }
                if ("应还本金".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "应还本金" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("人民币账户应还罚息1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "人民币账户应还罚息1" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("人民币账户应还罚息2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "人民币账户应还罚息2" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("人民币账户应还费用1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "人民币账户应还费用1" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("人民币账户应还费用2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "人民币账户应还费用2" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("应还费用".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "应还费用" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("最低应还金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "最低应还金额" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("欠款总金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "欠款总金额" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("人民币账户额度固定额度".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "人民币账户额度固定额度" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("放款金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "放款金额" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("剩余核销其他费用".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "剩余核销其他费用" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("剩余核销手续费".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "剩余核销手续费" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("剩余核销本金".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "剩余核销本金" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("剩余核销滞纳费".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "剩余核销滞纳费" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("剩余核销逾期息".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "剩余核销逾期息" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }
                if ("剩余核销金额合计".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "剩余核销金额合计" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                /*if ("外币账户应还罚息总额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "外币账户应还罚息总额" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }
                if ("外币账户应还利息总额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "外币账户应还利息总额" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }
                if ("外币账户应还本金1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "外币账户应还本金1" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("外币账户应还本金2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "外币账户应还本金2" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }
                if ("外币账户应还本金总额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "外币账户应还本金总额" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("外币账户应还罚息1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "外币账户应还罚息1" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("外币账户应还罚息2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "外币账户应还罚息2" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }
                if ("外币账户应还费用1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "外币账户应还费用1" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("外币账户应还费用2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "外币账户应还费用2" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("外币账户应还费用总额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "外币账户应还费用总额" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("外币账户最低应还额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "外币账户最低应还额" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("外币账户欠款总金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "外币账户欠款总金额" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }*/
                if ("结案应还金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "结案应还金额" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("授信额度".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "授信额度" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("收回核销手续费".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "收回核销手续费" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("收回核销本金".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "收回核销本金" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("收回核销滞纳费".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "收回核销滞纳费" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("收回核销费用".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "收回核销费用" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("收回核销逾期息".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "收回核销逾期息" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("收回核销金额合计".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "收回核销金额合计" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("核销手续费催收".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "核销手续费催收" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }
                if ("核销本金催收".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "核销本金催收" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }
                if ("核销滞纳费催收".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "核销滞纳费催收" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("核销费用催收".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "核销费用催收" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("核销逾期息催收".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "核销逾期息催收" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("核销金额合计催收".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "核销金额合计催收" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                /*if ("美元余额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "美元余额" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("美元最低应缴款金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "美元最低应缴款金额" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("美元最后一次缴款金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "美元最后一次缴款金额" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }*/
                if ("账户余额本金".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "账户余额本金" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }
                /*if ("当前收入".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "当前收入" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }*/
                //=======================日期========================
                if ("人民币账户最后一次缴款日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "人民币账户最后一次缴款日" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }

                if ("停卡日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "停卡日" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("停止计息日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "停止计息日期" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("入催日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "入催日" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("呆账核销日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "呆账核销日期" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("委外计划截止日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "委外计划截止日期" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("委外起始日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "委外起始日期" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("应还款日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "应还款日" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("开始逾期日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "开始逾期日期" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }
                if ("开户日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "开户日" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }

                if ("案件核准日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "案件核准日期" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("统计日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "统计日期" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                /*if ("美元最后一次缴款日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "美元最后一次缴款日" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }*/
                if ("账户结清日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "账户结清日期" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("额度激活日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "额度激活日期" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("首次放款日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "首次放款日期" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("逾期起始日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "逾期起始日" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("应还日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "应还日期" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("出生日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "出生日期" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                /*if ("最近逾期月份".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ2(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "最近逾期月份" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }*/
                //================其他====================
                /*if ("借款卡号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkKH(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "借款卡号" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }
                if ("还款卡号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkKH(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "还款卡号" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("年利率".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkLL(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "年利率" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("日利率".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkLL(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "日利率" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("帐单地址邮编".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkSZ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "帐单地址邮编" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }
                if ("客户家庭地址邮编".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkSZ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "客户家庭地址邮编" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("单位邮编".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkSZ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "单位邮编" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }*/
                /*if ("消费金融账号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkSZ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "消费金融账号" + "的内容或格式是否正确");
                            // continue;
                        }
                    }
                }*/
                if ("账单日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkSZ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "账单日" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("逾期天数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkSZ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "逾期天数" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("长委次数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkSZ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "长委次数" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("证件号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkSFZ(entry.getValue().trim())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "证件号" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                /*if ("电子邮箱".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkYX(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "电子邮箱" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }*/
                if ("移动电话".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkSJ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "移动电话" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("联系人1电话1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkSJ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "联系人1电话1" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("联系人2电话1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkSJ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "联系人2电话1" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("联系人3电话1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkSJ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "联系人3电话1" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                /*if ("住宅电话".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkDH(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "住宅电话" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("单位电话".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkDH(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "单位电话" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("联系人1电话2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkDH(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "联系人1电话2" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("联系人2电话2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkDH(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "联系人2电话2" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("联系人3电话2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkDH(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "联系人3电话2" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }*/
                //====================
                if ("贷款余额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "贷款余额" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("逾期金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "逾期金额" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("逾期本金".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "逾期本金" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("逾期利息".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "逾期利息" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("逾期手续费".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "逾期手续费" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("滞纳金".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkJE(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "滞纳金" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("数据日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "数据日期" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("起息日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "起息日期" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("到期日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "到期日期" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("分配时间".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "分配时间" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if ("案件回收时间".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue().trim())) {
                        if (!checkRQ(entry.getValue())) {
                            list.add("数据导入失败，请检查第" + i + "行" + "案件回收时间" + "的内容或格式是否正确");
                            //continue;
                        }
                    }
                }


            }

            // result.add(list);
        }
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }


    public static List<Map<String, String>> checkData2(List<TempCurAssetsPackage> dataList) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (TempCurAssetsPackage tempCurAssetsPackage : dataList) {
            Map<String, String> map = new HashMap<String, String>();
            String orgCasno = tempCurAssetsPackage.getOrgCasno();
            String id = tempCurAssetsPackage.getId();

            String waje = tempCurAssetsPackage.getRmbYe();
            if(waje==null || "".equals(waje)){
                String msg = "委案金额为空";
                map.put("orgCasno", orgCasno);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }else{
                if(!checkJE(waje)) {
                    String msg = "委案金额的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }
            String jayhje = tempCurAssetsPackage.getWaYe();
            if(jayhje==null || "".equals(jayhje)){
                String msg = "结案应还金额为空";
                map.put("orgCasno", orgCasno);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }else{
                if(!checkJE(jayhje)) {
                    String msg = "结案应还金额的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String curName = tempCurAssetsPackage.getCurName();
            if(curName==null || "".equals(curName)){
                String msg = "姓名为空";
                map.put("orgCasno", orgCasno);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }

            String Transfertype = tempCurAssetsPackage.getTransfertype();
            if(Transfertype==null || "".equals(Transfertype)){
                String msg = "手别为空";
                map.put("orgCasno", orgCasno);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }

            String CertificateNo = tempCurAssetsPackage.getCertificateNo();
            if(CertificateNo==null || "".equals(CertificateNo)){
                String msg = "证件号为空";
                map.put("orgCasno", orgCasno);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }

            String rcr = tempCurAssetsPackage.getRcr();
            if(rcr==null || "".equals(rcr)){
                String msg = "入催日为空";
                map.put("orgCasno", orgCasno);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }else{
                if(!checkRQ(rcr)) {
                    String msg = "入催日的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String CustomerMobile = tempCurAssetsPackage.getCustomerMobile();
            if(CustomerMobile==null || "".equals(CustomerMobile)){
                String msg = "移动电话为空";
                map.put("orgCasno", orgCasno);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }else{
                if(!checkSJ(CustomerMobile)) {
                    String msg = "移动电话的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            ///////////////////////////////////////////

           /* String rmbLastJkje = tempCurAssetsPackage.getRmbLastJkje();
            if(rmbLastJkje!=null && !"".equals(rmbLastJkje)){
                if(!checkJE(rmbLastJkje)) {
                    String msg = "人民币账户last缴款金额的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }*/

            String RmbYhfxzje = tempCurAssetsPackage.getRmbYhfxzje();
            if(RmbYhfxzje!=null && !"".equals(RmbYhfxzje)){
                if(!checkJE(RmbYhfxzje)) {
                    String msg = "应还罚息格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String RmbYhlizje = tempCurAssetsPackage.getRmbYhlizje();
            if(RmbYhlizje!=null && !"".equals(RmbYhlizje)){
                if(!checkJE(RmbYhlizje)) {
                    String msg = "应还利息格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String RmbYhbjzje = tempCurAssetsPackage.getRmbYhbjzje();
            if(RmbYhbjzje!=null && !"".equals(RmbYhbjzje)){
                if(!checkJE(RmbYhbjzje)) {
                    String msg = "应还本金格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String getRmbYhfyzje = tempCurAssetsPackage.getRmbYhfyzje();
            if(getRmbYhfyzje!=null && !"".equals(getRmbYhfyzje)){
                if(!checkJE(getRmbYhfyzje)) {
                    String msg = "应还费用格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String mqhkje = tempCurAssetsPackage.getMqhkje();//每期还款金额
            if(mqhkje!=null && !"".equals(mqhkje)){
                if(!checkJE(mqhkje)) {
                    String msg = "每期还款金额格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }
            String dqqkje = tempCurAssetsPackage.getDqqkje();//当期欠款金额
            if(dqqkje!=null && !"".equals(dqqkje)){
                if(!checkJE(dqqkje)) {
                    String msg = "当期欠款金额格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String ljyhje = tempCurAssetsPackage.getLjyhje();//累计已还金额
            if(ljyhje!=null && !"".equals(ljyhje)){
                if(!checkJE(ljyhje)) {
                    String msg = "累计已还金额格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String sfje = tempCurAssetsPackage.getSfje();//首付金额
            if(sfje!=null && !"".equals(sfje)){
                if(!checkJE(sfje)) {
                    String msg = "首付金额格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }
            String spjg = tempCurAssetsPackage.getSpjg();//商品价格
            if(spjg!=null && !"".equals(spjg)){
                if(!checkJE(spjg)) {
                    String msg = "商品价格格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String yqts = tempCurAssetsPackage.getOverdueDays();//逾期天数
            if(yqts!=null && !"".equals(yqts)){
                if(!checkZS(yqts)) {
                    String msg = "逾期天数格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }


           /* if(!map.isEmpty()){
                list.add(map);
            }*/
        }
        return list;
    }


    public static List<Map<String, String>> checkData3(List<TempCurAssetsPackage> dataList) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (TempCurAssetsPackage tempCurAssetsPackage : dataList) {
            Map<String, String> map = new HashMap<String, String>();
            String orgCasno = tempCurAssetsPackage.getOrgCasno();
            String id = tempCurAssetsPackage.getId();

//            String waje = tempCurAssetsPackage.getRmbYe();
//            if(waje==null || "".equals(waje)){
//                String msg = "委案金额为空";
//                map.put("orgCasno", orgCasno);
//                map.put("msg", msg);
//                map.put("id", id);
//                list.add(map);
//                continue;
//            }else{
//                if(!checkJE(waje)) {
//                    String msg = "委案金额的格式不正确";
//                    map.put("orgCasno", orgCasno);
//                    map.put("msg", msg);
//                    map.put("id", id);
//                    list.add(map);
//                    continue;
//                }
//            }
            String jayhje = tempCurAssetsPackage.getWaYe();
            if(jayhje==null || "".equals(jayhje)){
                String msg = "结案应还金额为空";
                map.put("orgCasno", orgCasno);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }else{
                if(!checkJE(jayhje)) {
                    String msg = "结案应还金额的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String curName = tempCurAssetsPackage.getCurName();
            if(curName==null || "".equals(curName)){
                String msg = "姓名为空";
                map.put("orgCasno", orgCasno);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }


        }
        return list;
    }

    public static List<Map<String, String>> checkUpdateData(List<TempCurAssetsPackage> dataList) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (TempCurAssetsPackage tempCurAssetsPackage : dataList) {
            Map<String, String> map = new HashMap<String, String>();
            String orgCasno = tempCurAssetsPackage.getOrgCasno();
            String id = tempCurAssetsPackage.getId();

            String waje = tempCurAssetsPackage.getRmbYe();
            if(waje!=null && !"".equals(waje)){
                if(!checkJE(waje)) {
                    String msg = "委案金额的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }
            String jayhje = tempCurAssetsPackage.getWaYe();
            if(jayhje!=null && !"".equals(jayhje)){
                if(!checkJE(jayhje)) {
                    String msg = "结案应还金额的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String rcr = tempCurAssetsPackage.getRcr();
            if(rcr!=null && !"".equals(rcr)){
                if(!checkRQ(rcr)) {
                    String msg = "入催日的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }
            String CustomerMobile = tempCurAssetsPackage.getCustomerMobile();
            if(CustomerMobile!=null && !"".equals(CustomerMobile)){
                if(!checkSJ(CustomerMobile)) {
                    String msg = "移动电话的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String RmbYhfxzje = tempCurAssetsPackage.getRmbYhfxzje();
            if(RmbYhfxzje!=null && !"".equals(RmbYhfxzje)){
                if(!checkJE(RmbYhfxzje)) {
                    String msg = "应还罚息格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String RmbYhlizje = tempCurAssetsPackage.getRmbYhlizje();
            if(RmbYhlizje!=null && !"".equals(RmbYhlizje)){
                if(!checkJE(RmbYhlizje)) {
                    String msg = "应还利息格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String RmbYhbjzje = tempCurAssetsPackage.getRmbYhbjzje();
            if(RmbYhbjzje!=null && !"".equals(RmbYhbjzje)){
                if(!checkJE(RmbYhbjzje)) {
                    String msg = "应还本金格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String getRmbYhfyzje = tempCurAssetsPackage.getRmbYhfyzje();
            if(getRmbYhfyzje!=null && !"".equals(getRmbYhfyzje)){
                if(!checkJE(getRmbYhfyzje)) {
                    String msg = "应还费用格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String mqhkje = tempCurAssetsPackage.getMqhkje();//每期还款金额
            if(mqhkje!=null && !"".equals(mqhkje)){
                if(!checkJE(mqhkje)) {
                    String msg = "每期还款金额格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }
            String dqqkje = tempCurAssetsPackage.getDqqkje();//当期欠款金额
            if(dqqkje!=null && !"".equals(dqqkje)){
                if(!checkJE(dqqkje)) {
                    String msg = "当期欠款金额格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String ljyhje = tempCurAssetsPackage.getLjyhje();//累计已还金额
            if(ljyhje!=null && !"".equals(ljyhje)){
                if(!checkJE(ljyhje)) {
                    String msg = "累计已还金额格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String sfje = tempCurAssetsPackage.getSfje();//首付金额
            if(sfje!=null && !"".equals(sfje)){
                if(!checkJE(sfje)) {
                    String msg = "首付金额格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }
            String spjg = tempCurAssetsPackage.getSpjg();//商品价格
            if(spjg!=null && !"".equals(spjg)){
                if(!checkJE(spjg)) {
                    String msg = "商品价格格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String yqts = tempCurAssetsPackage.getOverdueDays();//逾期天数
            if(yqts!=null && !"".equals(yqts)){
                if(!checkZS(yqts)) {
                    String msg = "逾期天数格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }


        }
        return list;
    }



    /**
     * 转换
     *
     * @param datas
     * @return
     * @throws Exception
     */
    @SuppressWarnings("all")
    public static List<TempCurAssetsPackage> dataConvert(List<Map<String, String>> datas, String orgId,String importBatchNo,String orgName) throws Exception {
        List<TempCurAssetsPackage> list = new ArrayList<TempCurAssetsPackage>();
        String loginName = ShiroUtils.getLoginName();
        for (Map<String, String> data : datas) {
            TempCurAssetsPackage dto = new TempCurAssetsPackage();
            for (Map.Entry<String, String> entry : data.entrySet()) {
                if ("自由导入".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFreeImport(entry.getValue().replaceAll("'",""));
                        continue;
                    }
                }

                if ("机构案件号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setOrgCasno(entry.getValue().replaceAll("'",""));
                        continue;
                    }else{
                        throw new RuntimeException("案件号必须不能为空，请重新配置模板！");
                    }
                }
                if ("所属机构".equals(entry.getKey())) {
                    if (entry.getValue() != null) {
                        dto.setOrg(entry.getValue());//所属机构
                        continue;
                    }
                }
                if ("手别".equals(entry.getKey())) {
                    if (entry.getValue() != null) {
                        dto.setTransfertype(entry.getValue());//手别
                        continue;
                    }
                }
                if ("案件回收时间".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setAjhssj(entry.getValue());
                    }
                    continue;
                }


                if ("姓名".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCurName(entry.getValue());
                    }
                    continue;
                }
                if ("证件号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCertificateNo(entry.getValue().replaceAll("'",""));
                    }
                    continue;
                }
                if ("性别".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String sex = entry.getValue().replaceAll("'","");
                        if(sex.equals("m")){
                            sex="男";
                        }
                        if(sex.equals("z")){
                            sex="女";
                        }
                        dto.setCurSex(sex);
                    }
                    continue;
                }
                if ("婚姻状况".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setMarriage(entry.getValue());
                    }
                    continue;
                }
                if ("教育程度".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setEducation(entry.getValue());
                    }
                    continue;
                }
                if ("委案金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String rmbYe = entry.getValue().replaceAll(",","");
                        dto.setRmbYe(rmbYe);
                    }
                    continue;
                }
                if ("结案应还金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String waYe = entry.getValue().replaceAll(",","");
                        dto.setWaYe(waYe);
                    }
                    continue;
                }
                if ("入催日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRcr(entry.getValue());
                    }
                    continue;
                }
                if ("账单日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setAccountDate(entry.getValue());
                    }
                    continue;
                }
                if ("逾期天数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setOverdueDays(entry.getValue());
                    }
                    continue;
                }
                if ("逾期起始日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFirstYqDate(entry.getValue());
                    }
                    continue;
                }
                if ("应还日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFirstYqjcDate(entry.getValue());
                    }
                    continue;
                }
                if ("欠款总金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String rmbQkzje = entry.getValue().replaceAll(",","");
                        dto.setRmbQkzje(rmbQkzje);
                    }
                    continue;
                }
                if ("最低应还金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String rmbZdyhje = entry.getValue().replaceAll(",","");
                        dto.setRmbZdyhje(rmbZdyhje);
                    }
                    continue;
                }
                if ("应还本金".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String rmbYhbjzje = entry.getValue().replaceAll(",","");
                        dto.setRmbYhbjzje(rmbYhbjzje);
                    }
                    continue;
                }
                if ("应还利息".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String rmbYhlizje = entry.getValue().replaceAll(",","");
                        dto.setRmbYhlizje(rmbYhlizje);
                    }
                    continue;
                }
                if ("应还罚息".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String rmbYhfxzje = entry.getValue().replaceAll(",","");
                        dto.setRmbYhfxzje(rmbYhfxzje);
                    }
                    continue;
                }
                if ("应还费用".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String rmbYhfyzje = entry.getValue().replaceAll(",","");
                        dto.setRmbYhfyzje(rmbYhfyzje);
                    }
                    continue;
                }
                if ("滞纳金".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String znj = entry.getValue().replaceAll(",","");
                        dto.setZnj(znj);
                    }
                    continue;
                }
                if ("所属城市".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWwCityName(entry.getValue());
                    }
                    continue;
                }
                if ("所属区域".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setAreaCenter(entry.getValue());
                    }
                    continue;
                }
                if ("推荐商户".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setTjFirm(entry.getValue());
                    }
                    continue;
                }
                if ("推荐网点".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setTjWd(entry.getValue());
                    }
                    continue;
                }
                if ("产品名称".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCpmc(entry.getValue());
                    }
                    continue;
                }
                if ("还款方式".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setHkType(entry.getValue());
                    }
                    continue;
                }
                if ("放款金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String borrowEd = entry.getValue().replaceAll(",","");
                        dto.setBorrowEd(borrowEd);
                    }
                    continue;
                }
                if ("分期期数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFz(entry.getValue());
                    }
                    continue;
                }
                if ("年利率".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setYearRates(entry.getValue());
                    }
                    continue;
                }
                if ("日利率".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setDayRates(entry.getValue());
                    }
                    continue;
                }
                if ("借款卡号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setBorrowNo(entry.getValue().replaceAll("'",""));
                    }
                    continue;
                }
                if ("借款卡银行".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setBorrowBlank(entry.getValue());
                    }
                    continue;
                }
                if ("单位名称".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWorkName(entry.getValue());
                    }
                    continue;
                }
                if ("电子邮箱".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setEmail(entry.getValue());
                    }
                    continue;
                }
                if ("单位地址".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWorkAddress(entry.getValue());
                    }
                    continue;
                }
                if ("住宅地址".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCustomerHomeAddress(entry.getValue());
                    }
                    continue;
                }
                if ("户籍地址".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRegistAddress(entry.getValue());
                    }
                    continue;
                }
                if ("身份证地址".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCertificateAddress(entry.getValue());
                    }
                    continue;
                }
                if ("账单地址".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setBillAddress(entry.getValue());
                    }
                    continue;
                }
                if ("首逾标识".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFirstYqFlag(entry.getValue());
                    }
                    continue;
                }
                if ("最大逾期天数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setMaxYqtsHis(entry.getValue());
                    }
                    continue;
                }
                if ("累计逾期天数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSumYqtsHis(entry.getValue());
                    }
                    continue;
                }
                if ("逾期次数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSumYqcsHis(entry.getValue());
                    }
                    continue;
                }
                if ("移动电话".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCustomerMobile(entry.getValue());
                    }
                    continue;
                }
                if ("联系人1姓名".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFirstLiaisonName(entry.getValue());
                    }
                    continue;
                }
                if ("联系人1关系".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFirstLiaisonRelation(entry.getValue());
                    }
                    continue;
                }
                if ("联系人2姓名".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSecondLiaisonName(entry.getValue());
                    }
                    continue;
                }
                if ("联系人2关系".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSecondLiaisonRelation(entry.getValue());
                    }
                    continue;
                }
                if ("联系人3姓名".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setThreeLiaisonName(entry.getValue());
                    }
                    continue;
                }
                if ("联系人3关系".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setThreeLiaisonRelation(entry.getValue());
                    }
                    continue;
                }
                if ("住宅电话".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCustomerHomeTel(entry.getValue().replaceAll("'","").replaceAll(" ",""));
                    }
                    continue;
                }
                if ("联系人1电话1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFirstLiaisonMobile(entry.getValue());
                    }
                    continue;
                }
                if ("联系人1电话2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFirstLiaisonTel(entry.getValue());
                    }
                    continue;
                }
                if ("联系人2电话1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSecondLiaisonMobile(entry.getValue());
                    }
                    continue;
                }
                if ("联系人2电话2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSecondLiaisonTel(entry.getValue());
                    }
                    continue;
                }
                if ("联系人3电话1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setThreeLiaisonMobile(entry.getValue());
                    }
                    continue;
                }
                if ("联系人3电话2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setThreeLiaisonTel(entry.getValue());
                    }
                    continue;
                }
                if ("单位电话".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWorkTel(entry.getValue().replaceAll("'","").replaceAll(" ",""));
                    }
                    continue;
                }
                if ("账龄".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setAccountAge(entry.getValue());
                    }
                    continue;
                }
                if ("留案标签".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setLaFlag(entry.getValue());
                    }
                    continue;
                }
                if ("风险标签".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFxFlag(entry.getValue());
                    }
                    continue;
                }
                if ("合同类型".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setHtlx(entry.getValue());
                    }
                    continue;
                }
                if ("减免标签".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setJmbq(entry.getValue());
                    }
                    continue;
                }
                if ("法催标签".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFcbq(entry.getValue());
                    }
                    continue;
                }
                if ("罚息是否变化".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFxsfbh(entry.getValue());
                    }
                    continue;
                }
                if ("备注".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRemark(entry.getValue());
                    }
                    continue;
                }
                if ("退案日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setTar(entry.getValue());
                    }
                    continue;
                }
                if ("借款日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setJkrq(entry.getValue());
                    }
                    continue;
                }
                if ("最近一次还款日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setZhychkr(entry.getValue());
                    }
                    continue;
                }
                if ("每期还款金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String mqhkje = entry.getValue().replaceAll(",","");
                        dto.setMqhkje(mqhkje);
                    }
                    continue;
                }
                if ("当期欠款金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String dqqkje = entry.getValue().replaceAll(",","");
                        dto.setDqqkje(dqqkje);
                    }
                    continue;
                }
                if ("累计已还金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String ljyhje = entry.getValue().replaceAll(",","");
                        dto.setLjyhje(ljyhje);
                    }
                    continue;
                }
                if ("首付金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String sfje = entry.getValue().replaceAll(",","");
                        dto.setSfje(sfje);
                    }
                    continue;
                }
                if ("指定还款账号1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setZdhkzh1(entry.getValue().replaceAll("'",""));
                    }
                    continue;
                }
                if ("指定还款账号2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setZdhkzh2(entry.getValue().replaceAll("'",""));
                    }
                    continue;
                }
                if ("指定还款账户户名1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setZdhkzhhm1(entry.getValue());
                    }
                    continue;
                }
                if ("指定还款账户户名2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setZdhkzhhm2(entry.getValue());
                    }
                    continue;
                }
                if ("指定还款渠道1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setZdhkqd1(entry.getValue());
                    }
                    continue;
                }
                if ("指定还款渠道2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setZdhkqd2(entry.getValue());
                    }
                    continue;
                }
                if ("考核目标".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setKhmb(entry.getValue());
                    }
                    continue;
                }
                if ("商品价格".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSpjg(entry.getValue());
                    }
                    continue;
                }
                if ("贷款类型".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setDklx(entry.getValue());
                    }
                    continue;
                }
                if ("借款笔数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setJkbs(entry.getValue());
                    }
                    continue;
                }
                if ("商品信息".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSpxx(entry.getValue());
                    }
                    continue;
                }
                if ("委案次数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWacs(entry.getValue());
                    }
                    continue;
                }
                if ("已还期数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setYkqs(entry.getValue());
                    }
                    continue;
                }
                if ("工作部门".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWorkDept(entry.getValue());
                    }
                    continue;
                }
                if ("客户电话2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCustomerMobile2(entry.getValue());
                    }
                    continue;
                }
                if ("客户电话3".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCustomerMobile3(entry.getValue());
                    }
                    continue;
                }
                if ("客户电话4".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCustomerMobile4(entry.getValue());
                    }
                    continue;
                }
                if ("联系人4姓名".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFourthLiaisonName(entry.getValue());
                    }
                    continue;
                }
                if ("联系人4关系".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFourthLiaisonRelation(entry.getValue());
                    }
                    continue;
                }
                if ("联系人4电话".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFourthLiaisonMobile(entry.getValue());
                    }
                    continue;
                }
                if ("联系人5姓名".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFifthLiaisonName(entry.getValue());
                    }
                    continue;
                }
                if ("联系人5关系".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFifthLiaisonRelation(entry.getValue());
                    }
                    continue;
                }
                if ("联系人5电话".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFifthLiaisonMobile(entry.getValue());
                    }
                    continue;
                }
                if ("呆账核销日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setDzhxrq(entry.getValue());
                    }
                    continue;
                }
                if ("客户号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCustomerNo(entry.getValue());
                    }
                    continue;
                }
                if ("数据日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSjrq(entry.getValue());
                    }
                    continue;
                }
                if ("借据号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setJjh(entry.getValue());
                    }
                    continue;
                }



            }
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            dto.setId(id);
            dto.setOrgId(orgId);//委托方id
            dto.setCreateBy(loginName);  //导入人
            dto.setCreateTime(new Date());
            dto.setImportBatchNo(importBatchNo);

            dto.setOrg(orgName);
            dto.setCloseCase(IsCloseCaseEnum.NOT_CLOSE_CASE.getValue());
            dto.setIsExitCollect(IsNoEnum.NO.getCode().toString());
            dto.setPackageFlag(PackageFlagEnum.NOT_PACKAGE.getValue());//打包标识
            list.add(dto);
        }

        return list;
    }


/*
    public static List<CurAssetsPackage> dataConvert(List<Map<String, String>> datas, String orgId) throws Exception {
        List<CurAssetsPackage> list = new ArrayList<CurAssetsPackage>();
        String loginName = ShiroUtils.getLoginName();
        for (Map<String, String> data : datas) {
            CurAssetsPackage dto = new CurAssetsPackage();
            for (Map.Entry<String, String> entry : data.entrySet()) {
                if ("机构案件号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setOrgCasno(entry.getValue());//机构案件号
                        continue;
                    }
                }
                if ("所属机构".equals(entry.getKey())) {
                    if (entry.getValue() != null) {
                        dto.setOrg(entry.getValue());//所属机构
                        continue;
                    }
                }
                if ("手别".equals(entry.getKey())) {
                    if (entry.getValue() != null) {
                        dto.setTransfertype(entry.getValue());//手别
                        continue;
                    }
                }
                if ("24CD值".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setTwentyfourcd(entry.getValue());//24CD值
                        continue;
                    }
                }
                if ("BLK".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setBlk(entry.getValue());//BLK
                        continue;
                    }
                }
                if ("产品线".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setProductline(entry.getValue());//产品线
                        continue;
                    }
                }
                if ("人民币账户last缴款金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbLastJkje(new BigDecimal(entry.getValue()));//人民币账户last缴款金额
                        continue;
                    }
                }
                if ("委案金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbYe(new BigDecimal(entry.getValue()));//委案金额
                        continue;
                    }
                }
                if ("应还罚息".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbYhfxzje(new BigDecimal(entry.getValue()));//应还罚息
                        continue;
                    }
                }
                if ("人民币账户当前CD值".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbCd(new BigDecimal(entry.getValue()));//人民币账户当前CD值
                        continue;
                    }
                }
                if ("人民币账户最后一次缴款日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date rmbZhycjkr = getDateByFormat(entry.getValue());
                        dto.setRmbZhycjkr(rmbZhycjkr);
                        continue;
                    }
                }
                if ("人民币账户最后还款笔数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbZhhkbs(entry.getValue());//人民币账户最后还款笔数
                        continue;
                    }
                }
                if ("应还利息".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbYhlizje(new BigDecimal(entry.getValue()));//应还利息
                        continue;
                    }
                }
                if ("人民币账户应还本金1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbYhbj1(new BigDecimal(entry.getValue()));//人民币账户应还本金1
                        continue;
                    }
                }
                if ("人民币账户应还本金2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbYhbj2(new BigDecimal(entry.getValue()));//人民币账户应还本金2
                        continue;
                    }
                }
                if ("应还本金".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbYhbjzje(new BigDecimal(entry.getValue()));//应还本金
                        continue;
                    }
                }
                if ("人民币账户应还罚息1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbYhfx1(new BigDecimal(entry.getValue()));//人民币账户应还罚息1
                        continue;
                    }
                }
                if ("人民币账户应还罚息2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbYhfx2(new BigDecimal(entry.getValue()));//人民币账户应还罚息2
                        continue;
                    }
                }
                if ("人民币账户应还费用1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbYhfy1(new BigDecimal(entry.getValue()));//人民币账户应还费用1
                        continue;
                    }
                }
                if ("人民币账户应还费用2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbYhfy2(new BigDecimal(entry.getValue()));//人民币账户应还费用2
                        continue;
                    }
                }
                if ("应还费用".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbYhfyzje(new BigDecimal(entry.getValue()));//应还费用
                        continue;
                    }
                }
                if ("最低应还金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbZdyhje(new BigDecimal(entry.getValue()));//最低应还金额
                        continue;
                    }
                }
                if ("欠款总金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbQkzje(new BigDecimal(entry.getValue()));//欠款总金额
                        continue;
                    }
                }
                if ("人民币账户额度固定额度".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRmbGded(new BigDecimal(entry.getValue()));//人民币账户额度固定额度
                        continue;
                    }
                }
                if ("代码".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCode(entry.getValue());//代码
                        continue;
                    }
                }
                if ("借款卡号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setBorrowNo(entry.getValue());//借款卡号
                        continue;
                    }
                }
                if ("借款卡银行".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setBorrowBlank(entry.getValue());//借款卡银行
                        continue;
                    }
                }
                if ("放款金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setBorrowEd(new BigDecimal(entry.getValue()));//放款金额
                        continue;
                    }
                }
                if ("债项类型".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setZxType(entry.getValue());//债项类型
                        continue;
                    }
                }
                if ("停卡日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date stopCard = getDateByFormat(entry.getValue());
                        dto.setStopCard(stopCard);
                    }
                    continue;
                }
                if ("停止计息日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date stopJxrq = getDateByFormat(entry.getValue());
                        dto.setStopJxrq(stopJxrq);
                    }
                    continue;
                }
                if ("催收公司".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCsCompany(entry.getValue());//催收公司
                        continue;
                    }
                }
                if ("入催日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date rcr = getDateByFormat(entry.getValue());
                        dto.setRcr(rcr);
                    }
                    continue;
                }
                if ("共债标识".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setGzFlag(entry.getValue());//共债标识
                        continue;
                    }
                }
                if ("分期期数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFz(entry.getValue());//分期期数
                        continue;
                    }
                }
                if ("分期标识".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setFqFlag(entry.getValue());//分期标识
                        continue;
                    }
                }
                if ("剩余核销其他费用".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSyhxQtfy(new BigDecimal(entry.getValue()));//剩余核销其他费用
                        continue;
                    }
                }
                if ("剩余核销手续费".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSyhxSxf(new BigDecimal(entry.getValue()));//剩余核销手续费
                        continue;
                    }
                }
                if ("剩余核销本金".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSyhxBj(new BigDecimal(entry.getValue()));//剩余核销本金
                        continue;
                    }
                }
                if ("剩余核销滞纳费".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSyhxZnf(new BigDecimal(entry.getValue()));//剩余核销滞纳费
                        continue;
                    }
                }
                if ("剩余核销逾期息".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSyhxYqx(new BigDecimal(entry.getValue()));//剩余核销逾期息
                        continue;
                    }
                }
                if ("剩余核销金额合计".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSyhxJehj(new BigDecimal(entry.getValue()));//剩余核销金额合计
                        continue;
                    }
                }
                if ("所属区域".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setAreaCenter(entry.getValue());//所属区域
                        continue;
                    }
                }
                if ("历史催收备注".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCsRemarkHis(entry.getValue());//历史催收备注
                        continue;
                    }
                }
                if ("最大逾期天数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setMaxYqtsHis(entry.getValue());//最大逾期天数
                        continue;
                    }
                }
                if ("累计逾期天数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSumYqtsHis(entry.getValue());//累计逾期天数
                        continue;
                    }
                }
                if ("逾期次数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSumYqcsHis(entry.getValue());//逾期次数
                        continue;
                    }
                }
                if ("历史经历催收机构个数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setCsjggsHis(entry.getValue());//历史经历催收机构个数
                        continue;
                    }
                }
                if ("取现标识".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setQxFlag(entry.getValue());//取现标识
                        continue;
                    }
                }
                if ("受托公司".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setStCompany(entry.getValue());//受托公司
                        continue;
                    }
                }
                if ("受理商户".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setAcceptFirm(entry.getValue());//受理商户
                        continue;
                    }
                }
                if ("受理城市".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setAcceptCity(entry.getValue());//受理城市
                        continue;
                    }
                }
                if ("受理网点".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setAcceptWd(entry.getValue());//受理网点
                        continue;
                    }
                }
                if ("受理网点代码".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setAcceptWdCode(entry.getValue());//受理网点代码
                        continue;
                    }
                }
                if ("呆账核销日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date dzhxrq = getDateByFormat(entry.getValue());
                        dto.setDzhxrq(dzhxrq);
                    }
                    continue;
                }
                if ("备注".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setRemark(entry.getValue());//备注
                        continue;
                    }
                }
                if ("外币账户应还罚息总额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWbYhfxze(new BigDecimal(entry.getValue()));//外币账户应还罚息总额
                        continue;
                    }
                }
                if ("外币账户应还利息总额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWbYhlxze(new BigDecimal(entry.getValue()));//外币账户应还利息总额
                        continue;
                    }
                }
                if ("外币账户应还本金1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWbYhbj1(new BigDecimal(entry.getValue()));//外币账户应还本金1
                        continue;
                    }
                }
                if ("外币账户应还本金2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWbYhbj2(new BigDecimal(entry.getValue()));//外币账户应还本金2
                        continue;
                    }
                }
                if ("外币账户应还本金总额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWbYhbjzje(new BigDecimal(entry.getValue()));//外币账户应还本金总额
                        continue;
                    }
                }
                if ("外币账户应还罚息1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWbYhfx1(new BigDecimal(entry.getValue()));//外币账户应还罚息1
                        continue;
                    }
                }
                if ("外币账户应还罚息2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWbYhfx2(new BigDecimal(entry.getValue()));//外币账户应还罚息2
                        continue;
                    }
                }
                if ("外币账户应还费用1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWbYhfy1(new BigDecimal(entry.getValue()));//外币账户应还费用1
                        continue;
                    }
                }
                if ("外币账户应还费用2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWbYhfy2(new BigDecimal(entry.getValue()));//外币账户应还费用2
                        continue;
                    }
                }
                if ("外币账户应还费用总额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWbYhfyze(new BigDecimal(entry.getValue()));//外币账户应还费用总额
                        continue;
                    }
                }
                if ("外币账户最低应还额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWbZdyhe(new BigDecimal(entry.getValue()));//外币账户最低应还额
                        continue;
                    }
                }
                if ("外币账户欠款总金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWbQkzje(new BigDecimal(entry.getValue()));//外币账户欠款总金额
                        continue;
                    }
                }
                if ("套现标识".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setTxFlag(entry.getValue());//套现标识
                    }
                    continue;
                }
                if ("委外公司代码".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWwCompanyCode(entry.getValue());//委外公司代码
                        continue;
                    }
                }
                if ("所属城市".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWwCityName(entry.getValue());//所属城市
                        continue;
                    }
                }
                if ("委外计划截止日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date wwjhenddate = getDateByFormat(entry.getValue());
                        dto.setWwJhEnddate(wwjhenddate);
                    }
                    continue;
                }
                if ("委外起始日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date wwqsrq = getDateByFormat(entry.getValue());
                        dto.setWwQsrq(wwqsrq);
                    }
                    continue;
                }
                if ("委托批次".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWwPc(entry.getValue());//委托批次
                    }
                    continue;
                }
                if ("结案应还金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setWaYe(new BigDecimal(entry.getValue()));//结案应还金额
                        continue;
                    }
                }
                if ("账单地址".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setBillAddress(entry.getValue());//账单地址
                        continue;
                    }
                }
                if ("帐单地址邮编".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setBillAddressPostcode(entry.getValue());//帐单地址邮编
                        continue;
                    }
                }
                if ("年利率".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setYearRates(entry.getValue());//年利率
                        continue;
                    }
                }
                if ("应还款日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date yhkdate = getDateByFormat(entry.getValue());
                        dto.setYhkDate(yhkdate);
                    }
                    continue;
                }
                if ("开始逾期日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date yuDate = getDateByFormat(entry.getValue());
                        dto.setStartYqDate(yuDate);//开始逾期日期
                    }
                    continue;
                }
                if ("开户日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date khDate = getDateByFormat(entry.getValue());
                        dto.setKhDate(khDate);//开户日
                    }
                    continue;
                }
                if ("投诉标识".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setTsFlag(entry.getValue());//投诉标识
                    continue;
                }
                if ("授信额度".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setCreditValue(new BigDecimal(entry.getValue()));//授信额度
                    continue;
                }
                if ("推荐商户".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setTjFirm(entry.getValue());//推荐商户
                    continue;
                }
                if ("推荐城市".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setTjCity(entry.getValue());//推荐城市
                    continue;
                }
                if ("推荐网点".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setTjWd(entry.getValue());//推荐网点
                    continue;
                }
                if ("收回核销手续费".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setShhxSxf(new BigDecimal(entry.getValue()));//收回核销手续费
                    continue;
                }
                if ("收回核销本金".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setShhxBj(new BigDecimal(entry.getValue()));//收回核销本金
                    continue;
                }
                if ("收回核销滞纳费".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setShhxZnf(new BigDecimal(entry.getValue()));//收回核销滞纳费
                    continue;
                }
                if ("收回核销费用".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setShhxFy(new BigDecimal(entry.getValue()));//收回核销费用
                    continue;
                }
                if ("收回核销逾期息".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setShhxYqx(new BigDecimal(entry.getValue()));//收回核销逾期息
                    continue;
                }
                if ("收回核销金额合计".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setShhxJehj(new BigDecimal(entry.getValue()));//收回核销金额合计
                    continue;
                }
                if ("整合30DAY".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setZh30Day(entry.getValue());//整合30 DAY
                    continue;
                }
                if ("整合XDAY".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setZhXDay(entry.getValue());//整合X DAY
                    continue;
                }
                if ("是否仲裁".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setIsZc(entry.getValue());//是否仲裁
                    continue;
                }
                if ("是否债务重组".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setIsZwcz(entry.getValue());//是否债务重组
                    continue;
                }
                if ("是否计息".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setIsJx(entry.getValue());//是否计息
                    continue;
                }
                if ("是否诉讼".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setIsSx(entry.getValue());//是否诉讼
                    continue;
                }
                if ("最近逾期月份".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setZjyqMonth(format2.parse(entry.getValue()));//最近逾期月份
                    continue;
                }
                if ("核销手续费催收".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setHxSxfcs(entry.getValue());//核销手续费催收
                    continue;
                }
                if ("核销收回状态".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setHxShzt(entry.getValue());//核销收回状态
                    continue;
                }
                if ("核销本金催收".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setHxBjcs(entry.getValue());//核销本金催收
                    continue;
                }
                if ("核销滞纳费催收".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setHxZnfcs(entry.getValue());//核销滞纳费催收
                    continue;
                }
                if ("核销费用催收".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setHxFycs(entry.getValue());//核销费用催收
                    continue;
                }
                if ("核销逾期息催收".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setHxYqlxcs(entry.getValue());//核销逾期息催收
                    continue;
                }
                if ("核销金额合计催收".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setHxJehjcs(entry.getValue());//核销金额合计催收
                    continue;
                }
                if ("案件核准日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date ajhzDate = getDateByFormat(entry.getValue());
                        dto.setAjHzDate(ajhzDate);
                    }
                    continue;
                }
                if ("消费金融账号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setXfjzzh(entry.getValue());//消费金融账号
                    continue;
                }
                if ("特殊案件类型".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setTsajType(entry.getValue());//特殊案件类型
                    continue;
                }
                if ("日利率".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setDayRates(entry.getValue());//日利率
                    continue;
                }
                if ("统计日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date tjDate = getDateByFormat(entry.getValue());
                        dto.setTjDate(tjDate);
                    }
                    continue;
                }
                if ("美元余额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setDollarYe(new BigDecimal(entry.getValue()));//美元余额
                    continue;
                }
                if ("美元当前CD值".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setDollarCd(entry.getValue());//美元当前CD值
                    continue;
                }
                if ("美元最低应缴款金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setDollarZdyjkje(new BigDecimal(entry.getValue()));//美元最低应缴款金额
                    continue;
                }
                if ("美元最后一次缴款日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date dollarZhycjkr = getDateByFormat(entry.getValue());
                        dto.setDollarZhycjkr(dollarZhycjkr);
                    }
                    continue;
                }
                if ("美元最后一次缴款金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setDollarZhycjkje(new BigDecimal(entry.getValue()));//美元最后一次缴款金额
                    continue;
                }
                if ("美元还款日还款笔数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setDollarHkrhkbs(entry.getValue());//美元还款日还款笔数
                    continue;
                }
                if ("获客方式".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setHkfs(entry.getValue());//获客方式
                    continue;
                }
                if ("获客渠道".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setHkqd(entry.getValue());//获客渠道
                    continue;
                }
                if ("行方评分".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setXwpf(entry.getValue());//行方评分
                    continue;
                }
                if ("账单日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setAccountDate(entry.getValue());//账单日
                    continue;
                }
                if ("账户余额本金".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setAccountYebj(new BigDecimal(entry.getValue()));//账户余额本金
                    continue;
                }
                if ("账户结清日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date accountJqrq = getDateByFormat(entry.getValue());
                        dto.setAccountJqrq(accountJqrq);
                    }
                    continue;
                }
                if ("辅助组别".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setFzzb(entry.getValue());//辅助组别
                    continue;
                }
                if ("还款卡号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setRevertCardNo(entry.getValue());//还款卡号
                    continue;
                }
                if ("还款卡银行".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setRevertCardBlank(entry.getValue());//还款卡银行
                    continue;
                }
                if ("进件渠道".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setJjqd(entry.getValue());//进件渠道
                    continue;
                }
                if ("逾期天数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setOverdueDays(entry.getValue());//逾期天数
                    continue;
                }
                if ("逾期标记".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setOverdueFlag(entry.getValue());//逾期标记
                    continue;
                }
                if ("长委次数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setCwcs(entry.getValue());//长委次数
                    continue;
                }
                if ("额度产品".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setQuotaProduct(entry.getValue());//额度产品
                    continue;
                }
                if ("额度代码".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setQuotaCode(entry.getValue());//额度代码
                    continue;
                }
                if ("额度激活日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date quotaDate = getDateByFormat(entry.getValue());
                        dto.setQuotaDate(quotaDate);
                    }
                    continue;
                }
                if ("风险".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setRisk(entry.getValue());//风险
                    continue;
                }
                if ("首次放款日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date firstFkDate = getDateByFormat(entry.getValue());
                        dto.setFirstFkDate(firstFkDate);
                    }
                    continue;
                }
                if ("逾期起始日".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date firstYqDate = getDateByFormat(entry.getValue());
                        dto.setFirstYqDate(firstYqDate);
                    }
                    continue;
                }
                if ("应还日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date firstYqJcDate = getDateByFormat(entry.getValue());
                        dto.setFirstYqjcDate(firstYqJcDate);
                    }
                    continue;
                }
                if ("首逾标识".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setFirstYqFlag(entry.getValue());//首逾标识
                    continue;
                }
                if ("姓名".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setCurName(entry.getValue());//姓名
                    continue;
                }
                if ("性别".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setCurSex(entry.getValue());//性别
                    continue;
                }
                if ("证件号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setCertificateNo(entry.getValue());//证件号
                    continue;
                }
                if ("证件类型".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setCertificateType(entry.getValue());//证件类型
                    continue;
                }
                if ("身份证地址".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setCertificateAddress(entry.getValue());//身份证地址
                    continue;
                }
                if ("出生日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date birthday = getDateByFormat(entry.getValue());
                        dto.setBirthday(birthday);
                    }
                    continue;
                }
                if ("户籍地址".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setRegistAddress(entry.getValue());//户籍地址
                    continue;
                }
                if ("电子邮箱".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setEmail(entry.getValue());//电子邮箱
                    continue;
                }
                if ("城市".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setCity(entry.getValue());//城市
                    continue;
                }
                if ("婚姻状况".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setMarriage(entry.getValue());//婚姻状况
                    continue;
                }
                if ("教育程度".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setEducation(entry.getValue());//教育程度
                    continue;
                }
                if ("经济类型".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setEconomy(entry.getValue());//经济类型
                    continue;
                }
                if ("当前收入".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setCurIncome(new BigDecimal(entry.getValue()));//当前收入
                    continue;
                }
                if ("新增地址".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setNewAddress(entry.getValue());//新增地址
                    continue;
                }
                if ("客户号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setCustomerNo(entry.getValue());//客户号
                    continue;
                }
                if ("移动电话".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setCustomerMobile(entry.getValue());//移动电话
                    continue;
                }
                if ("住宅电话".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setCustomerHomeTel(entry.getValue());//住宅电话
                    continue;
                }
                if ("住宅地址".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setCustomerHomeAddress(entry.getValue());//住宅地址
                    continue;
                }
                if ("客户家庭地址邮编".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setCustomerHomePostcode(entry.getValue());//客户家庭地址邮编
                    continue;
                }
                if ("卡片寄送地址".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setCardPostAddress(entry.getValue());//卡片寄送地址
                    continue;
                }
                if ("职务".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setJob(entry.getValue());//职务
                    continue;
                }
                if ("部门名称".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setDeptName(entry.getValue());//部门名称
                    continue;
                }
                if ("行业性质".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setIndust(entry.getValue());//行业性质
                    continue;
                }
                if ("单位名称".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setWorkName(entry.getValue());//单位名称
                    continue;
                }
                if ("单位地址".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setWorkAddress(entry.getValue());//单位地址
                    continue;
                }
                if ("单位电话".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setWorkTel(entry.getValue());//单位电话
                    continue;
                }
                if ("单位邮编".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setWorkPostcode(entry.getValue());//单位邮编
                    continue;
                }
                if ("联系人1姓名".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setFirstLiaisonName(entry.getValue());//联系人1姓名
                    continue;
                }
                if ("联系人1关系".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setFirstLiaisonRelation(entry.getValue());//联系人1关系
                    continue;
                }
                if ("联系人1电话1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setFirstLiaisonMobile(entry.getValue());//联系人1电话1
                    continue;
                }
                if ("联系人1电话2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setFirstLiaisonTel(entry.getValue());//联系人1电话2
                    continue;
                }
                if ("联系人2姓名".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setSecondLiaisonName(entry.getValue());//联系人2姓名
                    continue;
                }
                if ("联系人2关系".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setSecondLiaisonRelation(entry.getValue());//联系人2关系
                    continue;
                }
                if ("联系人2电话1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setSecondLiaisonMobile(entry.getValue());//联系人2电话1
                    continue;
                }
                if ("联系人2电话2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setSecondLiaisonTel(entry.getValue());//联系人2电话2
                    continue;
                }
                if ("联系人3姓名".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setThreeLiaisonName(entry.getValue());//联系人3姓名
                    continue;
                }
                if ("联系人3关系".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setThreeLiaisonRelation(entry.getValue());//联系人3关系
                    continue;
                }
                if ("联系人3电话1".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setThreeLiaisonMobile(entry.getValue());//联系人3电话1
                    continue;
                }
                if ("联系人3电话2".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setThreeLiaisonTel(entry.getValue());//联系人3电话2
                    continue;
                }

                if ("数据日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date dataDate = getDateByFormat(entry.getValue());
                        dto.setSjrq(dataDate);
                    }
                    continue;
                }
                if ("合同号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setHth(entry.getValue());//合同号
                    continue;
                }
                if ("借据号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setJjh(entry.getValue());//借据号
                    continue;
                }
                if ("地区事业部(一级)".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setDqsybYj(entry.getValue());//地区事业部(一级)
                    continue;
                }
                if ("地区事业部(二级)".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setDqsybEj(entry.getValue());//地区事业部(二级)
                    continue;
                }
                if ("催收节点".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setCsjd(entry.getValue());//催收节点
                    continue;
                }
                if ("外包经办".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setWbjb(entry.getValue());//外包经办
                    continue;
                }
                if ("原始客户经理名称".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setYsKhjlmc(entry.getValue());//原始客户经理名称
                    continue;
                }
                if ("客户经理名称".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setKhjlmc(entry.getValue());//客户经理名称
                    continue;
                }
                if ("产品名称".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setCpmc(entry.getValue());//产品名称
                    continue;
                }
                if ("新分类对应产品名称".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setXfldycpmc(entry.getValue());//新分类对应产品名称
                    continue;
                }
                if ("起息日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date qxrq = getDateByFormat(entry.getValue());
                        dto.setQxrq(qxrq);
                    }
                    continue;
                }
                if ("到期日期".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date dqrq = getDateByFormat(entry.getValue());
                        dto.setDqrq(dqrq);
                    }
                    continue;
                }
                if ("还款方式".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setHkType(entry.getValue());//还款方式
                    continue;
                }
                if ("贷款余额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setDkye(new BigDecimal(entry.getValue()));//贷款余额
                    continue;
                }
                if ("外包标的".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setWbbs(entry.getValue());//外包标的
                    continue;
                }
                if ("逾期金额".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setYqje(new BigDecimal(entry.getValue()));//逾期金额
                    continue;
                }
                if ("逾期本金".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setYqbj(new BigDecimal(entry.getValue()));//逾期本金
                    continue;
                }
                if ("逾期利息".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setYqlx(new BigDecimal(entry.getValue()));//逾期利息
                    continue;
                }
                if ("逾期手续费".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setYqsxf(new BigDecimal(entry.getValue()));//逾期手续费
                    continue;
                }
                if ("滞纳金".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue()))
                        dto.setZnj(new BigDecimal(entry.getValue()));//滞纳金
                    continue;
                }
                if ("渠道名称".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setQdmc(entry.getValue());//渠道名称
                    continue;
                }
                if ("外包手别".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setWbsb(entry.getValue());//外包手别
                    continue;
                }
                if ("外包期数".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) dto.setWbqs(entry.getValue());//外包期数
                    continue;
                }
                if ("分配时间".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date fpsj = getDateByFormat(entry.getValue());
                        dto.setFpsj(fpsj);
                    }
                    continue;
                }
                if ("案件回收时间".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date ajhssj = getDateByFormat(entry.getValue());
                        dto.setAjhssj(ajhssj);
                    }
                    continue;
                }


            }
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            dto.setId(id);
            dto.setOrgId(orgId);//委托方id
            dto.setCreateBy(loginName);  //导入人
            dto.setCreateTime(new Date());
            list.add(dto);
        }

        return list;
    }*/

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

    public static boolean checkZS(String value){
        Matcher mer = Pattern.compile("^[0-9]+$").matcher(value);
        return mer.find();
    }


    public static List<CurAssetsPackage> contactsDistinct(List<CurAssetsPackage> paramList){
        for (CurAssetsPackage curAsset : paramList) {
            String customerMobile = curAsset.getCustomerMobile();
            String firstLiaisonMobile = curAsset.getFirstLiaisonMobile();
            String secondLiaisonMobile = curAsset.getSecondLiaisonMobile();
            String threeLiaisonMobile = curAsset.getThreeLiaisonMobile();
            String fourthLiaisonMobile = curAsset.getFourthLiaisonMobile();
            String fifthLiaisonMobile = curAsset.getFifthLiaisonMobile();
            HashMap<Object, Object> map = new HashMap<>();
            map.put(customerMobile,1);
            if (!map.containsKey(firstLiaisonMobile)) {
                map.put(firstLiaisonMobile,1);
            } else {
                curAsset.setFirstLiaisonMobile(null);
            }
            if (!map.containsKey(secondLiaisonMobile)) {
                map.put(secondLiaisonMobile,1);
            } else {
                curAsset.setSecondLiaisonMobile(null);
            }
            if (!map.containsKey(threeLiaisonMobile)) {
                map.put(threeLiaisonMobile,1);
            } else {
                curAsset.setThreeLiaisonMobile(null);
            }
            if (!map.containsKey(fourthLiaisonMobile)) {
                map.put(fourthLiaisonMobile,1);
            } else {
                curAsset.setFourthLiaisonMobile(null);
            }
            if (!map.containsKey(fifthLiaisonMobile)) {
                map.put(fifthLiaisonMobile,1);
            } else {
                curAsset.setFifthLiaisonMobile(null);
            }
        }
        return paramList;
    }


}
