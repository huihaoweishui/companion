package com.example.companion.service.impl;

import com.example.companion.entity.param.UserDO;
import com.example.companion.entity.po.WeiXinUser;
import com.example.companion.entity.po.WeiXinUserExample;
import com.example.companion.entity.result.UserVO;
import com.example.companion.exception.CompanionException;
import com.example.companion.mapper.WeiXinUserMapper;
import com.example.companion.service.IWeiXinUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
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
    public void updateUserInfo(UserDO userDO) {
        String openId = userDO.getOpenId();
        if (!StringUtils.isEmpty(openId)) {
            WeiXinUser user = userMapper.selectByPrimaryKey(openId);
            BeanUtils.copyProperties(userDO, user);
            userMapper.updateByPrimaryKeySelective(user);
        }
    }

    @Override
    @Transactional
    public void bindPartner(String openId, String companionOpenId) throws CompanionException {
        Date loveDay = new Date();
        WeiXinUser user = userMapper.selectByPrimaryKey(openId);
        if (user != null && StringUtils.isEmpty(user.getCompanionOpenId())) {
            user.setCompanionOpenId(companionOpenId);
            user.setLoveTime(loveDay);
            userMapper.updateByPrimaryKeySelective(user);
            WeiXinUser user1 = userMapper.selectByPrimaryKey(companionOpenId);
            if (user1 != null) {
                user1.setCompanionOpenId(openId);
                user1.setLoveTime(loveDay);
                userMapper.updateByPrimaryKeySelective(user1);
            }
        } else {
            throw new CompanionException("你来晚一步，对方已经有心仪的对象了");
        }
    }

    @Override
    public UserVO getCompanion(String openId) {
        UserVO vo = null;
        WeiXinUser user = userMapper.selectByPrimaryKey(openId);
        if (user != null) {
            String companionOpenId = user.getCompanionOpenId();
            if (!StringUtils.isEmpty(companionOpenId)) {
                WeiXinUser companion = userMapper.selectByPrimaryKey(companionOpenId);
                if (companion != null) {
                    vo = new UserVO();
                    vo.setAvatarUrl(companion.getAvatarUrl());
                    vo.setNickName(companion.getNickName());
                    vo.setLoveDay(companion.getLoveTime());
                    vo.setGender(companion.getGender() == 0 ? "妖" : (companion.getGender() == 1 ? "他" : "她"));
                    Date nowDate = new Date();
                    double togetherDays = Math.ceil(Double.valueOf((nowDate.getTime() - companion.getLoveTime().getTime()) + ""));
                    vo.setTogetherDay((short) (togetherDays == 0 ? 1 : togetherDays));
                }
            }
        }
        return vo;
    }

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
