/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.controller;

import booklibrary.library_backend.entity.RestBean;
import booklibrary.library_backend.entity.view_obj.response.borrowMessageViewObj;
import booklibrary.library_backend.service.AccountService;
import booklibrary.library_backend.service.BookService;
import booklibrary.library_backend.utils.JsonUtil;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        return RestBean.success(JsonUtil.toJson(accountService.findUserAccountList()));
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
    @GetMapping("getBorrowMessage")
    public RestBean<String> getBorrowMessageById(@RequestParam String uid) {
        List<borrowMessageViewObj> borrowbookList = bookService.getBorrowBookById(Integer.parseInt(uid));
        return RestBean.success(JsonUtil.toJson(borrowbookList));
    }
}
