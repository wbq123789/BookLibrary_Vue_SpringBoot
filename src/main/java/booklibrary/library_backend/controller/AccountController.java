/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.controller;

import booklibrary.library_backend.entity.RestBean;
import booklibrary.library_backend.entity.database_obj.Account;
import booklibrary.library_backend.entity.view_obj.request.UidViewObj;
import booklibrary.library_backend.entity.view_obj.response.AccountViewObj;
import booklibrary.library_backend.entity.view_obj.response.borrowMessageViewObj;
import booklibrary.library_backend.service.AccountService;
import booklibrary.library_backend.service.BookService;
import booklibrary.library_backend.utils.JsonUtil;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 用户相关接口
 * @Author: 王贝强
 * @Date: 2023/12/26
 */
@Validated
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Resource
    AccountService accountService;
    @Resource
    BookService bookService;

    /**
     * @Description: 获取用户列表
     * @Param: null
     * @return: RestBean<java.lang.String>
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    @GetMapping("userList")
    public RestBean<String> getUserList() {
        List<Account> userAccountList = accountService.findUserAccountList();
        List<AccountViewObj> viewObjList=new ArrayList<>();
        userAccountList.forEach(account -> viewObjList.add(AccountViewObj.toAccount(account)));
        return RestBean.success(JsonUtil.toJson(viewObjList));
    }

    @GetMapping("findUserId")
    public RestBean<String> findUserIdByName(@RequestParam String UserName) {
        return RestBean.success(JsonUtil.toJson(accountService.findAccountByNameOrEmail(UserName).getId()));
    }

    /**
     * @Description: 获取用户总数
     * @Param: null
     * @return: RestBean<java.lang.String>
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    @GetMapping("userCount")
    public RestBean<String> getUserCount() {
        return RestBean.success(accountService.findUserCount());
    }

    /**
     * @Description: 获取当前用户借阅书籍信息列表
     * @Param: [uid]
     * @return: RestBean<java.lang.String>
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    @PostMapping("getBorrowMessage")
    public RestBean<String> getBorrowMessageById(@RequestBody @Validated UidViewObj uid) {
        List<borrowMessageViewObj> borrowbookList = bookService.getBorrowBookById(Integer.parseInt(uid.getUid()));
        if (borrowbookList.isEmpty())
            return RestBean.success();
        return RestBean.success(JsonUtil.toJson(borrowbookList));
    }
}
