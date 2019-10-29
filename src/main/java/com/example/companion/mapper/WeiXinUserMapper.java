package com.example.companion.mapper;

import com.example.companion.entity.po.WeiXinUser;
import com.example.companion.entity.po.WeiXinUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeiXinUserMapper {
    long countByExample(WeiXinUserExample example);

    int deleteByExample(WeiXinUserExample example);

    int deleteByPrimaryKey(String openId);

    int insert(WeiXinUser record);

    int insertSelective(WeiXinUser record);

    List<WeiXinUser> selectByExample(WeiXinUserExample example);

    WeiXinUser selectByPrimaryKey(String openId);

    int updateByExampleSelective(@Param("record") WeiXinUser record, @Param("example") WeiXinUserExample example);

    int updateByExample(@Param("record") WeiXinUser record, @Param("example") WeiXinUserExample example);

    int updateByPrimaryKeySelective(WeiXinUser record);

    int updateByPrimaryKey(WeiXinUser record);
}