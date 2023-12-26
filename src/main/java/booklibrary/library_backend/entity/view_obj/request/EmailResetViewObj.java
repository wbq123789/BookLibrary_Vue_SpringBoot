/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.entity.view_obj.request;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @Description: 请求重置密码实体
 * @Author: 王贝强
 * @Date: 2023/12/26
 */
@Data
public class EmailResetViewObj {
    @Email
    String email;
    @Length(min = 6, max = 6)
    String code;
    @Length(min = 6, max = 20)
    String password;
}
