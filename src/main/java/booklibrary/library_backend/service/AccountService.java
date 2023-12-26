/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.service;

import booklibrary.library_backend.entity.database_obj.Account;
import booklibrary.library_backend.entity.view_obj.request.ConfirmRestViewObj;
import booklibrary.library_backend.entity.view_obj.request.EmailRegisterViewObj;
import booklibrary.library_backend.entity.view_obj.request.EmailResetViewObj;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String Text);
    String registerEmailVerifyCode(String Type,String Email,String IP);
    String registerEmailAccount(EmailRegisterViewObj obj);
    String resetConfirm(ConfirmRestViewObj obj);
    String resetEmailAccountPassword(EmailResetViewObj obj);
    List<Account> findUserAccountList();
    String findUserCount();
}
