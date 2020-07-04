package com.ruoyi.utils;

import com.ruoyi.common.domain.CloseCase;
import com.ruoyi.system.domain.SysUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: 结案接口使用
 * @author: liurunkai
 * @Date: 2020/3/23 10:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CloseCaseUser {
    private List<CloseCase> closeCaseList; //结案集合
    private SysUser sysUser; //当前用户信息
}
