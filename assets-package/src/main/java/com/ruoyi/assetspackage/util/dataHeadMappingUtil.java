package com.ruoyi.assetspackage.util;

import com.ruoyi.assetspackage.domain.RecordSystemHeadMapping;
import com.ruoyi.assetspackage.domain.RepaymentSystemHeadMapping;
import com.ruoyi.assetspackage.domain.SystemHeadMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guozeqi
 * @create 2020-01-02
 */

public class dataHeadMappingUtil {


    public static List<String> getMappingData(SystemHeadMapping dataHeadMapping) {
        List<String> list = new ArrayList<>();

        String cur_name = dataHeadMapping.getCur_name();
        String transferType = dataHeadMapping.getTransferType();
        String certificate_no = dataHeadMapping.getCertificate_no();
        String cur_sex = dataHeadMapping.getCur_sex();
        String marriage = dataHeadMapping.getMarriage();
        String education = dataHeadMapping.getEducation();
        String rmb_ye = dataHeadMapping.getRmb_ye();
        String wa_ye = dataHeadMapping.getWa_ye();
        String org_casNo = dataHeadMapping.getOrg_casNo();
        String rcr = dataHeadMapping.getRcr();
        String account_date = dataHeadMapping.getAccount_date();
        String overdue_days = dataHeadMapping.getOverdue_days();
        String first_yq_date = dataHeadMapping.getFirst_yq_date();
        String first_yqjc_date = dataHeadMapping.getFirst_yqjc_date();
        String rmb_qkzje = dataHeadMapping.getRmb_qkzje();
        String rmb_zdyhje = dataHeadMapping.getRmb_zdyhje();
        String rmb_yhbjzje = dataHeadMapping.getRmb_yhbjzje();
        String rmb_yhlizje = dataHeadMapping.getRmb_yhlizje();
        String rmb_yhfxzje = dataHeadMapping.getRmb_yhfxzje();
        String rmb_yhfyzje = dataHeadMapping.getRmb_yhfyzje();
        String znj = dataHeadMapping.getZnj();
        String ww_city_name = dataHeadMapping.getWw_city_name();
        String area_center = dataHeadMapping.getArea_center();
        String tj_firm = dataHeadMapping.getTj_firm();
        String tj_wd = dataHeadMapping.getTj_wd();
        String cpmc = dataHeadMapping.getCpmc();
        String hk_type = dataHeadMapping.getHk_type();
        String borrow_ed = dataHeadMapping.getBorrow_ed();
        String fz = dataHeadMapping.getFz();
        String year_rates = dataHeadMapping.getYear_rates();
        String day_rates = dataHeadMapping.getDay_rates();
        String borrow_no = dataHeadMapping.getBorrow_no();
        String borrow_blank = dataHeadMapping.getBorrow_blank();
        String work_name = dataHeadMapping.getWork_name();
        String email = dataHeadMapping.getEmail();
        String work_address = dataHeadMapping.getWork_address();
        String customer_home_address = dataHeadMapping.getCustomer_home_address();
        String regist_address = dataHeadMapping.getRegist_address();
        String certificate_address = dataHeadMapping.getCertificate_address();
        String bill_address = dataHeadMapping.getBill_address();
        String first_yq_flag = dataHeadMapping.getFirst_yq_flag();
        String max_yqts_his = dataHeadMapping.getMax_yqts_his();
        String sum_yqts_his = dataHeadMapping.getSum_yqts_his();
        String sum_yqcs_his = dataHeadMapping.getSum_yqcs_his();
        String customer_mobile = dataHeadMapping.getCustomer_mobile();
        String first_liaison_name = dataHeadMapping.getFirst_liaison_name();
        String first_liaison_relation = dataHeadMapping.getFirst_liaison_relation();
        String second_liaison_name = dataHeadMapping.getSecond_liaison_name();
        String second_liaison_relation = dataHeadMapping.getSecond_liaison_relation();
        String three_liaison_name = dataHeadMapping.getThree_liaison_name();
        String three_liaison_relation = dataHeadMapping.getThree_liaison_relation();
        String customer_home_tel = dataHeadMapping.getCustomer_home_tel();
        String first_liaison_mobile = dataHeadMapping.getFirst_liaison_mobile();
        String first_liaison_tel = dataHeadMapping.getFirst_liaison_tel();
        String second_liaison_mobile = dataHeadMapping.getSecond_liaison_mobile();
        String second_liaison_tel = dataHeadMapping.getSecond_liaison_tel();
        String three_liaison_mobile = dataHeadMapping.getThree_liaison_mobile();
        String three_liaison_tel = dataHeadMapping.getThree_liaison_tel();
        String work_tel = dataHeadMapping.getWork_tel();

        String account_age = dataHeadMapping.getAccount_age();
        String la_flag = dataHeadMapping.getLa_flag();
        String fx_flag = dataHeadMapping.getFx_flag();
        String htlx = dataHeadMapping.getHtlx();
        String jmbq = dataHeadMapping.getJmbq();
        String fcbq = dataHeadMapping.getFcbq();
        String fxsfbh = dataHeadMapping.getFxsfbh();
        String remark = dataHeadMapping.getRemark();
        String tar = dataHeadMapping.getTar();
        String jkrq = dataHeadMapping.getJkrq();
        String zhychkr = dataHeadMapping.getZhychkr();
        String mqhkje = dataHeadMapping.getMqhkje();
        String dqqkje = dataHeadMapping.getDqqkje();
        String ljyhje = dataHeadMapping.getLjyhje();
        String sfje = dataHeadMapping.getSfje();
        String zdhkzh1 = dataHeadMapping.getZdhkzh1();
        String zdhkzh2 = dataHeadMapping.getZdhkzh2();
        String zdhkzhhm1 = dataHeadMapping.getZdhkzhhm1();
        String zdhkzhhm2 = dataHeadMapping.getZdhkzhhm2();
        String zdhkqd1 = dataHeadMapping.getZdhkqd1();
        String zdhkqd2 = dataHeadMapping.getZdhkqd2();
        String khmb = dataHeadMapping.getKhmb();
        String spjg = dataHeadMapping.getSpjg();
        String dklx = dataHeadMapping.getDklx();
        String jkbs = dataHeadMapping.getJkbs();
        String spxx = dataHeadMapping.getSpxx();
        String wacs = dataHeadMapping.getWacs();
        String ykqs = dataHeadMapping.getYkqs();
        String work_dept = dataHeadMapping.getWork_dept();
        String customer_mobile2 = dataHeadMapping.getCustomer_mobile2();
        String customer_mobile3 = dataHeadMapping.getCustomer_mobile3();
        String customer_mobile4 = dataHeadMapping.getCustomer_mobile4();
        String fourth_liaison_name = dataHeadMapping.getFourth_liaison_name();
        String fourth_liaison_relation = dataHeadMapping.getFourth_liaison_relation();
        String fourth_liaison_mobile = dataHeadMapping.getFourth_liaison_mobile();
        String fifth_liaison_name = dataHeadMapping.getFifth_liaison_name();
        String fifth_liaison_relation = dataHeadMapping.getFifth_liaison_relation();
        String fifth_liaison_mobile = dataHeadMapping.getFifth_liaison_mobile();



//        String	work_postcode=dataHeadMapping.getWork_postcode();




//        String	org=dataHeadMapping.getOrg();

//        String	twentyFourCD=dataHeadMapping.getTwentyFourCD();
//        String	BLK=dataHeadMapping.getBLK();
//        String	productLine=dataHeadMapping.getProductLine();
//        String	rmb_last_jkje=dataHeadMapping.getRmb_last_jkje();


//        String	rmb_cd=dataHeadMapping.getRmb_cd();
//        String	rmb_zhycjkr=dataHeadMapping.getRmb_zhycjkr();
//        String	rmb_zhhkbs=dataHeadMapping.getRmb_zhhkbs();

//        String	rmb_yhbj1=dataHeadMapping.getRmb_yhbj1();
//        String	rmb_yhbj2=dataHeadMapping.getRmb_yhbj2();

//        String	rmb_yhfx1=dataHeadMapping.getRmb_yhfx1();
//        String	rmb_yhfx2=dataHeadMapping.getRmb_yhfx2();
//        String	rmb_yhfy1=dataHeadMapping.getRmb_yhfy1();
//        String	rmb_yhfy2=dataHeadMapping.getRmb_yhfy2();



//        String	rmb_gded=dataHeadMapping.getRmb_gded();
//        String	code=dataHeadMapping.getCode();



//        String	zx_type=dataHeadMapping.getZx_type();
//        String	stop_card=dataHeadMapping.getStop_card();
//        String	stop_jxrq=dataHeadMapping.getStop_jxrq();
//        String	cs_company=dataHeadMapping.getCs_company();

//        String	gz_flag=dataHeadMapping.getGz_flag();

//        String	fq_flag=dataHeadMapping.getFq_flag();
//        String	syhx_qtfy=dataHeadMapping.getSyhx_qtfy();
//        String	syhx_sxf=dataHeadMapping.getSyhx_sxf();
//        String	syhx_bj=dataHeadMapping.getSyhx_bj();
//        String	syhx_znf=dataHeadMapping.getSyhx_znf();
//        String	syhx_yqx=dataHeadMapping.getSyhx_yqx();
//        String	syhx_jehj=dataHeadMapping.getSyhx_jehj();

//        String	cs_remark_his=dataHeadMapping.getCs_remark_his();



//        String	csjggs_his=dataHeadMapping.getCsjggs_his();
//        String	qx_flag=dataHeadMapping.getQx_flag();
//        String	st_company=dataHeadMapping.getSt_company();
//        String	accept_firm=dataHeadMapping.getAccept_firm();
//        String	accept_city=dataHeadMapping.getAccept_city();
//        String	accept_wd=dataHeadMapping.getAccept_wd();
//        String	accept_wd_code=dataHeadMapping.getAccept_wd_code();
//        String	dzhxrq=dataHeadMapping.getDzhxrq();
//        String	remark=dataHeadMapping.getRemark();
//        String	wb_yhfxze=dataHeadMapping.getWb_yhfxze();
//        String	wb_yhlxze=dataHeadMapping.getWb_yhlxze();
//        String	wb_yhbj1=dataHeadMapping.getWb_yhbj1();
//        String	wb_yhbj2=dataHeadMapping.getWb_yhbj2();
//        String	wb_yhbjzje=dataHeadMapping.getWb_yhbjzje();
//        String	wb_yhfx1=dataHeadMapping.getWb_yhfx1();
//        String	wb_yhfx2=dataHeadMapping.getWb_yhfx2();
//        String	wb_yhfy1=dataHeadMapping.getWb_yhfy1();
//        String	wb_yhfy2=dataHeadMapping.getWb_yhfy2();
//        String	wb_yhfyze=dataHeadMapping.getWb_yhfyze();
//        String	wb_zdyhe=dataHeadMapping.getWb_zdyhe();
//        String	wb_qkzje=dataHeadMapping.getWb_qkzje();
//        String	tx_flag=dataHeadMapping.getTx_flag();
//        String	ww_company_code=dataHeadMapping.getWw_company_code();

//        String	ww_jh_enddate=dataHeadMapping.getWw_jh_enddate();
//        String	ww_qsrq=dataHeadMapping.getWw_qsrq();
//        String	ww_pc=dataHeadMapping.getWw_pc();


//        String	bill_address_postcode=dataHeadMapping.getBill_address_postcode();

//        String	yhk_date=dataHeadMapping.getYhk_date();
//        String	start_yq_date=dataHeadMapping.getStart_yq_date();
//        String	kh_date=dataHeadMapping.getKh_date();
//        String	ts_flag=dataHeadMapping.getTs_flag();
//        String	credit_value=dataHeadMapping.getCredit_value();

//        String	tj_city=dataHeadMapping.getTj_city();

//        String	shhx_sxf=dataHeadMapping.getShhx_sxf();
//        String	shhx_bj=dataHeadMapping.getShhx_bj();
//        String	shhx_znf=dataHeadMapping.getShhx_znf();
//        String	shhx_fy=dataHeadMapping.getShhx_fy();
//        String	shhx_yqx=dataHeadMapping.getShhx_yqx();
//        String	shhx_jehj=dataHeadMapping.getShhx_jehj();
//        String	zh_30_day=dataHeadMapping.getZh_30_day();
//        String	zh_x_day=dataHeadMapping.getZh_x_day();
//        String	is_zc=dataHeadMapping.getIs_zc();
//        String	is_zwcz=dataHeadMapping.getIs_zwcz();
//        String	is_jx=dataHeadMapping.getIs_jx();
//        String	is_sx=dataHeadMapping.getIs_sx();
//        String	zjyq_month=dataHeadMapping.getZjyq_month();
//        String	hx_sxfcs=dataHeadMapping.getHx_sxfcs();
//        String	hx_shzt=dataHeadMapping.getHx_shzt();
//        String	hx_bjcs=dataHeadMapping.getHx_bjcs();
//        String	hx_znfcs=dataHeadMapping.getHx_znfcs();
//        String	hx_fycs=dataHeadMapping.getHx_fycs();
//        String	hx_yqlxcs=dataHeadMapping.getHx_yqlxcs();
//        String	hx_jehjcs=dataHeadMapping.getHx_jehjcs();
//        String	aj_hz_date=dataHeadMapping.getAj_hz_date();
//        String	xfjzzh=dataHeadMapping.getXfjzzh();
//        String	tsaj_type=dataHeadMapping.getTsaj_type();

//        String	tj_date=dataHeadMapping.getTj_date();
//        String	dollar_ye=dataHeadMapping.getDollar_ye();
//        String	dollar_CD=dataHeadMapping.getDollar_CD();
//        String	dollar_zdyjkje=dataHeadMapping.getDollar_zdyjkje();
//        String	dollar_zhycjkr=dataHeadMapping.getDollar_zhycjkr();
//        String	dollar_zhycjkje=dataHeadMapping.getDollar_zhycjkje();
//        String	dollar_hkrhkbs=dataHeadMapping.getDollar_hkrhkbs();
//        String	hkfs=dataHeadMapping.getHkfs();
//        String	hkqd=dataHeadMapping.getHkqd();
//        String	xwpf=dataHeadMapping.getXwpf();

//        String	account_yebj=dataHeadMapping.getAccount_yebj();
//        String	account_jqrq=dataHeadMapping.getAccount_jqrq();
//        String	fzzb=dataHeadMapping.getFzzb();
//        String	revert_card_no=dataHeadMapping.getRevert_card_no();
//        String	revert_card_blank=dataHeadMapping.getRevert_card_blank();
//        String	jjqd=dataHeadMapping.getJjqd();

//        String	overdue_flag=dataHeadMapping.getOverdue_flag();
//        String	cwcs=dataHeadMapping.getCwcs();
//        String	quota_product=dataHeadMapping.getQuota_product();
//        String	quota_code=dataHeadMapping.getQuota_code();
//        String	quota_date=dataHeadMapping.getQuota_date();
//        String	risk=dataHeadMapping.getRisk();
//        String	first_fk_date=dataHeadMapping.getFirst_fk_date();






//        String	certificate_type=dataHeadMapping.getCertificate_type();

//        String	birthday=dataHeadMapping.getBirthday();


//        String	city=dataHeadMapping.getCity();


//        String	economy=dataHeadMapping.getEconomy();
//        String	cur_income=dataHeadMapping.getCur_income();
//        String	new_address=dataHeadMapping.getNew_address();
//        String	customer_no=dataHeadMapping.getCustomer_no();



//        String	customer_home_postcode=dataHeadMapping.getCustomer_home_postcode();
//        String	card_post_address=dataHeadMapping.getCard_post_address();
//        String	job=dataHeadMapping.getJob();
//        String	dept_name=dataHeadMapping.getDept_name();
//        String	indust=dataHeadMapping.getIndust();



//        String	sjrq=dataHeadMapping.getSjrq();
//        String	hth =dataHeadMapping.getHth();
//        String	jjh =dataHeadMapping.getJjh();
//        String	dqsyb_yj=dataHeadMapping.getDqsyb_yj();
//        String	dqsyb_ej=dataHeadMapping.getDqsyb_ej();
//        String	csjd=dataHeadMapping.getCsjd();
//        String	wbjb=dataHeadMapping.getWbjb();
//        String	ys_khjlmc =dataHeadMapping.getYs_khjlmc();
//        String	khjlmc =dataHeadMapping.getKhjlmc();

//        String	xfldycpmc=dataHeadMapping.getXfldycpmc();
//        String	qxrq=dataHeadMapping.getQxrq();
//        String	dqrq=dataHeadMapping.getDqrq();

//        String	dkye=dataHeadMapping.getDkye();
//        String	wbbs =dataHeadMapping.getWbbs();
//        String	yqje=dataHeadMapping.getYqje();
//        String	yqbj=dataHeadMapping.getYqbj();
//        String	yqlx=dataHeadMapping.getYqlx();
//        String	yqsxf=dataHeadMapping.getYqsxf();

//        String	qdmc=dataHeadMapping.getQdmc();
//        String	wbsb=dataHeadMapping.getWbsb();
//        String	wbqs=dataHeadMapping.getWbqs();
//        String	fpsj=dataHeadMapping.getFpsj();
//        String	ajhssj=dataHeadMapping.getAjhssj();


        list.add(cur_name);
        list.add(transferType);
        list.add(certificate_no);
        list.add(cur_sex);
        list.add(marriage);
        list.add(education);
        list.add(rmb_ye);
        list.add(wa_ye);
        list.add(org_casNo);
        list.add(rcr);
        list.add(account_date);
        list.add(overdue_days);
        list.add(first_yq_date);
        list.add(first_yqjc_date);
        list.add(rmb_qkzje);
        list.add(rmb_zdyhje);
        list.add(rmb_yhbjzje);
        list.add(rmb_yhlizje);
        list.add(rmb_yhfxzje);
        list.add(rmb_yhfyzje);
        list.add(znj);
        list.add(ww_city_name);
        list.add(area_center);
        list.add(tj_firm);
        list.add(tj_wd);
        list.add(cpmc);
        list.add(hk_type);
        list.add(borrow_ed);
        list.add(fz);
        list.add(year_rates);
        list.add(day_rates);
        list.add(borrow_no);
        list.add(borrow_blank);
        list.add(work_name);
        list.add(email);
        list.add(work_address);
        list.add(customer_home_address);
        list.add(regist_address);
        list.add(certificate_address);
        list.add(bill_address);
        list.add(first_yq_flag);
        list.add(max_yqts_his);
        list.add(sum_yqts_his);
        list.add(sum_yqcs_his);
        list.add(customer_mobile);
        list.add(first_liaison_name);
        list.add(first_liaison_relation);
        list.add(second_liaison_name);
        list.add(second_liaison_relation);
        list.add(three_liaison_name);
        list.add(three_liaison_relation);
        list.add(customer_home_tel);
        list.add(first_liaison_mobile);
        list.add(first_liaison_tel);
        list.add(second_liaison_mobile);
        list.add(second_liaison_tel);
        list.add(three_liaison_mobile);
        list.add(three_liaison_tel);
        list.add(work_tel);

        list.add(account_age);
        list.add(la_flag);
        list.add(fx_flag);
        list.add(htlx);
        list.add(jmbq);
        list.add(fcbq);
        list.add(fxsfbh);
        list.add(remark);
        list.add(tar);
        list.add(jkrq);
        list.add(zhychkr);
        list.add(mqhkje);
        list.add(dqqkje);
        list.add(ljyhje);
        list.add(sfje);
        list.add(zdhkzh1);
        list.add(zdhkzh2);
        list.add(zdhkzhhm1);
        list.add(zdhkzhhm2);
        list.add(zdhkqd1);
        list.add(zdhkqd2);
        list.add(khmb);
        list.add(spjg);
        list.add(dklx);
        list.add(jkbs);
        list.add(spxx);
        list.add(wacs);
        list.add(ykqs);
        list.add(work_dept);
        list.add(customer_mobile2);
        list.add(customer_mobile3);
        list.add(customer_mobile4);
        list.add(fourth_liaison_name);
        list.add(fourth_liaison_relation);
        list.add(fourth_liaison_mobile);
        list.add(fifth_liaison_name);
        list.add(fifth_liaison_relation);
        list.add(fifth_liaison_mobile);

//        list.add(org);

//        list.add(twentyFourCD);
//        list.add(BLK);
//        list.add(productLine);
//        list.add(rmb_last_jkje);


//        list.add(rmb_cd);
//        list.add(rmb_zhycjkr);
//        list.add(rmb_zhhkbs);

//        list.add(rmb_yhbj1);
//        list.add(rmb_yhbj2);

//        list.add(rmb_yhfx1);
//        list.add(rmb_yhfx2);
//        list.add(rmb_yhfy1);
//        list.add(rmb_yhfy2);



//        list.add(rmb_gded);
//        list.add(code);


//        list.add(zx_type);
//        list.add(stop_card);
//        list.add(stop_jxrq);
//        list.add(cs_company);

//        list.add(gz_flag);

//        list.add(fq_flag);
//        list.add(syhx_qtfy);
//        list.add(syhx_sxf);
//        list.add(syhx_bj);
//        list.add(syhx_znf);
//        list.add(syhx_yqx);
//        list.add(syhx_jehj);

//        list.add(cs_remark_his);

//        list.add(csjggs_his);
//        list.add(qx_flag);
//        list.add(st_company);
//        list.add(accept_firm);
//        list.add(accept_city);
//        list.add(accept_wd);
//        list.add(accept_wd_code);
//        list.add(dzhxrq);
//        list.add(remark);
//        list.add(wb_yhfxze);
//        list.add(wb_yhlxze);
//        list.add(wb_yhbj1);
//        list.add(wb_yhbj2);
//        list.add(wb_yhbjzje);
//        list.add(wb_yhfx1);
//        list.add(wb_yhfx2);
//        list.add(wb_yhfy1);
//        list.add(wb_yhfy2);
//        list.add(wb_yhfyze);
//        list.add(wb_zdyhe);
//        list.add(wb_qkzje);
//        list.add(tx_flag);
//        list.add(ww_company_code);

//        list.add(ww_jh_enddate);
//        list.add(ww_qsrq);
//        list.add(ww_pc);


//        list.add(bill_address_postcode);

//        list.add(yhk_date);
//        list.add(start_yq_date);
//        list.add(kh_date);
//        list.add(ts_flag);
//        list.add(credit_value);

//        list.add(tj_city);

//        list.add(shhx_sxf);
//        list.add(shhx_bj);
//        list.add(shhx_znf);
//        list.add(shhx_fy);
//        list.add(shhx_yqx);
//        list.add(shhx_jehj);
//        list.add(zh_30_day);
//        list.add(zh_x_day);
//        list.add(is_zc);
//        list.add(is_zwcz);
//        list.add(is_jx);
//        list.add(is_sx);
//        list.add(zjyq_month);
//        list.add(hx_sxfcs);
//        list.add(hx_shzt);
//        list.add(hx_bjcs);
//        list.add(hx_znfcs);
//        list.add(hx_fycs);
//        list.add(hx_yqlxcs);
//        list.add(hx_jehjcs);
//        list.add(aj_hz_date);
//        list.add(xfjzzh);
//        list.add(tsaj_type);

//        list.add(tj_date);
//        list.add(dollar_ye);
//        list.add(dollar_CD);
//        list.add(dollar_zdyjkje);
//        list.add(dollar_zhycjkr);
//        list.add(dollar_zhycjkje);
//        list.add(dollar_hkrhkbs);
//        list.add(hkfs);
//        list.add(hkqd);
//        list.add(xwpf);

//        list.add(account_yebj);
//        list.add(account_jqrq);
//        list.add(fzzb);
//        list.add(revert_card_no);
//        list.add(revert_card_blank);
//        list.add(jjqd);

//        list.add(overdue_flag);
//        list.add(cwcs);
//        list.add(quota_product);
//        list.add(quota_code);
//        list.add(quota_date);
//        list.add(risk);
//        list.add(first_fk_date);





//        list.add(certificate_type);

//        list.add(birthday);

//        list.add(city);

//        list.add(economy);
//        list.add(cur_income);
//        list.add(new_address);
//        list.add(customer_no);



//        list.add(customer_home_postcode);
//        list.add(card_post_address);
//        list.add(job);
//        list.add(dept_name);
//        list.add(indust);


//        list.add(work_postcode);

//        list.add(sjrq);
//        list.add(hth );
//        list.add(jjh );
//        list.add(dqsyb_yj);
//        list.add(dqsyb_ej);
//        list.add(csjd);
//        list.add(wbjb);
//        list.add(ys_khjlmc );
//        list.add(khjlmc );

//        list.add(xfldycpmc);
//        list.add(qxrq);
//        list.add(dqrq);

//        list.add(dkye);
//        list.add(wbbs );
//        list.add(yqje);
//        list.add(yqbj);
//        list.add(yqlx);
//        list.add(yqsxf);

//        list.add(qdmc);
//        list.add(wbsb);
//        list.add(wbqs);
//        list.add(fpsj);
//        list.add(ajhssj);


        return list;

    }


