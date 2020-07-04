package com.ruoyi.agent.service;

import com.ruoyi.agent.domain.ExtPhone;
import com.ruoyi.system.domain.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 分机号码Service接口
 * 
 * @author guozeqi
 * @date 2020-03-02
 */
public interface IExtPhoneService 
{
    /**
     * 查询分机号码
     * 
     * @param id 分机号码ID
     * @return 分机号码
     */
    public ExtPhone selectExtPhoneById(String id);

    /**
     * 查询分机号码列表
     * 
     * @param extPhone 分机号码
     * @return 分机号码集合
     */
    public List<ExtPhone> selectExtPhoneList(ExtPhone extPhone);

    /**
     * 新增分机号码
     * 
     * @param extPhone 分机号码
     * @return 结果
     */
    public int insertExtPhone(ExtPhone extPhone);

    /**
     * 修改分机号码
     * 
     * @param extPhone 分机号码
     * @return 结果
     */
    public int updateExtPhone(ExtPhone extPhone);

    /**
     * 批量删除分机号码
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExtPhoneByIds(String ids);

    /**
     * 删除分机号码信息
     * 
     * @param id 分机号码ID
     * @return 结果
     */
    public int deleteExtPhoneById(String id);

    List<SysUser> selectNoBindUser(SysUser sysUser);

    int updateExtPhoneStatus(ExtPhone extPhone);

    List<Map<String, Object>> selectExtPhoneListByUsername(String username);

    List<String> selectExtNumBySeat(String seatId, String agentId, String callPlatform);
}
