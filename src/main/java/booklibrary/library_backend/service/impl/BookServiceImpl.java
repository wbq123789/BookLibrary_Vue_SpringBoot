package booklibrary.library_backend.service.impl;

import booklibrary.library_backend.entity.database_obj.Book;
import booklibrary.library_backend.entity.database_obj.Borrow;
import booklibrary.library_backend.entity.view_obj.response.borrowMessageViewObj;
import booklibrary.library_backend.mapper.BookMapper;
import booklibrary.library_backend.mapper.BorrowMapper;
import booklibrary.library_backend.service.BookService;
import booklibrary.library_backend.service.BorrowService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper,Book> implements BookService {


    @Resource
    BorrowMapper borrowMapper;

    @Override
    public List<borrowMessageViewObj> getBorrowBookById(int uid) {
        List<Borrow> list=borrowMapper.getListBorrowByUid(uid);
        if (list==null) return null;
        List<Book> bookList=new ArrayList<>();
        List<borrowMessageViewObj> borrowList=new ArrayList<>();
        list.forEach(borrow-> bookList.add(this.getById(borrow.getBookId())));
        list.forEach(borrow -> bookList
                .forEach(book -> borrowList
                        .add(new borrowMessageViewObj(book,borrow))));
        return borrowList;
    }

    @Override
    public String getBookCount() {
        return String.valueOf(this.query().count());
    }
}