    public static List<String> getMappingData(RepaymentSystemHeadMapping repaymentSystemHeadMapping) {
        List<String> list = new ArrayList<String>();

        String org_casNo = repaymentSystemHeadMapping.getOrg_casNo();
        String jyqtfy = repaymentSystemHeadMapping.getJyqtfy();
        String jylx = repaymentSystemHeadMapping.getJylx();
        String jybj = repaymentSystemHeadMapping.getJybj();
        String jyznf = repaymentSystemHeadMapping.getJyznf();
        String jy_type = repaymentSystemHeadMapping.getJy_type();
        String jyje = repaymentSystemHeadMapping.getJyje();
        String product_type = repaymentSystemHeadMapping.getProduct_type();
        String jjh = repaymentSystemHeadMapping.getJjh();
        String csr = repaymentSystemHeadMapping.getCsr();
        String csjd = repaymentSystemHeadMapping.getCsjd();
        String fprq = repaymentSystemHeadMapping.getFprq();
        String area_center = repaymentSystemHeadMapping.getArea_center();
        String accept_city = repaymentSystemHeadMapping.getAccept_city();
        String hth = repaymentSystemHeadMapping.getHth();
        String dqsyb_yj = repaymentSystemHeadMapping.getDqsyb_yj();
        String dqsyb_ej = repaymentSystemHeadMapping.getDqsyb_ej();
        String wbqs = repaymentSystemHeadMapping.getWbqs();
        String wbjb = repaymentSystemHeadMapping.getWbjb();
        String warq = repaymentSystemHeadMapping.getWarq();
        String cur_name = repaymentSystemHeadMapping.getCur_name();
        String khjlxm = repaymentSystemHeadMapping.getKhjlxm();
        String sjrq = repaymentSystemHeadMapping.getSjrq();
        String sfwbcs = repaymentSystemHeadMapping.getSfwbcs();
        String sfjq = repaymentSystemHeadMapping.getSfjq();
        String bywa = repaymentSystemHeadMapping.getBywa();
        String ajhsrq = repaymentSystemHeadMapping.getAjhsrq();
        String xfjrzh = repaymentSystemHeadMapping.getXfjrzh();
        String tzsx = repaymentSystemHeadMapping.getTzsx();
        String tzje = repaymentSystemHeadMapping.getTzje();
        String zhzt = repaymentSystemHeadMapping.getZhzt();
        String hkrq = repaymentSystemHeadMapping.getHkrq();
        String hksyqqs = repaymentSystemHeadMapping.getHksyqqs();
        String hkje = repaymentSystemHeadMapping.getHkje();
        String yqcplx = repaymentSystemHeadMapping.getYqcplx();
        String yqjd = repaymentSystemHeadMapping.getYqjd();
        String quota_product = repaymentSystemHeadMapping.getQuota_product();


        list.add(org_casNo);
        list.add(hkrq);
        list.add(hkje);
        list.add(sfjq);
        list.add(ajhsrq);
        list.add(jyqtfy);
        list.add(jylx);
        list.add(jybj);
        list.add(jyznf);
        list.add(jy_type);
        list.add(jyje);
        list.add(product_type);
        list.add(jjh);
        list.add(csr);
        list.add(csjd);
        list.add(fprq);
        list.add(area_center);
        list.add(accept_city);
        list.add(hth);
        list.add(dqsyb_yj);
        list.add(dqsyb_ej);
        list.add(wbqs);
        list.add(wbjb);
        list.add(warq);
        list.add(cur_name);
        list.add(khjlxm);
        list.add(sjrq);
        list.add(sfwbcs);

        list.add(bywa);

        list.add(xfjrzh);
        list.add(tzsx);
        list.add(tzje);
        list.add(zhzt);

        list.add(hksyqqs);

        list.add(yqcplx);
        list.add(yqjd);
        list.add(quota_product);


        return list;

    }


