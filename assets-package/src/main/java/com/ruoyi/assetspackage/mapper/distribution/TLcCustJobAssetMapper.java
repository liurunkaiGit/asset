package com.ruoyi.assetspackage.mapper.distribution;

import com.ruoyi.assetspackage.domain.distribution.TLcCustJobAsset;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户工作单位信息Mapper接口
 *
 * @author liurunkai
 * @date 2019-12-26
 */
public interface TLcCustJobAssetMapper {

    /**
     * 批量插入客户工作信息
     *
     * @param jobInsertList
     */
    void batchInsertCustJob(@Param("jobInsertList") List<TLcCustJobAsset> jobInsertList);
}
