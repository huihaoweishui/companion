package com.example.companion.entity.wechat;

import lombok.Data;

/**
 * @auther 薛晨
 * @date 2019/8/15
 * @time 16:10
 * @description
 */
@Data
public class WeChatLoginSuccessVO {
    private String openid;
    private String session_key;
    private String unionid;
    //-1 0 40029 45011
    private int errcode;
    private String errmsg;
}
