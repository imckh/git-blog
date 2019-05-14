package com.imckh.gitblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private BigDecimal id;
    private String userName;
    private String password;
    private Timestamp createTime;
    private String status;
}
