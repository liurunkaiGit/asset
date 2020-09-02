package com.ruoyi.assetspackage.service;

import com.ruoyi.assetspackage.domain.phoneStatus.PhoneStatus;

import java.util.List;

/**
 * 号码状态Service接口
 * 
 * @author guozeqi
 * @date 2020-08-31
 */
public interface IPhoneStatusService 
{
    /**
     * 查询号码状态
     * 
     * @param id 号码状态ID
     * @return 号码状态
     */
    public PhoneStatus selectPhoneStatusById(Long id);

    /**
     * 查询号码状态列表
     * 
     * @param phoneStatus 号码状态
     * @return 号码状态集合
     */
    public List<PhoneStatus> selectPhoneStatusList(PhoneStatus phoneStatus);

    /**
     * 新增号码状态
     * 
     * @param phoneStatus 号码状态
     * @return 结果
     */
    public int insertPhoneStatus(PhoneStatus phoneStatus);

    /**
     * 修改号码状态
     * 
     * @param phoneStatus 号码状态
     * @return 结果
     */
    public int updatePhoneStatus(PhoneStatus phoneStatus);

    /**
     * 批量删除号码状态
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePhoneStatusByIds(String ids);

    /**
     * 删除号码状态信息
     * 
     * @param id 号码状态ID
     * @return 结果
     */
    public int deletePhoneStatusById(Long id);
}
