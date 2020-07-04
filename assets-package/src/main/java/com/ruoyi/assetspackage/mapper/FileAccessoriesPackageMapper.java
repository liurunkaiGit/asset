package com.ruoyi.assetspackage.mapper;

import com.ruoyi.assetspackage.domain.FileAccessoriesPackage;
import java.util.List;

/**
 * 文件附件Mapper接口
 * 
 * @author guozeqi
 * @date 2019-12-27
 */
public interface FileAccessoriesPackageMapper 
{
    /**
     * 查询文件附件
     * 
     * @param id 文件附件ID
     * @return 文件附件
     */
    public FileAccessoriesPackage selectFileAccessoriesPackageById(String id);

    /**
     * 查询文件附件列表
     * 
     * @param fileAccessoriesPackage 文件附件
     * @return 文件附件集合
     */
    public List<FileAccessoriesPackage> selectFileAccessoriesPackageList(FileAccessoriesPackage fileAccessoriesPackage);

    /**
     * 新增文件附件
     * 
     * @param fileAccessoriesPackage 文件附件
     * @return 结果
     */
    public int insertFileAccessoriesPackage(FileAccessoriesPackage fileAccessoriesPackage);

    /**
     * 修改文件附件
     * 
     * @param fileAccessoriesPackage 文件附件
     * @return 结果
     */
    public int updateFileAccessoriesPackage(FileAccessoriesPackage fileAccessoriesPackage);

    /**
     * 删除文件附件
     * 
     * @param id 文件附件ID
     * @return 结果
     */
    public int deleteFileAccessoriesPackageById(String id);

    /**
     * 批量删除文件附件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFileAccessoriesPackageByIds(String[] ids);
}
