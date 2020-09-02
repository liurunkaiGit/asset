package com.ruoyi.assetspackage.service.impl;

import java.util.Arrays;
import java.util.List;

import com.ruoyi.assetspackage.domain.phoneStatus.PhoneStatus;
import com.ruoyi.assetspackage.mapper.PhoneStatusMapper;
import com.ruoyi.assetspackage.service.IPhoneStatusService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 号码状态Service业务层处理
 * 
 * @author guozeqi
 * @date 2020-08-31
 */
@Service
public class PhoneStatusServiceImpl implements IPhoneStatusService
{
    @Autowired
    private PhoneStatusMapper phoneStatusMapper;

    /**
     * 查询号码状态
     * 
     * @param id 号码状态ID
     * @return 号码状态
     */
    @Override
    public PhoneStatus selectPhoneStatusById(Long id)
    {
        return phoneStatusMapper.selectPhoneStatusById(id);
    }

    /**
     * 查询号码状态列表
     * 
     * @param phoneStatus 号码状态
     * @return 号码状态
     */
    @Override
    public List<PhoneStatus> selectPhoneStatusList(PhoneStatus phoneStatus)
    {
        String status = phoneStatus.getPhonestatus();
        if(StringUtils.isNotEmpty(status)){
            phoneStatus.setPhoneStatusList(Arrays.asList(status.split(",")));
        }
        List<PhoneStatus> list = phoneStatusMapper.selectPhoneStatusList(phoneStatus);
        for (PhoneStatus resultdata : list) {
            String phonestatus = resultdata.getPhonestatus();
            if("2".equals(phonestatus) || "32".equals(phonestatus) || "33".equals(phonestatus)){
                resultdata.setPhonestatus("可联");
            }else if("31".equals(phonestatus)){
                resultdata.setPhonestatus("关机");
            }else if("4".equals(phonestatus)){
                resultdata.setPhonestatus("空号");
            }else if("5".equals(phonestatus)){
                resultdata.setPhonestatus("停机");
            }else if("11".equals(phonestatus) || "91".equals(phonestatus)){
                resultdata.setPhonestatus("识别失败");
            }else if("-1".equals(phonestatus)){
                resultdata.setPhonestatus("查询失败");
            }
        }
        return list;
    }

    /**
     * 新增号码状态
     * 
     * @param phoneStatus 号码状态
     * @return 结果
     */
    @Override
    public int insertPhoneStatus(PhoneStatus phoneStatus)
    {
        phoneStatus.setCreateTime(DateUtils.getNowDate());
        return phoneStatusMapper.insertPhoneStatus(phoneStatus);
    }

    /**
     * 修改号码状态
     * 
     * @param phoneStatus 号码状态
     * @return 结果
     */
    @Override
    public int updatePhoneStatus(PhoneStatus phoneStatus)
    {
        phoneStatus.setUpdateTime(DateUtils.getNowDate());
        return phoneStatusMapper.updatePhoneStatus(phoneStatus);
    }

    /**
     * 删除号码状态对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePhoneStatusByIds(String ids)
    {
        return phoneStatusMapper.deletePhoneStatusByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除号码状态信息
     * 
     * @param id 号码状态ID
     * @return 结果
     */
    @Override
    public int deletePhoneStatusById(Long id)
    {
        return phoneStatusMapper.deletePhoneStatusById(id);
    }


}
