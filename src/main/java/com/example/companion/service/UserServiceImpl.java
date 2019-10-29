package com.example.companion.service;

import com.example.companion.entity.po.User;
import com.example.companion.entity.po.UserDetail;
import com.example.companion.entity.po.UserWeChat;
import com.example.companion.entity.po.UserWeChatExample;
import com.example.companion.mapper.ExtendedUserMapper;
import com.example.companion.mapper.UserMapper;
import com.example.companion.mapper.UserWeChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @auther 薛晨
 * @date 2019/8/14
 * @time 15:09
 * @description
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserWeChatMapper weChatMapper;
    @Autowired
    private ExtendedUserMapper extendedUserMapper;

    @Override
    @Transactional
    public User insertOrUpdate(User user) {
        if (user.getId() != null) {
            userMapper.updateByPrimaryKeySelective(user);
        } else {
            userMapper.insertSelective(user);
            UserDetail detail = new UserDetail();
            detail.setUserId(user.getId());
        }
        return user;
    }

    @Override
    public User selectByOpenId(String openId) {
        UserWeChatExample example = new UserWeChatExample();
        example.createCriteria().andOpenIdEqualTo(openId);
        List<UserWeChat> userWeChats = weChatMapper.selectByExample(example);
        if (!userWeChats.isEmpty()) {
            UserWeChat userWeChat = userWeChats.get(0);
            return userMapper.selectByPrimaryKey(userWeChat.getUserId());
        }
        return null;
    }

    @Override
    @Transactional
    public UserWeChat bindUserToWeChat(Integer userId, String openId, String unionId) {
        UserWeChat weChat = new UserWeChat();
        weChat.setUserId(userId);
        weChat.setOpenId(openId);
        weChat.setUnionId(unionId);
        weChatMapper.insertSelective(weChat);
        return weChat;
    }

    @Override
    public Boolean judgeBind(String openid, String unionid) {
        UserWeChatExample example = new UserWeChatExample();
        if (!StringUtils.isEmpty(unionid)) {
            example.createCriteria().andOpenIdEqualTo(openid).andUnionIdEqualTo(unionid);
        } else {
            example.createCriteria().andOpenIdEqualTo(openid);
        }
        List<UserWeChat> userWeChats = weChatMapper.selectByExample(example);
        return !userWeChats.isEmpty();
    }

    @Override
    public UserDetail getPartner(String openId) {
        return extendedUserMapper.getPartner(openId);
    }
}
