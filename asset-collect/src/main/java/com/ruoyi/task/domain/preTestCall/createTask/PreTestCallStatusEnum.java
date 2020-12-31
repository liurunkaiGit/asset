package com.ruoyi.task.domain.preTestCall.createTask;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author guozeqi
 * @create 2020-12-17
 */
@Getter
@AllArgsConstructor
public enum PreTestCallStatusEnum {
    SCZ("UPLOADING", "上传中"),
    WKS("NOT_STARTED", "未开始"),
    ZXZ("IN_PROGRESS", "执行中"),
    YZT("PAUSED", "已暂停"),
    YQX("CANCELLED", "已取消"),
    YWC("FINISHED", "已完成");

    private String code;
    private String name;

    public static String getCode(String name) {
        PreTestCallStatusEnum result = Arrays.stream(PreTestCallStatusEnum.values())
                .filter(obj -> obj.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new RuntimeException(String.format("值错误(是/否)，值是%s", name)));
        return result.getCode();
    }

}
