package com.ruoyi.assetspackage.service.impl.distribution;

import com.ruoyi.assetspackage.domain.distribution.TLcTaskAsset;
import com.ruoyi.assetspackage.mapper.distribution.TLcTaskAssetMapper;
import com.ruoyi.assetspackage.service.distribution.ITLcTaskAssetService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务Service业务层处理
 *
 * @author liurunkai
 * @date 2019-12-25
 */
@Slf4j
@Service("com.ruoyi.task.service.impl.TLcTaskAssetServiceImpl")
public class TLcTaskAssetServiceImpl implements ITLcTaskAssetService {

    @Autowired
    private TLcTaskAssetMapper tLcTaskAssetMapper;
    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public void batchInsertTask(List<TLcTaskAsset> taskList) {
        this.tLcTaskAssetMapper.batchInsertTask(taskList);
    }


    @Override
    public Long findDeptIdByOrgId(String orgId) {
        return this.tLcTaskAssetMapper.findDeptIdByOrgId(orgId);
    }


    @Override
    public List<SysUser> searchAllUser(SysUser sysUser) {
        return this.sysUserMapper.selectUserList(sysUser);
    }

    @Override
    public List<TLcTaskAsset> selectTaskListByCertificateNosAndOrdId(List<String> certificateNos, String orgId) {
        return this.tLcTaskAssetMapper.selectTaskListByCertificateNosAndOrdId(certificateNos, orgId);
    }

    @Override
    public List<TLcTaskAsset> selectSameCaseTaskList(String certificateNo, String orgId) {
        return this.tLcTaskAssetMapper.selectSameCaseTaskList(certificateNo, orgId);
    }
}
