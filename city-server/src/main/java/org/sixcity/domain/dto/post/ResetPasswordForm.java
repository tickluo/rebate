package org.sixcity.domain.dto.post;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ResetPasswordForm {
    @NotNull(message = "密码不能为空")
    @Size(max = 18, min = 6, message = "密码为6-18个英文字母或者数字")
    private String oldPassword;

    @NotNull(message = "密码不能为空")
    @Size(max = 18, min = 6, message = "密码为6-18个英文字母或者数字")
    private String newPassword;
}
