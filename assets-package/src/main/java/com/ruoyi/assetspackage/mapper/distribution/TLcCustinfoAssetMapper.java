package com.ruoyi.assetspackage.mapper.distribution;

import com.ruoyi.assetspackage.domain.distribution.TLcCustinfoAsset;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户信息Mapper接口
 *
 * @author liurunkai
 * @date 2019-12-26
 */
public interface TLcCustinfoAssetMapper {

    /**
     * 批量插入客户信息
     *
     * @param custInsertList
     */
    void batchInsertCustinfo(@Param("custInsertList") List<TLcCustinfoAsset> custInsertList);
}
