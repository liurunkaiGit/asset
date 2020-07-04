package com.ruoyi.custom.service.impl;

import java.util.List;

import com.ruoyi.custom.domain.TLcCustTag;
import com.ruoyi.custom.mapper.TLcCustTagMapper;
import com.ruoyi.custom.service.ITLcCustTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 客户标签Service业务层处理
 *
 * @author liurunkai
 * @date 2020-01-09
 */
@Service
public class TLcCustTagServiceImpl implements ITLcCustTagService {
    @Autowired
    private TLcCustTagMapper tLcCustTagMapper;

    /**
     * 查询客户标签
     *
     * @param id 客户标签ID
     * @return 客户标签
     */
    @Override
    public TLcCustTag selectTLcCustTagById(Long id) {
        return tLcCustTagMapper.selectTLcCustTagById(id);
    }

    /**
     * 查询客户标签列表
     *
     * @param tLcCustTag 客户标签
     * @return 客户标签
     */
    @Override
    public List<TLcCustTag> selectTLcCustTagList(TLcCustTag tLcCustTag) {
        return tLcCustTagMapper.selectTLcCustTagList(tLcCustTag);
    }

    /**
     * 新增客户标签
     *
     * @param tLcCustTag 客户标签
     * @return 结果
     */
    @Override
    public int insertTLcCustTag(TLcCustTag tLcCustTag) {
        return tLcCustTagMapper.insertTLcCustTag(tLcCustTag);
    }

    /**
     * 修改客户标签
     *
     * @param tLcCustTag 客户标签
     * @return 结果
     */
    @Override
    public int updateTLcCustTag(TLcCustTag tLcCustTag) {
        return tLcCustTagMapper.updateTLcCustTag(tLcCustTag);
    }

    /**
     * 删除客户标签对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcCustTagByIds(String ids) {
        return tLcCustTagMapper.deleteTLcCustTagByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户标签信息
     *
     * @param id 客户标签ID
     * @return 结果
     */
    @Override
    public int deleteTLcCustTagById(Long id) {
        return tLcCustTagMapper.deleteTLcCustTagById(id);
    }
}
