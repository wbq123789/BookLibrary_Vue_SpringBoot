/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.entity.view_obj.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: LibraryManagement_Vue_SpringBoot
 * @description: 添加书籍实体类
 * @author: 王贝强
 * @create: 2023-12-28
 **/
@Data
@AllArgsConstructor
public class BookViewObj {
    String title;
    String author;
    String desc;
    String label;
}
