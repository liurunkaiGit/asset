package com.ruoyi.assetspackage.domain.phoneStatus;

import lombok.Data;

/**
 * @author guozeqi
 * @create 2020-08-28
 */
@Data
public class PhoneStatusResponse {
    private static final long serialVersionUID = 1L;

    private String code;
    private String swift_number;
    private Flag flag;
    private PhoneStatus phoneStatus;
    private code_detail codeDetail;

    @Data
    public static class Flag{
        private String phonestatus;//1查询成功，有数据；0查询成功，无数据 99查询失败（预留）
        private String infoverification;
    }
    @Data
    public static class PhoneStatus{
        private String result;//0 -> 正在识别；1 -> 待接听铃声；2 -> 彩铃；21 -> 包含广告语，运营商铃声广告；22 -> 音乐；31 -> 关机；32 -> 通话中；33 -> 无法接通，无法接听，不在服务区；34 -> 呼入限制；35 -> 已设置来电提醒；36 -> 呼叫转移；4 -> 空号；5 -> 停机；6 -> 暂停服务；10 -> 大概率彩铃（90%）；11 -> 识别失败（疑似小号或携号转网）；12 -> 识别失败；91-> 识别失败；92-> 识别失败
        private String costTime;//返回时长
        private String operation;//1：电信 2：联通 3：移动 4：其他运营商，如170
    }
    @Data
    public static class code_detail{
        private String PhoneStatus;
    }


}
