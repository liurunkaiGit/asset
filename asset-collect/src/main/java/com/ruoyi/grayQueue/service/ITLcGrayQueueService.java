package com.ruoyi.grayQueue.service;

import com.ruoyi.grayQueue.domain.TLcGrayQueue;

import java.util.List;

/**
 * 灰色队列Service接口
 *
 * @author liurunkai
 * @date 2020-01-09
 */
public interface ITLcGrayQueueService {
    /**
     * 查询灰色队列
     *
     * @param id 灰色队列ID
     * @return 灰色队列
     */
    public TLcGrayQueue selectTLcGrayQueueById(Long id);

    /**
     * 查询灰色队列列表
     *
     * @param tLcGrayQueue 灰色队列
     * @return 灰色队列集合
     */
    public List<TLcGrayQueue> selectTLcGrayQueueList(TLcGrayQueue tLcGrayQueue);

    /**
     * 新增灰色队列
     *
     * @param tLcGrayQueue 灰色队列
     * @return 结果
     */
    public int insertTLcGrayQueue(TLcGrayQueue tLcGrayQueue);

    /**
     * 修改灰色队列
     *
     * @param tLcGrayQueue 灰色队列
     * @return 结果
     */
    public int updateTLcGrayQueue(TLcGrayQueue tLcGrayQueue);

    /**
     * 批量删除灰色队列
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcGrayQueueByIds(String ids);

    /**
     * 删除灰色队列信息
     *
     * @param id 灰色队列ID
     * @return 结果
     */
    public int deleteTLcGrayQueueById(Long id);

    /**
     * 批量插入灰色队列
     *
     * @param tLcGrayQueue
     * @param taskIds
     * @return
     */
    int addGrayQueue(TLcGrayQueue tLcGrayQueue, String taskIds);
}
