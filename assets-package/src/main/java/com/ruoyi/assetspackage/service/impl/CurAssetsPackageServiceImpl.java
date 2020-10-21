package com.ruoyi.assetspackage.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.domain.distribution.*;
import com.ruoyi.assetspackage.domain.score.TLcScore;
import com.ruoyi.assetspackage.enums.AllocatRuleEnum;
import com.ruoyi.assetspackage.enums.ImportTypeEnum;
import com.ruoyi.assetspackage.enums.IsCloseCaseEnum;
import com.ruoyi.assetspackage.enums.IsNoEnum;
import com.ruoyi.assetspackage.exception.ImportDataExcepion;
import com.ruoyi.assetspackage.mapper.CurAssetsPackageMapper;
import com.ruoyi.assetspackage.mapper.distribution.*;
import com.ruoyi.assetspackage.service.*;
import com.ruoyi.assetspackage.service.distribution.ITLcTaskAssetService;
import com.ruoyi.assetspackage.util.*;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.domain.CloseCase;
import com.ruoyi.common.enums.*;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RestTemplateUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 客户资产Service业务层处理
 *
 * @author guozeqi
 * @date 2019-12-25
 */
@Slf4j
@Service
public class CurAssetsPackageServiceImpl extends BaseController implements ICurAssetsPackageService {

    @Autowired
    private CurAssetsPackageMapper curAssetsPackageMapper;
    @Autowired
    RemoteConfigure remoteConfigure;
    @Autowired
    private IOrgPackageService orgPackageService;
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private ITLcImportFlowService tlcImportFlowService;
    @Autowired
    private ITemplatesPackageService templatesPackageService;
    @Autowired
    private ITemplateRelationPackageService templateRelationPackageService;
    @Autowired
    private IDuncaseService tlcDuncaseService;
    @Resource
    private ISysUserService sysUserService;
    @Autowired
    private ITLcScoreService tlcScoreService;
    @Autowired
    private TLcDuncaseAssignAssetMapper tLcDuncaseAssignAssetMapper;
    @Autowired
    private TLcDuncaseAssetMapper tLcDuncaseAssetMapper;
    @Autowired
    private TLcCustJobAssetMapper tLcCustJobAssetMapper;
    @Autowired
    private TLcCustinfoAssetMapper tLcCustinfoAssetMapper;
    @Autowired
    private TLcCustContactAssetMapper tLcCustContactAssetMapper;
    @Autowired
    private TLcAllocatCaseConfigAssetMapper tLcAllocatCaseConfigAssetMapper;
    @Autowired
    private AllocatRuleUtilAsset allocatRuleUtilAsset;
    @Autowired
    private ITLcTaskAssetService tLcTaskAssetService;
    @Autowired
    private ExcelParser excelParser;

    /**
     * 查询客户资产
     *
     * @param id 客户资产ID
     * @return 客户资产
     */
    @Override
    public CurAssetsPackage selectCurAssetsPackageById(String id) {
        return curAssetsPackageMapper.selectCurAssetsPackageById(id);
    }

    /**
     * 查询客户资产列表
     *
     * @param curAssetsPackage 客户资产
     * @return 客户资产
     */
    @Override
    public List<CurAssetsPackage> selectCurAssetsPackageList(CurAssetsPackage curAssetsPackage) {
        if (curAssetsPackage.getEndDate() != null) {
            curAssetsPackage.setEndDate(DateUtils.getEndOfDay(curAssetsPackage.getEndDate()));
        }
        return curAssetsPackageMapper.selectCurAssetsPackageList(curAssetsPackage);
    }

    /**
     * 查询客户资产列表
     *
     * @param curAssetsPackage 客户资产
     * @return 客户资产
     */
    @Override
    public List<CurAssetsPackage> selectCurAssetsPackageByPage(CurAssetsPackage curAssetsPackage) {
        if (curAssetsPackage.getEndDate() != null) {
            curAssetsPackage.setEndDate(DateUtils.getEndOfDay(curAssetsPackage.getEndDate()));
        }
        return curAssetsPackageMapper.selectCurAssetsPackageByPage(curAssetsPackage);
    }

    @Override
    public List<CurAssetsPackage> selectCurAssetsPackageList2(List<String> param, CurAssetsPackage curAssetsPackage) {
        return curAssetsPackageMapper.selectCurAssetsPackageList2(param, curAssetsPackage);
    }

    /**
     * 新增客户资产
     *
     * @param curAssetsPackage 客户资产
     * @return 结果
     */
    @Override
    public int insertCurAssetsPackage(CurAssetsPackage curAssetsPackage) {
        curAssetsPackage.setCreateTime(DateUtils.getNowDate());
        return curAssetsPackageMapper.insertCurAssetsPackage(curAssetsPackage);
    }

    /**
     * 修改客户资产
     *
     * @param curAssetsPackage 客户资产
     * @return 结果
     */
    @Override
    public int updateCurAssetsPackage(CurAssetsPackage curAssetsPackage) {
        return curAssetsPackageMapper.updateCurAssetsPackage(curAssetsPackage);
    }

    /**
     * 删除客户资产对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCurAssetsPackageByIds(String ids) {
        return curAssetsPackageMapper.deleteCurAssetsPackageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户资产信息
     *
     * @param id 客户资产ID
     * @return 结果
     */
    @Override
    public int deleteCurAssetsPackageById(String id) {
        return curAssetsPackageMapper.deleteCurAssetsPackageById(id);
    }

    /**
     * 根据资产包id查询该包的所有资产
     *
     * @param packageId
     * @return
     */
    @Override
    public List<CurAssetsPackage> selectCurAssetsByPackageId(String packageId) {
//        return curAssetsPackageMapper.selectCurAssetsByPackageId(packageId);
        return null;
    }

    @Override
    @Transactional()
    public AjaxResult callRemote(List<CurAssetsPackage> remoteList) {
        try {
            String url = remoteConfigure.getAssetsInterfaceUrl();
            for (CurAssetsPackage assets : remoteList) {
                assets.setCreateBy(ShiroUtils.getLoginName());
            }
            int total = remoteList.size();
            int index = 500;
            int pagesize = total / index;
            if (total <= index) {
                // 分发
                distribution(remoteList);
            } else {
                for (int i = 0; i < pagesize; i++) {
                    List lt = remoteList.subList(i * index, (i + 1) * index);
//                    ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, lt, Response.class);
//                    if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
//                        throw new ImportDataExcepion("分发失败:" + responseResponseEntity.getBody().getMessage());
//                    }
                    distribution(lt);
                }
                if (total % index != 0) {
                    List lt = remoteList.subList(index * pagesize, total);
//                    ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, lt, Response.class);
//                    if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
//                        throw new ImportDataExcepion("分发失败:" + responseResponseEntity.getBody().getMessage());
//                    }
                    distribution(lt);
                }
            }
        } catch (Exception e) {
            log.error("分发失败，error is {}", e);
            throw new RuntimeException("分发失败");
        }
        return AjaxResult.success();
    }

