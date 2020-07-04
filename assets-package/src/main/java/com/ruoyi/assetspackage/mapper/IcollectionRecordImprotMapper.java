package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.*;

import java.util.List;
import java.util.Map;

/**
 * 催收记录Mapper接口
 * 
 * @author guozeqi
 * @date 2020-05-09
 */
public interface IcollectionRecordImprotMapper
{

    /**
     * 查询collectionrecordimprot
     *
     */
    public CollectionRecordImport selectTLcCollectionrecordimprotById(String id);

    /**
     * 查询collectionrecordimprot列表
     *
     */
    public List<CollectionRecordImport> selectTLcCollectionrecordimprotList(CollectionRecordImport CollectionRecordImport);

    /**
     * 新增collectionrecordimprot
     *
     */
    public int insertTLcCollectionrecordimprot(CollectionRecordImport CollectionRecordImport);

    /**
     * 修改collectionrecordimprot
     *
     */
    public int updateTLcCollectionrecordimprot(CollectionRecordImport CollectionRecordImport);

    /**
     * 删除collectionrecordimprot
     *
     */
    public int deleteTLcCollectionrecordimprotById(String id);

    /**
     * 批量删除collectionrecordimprot
     *
     */
    public int deleteTLcCollectionrecordimprotByIds(String[] ids);


    /**
     * 插入临时表
     */
    public void batchAddTemp(List<CollectionRecordImportTemp> paramList)throws Exception;


    public void deleteTempTable(String importBatchNo)throws Exception;


    public List<CollectionRecordImportTemp> selectTempCollectionRecordList(CollectionRecordImportTemp param)throws Exception;


    public int selectTempCollectionRecordCount(CollectionRecordImportTemp param)throws Exception;


    public int updateExceptionStatus(String id)throws Exception;

    public int updateExceptionStatus2(String id)throws Exception;


    public List<Map<String,String>> findNotExistsCase(String importBatchNo)throws Exception;

    public void batchAddCallRecord(CollectionRecordImportTemp param)throws Exception;


    public void updateDuncaseTable(String importBatchNo)throws Exception;

    public void updateTaskTable(String importBatchNo)throws Exception;

    public Map<String,Object> selectBatchInfo(CollectionRecordImportTemp param)throws Exception;

    public int insertTLcCollectionrecordimprotFlow(CollectionrecordimprotFlow param)throws Exception;

    public List<CollectionrecordimprotFlow> selectTLcCollectionrecordimprotFlowList(CollectionRecordImportTemp param)throws Exception;





}
