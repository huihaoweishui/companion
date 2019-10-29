package com.example.companion.mapper;

import com.example.companion.entity.po.UserDetail;
import org.apache.ibatis.annotations.Select;

/**
 * @auther 薛晨
 * @date 2019/8/17
 * @time 13:54
 * @description
 */
public interface ExtendedUserMapper {

    @Select("select detail.id,detail.nickname,detail.head_image_url,detail.id,detail.user_id from user_detail detail inner join user_wechat wechat on  wechat.user_id = detail.user_id and wechat.open_id = #{openId}")
    UserDetail getPartner(String openId);
}
