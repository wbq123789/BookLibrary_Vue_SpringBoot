/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.entity.view_obj.response;

import booklibrary.library_backend.entity.database_obj.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @program: LibraryManagement_Vue_SpringBoot
 * @description: 返回用户信息实体类
 * @author: 王贝强
 * @create: 2023-12-26
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AccountViewObj {
    String id;
    String username;
    String email;
    Date register_time;
    public static AccountViewObj toAccount(Account account){
        return new AccountViewObj()
                .setId(String.valueOf(account.getId()))
                .setUsername(account.getUsername())
                .setEmail(account.getEmail())
                .setRegister_time(account.getRegister_time());
    }
}
