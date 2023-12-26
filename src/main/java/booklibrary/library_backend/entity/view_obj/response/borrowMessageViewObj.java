/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.entity.view_obj.response;

import booklibrary.library_backend.entity.database_obj.Book;
import booklibrary.library_backend.entity.database_obj.Borrow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 借阅信息返回实体
 * @Author: 王贝强
 * @Date: 2023/12/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class borrowMessageViewObj {
    Integer bid;
    String author;
    String title;
    String desc;
    String label;
    Date time;

    public borrowMessageViewObj(Book book, Borrow borrow) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.bid = book.getBid();
        this.desc = book.getDesc();
        this.time = borrow.getBorrowTime();
        this.label=book.getLabel();
    }
}
