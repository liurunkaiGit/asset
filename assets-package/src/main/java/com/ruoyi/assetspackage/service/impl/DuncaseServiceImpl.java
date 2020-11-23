package com.ruoyi.assetspackage.service.impl;

import com.ruoyi.assetspackage.domain.Assets2;
import com.ruoyi.assetspackage.domain.Duncase;
import com.ruoyi.assetspackage.domain.Task;
import com.ruoyi.assetspackage.enums.IsNoEnum;
import com.ruoyi.assetspackage.mapper.DuncaseMapper;
import com.ruoyi.assetspackage.mapper.TaskMapper;
import com.ruoyi.assetspackage.service.IDuncaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 案件Service业务层处理
 *
 * @author liurunkai
 * @date 2019-12-25
 */
@Slf4j
@Service
public class DuncaseServiceImpl implements IDuncaseService {

    @Resource
    private DuncaseMapper tLcDuncaseMapper;

    @Resource
    private TaskMapper tLcTaskMapper;

    @Override
    @Transactional
    public void DuncaseUpdate(List<Assets2> assetList) throws Exception{
        // 创建案件集合
        List<Duncase> duncaseUpdateList = new ArrayList<>(assetList.size());
        assetList.stream().forEach(assetsPackage -> {
            Task tLcTask = this.tLcTaskMapper.selectTaskByCaseNo(assetsPackage.getOrgCasno(), assetsPackage.getOrgId());
            if(tLcTask != null){
                //创建借据信息对象并分别加入到对应的集合中
                Duncase tLcDuncase = createTLcDuncase(assetsPackage);
                // 根据案件号、机构、导入批次号判断该借据是否存在，如果存在则修改，反之新增
//              Duncase duncase = this.tLcDuncaseMapper.findDuncaseByCaseNoAndImportBatchNo(assetsPackage.getOrgCasno(), assetsPackage.getOrgId(), assetsPackage.getImportBatchNo());
//              if (duncase != null) {
                duncaseUpdateList.add(tLcDuncase);
//              }
            }

        });
        // 修改任务表中的数据
        if (duncaseUpdateList != null && duncaseUpdateList.size() > 0) {
            List<Task> taskList = duncaseUpdateList.stream()
                    .map(duncase -> createUpdateTask(duncase))
                    .collect(Collectors.toList());
            this.tLcTaskMapper.batchUpdateTask(taskList);
        }
        // 分别插入借据信息表
//        updateData2(duncaseUpdateList);
    }



