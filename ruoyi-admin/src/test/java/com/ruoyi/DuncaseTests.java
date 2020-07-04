package com.ruoyi;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.util.DataImportUtil;
import com.ruoyi.duncase.domain.Assets;
import com.ruoyi.duncase.domain.TLcDuncase;
import com.ruoyi.duncase.mapper.TLcDuncaseMapper;
import com.ruoyi.duncase.service.ITLcDuncaseService;
import com.ruoyi.enums.AllocatTaskEnum;
import com.ruoyi.task.domain.TLcTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DuncaseTests {

    @Autowired
    private ITLcDuncaseService tLcDuncaseService;
    @Autowired
    private TLcDuncaseMapper tLcDuncaseMapper;
    @Autowired
    private DataImportUtil dataImportUtil;

    /**
     * 测试上传借据信息接口
     */
    @Test
    public void acceptDuncaseTest() {
        ArrayList<Assets> assetsPackageList = new ArrayList<>();
        Assets assetsPackageDTO = Assets.builder()
                // 借据信息
                .orgCasno("123123")
                .customerNo("123123")
                .accountDate("1")
                .overdueDays("5")
                .revertCardNo("111")
                .revertCardBlank("333")
                .org("1")
                .orgId("28adcfe983fc417793b3084c577392cb")
                .firstYqDate(new Date())
                .maxYqtsHis("5")
                .transfertype("3")
                .yhkDate(new Date())
                .creditValue(new BigDecimal(500))
                .borrowEd(new BigDecimal(500))
                .borrowNo("111")
                .borrowBlank("222")
                .rmbYhfy1(new BigDecimal(500))
                .rmbYhfy2(new BigDecimal(500))
                .rmbYhfyzje(new BigDecimal(500))
                .rmbYe(new BigDecimal(500))
                .rmbYhfx1(new BigDecimal(500))
                .rmbYhfx2(new BigDecimal(500))
                .rmbYhfxzje(new BigDecimal(500))
                .rmbGded(new BigDecimal(500))
                .rmbLastJkje(new BigDecimal(500))
                .rmbZhycjkr(new Date())
                .rmbYhbj1(new BigDecimal(500))
                .rmbYhbj2(new BigDecimal(500))
                .rmbYhbjzje(new BigDecimal(500))
                .rmbZdyhje(new BigDecimal(500))
                .wbYhfx1(new BigDecimal(500))
                .wbYhfx2(new BigDecimal(500))
                .wbYhfxze(new BigDecimal(500))
                .wbYhfy1(new BigDecimal(500))
                .wbYhfy2(new BigDecimal(500))
                .wbYhfyze(new BigDecimal(500))
                .wbYhbj1(new BigDecimal(500))
                .wbYhbj2(new BigDecimal(500))
                .wbYhbjzje(new BigDecimal(500))
                .wbQkzje(new BigDecimal(500))
                .wbYhlxze(new BigDecimal(500))
                // 客户信息
                .certificateAddress("addreds")
                .job("ss")
                .city("ss")
                .education("ss")
                .curName("王五1")
                .curIncome(new BigDecimal(22))
                .marriage("是")
                .customerHomeAddress("bi")
                .birthday(new Date())
                .curSex("男")
                .certificateNo("123123x")
                .certificateType("身份证")
                .customerMobile("1111")
                .customerHomeTel("222")
                .email("1qqaa@qq.com")
                // 客户单位信息
                .workTel("0110-111111")
                .workName("修改hdsj")
                .workAddress("修改linhe")
                .workPostcode("123456")
                .indust("aa")
                // 客户联系人信息
                .firstLiaisonName("ss")
                .firstLiaisonRelation("直系")
                .firstLiaisonMobile("222")
                .firstLiaisonTel("111")
                .build();
//        Assets assetsPackageDTO2 = Assets.builder()
//                // 借据信息
//                .orgCasno("456456")
//                .customerNo("456456")
//                .accountDate("1")
//                .overdueDays("5")
//                .revertCardNo("111")
//                .revertCardBlank("333")
//                .org("123")
//                .orgId("28adcfe983fc417793b3084c577392cb")
//                .firstYqDate(new Date())
//                .maxYqtsHis("5")
//                .transfertype("3")
//                .yhkDate(new Date())
//                .creditValue(new BigDecimal(500))
//                .borrowEd(new BigDecimal(500))
//                .borrowNo("111")
//                .borrowBlank("222")
//                .rmbYhfy1(new BigDecimal(500))
//                .rmbYhfy2(new BigDecimal(500))
//                .rmbYhfyzje(new BigDecimal(500))
//                .rmbYe(new BigDecimal(500))
//                .rmbYhfx1(new BigDecimal(500))
//                .rmbYhfx2(new BigDecimal(500))
//                .rmbYhfxzje(new BigDecimal(500))
//                .rmbGded(new BigDecimal(500))
//                .rmbLastJkje(new BigDecimal(500))
//                .rmbZhycjkr(new Date())
//                .rmbYhbj1(new BigDecimal(500))
//                .rmbYhbj2(new BigDecimal(500))
//                .rmbYhbjzje(new BigDecimal(500))
//                .rmbZdyhje(new BigDecimal(500))
//                .wbYhfx1(new BigDecimal(500))
//                .wbYhfx2(new BigDecimal(500))
//                .wbYhfxze(new BigDecimal(500))
//                .wbYhfy1(new BigDecimal(500))
//                .wbYhfy2(new BigDecimal(500))
//                .wbYhfyze(new BigDecimal(500))
//                .wbYhbj1(new BigDecimal(500))
//                .wbYhbj2(new BigDecimal(500))
//                .wbYhbjzje(new BigDecimal(500))
//                .wbQkzje(new BigDecimal(500))
//                .wbYhlxze(new BigDecimal(500))
//                // 客户信息
//                .certificateAddress("addreds")
//                .job("ss")
//                .city("ss")
//                .education("ss")
//                .curName("王五2")
//                .curIncome(new BigDecimal(22))
//                .marriage("是")
//                .customerHomeAddress("bi")
//                .birthday(new Date())
//                .curSex("男")
//                .certificateNo("456456x")
//                .certificateType("身份证")
//                .customerMobile("1111")
//                .customerHomeTel("222")
//                .email("1qqaa@qq.com")
//                // 客户单位信息
//                .workTel("0110-111111")
//                .workName("修改hdsj")
//                .workAddress("修改linhe")
//                .workPostcode("123456")
//                .indust("aa")
//                // 客户联系人信息
//                .firstLiaisonName("ss")
//                .firstLiaisonRelation("直系")
//                .firstLiaisonMobile("222")
//                .firstLiaisonTel("111")
//                .build();
        assetsPackageList.add(assetsPackageDTO);
//        assetsPackageList.add(assetsPackageDTO2);
        tLcDuncaseService.acceptDuncase(assetsPackageList);
//        String s = JSON.toJSONString(assetsPackageList);
//        System.out.println("====" + s);
        log.info("测试完成");
    }

    /**
     * 测试任务分配枚举
     */
    @Test
    public void testAllocatTask() {
        List<TLcDuncase> list = this.tLcDuncaseMapper.selectTLcDuncaseList(new TLcDuncase());
        List<TLcTask> collect = list.stream()
                .map(duncase -> {
                    TLcTask task = TLcTask.builder()
                            .certificateNo(duncase.getCertificateNo())
                            .build();
                    return task;
                })
                .collect(Collectors.toList());
        log.info("collect is {}", collect);
//        AllocatTaskEnum.valueOf("ROBOT").allocatTask(list);
    }

    @org.junit.Test
    public void test02() throws Exception{
        String value = "1995/4/3";
        boolean b = dataImportUtil.checkRQ(value);
        System.out.println(b);
    }
}
