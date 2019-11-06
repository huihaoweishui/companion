package com.example.companion.controller;

import com.example.companion.config.wechat.WeChatConfig;
import com.example.companion.entity.CommonServerResponseDTO;
import com.example.companion.entity.wechat.WeChatLoginSuccessVO;
import com.example.companion.service.IWeChatService;
import com.example.companion.service.IWeiXinUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @auther 薛晨
 * @date 2019/10/22
 * @time 13:27
 * @description
 */
@RestController
@RequestMapping("user")
public class SignInController {
    @Autowired
    private IWeChatService weChatService;

    @Autowired
    private IWeiXinUserService userService;

    @Autowired
    private WeChatConfig weChatConfig;

    @PostMapping("judgeBind")
    public CommonServerResponseDTO judgeBind(@NotNull String code) {
        WeChatLoginSuccessVO loginSuccessVO = weChatService.getSessionKeyAndOpenId(code);
        if (loginSuccessVO.getErrcode() == 0) {
            Boolean binded = userService.judgeBind(loginSuccessVO.getOpenid(), loginSuccessVO.getUnionid());
            if (binded) {
                return CommonServerResponseDTO.expectSuccess(loginSuccessVO.getOpenid());
            }
            //绑定
            userService.bind(loginSuccessVO.getOpenid(), loginSuccessVO.getUnionid(), weChatConfig.getAppId());
            return CommonServerResponseDTO.expectSuccess(loginSuccessVO.getOpenid());
        } else {
            return CommonServerResponseDTO.defineAll(loginSuccessVO.getErrcode(), loginSuccessVO.getErrmsg(), null);
        }
    }
}
