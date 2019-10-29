package com.example.companion.mapper;

import com.example.companion.entity.po.WeiXinConfig;
import com.example.companion.entity.po.WeiXinConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeiXinConfigMapper {
    long countByExample(WeiXinConfigExample example);

    int deleteByExample(WeiXinConfigExample example);

    int deleteByPrimaryKey(String appId);

    int insert(WeiXinConfig record);

    int insertSelective(WeiXinConfig record);

    List<WeiXinConfig> selectByExample(WeiXinConfigExample example);

    WeiXinConfig selectByPrimaryKey(String appId);

    int updateByExampleSelective(@Param("record") WeiXinConfig record, @Param("example") WeiXinConfigExample example);

    int updateByExample(@Param("record") WeiXinConfig record, @Param("example") WeiXinConfigExample example);

    int updateByPrimaryKeySelective(WeiXinConfig record);

    int updateByPrimaryKey(WeiXinConfig record);
}