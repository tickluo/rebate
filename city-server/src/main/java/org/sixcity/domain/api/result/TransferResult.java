package org.sixcity.domain.api.result;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TransferResult {

    @NotBlank(message = "TransId不能为空")
    private Long TransId;
}
