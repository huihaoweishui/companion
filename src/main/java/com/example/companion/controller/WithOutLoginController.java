package com.example.companion.controller;

import com.example.companion.config.TokenConfig;
import com.example.companion.entity.CommonServerResponseDTO;
import com.example.companion.entity.po.User;
import com.example.companion.entity.wechat.WeChatLoginSuccessVO;
import com.example.companion.service.IUserService;
import com.example.companion.service.IWeChatService;
import com.xc.utils.algorithm.MyAlgorithmUtil;
import com.xc.utils.jwt.MyJWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @auther 薛晨
 * @date 2019/8/14
 * @time 13:25
 * @description 无需登录即可调用的接口
 */
@RestController
@RequestMapping("/")
@Validated
@Deprecated
public class WithOutLoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IWeChatService weChatService;

    @PostMapping("signUp")
//@Pattern(message = "不是合法的手机号", regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\\\d{8}$")不知道为何没用
    public CommonServerResponseDTO<Integer> signUp(@NotNull @Size(max = 11) String telephone,
                                                   @NotNull @Size(max = 64) String base64EncodedPassword) throws Exception {
//        getSessionKeyAndOpenId
        //1、得到密码明文 然后md5加密
        String decodedBase64Password = MyAlgorithmUtil.decodedBase64(base64EncodedPassword, "UTF-8");
        String md5EncodedPassword = MyAlgorithmUtil.md5(decodedBase64Password, "hehe");
        /* 2、验证手机验证码 TODO 阿里云要开短信服务 第一期暂时不做 */
        User user = new User();
        user.setTelephone(telephone);
        user.setPassword(md5EncodedPassword);
        userService.insertOrUpdate(user);
        return CommonServerResponseDTO.expectSuccess(user.getId());
    }

    /**
     * @param code 微信login()返回的临时凭证
     * @return
     * @throws Exception
     */
    @PostMapping("signIn")
    public CommonServerResponseDTO signIn(@NotNull String code, Integer userId) {
        WeChatLoginSuccessVO loginSuccessVO = weChatService.getSessionKeyAndOpenId(code);
        if (loginSuccessVO.getErrcode() == 0) {
            //生成token
            TokenConfig.claims.put("openId", loginSuccessVO.getOpenid());
            String token = MyJWTUtil.createToken(TokenConfig.secret, TokenConfig.issuer, TokenConfig.audience, TokenConfig.claims);
            //绑定user
            if (userId != null) {
                userService.bindUserToWeChat(userId, loginSuccessVO.getOpenid(), loginSuccessVO.getUnionid());
            }
            // 放入redis TODO
            return CommonServerResponseDTO.expectSuccess(token);
        } else {
            return CommonServerResponseDTO.defineAll(loginSuccessVO.getErrcode(), loginSuccessVO.getErrmsg(), null);
        }
    }

    @PostMapping("judgeBind")
    public CommonServerResponseDTO judgeBind(@NotNull String code) {
        WeChatLoginSuccessVO loginSuccessVO = weChatService.getSessionKeyAndOpenId(code);
        if (loginSuccessVO.getErrcode() == 0) {
            Boolean binded = userService.judgeBind(loginSuccessVO.getOpenid(), loginSuccessVO.getUnionid());
            if (!binded) {
                return CommonServerResponseDTO.expectSuccess(null);
            }
            //生成token
            TokenConfig.claims.put("openId", loginSuccessVO.getOpenid());
            String token = MyJWTUtil.createToken(TokenConfig.secret, TokenConfig.issuer, TokenConfig.audience, TokenConfig.claims);
            return CommonServerResponseDTO.expectSuccess(token);
        } else {
            return CommonServerResponseDTO.defineAll(loginSuccessVO.getErrcode(), loginSuccessVO.getErrmsg(), null);
        }
    }
}
