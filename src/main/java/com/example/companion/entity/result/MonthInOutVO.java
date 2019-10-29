package com.example.companion.entity.result;

import lombok.Data;

/**
 * @auther 薛晨
 * @date 2019/10/22
 * @time 11:07
 * @description
 */
@Data
public class MonthInOutVO {
    //记录id
    private Integer id;
    //类型icon
    private String icon;
    //类型名称
    private String name;
    //类型id 1收入 2 之处
    private Integer typeId;
    //备注
    private String remark;
    //记账时间
    private String dateTime;
    //记账金额
    private String amount;
}
