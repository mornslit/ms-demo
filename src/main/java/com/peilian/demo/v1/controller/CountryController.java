package com.peilian.demo.v1.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.peilian.comm.exceptionhandling.BsException;
import com.peilian.demo.v1.entity.Country;
import com.peilian.demo.v1.service.CountryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 使用feign 调用ms-auth的功能,rpc调用
 *
 * @author fabletang
 * @date 2017/10/1
 */
@RestController //表明是返回 json格式
@RequestMapping("/v1") //全局url前缀
@Log4j2  //引用log组件,省略写代码
public class CountryController {

    //自动组装bean,并且 new 实例
    @Autowired
    private CountryService countryService;

    //只匹配 GET 方法的request
    @GetMapping("/countries")
    public List<Country> getAll() {

        //翻页功能，第一页,每页20条记录
        PageHelper.startPage(1, 20);

        List<Country> countries = countryService.findAll();

        log.debug("Total: " + ((Page) countries).getTotal());
        for (Country country : countries) {
            log.debug("Country Name: " + country.getCountryName());
        }
        return countries;
    }

    //只匹配 GET 方法的request
    @GetMapping("/country")
    public String getCountry() throws BsException {
        countryService.bsExcp();
        return "abc";
    }
}
