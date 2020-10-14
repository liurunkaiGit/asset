package com.ruoyi.assetspackage.mapper.distribution;

import com.ruoyi.assetspackage.domain.distribution.TLcDuncaseAssignAsset;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 案件轨迹Mapper接口
 *
 * @author liurunkai
 * @date 2019-12-27
 */
public interface TLcDuncaseAssignAssetMapper {

    /**
     * 批量插入案件轨迹表
     *
     * @param duncaseAssignList
     */
    int batchInsertDuncaseAssign(@Param("duncaseAssignList") List<TLcDuncaseAssignAsset> duncaseAssignList);
}
