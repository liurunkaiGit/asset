package com.ruoyi.assetspackage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.assetspackage.mapper.FileAccessoriesPackageMapper;
import com.ruoyi.assetspackage.domain.FileAccessoriesPackage;
import com.ruoyi.assetspackage.service.IFileAccessoriesPackageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文件附件Service业务层处理
 * 
 * @author guozeqi
 * @date 2019-12-27
 */
@Service
public class FileAccessoriesPackageServiceImpl implements IFileAccessoriesPackageService 
{
    @Autowired
    private FileAccessoriesPackageMapper fileAccessoriesPackageMapper;

    /**
     * 查询文件附件
     * 
     * @param id 文件附件ID
     * @return 文件附件
     */
    @Override
    public FileAccessoriesPackage selectFileAccessoriesPackageById(String id)
    {
        return fileAccessoriesPackageMapper.selectFileAccessoriesPackageById(id);
    }

    /**
     * 查询文件附件列表
     * 
     * @param fileAccessoriesPackage 文件附件
     * @return 文件附件
     */
    @Override
    public List<FileAccessoriesPackage> selectFileAccessoriesPackageList(FileAccessoriesPackage fileAccessoriesPackage)
    {
        return fileAccessoriesPackageMapper.selectFileAccessoriesPackageList(fileAccessoriesPackage);
    }

    /**
     * 新增文件附件
     * 
     * @param fileAccessoriesPackage 文件附件
     * @return 结果
     */
    @Override
    public int insertFileAccessoriesPackage(FileAccessoriesPackage fileAccessoriesPackage)
    {
        fileAccessoriesPackage.setCreateTime(DateUtils.getNowDate());
        return fileAccessoriesPackageMapper.insertFileAccessoriesPackage(fileAccessoriesPackage);
    }

    /**
     * 修改文件附件
     * 
     * @param fileAccessoriesPackage 文件附件
     * @return 结果
     */
    @Override
    public int updateFileAccessoriesPackage(FileAccessoriesPackage fileAccessoriesPackage)
    {
        return fileAccessoriesPackageMapper.updateFileAccessoriesPackage(fileAccessoriesPackage);
    }

    /**
     * 删除文件附件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFileAccessoriesPackageByIds(String ids)
    {
        return fileAccessoriesPackageMapper.deleteFileAccessoriesPackageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文件附件信息
     * 
     * @param id 文件附件ID
     * @return 结果
     */
    @Override
    public int deleteFileAccessoriesPackageById(String id)
    {
        return fileAccessoriesPackageMapper.deleteFileAccessoriesPackageById(id);
    }
}
