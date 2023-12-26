/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.controller;

import booklibrary.library_backend.entity.RestBean;
import booklibrary.library_backend.entity.view_obj.request.ConfirmRestViewObj;
import booklibrary.library_backend.entity.view_obj.request.EmailRegisterViewObj;
import booklibrary.library_backend.entity.view_obj.request.EmailResetViewObj;
import booklibrary.library_backend.service.AccountService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;

/**
 * @Description: 登录、登出、注册、重置密码权限校验接口
 * @Author: 王贝强
 * @Date: 2023/12/26
 */
@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {
    @Resource
    AccountService accountService;

    /**
     * @Description: 请求验证码接口
     * @Param: [email, type, request]
     * @return: booklibrary.library_backend.entity.RestBean<java.lang.Void>
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    @GetMapping("/ask-code")
    public RestBean<Void> askVerifyCode(@RequestParam @Email String email,
                                        @RequestParam @Pattern(regexp = "(register|reset)") String type,
                                        HttpServletRequest request) {
        return this.messageHandle(() -> accountService.
                registerEmailVerifyCode(type, email, request.getRemoteAddr()));
    }

    /**
     * @Description: 注册用户接口
     * @Param: EmailRegisterViewObj
     * @return: booklibrary.library_backend.entity.RestBean<java.lang.Void>
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    @PostMapping("/register")
    public RestBean<Void> register(@RequestBody @Validated EmailRegisterViewObj obj) {
        return this.messageHandle(() -> accountService.registerEmailAccount(obj));
    }

    /**
     * @Description: 校验验证码接口
     * @Param: ConfirmRestViewObj
     * @return: booklibrary.library_backend.entity.RestBean<java.lang.Void>
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    @PostMapping("/reset-confirm")
    public RestBean<Void> resetConfirm(@RequestBody @Validated ConfirmRestViewObj obj) {
        return this.messageHandle(() -> accountService.resetConfirm(obj));
    }

    /**
     * @Description: 重置密码接口
     * @Param: EmailResetViewObj
     * @return: booklibrary.library_backend.entity.RestBean<java.lang.Void>
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    @PostMapping("/reset-password")
    public RestBean<Void> resetPassword(@RequestBody @Validated EmailResetViewObj obj) {
        return this.messageHandle(() -> accountService.resetEmailAccountPassword(obj));
    }

    /**
     * @Description: 信息返回校验
     * @Param: [action]
     * @return: booklibrary.library_backend.entity.RestBean<java.lang.Void>
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    private RestBean<Void> messageHandle(Supplier<String> action) {
        String message = action.get();
        return message == null ? RestBean.success() : RestBean.failure(400, message);
    }
}

