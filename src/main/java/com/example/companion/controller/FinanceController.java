package com.example.companion.controller;

import com.example.companion.entity.CommonServerResponseDTO;
import com.example.companion.entity.result.DictionaryVO;
import com.example.companion.entity.result.MonthInOutTotalVO;
import com.example.companion.entity.result.MonthInOutVO;
import com.example.companion.entity.result.RecordVO;
import com.example.companion.service.IFinanceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @auther 薛晨
 * @date 2019/10/21
 * @time 15:34
 * @description
 */
@RestController
@RequestMapping("/finance")
@Validated
public class FinanceController {

    @Autowired
    private IFinanceService financeService;

    /**
     * 交易类型
     *
     * @return
     */
    @PostMapping("/type")
    public CommonServerResponseDTO<List<DictionaryVO>> types() {
        List<DictionaryVO> types = financeService.types();
        return CommonServerResponseDTO.expectSuccess(types);
    }

    /**
     * 交易类型变体
     *
     * @return
     */
    @PostMapping("/type2")
    public CommonServerResponseDTO<List<RecordVO>> types2() {
        List<RecordVO> types = financeService.types2();
        return CommonServerResponseDTO.expectSuccess(types);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param yearAndMonth   yyyy-MM
     * @param type           收入1 支出2
     * @param typeId         具体的收支类型
     * @param openId         当前微信账号
     * @param partnerOpenIds 伴侣或者合作伙伴
     * @return
     */
    @PostMapping("/monthInOut")
    public CommonServerResponseDTO<Page<MonthInOutVO>> monthInOut(@NotNull Integer pageNum, @NotNull Integer pageSize,
                                                                  @NotNull String yearAndMonth, @NotNull String openId,
                                                                  Integer type, Integer typeId, String partnerOpenIds) {
        PageInfo<MonthInOutVO> types = financeService.monthInOut(pageNum, pageSize, yearAndMonth, type, typeId, openId, partnerOpenIds);
        return CommonServerResponseDTO.expectSuccess(types);
    }

    /**
     * 新增收支记录
     *
     * @param amount
     * @param typeId
     * @param openId
     * @param remark
     * @return
     */
    @PostMapping("/add")
    public CommonServerResponseDTO add(@NotNull String amount, @NotNull Integer typeId, @NotNull String openId, String remark) {
        financeService.addNewRecord(typeId, amount, remark, openId);
        return CommonServerResponseDTO.expectSuccess(null);
    }

    /**
     * @param yearAndMonth
     * @param openId
     * @param partnerOpenIds
     * @return
     */
    @PostMapping("/monthInOutTotal")
    public CommonServerResponseDTO monthInOutTotal(@NotNull String yearAndMonth, @NotNull String openId, String partnerOpenIds) {
        List<MonthInOutTotalVO> inAndOut = financeService.monthInOutTotal(yearAndMonth, openId, partnerOpenIds);
        String monthInAndOut;
        if (inAndOut.isEmpty()) {
            monthInAndOut = "0,0";
        } else {
            if (inAndOut.size() == 1) {
                MonthInOutTotalVO monthInOutTotalVO = inAndOut.get(0);
                Integer typeId = monthInOutTotalVO.getTypeId();
                if (typeId == 1) {
                    monthInAndOut = monthInOutTotalVO.getAmount() + ",0";
                } else {
                    monthInAndOut = "0," + monthInOutTotalVO.getAmount();
                }
            } else {
                monthInAndOut = inAndOut.get(0).getAmount() + "," + inAndOut.get(1).getAmount();
            }
        }
        return CommonServerResponseDTO.expectSuccess(monthInAndOut);
    }
}
