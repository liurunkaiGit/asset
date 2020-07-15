package com.ruoyi.agent.mapper;

import com.ruoyi.agent.domain.ExtPhone;
import com.ruoyi.system.domain.SysUser;

import java.util.List;

/**
 * 分机号码Mapper接口
 * 
 * @author guozeqi
 * @date 2020-03-02
 */
public interface ExtPhoneMapper 
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
     * 删除分机号码
     * 
     * @param id 分机号码ID
     * @return 结果
     */
    public int deleteExtPhoneById(String id);

    /**
     * 批量删除分机号码
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExtPhoneByIds(String[] ids);

    List<SysUser> selectNoBindUser(SysUser sysUser);

    int updateExtPhoneStatus(ExtPhone extPhone);

    List<ExtPhone> selectExtPhoneListBySeatId(Long userId);

    ExtPhone selectExtPhoneByAgent(ExtPhone extPhone);
}
