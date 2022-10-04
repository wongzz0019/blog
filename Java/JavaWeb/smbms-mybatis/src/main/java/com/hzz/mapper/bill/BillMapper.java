package com.hzz.mapper.bill;

import com.hzz.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bosco
 * @date 2021/12/2
 */

public interface BillMapper {
    //根据供应商ID查询订单数量
    public int getBillCountByProviderId(@Param("providerId") Integer providerId);

    //增加订单
    public int add(Bill bill);

    //通过查询条件获取供应商列表-getBillList
    public List<Bill> getBillList(@Param("provideName") String provideName,
                                  @Param("providerId") Integer providerId,
                                  @Param("isPayment") String isPayment,
                                  @Param("from") Integer currentPageNo,
                                  @Param("pageSize") Integer pageSize);

    //通过条件查询-订单表记录数
    public int getBillCount(@Param("productName") String productName,@Param("providerId") Integer providerId);

    //通过delId删除Bill
    public int deleteBillById(@Param("id") Integer delId);

    //通过billId获取Bill
    public Bill getBillById(@Param("id") Integer id);

    //修改订单信息
    public int modify(Bill bill);

    //根据供应商ID删除订单信息
    public int deleteBillByProviderId(@Param("providerId") Integer providerId);

}
