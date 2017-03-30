package org.sixcity.domain.dto.post;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.sixcity.util.custom.validator.Email;
import org.sixcity.util.custom.validator.Phone;

@Data
public class UserInfoForm {

    @NotBlank(message = "手机号不能为空")
    @Phone(message = "非法手机号格式")
    private String phone;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "非法邮箱格式")
    private String email;
}