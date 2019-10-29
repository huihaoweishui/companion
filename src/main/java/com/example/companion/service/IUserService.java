package com.example.companion.service;

import com.example.companion.entity.po.User;
import com.example.companion.entity.po.UserDetail;
import com.example.companion.entity.po.UserWeChat;

/**
 * @auther 薛晨
 * @date 2019/8/14
 * @time 15:07
 * @description user、user_wechat
 */
public interface IUserService {
    /**
     * 保存或更新user 单表
     *
     * @param user
     * @return
     */
    User insertOrUpdate(User user);

    /**
     * 根据openId返回绑定用户 不管用户状态
     *
     * @param openId
     * @return
     */
    User selectByOpenId(String openId);

    /**
     * 将用户和微信绑定
     * @param userId
     * @return
     */
    UserWeChat bindUserToWeChat(Integer userId,String openId,String unionId);

    /**
     * 判断是否有用户绑定openId
     * @param openid
     * @param unionid
     * @return
     */
    Boolean judgeBind(String openid, String unionid);

    /**
     * 获取伴侣
     * @param openId
     * @return
     */
    UserDetail getPartner(String openId);
}
