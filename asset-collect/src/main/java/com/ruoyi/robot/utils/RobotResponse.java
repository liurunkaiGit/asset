package com.ruoyi.robot.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/11 14:27
 */
@Data
@Accessors(chain = true)
public class RobotResponse {

    /**
     * 接口响应码:
     * 200:成功
     * 401:校验数据错误
     * 404:资源未找到
     * 403:权限不足
     * 412:参数错误
     * 500:未知错误
     */
    private Integer code;

    /**
     * API 调用返回结果（若调用失败， 则为 null）
     */
    private Object data;

    /**
     * 响应信息提示
     */
    private String resultMsg;

    /**
     * 错误调试跟踪信息， 仅内部使用
     */
    private String errorStackTrace;

    /**
     * 请求链路跟踪 id， 仅内部使用
     */
    private String requestId;
}
