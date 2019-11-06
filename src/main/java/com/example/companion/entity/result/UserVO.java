package com.example.companion.entity.result;

import lombok.Data;

import java.util.Date;

/**
 * @auther 薛晨
 * @date 2019/11/5
 * @time 15:13
 * @description
 */
@Data
public class UserVO {

    private String nickName;

    private String avatarUrl;

    private String gender;

    private Date loveDay;
}
