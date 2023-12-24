package booklibrary.library_backend.service.impl;

import booklibrary.library_backend.entity.database_obj.Book;
import booklibrary.library_backend.entity.database_obj.Borrow;
import booklibrary.library_backend.mapper.BookMapper;
import booklibrary.library_backend.service.BookService;
import booklibrary.library_backend.service.BorrowService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper,Book> implements BookService {

    @Resource
    BorrowService borrowService;
    @Override
    public List<Book> getBookListNotBorrow() {

        List<Borrow> list = borrowService.query().select("book_id").list();

        System.out.println(list);
        return null;
    }
}
