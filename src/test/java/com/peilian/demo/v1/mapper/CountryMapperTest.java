package com.peilian.demo.v1.mapper;

import com.peilian.demo.v1.entity.Country;
import io.shardingjdbc.core.api.HintManager;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class CountryMapperTest {

    @Autowired
    private CountryMapper countryMapper;

    @Before
    public void init() {
    }
 @Test
    public void testlave() throws SQLException {
//        HintManager hintManager = HintManager.getInstance();
//        hintManager.setMasterRouteOnly();
        List<Country> countries = countryMapper.getAll();
        System.out.println("--testSlave-");
        System.out.println(countries);
        assertNotNull(countries);
        assertTrue(countries.size()==0);
    }
    @Test
    public void testMasterSlave() throws SQLException {
        //强制切换到 主数据库
        HintManager hintManager = HintManager.getInstance();
        hintManager.setMasterRouteOnly();
        List<Country> countries = countryMapper.getAll();
        System.out.println("--testMasterSlave-");
        assertNotNull(countries);
        assertTrue(countries.size()>0);
        System.out.println(countries);
    }
    @Test
    public void testCURD() throws SQLException {
        Country country=new Country();
        country.setCountryCode("abc1111");
        country.setCountryName("test");
//        country.setStatus(0);
        Integer num=countryMapper.insert(country);
        assertNotNull(country.getId());
        assertTrue(num==1);
        System.out.println(country);
        List<Country> countries = countryMapper.getAll();
        assertNotNull(countries);
        System.out.println("countries.size()="+countries.size());
        System.out.println(countries);
        num=countryMapper.deleteById(country.getId());
        assertNotNull(num);
        assertTrue(num==1);
    }
}
