package com.example.companion.entity.result;

import lombok.Data;

/**
 * @auther 薛晨
 * @date 2019/10/21
 * @time 15:37
 * @description 字典表
 */
@Data
public class DictionaryVO {

    private Integer id;
    //类型id
    private Integer typeId;
    // 描述
    private String name;
    // 图标地址
    private String icon;
}
