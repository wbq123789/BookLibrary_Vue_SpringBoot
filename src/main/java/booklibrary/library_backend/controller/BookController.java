/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.controller;

import booklibrary.library_backend.entity.RestBean;
import booklibrary.library_backend.entity.database_obj.Book;
import booklibrary.library_backend.entity.database_obj.Borrow;
import booklibrary.library_backend.service.BookService;
import booklibrary.library_backend.service.BorrowService;
import booklibrary.library_backend.utils.JsonUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Validated
@RestController
@RequestMapping("/api/book")
public class BookController {
    @Resource
    BookService bookService;
    @Resource
    BorrowService borrowService;

    @PostMapping("borrowBook")
    public RestBean<String> borrowBookById(@RequestParam String uid,
                                           @RequestParam String bid){
        Borrow borrow=new Borrow();
        borrow.setBookId(Integer.valueOf(bid))
                .setUserId(Integer.valueOf(uid))
                .setBorrowTime(new Date());
        boolean save = borrowService.save(borrow);
        if (save) return RestBean.success("借阅成功");
        else return RestBean.failure(401,"服务器错误");
    }
    @PostMapping("returnBook")
    public RestBean<String> returnBookById(@RequestParam String uid,
                                           @RequestParam String bid){
        QueryWrapper<Borrow> wrapper =new QueryWrapper<>();
        wrapper.select().eq("user_id",uid).eq("book_id",bid);
        boolean remove = borrowService.remove(wrapper);
        if (!remove) return RestBean.failure(402,"借阅信息不存在");
        else return RestBean.success("书籍归还成功");
    }
    @GetMapping("/bookCount")
    public RestBean<String> getBookCount(){
        return RestBean.success(bookService.getBookCount());
    }
    @GetMapping("/bookList")
    public RestBean<String> getBookListByType(@RequestParam @Pattern(regexp = "(book|c_borrow|n_borrow)")String type){
        if(Objects.equals(type, "book")){
            List<Book> bookList = bookService.list();
            return RestBean.success(JsonUtil.toJson(bookList));
        }else {
            List<Borrow> borrowsBookList = borrowService.list();
            List<Integer> borrows = borrowsBookList
                    .stream()
                    .map(Borrow::getBookId)
                    .toList();
            List<Book> bookList = bookService.list();
            if (Objects.equals(type, "c_borrow")) {
                List<Book> c_borrow = bookList
                        .stream()
                        .filter(book -> !borrows.contains(book.getBid()))
                        .toList();
                return RestBean.success(JsonUtil.toJson(c_borrow));
            } else {
                List<Book> n_borrow = bookList
                        .stream()
                        .filter(book -> borrows.contains(book.getBid()))
                        .toList();
                return RestBean.success(JsonUtil.toJson(n_borrow));
            }
        }
    }
}
