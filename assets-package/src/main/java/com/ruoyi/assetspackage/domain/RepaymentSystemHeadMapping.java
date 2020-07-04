package com.ruoyi.assetspackage.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author guozeqi
 * @create 2020-01-02
 */
@Data
@Component
@PropertySource("classpath:configure/repaymentDataHeadMapping.properties")
public class RepaymentSystemHeadMapping {

    @Value("${org_casNo}")
    private String org_casNo;
    @Value("${hkrq}")
    private String hkrq;
    @Value("${hkje}")
    private String hkje;
    @Value("${sfjq}")
    private String sfjq;
    @Value("${ajhsrq}")
    private String ajhsrq;
    @Value("${jyqtfy}")
    private String jyqtfy;
    @Value("${jylx}")
    private String jylx;
    @Value("${jybj}")
    private String jybj;
    @Value("${jyznf}")
    private String jyznf;
    @Value("${jy_type}")
    private String jy_type;
    @Value("${jyje}")
    private String jyje;
    @Value("${product_type}")
    private String product_type;
    @Value("${jjh}")
    private String jjh;
    @Value("${csr}")
    private String csr;
    @Value("${csjd}")
    private String csjd;
    @Value("${fprq}")
    private String fprq;
    @Value("${area_center1}")
    private String area_center;
    @Value("${accept_city}")
    private String accept_city;
    @Value("${hth}")
    private String hth;
    @Value("${dqsyb_yj}")
    private String dqsyb_yj;
    @Value("${dqsyb_ej}")
    private String dqsyb_ej;
    @Value("${wbqs}")
    private String wbqs;
    @Value("${wbjb}")
    private String wbjb;
    @Value("${warq}")
    private String warq;
    @Value("${cur_name2}")
    private String cur_name;
    @Value("${khjlxm}")
    private String khjlxm;
    @Value("${sjrq}")
    private String sjrq;
    @Value("${sfwbcs}")
    private String sfwbcs;
    @Value("${bywa}")
    private String bywa;
    @Value("${xfjrzh}")
    private String xfjrzh;
    @Value("${tzsx}")
    private String tzsx;
    @Value("${tzje}")
    private String tzje;
    @Value("${zhzt}")
    private String zhzt;
    @Value("${hksyqqs}")
    private String hksyqqs;
    @Value("${yqcplx}")
    private String yqcplx;
    @Value("${yqjd}")
    private String yqjd;
    @Value("${quota_product}")
    private String quota_product;

}
