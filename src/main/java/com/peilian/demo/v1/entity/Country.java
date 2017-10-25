package com.peilian.demo.v1.entity;


import lombok.Data;

// `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
//         `countryname` varchar(255) DEFAULT NULL COMMENT '名称',
//         `countrycode` varchar(255) DEFAULT NULL COMMENT '代码',
//         PRIMARY KEY (`Id`)

/**
 * entity country
 *
 * @author fabletang
 * @date 2017/10/1
 */
@Data //省略 set get toString 的代码
public class Country {
    private Long id;
    private String countryName;
    private String countryCode;
    private Integer status;

//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
}
