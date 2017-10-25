package com.peilian.demo.v1.service;

import com.peilian.comm.exceptionhandling.BsError;
import com.peilian.comm.exceptionhandling.BsException;
import com.peilian.demo.v1.entity.Country;
import com.peilian.demo.v1.mapper.CountryMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * service
 *
 * @author fabletang
 * @date 2017/10/1
 */
@Service //表明是 service 可以供 controller @AutoWired
@Log4j2
public class CountryService {
    //    @Autowired
//    RestTemplate restTemplate;
    @Autowired
    private CountryMapper countryMapper;
    //自动装配 mapper

    public List<Country> findAll() {
        //do other business
        return countryMapper.getAll();
    }

    public void bsExcp() throws BsException {
        //do something
        throw new BsException(this.getClass(), new BsError("999999", "国家不存在"));
    }
    //事务处理，定位到方法。
    @Transactional
    public void transaction(Country country) {
        int num1 = 0;
        Country tmp = null;
        int num3 = 0;
        try {
            num1 = countryMapper.insert(country);
            tmp = countryMapper.getById(country.getId());
            num3 = countryMapper.insert(tmp);
        } catch (Exception e) {
            log.error(" ## transaction() error:" + e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
        } finally {
            if (num1 == 0) {
                log.error(" ## 1 transaction() 第一次插入出错,回滚:");
                return;
            }
            if (tmp == null) {
                log.error(" ## 2 transaction() transaction() 查找出错,回滚:");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
                return;
            }
            if (num3 == 0) {
                log.error(" ## 3 transaction() 第二次插入出错,回滚:");
            }
        }
    }
}

