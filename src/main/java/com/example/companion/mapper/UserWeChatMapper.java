package com.example.companion.mapper;

import com.example.companion.entity.po.UserWeChat;
import com.example.companion.entity.po.UserWeChatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserWeChatMapper {
    long countByExample(UserWeChatExample example);

    int deleteByExample(UserWeChatExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserWeChat record);

    int insertSelective(UserWeChat record);

    List<UserWeChat> selectByExample(UserWeChatExample example);

    UserWeChat selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserWeChat record, @Param("example") UserWeChatExample example);

    int updateByExample(@Param("record") UserWeChat record, @Param("example") UserWeChatExample example);

    int updateByPrimaryKeySelective(UserWeChat record);

    int updateByPrimaryKey(UserWeChat record);
}