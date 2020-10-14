package com.ruoyi.assetspackage.mapper.distribution;

import com.ruoyi.assetspackage.domain.distribution.TLcCustContactAsset;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户联系人信息Mapper接口
 *
 * @author liurunkai
 * @date 2019-12-26
 */
public interface TLcCustContactAssetMapper {

    /**
     * 批量插入客户联系人信息
     *
     * @param contactInsertList
     */
    void batchInsertContact(@Param("contactInsertList") List<TLcCustContactAsset> contactInsertList);
}
