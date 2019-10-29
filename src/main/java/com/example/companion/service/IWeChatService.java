package com.example.companion.service;

import com.example.companion.entity.wechat.WeChatLoginSuccessVO;

/**
 * @auther 薛晨
 * @date 2019/8/15
 * @time 16:04
 * @description 微信服务端服务接口
 */
public interface IWeChatService {


    WeChatLoginSuccessVO getSessionKeyAndOpenId(String code);
}
