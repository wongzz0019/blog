package com.hzz.service.bill;

import com.hzz.mapper.bill.BillMapper;
import com.hzz.pojo.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Bosco
 * @date 2021/12/16
 */

@Service
public class BillServiceImpl implements BillService{

    //service 调 mapper
    @Resource
    private BillMapper billMapper;

    public void setBillMapper(BillMapper billMapper) {
        this.billMapper = billMapper;
    }

    public int getBillCountByProviderId(Integer providerId) {
        return billMapper.getBillCountByProviderId(providerId);
    }

    public int add(Bill bill) {
        return billMapper.add(bill);
    }

    public List<Bill> getBillList(String provideName, Integer providerId, String isPayment, Integer currentPageNo, Integer pageSize) {
        return billMapper.getBillList(provideName, providerId, isPayment, currentPageNo, pageSize);
    }

    public int getBillCount(String productName, Integer providerId) {
        return billMapper.getBillCount(productName, providerId);
    }

    public int deleteBillById(Integer delId) {
        return billMapper.deleteBillById(delId);
    }

    public Bill getBillById(Integer id) {
        return billMapper.getBillById(id);
    }

    public int modify(Bill bill) {
        return billMapper.modify(bill);
    }

    public int deleteBillByProviderId(Integer providerId) {
        return billMapper.deleteBillByProviderId(providerId);
    }
}
