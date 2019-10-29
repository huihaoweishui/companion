package com.example.companion.mapper;

import com.example.companion.entity.po.UserPartner;
import com.example.companion.entity.po.UserPartnerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPartnerMapper {
    long countByExample(UserPartnerExample example);

    int deleteByExample(UserPartnerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserPartner record);

    int insertSelective(UserPartner record);

    List<UserPartner> selectByExample(UserPartnerExample example);

    UserPartner selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserPartner record, @Param("example") UserPartnerExample example);

    int updateByExample(@Param("record") UserPartner record, @Param("example") UserPartnerExample example);

    int updateByPrimaryKeySelective(UserPartner record);

    int updateByPrimaryKey(UserPartner record);
}