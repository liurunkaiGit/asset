package com.ruoyi.assetspackage.mapper.distribution;

import com.ruoyi.assetspackage.domain.distribution.TLcAllocatCaseConfigAsset;
import org.apache.ibatis.annotations.Param;

/**
 * 智能分案配置Mapper接口
 *
 * @author liurunkai
 * @date 2020-04-23
 */
public interface TLcAllocatCaseConfigAssetMapper {

    TLcAllocatCaseConfigAsset selectTLcAllocatCaseConfigByOrgId(@Param("orgId") String orgId);
}
