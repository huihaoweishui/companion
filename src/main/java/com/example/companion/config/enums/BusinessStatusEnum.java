package com.example.companion.config.enums;

/**
 * @auther 薛晨
 * @date 2019/8/14
 * @time 14:18
 * @description 业务代码
 */
public enum BusinessStatusEnum {
    EXPECT(0,"按预期返回");

    private int code;

    private String description;

     BusinessStatusEnum(int code,String description){
        this.code=code;
        this.description=description;
    }

    public Integer getCode() {
        return code;
    }

}
