package com.ruoyi.duncase.service;

import com.ruoyi.duncase.domain.Assets;
import com.ruoyi.duncase.domain.TLcDuncase;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.utils.Response;

import java.util.List;

/**
 * 案件Service接口
 *
 * @author liurunkai
 * @date 2019-12-25
 */
public interface AsyncITLcDuncaseService {

    void updateDuncase(List<TLcTask> taskList, Integer code, Integer status);

    void updateDuncase(List<TLcTask> taskList);
}
