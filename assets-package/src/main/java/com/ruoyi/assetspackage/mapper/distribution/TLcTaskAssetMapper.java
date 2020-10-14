package com.ruoyi.assetspackage.mapper.distribution;

import com.ruoyi.assetspackage.domain.distribution.TLcTaskAsset;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务Mapper接口
 *
 * @author liurunkai
 * @date 2019-12-25
 */
public interface TLcTaskAssetMapper {

    /**
     * 批量生成任务
     *
     * @param taskList
     */
    void batchInsertTask(@Param("taskList") List<TLcTaskAsset> taskList);

    /**
     * 通过组织机构id查询对应的部门id
     *
     * @param orgId
     * @return
     */
    Long findDeptIdByOrgId(@Param("orgId") String orgId);

    List<TLcTaskAsset> selectSameCaseTaskList(@Param("certificateNo") String certificateNo, @Param("orgId") String orgId);

    List<TLcTaskAsset> selectTaskListByCertificateNosAndOrdId(@Param("certificateNos") List<String> certificateNos, @Param("orgId") String orgId);
}
