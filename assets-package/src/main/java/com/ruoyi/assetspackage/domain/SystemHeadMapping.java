package com.ruoyi.assetspackage.domain;

import lombok.Data;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author guozeqi
 * @create 2020-01-02
 */
@Data
@Component
@PropertySource("classpath:configure/dataHeadMapping.properties")
public class SystemHeadMapping {

    @Value("${cur_name}")
    private String cur_name;
    @Value("${transferType}")
    private String transferType;
    @Value("${certificate_no}")
    private String certificate_no;
    @Value("${cur_sex}")
    private String cur_sex;
    @Value("${marriage}")
    private String marriage;
    @Value("${education}")
    private String education;
    @Value("${rmb_ye}")
    private String rmb_ye;
    @Value("${wa_ye}")
    private String wa_ye;
    @Value("${org_casNo}")
    private String org_casNo;
    @Value("${rcr}")
    private String rcr;
    @Value("${account_date}")
    private String account_date;
    @Value("${overdue_days}")
    private String overdue_days;
    @Value("${first_yq_date}")
    private String first_yq_date;
    @Value("${first_yqjc_date}")
    private String first_yqjc_date;
    @Value("${rmb_qkzje}")
    private String rmb_qkzje;
    @Value("${rmb_zdyhje}")
    private String rmb_zdyhje;
    @Value("${rmb_yhbjzje}")
    private String rmb_yhbjzje;
    @Value("${rmb_yhlizje}")
    private String rmb_yhlizje;
    @Value("${rmb_yhfxzje}")
    private String rmb_yhfxzje;
    @Value("${rmb_yhfyzje}")
    private String rmb_yhfyzje;
    @Value("${znj}")
    private String znj;
    @Value("${ww_city_name}")
    private String ww_city_name;
    @Value("${area_center}")
    private String area_center;
    @Value("${tj_firm}")
    private String tj_firm;
    @Value("${tj_wd}")
    private String tj_wd;
    @Value("${cpmc}")
    private String cpmc;
    @Value("${hk_type}")
    private String hk_type ;
    @Value("${borrow_ed}")
    private String borrow_ed;
    @Value("${fz}")
    private String fz;
    @Value("${year_rates}")
    private String year_rates;
    @Value("${day_rates}")
    private String day_rates;
    @Value("${borrow_no}")
    private String borrow_no;
    @Value("${borrow_blank}")
    private String borrow_blank;
    @Value("${work_name}")
    private String work_name;
    @Value("${email}")
    private String email;
    @Value("${work_address}")
    private String work_address;
    @Value("${customer_home_address}")
    private String customer_home_address;
    @Value("${regist_address}")
    private String regist_address;
    @Value("${certificate_address}")
    private String certificate_address;
    @Value("${bill_address}")
    private String bill_address;
    @Value("${first_yq_flag}")
    private String first_yq_flag;
    @Value("${max_yqts_his}")
    private String max_yqts_his;
    @Value("${sum_yqts_his}")
    private String sum_yqts_his;
    @Value("${sum_yqcs_his}")
    private String sum_yqcs_his;
    @Value("${customer_mobile}")
    private String customer_mobile;
    @Value("${first_liaison_name}")
    private String first_liaison_name;
    @Value("${first_liaison_relation}")
    private String first_liaison_relation;
    @Value("${second_liaison_name}")
    private String second_liaison_name;
    @Value("${second_liaison_relation}")
    private String second_liaison_relation;
    @Value("${three_liaison_name}")
    private String three_liaison_name;
    @Value("${three_liaison_relation}")
    private String three_liaison_relation;
    @Value("${customer_home_tel}")
    private String customer_home_tel;
    @Value("${first_liaison_mobile}")
    private String first_liaison_mobile;
    @Value("${first_liaison_tel}")
    private String first_liaison_tel;
    @Value("${second_liaison_mobile}")
    private String second_liaison_mobile;
    @Value("${second_liaison_tel}")
    private String second_liaison_tel;
    @Value("${three_liaison_mobile}")
    private String three_liaison_mobile;
    @Value("${three_liaison_tel}")
    private String three_liaison_tel;
    @Value("${work_tel}")
    private String work_tel;
    @Value("${account_age}")
    private String account_age;
    @Value("${la_flag}")
    private String la_flag;
    @Value("${fx_flag}")
    private String fx_flag;
    @Value("${htlx}")
    private String htlx;
    @Value("${jmbq}")
    private String jmbq;
    @Value("${fcbq}")
    private String fcbq;
    @Value("${fxsfbh}")
    private String fxsfbh;
    @Value("${remark}")
    private String remark;
    @Value("${tar}")
    private String tar;
    @Value("${jkrq}")
    private String jkrq;
    @Value("${zhychkr}")
    private String zhychkr;
    @Value("${mqhkje}")
    private String mqhkje;
    @Value("${dqqkje}")
    private String dqqkje;
    @Value("${ljyhje}")
    private String ljyhje;
    @Value("${sfje}")
    private String sfje;
    @Value("${zdhkzh1}")
    private String zdhkzh1;
    @Value("${zdhkzh2}")
    private String zdhkzh2;
    @Value("${zdhkzhhm1}")
    private String zdhkzhhm1;
    @Value("${zdhkzhhm2}")
    private String zdhkzhhm2;
    @Value("${zdhkqd1}")
    private String zdhkqd1;
    @Value("${zdhkqd2}")
    private String zdhkqd2;
    @Value("${khmb}")
    private String khmb;
    @Value("${spjg}")
    private String spjg;
    @Value("${dklx}")
    private String dklx;
    @Value("${jkbs}")
    private String jkbs;
    @Value("${spxx}")
    private String spxx;
    @Value("${wacs}")
    private String wacs;
    @Value("${ykqs}")
    private String ykqs;
    @Value("${work_dept}")
    private String work_dept;
    @Value("${customer_mobile2}")
    private String customer_mobile2;
    @Value("${customer_mobile3}")
    private String customer_mobile3;
    @Value("${customer_mobile4}")
    private String customer_mobile4;
    @Value("${fourth_liaison_name}")
    private String fourth_liaison_name;
    @Value("${fourth_liaison_relation}")
    private String fourth_liaison_relation;
    @Value("${fourth_liaison_mobile}")
    private String fourth_liaison_mobile;
    @Value("${fifth_liaison_name}")
    private String fifth_liaison_name;
    @Value("${fifth_liaison_relation}")
    private String fifth_liaison_relation;
    @Value("${fifth_liaison_mobile}")
    private String fifth_liaison_mobile;
    @Value("${dzhxrq}")
    private String dzhxrq;



