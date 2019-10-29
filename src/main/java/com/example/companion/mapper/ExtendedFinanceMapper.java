package com.example.companion.mapper;

import com.example.companion.entity.result.MonthInOutTotalVO;
import com.example.companion.entity.result.MonthInOutVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther 薛晨
 * @date 2019/10/22
 * @time 11:32
 * @description
 */
public interface ExtendedFinanceMapper {
    List<MonthInOutVO> monthInOut(@Param("yearAndMonth") String yearAndMonth, @Param("type") Integer type, @Param("typeId") Integer typeId,
                                  @Param("openId") String openId, @Param("openIds") String[] openIds);

    List<MonthInOutTotalVO> monthInOutTotal(@Param("yearAndMonth") String yearAndMonth, @Param("openId") String openId, @Param("openIds") String partnerOpenIds);
}
