package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.CurAssetsRepaymentPackage;
import com.ruoyi.assetspackage.domain.DuncaseAssign;
import com.ruoyi.assetspackage.domain.TempCurAssetsRepaymentPackage;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 资产还款Mapper接口
 * 
 * @author guozeqi
 * @date 2020-01-13
 */
public interface CurAssetsRepaymentPackageMapper 
{
    /**
     * 查询资产还款
     * 
     * @param id 资产还款ID
     * @return 资产还款
     */
    public CurAssetsRepaymentPackage selectCurAssetsRepaymentPackageById(String id);

    /**
     * 查询资产还款列表
     * 
     * @param curAssetsRepaymentPackage 资产还款
     * @return 资产还款集合
     */
    public List<CurAssetsRepaymentPackage> selectCurAssetsRepaymentPackageList(CurAssetsRepaymentPackage curAssetsRepaymentPackage);

    /**
     * 新增资产还款
     * 
     * @param curAssetsRepaymentPackage 资产还款
     * @return 结果
     */
    public int insertCurAssetsRepaymentPackage(CurAssetsRepaymentPackage curAssetsRepaymentPackage);

    /**
     * 修改资产还款
     * 
     * @param curAssetsRepaymentPackage 资产还款
     * @return 结果
     */
    public int updateCurAssetsRepaymentPackage(CurAssetsRepaymentPackage curAssetsRepaymentPackage);

    /**
     * 删除资产还款
     * 
     * @param id 资产还款ID
     * @return 结果
     */
    public int deleteCurAssetsRepaymentPackageById(String id);

    /**
     * 批量删除资产还款
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCurAssetsRepaymentPackageByIds(String[] ids);


    /**
     * 判断重复记录查询
     * @param params
     * @return
     */
    public String findMoreByOrgCasNO(Map params);

    public String findMoreByHt(Map params);


    /**
     * 通过机构案件号查询还款金额的值
     * @param orgCasNo
     * @return
     */
    public BigDecimal selectHkjeByOrgCasNo(String orgCasNo);


    /**
     * 根据机构案件号更新结案状态
     * @param param
     * @return
     */
    public int updateJaztByCasNo(Map<String,Object> param);

    /**
     * 插入临时表
     */

    public void batchAddTemp(List<TempCurAssetsRepaymentPackage> paramList)throws Exception;

    public List<TempCurAssetsRepaymentPackage> selectTempTableList(TempCurAssetsRepaymentPackage param)throws Exception;

    public int updateExceptionStatus(String id)throws Exception;

    public int selectNotExistsNum(String importBatchNo)throws Exception;

    public int updateNotExists(String importBatchNo)throws Exception;

    public int selectCloseCaseNum(String importBatchNo)throws Exception;

    public int updateCloseCaseNum(String importBatchNo)throws Exception;

    public int selectNormalTotal(String importBatchNo)throws Exception;


    public List<Map<String,String>> selectNotExistsInfo(String importBatchNo)throws Exception;
    public List<Map<String,String>> selectCloseCaseInfo(String importBatchNo)throws Exception;

    public void batchAddRepaymentTable(String importBatchNo)throws Exception;

    public int deleteTempTable(String importBatchNo)throws Exception;

    /**
     * 批量插入案件轨迹表
     *
     * @param duncaseAssignList
     */
    int batchInsertDuncaseAssign(@Param("duncaseAssignList") List<DuncaseAssign> duncaseAssignList);



}
