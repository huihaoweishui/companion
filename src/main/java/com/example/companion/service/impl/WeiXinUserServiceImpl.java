package com.example.companion.service.impl;

import com.example.companion.entity.po.WeiXinUser;
import com.example.companion.entity.po.WeiXinUserExample;
import com.example.companion.mapper.WeiXinUserMapper;
import com.example.companion.service.IWeiXinUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @auther 薛晨
 * @date 2019/10/22
 * @time 13:33
 * @description
 */
@Service
public class WeiXinUserServiceImpl implements IWeiXinUserService {
    @Autowired
    private WeiXinUserMapper userMapper;

    @Override
    @Transactional
    public void bind(String openid, String unionId, String appId) {
        WeiXinUser user = new WeiXinUser();
        user.setAppId(appId);
        user.setOpenId(openid);
        user.setUnionId(unionId);
        userMapper.insertSelective(user);
    }

    @Override
    public Boolean judgeBind(String openid, String unionid) {
        WeiXinUserExample example = new WeiXinUserExample();
        if (!StringUtils.isEmpty(unionid)) {
            example.createCriteria().andOpenIdEqualTo(openid).andUnionIdEqualTo(unionid);
        } else {
            example.createCriteria().andOpenIdEqualTo(openid);
        }
        List<WeiXinUser> userWeChats = userMapper.selectByExample(example);
        return !userWeChats.isEmpty();
    }
}
