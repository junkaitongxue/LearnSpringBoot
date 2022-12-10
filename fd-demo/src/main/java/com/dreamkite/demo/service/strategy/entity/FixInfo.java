package com.dreamkite.demo.service.strategy.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FixInfo {
    String fixId;

    String fixType;

    LocalDateTime createTime;
}
