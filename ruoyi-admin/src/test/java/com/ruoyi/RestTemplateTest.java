package com.ruoyi;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RestTemplateUtil;
import com.ruoyi.duncase.domain.AllocatTaskInvokeRuleEngin;
import com.ruoyi.duncase.domain.TLcDuncase;
import com.ruoyi.duncase.service.ITLcDuncaseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/11 9:15
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RestTemplateTest {

    private static final String allocatTaskUrl = "http://106.15.60.221:8081/api/invokeRule";

    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private ITLcDuncaseService tLcDuncaseService;

    @Test
    public void testRuleEnginAllocatTask() {
        List<TLcDuncase> duncaseInsertList = this.tLcDuncaseService.selectTLcDuncaseList(new TLcDuncase());
        duncaseInsertList = duncaseInsertList.stream().map(duncase -> {
            AllocatTaskInvokeRuleEngin allocatTaskInvokeRuleEngin = new AllocatTaskInvokeRuleEngin();
            allocatTaskInvokeRuleEngin.setFrontTransNo(duncase.getCaseNo())
            .setFrontTransTime(LocalDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(DateUtils.YYYY_MM_DD_HH_MM_SS)))
            .setRulePackage("资产规则集/caseAutoDistribution")
            .setProcessId("caseAutoDistribution")
            .setFacts(getFact(duncase));
            ResponseEntity<Map> response = restTemplateUtil.getRestTemplate().postForEntity(allocatTaskUrl, allocatTaskInvokeRuleEngin, Map.class);
            if(response.getStatusCodeValue() != HttpStatus.OK.value()) {
                log.error("调用分案规则接口错误，response.getStatusCodeValue is {}", response.getStatusCodeValue());
                throw new RuntimeException(String.format("调用分案规则接口错误，接口返回状态response.getStatusCodeValue是%s", response.getStatusCodeValue()));
            }
            String retCode = (String) response.getBody().get("retCode");
            log.info("allocatTask retCode is {}", retCode);
            if (!"0000".equals(retCode)) {
                log.error("调用分案规则接口错误，retCode{}", retCode);
                throw new RuntimeException(String.format("调用分案规则接口错误，接口返回状态retCode是%s", retCode));
            }
            Map responseBodyMap = (Map)response.getBody().get("responseBody");
            Map factsMap = (Map)responseBodyMap.get("facts");
            Map caseInfoDTOMap = (Map)factsMap.get("caseInfoDTO");
            log.info("规则引擎分配案件的编码是{}",caseInfoDTOMap.get("distributionStrategy"));
            return duncase;
        }).collect(Collectors.toList());
    }

    private AllocatTaskInvokeRuleEngin.Fact getFact(TLcDuncase tLcDuncase) {
//        return AllocatTaskInvokeRuleEngin.Fact.builder()
//                .caseInfoDTO(getCaseInfo(tLcDuncase))
//                .build();
        AllocatTaskInvokeRuleEngin.Fact fact = new AllocatTaskInvokeRuleEngin().new Fact();
        fact.setCaseInfoDTO(getCaseInfo(tLcDuncase));
        return fact;
    }

    private AllocatTaskInvokeRuleEngin.CaseInfoDTO getCaseInfo(TLcDuncase tLcDuncase) {
//        return AllocatTaskInvokeRuleEngin.CaseInfoDTO.builder()
//                .caseCd(tLcDuncase.getCaseNo())
//                .customerName(tLcDuncase.getCustomName())
//                .cityName(tLcDuncase.getWwCityName())  //
//                .hands(tLcDuncase.getHandSeparation())
//                .blk(tLcDuncase.getBlk()) //
//                .startDate(tLcDuncase.getWwQsrq().toString()) //
//                .endDate(tLcDuncase.getWwJhEnddate().toString()) //
//                .sex(tLcDuncase.getCurSex()) //
//                .overdueDay(tLcDuncase.getOverdueDays().toString())
//                .balanceRMB(tLcDuncase.getBalanceRmb().toString())
//                .lastPayRMB(tLcDuncase.getLowestPaymentRmb().toString())
//                .balanceDollar("")
//                .lastPayDollar(tLcDuncase.getLowestPaymentFc().toString())
//                .billDay(tLcDuncase.getMonthRepayDay().toString())
//                .directHoldRelation("")
//                .familyAddr("")
//                .organAddr("")
//                .billAddr("")
//                .build();
        AllocatTaskInvokeRuleEngin.CaseInfoDTO caseInfoDTO = new AllocatTaskInvokeRuleEngin().new CaseInfoDTO();
        caseInfoDTO.setCaseCd(tLcDuncase.getCaseNo())
                .setCustomerName(tLcDuncase.getCustomName())
                .setCityName(tLcDuncase.getWwCityName())  //
                .setHands(tLcDuncase.getHandSeparation())
                .setBlk(tLcDuncase.getBlk()) //
                .setStartDate("") //
//                .setStartDate(tLcDuncase.getWwQsrq().toString()) //
                .setEndDate("") //
//                .setEndDate(tLcDuncase.getWwJhEnddate().toString()) //
                .setSex(tLcDuncase.getCurSex()) //
                .setOverdueDay(tLcDuncase.getOverdueDays().toString())
                .setBalanceRMB(tLcDuncase.getBalanceRmb().toString())
                .setLastPayRMB(tLcDuncase.getLowestPaymentRmb().toString())
                .setBalanceDollar("")
                .setLastPayDollar("")
                .setBillDay("")
                .setDirectHoldRelation("")
                .setFamilyAddr("")
                .setOrganAddr("")
                .setBillAddr("");
        return caseInfoDTO;
    }
}