    /*@Value("${org}")
    private String org;

    @Value("${twentyFourCD}")
    private String twentyFourCD;
    @Value("${BLK}")
    private String BLK;
    @Value("${productLine}")
    private String productLine;
    @Value("${rmb_last_jkje}")
    private String rmb_last_jkje;


    @Value("${rmb_cd}")
    private String rmb_cd;
    @Value("${rmb_zhycjkr}")
    private String rmb_zhycjkr;
    @Value("${rmb_zhhkbs}")
    private String rmb_zhhkbs;

    @Value("${rmb_yhbj1}")
    private String rmb_yhbj1;
    @Value("${rmb_yhbj2}")
    private String rmb_yhbj2;

    @Value("${rmb_yhfx1}")
    private String rmb_yhfx1;
    @Value("${rmb_yhfx2}")
    private String rmb_yhfx2;
    @Value("${rmb_yhfy1}")
    private String rmb_yhfy1;
    @Value("${rmb_yhfy2}")
    private String rmb_yhfy2;



    @Value("${rmb_gded}")
    private String rmb_gded;
    @Value("${code}")
    private String code;



    @Value("${zx_type}")
    private String zx_type;
    @Value("${stop_card}")
    private String stop_card;
    @Value("${stop_jxrq}")
    private String stop_jxrq;
    @Value("${cs_company}")
    private String cs_company;

    @Value("${gz_flag}")
    private String gz_flag;

    @Value("${fq_flag}")
    private String fq_flag;
    @Value("${syhx_qtfy}")
    private String syhx_qtfy;
    @Value("${syhx_sxf}")
    private String syhx_sxf;
    @Value("${syhx_bj}")
    private String syhx_bj;
    @Value("${syhx_znf}")
    private String syhx_znf;
    @Value("${syhx_yqx}")
    private String syhx_yqx;
    @Value("${syhx_jehj}")
    private String syhx_jehj;

    @Value("${cs_remark_his}")
    private String cs_remark_his;



    @Value("${csjggs_his}")
    private String csjggs_his;
    @Value("${qx_flag}")
    private String qx_flag;
    @Value("${st_company}")
    private String st_company;
    @Value("${accept_firm}")
    private String accept_firm;
    @Value("${accept_city}")
    private String accept_city;
    @Value("${accept_wd}")
    private String accept_wd;
    @Value("${accept_wd_code}")
    private String accept_wd_code;
    @Value("${dzhxrq}")
    private String dzhxrq;
    @Value("${remark}")
    private String remark;
    @Value("${wb_yhfxze}")
    private String wb_yhfxze;
    @Value("${wb_yhlxze}")
    private String wb_yhlxze;
    @Value("${wb_yhbj1}")
    private String wb_yhbj1;
    @Value("${wb_yhbj2}")
    private String wb_yhbj2;
    @Value("${wb_yhbjzje}")
    private String wb_yhbjzje;
    @Value("${wb_yhfx1}")
    private String wb_yhfx1;
    @Value("${wb_yhfx2}")
    private String wb_yhfx2;
    @Value("${wb_yhfy1}")
    private String wb_yhfy1;
    @Value("${wb_yhfy2}")
    private String wb_yhfy2;
    @Value("${wb_yhfyze}")
    private String wb_yhfyze;
    @Value("${wb_zdyhe}")
    private String wb_zdyhe;
    @Value("${wb_qkzje}")
    private String wb_qkzje;
    @Value("${tx_flag}")
    private String tx_flag;
    @Value("${ww_company_code}")
    private String ww_company_code;

    @Value("${ww_jh_enddate}")
    private String ww_jh_enddate;
    @Value("${ww_qsrq}")
    private String ww_qsrq;
    @Value("${ww_pc}")
    private String ww_pc;


    @Value("${bill_address_postcode}")
    private String bill_address_postcode;

    @Value("${yhk_date}")
    private String yhk_date;
    @Value("${start_yq_date}")
    private String start_yq_date;
    @Value("${kh_date}")
    private String kh_date;
    @Value("${ts_flag}")
    private String ts_flag;
    @Value("${credit_value}")
    private String credit_value;

    @Value("${tj_city}")
    private String tj_city;

    @Value("${shhx_sxf}")
    private String shhx_sxf;
    @Value("${shhx_bj}")
    private String shhx_bj;
    @Value("${shhx_znf}")
    private String shhx_znf;
    @Value("${shhx_fy}")
    private String shhx_fy;
    @Value("${shhx_yqx}")
    private String shhx_yqx;
    @Value("${shhx_jehj}")
    private String shhx_jehj;
    @Value("${zh_30_day}")
    private String zh_30_day;
    @Value("${zh_x_day}")
    private String zh_x_day;
    @Value("${is_zc}")
    private String is_zc;
    @Value("${is_zwcz}")
    private String is_zwcz;
    @Value("${is_jx}")
    private String is_jx;
    @Value("${is_sx}")
    private String is_sx;
    @Value("${zjyq_month}")
    private String zjyq_month;
    @Value("${hx_sxfcs}")
    private String hx_sxfcs;
    @Value("${hx_shzt}")
    private String hx_shzt;
    @Value("${hx_bjcs}")
    private String hx_bjcs;
    @Value("${hx_znfcs}")
    private String hx_znfcs;
    @Value("${hx_fycs}")
    private String hx_fycs;
    @Value("${hx_yqlxcs}")
    private String hx_yqlxcs;
    @Value("${hx_jehjcs}")
    private String hx_jehjcs;
    @Value("${aj_hz_date}")
    private String aj_hz_date;
    @Value("${xfjzzh}")
    private String xfjzzh;
    @Value("${tsaj_type}")
    private String tsaj_type;

    @Value("${tj_date}")
    private String tj_date;
    @Value("${dollar_ye}")
    private String dollar_ye;
    @Value("${dollar_CD}")
    private String dollar_CD;
    @Value("${dollar_zdyjkje}")
    private String dollar_zdyjkje;
    @Value("${dollar_zhycjkr}")
    private String dollar_zhycjkr;
    @Value("${dollar_zhycjkje}")
    private String dollar_zhycjkje;
    @Value("${dollar_hkrhkbs}")
    private String dollar_hkrhkbs;
    @Value("${hkfs}")
    private String hkfs;
    @Value("${hkqd}")
    private String hkqd;
    @Value("${xwpf}")
    private String xwpf;

    @Value("${account_yebj}")
    private String account_yebj;
    @Value("${account_jqrq}")
    private String account_jqrq;
    @Value("${fzzb}")
    private String fzzb;
    @Value("${revert_card_no}")
    private String revert_card_no;
    @Value("${revert_card_blank}")
    private String revert_card_blank;
    @Value("${jjqd}")
    private String jjqd;

    @Value("${overdue_flag}")
    private String overdue_flag;
    @Value("${cwcs}")
    private String cwcs;
    @Value("${quota_product}")
    private String quota_product;
    @Value("${quota_code}")
    private String quota_code;
    @Value("${quota_date}")
    private String quota_date;
    @Value("${risk}")
    private String risk;
    @Value("${first_fk_date}")
    private String first_fk_date;






    @Value("${certificate_type}")
    private String certificate_type;

    @Value("${birthday}")
    private String birthday;


    @Value("${city}")
    private String city;


    @Value("${economy}")
    private String economy;
    @Value("${cur_income}")
    private String cur_income;
    @Value("${new_address}")
    private String new_address;
    @Value("${customer_no}")
    private String customer_no;



    @Value("${customer_home_postcode}")
    private String customer_home_postcode;
    @Value("${card_post_address}")
    private String card_post_address;
    @Value("${job}")
    private String job;
    @Value("${dept_name}")
    private String dept_name;
    @Value("${indust}")
    private String indust;



    @Value("${work_postcode}")
    private String work_postcode;

    @Value("${sjrq}")
    private String sjrq;
    @Value("${hth}")
    private String hth ;
    @Value("${jjh}")
    private String jjh ;
    @Value("${dqsyb_yj}")
    private String dqsyb_yj;
    @Value("${dqsyb_ej}")
    private String dqsyb_ej;
    @Value("${csjd}")
    private String csjd;
    @Value("${wbjb}")
    private String wbjb;
    @Value("${ys_khjlmc}")
    private String ys_khjlmc ;
    @Value("${khjlmc}")
    private String khjlmc ;

    @Value("${xfldycpmc}")
    private String xfldycpmc;
    @Value("${qxrq}")
    private String qxrq;
    @Value("${dqrq}")
    private String dqrq;

    @Value("${dkye}")
    private String dkye;
    @Value("${wbbs}")
    private String wbbs ;
    @Value("${yqje}")
    private String yqje;
    @Value("${yqbj}")
    private String yqbj;
    @Value("${yqlx}")
    private String yqlx;
    @Value("${yqsxf}")
    private String yqsxf;

    @Value("${qdmc}")
    private String qdmc;
    @Value("${wbsb}")
    private String wbsb;
    @Value("${wbqs}")
    private String wbqs;
    @Value("${fpsj}")
    private String fpsj;
    @Value("${ajhssj}")
    private String ajhssj;*/

}
