package com.ruoyi.assetspackage.service.distribution;

import com.ruoyi.assetspackage.domain.distribution.TLcTaskAsset;
import com.ruoyi.system.domain.SysUser;

import java.util.List;

/**
 * 任务Service接口
 *
 * @author liurunkai
 * @date 2019-12-25
 */
public interface ITLcTaskAssetService {

    /**
     * 通过组织机构id查询对应的部门id
     *
     * @param orgId
     * @return
     */
    Long findDeptIdByOrgId(String orgId);

    /**
     * 查询用户信息
     *
     * @return
     */
    List<SysUser> searchAllUser(SysUser sysUser);

    /**
     * 批量生成任务
     *
     * @param taskList
     */
    void batchInsertTask(List<TLcTaskAsset> taskList);

    List<TLcTaskAsset> selectTaskListByCertificateNosAndOrdId(List<String> certificateNos, String orgId);

    List<TLcTaskAsset> selectSameCaseTaskList(String certificateNo, String orgId);
}
