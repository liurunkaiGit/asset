package com.ruoyi.assetspackage.util;

import com.ruoyi.assetspackage.domain.CurAssetsRepaymentPackage;
import com.ruoyi.assetspackage.domain.RepaymentImportDataMapping;
import com.ruoyi.assetspackage.domain.TempCurAssetsPackage;
import com.ruoyi.assetspackage.domain.TempCurAssetsRepaymentPackage;
import com.ruoyi.assetspackage.enums.IsCloseCaseEnum;
import com.ruoyi.assetspackage.enums.IsNoEnum;
import com.ruoyi.framework.util.ShiroUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guozeqi
 * @create 2019-12-27
 */

public class RepaymentDataImportUtil {

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
    public static List<Map<String,String>> dataReplace (List<Map<String,String>> list,RepaymentImportDataMapping bean){
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        for (Map<String, String> rowMap : list) {
            Map<String, String> map = new HashMap<>();//转换后每一行的k,v
            for(Map.Entry<String, String> colum :rowMap.entrySet()){
                String key = colum.getKey().trim().replace(" ","");//导入的key
                String value = colum.getValue();//导入的value

                String orgCasNo = bean.getOrgCasNo();//机构案件号的配置值
                if(key.equals(orgCasNo)){
                    map.put("机构案件号",value);
//                    continue;
                }
                String jyqtfy = bean.getJyqtfy();//交易其他费用
                if(key.equals(jyqtfy)){
                    map.put("交易其他费用",value);
//                    continue;
                }
                String jylx = bean.getJylx();//交易利息
                if(key.equals(jylx)){
                    map.put("交易利息",value);
//                    continue;
                }
                String jybj = bean.getJybj();//交易本金
                if(key.equals(jybj)){
                    map.put("交易本金",value);
//                    continue;
                }
                String jyznf = bean.getJyznf();//交易滞纳费
                if(key.equals(jyznf)){
                    map.put("交易滞纳费",value);
//                    continue;
                }
                String jyType = bean.getJyType();//交易类型
                if(key.equals(jyType)){
                    map.put("交易类型",value);
//                    continue;
                }
                String jyje = bean.getJyje();//交易金额
                if(key.equals(jyje)){
                    map.put("交易金额",value);
//                    continue;
                }
                String productType = bean.getProductType();//产品类型
                if(key.equals(productType)){
                    map.put("产品类型",value);
//                    continue;
                }
                String jjh = bean.getJjh();//借据号
                if(key.equals(jjh)){
                    map.put("借据号",value);
//                    continue;
                }
                String csr = bean.getCsr();//催收人
                if(key.equals(csr)){
                    map.put("催收人",value);
//                    continue;
                }
                String csjd = bean.getCsjd();//催收节点
                if(key.equals(csjd)){
                    map.put("催收节点",value);
//                    continue;
                }
                String fprq = bean.getFprq();//分配日期
                if(key.equals(fprq)){
                    map.put("分配日期",value);
//                    continue;
                }
                String areaCenter = bean.getAreaCenter();//区域中心
                if(key.equals(areaCenter)){
                    map.put("区域中心",value);
//                    continue;
                }
                String acceptCity = bean.getAcceptCity();//受理城市
                if(key.equals(acceptCity)){
                    map.put("受理城市",value);
//                    continue;
                }
                String hth = bean.getHth();//合同号
                if(key.equals(hth)){
                    map.put("合同号",value);
//                    continue;
                }
                String dqsybYj = bean.getDqsybYj();//地区事业部(一级)
                if(key.equals(dqsybYj)){
                    map.put("地区事业部(一级)",value);
//                    continue;
                }
                String dqsybEj = bean.getDqsybEj();//地区事业部(二级)
                if(key.equals(dqsybEj)){
                    map.put("地区事业部(二级)",value);
//                    continue;
                }
                String wbqs = bean.getWbqs();//外包期数
                if(key.equals(wbqs)){
                    map.put("外包期数",value);
//                    continue;
                }
                String wbjb = bean.getWbjb();//外包经办
                if(key.equals(wbjb)){
                    map.put("外包经办",value);
//                    continue;
                }
                String warq = bean.getWarq();//委案日期
                if(key.equals(warq)){
                    map.put("委案日期",value);
//                    continue;
                }
                String curName = bean.getCurName();//客户名称
                if(key.equals(curName)){
                    map.put("客户名称",value);
//                    continue;
                }
                String khjlxm = bean.getKhjlxm();//客户经理姓名
                if(key.equals(khjlxm)){
                    map.put("客户经理姓名",value);
//                    continue;
                }
                String sjrq = bean.getSjrq();//数据日期
                if(key.equals(sjrq)){
                    map.put("数据日期",value);
//                    continue;
                }
                String sfwbcs = bean.getSfwbcs();//是否外包催收
                if(key.equals(sfwbcs)){
                    map.put("是否外包催收",value);
//                    continue;
                }
                String sfjq = bean.getSfjq();//是否出催
                if(key.equals(sfjq)){
                    map.put("是否出催",value);
//                    continue;
                }
                String bywa = bean.getBywa();//本月委案
                if(key.equals(bywa)){
                    map.put("本月委案",value);
//                    continue;
                }
                String ajhsrq = bean.getAjhsrq();//出催日期
                if(key.equals(ajhsrq)){
                    map.put("出催日期",value);
//                    continue;
                }
                String xfjrzh = bean.getXfjrzh();//消费金融账号
                if(key.equals(xfjrzh)){
                    map.put("消费金融账号",value);
//                    continue;
                }
                String tzsx = bean.getTzsx();//调整事项
                if(key.equals(tzsx)){
                    map.put("调整事项",value);
//                    continue;
                }
                String tzje = bean.getTzje();//调整金额
                if(key.equals(tzje)){
                    map.put("调整金额",value);
//                    continue;
                }
                String zhzt = bean.getZhzt();//账户状态
                if(key.equals(zhzt)){
                    map.put("账户状态",value);
//                    continue;
                }
                String hkrq = bean.getHkrq();//还款日期
                if(key.equals(hkrq)){
                    map.put("还款日期",value);
//                    continue;
                }
                String hksyqqs = bean.getHksyqqs();//还款时逾期期数
                if(key.equals(hksyqqs)){
                    map.put("还款时逾期期数",value);
//                    continue;
                }
                String hkje = bean.getHkje();//还款金额
                if(key.equals(hkje)){
                    map.put("还款金额",value);
//                    continue;
                }
                String yqcplx = bean.getYqcplx();//逾期产品类型
                if(key.equals(yqcplx)){
                    map.put("逾期产品类型",value);
//                    continue;
                }
                String yqjd = bean.getYqjd();//逾期阶段
                if(key.equals(yqjd)){
                    map.put("逾期阶段",value);
//                    continue;
                }
                String quotaProduct = bean.getQuotaProduct();//额度产品
                if(key.equals(quotaProduct)){
                    map.put("额度产品",value);
//                    continue;
                }



            }

            result.add(map);
        }

        return result;
    }