    private Task createUpdateTask(Duncase duncase) {
//        Task tLcTask = this.tLcTaskMapper.selectTaskByCaseNo(duncase.getCaseNo(), duncase.getOrgId());
        Task tLcTask = new Task();
        tLcTask.setCaseNo(duncase.getCaseNo())
                .setCertificateNo(duncase.getCertificateNo())
                .setCertificateType(duncase.getCertificateType())
                .setCustomCode(duncase.getCustomNo())
                .setCustomName(duncase.getCustomName())
                .setArrearsTotal(duncase.getAppointCaseBalance())
                .setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()))
                .setOverdueDays(duncase.getOverdueDays())
                .setCollectTimeLimit(null)
                .setCollectLastTime(null)
                .setCollectTeamId(null)
                .setCollectTeamName(null)
                .setOwnerId(duncase.getOwnerId())
                .setOwnerName(duncase.getOwnerName())
                /*.setOrgId(duncase.getOrgId())
                .setOrgName(duncase.getOrgName())*/
                .setOldOwnerId(null)
               /* .setCloseDate(null)*/
                .setValidateStatus(IsNoEnum.IS.getCode())
                .setModifyBy(duncase.getModifyBy())
                .setTransferType(duncase.getTransferType()) //手别
                .setEnterCollDate(duncase.getEnterCollDate()) // 入催日
                .setCloseCaseYhje(duncase.getCloseCaseYhje()) // 结案应还金额
                /*.setImportBatchNo(duncase.getImportBatchNo())*/
                .setUpdateBy(duncase.getCreateBy());
        return tLcTask;
    }




    private void updateData2(List<Duncase> duncaseUpdateList) {
        //如果原案件存在，则修改，反之新增
        if (duncaseUpdateList != null && duncaseUpdateList.size() > 0) {
            this.tLcDuncaseMapper.batchUpdateDuncase(duncaseUpdateList);
        }
    }


    /**
     * 根据从资产包传过来的借据信息创建借据信息对象
     *
     * @param assetsPackage
     * @return
     */
    private Duncase createTLcDuncase(Assets2 assetsPackage) {
        Duncase tLcDuncase = Duncase.builder()
                .caseNo(assetsPackage.getOrgCasno()) //案件号
                .customName(assetsPackage.getCurName())   //客户姓名
                .customNo(assetsPackage.getCustomerNo())//客户号
                .certificateNo(assetsPackage.getCertificateNo()) //身份证号
                .customPhone(assetsPackage.getCustomerMobile()) //客户手机号
                .monthRepayDay(stringConverLong(assetsPackage.getAccountDate())) //账单日
                .overdueDays(stringConverLong(assetsPackage.getOverdueDays())) //逾期天数
                .repayAccountNo(assetsPackage.getRevertCardNo()) //还款卡号
                .repayBank(assetsPackage.getRevertCardBlank()) //还款银行
                .modifyBy(assetsPackage.getUpdateId()) //修改人
                .orgId(assetsPackage.getOrgId()) //业务归属机构
                .orgName(this.tLcDuncaseMapper.selectOrgNameByOrgId(assetsPackage.getOrgId())) //业务归属机构名称
                /*.caseStatus(1) //案件状态==任务状态*/
                .firstOverdueTime(assetsPackage.getFirstYqDate()) //首次逾期时间
                .maxOverdueDay(stringConverLong(assetsPackage.getMaxYqtsHis())) //历史最大逾期天数
                .repayDate(assetsPackage.getFirstYqjcDate()) //应还日期-取首次逾期解除日期
                .borrowLine(assetsPackage.getBorrowEd()) //借款额度
                .borrowCardNo(assetsPackage.getBorrowNo()) //借款卡号
                .borrowBank(assetsPackage.getBorrowBlank()) //借款卡银行
                .totalExpensesPayableRmb(assetsPackage.getRmbYhfyzje()) //人民币账户应还费用总金额
                .balanceRmb(assetsPackage.getRmbYe()) //人民币账户余额
                .totalDefaultInterestRmb(assetsPackage.getRmbYhfxzje()) //人民币账户应还罚息总额
                .fixLimitRmb(assetsPackage.getRmbGded()) //人民币账户额度固定额度
                .lastRepayAmountRmb(assetsPackage.getRmbLastJkje()) //人民币账户最后缴款金额
                .lastRepayDateRmb(assetsPackage.getRmbZhycjkr()) //人民币账户最后一次缴款日
                .totalPrincipalRmb(assetsPackage.getRmbYhbjzje()) // 人民币账户应还本金总额
                .lowestPaymentRmb(assetsPackage.getRmbZdyhje()) // 人民币账户最低应还金额
                .totalDebtAmountRmb(assetsPackage.getRmbQkzje()) // 人民币欠款总金额
                .totalInterestRmb(assetsPackage.getRmbYhlizje()) // 应还利息
                .appointCaseBalance(assetsPackage.getRmbYe()) //委案金额
                .transferType(assetsPackage.getTransfertype()) //手别
                .enterCollDate(assetsPackage.getRcr()) // 入催日
                .closeCaseYhje(assetsPackage.getWaYe()) // 结案应还金额
                .overdueFine(assetsPackage.getZnj())
                .city(assetsPackage.getWwCityName())
                .area(assetsPackage.getAreaCenter())
                .recommendVendor(assetsPackage.getTjFirm())
                .recommendWebsite(assetsPackage.getTjWd())
                .productName(assetsPackage.getCpmc())
                .repayMethod(assetsPackage.getHkType())
                .agingPeriods(assetsPackage.getFz())
                .billAddress(assetsPackage.getBillAddress())
                .yearInterestRate(assetsPackage.getYearRates())
                .dayInterestRate(assetsPackage.getDayRates())
                .firstOverdueSign(assetsPackage.getFirstYqFlag())
                .totalOverdueDay(assetsPackage.getSumYqtsHis())
                .overdueFrequency(assetsPackage.getSumYqcsHis())
                /*.importBatchNo(assetsPackage.getImportBatchNo())*/
                /*.packNo(assetsPackage.getPackNo())*/
                .backCaseDate(assetsPackage.getTar())
                .loanType(assetsPackage.getDklx())
                .stayCaseFlag(assetsPackage.getLaFlag())
                .riskFlag(assetsPackage.getFxFlag())
                .contractType(assetsPackage.getHtlx())
                .reductionFlag(assetsPackage.getJmbq())
                .legalFlag(assetsPackage.getFcbq())
                .ljyhje(assetsPackage.getLjyhje())//累计已还金额
                .build();
        tLcDuncase.setCreateBy(notNullToString(assetsPackage.getSendOptId()));
        return tLcDuncase;
    }

    /**
     * 如果不为空转字符串
     *
     * @param object
     * @return
     */
    private String notNullToString(Object object) {
        if (Objects.nonNull(object)) {
            return object.toString();
        }
        return null;
    }


    /**
     * string类型转Long类型
     *
     * @param accountDate
     * @return
     */
    private Long stringConverLong(String accountDate) {
        if (StringUtils.isEmpty(accountDate)) {
            return null;
        }
        return Long.valueOf(accountDate);
    }

    /**
     * 根据逾期天数判断逾期账龄
     *
     * @param overdueDays
     * @return
     */
    private String getOverterm(Long overdueDays) {
        if (overdueDays == null) {
            return null;
        }
        String overterm = null;
        if (overdueDays <= 30) {
            overterm = "M1";
        } else if (overdueDays > 30 && overdueDays <= 60) {
            overterm = "M2";
        } else if (overdueDays > 60 && overdueDays <= 90) {
            overterm = "M3";
        } else if (overdueDays > 90 && overdueDays <= 120) {
            overterm = "M4";
        } else if (overdueDays > 120 && overdueDays <= 150) {
            overterm = "M5";
        } else if (overdueDays > 150) {
            overterm = "M6";
        }
        return overterm;
    }


}
