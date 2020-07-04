package com.ruoyi.duncase.mapper;

import com.ruoyi.duncase.domain.TLcDuncaseAssign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 案件轨迹Mapper接口
 *
 * @author liurunkai
 * @date 2019-12-27
 */
public interface TLcDuncaseAssignMapper {
    /**
     * 查询案件轨迹
     *
     * @param id 案件轨迹ID
     * @return 案件轨迹
     */
    public TLcDuncaseAssign selectTLcDuncaseAssignById(Long id);

    /**
     * 查询案件轨迹列表
     *
     * @param tLcDuncaseAssign 案件轨迹
     * @return 案件轨迹集合
     */
    public List<TLcDuncaseAssign> selectTLcDuncaseAssignList(TLcDuncaseAssign tLcDuncaseAssign);

    /**
     * 新增案件轨迹
     *
     * @param tLcDuncaseAssign 案件轨迹
     * @return 结果
     */
    public int insertTLcDuncaseAssign(TLcDuncaseAssign tLcDuncaseAssign);

    /**
     * 修改案件轨迹
     *
     * @param tLcDuncaseAssign 案件轨迹
     * @return 结果
     */
    public int updateTLcDuncaseAssign(TLcDuncaseAssign tLcDuncaseAssign);

    /**
     * 删除案件轨迹
     *
     * @param id 案件轨迹ID
     * @return 结果
     */
    public int deleteTLcDuncaseAssignById(Long id);

    /**
     * 批量删除案件轨迹
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcDuncaseAssignByIds(String[] ids);

    /**
     * 批量插入案件轨迹表
     *
     * @param duncaseAssignList
     */
    int batchInsertDuncaseAssign(@Param("duncaseAssignList") List<TLcDuncaseAssign> duncaseAssignList);

    List<TLcDuncaseAssign> findDuncaseAssignByCertificateNo(@Param("certificateNo") String certificateNo);

    /**
     * 根据案件编号查询历史轨迹
     *
     * @param caseNo
     * @return
     */
    List<TLcDuncaseAssign> findDuncaseAssignByCaseNo(@Param("caseNo") String caseNo);
}
