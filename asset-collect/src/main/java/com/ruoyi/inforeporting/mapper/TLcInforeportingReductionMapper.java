package com.ruoyi.inforeporting.mapper;

import com.ruoyi.inforeporting.domain.TLcInforeportingReduction;
import com.ruoyi.inforeporting.domain.TLcInforeportingReductionJiexin;
import com.ruoyi.inforeporting.domain.TLcInforeportingReductionXing;
import com.ruoyi.inforeporting.domain.TLcInforeportingTemplate;
import com.ruoyi.task.domain.TLcTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @Description: 上报信息-减免
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Mapper
public interface TLcInforeportingReductionMapper {
    /**
     * @param inforeportingReduction 新增上报信息减免
     * @return 是否成功 成功>0 失败<0
     */
    public int insertTLcInforeportingReduction(TLcInforeportingReduction inforeportingReduction);

    /**
     * @param caseNo 合同号
     * @param orgId 机构id
     * @param importBatchNo 批次号
     * @return 返回任务数据
     */
    TLcTask selectTLcTaskByCaseNo(@Param("caseNo") String caseNo, @Param("orgId") String orgId, @Param("importBatchNo") String importBatchNo);

    /**
     * @param inforeportingReduction 上报信息减免实体 参数
     * @return 上报信息减免实体集合数据
     */
    public List<TLcInforeportingReduction> selectTLcInforeportingReductionList(TLcInforeportingReduction inforeportingReduction);
    /**
     * @param inforeportingReduction 上报信息减免实体 参数
     * @return 上报信息减免实体集合数据  导出
     */
    public List<TLcInforeportingReduction> selectTLcInforeportingReductionListExp(TLcInforeportingReduction inforeportingReduction);
    /**
     * @param inforeportingReduction  上报信息减免实体 参数
     * @return 上报信息减免实体集合数据 兴业消费金融
     */
    public List<TLcInforeportingReductionXing> selectTLcInforeportingReductionXingList(TLcInforeportingReduction inforeportingReduction);

    /**
     * @param tLcInforeportingTemplate 所有业务类型上报信息 模板参数
     * @return 所有业务类型上报信息结合数据
     */
    public List<TLcInforeportingTemplate> selectTLcInforeportingAllList(TLcInforeportingTemplate tLcInforeportingTemplate);

    /**
     * @param id 批量驳回上报信息减免
     * @return 是否成功 成功>0 失败<0
     */
    public int rejectTLcInforeportingReductionByIds(Long[] id);
}
