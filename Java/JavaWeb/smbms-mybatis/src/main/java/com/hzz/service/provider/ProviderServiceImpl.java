package com.hzz.service.provider;

import com.hzz.mapper.provider.ProviderMapper;
import com.hzz.pojo.Provider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Bosco
 * @date 2021/12/16
 */
@Service
public class ProviderServiceImpl implements ProviderService{

    @Resource
    private ProviderMapper providerMapper;

    public void setProviderMapper(ProviderMapper providerMapper) {
        this.providerMapper = providerMapper;
    }

    public int add(Provider provider) {
        return providerMapper.add(provider);
    }

    public List<Provider> getProviderList(String proName, String proCode, Integer currentPageNo, Integer pageSize) {
        return providerMapper.getProviderList(proName, proCode, currentPageNo, pageSize);
    }

    public List<Provider> getProList() {
        return providerMapper.getProList();
    }

    public int getProviderCount(String proName, String proCode) {
        return providerMapper.getProviderCount(proName, proCode);
    }

    public int deleteProviderById(Integer delId) {
        return providerMapper.deleteProviderById(delId);
    }

    public Provider getProviderById(Integer id) {
        return providerMapper.getProviderById(id);
    }

    public int modify(Provider provider) {
        return providerMapper.modify(provider);
    }
}
