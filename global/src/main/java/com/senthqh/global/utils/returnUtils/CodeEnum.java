package com.senthqh.global.utils.returnUtils;

/**
 * Created Author fengye
 * Created Date 2015/10/23
 * Created Time 18:12
 * 返回状态定义
 */
public enum CodeEnum {
    // 成功状态
    SUCCESS(200),
    // 系统内部错误
    ERROR(500),
    // 不支持GET请求
    NotSupportMethod(405),
    // 参数值不合法
    InvalidParameter(400),
    //失败状态
    Failed(900);

    private final int value;

    private CodeEnum(final int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
