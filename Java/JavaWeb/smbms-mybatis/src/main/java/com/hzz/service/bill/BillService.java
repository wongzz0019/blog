package com.hzz.service.bill;

import com.hzz.pojo.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bosco
 * @date 2021/12/16
 */


public interface BillService {

    //根据供应商ID查询订单数量
    public int getBillCountByProviderId(Integer providerId);

    //增加订单
    public int add(Bill bill);

    //通过查询条件获取供应商列表-getBillList
    public List<Bill> getBillList(String provideName, Integer providerId, String isPayment,
                                  Integer currentPageNo, Integer pageSize);

    //通过条件查询-订单表记录数
    public int getBillCount(String productName,Integer providerId);

    //通过delId删除Bill
    public int deleteBillById(Integer delId);

    //通过billId获取Bill
    public Bill getBillById(Integer id);

    //修改订单信息
    public int modify(Bill bill);

    //根据供应商ID删除订单信息
    public int deleteBillByProviderId(Integer providerId);

}
