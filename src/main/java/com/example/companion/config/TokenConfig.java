package com.example.companion.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther 薛晨
 * @date 2019/8/17
 * @time 13:16
 * @description
 */
@Data
@ConfigurationProperties(prefix = "token")
public class TokenConfig {
    public static String issuer;
    public static String audience;
    public static String secret;
    public static Map<String, String> claims = new HashMap<>();
}
