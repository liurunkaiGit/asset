package com.ruoyi.columnQuery.mapper;

import com.ruoyi.columnQuery.domain.TLcColumnQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 字段查询配置Mapper接口
 *
 * @author liurunkai
 * @date 2020-05-22
 */
public interface TLcColumnQueryMapper {
    /**
     * 查询字段查询配置
     *
     * @param id 主键id
     * @return 字段查询配置
     */
    public TLcColumnQuery selectTLcColumnQueryById(Long id);

    /**
     * 查询字段查询配置列表
     *
     * @param tLcColumnQuery
     * @return
     */
    public List<TLcColumnQuery> selectTLcColumnQueryList(TLcColumnQuery tLcColumnQuery);

    /**
     * 新增
     *
     * @param tLcColumnQuery
     * @return 结果
     */
    public int insertTLcColumnQuery(TLcColumnQuery tLcColumnQuery);

    /**
     * 修改
     *
     * @param tLcColumnQuery
     * @return 结果
     */
    public int updateTLcColumnQuery(TLcColumnQuery tLcColumnQuery);

    /**
     * 删除
     *
     * @param id
     * @return 结果
     */
    public int deleteTLcColumnQueryById(Long id);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcColumnQueryByIds(String[] ids);

    List<Map<String, Object>> getColumnByTable(@Param("tableName") String tableName, @Param("database") String database);
}