    /**
     * 参数校验
     * @param params
     * @return
     */
    @SuppressWarnings("all")
    public static List<String> checkData(List<Map<String, String>> datas,RepaymentImportDataMapping bean)throws Exception{
        String key = "message";//数据导入失败：请检查第1行哪个字段的内容或格式是否正确
        List<String> list = new ArrayList<String>();
        int dataRowNum = Integer.valueOf(bean.getDataRowNum());

        for (int i=dataRowNum;i<datas.size()+dataRowNum;i++) {
            Map<String, String> data = datas.get(i-dataRowNum);
            for (Map.Entry<String, String> entry : data.entrySet()) {
                if("交易其他费用".equals(entry.getKey())){
                    if(entry.getValue()!=null && !"".equals(entry.getValue().trim())){
                        if(!checkJE(entry.getValue())){
                            list.add("数据导入失败，请检查第"+i+"行"+"交易其他费用"+"的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if("交易利息".equals(entry.getKey())){
                    if(entry.getValue()!=null && !"".equals(entry.getValue().trim())){
                        if(!checkJE(entry.getValue())){
                            list.add("数据导入失败，请检查第"+i+"行"+"交易利息"+"的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if("交易本金".equals(entry.getKey())){
                    if(entry.getValue()!=null && !"".equals(entry.getValue().trim())){
                        if(!checkJE(entry.getValue())){
                            list.add("数据导入失败，请检查第"+i+"行"+"交易本金"+"的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if("交易滞纳费".equals(entry.getKey())){
                    if(entry.getValue()!=null && !"".equals(entry.getValue().trim())){
                        if(!checkJE(entry.getValue())){
                            list.add("数据导入失败，请检查第"+i+"行"+"交易滞纳费"+"的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if("交易金额".equals(entry.getKey())){
                    if(entry.getValue()!=null && !"".equals(entry.getValue().trim())){
                        if(!checkJE(entry.getValue())){
                            list.add("数据导入失败，请检查第"+i+"行"+"交易金额"+"的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if("调整金额".equals(entry.getKey())){
                    if(entry.getValue()!=null && !"".equals(entry.getValue().trim())){
                        if(!checkJE(entry.getValue())){
                            list.add("数据导入失败，请检查第"+i+"行"+"调整金额"+"的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if("还款金额".equals(entry.getKey())){
                    if(entry.getValue()!=null && !"".equals(entry.getValue().trim())){
                        if(!checkJE(entry.getValue())){
                            list.add("数据导入失败，请检查第"+i+"行"+"还款金额"+"的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                //////////////////////
                if("分配日期".equals(entry.getKey())){
                    if(entry.getValue()!=null && !"".equals(entry.getValue().trim())){
                        if(!checkRQ(entry.getValue())){
                            list.add("数据导入失败，请检查第"+i+"行"+"分配日期"+"的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if("委案日期".equals(entry.getKey())){
                    if(entry.getValue()!=null && !"".equals(entry.getValue().trim())){
                        if(!checkRQ(entry.getValue())){
                            list.add("数据导入失败，请检查第"+i+"行"+"委案日期"+"的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if("数据日期".equals(entry.getKey())){
                    if(entry.getValue()!=null && !"".equals(entry.getValue().trim())){
                        if(!checkRQ(entry.getValue())){
                            list.add("数据导入失败，请检查第"+i+"行"+"数据日期"+"的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if("出催日期".equals(entry.getKey())){
                    if(entry.getValue()!=null && !"".equals(entry.getValue().trim())){
                        if(!checkRQ(entry.getValue())){
                            list.add("数据导入失败，请检查第"+i+"行"+"出催日期"+"的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                if("还款日期".equals(entry.getKey())){
                    if(entry.getValue()!=null && !"".equals(entry.getValue().trim())){
                        if(!checkRQ(entry.getValue())){
                            list.add("数据导入失败，请检查第"+i+"行"+"还款日期"+"的内容或格式是否正确");
                            //continue;
                        }
                    }
                }
                /////////////////////////////////
                /*if("消费金融账号".equals(entry.getKey())){
                    if(entry.getValue()!=null && !"".equals(entry.getValue().trim())){
                        if(!checkSZ(entry.getValue())){
                            list.add("数据导入失败，请检查第"+i+"行"+"消费金融账号"+"的内容或格式是否正确");
                            //continue;
                        }
                    }
                }*/


            }

        }
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }

    public static List<Map<String, String>> checkData2(List<TempCurAssetsRepaymentPackage> dataList) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (TempCurAssetsRepaymentPackage tempAssetRepayment : dataList) {
            Map<String, String> map = new HashMap<String, String>();
            String orgCasno = tempAssetRepayment.getOrgCasno();
            String id = tempAssetRepayment.getId();

            String sfjq = tempAssetRepayment.getSfjq();
            if(sfjq !=null && !"".equals(sfjq)){
                String ajhsrq = tempAssetRepayment.getAjhsrq();
                if(ajhsrq==null || "".equals(ajhsrq)){
                    String msg = "出催日期为空";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }else{
                    boolean flag = false;
                    if(checkRQ3(ajhsrq,"yyyy-MM-dd")){
                        flag=true;
                    }
                    if(checkRQ3(ajhsrq,"yyyy/MM/dd")){
                        flag=true;
                    }
                    if(flag==false) {
                        String msg = "出催日期的格式不正确";
                        map.put("orgCasno", orgCasno);
                        map.put("msg", msg);
                        map.put("id", id);
                        list.add(map);
                        continue;
                    }
                }
            }

            String hkje = tempAssetRepayment.getHkje();
            if(hkje==null || "".equals(hkje)){
                String msg = "还款金额为空";
                map.put("orgCasno", orgCasno);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }else{
                if(!checkJE(hkje)) {
                    String msg = "还款金额的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
                String hkrq = tempAssetRepayment.getHkrq();
                if(hkrq==null || "".equals(hkrq)){
                    String msg = "还款日期为空";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }else {
                    boolean flag = false;
                    if(checkRQ3(hkrq,"yyyy-MM-dd")){
                        flag=true;
                    }
                    if(checkRQ3(hkrq,"yyyy/MM/dd")){
                        flag=true;
                    }
                    if(flag==false) {
                        String msg = "还款日期的格式不正确";
                        map.put("orgCasno", orgCasno);
                        map.put("msg", msg);
                        map.put("id", id);
                        list.add(map);
                        continue;
                    }
                }
            }

            String jyqtfy = tempAssetRepayment.getJyqtfy();
            if(jyqtfy!=null && !"".equals(jyqtfy)){
                if(!checkJE(jyqtfy)) {
                    String msg = "交易其他费用的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String jylx = tempAssetRepayment.getJylx();
            if(jylx!=null && !"".equals(jylx)){
                if(!checkJE(jylx)) {
                    String msg = "交易利息的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String jybj = tempAssetRepayment.getJybj();
            if(jybj!=null && !"".equals(jybj)){
                if(!checkJE(jylx)) {
                    String msg = "交易本金的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String jyznf = tempAssetRepayment.getJyznf();
            if(jyznf!=null && !"".equals(jyznf)){
                if(!checkJE(jyznf)) {
                    String msg = "交易滞纳费的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String jyje = tempAssetRepayment.getJyje();
            if(jyje!=null && !"".equals(jyje)){
                if(!checkJE(jyje)) {
                    String msg = "交易金额的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }
            String tzje = tempAssetRepayment.getTzje();
            if(tzje!=null && !"".equals(tzje)){
                if(!checkJE(tzje)) {
                    String msg = "调整金额的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }
        ////////////////////////////////////////////////
            String fprq = tempAssetRepayment.getFprq();
            if(fprq!=null && !"".equals(fprq)){
                boolean flag = false;
                if(checkRQ3(fprq,"yyyy-MM-dd")){
                    flag=true;
                }
                if(checkRQ3(fprq,"yyyy/MM/dd")){
                    flag=true;
                }
                if(flag==false) {
                    String msg = "分配日期的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String warq = tempAssetRepayment.getWarq();
            if(warq!=null && !"".equals(warq)){
                boolean flag = false;
                if(checkRQ3(warq,"yyyy-MM-dd")){
                    flag=true;
                }
                if(checkRQ3(warq,"yyyy/MM/dd")){
                    flag=true;
                }
                if(flag==false) {
                    String msg = "委案日期的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }

            String sjrq = tempAssetRepayment.getSjrq();
            if(sjrq!=null && !"".equals(sjrq)){
                boolean flag = false;
                if(checkRQ3(sjrq,"yyyy-MM-dd")){
                    flag=true;
                }
                if(checkRQ3(sjrq,"yyyy/MM/dd")){
                    flag=true;
                }
                if(flag==false) {
                    String msg = "数据日期的格式不正确";
                    map.put("orgCasno", orgCasno);
                    map.put("msg", msg);
                    map.put("id", id);
                    list.add(map);
                    continue;
                }
            }
    //////////////////////////////////////////////////////
           /* String curName = tempAssetRepayment.getCurName();
            if(curName==null || "".equals(curName)){
                String msg = "客户名称为空";
                map.put("orgCasno", orgCasno);
                map.put("msg", msg);
                map.put("id", id);
                list.add(map);
                continue;
            }*/

        }
        return list;
    }



    /**
     * 转换为参数实体
     * @param datas
     * @return
     * @throws Exception
     */
    @SuppressWarnings("all")
    public static List<CurAssetsRepaymentPackage> dataConvert(List<Map<String, String>> datas, String orgId) throws Exception{
        List<CurAssetsRepaymentPackage> list = new ArrayList<CurAssetsRepaymentPackage>();
        String loginName = ShiroUtils.getLoginName();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, String> data : datas) {
            CurAssetsRepaymentPackage dto = new CurAssetsRepaymentPackage();
            for (Map.Entry<String, String> entry : data.entrySet()) {
                if("机构案件号".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setOrgCasno(entry.getValue());//机构案件号
                    continue;
                }
                if("交易其他费用".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setJyqtfy(new BigDecimal(entry.getValue()));//交易其他费用
                    continue;
                }
                if("交易利息".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setJylx(new BigDecimal(entry.getValue()));//交易利息
                    continue;
                }
                if("交易本金".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setJybj(new BigDecimal(entry.getValue()));//交易本金
                    continue;
                }
                if("交易滞纳费".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setJyznf(new BigDecimal(entry.getValue()));//交易滞纳费
                    continue;
                }
                if("交易类型".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setJyType(entry.getValue());//交易类型
                    continue;
                }
                if("交易金额".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setJyje(new BigDecimal(entry.getValue()));//交易金额
                    continue;
                }
                if("产品类型".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setProductType(entry.getValue());//产品类型
                    continue;
                }
                if("借据号".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setJjh(entry.getValue());//借据号
                    continue;
                }
                if("催收人".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setCsr(entry.getValue());//催收人
                    continue;
                }
                if("催收节点".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setCsjd(entry.getValue());//催收节点
                    continue;
                }
                if("分配日期".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date fprq = getDateByFormat(entry.getValue());
                        dto.setFprq(fprq);//分配日期
                        continue;
                    }
                }
                if("区域中心".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setAreaCenter(entry.getValue());//区域中心
                    continue;
                }
                if("受理城市".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setAcceptCity(entry.getValue());//受理城市
                    continue;
                }
                if("合同号".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setHth(entry.getValue());//合同号
                    continue;
                }
                if("地区事业部(一级)".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setDqsybYj(entry.getValue());//地区事业部(一级)
                    continue;
                }
                if("地区事业部(二级)".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setDqsybEj(entry.getValue());//地区事业部(二级)
                    continue;
                }
                if("外包期数".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setWbqs(entry.getValue());//外包期数
                    continue;
                }
                if("外包经办".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setWbjb(entry.getValue());//外包经办
                    continue;
                }
                if("委案日期".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date warq = getDateByFormat(entry.getValue());
                        dto.setWarq(warq);//委案日期
                        continue;
                    }
                }
                if("客户名称".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setCurName(entry.getValue());//客户名称
                    continue;
                }
                if("客户经理姓名".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setKhjlxm(entry.getValue());//客户经理姓名
                    continue;
                }
                if("数据日期".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date sjrq = getDateByFormat(entry.getValue());
                        dto.setSjrq(sjrq);//数据日期
                        continue;
                    }
                }
                if("是否外包催收".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setSfwbcs(entry.getValue());//是否外包催收
                    continue;
                }
                if("是否出催".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSfjq(entry.getValue());//是否出催
                        dto.setIsExitCollect(String.valueOf(IsNoEnum.getCodeByDes(entry.getValue())));
                    } else {
                        dto.setIsExitCollect(IsNoEnum.NO.getCode().toString());
                    }
                    continue;
                }
                if("本月委案".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setBywa(entry.getValue());//本月委案
                    continue;
                }
                if("出催日期".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date ccrq = getDateByFormat(entry.getValue());
                        dto.setAjhsrq(ccrq);//出催日期
                        continue;
                    }
                }
                if("消费金融账号".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setXfjrzh(entry.getValue());//消费金融账号
                    continue;
                }
                if("调整事项".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setTzsx(entry.getValue());//调整事项
                    continue;
                }
                if("调整金额".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setTzje(new BigDecimal(entry.getValue()));//调整金额
                    continue;
                }
                if("账户状态".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setZhzt(entry.getValue());//账户状态
                    continue;
                }
                if("还款日期".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())) {
                        Date hkrq = getDateByFormat(entry.getValue());
                        dto.setHkrq(hkrq);//还款日期
                        continue;
                    }
                }
                if("还款时逾期期数".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setHksyqqs(entry.getValue());//还款时逾期期数
                    continue;
                }
                if("还款金额".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setHkje(new BigDecimal(entry.getValue()));//还款金额
                    continue;
                }
                if("逾期产品类型".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setYqcplx(entry.getValue());//逾期产品类型
                    continue;
                }
                if("逾期阶段".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setYqjd(entry.getValue());//逾期阶段
                    continue;
                }
                if("额度产品".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setQuotaProduct(entry.getValue());//额度产品
                    continue;
                }
                if("是否出催".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setIsExitCollect(entry.getValue());//是否出催
                    continue;
                }
            }
            String id = UUID.randomUUID().toString().replaceAll("-","");
            dto.setId(id);
            dto.setOrgId(orgId);//委托方id
            dto.setCreateBy(loginName);  //导入人
            dto.setCreateTime(new Date());
            list.add(dto);
        }

        return list;
    }

    public static List<TempCurAssetsRepaymentPackage> dataConvert2(List<Map<String, String>> datas, String orgId,String importBatchNo,String orgName) throws Exception{
        List<TempCurAssetsRepaymentPackage> list = new ArrayList<TempCurAssetsRepaymentPackage>();
        String loginName = ShiroUtils.getLoginName();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, String> data : datas) {
            TempCurAssetsRepaymentPackage dto = new TempCurAssetsRepaymentPackage();
            for (Map.Entry<String, String> entry : data.entrySet()) {
                /*if("机构案件号".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setOrgCasno(entry.getValue());//机构案件号
                    continue;
                }*/
                if ("机构案件号".equals(entry.getKey())) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setOrgCasno(entry.getValue().replaceAll("'",""));
                        continue;
                    }
                }
                if("交易其他费用".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())){
                        String Jyqtfy = entry.getValue().replaceAll(",","");
                        dto.setJyqtfy(Jyqtfy);//交易其他费用
                        continue;
                    }
                }
                if("交易利息".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())){
                        String Jylx = entry.getValue().replaceAll(",","");
                        dto.setJylx(Jylx);//交易利息
                        continue;
                    }
                }
                if("交易本金".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())){
                        String Jybj = entry.getValue().replaceAll(",","");
                        dto.setJybj(Jybj);//交易本金
                        continue;
                    }
                }
                if("交易滞纳费".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())){
                        String Jyznf = entry.getValue().replaceAll(",","");
                        dto.setJyznf(Jyznf);//交易滞纳费
                        continue;
                    }
                }
                if("交易类型".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setJyType(entry.getValue());//交易类型
                    continue;
                }
                if("交易金额".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())){
                        String Jyje = entry.getValue().replaceAll(",","");
                        dto.setJyje(Jyje);//交易金额
                        continue;
                    }
                }
                if("产品类型".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setProductType(entry.getValue());//产品类型
                    continue;
                }
                if("借据号".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setJjh(entry.getValue());//借据号
                    continue;
                }
                if("催收人".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setCsr(entry.getValue());//催收人
                    continue;
                }
                if("催收节点".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setCsjd(entry.getValue());//催收节点
                    continue;
                }
                if("分配日期".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())) {
//                        Date fprq = getDateByFormat(entry.getValue());
                        dto.setFprq(entry.getValue());//分配日期
                        continue;
                    }
                }
                if("区域中心".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setAreaCenter(entry.getValue());//区域中心
                    continue;
                }
                if("受理城市".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setAcceptCity(entry.getValue());//受理城市
                    continue;
                }
                if("合同号".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setHth(entry.getValue());//合同号
                    continue;
                }
                if("地区事业部(一级)".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setDqsybYj(entry.getValue());//地区事业部(一级)
                    continue;
                }
                if("地区事业部(二级)".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setDqsybEj(entry.getValue());//地区事业部(二级)
                    continue;
                }
                if("外包期数".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setWbqs(entry.getValue());//外包期数
                    continue;
                }
                if("外包经办".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setWbjb(entry.getValue());//外包经办
                    continue;
                }
                if("委案日期".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())) {
//                        Date warq = getDateByFormat(entry.getValue());
                        dto.setWarq(entry.getValue());//委案日期
                        continue;
                    }
                }
                if("客户名称".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setCurName(entry.getValue());//客户名称
                    continue;
                }
                if("客户经理姓名".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setKhjlxm(entry.getValue());//客户经理姓名
                    continue;
                }
                if("数据日期".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())) {
//                        Date sjrq = getDateByFormat(entry.getValue());
                        dto.setSjrq(entry.getValue());//数据日期
                        continue;
                    }
                }
                if("是否外包催收".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setSfwbcs(entry.getValue());//是否外包催收
                    continue;
                }
                if("是否出催".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())) {
                        dto.setSfjq(entry.getValue());//是否出催
                        dto.setIsExitCollect(String.valueOf(IsNoEnum.getCodeByDes(entry.getValue())));
                    }/* else {
                        dto.setIsExitCollect(IsNoEnum.NO.getCode().toString());
                    }*/
                    continue;
                }
                if("本月委案".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setBywa(entry.getValue());//本月委案
                    continue;
                }
                if("出催日期".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())) {
//                        Date ccrq = getDateByFormat(entry.getValue());
                        dto.setAjhsrq(entry.getValue());//出催日期
                        continue;
                    }
                }
                if("消费金融账号".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setXfjrzh(entry.getValue());//消费金融账号
                    continue;
                }
                if("调整事项".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setTzsx(entry.getValue());//调整事项
                    continue;
                }
                if("调整金额".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())){
                        String Tzje = entry.getValue().replaceAll(",","");
                        dto.setTzje(Tzje);//调整金额
                        continue;
                    }
                }
                if("账户状态".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setZhzt(entry.getValue());//账户状态
                    continue;
                }
                if("还款日期".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())) {
//                        Date hkrq = getDateByFormat(entry.getValue());
                        dto.setHkrq(entry.getValue());//还款日期
                        continue;
                    }
                }
                if("还款时逾期期数".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setHksyqqs(entry.getValue());//还款时逾期期数
                    continue;
                }
                if("还款金额".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue())){
                        String Hkje = entry.getValue().replaceAll(",","");
                        dto.setHkje(Hkje);//还款金额
                        continue;
                    }
                }
                if("逾期产品类型".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setYqcplx(entry.getValue());//逾期产品类型
                    continue;
                }
                if("逾期阶段".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setYqjd(entry.getValue());//逾期阶段
                    continue;
                }
                if("额度产品".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setQuotaProduct(entry.getValue());//额度产品
                    continue;
                }
                if("是否出催".equals(entry.getKey())){
                    if(entry.getValue() != null && !"".equals(entry.getValue()))dto.setIsExitCollect(entry.getValue());//是否出催
                    continue;
                }
            }
            String id = UUID.randomUUID().toString().replaceAll("-","");
            dto.setId(id);
            dto.setOrgId(orgId);//委托方id
            dto.setCreateBy(loginName);  //导入人
            dto.setCreateTime(new Date());
            dto.setImportBatchNo(importBatchNo);
            dto.setOrgName(orgName);
            dto.setJazt(IsCloseCaseEnum.NOT_CLOSE_CASE.getValue());
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
     * @param value
     * @return
     */
    private static boolean checkJE(String value){
        String regex = "^[+]?(([0-9]\\d*[.]?)|(0.))(\\d{0,2})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    /**
     * 日期校验
     * @param value
     * @return
     */
    private static boolean checkRQ(String value){
//        String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(value);
//        return matcher.matches();
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
    private static boolean checkRQ2(String value){
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
     * @param value
     * @return
     */
    private static boolean checkSZ(String value){
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    /**
     * 卡号校验
     * @param value
     * @return
     */
    private static boolean checkKH(String value){
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if(matcher.matches() && value.length()>=16){
            return true;
        }
        return false;
    }
    /**
     * 手机校验
     * @param value
     * @return
     */
    private static boolean checkSJ(String value){
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if(matcher.matches() && value.length()==11){
            return true;
        }
        return false;
    }
    /**
     * 电话校验
     * @param value
     * @return
     */
    private static boolean checkDH(String value){
        String regex = "0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}|13[0-9]\\d{8}|15[1089]\\d{8}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    /**
     * 邮箱校验
     * @param value
     * @return
     */
    private static boolean checkYX(String value){
        String mailRegex,mailName,mailDomain;
        mailName="^[0-9a-z]+\\w*";       //^表明一行以什么开头；^[0-9a-z]表明要以数字或小写字母开头；\\w*表明匹配任意个大写小写字母或数字或下划线
        mailDomain="([0-9a-z]+\\.)+[0-9a-z]+$";       //***.***.***格式的域名，其中*为小写字母或数字;第一个括号代表有至少一个***.匹配单元，而[0-9a-z]$表明以小写字母或数字结尾
        mailRegex=mailName+"@"+mailDomain;          //邮箱正则表达式      ^[0-9a-z]+\w*@([0-9a-z]+\.)+[0-9a-z]+$
        Pattern pattern=Pattern.compile(mailRegex);
        Matcher matcher=pattern.matcher(value);
        return  matcher.matches();
    }
    /**
     * 利率校验
     * @param value
     * @return
     */
    private static boolean checkLL(String value){
        String[] split = value.split("%");
        String regex = "^[0-9]+(.[0-9]{0,2})?$";
        Pattern pattern = Pattern.compile(regex);
        if(split.length>0){
            Matcher matcher = pattern.matcher(split[0]);
            if(matcher.matches() && value.endsWith("%")){
                return true;
            }
        }

        return false;
    }
    /**
     * 身份证校验
     * @param value
     * @return
     */
    private static boolean checkSFZ(String value){
        String reg = "\\d{15}(\\d{2}[0-9xX])?";
        Pattern compile = Pattern.compile(reg);
        Matcher matcher = compile.matcher(value);
        return matcher.matches();
    }



}
