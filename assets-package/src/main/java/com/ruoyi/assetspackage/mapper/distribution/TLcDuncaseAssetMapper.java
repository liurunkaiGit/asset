package com.ruoyi.assetspackage.mapper.distribution;

import com.ruoyi.assetspackage.domain.distribution.TLcDuncaseAsset;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 案件Mapper接口
 *
 * @author liurunkai
 * @date 2019-12-25
 */
public interface TLcDuncaseAssetMapper {

    /**
     * 批量插入案件信息
     *
     * @param duncaseInsertList
     */
    void batchInsertDuncase(@Param("duncaseInsertList") List<TLcDuncaseAsset> duncaseInsertList);
}
