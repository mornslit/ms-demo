package com.peilian.demo.v1.mapper;

import com.peilian.demo.v1.entity.Country;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper //表明使用 mybatis ORM 插件
/**
 * mapper
 *
 * @author fabletang
 * @date 2017/10/1
 */
public interface CountryMapper {
    /**
     * 获取country列表
     * @return List<Country>
     */
    List<Country> getAll();

    /**
     * 插入一个country
     * @param country
     * @return Integer 1 表示成功
     */
    Integer insert(Country country);

    /**
     * 删除一个country 通过id值
     * @param id Long
     * @return Integer 1 表示成功
     */
    Integer deleteById(Long id);

    /**
     * 根据Id查找Country
     * @param id
     * @return 单个 country
     */
    Country getById(Long id);
}
