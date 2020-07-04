package com.ruoyi.columnQuery.service.impl;

import com.ruoyi.columnQuery.domain.TLcColumnQuery;
import com.ruoyi.columnQuery.mapper.TLcColumnQueryMapper;
import com.ruoyi.columnQuery.service.ITLcColumnQueryService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.framework.util.ShiroUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service业务层处理
 *
 * @author liurunkai
 * @date 2020-05-22
 */
@Service
public class TLcColumnQueryServiceImpl implements ITLcColumnQueryService {

    @Autowired
    private TLcColumnQueryMapper tLcColumnQueryMapper;

    /**
     * 查询
     *
     * @param id
     * @return
     */
    @Override
    public TLcColumnQuery selectTLcColumnQueryById(Long id) {
        return tLcColumnQueryMapper.selectTLcColumnQueryById(id);
    }

    /**
     * 查询列表
     *
     * @param tLcColumnQuery
     * @return
     */
    @Override
    public List<TLcColumnQuery> selectTLcColumnQueryList(TLcColumnQuery tLcColumnQuery) {
        return tLcColumnQueryMapper.selectTLcColumnQueryList(tLcColumnQuery);
    }

    /**
     * 新增
     *
     * @param tLcColumnQuery
     * @return 结果
     */
    @Override
    public int insertTLcColumnQuery(TLcColumnQuery tLcColumnQuery) {
//        String tableName = tLcColumnQuery.getTableNameAndComment().split(",")[0];
        tLcColumnQuery.setOrgId(Long.valueOf(tLcColumnQuery.getOrgIdAndName().split(",")[0]));
        tLcColumnQuery.setOrgName(tLcColumnQuery.getOrgIdAndName().split(",")[1]);
//        tLcColumnQuery.setTableName(tableName);
        tLcColumnQuery.setColumnName(tLcColumnQuery.getColumnNameAndComment().split(",")[0]);
        tLcColumnQuery.setColumnNameCn(tLcColumnQuery.getColumnNameAndComment().split(",")[1]);
        tLcColumnQuery.setCreateBy(String.valueOf(ShiroUtils.getSysUser().getUserId()));
        tLcColumnQuery.setModifyBy(ShiroUtils.getSysUser().getUserId());
        // todo 优化为批量插入 数值区间
//        String niStr = tLcColumnQuery.getNiObj();
//        ColumnObj niObj = JSON.parseObject(niStr, ColumnObj.class);
//        tLcColumnQuery.setColumnType(niObj.getType());
//        niObj.getValue().stream().forEach(ni -> {
//            tLcColumnQuery.setColumnName(ni);
//            tLcColumnQuery.setColumnNameCn(ColumnEnum.getColumnComment(tableName, niObj.getType(), ni));
//            tLcColumnQueryMapper.insertTLcColumnQuery(tLcColumnQuery);
//        });
//        // 日期区间
//        String diStr = tLcColumnQuery.getDiObj();
//        ColumnObj diObj = JSON.parseObject(diStr, ColumnObj.class);
//        tLcColumnQuery.setColumnType(diObj.getType());
//        diObj.getValue().stream().forEach(di -> {
//            tLcColumnQuery.setColumnName(di);
//            tLcColumnQuery.setColumnNameCn(ColumnEnum.getColumnComment(tableName, diObj.getType(), di));
//            tLcColumnQueryMapper.insertTLcColumnQuery(tLcColumnQuery);
//        });
//        // 下拉框
//        String seStr = tLcColumnQuery.getSeObj();
//        ColumnObj seObj = JSON.parseObject(seStr, ColumnObj.class);
//        tLcColumnQuery.setColumnType(seObj.getType());
//        seObj.getValue().stream().forEach(se -> {
//            tLcColumnQuery.setColumnName(se);
//            tLcColumnQuery.setColumnNameCn(ColumnEnum.getColumnComment(tableName, seObj.getType(), se));
//            tLcColumnQueryMapper.insertTLcColumnQuery(tLcColumnQuery);
//        });
//        // 字符串
//        String sStr = tLcColumnQuery.getSObj();
//        ColumnObj sObj = JSON.parseObject(sStr, ColumnObj.class);
//        tLcColumnQuery.setColumnType(sObj.getType());
//        sObj.getValue().stream().forEach(s -> {
//            tLcColumnQuery.setColumnName(s);
//            tLcColumnQuery.setColumnNameCn(ColumnEnum.getColumnComment(tableName, sObj.getType(), s));
//            tLcColumnQueryMapper.insertTLcColumnQuery(tLcColumnQuery);
//        });
//        // 字符串精确查询
//        String seStr = tLcColumnQuery.getSeObj();
//        ColumnObj seObj = JSON.parseObject(seStr, ColumnObj.class);
//        tLcColumnQuery.setColumnType(seObj.getType());
//        seObj.getValue().stream().forEach(se -> {
//            tLcColumnQuery.setColumnName(se);
//            tLcColumnQuery.setColumnNameCn(ColumnEnum.getColumnComment(tableName, seObj.getType(), se));
//            tLcColumnQueryMapper.insertTLcColumnQuery(tLcColumnQuery);
//        });
//        // 字符串模糊匹配
//        String slStr = tLcColumnQuery.getSlObj();
//        ColumnObj slObj = JSON.parseObject(slStr, ColumnObj.class);
//        tLcColumnQuery.setColumnType(slObj.getType());
//        slObj.getValue().stream().forEach(sl -> {
//            tLcColumnQuery.setColumnName(sl);
//            tLcColumnQuery.setColumnNameCn(ColumnEnum.getColumnComment(tableName, slObj.getType(), sl));
//            tLcColumnQueryMapper.insertTLcColumnQuery(tLcColumnQuery);
//        });
        return tLcColumnQueryMapper.insertTLcColumnQuery(tLcColumnQuery);
    }

    /**
     * 修改
     *
     * @param tLcColumnQuery
     * @return 结果
     */
    @Override
    public int updateTLcColumnQuery(TLcColumnQuery tLcColumnQuery) {
        return tLcColumnQueryMapper.updateTLcColumnQuery(tLcColumnQuery);
    }

    /**
     * 删除对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcColumnQueryByIds(String ids) {
        return tLcColumnQueryMapper.deleteTLcColumnQueryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除信息
     *
     * @param id
     * @return 结果
     */
    @Override
    public int deleteTLcColumnQueryById(Long id) {
        return tLcColumnQueryMapper.deleteTLcColumnQueryById(id);
    }
}

@Data
class ColumnObj {
    private String type;
    private List<String> value;
}
