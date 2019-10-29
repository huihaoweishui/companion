package com.example.companion.service.impl;

import com.example.companion.entity.po.Finance;
import com.example.companion.entity.result.DictionaryVO;
import com.example.companion.entity.result.MonthInOutTotalVO;
import com.example.companion.entity.result.MonthInOutVO;
import com.example.companion.entity.result.RecordVO;
import com.example.companion.mapper.ExtendedDictionaryMapper;
import com.example.companion.mapper.ExtendedFinanceMapper;
import com.example.companion.mapper.FinanceMapper;
import com.example.companion.service.IFinanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @auther 薛晨
 * @date 2019/10/21
 * @time 15:38
 * @description
 */
@Service
public class FinanceServiceImpl implements IFinanceService {

    @Autowired
    private ExtendedDictionaryMapper extendedDictionaryMapper;
    @Autowired
    private ExtendedFinanceMapper extendedFinanceMapper;
    @Autowired
    private FinanceMapper financeMapper;

    @Override
    public List<DictionaryVO> types() {
        return extendedDictionaryMapper.types();
    }

    @Override
    public List<MonthInOutTotalVO> monthInOutTotal(String yearAndMonth, String openId, String partnerOpenIds) {
        return extendedFinanceMapper.monthInOutTotal(yearAndMonth, openId, partnerOpenIds);
    }

    @Override
    public List<RecordVO> types2() {
        return extendedDictionaryMapper.types2();
    }

    @Override
    @Transactional
    public void addNewRecord(Integer typeId, String amount, String remark, String openId) {
        Finance finance = new Finance();
        finance.setAmount(new BigDecimal(amount));
        finance.setEnumId(typeId);
        finance.setRemark(remark);
        finance.setOpenId(openId);
        financeMapper.insertSelective(finance);
    }

    @Override
    public PageInfo<MonthInOutVO> monthInOut(Integer pageNum, Integer pageSize, String yearAndMonth, Integer type, Integer typeId, String openId, String partnerOpenIds) {
        PageHelper.startPage(pageNum, pageSize);
        String[] openIds = null;
        if (!StringUtils.isNullOrEmpty(partnerOpenIds)) {
            openIds = partnerOpenIds.split(",");
        }
        List<MonthInOutVO> list = extendedFinanceMapper.monthInOut(yearAndMonth, type, typeId, openId, openIds);
        return new PageInfo(list);
    }
}
