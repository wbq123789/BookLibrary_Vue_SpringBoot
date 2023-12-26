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

@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {
    @Resource
    AccountService accountService;
    @GetMapping("/ask-code")
    public RestBean<Void> askVerifyCode(@RequestParam @Email String email,
                                        @RequestParam @Pattern(regexp = "(register|reset)") String type,
                                        HttpServletRequest request){
        return this.messageHandle(()->accountService.
                registerEmailVerifyCode(type,email,request.getRemoteAddr()));
    }
    @PostMapping("/register")
    public  RestBean<Void> register(@RequestBody @Validated EmailRegisterViewObj obj){
        return this.messageHandle(()->accountService.registerEmailAccount(obj));
    }
    @PostMapping("/reset-confirm")
    public RestBean<Void> resetConfirm(@RequestBody @Validated ConfirmRestViewObj obj){
        return this.messageHandle(()->accountService.resetConfirm(obj));
    }
    @PostMapping("/reset-password")
    public RestBean<Void> resetPassword(@RequestBody @Validated EmailResetViewObj obj){
        return this.messageHandle(()->accountService.resetEmailAccountPassword(obj));
    }

    private RestBean<Void> messageHandle(Supplier<String> action){
        String message=action.get();
        return message==null ? RestBean.success() : RestBean.failure(400,message);
    }
}

