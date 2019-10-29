package com.example.companion.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.companion.config.TokenConfig;
import com.example.companion.entity.CommonServerResponseDTO;
import com.example.companion.entity.po.UserDetail;
import com.example.companion.service.IUserService;
import com.xc.utils.jwt.MyJWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther 薛晨
 * @date 2019/8/17
 * @time 13:00
 * @description
 */
@RestController
@RequestMapping("/user")
@Deprecated
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/partner")
    public CommonServerResponseDTO getPartner(HttpServletRequest request) {
        String token = request.getHeader("token");
        Boolean tokenLegal = MyJWTUtil.verifyToken(token, TokenConfig.secret, TokenConfig.issuer, TokenConfig.audience);
        if (tokenLegal) {
            DecodedJWT decode = JWT.decode(token);
            String openId = decode.getClaim("openId").asString();
            UserDetail partner = userService.getPartner(openId);
            return CommonServerResponseDTO.expectSuccess(partner);
        }
        return CommonServerResponseDTO.expectSuccess(null);
    }
}
