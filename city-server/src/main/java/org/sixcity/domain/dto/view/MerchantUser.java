package org.sixcity.domain.dto.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.DataEntity;
import org.hibernate.validator.constraints.NotBlank;
import org.sixcity.util.custom.validator.Email;
import org.sixcity.util.custom.validator.PersonName;
import org.sixcity.util.custom.validator.Phone;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class MerchantUser extends DataEntity {
    @NotBlank(message = "用户名不能为空")
    @Size(max = 10, min = 4, message = "用户名为4-10个英文字母或者数字")
    private String username;

    @NotBlank(message = "appId不能为空")
    private String appId;

    @NotBlank(message = "账户类型不能为空")
    private String accountType;

    private String companyName;

    @NotBlank(message = "真实姓名不能为空")
    @PersonName(message = "非法真实姓名")
    private String actualName;

    @NotBlank(message = "手机号不能为空")
    @Phone(message = "非法手机号格式")
    private String phone;

    private BigDecimal amount = new BigDecimal("0.00");

    @NotNull(message = "密码不能为空")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "非法邮箱格式")
    private String email;

    @NotBlank(message = "用户权限不能为空")
    private String roles;

    @NotBlank(message = "key不能为空")
    private String appKey;

    @NotBlank(message = "秘钥不能为空")
    private String appSecret;
}
