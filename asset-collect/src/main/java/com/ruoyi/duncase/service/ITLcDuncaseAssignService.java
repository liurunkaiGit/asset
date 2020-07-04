package com.ruoyi.duncase.service;

import com.ruoyi.duncase.domain.TLcDuncaseAssign;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.task.domain.TLcTask;

import java.util.List;

/**
 * 案件轨迹Service接口
 *
 * @author liurunkai
 * @date 2019-12-27
 */
public interface ITLcDuncaseAssignService {
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
     * 批量删除案件轨迹
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcDuncaseAssignByIds(String ids);

    /**
     * 删除案件轨迹信息
     *
     * @param id 案件轨迹ID
     * @return 结果
     */
    public int deleteTLcDuncaseAssignById(Long id);

    /**
     * 根据证件号码查询对应的案件轨迹信息
     *
     * @param certificateNo
     * @return
     */
    List<TLcDuncaseAssign> findDuncaseAssignByCertificateNo(String certificateNo);

    /**
     * 根据案件编号查询对应的案件轨迹信息
     *
     * @param caseNo
     * @return
     */
    List<TLcDuncaseAssign> findDuncaseAssignByCaseNo(String caseNo);

    void batchInsertDuncaseAssign(List<TLcTask> taskList, SysUser sysUser, Integer code);
}
