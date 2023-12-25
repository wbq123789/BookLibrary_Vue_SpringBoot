package booklibrary.library_backend.controller;

import booklibrary.library_backend.entity.RestBean;
import booklibrary.library_backend.entity.view_obj.response.borrowMessageViewObj;
import booklibrary.library_backend.service.AccountService;
import booklibrary.library_backend.service.BookService;
import booklibrary.library_backend.utils.JsonUtil;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Resource
    AccountService accountService;
    @Resource
    BookService bookService;
    @GetMapping("userList")
    public RestBean<String> getUserList(){
       return RestBean.success(JsonUtil.toJson(accountService.findUserAccountList()));
    }
    @GetMapping("userCount")
    public RestBean<String> getUserCount(){
        return RestBean.success(accountService.findUserCount());
    }

    @GetMapping("getBorrowMessage")
    public RestBean<String> getBorrowMessageById(@RequestParam String uid){
        List<borrowMessageViewObj> borrowbookList = bookService.getBorrowBookById(Integer.parseInt(uid));
        return RestBean.success(JsonUtil.toJson(borrowbookList));
    }
}
