package com.hzz.service.provider;

import com.hzz.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bosco
 * @date 2021/12/16
 */
public interface ProviderService {
    //增加用户信息
    public int add(Provider provider);

    //通过条件查询-providerList
    public List<Provider> getProviderList(String proName, String proCode, Integer currentPageNo, Integer pageSize);

    //获取供应商列表
    public List<Provider> getProList();

    //通过条件查询-供应商表记录数
    public int getProviderCount(String proName, String proCode);

    //通过供应商ID删除供应商信息
    public int deleteProviderById(Integer delId);

    //根据provider id 获取供应商信息
    public Provider getProviderById(Integer id);

    //修改供应商
    public int modify(Provider provider);
}
