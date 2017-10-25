package com.peilian.demo.v1.service;

import com.peilian.demo.v1.entity.Country;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class CountryServiceTest {
    @Autowired
    CountryService countryService;

    @Test
    public void testCurd() throws Exception {
        Country country=new Country();
        country.setCountryCode("TTT");
        country.setCountryName("测试");
        countryService.transaction(country);
    }
}
