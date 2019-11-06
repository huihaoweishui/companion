package com.example.companion.entity.param;

import lombok.Data;

/**
 * @auther 薛晨
 * @date 2019/11/5
 * @time 17:13
 * @description
 */
@Data
public class UserDO {

    private String openId;

    private String nickName;

    private String avatarUrl;

    private Byte gender;

    private String country;

    private String province;

    private String city;

    private String language;
}
