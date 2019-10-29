package com.example.companion.entity;

import com.example.companion.config.enums.BusinessStatusEnum;
import lombok.Data;

/**
 * @auther 薛晨
 * @date 2019/8/14
 * @time 14:12
 * @description 通用数据返回对象
 */
@Data
public class CommonServerResponseDTO<T> {

    private int status;

    private String message;

    private T data;

    public CommonServerResponseDTO(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonServerResponseDTO expectSuccess(T data) {
        return new CommonServerResponseDTO(BusinessStatusEnum.EXPECT.getCode(), "预期返回", data);
    }

    public static <T> CommonServerResponseDTO defineAll(int status, String message, T data) {
        return new CommonServerResponseDTO(status, message, data);
    }
}
