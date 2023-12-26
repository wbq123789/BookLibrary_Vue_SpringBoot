/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.entity.view_obj.request;

import lombok.Data;

/**
 * @program: LibraryManagement_Vue_SpringBoot
 * @description: 用户借阅或归还书籍实体类
 * @author: 王贝强
 * @create: 2023-12-26 22:46
 **/
@Data
public class BorrowBookViewObj {
    String uid;
    String bid;
}
