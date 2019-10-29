package com.example.companion.mapper;

import com.example.companion.entity.result.DictionaryVO;
import com.example.companion.entity.result.RecordVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther 薛晨
 * @date 2019/10/21
 * @time 15:46
 * @description
 */
public interface ExtendedDictionaryMapper {
    @Select("select id, type_id typeId,icon,name from enum where status = 0 ")
    List<DictionaryVO> types();

    List<RecordVO> types2();

}
