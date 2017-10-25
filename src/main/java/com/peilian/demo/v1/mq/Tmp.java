package com.peilian.demo.v1.mq;

import lombok.Data;

@Data
public class Tmp {
    private Long userId;
    private Long classId;
    private Long initId;
    private String headInitPath;
    private String headPath;
    private String area;
    private Integer srcType;
    private String event;
}
