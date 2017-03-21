package org.sixcity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class User {
    @JsonIgnore
    private Long id;
    private String username;
    private String appkey;
    private String accountType;
    private String companyName;
    private String actualName;
    private String phone;
    private BigDecimal amount;
    @JsonIgnore
    private String password;
    private String email;
    @JsonIgnore
    private List<String> roles;
    private Timestamp createTime;
    private Timestamp updateTime;
}

