package com.example.companion.service;

/**
 * @auther 薛晨
 * @date 2019/10/22
 * @time 13:32
 * @description
 */
public interface IWeiXinUserService {
    Boolean judgeBind(String openid, String unionid);

    void bind(String openid, String unionid, String appId);
}
