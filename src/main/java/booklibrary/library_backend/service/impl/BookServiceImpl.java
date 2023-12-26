/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.service.impl;

import booklibrary.library_backend.entity.database_obj.Book;
import booklibrary.library_backend.entity.database_obj.Borrow;
import booklibrary.library_backend.entity.view_obj.response.borrowMessageViewObj;
import booklibrary.library_backend.mapper.BookMapper;
import booklibrary.library_backend.mapper.BorrowMapper;
import booklibrary.library_backend.service.BookService;
import booklibrary.library_backend.service.BorrowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
* @Description: Mybatis_PlusService->BookService实现类
* @Author: 王贝强
* @Date: 2023/12/26
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper,Book> implements BookService {


    @Resource
    BorrowService borrowService;
    /**
    * @Description: 查询用户借阅的书籍信息列表
    * @Param: [user_id]
    * @return: List<borrowMessageViewObj>
    * @Author: 王贝强
    * @Date: 2023/12/26
    */
    @Override
    public List<borrowMessageViewObj> getBorrowBookById(int uid) {
        List<Borrow> list=borrowService.getBorrowListByUserId(uid);
        if (list==null) return null;
        List<Book> bookList=new ArrayList<>();
        List<borrowMessageViewObj> borrowList=new ArrayList<>();
        list.forEach(borrow-> bookList.add(this.getById(borrow.getBookId())));
        for (int i = 0; i < list.size(); i++) {
                borrowList.add(new borrowMessageViewObj(bookList.get(i),list.get(i)));
        }
        return borrowList;
    }
    /**
    * @Description: 获取数据库中书籍数量
    * @Param: null
    * @return: String
    * @Author: 王贝强
    * @Date: 2023/12/26
    */
    @Override
    public String getBookCount() {
        return String.valueOf(this.query().count());
    }
}
