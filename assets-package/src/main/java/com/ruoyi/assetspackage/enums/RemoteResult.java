package com.ruoyi.assetspackage.enums;

import com.ruoyi.common.utils.StringUtils;

import java.util.HashMap;

/**
 * @author guozeqi
 * @create 2020-01-13
 */

public class RemoteResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type
    {
        /** 成功 */
        SUCCESS(200),
        /** 错误 */
        ERROR(500);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int getValue()
        {
            return this.value;
        }
    }

    private RemoteResult(int status,String msg,Object data){
        super.put(CODE_TAG, status);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static RemoteResult success()

    {
        return new RemoteResult(Type.SUCCESS.getValue(),"成功",null);
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static RemoteResult success(String msg)

    {
        return new RemoteResult(Type.SUCCESS.getValue(),msg,null);
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static RemoteResult success(String msg,Object data)

    {
        return new RemoteResult(Type.SUCCESS.getValue(),msg,data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static RemoteResult error()

    {
        return new RemoteResult(Type.ERROR.getValue(),"失败",null);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static RemoteResult error(String msg)

    {
        return new RemoteResult(Type.ERROR.getValue(),msg,null);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static RemoteResult error(String msg,Object data)

    {
        return new RemoteResult(Type.ERROR.getValue(),msg,data);
    }



}
