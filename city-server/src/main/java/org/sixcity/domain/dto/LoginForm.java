package org.sixcity.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginForm {

    @NotBlank(message = "用户名不能为空")
    @Size(max = 10, min = 4, message = "用户名为4-10个英文字母或者数字")
    private String username;

    @NotNull(message = "密码不能为空")
    @Size(max = 18, min = 6, message = "密码为6-18个英文字母或者数字")
    private String password;

    @NotBlank(message = "验证码不能为空")
    @Size(max = 5, min = 5, message = "验证码为5位")
    private String code;
}
