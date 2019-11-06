package com.example.companion.service;

import com.example.companion.entity.param.UserDO;
import com.example.companion.entity.result.UserVO;
import com.example.companion.exception.CompanionException;

/**
 * @auther 薛晨
 * @date 2019/10/22
 * @time 13:32
 * @description
 */
public interface IWeiXinUserService {
    Boolean judgeBind(String openid, String unionid);

    void bind(String openid, String unionid, String appId);

    UserVO getCompanion(String openId);

    void bindPartner(String openId, String companionOpenId) throws CompanionException;

    void updateUserInfo(UserDO userDO);
}
