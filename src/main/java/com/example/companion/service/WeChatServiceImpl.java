package com.example.companion.service;

import com.alibaba.fastjson.JSONObject;
import com.example.companion.config.wechat.WeChatConfig;
import com.example.companion.entity.wechat.WeChatLoginSuccessVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @auther 薛晨
 * @date 2019/8/15
 * @time 16:08
 * @description
 */
@Service
public class WeChatServiceImpl implements IWeChatService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeChatConfig weChatConfig;

    @Override
    public WeChatLoginSuccessVO getSessionKeyAndOpenId(String code) {
        String url = weChatConfig.getLoginUrl() + "?appid=" + weChatConfig.getAppId() + "&secret=" + weChatConfig.getSecret() + "&js_code=" + code + "&grant_type=authorization_code";
        WebClient webClient = WebClient.create();
        Mono<String> mono = webClient.get().uri(url).retrieve().bodyToMono(String.class);
        String res = mono.block();
        WeChatLoginSuccessVO loginSuccessVO = JSONObject.parseObject(res, WeChatLoginSuccessVO.class);
        return loginSuccessVO;
    }
}