    public static List<String> getMappingData(RecordSystemHeadMapping recordSystemHeadMapping) {
        List<String> list = new ArrayList<String>();
        String org_caseNo = recordSystemHeadMapping.getOrg_caseNo();
        String name = recordSystemHeadMapping.getName();
        String certificate_no = recordSystemHeadMapping.getCertificate_no();
        String phone = recordSystemHeadMapping.getPhone();
        String relation = recordSystemHeadMapping.getRelation();
//        String phone_code = recordSystemHeadMapping.getPhone_code();
        String remark = recordSystemHeadMapping.getRemark();
        String seat = recordSystemHeadMapping.getSeat();
        String make_call_time = recordSystemHeadMapping.getMake_call_time();
        String call_start_time = recordSystemHeadMapping.getCall_start_time();
        String call_end_time = recordSystemHeadMapping.getCall_end_time();
        String call_length = recordSystemHeadMapping.getCall_length();
        String call_record_id = recordSystemHeadMapping.getCall_record_id();
        String grade = recordSystemHeadMapping.getGrade();
        String call_status = recordSystemHeadMapping.getCall_status();

        list.add(org_caseNo);
        list.add(name);
        list.add(certificate_no);
        list.add(phone);
        list.add(relation);
//        list.add(phone_code);
        list.add(remark);
        list.add(seat);
        list.add(make_call_time);
        list.add(call_start_time);
        list.add(call_end_time);
        list.add(call_length);
        list.add(call_record_id);
        list.add(grade);
        list.add(call_status);

        return list;

    }

}
