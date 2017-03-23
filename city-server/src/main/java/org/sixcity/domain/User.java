package org.sixcity.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class User {

    private Long id;
    private String username;
    private String appkey;
    private String accountType;
    private String companyName;
    private String actualName;
    private String phone;
    private BigDecimal amount;
    private String password;
    private String email;
    private List<String> roles;
    private Timestamp createTime;
    private Timestamp updateTime;
}

