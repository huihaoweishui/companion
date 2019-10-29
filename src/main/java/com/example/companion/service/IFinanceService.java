package com.example.companion.service;

import com.example.companion.entity.result.DictionaryVO;
import com.example.companion.entity.result.MonthInOutTotalVO;
import com.example.companion.entity.result.MonthInOutVO;
import com.example.companion.entity.result.RecordVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @auther 薛晨
 * @date 2019/10/21
 * @time 15:36
 * @description
 */
public interface IFinanceService {

    List<DictionaryVO> types();

    PageInfo<MonthInOutVO> monthInOut(Integer pageNum, Integer pageSize, String yearAndMonth, Integer type, Integer typeId, String openId, String partnerOpenIds);

    void addNewRecord(Integer typeId, String amount, String remark, String openId);

    List<RecordVO> types2();

    List<MonthInOutTotalVO> monthInOutTotal(String yearAndMonth, String openId, String partnerOpenIds);
}