    /**
     * 分发
     */
    private void distribution(List<CurAssetsPackage> curAssetsPackageList) {
        //获取当前用户登录信息
        // 创建案件集合、客户集合、客户单位集合、客户联系人集合
        List<TLcDuncaseAsset> duncaseInsertList = new ArrayList<>(curAssetsPackageList.size());
        List<TLcCustinfoAsset> custInsertList = new ArrayList<>(curAssetsPackageList.size());
        List<TLcCustJobAsset> jobInsertList = new ArrayList<>(curAssetsPackageList.size());
        List<TLcCustContactAsset> contactInsertList = new ArrayList<>(curAssetsPackageList.size() * 3);
        // 查询机构信息
        String orgId = curAssetsPackageList.get(0).getOrgId();
        OrgPackage orgPackage = this.orgPackageService.selectOrgPackageByDeptId(orgId);
        // 遍历资产生成对应的案件、客户基本信息、客户联系人信息、客户工作信息
        curAssetsPackageList.stream().forEach(assetsPackage -> {
            //创建借据信息对象、客户信息对象、客户单位信息对象、客户联系人信息对象并分别加入到对应的集合中
            TLcDuncaseAsset tLcDuncase = createTLcDuncase(assetsPackage, orgPackage);
            TLcCustinfoAsset tLcCustinfo = createCustinfo(assetsPackage, orgPackage);
            TLcCustJobAsset tLcCustJob = createJobInfo(assetsPackage, orgPackage);
            List<TLcCustContactAsset> contacts = createContactList(assetsPackage, orgPackage);
//                // 根据案件号、机构判断该借据是否存在，如果存在则修改，反之新增
//                TLcDuncase duncase = this.tLcDuncaseMapper.findDuncaseByCaseNo(assetsPackage.getOrgCasno(), assetsPackage.getOrgId());
            duncaseInsertList.add(tLcDuncase);
            custInsertList.add(tLcCustinfo);
            jobInsertList.add(tLcCustJob);
            contacts.stream().forEach(contact -> contactInsertList.add(contact));
        });
        // 查询机构的智能分案配置
        TLcAllocatCaseConfigAsset caseConfig = this.tLcAllocatCaseConfigAssetMapper.selectTLcAllocatCaseConfigByOrgId(orgId);
        if (duncaseInsertList != null && duncaseInsertList.size() > 0) {
//            if (org.apache.commons.lang3.StringUtils.isNoneBlank(caseConfig.getRuleEngine()) && caseConfig.getAllocatCaseStartegy().equals(AllocatCaseStartegyEnum.ROBOT_PERSON.getCode())) {
//                // 开始调用规则引擎分配任务、并向任务表同步任务：只有新增的案件需要分配
//                allocatTaskAndSyncTask(duncaseInsertList, caseConfig);
//            } else {
            // 不调用规则引擎系统
            SysUser sysUser = new SysUser();
            sysUser.setDeptId(this.tLcTaskAssetService.findDeptIdByOrgId(orgId));
            List<SysUser> userList = this.tLcTaskAssetService.searchAllUser(sysUser);
            // 创建任务
            List<TLcTaskAsset> taskList = duncaseInsertList.stream()
                    .map(duncase -> createInsertTask(duncase))
                    .collect(Collectors.toList());
            // 判断是否全人工
            if (caseConfig.getAllocatCaseStartegy().equals(AllocatCaseStartegyEnum.ALL_PERSON.getCode())) {
                if (caseConfig.getAutoAllocatCase().equals(IsNoEnum.IS.getCode())) {
                    // 如果开启自动分配任务就将任务状态改为分配中，否则默认未分配
                    taskList.stream().forEach(task -> task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus()));
//                    duncaseInsertList.stream().forEach(duncase -> duncase.setCaseStatus(TaskStatusEnum.ALLOCATING.getStatus()));
                    if (orgPackage.getIsSameCaseDeal().equals(IsNoEnum.IS.getCode())) {
                        // 共案处理
                        if (caseConfig.getAllocatCaseRule().equals(AllocatRuleEnum.DUNCASE_NUM_AVERAGE.getCode())) {
                            // 利用取模法按照数量平均分配任务
                            allocatRuleUtilAsset.averageAllocatTaskByNum(taskList, userList);
                        } else if (caseConfig.getAllocatCaseRule().equals(AllocatRuleEnum.DUNCASE_MONEY_AVERAGE.getCode())) {
                            // 按照逾期金额平均分配任务
                            allocatRuleUtilAsset.averageAllocatTaskByMoney(taskList, userList);
                        } else if (caseConfig.getAllocatCaseRule().equals(AllocatRuleEnum.DUNCASE_MONEY_NUM_AVERAGE.getCode())) {
                            // 按照逾期金额和数量平均分配任务
                            allocatRuleUtilAsset.averageAllocatTaskByMoneyNum(taskList, userList);
                        }
                    } else {
                        // 非共案处理
                        if (caseConfig.getAllocatCaseRule().equals(AllocatRuleEnum.DUNCASE_NUM_AVERAGE.getCode())) {
                            // 利用取模法按照数量平均分配任务
                            allocatRuleUtilAsset.averageAllocatTaskByNumSameDeal2(taskList, userList, orgId);
                        } else if (caseConfig.getAllocatCaseRule().equals(AllocatRuleEnum.DUNCASE_MONEY_AVERAGE.getCode())) {
                            // 按照逾期金额平均分配任务
                            allocatRuleUtilAsset.averageAllocatTaskByMoneySameDeal2(taskList, userList, orgId);
                        } else if (caseConfig.getAllocatCaseRule().equals(AllocatRuleEnum.DUNCASE_MONEY_NUM_AVERAGE.getCode())) {
                            // 按照逾期金额和数量平均分配任务
                            allocatRuleUtilAsset.averageAllocatTaskByMoneyNumSameDeal2(taskList, userList, orgId);
                        }
                    }
                }
                this.tLcTaskAssetService.batchInsertTask(taskList);
            } /*else if (caseConfig.getAllocatCaseStartegy().equals(AllocatCaseStartegyEnum.ALL_ROBOT.getCode())) {
                    // 如果是全人工就将任务类型改为AllocatTaskEnum机器人，默认为1手动
                    taskList.stream().forEach(task -> task.setAllotType(3));
                    // 获取新案机器人呼叫策略规则
                    TLcCallStrategyConfig callStrategyConfig = TLcCallStrategyConfig.builder().businessScene(BusinessSceneEnum.NEW_DUNCASE.getCode()).status(IsNoEnum.IS.getCode()).orgId(String.valueOf(caseConfig.getOrgId())).build();
                    TLcCallStrategyConfig tLcCallStrategyConfig = this.callStrategyConfigService.selectTLcCallStrategyConfigList(callStrategyConfig).get(0);
                    // 获取单次推送到机器人的号码数
                    String taskCallNum = this.sysDictDataService.selectDictLabel("robot_call_config", "task_call_num");
                    // 获取ai坐席数
                    TLcOrgSpeechcraftConf orgSpeechcraftConf = this.orgSpeechcraftConfService.selectTLcOrgSpeechcraftConfByOrgId(caseConfig.getOrgId());
                    if (caseConfig.getRobot().equals("BR")) {
                        if (taskList.size() <= Integer.valueOf(taskCallNum)) {
                            Integer robotTaskId = this.robotMethodUtil.createTask(taskList, callStrategyConfig, tLcCallStrategyConfig.getContinueCallDays(), tLcCallStrategyConfig.getCallFrequencyDay(), orgSpeechcraftConf, DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS,new Date()));
                            taskList.stream().forEach(task -> {
                                task.setRobotCallStrategyId(tLcCallStrategyConfig.getId());
                                task.setRobotTaskId(robotTaskId);
                            });
                            this.tLcTaskAssetService.batchInsertTask(taskList);
                        } else {
                            Integer taskNums = taskList.size() / Integer.valueOf(taskCallNum);
                            for (int i = 0; i < taskNums; i++) {
                                List subTaskIdList = taskList.subList(i * Integer.valueOf(taskCallNum), (i + 1) * Integer.valueOf(taskCallNum));
                                Integer robotTaskId = this.robotMethodUtil.createTask(subTaskIdList, callStrategyConfig, tLcCallStrategyConfig.getContinueCallDays(), tLcCallStrategyConfig.getCallFrequencyDay(), orgSpeechcraftConf, DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS,new Date()));
                                taskList.stream().forEach(task -> {
                                    task.setRobotCallStrategyId(tLcCallStrategyConfig.getId());
                                    task.setRobotTaskId(robotTaskId);
                                });
                                this.tLcTaskAssetService.batchInsertTask(taskList);
                            }
                            if (taskList.size() % Integer.valueOf(taskCallNum) != 0) {
                                List subTaskIdList = taskList.subList(Integer.valueOf(taskCallNum) * taskNums, taskList.size());
                                Integer robotTaskId = this.robotMethodUtil.createTask(subTaskIdList, callStrategyConfig, tLcCallStrategyConfig.getContinueCallDays(), tLcCallStrategyConfig.getCallFrequencyDay(), orgSpeechcraftConf, DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS,new Date()));
                                taskList.stream().forEach(task -> {
                                    task.setRobotCallStrategyId(tLcCallStrategyConfig.getId());
                                    task.setRobotTaskId(robotTaskId);
                                });
                                this.tLcTaskAssetService.batchInsertTask(taskList);
                            }
                        }
                    }
                }*/
//            }
        }
        // 分别插入借据信息表，客户信息表,客户工作表，客户联系人表
        insertData(duncaseInsertList, custInsertList, jobInsertList, contactInsertList);
    }

    private TLcTaskAsset createInsertTask(TLcDuncaseAsset duncase) {
        TLcTaskAsset task = TLcTaskAsset.builder()
                .caseNo(duncase.getCaseNo())
                .certificateNo(duncase.getCertificateNo())
                .certificateType(duncase.getCertificateType())
                .customCode(duncase.getCustomNo())
                .customName(duncase.getCustomName())
                .arrearsTotal(duncase.getAppointCaseBalance())
                .taskStatus(TaskStatusEnum.NO_ALLOCAT.getStatus())
                .modifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()))
                .overdueDays(duncase.getOverdueDays())
                .collectTimeLimit(null)
                .collectLastTime(null)
                .collectTeamId(null)
                .collectTeamName(null)
                .ownerId(duncase.getOwnerId())
                .ownerName(duncase.getOwnerName())
                .orgId(duncase.getOrgId())
                .orgName(duncase.getOrgName())
                .oldOwnerId(null)
                .closeDate(null)
                .validateStatus(IsNoEnum.IS.getCode())
                .allotType(1) // 默认为手动=人工
                .taskType(TaskTypeEnum.FIRST_CREATE.getCode())
                .modifyBy(duncase.getModifyBy())
                .transferType(duncase.getTransferType()) //手别
                .enterCollDate(duncase.getEnterCollDate()) // 入催日
                .closeCaseYhje(duncase.getCloseCaseYhje()) // 结案应还金额
                .importBatchNo(duncase.getImportBatchNo())
                .phone(duncase.getCustomPhone())
                .actionCode(CollActionCodeEnum.FRESH.getMessage())
                .build();
        task.setCreateBy(duncase.getCreateBy());
        return task;
    }

    /**
     * 根据从资产包传过来的借据信息创建借据信息对象
     *
     * @param assetsPackage
     * @return
     */
    private TLcDuncaseAsset createTLcDuncase(CurAssetsPackage assetsPackage, OrgPackage orgPackage) {
        TLcDuncaseAsset tLcDuncase = TLcDuncaseAsset.builder()
                .caseNo(assetsPackage.getOrgCasno()) //案件号
                .customName(assetsPackage.getCurName())   //客户姓名
                .certificateNo(assetsPackage.getCertificateNo()) //身份证号
                .customPhone(assetsPackage.getCustomerMobile()) //客户手机号
                .monthRepayDay(stringConverLong(assetsPackage.getAccountDate())) //账单日
                .overdueDays(stringConverLong(assetsPackage.getOverdueDays())) //逾期天数
//                .repayAccountNo(assetsPackage.getRevertCardNo()) //还款卡号
//                .repayBank(assetsPackage.getRevertCardBlank()) //还款银行
                .modifyBy(assetsPackage.getUpdateId()) //修改人
                .orgId(assetsPackage.getOrgId()) //业务归属机构
                .orgName(orgPackage.getOrgName()) //业务归属机构名称
//                .caseStatus(TaskStatusEnum.NO_ALLOCAT.getStatus()) //案件状态==任务状态
                .firstOverdueTime(assetsPackage.getFirstYqDate()) //首次逾期时间
                .maxOverdueDay(stringConverLong(assetsPackage.getMaxYqtsHis())) //历史最大逾期天数
                .repayDate(assetsPackage.getFirstYqjcDate()) //应还日期-取首次逾期解除日期
                .borrowLine(assetsPackage.getBorrowEd()) //借款额度
                .borrowCardNo(assetsPackage.getBorrowNo()) //借款卡号
                .borrowBank(assetsPackage.getBorrowBlank()) //借款卡银行
                .totalExpensesPayableRmb(assetsPackage.getRmbYhfyzje()) //人民币账户应还费用总金额
                .balanceRmb(assetsPackage.getRmbYe()) //人民币账户余额
                .totalDefaultInterestRmb(assetsPackage.getRmbYhfxzje()) //人民币账户应还罚息总额
//                .fixLimitRmb(assetsPackage.getRmbGded()) //人民币账户额度固定额度
//                .lastRepayAmountRmb(assetsPackage.getRmbLastJkje()) //人民币账户最后缴款金额
                .lastRepayDateRmb(assetsPackage.getZhychkr()) //人民币账户最后一次缴款日
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
                .totalOverdueDay(StringUtils.isNotEmpty(assetsPackage.getSumYqtsHis()) ? Integer.valueOf(assetsPackage.getSumYqtsHis()) : null)
                .overdueFrequency(StringUtils.isNotEmpty(assetsPackage.getSumYqcsHis()) ? Integer.valueOf(assetsPackage.getSumYqcsHis()) : null)
                .importBatchNo(assetsPackage.getImportBatchNo())
                .packNo(assetsPackage.getPackNo())
                .backCaseDate(assetsPackage.getTar())
                .loanType(assetsPackage.getDklx())
                .stayCaseFlag(assetsPackage.getLaFlag())
                .riskFlag(assetsPackage.getFxFlag())
                .contractType(assetsPackage.getHtlx())
                .reductionFlag(assetsPackage.getJmbq())
                .legalFlag(assetsPackage.getFcbq())
                .ljyhje(assetsPackage.getLjyhje())//累计已还金额
                .remark(assetsPackage.getRemark())
                .build();
        tLcDuncase.setCreateBy(notNullToString(assetsPackage.getSendOptId()));
        return tLcDuncase;
    }

    /**
     * 创建客户单位信息对象
     *
     * @param assetsPackage
     * @return
     */
    private TLcCustJobAsset createJobInfo(CurAssetsPackage assetsPackage, OrgPackage orgPackage) {
        TLcCustJobAsset tLcCustJob = TLcCustJobAsset.builder()
//                .customCode(assetsPackage.getCustomerNo())
                .companyTel(assetsPackage.getWorkTel())
                .companyName(assetsPackage.getWorkName())
                .companyAddress(assetsPackage.getWorkAddress())
//                .companyPostcode(assetsPackage.getWorkPostcode())
//                .companyIndustry(assetsPackage.getIndust())
//                .profession(assetsPackage.getJob())
                .certificateNo(assetsPackage.getCertificateNo())
                .modifyBy(assetsPackage.getSendOptId())
                .validateStatus(IsNoEnum.IS.getCode())
                .caseNo(assetsPackage.getOrgCasno())
                .modifyBy(assetsPackage.getUpdateId())
                .orgId(assetsPackage.getOrgId()) //业务归属机构
                .orgName(orgPackage.getOrgName()) //业务归属机构名称
                .importBatchNo(assetsPackage.getImportBatchNo())
                .build();
        tLcCustJob.setCreateBy(notNullToString(assetsPackage.getSendOptId()));
        return tLcCustJob;
    }

    /**
     * 根据从资产包传过来的借据信息创建逾期客户联系人对象
     *
     * @param assetsPackage
     * @return
     */
    private List<TLcCustContactAsset> createContactList(CurAssetsPackage assetsPackage, OrgPackage orgPackage) {
        List<TLcCustContactAsset> tLcCustContactList = new ArrayList<>();
        // 本人
        TLcCustContactAsset selfContact = buildContactCommon(assetsPackage, orgPackage);
        selfContact = selfContact.setContactName(assetsPackage.getCurName())
                .setRelation(ContactRelaEnum.SELE.getCode())
                .setCertificateNo(assetsPackage.getCertificateNo())
                .setPhone(assetsPackage.getCustomerMobile());
        tLcCustContactList.add(selfContact);
        // 本人家庭电话
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getCustomerHomeTel()) && !assetsPackage.getCustomerHomeTel().equals(assetsPackage.getCustomerMobile())) {
            TLcCustContactAsset telContact = buildContactCommon(assetsPackage, orgPackage);
            telContact = telContact.setContactName(assetsPackage.getCurName())
                    .setRelation(ContactRelaEnum.SELE.getCode())
                    .setPhone(assetsPackage.getCustomerHomeTel());
            tLcCustContactList.add(telContact);
        }
        // 第一联系人手机
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getFirstLiaisonName()) && org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getFirstLiaisonMobile())) {
            TLcCustContactAsset firstContact = buildContactCommon(assetsPackage, orgPackage);
            firstContact = firstContact.setContactName(assetsPackage.getFirstLiaisonName())
                    .setRelation(org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getFirstLiaisonRelation()) ? ContactRelaEnum.getCodeByRela(assetsPackage.getFirstLiaisonRelation()) : ContactRelaEnum.UN_MATCH.getCode())
                    .setPhone(assetsPackage.getFirstLiaisonMobile());
            tLcCustContactList.add(firstContact);
        }
        // 第二联系人
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getSecondLiaisonName()) && org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getSecondLiaisonMobile())) {
            TLcCustContactAsset secondContact = buildContactCommon(assetsPackage, orgPackage);
            secondContact = secondContact.setContactName(assetsPackage.getSecondLiaisonName())
                    .setRelation(org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getSecondLiaisonRelation()) ? ContactRelaEnum.getCodeByRela(assetsPackage.getSecondLiaisonRelation()) : ContactRelaEnum.UN_MATCH.getCode())
                    .setPhone(assetsPackage.getSecondLiaisonMobile());
            tLcCustContactList.add(secondContact);
        }
        // 第三联系人
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getThreeLiaisonName()) && org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getThreeLiaisonMobile())) {
            TLcCustContactAsset thirdContact = buildContactCommon(assetsPackage, orgPackage);
            thirdContact = thirdContact.setContactName(assetsPackage.getThreeLiaisonName())
                    .setRelation(org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getThreeLiaisonRelation()) ? ContactRelaEnum.getCodeByRela(assetsPackage.getThreeLiaisonRelation()) : ContactRelaEnum.UN_MATCH.getCode())
                    .setPhone(assetsPackage.getThreeLiaisonMobile());
            tLcCustContactList.add(thirdContact);
        }
        // 第四联系人
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getFourthLiaisonName()) && org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getFourthLiaisonMobile())) {
            TLcCustContactAsset fouthContact = buildContactCommon(assetsPackage, orgPackage);
            fouthContact = fouthContact.setContactName(assetsPackage.getFourthLiaisonName())
                    .setRelation(org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getFourthLiaisonRelation()) ? ContactRelaEnum.getCodeByRela(assetsPackage.getFourthLiaisonRelation()) : ContactRelaEnum.UN_MATCH.getCode())
                    .setPhone(assetsPackage.getFourthLiaisonMobile());
            tLcCustContactList.add(fouthContact);
        }
        // 第5联系人
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getFifthLiaisonName()) && org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getFifthLiaisonMobile())) {
            TLcCustContactAsset fifthContact = buildContactCommon(assetsPackage, orgPackage);
            fifthContact = fifthContact.setContactName(assetsPackage.getFifthLiaisonName())
                    .setRelation(org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getFifthLiaisonRelation()) ? ContactRelaEnum.getCodeByRela(assetsPackage.getFifthLiaisonRelation()) : ContactRelaEnum.UN_MATCH.getCode())
                    .setPhone(assetsPackage.getFifthLiaisonMobile());
            tLcCustContactList.add(fifthContact);
        }
        // 单位联系人
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getWorkTel())) {
            TLcCustContactAsset organContact = buildContactCommon(assetsPackage, orgPackage);
            organContact = organContact.setContactName("单位电话")
                    .setRelation(ContactRelaEnum.getCodeByRela("单位"))
                    .setPhone(assetsPackage.getWorkTel());
            tLcCustContactList.add(organContact);
        }
        // 客户电话2
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getCustomerMobile2()) && !assetsPackage.getCustomerMobile2().equals(assetsPackage.getCustomerMobile())) {
            TLcCustContactAsset selfContact2 = buildContactCommon(assetsPackage, orgPackage);
            selfContact2 = selfContact2.setContactName(assetsPackage.getCurName())
                    .setRelation(ContactRelaEnum.SELE.getCode())
                    .setPhone(assetsPackage.getCustomerMobile2());
            tLcCustContactList.add(selfContact2);
        }
        // 客户电话3
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getCustomerMobile3()) && !assetsPackage.getCustomerMobile3().equals(assetsPackage.getCustomerMobile())) {
            TLcCustContactAsset selfContact3 = buildContactCommon(assetsPackage, orgPackage);
            selfContact3 = selfContact3.setContactName(assetsPackage.getCurName())
                    .setRelation(ContactRelaEnum.SELE.getCode())
                    .setPhone(assetsPackage.getCustomerMobile3());
            tLcCustContactList.add(selfContact3);
        }
        // 客户电话4
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(assetsPackage.getCustomerMobile4()) && !assetsPackage.getCustomerMobile4().equals(assetsPackage.getCustomerMobile())) {
            TLcCustContactAsset selfContact4 = buildContactCommon(assetsPackage, orgPackage);
            selfContact4 = selfContact4.setContactName(assetsPackage.getCurName())
                    .setRelation(ContactRelaEnum.SELE.getCode())
                    .setPhone(assetsPackage.getCustomerMobile4());
            tLcCustContactList.add(selfContact4);
        }
        return tLcCustContactList;
    }

    /**
     * 构建客户联系人公共属性
     *
     * @param assetsPackage
     * @return
     */
    private TLcCustContactAsset buildContactCommon(CurAssetsPackage assetsPackage, OrgPackage orgPackage) {
        TLcCustContactAsset contact = TLcCustContactAsset.builder()
                .modifyBy(assetsPackage.getSendOptId())
                .address(null)
                .validateStatus(IsNoEnum.IS.getCode())
                .origin(ContactOriginEnum.ASSET_IMPORT.getCode())
                .caseNo(assetsPackage.getOrgCasno())
                .modifyBy(assetsPackage.getUpdateId())
                .orgId(assetsPackage.getOrgId()) //业务归属机构
                .orgName(orgPackage.getOrgName()) //业务归属机构名称
                .importBatchNo(assetsPackage.getImportBatchNo())
                .build();
        contact.setCreateBy(notNullToString(assetsPackage.getSendOptId()));
        return contact;
    }

    /**
     * 根据从资产包传过来的借据信息创建逾期客户对象
     *
     * @param assetsPackage
     * @return
     */
    private TLcCustinfoAsset createCustinfo(CurAssetsPackage assetsPackage, OrgPackage orgPackage) {
        TLcCustinfoAsset tLcCustinfo = TLcCustinfoAsset.builder()
//                .customCode(assetsPackage.getCustomerNo()) //客户编号
                .certificateAddress(assetsPackage.getCertificateAddress()) //证件地址
                .censusAddress(assetsPackage.getRegistAddress()) //户籍地址
//                .profession(assetsPackage.getJob()) //职业身份
//                .city(assetsPackage.getCity())
                .education(assetsPackage.getEducation())
                .phone(assetsPackage.getCustomerMobile())
                .tel(assetsPackage.getCustomerHomeTel())
                .email(assetsPackage.getEmail())
//                .incomeYear(assetsPackage.getCurIncome())
                .marrageStatus(org.apache.commons.lang3.StringUtils.isNoneBlank(assetsPackage.getMarriage()) ? MarriageStatusEnum.getCodeByDes(assetsPackage.getMarriage()) : null)
                .hasChild(null)
                .hasHouse(null)
                .hasCar(null)
                .orgId(assetsPackage.getOrgId())
                .orgName(orgPackage.getOrgName())
                .address(assetsPackage.getCustomerHomeAddress())
                .modifyBy(assetsPackage.getSendOptId())
                .validateStatus(IsNoEnum.IS.getCode())
                .certificateNo(assetsPackage.getCertificateNo())
                .customName(assetsPackage.getCurName())
                .customSex(SexEnum.getCodeByMessage(assetsPackage.getCurSex()))
//                .birthday(assetsPackage.getBirthday())
//                .certificateType(CertificateTypeEnum.getCodeByType(assetsPackage.getCertificateType()))
                .caseNo(assetsPackage.getOrgCasno())
                .modifyBy(assetsPackage.getUpdateId())
                .importBatchNo(assetsPackage.getImportBatchNo())
                .build();
        tLcCustinfo.setCreateBy(notNullToString(assetsPackage.getSendOptId()));
        return tLcCustinfo;
    }

    private void insertData(List<TLcDuncaseAsset> duncaseInsertList, List<TLcCustinfoAsset> custInsertList, List<TLcCustJobAsset> jobInsertList, List<TLcCustContactAsset> contactInsertList) {
        if (duncaseInsertList != null && duncaseInsertList.size() > 0) {
            this.tLcDuncaseAssetMapper.batchInsertDuncase(duncaseInsertList);
            this.tLcCustinfoAssetMapper.batchInsertCustinfo(custInsertList);
            this.tLcCustJobAssetMapper.batchInsertCustJob(jobInsertList);
            this.tLcCustContactAssetMapper.batchInsertContact(contactInsertList);
            // 将案件信息插入到案件轨迹表
            batchInsertDuncaseAssign(duncaseInsertList);
        }
    }

    /**
     * 将案件信息插入到案件轨迹表
     *
     * @param duncaseInsertList
     */
    private void batchInsertDuncaseAssign(List<TLcDuncaseAsset> duncaseInsertList) {
        SysUser sysUser = this.sysUserService.selectUserById(Long.valueOf(duncaseInsertList.get(0).getCreateBy()));
        List<TLcDuncaseAssignAsset> duncaseAssignList = duncaseInsertList.stream()
                .map(duncase -> {
                    TLcDuncaseAssignAsset tLcDuncaseAssign = TLcDuncaseAssignAsset.builder()
                            .caseNo(duncase.getCaseNo())
                            .certificateNo(duncase.getCertificateNo())
                            .collectTeamId(null)
                            .collectTeamName(null)
                            .customName(duncase.getCustomName())
                            .operationId(sysUser.getUserId())
                            .operationName(sysUser.getUserName())
                            .ownerId(duncase.getOwnerId())
                            .orgName(duncase.getOwnerName())
                            .orgId(duncase.getOrgId())
                            .orgName(duncase.getOrgName())
                            .taskId(null)
                            .taskStatus(2)
                            .transferType(TaskTypeEnum.FIRST_CREATE.getCode())
                            .validateStatus(IsNoEnum.IS.getCode())
                            .build();
                    tLcDuncaseAssign.setCreateBy(notNullToString(sysUser.getUserId()));
                    return tLcDuncaseAssign;
                }).collect(Collectors.toList());
        this.tLcDuncaseAssignAssetMapper.batchInsertDuncaseAssign(duncaseAssignList);
    }

    /**
     * string类型转Long类型
     *
     * @param accountDate
     * @return
     */
    private Long stringConverLong(String accountDate) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(accountDate)) {
            return null;
        }
        return Long.valueOf(accountDate);
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

    @Override
    @Transactional()
    public AjaxResult callRemoteUpdate(List<CurAssetsPackage> remoteList) {
        try {
            String url = remoteConfigure.getAssetsUpdateInterfaceUrl();
            for (CurAssetsPackage assets : remoteList) {
                assets.setCreateBy(ShiroUtils.getLoginName());
            }
            int total = remoteList.size();
            int index = 500;
            int pagesize = total / index;
            if (total <= index) {
                ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, remoteList, Response.class);
                if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
                    throw new ImportDataExcepion("调用更新接口调用失败:" + responseResponseEntity.getBody().getMessage());
                }
            } else {
                for (int i = 0; i < pagesize; i++) {
                    List lt = remoteList.subList(i * index, (i + 1) * index);
                    ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, lt, Response.class);
                    if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
                        throw new ImportDataExcepion("调用更新接口调用失败:" + responseResponseEntity.getBody().getMessage());
                    }
                }
                if (total % index != 0) {
                    List lt = remoteList.subList(index * pagesize, total);
                    ResponseEntity<Response> responseResponseEntity = restTemplateUtil.getRestTemplate().postForEntity(url, lt, Response.class);
                    if (responseResponseEntity.getStatusCodeValue() != HttpStatus.OK.value() || responseResponseEntity.getBody().getStatus() != HttpStatus.OK.value()) {
                        throw new ImportDataExcepion("调用更新接口调用失败:" + responseResponseEntity.getBody().getMessage());
                    }
                }
            }

        } catch (Exception e) {
            log.error("调用更新接口调用失败，error is {}", e);
            throw new RuntimeException("调用更新接口调用失败");
        }
        return AjaxResult.success();
    }



    /*@Override
    public Response closeCase(List<CloseCase> caseList) {
        try {
            caseList.stream()
                    .forEach(assetCloseCase -> {
                        CurAssetsPackage curAssetsPackage = new CurAssetsPackage();
                        curAssetsPackage.setOrgCasno(assetCloseCase.getCaseNo());
                        curAssetsPackage.setCloseCase(IsCloseCaseEnum.NOT_CLOSE_CASE.getValue());
                        List<CurAssetsPackage> curAssetsPackageList = this.curAssetsPackageMapper.selectCurAssetsPackageList(curAssetsPackage);
                        if (curAssetsPackageList == null || curAssetsPackageList.size() == 0) {
                            log.error("案件结束失败，资产管理案件不存在，案件编号是{}", assetCloseCase.getCaseNo());
//                            throw new RuntimeException("案件结束失败，资产管理案件不存在");
                        } else {
                            curAssetsPackage = curAssetsPackageList.get(0);
                            curAssetsPackage.setOrgCasno(assetCloseCase.getCaseNo());
                            curAssetsPackage.setCloseCase(IsCloseCaseEnum.CLOSE_CASE.getValue());
                            curAssetsPackage.setIsExitCollect(assetCloseCase.getIsExitCollect());
                            curAssetsPackage.setCloseCaseDate(new Date());
                            if(String.valueOf(IsNoEnum.IS.getCode()).equals(assetCloseCase.getIsExitCollect())){
                                curAssetsPackage.setAjhssj(new Date());
                            }
                            this.curAssetsPackageMapper.updateCurAssetsPackage(curAssetsPackage);
                        }
                    });
            return Response.success(Response.ResponseStatusEnum.SUCCESS.getStatus(), Response.ResponseStatusEnum.SUCCESS.getMessage(), null);
        } catch (Exception e) {
            log.error("结案失败，exception is {}", e);
            return Response.success(Response.ResponseStatusEnum.SUCCESS.getStatus(), e.getMessage(), null);
        }
    }*/

    @Override
    public void closeCase(List<CloseCase> caseList) {
        try {
            List<CurAssetsPackage> curAssetsPackageList = BuildCloseCaseParam(caseList);
            this.curAssetsPackageMapper.updateCloseCase2(curAssetsPackageList);
        } catch (Exception e) {
            log.error("结案失败，exception is {}", e);
        }
    }

    public List<CurAssetsPackage> BuildCloseCaseParam(List<CloseCase> caseList) throws Exception {
        List<CurAssetsPackage> resultList = new ArrayList<>();
        caseList.stream()
                .forEach(assetCloseCase -> {
                    CurAssetsPackage curAssetsPackage = new CurAssetsPackage();
                    curAssetsPackage.setOrgCasno(assetCloseCase.getCaseNo());
                    curAssetsPackage.setCloseCase(IsCloseCaseEnum.CLOSE_CASE.getValue());
                    curAssetsPackage.setCloseCaseDate(new Date());
                    curAssetsPackage.setIsExitCollect(assetCloseCase.getIsExitCollect());
                    if (String.valueOf(IsNoEnum.IS.getCode()).equals(assetCloseCase.getIsExitCollect())) {
                        curAssetsPackage.setAjhssj(new Date());
                    }
                    resultList.add(curAssetsPackage);
                });
        return resultList;
    }

    @Override
    public CurAssetsPackage selectCurAssetsPackageByCaseNo(Map<String, String> param) {
        return this.curAssetsPackageMapper.selectCurAssetsPackageByCaseNo(param);
    }


    /**
     * 插入临时表
     */
    @Override
    public void batchAddTemp(List<TempCurAssetsPackage> paramList) throws Exception {

        int total = paramList.size();
        int index = 500;
        int pagesize = total / index;
        if (total <= index) {
            this.curAssetsPackageMapper.batchAddTemp(paramList);
        } else {
            for (int i = 0; i < pagesize; i++) {
                List lt = paramList.subList(i * index, (i + 1) * index);
                this.curAssetsPackageMapper.batchAddTemp(lt);

            }
            if (total % index != 0) {
                List lt = paramList.subList(index * pagesize, total);
                this.curAssetsPackageMapper.batchAddTemp(lt);
            }
        }

    }


    /**
     * 批量添加资产
     *
     * @param paramList
     * @throws Exception
     */
    @Override
    public void batchAddAssets(List<TempCurAssetsPackage> paramList) throws Exception {
        String orgId = paramList.get(0).getOrgId();
        String orgName = paramList.get(0).getOrg();
        String importBatchNo = paramList.get(0).getImportBatchNo();

        //导入总金额
        BigDecimal totalMoney = new BigDecimal(0.00);
        // 新增流水集合，如果案件是修改的话，不修改批次号
        List<TempCurAssetsPackage> tLcImportFlowList = new ArrayList<>(paramList.size());
        //参数计算、赋值
        for (TempCurAssetsPackage tempCurAssetsPackage : paramList) {
            totalMoney = totalMoney.add(tempCurAssetsPackage.getRmbYe() != null ? new BigDecimal(tempCurAssetsPackage.getRmbYe()) : new BigDecimal(0.00));
            tLcImportFlowList.add(tempCurAssetsPackage);
        }

        int total = paramList.size();
        int index = 500;
        int pagesize = total / index;
        if (total <= index) {
            this.curAssetsPackageMapper.batchAddAssets(paramList);
        } else {
            for (int i = 0; i < pagesize; i++) {
                List lt = paramList.subList(i * index, (i + 1) * index);
                this.curAssetsPackageMapper.batchAddAssets(lt);

            }
            if (total % index != 0) {
                List lt = paramList.subList(index * pagesize, total);
                this.curAssetsPackageMapper.batchAddAssets(lt);
            }
        }

        //插入流水表
        if (tLcImportFlowList != null && tLcImportFlowList.size() > 0) {
            TLcImportFlow tLcImportFlow = new TLcImportFlow();
            tLcImportFlow.setImportBatchNo(importBatchNo)
                    .setImportType(ImportTypeEnum.ASSET_TEMPLETE.getCode())
                    .setOrgId(orgId)
                    .setOrgName(orgName)
                    .setTotalNum(tLcImportFlowList.size())
                    .setTotalMoney(totalMoney)
                    .setCreateBy(String.valueOf(ShiroUtils.getUserId()));
            this.tlcImportFlowService.insertTLcImportFlow(tLcImportFlow);
        }
        OrgPackage orgPackage = orgPackageService.selectOrgPackageByOrgId(orgId);
        if ("1".equals(orgPackage.getIsAutoScore())) {
            //插入催收评分表
            List<TLcScore> tLcScores = tlcScoreService.buildParam(paramList);
            tlcScoreService.batchInsert(tLcScores);
        }
    }


    /**
     * 用临时表的数据更新主表
     *
     * @param param
     * @throws Exception
     */
    @Override
    public void updateCurAssetsPackage2(TempCurAssetsPackage param, List<CurAssetsPackage> remoteList) throws Exception {
        CurAssetsPackage selectParam = new CurAssetsPackage();
        selectParam.setOrgCasno(param.getOrgCasno());
        selectParam.setOrgId(param.getOrgId());
        List<Map<String, Object>> findResult = this.curAssetsPackageMapper.selectCurAssetsPackageList3(selectParam);
        String curAssetsId = (String) findResult.get(0).get("id");
        param.setId(curAssetsId);
        this.curAssetsPackageMapper.updateCurAssetsPackage2(param);
        //查询资产信息
        CurAssetsPackage curAssetsPackage = this.curAssetsPackageMapper.selectCurAssetsPackageById(curAssetsId);
        curAssetsPackage.setUpdateId(ShiroUtils.getUserId());
        remoteList.add(curAssetsPackage);
    }

    /**
     * 根据条件查询临时表
     *
     * @param param
     * @throws Exception
     */
    @Override
    public List<TempCurAssetsPackage> selectTempCurAssetsPackageList(TempCurAssetsPackage param) throws Exception {
        return this.curAssetsPackageMapper.selectTempCurAssetsPackageList(param);
    }


    /**
     * 清空临时表
     *
     * @throws Exception
     */
    @Override
    public void deleteTempCurAssetsPackage(String importBatchNo) throws Exception {
        curAssetsPackageMapper.deleteTempCurAssetsPackage(importBatchNo);
    }

    /**
     * 根据机构案件号和委托方查询主表
     *
     * @param curAssetsPackage
     * @return
     */
    @Override
    public List<Map<String, Object>> selectCurAssetsPackageList3(CurAssetsPackage curAssetsPackage) {
        return curAssetsPackageMapper.selectCurAssetsPackageList3(curAssetsPackage);
    }

    /**
     * 过滤临时表中的资产
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public List<TempCurAssetsPackage> selectTempCurAssetsPackageList2(List<String> param) throws Exception {
        return curAssetsPackageMapper.selectTempCurAssetsPackageList2(param);
    }

    /**
     * 更新异常状态
     *
     * @throws Exception
     */
    @Override
    public int updateExceptionStatus(String id) throws Exception {
        return curAssetsPackageMapper.updateExceptionStatus(id);
    }


    /**
     * 查询临时表需要更新的数据
     *
     * @param improtBatchNo
     * @return
     * @throws Exception
     */
    @Override
    public List<TempCurAssetsPackage> selectUpdateList(String improtBatchNo) throws Exception {
        return curAssetsPackageMapper.selectUpdateList(improtBatchNo);
    }

    /**
     * 根据id修改状态为更新状态
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int modifyUpdateStatus(String id) throws Exception {
        return curAssetsPackageMapper.modifyUpdateStatus(id);
    }

    /**
     * 根据批次号查询需要新增的集合
     *
     * @param improtBatchNo
     * @return
     * @throws Exception
     */
    @Override
    public List<TempCurAssetsPackage> selectInsertList(String improtBatchNo) throws Exception {
        return curAssetsPackageMapper.selectInsertList(improtBatchNo);
    }


    @Override
    public int selectUpdateCount(String improtBatchNo) throws Exception {
        return this.curAssetsPackageMapper.selectUpdateCount(improtBatchNo);
    }

    @Override
    public int selectInsertCount(String improtBatchNo) throws Exception {
        return this.curAssetsPackageMapper.selectInsertCount(improtBatchNo);
    }

    /**
     * 批量更新状态
     *
     * @param improtBatchNo
     * @throws Exception
     */
    @Override
    public void modifyUpdateStatus2(String improtBatchNo) throws Exception {
        this.curAssetsPackageMapper.modifyUpdateStatus2(improtBatchNo);
    }

    /**
     * 查出需要更新的数据
     *
     * @param improtBatchNo
     * @return
     * @throws Exception
     */
    @Override
    public List<TempCurAssetsPackage> selectUpdateList2(String improtBatchNo) throws Exception {
        return this.curAssetsPackageMapper.selectUpdateList2(improtBatchNo);
    }


    @Override
    public CurAssetsPackage selectAsset(String orgCaseNo, String importBatchNo) throws Exception {
        CurAssetsPackage param = new CurAssetsPackage();
        param.setOrgCasno(orgCaseNo);
        param.setImportBatchNo(importBatchNo);
        CurAssetsPackage curAssets = this.curAssetsPackageMapper.selectCurAssetsPackageList(param).get(0);
        curAssets.setPackNo(curAssets.getPackageId());
        return curAssets;
    }

    @Override
    public CurAssetsPackage selectHisAsset(String orgCaseNo, String importBatchNo) throws Exception {
        CurAssetsPackage param = new CurAssetsPackage();
        param.setOrgCasno(orgCaseNo);
        param.setImportBatchNo(importBatchNo);
        CurAssetsPackage curAssets = this.curAssetsPackageMapper.selectHisCurAssetsPackageList(param).get(0);
        curAssets.setPackNo(curAssets.getPackageId());
        return curAssets;
    }

    @Override
    public List<Map<String, String>> findNotExistsList(String improtBatchNo) throws Exception {
        return this.curAssetsPackageMapper.findNotExistsList(improtBatchNo);
    }

    @Override
    public List<TempCurAssetsPackage> findNormalList(String improtBatchNo) throws Exception {
        return this.curAssetsPackageMapper.findNormalList(improtBatchNo);
    }

    @Override
    public void updateHandler(HttpServletRequest request, String importBatchNo) throws Exception {
        List<TempCurAssetsPackage> updateList = this.curAssetsPackageMapper.selectUpdateList2(importBatchNo);
        //分批次批量执行
        if (updateList.size() > 0) {
            int total = updateList.size();
            int index = 500;
            int pagesize = total / index;
            if (total <= index) {
                this.curAssetsPackageMapper.updateCurAssetsPackage3(updateList);
                updateCollect(updateList);
            } else {
                for (int i = 0; i < pagesize; i++) {
                    List lt = updateList.subList(i * index, (i + 1) * index);
                    this.curAssetsPackageMapper.updateCurAssetsPackage3(lt);
                    updateCollect(lt);

                }
                if (total % index != 0) {
                    List lt = updateList.subList(index * pagesize, total);
                    this.curAssetsPackageMapper.updateCurAssetsPackage3(lt);
                    updateCollect(lt);
                }
            }
        }


    }

    /**
     * 更新导入处理
     *
     * @param request
     * @param importBatchNo
     * @throws Exception
     */
    @Override
    @Transactional
    public void updateImortHandler(HttpServletRequest request, String importBatchNo) throws Exception {
        /**查出正常需要更新的集合*/
        List<TempCurAssetsPackage> normalList = this.curAssetsPackageMapper.findNormalList(importBatchNo);
        /**流水表参数组装*/
        String orgId = normalList.get(0).getOrgId();
        String orgName = normalList.get(0).getOrg();
        //导入总金额
        BigDecimal totalMoney = new BigDecimal(0.00);
        // 新增流水集合，如果案件是修改的话，不修改批次号
        List<TempCurAssetsPackage> tLcImportFlowList = new ArrayList<>(normalList.size());
        //参数计算、赋值
        for (TempCurAssetsPackage tempCurAssetsPackage : normalList) {
            totalMoney = totalMoney.add(tempCurAssetsPackage.getRmbYe() != null ? new BigDecimal(tempCurAssetsPackage.getRmbYe()) : new BigDecimal(0.00));
            tLcImportFlowList.add(tempCurAssetsPackage);
        }
        /**插入流水表*/
        if (tLcImportFlowList != null && tLcImportFlowList.size() > 0) {
            TLcImportFlow tLcImportFlow = new TLcImportFlow();
            tLcImportFlow.setImportBatchNo(importBatchNo)
                    .setImportType(ImportTypeEnum.UPDATE_TEMPLETE.getCode())
                    .setOrgId(orgId)
                    .setOrgName(orgName)
                    .setTotalNum(tLcImportFlowList.size())
                    .setTotalMoney(totalMoney)
                    .setCreateBy(String.valueOf(ShiroUtils.getUserId()));
            this.tlcImportFlowService.insertTLcImportFlow(tLcImportFlow);
        }
        //分批次批量执行
        int total = normalList.size();
        int index = 500;
        int pagesize = total / index;
        if (total <= index) {
            /**执行更新*/
            this.curAssetsPackageMapper.updateCurAssetsPackage3(normalList);
            /**更新案件、表任务表*/
            updateCollect(normalList);
            /**插入自由导入表*/
            this.insertFreeImport(normalList);
        } else {
            for (int i = 0; i < pagesize; i++) {
                List lt = normalList.subList(i * index, (i + 1) * index);
                /**执行更新*/
                this.curAssetsPackageMapper.updateCurAssetsPackage3(lt);
                /**更新案件、表任务表*/
                updateCollect(lt);
                /**插入自由导入表*/
                this.insertFreeImport(lt);

            }
            if (total % index != 0) {
                List lt = normalList.subList(index * pagesize, total);
                /**执行更新*/
                this.curAssetsPackageMapper.updateCurAssetsPackage3(lt);
                /**更新案件、表任务表*/
                updateCollect(lt);
                /**插入自由导入表*/
                this.insertFreeImport(lt);
            }
        }
    }

    @Override
    public List<Map<String, String>> selectFreeImportByCaseno(String orgCasno) throws Exception {
        return this.curAssetsPackageMapper.selectFreeImportByCaseno(orgCasno);
    }

    @Override
    public List<Map<String, String>> selectHisFreeImportByCaseno(String orgCasno) throws Exception {
        return this.curAssetsPackageMapper.selectHisFreeImportByCaseno(orgCasno);
    }

    @Override
    public void updateCloseCase(List<CurAssetsPackage> CurAssetsList) throws Exception {
        this.curAssetsPackageMapper.updateCloseCase(CurAssetsList);
    }

    @Override
    public void updateNoCloseCase(List<CurAssetsPackage> CurAssetsList) throws Exception {
        this.curAssetsPackageMapper.updateNoCloseCase(CurAssetsList);
    }

    @Override
    public void clearTempTable() {
        this.curAssetsPackageMapper.clearTempTable();
    }


    @Override
    @Transactional
    public String handler(HttpServletRequest request, MultipartFile file, String templateId, String orgId, String type) throws Exception {
        List<CurAssetsPackage> paramList = null;
        List<TempCurAssetsPackage> paramList2 = null;
        InputStream stream = null;
        String fileName = "";
        String fileNameFull = "";
        String importBatchNo = "";
        TemplatesPackage templatesPackage = this.templatesPackageService.selectTemplatesPackageById(templateId);
        // 本地资源路径
        String localPath = Global.getProfile();
        // 数据库资源地址
        String uploadDir = localPath + StringUtils.substringAfter(templatesPackage.getUrl(), Constants.RESOURCE_PREFIX);
        File dirPath = new File(uploadDir);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        if (file != null) {
            stream = file.getInputStream();
            fileName = file.getOriginalFilename();
            String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);

            //组装文件路径写入磁盘
            fileNameFull = uploadDir + fileName;
            OutputStream bos = new FileOutputStream(fileNameFull);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.close();
            stream.close();
            orgId = String.valueOf(ShiroUtils.getSysUser().getOrgId());
            ImportDataMapping importDataMapping = SpringUtils.getBean(ImportDataMapping.class);
            importDataMapping = this.voluation(importDataMapping, templateId);
            int headNum = Integer.valueOf(importDataMapping.getHeadRowNum());
            int dataNum = Integer.valueOf(importDataMapping.getDataRowNum());
            List<Map<String, String>> datas = ParseExcelUtil.resolveExcel(fileNameFull, headNum, dataNum);
            //自由导入数据组装、更新
            String templateType = templatesPackage.getType();
            if ("4".equals(templateType)) {//更新模板
                //查询自由导入匹配关系
                List<String> freeRelation = this.templateRelationPackageService.selectFreeTemplateRelationBytemplateId(templateId);
                datas = DataImportUtil.dataReplace2(datas, importDataMapping, freeRelation);
            } else {
                datas = DataImportUtil.dataReplace(datas, importDataMapping);
            }
            importBatchNo = DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS, new Date());// 生成导入批次号年月日时分秒
            OrgPackage orgPackage = this.orgPackageService.selectOrgPackageByDeptId(orgId);
            String orgName = orgPackage.getOrgName();
            paramList2 = DataImportUtil.dataConvert(datas, orgId, importBatchNo, orgName);
            //插入临时表
            this.batchAddTemp(paramList2);
            datas = null;
        }
        return importBatchNo;
    }

    @Override
    public Map<String, String> checkData(HttpServletRequest request, String importBatchNo) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        List<Map<String, String>> exectionList = null;
        List<String> exceptionIds = new ArrayList<String>();//异常的机构案件号
        //查询临时表信息
        TempCurAssetsPackage param = new TempCurAssetsPackage();
        param.setImportBatchNo(importBatchNo);
        List<TempCurAssetsPackage> tempCurAssetList = this.selectTempCurAssetsPackageList(param);
        //规则校验
        exectionList = DataImportUtil.checkData2(tempCurAssetList);
        //去除异常数据
        if (exectionList.size() > 0) {
            for (Map<String, String> exceptionidmap : exectionList) {
                String exceptionid = exceptionidmap.get("id");
                exceptionIds.add(exceptionid);
                //更新状态
                this.updateExceptionStatus(exceptionid);
            }
        }
        //查询需要更新的数据集合
        int updateCount = this.selectUpdateCount(importBatchNo);
        //修改状态为需要更新状态
        if (updateCount > 0) {
            this.modifyUpdateStatus2(importBatchNo);
        }
        //查询需要新增的数据集合
        int insertCount = this.selectInsertCount(importBatchNo);

        request.getSession().setAttribute("exectionList", exectionList);
        map.put("insertSize", String.valueOf(insertCount));
        map.put("updateSize", String.valueOf(updateCount));
        map.put("exectionSize", String.valueOf(exectionList.size()));

        return map;
    }

    @Override
    public Map<String, String> checkUpdateData(HttpServletRequest request, String importBatchNo) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        List<Map<String, String>> exectionList = null;
        //查询临时表信息
        TempCurAssetsPackage param = new TempCurAssetsPackage();
        param.setImportBatchNo(importBatchNo);
        List<TempCurAssetsPackage> tempCurAssetList = this.selectTempCurAssetsPackageList(param);
        //规则校验
        exectionList = DataImportUtil.checkUpdateData(tempCurAssetList);
        //去除异常数据
        if (exectionList.size() > 0) {
            for (Map<String, String> exceptionidmap : exectionList) {
                String exceptionid = exceptionidmap.get("id");
                //更新状态
                this.updateExceptionStatus(exceptionid);
            }
        }
        //去除不存在的数据
        this.curAssetsPackageMapper.updateNotExists(importBatchNo);
        //查询不存在的数据数量
        int notExistsCount = this.curAssetsPackageMapper.findNotExistsCount(importBatchNo);
        //查询正常的的数据数量
        int normalCount = this.curAssetsPackageMapper.findNormalCount(importBatchNo);

        request.getSession().setAttribute("exectionList", exectionList);
        map.put("exectionCount", String.valueOf(exectionList.size()));
        map.put("notExistsCount", String.valueOf(notExistsCount));
        map.put("normalCount", String.valueOf(normalCount));

        return map;
    }


    @Override
    public ImportDataMapping voluation(ImportDataMapping bean, String templateId) {
        /** 查询匹配关系 */
        //根据模板名称查询模板信息
        TemplatesPackage templateInfo = templatesPackageService.selectTemplatesPackageById(templateId);
        String headRowNum = templateInfo.getHeadRowNum();
        String dataRowNum = templateInfo.getDataRowNum();
        //根据模板id查询模板表头映射关系
        List<TemplateRelationPackage> relations = templateRelationPackageService.selectTemplateRelationPackageListBytemplateId(templateId);

        /** 给bean赋值 */
        bean.setHeadRowNum(headRowNum);//表头行
        bean.setDataRowNum(dataRowNum);//数据行
        for (TemplateRelationPackage relation : relations) {
            String systemTemplateName = relation.getSystemTemplateName();
            String customerTemplateName = relation.getCustomerTemplateName();

            if ("机构案件号".equals(systemTemplateName)) {
                bean.setOrgCasNo(customerTemplateName);
                continue;
            }
            if ("所属机构".equals(systemTemplateName)) {
                bean.setOrg(customerTemplateName);
                continue;
            }
            if ("案件回收时间".equals(systemTemplateName)) {
                bean.setAjhssj(customerTemplateName);
                continue;
            }
            if ("手别".equals(systemTemplateName)) {
                bean.setTransferType(customerTemplateName);
                continue;
            }
            if ("姓名".equals(systemTemplateName)) {
                bean.setCurName(customerTemplateName);
                continue;
            }
            if ("证件号".equals(systemTemplateName)) {
                bean.setCertificateNo(customerTemplateName);
                continue;
            }
            if ("性别".equals(systemTemplateName)) {
                bean.setCurSex(customerTemplateName);
                continue;
            }
            if ("婚姻状况".equals(systemTemplateName)) {
                bean.setMarriage(customerTemplateName);
                continue;
            }
            if ("教育程度".equals(systemTemplateName)) {
                bean.setEducation(customerTemplateName);
                continue;
            }
            if ("委案金额".equals(systemTemplateName)) {
                bean.setRmbYe(customerTemplateName);
                continue;
            }
            if ("结案应还金额".equals(systemTemplateName)) {
                bean.setWaYe(customerTemplateName);
                continue;
            }
            if ("入催日".equals(systemTemplateName)) {
                bean.setRcr(customerTemplateName);
                continue;
            }
            if ("账单日".equals(systemTemplateName)) {
                bean.setAccountDate(customerTemplateName);
                continue;
            }
            if ("逾期天数".equals(systemTemplateName)) {
                bean.setOverdueDays(customerTemplateName);
                continue;
            }
            if ("逾期起始日".equals(systemTemplateName)) {
                bean.setFirstYqDate(customerTemplateName);
                continue;
            }
            if ("应还日期".equals(systemTemplateName)) {
                bean.setFirstYqjcDate(customerTemplateName);
                continue;
            }
            if ("欠款总金额".equals(systemTemplateName)) {
                bean.setRmbQkzje(customerTemplateName);
                continue;
            }
            if ("最低应还金额".equals(systemTemplateName)) {
                bean.setRmbZdyhje(customerTemplateName);
                continue;
            }
            if ("应还本金".equals(systemTemplateName)) {
                bean.setRmbYhbjzje(customerTemplateName);
                continue;
            }
            if ("应还利息".equals(systemTemplateName)) {
                bean.setRmbYhlizje(customerTemplateName);
                continue;
            }
            if ("应还罚息".equals(systemTemplateName)) {
                bean.setRmbYhfxzje(customerTemplateName);
                continue;
            }
            if ("应还费用".equals(systemTemplateName)) {
                bean.setRmbYhfyzje(customerTemplateName);
                continue;
            }
            if ("滞纳金".equals(systemTemplateName)) {
                bean.setZnj(customerTemplateName);
                continue;
            }
            if ("所属城市".equals(systemTemplateName)) {
                bean.setWwCityName(customerTemplateName);
                continue;
            }
            if ("所属区域".equals(systemTemplateName)) {
                bean.setAreaCenter(customerTemplateName);
                continue;
            }
            if ("推荐商户".equals(systemTemplateName)) {
                bean.setTjFirm(customerTemplateName);
                continue;
            }
            if ("推荐网点".equals(systemTemplateName)) {
                bean.setTjWd(customerTemplateName);
                continue;
            }
            if ("产品名称".equals(systemTemplateName)) {
                bean.setCpmc(customerTemplateName);
                continue;
            }
            if ("还款方式".equals(systemTemplateName)) {
                bean.setHkType(customerTemplateName);
                continue;
            }
            if ("放款金额".equals(systemTemplateName)) {
                bean.setBorrowEd(customerTemplateName);
                continue;
            }
            if ("分期期数".equals(systemTemplateName)) {
                bean.setFz(customerTemplateName);
                continue;
            }
            if ("年利率".equals(systemTemplateName)) {
                bean.setYearRates(customerTemplateName);
                continue;
            }
            if ("日利率".equals(systemTemplateName)) {
                bean.setDayRates(customerTemplateName);
                continue;
            }
            if ("借款卡号".equals(systemTemplateName)) {
                bean.setBorrowNo(customerTemplateName);
                continue;
            }
            if ("借款卡银行".equals(systemTemplateName)) {
                bean.setBorrowBlank(customerTemplateName);
                continue;
            }
            if ("单位名称".equals(systemTemplateName)) {
                bean.setWorkName(customerTemplateName);
                continue;
            }
            if ("电子邮箱".equals(systemTemplateName)) {
                bean.setEmail(customerTemplateName);
                continue;
            }
            if ("单位地址".equals(systemTemplateName)) {
                bean.setWorkAddress(customerTemplateName);
                continue;
            }
            if ("住宅地址".equals(systemTemplateName)) {
                bean.setCustomerHomeAddress(customerTemplateName);
                continue;
            }
            if ("户籍地址".equals(systemTemplateName)) {
                bean.setRegistAddress(customerTemplateName);
                continue;
            }
            if ("身份证地址".equals(systemTemplateName)) {
                bean.setCertificateAddress(customerTemplateName);
                continue;
            }
            if ("账单地址".equals(systemTemplateName)) {
                bean.setBillAddress(customerTemplateName);
                continue;
            }
            if ("首逾标识".equals(systemTemplateName)) {
                bean.setFirstYqFlag(customerTemplateName);
                continue;
            }
            if ("最大逾期天数".equals(systemTemplateName)) {
                bean.setMaxYqtsHis(customerTemplateName);
                continue;
            }
            if ("累计逾期天数".equals(systemTemplateName)) {
                bean.setSumYqtsHis(customerTemplateName);
                continue;
            }
            if ("逾期次数".equals(systemTemplateName)) {
                bean.setSumYqcsHis(customerTemplateName);
                continue;
            }
            if ("移动电话".equals(systemTemplateName)) {
                bean.setCustomerMobile(customerTemplateName);
                continue;
            }
            if ("联系人1姓名".equals(systemTemplateName)) {
                bean.setFirstLiaisonName(customerTemplateName);
                continue;
            }
            if ("联系人1关系".equals(systemTemplateName)) {
                bean.setFirstLiaisonRelation(customerTemplateName);
                continue;
            }
            if ("联系人2姓名".equals(systemTemplateName)) {
                bean.setSecondLiaisonName(customerTemplateName);
                continue;
            }
            if ("联系人2关系".equals(systemTemplateName)) {
                bean.setSecondLiaisonRelation(customerTemplateName);
                continue;
            }
            if ("联系人3姓名".equals(systemTemplateName)) {
                bean.setThreeLiaisonName(customerTemplateName);
                continue;
            }
            if ("联系人3关系".equals(systemTemplateName)) {
                bean.setThreeLiaisonRelation(customerTemplateName);
                continue;
            }
            if ("住宅电话".equals(systemTemplateName)) {
                bean.setCustomerHomeTel(customerTemplateName);
                continue;
            }
            if ("联系人1电话1".equals(systemTemplateName)) {
                bean.setFirstLiaisonMobile(customerTemplateName);
                continue;
            }
            if ("联系人1电话2".equals(systemTemplateName)) {
                bean.setFirstLiaisonTel(customerTemplateName);
                continue;
            }
            if ("联系人2电话1".equals(systemTemplateName)) {
                bean.setSecondLiaisonMobile(customerTemplateName);
                continue;
            }
            if ("联系人2电话2".equals(systemTemplateName)) {
                bean.setSecondLiaisonTel(customerTemplateName);
                continue;
            }
            if ("联系人3电话1".equals(systemTemplateName)) {
                bean.setThreeLiaisonMobile(customerTemplateName);
                continue;
            }
            if ("联系人3电话2".equals(systemTemplateName)) {
                bean.setThreeLiaisonTel(customerTemplateName);
                continue;
            }
            if ("单位电话".equals(systemTemplateName)) {
                bean.setWorkTel(customerTemplateName);
                continue;
            }

            if ("账龄".equals(systemTemplateName)) {
                bean.setAccountAge(customerTemplateName);
                continue;
            }
            if ("留案标签".equals(systemTemplateName)) {
                bean.setLaFlag(customerTemplateName);
                continue;
            }
            if ("风险标签".equals(systemTemplateName)) {
                bean.setFxFlag(customerTemplateName);
                continue;
            }
            if ("合同类型".equals(systemTemplateName)) {
                bean.setHtlx(customerTemplateName);
                continue;
            }
            if ("减免标签".equals(systemTemplateName)) {
                bean.setJmbq(customerTemplateName);
                continue;
            }
            if ("法催标签".equals(systemTemplateName)) {
                bean.setFcbq(customerTemplateName);
                continue;
            }
            if ("罚息是否变化".equals(systemTemplateName)) {
                bean.setFxsfbh(customerTemplateName);
                continue;
            }
            if ("备注".equals(systemTemplateName)) {
                bean.setRemark(customerTemplateName);
                continue;
            }
            if ("退案日".equals(systemTemplateName)) {
                bean.setTar(customerTemplateName);
                continue;
            }
            if ("借款日期".equals(systemTemplateName)) {
                bean.setJkrq(customerTemplateName);
                continue;
            }
            if ("最近一次还款日".equals(systemTemplateName)) {
                bean.setZhychkr(customerTemplateName);
                continue;
            }
            if ("每期还款金额".equals(systemTemplateName)) {
                bean.setMqhkje(customerTemplateName);
                continue;
            }
            if ("当期欠款金额".equals(systemTemplateName)) {
                bean.setDqqkje(customerTemplateName);
                continue;
            }
            if ("累计已还金额".equals(systemTemplateName)) {
                bean.setLjyhje(customerTemplateName);
                continue;
            }
            if ("首付金额".equals(systemTemplateName)) {
                bean.setSfje(customerTemplateName);
                continue;
            }
            if ("指定还款账号1".equals(systemTemplateName)) {
                bean.setZdhkzh1(customerTemplateName);
                continue;
            }
            if ("指定还款账号2".equals(systemTemplateName)) {
                bean.setZdhkzh2(customerTemplateName);
                continue;
            }
            if ("指定还款账户户名1".equals(systemTemplateName)) {
                bean.setZdhkzhhm1(customerTemplateName);
                continue;
            }
            if ("指定还款账户户名2".equals(systemTemplateName)) {
                bean.setZdhkzhhm2(customerTemplateName);
                continue;
            }
            if ("指定还款渠道1".equals(systemTemplateName)) {
                bean.setZdhkqd1(customerTemplateName);
                continue;
            }
            if ("指定还款渠道2".equals(systemTemplateName)) {
                bean.setZdhkqd2(customerTemplateName);
                continue;
            }
            if ("考核目标".equals(systemTemplateName)) {
                bean.setKhmb(customerTemplateName);
                continue;
            }
            if ("商品价格".equals(systemTemplateName)) {
                bean.setSpjg(customerTemplateName);
                continue;
            }
            if ("贷款类型".equals(systemTemplateName)) {
                bean.setDklx(customerTemplateName);
                continue;
            }
            if ("借款笔数".equals(systemTemplateName)) {
                bean.setJkbs(customerTemplateName);
                continue;
            }
            if ("商品信息".equals(systemTemplateName)) {
                bean.setSpxx(customerTemplateName);
                continue;
            }
            if ("委案次数".equals(systemTemplateName)) {
                bean.setWacs(customerTemplateName);
                continue;
            }
            if ("已还期数".equals(systemTemplateName)) {
                bean.setYkqs(customerTemplateName);
                continue;
            }
            if ("工作部门".equals(systemTemplateName)) {
                bean.setWorkDept(customerTemplateName);
                continue;
            }
            if ("客户电话2".equals(systemTemplateName)) {
                bean.setCustomerMobile2(customerTemplateName);
                continue;
            }
            if ("客户电话3".equals(systemTemplateName)) {
                bean.setCustomerMobile3(customerTemplateName);
                continue;
            }
            if ("客户电话4".equals(systemTemplateName)) {
                bean.setCustomerMobile4(customerTemplateName);
                continue;
            }
            if ("联系人4姓名".equals(systemTemplateName)) {
                bean.setFourthLiaisonName(customerTemplateName);
                continue;
            }
            if ("联系人4关系".equals(systemTemplateName)) {
                bean.setFourthLiaisonRelation(customerTemplateName);
                continue;
            }
            if ("联系人4电话".equals(systemTemplateName)) {
                bean.setFourthLiaisonMobile(customerTemplateName);
                continue;
            }
            if ("联系人5姓名".equals(systemTemplateName)) {
                bean.setFifthLiaisonName(customerTemplateName);
                continue;
            }
            if ("联系人5关系".equals(systemTemplateName)) {
                bean.setFifthLiaisonRelation(customerTemplateName);
                continue;
            }
            if ("联系人5电话".equals(systemTemplateName)) {
                bean.setFifthLiaisonMobile(customerTemplateName);
                continue;
            }
            if ("呆账核销日期".equals(systemTemplateName)) {
                bean.setDzhxrq(customerTemplateName);
                continue;
            }
            if ("客户号".equals(systemTemplateName)) {
                bean.setCustomerNo(customerTemplateName);
                continue;}
            if ("数据日期".equals(systemTemplateName)) {
                bean.setSjrq(customerTemplateName);
                continue;}
        }
        return bean;
    }


    public void updateCollect(List<TempCurAssetsPackage> paramList) throws Exception {
        List<Assets2> assets = buildParam(paramList);
        this.tlcDuncaseService.DuncaseUpdate(assets);
    }


    public List<Assets2> buildParam(List<TempCurAssetsPackage> paramList) throws Exception {
        List<Assets2> desList = new ArrayList<>(paramList.size());
        paramList.stream().forEach(tempCurAssets -> {
//            String importBatchNo = this.curAssetsPackageMapper.selectBatchNo(tempCurAssets);
//            tempCurAssets.setImportBatchNo(importBatchNo);
            String jsonStr = JSON.toJSONString(tempCurAssets);
            Assets2 assets = JSONObject.parseObject(jsonStr, Assets2.class);
            desList.add(assets);
        });
        return desList;
    }

    private void insertFreeImport(List<TempCurAssetsPackage> paramList) throws Exception {
        List<FreeImport> freeImports = buildFreeImport(paramList);
        if (freeImports.size() > 0) {
            this.curAssetsPackageMapper.insertFreeImport(freeImports);
        }
    }

    private List<FreeImport> buildFreeImport(List<TempCurAssetsPackage> paramList) {
        List<FreeImport> resultList = new ArrayList<>();
        paramList.stream().forEach(tempCurAssets -> {
            String freeImport = tempCurAssets.getFreeImport();
            if (freeImport != null && !"".equals(freeImport)) {
                FreeImport dto = new FreeImport();
                dto.setOrgCasno(tempCurAssets.getOrgCasno());
                dto.setOrgId(tempCurAssets.getOrgId());
                dto.setImportBatchNo(tempCurAssets.getImportBatchNo());
                dto.setValue(freeImport);
                dto.setCreateBy(ShiroUtils.getSysUser().getUserName());
                dto.setCreateTime(tempCurAssets.getCreateTime());
                resultList.add(dto);
            }
        });
        return resultList;
    }

}
