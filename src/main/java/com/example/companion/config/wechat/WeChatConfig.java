package com.example.companion.config.wechat;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @auther 薛晨
 * @date 2019/8/14
 * @time 13:28
 * @description 微信配置
 */
@Component
@ConfigurationProperties(prefix = "wechat")
@Data
public class WeChatConfig {

    private String appId;

    private String secret;

    private String accessTokenUrl;

    private String refreshTokenUrl;

    private String userInfoUrl;

    private String loginUrl;
}
