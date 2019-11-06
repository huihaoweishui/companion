package com.example.companion.controller;

import com.example.companion.entity.CommonServerResponseDTO;
import com.example.companion.entity.param.UserDO;
import com.example.companion.entity.result.UserVO;
import com.example.companion.exception.CompanionException;
import com.example.companion.service.IWeiXinUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @auther 薛晨
 * @date 2019/11/5
 * @time 15:09
 * @description
 */
@RestController
@RequestMapping("/companion")
@Validated
public class CompanionController {

    @Autowired
    private IWeiXinUserService userService;

    @PostMapping("partner")
    public CommonServerResponseDTO partner(@NotNull String openId) {
        UserVO vo = userService.getCompanion(openId);
        return CommonServerResponseDTO.expectSuccess(vo);
    }

    @PostMapping("info")
    public CommonServerResponseDTO info(@NotNull @RequestBody UserDO userDO) {
        userService.updateUserInfo(userDO);
        return CommonServerResponseDTO.expectSuccess(null);
    }

    @PostMapping("bind")
    public CommonServerResponseDTO bind(@NotNull String openId, @NotNull String companionOpenId) {
        try {
            userService.bindPartner(openId, companionOpenId);
        } catch (CompanionException e) {
            return CommonServerResponseDTO.error(e.getMessage());
        }
        return CommonServerResponseDTO.expectSuccess(null);
    }
}
